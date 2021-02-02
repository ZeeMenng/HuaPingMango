package com.zee.app.extend.swagger.pi;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiProductRecommendGenSwgApp;
import com.zee.bll.extend.split.pi.PirProductResourceSplBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.bll.extend.unity.pi.PirEnterpriseProductUntBll;
import com.zee.bll.extend.unity.pi.PirProductResourceUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiProductRecommend;
import com.zee.ent.extend.pi.PirEnterpriseProduct;
import com.zee.ent.extend.pi.PirProductResource;
import com.zee.set.enumer.ProductImgTypeEnum;
import com.zee.set.enumer.RecommendProductType;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/7/11 17:38:33
 * @description 企业推介产品表 对外接口，扩展自PiProductRecommendGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piProductRecommend")
public class PiProductRecommendSwgApp extends PiProductRecommendGenSwgApp {

	@Autowired
	@Qualifier("pirProductResourceUntBll")
	protected PirProductResourceUntBll pirProductResourceUntBll;

	@Autowired
	@Qualifier("pirProductResourceSplBll")
	protected PirProductResourceSplBll pirProductResourceSplBll;

	@Autowired
	protected GpDictionaryUntBll gpDictionaryUntBll;
	
	@Autowired
	@Qualifier("pirEnterpriseProductUntBll")
	protected PirEnterpriseProductUntBll pirEnterpriseProductUntBll;
	
	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = piProductRecommendUntBll.delete(id);

		result=pirProductResourceSplBll.deleteTitleImageByContentId(result.getObjectId());
		
