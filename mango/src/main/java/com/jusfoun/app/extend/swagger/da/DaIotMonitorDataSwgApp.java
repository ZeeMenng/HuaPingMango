package com.jusfoun.app.extend.swagger.da;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.da.DaIotMonitorDataGenSwgApp;
import com.jusfoun.bll.extend.unity.da.DaIotMonitorBasicUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.parameter.da.DaIotMonitorDataParameter;
import com.jusfoun.set.enumer.StatusEnum;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.MongoUtil;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.TimesView;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaIotMonitorDataGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daIotMonitorData")
public class DaIotMonitorDataSwgApp extends DaIotMonitorDataGenSwgApp {

	// 总预警 0 0
	// 空气温度预警 1 770
	// 空气湿度预警 2 769
	// 土壤温度预警 3 788
	// 土壤湿度预警 4 787
	// 风速预警 5 781
	// 降雨量预警 6 780
	// 日雨预警 7 797
	// 光照强度预警 8 782

	// //空气温度下限
	// double minAirTemperature = 13.60;
	// //空气温度上限
	// double maxAirTemperature = 37.60;
	//
	// //物联网各个类型预警阈值
	// double total = 0.00;
	// double airTemperature = 37.60;
	// double airHumidity = 99.89;
	// double soilTemperature = 38.00;
	// double soilHumidity = 79.80;
	// double windSpeed = 4.10;
	// double rainfall = 1.00;
	// double dayRain = 59.00;
	// double illuminationIntensity = 147001.00;
	//
	// //农业灾害评估各个类型预警阈值
	// //冻害
	// double freezeInjury = 15.5;
	// //暴雨
	// double torrentialRain = 9.8;
	// //干旱
	// double drought = 3.3;
	// //大风
	// double highWind = 0.8;

	// 空气温度下限
	double minAirTemperature = 10.00;
	// 空气温度上限
	double maxAirTemperature = 37.00;

	// 物联网各个类型预警阈值
	double total = 0.00;
	double airTemperature = 37.00;
	double airHumidity = 90.00;
	double soilTemperature = 35.00;
	double soilHumidity = 50.00;
	double windSpeed = 4.20;
	double rainfall = 4.00;
	double dayRain = 50.00;
	double illuminationIntensity = 147001.00;

	// 农业灾害评估各个类型预警阈值
	// 冻害
	double freezeInjury = 5.0;
	// 暴雨
	double torrentialRain = 50.00;
	// 干旱
	double drought = 50.00;
	// 大风
	double highWind = 17.2;

	private static final String[] SENSOR_TYPE_ARRAY = { "0", "770", "769", "788", "787", "781", "780", "797", "782" };
	private double[] THRESHOLD_VALUE = { total, airTemperature, airHumidity, soilTemperature, soilHumidity, windSpeed, rainfall, dayRain, illuminationIntensity };

	// 物联网设备初次安装时间
	private static final String IOT_DEVICE_INSTALL_DATE = "2018-05-25 00:00:00";

	// 对应数据字典表（dictionary）中的文本字段(text) 农业灾害类型 0：全部 1：冻害，2：暴雨，3：干旱，4：大风
	private static final String[] DISASTER_TYPE_TEXT = { "全部", "冻害", "暴雨", "干旱", "大风" };

	@Autowired
	private MongoUtil mongoUtil;

	@Autowired
	@Qualifier("daIotMonitorBasicUntBll")
	protected DaIotMonitorBasicUntBll daIotMonitorBasicUntBll;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询设备状态", notes = "查询设备状态")
	@RequestMapping(value = "/getDeviceStatus/{bascCode}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getDeviceStatus(@PathVariable("bascCode") String bascCode) {

		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                       ");
		selectBuffer.append("		A.hid hid,                                               ");
		selectBuffer.append("		A.device_name deviceName,                                ");
		selectBuffer.append("		A.basc_code bascCode,                                    ");
		selectBuffer.append("		A.basc_name bascName,                                    ");
		selectBuffer.append("		A.address address,                                       ");
		selectBuffer.append("		A.longitude longitude,                                   ");
		selectBuffer.append("		A.latitude latitude,                                     ");
		selectBuffer.append("		A.mango_type mangoType,                                  ");
		selectBuffer.append("		A.plant_area plantArea,                                  ");
		selectBuffer.append("		A.farmers farmers,                                       ");
		selectBuffer.append("		A.person person,                                         ");
		selectBuffer.append("		A.telephone telephone,                                   ");
		selectBuffer.append("		A.remark remark,                                         ");
		//默认所有设备都正常
		selectBuffer.append("		" + StatusEnum.ZHENGCHANG.getCode() + " deviceStatus            ");
		selectBuffer.append("	FROM                                                         ");
		selectBuffer.append("		da_iot_monitor_basic A                                   ");
		if(!bascCode.equals("")){
			selectBuffer.append("	WHERE A.basc_code = '" + bascCode + "'                           ");
		}
		sqlMap.put("Sql", selectBuffer.toString());
		ResultModel result = daIotMonitorBasicUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) result.getData();

