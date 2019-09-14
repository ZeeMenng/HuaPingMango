package com.jusfoun.app.extend.swagger.mf;

import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.mf.MfWeatherGenSwgApp;
import com.jusfoun.bll.extend.unity.da.DaIotMonitorDataUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.MongoUtil;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.TimesView;



/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  对外接口，扩展自MfWeatherGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfWeather")
public class  MfWeatherSwgApp extends  MfWeatherGenSwgApp {
	
	double airTemperature  = 37.00;	
	double dayRain = 50.00;
	
	@Autowired
	@Qualifier("daIotMonitorDataUntBll")
	protected DaIotMonitorDataUntBll daIotMonitorDataUntBll;
	
	@Autowired
	private MongoUtil mongoUtil;
	
	/**
	 * 
	 * @title: forecast
	 * @description: 气象角度预测
	 * @author: lxl
	 * @date:2018年5月30日 下午11:22:00
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "预测", notes = "预测")
	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel forecast() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String weatherType = "";//类型        0-温度（770）；1- 降雨量（780）
		String sensorType = "";
		double warningNum = 0.00;
		String warningMsg = "0";
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("sensorType") && StringUtils.isNotBlank(entityRelatedObject.getString("sensorType")))
					sensorType = entityRelatedObject.getString("sensorType");
				if(sensorType.equals("0")){
					sensorType = "770";
					warningNum = airTemperature;
				}else{
					sensorType = "797";
					warningNum = dayRain;
				}
			}
		}
		
		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "date");//视图名，年year，月month，日date，hour时（默认为年）
		timeMap.put("hasCurrent", "true");
		timeMap.put("pastNum", "7");
		timeMap.put("afterNum", "1");//mongodb少8个小时，后期存时间字符串
		List<String> timesList = TimesView.getTimesView(timeMap);
		
		Document sub_match = new Document();
		sub_match.put("time", new Document("$gte", DateUtils.string2Date(timesList.get(0), "yyyy-MM-dd"))
										.append("$lte", DateUtils.string2Date(timesList.get(timesList.size()-1), "yyyy-MM-dd")));
		sub_match.put("sensorType", Integer.valueOf(sensorType));

		Document _id = new Document();
		_id.put("$dateToString", new Document("format", "%Y-%m-%d").append("date", "$time"));
		Document maxVal = new Document();
		maxVal.put("$max", "$val");
		Document sub_group = new Document();
		sub_group.put("_id", _id);
		sub_group.put("maxVal", maxVal);
		
		Document sub_sort = new Document();
		sub_sort.put("_id", 1);
		
		Document match = new Document("$match", sub_match);
		Document group = new Document("$group", sub_group);
		Document sort = new Document("$sort", sub_sort);
		List<Document> aggregateList = new ArrayList<Document>();
		aggregateList.add(match);
		aggregateList.add(group);
		aggregateList.add(sort);
		List<Map<String, Object>> dataList = mongoUtil.queryForAggregate("DaIotMonitorData", aggregateList);
		
		List<String> dateTimeList = new ArrayList<String>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		for (Map<String, Object> map : dataList) {
			dateTimeList.add(map.get("_id").toString());
			dataMap.put(map.get("_id").toString(), map.get("maxVal").toString());
		}
		
		List<Object> weatherThresholdList = new ArrayList<Object>();
		List<Object> warningNumList = new ArrayList<Object>();
		
		
		int serialNum = 0;
		for (String str : dateTimeList) {
			weatherThresholdList.add(dataMap.get(str).toString());
			warningNumList.add(warningNum);
			if(Double.valueOf(dataMap.get(str).toString()) > warningNum){
				serialNum ++;
			}else{
				serialNum = 0;
			}
		}
		
		if(serialNum >= 3){
			warningMsg = "1";
		}
		
		Map<String, Object> xmap = new HashMap<String, Object>();
		xmap.put("dateTime", dateTimeList);
		xmap.put("weatherThresholdList", weatherThresholdList);
		xmap.put("warningNum",warningNumList);
		xmap.put("warningMsg",warningMsg);
		resultModel.setData(xmap);
		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
//	@SuppressWarnings("unchecked")
//	@ApiOperation(value = "预测", notes = "预测")
//	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultModel forecast() {
//		ResultModel resultModel = new ResultModel();
//
//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		StringBuffer selectBuffer = new StringBuffer();
//		selectBuffer.append("	SELECT                                                          ");
//		selectBuffer.append("		A.id id,                                                    ");
//		selectBuffer.append("		IFNULL(A.warning_num,0) warningNum,                         ");
//		selectBuffer.append("		IFNULL(A.weather_threshold,0) weatherThreshold,             ");
//		selectBuffer.append("		A.date_time dateTime                                        ");
//		selectBuffer.append("	FROM                                                            ");
//		selectBuffer.append("		mf_weather A                                                ");
//
//		
//		Set<String> keySet = new HashSet<String>();
//		keySet.add("id");
//		keySet.add("warningNum");
//		keySet.add("weatherThreshold");
//		keySet.add("dateTime");
//		map.put("Sql", selectBuffer.toString());
//		resultModel = mfWeatherUntBll.getListBySQL(map);
//		
//		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
//		Long warningNum = (Long) modelList.get(0).get("weatherThreshold");
//		Map<String, String> pmap = new HashMap<String, String>();
//		pmap.put("viewName", "month");//视图名，年year，月month，日date（默认为年）
//		pmap.put("hasCurrent", "true");
//		pmap.put("pastNum", "5");
//		pmap.put("afterNum", "0");
//		
//		resultModel = TimesView.getTimesData(pmap, modelList,keySet);
//		List<Map<String, Object>> modelLists =(List<Map<String, Object>>) resultModel.getData();
//		List<String> list = new ArrayList<String>();
//		List<Object> weatherThresholdList = new ArrayList<Object>();
//		List<Object> warningNumList = new ArrayList<Object>();
//		Map<String, Object> xmap = new HashMap<String, Object>();
//		for(Map<String, Object> maps :modelLists){
//			list.add((String) maps.get("dateTime"));
//			weatherThresholdList.add(maps.get("warningNum"));
//			warningNumList.add(maps.get("weatherThreshold"));
//		}
//		xmap.put("dateTime", list);
//		xmap.put("weatherThresholdList", weatherThresholdList);
//		xmap.put("warningNum",warningNumList);
//		resultModel.setData(xmap);
//		return resultModel;
//	}
	
}



