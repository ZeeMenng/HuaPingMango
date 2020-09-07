package com.jusfoun.app.extend.swagger.gp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GpRoleGenSwgApp;
import com.jusfoun.bll.extend.split.gp.GprDomainUserSplBll;
import com.jusfoun.bll.extend.split.gp.GprRoleDomainSplBll;
import com.jusfoun.bll.extend.split.gp.GprRoleModuleSplBll;
import com.jusfoun.bll.extend.unity.gp.GprDomainUserUntBll;
import com.jusfoun.bll.extend.unity.gp.GprRoleControlUntBll;
import com.jusfoun.bll.extend.unity.gp.GprRoleDomainUntBll;
import com.jusfoun.bll.extend.unity.gp.GprRoleInterfaceUntBll;
import com.jusfoun.bll.extend.unity.gp.GprRoleModuleUntBll;
import com.jusfoun.bll.extend.unity.gp.GprRolePageUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpRole;
import com.jusfoun.ent.extend.gp.GprDomainUser;
import com.jusfoun.ent.extend.gp.GprRoleDomain;
import com.jusfoun.ent.extend.gp.GprRoleModule;
import com.jusfoun.ent.parameter.gp.GpRoleParameter;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

	@Autowired
	@Qualifier("gprRoleDomainUntBll")
	protected GprRoleDomainUntBll gprRoleDomainUntBll;

	@Autowired
	@Qualifier("gprDomainUserUntBll")
	protected GprDomainUserUntBll gprDomainUserUntBll;

	@Autowired
	@Qualifier("gprRoleControlUntBll")
	protected GprRoleControlUntBll gprRoleControlUntBll;

	@Autowired
	@Qualifier("gprDomainUserSplBll")
	protected GprDomainUserSplBll gprDomainUserSplBll;

	@Autowired
	@Qualifier("gprRoleInterfaceUntBll")
	protected GprRoleInterfaceUntBll gprRoleInterfaceUntBll;

	@Autowired
	@Qualifier("gprRolePageUntBll")
	protected GprRolePageUntBll gprRolePageUntBll;

	@Autowired
	@Qualifier("gprRoleModuleSplBll")
	protected GprRoleModuleSplBll gprRoleModuleSplBll;

	@Autowired
	@Qualifier("gprRoleDomainSplBll")
	protected GprRoleDomainSplBll gprRoleDomainSplBll;

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		gprRoleModuleSplBll.deleteByRoleId(id);
		gprRoleDomainSplBll.deleteByRoleId(id);
		gprRolePageUntBll.deleteByRoleId(id);
		gprRoleControlUntBll.deleteByRoleId(id);
		gprRoleInterfaceUntBll.deleteByRoleId(id);

		ResultModel result = gpRoleUntBll.delete(id);
		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "GpRoleDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody GpRoleParameter.DeleteByIdList jsonData) {

		ResultModel result = gpRoleUntBll.deleteByIdList(jsonData.getIdList());
		return result;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = gpRoleUntBll.getModel(id);
		if (!result.getIsSuccess())
			return result;

		GpRole gpRole = (GpRole) result.getData();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT  group_concat(domain_id) domainIds  from gpr_role_domain where  role_id='").append(result.getObjectId()).append("'");
		map.put("Sql", selectBuffer.toString());
		ResultModel gprRoleDomainResult = gprRoleDomainUntBll.getListBySQL(map);
		List<Map<String, Object>> data = (List<Map<String, Object>>) gprRoleDomainResult.getData();
		if (data.get(0).get("domainIds") != null)
			gpRole.setDomainIds(data.get(0).get("domainIds").toString());

		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT  group_concat(module_id) moduleIds  from gpr_role_module where  role_id='").append(result.getObjectId()).append("'");
		map.put("Sql", selectBuffer.toString());
		ResultModel gprRoleModuleResult = gprRoleDomainUntBll.getListBySQL(map);
		data = (List<Map<String, Object>>) gprRoleModuleResult.getData();
		if (data.get(0).get("moduleIds") != null)
			gpRole.setModuleIds(data.get(0).get("moduleIds").toString());

		result.setData(gpRole);

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "GpRole") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody GpRole jsonData) {
		ResultModel result = gpRoleUntBll.update(jsonData);

		// 先删除再插入
		gprRoleModuleSplBll.deleteByRoleId(jsonData.getId());
		gprRoleDomainSplBll.deleteByRoleId(jsonData.getId());

		ArrayList<GprRoleModule> gprRoleModuleList = new ArrayList<GprRoleModule>();
		ArrayList<GprRoleDomain> gprRoleDomainList = new ArrayList<GprRoleDomain>();
		ArrayList<GprDomainUser> gprDomainUserList = new ArrayList<GprDomainUser>();

		//插入角色功能模块关系表
		if (StringUtils.isNotBlank(jsonData.getModuleIds())) {
			String[] moduleIdArray = jsonData.getModuleIds().split(",");
			for (String moduleId : moduleIdArray) {
				GprRoleModule gprRoleModule = new GprRoleModule();
				gprRoleModule.setRoleId(jsonData.getId());
				gprRoleModule.setModuleId(moduleId);
				gprRoleModule.setIsEnableCode(SymbolicConstant.DCODE_BOOLEAN_T);
				gprRoleModuleList.add(gprRoleModule);

			}
		}
		gprRoleModuleUntBll.add(gprRoleModuleList);
		
		//插入角色用户关系
		if (StringUtils.isNotBlank(jsonData.getDomainIds())) {
			String[] moduleIdArray = jsonData.getDomainIds().split(",");
			for (String domainId : moduleIdArray) {
				GprRoleDomain gprRoleDomain = new GprRoleDomain();
				gprRoleDomain.setRoleId(jsonData.getId());
				gprRoleDomain.setDomainId(domainId);
				gprRoleDomain.setIsEnableCode(SymbolicConstant.DCODE_BOOLEAN_T);
				gprRoleDomainList.add(gprRoleDomain);

			}
		}
		gprRoleDomainUntBll.add(gprRoleDomainList);

		//插入用户应用领域关系，先 获取这个角色下的用户和应用领域，再插入
		String sql = String.format(SymbolicConstant.SQL_SELECT_USER_DOMAIN_BY_ROLE, jsonData.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", sql);
		ResultModel resultModel = gprRoleDomainUntBll.getListBySQL(map);
		List<Map<String, Object>> domainIdList = (List<Map<String, Object>>) resultModel.getData();
		for (Map<String, Object> domainId : domainIdList) {
			GprDomainUser gprDomainUser = new GprDomainUser();
			gprDomainUser.setDomainId(domainId.get("domain_id").toString());
			gprDomainUser.setUserId(domainId.get("user_id").toString());
			gprDomainUserList.add(gprDomainUser);
		}

			gprDomainUserUntBll.add(gprDomainUserList);
		
		
		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "GpRoleUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody GpRoleParameter.UpdateList jsonData) {
		ResultModel result = gpRoleUntBll.updateList(jsonData);

		return result;
	}

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "GpRole") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody GpRole jsonData) {
		ResultModel result = gpRoleUntBll.add(jsonData);
		ArrayList<GprRoleModule> gprRoleModuleList = new ArrayList<GprRoleModule>();
		ArrayList<GprRoleDomain> gprRoleDomainList = new ArrayList<GprRoleDomain>();

		if (StringUtils.isNotBlank(jsonData.getModuleIds())) {
			String[] moduleIdArray = jsonData.getModuleIds().split(",");
			for (String moduleId : moduleIdArray) {
				GprRoleModule gprRoleModule = new GprRoleModule();
				gprRoleModule.setRoleId(jsonData.getId());
				gprRoleModule.setModuleId(moduleId);
				gprRoleModuleList.add(gprRoleModule);

			}
		}
		if (StringUtils.isNotBlank(jsonData.getDomainIds())) {
			String[] moduleIdArray = jsonData.getDomainIds().split(",");
			for (String domainId : moduleIdArray) {
				GprRoleDomain gprRoleDomain = new GprRoleDomain();
				gprRoleDomain.setRoleId(jsonData.getId());
				gprRoleDomain.setDomainId(domainId);
				gprRoleDomainList.add(gprRoleDomain);

			}
		}

		gprRoleModuleUntBll.add(gprRoleModuleList);
		gprRoleDomainUntBll.add(gprRoleDomainList);

		return result;
	}

	@RequestMapping(value = "/getRoleListByDomainId/{domainId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getRoleListByDomainId(@PathVariable("domainId") String domainId) {

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		group_concat(A.name)          roleNames                    ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		gp_role A                                                  ");
		selectBuffer.append("	INNER JOIN	gpr_role_domain B on A.id=B.role_id                ");
		selectBuffer.append("	WHERE                                                          ");
		selectBuffer.append("		B.domain_id = '" + domainId + "' order by A.name           ");
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
		StringBuffer selectBuffer = new StringBuffer(SymbolicConstant.SQL_SELECT_ROLE_GET_LIST);
	
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
					selectBuffer.append(" and B.domain_id ='").append(entityRelatedObject.getString("selectDomainId")).append("'");
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
