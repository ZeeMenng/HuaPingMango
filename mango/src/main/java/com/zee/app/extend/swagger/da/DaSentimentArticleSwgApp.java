package com.zee.app.extend.swagger.da;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.math.BigDecimal;
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

import com.zee.app.generate.swagger.da.DaSentimentArticleGenSwgApp;
import com.zee.bll.extend.unity.da.DaSentimentContentUntBll;
import com.zee.bll.extend.unity.da.DaSentimentThemeUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaSentimentArticle;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.MathUtil;
import com.zee.utl.TimesView;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:46
 * @description  对外接口，扩展自DaSentimentArticleGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daSentimentArticle")
public class  DaSentimentArticleSwgApp extends  DaSentimentArticleGenSwgApp {
	
	@Autowired
	@Qualifier("daSentimentContentUntBll")
	protected DaSentimentContentUntBll daSentimentContentUntBll;
	
	@Autowired
	@Qualifier("daSentimentThemeUntBll")
	protected DaSentimentThemeUntBll daSentimentThemeUntBll;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daSentimentArticleUntBll.getModel(id);

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT replace(A.txt,'\n','<br/>') txt FROM da_sentiment_content A WHERE A.article_id = '"+id+"'      ");
		map.put("Sql", selectBuffer.toString());
		ResultModel rstContent = daSentimentContentUntBll.getListBySQL(map);
		List<Map<String, Object>> contentList = (List<Map<String, Object>>) rstContent.getData();
		
		DaSentimentArticle daSentimentArticle = (DaSentimentArticle) result.getData();
		daSentimentArticle.getDaSentimentContent().setTxt(contentList.get(0).get("txt").toString());
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daSentimentArticleUntBll.getModel(id);
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                    ");
		selectBuffer.append("		B.id id,                                                              ");
		selectBuffer.append("		B.theme_name themeName,                                               ");
		selectBuffer.append("		B.key_name keyName                                                    ");
		selectBuffer.append("	FROM                                                                      ");
		selectBuffer.append("		da_sentiment_article A                                                ");
		selectBuffer.append("	INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                      ");
		selectBuffer.append("	WHERE A.id = '"+id+"'                                                     ");
		
		map.put("Sql", selectBuffer.toString());
		ResultModel rstTheme = daSentimentThemeUntBll.getListBySQL(map);
		List<Map<String, Object>> themeList = (List<Map<String, Object>>) rstTheme.getData();

		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT A.id id, replace(A.txt,'\n','<br/>') txt FROM da_sentiment_content A WHERE A.article_id = '"+id+"'      ");
		map.put("Sql", selectBuffer.toString());
		ResultModel rstContent = daSentimentContentUntBll.getListBySQL(map);
		List<Map<String, Object>> contentList = (List<Map<String, Object>>) rstContent.getData();
		
		DaSentimentArticle daSentimentArticle = (DaSentimentArticle) result.getData();
		daSentimentArticle.setThemeName(themeList.get(0).get("themeName").toString());
		daSentimentArticle.setKeyName(themeList.get(0).get("keyName").toString());
		daSentimentArticle.getDaSentimentContent().setId(contentList.get(0).get("id").toString());
		daSentimentArticle.getDaSentimentContent().setTxt(contentList.get(0).get("txt").toString());
		
