package com.zee.app.extend.swagger.da;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaIotMonitorDisasterDataGenSwgApp;
import com.zee.bll.extend.unity.da.DaIotMonitorDataUntBll;
import com.zee.bll.extend.unity.da.DaIotMonitorDisasterDataUntBll;
import com.zee.bll.extend.unity.da.DaIotMonitorQueryDateUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaIotMonitorDisasterData;
import com.zee.ent.extend.da.DaIotMonitorQueryDate;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.TimesView;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-6 14:42:27
 * @description  对外接口，扩展自DaIotMonitorDisasterDataGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daIotMonitorDisasterData")
public class  DaIotMonitorDisasterDataSwgApp extends  DaIotMonitorDisasterDataGenSwgApp {

	@Autowired
	@Qualifier("daIotMonitorQueryDateUntBll")
	protected DaIotMonitorQueryDateUntBll daIotMonitorQueryDateUntBll;
	
	@Autowired
	@Qualifier("daIotMonitorDataUntBll")
	protected DaIotMonitorDataUntBll daIotMonitorDataUntBll;
	
	@Autowired
	@Qualifier("daIotMonitorDisasterDataUntBll")
	protected DaIotMonitorDisasterDataUntBll daIotMonitorDisasterDataUntBll;
	
	//总预警			0	0
	//空气温度预警		1	770
	//空气湿度预警		2	769
	//土壤温度预警		3	788
	//土壤湿度预警		4	787
	//风速预警			5	781
	//降雨量预警		6	780
	//日雨预警			7	797
	//光照强度预警		8	782

	//空气温度下限
	double minAirTemperature  = 13.60;
	//空气温度上限
	double maxAirTemperature  = 37.60;
	
	//物联网各个类型预警阈值
	double total = 0.00;
	double airTemperature  = 37.60;	
	double airHumidity = 99.89;
	double soilTemperature = 38.00;
	double soilHumidity = 79.80;
	double windSpeed = 4.10; 
	double rainfall = 1.00;
	double dayRain = 59.00;
	double illuminationIntensity = 147001.00;
	
	//农业灾害评估各个类型预警阈值
	//冻害
	double freezeInjury = 15.5;
	//暴雨
	double torrentialRain = 9.8;
	//干旱
	double drought = 3.3;
	//大风
	double highWind = 0.8;
	
	private static final String[] SENSOR_TYPE_ARRAY = {"0","770","769","788","787","781","780","797","782"};
	private double[] THRESHOLD_VALUE = {total,airTemperature,airHumidity,soilTemperature,soilHumidity,windSpeed,rainfall,dayRain,illuminationIntensity};
	
	//物联网设备初次安装时间
	private static final String IOT_DEVICE_INSTALL_DATE = "2018-05-25 00:00:00";
	
	//对应数据字典表（dictionary）中的文本字段(text) 农业灾害类型  0：全部 1：冻害，2：暴雨，3：干旱，4：大风
	private static final String[] DISASTER_TYPE_TEXT = {"总","冻害","暴雨","干旱","大风"};
	
	//芒果生命周期code
	private static final String[] MANGO_LIFE_CYCLE_CODE = {"01","0203","0405","0607","0809"};
	//芒果生命周期text
	private static final String[] MANGO_LIFE_CYCLE_TEXT = {"花芽分化期","开花盛花期","幼果、生理落果期","果实膨大期","果实成熟期"};
	//芒果生命周期月份
	private static final String[] MANGO_LIFE_CYCLE_DATE = {"（1月份）","（2、3月份）","（4、5月份）","（6、7月份）","（8、9月份）"};
	
	@ApiOperation(value = "获取农业灾害次数", notes = "获取各个类型农业灾害在某个时间段的预警次数")
	@RequestMapping(value = "/getAgricultureDisasterFrequency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getAgricultureDisasterFrequency() {
		
		//首先将 从上次查询时间到现在  da_iot_monitor_data表中满足预警条件的数据 插入到da_iot_monitor_disaster_data表中
		//agricultureDisasterEstimate();
		//然后再到da_iot_monitor_disaster_data表中查询灾害预警数据
		
		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String date = "";//日期
		String preDate = "";//被选中日期的前一天
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("date") && StringUtils.isNotBlank(entityRelatedObject.getString("date")))
					date = entityRelatedObject.getString("date");
				
