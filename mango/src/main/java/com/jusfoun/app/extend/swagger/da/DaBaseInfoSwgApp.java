package com.zee.app.extend.swagger.da;

import java.io.FileInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.zee.app.generate.swagger.da.DaBaseInfoGenSwgApp;
import com.zee.bll.extend.unity.da.DaBaseInfoUntBll;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.da.DaIotMonitorDataUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaAltitudeInfo;
import com.zee.ent.extend.da.DaBaseInfo;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaImportExport;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaBaseInfoParameter;
import com.zee.ent.parameter.da.DaImportExportParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.TimesView;
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
 * @description  对外接口，扩展自DaBaseInfoGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daBaseInfo")
public class  DaBaseInfoSwgApp extends  DaBaseInfoGenSwgApp {

	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	
	@Autowired
	@Qualifier("daIotMonitorDataUntBll")
	protected DaIotMonitorDataUntBll daIotMonitorDataUntBll;
	
	@Autowired
	@Qualifier("daBaseInfoUntBll")
	protected DaBaseInfoUntBll daBaseInfoUntBll;
	
	
	@ApiOperation(value = "新增记录（直报数据）", notes = "新增单条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaBaseInfo") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaBaseInfo jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.add(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			resultModel = daBaseInfoUntBll.add(jsonData);
		}
		
