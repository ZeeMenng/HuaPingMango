package com.zee.app.extend.swagger.pi;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiChannelCountGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS栏目访问量计数表 对外接口，扩展自PiChannelCountGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piChannelCount")
public class PiChannelCountSwgApp extends PiChannelCountGenSwgApp {

	@ApiOperation(value = "栏目访问数量统计", notes = "栏目访问数量统计")
	@RequestMapping(value = "/getChannelCountByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannelCount() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)){
			return resultModel;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         "); 
		selectBuffer.append("		c.`id` AS id,                                           "); 
		selectBuffer.append("		c.`id` AS channel_id,                                           "); 
		selectBuffer.append("		e.`channel_name` AS `name`,                                            "); 
		selectBuffer.append("		t.`views` AS views,                                           "); 
		selectBuffer.append("		t.`views_month` AS  viewsMonth,                                           "); 
		selectBuffer.append("		t.`views_week` AS viewsWeek,                                            "); 
		selectBuffer.append("		t.`views_day` AS viewsDay                                           "); 
		selectBuffer.append("	FROM                                                           "); 
		selectBuffer.append("		pi_channel c                                               "); 
		selectBuffer.append("	LEFT JOIN pi_channel_ext e ON e.`channel_id`=c.`id`             "); 
		selectBuffer.append("	LEFT JOIN pi_channel_count t ON t.`channel_id`=c.`id`            "); 
		selectBuffer.append("   WHERE 1=1       											   ");
		
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("views") && StringUtils.isNotBlank(entityRelatedObject.getString("views")))
					selectBuffer.append(" and t.views like '%").append(entityRelatedObject.getString("views")).append("%'");
				if (entityRelatedObject.containsKey("viewsMonth") && StringUtils.isNotBlank(entityRelatedObject.getString("viewsMonth")))
					selectBuffer.append(" and t.views_month like '%").append(entityRelatedObject.getString("viewsMonth")).append("%'");
				if (entityRelatedObject.containsKey("viewsWeek") && StringUtils.isNotBlank(entityRelatedObject.getString("viewsWeek")))
					selectBuffer.append(" and t.views_week like '%").append(entityRelatedObject.getString("viewsWeek")).append("%'");
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
		resultModel = piChannelCountUntBll.getListBySQL(map);
		
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
        
		selectBuffer.append("	SELECT                                                         "); 
		selectBuffer.append("		c.id id, "); 
		selectBuffer.append("		c.id channel_id,                                           "); 
		selectBuffer.append("		e. channel_name name,                                           "); 
		selectBuffer.append("		t.views views,                                           "); 
		selectBuffer.append("		t.views_month viewsMonth,                                            "); 
		selectBuffer.append("		t.views_week viewsWeek,                                         "); 
		selectBuffer.append("		t.views_day viewsDay                                            "); 
		selectBuffer.append("	FROM                                                           "); 
		selectBuffer.append("		pi_channel c                                               "); 
		selectBuffer.append("	LEFT JOIN pi_channel_ext e ON e.`channel_id`=c.`id`             "); 
		selectBuffer.append("	LEFT JOIN pi_channel_count t ON t.`channel_id`=c.`id`             "); 
		selectBuffer.append("   WHERE 1=1       											   ");
		
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and c.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("views") && StringUtils.isNotBlank(entityRelatedObject.getString("views")))
					selectBuffer.append(" and t.views like '%").append(entityRelatedObject.getString("views")).append("%'");
				if (entityRelatedObject.containsKey("viewsMonth") && StringUtils.isNotBlank(entityRelatedObject.getString("viewsMonth")))
					selectBuffer.append(" and t.views_month like '%").append(entityRelatedObject.getString("viewsMonth")).append("%'");
				if (entityRelatedObject.containsKey("viewsWeek") && StringUtils.isNotBlank(entityRelatedObject.getString("viewsWeek")))
					selectBuffer.append(" and t.views_week like '%").append(entityRelatedObject.getString("viewsWeek")).append("%'");
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
					selectBuffer.append("c." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = piChannelCountUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "CMS栏目访问量计数表列表数据" + DateUtils.getCurrentDateStr() + ".xls";
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
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



