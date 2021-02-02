package com.zee.app.extend.swagger.da;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.zee.app.generate.swagger.da.DaSaleFreshGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.da.DaSaleFreshUntBll;
import com.zee.bll.extend.unity.da.DaSaleProcessUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaCheckCollection;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaSaleFresh;
import com.zee.ent.extend.da.DaSalePeasant;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaSaleFreshParameter;
import com.zee.ent.parameter.da.DaSalePeasantParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
import com.zee.utl.TimesView;
import com.zee.utl.Tools;
import com.zee.utl.UnitUtil;
import com.sun.jna.platform.win32.OaIdl.DATE;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:46
 * @description  对外接口，扩展自DaSaleFreshGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daSaleFresh")
public class  DaSaleFreshSwgApp extends  DaSaleFreshGenSwgApp {
	
	@Autowired
	@Qualifier("daSaleFreshUntBll")
	protected DaSaleFreshUntBll daSaleFreshUntBll;
	
	@Autowired
	@Qualifier("daSaleProcessUntBll")
	protected DaSaleProcessUntBll daSaleProcessUntBll;
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	
	@ApiOperation(value = "查询", notes = "查询鲜果销量、同比、环比;鲜果收益、同比、环比")
	@RequestMapping(value = "/getFreshData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFreshData() throws ParseException {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryTime = "";//查询日期  2018
		
		 if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);

				if (jsonObject.containsKey("entityRelated")) {
					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
					if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
						queryTime = entityRelatedObject.getString("queryTime").toString().substring(0, 4);
				}
				
			}
		 DateFormat format = new SimpleDateFormat("yyyy");
		 Date year = format.parse(queryTime);
		 Calendar calendar = Calendar.getInstance(); //使用Calendar日历类对日期进行加减
		 calendar.setTime(year);
		 calendar.add(Calendar.DAY_OF_YEAR, - 1);
		 year = calendar.getTime();//获取加减以后的Date类型日期

		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
	     String preyear = simpleDateFormat.format(year);
		 
		StringBuffer selectBuffer;
		Map<String, Object> map;
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		
		
		String sqlString = "select t1.time,t1.start_time,t1.freshSale,t2.freshOutput,t3.processSale,t4.processOutput FROM"
				+ " (SELECT substr(B.start_time,1,4) time,B.start_time,sum(A.sale_amount_unit) freshSale FROM da_sale_fresh A,da_common_field B"
				+ " WHERE A.common_field_id = B.id AND B.data_time_type_code = '1' AND B.area_latitude_type_code = 4 AND A.crop_type_code = '1'"
				+ " AND A.strains_code = '0' AND B.region_id = '530723' and A.sale_region_code = 0 group by substr(B.start_time,1,4)) t1,"
				+ " (select substr(B.start_time,1,4) time,avg(A.output_value_unit) freshOutput from da_grow_yield A,da_common_field B"
				+ " where A.common_field_id = B.id and B.data_time_type_code = '1' and B.area_latitude_type_code = 4 and A.crop_type_code = '1'"
				+ " AND A.strains_code = '0' and B.region_id = '530723' group by substr(B.start_time,1,4)) t2,"
				+ " (SELECT substr(B.start_time,1,4) time,sum(A.sale_amount_unit) processSale FROM da_sale_process A,da_common_field B"
				+ " WHERE A.common_field_id = B.id AND B.data_time_type_code = '1' AND B.area_latitude_type_code = 4 AND A.crop_type_code = '1'"
				+ " AND A.process_strains_code = '0' AND B.region_id = '530723' AND B.audit_status_code = 1 AND sale_region_code = '0' group by substr(B.start_time,1,4)) t3,"
				+ " (select substr(B.start_time,1,4) time,avg(A.output_value_unit) processOutput from da_process A,da_common_field B"
				+ " where A.common_field_id = B.id and B.data_time_type_code = '1' and B.area_latitude_type_code = 4 and A.crop_type_code = '1'"
				+ " AND A.process_strains_code = '0' and B.region_id = '530723' group by substr(B.start_time,1,4)) t4"
				+ " where t1.time = t2.time and t3.time = t4.time and t1.time = t3.time ";
