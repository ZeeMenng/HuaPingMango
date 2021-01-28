package com.zee.app.custom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.base.BaseSwgApp;
import com.zee.bll.extend.unity.da.DaIotMonitorDataUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.utl.DateUtils;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.TimesView;
import com.mongodb.client.MongoCollection;

import net.sf.json.JSONObject;

@RestController
@RequestMapping(value = "/mongodb")
public class MongodbController extends BaseSwgApp {
	
	private final Logger log = LoggerFactory.getLogger(MongodbController.class);
	
	private String KEY = "JTD4JL47qaz30RSf";
	
	private int PAGEINDEX = 1;
	
	private int PAGESIZE = 100000000;
	
	@Autowired
	@Qualifier("daIotMonitorDataUntBll")
	protected DaIotMonitorDataUntBll daIotMonitorDataUntBll;
	
	@Autowired
    private MongoCollection<Document> mongoCollection;
	
	/**
	 * 此方法会清空mongodb中的物联网数据，同步mysql的物联网数据
	 * @return
	 */
	@RequestMapping(value = "/dataSync", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel dataSync() {
		System.out.println("dataSync#########begin!!!!!!!!!");
		ResultModel resultModel = new ResultModel();
		String key = request.getParameter("key");
		if(key == null || !key.equals(KEY)){
			return resultModel;
		}
		try {
			mongoCollection.drop();
			Map<String, String> map = new HashMap<String, String>();
			map.put("viewName", "date");//视图名，年year，月month，日date，小时hour（默认为年）
			map.put("hasCurrent", "true");
			map.put("pastNum", getPastNum());
			List<String> timesList = TimesView.getTimesView(map);
			for (String timeStr : timesList) {
				insert(timeStr);
			}
			resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		} catch (Exception e) {
			log.error("dataSyncException" + e.getMessage(), e);
			e.printStackTrace();
		}
		System.out.println("dataSync#########end!!!!!!!!!");
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	private String getPastNum(){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" SELECT DATE_FORMAT(MIN(t.time),'%Y-%m-%d') minTime FROM da_iot_monitor_data t ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		String minTime = modelList.get(0).get("minTime").toString();
		int dd = DateUtils.differentDays(DateUtils.string2Date(minTime, SymbolicConstant.DATE_FORMAT), DateUtils.getCurrentDate());
		return String.valueOf(dd);
	}
	
	@SuppressWarnings("unchecked")
	private void insert(String timeStr) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                            ");
		selectBuffer.append("		A.id id,                                                      ");
		selectBuffer.append("		A.hid hid,                                                    ");
		selectBuffer.append("		A.sensor_type sensorType,                                     ");
		selectBuffer.append("		A.sensor_name sensorName,                                     ");
		selectBuffer.append("		A.display_name displayName,                                   ");
		selectBuffer.append("		A.val val,                                                    ");
		selectBuffer.append("		A.unit unit,                                                  ");
		selectBuffer.append("		DATE_FORMAT(A.time,'%Y-%m-%d %H:%i:%s') time,                 ");
		selectBuffer.append("		A.remark remark                                               ");
		selectBuffer.append("	FROM                                                              ");
		selectBuffer.append("		da_iot_monitor_data A                                         ");
		selectBuffer.append("	WHERE                                                             ");
		selectBuffer.append("	    DATE_FORMAT(A.time, '%Y-%m-%d') = '"+timeStr+"'               ");
		map.put("Sql", selectBuffer.toString());
		JSONObject pageObject = new JSONObject();
		pageObject.put("pageIndex", PAGEINDEX);
		pageObject.put("pageSize", PAGESIZE);
		map.put("Page", pageObject);
		ResultModel resultModel = daIotMonitorDataUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		List<Document> documents = new ArrayList<Document>();
		for (Map<String, Object> map2 : modelList) {
			Document doc = new Document();
			if(map2.get("id") != null){
				doc.append("id", map2.get("id").toString());
			}else{
				doc.append("id", null);
			}
			if(map2.get("hid") != null){
				doc.append("hid", map2.get("hid").toString());
			}else{
				doc.append("hid", null);
			}
			if(map2.get("sensorType") != null){
				doc.append("sensorType", Integer.valueOf(map2.get("sensorType").toString()));
				doc.append("sensorTypeStr", map2.get("sensorType").toString());
			}else{
				doc.append("sensorType", null);
				doc.append("sensorTypeStr", null);
			}
			if(map2.get("sensorName") != null){
				doc.append("sensorName", map2.get("sensorName").toString());
			}else{
				doc.append("sensorName", null);
			}
			if(map2.get("displayName") != null){
				doc.append("displayName", map2.get("displayName").toString());
			}else{
				doc.append("displayName", null);
			}
			if(map2.get("val") != null){
				doc.append("val", Double.valueOf(map2.get("val").toString()));
				doc.append("valStr", map2.get("val").toString());
			}else{
				doc.append("val", null);
				doc.append("valStr", null);
			}
			if(map2.get("unit") != null){
				doc.append("unit", map2.get("unit").toString());
			}else{
				doc.append("unit", null);
			}
			if(map2.get("time") != null){
				doc.append("time", DateUtils.string2Date(map2.get("time").toString(), SymbolicConstant.TIME_FORMAT));
				doc.append("timeStr", map2.get("time").toString());
			}else{
				doc.append("time", null);
				doc.append("timeStr", null);
			}
			if(map2.get("remark") != null){
				doc.append("remark", map2.get("remark").toString());
			}else{
				doc.append("remark", null);
			}
			documents.add(doc);
		}
		mongoCollection.insertMany(documents);
	}
}
