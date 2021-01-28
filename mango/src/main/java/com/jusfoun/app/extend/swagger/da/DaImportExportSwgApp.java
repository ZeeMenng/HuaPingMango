package com.zee.app.extend.swagger.da;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
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
import com.zee.app.generate.swagger.da.DaImportExportGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.da.DaImportExportUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaAltitudeInfo;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaImportExport;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaAltitudeInfoParameter;
import com.zee.ent.parameter.da.DaImportExportParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
import com.zee.utl.MathUtil;
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
 * @description  对外接口，扩展自DaImportExportGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daImportExport")
public class  DaImportExportSwgApp extends  DaImportExportGenSwgApp {
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	
	@Autowired
	@Qualifier("daImportExportUntBll")
	protected DaImportExportUntBll daImportExportUntBll;
	
	private static final String[] TIME_FORMAT = { "yyyy", "yyyy-MM", "yyyy-MM-dd" };
	private static final String[] TIME_START = { "1990", "1990-01", "1990-01-01" };
	
	private static final String DIMENSION_YEAR = "1";
	private static final String DIMENSION_MONTH = "3";
	private static final String DIMENSION_DAY = "4";
	
	
	@ApiOperation(value = "新增记录（直报数据）", notes = "新增单条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaImportExport") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaImportExport jsonData) {
		ResultModel resultModel = new ResultModel();
		DaCommonField daCommonField = jsonData.getDaCommonField();
		daCommonField.setAddUserId(this.getCurrentUser().getId());
		ResultModel result = daCommonFieldUntBll.add(daCommonField);
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			resultModel = daImportExportUntBll.add(jsonData);
		}
		
		return resultModel;
		
	}

	@SuppressWarnings("unchecked")
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
				resultModel = daImportExportUntBll.delete(id);
			}
		}

		return resultModel;
		
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量删除（直报数据）", notes = "根据主键列表批量删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaImportExportDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaImportExportParameter.DeleteByIdList jsonData) {
		
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
				resultModel = daImportExportUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "修改记录（直报数据）", notes = "修改指定记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaImportExport") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaImportExport jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			resultModel = daImportExportUntBll.update(jsonData);
		}
		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaImportExportUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaImportExportParameter.UpdateList jsonData) {
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
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaImportExportAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaImportExport> list1 = (List<DaImportExport>) ImportExcelUtil.transformJsonToBeanList(
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
						if(map.containsKey(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText())){
							  String status = map.get(DictionaryEnum.CROP_TYPE_CODE.getText()+list1.get(i).getCropTypeText()).toString();
								list1.get(i).setCropTypeCode(Byte.valueOf(status));//产品种类
							}
						if(map.containsKey(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getStrainsText())){
							  String status = map.get(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getStrainsText()).toString();
								list1.get(i).setStrainsCode(Byte.valueOf(status));//产品品种
							}
//						if(map.containsKey(DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText()+list1.get(i).getSourceChannelTypeText())){
//							  String status = map.get(DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText()+list1.get(i).getSourceChannelTypeText()).toString();
//								list1.get(i).setxxxCde(Byte.valueOf(status));//进口国家
//							}
//						if(map.containsKey(DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText()+list1.get(i).getSourceChannelTypeText())){
//							  String status = map.get(DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText()+list1.get(i).getSourceChannelTypeText()).toString();
//								list1.get(i).setxxxCde(Byte.valueOf(status));//出口国家
//							}
//						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getImportAmountUnitText())){
//							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getImportAmountUnitText()).toString();
//								list1.get(i).setImportAmountUnitCode(Byte.valueOf(status));//进口量单位
//							}
//						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getExportAmountUnitText())){
//							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getExportAmountUnitText()).toString();
//								list1.get(i).setExportAmountUnitCode(Byte.valueOf(status));//出口量单位
//							}
//						if(map.containsKey(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getImportVolumeUnitText())){
//							  String status = map.get(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getImportVolumeUnitText()).toString();
//								list1.get(i).setImportVolumeUnitCode(Byte.valueOf(status));//进口额单位
//							}
//						if(map.containsKey(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getExportVolumeUnitText())){
//							  String status = map.get(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getExportVolumeUnitText()).toString();
//								list1.get(i).setExportVolumeUnitCode(Byte.valueOf(status));//出口额单位
//							}
						resultModel = daImportExportUntBll.add(list1.get(i));
						
					
				}
		}
		return resultModel;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daImportExportUntBll.getModel(id);

		if(result.getIsSuccess()){
			DaImportExport jsonData = (DaImportExport) result.getData();
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
//		selectBuffer.append("select A.id id,A.common_field_id commonFieldId,A.name name,A.crop_type_code cropTypeCode,A.crop_type_text cropTypeText,A.strains_code strainsCode,A.strains_text strainsText,A.product_name productName,A.import_amount importAmount,A.import_amount_unit_code importAmountUnitCode,A.import_amount_unit_text importAmountUnitText,A.import_amount_unit importAmountUnit,A.import_volume importVolume,A.import_volume_unit_code importVolumeUnitCode,A.import_volume_unit_text importVolumeUnitText,A.import_volume_unit importVolumeUnit,A.import_price importPrice,A.import_price_unit_code importPriceUnitCode,A.import_price_unit_text importPriceUnitText,A.import_price_unit importPriceUnit,A.import_time importTime,A.import_country_code importCountryCode,A.import_country_text importCountryText,A.import_enterprise importEnterprise,A.export_amount exportAmount,A.export_amount_unit_code exportAmountUnitCode,A.export_amount_unit_text exportAmountUnitText,A.export_amount_unit exportAmountUnit,A.export_volume exportVolume,A.export_volume_unit_code exportVolumeUnitCode,A.export_volume_unit_text exportVolumeUnitText,A.export_volume_unit exportVolumeUnit,A.export_price exportPrice,A.export_price_unit_code exportPriceUnitCode,A.export_price_unit_text exportPriceUnitText,A.export_price_unit exportPriceUnit,A.export_time exportTime,A.export_country_code exportCountryCode,A.export_country_text exportCountryText,A.export_enterprise exportEnterprise  from da_import_export A inner join da_import_export B on A.id=B.id where 1=1 ");
		selectBuffer.append(" select  ");
		selectBuffer.append(" A.id id,  ");
		selectBuffer.append(" A.common_field_id commonFieldId,  ");
		selectBuffer.append(" A.name name,  ");
		selectBuffer.append(" A.crop_type_text cropTypeText,  ");
		selectBuffer.append(" A.import_country_text importCountryText,  ");
		selectBuffer.append(" A.export_country_text exportCountryText,  ");
		selectBuffer.append(" A.import_volume importVolume,  ");
		selectBuffer.append(" A.export_volume exportVolume,  ");
		selectBuffer.append(" A.import_amount importAmount,  ");
		selectBuffer.append(" A.export_amount exportAmount,  ");
		selectBuffer.append(" A.import_price importPrice,  ");
		selectBuffer.append(" A.export_price exportPrice,  ");
		selectBuffer.append(" A.import_enterprise importEnterprise,  ");
		selectBuffer.append(" A.export_enterprise exportEnterprise,  ");
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
		selectBuffer.append(" da_import_export A  ");
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
				if (entityRelatedObject.containsKey("cropTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeCode")))
					selectBuffer.append(" and A.crop_type_code like '%").append(entityRelatedObject.getString("cropTypeCode")).append("%'");
				if (entityRelatedObject.containsKey("cropTypeText") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeText")))
					selectBuffer.append(" and A.crop_type_text like '%").append(entityRelatedObject.getString("cropTypeText")).append("%'");
				if (entityRelatedObject.containsKey("importCountryText") && StringUtils.isNotBlank(entityRelatedObject.getString("importCountryText")))
					selectBuffer.append(" and A.import_country_text like '%").append(entityRelatedObject.getString("importCountryText")).append("%'");
				if (entityRelatedObject.containsKey("exportCountryText") && StringUtils.isNotBlank(entityRelatedObject.getString("exportCountryText")))
					selectBuffer.append(" and A.export_country_text like '%").append(entityRelatedObject.getString("exportCountryText")).append("%'");
				if (entityRelatedObject.containsKey("exportAmountUnitS") && StringUtils.isNotBlank(entityRelatedObject.getString("exportAmountUnitS")))
					selectBuffer.append(" and A.export_amount >=").append(entityRelatedObject.getString("exportAmountUnitS"));
				if (entityRelatedObject.containsKey("exportAmountUnitE") && StringUtils.isNotBlank(entityRelatedObject.getString("exportAmountUnitE")))
					selectBuffer.append(" and A.export_amount <=").append(entityRelatedObject.getString("exportAmountUnitE"));
				if (entityRelatedObject.containsKey("importAmountUnitS") && StringUtils.isNotBlank(entityRelatedObject.getString("importAmountUnitS")))
					selectBuffer.append(" and A.import_amount >=").append(entityRelatedObject.getString("importAmountUnitS"));
				if (entityRelatedObject.containsKey("importAmountUnitE") && StringUtils.isNotBlank(entityRelatedObject.getString("importAmountUnitE")))
					selectBuffer.append(" and A.import_amount <=").append(entityRelatedObject.getString("importAmountUnitE"));
				if (entityRelatedObject.containsKey("year") && StringUtils.isNotBlank(entityRelatedObject.getString("year")))
					selectBuffer.append(" and substr(B.start_time,1,4) = '"+entityRelatedObject.getString("year")+"' ");
				if (entityRelatedObject.containsKey("flag") && StringUtils.isNotBlank(entityRelatedObject.getString("flag")))
					if("1".equals(entityRelatedObject.getString("flag")))
						selectBuffer.append(" and A.export_amount is not null ");
					else
						selectBuffer.append(" and A.import_amount is not null ");
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

		resultModel = daImportExportUntBll.getListBySQL(map);

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
		selectBuffer.append("	INNER JOIN da_import_export B ON A.id = B.common_field_id                               ");
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
	
	@ApiOperation(value = "查询", notes = "查询贸易排名")
	@RequestMapping(value = "/getTradeRank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTradeRank() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String queryPattern = "";   //进出口类型 1：对中国出口（进口） 2：自中国进口（出口）。
		String queryType = "";		//查询类型     1：按金额（万美元），2：按数量（吨）。
		String queryTime = "";		//查询日期     2018
		Map<String, Object> map = new HashMap<String, Object>();
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("queryPattern") && StringUtils.isNotBlank(entityRelatedObject.getString("queryPattern")))
					queryPattern = entityRelatedObject.getString("queryPattern");
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
		 if(queryPattern.equals("1") && queryType.equals("1")){
			//进口&按金额
				selectBuffer.append(" select round(A.import_volume,2) value,'万美元' unit,A.import_country_text country,'进口' Pattern,'金额' Type ");
				selectBuffer.append(" from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text <> '全球' and substr(B.start_time,1,4) = '"+queryTime+"' ");
				selectBuffer.append(" order by A.import_volume_unit desc");
		 }else if(queryPattern.equals("1") && queryType.equals("2")){
			//进口&按数量
			 	selectBuffer.append(" select round(A.import_amount,2) value,'吨' unit,A.import_country_text country,'进口' Pattern,'数量' Type ");
				selectBuffer.append(" from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text <> '全球' and substr(B.start_time,1,4) = '"+queryTime+"' ");
				selectBuffer.append(" order by A.import_amount_unit desc");
		 }else if(queryPattern.equals("2") && queryType.equals("1")){
			//出口&按金额
			 	selectBuffer.append(" select round(A.export_volume,2) value,'万美元' unit,A.export_country_text country,'出口' Pattern,'金额' Type ");
				selectBuffer.append(" from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.export_country_text <> '全球' and substr(B.start_time,1,4) = '"+queryTime+"' ");
				selectBuffer.append(" order by A.export_volume_unit desc");
		 }else if(queryPattern.equals("2") && queryType.equals("2")){
			//出口&按数量
			 	selectBuffer.append(" select round(A.export_amount,2) value,'吨' unit,A.export_country_text country,'出口' Pattern,'数量' Type ");
				selectBuffer.append(" from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.export_country_text <> '全球' and substr(B.start_time,1,4) = '"+queryTime+"' ");
				selectBuffer.append(" order by A.export_amount_unit desc");
		 }
		 
		map.put("Sql", selectBuffer.toString());
		resultModel = daImportExportUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询贸易规模")
	@RequestMapping(value = "/getTradeScale", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTradeScale() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		String queryPattern = "";   //进出口类型 1：进口 2：出口。
		String queryType = "";		//查询类型    1：按金额（万美元），2：按数量（吨）。
		String queryTimes = "";		//查询日期     '2015','2016','2017','2018'
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryPattern") && StringUtils.isNotBlank(entityRelatedObject.getString("queryPattern")))
				queryPattern = entityRelatedObject.getString("queryPattern");
			if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
				queryType = entityRelatedObject.getString("queryType");
			if (entityRelatedObject.containsKey("queryTimes") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTimes")))
				queryTimes = entityRelatedObject.getString("queryTimes");
		}

		 if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
		}
				

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryPattern.equals("1") && queryType.equals("1")){
			//进口&按金额
				selectBuffer.append(" select distinct substr(B.start_time,1,4) time,round(A.import_volume,2) value,'万美元' unit from da_import_export A,da_common_field B ");
				selectBuffer.append(" where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc");
		 }else if(queryPattern.equals("1") && queryType.equals("2")){
			//进口&按数量
			 	selectBuffer.append(" select distinct substr(B.start_time,1,4) time,round(A.import_amount,2) value,'吨' unit from da_import_export A,da_common_field B ");
			 	selectBuffer.append(" where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc");
		 }else if(queryPattern.equals("2") && queryType.equals("1")){
			//出口&按金额
			 	selectBuffer.append(" select distinct substr(B.start_time,1,4) time,round(A.export_volume,2) value,'万美元' unit from da_import_export A,da_common_field B ");
			 	selectBuffer.append(" where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc");
		 }else if(queryPattern.equals("2") && queryType.equals("2")){
			//出口&按数量
			 	selectBuffer.append(" select distinct substr(B.start_time,1,4) time,round(A.export_amount,2) value,'吨' unit from da_import_export A,da_common_field B ");
			 	selectBuffer.append(" where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc");
		 }
		
		map.put("Sql", selectBuffer.toString());

		resultModel = daImportExportUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询进出口价格")
	@RequestMapping(value = "/getTradePrice", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTradePrice() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		String queryPattern = "";   //进出口类型 1：进口（美元/公斤）  2：出口（美元/公斤）。
		String queryTimes = "";		//查询日期     '2015','2016','2017','2018'
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryPattern") && StringUtils.isNotBlank(entityRelatedObject.getString("queryPattern")))
				queryPattern = entityRelatedObject.getString("queryPattern");
			if (entityRelatedObject.containsKey("queryTimes") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTimes")))
				queryTimes = entityRelatedObject.getString("queryTimes");
		}
				
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryPattern.equals("1")){
			//进口价格
				selectBuffer.append(" select distinct substr(B.start_time,1,4) time,round(A.import_price,2) value,'美元/公斤' unit from da_import_export A,da_common_field B ");
				selectBuffer.append(" where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc");
		 }else if(queryPattern.equals("2")){
			//出口价格
			 	selectBuffer.append(" select distinct substr(B.start_time,1,4) time,round(A.export_price,2) value,'美元/公斤' unit from da_import_export A,da_common_field B ");
				selectBuffer.append(" where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.export_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc");
		 }
		 
		map.put("Sql", selectBuffer.toString());

		resultModel = daImportExportUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询贸易规模-数字服务版")
	@RequestMapping(value = "/getTradeScaleData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTradeScaleData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		String queryPattern = "";   //进出口类型 1：进口 2：出口。
		String queryType = "";		//查询类型    1：按金额（万美元），2：按数量（吨）。
		String queryTimes = "";		//查询日期     '2015','2016','2017','2018'
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryPattern") && StringUtils.isNotBlank(entityRelatedObject.getString("queryPattern")))
				queryPattern = entityRelatedObject.getString("queryPattern");
			if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
				queryType = entityRelatedObject.getString("queryType");
			if (entityRelatedObject.containsKey("queryTimes") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTimes")))
				queryTimes = entityRelatedObject.getString("queryTimes");
		}

		 if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
		}
				

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryPattern.equals("1") && queryType.equals("1")){
			//进口&按金额
				selectBuffer.append(" select T.* from (select distinct substr(B.start_time,1,4) time,round(A.import_volume,2) value,'万美元' unit,'进口' Pattern,'金额' Type ");
				selectBuffer.append("  from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc) T where T.time in ("+queryTimes+")");
		 }else if(queryPattern.equals("1") && queryType.equals("2")){
			//进口&按数量
			 	selectBuffer.append(" select T.* from (select distinct substr(B.start_time,1,4) time,round(A.import_amount,2) value,'吨' unit,'进口' Pattern,'数量' Type ");
			 	selectBuffer.append("  from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc) T where T.time in ("+queryTimes+")");
		 }else if(queryPattern.equals("2") && queryType.equals("1")){
			//出口&按金额
			 	selectBuffer.append(" select T.* from (select distinct substr(B.start_time,1,4) time,round(A.export_volume,2) value,'万美元' unit,'出口' Pattern,'金额' Type ");
			 	selectBuffer.append("  from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc) T where T.time in ("+queryTimes+")");
		 }else if(queryPattern.equals("2") && queryType.equals("2")){
			//出口&按数量
			 	selectBuffer.append(" select T.* from (select distinct substr(B.start_time,1,4) time,round(A.export_amount,2) value,'吨' unit,'出口' Pattern,'数量' Type ");
			 	selectBuffer.append("  from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc) T where T.time in ("+queryTimes+")");
		 }
		
		map.put("Sql", selectBuffer.toString());

		resultModel = daImportExportUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询进出口价格-数字服务版")
	@RequestMapping(value = "/getTradePriceData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTradePriceData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		String queryPattern = "";   //进出口类型 1：进口（美元/公斤）  2：出口（美元/公斤）。
		String queryTimes = "";		//查询日期     '2015','2016','2017','2018'
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryPattern") && StringUtils.isNotBlank(entityRelatedObject.getString("queryPattern")))
				queryPattern = entityRelatedObject.getString("queryPattern");
			if (entityRelatedObject.containsKey("queryTimes") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTimes")))
				queryTimes = entityRelatedObject.getString("queryTimes");
		}
				
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}

			StringBuffer selectBuffer = new StringBuffer();
		 if(queryPattern.equals("1")){
			//进口价格
				selectBuffer.append(" select T.* from (select distinct substr(B.start_time,1,4) time,round(A.import_price,2) value,'美元/公斤' unit,'进口' Pattern ");
				selectBuffer.append(" from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.import_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc) T where T.time in ("+queryTimes+")");
		 }else if(queryPattern.equals("2")){
			//出口价格
			 	selectBuffer.append(" select T.* from (select distinct substr(B.start_time,1,4) time,round(A.export_price,2) value,'美元/公斤' unit,'出口' Pattern ");
				selectBuffer.append(" from da_import_export A,da_common_field B where A.common_field_id = B.id and A.crop_type_code = '1' and B.data_time_type_code = '1' ");
				selectBuffer.append(" and B.area_latitude_type_code = 1 and B.region_name = '中国' and A.export_country_text = '全球' ");
				selectBuffer.append(" group by substr(B.start_time,1,4) order by substr(B.start_time,1,4) asc) T where T.time in ("+queryTimes+")");
		 }
		 
		map.put("Sql", selectBuffer.toString());

		resultModel = daImportExportUntBll.getListBySQL(map);

		return resultModel;
	}
	
	
	@ApiOperation(value = "查询指定年份区间各地区芒果进出口数据及同比增长率（门户数据服务版）", notes = "查询指定年份区间各地区芒果进出口数据及同比增长率（门户数据服务版）")
	@RequestMapping(value = "/getImportExportDataAndProportion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getImportExportDataAndProportion() throws ParseException {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionId = "";// 地区id
		String breed = "";// 芒果品种id
		String startTime = "";// 开始时间
		String endTime = "";// 结束时间
		String dimension = "";// 时间维度
		String priceUnit = "";//价格单位
		String queryType = "";//查询内容：volume 金额  amount 数量

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
				if (entityRelatedObject.containsKey("priceUnit") && StringUtils.isNotBlank(entityRelatedObject.getString("priceUnit")))
					priceUnit = entityRelatedObject.getString("priceUnit");
				if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
					queryType = entityRelatedObject.getString("queryType");
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

		Map<String, Object> importResultMap = new HashMap<String, Object>();
		Map<String, Object> exportResultMap = new HashMap<String, Object>();

		List importAmountYearList = new ArrayList();
		List importVolumeYearList = new ArrayList();
		List importAmountDataList = new ArrayList();
		List importVolumeDataList = new ArrayList();
		List importAmountProportionList = new ArrayList();
		List importVolumeProportionList = new ArrayList();

		List exportAmountYearList = new ArrayList();
		List exportVolumeYearList = new ArrayList();
		List exportAmountDataList = new ArrayList();
		List exportVolumeDataList = new ArrayList();
		List exportAmountProportionList = new ArrayList();
		List exportVolumeProportionList = new ArrayList();

		for (int i = 0; i < timesList.size() - 1; i++) {
			importAmountYearList.add(timesList.get(i + 1));
			exportAmountYearList.add(timesList.get(i + 1));
			importVolumeYearList.add(timesList.get(i + 1));
			exportVolumeYearList.add(timesList.get(i + 1));

			BigDecimal currentImportAmount = new BigDecimal("0");
			BigDecimal currentImportVolume = new BigDecimal("0");
			BigDecimal currentExportAmount = new BigDecimal("0");
			BigDecimal currentExportVolume = new BigDecimal("0");
			BigDecimal lastImportAmount = new BigDecimal("0");
			BigDecimal lastImportVolume = new BigDecimal("0");
			BigDecimal lastExportAmount = new BigDecimal("0");
			BigDecimal lastExportVolume = new BigDecimal("0");
			
			// 获取所需年的进出口数量和总额
			ResultModel currentModel = new ResultModel();
			if(StringUtils.isNotBlank(regionId) && regionId.equals("031000000")) {
				currentModel = getImportAndExportInfoForChina(timesList.get(i + 1), regionId, breed);
			}else {
				currentModel = getImportAndExportInfo(timesList.get(i + 1), regionId, breed);
			}
			
			Map<String, Object> currentMap = getMapFromModel(currentModel);
			if (currentMap != null) {
				if (currentMap.containsKey("importAmount") && currentMap.get("importAmount")!=null) {
					currentImportAmount = MathUtil.decimalFormat((BigDecimal) currentMap.get("importAmount"));
				}
				if (currentMap.containsKey("importVolume") && currentMap.get("importVolume")!=null) {
					currentImportVolume = MathUtil.decimalFormat((BigDecimal) currentMap.get("importVolume"));
				}
				if (currentMap.containsKey("exportAmount") && currentMap.get("exportAmount")!=null) {
					currentExportAmount = MathUtil.decimalFormat((BigDecimal) currentMap.get("exportAmount"));
				}
				if (currentMap.containsKey("exportVolumeSum") && currentMap.get("exportVolumeSum")!=null) {
					currentExportVolume = MathUtil.decimalFormat((BigDecimal) currentMap.get("exportVolumeSum"));
				}
			}
			importAmountDataList.add(currentImportAmount+"");
			importVolumeDataList.add(currentImportVolume+"");
			exportAmountDataList.add(currentExportAmount+"");
			exportVolumeDataList.add(currentExportVolume+"");
			
			// 获取所需年的上一年的进出口数量和总额
			ResultModel lastModel = new ResultModel();
			if(StringUtils.isNotBlank(regionId) && regionId.equals("031000000")) {
				lastModel = getImportAndExportInfoForChina(timesList.get(i), regionId, breed);
			}else {
				lastModel = getImportAndExportInfo(timesList.get(i), regionId, breed);
			}
			
			Map<String, Object> lastMap = getMapFromModel(lastModel);
			if (lastMap != null) {
				if (lastMap.containsKey("importAmount") && lastMap.get("importAmount")!=null) {
					lastImportAmount = (BigDecimal) lastMap.get("importAmount");
				}
				if (lastMap.containsKey("importVolume") && lastMap.get("importVolume")!=null) {
					lastImportVolume = (BigDecimal) lastMap.get("importVolume");
				}
				if (lastMap.containsKey("exportAmount") && lastMap.get("exportAmount")!=null) {
					lastExportAmount = (BigDecimal) lastMap.get("exportAmount");
				}
				if (lastMap.containsKey("exportVolumeSum") && lastMap.get("exportVolumeSum")!=null) {
					lastExportVolume = (BigDecimal) lastMap.get("exportVolume");
				}
			}
			// 计算同比增长率
			String importAmountProportion = getProportion(currentImportAmount, lastImportAmount);
			String importVolumeProportion = getProportion(currentImportVolume, lastImportVolume);
			String exportAmountProportion = getProportion(currentExportAmount, lastExportAmount);
			String exportVolumeProportion = getProportion(currentExportVolume, lastExportVolume);
			
			importAmountProportionList.add(importAmountProportion.substring(0, importAmountProportion.length()-1));
			importVolumeProportionList.add(importVolumeProportion.substring(0, importVolumeProportion.length()-1));
			exportAmountProportionList.add(exportAmountProportion.substring(0, exportAmountProportion.length()-1));
			exportVolumeProportionList.add(exportVolumeProportion.substring(0, exportVolumeProportion.length()-1));
		}
		
		if("amount".equals(queryType)) {
			importAmountYearList = getFormalListAccordingToData(importAmountYearList,importAmountDataList);
			importAmountProportionList = getFormalListAccordingToData(importAmountProportionList,importAmountDataList);
			if(importAmountProportionList.size()>0) {
				importAmountProportionList.set(0, "0");
			}
			importAmountDataList = getFormalList(importAmountDataList);
			
			exportAmountYearList = getFormalListAccordingToData(exportAmountYearList,exportAmountDataList);
			exportAmountProportionList = getFormalListAccordingToData(exportAmountProportionList,exportAmountDataList);
			if(exportAmountProportionList.size()>0) {
				exportAmountProportionList.set(0, "0");
			}
			exportAmountDataList = getFormalList(exportAmountDataList);
			
			importResultMap.put("times", importAmountYearList);
			importResultMap.put("amountData", importAmountDataList);
			importResultMap.put("amountProportion", importAmountProportionList);
			
			exportResultMap.put("times", exportAmountYearList);
			exportResultMap.put("amountData", exportAmountDataList);
			exportResultMap.put("amountProportion", exportAmountProportionList);
		}else if("volume".equals(queryType)) {
			importVolumeYearList = getFormalListAccordingToData(importVolumeYearList,importVolumeDataList);
			importVolumeProportionList = getFormalListAccordingToData(importVolumeProportionList,importVolumeDataList);
			if(importVolumeProportionList.size()>0) {
				importVolumeProportionList.set(0, "0");
			}
			importVolumeDataList = getFormalList(importVolumeDataList);
			
			exportVolumeYearList = getFormalListAccordingToData(exportVolumeYearList,exportVolumeDataList);
			exportVolumeProportionList = getFormalListAccordingToData(exportVolumeProportionList,exportVolumeDataList);
			if(exportVolumeProportionList.size()>0) {
				exportVolumeProportionList.set(0, "0");
			}
			exportVolumeDataList = getFormalList(exportVolumeDataList);
			
			importResultMap.put("times", importVolumeYearList);
			importResultMap.put("volumeData", importVolumeDataList);
			importResultMap.put("volumeProportion", importVolumeProportionList);
			
			exportResultMap.put("times", exportVolumeYearList);
			exportResultMap.put("volumeData", exportVolumeDataList);
			exportResultMap.put("volumeProportion", exportVolumeProportionList);
		}
		
		resultMap.put("importData", importResultMap);
		resultMap.put("exportData", exportResultMap);
		
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		return resultModel;	
	}

	private List getFormalListAccordingToData(List hostList,List followList) {
		List formatList = new ArrayList();
		BigDecimal b = MathUtil.decimalFormat(new BigDecimal("0"));
		for(int i = 0;i<followList.size();i++) {
			if(followList.get(i)!=null && !followList.get(i).equals("null")) {
				BigDecimal a = MathUtil.decimalFormat(new BigDecimal(followList.get(i)+"")); 
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
			if(tempList.get(i)!=null && !tempList.get(i).equals("null")) {
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
	
	private ResultModel getImportAndExportInfoForChina(String time, String regionId, String breed) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    t0.* 
		FROM
		    (SELECT 
		        SUBSTR(B.start_time, 1, 4) TIME,
		        B.start_time AS startTime,
		        CASE WHEN A.import_amount_unit IS NULL THEN 0 ELSE A.import_amount_unit END AS importAmount,
		        CASE WHEN A.import_volume_unit IS NULL THEN 0 ELSE A.import_volume_unit END AS importVolume,
		        CASE WHEN A.export_amount_unit IS NULL THEN 0 ELSE A.export_amount_unit END AS exportAmount,
		        CASE WHEN A.export_volume_unit IS NULL THEN 0 ELSE A.export_volume_unit END AS exportVolume
		    FROM
		        da_import_export A,
		        da_common_field B 
		    WHERE A.common_field_id = B.id 
		        AND A.crop_type_code = 1 
		        AND B.audit_status_code = 1 
		        AND B.region_id = '031000000' 
		        AND SUBSTR(B.start_time, 1, 4) = 2011
		    ORDER BY startTime DESC) t0 
		GROUP BY TIME*/
	
		selectBuffer.append(" SELECT 																									");
		selectBuffer.append(" t0.* 																										");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" (SELECT 																									");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																			");
		selectBuffer.append(" B.start_time AS startTime,																				");
		selectBuffer.append(" CASE WHEN A.import_amount_unit IS NULL THEN 0 ELSE A.import_amount_unit END AS importAmount,				");
		selectBuffer.append(" CASE WHEN A.import_volume_unit IS NULL THEN 0 ELSE A.import_volume_unit END AS importVolume,				");
		selectBuffer.append(" CASE WHEN A.export_amount_unit IS NULL THEN 0 ELSE A.export_amount_unit END AS exportAmount,				");
		selectBuffer.append(" CASE WHEN A.export_volume_unit IS NULL THEN 0 ELSE A.export_volume_unit END AS exportVolume				");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_import_export A,																						");
		selectBuffer.append(" da_common_field B 																						");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1 																					");
		selectBuffer.append(" AND B.audit_status_code = 1																				");
		selectBuffer.append(" AND B.region_id =																							");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append("'");
			selectBuffer.append(regionId);
			selectBuffer.append("'");
		}
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																			");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append("'");
			selectBuffer.append(time);
			selectBuffer.append("'");
		}
		selectBuffer.append(" ORDER BY startTime DESC) t0 																				");
		selectBuffer.append(" GROUP BY time																								");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daImportExportUntBll.getListBySQL(map);
		return resultModel;
	}
	
	private ResultModel getImportAndExportInfo(String time, String regionId, String breed) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    t0.* 
		FROM
		    (SELECT 
		        SUBSTR(B.start_time, 1, 4) TIME,
		        B.start_time AS startTime,
		        CASE WHEN A.import_amount_unit IS NULL THEN 0 ELSE A.import_amount_unit END AS importAmount,
		        CASE WHEN A.import_volume_unit IS NULL THEN 0 ELSE A.import_volume_unit END AS importVolume,
		        CASE WHEN A.export_amount_unit IS NULL THEN 0 ELSE A.export_amount_unit END AS exportAmount,
		        CASE WHEN A.export_volume_unit IS NULL THEN 0 ELSE A.export_volume_unit END AS exportVolume
		    FROM
		        da_import_export A,
		        da_common_field B 
		    WHERE A.common_field_id = B.id 
		        AND A.crop_type_code = 1 
		        AND B.audit_status_code = 1 
		        AND A.import_country_code = '031000000' 
		        AND SUBSTR(B.start_time, 1, 4) = 2011
		    ORDER BY startTime DESC) t0 
		GROUP BY TIME*/
	
		selectBuffer.append(" SELECT 																									");
		selectBuffer.append(" t0.* 																										");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" (SELECT 																									");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																			");
		selectBuffer.append(" B.start_time AS startTime,																				");
		selectBuffer.append(" CASE WHEN SUM(A.import_amount_unit) IS NULL THEN 0 ELSE SUM(A.import_amount_unit) END AS importAmount,	");
		selectBuffer.append(" CASE WHEN SUM(A.import_volume_unit) IS NULL THEN 0 ELSE SUM(A.import_volume_unit) END AS importVolume,	");
		selectBuffer.append(" CASE WHEN SUM(A.export_amount_unit) IS NULL THEN 0 ELSE SUM(A.export_amount_unit) END AS exportAmount,	");
		selectBuffer.append(" CASE WHEN SUM(A.export_volume_unit) IS NULL THEN 0 ELSE SUM(A.export_volume_unit) END AS exportVolume		");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_import_export A,																						");
		selectBuffer.append(" da_common_field B 																						");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1 																					");
		selectBuffer.append(" AND B.audit_status_code = 1																				");
		selectBuffer.append(" AND B.region_id =																							");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append("'");
			selectBuffer.append(regionId);
			selectBuffer.append("'");
		}
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																			");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append("'");
			selectBuffer.append(time);
			selectBuffer.append("'");
		}
		selectBuffer.append(" ORDER BY startTime DESC) t0 																				");
//		selectBuffer.append(" GROUP BY time																								");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daImportExportUntBll.getListBySQL(map);
		return resultModel;
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
	
}



