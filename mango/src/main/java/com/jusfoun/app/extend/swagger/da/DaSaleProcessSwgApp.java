package com.zee.app.extend.swagger.da;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.zee.app.generate.swagger.da.DaSaleProcessGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.da.DaSaleFreshUntBll;
import com.zee.bll.extend.unity.da.DaSaleProcessUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaSaleProcess;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaSaleProcessParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.TimesView;
import com.zee.utl.Tools;
import com.zee.utl.UnitUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:46
 * @description  对外接口，扩展自DaSaleProcessGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daSaleProcess")
public class  DaSaleProcessSwgApp extends  DaSaleProcessGenSwgApp {

	private static final String SALE_PROCESS = "1";
	private static final String WASTAGE = "2";

	@Autowired
	@Qualifier("daSaleProcessUntBll")
	protected DaSaleProcessUntBll daSaleProcessUntBll;
	
	@Autowired
	@Qualifier("daSaleFreshUntBll")
	protected DaSaleFreshUntBll daSaleFreshUntBll;
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	
//	@ApiOperation(value = "查询", notes = "查询加工品销量、同比、环比;鲜果收益、同比、环比")
//	@RequestMapping(value = "/getProcessData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultModel getProcessData() {
//		ResultModel resultModel = new ResultModel();
//
//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//
//		//加工品销量、同比、环比;加工品收益、同比、环比;
//		StringBuffer selectBuffer = new StringBuffer();
//		selectBuffer.append(" select T1.sale_amount_unit/1000 sale_amount,(T1.sale_amount_unit-T2.sale_amount_unit)/T2.sale_amount_unit*100 amount_tb,(T1.sale_amount_unit-T3.sale_amount_unit)/T3.sale_amount_unit*100 amount_hb,");
//		selectBuffer.append(" T1.sale_volume_unit/1000 sale_volume,(T1.sale_volume_unit-T2.sale_volume_unit)/T2.sale_volume_unit*100 volume_tb,(T1.sale_volume_unit-T3.sale_volume_unit)/T3.sale_volume_unit*100 volume_hb FROM ");
//		selectBuffer.append(" (select A.* from da_sale_process A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '3' and B.area_latitude_type_code = 4 ");
//		selectBuffer.append(" and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723' and B.start_time = (select max(B.start_time) from da_sale_process A,da_common_field B ");
//		selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '3' and B.area_latitude_type_code = 4 and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723') order by B.add_time limit 1) T1,");
//		selectBuffer.append(" (select A.* from da_sale_process A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '3' and B.area_latitude_type_code = 4 ");
//		selectBuffer.append(" and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723' and B.start_time = (select max(DATE_ADD(B.start_time,INTERVAL -1 MONTH)) hb_time from da_sale_process A,da_common_field B ");
//		selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '3' and B.area_latitude_type_code = 4 and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723') order by B.add_time limit 1)T2,");
//		selectBuffer.append(" (select A.* from da_sale_process A,da_common_field B where A.common_field_id = B.id and B.area_latitude_type_code = 4 ");
//		selectBuffer.append(" and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723'and B.start_time = (select max(DATE_ADD(B.start_time,INTERVAL -1 YEAR)) tb_time from da_sale_process A,da_common_field B ");
//		selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '3' and B.area_latitude_type_code = 4 and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723') order by B.add_time limit 1)T3 ");
//
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("Sql", selectBuffer.toString());
//
//		resultModel = daSaleProcessUntBll.getListBySQL(map);
//
//		return resultModel;
//	}

