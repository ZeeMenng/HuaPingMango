package com.jusfoun.app.generate.swagger.da;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jusfoun.app.generate.swagger.base.BaseSwgApp;
import com.jusfoun.bll.extend.split.da.DaEnterpriseInfoSplBll;
import com.jusfoun.bll.extend.unity.da.DaEnterpriseInfoUntBll;
import com.jusfoun.ent.extend.da.DaEnterpriseInfo;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.parameter.da.DaEnterpriseInfoParameter;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:00:55
 * @updateDate 2020/8/11 11:42:42
 * @description da_enterprise_info 对外接口，扩展自BaseSwgApp，自动生成。
 */

@Api(value = "DaEnterpriseInfo",tags="da_enterprise_info")
@RequestMapping(value = "/generate/swagger/da/daEnterpriseInfo")
public class DaEnterpriseInfoGenSwgApp extends BaseSwgApp {

	@Autowired
	@Qualifier("daEnterpriseInfoUntBll")
	protected DaEnterpriseInfoUntBll daEnterpriseInfoUntBll;

	@Autowired
	@Qualifier("daEnterpriseInfoSplBll")
	protected DaEnterpriseInfoSplBll daEnterpriseInfoSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaEnterpriseInfo") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaEnterpriseInfo jsonData) {
		ResultModel result = daEnterpriseInfoUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量新增", notes = "新增多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterpriseInfoAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody DaEnterpriseInfoParameter.AddList jsonData) {
		ResultModel result = daEnterpriseInfoUntBll.add(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = daEnterpriseInfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = daEnterpriseInfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaEnterpriseInfoDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaEnterpriseInfoParameter.DeleteByIdList jsonData) {
		ResultModel result = daEnterpriseInfoUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaEnterpriseInfo") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaEnterpriseInfo jsonData) {
		ResultModel result = daEnterpriseInfoUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaEnterpriseInfoUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody DaEnterpriseInfoParameter.UpdateList jsonData) {
		ResultModel result = daEnterpriseInfoUntBll.updateList(jsonData);

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterpriseInfoAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDff(@RequestBody DaEnterpriseInfoParameter.AddList jsonData) {
		ResultModel result = daEnterpriseInfoUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值,如果没有此条记录则执行新增")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterpriseInfoAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDffOrAdd(@RequestBody DaEnterpriseInfoParameter.AddList jsonData) {
		ResultModel result = daEnterpriseInfoUntBll.updateListWithDffOrAdd(jsonData.getEntityList());

		return result;
	}
     

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daEnterpriseInfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daEnterpriseInfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "DaEnterpriseInfoGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody DaEnterpriseInfoParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = daEnterpriseInfoUntBll.getList(jsonData);

		return result;
	}
    
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.common_field_id commonFieldId,A.organization_id organizationId,A.enterprise_name enterpriseName,A.enterprise_code enterpriseCode,A.registration_mark registrationMark,A.archives_number archivesNumber,A.society_credit_id societyCreditId,A.register_status_text registerStatusText,A.register_status_code registerStatusCode,A.register_type_text registerTypeText,A.register_type_code registerTypeCode,A.statistics_Department_number statisticsDepartmentNumber,A.found_date foundDate,A.corporation_name corporationName,A.corporation_identity_card corporationIdentityCard,A.corporation_mobile corporationMobile,A.linkman linkman,A.linkman_mobile linkmanMobile,A.landline_telephone landlineTelephone,A.enterprise_type_text enterpriseTypeText,A.enterprise_type_code enterpriseTypeCode,A.enterprise_nature_code enterpriseNatureCode,A.enterprise_nature_text enterpriseNatureText,A.industry_type_code industryTypeCode,A.industry_type_text industryTypeText,A.registered_capital registeredCapital,A.paidin_capital paidinCapital,A.address address,A.enterprise_address enterpriseAddress,A.enterprise_phone enterprisePhone,A.administrative_area administrativeArea,A.region_code regionCode,A.region_text regionText,A.measure_longitude measureLongitude,A.measure_latitude measureLatitude,A.establish_date establishDate,A.approval_date approvalDate,A.business_duetime businessDuetime,A.longdistance_number longdistanceNumber,A.administration_number administrationNumber,A.business_scope businessScope,A.professional_activity professionalActivity,A.enterprise_control enterpriseControl,A.organization_type_text organizationTypeText,A.organization_type_code organizationTypeCode,A.industry_activity industryActivity,A.industry_code industryCode,A.industry_text industryText,A.risk_grade riskGrade,A.risk_value riskValue,A.category_code categoryCode,A.category_text categoryText,A.risk_tags_code riskTagsCode,A.risk_tags_text riskTagsText,A.funnel_tags_code funnelTagsCode,A.funnel_tags_text funnelTagsText,A.core_code coreCode,A.core_text coreText,A.person_scale personScale,A.money_scale moneyScale,A.year_they yearThey,A.is_corporate_champion_type_code isCorporateChampionTypeCode,A.is_corporate_champion_type_text isCorporateChampionTypeText,A.scale_enterprise_type_code scaleEnterpriseTypeCode,A.email email,A.scale_enterprise_type_text scaleEnterpriseTypeText,A.summary summary,A.description description,A.zip_code zipCode,A.telautogram telautogram,A.is_recommend isRecommend,A.organization_code organizationCode,A.manage_status manageStatus,A.trade_text tradeText,A.registry_office registryOffice,A.logo_resource_id logoResourceId,A.logo_resource_path logoResourcePath,A.is_cooperative_code isCooperativeCode,A.is_cooperative_text isCooperativeText  from da_enterprise_info A inner join da_enterprise_info B on A.id=B.id where 1=1 ");
        
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
                
				if (entityRelatedObject.containsKey("enterpriseName") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseName")))
					selectBuffer.append(" and A.enterprise_name like '%").append(entityRelatedObject.getString("enterpriseName")).append("%'");
				if (entityRelatedObject.containsKey("enterpriseCode") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseCode")))
					selectBuffer.append(" and A.enterprise_code like '%").append(entityRelatedObject.getString("enterpriseCode")).append("%'");
				if (entityRelatedObject.containsKey("registrationMark") && StringUtils.isNotBlank(entityRelatedObject.getString("registrationMark")))
					selectBuffer.append(" and A.registration_mark like '%").append(entityRelatedObject.getString("registrationMark")).append("%'");
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
					selectBuffer.append("A." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "da_enterprise_info列表数据" + DateUtils.getCurrentDateStr() + ".xls";
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		JSONArray columnInfoList = new JSONArray();
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);

			if (json.containsKey("columnInfo")) {
				columnInfoList = json.getJSONArray("columnInfo");
			}
		}

		if (resultModel != null) {
			try {
				exportExcel(fileName, columnInfoList, resultModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}