		return resultModel;
	}

	@ApiOperation(value = "删除记录（直报数据）", notes = "根据主键删除相应记录（直报数据）")
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
				resultModel = daBaseInfoUntBll.delete(id);
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "批量删除（直报数据）", notes = "根据主键列表批量删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaBaseInfoDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaBaseInfoParameter.DeleteByIdList jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel commonModel = getCommonIds(jsonData.getIdList());
		ArrayList<String> idList = new ArrayList<String>();
		if(commonModel.getIsSuccess()){
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) commonModel.getData();
			for (Map<String, Object> map : modelList) {
				idList.add(map.get("id").toString());
			}
			ResultModel result = daCommonFieldUntBll.deleteByIdList(idList);
			if(result.getIsSuccess()){
				resultModel = daBaseInfoUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "修改记录（直报数据）", notes = "修改指定记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaBaseInfo") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaBaseInfo jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			resultModel = daBaseInfoUntBll.update(jsonData);
		}
		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaBaseInfoUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaBaseInfoParameter.UpdateList jsonData) {
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
	 * 导入excel
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量导入（直报数据）", notes = "批量导入多条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaBaseInfoAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaBaseInfo> list1 = (List<DaBaseInfo>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaImportExport.class);
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
						//业务表里有涉及到字典表的，自行加入如下,  DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText() ： gp_dictionary_type表主键id
						if(map.containsKey(DictionaryEnum.BASE_TYPE_CODE.getText()+list1.get(i).getBaseTypeText())){
							  String status = map.get(DictionaryEnum.BASE_TYPE_CODE.getText()+list1.get(i).getBaseTypeText()).toString();
								list1.get(i).setBaseTypeCode(Byte.valueOf(status));//基地类型
							}
						if(map.containsKey(DictionaryEnum.IDENTIFICATION_TYPE_CODE.getText()+list1.get(i).getIdentificationTypeText())){
							  String status = map.get(DictionaryEnum.IDENTIFICATION_TYPE_CODE.getText()+list1.get(i).getIdentificationTypeText()).toString();
								list1.get(i).setIdentificationTypeCode(Byte.valueOf(status));//认证类型
							}
						if(map.containsKey(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getBaseAreaUnit())){
							  String status = map.get(DictionaryEnum.IDENTIFICATION_AREA_CODE.getText()+list1.get(i).getBaseAreaUnit()).toString();
								list1.get(i).setBaseAreaUnit(Byte.valueOf(status));//面积单位
							}
						resultModel = daBaseInfoUntBll.add(list1.get(i));
						
					
				}
		}
		return resultModel;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daBaseInfoUntBll.getModel(id);

		if(result.getIsSuccess()){
			DaBaseInfo jsonData = (DaBaseInfo) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			result.setData(jsonData);
		}
		return result;
	}
	
	@ApiOperation(value = "模糊查询（直报数据）", notes = "根据查询条件模糊查询（直报数据）")
	@RequestMapping(value = "/getListByJsonDatas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonDatas() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
//		selectBuffer.append("select A.id id,A.common_field_id commonFieldId,A.name name,A.base_code baseCode,A.base_name baseName,A.address address,A.base_longitude baseLongitude,A.base_latitude baseLatitude,A.base_area baseArea,A.base_area_unit baseAreaUnit,A.area_unit areaUnit,A.base_type_code baseTypeCode,A.base_type_text baseTypeText,A.owner owner,A.identification_type_code identificationTypeCode,A.identification_type_text identificationTypeText,A.peasant_count peasantCount,A.enterprise_id enterpriseId  from da_base_info A inner join da_base_info B on A.id=B.id where 1=1 ");
		selectBuffer.append(" select  ");
		selectBuffer.append(" A.id id,  ");
		selectBuffer.append(" A.common_field_id commonFieldId,  ");
		selectBuffer.append(" A.base_code baseCode,  ");
		selectBuffer.append(" A.base_name baseName,  ");
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
		selectBuffer.append(" da_base_info A  ");
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
				if (entityRelatedObject.containsKey("baseCode") && StringUtils.isNotBlank(entityRelatedObject.getString("baseCode")))
					selectBuffer.append(" and A.base_code like '%").append(entityRelatedObject.getString("baseCode")).append("%'");
				if (entityRelatedObject.containsKey("baseName") && StringUtils.isNotBlank(entityRelatedObject.getString("baseName")))
					selectBuffer.append(" and A.base_name like '%").append(entityRelatedObject.getString("baseName")).append("%'");
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

		resultModel = daBaseInfoUntBll.getListBySQL(map);

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
		selectBuffer.append("	INNER JOIN da_base_info B ON A.id = B.common_field_id                              	    ");
		selectBuffer.append("	WHERE                                                                                   ");
		selectBuffer.append("		B.id IN ("+Tools.list2SqlStr(objIds)+")  								            ");
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
		selectBuffer.append("  GROUP BY name ");
		
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
	 * 获取芒果基地信息列表
	 * @return
	 */
	@ApiOperation(value = "获取芒果基地信息列表", notes = "获取芒果基地信息列表")
	@RequestMapping(value = "/getBaseList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getBaseList() {
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
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		A.base_code AS baseCode,
		A.NAME AS NAME,
		A.`address` AS address,
		A.`area_unit` AS areaSum,
		A.`base_longitude` AS longitude,
		A.`base_latitude` AS latitude
		FROM da_base_info A ,da_common_field B 
		WHERE A.common_field_id = B.id																								
		AND B.area_latitude_type_code = 4	
		AND B.region_id = '530723';*/
		
		selectBuffer.append(" SELECT																				");
		selectBuffer.append(" A.base_code AS baseCode,																");
		selectBuffer.append(" A.name AS baseName,																	");
		selectBuffer.append(" A.`address` AS address,																");
		selectBuffer.append(" A.`area_unit` AS areaSum,																");
		selectBuffer.append(" A.`base_longitude` AS longitude,														");
		selectBuffer.append(" A.`base_latitude` AS latitude															");
		selectBuffer.append(" FROM da_base_info A ,da_common_field B 												");
		selectBuffer.append(" WHERE A.common_field_id = B.id														");
		selectBuffer.append(" AND B.area_latitude_type_code = 4														");
		selectBuffer.append(" AND B.region_id =																		");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daBaseInfoUntBll.getListBySQL(map);
		return resultModel;
	}
	
	/**
	 * 获取芒果基地设备信息列表
	 * @return
	 */
	@ApiOperation(value = "根据芒果基地base_code获取设备信息列表", notes = "根据芒果基地base_code获取设备信息列表")
	@RequestMapping(value = "/getDeviceListByBaseCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getDeviceListByBaseCode() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String regionId = "";//地区id
		String baseCode = "";//基地base_code
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("baseCode") && StringUtils.isNotBlank(entityRelatedObject.getString("baseCode")))
					baseCode = entityRelatedObject.getString("baseCode");
				
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		A.base_code AS baseCode,
		A.NAME AS baseName,
		C.hid AS hid,
		C.device_name AS deviceName,
		C.`longitude` AS longitude,
		C.`latitude` AS latitude,
		C.`mango_type` AS mangoType
		FROM da_base_info A 
		LEFT JOIN da_common_field B ON A.common_field_id = B.id
		LEFT JOIN da_iot_monitor_basic C ON C.basc_code = A.`base_code`
		WHERE B.area_latitude_type_code = 4	
		AND B.region_id = '530723'
		AND A.base_code='530723001';*/
		
		selectBuffer.append(" SELECT																					");
		selectBuffer.append(" A.base_code AS baseCode,																	");
		selectBuffer.append(" A.name AS baseName,																		");
		selectBuffer.append(" C.hid AS hid,																				");
		selectBuffer.append(" C.device_name AS deviceName,																");
		selectBuffer.append(" C.`longitude` AS longitude,																");
		selectBuffer.append(" C.`latitude` AS latitude,																	");
		selectBuffer.append(" C.`mango_type` AS mangoType																");
		selectBuffer.append(" FROM da_base_info A 																		");
		selectBuffer.append(" LEFT JOIN da_common_field B ON A.common_field_id = B.id									");
		selectBuffer.append(" LEFT JOIN da_iot_monitor_basic C ON C.basc_code = A.`base_code`							");
		selectBuffer.append(" WHERE B.area_latitude_type_code = 4														");
		selectBuffer.append(" AND B.region_id =																			");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.base_code=																			");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(baseCode)) {
			selectBuffer.append(baseCode);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daBaseInfoUntBll.getListBySQL(map);
		return resultModel;
	}
	
	/**
	 * 根据设备id获取芒果基地数据
	 * @return
	 */
	@ApiOperation(value = "根据设备id获取芒果基地数据", notes = "根据设备id获取芒果基地数据")
	@RequestMapping(value = "/getBaseInfoByDeviceId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getBaseInfoByDeviceId() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String regionId = "";//地区id
		String deviceId = "";//设备id
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("deviceId") && StringUtils.isNotBlank(entityRelatedObject.getString("deviceId")))
					deviceId = entityRelatedObject.getString("deviceId");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		  A.base_code AS baseCode,
		  A.name AS baseName,
		  A.`area_unit` AS areaSum,
		  A.`peasant_count` AS peasantCount,
		  C.`mango_type` AS mangoType 
		FROM
		  da_base_info A 
		  LEFT JOIN da_common_field B 
		    ON A.common_field_id = B.id 
		  LEFT JOIN da_iot_monitor_basic C 
		    ON C.basc_code = A.`base_code` 
		WHERE B.area_latitude_type_code = 4 
		  AND B.region_id = '530723' 
		  AND C.hid = '16057143' */
		
		selectBuffer.append(" SELECT 																						");
		selectBuffer.append(" A.base_code AS baseCode,																		");
		selectBuffer.append(" A.name AS baseName,																			");
		selectBuffer.append(" A.`area_unit` AS areaSum,																		");
		selectBuffer.append(" A.`peasant_count` AS peasantCount,															");
		selectBuffer.append(" C.`mango_type` AS mangoType 																	");
		selectBuffer.append(" FROM da_base_info A 																			");
		selectBuffer.append(" LEFT JOIN da_common_field B ON A.common_field_id = B.id										");
		selectBuffer.append(" LEFT JOIN da_iot_monitor_basic C ON C.basc_code = A.`base_code`								");
		selectBuffer.append(" WHERE B.area_latitude_type_code = 4															");
		selectBuffer.append(" AND B.region_id =																				");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND C.hid =																					");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(deviceId)) {
			selectBuffer.append(deviceId);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daBaseInfoUntBll.getListBySQL(map);
		return resultModel;
	}
	
}