	@ApiOperation(value = "查询", notes = "查询月度，加工品，销售量/销售金额 排名-华坪到各地")
	@RequestMapping(value = "/getProcessRank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFruitRank() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = "";//查询类型 1：销售量，2：销售额。
		String queryTime = "";//查询日期  2018-05
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
				queryType = entityRelatedObject.getString("queryType");
			if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
				queryTime = entityRelatedObject.getString("queryTime");
		}
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
				

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryType.equals("1")){
			//月度，加工品，销售量 排名-华坪到各地
				selectBuffer.append(" SELECT A.sale_region_code,A.sale_region_text,sum(A.sale_amount_unit) sale_amount_unit ");
				selectBuffer.append(" FROM da_sale_process A,da_common_field B WHERE A.common_field_id = B.id AND B.data_time_type_code = '3' ");
				selectBuffer.append(" AND B.area_latitude_type_code = 4 and A.crop_type_code = '1' AND A.process_strains_code = '0' AND B.region_id = '530723' AND substr(B.start_time, 1, 7) = '"+queryTime+"' ");
				selectBuffer.append(" group by A.sale_region_code ORDER BY A.sale_amount_unit desc ");
		 }else if(queryType.equals("2")){
			//月度，加工品，销售金额 排名-华坪到各地
				selectBuffer.append(" SELECT DISTINCT A.sale_region_code,A.sale_region_text,sum(A.sale_volume_unit) sale_amount_unit ");
				selectBuffer.append(" FROM da_sale_process A,da_common_field B WHERE A.common_field_id = B.id AND B.data_time_type_code = '3' ");
				selectBuffer.append(" AND B.area_latitude_type_code = 4 and A.crop_type_code = '1' AND A.process_strains_code = '0' AND B.region_id = '530723' AND substr(B.start_time, 1, 7) = '"+queryTime+"' ");
				selectBuffer.append(" group by A.sale_region_code ORDER BY A.sale_volume_unit desc ");
		 }
		
		
		map.put("Sql", selectBuffer.toString());

		resultModel = daSaleProcessUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询月度，加工品，销售量/销售金额 流向")
	@RequestMapping(value = "/getProcessFlow", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFruitFlow() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = "";//查询类型 1：销售量，2：销售额。
		String queryTime = "";//查询日期  2018-05
		
		 if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);

				if (jsonObject.containsKey("entityRelated")) {
					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
					if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
						queryType = entityRelatedObject.getString("queryType");
					if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
						queryTime = entityRelatedObject.getString("queryTime");
				}
				
			}

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryType.equals("1")){
			//月度，加工品，销售量 流向
				selectBuffer.append(" select distinct '530723' oregion_code,'华坪' oregion_text,A.sale_region_code,A.sale_region_text, ");
				selectBuffer.append(" A.sale_amount_unit unit from da_sale_process A,da_common_field B ");
				selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '3' and B.area_latitude_type_code = 4 ");
				selectBuffer.append(" and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723' and substr(B.start_time,1,7) = '"+queryTime+"' order by A.sale_amount desc ");
		 }else if(queryType.equals("2")){
			//月度，加工品，销售金额 流向
				selectBuffer.append(" select distinct '530723' oregion_code,'华坪' oregion_text,A.sale_region_code,A.sale_region_text, ");
				selectBuffer.append(" A.sale_volume_unit unit from da_sale_process A,da_common_field B ");
				selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '3' and B.area_latitude_type_code = 4 ");
				selectBuffer.append(" and A.crop_type_code = '1' AND A.process_strains_code = '0' and B.region_id = '530723' and substr(B.start_time,1,7) = '"+queryTime+"' order by A.sale_volume desc ");
		 }
		
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());

		resultModel = daSaleProcessUntBll.getListBySQL(map);
		maps.put("data", (List<Map<String, Object>>)resultModel.getData());
		
		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> dataMap;
		for (Map<String, Object> map1 : maps.get("data") ) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("oregion_code",map1.get("oregion_code").toString());
			dataMap.put("oregion_text",map1.get("oregion_text").toString());
			dataMap.put("sale_region_code",map1.get("sale_region_code").toString());
			String area = map1.get("sale_region_text").toString();
			//省名处理
			String areaName;
            if(area.equals("黑龙江省")){
            	areaName = "黑龙江";
            }else {
            	areaName = area.substring(0,2);
            }
            dataMap.put("sale_region_text",areaName);
            dataMap.put("unit",map1.get("unit"));
			dataList.add(dataMap);
		}
    	
        resultModel.setData(dataList);

		return resultModel;
	}
	
