package com.zee.app.extend.swagger.pi;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiFriendlinkGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiFriendlink;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.BeanUtil;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/7/12 14:43:26
 * @description CMS友情链接 对外接口，扩展自PiFriendlinkGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piFriendlink")
public class PiFriendlinkSwgApp extends PiFriendlinkGenSwgApp {

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "PiFriendlink") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody PiFriendlink jsonData) {
		if (jsonData.getLogoResourcePath().contains(this.linkPath)) {
			jsonData.setLogoResourcePath(jsonData.getLogoResourcePath().substring(this.linkPath.length(), jsonData.getLogoResourcePath().length()));
		}
		ResultModel result = piFriendlinkUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = piFriendlinkUntBll.getModel(id);
		PiFriendlink piFriendlink = (PiFriendlink) result.getData();
		piFriendlink.setLogoResourcePath(this.linkPath + piFriendlink.getLogoResourcePath());
		result.setData(piFriendlink);
		return result;
	}

	@RequestMapping(value = "updateFriendlink", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateFriendlink() throws Exception {
		String strJson = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		ResultModel result = new ResultModel();
		String jsonData = new String(strJson);
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			PiFriendlink piFriendlink = (PiFriendlink) BeanUtil.map2Bean(jsonObject, PiFriendlink.class);
			if (piFriendlink.getLogoResourcePath().contains(this.linkPath)) {
				piFriendlink.setLogoResourcePath(piFriendlink.getLogoResourcePath().substring(this.linkPath.length(), piFriendlink.getLogoResourcePath().length()));
			}
			result = piFriendlinkUntBll.update(piFriendlink);
		}
		return result;

	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.domain_id domainId,A.name name,A.url url,A.logo_resource_id logoResourceId,CONCAT('" + this.linkPath + "',A.logo_resource_path) logoResourcePath,A.email email,A.remark remark,A.views views,A.is_enabled_code isEnabledCode,A.priority priority,A.add_time addTime,A.update_time updateTime  from pi_friendlink A inner join pi_friendlink B on A.id=B.id where 1=1 ");
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

				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("url") && StringUtils.isNotBlank(entityRelatedObject.getString("url")))
					selectBuffer.append(" and A.url like '%").append(entityRelatedObject.getString("url")).append("%'");
				if (entityRelatedObject.containsKey("logoResourcePath") && StringUtils.isNotBlank(entityRelatedObject.getString("logoResourcePath")))
					selectBuffer.append(" and A.logo_resource_path like '%").append(entityRelatedObject.getString("logoResourcePath")).append("%'");
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
					selectBuffer.append("A." + Tools.getCamelUnderline(orderColumnObject.getString("columnName")));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = piFriendlinkUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByDomain", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByDomain() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.domain_id domainId,A.name name,A.url url,A.logo_resource_id logoResourceId,CONCAT('" + this.linkPath + "',A.logo_resource_path) logoResourcePath,A.email email,A.remark remark,A.views views,A.is_enabled_code isEnabledCode,A.priority priority,A.add_time addTime,A.update_time updateTime  from pi_friendlink A inner join pi_friendlink B on A.id=B.id where 1=1 ");
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				String domainId = entityRelatedObject.getString("domainId");
				if (entityRelatedObject.containsKey("domainId") && StringUtils.isNotBlank(entityRelatedObject.getString("domainId")))
					selectBuffer.append(" and A.domain_id = '" + domainId + "'");
			}

			selectBuffer.append(" order by A.priority ");
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = piFriendlinkUntBll.getListBySQL(map);

		return resultModel;
	}
}
