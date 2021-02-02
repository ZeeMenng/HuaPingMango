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
import com.zee.app.generate.swagger.da.DaEnterpriseInfoGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.bll.extend.unity.gp.GpOrganizationUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaEnterpriseInfo;
import com.zee.ent.extend.gp.GpOrganization;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaEnterpriseInfoParameter;
import com.zee.ent.parameter.da.DaPeasantInfoParameter;
import com.zee.set.enumer.DataControlEnum;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.set.enumer.EnterpriseTypeEnum;
import com.zee.set.enumer.OrgTypeEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaEnterpriseInfoGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daEnterpriseInfo")
public class  DaEnterpriseInfoSwgApp extends  DaEnterpriseInfoGenSwgApp {
	
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	
	@Autowired
	@Qualifier("gpOrganizationUntBll")
	protected GpOrganizationUntBll gpOrganizationUntBll;
	
	private StringBuffer getEnterpriseCommonSql(Byte code){
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT													                                              "); 
		selectBuffer.append("		A.id,                                                                                             "); 
		selectBuffer.append("		A.enterprise_name enterpriseName,                                                                 "); 
		selectBuffer.append("		A.enterprise_code enterpriseCode,                                                                 "); 
		selectBuffer.append("		A.corporation_name corporationName,                                                               "); 
		selectBuffer.append("		A.corporation_mobile corporationMobile,                                                           "); 
		selectBuffer.append("		A.linkman linkman,                                                                                "); 
		selectBuffer.append("		A.linkman_mobile linkmanMobile,                                                                   "); 
		selectBuffer.append("		A.enterprise_type_code enterpriseTypeCode,                                                        "); 
		selectBuffer.append("		A.enterprise_type_text enterpriseTypeText,                                                        "); 
		selectBuffer.append("		A.found_date foundDate,                                                                           "); 
		selectBuffer.append("		A.registered_capital registeredCapital,                                                           "); 
		selectBuffer.append("		A.region_code regionCode,                                                                         "); 
		selectBuffer.append("		A.region_text regionText,                                                                         "); 
		selectBuffer.append("		A.address address,                                                                                "); 
		selectBuffer.append("		A.enterprise_address enterpriseAddress,                                                           ");
		selectBuffer.append("		A.logo_resource_id logoResourceId,                                                                ");
		selectBuffer.append("		CONCAT('"+this.linkPath+"',A.logo_resource_path) logoResourcePath,                                "); 
		selectBuffer.append("		A.email email,                                                                                    "); 
		selectBuffer.append("		A.measure_longitude measureLongitude,                                                             "); 
		selectBuffer.append("		A.measure_latitude measureLatitude,                                                               "); 
		selectBuffer.append("		A.society_credit_id societyCreditId,                                                              "); 
		selectBuffer.append("		A.description description,		                                                                  ");
		selectBuffer.append("		D.audit_status_code auditStatusCode,                                                              "); 
		selectBuffer.append("		D.audit_status_text auditStatusText                                                               "); 
		selectBuffer.append("	FROM                                                                                                  "); 
		selectBuffer.append("		da_enterprise_info A                                                                              "); 
		selectBuffer.append(" 	INNER JOIN da_common_field D ON A.common_field_id = D.id  											  ");
		if(DataControlEnum.OWN.getCode().equals(code)){
			selectBuffer.append("	INNER JOIN gpr_user_organization B ON A.organization_id = B.organization_id         			  "); 
			selectBuffer.append("	INNER JOIN gp_user C ON C.id = B.user_id  AND C.id = '"+this.getCurrentUser().getId()+"'          "); 
		}
		selectBuffer.append("	WHERE 1=1												                                			  "); 
		
		return selectBuffer;
	}
	
	@ApiOperation(value = "获取用户的企业", notes = "获取用户的企业")
	@RequestMapping(value = "/getUserEnterprise", method = {RequestMethod.POST,RequestMethod.GET}, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getUserEnterprise() {
		ResultModel resultModel = new ResultModel();
		
		StringBuffer selectBuffer = this.getEnterpriseCommonSql(DataControlEnum.OWN.getCode());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());
		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		return resultModel;
	}
	
	/**
	 * 单条数据填报
	 */
	@ApiOperation(value = "新增记录（直报，通用后台）", notes = "新增单条记录（直报）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaEnterpriseInfo") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaEnterpriseInfo jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		//自动对应华坪县的组织机构
		ResultModel autoAddOrg = autoAddOrg(jsonData);
		jsonData.setOrganizationId(autoAddOrg.getObjectId());
		
		jsonData.getDaCommonField().setAddUserId(this.getCurrentUser().getId());
		ResultModel result = daCommonFieldUntBll.add(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			if(StringUtils.isNotBlank(jsonData.getLogoResourcePath()) && jsonData.getLogoResourcePath().contains(this.linkPath)){
				jsonData.setLogoResourcePath(jsonData.getLogoResourcePath().substring(this.linkPath.length(), jsonData.getLogoResourcePath().length()));
			}
			//自动生成企业编码
			addEnterpriseCode(jsonData);
			resultModel = daEnterpriseInfoUntBll.add(jsonData);
		}
		