//	@ApiOperation(value = "查询", notes = "查询年度，加工品，消费量/消费金额 预测")
//	@RequestMapping(value = "/getProcessPredict", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultModel getFruitPredict() {
//		ResultModel resultModel = new ResultModel();
//
//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//
//		String queryType = "";//查询类型 1：消费量，2：消费金额。
//		
//		 if (!StringUtils.isBlank(jsonData)) {
//				JSONObject jsonObject = JSONObject.fromObject(jsonData);
//
//				if (jsonObject.containsKey("entityRelated")) {
//					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
//					if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
//						queryType = entityRelatedObject.getString("queryType");
//				}
//				
//			}
//
//			StringBuffer selectBuffer = new StringBuffer();
//		 if(queryType.equals("1")){
//			//年度，加工品，消费量 预测
//			 selectBuffer.append(" select t0.year,case when t1.val1 is null then '-' else t1.val1 end sale_amount_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time1,sale_amount_actual val1 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '2' and sale_amount_forecast_flag = '0') t1 on t0.year = t1.time1 ");
//				selectBuffer.append(" UNION select t0.year,case when t2.val2 is null then '-' else t2.val2 end sale_amount_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year = (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time2,sale_amount_forecast val2 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '2' and sale_amount_forecast_flag = '1') t2 on t0.year = t2.time2 ");
//		 }else if(queryType.equals("2")){
//			//年度，加工品，消费金额 预测
//			 	selectBuffer.append(" select t0.year,case when t1.val1 is null then '-' else t1.val1 end sale_volume_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time1,sale_volume_actual val1 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '2' and sale_volume_forecast_flag = '0') t1 on t0.year = t1.time1 ");
//				selectBuffer.append(" UNION select t0.year,case when t2.val2 is null then '-' else t2.val2 end sale_volume_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year = (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time2,sale_volume_forecast val2 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '2' and sale_volume_forecast_flag = '1') t2 on t0.year = t2.time2 ");
//		 }
//		
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("Sql", selectBuffer.toString());
//
//		resultModel = daSaleProcessUntBll.getListBySQL(map);
//
//
//		return resultModel;
//	}
	
	@ApiOperation(value = "查询", notes = "查询加工品产值")
	@RequestMapping(value = "/getProcessOutput", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getProcessOutput() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		//加工品产值;
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select t0.year,case when t1.output_value_unit is null then '-' else t1.output_value_unit end output_value_unit ");
		selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 ");
		selectBuffer.append(" left join (select substr(t.start_time,1,4) time1,t.output_value_unit from (select B.start_time,A.output_value_unit from da_industry_process A,da_common_field B ");
		selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '1' and B.area_latitude_type_code = 4 ");
		selectBuffer.append(" and A.crop_type_code = '1' and A.process_strains_code = '0' and B.region_id = '530723' order by B.add_time desc) t group by substr(t.start_time,1,4)) t1 on t0.year = t1.time1 ");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());

		resultModel = daSaleProcessUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询芒果消费结构", notes = "查询芒果消费结构-鲜果销量、加工品销量和损耗量")
	@RequestMapping(value = "/getMangoConsumptionStructure", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoConsumptionStructure() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
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
		
		List<Map<String, Object>> freshList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> processList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> wastageList = new ArrayList<Map<String,Object>>();
				
		//获取时间列表
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "year");//视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "5");
		List<String> timesList = TimesView.getTimesView(map);
				
		Map<String, Object> perFreshMap = null;
		for(String time : timesList) {
			//鲜果销量数据
			ResultModel perFreshModel =  getSaleFreshInfo(regionId,time);
			Object freshObj = perFreshModel.getData();
			perFreshMap = new HashMap<String, Object>();
			if(freshObj!=null) {
				List<Map<String, Object>> tempFreshList = (List<Map<String, Object>>) freshObj;
				if(tempFreshList.size()>=1) {
					perFreshMap = tempFreshList.get(0);
				}else {
					perFreshMap.put("time", time);
					perFreshMap.put("saleSum", "--");
				}
				freshList.add(perFreshMap);
			}
		}
		Map<String, Object> perProcessMap = null;
		for(String time : timesList) {
			//加工品销量数据
			ResultModel perProcessModel =  getProcessAndWastageInfo(regionId,time,SALE_PROCESS);
			Object processObj = perProcessModel.getData();
			perProcessMap = new HashMap<String, Object>();
			if(processObj!=null) {
				List<Map<String, Object>> tempProcessList = (List<Map<String, Object>>) processObj;
				if(tempProcessList.size()>=1) {
					perProcessMap = tempProcessList.get(0);
				}else {
					perProcessMap.put("time", time);
					perProcessMap.put("saleSum", "--");
				}
				processList.add(perProcessMap);
			}
		}
		Map<String, Object> perWastageMap = null;
		for(String time : timesList) {
			//损耗量数据
			ResultModel perWastageModel =  getProcessAndWastageInfo(regionId,time,WASTAGE);
			Object wastageObj = perWastageModel.getData();
			perWastageMap = new HashMap<String, Object>();
			if(wastageObj!=null) {
				List<Map<String, Object>> tempWastageList = (List<Map<String, Object>>) wastageObj;
				if(tempWastageList.size()>=1) {
					perWastageMap = tempWastageList.get(0);
				}else {
					perWastageMap.put("time", time);
					perWastageMap.put("wasteSum", "--");
				}
				wastageList.add(perWastageMap);
			}
		}
		
		Map<String, Object> freshResultMap = new HashMap<String, Object>();
		Map<String, Object> processResultMap = new HashMap<String, Object>();
		Map<String, Object> wastageResultMap = new HashMap<String, Object>();
		
		freshResultMap.put("freshList", freshList);
		processResultMap.put("processList", processList);
		wastageResultMap.put("wastageList", wastageList);
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList.add(freshResultMap);
		resultList.add(processResultMap);
		resultList.add(wastageResultMap);
		
		//造假数据begin
		/*resultList = new ArrayList<Map<String, Object>>();
		
		freshResultMap = new HashMap<String, Object>();
		processResultMap = new HashMap<String, Object>();
		wastageResultMap = new HashMap<String, Object>();
		
		freshList = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> perFreshMap_1 = new HashMap<String, Object>();
		perFreshMap_1.put("YEAR", "2013");
		perFreshMap_1.put("saleSum", "75000000");
		Map<String,Object> perFreshMap_2 = new HashMap<String, Object>();
		perFreshMap_2.put("YEAR", "2014");
		perFreshMap_2.put("saleSum", "73000000");
		Map<String,Object> perFreshMap_3 = new HashMap<String, Object>();
		perFreshMap_3.put("YEAR", "2015");
		perFreshMap_3.put("saleSum", "83000000");
		Map<String,Object> perFreshMap_4 = new HashMap<String, Object>();
		perFreshMap_4.put("YEAR", "2016");
		perFreshMap_4.put("saleSum", "87000000");
		Map<String,Object> perFreshMap_5 = new HashMap<String, Object>();
		perFreshMap_5.put("YEAR", "2017");
		perFreshMap_5.put("saleSum", "81000000");
		
		freshList.add(perFreshMap_1);
		freshList.add(perFreshMap_2);
		freshList.add(perFreshMap_3);
		freshList.add(perFreshMap_4);
		freshList.add(perFreshMap_5);
		
		processList = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> perProcesMap_1 = new HashMap<String, Object>();
		perProcesMap_1.put("YEAR", "2013");
		perProcesMap_1.put("saleSum", "21000000");
		Map<String,Object> perProcesMap_2 = new HashMap<String, Object>();
		perProcesMap_2.put("YEAR", "2014");
		perProcesMap_2.put("saleSum", "24000000");
		Map<String,Object> perProcesMap_3 = new HashMap<String, Object>();
		perProcesMap_3.put("YEAR", "2015");
		perProcesMap_3.put("saleSum", "26000000");
		Map<String,Object> perProcesMap_4 = new HashMap<String, Object>();
		perProcesMap_4.put("YEAR", "2016");
		perProcesMap_4.put("saleSum", "19000000");
		Map<String,Object> perProcesMap_5 = new HashMap<String, Object>();
		perProcesMap_5.put("YEAR", "2017");
		perProcesMap_5.put("saleSum", "21100000");
		
		processList.add(perProcesMap_1);
		processList.add(perProcesMap_2);
		processList.add(perProcesMap_3);
		processList.add(perProcesMap_4);
		processList.add(perProcesMap_5);
		
		wastageList = new ArrayList<Map<String, Object>>();
		
		Map<String,Object> perWastageMap_1 = new HashMap<String, Object>();
		perWastageMap_1.put("YEAR", "2013");
		perWastageMap_1.put("wasteSum", "1300000");
		Map<String,Object> perWastageMap_2 = new HashMap<String, Object>();
		perWastageMap_2.put("YEAR", "2014");
		perWastageMap_2.put("wasteSum", "1100000");
		Map<String,Object> perWastageMap_3 = new HashMap<String, Object>();
		perWastageMap_3.put("YEAR", "2015");
		perWastageMap_3.put("wasteSum", "1200000");
		Map<String,Object> perWastageMap_4 = new HashMap<String, Object>();
		perWastageMap_4.put("YEAR", "2016");
		perWastageMap_4.put("wasteSum", "900000");
		Map<String,Object> perWastageMap_5 = new HashMap<String, Object>();
		perWastageMap_5.put("YEAR", "2017");
		perWastageMap_5.put("wasteSum", "1240000");
		
		wastageList.add(perWastageMap_1);
		wastageList.add(perWastageMap_2);
		wastageList.add(perWastageMap_3);
		wastageList.add(perWastageMap_4);
		wastageList.add(perWastageMap_5);
		
		freshResultMap.put("freshList", freshList);
		processResultMap.put("processList", processList);
		wastageResultMap.put("wastageList", wastageList);
		
		resultList.add(freshResultMap);
		resultList.add(processResultMap);
		resultList.add(wastageResultMap);*/
		//造假数据end
		
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	private List<Map<String, Object>> getListFromModel(ResultModel resultModel) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> objList = null;
		if (obj != null)
			objList = (List<Map<String, Object>>) obj;
		return objList;
	}
	
	private ResultModel getSaleFreshInfo(String regionId,String time) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		t0.TIME,
		CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum
		FROM
		(SELECT 
		SUBSTR(B.start_time,1,4) TIME,
			B.start_time AS startTime,
			A.sale_amount_unit AS saleSum
			FROM da_sale_fresh A,
			da_common_field B 
			WHERE A.common_field_id = B.id 
				AND A.crop_type_code = 1
				AND A.strains_code = 0
				AND B.data_time_type_code = 1
				AND B.area_latitude_type_code = 4 
				AND B.audit_status_code = 1
				AND B.region_id = '530723'
				AND SUBSTR(B.start_time,1,4) = '2018'
				ORDER BY startTime DESC) t0
				GROUP BY TIME */
		
		selectBuffer.append(" SELECT 																									");
		selectBuffer.append(" t0.time,																									");
		selectBuffer.append(" CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum										");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" (SELECT 																									");
		selectBuffer.append(" SUBSTR(B.start_time,1,4) time,																			");
		selectBuffer.append(" B.start_time AS startTime,																				");
		selectBuffer.append(" A.sale_amount_unit AS saleSum																				");
		selectBuffer.append(" FROM da_sale_fresh A,da_common_field B 																	");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1																					");
		selectBuffer.append(" AND A.strains_code = 0																					");
		selectBuffer.append(" AND B.data_time_type_code = 1																				");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																		");
		selectBuffer.append(" AND B.audit_status_code = 1																				");
		selectBuffer.append(" AND B.region_id =																							");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time,1,4) =																			");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("' and sale_region_code = '0' ");
		selectBuffer.append(" ORDER BY startTime DESC) t0 GROUP BY time																	");
	
		map.put("Sql", selectBuffer.toString());
		resultModel = daSaleFreshUntBll.getListBySQL(map);
		return resultModel;	
	}

	private ResultModel getProcessAndWastageInfo(String regionId,String time,String tag) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		t0.TIME,
		CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum
		FROM
		(SELECT 
		SUBSTR(B.start_time,1,4) TIME,
			B.start_time AS startTime,
			A.sale_amount_unit AS saleSum
			FROM da_sale_process A,
			da_common_field B 
			WHERE A.common_field_id = B.id 
				AND A.crop_type_code = 1
				AND A.process_strains_code = 1
				AND B.data_time_type_code = 1
				AND B.area_latitude_type_code = 4 
				AND B.audit_status_code = 0
				AND B.region_id = '530723'
				AND SUBSTR(B.start_time,1,4) = '2018'
				ORDER BY startTime DESC) t0
				GROUP BY TIME*/
		
		selectBuffer.append(" SELECT 																									");
		selectBuffer.append(" t0.time,																									");
		if(SALE_PROCESS.equals(tag)) {
			selectBuffer.append(" CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum									");
		}
		if(WASTAGE.equals(tag)) {
			selectBuffer.append(" CASE WHEN t0.wasteSum IS NULL THEN 0 ELSE t0.wasteSum END AS wasteSum									");
		}
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" (SELECT 																									");
		selectBuffer.append(" SUBSTR(B.start_time,1,4) time,																			");
		selectBuffer.append(" B.start_time AS startTime,																				");
		selectBuffer.append(" A.sale_amount_unit AS saleSum,																				");
		selectBuffer.append(" A.waste_amount_unit AS wasteSum																			");
		selectBuffer.append(" FROM da_sale_process A,da_common_field B 																	");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1																					");
		selectBuffer.append(" AND A.process_strains_code = 0																			");
		selectBuffer.append(" AND B.data_time_type_code = 1																				");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																		");
		selectBuffer.append(" AND B.audit_status_code = 1																				");
		selectBuffer.append(" AND B.region_id =																							");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time,1,4) =																			");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("' and sale_region_code = '0' ");
		selectBuffer.append(" ORDER BY startTime DESC) t0 GROUP BY time																	");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daSaleProcessUntBll.getListBySQL(map);
		
		return resultModel;
	}
	@ApiOperation(value = "新增记录（直报数据）", notes = "新增单条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaSaleProcess") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaSaleProcess jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.add(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			jsonData.setSaleAmountUnit(UnitUtil.switchUnit(jsonData.getSaleAmount(), jsonData.getSaleAmountUnitCode(), "DI_WEIGHT_UNIT"));
			jsonData.setSaleVolumeUnit(UnitUtil.switchUnit(jsonData.getSaleVolume(), jsonData.getSaleVolumeUnitCode(), "DI_PER_PRICE_UNIT"));
			jsonData.setWasteAmountUnit(UnitUtil.switchUnit(jsonData.getWasteAmount(), jsonData.getWasteAmountUnitCode(), "DI_WEIGHT_UNIT"));
			resultModel = daSaleProcessUntBll.add(jsonData);
		}
		
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "删除记录（直报数据）", notes = "根据主键删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "query", name = "id", value = "主键ID", required = true, dataType = "String")
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
				resultModel = daSaleProcessUntBll.delete(id);
			}
		}

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量删除（直报数据）", notes = "根据主键列表批量删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaSaleProcessDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaSaleProcessParameter.DeleteByIdList jsonData) {
		
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
				resultModel = daSaleProcessUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}
	@ApiOperation(value = "单条查询（直报）", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daSaleProcessUntBll.getModel(id);
		if(result.getIsSuccess()){
			DaSaleProcess jsonData = (DaSaleProcess) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			result.setData(jsonData);
		}
		return result;
	}
	@ApiOperation(value = "修改记录（直报数据）", notes = "修改指定记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaSaleProcess") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaSaleProcess jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setSaleAmountUnit(UnitUtil.switchUnit(jsonData.getSaleAmount(), jsonData.getSaleAmountUnitCode(), "DI_WEIGHT_UNIT"));
			jsonData.setSaleVolumeUnit(UnitUtil.switchUnit(jsonData.getSaleVolume(), jsonData.getSaleVolumeUnitCode(), "DI_PER_PRICE_UNIT"));
			jsonData.setWasteAmountUnit(UnitUtil.switchUnit(jsonData.getWasteAmount(), jsonData.getWasteAmountUnitCode(), "DI_WEIGHT_UNIT"));
			resultModel = daSaleProcessUntBll.update(jsonData);
		}
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaSaleProcessUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaSaleProcessParameter.UpdateList jsonData) {
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
		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
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
		selectBuffer.append("	INNER JOIN da_sale_process B ON A.id = B.common_field_id                               ");
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
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
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
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaSaleProcessAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaSaleProcess> list1 = (List<DaSaleProcess>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaSaleProcess.class);
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
						if(map.containsKey(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getProcessStrainsText())){
							  String status = map.get(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getProcessStrainsText()).toString();
								list1.get(i).setProcessStrainsCode(Byte.valueOf(status));//加工品种
							}
						if(map.containsKey(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText())){
							  String status = map.get(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText()).toString();
								list1.get(i).setCropTypeCode(Byte.valueOf(status));//作物种类
							}
						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getSaleAmountUnitText())){
							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getSaleAmountUnitText()).toString();
								list1.get(i).setSaleAmountUnitCode(Byte.valueOf(status));//销售量
							}
						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getWasteAmountUnitText())){
							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getWasteAmountUnitText()).toString();
								list1.get(i).setWasteAmountUnitCode(Byte.valueOf(status));//损耗量
							}
						if(map.containsKey(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getSaleVolumeUnitText())){
							  String status = map.get(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getSaleVolumeUnitText()).toString();
								list1.get(i).setSaleVolumeUnitCode(Byte.valueOf(status));//销售额
							}
						if(map.containsKey(DictionaryEnum.DI_PROCESS_TYPE.getText()+list1.get(i).getProcessTypeText())){
							  String status = map.get(DictionaryEnum.DI_PROCESS_TYPE.getText()+list1.get(i).getProcessTypeText()).toString();
								list1.get(i).setProcessTypeCode(Byte.valueOf(status));//加工类型
							}
						resultModel = daSaleProcessUntBll.add(list1.get(i));
						
					
				}
		}
		return resultModel;
	}
	/**
	 * 模糊查询（直报数据）
	 */
	@ApiOperation(value = "模糊查询（直报数据）", notes = "根据查询条件模糊查询（直报数据）")
	@RequestMapping(value = "/getListByJsonDatas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonDatas() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select  ");
		selectBuffer.append(" A.name name,  ");
		selectBuffer.append(" A.sale_region_text saleRegionText,  ");
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
		selectBuffer.append(" da_sale_process A  ");
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
					selectBuffer.append(" and B.source_channel_type_code = '"+entityRelatedObject.getString("dataTimeTypeCode")+"' ");
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

		resultModel = daSaleProcessUntBll.getListBySQL(map);

		return resultModel;
	}
}



