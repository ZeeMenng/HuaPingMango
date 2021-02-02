package com.zee.app.extend.swagger.mf;

import java.util.ArrayList;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfSaleEcommerceCrawGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.da.DaSaleFreshUntBll;
import com.zee.bll.extend.unity.mf.MfSaleEcommerceCrawUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaMarketPrice;
import com.zee.ent.extend.mf.MfSaleEcommerceCraw;
import com.zee.ent.parameter.da.DaMarketPriceParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  对外接口，扩展自MfSaleEcommerceCrawGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfSaleEcommerceCraw")
public class  MfSaleEcommerceCrawSwgApp extends  MfSaleEcommerceCrawGenSwgApp {
	
	@Autowired
	@Qualifier("mfSaleEcommerceCrawUntBll")
	protected MfSaleEcommerceCrawUntBll mfSaleEcommerceCrawUntBll;
	
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	@ApiOperation(value = "查询", notes = "查询华坪鲜果组合偏好")
	@RequestMapping(value = "/getCombinatorialPreference", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getCombinatorialPreference() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = "";//查询类型  1：品种、单价组合，2：规格、单价组合
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
			List<String> x = new ArrayList<>();
			List<String> xCode = new ArrayList<>();
			
		 if(queryType.equals("1")){
			 Map<String, Object> map1 = new HashMap<String, Object>();
				Map<String, List<Map<String, Object>>> maps1 = new HashMap<String, List<Map<String, Object>>>();
				// 查询华坪芒果种植面积前五的品种
				StringBuffer selectBuffer1 = new StringBuffer();
				selectBuffer1.append(" select t.strains_code,t.strains_text from (select A.strains_code,A.strains_text from mf_sale_ecommerce_craw A ");
				selectBuffer1.append(" inner join da_common_field B on A.common_field_id = B.id where B.data_time_type_code = 5 and A.crop_type_code = '1' ");
				selectBuffer1.append(" and A.strains_text <> '其他' group by A.strains_code limit 0,5) t ");

				map1.put("Sql", selectBuffer1.toString());
				resultModel = mfSaleEcommerceCrawUntBll.getListBySQL(map1);
				maps1.put("strains", (List<Map<String, Object>>) resultModel.getData());// 数据

				List<String> strainsList = new ArrayList<String>();
				for (Map<String, Object> map0 : maps1.get("strains")) {
					strainsList.add("'" + map0.get("strains_code").toString() + "'");
					x.add(map0.get("strains_text").toString());
					xCode.add(map0.get("strains_code").toString());
				}

				String strainsCode = strainsList.toString();
				strainsCode = strainsCode.replace("[", "").replace("]", "");
				String[] strainsArr = strainsCode.split(",");
				if(strainsCode.equals("")){
					strainsCode = "''";
					x.add("");
				}
				//组合偏好-品种、单价;
//			 	selectBuffer.append(" select distinct A.strains_code combinaCode,A.strains_text sname,A.price_range_code rangeCode,A.price_range_text ranges,sum(A.sale_amount_unit) amount ");
//				selectBuffer.append(" from mf_sale_ecommerce_craw A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '1' ");
//				selectBuffer.append(" and substr(B.start_time,1,4) = '"+queryTime+"' and A.strains_code in ("+strainsCode+") ");
//				selectBuffer.append(" and A.crop_type_code = '1' group by A.strains_code,A.strains_text,A.price_range_code,A.price_range_text ");
				
				selectBuffer.append(" select round(sum(amount),2) amount,T.strains_code combinaCode,T.strains_text sname,T.price_range_code rangeCode,T.price_range_text ranges from ( ");
				selectBuffer.append(" select substr(B.start_time,1,10) start_time,avg(A.sale_amount_unit) amount,A.strains_code,A.strains_text,A.price_range_code,A.price_range_text ");
				selectBuffer.append(" from mf_sale_ecommerce_craw A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '5' ");
				selectBuffer.append(" and A.crop_type_code = '1' group by substr(B.start_time,1,10),A.strains_code,A.strains_text,A.price_range_code,A.price_range_text) T ");
				selectBuffer.append(" where substr(T.start_time,1,4) = '"+queryTime+"' and T.strains_code in ("+strainsCode+") and T.strains_code is not null and T.price_range_code is not null group by T.strains_code,T.price_range_code ");
		 }else if(queryType.equals("2")){
			 	//组合偏好-规格、单价;
//			 	selectBuffer.append(" select distinct A.weight_specification_code combinaCode,A.weight_specification_text sname,A.price_range_code rangeCode,A.price_range_text ranges,sum(A.sale_amount_unit) amount ");
//				selectBuffer.append(" from mf_sale_ecommerce_craw A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '1' ");
//				selectBuffer.append(" and substr(B.start_time,1,4) = '"+queryTime+"' and A.crop_type_code = '1' ");
//				selectBuffer.append(" group by A.weight_specification_code,A.weight_specification_text,A.price_range_code,A.price_range_text ");
				
				selectBuffer.append(" select round(sum(amount),2) amount,T.weight_specification_code combinaCode,T.weight_specification_text sname,T.price_range_code rangeCode,T.price_range_text ranges from ( ");
				selectBuffer.append(" select substr(B.start_time,1,10) start_time,avg(A.sale_amount_unit) amount,A.weight_specification_code,A.weight_specification_text,A.price_range_code,A.price_range_text ");
				selectBuffer.append(" from mf_sale_ecommerce_craw A,da_common_field B where A.common_field_id = B.id and B.data_time_type_code = '5' ");
				selectBuffer.append(" and A.crop_type_code = '1' group by substr(B.start_time,1,10),A.weight_specification_code,A.weight_specification_text,A.price_range_code,A.price_range_text) T ");
				selectBuffer.append(" where substr(T.start_time,1,4) = '"+queryTime+"' and T.weight_specification_code is not null and T.price_range_code is not null group by T.weight_specification_code,T.price_range_code  ");
		 }

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());

