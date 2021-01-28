package com.zee.app.extend.swagger.mf;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfFreshVolumeGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.TimesView;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-29 15:47:04
 * @description mf_fresh_volume 对外接口，扩展自MfFreshVolumeGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfFreshVolume")
public class  MfFreshVolumeSwgApp extends  MfFreshVolumeGenSwgApp {
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "预测", notes = "预测")
	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel forecast() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = "";//查询类型 1：消费量，2：消费金额。
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
				queryType = entityRelatedObject.getString("queryType");
		}
				
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                        "); 
		selectBuffer.append("		A.id id,                                                  "); 
		selectBuffer.append("		IFNULL(A.actual_amount,0) actualAmount,                   "); 
		selectBuffer.append("		IFNULL(A.forecast_amount,0) forecastAmount,               "); 
		selectBuffer.append("		IFNULL(A.actual_volume,0) actualVolume,                   "); 
		selectBuffer.append("		IFNULL(A.forecast_volume,0) forecastVolume,               "); 
		selectBuffer.append("		A.date_time dateTime                                      "); 
		selectBuffer.append("	FROM                                                          "); 
		selectBuffer.append("		mf_fresh_volume A                                         "); 
		
		map.put("Sql", selectBuffer.toString());
		resultModel = mfFreshVolumeUntBll.getListBySQL(map);
		
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		
		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("actualAmount");
		keySet.add("forecastAmount");
		keySet.add("actualVolume");
		keySet.add("forecastVolume");
		keySet.add("dateTime");
		
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "1");
		
		resultModel = TimesView.getTimesData(pmap, modelList,keySet);
		List<Map<String, Object>> modelLists =(List<Map<String, Object>>) resultModel.getData();
//		List<Object> list;
		Map<String, Object> listmap;
		List<Object> mfFreshList = new ArrayList<Object>();
		Map<String, Object> xmap = new HashMap<String, Object>();
		int i = 0;
		//查询类型 1：消费量，2：消费金额。
		if(queryType.equals("1")){
			for(Map<String, Object> maps :modelLists){
				i++;
				listmap  = new HashMap<String, Object>();
				if(i<=6){
					listmap.put("time",(String) maps.get("dateTime"));
					listmap.put("value", maps.get("actualVolume"));
				}else{
					listmap.put("time",(String) maps.get("dateTime"));
					listmap.put("value", maps.get("forecastVolume"));
				}
				mfFreshList.add(listmap);
			}
		}else if(queryType.equals("2")){
			for(Map<String, Object> maps :modelLists){
				i++;
				listmap  = new HashMap<String, Object>();
				if(i<=6){
					listmap.put("time",(String) maps.get("dateTime"));
					listmap.put("value", maps.get("actualAmount"));
				}else{
					listmap.put("time",(String) maps.get("dateTime"));
					listmap.put("value", maps.get("forecastAmount"));
				}
				mfFreshList.add(listmap);
			}
		}
		xmap.put("mfFreshList", mfFreshList);
		resultModel.setData(xmap);
		return resultModel;
	}
}



