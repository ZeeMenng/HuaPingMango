package com.zee.app.extend.swagger.pi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiAdvertisingGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiAdvertising;
import com.zee.ent.parameter.pi.PiAdvertisingParameter;
import com.zee.utl.BeanUtil;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/7/12 14:46:55
 * @description CMS广告表 对外接口，扩展自PiAdvertisingGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piAdvertising")
public class PiAdvertisingSwgApp extends PiAdvertisingGenSwgApp {

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "PiAdvertising") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody PiAdvertising jsonData) {
		if (StringUtils.isNotBlank(jsonData.getResourcePath()))
			jsonData.setResourcePath(jsonData.getResourcePath().replaceAll(linkPath, ""));
		ResultModel result = piAdvertisingUntBll.add(jsonData);
		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = piAdvertisingUntBll.getModel(id);
		PiAdvertising piAdvertising = (PiAdvertising) result.getData();
		if (StringUtils.isNotBlank(piAdvertising.getResouceId()))
			piAdvertising.setResourceIdArray(piAdvertising.getResouceId().split("\\|"));
		if (StringUtils.isNotBlank(piAdvertising.getResourcePath()))
			piAdvertising.setResourcePathArray(getResourcePathArray(piAdvertising.getResourcePath()));
		result.setData(piAdvertising);
		return result;
	}

	@RequestMapping(value = "/updateAdvertising", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateAdvertising() throws Exception {
		String strJson = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		ResultModel result = new ResultModel();
		String jsonData = new String(strJson);
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			PiAdvertising piAdvertising = (PiAdvertising) BeanUtil.map2Bean(jsonObject, PiAdvertising.class);
			if (piAdvertising.getResourcePath().contains(this.linkPath)) {
				piAdvertising.setResourcePath(piAdvertising.getResourcePath().replace(linkPath, ""));
			} else {
				piAdvertising.setResourcePath(piAdvertising.getResourcePath());
			}
			result = piAdvertisingUntBll.update(piAdvertising);
		}
		return result;

	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "PiAdvertisingGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody PiAdvertisingParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = piAdvertisingUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.serial_no serialNo,A.domain_id domainId,A.name name,A.title title,A.resouce_id resouceIds,CONCAT('" + this.linkPath + "',A.resource_path) resourcePaths,A.width width,A.height height,A.target_code targetCode,A.space_code spaceCode,A.category_code categoryCode,A.weight weight,A.display_count displayCount,A.click_count clickCount,A.start_time startTime,A.end_time endTime,A.remark remark,A.is_enabled_code isEnabledCode,A.add_time addTime,A.update_time updateTime  from pi_advertising A inner join pi_advertising B on A.id=B.id where 1=1 ");

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

				if (entityRelatedObject.containsKey("serialNo") && StringUtils.isNotBlank(entityRelatedObject.getString("serialNo")))
					selectBuffer.append(" and A.serial_no like '%").append(entityRelatedObject.getString("serialNo")).append("%'");
				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("title") && StringUtils.isNotBlank(entityRelatedObject.getString("title")))
					selectBuffer.append(" and A.title like '%").append(entityRelatedObject.getString("title")).append("%'");
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

		resultModel = piAdvertisingUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByCode", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByCode() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;


		String domainId = "";
		Byte spaceCode = 0;
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");

				if (entityRelatedObject.containsKey("domainId") && StringUtils.isNotBlank(entityRelatedObject.getString("domainId")))
					domainId = entityRelatedObject.getString("domainId");
				// selectBuffer.append(" and A.domain_id = '" +
				// entityRelatedObject.getString("domainId") + "'");
				if (entityRelatedObject.containsKey("spaceCode") && StringUtils.isNotBlank(entityRelatedObject.getString("spaceCode")))
					spaceCode = Byte.valueOf(entityRelatedObject.getString("spaceCode"));
				// selectBuffer.append(" and A.space_code='" +
				// entityRelatedObject.getString("spaceCode") + "' ");

			}

		}

		// map.put("Sql", selectBuffer.toString());

		// resultModel = piAdvertisingUntBll.getListBySQL(map);

		resultModel = piAdvertisingSplBll.getModelBySpace(domainId, spaceCode);

		PiAdvertising piAdvertising = (PiAdvertising) resultModel.getData();
		if (StringUtils.isNotBlank(piAdvertising.getResouceId()))
			piAdvertising.setResourceIdArray(piAdvertising.getResouceId().split("\\|"));
		if (StringUtils.isNotBlank(piAdvertising.getResourcePath()))
			piAdvertising.setResourcePathArray(getResourcePathArray(piAdvertising.getResourcePath()));
		resultModel.setData(piAdvertising);

		return resultModel;
	}

	/**
	 * 广告点击次数
	 * @param jsonData
	 * @return
	 */
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "PiAdvertising") })
	@RequestMapping(value = "/updateClickCount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateClickCount(@RequestBody PiAdvertising jsonData) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select A.id id, ");
		selectBuffer.append(" A.click_count clickCount ");
		selectBuffer.append(" from pi_advertising A ");
		selectBuffer.append(" where A.id='" + jsonData.getId() + "' ");
		map.put("Sql", selectBuffer.toString());
		resultModel = piAdvertisingUntBll.getListBySQL(map);
		List<Map<String, Object>> list = (List<Map<String, Object>>) resultModel.getData();
		Long clickCount = (Long) list.get(0).get("clickCount");
		jsonData.setClickCount(clickCount + 1);
		ResultModel result = piAdvertisingUntBll.update(jsonData);

		return result;
	}
}
