package com.jusfoun.app.extend.swagger.gp;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import com.jusfoun.app.generate.swagger.gp.GpRoleGenSwgApp;
import com.jusfoun.bll.extend.unity.gp.GprRoleModuleUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpRole;
import com.jusfoun.ent.extend.gp.GprRoleModule;
import com.jusfoun.ent.parameter.gp.GpRoleParameter;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 系统角色。 对外接口，扩展自GpRoleGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpRole")
public class GpRoleSwgApp extends GpRoleGenSwgApp {

	@Autowired
	@Qualifier("gprRoleModuleUntBll")
	protected GprRoleModuleUntBll gprRoleModuleUntBll;

	/**
	 * 根据roleId查询中间表列表
	 * @param roleId
	 */
	private List<Map<String, Object>> getModelList(String roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                       ");
		selectBuffer.append("		A.id id,                                                 ");
		selectBuffer.append("		A.role_id roleId,                                        ");
		selectBuffer.append("		A.module_id moduleId,                                    ");
		selectBuffer.append("		C.name menuName                                          ");
		selectBuffer.append("	FROM                                                         ");
		selectBuffer.append("		gpr_role_module A                                        ");
		selectBuffer.append("	INNER JOIN gp_module B ON A.module_id = B.id                 ");
		selectBuffer.append("	INNER JOIN gp_menu C ON C.module_id = B.id                   ");
		selectBuffer.append("	WHERE A.role_id = '" + roleId + "'                               ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gprRoleModuleUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		return modelList;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		List<Map<String, Object>> modelList = getModelList(id);
		ArrayList<String> idList = new ArrayList<String>();
		for (Map<String, Object> map2 : modelList) {
			idList.add(map2.get("id").toString());
		}
		if (idList != null && idList.size() > 0) {
			gprRoleModuleUntBll.deleteByIdList(idList);
		}
		ResultModel result = gpRoleUntBll.delete(id);
		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "GpRoleDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpRoleParameter.DeleteByIdList jsonData) {
		for (String roleId : jsonData.getIdList()) {
			List<Map<String, Object>> modelList = getModelList(roleId);
			ArrayList<String> idList = new ArrayList<String>();
			for (Map<String, Object> map2 : modelList) {
				idList.add(map2.get("id").toString());
			}
			if (idList != null && idList.size() > 0) {
				gprRoleModuleUntBll.deleteByIdList(idList);
			}
		}
		ResultModel result = gpRoleUntBll.deleteByIdList(jsonData.getIdList());
		return result;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpRoleUntBll.getModel(id);
		if (result.getData() != null) {
			GpRole gpRole = (GpRole) result.getData();
			Map<String, Object> map1 = new HashMap<String, Object>();
			StringBuffer selectBuffer1 = new StringBuffer();

			// 查询模块id
			List<Map<String, Object>> modelList1 = getModelList(id);
			String moduleIds = "";
			String menuNames = "";
			for (int i = 0; i < modelList1.size(); i++) {
				moduleIds += modelList1.get(i).get("moduleId");
				menuNames += modelList1.get(i).get("menuName");
				if (i != modelList1.size() - 1) {
					moduleIds += ",";
					menuNames += ",";
				}
			}
			gpRole.setModuleIds(moduleIds);
			gpRole.setMenuNames(menuNames);

			// 查询应用领域
			Map<String, Object> map2 = new HashMap<String, Object>();
			StringBuffer selectBuffer2 = new StringBuffer();
			selectBuffer2.append("		SELECT                                                   ");
			selectBuffer2.append("			B.name domainName                                    ");
			selectBuffer2.append("		FROM                                                     ");
			selectBuffer2.append("			gp_role A                                            ");
			selectBuffer2.append("		LEFT JOIN gp_domain B ON A.domain_id = B.id              ");
			selectBuffer2.append("		WHERE A.id = '" + gpRole.getId() + "'	                     ");
			map2.put("Sql", selectBuffer2.toString());
			ResultModel resultModel2 = gpRoleUntBll.getListBySQL(map2);
			List<Map<String, Object>> modelList2 = (List<Map<String, Object>>) resultModel2.getData();
			if (modelList2 != null && modelList2.size() > 0) {
				gpRole.setDomainName(modelList2.get(0).get("domainName").toString());
			}
			result.setData(gpRole);
		}
		return result;
	}

	/**
	 * 修改角色模块关系表
	 * @param userId
	 * @param jsonData
	 */
	private void updateRoleModule(String roleId, GpRole jsonData) {
		// 先删除再修改
		List<Map<String, Object>> modelList = getModelList(roleId);
		ArrayList<String> idList = new ArrayList<String>();
		for (Map<String, Object> map2 : modelList) {
			idList.add(map2.get("id").toString());
		}
		if (idList != null && idList.size() > 0) {
			gprRoleModuleUntBll.deleteByIdList(idList);
		}
		if (StringUtils.isNotBlank(jsonData.getModuleIds())) {
			for (String moduleId : jsonData.getModuleIds().split(",")) {
				GprRoleModule gprRoleModule = new GprRoleModule();
				gprRoleModule.setRoleId(roleId);
				gprRoleModule.setModuleId(moduleId);
				gprRoleModuleUntBll.add(gprRoleModule);
			}
		}
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpRole") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpRole jsonData) {
		ResultModel result = gpRoleUntBll.update(jsonData);
		// 先删除再修改
		updateRoleModule(jsonData.getId(), jsonData);
		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpRoleUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpRoleParameter.UpdateList jsonData) {
		jsonData.getEntity().setUpdateTime(DateUtils.getCurrentTime());
		ResultModel result = gpRoleUntBll.updateList(jsonData);
		// 如果没有选模块不批量修改
		if (StringUtils.isNotBlank(jsonData.getEntity().getModuleIds())) {
			for (String roleId : jsonData.getIdList()) {
				updateRoleModule(roleId, jsonData.getEntity());
			}
		}
		return result;
	}

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpRole") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpRole jsonData) {
		ResultModel result = gpRoleUntBll.add(jsonData);
		if (StringUtils.isNotBlank(jsonData.getModuleIds())) {
			for (String moduleId : jsonData.getModuleIds().split(",")) {
				GprRoleModule gprRoleModule = new GprRoleModule();
				gprRoleModule.setRoleId(jsonData.getId());
				gprRoleModule.setModuleId(moduleId);
				gprRoleModuleUntBll.add(gprRoleModule);
			}
		}
		return result;
	}