		resultModel = mfSaleEcommerceCrawUntBll.getListBySQL(map);
		
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		maps.put("data", (List<Map<String, Object>>)resultModel.getData());// 数据

		
		
		 Map<String, List<String>> xMap = new HashMap<String, List<String>>();
		if(queryType.equals("1")){
			xMap.put("Xdata", x);
		}else if(queryType.equals("2")){
			x.add("1kg以下");
			x.add("1kg-2kg");
			x.add("2kg-3kg");
			x.add("3kg以上");
			xMap.put("Xdata", x);
		}
		
		List<String> y = new ArrayList<>();
		Map<String, List<String>> yMap = new HashMap<String, List<String>>();
		y.add("5元/公斤以下");
		y.add("5～10元/公斤");
		y.add("10～15元/公斤");
		y.add("15～20元/公斤");
		y.add("20元以上/公斤");
		yMap.put("Ydata", y);
		
		List<Object> numList0 = new ArrayList<Object>();
        Map<String, List<Object>> dataMap = new HashMap<String, List<Object>>();
		Map<String, Object> nameMap = new HashMap<String, Object>();

    		for(int i = 0;i < x.size(); i++){
				nameMap = new HashMap<String, Object>();
    			nameMap.put("name", x.get(i));
	    			for(int j = 0;j < y.size(); j++){
	    				String k = j+1+"";
		 				String val = "-";
	    				for (Map<String, Object> map0 : maps.get("data") ){
	    					if(queryType.equals("1")){
	    						if( (xCode.get(i).equals(map0.get("combinaCode").toString())) && (k.equals(map0.get("rangeCode").toString())) ){
				 					val = map0.get("amount").toString();
	    						}
	    					}else{
	    						if( (x.get(i).equals(map0.get("sname").toString())) && (k.equals(map0.get("rangeCode").toString())) ){
				 					val = map0.get("amount").toString();
	    						}
	    					}
	    				}
    					nameMap.put("lev"+(j+1), val);
	    			}
				numList0.add(nameMap);
			}
    		
        dataMap.put("data", numList0);
        resultModel.setData(dataMap);
		