		/**从mongdb中查询设备最新数据，判断设备异常状态
		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "hour");// 视图名，年year，月month，日date，小时hour（默认为年）
		timeMap.put("pastNum", SymbolicConstant.DEVICE_ERROR_TIME);
		List<String> timesList = TimesView.getTimesView(timeMap);

		Document sub_match = new Document();
		sub_match.put("time", new Document("$gte", DateUtils.string2Date(timesList.get(0), "yyyy-MM-dd HH")));
		Document sub_group = new Document();
		sub_group.put("_id", "$hid");
		Document match = new Document("$match", sub_match);
		Document group = new Document("$group", sub_group);

		List<Document> aggregateList = new ArrayList<Document>();
		aggregateList.add(match);
		aggregateList.add(group);

		List<Map<String, Object>> dataList = mongoUtil.queryForAggregate("DaIotMonitorData", aggregateList);
		Set<Object> hidSet = new HashSet<Object>();
		for (Map<String, Object> dataMap : dataList) {
			hidSet.add(dataMap.get("_id"));
		}

		for (Map<String, Object> modelMap : modelList) {
			if (hidSet.contains(modelMap.get("hid"))) {
				modelMap.put("deviceStatus", StatusEnum.ZHENGCHANG.getCode());
			}
		}
		**/
		//从gp_message表（联网设备故障预警，2小时预警一次）中查询设备最新数据，判断设备异常状态
		Map<String, Object> msgMap = new HashMap<String, Object>();
		StringBuffer msgSql = new StringBuffer();
		msgSql.append("SELECT A.id id,A.user_id userId,A.user_name userName,A.title title,A.content content,A.remark remark,A.add_time addTime ");
		msgSql.append(" FROM gp_message A ");
		msgSql.append(" WHERE A.add_time >= DATE_ADD(NOW(), INTERVAL - " + SymbolicConstant.DEVICE_ERROR_TIME + " HOUR) ");
		msgMap.put("Sql", msgSql.toString());
		ResultModel msgRes = daIotMonitorBasicUntBll.getListBySQL(msgMap);
		List<Map<String, Object>> msgModelList = (List<Map<String, Object>>) msgRes.getData();
		if(msgModelList != null && msgModelList.size() > 0){
			for(Map<String, Object> msgModel : msgModelList){
				String title = (String) msgModel.get("title");
				String content = (String) msgModel.get("content");
				if(SymbolicConstant.DEVICE_ERROR_MSG_TITLE.equals(title)){
					for (Map<String, Object> modelMap : modelList) {
						String hid = (String) modelMap.get("hid");
						if (content.contains(hid)) {
							modelMap.put("deviceStatus", StatusEnum.YICHANG.getCode());
							break;
						}
					}
				}
			}
		}
		
