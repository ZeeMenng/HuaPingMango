package com.zee.app.extend.swagger.mf;

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

import com.zee.app.generate.swagger.mf.MfHpFluctGenSwgApp;
import com.zee.bll.extend.unity.mf.MfHpFluctUntBll;
import com.zee.bll.extend.unity.mf.MfMarketPriceCrawUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.TimesView;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-25 17:07:14
 * @description 价格周期性波动规律分析 对外接口，扩展自MfHpFluctGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfHpFluct")
public class  MfHpFluctSwgApp extends  MfHpFluctGenSwgApp {

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "预测", notes = "预测")
	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel forecast() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                      ");
		selectBuffer.append("		A.id id,                                                ");
		selectBuffer.append("		A.region_code regionCode,                               ");
		selectBuffer.append("		A.region_name regionName,                               ");
		selectBuffer.append("		IFNULL(A.dately_price,0) datelyPrice,                   ");
		selectBuffer.append("		IFNULL(A.trend,0) trend,                                ");
		selectBuffer.append("		IFNULL(A.undulation,0) undulation,                      ");
		selectBuffer.append("		A.date_time dateTime                                    ");
		selectBuffer.append("	FROM                                                        ");
		selectBuffer.append("		mf_hp_fluct A                                           ");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = mfHpFluctUntBll.getListBySQL(map);
		
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		
		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("regionCode");
		keySet.add("regionName");
		keySet.add("datelyPrice");
		keySet.add("trend");
		keySet.add("undulation");
		keySet.add("dateTime");
		
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");
		
		return TimesView.getTimesData(pmap, modelList,keySet);
	}
	
	@ApiOperation(value = "查询", notes = "市场价格-波动规律-全国芒果批发价格长期波动规律")
	@RequestMapping(value = "/getPriceTrend", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceTrend() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		
	 	//全国芒果批发价格长期波动规律
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append(" select t0.date_time,t0.dately_price,t0.trend from (select t.* from mf_hp_fluct t where t.region_name like '%全国%' order by substr(t.date_time,1,10) desc limit 200) t0");
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
		if (jsonObject.containsKey("orderList")) {
			JSONArray orderListArray = jsonObject.getJSONArray("orderList");
			if (orderListArray.size() != 0)
				selectBuffer.append(" order by ");
			for (int i = 0; i < orderListArray.size(); i++) {
				JSONObject orderColumnObject = orderListArray.getJSONObject(i);
				selectBuffer.append(orderColumnObject.getString("columnName"));
				selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
				selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
			}
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = mfHpFluctUntBll.getListBySQL(map);
			
		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "市场价格-波动规律-全国芒果批发价格短周期波动")
	@RequestMapping(value = "/getPriceShortPeriod", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceShortPeriod() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		
	 	//全国芒果批发价格短周期波动
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append(" select t0.date_time,t0.undulation from (select t.* from mf_hp_fluct t where t.region_name like '%全国%' order by substr(t.date_time,1,10) desc limit 200) t0");
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
		if (jsonObject.containsKey("orderList")) {
			JSONArray orderListArray = jsonObject.getJSONArray("orderList");
			if (orderListArray.size() != 0)
				selectBuffer.append(" order by ");
			for (int i = 0; i < orderListArray.size(); i++) {
				JSONObject orderColumnObject = orderListArray.getJSONObject(i);
				selectBuffer.append(orderColumnObject.getString("columnName"));
				selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
				selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
			}
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = mfHpFluctUntBll.getListBySQL(map);
			
		return resultModel;
	}
}



