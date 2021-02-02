package com.zee.app.extend.swagger.da;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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
import com.zee.app.generate.swagger.da.DaMarketPriceGenSwgApp;
import com.zee.bll.extend.unity.da.DaCommonFieldUntBll;
import com.zee.bll.extend.unity.da.DaMarketPriceUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.bll.extend.unity.pi.PiChannelUntBll;
import com.zee.bll.extend.unity.pi.PiContentUntBll;
import com.zee.bll.generate.unity.pi.PiContentGenUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaAltitudeInfo;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.extend.da.DaMarketPrice;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.da.DaMarketPriceParameter;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.set.enumer.DomainEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.DateUtils;
import com.zee.utl.FileUtil;
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
 * @description  对外接口，扩展自DaMarketPriceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daMarketPrice")
public class DaMarketPriceSwgApp extends DaMarketPriceGenSwgApp {

	private static final String DAY_AVG_PRICE = "DAY";
	private static final String MON_AVG_PRICE = "MONTH";
	private static final String YEAR_AVG_PRICE = "YEAR";

	private static final String FIELD_PRICE = "1";
	private static final String DISPATCH_PRICE = "2";
	private static final String RETAIL_PRICE = "3";
	private static final String ECOMMERCE_PRICE = "4";

	private static final String DIMENSION_YEAR = "1";
	private static final String DIMENSION_MONTH = "3";
	private static final String DIMENSION_DAY = "4";

	private static final String[] TIME_FORMAT = { "yyyy", "","yyyy-MM", "yyyy-MM-dd" };
	private static final String[] TIME_START = { "1990","", "1990-01", "1990-01-01" };

	// 1，田头价 2，批发价 3，零售价 4，电商价 5，进口价 6，出口价
	private static final String PRICE_TYPE_FIELD = "1";
	private static final String PRICE_TYPE_DISPATCH = "2";
	private static final String PRICE_TYPE_RETAIL = "3";
	private static final String PRICE_TYPE_ECOMMERCE = "4";
	private static final String PRICE_TYPE_IMPORTATION = "5";
	private static final String PRICE_TYPE_EXPORT = "6";
	
	@Autowired
	private DaCommonFieldUntBll daCommonFieldUntBll;

	@Autowired
	@Qualifier("gpDictionaryUntBll")
	protected GpDictionaryUntBll gpDictionaryUntBll;

	@Autowired
	DaUserContributionUtil daUserContributionUtil;

	@Autowired
	private PiChannelUntBll piChannelUntBll;
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private PiContentGenUntBll piContentGenUntBll;
	@Autowired
	private PiContentUntBll piContentUntBll;
	
	@Autowired
	@Qualifier("daMarketPriceUntBll")
	protected DaMarketPriceUntBll daMarketPriceUntBll;
	