		return result;
	}
	
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaSentimentArticle") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaSentimentArticle jsonData) {
		jsonData.setUpdateUserId(this.getCurrentUser().getId());
		ResultModel result = daSentimentArticleUntBll.update(jsonData);
		if(result.getIsSuccess()){
			daSentimentContentUntBll.update(jsonData.getDaSentimentContent());
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
		
		String timeTypeCode = "";//时间纬度code
		String startTime = "";//自定义时间，开始时间
		String endTime = "";//自定义时间，开始时间
		
		String mediaTypeCode = "";//媒体类型
		String themeName = "";//主题名称
		String keyName = "";//关键词，逗号分割
		String sentimentTypeCode = "";//情感类型code
		
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			
			if (entityRelatedObject.containsKey("timeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("timeTypeCode")))
				timeTypeCode = entityRelatedObject.getString("timeTypeCode");
			if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
				startTime = entityRelatedObject.getString("startTime");
			if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
				endTime = entityRelatedObject.getString("endTime");
			if (entityRelatedObject.containsKey("mediaTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("mediaTypeCode")))
				mediaTypeCode = entityRelatedObject.getString("mediaTypeCode");
			if (entityRelatedObject.containsKey("themeName") && StringUtils.isNotBlank(entityRelatedObject.getString("themeName")))
				themeName = entityRelatedObject.getString("themeName");
			if (entityRelatedObject.containsKey("keyName") && StringUtils.isNotBlank(entityRelatedObject.getString("keyName")))
				keyName = entityRelatedObject.getString("keyName");
			if (entityRelatedObject.containsKey("sentimentTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("sentimentTypeCode")))
				sentimentTypeCode = entityRelatedObject.getString("sentimentTypeCode");
		}
        
        Map<String, String> dealTime = this.dealTime(timeTypeCode, startTime, endTime);
        String dateFormat = dealTime.get("dateFormat");
        startTime = dealTime.get("startTime");
        endTime = dealTime.get("endTime");
        

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                  ");
		selectBuffer.append("		A.id id,                                                            ");
		selectBuffer.append("		A.theme_id themeId,                                                 ");
		selectBuffer.append("		B.theme_name themeName,                                             ");
		selectBuffer.append("		A.article_name articleName,                                         ");
		selectBuffer.append("		A.publish_time publishTime,                                         ");
		selectBuffer.append("		A.article_txt_id articleTxtId,                                      ");
		selectBuffer.append("		A.thread_starter threadStarter,                                     ");
		selectBuffer.append("		A.url url,                                                          ");
		selectBuffer.append("		A.media_type_code mediaTypeCode,                                    ");
		selectBuffer.append("		A.media_type_text mediaTypeText,                                    ");
		selectBuffer.append("		A.sentiment_type_code sentimentTypeCode,                            ");
		selectBuffer.append("		A.sentiment_type_text sentimentTypeText,                            ");
		selectBuffer.append("		A.view_count viewCount,                                             ");
		selectBuffer.append("		A.datasource_code datasourceCode,                                   ");
		selectBuffer.append("		A.datasource_text datasourceText,                                   ");
		selectBuffer.append("		A.serial_number serialNumber,                                       ");
		selectBuffer.append("		A.remark remark,                                                    ");
		selectBuffer.append("		A.update_status_code updateStatusCode,                              ");
		selectBuffer.append("		A.add_time addTime,                                                 ");
		selectBuffer.append("		B.key_name keyName,                                                 ");
		selectBuffer.append("		replace(C.txt,'\n','<br/>') txt                                     ");
		selectBuffer.append("	FROM                                                                    ");
		selectBuffer.append("		da_sentiment_article A                                              ");
		selectBuffer.append("	INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                    ");
		selectBuffer.append("	INNER JOIN da_sentiment_content C ON C.article_id = A.id                ");
		selectBuffer.append("	WHERE                                                                   ");
		selectBuffer.append("		1 = 1                                                               ");
		if(StringUtils.isNotBlank(mediaTypeCode)){
			selectBuffer.append("	AND A.media_type_code = '"+mediaTypeCode+"'                             ");
		}
		if(StringUtils.isNotBlank(themeName)){
			selectBuffer.append("	AND B.theme_name LIKE '%"+themeName+"%'                                 ");
		}
		if(StringUtils.isNotBlank(keyName)){
			selectBuffer.append("	AND B.key_name LIKE '%"+keyName+"%'                                     ");
		}
		if(StringUtils.isNotBlank(sentimentTypeCode)){
			selectBuffer.append("	AND A.sentiment_type_code = '"+sentimentTypeCode+"'                     ");
		}
		if(StringUtils.isNotBlank(startTime)){
			selectBuffer.append("   AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') >= '"+startTime+"'    ");
		}
		if(StringUtils.isNotBlank(endTime)){
			selectBuffer.append("   AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') <= '"+endTime+"'      ");
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
        if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
        
		map.put("Sql", selectBuffer.toString());
		resultModel = daSentimentArticleUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "情感分析", notes = "情感分析")
	@RequestMapping(value = "/analyze", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel analyze() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String themeId = "";//主题id
		String timeTypeCode = "";//时间纬度code
		
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				
				if (entityRelatedObject.containsKey("themeId") && StringUtils.isNotBlank(entityRelatedObject.getString("themeId")))
					themeId = entityRelatedObject.getString("themeId");
				if (entityRelatedObject.containsKey("timeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("timeTypeCode")))
					timeTypeCode = entityRelatedObject.getString("timeTypeCode");
				
			}
		}

        Map<String, List<Map<String, Object>>> dataMap = new HashMap<String, List<Map<String, Object>>>();
        for(int i=1;i<=3;i++){//1负面，2中性，3正面
        	StringBuffer selectBuffer = new StringBuffer();
    		selectBuffer.append("	SELECT                                                                                           ");
    		selectBuffer.append("	"+i+" sentiment,                                                                                 ");
    		selectBuffer.append("		V.times time,                                                                                ");
    		selectBuffer.append("		CAST(COUNT(A.id) AS CHAR) num                                                                ");
    		selectBuffer.append("	FROM                                                                                             ");
    		selectBuffer.append("		da_sentiment_article A                                                                       ");
    		selectBuffer.append("	INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                                             ");
    		selectBuffer.append("	AND B.id = '"+themeId+"'                                                				         ");
    		selectBuffer.append("	AND A.sentiment_type_code = "+i+"                                                                ");
    		if("5".equals(timeTypeCode)){//1：年，2：季度，3：月，4：周，5：日，6：实时
    			selectBuffer.append("	RIGHT JOIN past_24_hour_view V ON DATE_FORMAT(A.publish_time, '%Y-%m-%d %H') = V.times       ");
    		}else if("4".equals(timeTypeCode)){
    			selectBuffer.append("	RIGHT JOIN past_1_week_view V ON DATE_FORMAT(A.publish_time, '%Y-%m-%d') = V.times           ");
    		}else if("3".equals(timeTypeCode)){
    			selectBuffer.append("	RIGHT JOIN past_1_month_view V ON DATE_FORMAT(A.publish_time, '%Y-%m-%d') = V.times          ");
    		}
    		selectBuffer.append("	GROUP BY V.times                                                                                 ");
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("Sql", selectBuffer.toString());
    		resultModel = daSentimentArticleUntBll.getListBySQL(map);
    		dataMap.put(String.valueOf(i), (List<Map<String, Object>>)resultModel.getData());
        }
        
        //封装X轴时间
        Map<String, List<String>> xMap = new HashMap<String, List<String>>();
        List<String> timeList = new ArrayList<String>();
        for (Map<String, Object> map :  dataMap.get("1")) {
        	timeList.add(map.get("time").toString());
		}
        xMap.put("times", timeList);
        
        //封装Y轴数量
        Map<String, List<String>> yMap = new HashMap<String, List<String>>();
        for(int i=1;i<=3;i++){
        	List<String> numList = new ArrayList<String>();
        	for (Map<String, Object> map :  dataMap.get(String.valueOf(i))) {
        		numList.add(map.get("num").toString());
    		}
        	yMap.put("sentimentTypeCode"+String.valueOf(i), numList);
        }
		
        List<Object> rt = new ArrayList<Object>();
        rt.add(xMap);
        rt.add(yMap);
        resultModel.setData(rt);
        
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "情感分布", notes = "情感分布")
	@RequestMapping(value = "/distribute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel distribute() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String themeId = "";//主题id
		String timeTypeCode = "";//时间纬度code
		String startTime = "";//自定义时间，开始时间
		String endTime = "";//自定义时间，开始时间
		
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				
				if (entityRelatedObject.containsKey("themeId") && StringUtils.isNotBlank(entityRelatedObject.getString("themeId")))
					themeId = entityRelatedObject.getString("themeId");
				if (entityRelatedObject.containsKey("timeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("timeTypeCode")))
					timeTypeCode = entityRelatedObject.getString("timeTypeCode");
				if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
					startTime = entityRelatedObject.getString("startTime");
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
					endTime = entityRelatedObject.getString("endTime");
				
			}
		}
        
        Map<String, String> dealTime = this.dealTime(timeTypeCode, startTime, endTime);
        String dateFormat = dealTime.get("dateFormat");
        startTime = dealTime.get("startTime");
        endTime = dealTime.get("endTime");
        
    	StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("    	SELECT                                                                                            ");
		selectBuffer.append("    		CAST(COUNT(A.sentiment_type_code) AS CHAR) num,                                               ");
		selectBuffer.append("    		A.sentiment_type_code sentimentTypeCode                                                       ");
		selectBuffer.append("    	FROM                                                                                              ");
		selectBuffer.append("    		da_sentiment_article A                                                                        ");
		selectBuffer.append("    	INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                                              ");
		selectBuffer.append("    	AND B.id = '"+themeId+"'                                                     					  ");
		selectBuffer.append("    	AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') >= '"+startTime+"'                              ");
		selectBuffer.append("    	AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') <= '"+endTime+"'                                ");
		selectBuffer.append("    	GROUP BY A.sentiment_type_code                                                                    ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());
		resultModel = daSentimentArticleUntBll.getListBySQL(map);
		List<Map<String, Object>> list = (List<Map<String, Object>>)resultModel.getData();
		Map<String, String> rmap = new HashMap<String, String>();
		rmap.put("sentimentTypeCode1", "0");
		rmap.put("sentimentTypeCode2", "0");
		rmap.put("sentimentTypeCode3", "0");
		Integer count = 0;
		for (Map<String, Object> lmap : list) {
			String num = lmap.get("num").toString();
			count += Integer.valueOf(num);
			switch (lmap.get("sentimentTypeCode").toString()) {
			case "1":
				rmap.put("sentimentTypeCode1", num);
				break;
			case "2":
				rmap.put("sentimentTypeCode2", num);
				break;
			case "3":
				rmap.put("sentimentTypeCode3", num);
				break;
			}
		}
		
		rmap.put("sentimentTypeCode1", MathUtil.getPercent(new BigDecimal(rmap.get("sentimentTypeCode1")), new BigDecimal(count), "0%"));
		rmap.put("sentimentTypeCode2", MathUtil.getPercent(new BigDecimal(rmap.get("sentimentTypeCode2")), new BigDecimal(count), "0%"));
		rmap.put("sentimentTypeCode3", MathUtil.getPercent(new BigDecimal(rmap.get("sentimentTypeCode3")), new BigDecimal(count), "0%"));
		resultModel.setData(rmap);
		return resultModel;
	}
	
	@ApiOperation(value = "信息媒体来源", notes = "信息媒体来源")
	@RequestMapping(value = "/newsFromMedia", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel newsFromMedia() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String themeId = "";//主题id
		String timeTypeCode = "";//时间纬度code
		String startTime = "";//自定义时间，开始时间
		String endTime = "";//自定义时间，开始时间
		String sentimentTypeCode = "";//情感类型
		
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				
				if (entityRelatedObject.containsKey("themeId") && StringUtils.isNotBlank(entityRelatedObject.getString("themeId")))
					themeId = entityRelatedObject.getString("themeId");
				if (entityRelatedObject.containsKey("timeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("timeTypeCode")))
					timeTypeCode = entityRelatedObject.getString("timeTypeCode");
				if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
					startTime = entityRelatedObject.getString("startTime");
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
					endTime = entityRelatedObject.getString("endTime");
				if (entityRelatedObject.containsKey("sentimentTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("sentimentTypeCode")))
					sentimentTypeCode = entityRelatedObject.getString("sentimentTypeCode");
				
			}
		}
        
        Map<String, String> dealTime = this.dealTime(timeTypeCode, startTime, endTime);
        String dateFormat = dealTime.get("dateFormat");
        startTime = dealTime.get("startTime");
        endTime = dealTime.get("endTime");

    	StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                           ");
		selectBuffer.append("		A.thread_starter threadStarter,                                              ");
		selectBuffer.append("		A.sentiment_type_code sentimentTypeCode,                                     ");
		selectBuffer.append("		A.sentiment_type_text sentimentTypeText,                                     ");
		selectBuffer.append("		COUNT(A.thread_starter) num                                                  ");
		selectBuffer.append("	FROM                                                                             ");
		selectBuffer.append("		da_sentiment_article A                                                       ");
		selectBuffer.append("	INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                             ");
		selectBuffer.append("	AND B.id = '"+themeId+"'                                                         ");
		selectBuffer.append("	AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') >= '"+startTime+"'             ");
		selectBuffer.append("	AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') <= '"+endTime+"'               ");
		if(StringUtils.isNotBlank(sentimentTypeCode)){
			selectBuffer.append("	AND A.sentiment_type_code = "+sentimentTypeCode+"                            ");
		}
		selectBuffer.append("	GROUP BY A.thread_starter                                                        ");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());
		resultModel = daSentimentArticleUntBll.getListBySQL(map);
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "媒体类型覆盖", notes = "媒体类型覆盖")
	@RequestMapping(value = "/fromMediaType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel fromMediaType() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String themeId = "";//主题id
		String timeTypeCode = "";//时间纬度code
		String startTime = "";//自定义时间，开始时间
		String endTime = "";//自定义时间，开始时间
		
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				
				if (entityRelatedObject.containsKey("themeId") && StringUtils.isNotBlank(entityRelatedObject.getString("themeId")))
					themeId = entityRelatedObject.getString("themeId");
				if (entityRelatedObject.containsKey("timeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("timeTypeCode")))
					timeTypeCode = entityRelatedObject.getString("timeTypeCode");
				if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
					startTime = entityRelatedObject.getString("startTime");
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
					endTime = entityRelatedObject.getString("endTime");
				
			}
		}
        
        Map<String, String> dealTime = this.dealTime(timeTypeCode, startTime, endTime);
        String dateFormat = dealTime.get("dateFormat");
        startTime = dealTime.get("startTime");
        endTime = dealTime.get("endTime");

    	StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                  ");
		selectBuffer.append("		A.media_type_code mediaTypeCode,                                    ");
		selectBuffer.append("		A.media_type_text mediaTypeText,                                    ");
		selectBuffer.append("		COUNT(A.id) num                                                     ");
		selectBuffer.append("	FROM                                                                    ");
		selectBuffer.append("		da_sentiment_article A                                              ");
		selectBuffer.append("	INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                    ");
		selectBuffer.append("	AND B.id = '"+themeId+"'                                                ");
		selectBuffer.append("	AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') >= '"+startTime+"'    ");
		selectBuffer.append("	AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') <= '"+endTime+"'      ");
		selectBuffer.append("	GROUP BY                                                                ");
		selectBuffer.append("		A.media_type_code                                                   ");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", selectBuffer.toString());
		resultModel = daSentimentArticleUntBll.getListBySQL(map);
		List<Map<String, Object>> list = (List<Map<String, Object>>)resultModel.getData();
		Integer count = 0;
		for (Map<String, Object> lmap : list) {
			String num = lmap.get("num").toString();
			count += Integer.valueOf(num);
		}
		for (Map<String, Object> lmap : list) {
			String num = lmap.get("num").toString();
			lmap.put("num", MathUtil.getPercent(new BigDecimal(num), new BigDecimal(count), "0%"));
		}
		resultModel.setData(list);
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "媒体来源排行", notes = "媒体类型覆盖")
	@RequestMapping(value = "/fromMediaRank", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel fromMediaRank() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String themeId = "";//主题id
		String timeTypeCode = "";//时间纬度code
		String startTime = "";//自定义时间，开始时间
		String endTime = "";//自定义时间，开始时间
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			
			if (entityRelatedObject.containsKey("themeId") && StringUtils.isNotBlank(entityRelatedObject.getString("themeId")))
				themeId = entityRelatedObject.getString("themeId");
			if (entityRelatedObject.containsKey("timeTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("timeTypeCode")))
				timeTypeCode = entityRelatedObject.getString("timeTypeCode");
			if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
				startTime = entityRelatedObject.getString("startTime");
			if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
				endTime = entityRelatedObject.getString("endTime");
			
		}
		
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}
        
        Map<String, String> dealTime = this.dealTime(timeTypeCode, startTime, endTime);
        String dateFormat = dealTime.get("dateFormat");
        startTime = dealTime.get("startTime");
        endTime = dealTime.get("endTime");

    	StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("		SELECT                                                               ");
		selectBuffer.append("			A.thread_starter threadStarter,                                  ");
		selectBuffer.append("			COUNT(A.thread_starter) num                                      ");
		selectBuffer.append("		FROM                                                                 ");
		selectBuffer.append("			da_sentiment_article A                                           ");
		selectBuffer.append("		INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                 ");
		selectBuffer.append("		AND B.id = '"+themeId+"'                                             ");
		selectBuffer.append("		AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') >= '"+startTime+"' ");
		selectBuffer.append("		AND DATE_FORMAT(A.publish_time, '"+dateFormat+"') <= '"+endTime+"'   ");
		selectBuffer.append("		GROUP BY A.thread_starter                                            ");
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
		map.put("Sql", selectBuffer.toString());
		resultModel = daSentimentArticleUntBll.getListBySQL(map);
		List<Map<String, Object>> list = (List<Map<String, Object>>)resultModel.getData();
		List<Integer> numList = new ArrayList<Integer>();
		for (Map<String, Object> map2 : list) {
			numList.add(Integer.valueOf(map2.get("num").toString()));
		}
		Integer max = MathUtil.getMax(numList);
		Map<String, Object> rmap = new HashMap<String, Object>();
		rmap.put("max", max);
		rmap.put("list", list);
		resultModel.setData(rmap);
		return resultModel;
	}
	
	@ApiOperation(value = "最新舆情（1：负面2：中性，3：正面）", notes = "最新舆情（1：负面2：中性，3：正面）")
	@RequestMapping(value = "/news", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel news() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String themeId = "";//主题id
		String sentimentTypeCode = "";//情感类型
		
		Map<String, Object> map = new HashMap<String, Object>();
		JSONObject jsonObject = JSONObject.fromObject(jsonData);

		if (jsonObject.containsKey("entityRelated")) {
			JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
			
			if (entityRelatedObject.containsKey("themeId") && StringUtils.isNotBlank(entityRelatedObject.getString("themeId")))
				themeId = entityRelatedObject.getString("themeId");
			if (entityRelatedObject.containsKey("sentimentTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("sentimentTypeCode")))
				sentimentTypeCode = entityRelatedObject.getString("sentimentTypeCode");
			
		}
		
		if (jsonObject.containsKey("page")) {
			JSONObject pageObject = jsonObject.getJSONObject("page");
			map.put("Page", pageObject);
		}

    	StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                 ");
		selectBuffer.append("		A.article_name articleName,                                        ");
		selectBuffer.append("		A.sentiment_type_code sentimentTypeCode,                           ");
		selectBuffer.append("		A.sentiment_type_text sentimentTypeText,                           ");
		selectBuffer.append("		A.url url,                                                         ");
		selectBuffer.append("		A.publish_time publishTime,                                        ");
		selectBuffer.append("		replace(C.txt,'\n','<br/>') txt,                                   ");
		selectBuffer.append("		A.thread_starter threadStarter,									   ");
		selectBuffer.append("		A.view_count viewCount                                             ");
		selectBuffer.append("	FROM                                                                   ");
		selectBuffer.append("		da_sentiment_article A                                             ");
		selectBuffer.append("	INNER JOIN da_sentiment_theme B ON A.theme_id = B.id                   ");
		selectBuffer.append("	INNER JOIN da_sentiment_content C ON C.article_id = A.id               ");
		selectBuffer.append("	AND B.id = '"+themeId+"'                                               ");
		if(StringUtils.isNotBlank(sentimentTypeCode)){
			selectBuffer.append("	AND A.sentiment_type_code = "+sentimentTypeCode+"                  ");
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
		map.put("Sql", selectBuffer.toString());
		resultModel = daSentimentArticleUntBll.getListBySQL(map);
		return resultModel;
	}
	
	private Map<String, String> dealTime(String timeTypeCode, String startTime, String endTime){
		Map<String, String> map = new HashMap<String, String>();
		String dateFormat = "%Y-%m-%d";
        List<String> timesList = new ArrayList<String>();
        Map<String, String> pmap = new HashMap<String, String>();
        if("5".equals(timeTypeCode)){//24小时
        	dateFormat = "%Y-%m-%d %H";
        	pmap.put("viewName", "hour");
        	pmap.put("hasCurrent", "true");
        	pmap.put("pastNum", "23");
        	timesList = TimesView.getTimesView(pmap);
        	startTime = timesList.get(0);
        	endTime = timesList.get(timesList.size() - 1);
        }else if("4".equals(timeTypeCode)){//周，包含现在7天
        	pmap.put("viewName", "date");
        	pmap.put("hasCurrent", "true");
        	pmap.put("pastNum", "6");
        	timesList = TimesView.getTimesView(pmap);
        	startTime = timesList.get(0);
        	endTime = timesList.get(timesList.size() - 1);
        }else if("3".equals(timeTypeCode)){//月，包含现在30天
        	pmap.put("viewName", "date");
        	pmap.put("hasCurrent", "true");
        	pmap.put("pastNum", "29");
        	timesList = TimesView.getTimesView(pmap);
        	startTime = timesList.get(0);
        	endTime = timesList.get(timesList.size() - 1);
        }
        map.put("dateFormat", dateFormat);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        return map;
	}

}
	



