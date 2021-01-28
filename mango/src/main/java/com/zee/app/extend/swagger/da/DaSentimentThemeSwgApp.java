package com.zee.app.extend.swagger.da;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaSentimentThemeGenSwgApp;
import com.zee.bll.extend.unity.da.DaSentimentArticleUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaSentimentTheme;
import com.zee.set.enumer.sentimentEnum;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.TimesView;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:46
 * @description  对外接口，扩展自DaSentimentThemeGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daSentimentTheme")
public class  DaSentimentThemeSwgApp extends  DaSentimentThemeGenSwgApp {
	
	@Autowired
	@Qualifier("daSentimentArticleUntBll")
	protected DaSentimentArticleUntBll daSentimentArticleUntBll;
	
	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaSentimentTheme") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaSentimentTheme jsonData) {
		jsonData.setAddUserId(this.getCurrentUser().getId());
		ResultModel result = daSentimentThemeUntBll.add(jsonData);

		return result;
	}
	
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaSentimentTheme") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaSentimentTheme jsonData) {
		jsonData.setAddUserId(this.getCurrentUser().getId());
		ResultModel result = daSentimentThemeUntBll.update(jsonData);

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
		selectBuffer.append("	SELECT                                                          ");
		selectBuffer.append("		A.id id,                                                    ");
		selectBuffer.append("		A.theme_name themeName,                                     ");
		selectBuffer.append("		A.key_name keyName,                                         ");
		selectBuffer.append("		A.exclude_name excludeName,                                 ");
		selectBuffer.append("		A.rule_id ruleId,                                           ");
		selectBuffer.append("		A.remark remark,                                            ");
		selectBuffer.append("		A.add_user_id addUserId,                                    ");
		selectBuffer.append("		A.add_time addTime,                                         ");
		selectBuffer.append("		A.status_code statusCode,                                   ");
		selectBuffer.append("		A.status_text statusText                                    ");
		selectBuffer.append("	FROM                                                            ");
		selectBuffer.append("		da_sentiment_theme A                                        ");
		selectBuffer.append("	INNER JOIN da_sentiment_theme B ON A.id = B.id                  ");
		selectBuffer.append("	WHERE                                                           ");
		selectBuffer.append("		1 = 1                                                       ");
		
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
                
				if (entityRelatedObject.containsKey("themeName") && StringUtils.isNotBlank(entityRelatedObject.getString("themeName")))
					selectBuffer.append(" and A.theme_name like '%").append(entityRelatedObject.getString("themeName")).append("%'");
				if (entityRelatedObject.containsKey("keyName") && StringUtils.isNotBlank(entityRelatedObject.getString("keyName")))
					selectBuffer.append(" and A.key_name like '%").append(entityRelatedObject.getString("keyName")).append("%'");
				if (entityRelatedObject.containsKey("excludeName") && StringUtils.isNotBlank(entityRelatedObject.getString("excludeName")))
					selectBuffer.append(" and A.exclude_name like '%").append(entityRelatedObject.getString("excludeName")).append("%'");
				if (entityRelatedObject.containsKey("statusCode") && StringUtils.isNotBlank(entityRelatedObject.getString("statusCode")))
					selectBuffer.append(" and A.status_code = '").append(entityRelatedObject.getString("statusCode")).append("'");
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
		resultModel = daSentimentThemeUntBll.getListBySQL(map);
		
		Map<String, String> timeMap = new HashMap<String, String>();
		timeMap.put("viewName", "hour");//视图名，年year，月month，日date，小时hour（默认为年）
		timeMap.put("hasCurrent", "true");
		timeMap.put("pastNum", "23");
		List<String> timesList = TimesView.getTimesView(timeMap);
		
		//查询24小时主题数以及负面数
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		for (Map<String, Object> map2 : modelList) {
			Map<String, Object> artMap = new HashMap<String, Object>();
			StringBuffer artBuffer = new StringBuffer();
			artBuffer.append("		SELECT                                                                           "); 
			artBuffer.append("			A.id id,                                                                     "); 
			artBuffer.append("			A.article_name articleName,                                                  "); 
			artBuffer.append("			A.sentiment_type_code sentimentTypeCode,                                     "); 
			artBuffer.append("			A.sentiment_type_text sentimentTypeText,                                     "); 
			artBuffer.append("			A.publish_time publishTime                                                   "); 
			artBuffer.append("		FROM                                                                             "); 
			artBuffer.append("			da_sentiment_article A                                                       "); 
			artBuffer.append("		WHERE                                                                            "); 
			artBuffer.append("			A.theme_id = '"+map2.get("id").toString()+"'                                 "); 
			artBuffer.append("		AND DATE_FORMAT(A.publish_time, '%Y-%m-%d %H') >= '"+timesList.get(0)+"'         "); 
			artMap.put("Sql", artBuffer.toString());
			ResultModel artModel = daSentimentArticleUntBll.getListBySQL(artMap);
			List<Map<String, Object>> artList = (List<Map<String, Object>>) artModel.getData();
			int all24Num = 0;
			int negative24Num = 0;
			for (Map<String, Object> map3 : artList) {
				if(sentimentEnum.FUMIAN.getCode() == Byte.valueOf(map3.get("sentimentTypeCode").toString())){
					negative24Num ++;
				}
				all24Num ++;
			}
			map2.put("all24Num", all24Num);
			map2.put("negative24Num", negative24Num);
		}

		return resultModel;
	}
	
}