		return result;
	}

	@ApiOperation(value = "批量新增", notes = "新增多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaIotMonitorDataAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody DaIotMonitorDataParameter.AddList jsonData) throws Exception {
		ResultModel result = new ResultModel();
		result = daIotMonitorDataUntBll.add(jsonData.getEntityList(),false);
		mongoUtil.insertMany(jsonData.getEntityList());
		return result;
	}

	/**
	 * 根据设备id获取实时监测数据
	 * @return
	 */
	@ApiOperation(value = "根据设备id获取实时监测数据", notes = "根据设备id获取实时监测数据")
	@RequestMapping(value = "/getActualTimeDataByDeviceId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getActualTimeDataByDeviceId() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String deviceId = "";// 设备id

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("deviceId") && StringUtils.isNotBlank(entityRelatedObject.getString("deviceId")))
					deviceId = entityRelatedObject.getString("deviceId");
			}
		}

		if (StringUtils.isBlank(deviceId)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
		timeMap.put("hasCurrent", "true");
		timeMap.put("pastNum", "1");
		List<String> timesList = TimesView.getTimesView(timeMap);

		selectBuffer.append("			SELECT                                        ");
		selectBuffer.append("		    b.`time` AS time,                             ");
		selectBuffer.append("		    c.`basc_name` AS bascName,                    ");
		selectBuffer.append("		    b.sensor_type AS sensorType,                  ");
		selectBuffer.append("		    b.`display_name` AS displayName,              ");
		selectBuffer.append("		    b.`val` AS val,                               ");
		selectBuffer.append("		    b.`unit` AS unit                              ");
		selectBuffer.append("		FROM                                              ");
		selectBuffer.append("		    (SELECT                                       ");
		selectBuffer.append("		        *                                         ");
		selectBuffer.append("		    FROM                                          ");
		selectBuffer.append("		        da_iot_monitor_data a                     ");
		selectBuffer.append("		    WHERE                                         ");
		selectBuffer.append("		        a.`hid` = '").append(deviceId).append("'                      ");
		selectBuffer.append("		    ORDER BY a.time DESC                          ");
		selectBuffer.append("		    LIMIT 0 , 9) b                                ");
		selectBuffer.append("		        INNER JOIN                                ");
		selectBuffer.append("		    da_iot_monitor_basic c ON b.hid = c.hid       ");
		selectBuffer.append("		GROUP BY b.sensor_type                           ");

		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		Object resultObject = resultModel.getData();
		List<Map<String, Object>> resultList = null;
		if (resultObject != null) {
			resultList = (List<Map<String, Object>>) resultObject;
			String sensorType = "";
			double val = 0;
			String unit = "";
			String time = "";
			String bascName = "";
			for (Map<String, Object> tempMap : resultList) {
				if (tempMap.containsKey("bascName")) {
					bascName = (String) tempMap.get("bascName");
				}
				resultMap.put("bascName", bascName);
				if (tempMap.containsKey("time")) {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					time = df.format(tempMap.get("time"));
				}
				resultMap.put("time", time);
				if (tempMap.containsKey("sensorType")) {
					sensorType = "" + tempMap.get("sensorType");
				}
				if (tempMap.containsKey("val")) {
					val = (double) tempMap.get("val");
				}
				if (tempMap.containsKey("unit")) {
					unit = (String) tempMap.get("unit");
				}
				dataMap.put(sensorType, val + unit);
			}
			resultMap.put("data", dataMap);
		}

		resultModel.setData(resultMap);
		return resultModel;
	}

	/**
	 * 根据设备id获取过去24小时的监测数据
	 * @return
	 */
	@ApiOperation(value = "根据设备id获取过去24小时的监测数据", notes = "根据设备id获取过去24小时的监测数据")
	@RequestMapping(value = "/getTimesDataByDeviceId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTimesDataByDeviceId() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String deviceId = "";// 设备id
		String sensorType = "";// 传感器类型

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("deviceId") && StringUtils.isNotBlank(entityRelatedObject.getString("deviceId")))
					deviceId = entityRelatedObject.getString("deviceId");
				if (entityRelatedObject.containsKey("sensorType") && StringUtils.isNotBlank(entityRelatedObject.getString("sensorType")))
					sensorType = entityRelatedObject.getString("sensorType");
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