	@ApiOperation(value = "获取当前用户上传价格列表", notes = "获取当前用户上传价格列表")
	@RequestMapping(value = "/getListByCurrentUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByCurrentUser() {
		
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                    "); 
		selectBuffer.append("		A.strains_code strainsCode,                                           "); 
		selectBuffer.append("		A.strains_text strainsText,                                           "); 
//		selectBuffer.append("		A.price_type_code priceTypeCode,                                      "); 
//		selectBuffer.append("		A.price_type_text priceTypeText,                                      "); 
//		selectBuffer.append("		A.average_price_unit perPriceUnit,                                    "); 
		
		selectBuffer.append("		A.id,													");
		selectBuffer.append("		A.common_field_id commonFieldId,			");
		selectBuffer.append("		A.year,													");
		selectBuffer.append("round(ifnull(A.acre_cost_price,0),2) acreCostPrice,							");
		selectBuffer.append("		A.acre_cost_price_unit_text  acreCostPriceUnitText,		");
		selectBuffer.append("round(ifnull(A.field_price,0),2) fieldPrice,									");
		selectBuffer.append("		A.field_price_unit_text fieldPriceUnitText,				");
		selectBuffer.append("round(ifnull(A.retail_price,0),2) retailPrice,								");
		selectBuffer.append("		A.retail_price_unit_text retailPriceUnitText,				");
		selectBuffer.append("round(ifnull(A.trade_price,0),2) tradePrice,									");
		selectBuffer.append("		A.trade_price_unit_text tradePriceUnitText,				");
		selectBuffer.append("round(ifnull(A.e_commerce_price,0),2) eCommercePrice,							");
		selectBuffer.append("		A.e_commerce_price_unit_text eCommercePriceUnitText,		");
		
		selectBuffer.append("		DATE_FORMAT(B.add_time,'%Y-%m-%d') addTime                            "); 
		selectBuffer.append("	FROM                                                                      "); 
		selectBuffer.append("		da_market_price A                                                     "); 
		selectBuffer.append("	INNER JOIN da_common_field B ON A.common_field_id = B.id                  "); 
		selectBuffer.append("	WHERE                                                                     "); 
		selectBuffer.append("		B.add_user_id = '"+this.getCurrentUser().getId()+"'                   "); 
		
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

		resultModel = daMarketPriceUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "新增记录（直报数据）", notes = "新增单条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaMarketPrice") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaMarketPrice jsonData) {

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
			daCommonField.setDataTimeTypeCode((byte) 6);
			daCommonField.setDataTimeTypeText("实时");
			daCommonField.setStartTime(DateUtils.getCurrentTime());
			daCommonField.setEndTime(DateUtils.getCurrentTime());
		}

		ResultModel result = daCommonFieldUntBll.add(daCommonField);
		if (result.getIsSuccess()) {
			jsonData.setCommonFieldId(result.getObjectId());
			//jsonData.setPerPriceUnit(UnitUtil.switchUnit(jsonData.getPerPrice(), jsonData.getPerPriceUnitCode(), "DI_PER_PRICE_UNIT"));
			
			if(jsonData.getAcreCostPrice()!=null){
				jsonData.setAcreCostPriceUnit(UnitUtil.switchUnit(jsonData.getAcreCostPrice(), jsonData.getAcreCostPriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getFieldPrice()!=null){
				jsonData.setFieldPriceUnit(UnitUtil.switchUnit(jsonData.getFieldPrice(), jsonData.getFieldPriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getRetailPrice()!=null){
				jsonData.setRetailPriceUnit(UnitUtil.switchUnit(jsonData.getRetailPrice(), jsonData.getRetailPriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getTradePrice()!=null){
				jsonData.setTradePriceUnit(UnitUtil.switchUnit(jsonData.getTradePrice(), jsonData.getTradePriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getECommercePrice()!=null){
				jsonData.setECommercePriceUnit(UnitUtil.switchUnit(jsonData.getECommercePrice(), jsonData.getECommercePriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			
			resultModel = daMarketPriceUntBll.add(jsonData);
		}

		// 新增完之后，更新用户贡献度
		daUserContributionUtil.updateUserContribution(CustomSymbolic.CONTRIBUTION_PRICE);

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
		selectBuffer.append("	INNER JOIN da_market_price B ON A.id = B.common_field_id                               ");
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
				resultModel = daMarketPriceUntBll.delete(id);
			}
		}

		return resultModel;
	}

	@ApiOperation(value = "批量删除（直报数据）", notes = "根据主键列表批量删除相应记录（直报数据）")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaMarketPriceDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaMarketPriceParameter.DeleteByIdList jsonData) {
		
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
				resultModel = daMarketPriceUntBll.deleteByIdList(jsonData.getIdList());
			}
		}

		return resultModel;
	
		
	}

	@ApiOperation(value = "修改记录（直报数据）", notes = "修改指定记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaMarketPrice") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaMarketPrice jsonData) {
		
		ResultModel resultModel = new ResultModel();
		
		ResultModel result = daCommonFieldUntBll.update(jsonData.getDaCommonField());
		if(result.getIsSuccess()){
			jsonData.setCommonFieldId(result.getObjectId());
			if(jsonData.getAcreCostPrice()!=null){
				jsonData.setAcreCostPriceUnit(UnitUtil.switchUnit(jsonData.getAcreCostPrice(), jsonData.getAcreCostPriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getFieldPrice()!=null){
				jsonData.setFieldPriceUnit(UnitUtil.switchUnit(jsonData.getFieldPrice(), jsonData.getFieldPriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getRetailPrice()!=null){
				jsonData.setRetailPriceUnit(UnitUtil.switchUnit(jsonData.getRetailPrice(), jsonData.getRetailPriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getTradePrice()!=null){
				jsonData.setTradePriceUnit(UnitUtil.switchUnit(jsonData.getTradePrice(), jsonData.getTradePriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			if(jsonData.getECommercePrice()!=null){
				jsonData.setECommercePriceUnit(UnitUtil.switchUnit(jsonData.getECommercePrice(), jsonData.getECommercePriceUnitCode(), "DI_PER_PRICE_UNIT"));
			}
			resultModel = daMarketPriceUntBll.update(jsonData);
		}
		return resultModel;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "数据审核（直报数据）", notes = "数据审核（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaMarketPriceUpdateList") })
	@RequestMapping(value = "/audit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel audit(@RequestBody DaMarketPriceParameter.UpdateList jsonData) {
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
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "批量导入（直报数据）", notes = "批量导入多条记录（直报数据）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaMarketPriceAddList") })
	@RequestMapping(value = "/importExcel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel importExcel(MultipartFile file,HttpServletRequest request) throws Exception {
		ResultModel resultModel = new ResultModel();
		FileInputStream in = null;
		in = (FileInputStream) file.getInputStream();
           List<Map<String, Object>> ls = ImportExcelUtil.parseExcel(in, file.getOriginalFilename());
           List<DaMarketPrice> list1 = (List<DaMarketPrice>) ImportExcelUtil.transformJsonToBeanList(
           		JSON.toJSONString(ls).toString(), DaMarketPrice.class);
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
						if(map.containsKey(DictionaryEnum.DI_PRICE_TYPE.getText()+list1.get(i).getPriceTypeText())){
							  String status = map.get(DictionaryEnum.DI_PRICE_TYPE.getText()+list1.get(i).getPriceTypeText()).toString();
								list1.get(i).setPriceTypeCode(Byte.valueOf(status));//价格类型
							}
						if(map.containsKey(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getPerPriceUnitText())){
							  String status = map.get(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getPerPriceUnitText()).toString();
								list1.get(i).setPerPriceUnitCode(Byte.valueOf(status));//单价单位
							}
						if(map.containsKey(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getAveragePriceUnitText())){
							  String status = map.get(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getAveragePriceUnitText()).toString();
								list1.get(i).setAveragePriceUnitCode(Byte.valueOf(status));//平均价单位
							}
						if(map.containsKey(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getBottomPriceUnitText())){
							  String status = map.get(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getBottomPriceUnitText()).toString();
								list1.get(i).setBottomPriceUnitCode(Byte.valueOf(status));//最低价单位
							}
						if(map.containsKey(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getTopPriceUnitText())){
							  String status = map.get(DictionaryEnum.PER_PRICE_UNIT_CODE.getText()+list1.get(i).getTopPriceUnitText()).toString();
								list1.get(i).setTopPriceUnitCode(Byte.valueOf(status));//最高价单位
							}
						resultModel = daMarketPriceUntBll.add(list1.get(i));
						
					
				}
		}
		return resultModel;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daMarketPriceUntBll.getModel(id);

		if(result.getIsSuccess()){
			DaMarketPrice jsonData = (DaMarketPrice) result.getData();
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

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
//		selectBuffer.append("select A.id id,A.common_field_id commonFieldId,A.name name,A.crop_type_code cropTypeCode,A.crop_type_text cropTypeText,A.strains_code strainsCode,A.strains_text strainsText,A.price_type_code priceTypeCode,A.price_type_text priceTypeText,A.per_price perPrice,A.per_price_unit_code perPriceUnitCode,A.per_price_unit_text perPriceUnitText,A.per_price_unit perPriceUnit,A.average_price averagePrice,A.average_price_unit_code averagePriceUnitCode,A.average_price_unit_text averagePriceUnitText,A.average_price_unit averagePriceUnit,A.price_range_code priceRangeCode,A.price_range_text priceRangeText,A.bottom_price bottomPrice,A.bottom_price_unit_code bottomPriceUnitCode,A.bottom_price_unit_text bottomPriceUnitText,A.bottom_price_unit bottomPriceUnit,A.top_price topPrice,A.top_price_unit_code topPriceUnitCode,A.top_price_unit_text topPriceUnitText,A.top_price_unit topPriceUnit  from da_market_price A inner join da_market_price B on A.id=B.id where 1=1 ");
		selectBuffer.append(" select  ");
		selectBuffer.append(" A.id id,  ");
		selectBuffer.append(" A.common_field_id commonFieldId,  ");
		selectBuffer.append(" A.name name,  ");
		selectBuffer.append(" A.price_type_text priceTypeText,  ");
		selectBuffer.append(" A.strains_text strainsText,  ");
		selectBuffer.append(" A.average_price_unit averagePriceUnit,  ");
		selectBuffer.append(" A.bottom_price_unit bottomPriceUnit,  ");
		selectBuffer.append(" A.top_price_unit topPriceUnit,  ");
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
		selectBuffer.append(" da_market_price A  ");
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
				if (entityRelatedObject.containsKey("bottomPrice") && StringUtils.isNotBlank(entityRelatedObject.getString("bottomPrice")))
					selectBuffer.append(" and A.per_price_unit >= ").append(entityRelatedObject.getString("bottomPrice"));
				if (entityRelatedObject.containsKey("topPrice") && StringUtils.isNotBlank(entityRelatedObject.getString("topPrice")))
					selectBuffer.append(" and A.per_price_unit <= ").append(entityRelatedObject.getString("topPrice"));
				if (entityRelatedObject.containsKey("priceTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("priceTypeCode")))
					selectBuffer.append(" and A.price_type_code = ").append(entityRelatedObject.getString("priceTypeCode"));
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

		resultModel = daMarketPriceUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "价格监测-价格K线图-8")
	@RequestMapping(value = "/get8PriceKline", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel get8PriceKline() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = ""; // 查询类型 1：田头价，2：批发价，3：电商价
		String regionCode = ""; // 地区code 1：中国 510400：攀枝花市 460000：海南省 530723：华坪县
		String regionName = ""; // 地区name 1：中国 510400：攀枝花市 460000：海南省 530723：华坪县

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
					if(entityRelatedObject.getString("queryType").equals("3")){
						queryType = "7";
					}else{
						queryType = entityRelatedObject.getString("queryType");
					}
					
				if (entityRelatedObject.containsKey("regionCode") && StringUtils.isNotBlank(entityRelatedObject.getString("regionCode")))
					regionCode = entityRelatedObject.getString("regionCode");
				if (entityRelatedObject.containsKey("regionName") && StringUtils.isNotBlank(entityRelatedObject.getString("regionName")))
					regionName = entityRelatedObject.getString("regionName");
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();

		// 查询华坪芒果种植面积前八的品种
		StringBuffer selectBuffer = new StringBuffer();
		if(queryType.equals("7")){
			selectBuffer.append(" select t.strains_code,t.strains_text from (select A.strains_code,A.strains_text from mf_sale_ecommerce_craw A ");
			selectBuffer.append(" inner join da_common_field B on A.common_field_id = B.id where B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBuffer.append(" and A.strains_text <> '其他' group by A.strains_code limit 0,4) t ");
		}else{
			selectBuffer.append(" select t.strains_code,t.strains_text from (select A.strains_code,A.strains_text,sum(A.grow_area_unit) from da_grow_yield A ");
			selectBuffer.append(" inner join da_common_field B on A.common_field_id = B.id where A.crop_type_code = '1' and B.region_id = '530723' ");
			selectBuffer.append(" and A.strains_code <> '0' and A.strains_text <> '其他' group by A.strains_code order by sum(A.grow_area_unit) desc limit 0,8) t ");
		}
		

		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("strains", (List<Map<String, Object>>) resultModel.getData());// 数据

		List<String> strainsList = new ArrayList<String>();
		for (Map<String, Object> map1 : maps.get("strains")) {
			strainsList.add("'" + map1.get("strains_code").toString() + "'");
		}

		String strainsCode = strainsList.toString();
		strainsCode = strainsCode.replace("[", "").replace("]", "");
		String[] strainsArr = strainsCode.split(",");
		if(strainsCode.equals("")){
			strainsCode = "''";
		}

		// 查询近七日 [周最低价,周最高价,周初价,周末价]
		selectBuffer = new StringBuffer();
		if(queryType.equals("7")){
			String sqlString = "from mf_sale_ecommerce_craw A inner join da_common_field B on A.common_field_id = B.id where "
					+ "B.data_time_type_code = 5 and A.crop_type_code = '1' and A.strains_code in ("+ strainsCode +") "
					+ "group by B.start_time, A.strains_code order by substr(B.start_time,1,10)";
			selectBuffer.append(" select t0.times,t0.code,t0.text,case when t3.average_price_unit is null then '0' else t3.average_price_unit end start_price, ");
			selectBuffer.append(" case when t2.average_price_unit is null then '0' else t2.average_price_unit end end_price,  case when t1.bottom_price_unit ");
			selectBuffer.append(" is null then '0' else t1.bottom_price_unit end bottom_price, case when t1.top_price_unit is null then '0' else t1.top_price_unit ");
			selectBuffer.append(" end top_price from (select d.code,d.text,t.* from gp_dictionary d,past_1_week_view t where d.type_id = '48690fc04089cb696dfad2c1780153a7' and d.code in ("+ strainsCode +") ");
			selectBuffer.append(" order by d.code,t.times) t0 left join  (select T.start_time year, T.strains_code,T.strains_text,T.bottom_price_unit,T.top_price_unit ");
			selectBuffer.append(" from (select substr(B.start_time,1,10) start_time,A.strains_code,A.strains_text,round(min(A.sale_price_unit),2) bottom_price_unit,");
			selectBuffer.append(" round(max(A.sale_price_unit),2) top_price_unit from mf_sale_ecommerce_craw A inner join da_common_field B on A.common_field_id = B.id where ");
			selectBuffer.append(" B.data_time_type_code = 5 and A.crop_type_code = '1' and A.strains_code in ("+ strainsCode +") group by substr(B.start_time,1,10), A.strains_code order by ");
			selectBuffer.append(" substr(B.start_time,1,10) desc) T group by T.start_time,T.strains_code) t1 on t0.times = t1.year and t0.code = t1.strains_code left join ");
			selectBuffer.append(" (select substr(T.start_time,1,10) year, T.strains_code,T.strains_text,T.average_price_unit from (select max(B.start_time) start_time,A.strains_code,A.strains_text,");
			selectBuffer.append(" round(avg(A.sale_price_unit),2) average_price_unit " + sqlString + " desc) T group by substr(T.start_time,1,10),T.strains_code) t2 on t0.times = t2.year ");
			selectBuffer.append(" and t0.code = t2.strains_code left join  (select substr(T.start_time,1,10) year, T.strains_code,T.strains_text,T.average_price_unit ");
			selectBuffer.append(" from (select min(B.start_time) start_time,A.strains_code,A.strains_text,round(avg(A.sale_price_unit),2) average_price_unit ");
			selectBuffer.append(" " + sqlString + " asc) T group by substr(T.start_time,1,10),T.strains_code ) t3 on t0.times = t3.year and t0.code = t3.strains_code ");
		}else{
			selectBuffer.append(" select t0.times,t0.code,t0.text,case when t3.average_price_unit is null then '0' else t3.average_price_unit end start_price, ");
			selectBuffer.append(" case when t2.average_price_unit is null then '0' else t2.average_price_unit end end_price, ");
			selectBuffer.append(" case when t1.bottom_price_unit is null then '0' else t1.bottom_price_unit end bottom_price,");
			selectBuffer.append(" case when t1.top_price_unit is null then '0' else t1.top_price_unit end top_price ");
			selectBuffer.append(" from (select d.code,d.text,t.* from gp_dictionary d,past_1_week_view t where d.type_id = '48690fc04089cb696dfad2c1780153a7' ");
			selectBuffer.append(" and d.code in (" + strainsCode + ") order by d.code,t.times) t0 left join ");
			selectBuffer.append(" (select substr(T.start_time,1,10) year,T.strains_code,T.strains_text,T.bottom_price_unit,T.top_price_unit from ");
			selectBuffer.append(" (select B.start_time,A.strains_code,A.strains_text,A.bottom_price_unit,A.top_price_unit from da_market_price A ");
			selectBuffer.append(" INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBuffer.append(" and A.strains_code in (" + strainsCode + ") and B.region_id = '" + regionCode + "' and A.price_type_code = '" + queryType + "' group by B.start_time,A.strains_code ");
			selectBuffer.append(" order by B.add_time desc) T group by substr(T.start_time,1,10),T.strains_code) t1 on t0.times = t1.year and t0.code = t1.strains_code left join ");
			selectBuffer.append(" (select substr(T.start_time,1,10) year,T.strains_code,T.strains_text,T.average_price_unit from (select B.start_time,A.strains_code, ");
			selectBuffer.append(" A.strains_text,A.average_price_unit from  da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
			selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and A.strains_code in (" + strainsCode + ") and B.region_id = '" + regionCode + "' ");
			selectBuffer.append(" and A.price_type_code = '" + queryType + "' order by B.add_time desc) T group by substr(T.start_time,1,10),T.strains_code) t2 on t0.times = t2.year and t0.code = t2.strains_code left join ");
			selectBuffer.append(" (select substr(T.start_time,1,10) year,T.strains_code,T.strains_text,T.average_price_unit from (select B.start_time,A.strains_code, ");
			selectBuffer.append(" A.strains_text,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
			selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and A.strains_code in (" + strainsCode + ") and B.region_id = '" + regionCode + "' ");
			selectBuffer.append(" and A.price_type_code = '" + queryType + "' order by B.add_time asc) T group by substr(T.start_time,1,10),T.strains_code) t3 on t0.times = t3.year and t0.code = t3.strains_code ");
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("data", (List<Map<String, Object>>) resultModel.getData());// 数据

		// 查询时间轴 封装时间轴
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select * from past_1_week_view order by times asc ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("time", (List<Map<String, Object>>) resultModel.getData());// 时间
		
//		// 查询时间轴
//		Map<String, String> dayMap = new HashMap<String, String>();
//		dayMap.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
//		dayMap.put("hasCurrent", "false");
//		dayMap.put("pastNum", "7");
//		List<String> dayList = TimesView.getTimesView(dayMap);
//		
//		String days = dayList.toString();
//		String queryDays = days.replace("[", "'").replace("]", "'").replace(", ","','").trim();
//		
//		String day = days.replace("[", "").replace("]", "");
//		String[] daysArr = day.split(", ");            // 时间轴
				

		// 查询平均线
		selectBuffer = new StringBuffer();
		if(queryType.equals("7")){
			selectBuffer.append(" select t0.times,t0.code,t0.text,case when t1.average_price_unit is null then '0' else t1.average_price_unit end average_price_unit ");
			selectBuffer.append(" from (select d.code,d.text,t.* from gp_dictionary d,past_1_week_view t where d.type_id = '48690fc04089cb696dfad2c1780153a7' and d.code in (" + strainsCode + ") ");
			selectBuffer.append(" order by d.code,t.times) t0 left join (select T.start_time year, T.strains_code,T.strains_text,T.average_price_unit ");
			selectBuffer.append(" from (select substr(B.start_time,1,10) start_time,A.strains_code,A.strains_text,round(avg(A.sale_price_unit),2) average_price_unit ");
			selectBuffer.append(" from mf_sale_ecommerce_craw A inner join da_common_field B on A.common_field_id = B.id where B.data_time_type_code = 5  ");
			selectBuffer.append(" and A.crop_type_code = '1' and A.strains_code in (" + strainsCode + ") group by substr(B.start_time,1,10),A.strains_code order by substr(B.start_time,1,10) desc) T ");
			selectBuffer.append(" group by T.start_time,T.strains_code) t1 on t0.times = t1.year and t0.code = t1.strains_code  ");
		}else{
			selectBuffer.append(" select t0.times,t0.code,t0.text,case when t1.average_price_unit is null then '0' else t1.average_price_unit end average_price_unit ");
			selectBuffer.append(" from (select d.code,d.text,t.* from gp_dictionary d,past_1_week_view t where d.type_id = '48690fc04089cb696dfad2c1780153a7' ");
			selectBuffer.append(" and d.code in (" + strainsCode + ") order by d.code,t.times) t0 left join (select substr(T.start_time,1,10) year,");
			selectBuffer.append(" T.strains_code,T.strains_text,T.average_price_unit from (select B.start_time,A.strains_code,A.strains_text,A.average_price_unit ");
			selectBuffer.append(" from da_market_price A inner join da_common_field B on A.common_field_id = B.id where B.data_time_type_code = 5 ");
			selectBuffer.append(" and A.crop_type_code = '1' and B.region_id = '" + regionCode + "' and A.strains_code in (" + strainsCode + ") and A.price_type_code = '" + queryType + "' ");
			selectBuffer.append(" group by B.start_time,A.strains_code order by B.add_time desc) T group by substr(T.start_time,1,10),T.strains_code) t1 on t0.times = t1.year and t0.code = t1.strains_code ");
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("avg", (List<Map<String, Object>>) resultModel.getData());// 平均值

		List<Object> rt = new ArrayList<Object>();
		Map<String, List<Object>> dataMap;
		Map<String, Object> dataMap0;

		List<Object> timeList = new ArrayList<Object>();
		for (Map<String, Object> map1 : maps.get("time")) {
			timeList.add(map1.get("times").toString());
		}

		DecimalFormat format = new DecimalFormat("0.00");
		for (int j = 0; j < strainsArr.length; j++) {
			dataMap = new HashMap<String, List<Object>>();
			dataMap0 = new HashMap<String, Object>();
			String strain = strainsArr[j].replace("'", "").replace(" ", "");
			List<Object> nameList = new ArrayList<Object>();
			List<Object> avgList = new ArrayList<Object>();
			int i = 1;
			for (Map<String, Object> map1 : maps.get("avg")) {
				if (strain.equals(map1.get("code").toString())) {
					if (i == 1) {
						nameList.add(map1.get("text").toString());
					}
					String average_price_unit = map1.get("average_price_unit").toString();
					String averagePrice = format.format(new BigDecimal(average_price_unit));
					avgList.add(averagePrice);
					i++;
				}
			}
			dataMap.put("avg", avgList);
			// dataMap.put("name", nameList);

			List<Object> dataList = new ArrayList<Object>();
			for (Map<String, Object> map1 : maps.get("data")) {
				if (strain.equals(map1.get("code").toString())) {
					List<String> numList0 = new ArrayList<String>();

					String start_price = map1.get("start_price").toString();
					String end_price = map1.get("end_price").toString();
					String bottom_price = map1.get("bottom_price").toString();
					String top_price = map1.get("top_price").toString();
					String startPrice = format.format(new BigDecimal(start_price));
					String endPrice = format.format(new BigDecimal(end_price));
					String bottomPrice = format.format(new BigDecimal(bottom_price));
					String topPrice = format.format(new BigDecimal(top_price));

					numList0.add(map1.get("times").toString());
					numList0.add(startPrice);
					numList0.add(endPrice);
					numList0.add(bottomPrice);
					numList0.add(topPrice);
					dataList.add(numList0);
				}
			}
			dataMap.put("data", dataList);
			dataMap.put("time", timeList);
			dataMap0.put("Kdata", dataMap);
			dataMap0.put("name", nameList);

			rt.add(dataMap0);
		}

		resultModel.setData(rt);
		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "价格监测-芒果品种涨跌幅度监测")
	@RequestMapping(value = "/getPriceStrainChg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceStrainChg() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = ""; // 查询类型 1：田头价，2：批发价，3：电商价
		String queryTime = ""; // 查询时间 2018-05

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
					if(entityRelatedObject.getString("queryType").equals("3")){
						queryType = "7";
					}else{
						queryType = entityRelatedObject.getString("queryType");
					}
				if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
					queryTime = entityRelatedObject.getString("queryTime");
			}

		}

		Map<String, Object> map = new HashMap<String, Object>();

		// 价格监测-芒果品种涨跌幅度监测(全国)
		StringBuffer selectBuffer = new StringBuffer();
		if(queryType.equals("7")){
			selectBuffer.append(" select T1.strains_code,T1.strains_text,round((T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit*100,2) chg ");
			selectBuffer.append(" from (select T.strains_code,T.strains_text,AVG(T.average_price_unit) average_price_unit from (select A.strains_code,A.strains_text, ");
			selectBuffer.append(" A.sale_price_unit average_price_unit from mf_sale_ecommerce_craw A INNER JOIN da_common_field B ");
			selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBuffer.append(" and substr(B.start_time,1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.strains_code) T1, ");
			selectBuffer.append(" (select T.strains_code,T.strains_text,AVG(T.average_price_unit) average_price_unit from (select A.strains_code, ");
			selectBuffer.append(" A.strains_text,A.sale_price_unit average_price_unit from mf_sale_ecommerce_craw A INNER JOIN da_common_field B ");
			selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBuffer.append(" and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) = '" + queryTime + "' order by B.add_time desc) T ");
			selectBuffer.append(" group by T.strains_code) T2 where T1.strains_code = T2.strains_code ");
		}else{
			selectBuffer.append(" select T1.strains_code,T1.strains_text,round((T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit*100,2) chg from (select T.strains_code,T.strains_text,AVG(T.average_price_unit) average_price_unit from ");
			selectBuffer.append(" (select A.strains_code,A.strains_text,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ");
			selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBuffer.append(" and A.price_type_code = '" + queryType + "' and substr(B.start_time,1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.strains_code) T1, ");
			selectBuffer.append(" (select T.strains_code,T.strains_text,AVG(T.average_price_unit) average_price_unit from (select A.strains_code,A.strains_text,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ");
			selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBuffer.append(" and A.price_type_code = '" + queryType + "' and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) = '" + queryTime + "' order by B.add_time desc) T ");
			selectBuffer.append(" group by T.strains_code) T2 where T1.strains_code = T2.strains_code ");
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);

		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		maps.put("data", (List<Map<String, Object>>) resultModel.getData());

		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> dataMap;
		for (Map<String, Object> map1 : maps.get("data")) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("strains_code", map1.get("strains_code").toString());
			dataMap.put("strains_text", map1.get("strains_text").toString());
			dataMap.put("chg", map1.get("chg").toString());
			// 上传参数返回
			if (queryType.equals("1")) {
				queryType = "田头价";
			} else if (queryType.equals("2")) {
				queryType = "批发价";
			} else if (queryType.equals("7")) {
				queryType = "电商价";
			}

			dataMap.put("queryType", queryType);
			dataMap.put("queryTime", queryTime);
			dataList.add(dataMap);
		}

		resultModel.setData(dataList);
		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "价格监测-城市涨跌幅度TOP10")
	@RequestMapping(value = "/getPriceCityChgTop10", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceCityChgTop10() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryType = ""; // 查询类型 1：田头价，2：批发价，3：电商价
		String queryPattern = ""; // 查询模式 1：涨，2：跌
		String queryTime = ""; // 查询时间 2018-05

		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("queryPattern") && StringUtils.isNotBlank(entityRelatedObject.getString("queryPattern")))
				queryPattern = entityRelatedObject.getString("queryPattern");
			if (entityRelatedObject.containsKey("queryType") && StringUtils.isNotBlank(entityRelatedObject.getString("queryType")))
				if(entityRelatedObject.getString("queryType").equals("3")){
					queryType = "7";
				}else{
					queryType = entityRelatedObject.getString("queryType");
				}
			if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
				queryTime = entityRelatedObject.getString("queryTime");
		}

		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}

		StringBuffer selectBuffer = new StringBuffer();
		if (queryPattern.equals("1")) {
			// 价格监测-城市涨跌幅度TOP10(涨幅)--若为电商价则从电商表查询
			if(queryType.equals("7")){
				selectBuffer.append(" select T1.region_id,T1.region_name,round((T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit*100,2) chg ");
				selectBuffer.append(" from (select T.region_id,T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id, ");
				selectBuffer.append(" B.region_name,A.sale_price_unit average_price_unit from mf_sale_ecommerce_craw A INNER JOIN da_common_field B ");
				selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 ");
				selectBuffer.append(" and substr(B.start_time,1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.region_id) T1,(select T.region_id,");
				selectBuffer.append(" T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id,B.region_name,A.sale_price_unit average_price_unit ");
				selectBuffer.append(" from mf_sale_ecommerce_craw A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 ");
				selectBuffer.append(" and B.area_latitude_type_code = 3 and A.crop_type_code = '1' and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) = '" + queryTime + "' ");
				selectBuffer.append(" order by B.add_time desc) T group by T.region_id) T2 where T1.region_id = T2.region_id ");
				selectBuffer.append(" and (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit > 0 ");
				selectBuffer.append(" order by (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit desc ");
			}else{
				selectBuffer.append(" select T1.region_id,T1.region_name,round((T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit*100,2) chg from ");
				selectBuffer.append(" (select T.region_id,T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id,B.region_name,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ");
				selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 ");
				selectBuffer.append(" and A.price_type_code = '" + queryType + "' and substr(B.start_time,1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.region_id) T1,");
				selectBuffer.append(" (select T.region_id,T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id,B.region_name,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
				selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 and A.price_type_code = '" + queryType + "' ");
				selectBuffer.append(" and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.region_id) T2 ");
				selectBuffer.append(" where T1.region_id = T2.region_id and (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit > 0 ");
				selectBuffer.append(" order by (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit desc ");
			}
		} else if (queryPattern.equals("2")) {
			// 价格监测-城市涨跌幅度TOP10(跌幅)--若为电商价则从电商表查询
			if(queryType.equals("7")){
				selectBuffer.append(" select T1.region_id,T1.region_name,round((T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit*100,2) chg ");
				selectBuffer.append(" from (select T.region_id,T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id, ");
				selectBuffer.append(" B.region_name,A.sale_price_unit average_price_unit from mf_sale_ecommerce_craw A INNER JOIN da_common_field B ");
				selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 ");
				selectBuffer.append(" and substr(B.start_time,1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.region_id) T1,(select T.region_id,");
				selectBuffer.append(" T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id,B.region_name,A.sale_price_unit average_price_unit ");
				selectBuffer.append(" from mf_sale_ecommerce_craw A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 ");
				selectBuffer.append(" and B.area_latitude_type_code = 3 and A.crop_type_code = '1' and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) = '" + queryTime + "' ");
				selectBuffer.append(" order by B.add_time desc) T group by T.region_id) T2 where T1.region_id = T2.region_id ");
				selectBuffer.append(" and (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit < 0 ");
				selectBuffer.append(" order by (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit asc ");
			}else{
				selectBuffer.append(" select T1.region_id,T1.region_name,round((T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit*100,2) chg from ");
				selectBuffer.append(" (select T.region_id,T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id,B.region_name,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ");
				selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 ");
				selectBuffer.append(" and A.price_type_code = '" + queryType + "' and substr(B.start_time,1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.region_id) T1, ");
				selectBuffer.append(" (select T.region_id,T.region_name,AVG(T.average_price_unit) average_price_unit from (select B.region_id,B.region_name,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
				selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 and A.price_type_code = '" + queryType + "' ");
				selectBuffer.append(" and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) = '" + queryTime + "' order by B.add_time desc) T group by T.region_id) T2 ");
				selectBuffer.append(" where T1.region_id = T2.region_id and (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit < 0 ");
				selectBuffer.append(" order by (T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit asc ");
			}
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);

		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		maps.put("data", (List<Map<String, Object>>) resultModel.getData());

		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> dataMap;
		for (Map<String, Object> map1 : maps.get("data")) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("region_id", map1.get("region_id").toString());
			dataMap.put("region_name", map1.get("region_name").toString());
			dataMap.put("chg", map1.get("chg").toString());
			// 上传参数返回
			if (queryType.equals("1")) {
				queryType = "田头价";
			} else if (queryType.equals("2")) {
				queryType = "批发价";
			} else if (queryType.equals("7")) {
				queryType = "电商价";
			}

			if (queryPattern.equals("1")) {
				queryPattern = "涨";
			} else if (queryPattern.equals("2")) {
				queryPattern = "跌";
			}
			dataMap.put("queryType", queryType);
			dataMap.put("queryPattern", queryPattern);
			dataMap.put("queryTime", queryTime);
			dataList.add(dataMap);
		}

		resultModel.setData(dataList);

		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "价格监测-城市涨跌幅度-数字服务版")
	@RequestMapping(value = "/getPriceCityChg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceCityChg() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String compareType = "";     //查询类型   1：环比 2：同比
		String queryTime = "";       //查询时间   2018-05
		String queryPattern = "";    //涨跌标志

		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			if (entityRelatedObject.containsKey("compareType") && StringUtils.isNotBlank(entityRelatedObject.getString("compareType")))
				compareType = entityRelatedObject.getString("compareType");
		}

		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}

		Map<String, Object> map1 = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps1 = new HashMap<String, List<Map<String, Object>>>();
		// 查询最新有数据的月份
		StringBuffer selectBuffer1 = new StringBuffer();
		selectBuffer1.append(" select max(substr(B.start_time,1,7)) queryTime from da_market_price A INNER JOIN da_common_field B ");
		selectBuffer1.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
		selectBuffer1.append(" and A.price_type_code = '2' and (B.region_name like '%市' or B.area_latitude_type_code = 3 ) ORDER BY B.start_time DESC ");

		map1.put("Sql", selectBuffer1.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map1);
		maps1.put("queryTime", (List<Map<String, Object>>) resultModel.getData());// 数据

		for (Map<String, Object> map0 : maps1.get("queryTime")) {
			queryTime = map0.get("queryTime").toString();
		}

		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();

		StringBuffer selectBufferZ = new StringBuffer();
		StringBuffer selectBufferD = new StringBuffer();

		if(compareType.equals("1")){
			// 价格监测-城市涨跌幅度TOP5(涨幅)-环比
			selectBufferZ.append(" select A.* from (select T1.region_id,T1.region_name,round((T1.price - T2.price)/T2.price*100,2) chg from (select substr(B.start_time,1,7), ");
			selectBufferZ.append(" B.region_id,B.region_name, CASE WHEN AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price ");
			selectBufferZ.append(" from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBufferZ.append(" and substr(B.start_time,1,7) = '" + queryTime + "' and A.price_type_code = '2' and B.area_latitude_type_code = 3 ");
			selectBufferZ.append(" GROUP BY B.region_id ORDER BY B.start_time DESC) T1,(select substr(B.start_time,1,7),B.region_id,B.region_name,CASE WHEN ");
			selectBufferZ.append(" AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price  from da_market_price A INNER JOIN da_common_field B ");
			selectBufferZ.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) ");
			selectBufferZ.append(" = '" + queryTime + "' and A.price_type_code = '2' and  B.area_latitude_type_code = 3 GROUP BY B.region_id ORDER BY B.start_time DESC) T2 where ");
			selectBufferZ.append(" T1.region_id = T2.region_id and (T1.price - T2.price)/T2.price > 0 order by (T1.price - T2.price)/T2.price desc limit 0,5) A ");
			
			// 价格监测-城市涨跌幅度TOP5(跌幅)-环比
			selectBufferD.append(" select A.* from (select T1.region_id,T1.region_name,round((T1.price - T2.price)/T2.price*100,2) chg from (select substr(B.start_time,1,7), ");
			selectBufferD.append(" B.region_id,B.region_name, CASE WHEN AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price ");
			selectBufferD.append(" from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBufferD.append(" and substr(B.start_time,1,7) = '" + queryTime + "' and A.price_type_code = '2' and B.area_latitude_type_code = 3 ");
			selectBufferD.append(" GROUP BY B.region_id ORDER BY B.start_time DESC) T1,(select substr(B.start_time,1,7),B.region_id,B.region_name,CASE WHEN ");
			selectBufferD.append(" AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price  from da_market_price A INNER JOIN da_common_field B ");
			selectBufferD.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and substr(DATE_ADD(B.start_time,INTERVAL 1 MONTH),1,7) ");
			selectBufferD.append(" = '" + queryTime + "' and A.price_type_code = '2' and  B.area_latitude_type_code = 3 GROUP BY B.region_id ORDER BY B.start_time DESC) T2 where ");
			selectBufferD.append(" T1.region_id = T2.region_id and (T1.price - T2.price)/T2.price < 0 order by (T1.price - T2.price)/T2.price desc limit 0,5) A ");
		}else{
			// 价格监测-城市涨跌幅度TOP5(涨幅)-同比
			selectBufferZ.append(" select A.* from (select T1.region_id,T1.region_name,round((T1.price - T2.price)/T2.price*100,2) chg from (select substr(B.start_time,1,7), ");
			selectBufferZ.append(" B.region_id,B.region_name, CASE WHEN AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price ");
			selectBufferZ.append(" from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBufferZ.append(" and substr(B.start_time,1,7) = '" + queryTime + "' and A.price_type_code = '2' and B.area_latitude_type_code = 3 ");
			selectBufferZ.append(" GROUP BY B.region_id ORDER BY B.start_time DESC) T1,(select substr(B.start_time,1,7),B.region_id,B.region_name,CASE WHEN ");
			selectBufferZ.append(" AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price  from da_market_price A INNER JOIN da_common_field B ");
			selectBufferZ.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and substr(DATE_ADD(B.start_time,INTERVAL 1 YEAR),1,7) ");
			selectBufferZ.append(" = '" + queryTime + "' and A.price_type_code = '2' and  B.area_latitude_type_code = 3 GROUP BY B.region_id ORDER BY B.start_time DESC) T2 where ");
			selectBufferZ.append(" T1.region_id = T2.region_id and (T1.price - T2.price)/T2.price > 0 order by (T1.price - T2.price)/T2.price desc limit 0,5) A ");
			
			// 价格监测-城市涨跌幅度TOP5(跌幅)-同比
			selectBufferD.append(" select A.* from (select T1.region_id,T1.region_name,round((T1.price - T2.price)/T2.price*100,2) chg from (select substr(B.start_time,1,7), ");
			selectBufferD.append(" B.region_id,B.region_name, CASE WHEN AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price ");
			selectBufferD.append(" from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
			selectBufferD.append(" and substr(B.start_time,1,7) = '" + queryTime + "' and A.price_type_code = '2' and B.area_latitude_type_code = 3 ");
			selectBufferD.append(" GROUP BY B.region_id ORDER BY B.start_time DESC) T1,(select substr(B.start_time,1,7),B.region_id,B.region_name,CASE WHEN ");
			selectBufferD.append(" AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS price  from da_market_price A INNER JOIN da_common_field B ");
			selectBufferD.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and substr(DATE_ADD(B.start_time,INTERVAL 1 YEAR),1,7) ");
			selectBufferD.append(" = '" + queryTime + "' and A.price_type_code = '2' and  B.area_latitude_type_code = 3 GROUP BY B.region_id ORDER BY B.start_time DESC) T2 where ");
			selectBufferD.append(" T1.region_id = T2.region_id and (T1.price - T2.price)/T2.price < 0 order by (T1.price - T2.price)/T2.price desc limit 0,5) A ");
		}
		

		map.put("Sql", selectBufferZ.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("Zdata", (List<Map<String, Object>>) resultModel.getData());

		map.put("Sql", selectBufferD.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("Ddata", (List<Map<String, Object>>) resultModel.getData());

		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> dataMap;
		for (Map<String, Object> map0 : maps.get("Zdata")) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("region_id", map0.get("region_id").toString());
			dataMap.put("region_name", map0.get("region_name").toString());
			dataMap.put("chg", map0.get("chg").toString());

			queryPattern = "涨";
			dataMap.put("queryPattern", queryPattern);
			dataMap.put("queryTime",queryTime);
			dataList.add(dataMap);
		}

		for (Map<String, Object> map0 : maps.get("Ddata")) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("region_id", map0.get("region_id").toString());
			dataMap.put("region_name", map0.get("region_name").toString());
			dataMap.put("chg", map0.get("chg").toString());

			queryPattern = "跌";
			dataMap.put("queryPattern", queryPattern);
			dataMap.put("queryTime",queryTime);
			dataList.add(dataMap);
		}

		resultModel.setData(dataList);

		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "价格监测-产销价差（近7日，产地为华坪 530723/及其竞争对手如百色 海南 ；销地为34个省市自治区级的价格）")
	@RequestMapping(value = "/getPriceGap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceGap() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		
		// 查询时间轴
		Map<String, String> dayMap = new HashMap<String, String>();
		dayMap.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
		dayMap.put("hasCurrent", "false");
		dayMap.put("pastNum", "8");
		List<String> dayList = TimesView.getTimesView(dayMap);
		
		String days = dayList.toString();
		String queryDays = days.replace("[", "'").replace("]", "'").replace(", ","','").trim();
		
		String day = days.replace("[", "").replace("]", "");
		String[] daysArr = day.split(", ");            // 时间轴
		
		// 价格监测-产销价差
		
//		selectBuffer.append(" select C.times,round(C.value1,2) prod_price,round(X.value2,2) sale_price,X.region_id,X.region_name,round(X.value2-C.value1,2) gap from ");
//		selectBuffer.append(" (select t0.times,case when t1.value1 is null then 0 else t1.value1 end value1 from past_1_week_view t0 left join ");
//		selectBuffer.append(" (select substr(T.start_time,1,10) times,T.average_price_unit value1 from (select B.start_time,A.average_price_unit ");
//		selectBuffer.append(" from da_market_price A  INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 ");
//		selectBuffer.append(" and A.crop_type_code = '1' and A.price_type_code = '2'  and B.region_id = '530723' order by B.add_time desc) T ");
//		selectBuffer.append(" group by substr(T.start_time,1,10)) t1 on t0.times = t1.times) C,(select t0.times,t1.region_id,t1.region_name,case when t1.value2 is null then 0 else t1.value2 end value2 from past_1_week_view t0 left join ");
//		selectBuffer.append(" (select T.region_id,T0.name region_name,substr(T.start_time,1,10) times,T.average_price_unit value2 from (select B.start_time,");
//		selectBuffer.append(" CONCAT(substr(B.region_id,1,2),'0000') region_id,round(avg(A.average_price_unit),2) average_price_unit from da_market_price A ");
//		selectBuffer.append(" INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
//		selectBuffer.append(" and A.price_type_code = '2' and B.area_latitude_type_code = '3' group by substr(B.start_time,1,10),substr(B.region_id,1,2) ");
//		selectBuffer.append(" order by B.add_time desc) T,(select code,name from gp_region where code like '%0000' and region_level = '2') T0 ");
//		selectBuffer.append(" where T.region_id = T0.code group by substr(T.start_time,1,10),T.region_id) t1 ");
//		selectBuffer.append(" on t0.times = t1.times) X where C.times = X.times and X.region_name is not null order by C.times asc ");
		
		// 查询产地价格
		StringBuffer selectBufferPP = new StringBuffer();
		selectBufferPP.append(" select substr(B.start_time,1,10) times,round(avg(A.average_price_unit),2) value1 from da_market_price A  INNER JOIN da_common_field B ");
		selectBufferPP.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and A.price_type_code = '2' and B.region_id = '530723' ");
		selectBufferPP.append(" and substr(B.start_time,1,10) in ("+queryDays+") group by substr(B.start_time,1,10) ");
		
		map.put("Sql", selectBufferPP.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("prodPrice", (List<Map<String, Object>>)resultModel.getData());// 产地价格
		
		
		// 查询销地
		StringBuffer selectBufferS = new StringBuffer();
		selectBufferS.append(" select t.* from (select DISTINCT T.region_id,T0.name region_name from (select distinct CONCAT(substr(B.region_id,1,2),'0000') region_id ");
		selectBufferS.append(" from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 ");
		selectBufferS.append(" and A.crop_type_code = '1' and A.price_type_code = '2' and B.area_latitude_type_code = '3' and substr(B.start_time,1,10) ");
		selectBufferS.append(" in ("+queryDays+") group by substr(B.start_time,1,10),substr(B.region_id,1,2)) T,(select code,name from gp_region ");
		selectBufferS.append(" where code like '%0000' and region_level = '2') T0 where T.region_id = T0.code group by T.region_id ) t ");
		
		map.put("Sql", selectBufferS.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("saleRegion", (List<Map<String, Object>>)resultModel.getData());// 销地
		// 查询销地价格
		StringBuffer selectBufferSP = new StringBuffer();
		selectBufferSP.append(" select T.region_id,T0.name region_name,T.times times,T.value2 from (select substr(B.start_time,1,10) times, ");
		selectBufferSP.append(" CONCAT(substr(B.region_id,1,2),'0000') region_id,round(avg(A.average_price_unit),2) value2 from da_market_price A ");
		selectBufferSP.append(" INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
		selectBufferSP.append(" and A.price_type_code = '2' and B.area_latitude_type_code = '3' and substr(B.start_time,1,10) in ("+queryDays+") ");
		selectBufferSP.append(" group by substr(B.start_time,1,10),substr(B.region_id,1,2) order by B.add_time desc) T,(select code,name from gp_region ");
		selectBufferSP.append(" where code like '%0000' and region_level = '2') T0 where T.region_id = T0.code group by substr(T.times,1,10),T.region_id ");

		map.put("Sql", selectBufferSP.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("salePrice", (List<Map<String, Object>>)resultModel.getData());// 销地价格
		
		List<Object> dataList = new ArrayList<Object>();
		List<Object> Datalist = new ArrayList<Object>();
		Map<String, Object> listMap = new HashMap<String, Object>();
		Map<String, Object> DataMap = new HashMap<String, Object>();
		
		DataMap.put("time",daysArr);
		for (Map<String, Object> map0 : maps.get("saleRegion") ) {
			listMap = new HashMap<String, Object>();
			String saleRegionId = map0.get("region_id").toString();
			String saleRegionName = map0.get("region_name").toString();
			listMap.put("RegionName",saleRegionName);

			dataList = new ArrayList<Object>();
			for(int i = 0;i < dayList.size(); i++){
				String time = daysArr[i];
				double pprice = 0;
				double sprice = 0;
				if(!maps.get("prodPrice").isEmpty()){
					for (Map<String, Object> map1 : maps.get("prodPrice") ) {
						String ptime = map1.get("times").toString();
						if(ptime.equals(time)){
							pprice = Double.valueOf(map1.get("value1").toString());
							
							if(!maps.get("salePrice").isEmpty()){
								for (Map<String, Object> map2 : maps.get("salePrice") ) {
									String stime = map2.get("times").toString();
									String sregionId = map2.get("region_id").toString();
									if(stime.equals(time)&&sregionId.equals(saleRegionId)){
										if(!(map2.get("value2")==null)){
											sprice = Double.valueOf(map2.get("value2").toString());
										}
									}
									}
								double gap = sprice - pprice;
								dataList.add(gap);
							}
							double gap = 0;
							dataList.add(gap);
						}
					}
				}else{
					if(!maps.get("salePrice").isEmpty()){
						for (Map<String, Object> map2 : maps.get("salePrice") ) {
							String stime = map2.get("times").toString();
							String sregionId = map2.get("region_id").toString();
							if(stime.equals(time)&&sregionId.equals(saleRegionId)){
								if(!(map2.get("value2")==null)){
									sprice = Double.valueOf(map2.get("value2").toString());
								}
							}
							}
						double gap = sprice - pprice;
						dataList.add(gap);
					}else{
						double gap = 0;
						dataList.add(gap);
					}
				}
			}
			listMap.put("data",dataList);
			Datalist.add(listMap);
		}

		DataMap.put("data",Datalist);
		resultModel.setData(DataMap);
		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "价格监测-各环节价差（华坪 7-电商、2-批发、1-田头价三个环节价差）")
	@RequestMapping(value = "/getPriceEachLinkGap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceEachLinkGap() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// Map<String, Object> maps = new HashMap<String, Object>();

		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();

		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
		StringBuffer selectBuffer = new StringBuffer();
		// 各环节价差1-田头价格
		selectBuffer.append(" select t0.year,'1' price_type_code,'田头价' price_type_text,case when t1.average_price_unit is null then '-' else t1.average_price_unit end average_price_unit ");
		selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 left join (select substr(T.start_time,1,4) time1,");
		selectBuffer.append(" T.price_type_code,T.price_type_text,avg(T.average_price_unit) average_price_unit from (select B.start_time,A.average_price_unit,A.price_type_code,A.price_type_text ");
		selectBuffer.append(" from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = '1' and A.crop_type_code = '1' ");
		selectBuffer.append(" and A.price_type_code = '1' and B.area_latitude_type_code = '1' and B.region_id = '031000000'  ");
		selectBuffer.append(" order by B.add_time desc) T group by substr(T.start_time,1,4),T.price_type_code,T.price_type_text) t1 on t0.year = t1.time1 ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("shougou", (List<Map<String, Object>>) resultModel.getData());// 田头价格

		// 各环节价差2-批发价格
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select t0.year,'2' price_type_code,'批发价' price_type_text,case when t1.average_price_unit is null then '-' else t1.average_price_unit end average_price_unit ");
		selectBuffer.append(" from (select * from past_5_year_view where year <> (select max(year) from past_5_year_view)) t0 left join (select substr(T.start_time,1,4) time1,");
		selectBuffer.append(" T.price_type_code,T.price_type_text,avg(T.average_price_unit) average_price_unit from (select B.start_time,A.average_price_unit,A.price_type_code,A.price_type_text ");
		selectBuffer.append(" from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE B.data_time_type_code = '5' and A.crop_type_code = '1' ");
		selectBuffer.append(" and A.price_type_code = '2' order by B.add_time desc) T group by substr(T.start_time,1,4),T.price_type_code,T.price_type_text) t1 on t0.year = t1.time1 ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("pifa", (List<Map<String, Object>>) resultModel.getData());// 批发价格

		// 各环节价差7-电商价格
		selectBuffer = new StringBuffer();
		selectBuffer.append(" SELECT t0. YEAR,'7' price_type_code,'电商价' price_type_text,CASE WHEN t1.sale_price_unit IS NULL THEN ");
		selectBuffer.append(" '-' ELSE t1.sale_price_unit END average_price_unit FROM (SELECT * FROM past_5_year_view WHERE YEAR <> ( ");
		selectBuffer.append(" SELECT max(YEAR) FROM past_5_year_view)) t0 LEFT JOIN (SELECT substr(T.start_time, 1, 4) time1, ");
		selectBuffer.append(" avg(T.sale_price_unit) sale_price_unit FROM (SELECT B.start_time,A.sale_price_unit FROM mf_sale_ecommerce_craw A INNER JOIN ");
		selectBuffer.append(" da_common_field B ON A.common_field_id = B.id WHERE A.crop_type_code = '1' ");
		selectBuffer.append(" ORDER BY B.add_time DESC ) T GROUP BY substr(T.start_time, 1, 4)) t1 ON t0. YEAR = t1.time1 ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("lingshou", (List<Map<String, Object>>) resultModel.getData());// 零售价格

		Map<String, List<String>> xMap = new HashMap<String, List<String>>();
		List<String> numList = new ArrayList<String>();
		for (Map<String, Object> map1 : maps.get("shougou")) {
			numList.add(map1.get("year").toString());
		}
		xMap.put("time", numList);

		DecimalFormat format = new DecimalFormat("0.00");
		Map<String, List<String>> shougouMap = new HashMap<String, List<String>>();
		List<String> numList1 = new ArrayList<String>();
		for (Map<String, Object> map1 : maps.get("shougou")) {
			String average_price_unit = map1.get("average_price_unit").toString();
			String perPrice = "-";
			if(!average_price_unit.equals("-")){
				perPrice = format.format(new BigDecimal(average_price_unit));
			}
			numList1.add(perPrice);
		}
		shougouMap.put("shougou", numList1);

		Map<String, List<String>> pifaMap = new HashMap<String, List<String>>();
		List<String> numList2 = new ArrayList<String>();
		for (Map<String, Object> map1 : maps.get("pifa")) {
			String average_price_unit = map1.get("average_price_unit").toString();
			String perPrice = "-";
			if(!average_price_unit.equals("-")){
				perPrice = format.format(new BigDecimal(average_price_unit));
			}
			numList2.add(perPrice);
		}
		pifaMap.put("pifa", numList2);

		Map<String, List<String>> lingshouMap = new HashMap<String, List<String>>();
		List<String> numList3 = new ArrayList<String>();
		for (Map<String, Object> map1 : maps.get("lingshou")) {
			String average_price_unit = map1.get("average_price_unit").toString();
			String perPrice = "-";
			if(!average_price_unit.equals("-")){
				perPrice = format.format(new BigDecimal(average_price_unit));
			}
			numList3.add(perPrice);
		}
		lingshouMap.put("lingshou", numList3);

		List<Object> rt = new ArrayList<Object>();
		rt.add(xMap);
		rt.add(shougouMap);
		rt.add(pifaMap);
		rt.add(lingshouMap);
		resultModel.setData(rt);

		// list.add(maps);
		// resultModel.setData(list);

		return resultModel;
	}

	@ApiOperation(value = "查询日均、月均、年均田头价和零售价", notes = "查询日均、月均、年均田头价和零售价")
	@RequestMapping(value = "/getMarketPriceDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMarketPriceDetails() {
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
		
		String dayTime = "";
		String monthTime = "";
		String yearTime = "";
		
		//获取查询日均、月均、年均数据的时间列表
		Map<String, String> dayMap = new HashMap<String, String>();
		dayMap.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
		dayMap.put("hasCurrent", "false");
		dayMap.put("pastNum", "1");
		List<String> dayList = TimesView.getTimesView(dayMap);
		dayTime = dayList.get(0);
		
		Map<String, String> monthMap = new HashMap<String, String>();
		monthMap.put("viewName", "month");// 视图名，年year，月month，日date，小时hour（默认为年）
		monthMap.put("hasCurrent", "true");
		monthMap.put("pastNum", "1");
		List<String> monthList = TimesView.getTimesView(monthMap);
		monthTime = monthList.get(1);
		
		Map<String, String> yearMap = new HashMap<String, String>();
		yearMap.put("viewName", "year");// 视图名，年year，月month，日date，小时hour（默认为年）
		yearMap.put("hasCurrent", "true");
		yearMap.put("pastNum", "1");
		List<String> yearList = TimesView.getTimesView(yearMap);
		yearTime = yearList.get(1);
		
		//日均、月均、年均批发价
		ResultModel dispatchModel_day = getDispatchPriceInfo(regionId, dayTime, DAY_AVG_PRICE);
		ResultModel dispatchModel_month = getDispatchPriceInfo(regionId, monthTime, MON_AVG_PRICE);
		ResultModel dispatchModel_year = getDispatchPriceInfo(regionId, yearTime, YEAR_AVG_PRICE);
		
		// 日均、月均、年均电商价
		ResultModel ecommerceModel_day = getEcommercePriceInfo(regionId, dayTime, DAY_AVG_PRICE);
		ResultModel ecommerceModel_month = getEcommercePriceInfo(regionId, monthTime, MON_AVG_PRICE);
		ResultModel ecommerceModel_year = getEcommercePriceInfo(regionId, yearTime, YEAR_AVG_PRICE);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		List<Map<String, Object>> ecommerceList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dispatchList = new ArrayList<Map<String, Object>>();

		Map<String, Object> dispatchResultMap = new HashMap<String, Object>();
		Map<String, Object> ecommerceResultMap = new HashMap<String, Object>();

		ecommerceList.add(getMapFromModel(ecommerceModel_day, DAY_AVG_PRICE , "avgPrice"));
		ecommerceList.add(getMapFromModel(ecommerceModel_month, MON_AVG_PRICE , "avgPrice"));
		ecommerceList.add(getMapFromModel(ecommerceModel_year, YEAR_AVG_PRICE , "avgPrice"));
		
		dispatchList.add(getMapFromModel(dispatchModel_day, DAY_AVG_PRICE , "avgPrice"));
		dispatchList.add(getMapFromModel(dispatchModel_month, MON_AVG_PRICE , "avgPrice"));
		dispatchList.add(getMapFromModel(dispatchModel_year, YEAR_AVG_PRICE , "avgPrice"));

		ecommerceResultMap.put("retail", ecommerceList);
		resultList.add(ecommerceResultMap);

		dispatchResultMap.put("field", dispatchList);
		resultList.add(dispatchResultMap);
		
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private ResultModel getDispatchPriceInfo(String regionId, String timeType, String priceAmbit) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    SUBSTR(B.start_time, 1, 7) TIME,
		     CASE WHEN AVG(A.per_price_unit) IS NULL THEN 0 ELSE AVG(A.per_price_unit) END AS avgPrice 
		FROM
		    da_market_price A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND SUBSTR(B.start_time, 1, 7) = '2018-07' 
		    AND A.price_type_code = '2' */
	    
		selectBuffer.append(" SELECT 																									");
		if(DAY_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 10) time,																		");
		}else if(MON_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 7) time,																		");
		}else if(YEAR_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 4) time,																		");
		}
		selectBuffer.append(" CASE WHEN AVG(A.per_price_unit) IS NULL THEN 0 ELSE AVG(A.per_price_unit) END AS avgPrice 				");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_market_price A,																						");
		selectBuffer.append(" da_common_field B 																						");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.price_type_code = 2																					");
		if(DAY_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =																		");
		}else if(MON_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 7) =																		");
		}else if(YEAR_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																		");
		}
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(timeType)) {
			selectBuffer.append(timeType);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	private Map<String, Object> getMapFromModel(ResultModel resultModel,String priceFlag) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> objList = null;
		Map<String, Object> objMap = new HashMap<String, Object>();
		if (obj != null)
			objList = (List<Map<String, Object>>) obj;
		if (objList != null && objList.size() >= 1) {
			objMap = objList.get(0);
			if (objMap.containsKey(priceFlag)) {
				BigDecimal price = (BigDecimal) objMap.get(priceFlag);
				if (price.compareTo(BigDecimal.ZERO) != 0) {
					objMap.put(priceFlag, price);
				} else {
					objMap.put(priceFlag, new BigDecimal("0")+"");
				}
			}
		} else {
			objMap.put(priceFlag, new BigDecimal("0")+"");
		}
		return objMap;
	}
	
	private Map<String, Object> getMapFromModel(ResultModel resultModel,String timeType, String priceFlag) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> objList = null;
		Map<String, Object> objMap = new HashMap<String, Object>();
		if (obj != null)
			objList = (List<Map<String, Object>>) obj;
		if (objList != null && objList.size() >= 1) {
			objMap = objList.get(0);
			if (objMap.containsKey(priceFlag)) {
				BigDecimal price = (BigDecimal) objMap.get(priceFlag);
				if (price.compareTo(BigDecimal.ZERO) != 0) {
					objMap.put(timeType+priceFlag, price);
				} else {
					objMap.put(timeType+priceFlag, new BigDecimal("0")+"");
				}
			}
		} else {
			objMap.put(timeType+priceFlag, new BigDecimal("0")+"");
		}
		return objMap;
	}

	public ResultModel getEcommercePriceInfo(String regionId, String timeType, String priceAmbit) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    SUBSTR(A.start_time, 1, 10) TIME,
		    CASE
		        WHEN AVG(B.sale_price_unit) IS NULL 
		        THEN 0 
		        ELSE AVG(B.sale_price_unit) 
		    END AS salePriceUnit 
		FROM
		    da_common_field A,
		    mf_sale_ecommerce_craw B 
		WHERE A.id = B.common_field_id 
		    AND B.crop_type_code = '1' 
		    AND SUBSTR(A.start_time, 1, 10) = '2018-07-26'*/
		
		selectBuffer.append(" SELECT																									");
		if(DAY_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" SUBSTR(A.start_time, 1, 10) time,																		");
		}else if(MON_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" SUBSTR(A.start_time, 1, 7) time,																		");
		}else if(YEAR_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" SUBSTR(A.start_time, 1, 4) time,																		");
		}
		selectBuffer.append(" CASE WHEN AVG(B.sale_price_unit) IS NULL THEN 0 ELSE AVG(B.sale_price_unit) END AS avgPrice				");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_common_field A,																						");
		selectBuffer.append(" mf_sale_ecommerce_craw B 																					");
		selectBuffer.append(" WHERE A.id = B.common_field_id 																			");
		selectBuffer.append(" AND B.crop_type_code = '1'																				");
		if(DAY_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" AND SUBSTR(A.start_time, 1, 10) =																		");
		}else if(MON_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" AND SUBSTR(A.start_time, 1, 7) =																		");
		}else if(YEAR_AVG_PRICE.equals(priceAmbit)) {
			selectBuffer.append(" AND SUBSTR(A.start_time, 1, 4) =																		");
		}
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(timeType)) {
			selectBuffer.append(timeType);
		}
		selectBuffer.append("'");
		
		/*selectBuffer.append(" SELECT																											");
		selectBuffer.append(" CASE WHEN AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS								");
		selectBuffer.append(priceAmbit);
		selectBuffer.append("avgPrice																											");
		selectBuffer.append(" FROM																												");
		selectBuffer.append(" da_market_price A, 																								");
		selectBuffer.append(" da_common_field B 																								");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																					");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																				");
		selectBuffer.append(" AND B.audit_status_code = 1 																						");
		selectBuffer.append(" AND B.region_id =																									");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND (B.`start_time`>DATE_SUB(NOW(),INTERVAL 1 																	");
		selectBuffer.append(priceAmbit);
		selectBuffer.append(" ) AND B.`start_time`<NOW()) = 1																					");
		selectBuffer.append(" AND A.price_type_code =																							");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(priceType)) {
			selectBuffer.append(priceType);
		}
		selectBuffer.append("'");
*/
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	@ApiOperation(value = "查询最近一周的批发价和电商价", notes = "查询最近一周的批发价和电商价")
	@RequestMapping(value = "/getWeekPriceDetails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWeekPriceDetails() {
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
		map.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "7");
		List<String> timesList = TimesView.getTimesView(map);
		
		List<Map<String, Object>> dispatchList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> ecommerceList = new ArrayList<Map<String, Object>>();

		for (String time : timesList) {
			ResultModel dispatchPriceModel = getRecentWeekDispatchPriceInfo(time, regionId, DISPATCH_PRICE, "0");
			Map<String, Object> perDispatchPriceMap = getPriceMapFromModel(time, dispatchPriceModel);
			dispatchList.add(perDispatchPriceMap);
		}

		for (String time : timesList) {
			ResultModel ecommercePriceModel = getRecentWeekEcommercePriceInfo(time, regionId, ECOMMERCE_PRICE, "0");
			Map<String, Object> perecommercePriceMap = getPriceMapFromModel(time, ecommercePriceModel);
			ecommerceList.add(perecommercePriceMap);
		}

		Map<String, Object> dispatchResultMap = new HashMap<String, Object>();
		Map<String, Object> ecommerceResultMap = new HashMap<String, Object>();

		dispatchResultMap.put("dispatchPrice", dispatchList);
		ecommerceResultMap.put("ecommercePrice", ecommerceList);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		resultList.add(dispatchResultMap);
		resultList.add(ecommerceResultMap);

		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private ResultModel getRecentWeekEcommercePriceInfo(String time, String regionId, String ecommercePrice,
			String string) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    SUBSTR(A.start_time, 1, 10) TIME,
		    CASE
		        WHEN AVG(B.sale_price_unit) IS NULL 
		        THEN 0 
		        ELSE AVG(B.sale_price_unit) 
		    END AS salePriceUnit 
		FROM
		    da_common_field A,
		    mf_sale_ecommerce_craw B 
		WHERE A.id = B.common_field_id 
		    AND B.crop_type_code = '1' 
		    AND SUBSTR(A.start_time, 1, 10) = '2018-07-26'*/
		
		selectBuffer.append(" SELECT																									");
		selectBuffer.append(" SUBSTR(A.start_time, 1, 10) time,																			");
		selectBuffer.append(" CASE WHEN AVG(B.sale_price_unit) IS NULL THEN 0 ELSE AVG(B.sale_price_unit) END AS perPrice				");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_common_field A,																						");
		selectBuffer.append(" mf_sale_ecommerce_craw B 																					");
		selectBuffer.append(" WHERE A.id = B.common_field_id 																			");
		selectBuffer.append(" AND B.crop_type_code = '1'																				");
		selectBuffer.append(" AND SUBSTR(A.start_time, 1, 10) =																			");
		
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	private Map<String, Object> getPriceMapFromModel(String time, ResultModel resultModel) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> list = null;
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (obj != null)
			list = (List<Map<String, Object>>) obj;
		if (list != null && list.size() >= 1) {
			map = list.get(0);
			BigDecimal perPrice = new BigDecimal("0");
			if(map.containsKey("time")) {
				String times = (String) map.get("time");
				resultMap.put("times", time.substring(5, time.length()));
			}
			if(map.containsKey("perPrice")) {
				perPrice = (BigDecimal) map.get("perPrice");
				perPrice = MathUtil.decimalFormat(perPrice);
				resultMap.put("perPrice", perPrice+"");
			}
		} else {
			resultMap.put("times", time.substring(5, time.length()));
			resultMap.put("perPrice", new BigDecimal(0) + "");
		}
		return resultMap;
	}

	// 获取最近一周的价格（田头价、批发价、零售价）
	private ResultModel getRecentWeekMarkPriceInfo(String time, String regionId, String priceType, String strainsCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT t0.time, t0.startTime, t0.priceTypeCode, t0.priceTypeText,
		 * CASE WHEN t0.perPrice IS NULL THEN 0 ELSE t0.perPrice END AS perPrice
		 * FROM (SELECT SUBSTR(B.start_time, 1, 10) TIME, B.start_time AS
		 * startTime, A.price_type_code AS priceTypeCode, A.price_type_text AS
		 * priceTypeText, A.per_price_unit AS perPrice FROM da_market_price A,
		 * da_common_field B WHERE A.common_field_id = B.id AND A.crop_type_code
		 * = 1 AND A.strains_code = 1 AND A.price_type_code = 2 AND
		 * B.area_latitude_type_code = 4 AND B.audit_status_code = 1 AND
		 * B.region_id = '530723' AND SUBSTR(B.start_time, 1, 10) = 2018-06-14
		 * ORDER BY startTime DESC) t0 GROUP BY TIME
		 */
		
		/*SELECT 
		    A.strains_text AS strainsText,
		    A.price_type_code AS priceTypeCode,
		    A.price_type_text AS priceTypeText,
		    CASE
		        WHEN A.per_price_unit IS NULL 
		        THEN 0 
		        ELSE A.per_price_unit 
		    END AS perPrice,
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 10) AS TIME 
		FROM
		    da_market_price A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.price_type_code = '2' 
		    AND B.data_time_type_code = 5 
		    AND B.audit_status_code = 1 
		    AND A.strains_code = '5' 
		    AND SUBSTR(B.start_time, 1, 10) = '2018-08-13' 
		    ORDER BY startTime DESC*/

		selectBuffer.append(" SELECT 																						");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 10) time,																");
		selectBuffer.append(" B.start_time AS startTime,																	");
		selectBuffer.append(" A.strains_text AS strainsText,																");
		selectBuffer.append(" A.price_type_code AS priceTypeCode,															");
		selectBuffer.append(" A.price_type_text AS priceTypeText,															");
		selectBuffer.append(" CASE WHEN A.per_price_unit IS NULL THEN 0 ELSE A.per_price_unit END AS perPrice 				");
		selectBuffer.append(" FROM																							");
		selectBuffer.append(" da_market_price A,																			");
		selectBuffer.append(" da_common_field B 																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																");
		selectBuffer.append(" AND A.price_type_code =																		");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(priceType)) {
			selectBuffer.append(priceType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.data_time_type_code = 5 																");
		selectBuffer.append(" AND B.audit_status_code = 1 																	");
		selectBuffer.append(" AND A.strains_code =																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(strainsCode)) {
			selectBuffer.append(strainsCode);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =																");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC																		");
		/*selectBuffer.append(" AND B.region_id =																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY time																					");
		 */
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}
	
	// 获取最近一周批发价
	private ResultModel getRecentWeekDispatchPriceInfo(String time, String regionId, String priceType, String strainsCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    SUBSTR(B.start_time, 1, 10) TIME,
		    CASE WHEN AVG(A.per_price_unit) IS NULL THEN 0 ELSE AVG(A.per_price_unit) END AS perPrice 
		FROM
		    da_market_price A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND SUBSTR(B.start_time, 1, 10) = '2018-07-25' 
		    AND A.price_type_code = '2'*/
	    
	    selectBuffer.append(" SELECT 																									");
	    selectBuffer.append(" SUBSTR(B.start_time, 1, 10) time,																			");
	    selectBuffer.append(" CASE WHEN AVG(A.per_price_unit) IS NULL THEN 0 ELSE AVG(A.per_price_unit) END AS perPrice 				");
	    selectBuffer.append(" FROM																										");
	    selectBuffer.append(" da_market_price A,																						");
	    selectBuffer.append(" da_common_field B 																						");
	    selectBuffer.append(" WHERE A.common_field_id = B.id																			");
	    selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =																			");
	    selectBuffer.append("'");
	    if(StringUtils.isNotBlank(time)) {
	    	selectBuffer.append(time);
	    }
	    selectBuffer.append("'");
	    selectBuffer.append(" AND A.price_type_code = 2																					");

		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}
		

	private List<Map<String, Object>> getListFromModel(ResultModel resultModel) {
		Object obj = resultModel.getData();
		List<Map<String, Object>> objList = null;
		if (obj != null)
			objList = (List<Map<String, Object>>) obj;
		return objList;
	}

	public ResultModel getWeekMarketPriceInfo(String regionId, String priceType) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*
		 * SELECT t0.times, CASE WHEN t1.priceTypeCode IS NULL THEN 0 ELSE
		 * t1.priceTypeCode END AS priceTypeCode, CASE WHEN t1.priceTypeText IS
		 * NULL THEN '--' ELSE t1.priceTypeText END AS priceTypeText, CASE WHEN
		 * t1.perPrice IS NULL THEN 0 ELSE t1.perPrice END AS perPrice FROM
		 * (SELECT * FROM past_1_week_view WHERE times <> (SELECT MAX(times)
		 * FROM past_1_week_view)) t0 LEFT JOIN
		 * 
		 * (SELECT SUBSTR(B.start_time,1,10) times, B.start_time AS startTime,
		 * A.price_type_code AS priceTypeCode, A.price_type_text AS
		 * priceTypeText, A.per_price_unit AS perPrice FROM da_market_price
		 * A,da_common_field B WHERE A.common_field_id = B.id AND
		 * A.crop_type_code = 1 AND B.area_latitude_type_code = 4 AND
		 * B.region_id = '530723' AND A.price_type_code=3 GROUP BY times ORDER
		 * BY startTime DESC) t1 ON t0.times = t1.times
		 */

		selectBuffer.append(" SELECT																											");
		selectBuffer.append(" t0.times,																											");
		selectBuffer.append(" CASE WHEN t1.priceTypeCode IS NULL THEN 0 ELSE t1.priceTypeCode END AS priceTypeCode,								");
		selectBuffer.append(" CASE WHEN t1.priceTypeText IS NULL THEN '--' ELSE t1.priceTypeText END AS priceTypeText,							");
		selectBuffer.append(" CASE WHEN t1.perPrice IS NULL THEN 0 ELSE t1.perPrice END AS perPrice												");
		selectBuffer.append(" FROM																												");
		selectBuffer.append(" (SELECT * FROM past_1_week_view WHERE times <> (SELECT MAX(times) FROM past_1_week_view)) t0						");
		selectBuffer.append(" LEFT JOIN 																										");
		selectBuffer.append(" (SELECT SUBSTR(B.start_time,1,10) times,																			");
		selectBuffer.append(" B.start_time AS startTime,																						");
		selectBuffer.append(" A.price_type_code AS priceTypeCode,																				");
		selectBuffer.append(" A.price_type_text AS priceTypeText,																				");
		selectBuffer.append(" A.average_price_unit AS perPrice																						");
		selectBuffer.append(" FROM da_market_price A,da_common_field B 																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id																					");
		selectBuffer.append(" AND A.crop_type_code = 1																							");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 																				");
		selectBuffer.append(" AND B.region_id =																									");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.price_type_code=																							");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(priceType)) {
			selectBuffer.append(priceType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY times ORDER BY startTime DESC) t1 ON t0.times = t1.times													");

		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	// 获取最近有数据的日期（用于市场价格-批发价格-地图、省排名）
		private String getLatestDay() {
			String LatestDay = "";
			ResultModel resultModel = new ResultModel();
			Map<String, Object> map = new HashMap<String, Object>();
			Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
			StringBuffer selectBuffer = new StringBuffer();

			selectBuffer.append(" select max(substr(B.start_time,1,10)) LatestDay from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
			selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = '3' and A.price_type_code = '2' ");

			map.put("Sql", selectBuffer.toString());
			resultModel = daMarketPriceUntBll.getListBySQL(map);
			maps.put("avg", (List<Map<String, Object>>) resultModel.getData());// 最新日期
			for (Map<String, Object> map1 : maps.get("avg")) {
				LatestDay = map1.get("LatestDay").toString();
			}
			return LatestDay;
		}
	
	@ApiOperation(value = "查询", notes = "价格监测-批发价格-排名")
	@RequestMapping(value = "/getWholeSalePriceRank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWholeSalePriceRank() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String queryTime = "";//查询日期  2018-09
		
		 if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);

				if (jsonObject.containsKey("entityRelated")) {
					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
					if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
						queryTime = entityRelatedObject.getString("queryTime").toString();
				}
				
			}

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer = new StringBuffer();
		Map<String, List<Object>> xMap = new HashMap<String, List<Object>>();
		String LatestDay =getLatestDay();
		
		// 批发价格-平均价格-排名
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select t.region_id,t.name region_name,round(t.average_price_unit,2) average_price_unit from (select substr(T.start_time,1,10) time, ");
		selectBuffer.append(" T.region_id,T0.name,T.average_price_unit from ( select substr(B.start_time,1,7) start_time,CONCAT(substr(B.region_id,1,2),'0000') ");
		selectBuffer.append(" region_id,avg(A.average_price_unit) average_price_unit from da_market_price A  INNER JOIN da_common_field B ON A.common_field_id = B.id ");
		selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = '3' and A.price_type_code = '2' ");
		selectBuffer.append(" group by substr(B.start_time,1,7),substr(B.region_id,1,2) order by substr(B.start_time,1,7) desc) T,(select code,name from ");
		selectBuffer.append(" gp_region where code like '%0000' and region_level = '2') T0 where T.region_id = T0.code  and T.start_time = '"+queryTime+"' ");
		selectBuffer.append(" group by substr(T.region_id,1,2) order by T.average_price_unit desc limit 0,5) t ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("avg", (List<Map<String, Object>>) resultModel.getData());// 平均值

		List<Object> avgList = new ArrayList<Object>();
		Map<String, Object> avgMap;
		for (Map<String, Object> map1 : maps.get("avg")) {
			avgMap = new HashMap<String, Object>();
			avgMap.put("name", map1.get("region_name").toString());
			avgMap.put("val", map1.get("average_price_unit"));
			avgList.add(avgMap);
		}

		// 批发价格-最高价格-排名
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select t.region_id,t.name region_name,round(t.top_price_unit,2) top_price_unit from (select substr(T.start_time,1,10) time, ");
		selectBuffer.append(" T.region_id,T0.name,T.top_price_unit from ( select substr(B.start_time,1,7) start_time,CONCAT(substr(B.region_id,1,2),'0000') ");
		selectBuffer.append(" region_id,max(A.top_price_unit) top_price_unit from da_market_price A  INNER JOIN da_common_field B ON A.common_field_id = B.id ");
		selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = '3' and A.price_type_code = '2' ");
		selectBuffer.append(" group by substr(B.start_time,1,7),substr(B.region_id,1,2) order by substr(B.start_time,1,7) desc) T,(select code,name from ");
		selectBuffer.append(" gp_region where code like '%0000' and region_level = '2') T0 where T.region_id = T0.code  and T.start_time = '"+queryTime+"' ");
		selectBuffer.append(" group by substr(T.region_id,1,2) order by T.top_price_unit desc limit 0,5) t ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("top", (List<Map<String, Object>>) resultModel.getData());// 平均值

		List<Object> topList = new ArrayList<Object>();
		Map<String, Object> topMap;
		for (Map<String, Object> map1 : maps.get("top")) {
			topMap = new HashMap<String, Object>();
			topMap.put("name", map1.get("region_name").toString());
			topMap.put("val", map1.get("top_price_unit"));
			topList.add(topMap);
		}
		xMap.put("ave", avgList);
		xMap.put("top", topList);

		resultModel.setData(xMap);

		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "价格监测-批发价格-地图")
	@RequestMapping(value = "/getWholeSalePriceMap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWholeSalePriceMap() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String queryTime = "";//查询日期  2018-09
		
		 if (!StringUtils.isBlank(jsonData)) {
				JSONObject jsonObject = JSONObject.fromObject(jsonData);

				if (jsonObject.containsKey("entityRelated")) {
					JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
					if (entityRelatedObject.containsKey("queryTime") && StringUtils.isNotBlank(entityRelatedObject.getString("queryTime")))
						queryTime = entityRelatedObject.getString("queryTime").toString();
				}
				
			}

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer = new StringBuffer();
		String LatestDay =getLatestDay();
		// 批发价格-地图
//		selectBuffer = new StringBuffer();
//		selectBuffer.append(" select t.* from (select T.region_id,T0.name region_name,T.average_price_unit,T.bottom_price_unit,T.top_price_unit from ( ");
//		selectBuffer.append(" select substr(B.start_time,1,10) start_time,CONCAT(substr(B.region_id,1,2),'0000') region_id, ");
//		selectBuffer.append(" round(avg(A.average_price_unit),2) average_price_unit,round(min(A.bottom_price_unit),2) bottom_price_unit, ");
//		selectBuffer.append(" round(max(A.top_price_unit),2) top_price_unit from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
//		selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = '3' and A.price_type_code = '2' ");
//		selectBuffer.append(" group by substr(B.start_time,1,10),substr(B.region_id,1,2) order by substr(B.start_time,1,10) desc) T,(select code,name from ");
//		selectBuffer.append(" gp_region where code like '%0000' and region_level = '2') T0 where T.region_id = T0.code  and T.start_time = '"+LatestDay+"' ");
//		selectBuffer.append(" group by substr(T.region_id,1,2) ) t ");
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select t.* from (select T.region_id,T0.name region_name,T.average_price_unit,T.bottom_price_unit,T.top_price_unit from ( ");
		selectBuffer.append(" select substr(B.start_time,1,7) start_time,CONCAT(substr(B.region_id,1,2),'0000') region_id, ");
		selectBuffer.append(" round(avg(A.average_price_unit),2) average_price_unit,round(min(A.bottom_price_unit),2) bottom_price_unit, ");
		selectBuffer.append(" round(max(A.top_price_unit),2) top_price_unit from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
		selectBuffer.append(" WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = '3' and A.price_type_code = '2' ");
		selectBuffer.append(" group by substr(B.start_time,1,10),substr(B.region_id,1,2) order by substr(B.start_time,1,7) desc) T,(select code,name from ");
		selectBuffer.append(" gp_region where code like '%0000' and region_level = '2') T0 where T.region_id = T0.code  and T.start_time = '"+queryTime+"' ");
		selectBuffer.append(" group by substr(T.region_id,1,2) ) t ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		maps.put("data", (List<Map<String, Object>>) resultModel.getData());

		List<Object> dataList = new ArrayList<Object>();
		Map<String, Object> dataMap;
		for (Map<String, Object> map1 : maps.get("data")) {
			dataMap = new HashMap<String, Object>();
			dataMap.put("region_id", map1.get("region_id").toString());
			String area = map1.get("region_name").toString();
			// 省名处理
			String areaName;
			if (area.equals("黑龙江省")) {
				areaName = "黑龙江";
			} else {
				areaName = area.substring(0, 2);
			}
			dataMap.put("region_name", areaName);
//			dataMap.put("region_name", area);
			dataMap.put("average_price_unit", map1.get("average_price_unit"));
			dataMap.put("bottom_price_unit", map1.get("bottom_price_unit"));
			dataMap.put("top_price_unit", map1.get("top_price_unit"));
			dataList.add(dataMap);
		}

		resultModel.setData(dataList);

		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "价格监测-批发价格-价格走势")
	@RequestMapping(value = "/getWholeSalePriceTend", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWholeSalePriceTend() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();

		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();

		// 批发价格-价格走势
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select t.* from (select substr(T1.time,1,10) times,round(T1.average_price_unit,2) price,round((T1.average_price_unit - T2.average_price_unit)/T2.average_price_unit*100,2) chg from (");
		selectBuffer.append(" select T.time,avg(T.average_price_unit) average_price_unit from (select B.start_time time,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ");
		selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
		selectBuffer.append(" and A.price_type_code = '2' order by B.add_time desc) T group by substr(T.time,1,10)) T1, ");
		selectBuffer.append(" (select T.time,avg(T.average_price_unit) average_price_unit from (select B.start_time time,A.average_price_unit from da_market_price A INNER JOIN da_common_field B ");
		selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' ");
		selectBuffer.append(" and A.price_type_code = '2' order by B.add_time desc) T group by substr(T.time,1,10)) T2 ");
		selectBuffer.append(" where substr(DATE_ADD(T2.time,INTERVAL 1 MONTH),1,10) = substr(T1.time,1,10) order by substr(T1.time,1,10) desc limit 0,7) t order by times asc ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "按品种查询最近一周芒果的收购价和电商价走势", notes = "按品种查询最近一周芒果的收购价和电商价走势")
	@RequestMapping(value = "/getWeekPriceDetailsForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWeekPriceDetailsForApp() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionId = "";// 地区id
		String strainsCode = "";// 芒果品种

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("strainsCode") && StringUtils.isNotBlank(entityRelatedObject.getString("strainsCode")))
					strainsCode = entityRelatedObject.getString("strainsCode");
			}
		}

		// 获取时间列表
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "true");
		map.put("pastNum", "6");
		List<String> timesList = TimesView.getTimesView(map);

		List<Map<String, Object>> fieldList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> dispatchList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> retailList = new ArrayList<Map<String, Object>>();

		for (String time : timesList) {
			ResultModel fieldPriceModel = getRecentWeekMarkPriceInfo(time, regionId, DISPATCH_PRICE, strainsCode);
			Map<String, Object> perFieldPriceMap = getPriceMapFromModel(time, fieldPriceModel);
			fieldList.add(perFieldPriceMap);
		}

		for (String time : timesList) {
			ResultModel dispatchPriceModel = getRecentWeekMarkPriceInfo(time, regionId, DISPATCH_PRICE, strainsCode);
			Map<String, Object> perDispatchPriceMap = getPriceMapFromModel(time, dispatchPriceModel);
			dispatchList.add(perDispatchPriceMap);
		}

		for (String time : timesList) {
			ResultModel retailPriceModel = getRecentWeekEcommercePrice(time, regionId,strainsCode);
			Map<String, Object> perRetailPriceMap = getPriceMapFromModel(time, retailPriceModel);
			retailList.add(perRetailPriceMap);
		}

		Map<String, Object> fieldResultMap = new HashMap<String, Object>();
		Map<String, Object> dispatchResultMap = new HashMap<String, Object>();
		Map<String, Object> retailResultMap = new HashMap<String, Object>();

		fieldResultMap.put("fieldPrice", fieldList);
		dispatchResultMap.put("dispatchPrice", dispatchList);
		retailResultMap.put("retailPrice", retailList);

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		resultList.add(fieldResultMap);
		resultList.add(dispatchResultMap);
		resultList.add(retailResultMap);

		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private ResultModel getRecentWeekEcommercePrice(String time, String regionId, String strainsCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    A.strains_text AS strainsText,
		    CASE
		        WHEN AVG(A.sale_price_unit) IS NULL 
		        THEN 0 
		        ELSE AVG(A.sale_price_unit) 
		    END AS perPrice,
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 10) AS TIME 
		FROM
		    mf_sale_ecommerce_craw A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.strains_code = '5' 
		    AND B.audit_status_code = 1
		    AND SUBSTR(B.start_time, 1, 10) = '2018-08-06' */ 
		
		selectBuffer.append(" SELECT 																							");
		selectBuffer.append(" A.strains_text AS strainsText,																	");
		selectBuffer.append(" CASE WHEN AVG(A.sale_price_unit) IS NULL THEN 0 ELSE AVG(A.sale_price_unit) END AS perPrice,		");
		selectBuffer.append(" B.start_time AS startTime,																		");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS time																");
		selectBuffer.append(" FROM																								");
		selectBuffer.append(" mf_sale_ecommerce_craw A,																			");
		selectBuffer.append(" da_common_field B 																				");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																	");
		selectBuffer.append(" AND A.crop_type_code = 1																			");
		selectBuffer.append(" AND A.strains_code =																				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(strainsCode)) {
			selectBuffer.append(strainsCode);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.audit_status_code = 1 																		");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) = 																");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	@ApiOperation(value = "查询各个品种芒果的最新收购价和电商价", notes = "查询各个品种芒果的最新收购价和电商价")
	@RequestMapping(value = "/getLatestPriceForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getLatestPriceForApp() {
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

		// 获取芒果品种<code,text>列表
		Map<String, Object> mangoBreedMap = getDicMapByTypeId(CustomSymbolic.DI_CROP_STRAINS);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		Map<String, Object> perResultMap = null;
		Map<String, Object> perBreedMap = null;
		Iterator iter = mangoBreedMap.entrySet().iterator();
		while (iter.hasNext()) {
			perResultMap = new HashMap<String, Object>();
			Map.Entry entry = (Map.Entry) iter.next();
			String code = (String) entry.getKey();
			String text = (String) entry.getValue();

			ResultModel perBreedFieldModel = getPerBreedPriceModel(regionId, code, DISPATCH_PRICE);
//			ResultModel perRetailBreedModel = getPerBreedPriceModel(regionId, code, RETAIL_PRICE);
			ResultModel perRetailBreedModel = getPerBreedEcommercePriceModel(regionId, code);
			if (!"0".equals(code)) {
				perResultMap.put("code", code);
				perResultMap.put("text", text);
				perBreedMap = getMapFromModel(perBreedFieldModel, "perPrice");
				String fieldPrice = perBreedMap.get("perPrice") + "";
				perResultMap.put("fieldPrice", fieldPrice);
				Object fieldObj = perBreedFieldModel.getData();
				if (fieldObj != null) {
					List<Map<String, Object>> perFieldList = (List<Map<String, Object>>) fieldObj;
					if (perFieldList.size() >= 1) {
						perBreedMap = perFieldList.get(0);
						if (perBreedMap.containsKey("releaseTime")) {
							String releaseTime = (String) perBreedMap.get("releaseTime");
							perResultMap.put("fieldReleaseTime", releaseTime);
						}
					} else {
						perResultMap.put("fieldReleaseTime", "--");
					}
				}
				perBreedMap = getMapFromModel(perRetailBreedModel, "perPrice");
				String retailPrice = perBreedMap.get("perPrice") + "";
				perResultMap.put("retailPrice", retailPrice);
				Object retailObj = perRetailBreedModel.getData();
				if (retailObj != null) {
					List<Map<String, Object>> perRetailList = (List<Map<String, Object>>) retailObj;
					if (perRetailList.size() >= 1) {
						perBreedMap = perRetailList.get(0);
						if (perBreedMap.containsKey("releaseTime")) {
							String releaseTime = (String) perBreedMap.get("releaseTime");
							perResultMap.put("retailReleaseTime", releaseTime);
						}
					} else {
						perResultMap.put("retailReleaseTime", "--");
					}
				}
				if(new BigDecimal(fieldPrice).compareTo(BigDecimal.ZERO) != 0 ||
						new BigDecimal(retailPrice).compareTo(BigDecimal.ZERO) != 0) {
					resultList.add(perResultMap);
				}
			}
		}
		resultModel.setData(resultList);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private ResultModel getPerBreedEcommercePriceModel(String regionId, String strainsCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    A.strains_text AS strainsText,
		    A.sale_price_unit AS perPrice,
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 10) AS releaseTime 
		FROM
		    mf_sale_ecommerce_craw A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.strains_code = '5' 
		    AND B.audit_status_code = 1 
		ORDER BY startTime DESC*/
		
		selectBuffer.append(" SELECT 																		");
		selectBuffer.append(" A.strains_text AS strainsText,												");
		selectBuffer.append(" A.sale_price_unit AS perPrice,												");
		selectBuffer.append(" B.start_time AS startTime,													");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS releaseTime									");
		selectBuffer.append(" FROM																			");
		selectBuffer.append(" mf_sale_ecommerce_craw A,														");
		selectBuffer.append(" da_common_field B 															");
		selectBuffer.append(" WHERE A.common_field_id = B.id 												");
		selectBuffer.append(" AND A.crop_type_code = 1														");
		selectBuffer.append(" AND A.strains_code =															");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(strainsCode)) {
			selectBuffer.append(strainsCode);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.audit_status_code = 1 													");
		selectBuffer.append(" ORDER BY startTime DESC														");

		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	private ResultModel getPerBreedPriceModel(String regionId, String strainsCode, String priceType) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    A.strains_text AS strainsText,
		    A.price_type_text AS priceTypeText,
		    CASE
		        WHEN A.per_price_unit IS NULL 
		        THEN 0 
		        ELSE A.per_price_unit 
		    END AS perPrice,
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 10) AS releaseTime 
		FROM
		    da_market_price A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.price_type_code = '2' 
		    AND B.data_time_type_code = 5
		    AND B.audit_status_code = 1 
		    AND A.strains_code = '5' 
		    ORDER BY startTime DESC*/
	    
		selectBuffer.append(" SELECT 																						");
		selectBuffer.append(" A.strains_text AS strainsText,																");
		selectBuffer.append(" A.price_type_text AS priceTypeText,															");
		selectBuffer.append(" CASE WHEN A.per_price_unit IS NULL THEN 0 ELSE A.per_price_unit END AS perPrice,				");
		selectBuffer.append(" B.start_time AS startTime,																	");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS releaseTime													");
		selectBuffer.append(" FROM																							");
		selectBuffer.append(" da_market_price A,																			");
		selectBuffer.append(" da_common_field B 																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																");
		selectBuffer.append(" AND A.price_type_code =																		");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(priceType)) {
			selectBuffer.append(priceType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.data_time_type_code = 5 																");
		selectBuffer.append(" AND B.audit_status_code = 1 																	");
		selectBuffer.append(" AND A.strains_code =																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(strainsCode)) {
			selectBuffer.append(strainsCode);
		}
		selectBuffer.append("'");
		selectBuffer.append(" ORDER BY startTime DESC																		");
		
		/*selectBuffer.append(" AND A.crop_type_code = 1														");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 											");
		selectBuffer.append(" AND B.region_id =																");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");*/

		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	private Map<String, Object> getDicMapByTypeId(String dicTypeId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT A.`code` AS code, A.text AS text ,A.remark AS remark FROM gp_dictionary A WHERE type_id = '");
		if (StringUtils.isNotBlank(dicTypeId)) {
			selectBuffer.append(dicTypeId);
		}
		selectBuffer.append("'");

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
	
	private Map<String, Object> getDicMarkMapByTypeId(String dicTypeId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append(" SELECT A.`code` AS code, A.text AS text ,A.remark AS remark FROM gp_dictionary A WHERE type_id = '");
		if (StringUtils.isNotBlank(dicTypeId)) {
			selectBuffer.append(dicTypeId);
		}
		selectBuffer.append("'");

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
					String remark = "";
					if (tempMap.containsKey("code")) {
						code = (int) tempMap.get("code") + "";
					}
					if (tempMap.containsKey("remark")) {
						remark = (String) tempMap.get("remark");
					}
					resultMap.put(code, remark);
				}
				return resultMap;
			}
		}
		return null;
	}

	/**
	 * 门户数据版（价格行情）
	 * lxl
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select ");
		selectBuffer.append("A. NAME name,DATE_FORMAT( B.start_time,'%Y-%m-%d') start_time,A.strains_code strainsCode,A.strains_text strainsText,A.per_price_unit averagePriceUnit,B.region_name regionName ");
		selectBuffer.append("FROM da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id ");
		selectBuffer.append("WHERE A.price_type_code='2' and A.name <> '' and A.name is not null order by B.start_time desc ");
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

				if (jsonObject.containsKey("page")) {
					JSONObject pageObject = jsonObject.getJSONObject("page");
					map.put("Page", pageObject);
				}

			}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		
		Object obj = resultModel.getData();
		List<Map<String,Object>> list = null;
		if(obj!=null) {
			list = (List<Map<String, Object>>) obj;
			if(list.size()>=1) {
				for(Map<String,Object> tempMap : list) {
					BigDecimal averagePriceUnit = new BigDecimal("0");
					if(tempMap.containsKey("averagePriceUnit")) {
						BigDecimal price = MathUtil.decimalFormat((BigDecimal) tempMap.get("averagePriceUnit"));
						tempMap.put("averagePriceUnit", price);
					}
				}
			}
		}
		
		return resultModel;
	}

	@ApiOperation(value = "价格监测-价格走势-查询一定月份区间各地区芒果价格数据及同比增长率（门户数据服务版）", notes = "价格监测-价格走势-查询一定月份区间各地区芒果价格数据及同比增长率（门户数据服务版）")
	@RequestMapping(value = "/getPriceDataAndProportion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceDataAndProportion() {
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

		// 确定所需要的时间列表
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "month");// 视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "12");
		List<String> timesList = TimesView.getTimesView(map);
		Collections.sort(timesList);

		Map<String, Object> priceResultMap = new HashMap<String, Object>();

		List priceTimeList = new ArrayList();
		List priceDataList = new ArrayList();
		List priceProportionList = new ArrayList();

		for (int i = 0; i < timesList.size() - 1; i++) {
			priceTimeList.add(timesList.get(i + 1));

			BigDecimal perPrice = new BigDecimal("0");
			BigDecimal lastPerPrice = new BigDecimal("0");

			// 获取所需月份的批发价格
			ResultModel currentModel = getPriceUnderSpecifyCondition(regionId, timesList.get(i + 1), breed, PRICE_TYPE_DISPATCH, DIMENSION_MONTH);

			Map<String, Object> currentMap = getMapFromModel(currentModel);
			if (currentMap != null) {
				if (currentMap.containsKey("perPrice")) {
					perPrice = (BigDecimal) currentMap.get("perPrice");
				}
			}
			priceDataList.add(MathUtil.decimalFormat(perPrice));

			// 获取参考月份（上一个月）的批发价格
			ResultModel lastModel = getPriceUnderSpecifyCondition(regionId, timesList.get(i), breed, PRICE_TYPE_DISPATCH, DIMENSION_MONTH);
			Map<String, Object> lastMap = getMapFromModel(lastModel);
			if (lastMap != null) {
				if (lastMap.containsKey("perPrice")) {
					lastPerPrice = (BigDecimal) lastMap.get("perPrice");
				}
			}
			// 计算同比增长率
			String perPriceProportion = getProportion(perPrice, lastPerPrice);

			priceProportionList.add(perPriceProportion.substring(0, perPriceProportion.length()-1));
		}

		priceResultMap.put("time", priceTimeList);
		priceResultMap.put("data", priceDataList);
		if(priceProportionList.size()>0) {
			priceProportionList.set(0, "-");
		}
		priceResultMap.put("proportion", priceProportionList);

		resultModel.setData(priceResultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
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
		BigDecimal subtractData = lastSum.subtract(beforeLastSum);
		if (beforeLastSum.compareTo(BigDecimal.ZERO) != 0) {
			tempProportion = MathUtil.getPercent(subtractData, beforeLastSum, "0.00%");
		}
		return tempProportion;
	}

	@ApiOperation(value = "数据资源-价格数据-查询指定时间区间内各地区芒果价格数据（门户数据服务版）", notes = "查询指定时间区间内各地区芒果价格数据（门户数据服务版）")
	@RequestMapping(value = "/getTimesPriceData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTimesPriceData() throws NumberFormatException, ParseException {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		String regionId = "";// 地区id
		String breed = "";// 芒果品种id
		String priceType = "";// 价格类型
		String dimension = "";// 时间维度
		String startTime = "";// 开始时间
		String endTime = "";// 结束时间
		String priceUnit = "";//价格单位

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("regionId") && StringUtils.isNotBlank(entityRelatedObject.getString("regionId")))
					regionId = entityRelatedObject.getString("regionId");
				if (entityRelatedObject.containsKey("breed") && StringUtils.isNotBlank(entityRelatedObject.getString("breed")))
					breed = entityRelatedObject.getString("breed");
				if (entityRelatedObject.containsKey("priceType") && StringUtils.isNotBlank(entityRelatedObject.getString("priceType")))
					priceType = entityRelatedObject.getString("priceType");
				if (entityRelatedObject.containsKey("priceUnit") && StringUtils.isNotBlank(entityRelatedObject.getString("priceUnit")))
					priceUnit = entityRelatedObject.getString("priceUnit");
				if (entityRelatedObject.containsKey("dimension") && StringUtils.isNotBlank(entityRelatedObject.getString("dimension")))
					dimension = entityRelatedObject.getString("dimension");
				if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
					startTime = entityRelatedObject.getString("startTime").trim();
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
					endTime = entityRelatedObject.getString("endTime").trim();
			}
		}

		// 首先是计算所需的时间列表
		// 当没有指定时间区间时，使用默认时间列表： 维度为年时，显示最近10年 维度为月时，显示最近12个月 维度为日时，显示最近7天
		// 当指定了时间：无论时间维度是年、月、日，都使用指定的时间区间

		// 确定所需要的时间列表
		// 第一种情况：startTime和endTime都为空
		List<String> timesList = new ArrayList();
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
						Collections.sort(timesList);
						break;
					}
				}
			} else {
				timesList = tempTimesList;
				// if(tempTimesList.size()<=12) {
				// }else {
				// timesList = tempTimesList.subList(tempTimesList.size()-12,
				// tempTimesList.size()-1);
				// }
			}
		}

		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<String> prices = new ArrayList<String>();
		for (String time : timesList) {
			BigDecimal perPrice = new BigDecimal("0");
			ResultModel perPriceModel = new ResultModel();
			if(StringUtils.isNotBlank(priceType) && (priceType.equals("5") || priceType.equals("6"))) {
				perPriceModel = getPriceUnderSpecifyConditionImportAndExport(regionId, time, breed, priceType, dimension);
				/*if(StringUtils.isNotBlank(regionId) && regionId.equals("031000000")) {
				}else {
					perPriceModel = getPriceUnderSpecifyConditionImportAndExport(regionId, time, breed, priceType, dimension);
				}*/
			}else {
				perPriceModel = getPriceUnderSpecifyConditionForPrice(regionId, time, breed, priceType, dimension,priceUnit);
			}
			
			Object perObj = perPriceModel.getData();
			List<Map<String, Object>> perList = null;
			Map<String, Object> perMap = null;
			if (perObj != null) {
				perList = (List<Map<String, Object>>) perObj;
				if (perList.size() >= 1) {
					perMap = perList.get(0);
					if (perMap.containsKey("perPrice")) {
						perPrice = MathUtil.decimalFormat((BigDecimal) perMap.get("perPrice"));
						prices.add(perPrice + "");
					}
				} else {
					prices.add(MathUtil.decimalFormat(perPrice) + "");
				}
			}
		}
		
		timesList = getFormalListAccordingToData(timesList,prices);
		prices = getFormalList(prices);
		
		resultMap.put("priceDatas", prices);
		resultMap.put("times", timesList);

		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
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
	
	private ResultModel getPriceUnderSpecifyConditionImportAndExport(String regionId, String time, String breed,
			String priceType, String dimension) {
		
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 4) AS releaseTime,
		    A.import_price_unit AS perPrice 
		FROM
		    da_import_export A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.`import_country_code` = '0' 
		    AND B.audit_status_code = 1 
		    AND SUBSTR(B.start_time, 1, 4) = '2012' */

		selectBuffer.append(" SELECT																									");
		selectBuffer.append(" B.start_time AS startTime,																				");
		
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_YEAR)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 4) AS releaseTime,															");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_MONTH)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 7) AS releaseTime,															");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_DAY)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS releaseTime,															");
		}
		
		if(StringUtils.isNotBlank(priceType) && priceType.equals("5")) {
			selectBuffer.append(" CASE WHEN AVG(A.import_price) IS NULL THEN 0 ELSE AVG(A.import_price) END AS perPrice 				");
		}else if(StringUtils.isNotBlank(priceType) && priceType.equals("6")){
			selectBuffer.append(" CASE WHEN AVG(A.export_price) IS NULL THEN 0 ELSE AVG(A.export_price) END AS perPrice 				");
		}
		
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_import_export A,																						");
		selectBuffer.append(" da_common_field B 																						");
		
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1 																					");
		selectBuffer.append(" AND B.audit_status_code = 1 																				");
		
		if(StringUtils.isNotBlank(regionId)/* && regionId.equals("031000000")*/) {
			selectBuffer.append(" AND B.region_id =																						");
			selectBuffer.append("'");
			selectBuffer.append(regionId);
			selectBuffer.append("'");
		}/*else if(StringUtils.isNotBlank(regionId) && !regionId.equals("031000000")) {
			if(StringUtils.isNotBlank(priceType) && priceType.equals("5")) {
				selectBuffer.append(" AND A.import_country_code =																		");
			}else if(StringUtils.isNotBlank(priceType) && priceType.equals("6")) {
				selectBuffer.append(" AND A.export_country_code =																		");
			}
			if(StringUtils.isNotBlank(regionId)) {
				selectBuffer.append("'");
				selectBuffer.append(regionId);
				selectBuffer.append("'");
			}
		}*/
		
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_YEAR)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																		");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_MONTH)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 7) =																		");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_DAY)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =																		");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
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

	private ResultModel getPriceUnderSpecifyCondition(String regionId, String time, String strainsCode, String priceType, String dimension) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 7) AS releaseTime,
		    A.strains_text AS strainsText,
		    A.price_type_text AS priceTypeText,
		    CASE
		        WHEN AVG(A.per_price_unit) IS NULL 
		        THEN 0 
		        ELSE AVG(A.per_price_unit) 
		    END AS perPrice
		FROM
		    da_market_price A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.strains_code = '5' 
		    AND A.price_type_code = '2' 
		    AND B.audit_status_code = 1 
		    AND B.data_time_type_code = '5' 
		    AND SUBSTR(B.start_time, 1, 7) = '2017-12' 
		GROUP BY SUBSTR(B.start_time, 1, 7) */

		selectBuffer.append(" SELECT																									");
		selectBuffer.append(" B.start_time AS startTime,																				");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 7) AS releaseTime,																");
		selectBuffer.append(" A.strains_text AS strainsText,																			");
		selectBuffer.append(" A.price_type_text AS priceTypeText,																		");
		selectBuffer.append(" CASE WHEN AVG(A.per_price_unit) IS NULL THEN 0 ELSE AVG(A.per_price_unit) END AS perPrice 				");
		selectBuffer.append(" FROM																										");
		selectBuffer.append(" da_market_price A,																						");
		selectBuffer.append(" da_common_field B 																						");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1 																					");
		if(StringUtils.isNotBlank(strainsCode) && !strainsCode.equals("0")) {
			selectBuffer.append(" AND A.strains_code =																					");
			selectBuffer.append("'");
			selectBuffer.append(strainsCode);
			selectBuffer.append("'");
		}
		selectBuffer.append(" AND A.price_type_code =																					");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(priceType)) {
			selectBuffer.append(priceType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.audit_status_code = 1 																				");
		selectBuffer.append(" AND B.data_time_type_code = '5'																			");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 7) =																			");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(time)) {
			selectBuffer.append(time);
		}
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY SUBSTR(B.start_time, 1, 7) 																		");
		
		/*
		selectBuffer.append(" SELECT 																		");
		selectBuffer.append(" A.strains_text AS strainsText,												");
		selectBuffer.append(" A.price_type_text AS priceTypeText,											");
		selectBuffer.append(" A.average_price_unit AS perPrice,													");
		selectBuffer.append(" B.start_time AS startTime,													");
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_YEAR)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 4) AS releaseTime									");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_MONTH)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 7) AS releaseTime									");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_DAY)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS releaseTime								");
		}
		selectBuffer.append(" FROM																			");
		selectBuffer.append(" da_market_price A,															");
		selectBuffer.append(" da_common_field B 															");
		selectBuffer.append(" WHERE A.common_field_id = B.id 												");
		selectBuffer.append(" AND A.crop_type_code = 1														");
		selectBuffer.append(" AND A.strains_code =															");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(strainsCode)) {
			selectBuffer.append(strainsCode);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND A.price_type_code =														");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(priceType)) {
			selectBuffer.append(priceType);
		}
		selectBuffer.append("'");
		selectBuffer.append(" AND B.audit_status_code = 1 													");
		selectBuffer.append(" AND B.region_id =																");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(regionId)) {
			selectBuffer.append(regionId);
		}
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_YEAR)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =												");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_MONTH)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 7) =												");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_DAY)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =												");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		selectBuffer.append(" ORDER BY startTime DESC														");
*/
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}
	
	private ResultModel getPriceUnderSpecifyConditionForPrice(String regionId, String time, String strainsCode, String priceType, String dimension,String priceUnit) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 4) AS releaseTime,
		    A.strains_text AS strainsText,
		    A.price_type_text AS priceTypeText,
		    CASE
		        WHEN AVG(A.average_price_unit) IS NULL 
		        THEN 0 
		        ELSE AVG(A.average_price_unit) 
		    END AS perPrice 
		FROM
		    da_market_price A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.price_type_code = '2' 
		    AND B.audit_status_code = 1 
		    AND SUBSTR(B.start_time, 1, 4) = '2017' 
		GROUP BY SUBSTR(B.start_time, 1, 4) 
		ORDER BY startTime DESC */

		selectBuffer.append(" SELECT																									");
		selectBuffer.append(" B.start_time AS startTime,																				");
		
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_YEAR)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 4) AS releaseTime,															");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_MONTH)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 7) AS releaseTime,															");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_DAY)) {
			selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS releaseTime,															");
		}
		
		selectBuffer.append(" A.strains_text AS strainsText,																			");
		if(StringUtils.isNotBlank(priceType) && !priceType.equals("4")) {
			selectBuffer.append(" A.price_type_text AS priceTypeText,																	");
		}
		
		if(StringUtils.isNotBlank(priceType) && priceType.equals("4")) {
			selectBuffer.append(" CASE WHEN AVG(A.sale_price_unit) IS NULL THEN 0 ELSE AVG(A.sale_price_unit) END AS perPrice 			");
		}else {
			selectBuffer.append(" CASE WHEN AVG(A.average_price_unit) IS NULL THEN 0 ELSE AVG(A.average_price_unit) END AS perPrice 	");
		}
		
		selectBuffer.append(" FROM																										");
		
		if(StringUtils.isNotBlank(priceType) && priceType.equals("4")) {
			selectBuffer.append(" mf_sale_ecommerce_craw A,																				");
		}else {
			selectBuffer.append(" da_market_price A,																					");
		}
		
		selectBuffer.append(" da_common_field B 																						");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																			");
		selectBuffer.append(" AND A.crop_type_code = 1 																					");
		if(StringUtils.isNotBlank(strainsCode) && !strainsCode.equals("0")) {
			selectBuffer.append(" AND A.strains_code =																					");
			selectBuffer.append("'");
			selectBuffer.append(strainsCode);
			selectBuffer.append("'");
		}
		
		if(StringUtils.isNotBlank(priceType) && !priceType.equals("4")) {
			selectBuffer.append(" AND A.price_type_code =																				");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(priceType)) {
				selectBuffer.append(priceType);
			}
			selectBuffer.append("'");
		}
		
		selectBuffer.append(" AND B.audit_status_code = 1 																				");
		
		if(StringUtils.isNotBlank(priceType) && !priceType.equals("2") && !priceType.equals("4")) {
			selectBuffer.append(" AND B.region_id =																						");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(regionId)) {
				selectBuffer.append(regionId);
			}
			selectBuffer.append("'");
		}
		
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_YEAR)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 4) =																		");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_MONTH)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 7) =																		");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(dimension) && dimension.equals(DIMENSION_DAY)) {
			selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =																		");
			selectBuffer.append("'");
			if (StringUtils.isNotBlank(time)) {
				selectBuffer.append(time);
			}
			selectBuffer.append("'");
		}
