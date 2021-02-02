package com.zee.app.extend.swagger.da;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.zee.app.generate.swagger.da.DaIndustryProcessGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.da.DaIndustryFreshUntBll;
import com.zee.bll.extend.unity.da.DaIndustryProcessUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaIndustryProcess;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaIndustryProcessParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
import com.zee.utl.MathUtil;
import com.zee.utl.TimesView;
import com.zee.utl.Tools;
import com.zee.utl.UnitUtil;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaIndustryProcessGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daIndustryProcess")
public class  DaIndustryProcessSwgApp extends  DaIndustryProcessGenSwgApp {
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	@Autowired
	@Qualifier("daIndustryProcessUntBll")
	protected DaIndustryProcessUntBll daIndustryProcessUntBll;
	
	@Autowired
	@Qualifier("daIndustryFreshUntBll")
	protected DaIndustryFreshUntBll daIndustryFreshUntBll;
	
	private static final String HUAPING_REGION_ID = "530723";
	
	@ApiOperation(value = "查询芒果产值结构", notes = "查询芒果产值结构-鲜果产值、加工品产值")
	@RequestMapping(value = "/getMangoIndustryStructure", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoIndustryStructure() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String regionId = "";//地区id
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
			}
		}
		
		Map<String, String> timesMap = new HashMap<String, String>();
		timesMap.put("viewName", "year");//视图名，年year，月month，日date，小时hour（默认为年）
		timesMap.put("hasCurrent", "false");
		timesMap.put("pastNum", "5");
		List<String> timesList = TimesView.getTimesView(timesMap);
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> freshList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> processList = new ArrayList<Map<String, Object>>();
		
		for(String time : timesList) {
			//鲜果产值数据
			ResultModel freshIndustryModel =  getFreshIndustryInfo(time,regionId);
			Object freshIndustryObj = freshIndustryModel.getData();
			List<Map<String,Object>> freshIndustryList = null;
			Map<String,Object> freshIndustryMap = null;
			Map<String,Object> tempFreshResultMap = new HashMap<String,Object>();
			if(freshIndustryObj!=null) {
				freshIndustryList = (List<Map<String, Object>>) freshIndustryObj;
				if(freshIndustryList.size()>=1) {
					freshIndustryMap = freshIndustryList.get(0);
					tempFreshResultMap.put("YEAR",time);
					if(freshIndustryMap.containsKey("outputSum")) {
						BigDecimal outputSum = (BigDecimal) freshIndustryMap.get("outputSum");
						tempFreshResultMap.put("outputSum", outputSum);
					}
					if(freshIndustryMap.containsKey("proportion")) {
						BigDecimal proportion = (BigDecimal) freshIndustryMap.get("proportion");
						tempFreshResultMap.put("proportion", proportion);
					}
				}else {
					tempFreshResultMap.put("YEAR", time);
					tempFreshResultMap.put("outputSum", new BigDecimal(0));
					tempFreshResultMap.put("proportion", new BigDecimal(0));
				}
				freshList.add(tempFreshResultMap);
			}
			
			//加工品产值数据
			ResultModel processIndustryModel =  getProcessIndustryInfo(time,regionId);
			Object processIndustryObj = processIndustryModel.getData();
			List<Map<String,Object>> processIndustryList = null;
			Map<String,Object> processIndustryMap = null;
			Map<String,Object> tempProcessResultMap = new HashMap<String,Object>();
			if(processIndustryObj!=null) {
				processIndustryList = (List<Map<String, Object>>) processIndustryObj;
				if(processIndustryList.size()>=1) {
					processIndustryMap = processIndustryList.get(0);
					tempProcessResultMap.put("YEAR",time);
					if(processIndustryMap.containsKey("outputSum")) {
						BigDecimal outputSum = (BigDecimal) processIndustryMap.get("outputSum");
						tempProcessResultMap.put("outputSum", outputSum);
					}
					if(processIndustryMap.containsKey("proportion")) {
						BigDecimal proportion = (BigDecimal) processIndustryMap.get("proportion");
						tempProcessResultMap.put("proportion", proportion);
					}
				}else {
					tempProcessResultMap.put("YEAR", time);
					tempProcessResultMap.put("outputSum", new BigDecimal(0));
					tempProcessResultMap.put("proportion", new BigDecimal(0));
				}
				processList.add(tempProcessResultMap);
			}
		}
		
		Map<String, Object> freshResultMap = new HashMap<String, Object>();
		Map<String, Object> processResultMap = new HashMap<String, Object>();
		
		freshResultMap.put("freshList", freshList);
		processResultMap.put("processList", processList);
		resultList.add(freshResultMap);
		resultList.add(processResultMap);
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
//		List<Map<String, Object>> freshList = getListFromModel(freshIndustryModel);
//		List<Map<String, Object>> processList = getListFromModel(processIndustryModel);
//		
//		Map<String, Object> freshResultMap = new HashMap<String, Object>();
//		Map<String, Object> processResultMap = new HashMap<String, Object>();
//		
//		freshResultMap.put("freshList", freshList);
//		processResultMap.put("processList", processList);
//		
//		resultList.add(freshResultMap);
//		resultList.add(processResultMap);
	
	
	
	//造假数据begin
	/*resultList = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> freshResultMap = new HashMap<String, Object>();
		Map<String, Object> processResultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> freshList = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> perFreshMap_1 = new HashMap<String, Object>();
		perFreshMap_1.put("YEAR", "2013");
		perFreshMap_1.put("outputSum", "950000000");
		perFreshMap_1.put("proportion", "32");
		Map<String,Object> perFreshMap_2 = new HashMap<String, Object>();
		perFreshMap_2.put("YEAR", "2014");
		perFreshMap_2.put("outputSum", "990000000");
		perFreshMap_2.put("proportion", "33");
		Map<String,Object> perFreshMap_3 = new HashMap<String, Object>();
		perFreshMap_3.put("YEAR", "2015");
		perFreshMap_3.put("outputSum", "1010000000");
		perFreshMap_3.put("proportion", "31");
		Map<String,Object> perFreshMap_4 = new HashMap<String, Object>();
		perFreshMap_4.put("YEAR", "2016");
		perFreshMap_4.put("outputSum", "1130000000");
		perFreshMap_4.put("proportion", "29");
		Map<String,Object> perFreshMap_5 = new HashMap<String, Object>();
		perFreshMap_5.put("YEAR", "2017");
		perFreshMap_5.put("outputSum", "980000000");
		perFreshMap_5.put("proportion", "41");
		
		freshList.add(perFreshMap_1);
		freshList.add(perFreshMap_2);
		freshList.add(perFreshMap_3);
		freshList.add(perFreshMap_4);
		freshList.add(perFreshMap_5);
		
		List<Map<String, Object>> processList = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> perProcesMap_1 = new HashMap<String, Object>();
		perProcesMap_1.put("YEAR", "2013");
		perProcesMap_1.put("outputSum", "110000000");
		perProcesMap_1.put("proportion", "30");
		Map<String,Object> perProcesMap_2 = new HashMap<String, Object>();
		perProcesMap_2.put("YEAR", "2014");
		perProcesMap_2.put("outputSum", "140000000");
		perProcesMap_2.put("proportion", "40");
		Map<String,Object> perProcesMap_3 = new HashMap<String, Object>();
		perProcesMap_3.put("YEAR", "2015");
		perProcesMap_3.put("outputSum", "120000000");
		perProcesMap_3.put("proportion", "43");
		Map<String,Object> perProcesMap_4 = new HashMap<String, Object>();
		perProcesMap_4.put("YEAR", "2016");
		perProcesMap_4.put("outputSum", "130000000");
		perProcesMap_4.put("proportion", "29");
		Map<String,Object> perProcesMap_5 = new HashMap<String, Object>();
		perProcesMap_5.put("YEAR", "2017");
		perProcesMap_5.put("outputSum", "111000000");
		perProcesMap_5.put("proportion", "34");
		
		processList.add(perProcesMap_1);
		processList.add(perProcesMap_2);
		processList.add(perProcesMap_3);
		processList.add(perProcesMap_4);
		processList.add(perProcesMap_5);
		
		freshResultMap.put("freshList", freshList);
		processResultMap.put("processList", processList);
		
		resultList.add(freshResultMap);
		resultList.add(processResultMap);*/
	//造假数据end
	
	private Map<String, Object> getMapFromModel(ResultModel resultModel) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> objList = null;
		Map<String, Object> resultMap = null;
		if (obj != null) {
			objList = (List<Map<String, Object>>) obj;
			if(objList.size()>=1) {
				resultMap = objList.get(0);
			}
		}
		return resultMap;
	}
	
	private ResultModel getFreshIndustryInfo(String time,String regionId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    SUBSTR(B.start_time, 1, 4) TIME,
		    B.start_time AS startTime,
		    A.output_value_unit AS outputSum,
		    A.proportion AS proportion 
		FROM
		    da_industry_fresh A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.strains_code = 0 
		    AND B.data_time_type_code = 1
		    AND B.area_latitude_type_code = 4 
		    AND B.audit_status_code = 1
		    AND B.region_id = '530723' 
		    AND SUBSTR(B.start_time, 1, 4) = '2018'
		ORDER BY startTime DESC*/
		
		selectBuffer.append(" SELECT 																							");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																	");
		selectBuffer.append(" B.start_time AS startTime,																		");
		selectBuffer.append(" A.output_value_unit AS outputSum,																	");
		selectBuffer.append(" A.proportion AS proportion 																		");
		selectBuffer.append(" FROM																								");
		selectBuffer.append(" da_industry_fresh A,																				");
		selectBuffer.append(" da_common_field B 																				");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																	");
		selectBuffer.append(" AND A.crop_type_code = 1 																			");
		selectBuffer.append(" AND A.strains_code = 0																			");
		selectBuffer.append(" AND B.data_time_type_code = 1																		");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																");
		selectBuffer.append(" AND B.audit_status_code = 1																		");
		selectBuffer.append(" AND B.region_id =																					");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																	");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC																			");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daIndustryFreshUntBll.getListBySQL(map);
		return resultModel;	
	}

	private ResultModel getProcessIndustryInfo(String time,String regionId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*	SELECT 
		    SUBSTR(B.start_time, 1, 4) TIME,
		    B.start_time AS startTime,
		    A.output_value_unit AS outputSum,
		    A.proportion AS proportion 
		FROM
		    da_industry_process A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.process_strains_code = 0 
		    AND B.data_time_type_code = 1
		    AND B.area_latitude_type_code = 4 
		    AND B.audit_status_code = 0
		    AND B.region_id = '530723' 
		    AND SUBSTR(B.start_time, 1, 4) = '2018'
		ORDER BY startTime DESC*/
		
		selectBuffer.append(" SELECT 																							");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																	");
		selectBuffer.append(" B.start_time AS startTime,																		");
		selectBuffer.append(" A.output_value_unit AS outputSum,																	");
		selectBuffer.append(" A.proportion AS proportion 																		");
		selectBuffer.append(" FROM																								");
		selectBuffer.append(" da_industry_process A,																			");
		selectBuffer.append(" da_common_field B 																				");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																	");
		selectBuffer.append(" AND A.crop_type_code = 1 																			");
		selectBuffer.append(" AND A.process_strains_code = 0																	");
		selectBuffer.append(" AND B.data_time_type_code = 1																		");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																");
		selectBuffer.append(" AND B.audit_status_code = 1																		");
		selectBuffer.append(" AND B.region_id =																					");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																	");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC																			");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daIndustryProcessUntBll.getListBySQL(map);
		
		return resultModel;
	}

	@ApiOperation(value = "查询芒果产值概览", notes = "查询芒果产值概览")
	@RequestMapping(value = "/getMangoIndustrySurvey", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoIndustrySurvey() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String regionId = "";//地区id
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
			}
		}
		
		Map<String, String> timesMap = new HashMap<String, String>();
		timesMap.put("viewName", "year");//视图名，年year，月month，日date，小时hour（默认为年）
		timesMap.put("hasCurrent", "false");
		timesMap.put("pastNum", "2");
		List<String> timesList = TimesView.getTimesView(timesMap);
		Collections.sort(timesList);
		
		String lastYear = timesList.get(1);
		String theYearBeforeLast = timesList.get(0);
		
		//去年的鲜果产值
		ResultModel lastFreshModel = getFreshIndustryInfo(lastYear,HUAPING_REGION_ID);
		//去年的加工品产值
		ResultModel lastProcessModel = getProcessIndustryInfo(lastYear, HUAPING_REGION_ID);
		//前年的鲜果产值
		ResultModel beforeLastFreshModel = getFreshIndustryInfo(theYearBeforeLast,HUAPING_REGION_ID);
		//前年的加工品产值
		ResultModel beforeLastProcessModel = getProcessIndustryInfo(theYearBeforeLast,HUAPING_REGION_ID);

		//去年鲜果和加工品的总产值
		BigDecimal lastOutputSum = new BigDecimal(0);
		//前年鲜果和加工品的总产值
		BigDecimal beforeLastOutputSum = new BigDecimal(0);
		
		//去年的数据
		Map<String,Object> lastFreshMap = getMapFromModel(lastFreshModel);
		if(lastFreshMap!=null) {
			if(lastFreshMap.containsKey("outputSum")) {
				BigDecimal tempFreshSum = (BigDecimal) lastFreshMap.get("outputSum"); 
				lastOutputSum = lastOutputSum.add(tempFreshSum);
			}
			if(lastFreshMap.containsKey("proportion")) {
				BigDecimal proportion = (BigDecimal) lastFreshMap.get("proportion"); 
			}
		}
		Map<String,Object> lastProcessMap = getMapFromModel(lastProcessModel);
		if(lastProcessMap!=null) {
			if(lastProcessMap.containsKey("outputSum")) {
				BigDecimal tempProcessSum = (BigDecimal) lastProcessMap.get("outputSum"); 
				lastOutputSum = lastOutputSum.add(tempProcessSum);
			}
			if(lastProcessMap.containsKey("proportion")) {
				BigDecimal proportion = (BigDecimal) lastProcessMap.get("proportion"); 
			}
		}
		
		//前年的数据
		Map<String,Object> beforeLastFreshMap = getMapFromModel(beforeLastFreshModel);
		if(beforeLastFreshMap!=null) {
			if(beforeLastFreshMap.containsKey("outputSum")) {
				BigDecimal tempFreshSum = (BigDecimal) beforeLastFreshMap.get("outputSum"); 
				beforeLastOutputSum = beforeLastOutputSum.add(tempFreshSum);
			}
			if(beforeLastFreshMap.containsKey("proportion")) {
				BigDecimal proportion = (BigDecimal) beforeLastFreshMap.get("proportion"); 
			}
		}
		Map<String,Object> beforeLastProcessMap = getMapFromModel(beforeLastProcessModel);
		if(beforeLastProcessMap!=null) {
			if(beforeLastProcessMap.containsKey("outputSum")) {
				BigDecimal tempProcessSum = (BigDecimal) beforeLastProcessMap.get("outputSum");
				beforeLastOutputSum = beforeLastOutputSum.add(tempProcessSum);
			}
			if(beforeLastProcessMap.containsKey("proportion")) {
				BigDecimal proportion = (BigDecimal) beforeLastProcessMap.get("proportion"); 
			}
		}
		
		//计算去年比前年增长的百分比
		String outputProportion = "0.00%";
		BigDecimal subtractOutput = lastOutputSum.subtract(beforeLastOutputSum); 
		if(beforeLastOutputSum.compareTo(BigDecimal.ZERO)!=0) {
			outputProportion = MathUtil.getPercent(subtractOutput, beforeLastOutputSum, "0.00%");
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		resultMap.put("outputSum", lastOutputSum);
		resultMap.put("compareLastYear", outputProportion);
		resultMap.put("mangoFruitsProportion", "87");
		resultMap.put("mangoAgricultureProportion", "24");
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	
	@ApiOperation(value = "新增记录(直报)", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaIndustryProcess") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaIndustryProcess jsonData) {
		ResultModel resultModel = new ResultModel();
		ResultModel result = daCommonFieldUntBll.add(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			jsonData.setAreaUnit(UnitUtil.switchUnit(jsonData.getArea(), jsonData.getAreaCode(), "DI_AREA_UNIT"));
			jsonData.setOutputValueUnit(UnitUtil.switchUnit(jsonData.getOutputValue(), jsonData.getOutputValueCode(), "DI_PER_PRICE_UNIT"));
			jsonData.setYieldUnit(UnitUtil.switchUnit(jsonData.getYield(), jsonData.getYieldCode(), "DI_WEIGHT_UNIT"));
			
			resultModel = daIndustryProcessUntBll.add(jsonData);
		}
		return resultModel;
	}


	@ApiOperation(value = "删除记录(直报)", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel resultModel = new ResultModel();
		
		ArrayList<String> delList = new ArrayList<String>();
		delList.add(id);
		ResultModel commonModel = getCommonIds(delList);
		ArrayList<String> idList = new ArrayList<String>();
		if(commonModel.getIsSuccess()){
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) commonModel.getData();
			for (Map<String, Object> map : modelList) {
				idList.add(map.get("id").toString());
			}
			ResultModel result = daCommonFieldUntBll.deleteByIdList(idList);
			if(result.getIsSuccess()){
				resultModel = daIndustryProcessUntBll.delete(id);
			}
		}
		

		return resultModel;
	}


	@ApiOperation(value = "批量删除(直报)", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaIndustryProcessDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaIndustryProcessParameter.DeleteByIdList jsonData) {
		ResultModel resultModel = new ResultModel();
		
		ResultModel commonModel = getCommonIds(jsonData.getIdList());
		ArrayList<String> idList = new ArrayList<String>();
		if(commonModel.getIsSuccess()){
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) commonModel.getData();
			for (Map<String, Object> map : modelList) {
				idList.add(map.get("id").toString());
			}
			ResultModel result = daCommonFieldUntBll.deleteByIdList(idList);
			if(result.getIsSuccess()){
				resultModel = daIndustryProcessUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "修改记录(直报)", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaIndustryProcess") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaIndustryProcess jsonData) {
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setAreaUnit(UnitUtil.switchUnit(jsonData.getArea(), jsonData.getAreaCode(), "DI_AREA_UNIT"));
			jsonData.setOutputValueUnit(UnitUtil.switchUnit(jsonData.getOutputValue(), jsonData.getOutputValueCode(), "DI_PER_PRICE_UNIT"));
			jsonData.setYieldUnit(UnitUtil.switchUnit(jsonData.getYield(), jsonData.getYieldCode(), "DI_WEIGHT_UNIT"));
			resultModel = daIndustryProcessUntBll.update(jsonData);
		}

		return resultModel;
	}
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaIndustryProcessUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaIndustryProcessParameter.UpdateList jsonData) {
		ResultModel resultModel = new ResultModel();
		DaCommonField paramDaCommonField = jsonData.getEntity().getDaCommonField();
		
		ResultModel commonModel = getCommonIds(jsonData.getIdList());
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) commonModel.getData();
		for (Map<String, Object> map : modelList) {
			DaCommonField daCommonField = (DaCommonField)BeanUtil.map2Bean(map, DaCommonField.class);
			daCommonField.setAuditerUserId(this.getCurrentUser().getId());
			daCommonField.setAuditerTime(DateUtils.getCurrentTime());
			daCommonField.setAuditerSuggestion(paramDaCommonField.getAuditerSuggestion());
			daCommonField.setAuditStatusCode(paramDaCommonField.getAuditStatusCode());
			daCommonField.setAuditStatusText(paramDaCommonField.getAuditStatusText());
			daCommonFieldUntBll.update(daCommonField);
		}
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	@ApiOperation(value = "单条查询(直报)", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daIndustryProcessUntBll.getModel(id);
		
		if(result.getIsSuccess()){
			DaIndustryProcess jsonData = (DaIndustryProcess) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			result.setData(jsonData);
		}
		return result;
	}

    
	@ApiOperation(value = "模糊查询(直报)", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select  ");
		selectBuffer.append(" A.name name,  ");
		selectBuffer.append(" A.output_value_unit outputValueUnit,  ");
		selectBuffer.append(" A.id id,  ");
		selectBuffer.append(" B.id commonId,  ");
		selectBuffer.append(" B.add_time addTime,  ");
		selectBuffer.append(" B.add_user_id addUserId,  ");
		selectBuffer.append(" B.area_latitude_type_code areaLatitudeTypeCode,  ");
		selectBuffer.append(" B.area_latitude_type_text areaLatitudeTypeText,  ");
		selectBuffer.append(" B.auditer_suggestion auditerSuggestion,  ");
		selectBuffer.append(" B.auditer_time auditerTime,  ");
		selectBuffer.append(" B.auditer_user_id auditerUserId,  ");
		selectBuffer.append(" B.audit_status_code auditStatusCode,  ");
		selectBuffer.append(" B.audit_status_text auditStatusText,  ");
		selectBuffer.append(" B.data_sources dataSources,  ");
		selectBuffer.append(" B.data_time_type_code dataTimeTypeCode,  ");
		selectBuffer.append(" B.data_time_type_text dataTimeTypeText,  ");
		selectBuffer.append(" B.end_time endTime,  ");
		selectBuffer.append(" B.region_id regionId,  ");
		selectBuffer.append(" B.region_name regionName,  ");
		selectBuffer.append(" B.source_channel_type_code sourceChannelTypeCode,  ");
		selectBuffer.append(" B.source_channel_type_text sourceChannelTypeText,  ");
		selectBuffer.append(" B.start_time startTime  ");
		selectBuffer.append(" FROM  ");
		selectBuffer.append(" da_industry_process A  ");
		selectBuffer.append(" INNER JOIN da_common_field B ON A.common_field_id = B.id  ");
		selectBuffer.append(" WHERE  ");
		selectBuffer.append(" 1 = 1  ");
		
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and A.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("dataTimeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("dataTimeTypeCode")))
					selectBuffer.append(" and B.data_time_type_code = '"+entityRelatedObject.getString("dataTimeTypeCode")+"' ");
				if (entityRelatedObject.containsKey("sourceChannelTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("sourceChannelTypeCode")))
					selectBuffer.append(" and B.source_channel_type_code = '"+entityRelatedObject.getString("sourceChannelTypeCode")+"' ");
				if (entityRelatedObject.containsKey("dataSources") && StringUtils.isNotBlank(entityRelatedObject.getString("dataSources")))
					selectBuffer.append(" and B.data_sources like '%").append(entityRelatedObject.getString("dataSources")).append("%'");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					selectBuffer.append(" and B.region_id = '"+entityRelatedObject.getString("regionId")+"' ");
				if (entityRelatedObject.containsKey("startDate") && StringUtils.isNotBlank(entityRelatedObject.getString("startDate")))
					selectBuffer.append(" and B.add_time >= '"+entityRelatedObject.getString("startDate")+"' ");
				if (entityRelatedObject.containsKey("endDate") && StringUtils.isNotBlank(entityRelatedObject.getString("endDate")))
					selectBuffer.append(" and B.add_time <= '"+entityRelatedObject.getString("endDate")+"' ");
				if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
					selectBuffer.append(" and B.start_time >= '"+entityRelatedObject.getString("startTime")+"' ");
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
					selectBuffer.append(" and B.start_time <= '"+entityRelatedObject.getString("endTime")+"' ");
				if (entityRelatedObject.containsKey("regionCode") && StringUtils.isNotBlank(entityRelatedObject.getString("regionCode")))
					selectBuffer.append(" and B.region_code = '"+entityRelatedObject.getString("regionCode")+"' ");
				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
			}
			
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
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = daIndustryProcessUntBll.getListBySQL(map);

		return resultModel;
	}
	/**
	 * 获取对应的da_common_field列表
	 * @param objIds
	 * @return
	 */
	private ResultModel getCommonIds(ArrayList<String> objIds){
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                                  ");
		selectBuffer.append("		A.id id,                                                                            ");
		selectBuffer.append("		A.data_time_type_code dataTimeTypeCode,                                             ");
		selectBuffer.append("		A.data_time_type_text dataTimeTypeText,                                             ");
		selectBuffer.append("		A.start_time startTime,                                                             ");
		selectBuffer.append("		A.end_time endTime,                                                                 ");
		selectBuffer.append("		A.area_latitude_type_code areaLatitudeTypeCode,                                     ");
		selectBuffer.append("		A.area_latitude_type_text areaLatitudeTypeText,                                     ");
		selectBuffer.append("		A.region_id regionId,                                                               ");
		selectBuffer.append("		A.region_name regionName,                                                           ");
		selectBuffer.append("		A.source_channel_type_code sourceChannelTypeCode,                                   ");
		selectBuffer.append("		A.source_channel_type_text sourceChannelTypeText,                                   ");
		selectBuffer.append("		A.data_sources dataSources,                                                         ");
		selectBuffer.append("		A.audit_status_code auditStatusCode,                                                ");
		selectBuffer.append("		A.audit_status_text auditStatusText,                                                ");
		selectBuffer.append("		A.auditer_suggestion auditerSuggestion,                                             ");
		selectBuffer.append("		A.auditer_user_id auditerUserId,                                                    ");
		selectBuffer.append("		A.auditer_time auditerTime,                                                         ");
		selectBuffer.append("		A.add_user_id addUserId,                                                            ");
		selectBuffer.append("		A.add_time addTime,                                                                 ");
		selectBuffer.append("		A.update_time updateTime,                                                           ");
		selectBuffer.append("		A.remark remark                                                                     ");
		selectBuffer.append("	FROM                                                                                    ");
		selectBuffer.append("		da_common_field A                                                                   ");
		selectBuffer.append("	INNER JOIN da_industry_process B ON A.id = B.common_field_id                               ");
		selectBuffer.append("	WHERE                                                                                   ");
		selectBuffer.append("		B.id IN ("+Tools.list2SqlStr(objIds)+")  								");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = daCommonFieldUntBll.getListBySQL(map);
		
		return resultModel;
	}
	/**
	 * 获取字段放入map
	 * @return
	 */
	public Map getDictionary(){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("  SELECT CONCAT(type_id,text) as name ,code  from gp_dictionary  ");
		selectBuffer.append(" GROUP BY name ");
		
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gpDictionaryUntBll.getListBySQL(map);
		List<Map<String, Object>> list = CastObjectUtil.cast(resultModel.getData());
		Map<String,Object> maps = new HashMap<String,Object>();
		for(Map<String, Object> lists:list){
			maps.put(lists.get("name").toString(), lists.get("code"));
		}
		return maps;
		
	}
	
	/**
	 * 导入excel
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量导入（直报数据）", notes = "批量导入多条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaAltitudeInfoAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaIndustryProcess> list1 = (List<DaIndustryProcess>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaIndustryProcess.class);
           List<DaCommonFieldGenEnt> list2 = (List<DaCommonFieldGenEnt>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaCommonFieldGenEnt.class);
           
           
		Map map = getDictionary();
		for (int i = 0 ; i < list2.size(); i++) {
		
				DaCommonField  daCommonField  = new DaCommonField();
				daCommonField.setAddUserId(this.getCurrentUser().getId());//创建人
				daCommonField.setAddTime(new Date());//创建时间
				if(map.containsKey(DictionaryEnum.AREA_LATITUDE_TYPE_TEXT.getText()+list2.get(i).getAreaLatitudeTypeText())){
					  String status = map.get(DictionaryEnum.AREA_LATITUDE_TYPE_TEXT.getText()+list2.get(i).getAreaLatitudeTypeText()).toString();
						daCommonField.setAreaLatitudeTypeCode(Byte.valueOf(status));//区域维度
					}
				daCommonField.setAreaLatitudeTypeText(list2.get(i).getAreaLatitudeTypeText());//区域维度：文本
				
				daCommonField.setAuditStatusText(list2.get(i).getAuditStatusText());//审核状态文本：文本
				if(map.containsKey(DictionaryEnum.AUDIT_STATUS_TEXT.getText()+list2.get(i).getAuditStatusText())){
				  String status = map.get(DictionaryEnum.AUDIT_STATUS_TEXT.getText()+list2.get(i).getAuditStatusText()).toString();
					daCommonField.setAuditStatusCode(Byte.valueOf(status));//审核状态
				}
				daCommonField.setDataSources(list2.get(i).getDataSources());//数据来源
				if(map.containsKey(DictionaryEnum.DATA_TIME_TYPE_TEXT.getText()+list2.get(i).getDataTimeTypeText())){
					  String status = map.get(DictionaryEnum.DATA_TIME_TYPE_TEXT.getText()+list2.get(i).getDataTimeTypeText()).toString();
						daCommonField.setDataTimeTypeCode(Byte.valueOf(status));//数据采集维度
					}
				daCommonField.setDataTimeTypeText(list2.get(i).getDataTimeTypeText());//数据采集维度：文本
				//daCommonField.setRegionId(ReadExcelUtil.getCellValue(currentRow.getCell(8), true));//地理区域
				daCommonField.setRegionName(list2.get(i).getRegionName());//地理区域名称
				daCommonField.setRemark(list2.get(i).getRemark());//备注
				if(map.containsKey(DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText()+list2.get(i).getSourceChannelTypeText())){
					  String status = map.get(DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText()+list2.get(i).getSourceChannelTypeText()).toString();
						daCommonField.setSourceChannelTypeCode(Byte.valueOf(status));//来源渠道
					}
				daCommonField.setSourceChannelTypeText(list2.get(i).getSourceChannelTypeText());//来源渠道：文本
				daCommonField.setStartTime(list2.get(i).getStartTime());//开始时间
				daCommonField.setEndTime(list2.get(i).getEndTime());//结束时间
				ResultModel result = daCommonFieldUntBll.add(daCommonField);
				if(result.getIsSuccess()){
						list1.get(i).setCommonFieldId(result.getObjectId());
						if(map.containsKey(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText())){
							  String status = map.get(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText()).toString();
								list1.get(i).setCropTypeCode(Byte.valueOf(status));//作物种类
							}
						if(map.containsKey(DictionaryEnum.DI_PROCESS_BREED.getText()+list1.get(i).getProcessStrainsText())){
							  String status = map.get(DictionaryEnum.DI_PROCESS_BREED.getText()+list1.get(i).getProcessStrainsText()).toString();
								list1.get(i).setProcessStrainsCode(Byte.valueOf(status));//加工品品种
							}
						if(map.containsKey(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText())){
							  String status = map.get(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText()).toString();
								list1.get(i).setCropTypeCode(Byte.valueOf(status));//面积
							}
						if(map.containsKey(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getOutputValueText())){
							  String status = map.get(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getOutputValueText()).toString();
								list1.get(i).setOutputValueCode(Byte.valueOf(status));//产值
							}
						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getYieldText())){
							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getYieldText()).toString();
								list1.get(i).setYieldCode(Byte.valueOf(status));//产量
							}
						resultModel = daIndustryProcessUntBll.add(list1.get(i));
						
					
				}
		}
		return resultModel;
	}
}