		return resultModel;
	}
	
	private void addEnterpriseCode(DaEnterpriseInfo jsonData) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT A.enterprise_code enterpriseCode FROM da_enterprise_info A       ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		List<String> codeList = new ArrayList<String>();
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		for (Map<String, Object> map2 : modelList) {
			if(map2.get("enterpriseCode") != null){
				codeList.add(map2.get("enterpriseCode").toString());
			}
		}
		jsonData.setEnterpriseCode(getUniEnterpriseCode(codeList));
	}
	
	private String getUniEnterpriseCode(List<String> codeList){
		String enterpriseCode = Tools.getUUID().substring(0, 4);
		if(codeList.contains(enterpriseCode)){
			getUniEnterpriseCode(codeList);
		}
		return enterpriseCode;
	}

	private ResultModel autoAddOrg(DaEnterpriseInfo jsonData){
		GpOrganization gpOrganization = new GpOrganization();
		gpOrganization.setName(jsonData.getEnterpriseName());
		gpOrganization.setShortName(jsonData.getEnterpriseName());
		gpOrganization.setFartherId(CustomSymbolic.ORGANIZATION_ID_HUAPING_QIYE);
		gpOrganization.setTypeCode(OrgTypeEnum.QITA.getCode());
		gpOrganization.setTypeText(OrgTypeEnum.QITA.getText());
		gpOrganization.setLevelCode((byte) 3);
		gpOrganization.setLevelText("第三级");
		ResultModel resultModel = gpOrganizationUntBll.add(gpOrganization);
		return resultModel;
	}
	
	@ApiOperation(value = "单条查询（直报）", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daEnterpriseInfoUntBll.getModel(id);
		if(result.getIsSuccess()){
			DaEnterpriseInfo jsonData = (DaEnterpriseInfo) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			
			jsonData.setLogoResourcePath(linkPath + jsonData.getLogoResourcePath());
			
			result.setData(jsonData);
		}
		return result;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daEnterpriseInfoUntBll.getModel(id);
		if(result.getIsSuccess()){
			DaEnterpriseInfo jsonData = (DaEnterpriseInfo) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			
			jsonData.setLogoResourcePath(linkPath + jsonData.getLogoResourcePath());
			
			result.setData(jsonData);
		}
		return result;
	
	}

	/**
	 * 单体记录删除
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "删除记录（直报）", notes = "根据主键删除相应记录（直报）")
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
				resultModel = daEnterpriseInfoUntBll.delete(id);
			}
		}

		return resultModel;
	}
	/**
	 * 批量删除
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量删除（直报）", notes = "根据主键列表批量删除相应记录（直报）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaEnterpriseInfoDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaEnterpriseInfoParameter.DeleteByIdList jsonData) {
		
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
				resultModel = daEnterpriseInfoUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}
	/**
	 * 修改
	 * @param jsonData
	 * @return
	 */
	@ApiOperation(value = "修改记录（直报，通用后台）", notes = "修改指定记录（直报）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaEnterpriseInfo") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaEnterpriseInfo jsonData) {
		
		ResultModel resultModel = new ResultModel();
		if(StringUtils.isNotBlank(jsonData.getCommonFieldId())){
			jsonData.getDaCommonField().setId(jsonData.getCommonFieldId());
		}
		if(StringUtils.isNotBlank(jsonData.getDaCommonField().getId())){
			jsonData.getDaCommonField().setAddUserId(this.getCurrentUser().getId());
			daCommonFieldUntBll.update(jsonData.getDaCommonField());
		}
		if(StringUtils.isNotBlank(jsonData.getLogoResourcePath()) && jsonData.getLogoResourcePath().contains(this.linkPath)){
			jsonData.setLogoResourcePath(jsonData.getLogoResourcePath().substring(this.linkPath.length(), jsonData.getLogoResourcePath().length()));
		}
		if(jsonData.getEnterpriseTypeCode() != null){
			jsonData.setEnterpriseTypeText(EnterpriseTypeEnum.getText(jsonData.getEnterpriseTypeCode()));
		}
		resultModel = daEnterpriseInfoUntBll.update(jsonData);
		return resultModel;
	}
	/**
	 * 数据审核
	 * @param jsonData
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报）", notes = "数据审核（直报）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaEnterpriseInfoUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaPeasantInfoParameter.UpdateList jsonData) {
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
		selectBuffer.append("	INNER JOIN da_enterprise_info B ON A.id = B.common_field_id                               ");
		selectBuffer.append("	WHERE                                                                                   ");
		selectBuffer.append("		B.id IN ("+Tools.list2SqlStr(objIds)+")  								");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = daCommonFieldUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	/**
	 * 模糊查询（直报数据）
	 */
	@ApiOperation(value = "模糊查询（直报）", notes = "根据查询条件模糊查询（直报）")
	@RequestMapping(value = "/getListByJsonDatas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonDatas() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select  ");
		selectBuffer.append(" A.enterprise_name enterpriseName,  ");
		selectBuffer.append(" A.society_credit_id societyCreditId,  ");
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
		selectBuffer.append(" da_enterprise_info A  ");
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
					selectBuffer.append(" and B.region_code like '%").append(entityRelatedObject.getString("regionCode")).append("%'");
				if (entityRelatedObject.containsKey("enterpriseName") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseName")))
					selectBuffer.append(" and A.enterprise_name like '%").append(entityRelatedObject.getString("enterpriseName")).append("%'");
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

		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);

		return resultModel;
	}
	
	/**
	 * 导入excel
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量导入（直报）", notes = "批量导入多条记录（直报）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterpriseInfoAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaEnterpriseInfo> list1 = (List<DaEnterpriseInfo>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaEnterpriseInfo.class);
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
						//业务表里有涉及到字典表的，自行加入如下,  a700900a555b71ff47bb8cf7f7bb0956 ： gp_dictionary_type表主键id
						if(map.containsKey(DictionaryEnum.ENTERPRISE_TYPE_CODE.getText()+list1.get(i).getEnterpriseTypeText())){
							  String status = map.get(DictionaryEnum.ENTERPRISE_TYPE_CODE.getText()+list1.get(i).getEnterpriseTypeText()).toString();
								list1.get(i).setEnterpriseTypeCode(Byte.valueOf(status));//企业类型
							}
						if(map.containsKey(DictionaryEnum.INDUSTRY_TYPE_CODE.getText()+list1.get(i).getIndustryText())){
							  String status = map.get(DictionaryEnum.INDUSTRY_TYPE_CODE.getText()+list1.get(i).getIndustryText()).toString();
								list1.get(i).setIndustryCode(status);//企业产业类型
							}
						if(map.containsKey(DictionaryEnum.DI_BOOLEAN.getText()+list1.get(i).getIsCorporateChampionTypeText())){
							  String status = map.get(DictionaryEnum.DI_BOOLEAN.getText()+list1.get(i).getIsCorporateChampionTypeText()).toString();
								list1.get(i).setIsCorporateChampionTypeCode(Byte.valueOf(status));//是否龙头企业
							}
						//是否合作社
						//是否规模企业
						resultModel = daEnterpriseInfoUntBll.add(list1.get(i));
						
				}
		}
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
	 * 
	 * @title: 
	 * @description: 招商引资--企业基本信息列表
	 * @author: lxl
	 * @date:2018年5月23日 下午11:22:00
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

		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		StringBuffer selectBuffer = this.getEnterpriseCommonSql(DataControlEnum.ALL.getCode());
		if (jsonObject.containsKey("selectRows")) {
			JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
			if (selectRowsArray.size() > 0) {
				selectBuffer.append(" and dei.id in('");
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
			if (entityRelatedObject.containsKey("enterpriseTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseTypeCode")))
				selectBuffer.append(" and A.enterprise_type_code=").append(entityRelatedObject.getString("enterpriseTypeCode"));
			if (entityRelatedObject.containsKey("regionCode") && StringUtils.isNotBlank(entityRelatedObject.getString("regionCode")))
				//用等于华坪下面乡镇的企业查询不出来，所以改用like
				//selectBuffer.append(" and A.region_code = ").append(entityRelatedObject.getString("regionCode"));
			    selectBuffer.append(" and A.region_code like '%").append(entityRelatedObject.getString("regionCode")).append("%'");
			if (entityRelatedObject.containsKey("foundDateS") && StringUtils.isNotBlank(entityRelatedObject.getString("foundDateS")))
				selectBuffer.append(" and A.found_date >= '").append(entityRelatedObject.getString("foundDateS")+"' ");
			if (entityRelatedObject.containsKey("foundDateE") && StringUtils.isNotBlank(entityRelatedObject.getString("foundDateE")))
				selectBuffer.append(" and A.found_date <= '").append(entityRelatedObject.getString("foundDateE")+"' ");
			if (entityRelatedObject.containsKey("auditStatusCode") && StringUtils.isNotBlank(entityRelatedObject.getString("auditStatusCode")))
				selectBuffer.append(" and D.audit_status_code = ").append(entityRelatedObject.getString("auditStatusCode"));
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

		map.put("Sql", selectBuffer.toString());

		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);

		return resultModel;
	}
	/**
	 * 
	 * @title: getEnterpriseInfo
	 * @description: 招商引资--企业基本信息查看（存续）
	 * @author: lxl
	 * @date:2018年5月23日 下午11:22:00
	 * @param
	 * @return 
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getEnterpriseInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEnterpriseInfo() {
		ResultModel resultModel = new ResultModel();//基本信息
		ResultModel resultModel2 = new ResultModel();//股东信息
		ResultModel resultModel3 = new ResultModel();//行政许可
		ResultModel resultModel4 = new ResultModel();//企业资产状况信息
		ResultModel resultModel5 = new ResultModel();//疑似关系
		ResultModel resultModel6 = new ResultModel();//法院判决
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String id = "";// 企业id
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("id")
						&& !StringUtils.isBlank(entityRelatedObject.getString("id"))) {
					id = entityRelatedObject.getString("id");

				}
			}
		}
		//企业基本信息
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select")
					.append(" A.id id,")
					.append(" A.common_field_id commonFieldId,")
					.append(" A.enterprise_name enterpriseName,")
					.append(" A.registration_mark registrationMark,")
					.append(" A.archives_number archivesNumber,")
					.append(" A.society_credit_id societyCreditId,")
					.append(" A.register_status_text registerStatusText,")
					.append(" A.register_status_code registerStatusCode,")
					.append(" A.register_type_text registerTypeText,")
					.append(" A.register_type_code registerTypeCode,")
					.append(" A.statistics_Department_number statisticsDepartmentNumber,")
					.append(" A.found_date foundDate,")
					.append(" A.corporation_name corporationName,")
					.append(" A.corporation_mobile corporationMobile,")
					.append(" A.linkman linkman,")
					.append(" A.linkman_mobile linkmanMobile,")
					.append(" A.landline_telephone landlineTelephone,")
					.append(" A.enterprise_type_text enterpriseTypeText,")
					.append(" A.enterprise_type_code enterpriseTypeCode,")
					.append(" A.enterprise_nature_code enterpriseNatureCode,")
					.append(" A.enterprise_nature_text enterpriseNatureText,")
					.append(" A.industry_type_code industryTypeCode,")
					.append(" A.industry_type_text industryTypeText,")
					.append(" A.registered_capital registeredCapital,")
					.append(" A.paidin_capital paidinCapital,")
					.append(" A.address address,")
					.append(" A.enterprise_address enterpriseAddress,")
					.append(" A.enterprise_phone enterprisePhone,")
					.append(" A.administrative_area administrativeArea,")
					.append(" A.region_code regionCode,")
					.append(" A.region_text regionText,")
					.append(" A.measure_longitude measureLongitude,")
					.append(" A.measure_latitude measureLatitude,")
					.append(" A.establish_date establishDate,")
					.append(" A.approval_date approvalDate,")
					.append(" A.business_duetime businessDuetime,")
					.append(" A.longdistance_number longdistanceNumber,")
					.append(" A.administration_number administrationNumber,")
					.append(" A.business_scope businessScope,")
					.append(" A.professional_activity professionalActivity,")
					.append(" A.enterprise_control enterpriseControl,")
					.append(" A.organization_type_text organizationTypeText,")
					.append(" A.organization_type_code organizationTypeCode,")
					.append(" A.industry_activity industryActivity,")
					.append(" A.industry_code industryCode,")
					.append(" A.industry_text industryText,")
					.append(" A.risk_grade riskGrade,")
					.append(" A.risk_value riskValue,")
					.append(" A.category_code categoryCode,")
					.append(" A.category_text categoryText,")
					.append(" A.risk_tags_code riskTagsCode,")
					.append(" A.risk_tags_text riskTagsText,")
					.append(" A.funnel_tags_code funnelTagsCode,")
					.append(" A.funnel_tags_text funnelTagsText,")
					.append(" A.core_code coreCode,")
					.append(" A.core_text coreText,")
					.append(" A.person_scale personScale,")
					.append(" A.money_scale moneyScale,")
					.append(" A.year_they yearThey,")
					.append(" A.is_corporate_champion_type_code isCorporateChampionTypeCode,")
					.append(" A.is_corporate_champion_type_text isCorporateChampionTypeText,")
					.append(" A.scale_enterprise_type_code scaleEnterpriseTypeCode,")
					.append(" A.scale_enterprise_type_text scaleEnterpriseTypeText,")
					.append(" A.logo_resource_path logo,")
					.append(" A.email email")
					.append(" FROM")
					.append(" da_enterprise_info A")
					.append(" where A.id='"+id+"'");
		map.put("Sql", selectBuffer.toString());

		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		
		//股东信息
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("select")
					.append(" A.id id,")
					.append(" A.enterprise_id enterpriseId,")
					.append(" A.enterprise_name enterpriseName,")
					.append(" A.shareholder_name shareholderName,")
					.append(" A.nature_code natureCode,")
					.append(" A.nature_text natureText,")
					.append(" A.datasource_code datasourceCode,")
					.append(" A.subscribed_amount_sum subscribedAmountSum,")
					.append(" A.paid_amount_sum paidAmountSum,")
					.append(" A.share_rate shareRate,")
					.append(" A.remark remark,")
					.append(" A.created_time createdTime")
					.append(" FROM")
					.append(" da_enterprise_shareholder_info A")
					.append(" WHERE A.enterprise_id = '"+id+"'");
		map2.put("Sql", selectBuffer2.toString());
		resultModel2 = daEnterpriseInfoUntBll.getListBySQL(map2);
		
		//行政许可
		Map<String, Object> map3 = new HashMap<String, Object>();
		StringBuffer selectBuffer3 = new StringBuffer();
		selectBuffer3.append("select")
					.append(" A.id id,")
					.append(" A.enterprise_id enterpriseId,")
					.append(" A.enterprise_name enterpriseName,")
					.append(" A.order_by orderBy,")
					.append(" A.file_no fileNo,")
					.append(" A.file_name fileName,")
					.append(" A.file_path filePath,")
					.append(" A.start_date startDate,")
					.append(" A.end_date endDate,")
					.append(" A.organ organ,")
					.append(" A.content content,")
					.append(" A.certificate_status_code certificateStatusCode,")
					.append(" A.certificate_status_text certificateStatusText,")
					.append(" A.certificate_type_code certificateTypeCode,")
					.append(" A.certificate_type_text certificateTypeText,")
					.append(" A.publicity_time publicityTime,")
					.append(" A.detail detail,")
					.append(" A.datasource_code datasourceCode,")
					.append(" A.remark remark,")
					.append(" A.created_time createdTime")
					.append(" FROM")
					.append(" da_enterprise_certificate_info A")
					.append(" WHERE A.enterprise_id = '"+id+"'");
		map3.put("Sql", selectBuffer3.toString());
		resultModel3 = daEnterpriseInfoUntBll.getListBySQL(map3);
		//企业资产状况信息
		Map<String, Object> map4 = new HashMap<String, Object>();
		StringBuffer selectBuffer4 = new StringBuffer();
		selectBuffer4.append("select * from (select")
					.append(" A.total_assets totalAssets,")//
					.append(" A.total_liabilities totalLiabilities,")//
					.append(" A.total_owners_equity totalOwnersEquity,")//
					.append(" A.operating_income operatingIncome,")//
					.append(" A.total_profit totalProfit,")//
					.append(" A.net_profit netProfit")//
					.append(" FROM")
					.append(" da_enterprise_property_info A")
					.append(" where A.enterprise_id ='"+id+"' order by year desc limit 0,1) t");
		map4.put("Sql", selectBuffer4.toString());
		resultModel4 = daEnterpriseInfoUntBll.getListBySQL(map4);
		
		//企业疑似关系
		Map<String, Object> map5 = new HashMap<String, Object>();
		StringBuffer selectBuffer5 = new StringBuffer();
		selectBuffer5.append("select")
					.append(" A.id id,")
					.append(" A.enterprise_name enterpriseName,")
					.append(" A.registration_mark registrationMark,")
					.append(" A.archives_number archivesNumber,")
					.append(" A.society_credit_id societyCreditId,")
					.append(" A.register_status_text registerStatusText,")
					.append(" A.register_status_code registerStatusCode,")
					.append(" A.register_type_text registerTypeText,")
					.append(" A.register_type_code registerTypeCode,")
					.append(" A.statistics_Department_number statisticsDepartmentNumber,")
					.append(" A.found_date foundDate,")
					.append(" A.corporation_name corporationName,")
					.append(" A.corporation_mobile corporationMobile,")
					.append(" A.linkman linkman,")
					.append(" A.linkman_mobile linkmanMobile,")
					.append(" A.landline_telephone landlineTelephone,")
					.append(" A.enterprise_type_text enterpriseTypeText,")
					.append(" A.enterprise_type_code enterpriseTypeCode,")
					.append(" A.enterprise_nature_code enterpriseNatureCode,")
					.append(" A.enterprise_nature_text enterpriseNatureText,")
					.append(" A.industry_type_code industryTypeCode,")
					.append(" A.industry_type_text industryTypeText,")
					.append(" A.registered_capital registeredCapital,")
					.append(" A.paidin_capital paidinCapital,")
					.append(" A.address address,")
					.append(" A.enterprise_address enterpriseAddress,")
					.append(" A.enterprise_phone enterprisePhone,")
					.append(" A.administrative_area administrativeArea,")
					.append(" A.region_code regionCode,")
					.append(" A.region_text regionText,")
					.append(" A.measure_longitude measureLongitude,")
					.append(" A.measure_latitude measureLatitude,")
					.append(" A.establish_date establishDate,")
					.append(" A.approval_date approvalDate,")
					.append(" A.business_duetime businessDuetime,")
					.append(" A.longdistance_number longdistanceNumber,")
					.append(" A.administration_number administrationNumber,")
					.append(" A.business_scope businessScope,")
					.append(" A.professional_activity professionalActivity,")
					.append(" A.enterprise_control enterpriseControl,")
					.append(" A.organization_type_text organizationTypeText,")
					.append(" A.organization_type_code organizationTypeCode,")
					.append(" A.industry_activity industryActivity,")
					.append(" A.industry_code industryCode,")
					.append(" A.industry_text industryText,")
					.append(" A.risk_grade riskGrade,")
					.append(" A.risk_value riskValue,")
					.append(" A.category_code categoryCode,")
					.append(" A.category_text categoryText,")
					.append(" A.risk_tags_code riskTagsCode,")
					.append(" A.risk_tags_text riskTagsText,")
					.append(" A.funnel_tags_code funnelTagsCode,")
					.append(" A.funnel_tags_text funnelTagsText,")
					.append(" A.core_code coreCode,")
					.append(" A.core_text coreText,")
					.append(" A.person_scale personScale,")
					.append(" A.money_scale moneyScale,")
					.append(" A.year_they yearThey,")
					.append(" A.is_corporate_champion_type_code isCorporateChampionTypeCode,")
					.append(" A.is_corporate_champion_type_text isCorporateChampionTypeText,")
					.append(" A.scale_enterprise_type_code scaleEnterpriseTypeCode,")
					.append(" A.scale_enterprise_type_text scaleEnterpriseTypeText")
					.append(" FROM")
					.append(" da_enterprise_info A  LEFT JOIN da_enterprise_cognate_info  B ")
					.append(" on B.cognate_enterprise_id=A.id")
					.append(" where B.enterprise_id='"+id+"'");
		map5.put("Sql", selectBuffer5.toString());
		resultModel5 = daEnterpriseInfoUntBll.getListBySQL(map5);
		//法院判决
		Map<String, Object> map6 = new HashMap<String, Object>();
		StringBuffer selectBuffer6 = new StringBuffer();
		selectBuffer6.append("select")
					.append(" A.id id,")
					.append(" A.enterprise_name enterpriseName")
					.append(" FROM")
					.append(" da_enterprise_litigation_info A  ")
					.append(" where A.enterprise_id='"+id+"'");
		map6.put("Sql", selectBuffer6.toString());
		resultModel6 = daEnterpriseInfoUntBll.getListBySQL(map6);
		
		List<Map<String, Object>> list =  (List<Map<String, Object>>) resultModel.getData();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("enterpriseBasicInfo", list.get(0));// 基本信息
		maps.put("shareholderInfo", resultModel2.getData());// 股东信息
		maps.put("administrationInfo", resultModel3.getData());// 行政许可
		maps.put("assetsInfo", resultModel4.getData());// 企业资产状况信息
		maps.put("doubtfulRelationship", resultModel5.getData());//疑似关系
		maps.put("courtDecision", resultModel6.getData());//疑似关系
		maps.put("enterpriseName", list.get(0).get("enterpriseName"));
		maps.put("corporationName", list.get(0).get("corporationName"));
		maps.put("linkmanMobile", list.get(0).get("linkmanMobile"));
		maps.put("address", list.get(0).get("address"));
		maps.put("email", list.get(0).get("email"));
		
		Map<String, Object> relationshipMap = new HashMap<String, Object>();
		relationshipMap.put("股东", resultModel2.getData());
		relationshipMap.put("高管", new ArrayList());
		relationshipMap.put("对外投资", new ArrayList());
		relationshipMap.put("法院判决", new ArrayList());
		relationshipMap.put("法院公告", new ArrayList());
		relationshipMap.put("历史股东", new ArrayList());
		relationshipMap.put("疑似关系", resultModel5.getData());
		maps.put("relationship", relationshipMap);
		ResultModel result = new ResultModel();//基本信息
		result.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		result.setData(maps);
		return result;
		
	}
	
	@ApiOperation(value = "获取芒果微网站列表", notes = "获取芒果微网站列表")
	@RequestMapping(value = "/getEnterpriseList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEnterpriseList() {
		ResultModel resultModel = new ResultModel();//基本信息
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String regionId = "";//地区regionId;
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
				regionId = entityRelatedObject.getString("regionId");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT * FROM (
		SELECT 
		B.`id` AS id,
		B.enterprise_name AS enterpriseName,
		B.`description` AS description,
		D.path AS path
		FROM dar_enterprise_resource A
		LEFT JOIN da_enterprise_info B ON A.enterprise_id = B.id 
		LEFT JOIN da_common_field C ON B.`common_field_id` = C.`id`
		LEFT JOIN gp_resource D ON A.`resource_id` = D.`id`
		ORDER BY C.`add_time` DESC ) t0 GROUP BY id*/ 
		
		selectBuffer.append(" SELECT * FROM (																");
		selectBuffer.append(" SELECT 																		");
		selectBuffer.append(" B.`id` AS id,																	");
		selectBuffer.append(" B.enterprise_name AS enterpriseName,											");
		selectBuffer.append(" B.`description` AS description,												");
		selectBuffer.append(" CONCAT('");
		selectBuffer.append(this.linkPath);
		selectBuffer.append("',");
		selectBuffer.append(" D.path) AS path																");
		selectBuffer.append(" FROM dar_enterprise_resource A												");
		selectBuffer.append(" LEFT JOIN da_enterprise_info B ON A.enterprise_id = B.id 						");