//		//鲜果销量、同比、环比;鲜果收益、同比、环比;
//		selectBuffer = new StringBuffer();
//		selectBuffer.append(" select round(T1.freshSale,2) freshSale,round((T1.freshSale-T2.freshSale)/T2.freshSale*100,2) freshSale_tb,case when (T1.freshSale-T2.freshSale)/T2.freshSale*100 > 0 then '1'");
//		selectBuffer.append(" when (T1.freshSale-T2.freshSale)/T2.freshSale*100 = 0 then '0' else '-1' end freshSale_flag1,round(T1.freshOutput,2) freshOutput,round((T1.freshOutput-T2.freshOutput)/T2.freshOutput*100,2) freshOutput_tb,");
//		selectBuffer.append(" case when (T1.freshOutput-T2.freshOutput)/T2.freshOutput*100 > 0 then '1' when (T1.freshOutput-T2.freshOutput)/T2.freshOutput*100 = 0 then '0' else '-1' end freshOutput_flag2,");
//		selectBuffer.append(" round(T1.processSale,2) processSale,round((T1.processSale-T2.processSale)/T2.processSale*100,2) processSale_tb,case when (T1.processSale-T2.processSale)/T2.processSale*100 > 0 then '1'");
//		selectBuffer.append(" when (T1.processSale-T2.processSale)/T2.processSale*100 = 0 then '0' else '-1' end processSale_flag1,round(T1.processOutput,2) processOutput,round((T1.processOutput-T2.processOutput)/T2.processOutput*100,2) processOutput_tb,");
//		selectBuffer.append(" case when (T1.processOutput-T2.processOutput)/T2.processOutput*100 > 0 then '1' when (T1.processOutput-T2.processOutput)/T2.processOutput*100 = 0 then '0' else '-1' end processOutput_flag2 FROM ");
//		selectBuffer.append(" ("+ sqlString +" and t1.time = (select substr(max(t0.start_time),1,4) from ("+ sqlString +") t0)) T1, ");
//		selectBuffer.append(" ("+ sqlString +" and t1.time = (select substr(max(DATE_ADD(t0.start_time,INTERVAL - 1 YEAR)),1,4) from ("+ sqlString +") t0)) T2 ");
		
		//鲜果销量、同比、环比;鲜果收益、同比、环比;
				selectBuffer = new StringBuffer();
				selectBuffer.append(" select round(T1.freshSale,2) freshSale,round((T1.freshSale-T2.freshSale)/T2.freshSale*100,2) freshSale_tb,case when (T1.freshSale-T2.freshSale)/T2.freshSale*100 > 0 then '1'");
				selectBuffer.append(" when (T1.freshSale-T2.freshSale)/T2.freshSale*100 = 0 then '0' else '-1' end freshSale_flag1,round(T1.freshOutput,2) freshOutput,round((T1.freshOutput-T2.freshOutput)/T2.freshOutput*100,2) freshOutput_tb,");
				selectBuffer.append(" case when (T1.freshOutput-T2.freshOutput)/T2.freshOutput*100 > 0 then '1' when (T1.freshOutput-T2.freshOutput)/T2.freshOutput*100 = 0 then '0' else '-1' end freshOutput_flag2,");
				selectBuffer.append(" round(T1.processSale,2) processSale,round((T1.processSale-T2.processSale)/T2.processSale*100,2) processSale_tb,case when (T1.processSale-T2.processSale)/T2.processSale*100 > 0 then '1'");
				selectBuffer.append(" when (T1.processSale-T2.processSale)/T2.processSale*100 = 0 then '0' else '-1' end processSale_flag1,round(T1.processOutput,2) processOutput,round((T1.processOutput-T2.processOutput)/T2.processOutput*100,2) processOutput_tb,");
				selectBuffer.append(" case when (T1.processOutput-T2.processOutput)/T2.processOutput*100 > 0 then '1' when (T1.processOutput-T2.processOutput)/T2.processOutput*100 = 0 then '0' else '-1' end processOutput_flag2 FROM ");
				selectBuffer.append(" ("+ sqlString +" and t1.time = '"+ queryTime +"') T1, ");
				selectBuffer.append(" ("+ sqlString +" and t1.time =  '"+ preyear +"') T2 ");
		
		map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());
		resultModel = daSaleProcessUntBll.getListBySQL(map);
		maps.put("data", (List<Map<String, Object>>)resultModel.getData());
		
		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> listMap;
			for (Map<String, Object> map1 : maps.get("data") ) {
			listMap = new HashMap<String, Object>();
			listMap.put("name","鲜果销量");
			listMap.put("mount",map1.get("freshSale"));
			listMap.put("onYear",map1.get("freshSale_tb"));
			listMap.put("flag1",map1.get("freshSale_flag1"));
			dataList.add(listMap);
			listMap = new HashMap<String, Object>();
			listMap.put("name","鲜果产值");
			listMap.put("mount",map1.get("freshOutput"));
			listMap.put("onYear",map1.get("freshOutput_tb"));
			listMap.put("flag1",map1.get("freshOutput_flag2"));
			dataList.add(listMap);
			listMap = new HashMap<String, Object>();
			listMap.put("name","加工品销量");
			listMap.put("mount",map1.get("processSale"));
			listMap.put("onYear",map1.get("processSale_tb"));
			listMap.put("flag1",map1.get("processSale_flag1"));
			dataList.add(listMap);
			listMap = new HashMap<String, Object>();
			listMap.put("name","加工品产值");
			listMap.put("mount",map1.get("processOutput"));
			listMap.put("onYear",map1.get("processOutput_tb"));
			listMap.put("flag1",map1.get("processOutput_flag2"));
			dataList.add(listMap);
		}
		resultModel.setData(dataList);
		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询月度，鲜芒果，销售量/销售金额 排名-华坪到各地")
	@RequestMapping(value = "/getFreshRank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFreshRank() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = "";//查询类型 1：销售量，2：销售额。
		String queryTime = "";//查询日期 2018
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
				queryType = entityRelatedObject.getString("queryType");
			if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
				queryTime = entityRelatedObject.getString("queryTime").toString().substring(0, 4);
		}
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryType.equals("1")){
			//鲜芒果，销售量 排名-华坪到各地
				selectBuffer.append(" SELECT distinct A.sale_region_code,A.sale_region_text,avg(A.sale_amount_unit) sale_amount_unit ");
				selectBuffer.append(" FROM da_sale_fresh A,da_common_field B WHERE A.common_field_id = B.id AND B.data_time_type_code = '1' ");
				selectBuffer.append(" AND B.area_latitude_type_code = 4 and A.crop_type_code = '1' AND A.strains_code = '0' AND B.region_id = '530723' AND substr(B.start_time, 1, 4) = '"+queryTime+"' ");
				selectBuffer.append(" and A.sale_region_code is not null and A.sale_region_code != 0 and A.sale_region_code <> '' group by A.sale_region_code ORDER BY A.sale_amount_unit desc ");
		 }else if(queryType.equals("2")){
			//鲜芒果，销售金额 排名-华坪到各地
				selectBuffer.append(" SELECT DISTINCT A.sale_region_code,A.sale_region_text,avg(A.sale_volume_unit) sale_amount_unit ");
				selectBuffer.append(" FROM da_sale_fresh A,da_common_field B WHERE A.common_field_id = B.id AND B.data_time_type_code = '1' ");
				selectBuffer.append(" AND B.area_latitude_type_code = 4 and A.crop_type_code = '1' AND A.strains_code = '0' AND B.region_id = '530723' AND substr(B.start_time, 1, 4) = '"+queryTime+"' ");
				selectBuffer.append(" and A.sale_region_code is not null and A.sale_region_code != 0 and A.sale_region_code <> '' group by A.sale_region_code ORDER BY A.sale_volume_unit desc ");
		 }
		
		
		map.put("Sql", selectBuffer.toString());

		
		resultModel = daSaleFreshUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询月度，鲜芒果，销售量/销售金额 流向")
	@RequestMapping(value = "/getFreshFlow", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFreshFlow() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = "";//查询类型 1：销售量，2：销售额。
		String queryTime = "";//查询日期  2018
		
		 if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);

				if (jsonObject.containsKey("entityRelated")) {
					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
					if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
						queryType = entityRelatedObject.getString("queryType");
					if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
						queryTime = entityRelatedObject.getString("queryTime").toString().substring(0, 4);
				}
				
			}

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryType.equals("1")){
			//鲜芒果，销售量 流向
				selectBuffer.append(" select distinct '530723' oregion_code,'华坪' oregion_text,A.sale_region_code,A.sale_region_text, ");
				selectBuffer.append(" A.sale_amount_unit unit from da_sale_fresh A,da_common_field B ");
				selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '1' and B.area_latitude_type_code = 4 ");
				selectBuffer.append(" and A.crop_type_code = '1' and A.strains_code = '0' and B.region_id = '530723' ");
				selectBuffer.append(" and substr(B.start_time,1,4) = '"+queryTime+"' and A.sale_region_code is not null and A.sale_region_code <> '' order by A.sale_amount desc ");
		 }else if(queryType.equals("2")){
			//鲜芒果，销售金额 流向
				selectBuffer.append(" select distinct '530723' oregion_code,'华坪' oregion_text,A.sale_region_code,A.sale_region_text, ");
				selectBuffer.append(" A.sale_volume_unit unit from da_sale_fresh A,da_common_field B ");
				selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '1' and B.area_latitude_type_code = 4 ");
				selectBuffer.append(" and A.crop_type_code = '1' and A.strains_code = '0' and B.region_id = '530723' ");
				selectBuffer.append(" and substr(B.start_time,1,4) = '"+queryTime+"' and A.sale_region_code is not null and A.sale_region_code <> '' order by A.sale_volume desc ");
		 }
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		map.put("Sql", selectBuffer.toString());

		resultModel = daSaleFreshUntBll.getListBySQL(map);
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
	
