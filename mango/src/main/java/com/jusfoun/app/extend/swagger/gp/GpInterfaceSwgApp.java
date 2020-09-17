package com.jusfoun.app.extend.swagger.gp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.util.DateParseException;
import org.apache.commons.httpclient.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.jusfoun.app.generate.swagger.gp.GpInterfaceGenSwgApp;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpInterface;
import com.jusfoun.ent.parameter.gp.GpInterfaceParameter;
import com.jusfoun.set.enumer.DomainEnum;
import com.jusfoun.set.enumer.InterfaceType;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.CamelCaseUtl;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.DictionaryUtil;
import com.jusfoun.utl.FileUtil;
import com.jusfoun.utl.SnowFlakeSerialNoWorkerUtl;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 系统接口。 对外接口，扩展自GpInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpInterface")
public class GpInterfaceSwgApp extends GpInterfaceGenSwgApp {

	@Autowired
	private RequestMappingHandlerMapping requestMappingHandlerMapping;

	@Autowired
	private DictionaryUtil dictionaryUtil;

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpInterface") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpInterface jsonData) {
		ResultModel result = gpInterfaceUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpInterfaceUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpInterfaceParameter.UpdateList jsonData) {
		jsonData.getEntity().setAddTime(DateUtils.getCurrentTime());
		ResultModel result = gpInterfaceUntBll.updateList(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpInterfaceUntBll.getModel(id);
		if (result.getData() != null) {
			GpInterface gpInterface = (GpInterface) result.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("		SELECT                                            ");
			selectBuffer.append("			B.name domainName                             ");
			selectBuffer.append("		FROM                                              ");
			selectBuffer.append("			gp_interface A                                ");
			selectBuffer.append("		LEFT JOIN gp_domain B ON A.domain_id = B.id       ");
			selectBuffer.append("		WHERE A.id = '" + gpInterface.getId() + "'            ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpInterfaceUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
			Map<String, Object> modelMap = modelList.get(0);
			gpInterface.setDomainName(modelMap.get("domainName") != null ? modelMap.get("domainName").toString() : "");
			result.setData(dictionaryUtil.dictTransform(gpInterface));
		}
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
		selectBuffer.append("	SELECT                                                       ");
		selectBuffer.append("		A.id id,                                                 ");
		selectBuffer.append("		A.domain_id domainId,                                    ");
		selectBuffer.append("		A.table_name tableName,                                  ");
		selectBuffer.append("		A.url url,                                               ");
		selectBuffer.append("		A.remark remark,                                         ");
		selectBuffer.append("		A.add_time addTime,                                      ");
		selectBuffer.append("		IF(A.is_public_code=0,'是','否') isPublicCode,           ");
		selectBuffer.append("		IF(A.type_code='0','GET','POST') typeCode                ");
		selectBuffer.append("	FROM                                                         ");
		selectBuffer.append("		gp_interface A                                           ");
		selectBuffer.append("	INNER JOIN gp_interface B ON A.id = B.id                     ");
		selectBuffer.append("	WHERE                                                        ");
		selectBuffer.append("		1 = 1                                                    ");

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

				if (entityRelatedObject.containsKey("tableName") && StringUtils.isNotBlank(entityRelatedObject.getString("tableName")))
					selectBuffer.append(" and A.table_name like '%").append(entityRelatedObject.getString("tableName")).append("%'");
				if (entityRelatedObject.containsKey("url") && StringUtils.isNotBlank(entityRelatedObject.getString("url")))
					selectBuffer.append(" and A.url like '%").append(entityRelatedObject.getString("url")).append("%'");
				if (entityRelatedObject.containsKey("remark") && StringUtils.isNotBlank(entityRelatedObject.getString("remark")))
					selectBuffer.append(" and A.remark like '%").append(entityRelatedObject.getString("remark")).append("%'");
				if (entityRelatedObject.containsKey("isPublicCode") && StringUtils.isNotBlank(entityRelatedObject.getString("isPublicCode")))
					selectBuffer.append(" and A.is_public_code = '").append(entityRelatedObject.getString("isPublicCode")).append("'");
				if (entityRelatedObject.containsKey("typeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("typeCode")))
					selectBuffer.append(" and A.type_code = '").append(entityRelatedObject.getString("typeCode")).append("'");
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

		resultModel = gpInterfaceUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "系统接口列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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

	@RequestMapping(value = "/updateInterfaceConstants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateInterfaceConstants() throws IOException, DateParseException {
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			throw new GlobalException("缺少必要参数！");
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if (!jsonObject.containsKey("jsConstantsPath"))
			throw new GlobalException("缺少必要参数！");
		ResultModel result = new ResultModel();

		// 删除之前的常量文件
		String jsConstantsPath = jsonObject.getString("jsConstantsPath");
		FileUtil.deleteFile(new File(jsConstantsPath));

		Map<String, Object> sqlMap = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT * FROM gp_interface A");
		sqlMap.put("Sql", selectBuffer.toString());
		result = gpInterfaceUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> interfaceList = (List<Map<String, Object>>) result.getData();

		ArrayList<GpInterface> gpInterfaceList = new ArrayList<GpInterface>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			RequestMappingInfo requestMappingInfo = item.getKey();
			HandlerMethod handlerMethod = item.getValue();

			Date addTime = DateUtils.getCurrentTime();
			String domainId = null;
			String id = Tools.getUUID();
			Byte isPublicCode = SymbolicConstant.DCODE_BOOLEAN_T;
			String name = null;
			String remark = null;
			String serialNo = String.valueOf(new SnowFlakeSerialNoWorkerUtl(SymbolicConstant.SNOWFLAKE_SERIAL_NO_DATACENTER_ID, SymbolicConstant.SNOWFLAKE_SERIAL_NO_WORKDER_ID).nextId());

			String tableName = null;
			Byte typeCode = null;
			Date updateTime = DateUtils.getCurrentTime();
			String url = null;

			// 获取接口路径URL
			PatternsRequestCondition patternsRequestCondition = requestMappingInfo.getPatternsCondition();
			for (String urlPattern : patternsRequestCondition.getPatterns()) {
				if (urlPattern.equals("/error"))
					continue;
				if (urlPattern.contains("{")) {
					urlPattern = urlPattern.substring(0, urlPattern.indexOf("{") - 1) + "/";
				}
				url = urlPattern;
			}
			if (StringUtils.isBlank(url))
				continue;

			// 获取JS常量文件中的变量名，并写入JS常量文件中
			String className = handlerMethod.getMethod().getDeclaringClass().getSimpleName().replaceAll("GenSwgApp", "").replaceAll("SwgApp", "").replaceAll("Controller", "");
			String methodName = handlerMethod.getMethod().getName();
			String jsConstantName = "var RU_" + (className + "_" + methodName).toUpperCase();

			String path = "\"" + url + "\";";
			String text = jsConstantName + " = " + path + System.getProperty("line.separator", "/n");

			FileOutputStream fileOutputStream = new FileOutputStream(new File(jsConstantsPath), true);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(text.getBytes());
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			// 处理应用领域
			if (className.startsWith(DomainEnum.DA.getName()))
				domainId = DomainEnum.DA.getId();
			if (className.startsWith(DomainEnum.MF.getName()))
				domainId = DomainEnum.MF.getId();
			if (className.startsWith(DomainEnum.WP.getName()))
				domainId = DomainEnum.WP.getId();
			if (className.startsWith(DomainEnum.GP.getName()))
				domainId = DomainEnum.GP.getId();

			// 获取接口调用方式typeCode
			RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
			String type = methodsCondition.toString();
			if (type != null && type.startsWith("[") && type.endsWith("]")) {
				type = type.substring(1, type.length() - 1);
				if (InterfaceType.POST.getText().equals(type)) {
					typeCode = InterfaceType.POST.getCode();
				} else if (InterfaceType.GET.getText().equals(type)) {
					typeCode = InterfaceType.GET.getCode();
				}
			}

			// 获取接口名称name和接口描述remark
			ApiOperation apiOperation = handlerMethod.getMethodAnnotation(ApiOperation.class);
			if (apiOperation != null) {
				name = apiOperation.value();
				remark = apiOperation.notes();
			}

			// 将类名转换为表名
			tableName = CamelCaseUtl.toUnderlineName(className);

			// 将接口信息插入数据库
			GpInterface gpInterface = new GpInterface();
			gpInterface.setId(id);
			gpInterface.setAddTime(addTime);
			gpInterface.setName(name);
			gpInterface.setDomainId(domainId);
			gpInterface.setTableName(tableName);
			gpInterface.setUrl(url);
			gpInterface.setIsPublicCode(isPublicCode);
			gpInterface.setRemark(remark);
			gpInterface.setSerialNo(serialNo);
			gpInterface.setTypeCode(typeCode);
			gpInterface.setUpdateTime(updateTime);

			// 如果包含，说明是修改记录
			for (Map<String, Object> interfaceMap : interfaceList) {
				if (interfaceMap.get("url").equals(url)) {
					gpInterface.setId(interfaceMap.get("id").toString());
					if (interfaceMap.get("serial_no") != null)
						gpInterface.setSerialNo(interfaceMap.get("serial_no").toString());
					gpInterface.setAddTime(null);
					break;
				}
			}

			gpInterfaceList.add(gpInterface);

		}

		result = gpInterfaceUntBll.updateListWithDffOrAdd(gpInterfaceList);

		result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		result.setResultMessage("接口记录更新成功……");
		return result;
	}

}