//		Map<String, String> timeMap = new HashMap<String, String>();
//		timeMap.put("viewName", "hour");// 视图名，年year，月month，日date，hour时（默认为年）
//		timeMap.put("hasCurrent", "false");
//		timeMap.put("pastNum", "24");
//		List<String> timesList = TimesView.getTimesView(timeMap);

		selectBuffer.append(" SELECT														");
		selectBuffer.append(" DATE_FORMAT(a.time, '%Y-%m-%d %H') shortTime,														");
		selectBuffer.append(" a.display_name displayName,														");
		selectBuffer.append(" a.val val,														");
		selectBuffer.append(" a.unit unit														");
		selectBuffer.append(" FROM														");
		selectBuffer.append(" (SELECT														");
		selectBuffer.append(" time, display_name, val, unit														");
		selectBuffer.append(" FROM														");
		selectBuffer.append(" da_iot_monitor_data														");
		selectBuffer.append(" WHERE														");
		selectBuffer.append(" time BETWEEN '").append(DateUtils.date2String(DateUtils.addHour(DateUtils.getCurrentTime(), -23), SymbolicConstant.DATETIME_FORMAT)).append("' AND '").append(DateUtils.getCurrentTimeStr()).append("'");
		if (StringUtils.isNotBlank(sensorType))
			selectBuffer.append(" AND sensor_type = '").append(sensorType).append("'");
		if (StringUtils.isNotBlank(deviceId))
			selectBuffer.append(" AND hid = '").append(deviceId).append("' order by time) a														");
		selectBuffer.append(" GROUP BY (UNIX_TIMESTAMP(time) DIV 3600)														");

		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Object resultObj = resultModel.getData();
		List<Map<String, Object>> resultList = null;
		if (resultObj != null) {
			resultList = (List<Map<String, Object>>) resultObj;
		}
		resultMap.put("data", resultList);

		switch (sensorType) {
		case "770":
			resultMap.put("minAirTemperature", minAirTemperature);
			resultMap.put("maxAirTemperature", maxAirTemperature);
			break;
		case "769":
			resultMap.put("airHumidity", airHumidity);
			break;
		case "788":
			resultMap.put("soilTemperature", soilTemperature);
			break;
		case "787":
			resultMap.put("soilHumidity", soilHumidity);
			break;
		case "781":
			resultMap.put("windSpeed", windSpeed);
			break;
		case "780":
			resultMap.put("rainfall", rainfall);
			break;
		case "797":
			resultMap.put("dayRain", dayRain);
			break;
		case "782":
			resultMap.put("illuminationIntensity", illuminationIntensity);
			break;
		default:
			break;
		}

		resultModel.setData(resultMap);
		return resultModel;
	}

	/**
	 * 根据设备id获取某个时间段的监测数据
	 * @return
	 */
	@ApiOperation(value = "根据设备id获取某个时间段的监测数据", notes = "根据设备id获取某个时间段的监测数据")
	@RequestMapping(value = "/getTimeQuantumDataByDeviceId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTimeQuantumDataByDeviceId() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String deviceId = "";// 设备id
		String startTime = "";// 起始时间
		String endTime = "";// 结束时间

		JSONObject jsonObject = null;
		if (!StringUtils.isBlank(jsonData)) {
			jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("deviceId") && StringUtils.isNotBlank(entityRelatedObject.getString("deviceId")))
					deviceId = entityRelatedObject.getString("deviceId");
				if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime"))) {
					startTime = entityRelatedObject.getString("startTime");
					startTime += " 00:00:00";
				} else {
					Date date = new Date();
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date);
					calendar.add(Calendar.DAY_OF_MONTH, -1);
					date = calendar.getTime();
					startTime = DateUtils.date2String(date, SymbolicConstant.DATETIME_FORMAT);
				}
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime"))) {
					endTime = entityRelatedObject.getString("endTime");
					endTime += " 23:59:59";
				} else {
					endTime = DateUtils.date2String(new Date(), SymbolicConstant.DATETIME_FORMAT);
				}

			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT t0.* FROM(																									");
		selectBuffer.append(" SELECT																											");
		selectBuffer.append(" a.time,																											");
		selectBuffer.append(" GROUP_CONCAT(CONCAT_WS('_',a.sensor_type,a.display_name,a.val,a.unit) ) data										");
		selectBuffer.append(" FROM da_iot_monitor_data a																						");
		selectBuffer.append(" WHERE a.hid =																										");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(deviceId)) {
			selectBuffer.append(deviceId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND a.time BETWEEN '");
		if (StringUtils.isNotBlank(startTime)) {
			selectBuffer.append(startTime);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND '");
		if (StringUtils.isNotBlank(endTime)) {
			selectBuffer.append(endTime);
		}
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY a.time ORDER BY a.`time` DESC																			");
		selectBuffer.append(" ) t0");

		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}

		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		List resultList = new ArrayList();
		Map<String, Object> perMap;
		Map<String, Object> dataMap;
		Object tempObj = resultModel.getData();
		List<Map<String, Object>> tempList = null;
		if (tempObj != null)
			tempList = (List<Map<String, Object>>) tempObj;
		for (Map<String, Object> tempMap : tempList) {
			perMap = new HashMap<String, Object>();
			dataMap = new HashMap<String, Object>();
			if (tempMap.containsKey("data")) {
				String data = (String) tempMap.get("data");
				String[] dataArray = data.split(",");
				if (dataArray.length > 0) {
					for (String str : dataArray) {
						String[] s = str.split("_");
						if (s.length == 4) {
							dataMap.put(s[0], s[2] + s[3]);
						}
					}
				}
			}
			perMap.put("data", dataMap);
			if (tempMap.containsKey("time")) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String time = df.format(tempMap.get("time"));
				perMap.put("time", time);
			}
			resultList.add(perMap);
		}
		resultModel.setData(resultList);
		return resultModel;

	}

	/**
	 * 根据月份和预警类型获取预警次数和预警波及到的芒果品种的占比
	 * @return
	 */
	@ApiOperation(value = "根据月份和预警类型获取预警次数和预警波及到的芒果品种的占比", notes = "根据月份和预警类型获取预警次数和预警波及到的芒果品种的占比")
	@RequestMapping(value = "/getEarlyWarningAndMangoProportion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEarlyWarningAndMangoProportion() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String time = "";// 时间（目前单位为月份）
		String warningType = "";// 预警类型

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("time") && StringUtils.isNotBlank(entityRelatedObject.getString("time")))
					time = entityRelatedObject.getString("time");
				if (entityRelatedObject.containsKey("warningType") && StringUtils.isNotBlank(entityRelatedObject.getString("warningType")))
					warningType = entityRelatedObject.getString("warningType");
			}
		}

		List resultList = new ArrayList();
		List rList = new ArrayList();
		Map<String, Object> rMap = new HashMap<String, Object>();

		long warningCount = 0;

		ResultModel mangoPropotionModel = getBaseWarningProportion(time, warningType);

		Map<String, Object> propotionMap;

		Object propotionObj = mangoPropotionModel.getData();
		List<Map<String, Object>> propotionList = null;
		if (propotionObj != null) {
			propotionList = (List<Map<String, Object>>) propotionObj;
		}

		for (Map<String, Object> tempMap : propotionList) {
			propotionMap = new HashMap<String, Object>();
			String mangoType = "";
			long count = 0;
			if (tempMap.containsKey("mangoType")) {
				mangoType = (String) tempMap.get("mangoType");
			}
			if (tempMap.containsKey("count")) {
				count = (long) tempMap.get("count");
				warningCount = warningCount + count;
			}
			propotionMap.put("mangoType", mangoType);
			propotionMap.put("count", count);
			rList.add(propotionMap);
		}
		rMap.put("totalCount", warningCount);
		rMap.put("data", rList);
		resultList.add(rMap);
		resultModel.setData(rMap);
		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		return resultModel;

	}

	/**
	 * 根据月份和预警类型获取预警次数和预警波及到的芒果品种的占比 待优化
	 * @return
	 */
	@ApiOperation(value = "根据月份和预警类型获取预警次数和预警波及到的芒果品种的占比", notes = "根据月份和预警类型获取预警次数和预警波及到的芒果品种的占比")
	@RequestMapping(value = "/getEarlyWarningAndMangoProportion11", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEarlyWarningAndMangoProportion11() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String time = "";// 时间（目前单位为月份）
		String warningType = "";// 预警类型

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("time") && StringUtils.isNotBlank(entityRelatedObject.getString("time")))
					time = entityRelatedObject.getString("time");
				if (entityRelatedObject.containsKey("warningType") && StringUtils.isNotBlank(entityRelatedObject.getString("warningType")))
					warningType = entityRelatedObject.getString("warningType");
			}
		}

		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT       					");
		selectBuffer.append("		b.basc_code baseCode,       ");
		selectBuffer.append("		b.basc_name baseName,       ");
		selectBuffer.append("		b.hid       				");
		selectBuffer.append("	FROM      						");
		selectBuffer.append("		da_iot_monitor_basic b      ");
		selectBuffer.append("	WHERE       					");
		selectBuffer.append("		b.basc_name is not null     ");
		selectBuffer.append("		b.basc_code is not null     ");
		selectBuffer.append("		ORDER BY b.basc_name        ");
		sqlMap.put("Sql", selectBuffer.toString());
		ResultModel result = daIotMonitorBasicUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) result.getData();

		// 获取传入月份的第一天和最后一天
		String startTime = time.concat("-01");

		int year = Integer.parseInt(time.substring(1, 4));
		int month = Integer.parseInt(time.substring(6, 7));
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);
		a.roll(Calendar.DATE, -1);
		String maxDate = String.valueOf(a.get(Calendar.DATE));
		String endTime = time.concat("-").concat(maxDate);

		Document sub_match = new Document();
		sub_match.put("time", new Document("$gte", DateUtils.string2Date(startTime, "yyyy-MM-dd")));
		Document sub_group = new Document();
		sub_group.put("_id", "$hid");
		Document match = new Document("$match", sub_match);
		Document group = new Document("$group", sub_group);

		List<Document> aggregateList = new ArrayList<Document>();
		aggregateList.add(match);
		aggregateList.add(group);

		List<Map<String, Object>> dataList = mongoUtil.queryForAggregate("DaIotMonitorData", aggregateList);
		Set<Object> hidSet = new HashSet<Object>();
		for (Map<String, Object> dataMap : dataList) {
			hidSet.add(dataMap.get("_id"));
		}

		for (Map<String, Object> modelMap : modelList) {
			if (hidSet.contains(modelMap.get("hid"))) {
				modelMap.put("deviceStatus", StatusEnum.ZHENGCHANG.getCode());
			}
		}

		return result;
	}

	// 根据月份和预警类型获取预警次数
	public ResultModel getEarlyWarningCount(String time, String warningType) {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT COUNT(*) count     																			");
		selectBuffer.append(" FROM da_iot_monitor_data a                    														");
		selectBuffer.append(" WHERE val>																								");
		if (StringUtils.isNotBlank(warningType)) {
			selectBuffer.append(THRESHOLD_VALUE[Integer.parseInt(warningType)]);
		}
		if (StringUtils.isNotBlank(warningType)) {
			if (!SENSOR_TYPE_ARRAY[Integer.parseInt(warningType)].equals("0")) {
				selectBuffer.append(" AND a.`sensor_type` =   								                                              ");
				selectBuffer.append("'");
				if (StringUtils.isNotBlank(warningType)) {
					selectBuffer.append(SENSOR_TYPE_ARRAY[Integer.parseInt(warningType)]);
				}
				selectBuffer.append("'");
			}
		}
		selectBuffer.append(" AND SUBSTR(a.`time`,1,7) =																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		return resultModel;
	}

	// 根据月份和预警类型获取预警波及到的基地的占比
	public ResultModel getBaseWarningProportion(String time, String warningType) {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT b.basc_name mangoType,count(a.hid) count														");
		selectBuffer.append(" FROM (SELECT a.* FROM da_iot_monitor_data a WHERE 1=1 and	(											");
		if (StringUtils.isNotBlank(warningType)) {
			if (SENSOR_TYPE_ARRAY[Integer.parseInt(warningType)].equals("0")) {
				for (int i = 1; i < THRESHOLD_VALUE.length; i++) {
					if (i == 1) {
						selectBuffer.append(" (val> ");
					} else {
						selectBuffer.append(" or (val> ");
					}
					selectBuffer.append(THRESHOLD_VALUE[i]);
					selectBuffer.append(" AND a.`sensor_type` =   							                                       ");
					selectBuffer.append("'");
					selectBuffer.append(SENSOR_TYPE_ARRAY[i]);
					selectBuffer.append("')");
				}
			}
		}
		if (StringUtils.isNotBlank(warningType)) {
			if (!SENSOR_TYPE_ARRAY[Integer.parseInt(warningType)].equals("0")) {
				selectBuffer.append("  val> ");
				selectBuffer.append(THRESHOLD_VALUE[Integer.parseInt(warningType)]);
				selectBuffer.append(" AND a.`sensor_type` =   								                                       ");
				selectBuffer.append("'");
				if (StringUtils.isNotBlank(warningType)) {
					selectBuffer.append(SENSOR_TYPE_ARRAY[Integer.parseInt(warningType)]);
				}
				selectBuffer.append("'");
			}
		}
		selectBuffer.append(" ) AND SUBSTR(a.`time`,1,7) =																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ) a,da_iot_monitor_basic b WHERE a.`hid`= b.`hid` GROUP BY b.basc_name order by count					");

		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		return resultModel;
	}

	/**
	 * 获取最近几年各种类型预警的走势 
	 * @return
	 */
	@ApiOperation(value = "获取最近几年各种类型预警的走势 ", notes = "获取最近几年各种类型预警的走势 ")
	@RequestMapping(value = "/getEarlyWarningDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEarlyWarningDetails() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		// 暂时只有不到两个月的数据，先按月统计把功能完成
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "month");// 视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "2");
		List<String> timesList = TimesView.getTimesView(pmap);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> perSensorYearMap = null;
		for (String str : SENSOR_TYPE_ARRAY) {
			perSensorYearMap = new HashMap<String, Object>();
			for (int i = 0; i < timesList.size(); i++) {
				ResultModel perSensorYearModel = getPerSensorYearModel(str, timesList.get(i));
				Object perSensorYearObj = perSensorYearModel.getData();
				List<Map<String, Object>> perSensorYearList = null;
				Map<String, Object> tempPerSensorYearMap = null;
				if (perSensorYearObj != null) {
					perSensorYearList = (List<Map<String, Object>>) perSensorYearObj;
					if (perSensorYearList.size() >= 1) {
						tempPerSensorYearMap = perSensorYearList.get(0);
						if (tempPerSensorYearMap.containsKey("perCount")) {
							long count = (long) tempPerSensorYearMap.get("perCount");
							perSensorYearMap.put(timesList.get(i), count);
						}
					}
				}
			}
			resultMap.put(str, perSensorYearMap);
		}
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private ResultModel getPerSensorYearModel(String sensorType, String time) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT COUNT(*) perCount FROM da_iot_monitor_data a 															");
		selectBuffer.append(" WHERE SUBSTR(a.time,1,7)=																						");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("' AND																											");

		if (StringUtils.isNotBlank(sensorType)) {
			if (!"0".equals(sensorType)) {

				selectBuffer.append(" a.sensor_type =																						");
				selectBuffer.append("'");
				selectBuffer.append(sensorType);
				selectBuffer.append("'");

				if (getIndexToArray(SENSOR_TYPE_ARRAY, sensorType) != -1 && getIndexToArray(SENSOR_TYPE_ARRAY, sensorType) != 0) {
					selectBuffer.append(" AND val>																							");
					selectBuffer.append(THRESHOLD_VALUE[getIndexToArray(SENSOR_TYPE_ARRAY, sensorType)]);
				}
			} else {
				for (int i = 1; i < SENSOR_TYPE_ARRAY.length; i++) {

					selectBuffer.append(" (a.sensor_type =																					");
					selectBuffer.append("'");
					selectBuffer.append(SENSOR_TYPE_ARRAY[i]);
					selectBuffer.append("'");

					selectBuffer.append(" AND val>																							");
					selectBuffer.append(THRESHOLD_VALUE[i]);
					selectBuffer.append(")");

					if (i != SENSOR_TYPE_ARRAY.length - 1) {
						selectBuffer.append(" OR");
					}
				}
			}
		}

		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		return resultModel;
	}

	private int getIndexToArray(String[] sensorTypeArray, String sensorType) {
		for (int i = 0; i < sensorTypeArray.length; i++) {
			if (sensorType.equals(sensorTypeArray[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 根据设备id获取实时监测数据
	 * @return
	 */
	@ApiOperation(value = "根据设备id获取实时监测数据", notes = "根据设备id获取实时监测数据")
	@RequestMapping(value = "/getActualTimeDataByDeviceIdForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getActualTimeDataByDeviceIdForApp() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String deviceId = "";// 设备id

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("deviceId") && StringUtils.isNotBlank(entityRelatedObject.getString("deviceId")))
					deviceId = entityRelatedObject.getString("deviceId");
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append("			SELECT                                        ");
		selectBuffer.append("		    b.`time` AS time,                             ");
		selectBuffer.append("		    c.`basc_name` AS bascName,                    ");
		selectBuffer.append("		    b.sensor_type AS sensorType,                  ");
		selectBuffer.append("		    b.`display_name` AS displayName,              ");
		selectBuffer.append("		    b.`val` AS val,                               ");
		selectBuffer.append("		    b.`unit` AS unit                              ");
		selectBuffer.append("		FROM                                              ");
		selectBuffer.append("		    (SELECT                                       ");
		selectBuffer.append("		        *                                         ");
		selectBuffer.append("		    FROM                                          ");
		selectBuffer.append("		        da_iot_monitor_data a                     ");
		selectBuffer.append("		    WHERE                                         ");
		selectBuffer.append("		        a.`hid` = '").append(deviceId).append("'                      ");
		selectBuffer.append("		    ORDER BY a.time DESC                          ");
		selectBuffer.append("		    LIMIT 0 , 9) b                                ");
		selectBuffer.append("		        INNER JOIN                                ");
		selectBuffer.append("		    da_iot_monitor_basic c ON b.hid = c.hid       ");
		selectBuffer.append("		GROUP BY b.sensor_type                           ");

		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = null;
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();

		Object resultObject = resultModel.getData();
		List<Map<String, Object>> resultList = null;
		if (resultObject != null) {
			resultList = (List<Map<String, Object>>) resultObject;
			String sensorType = "";
			String displayName = "";
			double val = 0;
			String unit = "";
			String time = "";
			String bascName = "";
			for (Map<String, Object> tempMap : resultList) {
				dataMap = new HashMap<String, Object>();

				if (tempMap.containsKey("bascName")) {
					bascName = (String) tempMap.get("bascName");
				}
				resultMap.put("bascName", bascName);
				if (tempMap.containsKey("time")) {
					DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					time = df.format(tempMap.get("time"));
				}
				resultMap.put("time", time);

				if (tempMap.containsKey("sensorType")) {
					sensorType = "" + tempMap.get("sensorType");
				}
				if (tempMap.containsKey("displayName")) {
					displayName = (String) tempMap.get("displayName");
				}
				if (tempMap.containsKey("val")) {
					val = (double) tempMap.get("val");
				}
				if (tempMap.containsKey("unit")) {
					unit = (String) tempMap.get("unit");
				}

				dataMap.put("sensorType", sensorType);
				dataMap.put("displayName", displayName);
				dataMap.put("val", val + unit);
				dataList.add(dataMap);
			}
			resultMap.put("data", dataList);
		}

		resultModel.setData(resultMap);
		return resultModel;
	}

	/**
	 * 根据设备id获取过去24小时的监测数据
	 * @return
	 */
	@ApiOperation(value = "根据设备id获取过去24小时的监测数据", notes = "根据设备id获取过去24小时的监测数据")
	@RequestMapping(value = "/getTimesDataByDeviceIdForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTimesDataByDeviceIdForApp() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String deviceId = "";// 设备id
		String sensorType = "";// 传感器类型

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("deviceId") && StringUtils.isNotBlank(entityRelatedObject.getString("deviceId")))
					deviceId = entityRelatedObject.getString("deviceId");
				if (entityRelatedObject.containsKey("sensorType") && StringUtils.isNotBlank(entityRelatedObject.getString("sensorType")))
					sensorType = entityRelatedObject.getString("sensorType");
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

//		Map<String, String> timeMap = new HashMap<String, String>();
//		timeMap.put("viewName", "hour");// 视图名，年year，月month，日date，hour时（默认为年）
//		timeMap.put("hasCurrent", "false");
//		timeMap.put("pastNum", "24");
//		List<String> timesList = TimesView.getTimesView(timeMap);

		selectBuffer.append(" SELECT														");
		selectBuffer.append(" DATE_FORMAT(a.time, '%Y-%m-%d %H') shortTime,														");
		selectBuffer.append(" a.display_name displayName,														");
		selectBuffer.append(" a.val val,														");
		selectBuffer.append(" a.unit unit														");
		selectBuffer.append(" FROM														");
		selectBuffer.append(" (SELECT														");
		selectBuffer.append(" time, display_name, val, unit														");
		selectBuffer.append(" FROM														");
		selectBuffer.append(" da_iot_monitor_data														");
		selectBuffer.append(" WHERE														");
		selectBuffer.append(" time BETWEEN '").append(DateUtils.date2String(DateUtils.addHour(DateUtils.getCurrentTime(), -23), SymbolicConstant.DATETIME_FORMAT)).append("' AND '").append(DateUtils.getCurrentTimeStr()).append("'");
		if (StringUtils.isNotBlank(sensorType))
			selectBuffer.append(" AND sensor_type = '").append(sensorType).append("'");
		if (StringUtils.isNotBlank(deviceId))
			selectBuffer.append(" AND hid = '").append(deviceId).append("' order by time) a														");
		selectBuffer.append(" GROUP BY (UNIX_TIMESTAMP(time) DIV 3600)                                                                          ");

		map.put("Sql", selectBuffer.toString());
		resultModel = daIotMonitorDataUntBll.getListBySQL(map);

		// 渲染y轴所需要的数据
		double maxYData = 0.00;
		double minYData = 0.00;

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Object resultObj = resultModel.getData();
		List<Map<String, Object>> resultList = null;
		if (resultObj != null) {
			resultList = (List<Map<String, Object>>) resultObj;
			if (resultList.size() >= 1) {
				int index = 0;
				for (Map<String, Object> tempMap : resultList) {
					if (tempMap.containsKey("val")) {
						double tempMaxYData = (double) tempMap.get("val");
						double tempMinYData = (double) tempMap.get("val");
						if (index <= 0) {
							maxYData = tempMaxYData;
							minYData = tempMinYData;
							index++;
						}
						if (tempMaxYData > maxYData) {
							maxYData = tempMaxYData;
						}
						if (tempMinYData < minYData) {
							minYData = tempMinYData;
						}
						tempMap.put("val", tempMap.get("val") + "");
					}
				}
				maxYData = maxYData * 1.5;
				maxYData = new BigDecimal(maxYData).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				resultMap.put("maxYData", maxYData + "");
				if (minYData >= 0) {
					minYData = minYData * 0.67;
				} else {
					minYData = minYData * 1.5;
				}
				minYData = new BigDecimal(minYData).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				resultMap.put("minYData", minYData + "");
			}
		}
		resultMap.put("data", resultList);

		switch (sensorType) {
		case "770":
			resultMap.put("min", minAirTemperature + "");
			resultMap.put("max", maxAirTemperature + "");
			break;
		case "769":
			resultMap.put("min", "");
			resultMap.put("max", airHumidity + "");
			break;
		case "788":
			resultMap.put("min", "");
			resultMap.put("max", soilTemperature + "");
			break;
		case "787":
			resultMap.put("min", "");
			resultMap.put("max", soilHumidity + "");
			break;
		case "781":
			resultMap.put("min", "");
			resultMap.put("max", windSpeed + "");
			break;
		case "780":
			resultMap.put("min", "");
			resultMap.put("max", rainfall + "");
			break;
		case "797":
			resultMap.put("min", "");
			resultMap.put("max", dayRain + "");
			break;
		case "782":
			resultMap.put("min", "");
			resultMap.put("max", illuminationIntensity + "");
			break;
		default:
			break;
		}

		resultModel.setData(resultMap);
		return resultModel;
	}

}
