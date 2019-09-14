package com.jusfoun.app.extend.swagger.gp;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GpTokenGenSwgApp;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description token信息。 对外接口，扩展自GpTokenGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpToken")
public class GpTokenSwgApp extends GpTokenGenSwgApp {

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.login_log_id loginLogId,A.domain_id domainId,A.user_id userId,A.user_name userName,A.access_token accessToken,A.a_dead_time aDeadTime,A.refresh_token refreshToken,A.r_dead_time rDeadTime,A.secret secret,A.remark remark,A.add_time addTime,A.update_time updateTime  from gp_token A inner join gp_token B on A.id=B.id where 1=1 ");

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

				if (entityRelatedObject.containsKey("userName") && StringUtils.isNotBlank(entityRelatedObject.getString("userName")))
					selectBuffer.append(" and A.user_name like '%").append(entityRelatedObject.getString("userName")).append("%'");
				if (entityRelatedObject.containsKey("accessToken") && StringUtils.isNotBlank(entityRelatedObject.getString("accessToken")))
					selectBuffer.append(" and A.access_token like '%").append(entityRelatedObject.getString("accessToken")).append("%'");
				if (entityRelatedObject.containsKey("aDeadTime") && StringUtils.isNotBlank(entityRelatedObject.getString("aDeadTime")))
					selectBuffer.append(" and A.a_dead_time like '%").append(entityRelatedObject.getString("aDeadTime")).append("%'");
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
					selectBuffer.append("A.").append(Tools.getCamelUnderline(orderColumnObject.getString("columnName")));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = gpTokenUntBll.getListBySQL(map);

		return resultModel;
	}

}