				String tempDate = date+"-01 00:00:00";
				Date now = DateUtils.string2Date(tempDate, "yyyy-MM-dd HH:mm:ss");
				Calendar calendar = Calendar.getInstance();  
		        calendar.setTime(now);  
		        calendar.add(Calendar.MONTH, -1);  
		        now = calendar.getTime();
		        preDate = DateUtils.date2String(now, CustomSymbolic.DATETIME_FORMAT).substring(0, 7);
			}
		}
		
		Map<String,Object> resultMap = null;
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		if(StringUtils.isNotBlank(date)) {
			//获取所有的灾害预警类型code
			List<String> disasterTypeCodeList = getDisasterTypeCodeList();
			Collections.sort(disasterTypeCodeList);  
			if(disasterTypeCodeList!=null && disasterTypeCodeList.size()>=1) {
				Map<String,Object> tempMap = null;
				Map<String,Object> tempPreMap = null;
				for(String code : disasterTypeCodeList) {
					resultMap = new HashMap<String,Object>();
					
					tempPreMap = new HashMap<String,Object>();
					ResultModel perPreFrequencyModel = getPerFrequencyModel(preDate,code);
					Object perPreFrequencyObj = perPreFrequencyModel.getData();
					List<Map<String,Object>> perPreFrequencyList = null;
					
					tempMap = new HashMap<String,Object>();
					ResultModel perFrequencyModel = getPerFrequencyModel(date,code);
					Object perFrequencyObj = perFrequencyModel.getData();
					List<Map<String,Object>> perFrequencyList = null;
					
					long preCount = 0;
					if(perPreFrequencyObj!=null) {
						perPreFrequencyList = (List<Map<String, Object>>) perPreFrequencyObj;
						if(perPreFrequencyList!=null && perPreFrequencyList.size()>=1) {
							tempPreMap = perPreFrequencyList.get(0);
							if(tempPreMap.containsKey("count")&&tempPreMap.get("count")!=null&&Integer.parseInt(tempPreMap.get("count").toString())!=0) {
								preCount = (long) Integer.parseInt(tempPreMap.get("count").toString());
							}
						}
					}
					
					if(perFrequencyObj!=null) {
						perFrequencyList = (List<Map<String, Object>>) perFrequencyObj;
						if(perFrequencyList!=null && perFrequencyList.size()>=1) {
							tempMap = perFrequencyList.get(0);
							int typeCode = 0;
							String typeText = "";
							long count = 0;
							if(tempMap.containsKey("typeCode")) {
								typeCode = (int) tempMap.get("typeCode");
							}
							if(tempMap.containsKey("typeText")) {
								typeText = (String) tempMap.get("typeText");
							}
							if(tempMap.containsKey("count")&&Integer.parseInt(tempMap.get("count").toString())!=0) {
								count = (long) Integer.parseInt(tempMap.get("count").toString());
							}
							if("0".equals(code)) {
								resultMap.put("name", "总");
							}else {
								resultMap.put("name",typeText);
							}
							resultMap.put("number", count);
							resultMap.put("flag", count>preCount? 1:0);
							resultList.add(resultMap);
						}
					}
				}
				resultModel.setData(resultList);
				resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
			}
		}
		return resultModel;
	}
	
	@ApiOperation(value = "获取最新农业灾害预警信息", notes = "获取最新农业灾害预警信息")
	@RequestMapping(value = "/getRecentAgricultueDisasterInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getRecentAgricultueDisasterInfo() {
		
		//首先将 从上次查询时间到现在  da_iot_monitor_data表中满足预警条件的数据 插入到da_iot_monitor_disaster_data表中
		//agricultureDisasterEstimate();
		//然后再到da_iot_monitor_disaster_data表中查询灾害预警数据
		
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		JSONObject jsonObject = null;
		if (!StringUtils.isBlank(jsonData)) {
			jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		 /*SELECT 
			A.`disaster_type_code` AS typeCode,
			A.`disaster_type_text` AS typeText,
			SUBSTR(A.create_time, 1, 10) AS TIME
		    FROM da_iot_monitor_disaster_data A
		    ORDER BY create_time DESC;*/
		
		selectBuffer.append(" SELECT 																");
		selectBuffer.append(" A.`disaster_type_code` AS typeCode,									");
		selectBuffer.append(" A.`disaster_type_text` AS typeText,									");
		selectBuffer.append(" SUBSTR(A.create_time, 1, 10) AS time									");
		selectBuffer.append(" FROM da_iot_monitor_disaster_data A									");
		selectBuffer.append(" ORDER BY create_time DESC												");
		
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		return resultModel;
	}
	
	@ApiOperation(value = "获取每个基地的最新农业灾害预警信息", notes = "获取每个基地的最新农业灾害预警信息")
	@RequestMapping(value = "/getPerBaseAgricultueDisasterInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPerBaseAgricultueDisasterInfo() {
		
		//首先将 从上次查询时间到现在  da_iot_monitor_data表中满足预警条件的数据 插入到da_iot_monitor_disaster_data表中
		//agricultureDisasterEstimate();
		//然后再到da_iot_monitor_disaster_data表中查询灾害预警数据
		
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		 /*
		SELECT t0.* FROM 
		(
		SELECT 
		    SUBSTR(A.create_time, 1, 10) AS TIME,
		    A.`disaster_type_code` AS typeCode,
		    A.`disaster_type_text` AS typeText,
		    A.`basc_code` AS bascCode,
		    A.`basc_name` AS bascName,
		    A.`longitude` AS longitude,
		    A.`latitude` AS latitude
		FROM
		    da_iot_monitor_disaster_data A 
		ORDER BY create_time DESC) t0 GROUP BY bascCode*/
		
		selectBuffer.append(" SELECT t0.* FROM 																		");
		selectBuffer.append(" (SELECT 																				");
		selectBuffer.append(" SUBSTR(A.create_time, 1, 10) AS time,													");
		selectBuffer.append(" A.`disaster_type_code` AS typeCode,													");
		selectBuffer.append(" A.`disaster_type_text` AS typeText,													");
		selectBuffer.append(" A.`basc_code` AS bascCode,															");
		selectBuffer.append(" A.`basc_name` AS bascName,															");
		selectBuffer.append(" A.`hid` AS deviceCode,															    ");
		selectBuffer.append(" A.`device_name` AS deviceName,														");
		selectBuffer.append(" A.`longitude` AS longitude,															");
		selectBuffer.append(" A.`latitude` AS latitude																");
		selectBuffer.append(" FROM																					");
		selectBuffer.append(" da_iot_monitor_disaster_data A 														");
		selectBuffer.append(" ORDER BY create_time DESC) t0 GROUP BY deviceCode,typeCode							");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		
		//lifeCycle
		Object obj = resultModel.getData();
		List<Map<String,Object>> list = null;
		if(obj!=null) {
			list = (List<Map<String, Object>>) obj;
			if(list!=null && list.size()>=1) {
				for(Map<String,Object> tempMap : list) {
					if(tempMap.containsKey("time")) {
						String shortTime = (String) tempMap.get("time");
						String lifeCycleText = getLifeCycleText(shortTime.substring(5, 7),MANGO_LIFE_CYCLE_TEXT);
						tempMap.put("lifeCycle", lifeCycleText);
					}
					String longitude = "";
					String latitude = "";
					if(tempMap.containsKey("longitude")) {
						longitude = (String) tempMap.get("longitude");
					}
					if(tempMap.containsKey("latitude")) {
						latitude = (String) tempMap.get("latitude");
					}
					tempMap.put("point", longitude+","+latitude);
				}
			}
		}
		return resultModel;
	}
	
	@ApiOperation(value = "获取芒果各个生命周期内各种农业灾害预警次数", notes = "获取芒果各个生命周期内各种农业灾害预警次数")
	@RequestMapping(value = "/getPerLifeCycleAgricultueDisasterInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPerLifeCycleAgricultueDisasterInfo() {
		
		//首先将 从上次查询时间到现在  da_iot_monitor_data表中满足预警条件的数据 插入到da_iot_monitor_disaster_data表中
		//agricultureDisasterEstimate();
		//然后再到da_iot_monitor_disaster_data表中查询灾害预警数据
		
		ResultModel resultModel = new ResultModel();
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> perMap = null;
		for(String cycleCode : MANGO_LIFE_CYCLE_CODE) {
			perMap = getPerLifeCycleInfo(cycleCode);
			resultList.add(perMap);
		}
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	@ApiOperation(value = "各种农业灾害预警走势", notes = "各种农业灾害预警走势")
	@RequestMapping(value = "/getAgricultueDisasterEarlyWarningTrend", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getAgricultueDisasterEarlyWarningTrend() {
		
		//首先将 从上次查询时间到现在  da_iot_monitor_data表中满足预警条件的数据 插入到da_iot_monitor_disaster_data表中
		//agricultureDisasterEstimate();
		//然后再到da_iot_monitor_disaster_data表中查询灾害预警数据
		
		ResultModel resultModel = new ResultModel();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String typeCode = "";//灾害类型
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("typeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("typeCode")))
					typeCode = entityRelatedObject.getString("typeCode");
			}
		}
		
		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "year");//视图名，年year，月month，日date，小时hour（默认为年）
		timeMap.put("hasCurrent", "true");
		timeMap.put("pastNum", "5");
		List<String> timesList = TimesView.getTimesView(timeMap);
		
		Map<String,Object> tempMap = null;
		List disasterCountList = new ArrayList();
		for(String year:timesList) {
			ResultModel perYearModel = getPerYearModel(year,typeCode);
			Object perObj = perYearModel.getData();
			List<Map<String,Object>> perList = null;
			if(perObj!=null) {
				perList = (List<Map<String, Object>>) perObj;
				if(perList.size()>=1) {
					tempMap = perList.get(0);
					int code = 0;
					long count = 0;
					if(tempMap.containsKey("count")) {
						count = (long) tempMap.get("count");
					}
					disasterCountList.add(count);
				}
			}
		}
		String typeText = DISASTER_TYPE_TEXT[Integer.parseInt(typeCode)];
		resultMap.put("Xdata", timesList);
		resultMap.put("count", disasterCountList);
		resultMap.put("text", typeText);
		
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	
	private ResultModel getPerYearModel(String year, String typeCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT COUNT(*) AS COUNT,
		A.`disaster_type_code` AS typeCode,
		A.`disaster_type_text` AS typeText
		FROM da_iot_monitor_disaster_data A
		WHERE 
		SUBSTR(A.create_time, 1, 4) = '2018'
		AND A.disaster_type_code = 4*/
		
		selectBuffer.append(" SELECT COUNT(*) AS count,														");
		selectBuffer.append(" A.`disaster_type_code` AS typeCode,											");
		selectBuffer.append(" A.`disaster_type_text` AS typeText											");
		selectBuffer.append(" FROM da_iot_monitor_disaster_data A											");
		selectBuffer.append(" WHERE 																		");
		selectBuffer.append(" SUBSTR(A.create_time, 1, 4) =													");
		selectBuffer.append("'");
		selectBuffer.append(year);
		selectBuffer.append("'");
		if(!"0".equals(typeCode)) {
			selectBuffer.append(" AND A.disaster_type_code =												");
			selectBuffer.append("'");
			selectBuffer.append(typeCode);
			selectBuffer.append("'");
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		
		return resultModel;
	}

	private Map<String,Object> getPerLifeCycleInfo(String cycleCode) {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//获取所有的灾害预警类型code
		List<String> disasterTypeCodeList = getDisasterTypeCodeList();
		if(disasterTypeCodeList!=null && disasterTypeCodeList.size()>=1) {
			Map<String,Object> tempMap = null;
			List disasterTextList = new ArrayList();
			List disasterCountList = new ArrayList();
			for(String disasterTypeCode : disasterTypeCodeList) {
				if(!"0".equals(disasterTypeCode)) {
					tempMap = new HashMap<String,Object>();
					ResultModel perFrequencyModel = getPerLifeCycleModel(cycleCode,disasterTypeCode);
					Object perFrequencyObj = perFrequencyModel.getData();
					List<Map<String,Object>> perFrequencyList = null;
					if(perFrequencyObj!=null) {
						perFrequencyList = (List<Map<String, Object>>) perFrequencyObj;
						if(perFrequencyList!=null && perFrequencyList.size()>=1) {
							tempMap = perFrequencyList.get(0);
							int typeCode = 0;
							long count = 0;
							String typeText = "";
							if(tempMap.containsKey("count")) {
								count = (long) tempMap.get("count");
							}
							typeText = DISASTER_TYPE_TEXT[Integer.parseInt(disasterTypeCode)];
							disasterTextList.add(typeText);
							disasterCountList.add(count);
						}
					}
				}
			}
			String lifeCycleText = getLifeCycleText(cycleCode,MANGO_LIFE_CYCLE_TEXT)+getLifeCycleText(cycleCode,MANGO_LIFE_CYCLE_DATE);
			resultMap.put("cycleText", lifeCycleText);
			resultMap.put("disasterText", disasterTextList);
			resultMap.put("disasterCount", disasterCountList);
		}
		return resultMap;
	}
	
	private ResultModel getPerLifeCycleModel(String cycleCode,String disasterTypeCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 		COUNT(*) AS COUNT,
		A.`disaster_type_code` AS typeCode,
		A.`disaster_type_text` AS typeText
		FROM da_iot_monitor_disaster_data A
		
		WHERE (SELECT LOCATE(SUBSTR(A.create_time, 6, 2),'0506'))>0
		
		AND A.disaster_type_code = 1*/
		
		selectBuffer.append(" SELECT COUNT(*) AS count,														");
		selectBuffer.append(" A.`disaster_type_code` AS typeCode,											");
		selectBuffer.append(" A.`disaster_type_text` AS typeText											");
		selectBuffer.append(" FROM da_iot_monitor_disaster_data A											");
		selectBuffer.append(" WHERE (SELECT LOCATE(SUBSTR(A.create_time, 6, 2),'");
		selectBuffer.append(cycleCode);
		selectBuffer.append("'))>0		");
		if(!"0".equals(disasterTypeCode)) {
			selectBuffer.append(" AND A.disaster_type_code =												");
			selectBuffer.append("'");
			selectBuffer.append(disasterTypeCode);
			selectBuffer.append("'");
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		
		return resultModel;
	}

	//根据时间获取芒果所处的生命周期
	private String getLifeCycleText(String str, String[] array) {
		for(int i = 0;i<MANGO_LIFE_CYCLE_CODE.length;i++) {
			if(MANGO_LIFE_CYCLE_CODE[i].contains(str)) {
				return array[i];
			}
		}
		return "";
	}

	private List<String> getDisasterTypeCodeList() {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT A.code
		FROM	 
		gp_dictionary A
		WHERE A.type_id='e94c59ca245b8cb85038922067beb00d'*/
		
		selectBuffer.append(" SELECT A.code FROM gp_dictionary A WHERE A.type_id='");
		selectBuffer.append("e94c59ca245b8cb85038922067beb00d");
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		
		Object codeObj = resultModel.getData();
		List<Map<String,Object>> codeList = null;
		List<String> resultList = new ArrayList<String>();
		if(codeObj!=null) {
			codeList = (List<Map<String, Object>>) codeObj;
			if(codeList!=null && codeList.size()>=1) {
				for(Map<String,Object> tempMap : codeList) {
					if(tempMap.containsKey("code")) {
						int code = (int) tempMap.get("code");
						resultList.add(code+"");
					}
				}
				return resultList;
			}
		}
		return null;
	}

	private ResultModel getPerFrequencyModel(String date,String code) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT COUNT(*) AS COUNT,
		A.`disaster_type_code` AS typeCode,
		A.`disaster_type_text` AS typeText
		FROM da_iot_monitor_disaster_data A
		WHERE 
		SUBSTR(A.create_time, 1, 7) = '2018-06'
		AND A.disaster_type_code = 4*/
		
//		selectBuffer.append(" SELECT COUNT(*) AS count,														");
//		selectBuffer.append(" A.`disaster_type_code` AS typeCode,											");
//		selectBuffer.append(" A.`disaster_type_text` AS typeText											");
//		selectBuffer.append(" FROM da_iot_monitor_disaster_data A											");
//		selectBuffer.append(" WHERE 																		");
//		selectBuffer.append(" SUBSTR(A.create_time, 1, 7) =													");
//		selectBuffer.append("'");
//		selectBuffer.append(date);
//		selectBuffer.append("'");
//		if(!"0".equals(code)) {
//			selectBuffer.append(" AND A.disaster_type_code =												");
//			selectBuffer.append("'");
//			selectBuffer.append(code);
//			selectBuffer.append("'");
//		}
		selectBuffer.append(" SELECT A.code typeCode,A.text typeText,IFNULL(sum(B.count), 0) count FROM (SELECT A.code,A.text FROM gp_dictionary A ");
		selectBuffer.append(" WHERE A.type_id='e94c59ca245b8cb85038922067beb00d' order by A.code ASC) A LEFT JOIN ( ");
		
		selectBuffer.append(" SELECT COUNT(*) AS count,														");
		selectBuffer.append(" A.`disaster_type_code` AS typeCode,											");
		selectBuffer.append(" A.`disaster_type_text` AS typeText											");
		selectBuffer.append(" FROM da_iot_monitor_disaster_data A											");
		selectBuffer.append(" WHERE 																		");
		selectBuffer.append(" SUBSTR(A.create_time, 1, 7) =													");
		selectBuffer.append("'");
		selectBuffer.append(date);
		selectBuffer.append("'");
		if(!"0".equals(code)) {
			selectBuffer.append(" AND A.disaster_type_code = '"+code+"'                                    ");
		}
		selectBuffer.append(" GROUP BY A.`disaster_type_code` ) B ON A.CODE = B.typeCode where 1=1 ");
		if(!"0".equals(code)) {
			selectBuffer.append(" AND A.CODE =												");
			selectBuffer.append("'");
			selectBuffer.append(code);
			selectBuffer.append("'");
		}
		selectBuffer.append(" order by A.CODE asc ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		
		return resultModel;
	}

	/**
	 * 更新da_iot_monitor_disaster_data表中的农业灾害预警数据
	 */
	public void agricultureDisasterEstimate() {
		
		//冻害	1	温度<5℃
		//暴雨	2	24小时降水量为50毫米
		//干旱	3	土壤湿度<50%，连续3天
		//大风	4	风速>17.2 米/秒
		
		//首先从da_iot_monitor_query_date表中查询上次查询时间  如：2018-05-25 11:07:58
		String lastQueryDate = getLastQueryDate();
		
		//获取冻害数据
		ResultModel freezeInjuryModel = getFreezeInjuryData(lastQueryDate);
		//插入冻害数据
		ResultModel insertFreezeInjuryModel = insertAgricultureDisasterData(freezeInjuryModel);
		
		//获取暴雨数据
		ResultModel torrentialRainModel = getTorrentialRainData(lastQueryDate);
		//插入暴雨数据
		ResultModel insertTorrentialRainModel = insertAgricultureDisasterData(torrentialRainModel);
		
		//获取干旱数据
		ResultModel droughtModel = getDroughtData(lastQueryDate);
		//插入干旱数据
		ResultModel insertDroughtModel = insertAgricultureDisasterData(droughtModel);
		
		//获取大风数据
		ResultModel highWindModel = getHighWindData(lastQueryDate);
		//插入大风数据
		ResultModel insertHighWindModel = insertAgricultureDisasterData(highWindModel);
		
		//将当前时间插入到da_iot_monitor_query_date表中
		insertCurrentDate();
		
	}
	
	private void insertCurrentDate() {
		String currentDate = DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" SELECT * FROM da_iot_monitor_query_date 											");
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorQueryDateUntBll.getListBySQL(map);
		
		Object queryDateObj = resultModel.getData();
		List<Map<String,Object>> queryDateList = null;
		if(queryDateObj!=null) {
			queryDateList = (List<Map<String, Object>>) queryDateObj;
			if(queryDateList!=null && queryDateList.size()>=1) {
				Map<String,Object> tempMap = queryDateList.get(0);
				DaIotMonitorQueryDate daIotMonitorQueryDate = new DaIotMonitorQueryDate();
				String id = "";
				Date lastQueryDate = DateUtils.string2Date(currentDate, "yyyy-MM-dd HH:mm:ss");
				if(tempMap.containsKey("id")) {
					id = (String) tempMap.get("id");
					daIotMonitorQueryDate.setId(id);
					daIotMonitorQueryDate.setLastQueryDate(lastQueryDate);
					daIotMonitorQueryDateUntBll.update(daIotMonitorQueryDate);
				}
			}
		}
	}

	private ResultModel insertAgricultureDisasterData(ResultModel model) {
		ResultModel resultModel = new ResultModel();
		String hid = "";					//设备id
		String deviceName = "";				//设备名称
		String bascCode = "";				//基地编码
		String bascName = "";				//基地名称
		String longitude = "";				//经度
		String latitude = "";				//纬度
		String mangoType = "";				//监测的芒果品种，多个品种用逗号隔开
		int sensorType = 0;				//物联网传感器类型
		int disasterTypeCode = 0; 			//对应数据字典表（dictionary）中的编码字段（code）农业灾害类型  0：全部 1：冻害，2：暴雨，3：干旱，4：大风
		String disasterTypeText = "";		//对应数据字典表（dictionary）中的文本字段(text) 农业灾害类型  0：全部 1：冻害，2：暴雨，3：干旱，4：大风
		Date createTime = null;
		
		Object freezeInjuryObj = model.getData();
		List<Map<String,Object>> freezeInjuryList = null;
		if(freezeInjuryObj!=null) {
			freezeInjuryList = (List<Map<String, Object>>) freezeInjuryObj;
			if(freezeInjuryList!=null && freezeInjuryList.size()>=1) {
				DaIotMonitorDisasterData daIotMonitorDisasterData = null;
				for(Map<String,Object> tempMap : freezeInjuryList) {
					daIotMonitorDisasterData = new DaIotMonitorDisasterData();
					if(tempMap.containsKey("bascCode")) {
						bascCode = (String) tempMap.get("bascCode");
						daIotMonitorDisasterData.setBascCode(bascCode);
					}
					if(tempMap.containsKey("shortTime")) {
						String date = (String) tempMap.get("shortTime");
						if(StringUtils.isNotBlank(date)) {
							createTime = DateUtils.string2Date(date, "yyyy-MM-dd");
							daIotMonitorDisasterData.setCreateTime(createTime);
						}
					}
					if(tempMap.containsKey("sensorType")) {
						sensorType = (int) tempMap.get("sensorType");
					}
					switch (sensorType+"") {
					case "770":
						disasterTypeCode = 1;
						disasterTypeText = DISASTER_TYPE_TEXT[disasterTypeCode];
						daIotMonitorDisasterData.setDisasterTypeCode((byte)disasterTypeCode);
						daIotMonitorDisasterData.setDisasterTypeText(disasterTypeText);
						break;
					case "797":
						disasterTypeCode = 2;
						disasterTypeText = DISASTER_TYPE_TEXT[disasterTypeCode];
						daIotMonitorDisasterData.setDisasterTypeCode((byte)disasterTypeCode);
						daIotMonitorDisasterData.setDisasterTypeText(disasterTypeText);
						break;
					case "787":
						disasterTypeCode = 3;
						disasterTypeText = DISASTER_TYPE_TEXT[disasterTypeCode];
						daIotMonitorDisasterData.setDisasterTypeCode((byte)disasterTypeCode);
						daIotMonitorDisasterData.setDisasterTypeText(disasterTypeText);
						break;
					case "781":
						disasterTypeCode = 4;
						disasterTypeText = DISASTER_TYPE_TEXT[disasterTypeCode];
						daIotMonitorDisasterData.setDisasterTypeCode((byte)disasterTypeCode);
						daIotMonitorDisasterData.setDisasterTypeText(disasterTypeText);
						break;
					default:
						break;
					}
					
					//插入冻害数据
					//插入数据时，需要满足条件：在数据库中不存在 bascCode,shortTime和sensorType三个字段完全相同的值
					//即，一个基地、在一天中、只能发布某一个类型的一次预警信息
					
					if(hasSomeOrOtherDisasterData(bascCode,createTime,disasterTypeCode)) 
						continue;
					
					if(tempMap.containsKey("hid")) {
						hid = (String) tempMap.get("hid");
						daIotMonitorDisasterData.setHid(hid);
					}
					if(tempMap.containsKey("deviceName")) {
						deviceName = (String) tempMap.get("deviceName");	
						daIotMonitorDisasterData.setDeviceName(deviceName);
					}
					if(tempMap.containsKey("bascName")) {
						bascName = (String) tempMap.get("bascName");	
						daIotMonitorDisasterData.setBascName(bascName);
					}
					if(tempMap.containsKey("longitude")) {
						longitude = (String) tempMap.get("longitude");	
						daIotMonitorDisasterData.setLongitude(longitude);
					}
					if(tempMap.containsKey("latitude")) {
						latitude = (String) tempMap.get("latitude");	
						daIotMonitorDisasterData.setLatitude(latitude);
					}
					if(tempMap.containsKey("mangoType")) {
						mangoType = (String) tempMap.get("mangoType");	
						daIotMonitorDisasterData.setMangoType(mangoType);
					}
					resultModel = daIotMonitorDisasterDataUntBll.add(daIotMonitorDisasterData);
				}
			}
		}
		return resultModel;
	}

	private boolean hasSomeOrOtherDisasterData(String bascCode, Date createTime, int disasterTypeCode) {
		String time = DateUtils.date2String(createTime, "yyyy-MM-dd");
		
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

/*		SELECT * FROM da_iot_monitor_disaster_data 
			WHERE basc_code = ''
			AND create_time = ''
			AND disaster_type_code = '' */

		selectBuffer.append(" SELECT * FROM da_iot_monitor_disaster_data ");
		selectBuffer.append(" WHERE basc_code =");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(bascCode)) {
			selectBuffer.append(bascCode);
		}
		selectBuffer.append("'");
		selectBuffer.append("AND create_time =");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND disaster_type_code =");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(disasterTypeCode+"")) {
			selectBuffer.append(disasterTypeCode+"");
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		
		Object disasterObj = resultModel.getData();
		List<Map<String,Object>> disasterList = null;
		if(disasterObj!=null) {
			disasterList = (List<Map<String, Object>>) disasterObj;
			if(disasterList!=null && disasterList.size()>=1) {
				return true;
			}
		}
		return false;
	}

	private String getLastQueryDate() {
		
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
//		SELECT last_query_date FROM da_iot_monitor_query_date ;
		selectBuffer.append("SELECT last_query_date FROM da_iot_monitor_query_date");
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		
		String lastQueryDate = "";
		Object queryDateObj = resultModel.getData();
		List<Map<String,Object>> queryDateList = null;
		if(queryDateObj!=null) {
			queryDateList = (List<Map<String, Object>>) queryDateObj;
			if(queryDateList!=null && queryDateList.size()>=1) {
				Map<String,Object> queryDateMap = queryDateList.get(0);
				if(queryDateMap.containsKey("last_query_date")) {
					Date tempDate = (Date) queryDateMap.get("last_query_date");
					lastQueryDate = DateUtils.date2String(tempDate, "yyyy-MM-dd HH:mm:ss");
				}
			}
		}
		//如果数据库中查询不到上次查询时间，则从设备安装时间开始查询
		if(StringUtils.isBlank(lastQueryDate)) {
			lastQueryDate = IOT_DEVICE_INSTALL_DATE;
		}
		return lastQueryDate;
	}

	private ResultModel getFreezeInjuryData(String lastQueryDate) {
		String currentDate = DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		//为保证有数据，暂定冻害的标准为 空气温度<15.5
		
		/*SELECT 
		    t0.* 
		FROM
		    (SELECT 
		        A.hid AS hid,
		        B.`device_name` AS deviceName,
		        B.`longitude` AS longitude,
		        B.`latitude` AS latitude,
		        B.`mango_type` AS mangoType,
		        B.basc_code AS bascCode,
		        B.basc_name AS bascName,
		        A.sensor_type AS sensorType,
		        A.display_name AS displayName,
		        A.val AS val,
		        A.time AS TIME,
		        SUBSTR(A.time, 1, 10) shortTime
		    FROM
		        da_iot_monitor_data A,
		        da_iot_monitor_basic B 
		    WHERE A.hid = B.hid 
		        AND sensor_type = '770' 
		        AND SUBSTR(TIME, 1, 10) BETWEEN '2018-06-04' AND '2018-06-05'
		        AND val < 15.5
		    ORDER BY TIME DESC) t0 
		GROUP BY bascCode,shortTime */
		
		selectBuffer.append(" SELECT t0.* FROM																				");
		selectBuffer.append(" (SELECT A.hid AS hid,																			");
		selectBuffer.append(" B.`device_name` AS deviceName,																");
		selectBuffer.append(" B.`longitude` AS longitude,																	");
		selectBuffer.append(" B.`latitude` AS latitude,																		");
		selectBuffer.append(" B.`mango_type` AS mangoType,																	");
		selectBuffer.append(" B.basc_code AS bascCode,																		");
		selectBuffer.append(" B.basc_name AS bascName,																		");
		selectBuffer.append(" A.sensor_type AS sensorType,																	");
		selectBuffer.append(" A.display_name AS displayName,																");
		selectBuffer.append(" A.val AS val,																					");
		selectBuffer.append(" A.time AS time,																				");
		selectBuffer.append(" SUBSTR(A.time, 1, 10) shortTime																");
		selectBuffer.append(" FROM da_iot_monitor_data A ,da_iot_monitor_basic B											");
		selectBuffer.append(" WHERE A.hid = B.hid 																			");
		selectBuffer.append(" AND sensor_type =																				");
		selectBuffer.append("'");
		selectBuffer.append(SENSOR_TYPE_ARRAY[1]);
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(TIME,1,10) BETWEEN																	'");
		selectBuffer.append(lastQueryDate);
		selectBuffer.append("' AND 																						'");
		selectBuffer.append(currentDate);
		selectBuffer.append("' AND val<																						");
		selectBuffer.append(freezeInjury);
		selectBuffer.append(" ORDER BY time DESC) t0 GROUP BY bascCode,shortTime											");
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		
		return resultModel;
	}

	private ResultModel getTorrentialRainData(String lastQueryDate) {
		String currentDate = DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    t0.* 
		FROM
		    (SELECT 
		        A.hid AS hid,
		        B.`device_name` AS deviceName,
		        B.`longitude` AS longitude,
		        B.`latitude` AS latitude,
		        B.`mango_type` AS mangoType,
		        B.basc_code AS bascCode,
		        B.basc_name AS bascName,
		        A.sensor_type AS sensorType,
		        A.display_name AS displayName,
		        A.val AS val,
		        A.time AS TIME,
		        SUBSTR(A.time, 1, 10) shortTime
		    FROM
		        da_iot_monitor_data A,
		        da_iot_monitor_basic B 
		    WHERE A.hid = B.hid 
		        AND sensor_type = '797' 
		        AND SUBSTR(TIME, 1, 10) BETWEEN '2018-06-01' AND '2018-06-06'
		        AND val > 9.8 
		    ORDER BY TIME DESC) t0 
		GROUP BY bascCode,shortTime */
		
		selectBuffer.append(" SELECT t0.* FROM																				");
		selectBuffer.append(" (SELECT A.hid AS hid,																			");
		selectBuffer.append(" B.`device_name` AS deviceName,																");
		selectBuffer.append(" B.`longitude` AS longitude,																	");
		selectBuffer.append(" B.`latitude` AS latitude,																		");
		selectBuffer.append(" B.`mango_type` AS mangoType,																	");
		selectBuffer.append(" B.basc_code AS bascCode,																		");
		selectBuffer.append(" B.basc_name AS bascName,																		");
		selectBuffer.append(" A.sensor_type AS sensorType,																	");
		selectBuffer.append(" A.display_name AS displayName,																");
		selectBuffer.append(" A.val AS val,																					");
		selectBuffer.append(" A.time AS time,																				");
		selectBuffer.append(" SUBSTR(A.time, 1, 10) shortTime																");
		selectBuffer.append(" FROM da_iot_monitor_data A ,da_iot_monitor_basic B											");
		selectBuffer.append(" WHERE A.hid = B.hid 																			");
		selectBuffer.append(" AND sensor_type =																				");
		selectBuffer.append("'");
		selectBuffer.append(SENSOR_TYPE_ARRAY[7]);
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(TIME,1,10) BETWEEN																	'");
		selectBuffer.append(lastQueryDate);
		selectBuffer.append("' AND 																							'");
		selectBuffer.append(currentDate);
		selectBuffer.append("' AND val>																						");
		selectBuffer.append(torrentialRain);
		selectBuffer.append(" ORDER BY time DESC) t0 GROUP BY bascCode,shortTime											");
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		
		return resultModel;
	}

	private ResultModel getDroughtData(String lastQueryDate) {
		ResultModel resultModel = new ResultModel();
		String currentDate = DateUtils.date2String(new Date(), "yyyy-MM-dd");
		
		//获取本次查询需要的时间区域
		List<String> queryDateList = DateUtils.getQueryDateList(lastQueryDate,currentDate,"yyyy-MM-dd");
		
		//获取华坪县所有种植基地的code
		List<String> baseCodeList = getBaseCodeList();
		
		List<Map<String,Object>> droughtList = new ArrayList<Map<String,Object>>();
		
		for(String code : baseCodeList) {
			//获取某个基地符合干旱条件的数据
			ResultModel perBaseDroughtModel = getPerBaseDroughtData(code,queryDateList);
			Object perBaseDroughtObj = perBaseDroughtModel.getData();
			List<Map<String,Object>> perBaseDroughtList = null;
			if(perBaseDroughtObj!=null) {
				perBaseDroughtList = (List<Map<String, Object>>) perBaseDroughtObj;
				if(perBaseDroughtList!=null && perBaseDroughtList.size()>=1) {
					for(Map<String,Object> tempMap : perBaseDroughtList) {
						droughtList.add(tempMap);
					}
				}
			}
		}
		resultModel.setData(droughtList);
		return resultModel;
	}

	private ResultModel getHighWindData(String lastQueryDate) {
		String currentDate = DateUtils.date2String(new Date(), "yyyy-MM-dd HH:mm:ss");
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    t0.* 
		FROM
		    (SELECT 
		        A.hid AS hid,
		        B.`device_name` AS deviceName,
		        B.`longitude` AS longitude,
		        B.`latitude` AS latitude,
		        B.`mango_type` AS mangoType,
		        B.basc_code AS bascCode,
		        B.basc_name AS bascName,
		        A.sensor_type AS sensorType,
		        A.display_name AS displayName,
		        A.val AS val,
		        A.time AS TIME,
		        SUBSTR(A.time, 1, 10) shortTime
		    FROM
		        da_iot_monitor_data A,
		        da_iot_monitor_basic B 
		    WHERE A.hid = B.hid 
		        AND sensor_type = '781' 
		        AND SUBSTR(TIME, 1, 10) BETWEEN '2018-06-01' AND '2018-06-06'
		        AND val > 0.8 
		    ORDER BY TIME DESC) t0 
		GROUP BY bascCode,shortTime */
		
		selectBuffer.append(" SELECT t0.* FROM																				");
		selectBuffer.append(" (SELECT A.hid AS hid,																			");
		selectBuffer.append(" B.`device_name` AS deviceName,																");
		selectBuffer.append(" B.`longitude` AS longitude,																	");
		selectBuffer.append(" B.`latitude` AS latitude,																		");
		selectBuffer.append(" B.`mango_type` AS mangoType,																	");
		selectBuffer.append(" B.basc_code AS bascCode,																		");
		selectBuffer.append(" B.basc_name AS bascName,																		");
		selectBuffer.append(" A.sensor_type AS sensorType,																	");
		selectBuffer.append(" A.display_name AS displayName,																");
		selectBuffer.append(" A.val AS val,																					");
		selectBuffer.append(" A.time AS time,																				");
		selectBuffer.append(" SUBSTR(A.time, 1, 10) shortTime																");
		selectBuffer.append(" FROM da_iot_monitor_data A ,da_iot_monitor_basic B											");
		selectBuffer.append(" WHERE A.hid = B.hid 																			");
		selectBuffer.append(" AND sensor_type =																				");
		selectBuffer.append("'");
		selectBuffer.append(SENSOR_TYPE_ARRAY[5]);
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(TIME,1,10) BETWEEN																	'");
		selectBuffer.append(lastQueryDate);
		selectBuffer.append("' AND 																							'");
		selectBuffer.append(currentDate);
		selectBuffer.append("' AND val>																						");
		selectBuffer.append(highWind);
		selectBuffer.append(" ORDER BY time DESC) t0 GROUP BY bascCode,shortTime											");
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	private List getBaseCodeList() {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
//		SELECT * FROM da_iot_monitor_basic GROUP BY basc_code;
		
		selectBuffer.append(" SELECT * FROM da_iot_monitor_basic GROUP BY basc_code");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		
		Object codeObj = resultModel.getData();
		List<Map<String,Object>> codeList = null;
		if(codeObj!=null) {
			List<String> baseCodeList = new ArrayList<String>();
			codeList = (List<Map<String, Object>>) codeObj;
			for(Map<String,Object> tempMap : codeList) {
				if(tempMap.containsKey("basc_code")) {
					String baseCode = (String) tempMap.get("basc_code");
					baseCodeList.add(baseCode);
				}
			}
			return baseCodeList;
		}
		
		return null;
	}
	
	private ResultModel getPerBaseDroughtData(String code, List<String> queryDateList) {
		ResultModel resultModel = new ResultModel();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		int sum = 0;
		for(String date : queryDateList) {
			ResultModel perModel = getPerDroughtModel(code,date);
			Object perObj = perModel.getData();
			List<Map<String,Object>> perList = null;
			if(perObj!=null) {
				perList = (List<Map<String, Object>>) perObj;
				if(perList!=null && perList.size()>=1) {
					if(sum!=3) {
						sum+=1;
						if(sum%4 == 3) {
							//此时，已满足连续三天的条件，将当时结果集中的第一条数据存起来
							resultList.add(perList.get(0));
						}
					}else {
						sum = 3;
						//此时，已满足连续至少四天的条件，将当时结果集中的第一条数据存起来，相当于发布连续预警
						resultList.add(perList.get(0));
					}
				}else {
					//只要有某一天不满足条件，则将sum清零，重新计算预警条件
					sum = 0;
				}
			}
		}
		resultModel.setData(resultList);
		return resultModel;
	}

	private ResultModel getPerDroughtModel(String code, String date) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
			/*SELECT 
	        A.hid AS hid,
	        B.`device_name` AS deviceName,
	        B.`longitude` AS longitude,
	        B.`latitude` AS latitude,
	        B.`mango_type` AS mangoType,
	        B.basc_code AS bascCode,
	        B.basc_name AS bascName,
	        A.sensor_type AS sensorType,
	        A.display_name AS displayName,
	        A.val AS val,
	        A.time AS TIME,
	        SUBSTR(A.time, 1, 10) shortTime 
	    FROM
	        da_iot_monitor_data A,
	        da_iot_monitor_basic B 
	    WHERE A.hid = B.hid 
		AND basc_code='530723001'
	        AND sensor_type = '787' 
	        AND val<5.2
	        AND SUBSTR(TIME, 1, 10) = '2018-06-01'
	    ORDER BY TIME DESC*/
		
		
		selectBuffer.append(" SELECT 																						");
		selectBuffer.append(" A.hid AS hid,																					");
		selectBuffer.append(" B.`device_name` AS deviceName,																");
		selectBuffer.append(" B.`longitude` AS longitude,																	");
		selectBuffer.append(" B.`latitude` AS latitude,																		");
		selectBuffer.append(" B.`mango_type` AS mangoType,																	");
		selectBuffer.append(" B.basc_code AS bascCode,																		");
		selectBuffer.append(" B.basc_name AS bascName,																		");
		selectBuffer.append(" A.sensor_type AS sensorType,																	");
		selectBuffer.append(" A.display_name AS displayName,																");
		selectBuffer.append(" A.val AS val,																					");
		selectBuffer.append(" A.time AS time,																				");
		selectBuffer.append(" SUBSTR(A.time, 1, 10) shortTime 																");
		selectBuffer.append(" FROM																							");
		selectBuffer.append(" da_iot_monitor_data A,																		");
		selectBuffer.append(" da_iot_monitor_basic B 																		");
		selectBuffer.append(" WHERE A.hid = B.hid 																			");
		selectBuffer.append(" AND basc_code=																				");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(code)) {
			selectBuffer.append(code);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND sensor_type =																				");
		selectBuffer.append("'");
		selectBuffer.append(SENSOR_TYPE_ARRAY[4]);
		selectBuffer.append("'");
		selectBuffer.append(" AND val<																						");
		selectBuffer.append(drought);
		selectBuffer.append(" AND SUBSTR(time, 1, 10) = 																	");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(date)) {
			selectBuffer.append(date);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY time DESC																			");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		return resultModel;
	}
	
	@ApiOperation(value = "获取最新农业灾害预警信息(App接口)", notes = "获取最新农业灾害预警信息(App接口)")
	@RequestMapping(value = "/getRecentAgricultueDisasterInfoForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getRecentAgricultueDisasterInfoForApp() {
		
		//首先将 从上次查询时间到现在  da_iot_monitor_data表中满足预警条件的数据 插入到da_iot_monitor_disaster_data表中
		//agricultureDisasterEstimate();
		//然后再到da_iot_monitor_disaster_data表中查询灾害预警数据
		
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		JSONObject jsonObject = null;
		if (!StringUtils.isBlank(jsonData)) {
			jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		 /*SELECT 
			A.`disaster_type_code` AS typeCode,
			A.`disaster_type_text` AS typeText,
			SUBSTR(A.create_time, 1, 10) AS TIME
		    FROM da_iot_monitor_disaster_data A
		    ORDER BY create_time DESC;*/
		
		selectBuffer.append(" SELECT 																");
		selectBuffer.append(" A.`disaster_type_code` AS typeCode,									");
		selectBuffer.append(" A.`disaster_type_text` AS typeText,									");
		selectBuffer.append(" SUBSTR(A.create_time, 1, 10) AS time									");
		selectBuffer.append(" FROM da_iot_monitor_disaster_data A									");
		selectBuffer.append(" ORDER BY create_time DESC												");
		
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);
		return resultModel;
	}
	
	@ApiOperation(value = "接收物联网监控数据，将农业灾害数据插入到da_iot_monitor_disaster_data表中", notes = "接收物联网监控数据，将农业灾害数据插入到da_iot_monitor_disaster_data表中")
	@RequestMapping(value = "/checkUpIOTMonitorData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel checkUpIOTMonitorData() {
		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String hid = "";
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("hid")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("hid");
				if (entityRelatedObject.containsKey("hid") && StringUtils.isNotBlank(entityRelatedObject.getString("hid")))
					hid = entityRelatedObject.getString("hid");
			}
			if(jsonObject.containsKey("data")) {
				JSONArray jsonArray = jsonObject.getJSONArray("data");
				for (int i = 0; i < jsonArray.size(); i++) {
				    JSONObject object = (JSONObject) jsonArray.get(i);
				    
				    String sty = object.getString("sty");
				    String val = object.getString("val");
				    
				    //处理接收到的监控数据
				    handleMonitorData(hid,sty,val);
				}
			}
		}
		
		return resultModel;
	}

	private void handleMonitorData(String hid, String sty, String val) {
		
		
		
	}
	
}



