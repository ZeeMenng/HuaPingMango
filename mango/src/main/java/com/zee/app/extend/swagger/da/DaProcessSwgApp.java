package com.zee.app.extend.swagger.da;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.Iterator;
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
import com.zee.app.generate.swagger.da.DaProcessGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaAltitudeInfo;
import com.zee.ent.extend.da.DaBaseInfo;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaImportExport;
import com.zee.ent.extend.da.DaProcess;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaBaseInfoParameter;
import com.zee.ent.parameter.da.DaProcessParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.set.enumer.DiEnterpriseTypeEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.ImportExcelUtil;
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
 * @updateDate 2018-5-22 15:09:46
 * @description  对外接口，扩展自DaProcessGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daProcess")
public class  DaProcessSwgApp extends  DaProcessGenSwgApp {
	
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;
	
	@Autowired
	@Qualifier("gpDictionaryUntBll")
	protected GpDictionaryUntBll gpDictionaryUntBll;
	
	
	@ApiOperation(value = "新增记录（直报数据）", notes = "新增单条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaProcess") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaProcess jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.add(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			resultModel = daProcessUntBll.add(jsonData);
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
				resultModel = daProcessUntBll.delete(id);
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "批量删除（直报数据）", notes = "根据主键列表批量删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaProcessDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaProcessParameter.DeleteByIdList jsonData) {
		
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
				resultModel = daProcessUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "修改记录（直报数据）", notes = "修改指定记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaProcess") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaProcess jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			resultModel = daProcessUntBll.update(jsonData);
		}
		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaProcessUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaProcessParameter.UpdateList jsonData) {
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
	 * 导入excel
	 * @param file
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@ApiOperation(value = "批量导入（直报数据）", notes = "批量导入多条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaProcessAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaProcess> list1 = (List<DaProcess>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaProcess.class);
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
						if(map.containsKey(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getProcessStrainsText())){
							  String status = map.get(DictionaryEnum.STRAINS_CODE.getText()+list1.get(i).getProcessStrainsText()).toString();
								list1.get(i).setProcessStrainsCode(Byte.valueOf(status));//产品品种
							}
						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getProductTotalText())){
							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getProductTotalText()).toString();
								list1.get(i).setProductTotalCode(Byte.valueOf(status));//产量单位
							}
						if(map.containsKey(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getOutputValueText())){
							  String status = map.get(DictionaryEnum.PRICE_UNIT_CODE.getText()+list1.get(i).getOutputValueText()).toString();
								list1.get(i).setOutputValueCode(Byte.valueOf(status));//产值单位
							}
						if(map.containsKey(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getConsumeTotalText())){
							  String status = map.get(DictionaryEnum.WEIGHT_UNIT_CODE.getText()+list1.get(i).getConsumeTotalText()).toString();
								list1.get(i).setConsumeTotalCode(Byte.valueOf(status));//原材料消耗总量单位
							}
						resultModel = daProcessUntBll.add(list1.get(i));
						
					
				}
		}
		return resultModel;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daProcessUntBll.getModel(id);

		if(result.getIsSuccess()){
			DaProcess jsonData = (DaProcess) result.getData();
			DaCommonField DaCommonField = (DaCommonField) daCommonFieldUntBll.getModel(jsonData.getCommonFieldId()).getData();
			jsonData.setDaCommonField(DaCommonField);
			result.setData(jsonData);
		}
		return result;
	}
	
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonDatas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonDatas() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
//		selectBuffer.append("select A.id id,A.common_field_id commonFieldId,A.crop_type_code cropTypeCode,A.crop_type_text cropTypeText,A.process_strains_code processStrainsCode,A.process_strains_text processStrainsText,A.product_name productName,A.process_time processTime,A.output_value outputValue,A.output_value_code outputValueCode,A.output_value_text outputValueText,A.output_value_unit outputValueUnit,A.consume_total consumeTotal,A.consume_total_code consumeTotalCode,A.consume_total_text consumeTotalText,A.consume_total_unit consumeTotalUnit,A.product_total productTotal,A.product_total_code productTotalCode,A.product_total_text productTotalText,A.product_total_unit productTotalUnit,A.name name  from da_process A inner join da_process B on A.id=B.id where 1=1 ");
		selectBuffer.append(" select  ");
		selectBuffer.append(" A.id id,  ");
		selectBuffer.append(" A.common_field_id commonFieldId,  ");
		selectBuffer.append(" A.name name,  ");
		selectBuffer.append(" A.process_strains_text processStrainsText,  ");
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
		selectBuffer.append(" da_process A  ");
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
				if (entityRelatedObject.containsKey("cropTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeCode")))
					selectBuffer.append(" and A.crop_type_code like '%").append(entityRelatedObject.getString("cropTypeCode")).append("%'");
				if (entityRelatedObject.containsKey("cropTypeText") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeText")))
					selectBuffer.append(" and A.crop_type_text like '%").append(entityRelatedObject.getString("cropTypeText")).append("%'");
				if (entityRelatedObject.containsKey("processStrainsCode") && StringUtils.isNotBlank(entityRelatedObject.getString("processStrainsCode")))
					selectBuffer.append(" and A.process_strains_code like '%").append(entityRelatedObject.getString("processStrainsCode")).append("%'");
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

		resultModel = daProcessUntBll.getListBySQL(map);

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
		selectBuffer.append("	INNER JOIN da_process B ON A.id = B.common_field_id                               ");
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	 * 
	 * @title: getFaucetEnterpriseData
	 * @description: 精深加工--龙头企业产量及产值占比
	 * @author: lxl
	 * @date:2018年5月24日 下午11:22:00
	 * @param
	 * @return 
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getFaucetEnterpriseData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFaucetEnterpriseData() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityProcess")) {
				JSONObject entityScavengingObject = jsonObject.getJSONObject("entityProcess");
				if (entityScavengingObject.containsKey("year")
						&& !StringUtils.isBlank(entityScavengingObject.getString("year"))) {
					year = entityScavengingObject.getString("year");

				}
			}
		}
		
		
		//龙头企业的
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT dp.`name`,dp.output_value_unit output_value,dp.product_total_unit product_total")
					.append(" FROM")
					.append(" da_enterprise_info dei")
					.append(" INNER JOIN da_process dp ON dei.enterprise_name = dp.`name`")
					.append(" INNER JOIN da_common_field dcf on dcf.id=dp.common_field_id")
					.append(" where")
					.append(" dei.is_corporate_champion_type_code='0'")//龙头企业
					.append(" AND YEAR (dcf.start_time) ='"+year+"' ")
					.append(" and dcf.area_latitude_type_code='8'")//企业个体
					.append(" and dei.enterprise_type_code='"+DiEnterpriseTypeEnum.JIAGONG.getCode()+"' ")//加工
					.append(" group by dp.`name`");//龙头企业
        
		map.put("Sql", selectBuffer.toString());
		
		resultModel = daProcessUntBll.getListBySQL(map);
		
		//整个华坪的
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("SELECT dp.`name`,sum(dp.output_value_unit) output_value,sum(dp.product_total_unit) product_total")
					.append(" FROM")
					.append(" da_process dp")
					.append(" INNER JOIN da_common_field dcf on dcf.id=dp.common_field_id")
					.append(" where")
					.append("  YEAR (dcf.start_time) ='"+year+"' ");
        
		map2.put("Sql", selectBuffer2.toString());

		resultModel2 = daProcessUntBll.getListBySQL(map2);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("faucetEnterpriseDate", resultModel.getData());// 龙头企业的
		maps.put("hpEnterpriseDate", resultModel2.getData());// 整个华坪的
		list.add(maps);
		resultModel.setData(list);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	
	/**
	 * 
	 * @title: getConsumptionPredictionData
	 * @description: 精深加工--原料消耗及预测
	 * @author: lxl
	 * @date:2018年5月24日 下午11:22:00
	 * @param
	 * @return 
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getConsumptionPredictionData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getConsumptionPredictionData() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		//鲜果消耗量
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT p. YEAR as year,IFNULL(m.consume_total_unit,0) as consumeTotal")
					.append(" FROM")
					.append(" past_5_year_view p")
					.append(" LEFT JOIN (")
					.append(" SELECT DATE_FORMAT(dcf.start_time, '%Y') AS years,dp.consume_total_unit")
					.append(" FROM da_process dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id")
					.append(" GROUP BY years")
					.append(" ) m ON m.years = p.`year`")
					.append(" ORDER BY p.`year`");
		map.put("Sql", selectBuffer.toString());
		resultModel = daProcessUntBll.getListBySQL(map);

		//加工品消耗鲜果量占鲜果产量比例
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("SELECT xg.`YEAR` as year ,jg.consumeTotal / xg.yield * 100 AS proportion")
					.append(" FROM(")
					.append(" SELECT p. YEAR,IFNULL(m.consume_total_unit,0) as consumeTotal")
					.append(" FROM past_5_year_view p")
					.append(" LEFT JOIN (")
					.append(" SELECT DATE_FORMAT(dcf.start_time, '%Y') AS years,dp.consume_total_unit")
					.append(" FROM da_process dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id")
					.append(" GROUP BY years")
					.append(" ) m ON m.years = p.`year`")
					.append(" ORDER BY p.`year`")
					.append(" ) jg")
					.append(" LEFT JOIN ( SELECT")
					.append(" p. YEAR,IFNULL(m.yield,0) as yield")
					.append(" FROM past_5_year_view p")
					.append(" LEFT JOIN (")
					.append(" SELECT DATE_FORMAT(dcf.start_time, '%Y') AS years,dp.yield")
					.append(" FROM da_industry_fresh dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id")
					.append(" GROUP BY years")
					.append(" ) m ON m.years = p.`year`")
					.append(" ORDER BY p.`year`")
					.append(" ) xg ON xg.`YEAR` = jg.`YEAR`");
        
		map2.put("Sql", selectBuffer2.toString());

		resultModel2 = daProcessUntBll.getListBySQL(map2);
			
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("freshFruitDate", resultModel.getData());// 鲜果消耗量
		maps.put("processFreshDate", resultModel2.getData());// 加工品消耗鲜果量占鲜果产量比例
		list.add(maps);
		resultModel.setData(list);
		return resultModel;
	}
	
	/**
	 * 
	 * @title: getProcessOutputValueData
	 * @description: 精深加工--加工品产值及产量预测
	 * @author: lxl
	 * @date:2018年5月24日 下午11:22:00
	 * @param
	 * @return 
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getProcessOutputValueData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getProcessOutputValueData() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		//鲜果消耗量
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT p. YEAR,m.process_strains_code,m.process_strains_text,m.output_value_unit as output_value,")
					.append(" IFNULL(m.product_total_unit,0) as product_total")
					.append(" FROM past_5_year_view p")
					.append(" LEFT JOIN (")
					.append(" SELECT DATE_FORMAT(dcf.start_time, '%Y') AS years,dp.`name`,dp.output_value_unit,")
					.append(" dp.product_total_unit,dp.process_strains_code,dp.process_strains_text")
					.append(" FROM da_process dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id")
					.append(" GROUP BY years")
					.append(" ) m ON m.years = p.`year`")
					.append(" ORDER BY p.`year`");
		map.put("Sql", selectBuffer.toString());
		resultModel = daProcessUntBll.getListBySQL(map);
		return resultModel;
	}
	
	/**
	 * 
	 * @title: getProcessOutputValueData
	 * @description: 精深加工--可视化展示加工动画（产量产值同比）
	 * @author: lxl
	 * @date:2018年5月24日 下午11:22:00
	 * @param
	 * @return 
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getProcessVisualizationData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getProcessVisualizationData() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		ResultModel resultModel3 = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityProcess")) {
				JSONObject entityProcessObject = jsonObject.getJSONObject("entityProcess");
				if (entityProcessObject.containsKey("year")
						&& !StringUtils.isBlank(entityProcessObject.getString("year"))) {
					year = entityProcessObject.getString("year");

				}
			}
		}
		
		//产量产值同比
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT t5.*, CASE WHEN t5.last_product_total IS NULL OR t5.last_product_total =0 THEN 0.00")
					.append("  ELSE ROUND(((t5.product_total - t5.last_product_total)/t5.last_product_total)*100,2) END chanl,")
					.append(" CASE WHEN t5.last_output_value IS NULL OR t5.last_output_value =0 THEN 0.00")
					.append(" ELSE FORMAT(((t5.output_value - t5.last_output_value)/t5.last_output_value)*100,2) END chanzhi")
					.append(" FROM (  SELECT t3.*,t4.last_output_value,t4.last_product_total ")
					.append(" FROM  (")
					.append(" SELECT DATE_FORMAT(CONCAT(t1.start_time),'%Y') AS YearMonth,t1.start_time,t1.process_strains_text,t1.output_value_unit output_value,t1.product_total_unit product_total")
					.append(" FROM(SELECT dp.*,dcf.start_time,COUNT(DISTINCT dcf.start_time,dp.process_strains_text) c_cot")
					.append(" FROM da_process dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id ")
					.append(" where YEAR(dcf.start_time)='"+year+"' and dp.process_strains_code <> '0' ")
					.append(" GROUP BY dp.process_strains_text) t1")
					.append(" GROUP BY t1.process_strains_text")
					.append(" ) t3")
					.append(" INNER JOIN ")
					.append(" ( SELECT DATE_FORMAT(CONCAT(t1.start_time),'%Y') AS lastYearMonth,t1.process_strains_text,t1.output_value_unit last_output_value,t1.product_total_unit last_product_total")
					.append(" FROM(SELECT dp.*,dcf.start_time,COUNT(DISTINCT dcf.start_time,dp.process_strains_text) c_cot")
					.append(" FROM da_process dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id")
					.append(" where YEAR(dcf.start_time)='"+year+"'-1 and dp.process_strains_code <> '0' ")
					.append("  GROUP BY dp.process_strains_text) t1")
					.append(" GROUP BY t1.process_strains_text")
					.append("  ) t4")
					.append(" ON t3.process_strains_text = t4.process_strains_text) t5");
		map.put("Sql", selectBuffer.toString());
		
		//原料消耗同比
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("SELECT t5.*, CASE WHEN t5.last_count IS NULL OR t5.last_count =0 THEN 0.00")
					.append("  ELSE ROUND(((t5.counts - t5.last_count)/t5.last_count)*100,2) END consumeRise")
					.append(" FROM (  SELECT t3.*,t4.last_count ")
					.append(" FROM  (")
					.append(" SELECT DATE_FORMAT(CONCAT(t1.start_time),'%Y') AS YearMonth,t1.start_time,t1.c_cot counts")
					.append(" FROM(SELECT dp.*,dcf.start_time,SUM(dp.consume_total_unit) c_cot")
					.append(" FROM da_process dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id ")
					.append(" where YEAR(dcf.start_time)='"+year+"'")
					.append(" ) t1")
					.append(" ) t3")
					.append(" LEFT JOIN ")
					.append(" ( SELECT DATE_FORMAT(CONCAT(t1.start_time),'%Y') AS lastYearMonth,t1.c_cot last_count")
					.append(" FROM(SELECT dp.*,dcf.start_time,SUM(dp.consume_total_unit) c_cot")
					.append(" FROM da_process dp")
					.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id")
					.append(" where YEAR(dcf.start_time)='"+year+"'-1")
					.append("  ) t1")
					.append("  ) t4")
					.append(" ON t3.YearMonth = t4.lastYearMonth+1) t5");
		map2.put("Sql", selectBuffer2.toString());
		//加工企业同比
				Map<String, Object> map3 = new HashMap<String, Object>();
				StringBuffer selectBuffer3 = new StringBuffer();
				selectBuffer3.append("SELECT t5.*, CASE WHEN t5.last_count IS NULL OR t5.last_count =0 THEN 0.00")
							.append("  ELSE ROUND(((t5.counts - t5.last_count)/t5.last_count)*100,2) END enterpriserRise")
							.append(" FROM (  SELECT t3.*,t4.last_count ")
							.append(" FROM  (")
							.append(" SELECT DATE_FORMAT(CONCAT(t1.found_date),'%Y') AS YearMonth,t1.found_date,t1.c_cot counts")
							.append(" FROM(SELECT  max(dp.found_date) found_date,COUNT(DISTINCT dp.found_date) c_cot")
							.append(" FROM da_enterprise_info dp")
							//.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id ")
							.append(" where (YEAR(dp.found_date) <  '"+year+"'  or  YEAR(dp.found_date) =  '"+year+"') ")
							.append(" and dp.enterprise_type_code='"+DiEnterpriseTypeEnum.JIAGONG.getCode()+"' ")
							.append(" ) t1")
							.append(" ) t3")
							.append(" LEFT JOIN ")
							.append(" ( SELECT DATE_FORMAT(CONCAT(t1.found_date),'%Y') AS lastYearMonth,t1.c_cot last_count")
							.append(" FROM(SELECT  max(dp.found_date) found_date,COUNT(DISTINCT dp.found_date) c_cot")
							.append(" FROM da_enterprise_info dp")
							//.append(" INNER JOIN da_common_field dcf ON dcf.id = dp.common_field_id")
							.append(" where YEAR(dp.found_date) < '"+year+"' ")
							.append(" and dp.enterprise_type_code='"+DiEnterpriseTypeEnum.JIAGONG.getCode()+"' ")
							.append("  ) t1")
							.append("  ) t4")
							.append(" ON t3.YearMonth <> t4.lastYearMonth) t5");
				map3.put("Sql", selectBuffer3.toString());
		resultModel = daProcessUntBll.getListBySQL(map);
		resultModel2 = daProcessUntBll.getListBySQL(map2);
		resultModel3 = daProcessUntBll.getListBySQL(map3);
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("productRise", resultModel.getData());//产量产值同比
		maps.put("consumeRise", resultModel2.getData());//原料消耗同比
		maps.put("enterpriserRise", resultModel3.getData());//加工企业同比
		list.add(maps);
		resultModel.setData(list);
		return resultModel;
	}
	
	@ApiOperation(value = "查询加工品产值结构数据", notes = "查询加工品产值结构数据")
	@RequestMapping(value = "/getProcessStructureInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getProcessStructureInfo() {
		BigDecimal valueSum = new BigDecimal("0");
		
		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String year = "";//年份id
		String regionId = "";//地区id
		
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
			}
		}
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> processTypeMap = getDicMapByTypeId(CustomSymbolic.DI_PROCESS_BREED);
		
		BigDecimal saleNum = new BigDecimal("0");
		int index = 0;
		String maxTrainsText = "";
		
		Iterator iter = processTypeMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			String code = (String) entry.getKey();
			String text = (String) entry.getValue();
			
			ResultModel perProcessModel = getPerProcessModel(year,regionId,code);
			
			Object perObj = perProcessModel.getData();
			List<Map<String, Object>> perList = null;
			Map<String, Object> perMap = new HashMap<String, Object>();
			if (perObj != null)
				perList = (List<Map<String, Object>>) perObj;
			if (perList != null && perList.size() >= 1) {
				perMap = perList.get(0);
				if("0".equals(code)) {
					if(perMap.containsKey("valueSum")) {
						valueSum = (BigDecimal) perMap.get("valueSum");
					}
				}else {
					if(perMap.containsKey("valueSum")) {
						BigDecimal proportion = new BigDecimal("0");
						BigDecimal perValue = (BigDecimal) perMap.get("valueSum");
						//by zero
						if(valueSum.compareTo(BigDecimal.ZERO)!=0) {
							proportion = perValue.divide(valueSum,4,BigDecimal.ROUND_HALF_UP);
						}
						if(index==0) {
							saleNum = proportion;
							if(perMap.containsKey("strainsText")) {
								String tempMaxStrainsText = (String) perMap.get("strainsText");
								maxTrainsText = tempMaxStrainsText;
							}
						}else {
							if(proportion.compareTo(saleNum)==1) {
								saleNum = proportion;
								if(perMap.containsKey("strainsText")) {
									String tempMaxStrainsText = (String) perMap.get("strainsText");
									maxTrainsText = tempMaxStrainsText;
								}
							}
						}
						DecimalFormat df = new DecimalFormat("0.00%");  
						String percent=df.format(proportion);
						perMap.put("processProportion", percent);
						index++;
					}
				}
			}else {
				perMap.put("strainsCode",code);
				perMap.put("strainsText",text);
				perMap.put("valueSum","0");
				perMap.put("processProportion","0.00%");
			}
			if(!"0".equals(code)) {
				dataList.add(perMap);
			}
		}
		resultMap.put("year", year);
		resultMap.put("maxTrainsText", maxTrainsText);
		resultMap.put("resultList", dataList);
		resultMap.put("totalSum", valueSum);

		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	private ResultModel getPerProcessModel(String year,String regionId,String breed) {
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    t0.* 
		FROM
		    (SELECT 
		        SUBSTR(B.start_time, 1, 4) AS TIME,
		        B.start_time AS startTime,
		        A.process_strains_code AS strainsCode,
		        A.process_strains_text AS strainsText,
		        CASE WHEN A.output_value_unit IS NULL THEN 0 ELSE A.output_value_unit END AS valueSum
		    FROM
		        da_process A,
		        da_common_field B 
		    WHERE A.common_field_id = B.id 
		        AND A.crop_type_code = 1 
		        AND A.process_strains_code <> 0  
		        AND B.data_time_type_code = 1 
		        AND B.area_latitude_type_code = 4 
		        AND B.audit_status_code = 1 
		        AND B.region_id = '530723' 
		        AND SUBSTR(B.start_time, 1, 4) = 2018 
		    ORDER BY startTime DESC) t0 
		GROUP BY strainsCode */
		
		selectBuffer.append(" SELECT t0.* FROM																						");
		selectBuffer.append(" (SELECT 																								");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 4) AS time,																	");
		selectBuffer.append(" B.start_time AS startTime,																			");
		selectBuffer.append(" A.process_strains_code AS strainsCode,																");
		selectBuffer.append(" A.process_strains_text AS strainsText,																");
		selectBuffer.append(" CASE WHEN A.output_value_unit IS NULL THEN 0 ELSE A.output_value_unit END AS valueSum					");
		selectBuffer.append(" FROM																									");
		selectBuffer.append(" da_process A,																							");
		selectBuffer.append(" da_common_field B 																					");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																		");
		selectBuffer.append(" AND A.crop_type_code = 1 																				");
		selectBuffer.append(" AND A.process_strains_code = 																			");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(breed)) {
			selectBuffer.append(breed);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.data_time_type_code = 1 																		");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																	");
		selectBuffer.append(" AND B.audit_status_code = 1 																			");
		selectBuffer.append(" AND B.`region_id` =																					");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																		");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(year)) {
			selectBuffer.append(year);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC) t0							 												");
		selectBuffer.append(" GROUP BY strainsCode																					");
		map.put("Sql", selectBuffer.toString());
		resultModel = daProcessUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	private Map<String,Object> getDicMapByTypeId(String dicTypeId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append(" SELECT A.`code` AS code, A.text AS text FROM gp_dictionary A WHERE type_id = '");
		if(StringUtils.isNotBlank(dicTypeId)) {
			selectBuffer.append(dicTypeId);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = gpDictionaryUntBll.getListBySQL(map);
		
		Map<String,Object> resultMap = null;
		Object obj = resultModel.getData();
		List<Map<String,Object>> list = null;
		if(obj!=null) {
			list = (List<Map<String, Object>>) obj;
			if(list!=null && list.size()>=1) {
				resultMap = new HashMap<String,Object>();
				for(Map<String,Object> tempMap : list) {
					String code = "";
					String text = "";
					if(tempMap.containsKey("code")) {
						code = (int) tempMap.get("code")+"";
					}
					if(tempMap.containsKey("text")) {
						text = (String) tempMap.get("text");
					}
					resultMap.put(code, text);
				}
				return resultMap;
			}
		}
		return null;
	}
	
}
