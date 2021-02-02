package com.zee.app.extend.swagger.da;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
import com.zee.app.generate.swagger.da.DaGrowYieldGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.bll.extend.unity.gp.GpRegionUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaGrowYield;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaGrowYieldParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.set.enumer.DomainEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
import com.zee.utl.MathUtil;
import com.zee.utl.TimesView;
import com.zee.utl.Tools;
import com.zee.utl.UnitUtil;
import com.zee.utl.service.DaUserContributionUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaGrowYieldGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daGrowYield")
public class DaGrowYieldSwgApp extends DaGrowYieldGenSwgApp {

	private static final String ORGANIC_IDENTIFICATION = "2";
	private static final String GREEN_IDENTIFICATION = "3";

	private static final String DIMENSION_YEAR = "1";
	private static final String DIMENSION_MONTH = "3";
	private static final String DIMENSION_DAY = "4";

	private static final String[] TIME_FORMAT = { "yyyy", "yyyy-MM", "yyyy-MM-dd" };
	private static final String[] TIME_START = { "1990", "1990-01", "1990-01-01" };
	
	private static final String COMMON_UNIT_AREA = "平方米";
	private static final String COMMON_UNIT_PRODUCT = "千克";
	private static final String COMMON_UNIT_OUTPUT = "元";
	private static final String COMMON_UNIT_PEASANTCOUNT = "户";
	
	private static final String SQUARE_METER = "6666666.666667";
	private static final String KILOGRAM = "10000000";

	@Autowired
	@Qualifier("gpDictionaryUntBll")
	protected GpDictionaryUntBll gpDictionaryUntBll;

	@Autowired
	@Qualifier("gpRegionUntBll")
	protected GpRegionUntBll gpRegionUntBll;

	@Autowired
	DaUserContributionUtil daUserContributionUtil;
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	
	@ApiOperation(value = "获取当前用户上传产量列表", notes = "获取当前用户上传产量列表")
	@RequestMapping(value = "/getListByCurrentUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByCurrentUser() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                     ");
		selectBuffer.append("		A.base_code baseCode,                                                  ");
		selectBuffer.append("		A.base_name baseName,                                                  ");
		selectBuffer.append("		A.strains_code strainsCode,                                            ");
		selectBuffer.append("		A.strains_text strainsText,                                            ");
		selectBuffer.append("round(ifnull(A.product_total_unit,0),2) productTotalUnit,                     ");
		
		selectBuffer.append("		A.id, 																 ");
		selectBuffer.append("		A.common_field_id commonFieldId,			                          ");
		selectBuffer.append("round(ifnull(A.grow_area,0),2)	 growArea,                                    ");
		selectBuffer.append("		A.grow_area_code   growAreaCode ,                                     ");
		selectBuffer.append("		A.grow_area_text   growAreaText,                                     ");
		selectBuffer.append("round(ifnull(A.grow_area_unit,0),2)   growAreaUnit,                          ");
		selectBuffer.append("round(ifnull(A.fruit_area,0),2)       fruitArea,                              ");
		selectBuffer.append("		A.fruit_area_code  fruitAreaCode,                                     ");
		selectBuffer.append("		A.fruit_area_text   fruitAreaText,                                     ");
		selectBuffer.append("round(ifnull(A.fruit_area_unit,0),2)   fruitAreaUnit,                         ");
		selectBuffer.append("round(ifnull(A.product_total,0),2)     productTotal,                         ");
		selectBuffer.append("		A.product_total_code  productTotalCode,                               ");
		selectBuffer.append("		A.product_total_text  productTotalText,                                ");
		selectBuffer.append("round(ifnull(A.output_value,0),2)        outputValue,                         ");
		selectBuffer.append("		A.output_value_code   outputValueCode,                                  ");
		selectBuffer.append("		A.output_value_text   outputValueText,                                  ");
		selectBuffer.append("round(ifnull(A.output_value_unit,0),2)   outputValueUnit,                     ");
		selectBuffer.append("		A.year ,                                     							");
		selectBuffer.append("round(ifnull(A.organic_grow_area,0),2)	 organicGrowArea,                       ");
		selectBuffer.append("		A.organic_grow_area_code   organicGrowAreaCode,                         ");
		selectBuffer.append("		A.organic_grow_area_text   organicGrowAreaText,                         ");
		selectBuffer.append("round(ifnull(A.organic_grow_area_unit,0),2)   organicGrowAreaUnit,            ");
		selectBuffer.append("round(ifnull(A.sale_total,0),2)	         saleTotal,                         ");
		selectBuffer.append("		A.sale_total_code  saleTotalCode,                                       ");
		selectBuffer.append("		A.sale_total_text  saleTotalText,                                       ");
		selectBuffer.append("round(ifnull(A.sale_total_unit,0),2)  saleTotalUnit,                    ");
		selectBuffer.append("round(ifnull(A.e_commerce_output_value,0),2)  eCommerceOutputValue,            ");
		selectBuffer.append("		A.e_commerce_output_value_code  eCommerceOutputValueCode,               ");
		selectBuffer.append("		A.e_commerce_output_value_text  eCommerceOutputValueText,                ");
		selectBuffer.append("round(ifnull(A.e_commerce_output_value_unit,0),2)  eCommerceOutputValueUnit,    ");
		selectBuffer.append("round(ifnull(A.e_commerce_sale_total,0),2)   eCommerceSaleTotal,                ");
		selectBuffer.append("		A.e_commerce_sale_total_code  eCommerceSaleTotalCode,                    ");
		selectBuffer.append("		A.e_commerce_sale_total_text  eCommerceSaleTotalText,                    ");
		selectBuffer.append("round(ifnull(A.e_commerce_sale_total_unit,0),2)  eCommerceSaleTotalUnit,       ");
		
		selectBuffer.append("		DATE_FORMAT(B.add_time,'%Y-%m-%d') addTime                                 ");
		selectBuffer.append("	FROM                                                                           ");
		selectBuffer.append("		da_grow_yield A                                                            ");
		selectBuffer.append("	INNER JOIN da_common_field B ON A.common_field_id = B.id                       ");
		selectBuffer.append("	WHERE                                                                          ");
		selectBuffer.append("		B.add_user_id = '"+this.getCurrentUser().getId()+"'                        ");
		
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
		}else{
			selectBuffer.append("	ORDER BY addTime DESC	");
		}
		map.put("Sql", selectBuffer.toString());

		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "查询所需的年份数据", notes = "查询所需的年份数据")
	@RequestMapping(value = "/getYearInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getYearInfo() {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT t0.year FROM (SELECT * FROM past_5_year_view WHERE YEAR <> (SELECT MAX(YEAR) FROM past_5_year_view)) t0");
		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);
		return resultModel;
	}

	@ApiOperation(value = "查询种植结构数据", notes = "查询种植结构数据")
	@RequestMapping(value = "/getGrowStructureInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getGrowStructureInfo() {
		BigDecimal areaSum = new BigDecimal("0");
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String tendYear = "";// 种植趋势下拉框时间
		String year = "";// 年份id
		String regionId = "";// 地区id
		int pageSize = 0;// 种植结构数据 需要 返回的数据量

		// 获取时间列表
		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "year");// 视图名，年year，月month，日date，小时hour（默认为年）
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
				if (entityRelatedObject.containsKey("year") && StringUtils.isNotBlank(entityRelatedObject.getString("year")))
					tendYear = entityRelatedObject.getString("year");
			}
			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				if (pageObject.containsKey("pageSize") && StringUtils.isNotBlank(pageObject.getString("pageSize")))
					pageSize = Integer.parseInt(pageObject.getString("pageSize"));
			}
		}

		// 计算芒果的总种植面积
		ResultModel totalModel = new ResultModel();
		totalModel = getMangoSummationInfo(year, regionId, "0");

		Object totalObj = totalModel.getData();
		List<Map<String, Object>> totalList = null;
		Map<String, Object> totalMap = new HashMap<String, Object>();
		if (totalObj != null)
			totalList = (List<Map<String, Object>>) totalObj;
		if (totalList != null && totalList.size() >= 1) {
			totalMap = totalList.get(0);
			if (totalMap.containsKey("areaSum")) {
				areaSum = (BigDecimal) totalMap.get("areaSum");
			}
		}

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> tempResultList = new ArrayList<Map<String, Object>>();
		List<BigDecimal> tempKeyList = new ArrayList<BigDecimal>();
		Map<String, Object> perResultMap = null;

		// 获取芒果品种<code,text>列表  
		//20180814-01 客户问题跟踪表   固定展示 红象牙、圣心芒、凯特芒、金煌芒、爱文芒、澳芒、热农芒和鹰嘴芒这八个品种
		Map<String, Object> mangoBreedMap = getDicMapByTypeId(CustomSymbolic.DI_CROP_STRAINS,"10,3,4,9,7,1,2,11");

		Map<String, Object> perBreedMap = null;
		Iterator iter = mangoBreedMap.entrySet().iterator();
		while (iter.hasNext()) {
			perResultMap = new HashMap<String, Object>();
			Map.Entry entry = (Map.Entry) iter.next();
			String code = (String) entry.getKey();
			String text = (String) entry.getValue();

			if (!"0".equals(code)) {
				ResultModel perBreedGrowModel = getPerBreedGrowModel(tendYear, regionId, code);
				Object perBreedObj = perBreedGrowModel.getData();
				List<Map<String, Object>> perBreedList = null;
				if (perBreedObj != null) {
					perBreedList = (List<Map<String, Object>>) perBreedObj;
					if (perBreedList != null && perBreedList.size() >= 1) {
						perBreedMap = perBreedList.get(0);
						if (perBreedMap.containsKey("growArea")) {
							BigDecimal proportion = new BigDecimal("0");
							BigDecimal growArea = (BigDecimal) perBreedMap.get("growArea");
							if(growArea.compareTo(BigDecimal.ZERO) != 0) {
								perResultMap.put("strainsCode", code);
								perResultMap.put("strainsText", text);
								perResultMap.put("growArea", growArea);
								tempKeyList.add(growArea);
								if (areaSum.compareTo(BigDecimal.ZERO) != 0) {
									proportion = growArea.divide(areaSum, 4, BigDecimal.ROUND_HALF_UP);
								}
								DecimalFormat df = new DecimalFormat("0.00%");
								String percent = df.format(proportion);
								perResultMap.put("growProportion", percent);
								tempResultList.add(perResultMap);
							}
						}
					} else {
						perResultMap.put("growArea", "0.00");
						perResultMap.put("growProportion", "0.00%");
					}
				}
			}
		}
		Collections.sort(tempKeyList);
		Collections.reverse(tempKeyList);
		List<Integer> tempIndexList = new ArrayList<Integer>();
		for(int i = 0;i<tempKeyList.size();i++) {
			BigDecimal b = tempKeyList.get(i);
			for(int j = 0;j<tempResultList.size();j++) {
				BigDecimal a = (BigDecimal) tempResultList.get(j).get("growArea");
				if(a.compareTo(b) == 0 && !tempIndexList.contains(j)) {
					tempIndexList.add(j);
					resultList.add(tempResultList.get(j));
				}
			}
		}
		
		resultList = resultList.subList(0, resultList.size()>pageSize? pageSize:resultList.size());
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private ResultModel getPerBreedGrowModel(String year, String regionId, String breed) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT SUBSTR(B.start_time, 1, 4) AS TIME, B.start_time AS startTime,
		 * A.strains_code AS strainsCode, A.strains_text AS strainsText,
		 * A.grow_area_unit AS growArea FROM da_grow_yield A, da_common_field B
		 * WHERE A.common_field_id = B.id AND A.crop_type_code = 1 AND
		 * A.strains_code = 1 AND B.data_time_type_code = 1 AND
		 * B.area_latitude_type_code = 4 AND B.audit_status_code = 1 AND
		 * B.region_id = '530723' AND SUBSTR(B.start_time, 1, 4) = 2018 ORDER BY
		 * startTime DESC
		 */

		selectBuffer.append(" SELECT 																									");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) AS time,																		");
		selectBuffer.append(" B.start_time AS startTime,																				");
		selectBuffer.append(" A.strains_code AS strainsCode,																			");
		selectBuffer.append(" A.strains_text AS strainsText,																			");
		selectBuffer.append(" A.grow_area_unit AS growArea 																				");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_grow_yield A,																							");
		selectBuffer.append(" da_common_field B 																						");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1 																					");
		selectBuffer.append(" AND A.strains_code =																						");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(breed)) {
			selectBuffer.append(breed);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.data_time_type_code = 1 																			");
//		selectBuffer.append(" AND B.area_latitude_type_code = 4 																		");
		selectBuffer.append(" AND B.audit_status_code = 1 																				");
		selectBuffer.append(" AND B.`region_id`=																						");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC																					");

		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}

	private Map<String, Object> getDicMapByTypeId(String dicTypeId,String pCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT A.`code` AS code, A.text AS text FROM gp_dictionary A WHERE type_id = '");
		if (StringUtils.isNotBlank(dicTypeId)) {
			selectBuffer.append(dicTypeId);
		}
		selectBuffer.append("'");
		
		if(StringUtils.isNotBlank(pCode)){
			selectBuffer.append(" and code in("+pCode+") ");
		}

		map.put("Sql", selectBuffer.toString());
		resultModel = gpDictionaryUntBll.getListBySQL(map);

		Map<String, Object> resultMap = null;
		Object obj = resultModel.getData();
		List<Map<String, Object>> list = null;
		if (obj != null) {
			list = (List<Map<String, Object>>) obj;
			if (list != null && list.size() >= 1) {
				resultMap = new HashMap<String, Object>();
				for (Map<String, Object> tempMap : list) {
					String code = "";
					String text = "";
					if (tempMap.containsKey("code")) {
						code = (int) tempMap.get("code") + "";
					}
					if (tempMap.containsKey("text")) {
						text = (String) tempMap.get("text");
					}
					resultMap.put(code, text);
				}
				return resultMap;
			}
		}
		return null;
	}

	/**
	 * 查询某年某个品种芒果的种植数据，包括种植面积、挂果面积、产量、产值和种植户数量
	 * @param year
	 * @param regionId
	 * @return
	 */
	public ResultModel getMangoSummationInfo(String year, String regionId, String breed) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT t0.* FROM (SELECT SUBSTR(B.start_time, 1, 4) TIME,
		 * B.start_time AS startTime, A.`grow_area_unit` AS areaSum,
		 * A.fruit_area_unit AS fruitAreaSum, A.`product_total_unit` AS
		 * productSum, A.`output_value_unit` AS outputSum, A.`peasant_count` AS
		 * peasantCount FROM da_grow_yield A, da_common_field B WHERE
		 * A.common_field_id = B.id AND A.crop_type_code = 1 AND A.strains_code
		 * = 0 AND B.data_time_type_code = 1 AND B.area_latitude_type_code = 4
		 * AND B.audit_status_code = 1 AND B.region_id = '530723' AND
		 * SUBSTR(B.start_time, 1, 4) = 2018 ORDER BY startTime DESC) t0 GROUP
		 * BY TIME
		 */
		
		selectBuffer.append(" SELECT t0.* FROM																						");
		selectBuffer.append(" (SELECT 																								");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																		");
		selectBuffer.append(" B.start_time AS startTime ,																			");
		selectBuffer.append(" B.`data_sources` AS dataSources,																		");
		