		return resultModel;
	}
	
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT														");
		selectBuffer.append("			A.id id,												");
		selectBuffer.append("			A.common_field_id commonFieldId,						");
		selectBuffer.append("			A.strains_code strainsCode,								");
		selectBuffer.append("			A.strains_text strainsText,								");
		selectBuffer.append("			A.sale_price_unit salePriceUnit,						");
		selectBuffer.append("			A.product_area_code productAreaCode,					");
		selectBuffer.append("			A.product_area_text productAreaText,					");
		selectBuffer.append("			A.ecommerce_code ecommerceCode,							");
		selectBuffer.append("			A.ecommerce_text ecommerceText,							");
		selectBuffer.append("			B.add_time addTime,										");
		selectBuffer.append("			B.audit_status_code auditStatusCode,					");
		selectBuffer.append("			B.audit_status_text auditStatusText						");
		selectBuffer.append("		FROM														");
		selectBuffer.append("			mf_sale_ecommerce_craw A								");
		selectBuffer.append("		INNER JOIN da_common_field B ON A.common_field_id = B.id	");
		selectBuffer.append("		WHERE														");
		selectBuffer.append("			1 = 1													");
        
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
                
				if (entityRelatedObject.containsKey("cropTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeCode")))
					selectBuffer.append(" and A.crop_type_code like '%").append(entityRelatedObject.getString("cropTypeCode")).append("%'");
				if (entityRelatedObject.containsKey("cropTypeText") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeText")))
					selectBuffer.append(" and A.crop_type_text like '%").append(entityRelatedObject.getString("cropTypeText")).append("%'");
				if (entityRelatedObject.containsKey("strainsCode") && StringUtils.isNotBlank(entityRelatedObject.getString("strainsCode")))
					selectBuffer.append(" and A.strains_code like '%").append(entityRelatedObject.getString("strainsCode")).append("%'");
				if (entityRelatedObject.containsKey("startDate") && StringUtils.isNotBlank(entityRelatedObject.getString("startDate")))
					selectBuffer.append(" and B.add_time >= '"+entityRelatedObject.getString("startDate")+"' ");
				if (entityRelatedObject.containsKey("endDate") && StringUtils.isNotBlank(entityRelatedObject.getString("endDate")))
					selectBuffer.append(" and B.add_time <= '"+entityRelatedObject.getString("endDate")+"' ");
				if (entityRelatedObject.containsKey("topPrice") && StringUtils.isNotBlank(entityRelatedObject.getString("topPrice")))
					selectBuffer.append(" and A.sale_price_unit <= ").append(entityRelatedObject.getString("topPrice"));
				if (entityRelatedObject.containsKey("bottomPrice") && StringUtils.isNotBlank(entityRelatedObject.getString("bottomPrice")))
					selectBuffer.append(" and A.sale_price_unit >= ").append(entityRelatedObject.getString("bottomPrice"));
				if (entityRelatedObject.containsKey("productAreaText") && StringUtils.isNotBlank(entityRelatedObject.getString("productAreaText")))
					selectBuffer.append(" and A.product_area_text like '%").append(entityRelatedObject.getString("productAreaText")).append("%'");
				if (entityRelatedObject.containsKey("productAreaCode") && StringUtils.isNotBlank(entityRelatedObject.getString("productAreaCode")))
					selectBuffer.append(" and A.product_area_code =").append(entityRelatedObject.getString("productAreaCode"));
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

		resultModel = mfSaleEcommerceCrawUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel resultModel = new ResultModel();
		
		ResultModel model = mfSaleEcommerceCrawUntBll.getModel(id);
		if(model.getIsSuccess()){
			MfSaleEcommerceCraw msec = (MfSaleEcommerceCraw) model.getData();
			daCommonFieldUntBll.delete(msec.getCommonFieldId());
			resultModel = mfSaleEcommerceCrawUntBll.delete(id);
		}
		
		return resultModel;
	}
	
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaCommonField") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaCommonField jsonData) {
		ResultModel resultModel = new ResultModel();
		jsonData.setAuditerUserId(this.getCurrentUser().getId());
		jsonData.setAuditerTime(DateUtils.getCurrentTime());
		daCommonFieldUntBll.update(jsonData);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = mfSaleEcommerceCrawUntBll.getModel(id);
		if(result.getIsSuccess()){
			MfSaleEcommerceCraw msec = (MfSaleEcommerceCraw) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(msec.getCommonFieldId()).getData();
			msec.setDaCommonField(DaCommonField);
			result.setData(msec);
		}
		return result;
	}

}


