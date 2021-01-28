package com.zee.app.extend.swagger.mf;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfForreturnGenSwgApp;
import com.zee.bll.extend.unity.mf.MfForpriceUntBll;
import com.zee.bll.extend.unity.mf.MfForreturnUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.TimesView;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-29 15:47:04
 * @description 批发价格波动性预测 对外接口，扩展自MfForreturnGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfForreturn")
public class  MfForreturnSwgApp extends  MfForreturnGenSwgApp {
	
	@Autowired
	@Qualifier("mfForreturnUntBll")
	protected MfForreturnUntBll mfForreturnUntBll;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "预测", notes = "预测")
	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel forecast() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionCode = "";   	//地区code 0：全国  530723：华坪县
		String regionName = "";		//地区name 0：全国   530723：华坪县
		
		 if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);

				if (jsonObject.containsKey("entityRelated")) {
					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
					if (entityRelatedObject.containsKey("regionCode") && StringUtils.isNotBlank(entityRelatedObject.getString("regionCode")))
						regionCode = entityRelatedObject.getString("regionCode");
					if (entityRelatedObject.containsKey("regionName") && StringUtils.isNotBlank(entityRelatedObject.getString("regionName")))
						regionName = entityRelatedObject.getString("regionName");
				}
				
			}
		 
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                     ");
		selectBuffer.append("		A.id id,                                               ");
		selectBuffer.append("		A.region_code regionCode,                              ");
		selectBuffer.append("		A.region_name regionName,                              ");
		selectBuffer.append("		IFNULL(A.actual_amount,0) actualAmount,                ");
		selectBuffer.append("		IFNULL(A.forecast_amount,0) forecastAmount,            ");
		selectBuffer.append("		A.date_time dateTime                                   ");
		selectBuffer.append("	FROM                                                       ");
		selectBuffer.append("		mf_forreturn A                                         ");
		selectBuffer.append("		where A.region_code = '"+regionCode+"'                  ");

		map.put("Sql", selectBuffer.toString());
		resultModel = mfForreturnUntBll.getListBySQL(map);
		
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		
		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("regionCode");
		keySet.add("regionName");
		keySet.add("actualAmount");
		keySet.add("forecastAmount");
		keySet.add("dateTime");
		
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "date");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "6");
		pmap.put("afterNum", "0");
		
		return TimesView.getTimesData(pmap, modelList,keySet);
	}
	
	@ApiOperation(value = "查询", notes = "市场价格-价格预测-全国芒果批发价格波动性预测")
	@RequestMapping(value = "/getPriceReturn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceReturn() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String regionCode = "";   	//地区code 0：全国, 530723：华坪县
		String regionName = "";		//地区name 0：全国, 530723：华坪县
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("regionCode") && StringUtils.isNotBlank(entityRelatedObject.getString("regionCode")))
				regionCode = entityRelatedObject.getString("regionCode");
			if (entityRelatedObject.containsKey("regionName") && StringUtils.isNotBlank(entityRelatedObject.getString("regionName")))
				regionName = entityRelatedObject.getString("regionName");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		
	 	//未来一周芒果批发价格预测
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append(" select t0.dateTime,t1.actual_amount,t1.forecast_amount from ( ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE())-42 DAY) dateTime from dual UNION ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE())-35 DAY) dateTime from dual UNION ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE())-28 DAY) dateTime from dual UNION ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE())-21 DAY) dateTime from dual UNION ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE())-14 DAY) dateTime from dual UNION ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE())-7 DAY) dateTime from dual UNION ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE()) DAY) dateTime from dual UNION ");
		selectBuffer.append(" select DATE_ADD(DATE_FORMAT(CURRENT_DATE(),'%Y%m%d'),INTERVAL -WEEKDAY(CURRENT_DATE())+7 DAY) dateTime from dual ) t0 ");
		selectBuffer.append(" left join (select t.* from mf_forreturn t where t.region_code = '"+regionCode+"' order by substr(t.date_time,1,10) ) t1 ");
		selectBuffer.append(" on t0.dateTime = t1.date_time order by dateTime asc ");
		
		
		map.put("Sql", selectBuffer.toString());
		resultModel = mfForreturnUntBll.getListBySQL(map);
		maps.put("list", (List<Map<String, Object>>)resultModel.getData());// 时间
		
		Map<String, List<String>> xMap = new HashMap<String, List<String>>();
    	List<String> timeList = new ArrayList<String>();
    	List<String> valueList = new ArrayList<String>();
    	int i = 0;
    	for (Map<String, Object> map1 : maps.get("list") ) {
    		i++;
    		if(i<=7){
        		timeList.add(map1.get("dateTime").toString());
        		valueList.add(map1.get("actual_amount").toString());
    		}else{
        		timeList.add(map1.get("dateTime").toString());
        		valueList.add(map1.get("forecast_amount").toString());
    		}
		}
    	xMap.put("time", timeList);
    	xMap.put("value", valueList);
    	
        resultModel.setData(xMap);
			
		return resultModel;
	}
	
//	@ApiOperation(value = "查询", notes = "市场价格-价格预测-全国芒果批发价格波动性预测")
//	@RequestMapping(value = "/getPriceReturn", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultModel getPriceReturn() {
//		ResultModel resultModel = new ResultModel();
//
//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//		
//		String regionCode = "";   	//地区code 1：全国
//		String regionName = "";		//地区name 1：全国
//		
//		JSONObject jsonObject = JSONObject.fromObject(jsonData);
//
//		if (jsonObject.containsKey("entityRelated")) {
//			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
//			if (entityRelatedObject.containsKey("regionCode") && StringUtils.isNotBlank(entityRelatedObject.getString("regionCode")))
//				regionCode = entityRelatedObject.getString("regionCode");
//			if (entityRelatedObject.containsKey("regionName") && StringUtils.isNotBlank(entityRelatedObject.getString("regionName")))
//				regionName = entityRelatedObject.getString("regionName");
//		}
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
//		
//	 	//全国芒果批发价格波动性预测
//		StringBuffer selectBuffer = new StringBuffer();
//		
//		selectBuffer.append(" select t0.date_time,t0.price value from (select t.* from mf_forreturn t where t.region_name  like '%"+regionName+"%' order by substr(t.date_time,1,10) desc limit 27) t0");
//		
//		if (jsonObject.containsKey("page")) {
//			JSONObject pageObject = jsonObject.getJSONObject("page");
//			map.put("Page", pageObject);
//		}
//		if (jsonObject.containsKey("orderList")) {
//			JSONArray orderListArray = jsonObject.getJSONArray("orderList");
//			if (orderListArray.size() != 0)
//				selectBuffer.append(" order by ");
//			for (int i = 0; i < orderListArray.size(); i++) {
//				JSONObject orderColumnObject = orderListArray.getJSONObject(i);
//				selectBuffer.append(orderColumnObject.getString("columnName"));
//				selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
//				selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
//			}
//		}
//		
//		map.put("Sql", selectBuffer.toString());
//		resultModel = mfForreturnUntBll.getListBySQL(map);
//			
//		return resultModel;
//	}

}