//		selectBuffer.append(" GROUP BY SUBSTR(B.start_time, 1, 4) 																		");
		selectBuffer.append(" ORDER BY startTime DESC																					");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		return resultModel;
	}

	@ApiOperation(value = "价格监测-各品种价格（门户数据服务版）", notes = "价格监测-各品种价格（门户数据服务版）")
	@RequestMapping(value = "/getperBreedPriceData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getperBreedPriceData() {
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

		// 确定所需要的时间列表
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "month");// 视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "12");
		List<String> timesList = TimesView.getTimesView(map);
		Collections.sort(timesList);

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("time", timesList);

		// 获取芒果品种<code,text>列表
		Map<String, Object> mangoBreedMap = getDicMapByTypeId(CustomSymbolic.DI_CROP_STRAINS);

		Map<String, Object> perBreedMap = null;
		List<String> perBreedList = null;
		List<Map<String,Object>> infoList = new ArrayList<Map<String,Object>>();
		Iterator iter = mangoBreedMap.entrySet().iterator();
		while (iter.hasNext()) {
			Map<String,Object> detailsMap = new HashMap<String, Object>();
			perBreedList = new ArrayList<String>();
			perBreedMap = new HashMap<String, Object>();
			Map.Entry entry = (Map.Entry) iter.next();
			String code = (String) entry.getKey();
			String text = (String) entry.getValue();
			if (!"0".equals(code)) {
				for (String time : timesList) {
					BigDecimal perPrice = new BigDecimal("0");
					ResultModel perBreedModel = getPriceUnderSpecifyCondition(regionId, time, code, PRICE_TYPE_DISPATCH, DIMENSION_MONTH);
					Object perObj = perBreedModel.getData();
					List<Map<String, Object>> perList = null;
					Map<String, Object> perMap = null;
					if (perObj != null) {
						perList = (List<Map<String, Object>>) perObj;
						if (perList.size() >= 1) {
							perMap = perList.get(0);
							if (perMap.containsKey("perPrice")) {
								perPrice = (BigDecimal) perMap.get("perPrice");
								perBreedList.add(MathUtil.decimalFormat(perPrice) + "");
							}
						} else {
							perBreedList.add(MathUtil.decimalFormat(perPrice) + "");
						}
					}
				}
				boolean flag = false;
				for(int i = 0;i<perBreedList.size();i++) {
					BigDecimal price = new BigDecimal(perBreedList.get(i));
					if(price.compareTo(BigDecimal.ZERO) != 0) {
						flag = true;
					}
				}
				if(flag) {
					detailsMap.put("name", text);
					detailsMap.put("price", perBreedList);
					infoList.add(detailsMap);
				}
			}
		}
		resultMap.put("info", infoList);
		resultMap.put("time", timesList);
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	@ApiOperation(value = "App-首页-查询各个品种芒果的最高、平均、最低田头价和零售价", notes = "App-首页-查询各个品种芒果的最高、平均、最低田头价和零售价")
	@RequestMapping(value = "/getBreedsFieldAndRetailPriceForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getBreedsFieldAndRetailPriceForApp() throws ParseException {
		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
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
		
		resultModel = getBreedsFieldAndRetailPrice(regionId);
		
		return resultModel;
	}
	
	private ResultModel getBreedsFieldAndRetailPrice(String regionId) {
		ResultModel resultModel = new ResultModel();
		
		String time = "";
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "date");// 视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "1");
		List<String> timesList = TimesView.getTimesView(map);
		time = timesList.get(0);