		return result;
	}
	
	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "PiProductRecommend") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody PiProductRecommend jsonData) {
		if (jsonData.getWeight() != null && jsonData.getSellingPrice() != null) {
			jsonData.setPerPriceUnit(jsonData.getSellingPrice()
					.divide(jsonData.getWeight(), 10, BigDecimal.ROUND_HALF_UP));
		}
		jsonData.setTypeText(RecommendProductType.getText(jsonData.getTypeCode()));
		ResultModel result = piProductRecommendUntBll.add(jsonData);
		
		if (StringUtils.isNotBlank(jsonData.getEnterpriseId())) {
			PirEnterpriseProduct pir = new PirEnterpriseProduct();
			pir.setProductId(result.getObjectId());
			pir.setEnterpriseId(jsonData.getEnterpriseId());
			pirEnterpriseProductUntBll.add(pir);
		}
		
		if (StringUtils.isNotBlank(jsonData.getTitleResourceId())){//产品图片
				PirProductResource pirProductResource = new PirProductResource();
				pirProductResource.setProductId(result.getObjectId());
				pirProductResource.setResourceId(jsonData.getTitleResourceId());
				pirProductResource.setType(ProductImgTypeEnum.CHANPIN.getCode());
				pirProductResourceUntBll.add(pirProductResource);
		}
		
		if (StringUtils.isNotBlank(jsonData.getResourceId())){//产品证书
			String[] resourceIds = jsonData.getResourceId().split("\\|");
			for (String resId : resourceIds) {
				PirProductResource pirProductResource = new PirProductResource();
				pirProductResource.setProductId(result.getObjectId());
				pirProductResource.setResourceId(resId);
				pirProductResource.setType(ProductImgTypeEnum.ZHENGSHU.getCode());
				pirProductResourceUntBll.add(pirProductResource);
			}
		}

		return result;
	}
	
	@SuppressWarnings("unchecked")
	private ResultModel modelCommon(String id){
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                                                   ");  
		selectBuffer.append("		A.id AS id,                                                                                          ");
		selectBuffer.append("		A.name AS name,                                                                                      ");
		selectBuffer.append("		A.type_code AS typeCode,                                                                             ");
		selectBuffer.append("		A.type_text AS typeText,                                                                             ");
		selectBuffer.append("		A.brand AS brand,                                                                                    ");
		selectBuffer.append("		A.is_recommend AS isRecommend,                                                                       ");
		selectBuffer.append("		A.weight AS weight,                                                                                  ");
		selectBuffer.append("		A.selling_price AS sellingPrice,                                                                     ");
		selectBuffer.append("		A.release_time AS releaseTime,                                                                       ");
		selectBuffer.append("		A.mall_interlinkage AS mallInterlinkage,                                                             ");
		selectBuffer.append("		A.remark AS remark,                                                                                  ");
		selectBuffer.append("		C.id AS resourceId,                                                                                  ");
		selectBuffer.append("	    C.path AS resourcePath,                                                                              ");
		selectBuffer.append("	    B.type,                                                                                              ");
		selectBuffer.append("		D.enterprise_id AS enterpriseId                                                                      ");
		selectBuffer.append("	FROM                                                                                                     ");
		selectBuffer.append("		pi_product_recommend A                                                                               ");
		selectBuffer.append("	LEFT JOIN pir_product_resource B ON A.id = B.product_id                                                  ");
		selectBuffer.append("	LEFT JOIN gp_resource C ON B.resource_id = C.id                                                          ");
		selectBuffer.append("	LEFT JOIN pir_enterprise_product D ON D.product_id = A.id                                                ");
		selectBuffer.append("	WHERE A.id = '"+id+"'                                                                                    ");  
		
		map.put("Sql", selectBuffer.toString());
		resultModel = piProductRecommendUntBll.getListBySQL(map);
		
		List<Map<String,Object>> list = (List<Map<String, Object>>)resultModel.getData();
		if(list != null && list.size() > 0) {
			Map<String,Object> resultMap = list.get(0);
			if(resultMap.containsKey("isRecommend")) {
				int isRecommend = (Integer) resultMap.get("isRecommend");
				resultMap.put("isRecommendText", transfSqlData(CustomSymbolic.DI_BOOLEAN, String.valueOf(isRecommend)));
			}
			List<String> resourceIdList = new ArrayList<String>();
			List<String> resourcePathList = new ArrayList<String>();
			for (Map<String, Object> map2 : list) {
				if(map2.get("type") != null){
					if(ProductImgTypeEnum.CHANPIN.getCode().toString().equals(map2.get("type").toString())){
						resultMap.put("titleResourceId", map2.get("resourceId").toString());
						resultMap.put("titleResourcePath", linkPath + map2.get("resourcePath").toString());
					}else if(ProductImgTypeEnum.ZHENGSHU.getCode().toString().equals(map2.get("type").toString())){
						resourceIdList.add(map2.get("resourceId").toString());
						resourcePathList.add(linkPath + map2.get("resourcePath").toString());
					}
				}
			}
			String[] resourceIdArray = resourceIdList.toArray(new String[resourceIdList.size()]);
			String[] resourcePathArray = resourcePathList.toArray(new String[resourcePathList.size()]);
			resultMap.put("resourceIdArray", resourceIdArray);
			resultMap.put("resourcePathArray", resourcePathArray);
			resultModel.setData(resultMap);
		}
		
		return resultModel;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		return modelCommon(id);
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		return modelCommon(id);
	}

	private String transfSqlData(String dictType,String isRecommend) {
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         		"); 
		selectBuffer.append("		d.text AS text 												   	"); 
		selectBuffer.append("	FROM                                                           		"); 
		selectBuffer.append("		gp_dictionary d                                               	");
		selectBuffer.append("   WHERE d.type_id =        											");
		selectBuffer.append("'");
		selectBuffer.append(dictType);
		selectBuffer.append("'");
		selectBuffer.append(" AND ");
		selectBuffer.append(" d.code = ");
		selectBuffer.append(isRecommend);

		map.put("Sql", selectBuffer.toString());
		
		resultModel = gpDictionaryUntBll.getListBySQL(map);
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		String text = "";
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			if(objectList.size()>=1) {
				objMap = objectList.get(0);
				JSONObject jsonData = JSONObject.fromObject(objMap);
				if (jsonData.containsKey("text")) {
					text = jsonData.getString("text");
				}
			}
		}	
		return text;
	}
	
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "PiProductRecommend") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody PiProductRecommend jsonData) {
		
		if (jsonData.getWeight() != null && jsonData.getSellingPrice() != null) {
			jsonData.setPerPriceUnit(jsonData.getSellingPrice()
					.divide(jsonData.getWeight(), 2, BigDecimal.ROUND_HALF_UP));
		}
		jsonData.setTypeText(RecommendProductType.getText(jsonData.getTypeCode()));
		ResultModel resultModel = piProductRecommendUntBll.update(jsonData);
		
		pirProductResourceSplBll.deleteTitleImageByContentId(jsonData.getId());
		
		if (StringUtils.isNotBlank(jsonData.getTitleResourceId())){//产品图片
			PirProductResource pirProductResource = new PirProductResource();
			pirProductResource.setProductId(jsonData.getId());
			pirProductResource.setResourceId(jsonData.getTitleResourceId());
			pirProductResource.setType(ProductImgTypeEnum.CHANPIN.getCode());
			pirProductResourceUntBll.add(pirProductResource);
		}
	
		if (StringUtils.isNotBlank(jsonData.getResourceId())){//产品证书
			String[] resourceIds = jsonData.getResourceId().split("\\|");
			for (String resId : resourceIds) {
				PirProductResource pirProductResource = new PirProductResource();
				pirProductResource.setProductId(jsonData.getId());
				pirProductResource.setResourceId(resId);
				pirProductResource.setType(ProductImgTypeEnum.ZHENGSHU.getCode());
				pirProductResourceUntBll.add(pirProductResource);
			}
		}
		
		if (StringUtils.isNotBlank(jsonData.getEnterpriseId())) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("	SELECT                                                       "); 
			selectBuffer.append("		A.id id,                                                 "); 
			selectBuffer.append("		A.enterprise_id enterpriseId,                            "); 
			selectBuffer.append("		A.product_id productId                                   "); 
			selectBuffer.append("	FROM                                                         "); 
			selectBuffer.append("		pir_enterprise_product A                                 "); 
			selectBuffer.append("	WHERE A.product_id = '"+jsonData.getId()+"'         "); 
			map.put("Sql", selectBuffer.toString());
			ResultModel result = pirEnterpriseProductUntBll.getListBySQL(map);
			//删除再增加
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) result.getData();
			for (Map<String, Object> map2 : modelList) {
				pirEnterpriseProductUntBll.delete(map2.get("id").toString());
			}
			String enterpriseId = jsonData.getEnterpriseId();
			PirEnterpriseProduct pirEnterpriseProduct = new PirEnterpriseProduct();
			pirEnterpriseProduct.setEnterpriseId(enterpriseId);
			pirEnterpriseProduct.setProductId(jsonData.getId());
			pirEnterpriseProductUntBll.add(pirEnterpriseProduct);
		}

		return resultModel;
	}
	
	@ApiOperation(value = "获取芒果微商城列表", notes = "获取芒果微商城列表")
	@RequestMapping(value = "/getMangoMallList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoMallList() {
		ResultModel resultModel = new ResultModel();// 基本信息
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String typeCode = "";// 产品类型 1 鲜果 2 加工品
		String isRecommend = "";// 是否推荐 0推荐 1不推荐
		String maxPrice = "";// 最高价
		String minPrice = "";// 最低价

		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("typeCode")
					&& StringUtils.isNotBlank(entityRelatedObject.getString("typeCode")))
				typeCode = entityRelatedObject.getString("typeCode");
			if (entityRelatedObject.containsKey("isRecommend")
					&& StringUtils.isNotBlank(entityRelatedObject.getString("isRecommend")))
				isRecommend = entityRelatedObject.getString("isRecommend");
			if (entityRelatedObject.containsKey("maxPrice")
					&& StringUtils.isNotBlank(entityRelatedObject.getString("maxPrice")))
				maxPrice = entityRelatedObject.getString("maxPrice");
			if (entityRelatedObject.containsKey("minPrice")
					&& StringUtils.isNotBlank(entityRelatedObject.getString("minPrice")))
				minPrice = entityRelatedObject.getString("minPrice");
		}

		Map<String, Object> map = new HashMap<String, Object>();

		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT * FROM (SELECT B.`id` AS id, B.`type_code` AS typeCode, B.`brand` AS
		 * brand, B.`name` AS NAME, B.`weight` AS WEIGHT, B.`selling_price` AS
		 * sellingPrice, B.`per_price_unit` AS perPriceUnit, B.`mall_interlinkage` AS
		 * mallInterlinkage, B.`release_time` AS releaseTime, CONCAT(
		 * 'http://202.106.10.250:63011/res', C.path ) AS path FROM pir_product_resource
		 * A LEFT JOIN pi_product_recommend B ON A.`product_id` = B.`id` LEFT JOIN
		 * gp_resource C ON A.`resource_id` = C.`id`) t0 WHERE typeCode='2' GROUP BY id
		 */

		selectBuffer.append(" SELECT * FROM																			");
		selectBuffer.append(" (SELECT 																				");
		selectBuffer.append(" B.`id` AS id,																			");
		selectBuffer.append(" B.`type_code` AS typeCode,															");
		selectBuffer.append(" B.`brand` AS brand,																	");
		selectBuffer.append(" B.`name` AS name,																		");
		selectBuffer.append(" B.`weight` AS weight,																	");
		selectBuffer.append(" B.`selling_price` AS sellingPrice,													");
		selectBuffer.append(" B.`per_price_unit` AS perPriceUnit,													");
		selectBuffer.append(" B.`mall_interlinkage` AS mallInterlinkage,											");
		selectBuffer.append(" B.`release_time` AS releaseTime,														");
		selectBuffer.append(" B.`is_recommend` AS isRecommend,														");
		selectBuffer.append(" CONCAT('");
		selectBuffer.append(this.linkPath);
		selectBuffer.append("',");
		selectBuffer.append(" C.path) AS path																		");
		selectBuffer.append(" FROM pi_product_recommend B															");
		selectBuffer.append(" LEFT JOIN pir_product_resource A ON A.`product_id` = B.`id`							");
		selectBuffer.append(" LEFT JOIN gp_resource C ON A.`resource_id` = C.`id`) t0								");
		selectBuffer.append(" WHERE 1=1																				");
		if (StringUtils.isNotBlank(typeCode)) {
			selectBuffer.append(" AND typeCode=																		");
			selectBuffer.append("'");
			selectBuffer.append(typeCode);
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(isRecommend) && "0".equals(isRecommend)) {
			selectBuffer.append(" AND isRecommend = 																");
			selectBuffer.append("'");
			selectBuffer.append(isRecommend);
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(maxPrice)) {
			BigDecimal maxPerPrice = new BigDecimal(maxPrice);
			selectBuffer.append(" AND perPriceUnit <																");
			selectBuffer.append(maxPerPrice);
		}
		if (StringUtils.isNotBlank(minPrice)) {
			BigDecimal minPerPrice = new BigDecimal(minPrice);
			selectBuffer.append(" AND perPriceUnit >																");
			selectBuffer.append(minPerPrice);
		}
		selectBuffer.append(" GROUP BY id																			");
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

		map.put("Sql", selectBuffer.toString());
		resultModel = piProductRecommendUntBll.getListBySQL(map);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	@ApiOperation(value = "根据企业id获取芒果微商城列表", notes = "根据企业id获取芒果微商城列表")
	@RequestMapping(value = "/getMangoMallListByEnterpriseId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMangoMallListByEnterpriseId() {
		ResultModel resultModel = new ResultModel();// 基本信息
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String enterpriseId = "";// 企业id

		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("enterpriseId")
					&& StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseId")))
				enterpriseId = entityRelatedObject.getString("enterpriseId");
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT B.id AS enterpriseId, B.`enterprise_name` AS enterpriseName, C.`id` AS
		 * productId, C.`type_code` AS typeCode, C.`type_text` AS typeText, C.`brand` AS
		 * brand, C.`name` AS NAME, C.`weight` AS weight, C.`selling_price` AS
		 * sellingPrice, C.`per_price_unit` AS perPriceUnit, C.`mall_interlinkage` AS
		 * mallInterlinkage, C.`release_time` AS releaseTime, CONCAT(
		 * 'http://202.106.10.250:63011/res', E.path ) AS path, A.`priority` FROM
		 * pir_enterprise_product A LEFT JOIN da_enterprise_info B ON A.`enterprise_id`
		 * = B.`id` LEFT JOIN pi_product_recommend C ON A.`product_id` = C.`id` LEFT
		 * JOIN pir_product_resource D ON D.`product_id` = C.`id` LEFT JOIN gp_resource
		 * E ON D.`resource_id` = E.`id` WHERE B.`id` =
		 * 'dc0f69ff004949325cab1ee7b3af8458' GROUP BY productId ORDER BY A.`priority`
		 * DESC;
		 */

		selectBuffer.append(
				" SELECT 																						");
		selectBuffer.append(
				" B.id AS enterpriseId,																			");
		selectBuffer.append(
				" B.`enterprise_name` AS enterpriseName,														");
		selectBuffer.append(
				" C.`id` AS productId,																			");
		selectBuffer.append(
				" C.`type_code` AS typeCode,																	");
		selectBuffer.append(
				" C.`type_text` AS typeText,																	");
		selectBuffer.append(
				" C.`brand` AS brand,																			");
		selectBuffer.append(
				" C.`name` AS name,																				");
		selectBuffer.append(
				" C.`weight` AS weight,																			");
		selectBuffer.append(
				" C.`selling_price` AS sellingPrice,															");
		selectBuffer.append(
				" C.`per_price_unit` AS perPriceUnit,															");
		selectBuffer.append(
				" C.`mall_interlinkage` AS mallInterlinkage,													");
		selectBuffer.append(
				" C.`release_time` AS releaseTime,																");
		selectBuffer.append(" CONCAT('");
		selectBuffer.append(this.linkPath);
		selectBuffer.append("',");
		selectBuffer.append(
				" E.path) AS path,																				");
		selectBuffer.append(
				" A.`priority` AS priority																		");
		selectBuffer.append(
				" FROM pir_enterprise_product A 																");
		selectBuffer.append(
				" LEFT JOIN da_enterprise_info B ON A.`enterprise_id` = B.`id`									");
		selectBuffer.append(
				" LEFT JOIN pi_product_recommend C ON A.`product_id` = C.`id`									");
		selectBuffer.append(
				" LEFT JOIN pir_product_resource D ON D.`product_id` = C.`id` 									");
		selectBuffer.append(
				" LEFT JOIN gp_resource E ON D.`resource_id` = E.`id`											");
		selectBuffer.append(
				" WHERE B.`id` = 																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(enterpriseId)) {
			selectBuffer.append(enterpriseId);
		}
		selectBuffer.append("'");
		selectBuffer.append(
				" GROUP BY productId ORDER BY A.`priority` DESC													");

		map.put("Sql", selectBuffer.toString());

		resultModel = piProductRecommendUntBll.getListBySQL(map);
		// resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		return resultModel;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String name = "";
		String minPriceUnit = "";
		String maxPriceUnit = "";
		String enterpriseId = "";
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
            
			if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
				name = entityRelatedObject.getString("name");
			if(entityRelatedObject.containsKey("minPriceUnit") && StringUtils.isNotBlank(entityRelatedObject.getString("minPriceUnit")))
				minPriceUnit = entityRelatedObject.getString("minPriceUnit");
			if(entityRelatedObject.containsKey("maxPriceUnit") && StringUtils.isNotBlank(entityRelatedObject.getString("maxPriceUnit")))
				maxPriceUnit = entityRelatedObject.getString("maxPriceUnit");
			if (entityRelatedObject.containsKey("enterpriseId") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseId")))
				enterpriseId = entityRelatedObject.getString("enterpriseId");
			
		}
		

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                            ");
		selectBuffer.append("		A.id id,                                                      ");
		selectBuffer.append("		A.type_code typeCode,                                         ");
		selectBuffer.append("		A.type_text typeText,                                         ");
		selectBuffer.append("		A.name name,                                                  ");
		selectBuffer.append("		A.brand brand,                                                ");
		selectBuffer.append("		A.weight weight,                                              ");
		selectBuffer.append("		A.selling_price sellingPrice,                                 ");
		selectBuffer.append("		A.per_price_unit perPriceUnit,                                ");
		selectBuffer.append("		A.mall_interlinkage mallInterlinkage,                         ");
		selectBuffer.append("		A.release_time releaseTime,                                   ");
		selectBuffer.append("		A.add_time addTime,                                           ");
		selectBuffer.append("		A.is_recommend isRecommend                                    ");
		selectBuffer.append("	FROM                                                              ");
		selectBuffer.append("		pi_product_recommend A                                        ");
		if(StringUtils.isNotBlank(enterpriseId)){
			selectBuffer.append(" INNER JOIN pir_enterprise_product B ON A.id = B.product_id      ");
			selectBuffer.append(" AND B.enterprise_id = '"+enterpriseId+"'                        ");
		}
		selectBuffer.append("	WHERE                                                             ");
		selectBuffer.append("		1 = 1                                                         ");
		
		if(StringUtils.isNotBlank(name)){
			selectBuffer.append(" and A.name like '%").append(name).append("%'");
		}
		if(StringUtils.isNotBlank(minPriceUnit)){
			selectBuffer.append(" and A.per_price_unit >").append(minPriceUnit);
		}
		if(StringUtils.isNotBlank(maxPriceUnit)){
			selectBuffer.append(" and A.per_price_unit <").append(maxPriceUnit);
		}
		
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
				selectBuffer.append(Tools.getCamelUnderline(orderColumnObject.getString("columnName")));
				selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
				selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
			}
		}else{
			selectBuffer.append(" order by releaseTime DESC ");
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = piProductRecommendUntBll.getListBySQL(map);

		return resultModel;
	}

	
	
}