//		selectBuffer.append(" LEFT JOIN da_common_field C ON B.`common_field_id` = C.`id`					");
		selectBuffer.append(" LEFT JOIN gp_resource D ON A.`resource_id` = D.`id`							");
		selectBuffer.append(" WHERE B.region_code like'%");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("%'");
		selectBuffer.append(" AND A.type_code = 1) t0 GROUP BY id											");
		
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	@ApiOperation(value = "获取企业推介轮播图", notes = "获取企业推介轮播图")
	@RequestMapping(value = "/getEnterpriseRecommendPicList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEnterpriseRecommendPicList() {
		ResultModel resultModel = new ResultModel();//基本信息
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String enterpriseId = "";//企业id
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("enterpriseId") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseId")))
				enterpriseId = entityRelatedObject.getString("enterpriseId");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
	        B.`id` AS id,
	        B.enterprise_name AS enterpriseName,
	        CONCAT(
	            'http://202.106.10.250:63011/res',
	            D.path
	        ) AS path 
	    FROM
	        dar_enterprise_resource A 
	        LEFT JOIN da_enterprise_info B 
	            ON A.enterprise_id = B.id 
	        LEFT JOIN gp_resource D 
	            ON A.`resource_id` = D.`id`
	    WHERE B.id = 'dc0f69ff004949325cab1ee7b3af8458'
	        AND A.type_code = '1'*/
		
		selectBuffer.append(" SELECT																				");
		selectBuffer.append(" B.`id` AS id,																			");
		selectBuffer.append(" B.enterprise_name AS enterpriseName,													");
		selectBuffer.append(" CONCAT('");
		selectBuffer.append(this.linkPath);
		selectBuffer.append("',");
		selectBuffer.append(" D.path) AS path																		");
		selectBuffer.append(" FROM																					");
		selectBuffer.append(" dar_enterprise_resource A 															");
		selectBuffer.append(" LEFT JOIN da_enterprise_info B 														");
		selectBuffer.append(" ON A.enterprise_id = B.id 															");
		selectBuffer.append(" LEFT JOIN gp_resource D 																");
		selectBuffer.append(" ON A.`resource_id` = D.`id`															");
		selectBuffer.append(" WHERE B.id = 																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(enterpriseId)) {
			selectBuffer.append(enterpriseId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.type_code = 1																	");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		
		Object obj = resultModel.getData();
		List<Map<String,Object>> list = null;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<String> pathList = new ArrayList<String>();
		if(obj!=null) {
			list = (List<Map<String, Object>>) obj;
			if(list.size()>=1) {
				for(Map<String,Object> tempMap : list) {
					if(tempMap.containsKey("id")) {
						resultMap.put("enterpriseId", (String)tempMap.get("id"));
					}
					if(tempMap.containsKey("path")) {
						String path = (String) tempMap.get("path");
						pathList.add(path);
					}
				}
				resultMap.put("paths", pathList);
			}
		}
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	@ApiOperation(value = "获取企业简介", notes = "获取企业简介")
	@RequestMapping(value = "/getEnterpriseSummary", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEnterpriseSummary() {
		ResultModel resultModel = new ResultModel();//基本信息
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String enterpriseId = "";//企业id
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("enterpriseId") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseId")))
				enterpriseId = entityRelatedObject.getString("enterpriseId");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    B.`id` AS id,
		    B.enterprise_name AS enterpriseName,
		    B.summary AS summary,
		    B.`description` AS description,
		    B.`address` AS address,
		    B.`landline_telephone` AS landlineTelephone,
		    B.`linkman` AS linkMan,
		    B.`linkman_mobile` AS linkmanMobile,
		    B.`email` AS email,
		    B.`zip_code` AS zipCode,
		    B.`telautogram` AS telautogram,
		    B.`is_recommend` AS isRecommend, 
		    CONCAT(
		        'http://202.106.10.250:63011/res',
		        D.path
		    ) AS path 
		FROM
		    dar_enterprise_resource A 
		    LEFT JOIN da_enterprise_info B 
		        ON A.enterprise_id = B.id 
		    LEFT JOIN gp_resource D 
		        ON A.`resource_id` = D.`id` 
		WHERE B.id = 'dc0f69ff004949325cab1ee7b3af8458' 
		    AND A.type_code = 2 */
		
		selectBuffer.append(" SELECT																				");
		selectBuffer.append(" B.`id` AS id,																			");
		selectBuffer.append(" B.enterprise_name AS enterpriseName,													");
		selectBuffer.append(" B.summary AS summary,																	");
		selectBuffer.append(" B.`description` AS description,														");
		selectBuffer.append(" B.`address` AS address,																");
		selectBuffer.append(" B.`landline_telephone` AS landlineTelephone,											");
		selectBuffer.append(" B.`linkman` AS linkMan,																");
		selectBuffer.append(" B.`linkman_mobile` AS linkmanMobile,													");
		selectBuffer.append(" B.`email` AS email,																	");
		selectBuffer.append(" B.`zip_code` AS zipCode,																");
		selectBuffer.append(" B.`telautogram` AS telautogram,														");
		selectBuffer.append(" B.`is_recommend` AS isRecommend, 														");
		selectBuffer.append(" CONCAT('");
		selectBuffer.append(this.linkPath);
		selectBuffer.append("',");
		selectBuffer.append(" D.path) AS path																		");
		selectBuffer.append(" FROM																					");
		selectBuffer.append(" dar_enterprise_resource A 															");
		selectBuffer.append(" LEFT JOIN da_enterprise_info B 														");
		selectBuffer.append(" ON A.enterprise_id = B.id 															");
		selectBuffer.append(" LEFT JOIN gp_resource D 																");
		selectBuffer.append(" ON A.`resource_id` = D.`id`															");
		selectBuffer.append(" WHERE B.id = 																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(enterpriseId)) {
			selectBuffer.append(enterpriseId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.type_code = 2																	");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		
		Object obj = resultModel.getData();
		List<Map<String,Object>> list = null;
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<String> pathList = new ArrayList<String>();
		if(obj!=null) {
			list = (List<Map<String, Object>>) obj;
			if(list.size()>=1) {
				
				Map<String,Object> firstMap = list.get(0);
				if(firstMap.containsKey("id")) {
					resultMap.put("enterpriseId", (String)firstMap.get("id"));
				}
				if(firstMap.containsKey("enterpriseName")) {
					resultMap.put("enterpriseName", (String)firstMap.get("enterpriseName"));
				}
				if(firstMap.containsKey("summary")) {
					resultMap.put("summary", (String)firstMap.get("summary"));
				}
				if(firstMap.containsKey("description")) {
					resultMap.put("description", (String)firstMap.get("description"));
				}
				if(firstMap.containsKey("address")) {
					resultMap.put("address", (String)firstMap.get("address"));
				}
				if(firstMap.containsKey("landlineTelephone")) {
					resultMap.put("landlineTelephone", (String)firstMap.get("landlineTelephone"));
				}
				if(firstMap.containsKey("linkMan")) {
					resultMap.put("linkMan", (String)firstMap.get("linkMan"));
				}
				if(firstMap.containsKey("linkmanMobile")) {
					resultMap.put("linkmanMobile", (String)firstMap.get("linkmanMobile"));
				}
				if(firstMap.containsKey("email")) {
					resultMap.put("email", (String)firstMap.get("email"));
				}
				if(firstMap.containsKey("zipCode")) {
					resultMap.put("zipCode", (String)firstMap.get("zipCode"));
				}
				if(firstMap.containsKey("telautogram")) {
					resultMap.put("telautogram", (String)firstMap.get("telautogram"));
				}
				if(firstMap.containsKey("isRecommend")) {
					int b = (Integer)firstMap.get("isRecommend");
					resultMap.put("isRecommend",b+"");
				}
				
				for(Map<String,Object> tempMap : list) {
					
					if(tempMap.containsKey("path")) {
						String path = (String) tempMap.get("path");
						pathList.add(path);
					}
				}
				resultMap.put("paths", pathList);
			}
		}
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		
		return resultModel;
	}
	
	
	@ApiOperation(value = "获取企业工商信息", notes = "获取企业工商信息")
	@RequestMapping(value = "/getEnterpriseIndustryAndCommerceInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEnterpriseIndustryAndCommerceInfo() {
		ResultModel resultModel = new ResultModel();//基本信息
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String enterpriseId = "";//企业id
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("enterpriseId") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseId")))
				enterpriseId = entityRelatedObject.getString("enterpriseId");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		A.`id` AS id,
		A.enterprise_name AS enterpriseName,
		A.society_credit_id AS societyCreditId,
		A.organization_code AS organizationCode,
		A.registration_mark AS registrationMark,
		A.manage_status AS manageStatus,
		A.trade_text AS tradeText,
		A.establish_date AS establishDate,
		A.enterprise_type_text AS enterpriseTypeText,
		A.business_duetime AS businessDuetime,
		A.corporation_name AS corporationName,
		A.approval_date AS approvalDate,
		A.registered_capital AS registeredCapital,
		A.registry_office AS registryOffice,
		A.address AS address,
		A.business_scope AS businessScope
		FROM da_enterprise_info A
		WHERE A.`id`= 'dc0f69ff004949325cab1ee7b3af8458'*/
		
		selectBuffer.append(" SELECT																				");
		selectBuffer.append(" A.`id` AS id,																			");
		selectBuffer.append(" A.enterprise_name AS enterpriseName,													");
		selectBuffer.append(" A.society_credit_id AS societyCreditId,												");
		selectBuffer.append(" A.organization_code AS organizationCode,												");
		selectBuffer.append(" A.registration_mark AS registrationMark,												");
		selectBuffer.append(" A.manage_status AS manageStatus,														");
		selectBuffer.append(" A.trade_text AS tradeText,															");
		selectBuffer.append(" A.establish_date AS establishDate,													");
		selectBuffer.append(" A.enterprise_type_text AS enterpriseTypeText,											");
		selectBuffer.append(" A.business_duetime AS businessDuetime,												");
		selectBuffer.append(" A.corporation_name AS corporationName,												");
		selectBuffer.append(" A.approval_date AS approvalDate,														");
		selectBuffer.append(" A.registered_capital AS registeredCapital,											");
		selectBuffer.append(" A.registry_office AS registryOffice,													");
		selectBuffer.append(" A.address AS address,																	");
		selectBuffer.append(" A.business_scope AS businessScope														");
		selectBuffer.append(" FROM da_enterprise_info A																");
		selectBuffer.append(" WHERE A.`id`= ");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(enterpriseId)) {
			selectBuffer.append(enterpriseId);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	
	@ApiOperation(value = "获取企业视频介绍", notes = "获取企业视频介绍")
	@RequestMapping(value = "/getEnterpriseVideoInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEnterpriseVideoInfo() {
		ResultModel resultModel = new ResultModel();//基本信息
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String enterpriseId = "";//企业id
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("enterpriseId") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseId")))
				enterpriseId = entityRelatedObject.getString("enterpriseId");
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    B.`id` AS id,
		    B.enterprise_name AS enterpriseName, 
		    CONCAT(
		        'http://202.106.10.250:63011/res',
		        D.path
		    ) AS path 
		FROM
		    dar_enterprise_resource A 
		    LEFT JOIN da_enterprise_info B 
		        ON A.enterprise_id = B.id 
		    LEFT JOIN gp_resource D 
		        ON A.`resource_id` = D.`id` 
		WHERE B.id = 'dc0f69ff004949325cab1ee7b3af8458' 
		    AND A.type_code = 3 */
		
		selectBuffer.append(" SELECT																				");
		selectBuffer.append(" B.`id` AS id,																			");
		selectBuffer.append(" B.enterprise_name AS enterpriseName,													");
		selectBuffer.append(" CONCAT('");
		selectBuffer.append(this.linkPath);
		selectBuffer.append("',");
		selectBuffer.append(" D.path) AS path																		");
		selectBuffer.append(" FROM																					");
		selectBuffer.append(" dar_enterprise_resource A 															");
		selectBuffer.append(" LEFT JOIN da_enterprise_info B 														");
		selectBuffer.append(" ON A.enterprise_id = B.id 															");
		selectBuffer.append(" LEFT JOIN gp_resource D 																");
		selectBuffer.append(" ON A.`resource_id` = D.`id`															");
		selectBuffer.append(" WHERE B.id = 																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(enterpriseId)) {
			selectBuffer.append(enterpriseId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.type_code = 3																	");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	/**
	 * 芒果地图——相关企业分布——弹出列表 tabType=3
	 * 芒果地图——芒果电商分布——弹出列表tabType=2
	 */
	@ApiOperation(value = "查询芒果相关企业分布弹出列表", notes = "根据查询条件查询芒果相关企业分布弹出列表")
	@RequestMapping(value = "/getTabByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getEnterpriseByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String tabType ="";
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("tabType") && StringUtils.isNotBlank(entityRelatedObject.getString("tabType")))
					tabType = entityRelatedObject.getString("tabType");
			}
		}


		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		if(tabType.equals("3")){
			selectBuffer.append("select case when enterprise_name is null then '暂无信息' WHEN enterprise_name = '' THEN '暂无信息' else enterprise_name end enterpriseName,");
			selectBuffer.append("case when society_credit_id is null then '暂无信息' WHEN society_credit_id = '' THEN '暂无信息' else society_credit_id end societyId,");
			selectBuffer.append("case when registered_capital is null then '暂无信息' WHEN registered_capital = '' THEN '暂无信息' else registered_capital end registerCapital,");
			selectBuffer.append("case when business_scope is null then '暂无信息' WHEN business_scope = '' THEN '暂无信息' else business_scope end businessScope,");
			selectBuffer.append("case when enterprise_address is null then '暂无信息' WHEN enterprise_address = '' THEN '暂无信息' else enterprise_address end address, ");
			selectBuffer.append("case when enterprise_phone is null then '暂无信息' WHEN enterprise_phone = '' THEN '暂无信息' else enterprise_phone end tel ");
			selectBuffer.append(" from da_enterprise_info where 1=1 ");	
		}else if (tabType.equals("2")){
			selectBuffer.append("select case when name is null then '暂无信息' WHEN name = '' THEN '暂无信息' else name end onlineName, ");
			selectBuffer.append("case when address is null then '暂无信息' WHEN address = '' THEN '暂无信息' else address end address, ");
			selectBuffer.append("case when tel is null then '暂无信息' WHEN tel = '' THEN '暂无信息' else tel end tel ");
			selectBuffer.append("from da_online_retailers where 1=1 ");
		}else{
			return resultModel;
		}
		
        
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("regionCode") && StringUtils.isNotBlank(entityRelatedObject.getString("regionCode")))
					selectBuffer.append(" and region_code like '%").append(entityRelatedObject.getString("regionCode")).append("%'");
			}

			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
			}

			if(tabType.equals("3")){
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
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = daEnterpriseInfoUntBll.getListBySQL(map);

		return resultModel;
	}
	
}