//	@ApiOperation(value = "查询", notes = "查询年度，鲜芒果，销费量/销费金额 预测")
//	@RequestMapping(value = "/getFreshPredict", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultModel getFreshPredict() {
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
//			//年度，鲜芒果，销费量 预测
//				selectBuffer.append(" select t0.year,case when t1.val1 is null then '-' else t1.val1 end sale_amount_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time1,sale_amount_actual val1 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '1' and sale_amount_forecast_flag = '0') t1 on t0.year = t1.time1 ");
//				selectBuffer.append(" UNION select t0.year,case when t2.val2 is null then '-' else t2.val2 end sale_amount_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year = (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time2,sale_amount_forecast val2 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '1' and sale_amount_forecast_flag = '1') t2 on t0.year = t2.time2 ");
//		 }else if(queryType.equals("2")){
//			//年度，鲜芒果，销费金额 预测
//			 	selectBuffer.append(" select t0.year,case when t1.val1 is null then '-' else t1.val1 end sale_volume_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time1,sale_volume_actual val1 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '1' and sale_volume_forecast_flag = '0') t1 on t0.year = t1.time1 ");
//				selectBuffer.append(" UNION select t0.year,case when t2.val2 is null then '-' else t2.val2 end sale_volume_unit ");
//				selectBuffer.append(" from (select * from past_5_year_view where year = (select max(year) from past_5_year_view)) t0 left join ");
//				selectBuffer.append(" (select date_time time2,sale_volume_forecast val2 from mf_fresh_process_volume ");
//				selectBuffer.append(" where type = '1' and sale_volume_forecast_flag = '1') t2 on t0.year = t2.time2 ");
//		 }
//		
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("Sql", selectBuffer.toString());
//
//		resultModel = daSaleFreshUntBll.getListBySQL(map);
//
//
//		return resultModel;
//	}
	
	@ApiOperation(value = "查询", notes = "查询鲜果产值")
	@RequestMapping(value = "/getFreshOutput", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFreshOutput() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		//鲜果产值;
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select t0.year,case when t1.output_value_unit is null then '-' else t1.output_value_unit end output_value_unit ");
		selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 ");
		selectBuffer.append(" left join (select substr(t.start_time,1,4) time1,t.output_value_unit from (select B.start_time,A.output_value_unit from da_industry_fresh A,da_common_field B ");
		selectBuffer.append(" where A.common_field_id = B.id and B.data_time_type_code = '1' and B.area_latitude_type_code = 4 ");
		selectBuffer.append(" and A.crop_type_code = '1' and A.strains_code = '0' and B.region_id = '530723' order by B.add_time desc) t group by substr(t.start_time,1,4)) t1 on t0.year = t1.time1 ");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());

		resultModel = daSaleFreshUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询华坪鲜果单件偏好")
	@RequestMapping(value = "/getFreshAmountPreference", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFreshAmountPreference() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = "";//查询类型 1：芒果品种消费量偏好，2：芒果单斤价格偏好，3：芒果消费渠道偏好，4：芒果规格偏好
		String queryTime = "";//查询日期  2018
		
		 if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);

				if (jsonObject.containsKey("entityRelated")) {
					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
					if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
						queryTime = entityRelatedObject.getString("queryTime");
					if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
						queryType = entityRelatedObject.getString("queryType");
				}
				
			}
			StringBuffer selectBuffer = new StringBuffer();
		 if(queryType.equals("1")){
				//单件偏好-芒果品种消费量偏好;
			 	selectBuffer.append(" select round(sum(amount),2) amount,T.strains_code,T.strains_text from ( ");
				selectBuffer.append(" select substr(B.start_time,1,10) start_time,avg(A.sale_amount_unit) amount,A.strains_code,A.strains_text ");
				selectBuffer.append(" from mf_sale_ecommerce_craw A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '5' ");
				selectBuffer.append(" and A.crop_type_code = '1' group by substr(B.start_time,1,10),A.strains_code,A.strains_text) T ");
				selectBuffer.append(" where substr(T.start_time,1,4) = '"+queryTime+"' and T.strains_code is not null group by T.strains_code order by amount  ");
		 }else if(queryType.equals("2")){
			 	//单件偏好-芒果单斤价格偏好;
				selectBuffer.append(" select round(sum(amount),2) amount,T.price_range_code,T.price_range_text from ( ");
				selectBuffer.append(" select substr(B.start_time,1,10) start_time,avg(A.sale_amount_unit) amount,A.price_range_code,A.price_range_text ");
				selectBuffer.append(" from mf_sale_ecommerce_craw A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '5' ");
				selectBuffer.append(" and A.crop_type_code = '1' group by substr(B.start_time,1,10),A.price_range_code,A.price_range_text) T ");
				selectBuffer.append(" where substr(T.start_time,1,4) = '"+queryTime+"' and T.price_range_code is not null group by T.price_range_code order by amount ");
		 }else if(queryType.equals("3")){
			 	//单件偏好-芒果消费渠道偏好;
				selectBuffer.append(" select distinct sum(A.sale_amount_unit) amount,A.sale_channel_type_code,A.sale_channel_type_text ");
				selectBuffer.append(" from da_sale_channel A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '1' ");
				selectBuffer.append(" and substr(B.start_time,1,4) = '"+queryTime+"' ");
				selectBuffer.append(" and A.crop_type_code = '1' and A.sale_channel_type_code <> '0' group by A.sale_channel_type_code,A.sale_channel_type_text order by amount");
		 }else if(queryType.equals("4")){
			 	//单件偏好-芒果规格偏好;
			 	selectBuffer.append(" select round(sum(amount),2) amount,T.weight_specification_code,T.weight_specification_text from ( ");
				selectBuffer.append(" select substr(B.start_time,1,10) start_time,avg(A.sale_amount_unit) amount,A.weight_specification_code,A.weight_specification_text ");
				selectBuffer.append(" from mf_sale_ecommerce_craw A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '5' ");
				selectBuffer.append(" and A.crop_type_code = '1' group by substr(B.start_time,1,10),A.weight_specification_code,A.weight_specification_text) T ");
				selectBuffer.append(" where substr(T.start_time,1,4) = '"+queryTime+"' and T.weight_specification_code is not null group by T.weight_specification_code order by amount ");
		 }

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());

		resultModel = daSaleFreshUntBll.getListBySQL(map);

		return resultModel;
	}
	
	/**
	 * 查询某一年的总的鲜果销售数据
	 * @return
	 */
	@ApiOperation(value = "查询总的鲜果销售数据", notes = "查询总的鲜果销售数据")
	@RequestMapping(value = "/getSaleFreshSumInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getSaleFreshSumInfo() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String year = "";//年份id
		String regionId = "";//地区id
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("year") && StringUtils.isNotBlank(entityRelatedObject.getString("year")))
					year = entityRelatedObject.getString("year");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
			}
		}
		resultModel = getSaleFreshSummation(year,regionId);
		return resultModel;
	}

	private ResultModel getSaleFreshSummation(String year, String regionId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT SUBSTR(B.start_time,1,4) TIME,
		B.start_time AS startTime,
		A.sale_amount_unit AS saleSum
		FROM da_sale_fresh A,da_common_field B WHERE A.common_field_id = B.id 
			AND A.crop_type_code = 1 
			AND A.strains_code = 0
			AND B.data_time_type_code = 1
			AND B.area_latitude_type_code = 4 
			AND B.region_id = '530723' GROUP BY TIME ORDER BY startTime DESC*/
		
		selectBuffer.append(" SELECT SUBSTR(B.start_time,1,4) TIME,B.start_time AS startTime,A.sale_amount_unit AS saleSum			");
		selectBuffer.append(" FROM da_sale_fresh A,da_common_field B WHERE A.common_field_id = B.id 								");
		selectBuffer.append(" AND A.crop_type_code = 1																				");
		selectBuffer.append(" AND A.strains_code = 0																				");
		selectBuffer.append(" AND B.data_time_type_code = 1																			");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																	");
		selectBuffer.append(" AND SUBSTR(B.start_time,1,4)=																			");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.region_id =																						");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY TIME ORDER BY startTime DESC																	");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daSaleFreshUntBll.getListBySQL(map);
		return resultModel;
	}
	
	@ApiOperation(value = "查询鲜果和加工品的销售流向", notes = "查询鲜果和加工品的销售流向")
	@RequestMapping(value = "/getFreshProcessDirectionInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFreshProcessDirectionInfo() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String year = "";//年份id
		String regionId = "";//地区id
		String limit = "";//需要获取几条数据
		
		//获取时间列表
		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "year");//视图名，年year，月month，日date，小时hour（默认为年）
		timeMap.put("hasCurrent", "false");
		timeMap.put("pastNum", "1");
		List<String> timeList = TimesView.getTimesView(timeMap);
		year = timeList.get(0);
				
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("limit") && StringUtils.isNotBlank(entityRelatedObject.getString("limit")))
					limit = entityRelatedObject.getString("limit");
			}
		}
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		//查询鲜果的销售流向
		ResultModel freshModel = getFreshDirectionInfo(year,regionId,limit);
		//查询加工品的销售流向
		ResultModel processModel = getProcessDirectionInfo(year,regionId,limit);
		
		BigDecimal freshSum = new BigDecimal("0");
		BigDecimal processSum = new BigDecimal("0");
		BigDecimal freshSum4 = new BigDecimal("0");
		BigDecimal processSum4 = new BigDecimal("0");
		BigDecimal saleSum = new BigDecimal("0");

		List<Map<String, Object>> freshList = null;
		Object freshObj = freshModel.getData();
		Map<String, Object> freshMap = new HashMap<String, Object>();
		Map<String, Object> freshotherMap = new HashMap<String, Object>();
		if (freshObj != null) {
			freshList = (List<Map<String, Object>>) freshObj;
			for(Map<String, Object> map : freshList) {
				if(map.containsKey("saleSum")) {
					BigDecimal sale = (BigDecimal) map.get("saleSum");
					freshSum = freshSum.add(sale);
				}
			}
			
			if(StringUtils.isNotBlank(limit)) {
				int num = Integer.parseInt(limit) -1;
				if(freshList.size()>=num) {
					freshList = freshList.subList(0, num);
				}
			}else {
				if(freshList.size()>=4) {
					freshList = freshList.subList(0, 4);
				}
			}
		}
		
		List<Map<String, Object>> processList = null;
		Object processObj = processModel.getData();
		Map<String, Object> processMap = new HashMap<String, Object>();
		Map<String, Object> processotherMap = new HashMap<String, Object>();
		if (processObj != null) {
			processList = (List<Map<String, Object>>) processObj;
			for(Map<String, Object> map : processList) {
				if(map.containsKey("saleSum")) {
					BigDecimal sale = (BigDecimal) map.get("saleSum");
					processSum = processSum.add(sale);
				}
			}
			
			if(StringUtils.isNotBlank(limit)) {
				int num = Integer.parseInt(limit) -1;
				if(processList.size()>=num) {
					processList = processList.subList(0, num);
				}
			}else {
				if(processList.size()>=4) {
					processList = processList.subList(0, 4);
				}
			}
		}
		
		saleSum = freshSum.add(processSum);
		
		for(Map<String, Object> map : freshList) {
			if(map.containsKey("saleSum")) {
				BigDecimal sale = (BigDecimal) map.get("saleSum");
				freshSum4 = freshSum4.add(sale);
			}
		}
		freshotherMap.put("regionText", "其它");
		freshotherMap.put("marketName", "其它");
		freshotherMap.put("saleSum", freshSum.subtract(freshSum4));
		freshList.add(freshotherMap);
		
		for(Map<String, Object> map : processList) {
			if(map.containsKey("saleSum")) {
				BigDecimal sale = (BigDecimal) map.get("saleSum");
				processSum4 = processSum4.add(sale);
			}
		}
		processotherMap.put("regionText", "其它");
		processotherMap.put("marketName", "其它");
		processotherMap.put("saleSum", processSum.subtract(processSum4));
		processList.add(processotherMap);
		
		resultMap.put("year", year);
		resultMap.put("saleTotal", saleSum);
		resultMap.put("saleFresh", freshList);
		resultMap.put("saleProcess", processList);
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		resultList.add(resultMap);
		
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	/**
	 * 获取某一年度的鲜果销售流向
	 * @param year
	 * @param regionId
	 * @return
	 */
	private ResultModel getFreshDirectionInfo(String year,String regionId,String limit) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*
		SELECT 	
			t0.TIME,
			t0.startTime,
			t0.regionCode,
			t0.regionText,
			CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum
		FROM
		(SELECT 
		SUBSTR(B.start_time,1,4) TIME,
		B.start_time AS startTime,
		A.sale_region_code AS regionCode,
		A.sale_region_text AS regionText,
		CASE WHEN A.sale_amount_unit IS NULL THEN 0 ELSE A.sale_amount_unit END AS saleSum 
		FROM 
		da_sale_fresh A,
		da_common_field B 
		WHERE A.common_field_id = B.id 
			AND A.crop_type_code = '1'
			AND B.data_time_type_code = 1
			AND B.area_latitude_type_code = 4 
			AND B.audit_status_code = 0
			AND B.region_id = '530723'
			AND SUBSTR(B.start_time,1,4) = '2018'
			ORDER BY startTime DESC) t0 
			GROUP BY regionCode 
			ORDER BY saleSum DESC
		*/
		selectBuffer.append(" SELECT t0.time,t0.startTime,t0.regionCode,t0.regionText,t0.marketName,															");
		selectBuffer.append(" CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum												");
		selectBuffer.append(" FROM																												");
		selectBuffer.append(" (SELECT 																											");
		selectBuffer.append(" SUBSTR(B.start_time,1,4) time,																					");
		selectBuffer.append(" B.start_time AS startTime,																						");
		selectBuffer.append(" A.sale_region_code AS regionCode,																					");	
		selectBuffer.append(" A.sale_region_text AS regionText,																					");
		selectBuffer.append(" A.sale_city AS marketName,																					    ");
		selectBuffer.append(" CASE WHEN A.sale_amount_unit IS NULL THEN 0 ELSE A.sale_amount_unit END AS saleSum 								");
		selectBuffer.append(" FROM da_sale_fresh A,da_common_field B  																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																					");
		selectBuffer.append(" AND A.crop_type_code = 1																							");
		selectBuffer.append(" AND B.data_time_type_code = 1																						");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																				");
		selectBuffer.append(" AND B.audit_status_code = 1																						");
		selectBuffer.append(" AND B.region_id =																									");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time,1,4) =																					");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("' AND A.sale_region_text <> '' AND A.sale_region_code <> '0'                                                       ");
		selectBuffer.append(" ORDER BY startTime DESC) t0 																						");
		selectBuffer.append(" GROUP BY regionCode 																								");
		selectBuffer.append(" ORDER BY saleSum DESC 																							");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daSaleFreshUntBll.getListBySQL(map);
		return resultModel;
	}
	
	/**
	 * 获取某一年度的加工品销售流向数据
	 * @param year
	 * @param regionId
	 * @return
	 */
	private ResultModel getProcessDirectionInfo(String year,String regionId,String limit) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		/*
		SELECT 	
			t0.TIME,
			t0.startTime,
			t0.regionCode,
			t0.regionText,
			CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum
		FROM
		(SELECT 
		SUBSTR(B.start_time,1,4) TIME,
		B.start_time AS startTime,
		A.sale_region_code AS regionCode,
		A.sale_region_text AS regionText,
		CASE WHEN A.sale_amount_unit IS NULL THEN 0 ELSE A.sale_amount_unit END AS saleSum 
		FROM 
		da_sale_process A,
		da_common_field B 
		WHERE A.common_field_id = B.id 
			AND A.crop_type_code = '1'
			AND B.data_time_type_code = 1
			AND B.area_latitude_type_code = 4 
			AND B.audit_status_code = 1
			AND B.region_id = '530723'
			AND SUBSTR(B.start_time,1,4) = '2018'
			ORDER BY startTime DESC) t0 
			GROUP BY regionCode 
			ORDER BY saleSum DESC
		*/
		selectBuffer.append(" SELECT t0.time,t0.startTime,t0.regionCode,t0.regionText,t0.marketName,															");
		selectBuffer.append(" CASE WHEN t0.saleSum IS NULL THEN 0 ELSE t0.saleSum END AS saleSum												");
		selectBuffer.append(" FROM																												");
		selectBuffer.append(" (SELECT 																											");
		selectBuffer.append(" SUBSTR(B.start_time,1,4) time,																					");
		selectBuffer.append(" B.start_time AS startTime,																						");
		selectBuffer.append(" A.sale_region_code AS regionCode,																					");	
		selectBuffer.append(" A.sale_region_text AS regionText,																					");
		selectBuffer.append(" A.sale_area_text AS marketName,																					");
		selectBuffer.append(" CASE WHEN A.sale_amount_unit IS NULL THEN 0 ELSE A.sale_amount_unit END AS saleSum 								");
		selectBuffer.append(" FROM da_sale_process A,da_common_field B  																		");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																					");
		selectBuffer.append(" AND A.crop_type_code = 1																							");
		selectBuffer.append(" AND B.data_time_type_code = 1																						");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																				");
		selectBuffer.append(" AND B.audit_status_code = 1																						");
		selectBuffer.append(" AND B.region_id =																									");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time,1,4) =																					");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("' AND A.sale_region_text <> '' AND A.sale_region_code <> '0'                                                       ");
		selectBuffer.append(" ORDER BY startTime DESC) t0 																						");
		selectBuffer.append(" GROUP BY regionCode 																								");
		selectBuffer.append(" ORDER BY saleSum DESC 																							");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daSaleFreshUntBll.getListBySQL(map);
		return resultModel;
	}
	

	@ApiOperation(value = "新增记录（直报数据）", notes = "新增单条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaSaleFresh") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaSaleFresh jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.add(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			jsonData.setSaleAmountUnit(UnitUtil.switchUnit(jsonData.getSaleAmount(), jsonData.getSaleAmountUnitCode(), "DI_WEIGHT_UNIT"));
			jsonData.setSaleVolumeUnit(UnitUtil.switchUnit(jsonData.getSaleVolume(), jsonData.getSaleVolumeUnitCode(), "DI_PER_PRICE_UNIT"));
			resultModel = daSaleFreshUntBll.add(jsonData);
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
				resultModel = daSaleFreshUntBll.delete(id);
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "单条查询（直报）", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daSaleFreshUntBll.getModel(id);
		if(result.getIsSuccess()){
			DaSaleFresh jsonData = (DaSaleFresh) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			result.setData(jsonData);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量删除（直报数据）", notes = "根据主键列表批量删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaSaleFreshDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaSaleFreshParameter.DeleteByIdList jsonData) {
		
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
				resultModel = daSaleFreshUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}
	
	@ApiOperation(value = "修改记录（直报数据）", notes = "修改指定记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaSaleFresh") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaSaleFresh jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setSaleAmountUnit(UnitUtil.switchUnit(jsonData.getSaleAmount(), jsonData.getSaleAmountUnitCode(), "DI_WEIGHT_UNIT"));
			jsonData.setSaleVolumeUnit(UnitUtil.switchUnit(jsonData.getSaleVolume(), jsonData.getSaleVolumeUnitCode(), "DI_PER_PRICE_UNIT"));
			resultModel = daSaleFreshUntBll.update(jsonData);
		}
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaSaleFreshUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaSaleFreshParameter.UpdateList jsonData) {
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
		selectBuffer.append("	INNER JOIN da_sale_fresh B ON A.id = B.common_field_id                               ");
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
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaSaleFreshAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaSaleFresh> list1 = (List<DaSaleFresh>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaSaleFresh.class);
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
						if(map.containsKey(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getStrainsText())){
							  String status = map.get(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getStrainsText()).toString();
								list1.get(i).setStrainsCode(Byte.valueOf(status));//作物品种
							}
						if(map.containsKey(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText())){
							  String status = map.get(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText()).toString();
								list1.get(i).setCropTypeCode(Byte.valueOf(status));//作物种类
							}
						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getSaleAmountUnitText())){
							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getSaleAmountUnitText()).toString();
								list1.get(i).setSaleAmountUnitCode(Byte.valueOf(status));//销售量
							}
						if(map.containsKey(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getSaleVolumeUnitText())){
							  String status = map.get(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getSaleVolumeUnitText()).toString();
								list1.get(i).setSaleVolumeUnitCode(Byte.valueOf(status));//交易额
							}
						resultModel = daSaleFreshUntBll.add(list1.get(i));
						
					
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

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
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
		selectBuffer.append(" da_sale_fresh A  ");
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

		resultModel = daSaleFreshUntBll.getListBySQL(map);

		return resultModel;
	}
}