//		CASE WHEN t1.personCount IS NULL THEN 0 ELSE t1.personCount END AS personCount	
//		selectBuffer.append(" A.`grow_area_unit` AS areaSum,																		");
//		selectBuffer.append(" A.fruit_area_unit AS fruitAreaSum,																	");
//		selectBuffer.append(" A.`product_total_unit` AS productSum,																	");
//		selectBuffer.append(" A.`output_value_unit` AS outputSum,																	");
//		selectBuffer.append(" A.`peasant_count` AS peasantCount																		");
		
		selectBuffer.append(" CASE WHEN A.`grow_area_unit` IS NULL THEN 0 ELSE A.`grow_area_unit` END AS areaSum,					");
		selectBuffer.append(" CASE WHEN A.`fruit_area_unit` IS NULL THEN 0 ELSE A.`fruit_area_unit` END AS fruitAreaSum,			");
		selectBuffer.append(" CASE WHEN A.`product_total_unit` IS NULL THEN 0 ELSE A.`product_total_unit` END AS productSum,		");
		selectBuffer.append(" CASE WHEN A.`output_value_unit` IS NULL THEN 0 ELSE A.`output_value_unit` END AS outputSum,			");
		selectBuffer.append(" CASE WHEN A.`peasant_count` IS NULL THEN 0 ELSE A.`peasant_count` END AS peasantCount					");
		
		selectBuffer.append(" FROM																									");
		selectBuffer.append(" da_grow_yield A,																						");
		selectBuffer.append(" da_common_field B 																					");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																		");
		selectBuffer.append(" AND A.crop_type_code = 1 																				");
		selectBuffer.append(" AND A.strains_code =  																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(breed)) {
			selectBuffer.append(breed);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.data_time_type_code = 1 																		");
//		selectBuffer.append(" AND B.area_latitude_type_code = 4 																	");
		selectBuffer.append(" AND B.audit_status_code = 1 																			");
		selectBuffer.append(" AND B.region_id =																						");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																		");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC) t0 GROUP BY time																");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);
		return resultModel;
	}

	
	/**
	 * 查询某年某个品种芒果的种植数据，包括种植面积、挂果面积、产量、产值和种植户数量
	 * @param year
	 * @param regionId
	 * @return
	 */
	public ResultModel getMangoSummationInfosum(String year, String regionId, String breed) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT t1.*,t2.saleSum FROM (																			");
		
		selectBuffer.append(" SELECT t0.* FROM																						");
		selectBuffer.append(" (SELECT 																								");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																		");
		selectBuffer.append(" B.start_time AS startTime ,																			");
		selectBuffer.append(" B.`data_sources` AS dataSources,																		");
		selectBuffer.append(" CASE WHEN A.`grow_area_unit` IS NULL THEN 0 ELSE A.`grow_area_unit` END AS areaSum,					");
		selectBuffer.append(" CASE WHEN A.`fruit_area_unit` IS NULL THEN 0 ELSE A.`fruit_area_unit` END AS fruitAreaSum,			");
		selectBuffer.append(" CASE WHEN A.`product_total_unit` IS NULL THEN 0 ELSE A.`product_total_unit` END AS productSum,		");
		selectBuffer.append(" CASE WHEN A.`output_value_unit` IS NULL THEN 0 ELSE A.`output_value_unit` END AS outputSum,			");
		selectBuffer.append(" CASE WHEN A.`peasant_count` IS NULL THEN 0 ELSE A.`peasant_count` END AS peasantCount					");
		selectBuffer.append(" FROM																									");
		selectBuffer.append(" da_grow_yield A,																						");
		selectBuffer.append(" da_common_field B 																					");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																		");
		selectBuffer.append(" AND A.crop_type_code = 1 																				");
		selectBuffer.append(" AND A.strains_code =  																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(breed)) {
			selectBuffer.append(breed);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.data_time_type_code = 1 																		");
//		selectBuffer.append(" AND B.area_latitude_type_code = 4 																	");
		selectBuffer.append(" AND B.audit_status_code = 1 																			");
		selectBuffer.append(" AND B.region_id =																						");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																		");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC) t0 GROUP BY time																");

		selectBuffer.append(" ) t1,(SELECT t0.time,sum(t0.saleSum) saleSum FROM (SELECT SUBSTR(B.start_time, 1, 4) time,B.start_time AS startTime,CASE ");
		selectBuffer.append(" WHEN A.`sale_amount_unit` IS NULL THEN 0 ELSE A.`sale_amount_unit` END AS saleSum FROM da_sale_fresh A, ");
		selectBuffer.append(" da_common_field B WHERE A.common_field_id = B.id AND A.crop_type_code = 1 AND A.strains_code = '0' ");
		selectBuffer.append(" AND B.data_time_type_code = 1 AND B.audit_status_code = 1 AND B.region_id = ");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																		");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC) t0 GROUP BY time) t2 where t1.time = t2.time									");
		

		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);
		return resultModel;
	}
	
	@ApiOperation(value = "查询种植户数量", notes = "查询种植户数量")
	@RequestMapping(value = "/getPeasantCount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPeasantCount() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionId = "";// 地区id

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
			}
		}

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = null;
		ResultModel yearListModel = getYearInfo();
		Object yearListObj = yearListModel.getData();
		List<Map<String, Object>> yearList = null;
		if (yearListObj != null) {
			resultMap = new HashMap<String, Object>();
			yearList = (List<Map<String, Object>>) yearListObj;
			ResultModel tempResultModel = new ResultModel();
			for (Map<String, Object> map : yearList) {
				if (map.containsKey("year")) {
					String year = (String) map.get("year");
					tempResultModel = getPerYearPeasantCount(year, regionId);
					Object tempObj = tempResultModel.getData();
					List<Map<String, Object>> tempList = null;
					Map<String, Object> tempResultMap = new HashMap<String, Object>();
					if (tempObj != null) {
						tempList = (List<Map<String, Object>>) tempObj;
						if (tempList != null && tempList.size() >= 1) {
							tempResultMap = tempList.get(0);
							if (tempResultMap.containsKey("personCount")) {
								BigDecimal personCount =  (BigDecimal) tempResultMap.get("personCount");
								resultMap.put(year, personCount);
							}
						}
					}
				}
			}
		}
		resultList.add(resultMap);
		yearListModel.setData(resultList);
		// resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		return yearListModel;
	}

	public ResultModel getPerYearPeasantCount(String year, String regionId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT t0.year,t1.regionId,																			");
		selectBuffer.append(" CASE WHEN t1.personCount IS NULL THEN 0 ELSE t1.personCount END AS personCount						");
		selectBuffer.append(" FROM 																									");
		selectBuffer.append(" (SELECT * FROM past_5_year_view WHERE YEAR <> (SELECT MAX(YEAR) FROM past_5_year_view) AND YEAR =		");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ) t0 LEFT JOIN 																						");
		selectBuffer.append(" (SELECT SUBSTR(B.start_time, 1, 4) YEAR,B.region_id AS regionId,										");
		selectBuffer.append(" CASE WHEN A.`peasant_count` IS NULL THEN 0 ELSE A.`peasant_count` END AS personCount					");
		selectBuffer.append(" FROM da_grow_yield A,da_common_field B WHERE A.common_field_id = B.id AND A.crop_type_code = 1		");
		selectBuffer.append(" AND A.strains_code = '0' AND B.data_time_type_code = 1 AND B.audit_status_code = 1 AND B.region_id = 	");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ) t1 ON t0.year=t1.YEAR																	");

		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);
		return resultModel;
	}
	
	@ApiOperation(value = "查询种植面积、产量和产值", notes = "查询有机认证、绿色认证及总的种植面积、产量和产值")
	@RequestMapping(value = "/getGrowAreaSum", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getGrowAreaSum() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionId = "";// 地区id
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
			}
		}

		// 获取时间列表
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "year");// 视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "5");
		List<String> timesList = TimesView.getTimesView(map);

		// 总数据
		Map<String, Object> resultMap = new HashMap<String, Object>();

		// x轴数据
		List xDataList = new ArrayList();
		xDataList.addAll(timesList);
		// 面积数据
		Map<String, Object> areaMap = new HashMap<String, Object>();
		List totalAreaList = new ArrayList();
		List greenAreaList = new ArrayList();
		List organicAreaList = new ArrayList();
		// 产量数据
		Map<String, Object> productMap = new HashMap<String, Object>();
		List totalProductList = new ArrayList();
		List greenProductList = new ArrayList();
		List organicProductList = new ArrayList();
		// 产值数据
		Map<String, Object> outputMap = new HashMap<String, Object>();
		List totalOutputList = new ArrayList();
		List greenOutputList = new ArrayList();
		List organicOutputList = new ArrayList();

		Map<String, Object> tempTotalMap = null;
		// 获取总的种植面积、产量和产值
		for (String time : timesList) {
			ResultModel perYearGrowModel = getPerYearGrowAreaSum(time, regionId);
			Object totalObj = perYearGrowModel.getData();
			List<Map<String, Object>> totalList = null;
			if (totalObj != null) {
				totalList = (List<Map<String, Object>>) totalObj;
			}
			if (totalList != null && totalList.size() >= 1) {
				tempTotalMap = totalList.get(0);
				if (tempTotalMap.containsKey("areaSum")) {
					BigDecimal areaSum = (BigDecimal) tempTotalMap.get("areaSum");
					totalAreaList.add(areaSum);
				}
				if (tempTotalMap.containsKey("productSum")) {
					BigDecimal productSum = (BigDecimal) tempTotalMap.get("productSum");
					totalProductList.add(productSum);
				}
				if (tempTotalMap.containsKey("outputSum")) {
					BigDecimal outputSum = (BigDecimal) tempTotalMap.get("outputSum");
					totalOutputList.add(outputSum);
				}
			} else {
				totalAreaList.add(new BigDecimal(0));
				totalProductList.add(new BigDecimal(0));
				totalOutputList.add(new BigDecimal(0));
			}
		}

		Map<String, Object> tempOrganicMap = null;
		// 获取有机认证的面积、产量和产值
		for (String time : timesList) {
			ResultModel perYearOrganicGrowModel = getOrganicGreenGrowArea(time, regionId, ORGANIC_IDENTIFICATION);
			Object organicObj = perYearOrganicGrowModel.getData();
			List<Map<String, Object>> organicList = null;
			if (organicObj != null) {
				organicList = (List<Map<String, Object>>) organicObj;
			}
			if (organicList != null && organicList.size() >= 1) {
				tempOrganicMap = organicList.get(0);
				if (tempOrganicMap.containsKey("areaSum")) {
					BigDecimal areaSum = (BigDecimal) tempOrganicMap.get("areaSum");
					organicAreaList.add(areaSum);
				}
				if (tempOrganicMap.containsKey("productSum")) {
					BigDecimal productSum = (BigDecimal) tempOrganicMap.get("productSum");
					organicProductList.add(productSum);
				}
			} else {
				organicAreaList.add(new BigDecimal(0));
				organicProductList.add(new BigDecimal(0));
			}
		}

		Map<String, Object> tempGreenMap = null;
		// 获取绿色认证的面积、产量和产值
		for (String time : timesList) {
			ResultModel perYearGreenGrowModel = getOrganicGreenGrowArea(time, regionId, GREEN_IDENTIFICATION);
			Object greenObj = perYearGreenGrowModel.getData();
			List<Map<String, Object>> greenList = null;
			if (greenObj != null) {
				greenList = (List<Map<String, Object>>) greenObj;
			}
			if (greenList != null && greenList.size() >= 1) {
				tempGreenMap = greenList.get(0);
				if (tempGreenMap.containsKey("areaSum")) {
					BigDecimal areaSum = (BigDecimal) tempGreenMap.get("areaSum");
					greenAreaList.add(areaSum);
				}
				if (tempGreenMap.containsKey("productSum")) {
					BigDecimal productSum = (BigDecimal) tempGreenMap.get("productSum");
					greenProductList.add(productSum);
				}
			} else {
				greenAreaList.add(new BigDecimal(0));
				greenProductList.add(new BigDecimal(0));
			}
		}

		resultMap.put("xData", xDataList);
		// 面积数据
		areaMap.put("totalArea", totalAreaList);
		areaMap.put("greenArea", greenAreaList);
		areaMap.put("organic", organicAreaList);
		resultMap.put("areas", areaMap);
		// 产量数据
		productMap.put("totalProduct", totalProductList);
		productMap.put("greenProduct", greenProductList);
		productMap.put("organicProduct", organicProductList);
		resultMap.put("products", productMap);
		// 产值数据
		outputMap.put("totalOutput", totalOutputList);
		outputMap.put("greenOutput", greenOutputList);
		outputMap.put("organicOutput", organicOutputList);
		resultMap.put("outputs", outputMap);

		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	/**
	 * 查询某一年度华坪的总种植面积、总产量 、产值  。
	 * @return
	 */
	@ApiOperation(value = "查询某一年度华坪的总种植面积、总产量 、产值", notes = "查询某一年度华坪的总种植面积、总产量 、产值")
	@RequestMapping(value = "/getGrowSumInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getGrowSumInfo() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String year = "";// 年份id
		String regionId = "";// 地区id

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
		
		Map<String, Object> modelMap = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();

		BigDecimal areaSum = new BigDecimal(0);
		BigDecimal productSum = new BigDecimal(0);
		BigDecimal outputSum = new BigDecimal(0);

		ResultModel perYearGrowModel = getPerYearGrowAreaSum(year, regionId);
		Object totalObj = perYearGrowModel.getData();
		List<Map<String, Object>> totalList = null;
		if (totalObj != null) {
			totalList = (List<Map<String, Object>>) totalObj;
		}
		if (totalList != null && totalList.size() >= 1) {
			modelMap = totalList.get(0);
			if (modelMap.containsKey("areaSum")) {
				areaSum = (BigDecimal) modelMap.get("areaSum");
			}
			if (modelMap.containsKey("productSum")) {
				productSum = (BigDecimal) modelMap.get("productSum");
			}
			if (modelMap.containsKey("outputSum")) {
				outputSum = (BigDecimal) modelMap.get("outputSum");
			}
		}
		
			resultMap.put("year", year);
			resultMap.put("areaSum",areaSum);
			resultMap.put("productSum",productSum);
			resultMap.put("outputSum",outputSum);
		
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
//	/**
//	 * 查询某一年度华坪的总产量、总销量   20180808改为从mf_product_sale表查询，与产品确认该表只有华坪数据。
//	 * @return
//	 */
//	@ApiOperation(value = "查询某一年度华坪的总产量、总销量", notes = "查询某一年度华坪的总产量、总销量")
//	@RequestMapping(value = "/getGrowSumInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultModel getGrowSumInfo() {
//		ResultModel resultModel = new ResultModel();
//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//
//		String year = "";// 年份id
//		String regionId = "";// 地区id
//
//		if (!StringUtils.isBlank(jsonData)) {
//			JSONObject jsonObject = JSONObject.fromObject(jsonData);
//			if (jsonObject.containsKey("entityRelated")) {
//				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
//				if (entityRelatedObject.containsKey("year") && StringUtils.isNotBlank(entityRelatedObject.getString("year")))
//					year = entityRelatedObject.getString("year");
//				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
//					regionId = entityRelatedObject.getString("regionId");
//			}
//		}
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		StringBuffer selectBuffer = new StringBuffer();
//		selectBuffer.append("	SELECT                                                                    ");
//		selectBuffer.append("		IFNULL(A.yield_actual,0) yieldActual,                                 ");
//		selectBuffer.append("		IFNULL(A.sale_actual,0) saleActual,                                   ");
//		selectBuffer.append("		A.date_time time                                                      ");
//		selectBuffer.append("	FROM                                                                      ");
//		selectBuffer.append("		mf_product_sale A where  (A.date_time+1) = '"+year+"'                 ");
//
//		map.put("Sql", selectBuffer.toString());
//		resultModel = daGrowYieldUntBll.getListBySQL(map);
//		List<Map<String, Object>> dataList  = (List<Map<String, Object>>) resultModel.getData();
//
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		for(Map<String, Object> modelMap:dataList){
//				resultMap.put("year", modelMap.get("time"));
//				resultMap.put("productSum", modelMap.get("yieldActual"));
//				resultMap.put("saleSum", modelMap.get("saleActual"));
//		}
//		
//		resultModel.setData(resultMap);
//		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
//		return resultModel;
//	}
	
//	/**
//	 * 查询某一年度华坪、全国的总产量
//	 * @return
//	 */
//	@ApiOperation(value = "查询某一年度华坪、全国的总产量", notes = "查询某一年度华坪、全国的总产量")
//	@RequestMapping(value = "/getGrowSumInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResultModel getGrowSumInfo() {
//		ResultModel resultModel = new ResultModel();
//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//
////		String year = "";// 年份id
//		String regionId = "";// 地区id
//
//		// 获取时间列表
//		Map<String, String> timeMap = new HashMap<String, String>();
//		timeMap.put("viewName", "year");// 视图名，年year，月month，日date，小时hour（默认为年）
//		timeMap.put("hasCurrent", "false");
//		timeMap.put("pastNum", "5");
//		List<String> timeList = TimesView.getTimesView(timeMap);
//		Collections.reverse(timeList);
//		
//		if (!StringUtils.isBlank(jsonData)) {
//			JSONObject jsonObject = JSONObject.fromObject(jsonData);
//			if (jsonObject.containsKey("entityRelated")) {
//				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
//				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
//					regionId = entityRelatedObject.getString("regionId");
//			}
//		}
//		
//		Map<String, Object> resultMap = new HashMap<String, Object>();
//		for(String year:timeList) {
//			resultModel = getMangoSummationInfosum(year, regionId, "0");
//			
//			BigDecimal productSum = new BigDecimal("0");
//			BigDecimal outputSum = new BigDecimal("0");
//			
//			Object obj = resultModel.getData();
//			List<Map<String, Object>> list = null;
//			if (obj != null) {
//				list = (List<Map<String, Object>>) obj;
//				if (list.size() >= 1) {
//					Map<String, Object> map = list.get(0);
//					if (map.containsKey("productSum")) {
//						productSum = (BigDecimal) map.get("productSum");
//						outputSum = (BigDecimal) map.get("saleSum");
//						if (productSum.compareTo(BigDecimal.ZERO) != 0) {
//							resultMap.put("year", year);
//							resultMap.put("productSum", productSum);
//							resultMap.put("saleSum", outputSum);
//							break;
//						} else {
//							resultMap.put("productSum", new BigDecimal("0"));
//							resultMap.put("saleSum", new BigDecimal("0"));
//						}
//					}
//				} 
//			}
//		}
//
//		resultModel.setData(resultMap);
//		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
//		return resultModel;
//	}

	/**
	 * 查询每年总的种植面积、产量和产值
	 * @param year
	 * @param regionId
	 * @return
	 */
	public ResultModel getPerYearGrowAreaSum(String year, String regionId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT SUBSTR(B.start_time, 1, 4) time, MAX(B.start_time) AS
		 * startTime, CASE WHEN A.grow_area_unit IS NULL THEN 0 ELSE
		 * A.grow_area_unit END AS areaSum, CASE WHEN A.product_total_unit IS
		 * NULL THEN 0 ELSE A.product_total_unit END AS productSum, CASE WHEN
		 * A.output_value_unit IS NULL THEN 0 ELSE A.output_value_unit END AS
		 * outputSum FROM da_grow_yield A, da_common_field B WHERE
		 * A.common_field_id = B.id AND A.crop_type_code = 1 AND
		 * A.`strains_code` = 0 AND B.data_time_type_code = 1 AND
		 * B.area_latitude_type_code = 4 AND B.audit_status_code = 1 AND
		 * B.region_id = '530723' AND SUBSTR(B.start_time, 1, 4) = 2018 GROUP BY
		 * TIME
		 */

		/*
		 * SELECT t0.time, t0.startTime, CASE WHEN t0.areaSum IS NULL THEN 0
		 * ELSE t0.areaSum END AS areaSum, CASE WHEN t0.productSum IS NULL THEN
		 * 0 ELSE t0.productSum END AS productSum, CASE WHEN t0.outputSum IS
		 * NULL THEN 0 ELSE t0.outputSum END AS outputSum FROM (SELECT
		 * SUBSTR(B.start_time, 1, 4) time, B.start_time AS startTime,
		 * A.grow_area_unit AS areaSum, A.product_total_unit AS productSum,
		 * A.output_value_unit AS outputSum FROM da_grow_yield A,
		 * da_common_field B WHERE A.common_field_id = B.id AND A.crop_type_code
		 * = 1 AND A.`strains_code` = 0 AND B.data_time_type_code = 1 AND
		 * B.area_latitude_type_code = 4 AND B.audit_status_code = 1 AND
		 * B.region_id = '530723' AND SUBSTR(B.start_time, 1, 4) = 2018 ORDER BY
		 * startTime DESC) t0 GROUP BY TIME
		 */

		selectBuffer.append(" SELECT																										");
		selectBuffer.append(" t0.time,																										");
		selectBuffer.append(" t0.startTime,																									");
		selectBuffer.append(" CASE WHEN t0.areaSum IS NULL THEN 0 ELSE t0.areaSum END AS areaSum,											");
		selectBuffer.append(" CASE WHEN t0.productSum IS NULL THEN 0 ELSE t0.productSum END AS productSum,									");
		selectBuffer.append(" CASE WHEN t0.outputSum IS NULL THEN 0 ELSE t0.outputSum END AS outputSum 										");
		selectBuffer.append(" FROM																											");
		selectBuffer.append(" (SELECT 																										");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																				");
		selectBuffer.append(" B.start_time AS startTime,																					");
		selectBuffer.append(" A.grow_area_unit AS areaSum,																					");
		selectBuffer.append(" A.product_total_unit AS productSum,																			");
		selectBuffer.append(" A.output_value_unit AS outputSum 																				");
		selectBuffer.append(" FROM																											");
		selectBuffer.append(" da_grow_yield A,																								");
		selectBuffer.append(" da_common_field B 																							");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																				");
		selectBuffer.append(" AND A.crop_type_code = 1 																						");
		
		selectBuffer.append(" AND A.`strains_code` = 0 		");
		//20180814-01 客户问题跟踪表   固定展示 红象牙、圣心芒、凯特芒、金煌芒、爱文芒、澳芒、热农芒和鹰嘴芒这八个品种
//		selectBuffer.append(" AND A.`strains_code` in(10,3,4,9,7,1,2,11) 		");
		
		selectBuffer.append(" AND B.data_time_type_code = 1 																				");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																			");
		selectBuffer.append(" AND B.audit_status_code = 1 																					");
		selectBuffer.append(" AND B.region_id =																								");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC) t0 																					");
		selectBuffer.append(" GROUP BY time																									");
		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);
		return resultModel;
	}

	/**
	 * 查询每年有机产品认证和绿色认证的种植面积和产量
	 * @param year
	 * @param regionId
	 * @return
	 */
	public ResultModel getOrganicGreenGrowArea(String year, String regionId, String identificationType) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT SUBSTR(B.start_time, 1, 4) TIME, MAX(B.start_time) AS
		 * startTime, CASE WHEN A.identification_area_unit IS NULL THEN 0 ELSE
		 * A.identification_area_unit END AS areaSum, CASE WHEN
		 * A.produce_summation_unit IS NULL THEN 0 ELSE A.produce_summation_unit
		 * END AS productSum FROM da_identification_info A, da_common_field B
		 * WHERE A.common_field_id = B.id AND A.crop_type_code = 1 AND
		 * A.`strains_code` = 0 AND A.identification_type_code = 3 AND
		 * B.data_time_type_code = 1 AND B.area_latitude_type_code = 4 AND
		 * B.audit_status_code = 1 AND B.region_id = '530723' AND
		 * SUBSTR(B.start_time, 1, 4) = 2018 GROUP BY TIME
		 */

		/*
		 * SELECT t0.time, t0.startTime, CASE WHEN t0.areaSum IS NULL THEN 0
		 * ELSE t0.areaSum END AS areaSum, CASE WHEN t0.productSum IS NULL THEN
		 * 0 ELSE t0.productSum END AS productSum FROM (SELECT
		 * SUBSTR(B.start_time, 1, 4) TIME, B.start_time AS startTime,
		 * A.identification_area_unit AS areaSum, A.produce_summation_unit AS
		 * productSum FROM da_identification_info A, da_common_field B WHERE
		 * A.common_field_id = B.id AND A.crop_type_code = 1 AND
		 * A.`strains_code` = 0 AND A.identification_type_code = 3 AND
		 * B.data_time_type_code = 1 AND B.area_latitude_type_code = 4 AND
		 * B.audit_status_code = 1 AND B.region_id = '530723' AND
		 * SUBSTR(B.start_time, 1, 4) = 2018 ORDER BY startTime DESC) t0 GROUP
		 * BY TIME
		 */

		selectBuffer.append(" SELECT 																										");
		selectBuffer.append(" t0.time,																										");
		selectBuffer.append(" t0.startTime,																									");
		selectBuffer.append(" CASE WHEN t0.areaSum IS NULL THEN 0 ELSE t0.areaSum END AS areaSum,											");
		selectBuffer.append(" CASE WHEN t0.productSum IS NULL THEN 0 ELSE t0.productSum END AS productSum									");
		selectBuffer.append(" FROM																											");
		selectBuffer.append(" (SELECT 																										");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																				");
		selectBuffer.append(" B.start_time AS startTime,																					");
		selectBuffer.append(" A.identification_area_unit AS areaSum,																		");
		selectBuffer.append(" A.produce_summation_unit AS productSum																		");
		selectBuffer.append(" FROM																											");
		selectBuffer.append(" da_identification_info A,																						");
		selectBuffer.append(" da_common_field B 																							");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																				");
		selectBuffer.append(" AND A.crop_type_code = 1 																						");
		
		selectBuffer.append(" AND A.`strains_code` = 0 												");
		//20180814-01 客户问题跟踪表   固定展示 红象牙、圣心芒、凯特芒、金煌芒、爱文芒、澳芒、热农芒和鹰嘴芒这八个品种  