	@RequestMapping(value = "/getRoleListByDomainId/{domainId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getRoleListByDomainId(@PathVariable("domainId") String domainId) {

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		group_concat(A.name)          roleNames                    ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		gp_role A                                                   ");
		selectBuffer.append("	WHERE                                                          ");
		selectBuffer.append("		A.domain_id = '" + domainId + "' order by A.name             ");
		map.put("Sql", selectBuffer.toString());

		ResultModel result = gpRoleUntBll.getListBySQL(map);

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
		selectBuffer.append("	SELECT                                    ");
		selectBuffer.append("		A.id id,                              ");
		selectBuffer.append("		A.domain_id domainId,                 ");
		selectBuffer.append("		B.name domainName,             ");
		selectBuffer.append("		A.name name,                          ");
		selectBuffer.append("		A.remark remark,                      ");
		selectBuffer.append("		A.add_time addTime,                   ");
		selectBuffer.append("		A.update_time updateTime              ");
		selectBuffer.append("	FROM                                      ");
		selectBuffer.append("		gp_role A                             ");
		selectBuffer.append("	INNER JOIN gp_domain B ON A.domain_id = B.id       ");
		selectBuffer.append("	WHERE                                     ");
		selectBuffer.append("		1 = 1                                 ");

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
				if (entityRelatedObject.containsKey("selectDomainId") && StringUtils.isNotBlank(entityRelatedObject.getString("selectDomainId")))
					selectBuffer.append(" and A.domain_id = '").append(entityRelatedObject.getString("selectDomainId")).append("'");
				if (entityRelatedObject.containsKey("textName") && StringUtils.isNotBlank(entityRelatedObject.getString("textName")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("textName")).append("%'");
				if (entityRelatedObject.containsKey("textRemark") && StringUtils.isNotBlank(entityRelatedObject.getString("textRemark")))
					selectBuffer.append(" and A.remark like '%").append(entityRelatedObject.getString("textRemark")).append("%'");
	
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

		resultModel = gpRoleUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "系统角色列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
