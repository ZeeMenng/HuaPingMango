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
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.DictionaryUtil;
import com.jusfoun.utl.FileUtil;
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
	public ResultModel updateInterfaceConstants() throws IOException {
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
		selectBuffer.append("	SELECT A.url url FROM gp_interface A");
		sqlMap.put("Sql", selectBuffer.toString());
		result = gpInterfaceUntBll.getListBySQL(sqlMap);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) result.getData();
		List<String> interfaceUrlList = new ArrayList<String>();
		for (Map map2 : modelList) {
			interfaceUrlList.add(map2.get("url").toString());
		}

		ArrayList<GpInterface> gpInterfaceList = new ArrayList<GpInterface>();
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
		for (Map.Entry<RequestMappingInfo, HandlerMethod> item : handlerMethods.entrySet()) {
			HashMap<String, String> handlerMethodsMap = new HashMap<String, String>();
			RequestMappingInfo info = item.getKey();
			HandlerMethod method = item.getValue();

			PatternsRequestCondition p = info.getPatternsCondition();
			for (String url : p.getPatterns()) {
				if (url.equals("/error"))
					continue;
				if (url.contains("{")) {
					url = url.substring(0, url.indexOf("{") - 1) + "/";
				}
				handlerMethodsMap.put("url", url);
			}
			String className = method.getMethod().getDeclaringClass().getSimpleName();
			className = className.replaceAll("GenSwgApp", "");
			className = className.replaceAll("SwgApp", "");
			className = className.replaceAll("Controller", "");
			handlerMethodsMap.put("className", className); // 类名
			handlerMethodsMap.put("method", method.getMethod().getName()); // 方法名
			RequestMethodsRequestCondition methodsCondition = info.getMethodsCondition();
			String type = methodsCondition.toString();
			if (type != null && type.startsWith("[") && type.endsWith("]")) {
				type = type.substring(1, type.length() - 1);
				handlerMethodsMap.put("type", type); // 请求类型
			}
			// 变量名
			String name = "var RU_" + (handlerMethodsMap.get("className") + "_" + handlerMethodsMap.get("method")).toUpperCase();
			// 路径
			String path = "\"" + handlerMethodsMap.get("url") + "\";";
			String text = name + " = " + path + System.getProperty("line.separator", "/n");

			FileOutputStream fileOutputStream = new FileOutputStream(new File(jsConstantsPath), true);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
			bufferedOutputStream.write(text.getBytes());
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
	
			if (StringUtils.isNotBlank(handlerMethodsMap.get("url")) && !interfaceUrlList.contains(handlerMethodsMap.get("url"))) {
				GpInterface gpInterface = new GpInterface();
				gpInterface.setId(Tools.getUUID());
                gpInterface.setDomainId(Tools.getUUID());
				gpInterface.setTableName(handlerMethodsMap.get("className"));
				gpInterface.setUrl(handlerMethodsMap.get("url"));
				gpInterface.setAddTime(new Date());
				gpInterface.setIsPublicCode((byte) 0);
				if ("POST".equals(handlerMethodsMap.get("type"))) {
					gpInterface.setTypeCode("1");
				} else if ("GET".equals(handlerMethodsMap.get("type"))) {
					gpInterface.setTypeCode("0");
				}
				gpInterfaceList.add(gpInterface);
			}
		}
		result = gpInterfaceUntBll.add(gpInterfaceList);

		result.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		result.setResultMessage("接口记录更新成功……");
		return result;
	}

}