//		selectBuffer.append(" AND A.`strains_code` in(10,3,4,9,7,1,2,11)   ");
		
		selectBuffer.append(" AND A.identification_type_code = 																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(identificationType)) {
			selectBuffer.append(identificationType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.data_time_type_code = 1 																				");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																			");
		selectBuffer.append(" AND B.audit_status_code = 1 																					");
		selectBuffer.append(" AND B.region_id =																								");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC) t0 																					");
		selectBuffer.append(" GROUP BY time																									");

		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);
		return resultModel;
	}

	@ApiOperation(value = "查询各乡镇芒果种植情况", notes = "查询各乡镇芒果种植情况")
	@RequestMapping(value = "/getMangoGrowDetailsByRegion1", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoGrowDetailsByRegion1() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String year = "";// 年份
		String regionId = "";// 地区id
		String breed = "";// 芒果品种id

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("year") && StringUtils.isNotBlank(entityRelatedObject.getString("year")))
					year = entityRelatedObject.getString("year");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("breed") && StringUtils.isNotBlank(entityRelatedObject.getString("breed")))
					breed = entityRelatedObject.getString("breed");
			}
		}

		resultModel = getMangoGrowDetailInfo(year, regionId, breed);
		// 查询各乡镇的芒果种植品种
		Map<String, Object> breedResultMap = getBreedByRegion(year, regionId);

		Object resultObj = resultModel.getData();
		List<Map<String, Object>> resultList = null;
		if (resultObj != null) {
			resultList = (List<Map<String, Object>>) resultObj;
			for (Map<String, Object> map : resultList) {
				if (map.containsKey("YEAR")) {
					map.put("breeds", breedResultMap.get(map.get("YEAR")));
				}
			}
		}
		resultModel.setData(resultList);
		return resultModel;
	}

	private Map<String, Object> getBreedByRegion(String year, String regionId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		ResultModel resultModel = new ResultModel();
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");// 视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "4");
		pmap.put("afterNum", "1");
		List<String> timesList = TimesView.getTimesView(pmap);
		for (int i = 0; i < timesList.size(); i++) {
			String strainsText = "";
			resultModel = getGrowBreedByRegion(timesList.get(i), regionId);
			Object obj = resultModel.getData();
			List<Map<String, Object>> tempList = null;
			if (obj != null) {
				tempList = (List<Map<String, Object>>) obj;
				for (Map<String, Object> map : tempList) {
					if (map.containsKey("strainsText")) {
						String tempStrainsText = (String) map.get("strainsText");
						if (!strainsText.contains(tempStrainsText)) {
							strainsText += tempStrainsText + "、";
						}
					}
				}
			}
			if (StringUtils.isNotBlank(strainsText)) {
				resultMap.put(timesList.get(i), strainsText.substring(0, strainsText.length() - 1));
			} else {
				resultMap.put(timesList.get(i), "");
			}
		}
		return resultMap;
	}

	private ResultModel getGrowBreedByRegion(String year, String regionId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT SUBSTR(B.start_time,1,4) YEAR, B.start_time AS startTime,
		 * A.strains_code AS strainsCode, A.strains_text AS strainsText FROM
		 * da_grow_yield A,da_common_field B WHERE A.common_field_id = B.id AND
		 * A.crop_type_code = 1 AND SUBSTR(B.start_time,1,4) = '2018' AND
		 * B.area_latitude_type_code = 4 AND B.region_id = '530723'
		 */

		selectBuffer.append(" SELECT SUBSTR(B.start_time,1,4) YEAR,																				");
		selectBuffer.append(" B.start_time AS startTime,																						");
		selectBuffer.append(" A.strains_code AS strainsCode,																					");
		selectBuffer.append(" A.strains_text AS strainsText																						");
		selectBuffer.append(" FROM da_grow_yield A,da_common_field B 																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																					");
		selectBuffer.append(" AND A.crop_type_code = 1																							");
		selectBuffer.append(" AND SUBSTR(B.start_time,1,4) =																					");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.region_id =																									");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "查询芒果种植情况", notes = "查询各品种芒果种植情况")
	@RequestMapping(value = "/getMangoGrowDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoGrowDetails() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionId = "";// 地区id
		String breed = "";// 芒果品种id

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("breed") && StringUtils.isNotBlank(entityRelatedObject.getString("breed")))
					breed = entityRelatedObject.getString("breed");
			}
		}

		Map<String, String> timesMap = new HashMap<String, String>();
		timesMap.put("viewName", "year");// 视图名，年year，月month，日date，小时hour（默认为年）
		timesMap.put("hasCurrent", "false");
		timesMap.put("pastNum", "2");
		List<String> timesList = TimesView.getTimesView(timesMap);
		Collections.sort(timesList);

		String lastYear = timesList.get(1);
		String theYearBeforeLast = timesList.get(0);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();

		BigDecimal lastAreaSum = new BigDecimal("0");
		BigDecimal lastFruitAreaSum = new BigDecimal("0");
		BigDecimal lastPeasantCount = new BigDecimal("0");
		BigDecimal lastProductSum = new BigDecimal("0");
		BigDecimal lastOutputSum = new BigDecimal("0");
		BigDecimal lastOrganicAreaSum = new BigDecimal("0");
		BigDecimal lastGreenAreaSum = new BigDecimal("0");

		// 查询去年某个品种芒果的种植数据

		// 种植面积、挂果面积、总产量、总产值、种植户数量
		ResultModel lastModel_1 = getMangoSummationInfo(lastYear, regionId, breed);
		// 有机认证面积
		ResultModel lastModel_2 = getPerOrganicGreenGrowArea(lastYear, regionId, ORGANIC_IDENTIFICATION, breed);
		// 绿色认证面积
		ResultModel lastModel_3 = getPerOrganicGreenGrowArea(lastYear, regionId, GREEN_IDENTIFICATION, breed);

		Map<String, Object> lastModelMap_1 = getMapFromModel(lastModel_1);
		if (lastModelMap_1 != null) {
			if (lastModelMap_1.containsKey("areaSum")) {
				lastAreaSum = (BigDecimal) lastModelMap_1.get("areaSum");
			}
			if (lastModelMap_1.containsKey("fruitAreaSum")) {
				lastFruitAreaSum = (BigDecimal) lastModelMap_1.get("fruitAreaSum");
			}
			if (lastModelMap_1.containsKey("productSum")) {
				lastProductSum = (BigDecimal) lastModelMap_1.get("productSum");
			}
			if (lastModelMap_1.containsKey("outputSum")) {
				lastOutputSum = (BigDecimal) lastModelMap_1.get("outputSum");
			}
			if (lastModelMap_1.containsKey("peasantCount")) {
				lastPeasantCount = (BigDecimal) lastModelMap_1.get("peasantCount");
			}
		}
		Map<String, Object> lastModelMap_2 = getMapFromModel(lastModel_2);
		if (lastModelMap_2 != null) {
			if (lastModelMap_2.containsKey("identAreaSum")) {
				lastOrganicAreaSum = (BigDecimal) lastModelMap_2.get("identAreaSum");
			}
		}
		Map<String, Object> lastModelMap_3 = getMapFromModel(lastModel_3);
		if (lastModelMap_3 != null) {
			if (lastModelMap_3.containsKey("identAreaSum")) {
				lastGreenAreaSum = (BigDecimal) lastModelMap_3.get("identAreaSum");
			}
		}

		BigDecimal beforeLastAreaSum = new BigDecimal("0");
		BigDecimal beforeLastFruitAreaSum = new BigDecimal("0");
		BigDecimal beforeLastPeasantCount = new BigDecimal("0");
		BigDecimal beforeLastProductSum = new BigDecimal("0");
		BigDecimal beforeLastOutputSum = new BigDecimal("0");
		BigDecimal beforeLastOrganicAreaSum = new BigDecimal("0");
		BigDecimal beforeLastGreenAreaSum = new BigDecimal("0");

		// 查询前年某个品种芒果的种植数据

		// 种植面积、挂果面积、总产量、种植户数量
		ResultModel beforeLastModel_1 = getMangoSummationInfo(theYearBeforeLast, regionId, breed);
		// 有机认证面积
		ResultModel beforeLastModel_2 = getPerOrganicGreenGrowArea(theYearBeforeLast, regionId, ORGANIC_IDENTIFICATION, breed);
		// 绿色认证面积
		ResultModel beforeLastModel_3 = getPerOrganicGreenGrowArea(theYearBeforeLast, regionId, GREEN_IDENTIFICATION, breed);

		Map<String, Object> beforeLastModelMap_1 = getMapFromModel(beforeLastModel_1);
		if (beforeLastModelMap_1 != null) {
			if (beforeLastModelMap_1.containsKey("areaSum")) {
				beforeLastAreaSum = (BigDecimal) beforeLastModelMap_1.get("areaSum");
			}
			if (beforeLastModelMap_1.containsKey("fruitAreaSum")) {
				beforeLastFruitAreaSum = (BigDecimal) beforeLastModelMap_1.get("fruitAreaSum");
			}
			if (beforeLastModelMap_1.containsKey("productSum")) {
				beforeLastProductSum = (BigDecimal) beforeLastModelMap_1.get("productSum");
			}
			if (beforeLastModelMap_1.containsKey("outputSum")) {
				beforeLastOutputSum = (BigDecimal) beforeLastModelMap_1.get("outputSum");
			}
			if (beforeLastModelMap_1.containsKey("peasantCount")) {
				beforeLastPeasantCount = (BigDecimal) beforeLastModelMap_1.get("peasantCount");
			}
		}
		Map<String, Object> beforeLastModelMap_2 = getMapFromModel(beforeLastModel_2);
		if (beforeLastModelMap_2 != null) {
			if (beforeLastModelMap_2.containsKey("identAreaSum")) {
				beforeLastOrganicAreaSum = (BigDecimal) beforeLastModelMap_2.get("identAreaSum");
			}
		}
		Map<String, Object> beforeLastModelMap_3 = getMapFromModel(beforeLastModel_3);
		if (beforeLastModelMap_3 != null) {
			if (beforeLastModelMap_3.containsKey("identAreaSum")) {
				beforeLastGreenAreaSum = (BigDecimal) beforeLastModelMap_3.get("identAreaSum");
			}
		}

		// 计算增长的数量
		BigDecimal subtractAreaSum = lastAreaSum.subtract(beforeLastAreaSum);
		BigDecimal subtractFruitAreaSum = lastFruitAreaSum.subtract(beforeLastFruitAreaSum);
		BigDecimal subtractProductSum = lastProductSum.subtract(beforeLastProductSum);
		BigDecimal subtractOutputSum = lastOutputSum.subtract(beforeLastOutputSum);
		BigDecimal subtractPeasantCount = lastPeasantCount.subtract(beforeLastPeasantCount);
		BigDecimal subtractOrganicAreaSum = lastOrganicAreaSum.subtract(beforeLastOrganicAreaSum);
		BigDecimal subtractGreenAreaSum = lastGreenAreaSum.subtract(beforeLastGreenAreaSum);

		// 计算增长百分比
		String areaSumProportion = getProportion(lastAreaSum, beforeLastAreaSum);
		String fruitAreaSumProportion = getProportion(lastFruitAreaSum, beforeLastFruitAreaSum);
		String productSumProportion = getProportion(lastProductSum, beforeLastProductSum);
		String outputSumProportion = getProportion(lastOutputSum, beforeLastOutputSum);
		String peasantCountProportion = getProportion(lastPeasantCount, beforeLastPeasantCount);
		String organicAreaSumProportion = getProportion(lastOrganicAreaSum, beforeLastOrganicAreaSum);
		String greenAreaSumProportion = getProportion(lastGreenAreaSum, beforeLastGreenAreaSum);

		dataMap.put("areaSum", lastAreaSum);
		dataMap.put("fruitAreaSum", lastFruitAreaSum);
		dataMap.put("productSum", lastProductSum);
		dataMap.put("outputSum", lastOutputSum);
		dataMap.put("peasantCount", lastPeasantCount);
		dataMap.put("organicAreaSum", lastOrganicAreaSum);
		dataMap.put("greenAreaSum", lastGreenAreaSum);

		dataMap.put("subtractAreaSum", subtractAreaSum);
		dataMap.put("subtractFruitAreaSum", subtractFruitAreaSum);
		dataMap.put("subtractProductSum", subtractProductSum);
		dataMap.put("subtractOutputSum", subtractOutputSum);
		dataMap.put("subtractPeasantCount", subtractPeasantCount);
		dataMap.put("subtractOrganicAreaSum", subtractOrganicAreaSum);
		dataMap.put("subtractGreenAreaSum", subtractGreenAreaSum);

		dataMap.put("areaSumProportion", areaSumProportion.substring(0, areaSumProportion.length()-1));
		dataMap.put("fruitAreaSumProportion", fruitAreaSumProportion.substring(0, fruitAreaSumProportion.length()-1));
		dataMap.put("productSumProportion", productSumProportion.substring(0, productSumProportion.length()-1));
		dataMap.put("outputSumProportion", outputSumProportion.substring(0, outputSumProportion.length()-1));
		dataMap.put("peasantCountProportion", peasantCountProportion.substring(0, peasantCountProportion.length()-1));
		dataMap.put("organicAreaSumProportion", organicAreaSumProportion.substring(0, organicAreaSumProportion.length()-1));
		dataMap.put("greenAreaSumProportion", greenAreaSumProportion.substring(0, greenAreaSumProportion.length()-1));

		resultMap.put("info", dataMap);
		resultMap.put("lastYear", lastYear);
		resultMap.put("theYearBeforeLast", theYearBeforeLast);
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		return resultModel;
	}

	private String getProportion(BigDecimal lastSum, BigDecimal beforeLastSum) {
		String tempProportion = "0.00%";
		if(lastSum==null) {
			lastSum = new BigDecimal("0");
		}
		if(beforeLastSum==null) {
			beforeLastSum = new BigDecimal("0");
		}
		BigDecimal subtractData = lastSum.subtract(beforeLastSum);
		if (beforeLastSum.compareTo(BigDecimal.ZERO) != 0) {
			tempProportion = MathUtil.getPercent(subtractData, beforeLastSum, "0.00%");
		}
		return tempProportion;
	}

	private ResultModel getMangoGrowDetailInfo(String year, String regionId, String breed) {
		ResultModel resultModel = new ResultModel();
		// 获取单个品种的种植面积、挂果面积、产量和种植户数量
		ResultModel ordinaryModel = getOrdinaryMangoGrowDetails(regionId, breed);
		// 获取单个品种有机认证的面积
		ResultModel organicModel = getGreenMangoGrowDetails(regionId, breed, ORGANIC_IDENTIFICATION);
		// 获取单个品种绿色认证的面积
		ResultModel greenModel = getGreenMangoGrowDetails(regionId, breed, GREEN_IDENTIFICATION);

		List<Map<String, Object>> ordinaryList = getListFromModel(ordinaryModel);
		List<Map<String, Object>> organicList = getListFromModel(organicModel);
		List<Map<String, Object>> greenList = getListFromModel(greenModel);

		for (int i = 0; i < ordinaryList.size(); i++) {
			Map<String, Object> organicMap = organicList.get(i);
			if (organicMap.containsKey("identificationText")) {
				ordinaryList.get(i).put("organicText", organicMap.get("identificationText"));
				ordinaryList.get(i).put("organicArea", organicMap.get("identificationArea"));
			}
			Map<String, Object> greenMap = greenList.get(i);
			if (greenMap.containsKey("identificationArea")) {
				ordinaryList.get(i).put("greenText", greenMap.get("identificationText"));
				ordinaryList.get(i).put("greenArea", greenMap.get("identificationArea"));
			}
		}
		resultModel.setData(ordinaryList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private List<Map<String, Object>> getListFromModel(ResultModel resultModel) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> objList = null;
		Map<String, Object> objMap = new HashMap<String, Object>();
		if (obj != null)
			objList = (List<Map<String, Object>>) obj;
		return objList;
	}

	private Map<String, Object> getMapFromModel(ResultModel resultModel) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> objList = null;
		Map<String, Object> resultMap = null;
		if (obj != null) {
			objList = (List<Map<String, Object>>) obj;
			if (objList.size() >= 1) {
				resultMap = objList.get(0);
			}
		}
		return resultMap;
	}

	private ResultModel getOrdinaryMangoGrowDetails(String regionId, String breed) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT t0.YEAR, CASE WHEN t1.areaSum IS NULL THEN 0 ELSE t1.areaSum
		 * END AS areaSum, CASE WHEN t1.fruitAreaSum IS NULL THEN 0 ELSE
		 * t1.fruitAreaSum END AS fruitAreaSum, CASE WHEN t1.productSum IS NULL
		 * THEN 0 ELSE t1.productSum END AS productSum, CASE WHEN
		 * t1.peasantCount IS NULL THEN 0 ELSE t1.peasantCount END AS
		 * peasantCount FROM (SELECT * FROM past_5_year_view WHERE YEAR <>
		 * (SELECT MAX(YEAR) FROM past_5_year_view)) t0 LEFT JOIN
		 * 
		 * (SELECT SUBSTR(B.start_time,1,4) YEAR, B.start_time AS startTime,
		 * A.grow_area_unit AS areaSum, A.fruit_area_unit AS fruitAreaSum,
		 * A.product_total_unit AS productSum, A.peasant_count AS peasantCount
		 * FROM da_grow_yield A,da_common_field B WHERE A.common_field_id = B.id
		 * AND A.crop_type_code = 1 AND A.strains_code = 0 AND
		 * B.area_latitude_type_code = 4 AND B.region_id = '530723' GROUP BY
		 * YEAR ORDER BY startTime DESC) t1 ON t0.YEAR = t1.YEAR
		 */

		selectBuffer.append(" SELECT																											");
		selectBuffer.append(" t0.YEAR,																											");
		selectBuffer.append(" CASE WHEN t1.areaSum IS NULL THEN 0 ELSE t1.areaSum END AS areaSum,												");
		selectBuffer.append(" CASE WHEN t1.fruitAreaSum IS NULL THEN 0 ELSE t1.fruitAreaSum END AS fruitAreaSum,								");
		selectBuffer.append(" CASE WHEN t1.productSum IS NULL THEN 0 ELSE t1.productSum END AS productSum,										");
		selectBuffer.append(" CASE WHEN t1.peasantCount IS NULL THEN 0 ELSE t1.peasantCount END AS peasantCount									");
		selectBuffer.append(" FROM																												");
		selectBuffer.append(" (SELECT * FROM past_5_year_view WHERE YEAR <> (SELECT MAX(YEAR) FROM past_5_year_view)) t0						");
		selectBuffer.append(" LEFT JOIN 																										");
		selectBuffer.append(" (SELECT SUBSTR(B.start_time,1,4) YEAR,																			");
		selectBuffer.append(" B.start_time AS startTime,																						");
		selectBuffer.append(" A.grow_area_unit AS areaSum,																						");
		selectBuffer.append(" A.fruit_area_unit AS fruitAreaSum,																				");
		selectBuffer.append(" A.product_total_unit AS productSum,																				");
		selectBuffer.append(" A.peasant_count AS peasantCount																					");
		selectBuffer.append(" FROM da_grow_yield A,da_common_field B 																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																					");
		selectBuffer.append(" AND A.crop_type_code = 1																							");
		selectBuffer.append(" AND A.strains_code =																								");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(breed)) {
			selectBuffer.append(breed);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																				");
		selectBuffer.append(" AND B.region_id =																									");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY YEAR ORDER BY startTime DESC) t1 ON t0.YEAR = t1.YEAR													");

		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}

	private ResultModel getGreenMangoGrowDetails(String regionId, String breed, String identType) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT t0.YEAR, t1.identificationText, CASE WHEN
		 * t1.identificationArea IS NULL THEN 0 ELSE t1.identificationArea END
		 * AS identificationArea FROM (SELECT * FROM past_5_year_view WHERE YEAR
		 * <> (SELECT MAX(YEAR) FROM past_5_year_view)) t0 LEFT JOIN
		 * 
		 * (SELECT SUBSTR(B.start_time,1,4) YEAR, B.start_time AS startTime,
		 * A.identification_type_code AS identificationCode,
		 * A.identification_type_text AS identificationText,
		 * A.identification_area_unit AS identificationArea FROM
		 * da_identification_info A,da_common_field B WHERE A.common_field_id =
		 * B.id AND A.crop_type_code = 1 AND A.strains_code = 1 AND
		 * A.identification_type_code = 2 AND B.area_latitude_type_code = 4 AND
		 * B.region_id = '530723' GROUP BY YEAR ORDER BY startTime DESC) t1 ON
		 * t0.YEAR = t1.YEAR
		 */

		selectBuffer.append(" SELECT																											");
		selectBuffer.append(" t0.YEAR,																											");
		selectBuffer.append(" t1.identificationText,																							");
		selectBuffer.append(" CASE WHEN t1.identificationArea IS NULL THEN 0 ELSE t1.identificationArea END AS identificationArea				");
		selectBuffer.append(" FROM																												");
		selectBuffer.append(" (SELECT * FROM past_5_year_view WHERE YEAR <> (SELECT MAX(YEAR) FROM past_5_year_view)) t0						");
		selectBuffer.append(" LEFT JOIN 																										");
		selectBuffer.append(" (SELECT SUBSTR(B.start_time,1,4) YEAR,																			");
		selectBuffer.append(" B.start_time AS startTime,																						");
		selectBuffer.append(" A.identification_type_code AS identificationCode,																	");
		selectBuffer.append(" A.identification_type_text AS identificationText,																	");
		selectBuffer.append(" A.identification_area_unit AS identificationArea																	");
		selectBuffer.append(" FROM da_identification_info A,da_common_field B 																	");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																					");
		selectBuffer.append(" AND A.crop_type_code = 1																							");
		selectBuffer.append(" AND A.strains_code =																								");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(breed)) {
			selectBuffer.append(breed);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.identification_type_code =																					");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(identType)) {
			selectBuffer.append(identType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.area_latitude_type_code = 4																					");
		selectBuffer.append(" AND B.region_id =																									");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY YEAR ORDER BY startTime DESC) t1 ON t0.YEAR = t1.YEAR													");

		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "查询某年各乡镇芒果种植情况", notes = "查询某年各乡镇芒果种植情况")
	@RequestMapping(value = "/getMangoGrowDetailsByRegion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoGrowDetailsByRegion() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String year = "";// 年份
		String regionId = "";// 地区id

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

		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "year");//视图名，年year，月month，日date，小时hour（默认为年）
		timeMap.put("hasCurrent", "false");
		timeMap.put("pastNum", "1");
		List<String> timeList = TimesView.getTimesView(timeMap);
		if (year.equals("")){
			year = timeList.get(0);
		}
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> resultMap = null;

		// 1、计算出某年度华坪的总种植面积
		BigDecimal areaSum = new BigDecimal("0");
		ResultModel totalModel = getMangoSummationInfo(year, regionId, "0");
		Object totalObj = totalModel.getData();
		List<Map<String, Object>> totalList = null;
		Map<String, Object> totalMap = null;
		if (totalObj != null) {
			totalList = (List<Map<String, Object>>) totalObj;
			if (totalList != null && totalList.size() >= 1) {
				totalMap = totalList.get(0);
				if (totalMap.containsKey("areaSum")) {
					areaSum = (BigDecimal) totalMap.get("areaSum");
				}
			}
		}

		// 2、读取gp_region表中farther_code为530723的数据（即华坪县的下属乡镇）
		ResultModel regionModel = getRegionCodeList(regionId);
		Object regionObj = regionModel.getData();
		List<Map<String, Object>> regionList = null;
		Map<String, Object> regionMap = null;
		if (regionObj != null) {
			regionList = (List<Map<String, Object>>) regionObj;
			if (regionList.size() >= 1) {
				for (Map<String, Object> tempMap : regionList) {
					if (tempMap.containsKey("code")) {
//						BigDecimal growArea = new BigDecimal("0");
						String rCode = (String) tempMap.get("code");
						String rName = "";
						if (tempMap.containsKey("name")) {
							rName = (String) tempMap.get("name");
						}
						// 3、获取某年度各个乡镇的种植面积、产量和种植户数量
						ResultModel perModel = getMangoSummationInfo(year, rCode, "0");
						Object perObj = perModel.getData();
						List<Map<String, Object>> perList = null;
						Map<String, Object> perMap = null;
						if (perObj != null) {
							perList = (List<Map<String, Object>>) perObj;
							if (perList != null && perList.size() >= 1) {
								perMap = perList.get(0);
								perMap.put("regionName", rName);

								// 4、利用1和3的结果计算各个乡镇种植面积的占比
								if (perMap.containsKey("areaSum")) {
									BigDecimal growArea = (BigDecimal) perMap.get("areaSum");
									BigDecimal proportion = growArea.divide(areaSum, 4, BigDecimal.ROUND_HALF_UP);
									DecimalFormat df = new DecimalFormat("0.00%");
									String percent = df.format(proportion);
									perMap.put("growProportion", percent);
								}
							} else {
								perMap = new HashMap<String, Object>();
								perMap.put("regionName", rName);
								perMap.put("areaSum", new BigDecimal(0));
								perMap.put("productSum", new BigDecimal(0));
								perMap.put("growProportion", new BigDecimal(0));
								perMap.put("peasantCount", new BigDecimal(0));
							}
						}
						// 5、获取某年度各个乡镇的有机认证面积
						ResultModel organicModel = getPerOrganicGreenGrowArea(year, rCode, ORGANIC_IDENTIFICATION, "0");

						Object organicObj = organicModel.getData();
						List<Map<String, Object>> organicList = null;
						Map<String, Object> organicMap = null;
						if (organicObj != null) {
							organicList = (List<Map<String, Object>>) organicObj;
							if (organicList.size() >= 1) {
								organicMap = organicList.get(0);
								if (organicMap.containsKey("identAreaSum")) {
									BigDecimal identAreaSum = (BigDecimal) organicMap.get("identAreaSum");
									perMap.put("organicIdentAreaSum", identAreaSum);
								}
							} else {
								perMap.put("organicIdentAreaSum", new BigDecimal(0));
							}
						}

						// 6、获取各个乡镇的种植品种信息
						String strainsText = "";
						ResultModel breedModel = getGrowBreedByRegion(year, rCode);
						Object breedObj = breedModel.getData();
						List<Map<String, Object>> breedList = null;
						if (breedObj != null) {
							breedList = (List<Map<String, Object>>) breedObj;
							if (breedList.size() >= 1) {
								for (Map<String, Object> map : breedList) {
									if (map.containsKey("strainsCode")) {
										int tempStrainsCode = (int) map.get("strainsCode");
										if (!"0".equals(tempStrainsCode + "")) {
											if (map.containsKey("strainsText")) {
												String tempStrainsText = (String) map.get("strainsText");
												if (!strainsText.contains(tempStrainsText)) {
													strainsText += tempStrainsText + "、";
												}
											}
										}
									}
								}
							}
						}
						if (StringUtils.isNotBlank(strainsText)) {
							perMap.put("strainsText", strainsText.substring(0, strainsText.length() - 1));
						} else {
							perMap.put("strainsText", "");
						}

						resultMap = new HashMap<String, Object>();
						resultMap.put(rCode, perMap);
						resultList.add(resultMap);
//						if(growArea.compareTo(BigDecimal.ZERO) != 0) {
//						}
					}
				}
			}
		}

		// 7、计算种植面积和有机认证面积最多的乡镇

		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	public ResultModel getPerOrganicGreenGrowArea(String year, String regionCode, String identificationType, String breed) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT t0.* FROM (SELECT SUBSTR(B.start_time, 1, 4) TIME,
		 * B.start_time AS startTime , A.`identification_type_text` AS
		 * identText, A.`identification_area_unit` AS identAreaSum,
		 * A.`produce_summation_unit` AS identOutputSum FROM
		 * da_identification_info A, da_common_field B WHERE A.common_field_id =
		 * B.id AND B.`data_time_type_code`=1 AND B.region_id = '530723102' AND
		 * A.`crop_type_code`=1 AND A.strains_code = 1 AND
		 * A.`identification_type_code`=2 AND SUBSTR(B.start_time, 1, 4) ='2018'
		 * ORDER BY startTime DESC) t0 GROUP BY TIME
		 */

		selectBuffer.append(" SELECT t0.* FROM																					");
		selectBuffer.append(" (SELECT 																							");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																	");
		selectBuffer.append(" B.start_time AS startTime ,																		");
		
		selectBuffer.append(" A.`identification_type_text` AS identText,														");
		
