package com.zee.app.extend.swagger.da;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaSentimentSourceGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaSentimentSource;
import com.zee.set.enumer.StatusEnum;
import com.zee.set.symbolic.CustomSymbolic;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-7-18 16:59:25
 * @description  对外接口，扩展自DaSentimentSourceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daSentimentSource")
public class  DaSentimentSourceSwgApp extends  DaSentimentSourceGenSwgApp {

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaSentimentSource") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaSentimentSource jsonData) {
		jsonData.setAddUserId(this.getCurrentUser().getId());
		ResultModel result = daSentimentSourceUntBll.add(jsonData);
		return result;
	}
	
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaSentimentSource") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaSentimentSource jsonData) {
		jsonData.setAddUserId(this.getCurrentUser().getId());
		ResultModel result = daSentimentSourceUntBll.update(jsonData);

		return result;
	}
	
	@ApiOperation(value = "修改状态（开启、关闭）", notes = "修改状态（开启、关闭）")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaSentimentSource") })
	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateStatus(@RequestBody DaSentimentSource jsonData) {
		ResultModel result = daSentimentSourceUntBll.getModel(jsonData.getId());
		DaSentimentSource bean = (DaSentimentSource) result.getData();
		bean.setAddUserId(this.getCurrentUser().getId());
		bean.setStatusCode(jsonData.getStatusCode());
		if(StatusEnum.KAIQI.getCode() == jsonData.getStatusCode()){
			bean.setStatusText(StatusEnum.KAIQI.getText());
		}else if(StatusEnum.GUANBI.getCode() == jsonData.getStatusCode()){
			bean.setStatusText(StatusEnum.GUANBI.getText());
		}
		ResultModel resultModel = daSentimentSourceUntBll.update(bean);
		return resultModel;
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
		selectBuffer.append("	SELECT                                         ");
		selectBuffer.append("		A.id id,                                   ");
		selectBuffer.append("		A.thread_starter threadStarter,            ");
		selectBuffer.append("		A.media_type_code mediaTypeCode,           ");
		selectBuffer.append("		A.media_type_text mediaTypeText,           ");
		selectBuffer.append("		A.website website,                         ");
		selectBuffer.append("		A.remark remark,                           ");
		selectBuffer.append("		A.add_user_id addUserId,                   ");
		selectBuffer.append("		A.add_time addTime,                        ");
		selectBuffer.append("		A.status_code statusCode,                  ");
		selectBuffer.append("		A.status_text statusText                   ");
		selectBuffer.append("	FROM                                           ");
		selectBuffer.append("		da_sentiment_source A                      ");
		selectBuffer.append("	WHERE 1=1                                      ");
		
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
                
				if (entityRelatedObject.containsKey("threadStarter") && StringUtils.isNotBlank(entityRelatedObject.getString("threadStarter")))
					selectBuffer.append(" and A.thread_starter like '%").append(entityRelatedObject.getString("threadStarter")).append("%'");
				if (entityRelatedObject.containsKey("mediaTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("mediaTypeCode")))
					selectBuffer.append(" and A.media_type_code like '%").append(entityRelatedObject.getString("mediaTypeCode")).append("%'");
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

		resultModel = daSentimentSourceUntBll.getListBySQL(map);

		return resultModel;
	}
	
}



