package com.zee.app.extend.swagger.mf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfQualityGenSwgApp;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.TimesView;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description 对外接口，扩展自MfQualityGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfQuality")
public class MfQualitySwgApp extends MfQualityGenSwgApp {
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	/**
	 * 
	 * @title: forecast
	 * @description: 质量综合安全指数
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

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                 ");
		selectBuffer.append("		A.id id,                                           ");
		selectBuffer.append("		IFNULL(A.index_num,0) indexNum,                    ");
		selectBuffer.append("		A.level_code levelCode,                            ");
		selectBuffer.append("		A.level_text levelText,                            ");
		selectBuffer.append("		A.date_time dateTime                               ");
		selectBuffer.append("	FROM                                                   ");
		selectBuffer.append("		mf_quality A                                       ");

		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("indexNum");
		keySet.add("levelCode");
		keySet.add("levelText");
		keySet.add("dateTime");
		map.put("Sql", selectBuffer.toString());
		resultModel = mfQualityUntBll.getListBySQL(map);
		
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");
		
		resultModel = TimesView.getTimesData(pmap, modelList,keySet);
		return resultModel;
	}

	/**
	 * 
	 * @title: getListByJsonData
	 * @description: 质量安全综合指数
	 * @author: lxl
	 * @date:2018年5月22日 下午5:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 年份
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityQuality")) {
				JSONObject entityQualityObject = jsonObject.getJSONObject("entityQuality");
				if (entityQualityObject.containsKey("year")
						&& !StringUtils.isBlank(entityQualityObject.getString("year"))) {
					year = entityQualityObject.getString("year");

				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				.append("SELECT mq.index_num exponent FROM mf_quality mq WHERE 1=1")
				.append(" AND mq.date_time=" + year);
		  
		map.put("Sql", selectBuffer.toString());

		resultModel = mfQualityUntBll.getListBySQL(map);
		return resultModel;
	}
	
	/**
	 * 
	 * @title: getListByJsonData
	 * @description: 质量地图展示
	 * @author: lxl
	 * @date:2018年5月22日 下午5:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getMapByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMapByJsonData() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				.append("SELECT gp.`code`,gp.`name`,gp.longitude,gp.latitude")
				.append(" FROM gp_region gp where gp.farther_code = '530723'");
		  
		map.put("Sql", selectBuffer.toString());
		resultModel = mfQualityUntBll.getListBySQL(map);
		
		List<Map<String, Object>> areaList =(List<Map<String, Object>>) resultModel.getData();
		List<Object> reusltList= new ArrayList<Object>();
		for(Map<String, Object> list : areaList){
			Map<String, Object> maps = new HashMap<String, Object>();
			String code = (String) list.get("code");
			
			StringBuffer selectBuffer2 = new StringBuffer();
			selectBuffer2
					.append("SELECT m.organic_area organicArea,m.inspection inspection,m.inspection_qualified inspectionQualified,")
					.append(" m.issue_times issueTimes,m.area_code areaCode,m.area_name areaName")
			        .append(" FROM mf_quality_inspection m,da_common_field d")
			        .append(" where m.area_code='"+code+"'")
			        .append(" and d.id=m.common_field_id")
			        .append(" and d.add_time=(SELECT")
			        .append(" MAX(d.add_time)")
			        .append(" FROM mf_quality_inspection m,da_common_field d")
			        .append(" where m.area_code='"+code+"'")
			        .append(" and d.id=m.common_field_id)")
			        .append(" and YEAR (d.add_time) = DATE_FORMAT(NOW(), '%Y')");
			  
			map2.put("Sql", selectBuffer2.toString());
			resultModel2 = mfQualityUntBll.getListBySQL(map2);
			List<Map<String, Object>> areaLists =(List<Map<String, Object>>) resultModel2.getData();
			maps.put("inspection",areaLists.size()>0?areaLists.get(0).get("inspection"):0);
			maps.put("inspectionQualified", areaLists.size()>0?areaLists.get(0).get("inspectionQualified"):0);
			maps.put("issueTimes", areaLists.size()>0?areaLists.get(0).get("issueTimes"):0);
			maps.put("organicArea",areaLists.size()>0?areaLists.get(0).get("organicArea"):0);
			maps.put("longitude", list.get("longitude"));
			maps.put("latitude", list.get("latitude"));
			maps.put("code", list.get("code"));
			maps.put("name", list.get("name"));
			reusltList.add(maps);
		}
		
		
		resultModel.setData(reusltList);

		return resultModel;
	}		
			
			
	/**
	 * 
	 * @title: getIdentificationInfo
	 * @description: 三品种植情况
	 * @author: lxl
	 * @date:2018年5月22日 下午5:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getIdentificationInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getIdentificationInfo() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
//		selectBuffer.append("SELECT sum(di.identification_area) identificationArea,di.identification_area_text identificationAreaText,di.identification_type_text identificationTypeText, di.identification_type_code identificationTypeCode ")
//				.append(" FROM")
//				.append(" da_identification_info di ")
//				.append(" WHERE")
//				.append(" di.identification_type_code IN (1, 2, 3)")
//				.append(" GROUP BY di.identification_type_code");
		//获取当前年
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		selectBuffer.append("select A.identification_area identificationArea,A.identification_area_text identificationAreaText,A.identification_type_text identificationTypeText,A.identification_type_code identificationTypeCode "
				+ "from da_identification_info A inner join da_common_field B on A.common_field_id=B.id "
//				+ "INNER JOIN da_enterprise_info C ON A.`name`=C.enterprise_name "
				+ " where  YEAR(B.start_time)='"+year+"'-1 "
				+ " and B.area_latitude_type_code='4' "
				+ "GROUP BY A.identification_type_code");
		map.put("Sql", selectBuffer.toString());

		StringBuffer selectBuffer2 = new StringBuffer();
//		selectBuffer2
//				.append("SELECT sum(di.identification_area) identificationArea2,di.identification_area_text identificationAreaText2")
//				.append(" FROM").append(" da_identification_info di ").append(" WHERE")
//				.append(" di.identification_type_code IN (1, 2, 3)");
		selectBuffer2.append("select sum(A.identification_area) identificationArea2,A.identification_area_text identificationAreaText2 "
				+ "from da_identification_info A inner join da_common_field B on A.common_field_id=B.id "
//				+ "INNER JOIN da_enterprise_info C ON A.`name`=C.enterprise_name "
				+ " where  YEAR(B.start_time)='"+year+"'-1 "
				+ " and B.area_latitude_type_code='4' "
				+ "GROUP BY A.identification_type_code");
		map2.put("Sql", selectBuffer2.toString());
		resultModel = mfQualityUntBll.getListBySQL(map);
		List<Map<String, Object>> list1 =  (List<Map<String, Object>>) resultModel.getData();
		Map<String, Object> strMap = new HashMap<String, Object>();
		String [] str={"1","2","3"};
		List<String> strList = new ArrayList<String>();
		
		
			 for(Map<String, Object> maps :list1){
				 strList.add(maps.get("identificationTypeCode").toString());
					
			 }
			
			for(String str1 : str){
				    if(Arrays.asList(strList.toArray()).contains(str1)==false){
				    	if(str1.equals("1")){
				    		strMap.put("identificationArea", 0);
					    	strMap.put("identificationAreaText", "亩");
					    	strMap.put("identificationTypeText", "无公害认证");
					    	strMap.put("identificationTypeCode", 1);
					    	list1.add(strMap);
				    	}
				    	if(str1.equals("2")){
				    		strMap.put("identificationArea", 0);
					    	strMap.put("identificationAreaText", "亩");
					    	strMap.put("identificationTypeText", "有机认证");
					    	strMap.put("identificationTypeCode", 2);
					    	list1.add(strMap);
				    	}
				    	if(str1.equals("3")){
				    		strMap.put("identificationArea", 0);
					    	strMap.put("identificationAreaText", "亩");
					    	strMap.put("identificationTypeText", "绿色认证");
					    	strMap.put("identificationTypeCode", 3);
					    	list1.add(strMap);
				    	}
				    	
				    }
				
				}
    	
		
		resultModel2 = mfQualityUntBll.getListBySQL(map2);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("threeCertification", list1);// 三品认证
		maps.put("totalArea", resultModel2.getData());// 三品总面积
		list.add(maps);
		resultModel.setData(list);
		return resultModel;
	}
	/**
	 * 
	 * @title: getListByJsonData
	 * @description: 投入品监管--投入品种类监管
	 * @author: lxl
	 * @date:2018年5月22日 下午5:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getInpuType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getInpuType() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String typeInput = "";// 投入品种类
		String year = "";//时间
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityTypeInput")) {
				JSONObject entityTypeInputObject = jsonObject.getJSONObject("entityTypeInput");
				if (entityTypeInputObject.containsKey("typeInput")
						&& !StringUtils.isBlank(entityTypeInputObject.getString("typeInput"))) {
					typeInput = entityTypeInputObject.getString("typeInput");

				}
				
				if (entityTypeInputObject.containsKey("year")
						&& !StringUtils.isBlank(entityTypeInputObject.getString("year"))) {
					year = entityTypeInputObject.getString("year");

				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				.append("SELECT p.`month`, CASE WHEN num IS NULL THEN '0' ELSE num END num, ")
				.append(" CASE WHEN t.jcount IS NULL THEN '0' ELSE t.jcount END jcount,m.qualityThreshold")
				.append(" FROM")
				.append(" cur_month_view p")
				.append(" LEFT JOIN")
				.append(" (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS num,mit.quality_threshold as qualityThreshold")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_input_type mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" AND mit.issue = '0'")
				.append(" AND mit.type_input = '"+typeInput+"'")
				.append(" AND YEAR(s.start_time) = '"+year+"'")
				.append(" GROUP BY months")
				.append(" ) m ON m.months = p.`month`")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS jcount,mit.quality_threshold as qualityThreshold")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_input_type mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" AND mit.type_input = '"+typeInput+"'")
				.append(" AND YEAR(s.start_time) = '"+year+"'")
				.append(" GROUP BY months")
				.append(" ) t ON t.months = p.`month`")
				.append(" ORDER BY p.`month`");
		map.put("Sql", selectBuffer.toString());

		resultModel = mfQualityUntBll.getListBySQL(map);
		List<Object> list = new ArrayList<Object>();
		Map<String,Object> dateMap = new HashMap<String,Object>();
//		List<Map<String,Object>> maps = (List<Map<String, Object>>) resultModel.getData();
//		for(Map<String,Object> thresholdMap :maps){
//			if(thresholdMap.get("qualityThreshold")!=null && !"".equals(thresholdMap.get("qualityThreshold"))){
//				list.add(thresholdMap.get("qualityThreshold"));
//			}
//		}
		Map<String, Object> warningMap =getDictionary("ffea2e4a2e174986bf01d091b00537c2","4");
		dateMap.put("dateList", resultModel.getData());
		dateMap.put("qualityThreshold",warningMap.get("warning"));
		resultModel.setData(dateMap);
		return resultModel;
	}
	
	/**
	 * 
	 * @title: getListByJsonData
	 * @description: 投入品监管--投入品主体监管
	 * @author: lxl
	 * @date:2018年5月22日 下午5:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getInputSubject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getInputSubject() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityInputSubject")) {
				JSONObject entityInputSubjectObject = jsonObject.getJSONObject("entityInputSubject");
				if (entityInputSubjectObject.containsKey("year")
						&& !StringUtils.isBlank(entityInputSubjectObject.getString("year"))) {
					year = entityInputSubjectObject.getString("year");

				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				.append("SELECT p.`month`, CASE WHEN num IS NULL THEN '0' ELSE num END num, ")
				.append(" CASE WHEN t.ztcont IS NULL THEN '0' ELSE t.ztcont END ztcont")
				.append(" FROM")
				.append(" cur_month_view p")
				.append(" LEFT JOIN")
				.append(" (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS num")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_input_subject mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" AND mit.punish = '1'")
				.append(" and year(s.start_time) =  '"+year+"'")
				.append(" GROUP BY months")
				.append(" ) m ON m.months = p.`month`")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS ztcont")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_input_subject mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" and year(s.start_time) = '"+year+"'")
				.append(" GROUP BY months")
				.append(" ) t ON t.months = p.`month`")
				.append(" ORDER BY p.`month`");
		map.put("Sql", selectBuffer.toString());

		resultModel = mfQualityUntBll.getListBySQL(map);
		Map<String, Object> warningMap =getDictionary("ffea2e4a2e174986bf01d091b00537c2","2");
		Map<String, Object> map2 = new HashMap<String,Object>();
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
		map2.put("data", list);
		map2.put("warning", warningMap.get("warning"));
		resultModel.setData(map2);
		return resultModel;
	}
	
	
	/**
	 * 
	 * @title: getListByJsonData
	 * @description: 扫码反馈--根据年份查询每个月的扫码量(扫码数量和 质量问题数量)
	 * @author: lxl
	 * @date:2018年5月22日 下午5:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getScavenging", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getScavenging() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityScavenging")) {
				JSONObject entityScavengingObject = jsonObject.getJSONObject("entityScavenging");
				if (entityScavengingObject.containsKey("year")
						&& !StringUtils.isBlank(entityScavengingObject.getString("year"))) {
					year = entityScavengingObject.getString("year");

				}
			}
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				.append("SELECT p.`month`, CASE WHEN wtnum IS NULL THEN '0' ELSE wtnum END wtnum, ")
				.append(" CASE WHEN  t.smcount IS NULL THEN '0' ELSE  t.smcount END smcount")
				.append(" FROM")
				.append(" cur_month_view p")
				.append(" LEFT JOIN")
				.append(" (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS wtnum")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_scavenging mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" AND mit.status = '1'")
				.append(" and year(s.start_time) =  '"+year+"'")
				.append(" GROUP BY months")
				.append(" ) m ON m.months = p.`month`")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS smcount")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_scavenging mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" and year(s.start_time) = '"+year+"'")
				.append(" GROUP BY months")
				.append(" ) t ON t.months = p.`month`")
				.append(" ORDER BY p.`month`");
		map.put("Sql", selectBuffer.toString());
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2
				.append("SELECT IFNULL(t.smcount,0) as zcount,IFNULL(m.smcount,0) as wtcount ")
				.append(" FROM")
				.append(" (SELECT")
				.append(" count(mit.id) AS smcount, mit.id as id")
				.append(" FROM")
				.append(" da_common_field s,mf_scavenging mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" and year(s.start_time) = '"+year+"') t LEFT JOIN")
				.append(" (SELECT")
				.append(" count(mit.id) AS smcount,mit.id as id")
				.append(" FROM")
				.append(" da_common_field s,mf_scavenging mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" and mit.status='1' and year(s.start_time) ='"+year+"') m on t.id=m.id");
		map2.put("Sql", selectBuffer2.toString());
		
		resultModel = mfQualityUntBll.getListBySQL(map);
		resultModel2 = mfQualityUntBll.getListBySQL(map2);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> warningMap =getDictionary("ffea2e4a2e174986bf01d091b00537c2","3");
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("smcount", resultModel.getData());// 扫码数量图
		maps.put("qualityProblem", resultModel2.getData());// 扫码数量和 质量问题数量
		maps.put("qualityWarning", warningMap.get("warning"));
		list.add(maps);
		resultModel.setData(list);
				
		return resultModel;
	}
	
	
	/**
	 * 
	 * @title: getQualitInspection
	 * @description: 质量检测
	 * @author: lxl
	 * @date:2018年5月22日 下午5:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getQualitInspection", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getQualitInspection() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityScavenging")) {
				JSONObject entityScavengingObject = jsonObject.getJSONObject("entityScavenging");
				if (entityScavengingObject.containsKey("year")
						&& !StringUtils.isBlank(entityScavengingObject.getString("year"))) {
					year = entityScavengingObject.getString("year");

				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT ")
				.append(" t1.`month` AS month,")
				.append(" t1.inspectionQualified AS inspectionQualified,")
				.append(" t2.lastInspectionQualified AS lastInspectionQualified")
				.append(" FROM")
				.append(" (")
				.append(" SELECT")
				.append(" p.`month`, IFNULL(inspectionQualified, 0) AS inspectionQualified")
				.append(" FROM")
				.append(" cur_month_view p")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(mf.creat_time, '%m') AS months,")
				.append(" mf.inspection_qualified AS inspectionQualified,")
				.append(" ABS(NOW() - mf.creat_time) AS diffTime")
				.append(" FROM")
				.append(" mf_quality_inspection_rate mf")
				.append(" WHERE")
				.append(" YEAR (mf.creat_time) = '"+year+"' ")
				.append(" GROUP BY months")
				.append(" ORDER BY diffTime ASC")
				.append(" ) m ON m.months = p.`month`")
				.append(" ORDER BY p.`month`")
				.append(" ) t1")
				.append(" LEFT JOIN (")
				.append(" SELECT p.`month`, IFNULL(inspectionQualified, 0) AS lastInspectionQualified")
				.append(" FROM")
				.append(" cur_month_view p")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(mf.creat_time, '%m') AS months,")
				.append(" mf.inspection_qualified AS inspectionQualified,")
				.append(" ABS(NOW() - mf.creat_time) AS diffTime")
				.append(" FROM")
				.append(" mf_quality_inspection_rate mf")
				.append(" WHERE")
				.append(" YEAR (mf.creat_time) = DATE_FORMAT(NOW(), '%Y') - 1")
				.append(" GROUP BY months")
				.append(" ORDER BY diffTime ASC")
				.append(" ) m ON m.months = p.`month`")
				.append(" ORDER BY p.`month`")
				.append(" ) t2 ON t1.`month` = t2.`month`");
		map.put("Sql", selectBuffer.toString());
		Map<String, Object> map2 = new HashMap<String, Object>();
		resultModel=mfQualityUntBll.getListBySQL(map);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
		Map<String, Object> warningMap =getDictionary("ffea2e4a2e174986bf01d091b00537c2","1");
		map2.put("data", list);
		map2.put("warning", warningMap.get("warning"));
		resultModel.setData(map2);
		return resultModel;
		
	}
	public Map getDictionary(String type,String code){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("  SELECT text as name ,code  from gp_dictionary  ");
		selectBuffer.append("  where type_id ='"+type+"'   and code =  '"+code+"' ");
		selectBuffer.append(" GROUP BY name ");
		
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gpDictionaryUntBll.getListBySQL(map);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
		Map<String,Object> maps = new HashMap<String,Object>();
		for(Map<String, Object> lists:list){
			maps.put("warning", lists.get("name"));
		}
		return maps;
		
	}
	/**
	 * 质量安全监测预警
	 * lxl
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getWarningSum", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWarningSum() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityScavenging")) {
				JSONObject entityScavengingObject = jsonObject.getJSONObject("entityScavenging");
				if (entityScavengingObject.containsKey("year")
						&& !StringUtils.isBlank(entityScavengingObject.getString("year"))) {
					year = entityScavengingObject.getString("year");

				}
			}
		}
		
		Map<String, Object> warningMap =getDictionary("ffea2e4a2e174986bf01d091b00537c2","2");//投入品主体
		Map<String, Object> warningMap2 =getDictionary("ffea2e4a2e174986bf01d091b00537c2","1");//质量检测
		Map<String, Object> warningMap3 =getDictionary("ffea2e4a2e174986bf01d091b00537c2","3");//质量反馈
		Map<String, Object> warningMap4 =getDictionary("ffea2e4a2e174986bf01d091b00537c2","4");//投入品使用
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				.append("SELECT  COUNT(m.num) yjCount ")
				.append(" FROM")
				.append(" cur_month_view p")
				.append(" LEFT JOIN")
				.append(" (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS num")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_input_subject mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" and year(s.start_time) =  '"+year+"'")
				.append(" AND mit.punish = '1'")
				.append(" GROUP BY months")
				.append(" ) m ON m.months = p.`month`")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%m') AS months,")
				.append(" count(mit.id) AS ztcont")
				.append(" FROM")
				.append(" da_common_field s,")
				.append(" mf_input_subject mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" and year(s.start_time) =  '"+year+"'")
				.append(" GROUP BY months")
				.append(" ) t ON t.months = p.`month` where  m.num > '"+warningMap.get("warning")+"' ")
				.append(" ORDER BY p.`month`");
		map.put("Sql", selectBuffer.toString());

		resultModel = mfQualityUntBll.getListBySQL(map);
		List<Map<String, Object>> list2 = (List<Map<String, Object>>) resultModel.getData();
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("SELECT ")
				.append(" COUNT(t1.inspectionQualified) as  yjCount ")
				.append(" FROM")
				.append(" (")
				.append(" SELECT")
				.append(" p.`month`, IFNULL(inspectionQualified, 0) AS inspectionQualified")
				.append(" FROM")
				.append(" cur_month_view p")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(mf.creat_time, '%m') AS months,")
				.append(" mf.inspection_qualified AS inspectionQualified,")
				.append(" ABS(NOW() - mf.creat_time) AS diffTime")
				.append(" FROM")
				.append(" mf_quality_inspection_rate mf")
				.append(" where YEAR (mf.creat_time) = '"+year+"' ")
				.append(" GROUP BY months")
				.append(" ORDER BY diffTime ASC")
				.append(" ) m ON m.months = p.`month`")
				.append(" ORDER BY p.`month`")
				.append(" ) t1")
				.append(" where t1.inspectionQualified > '"+warningMap2.get("warning")+"' ")
		;
		map2.put("Sql", selectBuffer2.toString());
		resultModel=mfQualityUntBll.getListBySQL(map2);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
		
		
		
		Map<String, Object> map3 = new HashMap<String, Object>();
		StringBuffer selectBuffer3 = new StringBuffer();
		selectBuffer3
				.append("SELECT COUNT(t.smcount) yjCount ")
				.append(" FROM")
				.append(" (SELECT")
				.append(" count(mit.id) AS smcount")
				.append(" FROM")
				.append(" da_common_field s,mf_scavenging mit")
				.append(" WHERE")
				.append("  year(s.start_time) =  '"+year+"'")
				.append(" and s.id = mit.common_field_id ")
				.append(" AND mit. STATUS = '1'")
				.append(" )  t where t.smcount > '"+warningMap3.get("warning")+"' ");
				
		map3.put("Sql", selectBuffer3.toString());
		
		resultModel = mfQualityUntBll.getListBySQL(map3);
		List<Map<String, Object>> list3 = (List<Map<String, Object>>) resultModel.getData();
		
		Map<String, Object> map4 = new HashMap<String, Object>();
		StringBuffer selectBuffer4 = new StringBuffer();
		selectBuffer4
				.append("SELECT IFNULL(sum(t.cnt-'"+warningMap4.get("warning")+"'),0) yjCount  ")
				.append(" from(")
				.append(" SELECT COUNT(1) cnt")
				.append(" FROM da_common_field s,mf_input_type mit")
				.append(" WHERE")
				.append(" s.id = mit.common_field_id")
				.append(" AND YEAR(s.start_time) = '"+year+"'")
				.append(" AND mit.issue = '0'  GROUP BY mit.type_input ) t ")
				.append(" where t.cnt> '"+warningMap4.get("warning")+"' ");
				
		map4.put("Sql", selectBuffer4.toString());
		
		resultModel = mfQualityUntBll.getListBySQL(map4);
		List<Map<String, Object>> list4 = (List<Map<String, Object>>) resultModel.getData();
		String cnt =String.valueOf(list4.get(0).get("yjCount"));
		Map<String,Object> yjMap = new HashMap<String,Object>();
		yjMap.put("zljcCount", list.get(0).get("yjCount"));
		yjMap.put("ztCount", list2.get(0).get("yjCount"));
		yjMap.put("zlfkCount", list3.get(0).get("yjCount"));
		yjMap.put("inputUseCount",  cnt.equals(0)?0:cnt.substring(0, cnt.length()-2));
		resultModel.setData(yjMap);
		return resultModel;
		
	}
}