//		CASE WHEN A.`grow_area_unit` IS NULL THEN 0 ELSE A.`grow_area_unit` END AS areaSum,
//		selectBuffer.append(" A.`identification_area_unit` AS identAreaSum,														");
//		selectBuffer.append(" A.`produce_summation_unit` AS identOutputSum														");
		selectBuffer.append(" CASE WHEN A.`identification_area_unit` IS NULL THEN 0 ELSE A.`identification_area_unit` END AS identAreaSum,	");
		selectBuffer.append(" CASE WHEN A.`produce_summation_unit` IS NULL THEN 0 ELSE A.`produce_summation_unit` END AS identOutputSum		");
		
		selectBuffer.append(" FROM																								");
		selectBuffer.append(" da_identification_info A,																			");
		selectBuffer.append(" da_common_field B 																				");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																	");
		selectBuffer.append(" AND B.`data_time_type_code`=1																		");
		selectBuffer.append(" AND B.region_id =																					");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionCode)) {
			selectBuffer.append(regionCode);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.`crop_type_code`=1																			");
		selectBuffer.append(" AND A.strains_code =																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(breed)) {
			selectBuffer.append(breed);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.`identification_type_code`=																	");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(identificationType)) {
			selectBuffer.append(identificationType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																	");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC) t0 																		");
		selectBuffer.append(" GROUP BY time																						");
		map.put("Sql", selectBuffer.toString());
		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}

	private ResultModel getRegionCodeList(String regionCode) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT a.name AS NAME, a.code AS CODE FROM gp_region a WHERE
		 * a.`farther_code`='530723'
		 */

		selectBuffer.append(" SELECT														");
		selectBuffer.append(" a.name AS name,												");
		selectBuffer.append(" a.code AS code												");
		selectBuffer.append(" FROM gp_region a												");
		selectBuffer.append(" WHERE 														");
		selectBuffer.append(" a.`farther_code`=												");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionCode)) {
			selectBuffer.append(regionCode);
		}
		selectBuffer.append("'");
		map.put("Sql", selectBuffer.toString());
		resultModel = gpRegionUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "查询单位面积产量", notes = "查询单位面积产量")
	@RequestMapping(value = "/getPerAreaProduct", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPerAreaProduct() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String year = "";// 年份
		String regionId = "";// 地区id

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

		//获取时间列表
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "year");// 视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "5");
		List<String> timesList = TimesView.getTimesView(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for(String time : timesList) {
			//获取某年某个地区的单位面积产量
			ResultModel perAreaforecastVolume = getMangoSummationInfo(time,regionId,"0");
			BigDecimal areaSum = new BigDecimal("0");
			BigDecimal productSum = new BigDecimal("0");
			BigDecimal perGrowUnit = new BigDecimal("0");
			
			Object obj = perAreaforecastVolume.getData();
			List<Map<String,Object>> list = null;
			Map<String,Object> perMap = null;
			if(obj!=null) {
				list = (List<Map<String, Object>>) obj;
				if(list.size()>0) {
					perMap = list.get(0);
					if(perMap.containsKey("productSum")) {
						productSum = (BigDecimal) perMap.get("productSum");
					}
					if(perMap.containsKey("areaSum")) {
						areaSum = (BigDecimal) perMap.get("areaSum");
					}
					if(areaSum.compareTo(BigDecimal.ZERO) == 0) {
						perGrowUnit = MathUtil.decimalFormat(new BigDecimal("0"));
					}else{
						perGrowUnit = productSum.divide(areaSum, 2, BigDecimal.ROUND_HALF_UP);
					}
					resultMap.put(time,perGrowUnit);
				}
			}
		}
		
		/*resultMap.put("2014", new BigDecimal(0.735));
		resultMap.put("2015", new BigDecimal(0.715));
		resultMap.put("2016", new BigDecimal(0.695));
		resultMap.put("2017", new BigDecimal(0.721));
		resultMap.put("2018", new BigDecimal(0.718));*/
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	@ApiOperation(value = "查询指定年份区间各地区芒果种植数据及同比增长率（门户数据服务版）", notes = "查询指定年份区间各地区芒果种植数据及同比增长率（门户数据服务版）")
	@RequestMapping(value = "/getGrowDataAndProportion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getGrowDataAndProportion() throws ParseException {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionId = "";// 地区id
		String breed = "";// 芒果品种id
		String startTime = "";// 开始时间
		String endTime = "";// 结束时间
		String dimension = "";// 时间维度
		String relationId = "";//relationId

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("breed") && StringUtils.isNotBlank(entityRelatedObject.getString("breed")))
					breed = entityRelatedObject.getString("breed");
				if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
					startTime = entityRelatedObject.getString("startTime").trim();
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
					endTime = entityRelatedObject.getString("endTime").trim();
				if (entityRelatedObject.containsKey("dimension") && StringUtils.isNotBlank(entityRelatedObject.getString("dimension")))
					dimension = entityRelatedObject.getString("dimension");
				if (entityRelatedObject.containsKey("relationId") && StringUtils.isNotBlank(entityRelatedObject.getString("relationId")))
					relationId = entityRelatedObject.getString("relationId");
			}
		}
		// 确定所需要的时间列表
		// 第一种情况：startTime和endTime都为空
		List<String> timesList = new ArrayList<String>();
		if (StringUtils.isBlank(startTime) && StringUtils.isBlank(endTime)) {
			timesList = getTimesList(dimension);
		} else {
			// 第二种情况：startTime和endTime不都为空
			if (StringUtils.isBlank(endTime)) {
				endTime = DateUtils.date2String(new Date(), TIME_FORMAT[Integer.parseInt(dimension) - 1]);
			}
			String tempStartTime = TIME_START[Integer.parseInt(dimension) - 1];
			// 获取本次查询需要的时间区域
			List<String> tempTimesList = null;
			if (DIMENSION_YEAR.equals(dimension)) {
				tempTimesList = DateUtils.getYearBetween(tempStartTime, endTime);
			} else if (DIMENSION_MONTH.equals(dimension)) {
				tempTimesList = DateUtils.getMonthBetween(tempStartTime, endTime);
			} else if (DIMENSION_DAY.equals(dimension)) {
				tempTimesList = DateUtils.getQueryDateList(tempStartTime, endTime, TIME_FORMAT[Integer.parseInt(dimension) - 1]);
			}
			// 然后再考虑startTime为空和不为空两种情况
			if (StringUtils.isNotBlank(startTime)) {
				for (int i = tempTimesList.size() - 1; i >= 0; i--) {
					if (!startTime.equals(tempTimesList.get(i))) {
						timesList.add(tempTimesList.get(i));
						Collections.sort(timesList);
					} else {
						timesList.add(tempTimesList.get(i));
						if (i - 1 >= 0) {
							timesList.add(tempTimesList.get(i - 1));
						}
						Collections.sort(timesList);
						break;
					}
				}
			} else {
				timesList = tempTimesList;
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> growAreaResultMap = new HashMap<String, Object>();
		Map<String, Object> productResultMap = new HashMap<String, Object>();
		Map<String, Object> outputResultMap = new HashMap<String, Object>();

		List growAreaYearList = new ArrayList();
		List growAreaDataList = new ArrayList();
		List growAreaProportionList = new ArrayList();

		List productYearList = new ArrayList();
		List productDataList = new ArrayList();
		List productProportionList = new ArrayList();

		List outputYearList = new ArrayList();
		List outputDataList = new ArrayList();
		List outputProportionList = new ArrayList();

		for (int i = 0; i < timesList.size() - 1; i++) {
			growAreaYearList.add(timesList.get(i + 1));
			productYearList.add(timesList.get(i + 1));
			outputYearList.add(timesList.get(i + 1));

			BigDecimal lastAreaSum = new BigDecimal("0");
			BigDecimal lastProductSum = new BigDecimal("0");
			BigDecimal lastOutputSum = new BigDecimal("0");
			BigDecimal currentAreaSum = new BigDecimal("0");
			BigDecimal currentProductSum = new BigDecimal("0");
			BigDecimal currentOutputSum = new BigDecimal("0");

			// 获取所需年的种植面积、挂果面积、总产量、总产值、种植户数量
			ResultModel currentModel = getMangoSummationInfo(timesList.get(i + 1), regionId, breed);
			Map<String, Object> currentMap = getMapFromModel(currentModel);
			if (currentMap != null) {
				if (currentMap.containsKey("areaSum")) {
					currentAreaSum = (BigDecimal) currentMap.get("areaSum");
				}
				if (currentMap.containsKey("productSum")) {
					currentProductSum = (BigDecimal) currentMap.get("productSum");
				}
				if (currentMap.containsKey("outputSum")) {
					currentOutputSum = (BigDecimal) currentMap.get("outputSum");
				}
			}
			growAreaDataList.add(currentAreaSum + "");
			productDataList.add(currentProductSum + "");
			outputDataList.add(currentOutputSum + "");

			// 获取参考年份（上一年）的种植面积、挂果面积、总产量、总产值、种植户数量
			ResultModel lastModel = getMangoSummationInfo(timesList.get(i), regionId, breed);
			Map<String, Object> lastMap = getMapFromModel(lastModel);
			if (lastMap != null) {
				if (lastMap.containsKey("areaSum")) {
					lastAreaSum = (BigDecimal) lastMap.get("areaSum");
				}
				if (lastMap.containsKey("productSum")) {
					lastProductSum = (BigDecimal) lastMap.get("productSum");
				}
				if (lastMap.containsKey("outputSum")) {
					lastOutputSum = (BigDecimal) lastMap.get("outputSum");
				}
			}
			// 计算同比增长率
			String growAreaProportion = getProportion(currentAreaSum, lastAreaSum);
			String productProportion = getProportion(currentProductSum, lastProductSum);
			String outputProportion = getProportion(currentOutputSum, lastOutputSum);

			growAreaProportionList.add(growAreaProportion.substring(0, growAreaProportion.length()-1));
			productProportionList.add(productProportion.substring(0, productProportion.length()-1));
			outputProportionList.add(outputProportion.substring(0, outputProportion.length()-1));
		}

		//没有数据的年份不返回
		growAreaYearList = getFormalListAccordingToData(growAreaYearList,growAreaDataList);
		growAreaProportionList = getFormalListAccordingToData(growAreaProportionList,growAreaDataList);
		if(growAreaProportionList.size()>0) {
			growAreaProportionList.set(0, "0");
		}
		growAreaDataList = getFormalList(growAreaDataList);
		
		productYearList = getFormalListAccordingToData(productYearList,productDataList);
		productProportionList = getFormalListAccordingToData(productProportionList,productDataList);
		if(productProportionList.size()>0) {
			productProportionList.set(0, "0");
		}
		productDataList = getFormalList(productDataList);
		
		outputYearList = getFormalListAccordingToData(outputYearList,outputDataList);
		outputProportionList = getFormalListAccordingToData(outputProportionList,outputDataList);
		if(outputProportionList.size()>0) {
			outputProportionList.set(0, "0");
		}
		outputDataList = getFormalList(outputDataList);
		
		growAreaResultMap.put("times", growAreaYearList);
		growAreaResultMap.put("data", growAreaDataList);
		growAreaResultMap.put("proportion", growAreaProportionList);

		productResultMap.put("times", productYearList);
		productResultMap.put("data", productDataList);
		productResultMap.put("proportion", productProportionList);

		outputResultMap.put("times", outputYearList);
		outputResultMap.put("data", outputDataList);
		outputResultMap.put("proportion", outputProportionList);

		if("01".equals(relationId)) {
			resultMap.put("growArea", growAreaResultMap);
			resultMap.put("product", productResultMap);
		}else if("02".equals(relationId)){
			resultMap.put("output", outputResultMap);
		}

		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	private List getFormalListAccordingToData(List hostList,List followList) {
		List formatList = new ArrayList();
		BigDecimal b = MathUtil.decimalFormat(new BigDecimal("0"));
		for(int i = 0;i<followList.size();i++) {
			if(followList.get(i)!=null && !followList.get(i).equals("null")) {
				BigDecimal a = MathUtil.decimalFormat(new BigDecimal("0"));
				try {
						a = MathUtil.decimalFormat(new BigDecimal(followList.get(i)+""));
				} catch (Exception e) {
					e.printStackTrace();
				} 
				if(!b.equals(a)) {
					formatList.add(hostList.get(i));
				}
			}
		}
		return formatList;
	}

	private List getFormalList(List tempList) {
		List formatList = new ArrayList();
		BigDecimal b = MathUtil.decimalFormat(new BigDecimal("0"));
		for(int i = 0;i<tempList.size();i++) {
			if(tempList.get(i)!=null  && !tempList.get(i).equals("null")) {
				BigDecimal a = MathUtil.decimalFormat(new BigDecimal(tempList.get(i)+"")); 
				if(!b.equals(a)) {
					formatList.add(MathUtil.decimalFormat(new BigDecimal(""+tempList.get(i))));
				}
			}
		}
		return formatList;
	}

	private List<String> getTimesList(String dimension) {
		Map<String, String> timesMap = new HashMap<String, String>();
		if (DIMENSION_YEAR.equals(dimension)) {
			timesMap.put("viewName", "year");// 视图名，年year，月month，日date，小时hour（默认为年）
			timesMap.put("hasCurrent", "false");
			timesMap.put("pastNum", "10");
		} else if (DIMENSION_MONTH.equals(dimension)) {
			timesMap.put("viewName", "month");// 视图名，年year，月month，日date，小时hour（默认为年）
			timesMap.put("hasCurrent", "false");
			timesMap.put("pastNum", "12");
		} else if (DIMENSION_DAY.equals(dimension)) {
			timesMap.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
			timesMap.put("hasCurrent", "false");
			timesMap.put("pastNum", "7");
		}
		List<String> timesList = TimesView.getTimesView(timesMap);
		Collections.sort(timesList);
		return timesList;
	}
	
	@ApiOperation(value = "App-数据-数据查询-查询最近5年的产量、产值、种植面积和种植户数量", notes = "App-数据-数据查询-查询最近5年的产量、产值、种植面积和种植户数量")
	@RequestMapping(value = "/getTimesMonoidalDataForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTimesMonoidalDataForApp() throws ParseException {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String regionId = "";//地区id
		String queryId = "";//查询内容标志
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("queryId") && StringUtils.isNotBlank(entityRelatedObject.getString("queryId")))
					queryId = entityRelatedObject.getString("queryId");
			}
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		List<String> timesList = new ArrayList<String>();
		Map<String, String> timesMap = new HashMap<String, String>();
		timesMap.put("viewName", "year");//视图名，年year，月month，日date，小时hour（默认为年）
		timesMap.put("hasCurrent", "false");
		timesMap.put("pastNum", "5");
		timesList = TimesView.getTimesView(timesMap);
		Collections.sort(timesList);
		
		List<String> growAreaList = new ArrayList<String>();
		List<String> productList = new ArrayList<String>();
		List<String> outputList = new ArrayList<String>();
		List<String> peasantCountList = new ArrayList<String>();
		
		String dataSources = "";
		for(String time : timesList) {
			BigDecimal areaSum = new BigDecimal("0");
			BigDecimal productSum = new BigDecimal("0");
			BigDecimal outputSum = new BigDecimal("0");
			BigDecimal peasantCount = new BigDecimal("0");
			ResultModel perModel = getMangoSummationInfo(time, regionId, "0");
			Object perObj = perModel.getData();
			List<Map<String,Object>> perList = null;
			Map<String,Object> perMap = null;
			if(perObj!=null) {
				perList = (List<Map<String, Object>>) perObj;
				if(perList.size()>=1) {
					perMap = perList.get(0);
					if(perMap.containsKey("areaSum")) {
						areaSum = (BigDecimal) perMap.get("areaSum");
						growAreaList.add(areaSum+"");
					}
					if(perMap.containsKey("productSum")) {
						productSum = (BigDecimal) perMap.get("productSum");
						productList.add(productSum+"");
					}
					if(perMap.containsKey("outputSum")) {
						outputSum = (BigDecimal) perMap.get("outputSum");
						outputList.add(outputSum+"");
					}
					if(perMap.containsKey("peasantCount")) {
						peasantCount = (BigDecimal) perMap.get("peasantCount");
						peasantCountList.add(peasantCount+"");
					}
					if(perMap.containsKey("dataSources")) {
						dataSources = (String) perMap.get("dataSources");
					}
				}else {
					growAreaList.add(areaSum+"");
					productList.add(productSum+"");
					outputList.add(outputSum+"");
					peasantCountList.add(peasantCount+"");
				}
			}
		}
		resultMap.put("time", timesList);
		resultMap.put("dataSources", dataSources);
		if("1".equals(queryId)) {
			resultMap.put("resultData", productList);
			resultMap.put("unit", COMMON_UNIT_AREA);		
		}
		if("2".equals(queryId)) {
			resultMap.put("resultData", outputList);
			resultMap.put("unit", COMMON_UNIT_PRODUCT);	
		}
		if("3".equals(queryId)) {
			resultMap.put("resultData", growAreaList);
			resultMap.put("unit", COMMON_UNIT_OUTPUT);	
		}
		if("4".equals(queryId)) {
			resultMap.put("resultData", peasantCountList);
			resultMap.put("unit", COMMON_UNIT_PEASANTCOUNT);	
		}
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	@ApiOperation(value = "新增记录（直报数据）", notes = "新增单条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaGrowYield") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaGrowYield jsonData) {

		ResultModel resultModel = new ResultModel();

		DaCommonField daCommonField = jsonData.getDaCommonField();
		String domainId = this.getCurrentUser().getCurrentDomain().getId();
		// domain为app，信息默认来自当前用户，domain为数据采集，信息来自用户填写
		if (DomainEnum.IOSAPP.getId().equals(domainId) || DomainEnum.AndroidAPP.getId().equals(domainId)) {
			daCommonField.setAddUserId(this.getCurrentUser().getId());
			daCommonField.setAreaLatitudeTypeCode((byte) 4);
			daCommonField.setAreaLatitudeTypeText("县/区");
			daCommonField.setRegionId("530723");
			daCommonField.setRegionName("华坪县");
			daCommonField.setSourceChannelTypeCode((byte) 1);
			daCommonField.setSourceChannelTypeText("农户APP填报");
			daCommonField.setDataTimeTypeCode((byte) 1);
			daCommonField.setDataTimeTypeText("年");
			Calendar currCal = Calendar.getInstance();
			int currentYear = currCal.get(Calendar.YEAR);
			daCommonField.setStartTime(DateUtils.getYearFirst(currentYear));
			daCommonField.setEndTime(DateUtils.getYearLast(currentYear));
		}

		ResultModel result = daCommonFieldUntBll.add(daCommonField);
		if (result.getIsSuccess()) {
			jsonData.setCommonFieldId(result.getObjectId());
			
			
			if(jsonData.getGrowArea()!=null){
				jsonData.setGrowAreaUnit(UnitUtil.switchUnit(jsonData.getGrowArea(), jsonData.getGrowAreaCode(), "DI_AREA_UNIT"));
			}
			if(jsonData.getFruitArea()!=null){
				jsonData.setFruitAreaUnit(UnitUtil.switchUnit(jsonData.getFruitArea(), jsonData.getFruitAreaCode(), "DI_AREA_UNIT"));
			}
			if(jsonData.getProductTotal()!=null){
				jsonData.setProductTotalUnit(UnitUtil.switchUnit(jsonData.getProductTotal(), jsonData.getProductTotalCode(), "DI_WEIGHT_UNIT"));
			}
			if(jsonData.getOutputValue()!=null){
				jsonData.setOutputValueUnit(UnitUtil.switchUnit(jsonData.getOutputValue(), jsonData.getOutputValueCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getOrganicGrowArea()!=null){
				jsonData.setOrganicGrowAreaUnit(UnitUtil.switchUnit(jsonData.getOrganicGrowArea(), jsonData.getOrganicGrowAreaCode(), "DI_AREA_UNIT"));
			}
			if(jsonData.getSaleTotal()!=null){
				jsonData.setSaleTotalUnit(UnitUtil.switchUnit(jsonData.getSaleTotal(), jsonData.getSaleTotalCode(), "DI_WEIGHT_UNIT"));
			}
			if(jsonData.getECommerceOutputValue()!=null){
				jsonData.setECommerceOutputValueUnit(UnitUtil.switchUnit(jsonData.getECommerceOutputValue(), jsonData.getECommerceOutputValueCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getECommerceSaleTotal()!=null){
				jsonData.setECommerceSaleTotalUnit(UnitUtil.switchUnit(jsonData.getECommerceSaleTotal(), jsonData.getECommerceSaleTotalCode(), "DI_WEIGHT_UNIT"));
			}
			
			//jsonData.setImproveAreaUnit(UnitUtil.switchUnit(jsonData.getImproveArea(), jsonData.getImproveAreaCode(), "DI_AREA_UNIT"));
			
			resultModel = daGrowYieldUntBll.add(jsonData);
		}

		// 新增完之后，更新用户贡献度
		daUserContributionUtil.updateUserContribution(CustomSymbolic.CONTRIBUTION_YIELD);

		return resultModel;
	}
	@ApiOperation(value = "单条查询（直报）", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daGrowYieldUntBll.getModel(id);
		if(result.getIsSuccess()){
			DaGrowYield jsonData = (DaGrowYield) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			result.setData(jsonData);
		}
		return result;
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
				resultModel = daGrowYieldUntBll.delete(id);
			}
		}

		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量删除（直报数据）", notes = "根据主键列表批量删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaGrowYieldDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaGrowYieldParameter.DeleteByIdList jsonData) {
		
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
				resultModel = daGrowYieldUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}
	
	@ApiOperation(value = "修改记录（直报数据）", notes = "修改指定记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaGrowYield") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaGrowYield jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			
			if(jsonData.getGrowArea()!=null){
				jsonData.setGrowAreaUnit(UnitUtil.switchUnit(jsonData.getGrowArea(), jsonData.getGrowAreaCode(), "DI_AREA_UNIT"));
			}
			if(jsonData.getFruitArea()!=null){
				jsonData.setFruitAreaUnit(UnitUtil.switchUnit(jsonData.getFruitArea(), jsonData.getFruitAreaCode(), "DI_AREA_UNIT"));
			}
			if(jsonData.getProductTotal()!=null){
				jsonData.setProductTotalUnit(UnitUtil.switchUnit(jsonData.getProductTotal(), jsonData.getProductTotalCode(), "DI_WEIGHT_UNIT"));
			}
			if(jsonData.getOutputValue()!=null){
				jsonData.setOutputValueUnit(UnitUtil.switchUnit(jsonData.getOutputValue(), jsonData.getOutputValueCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getOrganicGrowArea()!=null){
				jsonData.setOrganicGrowAreaUnit(UnitUtil.switchUnit(jsonData.getOrganicGrowArea(), jsonData.getOrganicGrowAreaCode(), "DI_AREA_UNIT"));
			}
			if(jsonData.getSaleTotal()!=null){
				jsonData.setSaleTotalUnit(UnitUtil.switchUnit(jsonData.getSaleTotal(), jsonData.getSaleTotalCode(), "DI_WEIGHT_UNIT"));
			}
			if(jsonData.getECommerceOutputValue()!=null){
				jsonData.setECommerceOutputValueUnit(UnitUtil.switchUnit(jsonData.getECommerceOutputValue(), jsonData.getECommerceOutputValueCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getECommerceSaleTotal()!=null){
				jsonData.setECommerceSaleTotalUnit(UnitUtil.switchUnit(jsonData.getECommerceSaleTotal(), jsonData.getECommerceSaleTotalCode(), "DI_WEIGHT_UNIT"));
			}
			
			resultModel = daGrowYieldUntBll.update(jsonData);
		}
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaGrowYieldUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaGrowYieldParameter.UpdateList jsonData) {
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
		selectBuffer.append("	INNER JOIN da_grow_yield B ON A.id = B.common_field_id                               ");
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
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaGrowYieldAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaGrowYield> list1 = (List<DaGrowYield>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaGrowYield.class);
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
						if(map.containsKey(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getGrowAreaText())){
							  String status = map.get(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getGrowAreaText()).toString();
								list1.get(i).setGrowAreaCode(Byte.valueOf(status));//种植面积
							}
						if(map.containsKey(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getImproveAreaText())){
							  String status = map.get(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getImproveAreaText()).toString();
								list1.get(i).setImproveAreaCode(Byte.valueOf(status));//改良面积
							}
						if(map.containsKey(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getFruitAreaText())){
							  String status = map.get(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getFruitAreaText()).toString();
								list1.get(i).setFruitAreaCode(Byte.valueOf(status));//挂果面积
							}
						if(map.containsKey(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getStrainsText())){
							  String status = map.get(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getStrainsText()).toString();
								list1.get(i).setStrainsCode(Byte.valueOf(status));//作物品种
							}
						if(map.containsKey(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText())){
							  String status = map.get(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText()).toString();
								list1.get(i).setCropTypeCode(Byte.valueOf(status));//作物种类
							}
						if(map.containsKey(DictionaryEnum.PRODUCE_SUMMATION_UNIT_CODE.getText()+list1.get(i).getProductTotalText())){
							  String status = map.get(DictionaryEnum.PRODUCE_SUMMATION_UNIT_CODE.getText()+list1.get(i).getProductTotalText()).toString();
								list1.get(i).setProductTotalCode(Byte.valueOf(status));//产品产量
							}
						if(map.containsKey(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getOutputValueText())){
							  String status = map.get(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getOutputValueText()).toString();
								list1.get(i).setOutputValueCode(Byte.valueOf(status));//产值
							}
						list1.get(i).setProductTotalUnit(UnitUtil.switchUnit(list1.get(i).getProductTotal(), list1.get(i).getProductTotalCode(), "DI_WEIGHT_UNIT"));
						list1.get(i).setGrowAreaUnit(UnitUtil.switchUnit(list1.get(i).getGrowArea(), list1.get(i).getGrowAreaCode(), "DI_AREA_UNIT"));
						list1.get(i).setImproveAreaUnit(UnitUtil.switchUnit(list1.get(i).getImproveArea(), list1.get(i).getImproveAreaCode(), "DI_AREA_UNIT"));
						list1.get(i).setFruitAreaUnit(UnitUtil.switchUnit(list1.get(i).getFruitArea(), list1.get(i).getFruitAreaCode(), "DI_AREA_UNIT"));
						list1.get(i).setOutputValueUnit(UnitUtil.switchUnit(list1.get(i).getOutputValue(), list1.get(i).getOutputValueCode(), "DI_PER_PRICE_UNIT"));
						resultModel = daGrowYieldUntBll.add(list1.get(i));
						
					
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
		selectBuffer.append(" A.id id,  ");
		selectBuffer.append(" A.peasant_count peasantCount,  ");
		selectBuffer.append(" A.product_total_unit productTotalUnit,  ");
		selectBuffer.append(" A.grow_area_unit growAreaUnit,  ");
		selectBuffer.append(" A.crop_type_text cropTypeText,  ");
		selectBuffer.append(" A.strains_text strainsText,  ");
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
		selectBuffer.append(" da_grow_yield A  ");
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
                
				if (entityRelatedObject.containsKey("sourceChannelTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("sourceChannelTypeCode")))
					selectBuffer.append(" and B.source_channel_type_code = '"+entityRelatedObject.getString("sourceChannelTypeCode")+"' ");
				if (entityRelatedObject.containsKey("dataSources") && StringUtils.isNotBlank(entityRelatedObject.getString("dataSources")))
					selectBuffer.append(" and B.data_sources like '%").append(entityRelatedObject.getString("dataSources")).append("%'");
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
				
				if (entityRelatedObject.containsKey("yearS") && StringUtils.isNotBlank(entityRelatedObject.getString("yearS")))
					selectBuffer.append(" and SUBSTR(B.start_time, 1, 4) >= '"+entityRelatedObject.getString("yearS")+"' ");
				if (entityRelatedObject.containsKey("yearE") && StringUtils.isNotBlank(entityRelatedObject.getString("yearE")))
					selectBuffer.append(" and SUBSTR(B.start_time, 1, 4) <= '"+entityRelatedObject.getString("yearE")+"' ");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					selectBuffer.append(" and B.region_id = '"+entityRelatedObject.getString("regionId")+"' ");
				if (entityRelatedObject.containsKey("dataTimeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("dataTimeTypeCode")))
					selectBuffer.append(" and B.data_time_type_code = '"+entityRelatedObject.getString("dataTimeTypeCode")+"' ");
				if (entityRelatedObject.containsKey("strainsCode") && StringUtils.isNotBlank(entityRelatedObject.getString("strainsCode")))
					selectBuffer.append(" and A.strains_code = '"+entityRelatedObject.getString("strainsCode")+"' ");
				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%"+entityRelatedObject.getString("name")+"%' ");
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

		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "新增记录（数据采集）", notes = "新增单条记录（数据采集）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaGrowYield") })
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel insert(@RequestBody DaGrowYield jsonData) {
		ResultModel resultModel = new ResultModel();
		
		DaCommonField daCommonField = jsonData.getDaCommonField();
		daCommonField.setAddUserId(this.getCurrentUser().getId());
		daCommonField.setAuditerUserId(this.getCurrentUser().getId());
		daCommonField.setAuditerTime(DateUtils.getCurrentTime());
		ResultModel result = daCommonFieldUntBll.add(daCommonField);
		
		if (result.getIsSuccess()) {
			List<DaGrowYield> daGrowYieldList = jsonData.getDaGrowYieldList();
			for (Iterator iterator = daGrowYieldList.iterator(); iterator.hasNext();) {
				DaGrowYield daGrowYield = (DaGrowYield) iterator.next();
				jsonData.setStrainsCode(daGrowYield.getStrainsCode());
				jsonData.setStrainsText(daGrowYield.getStrainsText());
				jsonData.setGrowArea(daGrowYield.getGrowArea());
				jsonData.setCommonFieldId(result.getObjectId());
				jsonData.setGrowAreaUnit(jsonData.getGrowArea().multiply(new BigDecimal(SQUARE_METER)));
				jsonData.setProductTotalUnit(jsonData.getProductTotal().multiply(new BigDecimal(KILOGRAM)));
				jsonData.setId(null);
				resultModel = daGrowYieldUntBll.add(jsonData);
			}
		}

		return resultModel;
	}
	
	@ApiOperation(value = "修改记录（数据采集）", notes = "修改单条记录（数据采集）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaGrowYield") })
	@RequestMapping(value = "/modify", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel modify(@RequestBody DaGrowYield jsonData) {
		ResultModel resultModel = new ResultModel();
		
		DaCommonField daCommonField = jsonData.getDaCommonField();
		ResultModel result = daCommonFieldUntBll.update(daCommonField);
		
		if (result.getIsSuccess()) {
			List<DaGrowYield> daGrowYieldList = jsonData.getDaGrowYieldList();
			for (Iterator iterator = daGrowYieldList.iterator(); iterator.hasNext();) {
				DaGrowYield daGrowYield = (DaGrowYield) iterator.next();
				jsonData.setGrowArea(daGrowYield.getGrowArea());
				jsonData.setId(daGrowYield.getId());
				jsonData.setGrowAreaUnit(jsonData.getGrowArea().multiply(new BigDecimal(SQUARE_METER)));
				jsonData.setProductTotalUnit(jsonData.getProductTotal().multiply(new BigDecimal(KILOGRAM)));
				resultModel = daGrowYieldUntBll.update(daGrowYield);
			}
		}
		return resultModel;
	}
	
	@ApiOperation(value = "单条查询（直报）", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getInfo(@RequestParam String id) {
		DaGrowYield jsonData = new DaGrowYield();
		DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(id).getData();
		jsonData.setDaCommonField(DaCommonField);
		ResultModel growYieldList = getGrowYieldList(id, "0");
		jsonData.setDaGrowYieldList((List<DaGrowYield>) growYieldList.getData());
		ResultModel result = new ResultModel();
		result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		result.setData(jsonData);
		return result;
	}
	
	/**
	 * 获取对应的da_common_field列表
	 * @param objIds
	 * @return
	 */
	private ResultModel getGrowYieldList(String id, String strainsCode){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                      ");
		selectBuffer.append("		B.id id,                                                                ");
		selectBuffer.append("		B.strains_code strainsCode,                                        		");
		selectBuffer.append("		B.strains_text strainsText,                                             ");
		selectBuffer.append("		B.product_total productTotal,                                  ");
		selectBuffer.append("		B.grow_area growArea,                                  					");
		selectBuffer.append("		B.peasant_count peasantCount                                    		");
		selectBuffer.append("	FROM                                                                        ");
		selectBuffer.append("		da_common_field A                                                       ");
		selectBuffer.append("	INNER JOIN da_grow_yield B ON A.id = B.common_field_id                      ");
		selectBuffer.append("	WHERE                                                                       ");
		selectBuffer.append("		A.id = '"+id+"'  														");
		if(null != strainsCode)
		selectBuffer.append("		and B.strains_code <> '"+strainsCode+"'  								");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = daCommonFieldUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "删除记录（直报数据）", notes = "根据主键删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "query", name = "id", value = "主键ID", required = true, dataType = "String")
	@RequestMapping(value = "/remove", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel remove(@RequestParam String id) {
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.delete(id);
		if(result.getIsSuccess()){
			ResultModel growYieldList = getGrowYieldList(id, null);
			List<DaGrowYield> dgyList = (List<DaGrowYield>) growYieldList.getData();
			ArrayList<String> idList = new ArrayList<>();
			for (int i = 0; i < dgyList.size(); i++) 
				idList.add(dgyList.get(i).getId());
			resultModel = daGrowYieldUntBll.deleteByIdList(idList);
		}
		
		return resultModel;
	}
	
	@ApiOperation(value = "模糊查询（网络数据）", notes = "根据查询条件模糊查询（网络数据）")
	@RequestMapping(value = "/getListByJsonDatasWl", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonDatasWl() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select  ");
		selectBuffer.append(" B.id commonId,  ");
		selectBuffer.append(" B.add_time addTime,  ");
		selectBuffer.append(" B.region_id regionId,  ");
		selectBuffer.append(" B.region_name regionName,  ");
		selectBuffer.append(" A.peasant_count peasantCount,  ");
		selectBuffer.append(" A.product_total productTotal,  ");
		selectBuffer.append(" sum( A.grow_area ) growArea, ");
		selectBuffer.append(" B.audit_status_code auditStatusCode,  ");
		selectBuffer.append(" B.start_time startTime,  ");
		selectBuffer.append(" B.audit_status_text auditStatusText  ");
		selectBuffer.append(" FROM  ");
		selectBuffer.append(" da_grow_yield A  ");
		selectBuffer.append(" INNER JOIN da_common_field B ON A.common_field_id = B.id  ");
		selectBuffer.append(" WHERE  ");
		selectBuffer.append(" 1 = 1  ");
		
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("yearS") && StringUtils.isNotBlank(entityRelatedObject.getString("yearS")))
					selectBuffer.append(" and SUBSTR(B.start_time, 1, 4) >= '"+entityRelatedObject.getString("yearS")+"' ");
				if (entityRelatedObject.containsKey("yearE") && StringUtils.isNotBlank(entityRelatedObject.getString("yearE")))
					selectBuffer.append(" and SUBSTR(B.start_time, 1, 4) <= '"+entityRelatedObject.getString("yearE")+"' ");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					selectBuffer.append(" and B.region_id = '"+entityRelatedObject.getString("regionId")+"' ");
				if (entityRelatedObject.containsKey("dataTimeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("dataTimeTypeCode")))
					selectBuffer.append(" and B.data_time_type_code = '"+entityRelatedObject.getString("dataTimeTypeCode")+"' ");
				if (entityRelatedObject.containsKey("strainsCode") && StringUtils.isNotBlank(entityRelatedObject.getString("strainsCode")))
					selectBuffer.append(" and A.strains_code = '"+entityRelatedObject.getString("strainsCode")+"' ");
			}
			
			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
			}
			selectBuffer.append(" GROUP BY B.id");
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

		resultModel = daGrowYieldUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（网络数据）", notes = "数据审核（网络数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaGrowYieldUpdateList") })
	@RequestMapping(value = "/auditWl", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel auditWl(@RequestBody DaGrowYieldParameter.UpdateList jsonData) {
		ResultModel resultModel = new ResultModel();
		DaCommonField daCommonField = jsonData.getEntity().getDaCommonField();
		daCommonField.setAuditerUserId(this.getCurrentUser().getId());
		daCommonField.setAuditerTime(DateUtils.getCurrentTime());
		daCommonFieldUntBll.update(daCommonField);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
}