//		time = "2018-07-10";
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		// 获取芒果品种<code,text>列表
		Map<String, Object> mangoBreedMap = getDicMapByTypeId(CustomSymbolic.DI_CROP_STRAINS);
		// 获取芒果品种<code,remark>列表
		Map<String, Object> mangoRemarkMap = getDicMarkMapByTypeId(CustomSymbolic.DI_CROP_STRAINS);
		
		Map<String, Object> perResultMap = null;
		Map<String, Object> perBreedMap = null;
		
		Map<String, Object> perFieldPriceMap = null;
		Map<String, Object> perRetailPriceMap = null;
		
		Iterator iter = mangoBreedMap.entrySet().iterator();
		while (iter.hasNext()) {
			boolean flag = false;
			perResultMap = new HashMap<String, Object>();
			Map.Entry entry = (Map.Entry) iter.next();
			String code = (String) entry.getKey();
			String text = (String) entry.getValue();
			String remark = "";
			if(mangoRemarkMap.containsKey(code)) {
				remark = (String) mangoRemarkMap.get(code);
			}
			if (!"0".equals(code)) {
				perBreedMap = new HashMap<String,Object>();
				perFieldPriceMap = new HashMap<String,Object>();
				perRetailPriceMap = new HashMap<String,Object>();
				
				BigDecimal  divisor = new BigDecimal("2");
				
				BigDecimal fieldMaxPrice = new BigDecimal("0");
				BigDecimal fieldMinPrice = new BigDecimal("0");
				BigDecimal fieldAvgPrice = new BigDecimal("0");
				
				ResultModel perBreedModel_field = getDayPriceDetails(regionId,time,code,DISPATCH_PRICE);
				Object fieldObj = perBreedModel_field.getData();
				List<Map<String,Object>> fieldList = null;
				Map<String,Object> fieldMap = null;
				if(fieldObj!=null) {
					fieldList = (List<Map<String, Object>>) fieldObj;
					if(fieldList.size()>=1) {
						fieldMap = fieldList.get(0);
						if(fieldMap.containsKey("maxPrice")) {
							fieldMaxPrice = MathUtil.decimalFormat((BigDecimal) fieldMap.get("maxPrice"));
							fieldMaxPrice = fieldMaxPrice.divide(divisor, 2, RoundingMode.HALF_UP);
							perFieldPriceMap.put("maxPrice", fieldMaxPrice+"");
						}
						if(fieldMap.containsKey("minPrice")) {
							fieldMinPrice = MathUtil.decimalFormat((BigDecimal) fieldMap.get("minPrice"));
							fieldMinPrice = fieldMinPrice.divide(divisor, 2, RoundingMode.HALF_UP);
							perFieldPriceMap.put("minPrice", fieldMinPrice+"");
						}
						if(fieldMap.containsKey("avgPrice")) {
							fieldAvgPrice = MathUtil.decimalFormat((BigDecimal) fieldMap.get("avgPrice"));
							fieldAvgPrice = fieldAvgPrice.divide(divisor, 2, RoundingMode.HALF_UP);
							perFieldPriceMap.put("avgPrice", fieldAvgPrice+"");
						}
						
					}else {
						perFieldPriceMap.put("maxPrice", "--");
						perFieldPriceMap.put("minPrice", "--");
						perFieldPriceMap.put("avgPrice", "--");
					}
				}
				
				BigDecimal retailMaxPrice = new BigDecimal("0");
				BigDecimal retailMinPrice = new BigDecimal("0");
				BigDecimal retailAvgPrice = new BigDecimal("0");
				
//				ResultModel perBreedModel_retail = getDayPriceDetails(regionId,time,code,RETAIL_PRICE);
				ResultModel perBreedModel_retail = getDayEcommercePriceDetails(regionId,time,code);
				
				Object retailObj = perBreedModel_retail.getData();
				List<Map<String,Object>> retailList = null;
				Map<String,Object> retailMap = null;
				if(retailObj!=null) {
					retailList = (List<Map<String, Object>>) retailObj;
					if(retailList.size()>=1) {
						retailMap = retailList.get(0);
						if(retailMap.containsKey("maxPrice")) {
							retailMaxPrice = MathUtil.decimalFormat((BigDecimal) retailMap.get("maxPrice"));
							retailMaxPrice = retailMaxPrice.divide(divisor, 2, RoundingMode.HALF_UP);
							perRetailPriceMap.put("maxPrice", retailMaxPrice+"");
						}
						if(retailMap.containsKey("minPrice")) {
							retailMinPrice = MathUtil.decimalFormat((BigDecimal) retailMap.get("minPrice"));
							retailMinPrice = retailMinPrice.divide(divisor, 2, RoundingMode.HALF_UP);
							perRetailPriceMap.put("minPrice", retailMinPrice+"");
						}
						if(retailMap.containsKey("avgPrice")) {
							retailAvgPrice = MathUtil.decimalFormat((BigDecimal) retailMap.get("avgPrice"));
							retailAvgPrice = retailAvgPrice.divide(divisor, 2, RoundingMode.HALF_UP);
							perRetailPriceMap.put("avgPrice", retailAvgPrice+"");
						}
					}else {
						perRetailPriceMap.put("maxPrice", "--");
						perRetailPriceMap.put("minPrice", "--");
						perRetailPriceMap.put("avgPrice", "--");
					}
				}
				
				perBreedMap.put("mangoName", text);
				perBreedMap.put("summary", remark);
				perBreedMap.put("imgSrc", text+"imgSrc");
				perBreedMap.put("field", perFieldPriceMap);
				perBreedMap.put("retail", perRetailPriceMap);
				
				Iterator fieldIter = perFieldPriceMap.entrySet().iterator();
				while (fieldIter.hasNext()) {
					Map.Entry fieldEntry = (Map.Entry) fieldIter.next();
					BigDecimal tempPrice = new BigDecimal((String)fieldEntry.getValue());
					if(tempPrice.compareTo(BigDecimal.ZERO) != 0) {
						flag = true;
						break;
					}
				}
				Iterator retailIter = perRetailPriceMap.entrySet().iterator();
				while (retailIter.hasNext()) {
					Map.Entry retailEntry = (Map.Entry) retailIter.next();
					BigDecimal tempPrice = new BigDecimal((String)retailEntry.getValue());
					if(tempPrice.compareTo(BigDecimal.ZERO) != 0) {
						flag = true;
						break;
					}
				}
				if(flag) {
					resultList.add(perBreedMap);
				}
//				resultMap.put(code, perBreedMap);
			}
		}
		resultMap.put("time", time);
		resultMap.put("priceData", resultList);
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		
		return resultModel;
	}
	
	private ResultModel getDayEcommercePriceDetails(String regionId, String time, String strainsCode) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 10) AS releaseTime,
		    A.strains_text AS strainsText,
		    CASE
		        WHEN MAX(A.sale_price_unit) IS NULL 
		        THEN 0 
		        ELSE MAX(A.sale_price_unit) 
		    END AS maxPrice,
		    CASE
		        WHEN MIN(A.sale_price_unit) IS NULL 
		        THEN 0 
		        ELSE MIN(A.sale_price_unit) 
		    END AS minPrice,
		    CASE
		        WHEN AVG(A.sale_price_unit) IS NULL 
		        THEN 0 
		        ELSE AVG(A.sale_price_unit) 
		    END AS avgPrice
		FROM
		    mf_sale_ecommerce_craw A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.crop_type_code = 1 
		    AND A.strains_code = '4' 
		    AND B.audit_status_code = 1 
		    AND SUBSTR(B.start_time, 1, 10) = '2018-08-08'*/
		
		selectBuffer.append(" SELECT 																						");
		selectBuffer.append(" B.start_time AS startTime,																	");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS releaseTime, 													");
		selectBuffer.append(" A.strains_text AS strainsText,																");
		selectBuffer.append(" CASE WHEN MAX(A.sale_price_unit) IS NULL THEN 0 ELSE MAX(A.sale_price_unit) END AS maxPrice,	");
		selectBuffer.append(" CASE WHEN MIN(A.sale_price_unit) IS NULL THEN 0 ELSE MIN(A.sale_price_unit) END AS minPrice,	");
		selectBuffer.append(" CASE WHEN AVG(A.sale_price_unit) IS NULL THEN 0 ELSE AVG(A.sale_price_unit) END AS avgPrice	");
		selectBuffer.append(" FROM																							");
		selectBuffer.append(" mf_sale_ecommerce_craw A,																		");
		selectBuffer.append(" da_common_field B 																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																");
		selectBuffer.append(" AND A.crop_type_code = 1 																		");
		selectBuffer.append(" AND A.strains_code =																			");
		if(StringUtils.isNotBlank(strainsCode)) {
			selectBuffer.append("'");
			selectBuffer.append(strainsCode);
			selectBuffer.append("'");
			
		}
		selectBuffer.append(" AND B.audit_status_code = 1  																	");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =																");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append("'");
			selectBuffer.append(time);
			selectBuffer.append("'");
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		
		return resultModel;
	}

	private ResultModel getDayPriceDetails(String regionId,String time,String strainsCode, String priceType) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		/*SELECT 
		    A.strains_text AS strainsText,
		    A.price_type_text AS priceTypeText,
		    CASE
		        WHEN MAX(A.per_price_unit) IS NULL 
		        THEN 0 
		        ELSE MAX(A.per_price_unit) 
		    END AS maxPrice,
		    CASE
		        WHEN MIN(A.per_price_unit) IS NULL 
		        THEN 0 
		        ELSE MIN(A.per_price_unit) 
		    END AS minPrice,
		    CASE
		        WHEN AVG(A.per_price_unit) IS NULL 
		        THEN 0 
		        ELSE AVG(A.per_price_unit) 
		    END AS avgPrice,
		    B.start_time AS startTime,
		    SUBSTR(B.start_time, 1, 10) AS releaseTime 
		FROM
		    da_market_price A,
		    da_common_field B 
		WHERE A.common_field_id = B.id 
		    AND A.price_type_code = '2' 
		    AND B.data_time_type_code = 5 
		    AND B.audit_status_code = 1 
		    AND SUBSTR(B.start_time, 1, 10) = '2018-08-13' 
		    AND A.strains_code = '5' */
	    
		selectBuffer.append(" SELECT 																						");
		selectBuffer.append(" A.strains_text AS strainsText,																");
		selectBuffer.append(" A.price_type_text AS priceTypeText,															");
		selectBuffer.append(" CASE WHEN MAX(A.per_price_unit) IS NULL THEN 0 ELSE MAX(A.per_price_unit) END AS maxPrice,	");
		selectBuffer.append(" CASE WHEN MIN(A.per_price_unit) IS NULL THEN 0 ELSE MIN(A.per_price_unit) END AS minPrice,	");
		selectBuffer.append(" CASE WHEN AVG(A.per_price_unit) IS NULL THEN 0 ELSE AVG(A.per_price_unit) END AS avgPrice,	");
		selectBuffer.append(" B.start_time AS startTime,																	");
		selectBuffer.append(" SUBSTR(B.start_time, 1, 10) AS releaseTime 													");
		selectBuffer.append(" FROM																							");
		selectBuffer.append(" da_market_price A,																			");
		selectBuffer.append(" da_common_field B 																			");
		selectBuffer.append(" WHERE A.common_field_id = B.id 																");
		selectBuffer.append(" AND A.price_type_code =																		");
		if(StringUtils.isNotBlank(priceType)) {
			selectBuffer.append("'");
			selectBuffer.append(priceType);
			selectBuffer.append("'");
			
		}
		selectBuffer.append(" AND B.data_time_type_code = 5 																");
		selectBuffer.append(" AND B.audit_status_code = 1  																	");
		selectBuffer.append(" AND SUBSTR(B.start_time, 1, 10) =																");
		if(StringUtils.isNotBlank(time)) {
			selectBuffer.append("'");
			selectBuffer.append(time);
			selectBuffer.append("'");
		}
		selectBuffer.append(" AND A.strains_code =																			");
		if(StringUtils.isNotBlank(strainsCode)) {
			selectBuffer.append("'");
			selectBuffer.append(strainsCode);
			selectBuffer.append("'");
			
		}
		
		/*selectBuffer.append(" AND A.crop_type_code = 1 																	");
		selectBuffer.append(" AND B.area_latitude_type_code = 4 															");
		selectBuffer.append(" AND B.region_id =																				");
		if(StringUtils.isNotBlank(regionId)) {
			selectBuffer.append("'");
			selectBuffer.append(regionId);
			selectBuffer.append("'");
			
		}*/
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
		
		return resultModel;
	}
	
	public synchronized ResultModel getContentListForApp(String channelId, String relationId, String isRecommend) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append("   SELECT                                                                              ");
		selectBuffer.append("   	a.channel_id AS channel_id,                                                     ");
		selectBuffer.append("   	e.channel_name AS channel_name,                                                 ");
		selectBuffer.append("   	b.relation_id AS relation_id,                                                   ");
		selectBuffer.append("   	a.content_id AS content_id,                                                     ");
		selectBuffer.append("   	d.title AS title,                                                   			");
		selectBuffer.append("   	d.author AS author,                                                   			");
		selectBuffer.append("   	d.description AS description,                                                   ");
		selectBuffer.append("   	f.`id` AS type_id,                                                          	");
		selectBuffer.append("   	f.`name` AS type_name,                                                          ");
		selectBuffer.append("   	DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') AS release_date,                   ");
		selectBuffer.append("   	c.is_recommend AS is_recommend,                                                 ");
		selectBuffer.append("   	i.path AS img_path                                                              ");
		
		selectBuffer.append("   FROM                                                                                ");
		
		// 如果一个栏目有子栏目，展示文章列表时需要去除重复项
		if (isParentChannel(channelId)) {
			selectBuffer.append("   (SELECT * FROM pir_content_channel GROUP BY content_id)  a              ");
		} else {
			selectBuffer.append(" pir_content_channel a              										");
		}
		
		selectBuffer.append("   INNER JOIN pi_channel b ON a.channel_id = b.id                                      ");
		selectBuffer.append("   INNER JOIN pi_content c ON a.content_id = c.id                                      ");
		selectBuffer.append("   LEFT JOIN pi_content_ext d ON d.content_id = c.id                                   ");
		selectBuffer.append("   LEFT JOIN pi_channel_ext e ON e.channel_id = a.channel_id                           ");
		selectBuffer.append("   INNER JOIN pi_content_type f ON f.id = c.type_id                                    ");
		selectBuffer.append("   LEFT JOIN pi_content_txt g ON g.content_id = c.id                                   ");
		selectBuffer.append("	LEFT JOIN                                                                           ");
		selectBuffer.append("	pir_content_image h ON h.content_id = c.id                                          ");
		selectBuffer.append("	LEFT JOIN                                                                           ");
		selectBuffer.append("	gp_resource i ON h.resource_id = i.id                                               ");
		selectBuffer.append("   WHERE 1=1                                                                           ");
		
		selectBuffer.append("   AND relation_id LIKE '");
		selectBuffer.append(relationId);
		selectBuffer.append("%'");
		
		// 企业微网站的数据不应显示在首页和市场页面的轮播中
		if (!CustomSymbolic.CHANNEL_WEBSIT.equals(channelId)) {
			selectBuffer.append("AND a.channel_id !='");
			selectBuffer.append(CustomSymbolic.CHANNEL_WEBSIT);
			selectBuffer.append("'");
		}
		if (StringUtils.isNotBlank(isRecommend)) {
			if ("0".equals(isRecommend)) {
				selectBuffer.append("   AND is_recommend=0");
				selectBuffer.append("   AND path IS NOT NULL ");
			}
		}
		
		// 还没到发布时间的的文章暂时不显示
		String curDate = DateUtils.getCurrentTimeStr();
		selectBuffer.append("AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') <= '" + curDate + "'");
		
		selectBuffer.append(" order by release_date DESC");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		List<Map<String, Object>> resultList = (List<Map<String, Object>>) resultModel.getData();
		List<Map<String, Object>> rList = new ArrayList<Map<String, Object>>();
		// List<String> contentIdList = new ArrayList<String>();
		for (Map<String, Object> resultMap : resultList) {
			// String tempContentId = (String) resultMap.get("content_id");
			String tempPath = (String) resultMap.get("img_path");
			if (StringUtils.isNotBlank(tempPath)) {
				tempPath = linkPath + tempPath;
				resultMap.put("img_path", tempPath);
			}
			String tempTypeId = (String) resultMap.get("type_id");
			if (StringUtils.isNotBlank(tempTypeId)) {
				if (CustomSymbolic.CONTENT_TYPE_SEE.equals(tempTypeId)) {
					tempTypeId = "1";
				}
				if (CustomSymbolic.CONTENT_TYPE_HEAR.equals(tempTypeId)) {
					tempTypeId = "2";
				}
				if (CustomSymbolic.CONTENT_TYPE_IMAGE.equals(tempTypeId)) {
					tempTypeId = "3";
				}
				if (CustomSymbolic.CONTENT_TYPE_SAY.equals(tempTypeId)) {
					tempTypeId = "4";
				}
				resultMap.put("type_id", tempTypeId);
			}
			rList.add(resultMap);
			// if(!contentIdList.contains(tempContentId)) {
			// 将查询到的imgPath拼接为完整路径
			// contentIdList.add(tempContentId);
			// }
		}
		resultModel.setData(rList);
		return resultModel;
	}
	
	/*private ResultModel getContentListForApp(String channelId, String relationId, String isRecommend) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append("   SELECT                                                                              ");
		selectBuffer.append("   	a.channel_id AS channel_id,                                                     ");
		selectBuffer.append("   	e.channel_name AS channel_name,                                                 ");
		selectBuffer.append("   	b.relation_id AS relation_id,                                                   ");
		selectBuffer.append("   	a.content_id AS content_id,                                                     ");
		selectBuffer.append("   	d.title AS title,                                                   			");
		selectBuffer.append("   	d.author AS author,                                                   			");
		selectBuffer.append("   	d.description AS description,                                                   ");
		selectBuffer.append("   	f.`id` AS type_id,                                                          	");
		selectBuffer.append("   	f.`name` AS type_name,                                                          ");
		selectBuffer.append("   	DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') AS release_date,                ");
		selectBuffer.append("   	c.is_recommend AS is_recommend,                                                 ");
		selectBuffer.append("   	h.img_path AS img_path                                                          ");
		selectBuffer.append("   FROM                                                                                ");
		//如果一个栏目有子栏目，展示文章列表时需要去除重复项
		if(isParentChannel(channelId)) {
			selectBuffer.append("   (SELECT * FROM pir_content_channel GROUP BY content_id)  a              ");
		}else {
			selectBuffer.append(" pir_content_channel a              										");
		}
		selectBuffer.append("   INNER JOIN pi_channel b ON a.channel_id = b.id                                      ");
		selectBuffer.append("   INNER JOIN pi_content c ON a.content_id = c.id                                      ");
		selectBuffer.append("   LEFT JOIN pi_content_ext d ON d.content_id = c.id                                   ");
		selectBuffer.append("   LEFT JOIN pi_channel_ext e ON e.channel_id = a.channel_id                           ");
		selectBuffer.append("   INNER JOIN pi_content_type f ON f.id = c.type_id                                    ");
		selectBuffer.append("   LEFT JOIN pi_content_txt g ON g.content_id = c.id                                   ");
		selectBuffer.append("   LEFT JOIN pi_content_picture h ON h.content_id = c.id                               ");
		selectBuffer.append("   WHERE 1=1                                                                           ");
		selectBuffer.append("   AND relation_id LIKE '");
		selectBuffer.append(relationId);
		selectBuffer.append("%'");
		//企业微网站的数据不应显示在首页和市场页面的轮播中
		if(!SymbolicConstant.CHANNEL_WEBSIT.equals(channelId)) {
			selectBuffer.append("AND a.channel_id !='");
			selectBuffer.append(SymbolicConstant.CHANNEL_WEBSIT);
			selectBuffer.append("'");
		}
		if(StringUtils.isNotBlank(isRecommend)) {
			if("0".equals(isRecommend)) {
				selectBuffer.append("   AND is_recommend=0");
				selectBuffer.append("   AND img_path IS NOT NULL ");
			}
		}
		//还没到发布时间的的文章暂时不显示
		String curDate = DateUtils.getCurrentTimeStr();
		selectBuffer.append("AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') <= '" + curDate + "'");
			
		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);
		
		List<Map<String, Object>> resultList = (List<Map<String, Object>>) resultModel.getData();
		List<Map<String, Object>> rList = new ArrayList<Map<String, Object>>();
//		List<String> contentIdList = new ArrayList<String>();
		for(Map<String, Object> resultMap : resultList) {
//			String tempContentId = (String) resultMap.get("content_id");
			String tempPath = (String) resultMap.get("img_path");
			if(StringUtils.isNotBlank(tempPath)) {
				tempPath = fileUtil.getPathByFileName(tempPath).get("linkPath")+tempPath;
				resultMap.put("img_path", tempPath);
			}
			String tempTypeId = (String) resultMap.get("type_id");
			if(StringUtils.isNotBlank(tempTypeId)) {
				if(SymbolicConstant.CONTENT_TYPE_SEE.equals(tempTypeId)) {
					tempTypeId = "1";
				}
				if(SymbolicConstant.CONTENT_TYPE_HEAR.equals(tempTypeId)) {
					tempTypeId = "2";
				}
				if(SymbolicConstant.CONTENT_TYPE_IMAGE.equals(tempTypeId)) {
					tempTypeId = "3";
				}
				if(SymbolicConstant.CONTENT_TYPE_SAY.equals(tempTypeId)) {
					tempTypeId = "4";
				}
				resultMap.put("type_id", tempTypeId);
			}
			rList.add(resultMap);
//			if(!contentIdList.contains(tempContentId)) {
				//将查询到的imgPath拼接为完整路径
//				contentIdList.add(tempContentId);
//			}
		}
		resultModel.setData(rList);
		return resultModel;
	}*/

	private boolean isParentChannel(String tempcId) {
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         "); 
		selectBuffer.append("		a.id id 												   "); 
		selectBuffer.append("	FROM                                                           "); 
		selectBuffer.append("		pi_channel a                                               "); 
		selectBuffer.append("   WHERE parent_id =        									   ");
		selectBuffer.append("'");
		selectBuffer.append(tempcId);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if (object != null)
			objectList = (List<Map<String, Object>>) object;
		if (objectList != null && objectList.size() >= 1) {
			return true;
		}
		return false;
	}
	
	@ApiOperation(value = "App-首页-查询首页数据", notes = "合并1、获取轮播列表、2、查询各个品种芒果的最高、平均、最低田头价和零售价、3、获取热点文章列表 三个接口")
	@RequestMapping(value = "/getHomePageDataForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getHomePageDataForApp() throws ParseException {
		ResultModel resultModel = new ResultModel();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		//价格数据
		ResultModel priceModel = getBreedsFieldAndRetailPrice("530723");
		Object priceObj = priceModel.getData();
		Map<String,Object> priceMap = null;
		if(priceObj!=null) {
			priceMap = (Map<String, Object>) priceObj;
		}
		//轮播数据
		ResultModel bannerModel = getContentListForApp("3b8f6e987d853f93c899d82c32216c3e","01","0");
		Object bannerObj = bannerModel.getData();
		List<Map<String,Object>> bannerList = null;
		if(bannerObj!=null) {
			bannerList = (List<Map<String, Object>>) bannerObj;
			if(bannerList.size()>=3) {
				bannerList = bannerList.subList(0, 3);
			}
		}
		//热点数据
		ResultModel hotModel = getContentListForApp("6b8e0f738daed0e79952eba26faa82dc","0101","0");
		Object hotObj = hotModel.getData();
		List<Map<String,Object>> hotList = null;
		if(hotObj!=null) {
			hotList = (List<Map<String, Object>>) hotObj;
			if(hotList.size()>=3) {
				hotList = hotList.subList(0, 3);
			}
		}
		resultMap.put("price", priceMap);
		resultMap.put("banner", bannerList);
		resultMap.put("hot", hotList);
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	@ApiOperation(value = "App-查询新闻数据", notes = "合并1、获取热点文章列表、2、获取政策文章列表、3、获取病虫害文章列表、4、获取种植技术文章列表")
	@RequestMapping(value = "/getNewsDataForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getNewsDataForApp() throws ParseException {
		ResultModel resultModel = new ResultModel();
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		ResultModel hotnewModel = getContentListForApp("6b8e0f738daed0e79952eba26faa82dc","0101","1");
		ResultModel policyModel = getContentListForApp("b8cd038d85c616e5c63d207339c2a07f","010201","1");
		ResultModel pestsModel = getContentListForApp("1da6e5de08b7eabeb97bff0ad7384c7c","010204","1");
		ResultModel plantModel = getContentListForApp("90cb31e180b0f1579f59db9a7629fde6","010206","1");
		
		List<Map<String, Object>> hotnewList = getListFromModel(hotnewModel);
		if(hotnewList.size()>=3) {
			hotnewList = hotnewList.subList(0, 3);
		}
		List<Map<String, Object>> policyList = getListFromModel(policyModel);
		if(policyList.size()>=3) {
			policyList = policyList.subList(0, 3);
		}
		List<Map<String, Object>> pestsList = getListFromModel(pestsModel);
		if(pestsList.size()>=3) {
			pestsList = pestsList.subList(0, 3);
		}
		List<Map<String, Object>> plantList = getListFromModel(plantModel);
		if(plantList.size()>=3) {
			plantList = plantList.subList(0, 3);
		}
		
		resultMap.put("hotnew", hotnewList);
		resultMap.put("policy", policyList);
		resultMap.put("pests", pestsList);
		resultMap.put("plant", plantList);
		
		resultModel.setData(resultMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
}

