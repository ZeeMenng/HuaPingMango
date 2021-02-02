package com.zee.app.extend.swagger.pi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zee.app.generate.swagger.pi.PiContentGenSwgApp;
import com.zee.bll.extend.split.pi.PirContentChannelSplBll;
import com.zee.bll.extend.split.pi.PirContentImageSplBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.bll.extend.unity.pi.PiChannelUntBll;
import com.zee.bll.extend.unity.pi.PiContentCountUntBll;
import com.zee.bll.extend.unity.pi.PiContentExtUntBll;
import com.zee.bll.extend.unity.pi.PiContentPictureUntBll;
import com.zee.bll.extend.unity.pi.PiContentTxtUntBll;
import com.zee.bll.extend.unity.pi.PiContentUntBll;
import com.zee.bll.extend.unity.pi.PiUserFavoriteUntBll;
import com.zee.bll.extend.unity.pi.PirContentChannelUntBll;
import com.zee.bll.extend.unity.pi.PirContentImageUntBll;
import com.zee.bll.generate.unity.pi.PiContentGenUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiContent;
import com.zee.ent.extend.pi.PiContentCount;
import com.zee.ent.extend.pi.PiContentExt;
import com.zee.ent.extend.pi.PiContentPicture;
import com.zee.ent.extend.pi.PiContentTxt;
import com.zee.ent.extend.pi.PirContentChannel;
import com.zee.ent.extend.pi.PirContentImage;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.FileUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS内容表 对外接口，扩展自PiContentGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piContent")
public class PiContentSwgApp extends PiContentGenSwgApp {
	@Autowired
	private FileUtil fileUtil;
	@Autowired
	private PiContentGenUntBll piContentGenUntBll;
	@Autowired
	private PiContentUntBll piContentUntBll;
	@Autowired
	private PiContentExtUntBll piContentExtUntBll;
	@Autowired
	private PiContentTxtUntBll piContentTxtUntBll;
	@Autowired
	private PiContentPictureUntBll piContentPictureUntBll;
	@Autowired
	protected PirContentChannelUntBll pirContentChannelUntBll;
	@Autowired
	@Qualifier("pirContentChannelSplBll")
	protected PirContentChannelSplBll pirContentChannelSplBll;
	@Autowired
	private PiChannelUntBll piChannelUntBll;
	@Autowired
	protected GpDictionaryUntBll gpDictionaryUntBll;
	@Autowired
	protected PiContentCountUntBll piContentCountUntBll;
	@Autowired
	@Qualifier("pirContentImageUntBll")
	protected PirContentImageUntBll pirContentImageUntBll;
	@Autowired
	@Qualifier("pirContentImageSplBll")
	protected PirContentImageSplBll pirContentImageSplBll;

	@Autowired
	protected PiUserFavoriteUntBll piUserFavoriteUntBll;

	@ApiOperation(value = "查询内容列表", notes = "查询内容列表")
	@RequestMapping(value = "/getContentList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public synchronized ResultModel getContentList() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		String relationId = null;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			String cId = "";
			JSONObject entityRelatedObject = null;
			if (json.containsKey("entityRelated")) {
				entityRelatedObject = json.getJSONObject("entityRelated");
			}
			if (entityRelatedObject.containsKey("channelId")) {
				cId = entityRelatedObject.getString("channelId");
			}

			selectBuffer.append("   SELECT                                                                              ");
			selectBuffer.append("   	a.channel_id AS channel_id,                                                     ");
			selectBuffer.append("   	e.channel_name AS channel_name,                                                 ");
			selectBuffer.append("   	b.relation_id AS relation_id,                                                   ");
			selectBuffer.append("   	a.content_id AS content_id,                                                     ");
			selectBuffer.append("   	d.title AS title,                                                   			");
			selectBuffer.append("   	d.author AS author,                                                   			");
			selectBuffer.append("   	d.description AS description,                                                   ");
			selectBuffer.append("   	f.`id` AS type_id,                                                          	");
			selectBuffer.append("   	f.`name` AS type_name,                                                          ");
			selectBuffer.append("   	DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') AS release_date,                ");
			selectBuffer.append("   	c.is_recommend AS is_recommend,                                                 ");
			selectBuffer.append("   	i.path AS img_path                                                              ");
			// selectBuffer.append("'").append(linkPath).append("'").append("||i.path
			// AS img_path ");

			selectBuffer.append("   FROM                                                                                ");

			// 如果一个栏目有子栏目，展示文章列表时需要去除重复项
			if (isParentChannel(cId)) {
				selectBuffer.append("   (SELECT * FROM pir_content_channel GROUP BY content_id)  a              ");
			} else {
				selectBuffer.append(" pir_content_channel a              										");
			}

			selectBuffer.append("   LEFT JOIN pi_channel b ON a.channel_id = b.id                                      ");
			selectBuffer.append("   LEFT JOIN pi_content c ON a.content_id = c.id                                      ");
			selectBuffer.append("   LEFT JOIN pi_content_ext d ON d.content_id = c.id                                   ");
			selectBuffer.append("   LEFT JOIN pi_channel_ext e ON e.channel_id = a.channel_id                           ");
			selectBuffer.append("   LEFT JOIN pi_content_type f ON f.id = c.type_id                                    ");
			selectBuffer.append("   LEFT JOIN pi_content_txt g ON g.content_id = c.id                                   ");
			selectBuffer.append("   LEFT JOIN pir_content_image h ON h.content_id = c.id                               ");
			selectBuffer.append("   INNER JOIN gp_resource i ON h.resource_id = i.id                               ");

			selectBuffer.append("   WHERE 1=1                                                                           ");

			if (entityRelatedObject.containsKey("relationId")) {
				relationId = entityRelatedObject.getString("relationId");
				selectBuffer.append("   AND relation_id LIKE '");
				selectBuffer.append(relationId);
				selectBuffer.append("%'");
			}
			// 企业微网站的数据不应显示在首页和市场页面的轮播中
			if (!CustomSymbolic.CHANNEL_WEBSIT.equals(cId)) {
				selectBuffer.append("AND a.channel_id !='");
				selectBuffer.append(CustomSymbolic.CHANNEL_WEBSIT);
				selectBuffer.append("'");
			}

			if (entityRelatedObject.containsKey("channelId") && StringUtils.isNotBlank(entityRelatedObject.getString("channelId"))) {
				selectBuffer.append("AND a.channel_id IN (SELECT pc1.id FROM pi_channel pc1 WHERE pc1.relation_id LIKE (SELECT CONCAT(pc2.relation_id,'%') FROM pi_channel pc2 WHERE pc2.id = '" + entityRelatedObject.getString("channelId") + "'))");
			}

			if (entityRelatedObject.containsKey("contentType") && StringUtils.isNotBlank(entityRelatedObject.getString("contentType"))) {
				selectBuffer.append("AND c.type_id='");
				selectBuffer.append(entityRelatedObject.getString("contentType"));
				selectBuffer.append("'");
			}
			if (entityRelatedObject.containsKey("typeId") && StringUtils.isNotBlank(entityRelatedObject.getString("typeId"))) {
				selectBuffer.append("AND c.type_id = '" + entityRelatedObject.getString("typeId") + "'");
			}
			if (entityRelatedObject.containsKey("isRecommend")) {
				String recommend = entityRelatedObject.getString("isRecommend");
				if (StringUtils.isNotBlank(recommend)) {
					selectBuffer.append("   AND is_recommend=" + recommend);
					selectBuffer.append("   AND path IS NOT NULL ");
				}
			}
			if (entityRelatedObject.containsKey("keyword") && StringUtils.isNotBlank(entityRelatedObject.getString("keyword"))) {
				String keyword = entityRelatedObject.getString("keyword");
				selectBuffer.append("AND (d.title LIKE '%" + keyword + "%')");
			}
			if (entityRelatedObject.containsKey("beginTime") && StringUtils.isNotBlank(entityRelatedObject.getString("beginTime"))) {
				String beginTime = entityRelatedObject.getString("beginTime");
				selectBuffer.append("AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') >= '" + beginTime + "'");
			}
			if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime"))) {
				String endTime = entityRelatedObject.getString("endTime");
				selectBuffer.append("AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') <= '" + endTime + "'");
			}
			// 还没到发布时间的的文章暂时不显示
			String curDate = DateUtils.getCurrentTimeStr();
			selectBuffer.append("AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') <= '" + curDate + "'");

			if (json.containsKey("page")) {
				JSONObject pageObject = json.getJSONObject("page");
				map.put("Page", pageObject);
			}
			if (json.containsKey("orderList")) {
				JSONArray orderListArray = json.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append("d." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		List<Map<String, Object>> resultList = CastObjectUtil.cast(resultModel.getData());
		List<Map<String, Object>> rList = new ArrayList<Map<String, Object>>();
		// List<String> contentIdList = new ArrayList<String>();
		for (Map<String, Object> resultMap : resultList) {
			// String tempContentId = (String) resultMap.get("content_id");
			String tempPath = (String) resultMap.get("img_path");
			if (StringUtils.isNotBlank(tempPath)) {
				tempPath = linkPath + tempPath;
				resultMap.put("img_path", tempPath);
			}
			/*
			 * String tempPath = (String) resultMap.get("img_path"); if
			 * (StringUtils.isNotBlank(tempPath)) { tempPath =
			 * fileUtil.getPathByFileName(tempPath).get("linkPath") + tempPath;
			 * resultMap.put("img_path", tempPath); }
			 */
			String tempTypeId = (String) resultMap.get("type_id");
			if (StringUtils.isNotBlank(tempTypeId)) {
				if (CustomSymbolic.CONTENT_TYPE_SEE.equals(tempTypeId)) {
					tempTypeId = "1";
				}
				if (CustomSymbolic.CONTENT_TYPE_HEAR.equals(tempTypeId)) {
					tempTypeId = "2";
				}
				if (CustomSymbolic.CONTENT_TYPE_IMAGE.equals(tempTypeId)) {
					tempTypeId = "3";
				}
				if (CustomSymbolic.CONTENT_TYPE_SAY.equals(tempTypeId)) {
					tempTypeId = "4";
				}
				resultMap.put("type_id", tempTypeId);
			}
			rList.add(resultMap);
			// if(!contentIdList.contains(tempContentId)) {
			// 将查询到的imgPath拼接为完整路径
			// contentIdList.add(tempContentId);
			// }
		}
		resultModel.setData(rList);
		return resultModel;
	}

	@ApiOperation(value = "查询内容列表", notes = "查询内容列表")
	@RequestMapping(value = "/getRecommendContentList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getRecommendContentList() {

		String relationId = null;

		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("relationId")) {
					relationId = entityRelatedObject.getString("relationId");
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("   SELECT                                                                              ");
		selectBuffer.append("   	a.channel_id AS channel_id,                                                     ");
		selectBuffer.append("   	e.channel_name AS channel_name,                                                 ");
		selectBuffer.append("   	b.relation_id AS relation_id,                                                   ");
		selectBuffer.append("   	a.content_id AS content_id,                                                     ");
		selectBuffer.append("   	f.`name` AS type_name,                                                          ");
		selectBuffer.append("   	d.title AS title,                                                   			");
		selectBuffer.append("   	c.is_recommend AS is_recommend,                                                 ");
		selectBuffer.append("   	d.description AS description,                                                   ");
		selectBuffer.append("   	d.author AS author,                                                   			");
		selectBuffer.append("   	DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') AS release_date,                ");
		selectBuffer.append("   	h.img_path AS img_path                                                          ");
		selectBuffer.append("   FROM                                                                                ");
		selectBuffer.append("   	pir_content_channel a                                                           ");
		selectBuffer.append("   INNER JOIN pi_channel b ON a.channel_id = b.id                                      ");
		selectBuffer.append("   INNER JOIN pi_content c ON a.content_id = c.id                                      ");
		selectBuffer.append("   LEFT JOIN pi_content_ext d ON d.content_id = c.id                                   ");
		selectBuffer.append("   LEFT JOIN pi_channel_ext e ON e.channel_id = a.channel_id                           ");
		selectBuffer.append("   INNER JOIN pi_content_type f ON f.id = c.type_id                                    ");
		selectBuffer.append("   LEFT JOIN pi_content_txt g ON g.content_id = c.id                                   ");
		selectBuffer.append("   LEFT JOIN pi_content_picture h ON h.content_id = c.id                               ");
		selectBuffer.append("   WHERE is_recommend=0");
		selectBuffer.append("   AND img_path IS NOT NULL");

		selectBuffer.append("   AND relation_id LIKE '");
		selectBuffer.append(relationId);
		selectBuffer.append("%'");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("page")) {
				JSONObject pageObject = json.getJSONObject("page");
				map.put("Page", pageObject);
			}
			if (json.containsKey("orderList")) {
				JSONArray orderListArray = json.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append("d." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		/*
		 * if (!StringUtils.isBlank(jsonData)) { JSONObject json =
		 * JSONObject.fromObject(jsonData); if
		 * (json.containsKey("entityRelated")) { JSONObject entityRelatedObject
		 * = json.getJSONObject("entityRelated"); if
		 * (entityRelatedObject.containsKey("channelId")){ channelId =
		 * entityRelatedObject.getString("channelId"); relationId =
		 * getRelationId(channelId); } } }
		 */

		List<Map<String, Object>> resultList = CastObjectUtil.cast(resultModel.getData());
		List<Map<String, Object>> rList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> resultMap : resultList) {
			// 将查询到的imgPath拼接为完整路径
			String tempPath = (String) resultMap.get("img_path");

			tempPath = fileUtil.getPathByFileName(tempPath).get("linkPath") + tempPath;

			resultMap.put("img_path", tempPath);
			rList.add(resultMap);

			// String tempRelationId = (String) resultMap.get("relation_id");
			// int len = relationId.length();
			// if(tempRelationId.substring(0, len).equals(relationId)) {
			// }
			// String relationId = tempRelationId.substring(0, 4);
			// List<Map<String, Object>> channelInfoList = new
			// ArrayList<Map<String, Object>>();
			// channelInfoList = (List<Map<String, Object>>)
			// getChannelInfo(relationId).getData();
			// if(channelInfoList.size()==1) {
			// String channelId = (String) channelInfoList.get(0).get("aId");
			// resultMap.put("channel_id",channelId);
			// System.out.println(channelId);
			// }
		}
		// System.out.println(rList.get(0).get("img_path"));
		resultModel.setData(rList);
		return resultModel;
	}

	private String getRelationId(String channelId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.relation_id relationId 												   ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("   WHERE a.id =        											   ");
		selectBuffer.append("'");
		if (channelId.contains("=")) {
			selectBuffer.append(channelId.split("=")[1]);
		} else {
			selectBuffer.append(channelId);
		}
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);

		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		if (object != null)
			objectList = (List<Map<String, Object>>) object;
		if (objectList != null && objectList.size() == 1) {
			// 解析出需要的id值
			JSONObject jsonData = JSONObject.fromObject(objectList.get(0));
			// channel id
			if (jsonData.containsKey("relationId")) {
				return jsonData.getString("relationId");
			}
		}
		return null;
	}

	@ApiOperation(value = "查询内容详情", notes = "查询内容详情")
	@RequestMapping(value = "/getContent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getContent() {

		String contentId = null;

		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    				");
		selectBuffer.append("	DATE_FORMAT(b.release_date,'%Y-%m-%d %H:%i') AS release_date,    ");
		selectBuffer.append("	b.title AS title,                                                 	");
		selectBuffer.append("	b.author AS author,                                                	");
		selectBuffer.append("	b.origin AS origin,                                                	");
		selectBuffer.append("	b.description AS description,                                                	");
		selectBuffer.append("	d.id AS id,                                                 		");
		selectBuffer.append("	d.views AS views,                                                 	");
		selectBuffer.append("	c.txt AS txt,                                                 	    ");
		
		selectBuffer.append("concat('").append(linkPath).append("',").append("f.path) AS imgPath ");
		selectBuffer.append("FROM                                                      		        ");
		selectBuffer.append("	pi_content a                                             	        ");
		selectBuffer.append("LEFT JOIN pi_content_ext b ON a.id = b.content_id         		        ");
		selectBuffer.append("LEFT JOIN pi_content_txt c ON a.id = c.content_id         		        ");
		selectBuffer.append("LEFT JOIN pi_content_count d ON a.id = d.content_id       		        ");
		selectBuffer.append("LEFT JOIN pir_content_image e ON a.id = e.content_id        ");
		selectBuffer.append("inner JOIN gp_resource f ON e.resource_id = f.id   ");		
		selectBuffer.append("WHERE 1=1");
		selectBuffer.append(" and (e.is_title_img_code='0' or e.is_title_img_code is null)");
		
	
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("contentId") && StringUtils.isNotBlank(entityRelatedObject.getString("contentId"))) {
					contentId = entityRelatedObject.getString("contentId");
					selectBuffer.append("AND a.id = '" + contentId + "'");
				}
			}
			if (json.containsKey("page")) {
				JSONObject pageObject = json.getJSONObject("page");
				map.put("Page", pageObject);
			}
			if (json.containsKey("orderList")) {
				JSONArray orderListArray = json.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append("b." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (modelList.size() >= 1) {
			resultMap = modelList.get(0);
			if (resultMap.containsKey("views")) {
				if (resultMap.get("views") == null) {
					// 说明是第一次访问
					PiContentCount piContentCount = new PiContentCount();
					piContentCount.setContentId(contentId);
					piContentCount.setViews(1);
					piContentCountUntBll.add(piContentCount);
					resultMap.put("views", 1);
				} else {
					String tempContentCountId = (String) resultMap.get("id");
					PiContentCount piContentCount = new PiContentCount();
					piContentCount.setId(tempContentCountId);
					int views = (Integer) (resultMap.get("views")) + 1;
					piContentCount.setViews(views);
					piContentCount.setContentId(contentId);
					piContentCountUntBll.update(piContentCount);
					resultMap.put("views", views);
				}
			}
			String tempPath = (String) resultMap.get("img_path");
			if (StringUtils.isNotBlank(tempPath)) {
				tempPath = fileUtil.getPathByFileName(tempPath).get("linkPath") + tempPath;
				resultMap.put("img_path", tempPath);
			}
		}
		modelList.clear();
		modelList.add(resultMap);
		resultModel.setData(modelList);

		return resultModel;
	}

	@ApiOperation(value = "单条查询", notes = "根据id查询pi_content、pi_content_ext、pi_content_txt的内容")
	@ApiImplicitParam(paramType = "path", name = "id", value = "contentId", required = true, dataType = "String")
	@RequestMapping(value = "/getContentById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getContentById(@PathVariable("id") String id) {

		String contentChannelIds = "";
		String channelIds = "";
		String relationIds = "";

		// 1、查询数据库，回显修改页面需要的数据
		// 2、查询修改pi_content\pi_content_ext\pi_content_txt及删除pir_content_channel数据所需的id值

		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    							");
		selectBuffer.append("	a.id AS contentId,                                                 					");
		selectBuffer.append("	a.has_title_img AS hasTitleImg,                                                 ");
		selectBuffer.append("	a.is_recommend AS isRecommend,                                                 	");
		selectBuffer.append("	a.type_id AS contentType,                                                 		");
		selectBuffer.append("	DATE_FORMAT(b.release_date,'%Y-%m-%d %H:%i') AS releaseDate,    				");
		selectBuffer.append("	b.id AS contentExtId,                                                 					");
		selectBuffer.append("	b.title AS title,                                                 				");
		selectBuffer.append("	b.short_title AS shortTitle,                                                 	");
		selectBuffer.append("	b.description  AS description,                                                 	");
		selectBuffer.append("	b.author AS author,                                                				");
		selectBuffer.append("	b.origin AS origin,                                                				");
		selectBuffer.append("	b.origin_url  AS originUrl,                                                 	");
		selectBuffer.append("	b.title_img  AS titleImg,                                                 		");
		selectBuffer.append("	b.content_img  AS contentImg,                                                 	");
		selectBuffer.append("	c.id AS contentTxtId,                                                  	    			");
		selectBuffer.append("	c.txt AS txt,                                                  	    			");
		selectBuffer.append("	d.id AS pirContentChannelId,                                                 	    			");
		selectBuffer.append("	d.channel_id AS channelId,                                                  	");
		selectBuffer.append("	e.relation_id AS relationId,                                                  	");
		selectBuffer.append("	f.id AS pirContentImageId,                                                  					");
		selectBuffer.append("	h.id AS titleImageResourceId,                                                  					");
		selectBuffer.append("concat('").append(linkPath).append("',").append("h.path) AS imgPath                                                          ");
		selectBuffer.append("FROM                                                      		        			");
		selectBuffer.append("	pi_content a                                             	        			");
		selectBuffer.append("LEFT JOIN pi_content_ext b ON a.id = b.content_id         		        			");
		selectBuffer.append("LEFT JOIN pi_content_txt c ON a.id = c.content_id         		        			");
		selectBuffer.append("LEFT JOIN pir_content_channel d ON a.id = d.content_id         		        	");
		selectBuffer.append("LEFT JOIN pi_channel e ON d.channel_id = e.id 									 	");
		selectBuffer.append("LEFT JOIN pir_content_image f ON a.id = f.content_id         		        		");
		selectBuffer.append("LEFT JOIN gp_resource h ON f.resource_id = h.id                               ");
		selectBuffer.append("WHERE 1=1");
		selectBuffer.append(" and (f.is_title_img_code='0' or f.is_title_img_code is null)");
		selectBuffer.append(" and a.id =      												        			");
		selectBuffer.append("'");
		selectBuffer.append(id);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		// 由于一篇文章可以发布到多个栏目下，所以该查询结果为多条数据，这些数据中
		// 和pi_content pi_content_ext pi_content_txt pi_content_picture
		// 表相关的字段值完全相同
		// pi_channel和pir_content_channel表中的数据需要分别查询，
		// 获取pir_content_channel的id用于删除原有数据
		// 获取pir_content_channel的channel_id用于回显数据
		// 获取pi_channel 的relation_id用于得到文章所属栏目的所有父级目录进行回显

		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		String imgPath = "";
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			for (int i = 0; i < objectList.size(); i++) {
				if (objectList.get(i) != null) {
					objMap = objectList.get(i);
					// 解析出需要的channelId值
					JSONObject jsonData = JSONObject.fromObject(objMap);
					if (jsonData.containsKey("channelId")) {
						String tempChannelId = jsonData.getString("channelId");
						if (i != objectList.size() - 1) {
							channelIds += tempChannelId + ",";
						} else {
							channelIds += tempChannelId;
						}
					}
					// 解析出需要的contentChannelId值
					if (jsonData.containsKey("dId")) {
						String tempContentChannelId = jsonData.getString("dId");
						if (i != objectList.size() - 1) {
							contentChannelIds += tempContentChannelId + ",";
						} else {
							contentChannelIds += tempContentChannelId;
						}
					}
					// 解析出需要的relation_id值
					if (jsonData.containsKey("relationId")) {
						String tempRelationIds = jsonData.getString("relationId");
						if (i != objectList.size() - 1) {
							relationIds += tempRelationIds + ",";
						} else {
							relationIds += tempRelationIds;
						}
					}
				}
			}

			// 由于点击修改时，需要将文章所属栏目的父级栏目进行回显，
			// 所以需要根据relation_id的值获取文章所属栏目的父级栏目id
			channelIds = getChannelIdsByRelationIds(relationIds);
			// 这里获取的channelIds有很多重复值
			// 比如，保险和认证同属于政策-服务-首页，channelIds里分别有两个政策-服务-首页的值，需去除重复项
			String[] tempChannelIds = channelIds.split(",");
			String formalChannelIds = "";
			for (int i = 0; i < tempChannelIds.length; i++) {
				if (!formalChannelIds.contains(tempChannelIds[i])) {
					formalChannelIds += tempChannelIds[i] + ",";
				}
			}
			if (StringUtils.isNotBlank(formalChannelIds)) {
				objMap.put("channelId", formalChannelIds.substring(0, formalChannelIds.length() - 1));
			} else {
				objMap.put("channelId", "");
			}

			resultModel.setData(objMap);
		}

		return resultModel;
	}

	private String getChannelIdsByRelationIds(String relIds) {
		String resultFatherChannelIds = "";
		String[] relIdArr = relIds.split(",");
		for (int i = 0; i < relIdArr.length; i++) {
			// 根据relationId获取所有的父级栏目id
			// 一个relationId值可能对应多个父级栏目的channelId
			String tempFatherChannelIds = getFatherChannelIds(relIdArr[i]);
			resultFatherChannelIds += tempFatherChannelIds;
		}
		if (StringUtils.isNotBlank(resultFatherChannelIds)) {
			return resultFatherChannelIds.substring(0, resultFatherChannelIds.length() - 1);
		} else {
			return "";
		}
	}

	private String getFatherChannelIds(String relId) {
		String resultChannelIds = "";
		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(relId)) {
			return "";
		}
		int relationIdLen = relId.length();

		List<Map<String, Object>> channelInfoList = new ArrayList<Map<String, Object>>();

		while (relationIdLen >= 2) {
			ResultModel tempResultModel = getChannelInfo(relId);
			Object object = tempResultModel.getData();
			List<Map<String, Object>> objectList = null;
			Map<String, Object> objMap = null;
			if (object != null)
				objectList = (List<Map<String, Object>>) object;
			if (objectList != null) {
				for (int i = 0; i < objectList.size(); i++) {
					objMap = objectList.get(i);
					JSONObject jsonData = JSONObject.fromObject(objMap);
					// channel id
					if (jsonData.containsKey("aId")) {
						resultChannelIds += jsonData.getString("aId") + ",";
					}
				}
			}
			relationIdLen -= 2;
			relId = relId.substring(0, relationIdLen);
		}
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		resultModel.setData(channelInfoList);
		return resultChannelIds;

		// ResultModel resultModel = new ResultModel();
		/*
		 * Map<String, Object> map = new HashMap<String, Object>(); StringBuffer
		 * selectBuffer = new StringBuffer(); selectBuffer.append(
		 * "SELECT                                                    				"
		 * ); selectBuffer.append(
		 * "	a.id AS aId                                                 	    "
		 * ); selectBuffer.append(
		 * "FROM                                                      		        "
		 * ); selectBuffer.append(
		 * "	pi_channel a                                             	        "
		 * ); selectBuffer.append(
		 * "WHERE a.relation_id like     												        "
		 * ); selectBuffer.append("'"); selectBuffer.append(relId);
		 * selectBuffer.append("%'");
		 * 
		 * map.put("Sql", selectBuffer.toString()); resultModel =
		 * piChannelUntBll.getListBySQL(map); Object object =
		 * resultModel.getData(); List<Map<String, Object>> objectList = null;
		 * Map<String, Object> objMap = null; if (object != null) objectList =
		 * (List<Map<String, Object>>) object; if (objectList != null) { for(int
		 * i = 0;i<objectList.size();i++) { objMap = objectList.get(i);
		 * JSONObject jsonData = JSONObject.fromObject(objMap); // channel id if
		 * (jsonData.containsKey("aId")) {
		 * resultChannelIds+=jsonData.getString("aId")+","; } } }
		 * 
		 * return resultChannelIds;
		 */
	}

	private ResultModel getChannelInfo(String relationId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         		");
		selectBuffer.append("		a.id AS aId 												   	");
		selectBuffer.append("	FROM                                                           		");
		selectBuffer.append("		pi_channel a                                               		");
		selectBuffer.append("   WHERE a.relation_id =        										");
		selectBuffer.append("'");
		selectBuffer.append(relationId);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "单条查询", notes = "根据id查询文章所属的二三级栏目的内容")
	@ApiImplicitParam(paramType = "path", name = "id", value = "contentId", required = true, dataType = "String")
	@RequestMapping(value = "/getSecAndThiCodeById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getSecAndThiCodeById(@PathVariable("id") String id) {

		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    								");
		selectBuffer.append("	e.relation_id AS relationId                                                  	    ");
		selectBuffer.append("FROM                                                      		        				");
		selectBuffer.append("	pi_content a                                             	        				");
		selectBuffer.append("LEFT JOIN pir_content_channel d ON a.id = d.content_id         		       		    ");
		selectBuffer.append("LEFT JOIN pi_channel e ON d.channel_id = e.id ");
		selectBuffer.append("WHERE a.id =      												        				");
		selectBuffer.append("'");
		selectBuffer.append(id.split("=")[1]);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if (object != null)
			objectList = (List<Map<String, Object>>) object;
		if (objectList != null && objectList.size() == 1) {
			objMap = objectList.get(0);
		}

		JSONObject jsonData = JSONObject.fromObject(objMap);

		String firCode = null;
		String secCode = null;
		String thiCode = null;
		if (jsonData.containsKey("relationId")) {
			String relationIds = jsonData.getString("relationId");
			String tempReId = getTempReId(relationIds, "xxxxxxxx");

			// 获取一级栏目、二级栏目和三级栏目的id值
			// firCode = getChannelId(tempReId.substring(0, 4));
			// secCode = getChannelId(tempReId.substring(0, 6));
			// thiCode = getChannelId(tempReId.substring(0, 8));
			return getSecChannelId(tempReId.substring(0, 6));
		}

		return null;
	}

	private ResultModel getSecChannelId(String tempCode) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    						");
		selectBuffer.append("	a.id AS secChannelId,                                                 	    ");
		selectBuffer.append("	e.channel_name secChannelName                                 				");
		selectBuffer.append("FROM                                                      		        		");
		selectBuffer.append("	pi_channel a                                             	        		");
		selectBuffer.append("	LEFT JOIN pi_channel_ext e ON e.`channel_id`=a.`id`            				");
		selectBuffer.append("WHERE a.relation_id=     												        ");
		selectBuffer.append("'");
		selectBuffer.append(tempCode);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		/*
		 * Object object = resultModel.getData(); List<Map<String, Object>>
		 * objectList = null; if (object != null) objectList = (List<Map<String,
		 * Object>>) object; if (objectList != null && objectList.size() == 1) {
		 * resultModel.setData(objectList.get(0)); // 解析出需要的id值 JSONObject
		 * jsonData =
		 * JSONObject.fromObject(objectList.get(0)resultModel.getData()); //
		 * channel id if (jsonData.containsKey("aId")) { return
		 * jsonData.getString("aId"); } }
		 */

		return resultModel;
	}

	private String getChannelId(String tempCode) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    						");
		selectBuffer.append("	a.id AS aId                                                 	    		");
		selectBuffer.append("FROM                                                      		        		");
		selectBuffer.append("	pi_channel a                                             	        		");
		selectBuffer.append("WHERE a.relation_id=     												        ");
		selectBuffer.append("'");
		selectBuffer.append(tempCode);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		if (object != null)
			objectList = (List<Map<String, Object>>) object;
		if (objectList != null && objectList.size() == 1) {
			resultModel.setData(objectList.get(0));
			// 解析出需要的id值
			JSONObject jsonData = JSONObject
					.fromObject(objectList.get(0)/* resultModel.getData() */);
			// channel id
			if (jsonData.containsKey("aId")) {
				return jsonData.getString("aId");
			}
		}

		return null;
	}

	private String getTempReId(String secCode, String tempCode) {
		char[] code = secCode.toCharArray();
		char[] temp = tempCode.toCharArray();
		for (int i = 0; i < code.length; i++) {
			temp[i] = code[i];
		}
		String str = "";
		for (int i = 0; i < temp.length; i++) {
			str += temp[i];
		}
		return str;
	}

	@RequestMapping(value = "/updateContent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateContent() throws Exception {
		ResultModel resultModel = new ResultModel();

		// 首先获取需要更新的数据
		String strJson = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		String jsonData = new String(strJson);
		String contentId = "";

		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		// pi_content表相关
		int isRecommend = 0;
		String typeId = null;

		// pi_content_txt表相关
		String txt = null;

		// pi_content_ext表相关
		String title = null;
		String shortTitle = null;
		String author = null;
		String description = null;
		String releaseDate = null;
		String origin = null;
		String originUrl = null;
		String titleImg = null;
		String contentImg = null;

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			// 修改pi_content表
			PiContent piContent = new PiContent();

			if (jsonObject.containsKey("contentId")) {
				contentId = jsonObject.getString("contentId");
				piContent.setId(contentId);
			}

			if (jsonObject.containsKey("titleImageResourceId") && StringUtils.isNotBlank(jsonObject.getString("titleImageResourceId")))
				piContent.setHasTitleImg(CustomSymbolic.DCODE_BOOLEAN_T);
			else
				piContent.setHasTitleImg(CustomSymbolic.DCODE_BOOLEAN_F);

			if (jsonObject.containsKey("isRecommend")) {
				isRecommend = Integer.parseInt(jsonObject.getString("isRecommend"));
				piContent.setIsRecommend((byte) isRecommend);
			}
			if (jsonObject.containsKey("contentType")) {
				typeId = jsonObject.getString("contentType");
				piContent.setTypeId(typeId);
			}

			resultModel = piContentUntBll.update(piContent);

			// 修改pi_content_txt表
			PiContentTxt piContentTxt = new PiContentTxt();
			if (jsonObject.containsKey("contentTxtId")) {
				piContentTxt.setId(jsonObject.getString("contentTxtId"));
			}

			if (jsonObject.containsKey("txt")) {
				txt = jsonObject.getString("txt");
				piContentTxt.setTxt(txt);
			}
			resultModel = piContentTxtUntBll.update(piContentTxt);

			// 修改pi_content_ext表
			PiContentExt piContentExt = new PiContentExt();

			if (jsonObject.containsKey("contentExtId")) {
				piContentExt.setId(jsonObject.getString("contentExtId"));
			}

			if (jsonObject.containsKey("title")) {
				title = jsonObject.getString("title");
				piContentExt.setTitle(title);
			}
			if (jsonObject.containsKey("shortTitle")) {
				shortTitle = jsonObject.getString("shortTitle");
				piContentExt.setShortTitle(shortTitle);
			}
			piContentExt.setAuthor(getCurrentUser().getUserName());
			if (jsonObject.containsKey("author")) {
				author = jsonObject.getString("author");
				if (StringUtils.isNotBlank(author))
					piContentExt.setAuthor(author);
			}
			if (jsonObject.containsKey("description")) {
				description = jsonObject.getString("description");
				piContentExt.setDescription(description);
			}
			piContentExt.setReleaseDate(DateUtils.getCurrentTime());
			if (jsonObject.containsKey("releaseDate")) {
				releaseDate = jsonObject.getString("releaseDate");
				if (StringUtils.isNotBlank(releaseDate)) {
					piContentExt.setReleaseDate(DateUtils.string2Date(releaseDate, CustomSymbolic.DATE_FORMAT));
				}
			}
			if (jsonObject.containsKey("origin")) {
				origin = jsonObject.getString("origin");
				piContentExt.setOrigin(origin);
			}
			if (jsonObject.containsKey("originUrl")) {
				originUrl = jsonObject.getString("originUrl");
				piContentExt.setOriginUrl(originUrl);
			}
			if (jsonObject.containsKey("titleImg")) {
				titleImg = jsonObject.getString("titleImg");
				piContentExt.setDescription(titleImg);
			}

			if (jsonObject.containsKey("contentImg")) {
				contentImg = jsonObject.getString("contentImg");
				piContentExt.setDescription(contentImg);
			}
			resultModel = piContentExtUntBll.update(piContentExt);

			// 修改pir_content_image（删除pir_content_image表中的标题图片，再重新插入）
			resultModel = pirContentImageSplBll.deleteTitleImageByContentId(contentId);
			PirContentImage contentImage = new PirContentImage();
			contentImage.setContentId(contentId);
			if (jsonObject.containsKey("titleImageResourceId") && StringUtils.isNotBlank(jsonObject.getString("titleImageResourceId"))) {
				contentImage.setResourceId(jsonObject.getString("titleImageResourceId"));
				contentImage.setIsTitleImgCode(CustomSymbolic.DCODE_BOOLEAN_T);
			}
			resultModel = pirContentImageUntBll.add(contentImage);

			// 修改pirContentChannel（先删除，再插入）
			pirContentChannelSplBll.deleteByContentId(contentId);

			if (jsonObject.containsKey("channelId")) {
				String[] channelIdArr = jsonObject.getString("channelId").split(",");
				for (int i = 0; i < channelIdArr.length; i++) {
					if (!isParentChannel(channelIdArr[i])) {
						// 如果一个栏目没有子栏目，那么在该栏目下增加一篇文章
						addContentToChannel(contentId, channelIdArr[i], resultModel);
					}
				}
			}
		}
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

		// selectBuffer.append("select A.id id,A.user_id userId,A.type_id
		// typeId,A.model_id modelId,A.site_id siteId,A.sort_date
		// sortDate,A.top_level topLevel,A.has_title_img
		// hasTitleImg,A.is_recommend isRecommend,A.status status,A.views_day
		// viewsDay,A.comments_day commentsDay,A.downloads_day
		// downloadsDay,A.ups_day upsDay,A.score score from pi_content A inner
		// join pi_content B on A.id=B.id where 1=1 ");
		//
		// select
		// A.id id,
		// A.user_id userId,
		// A.type_id typeId,
		// A.model_id modelId,
		// A.site_id siteId,
		// A.sort_date sortDate,
		// A.top_level topLevel,
		// A.has_title_img hasTitleImg,
		// A.is_recommend isRecommend,
		// A.status status,
		// A.views_day viewsDay,
		// A.comments_day commentsDay,
		// A.downloads_day downloadsDay,
		// A.ups_day upsDay,
		// A.score score
		// from
		// pi_content A
		// inner join pi_content B
		// on A.id = B.id

		selectBuffer.append("SELECT                                                    							");
		selectBuffer.append("	a.id AS id,                                                 					");
		selectBuffer.append("	DATE_FORMAT(b.release_date,'%Y-%m-%d %H:%i') AS release_date,    			");
		selectBuffer.append("	a.sort_date AS sortDate,                                                 		");
		selectBuffer.append("	b.title AS title,                                                 				");
		selectBuffer.append("	b.short_title AS shortTitle,                                                 	");
		selectBuffer.append("	b.description AS description,                                                 	");
		selectBuffer.append("	b.author AS author,                                                				");
		selectBuffer.append("	b.origin AS origin,                                                				");
		selectBuffer.append("	b.add_time AS addTime,                                                 				");
		selectBuffer.append("	d.views AS views,                                                 				");
		selectBuffer.append("	c.txt AS txt                                                  	    			");
		selectBuffer.append("FROM                                                      		        			");
		selectBuffer.append("	pi_content a                                             	        			");
		selectBuffer.append("LEFT JOIN pi_content_ext b ON a.id = b.content_id         		        			");
		selectBuffer.append("LEFT JOIN pi_content_txt c ON a.id = c.content_id         		       			 	");
		selectBuffer.append("LEFT JOIN pi_content_count d ON a.id = d.content_id       		        			");
		selectBuffer.append("WHERE 1=1       												        			");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and a.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");

				if (entityRelatedObject.containsKey("title") && StringUtils.isNotBlank(entityRelatedObject.getString("title"))) {
					selectBuffer.append(" and b.title like '%").append(entityRelatedObject.getString("title")).append("%'");
				}
				if (entityRelatedObject.containsKey("startDate") && StringUtils.isNotBlank(entityRelatedObject.getString("startDate"))) {
					String startDate = entityRelatedObject.getString("startDate");
					selectBuffer.append("AND DATE_FORMAT(b.release_date,'%Y-%m-%d') >= '" + startDate + "'");
				}
				if (entityRelatedObject.containsKey("endDate") && StringUtils.isNotBlank(entityRelatedObject.getString("endDate"))) {
					String endDate = entityRelatedObject.getString("endDate");
					selectBuffer.append("AND DATE_FORMAT(b.release_date,'%Y-%m-%d') <= '" + endDate + "'");
				}

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
					if (orderColumnObject.getString("columnName").equals("id")) {
						selectBuffer.append("release_date desc");
						selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
					} else {
						selectBuffer.append(orderColumnObject.getString("columnName"));
						selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
						selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
					}
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = piContentUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "CMS内容表列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@RequestMapping(value = "/addContent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultModel addContent() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		String tempChannelId = null;

		// pi_content表相关
		String sortDate = null;
		byte[] hasTitleImg = new byte[] { 0 };
		byte[] isRecommend = new byte[] { 0 };
		String typeId = null;

		// pi_content_txt表相关
		String txt = null;

		// pi_content_ext表相关
		String title = null;
		String shortTitle = null;
		String author = null;
		String description = null;
		String origin = null;
		String originUrl = null;
		String titleImg = null;
		String contentImg = null;

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			String tempContentId = null;
			// 所属栏目
			if (jsonObject.containsKey("channelId")) {
				tempChannelId = jsonObject.getString("channelId");
				System.out.println(tempChannelId);
			}

			// pi_content表相关数据
			PiContent piContent = new PiContent();
			if (jsonObject.containsKey("sortDate")) {
				sortDate = jsonObject.getString("sortDate");
				java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date date = null;
				try {
					date = formatter.parse(sortDate);
					piContent.setSortDate(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (jsonObject.containsKey("hasTitleImg")) {
				hasTitleImg = jsonObject.getString("hasTitleImg").getBytes();
				piContent.setHasTitleImg(hasTitleImg[0]);
			}
			if (jsonObject.containsKey("isRecommend")) {
				isRecommend = jsonObject.getString("isRecommend").getBytes();
				piContent.setIsRecommend(isRecommend[0]);
			}
			if (jsonObject.containsKey("contentType")) {
				typeId = jsonObject.getString("contentType");
				piContent.setTypeId(typeId);
			}
			resultModel = piContentUntBll.add(piContent);
			tempContentId = resultModel.getObjectId();
			System.out.println(tempContentId);

			// pi_content_txt表相关数据
			PiContentTxt piContentTxt = new PiContentTxt();
			if (jsonObject.containsKey("txt")) {
				txt = jsonObject.getString("txt");
				piContentTxt.setTxt(txt);
			}
			piContentTxt.setContentId(tempContentId);
			resultModel = piContentTxtUntBll.add(piContentTxt);

			// pi_content_ext表相关数据
			PiContentExt piContentExt = new PiContentExt();
			if (jsonObject.containsKey("title")) {
				title = jsonObject.getString("title");
				piContentExt.setTitle(title);
			}
			if (jsonObject.containsKey("shortTitle")) {
				shortTitle = jsonObject.getString("shortTitle");
				piContentExt.setShortTitle(shortTitle);
			}
			if (jsonObject.containsKey("author")) {
				author = jsonObject.getString("author");
				piContentExt.setAuthor(author);
			}
			if (jsonObject.containsKey("description")) {
				description = jsonObject.getString("description");
				piContentExt.setDescription(description);
			}
			if (jsonObject.containsKey("origin")) {
				origin = jsonObject.getString("origin");
				piContentExt.setDescription(origin);
			}
			if (jsonObject.containsKey("originUrl")) {
				originUrl = jsonObject.getString("originUrl");
				piContentExt.setDescription(originUrl);
			}
			if (jsonObject.containsKey("titleImg")) {
				titleImg = jsonObject.getString("titleImg");
				piContentExt.setDescription(titleImg);
			}
			if (jsonObject.containsKey("contentImg")) {
				contentImg = jsonObject.getString("contentImg");
				piContentExt.setDescription(contentImg);
			}
			piContentExt.setContentId(tempContentId);
			piContentExt.setReleaseDate(new Date());
			resultModel = piContentExtUntBll.add(piContentExt);

			PirContentChannel pirContentChannel = new PirContentChannel();
			pirContentChannel.setChannelId(tempChannelId);
			pirContentChannel.setContentId(tempContentId);
			resultModel = pirContentChannelUntBll.add(pirContentChannel);
		}

		return resultModel;
	}

	@ApiOperation(value = "新增记录", notes = "新增一条或多条记录")
	@RequestMapping(value = "/batchAddContent", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel batchAddContent() throws Exception {

		ResultModel resultModel = new ResultModel();
		String jsonData = new String(request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON));

		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		// pi_content表相关
		String tempContentId = null;
		int isRecommend = 0;
		String typeId = null;

		// pi_content_txt表相关
		String txt = null;

		// pi_content_ext表相关
		String title = null;
		String shortTitle = null;
		String author = null;
		String description = null;
		String releaseDate = null;
		String origin = null;
		String originUrl = null;
		String titleImg = null;
		String contentImg = null;

		// pir_content_channel 相关
		String tempChannelId = null;

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			// 插入pi_content表
			PiContent piContent = new PiContent();
			if (jsonObject.containsKey("titleImageResourceId") && StringUtils.isNotBlank(jsonObject.getString("titleImageResourceId")))
				piContent.setHasTitleImg(CustomSymbolic.DCODE_BOOLEAN_T);
			else
				piContent.setHasTitleImg(CustomSymbolic.DCODE_BOOLEAN_F);

			if (jsonObject.containsKey("isRecommend")) {
				isRecommend = Integer.parseInt(jsonObject.getString("isRecommend"));
				piContent.setIsRecommend((byte) isRecommend);
			}
			if (jsonObject.containsKey("contentType")) {
				typeId = jsonObject.getString("contentType");
				piContent.setTypeId(typeId);
			}
			resultModel = piContentUntBll.add(piContent);
			tempContentId = resultModel.getObjectId();

			// 插入pi_content_txt表
			PiContentTxt piContentTxt = new PiContentTxt();
			if (jsonObject.containsKey("txt")) {
				txt = jsonObject.getString("txt");
				piContentTxt.setTxt(txt);
			}
			piContentTxt.setContentId(tempContentId);
			resultModel = piContentTxtUntBll.add(piContentTxt);

			// 插入pi_content_ext表
			PiContentExt piContentExt = new PiContentExt();
			if (jsonObject.containsKey("title")) {
				title = jsonObject.getString("title");
				piContentExt.setTitle(title);
			}
			if (jsonObject.containsKey("shortTitle")) {
				shortTitle = jsonObject.getString("shortTitle");
				piContentExt.setShortTitle(shortTitle);
			}
			piContentExt.setAuthor(getCurrentUser().getUserName());
			if (jsonObject.containsKey("author")) {
				author = jsonObject.getString("author");
				if (StringUtils.isNotBlank(author))
					piContentExt.setAuthor(author);
			}
			if (jsonObject.containsKey("description")) {
				description = jsonObject.getString("description");
				piContentExt.setDescription(description);
			}
			piContentExt.setReleaseDate(DateUtils.getCurrentTime());
			if (jsonObject.containsKey("releaseDate")) {
				releaseDate = jsonObject.getString("releaseDate");
				if (StringUtils.isNotBlank(releaseDate)) {
					piContentExt.setReleaseDate(DateUtils.string2Date(releaseDate, CustomSymbolic.DATE_FORMAT));
				}
			}
			if (jsonObject.containsKey("textOrigin")) {
				origin = jsonObject.getString("textOrigin");
				piContentExt.setOrigin(origin);
			}
			if (jsonObject.containsKey("textOriginUrl")) {
				originUrl = jsonObject.getString("textOriginUrl");
				piContentExt.setOriginUrl(originUrl);
			}
			if (jsonObject.containsKey("titleImg")) {
				titleImg = jsonObject.getString("titleImg");
				piContentExt.setDescription(titleImg);
			}
			if (jsonObject.containsKey("contentImg")) {
				contentImg = jsonObject.getString("contentImg");
				piContentExt.setDescription(contentImg);
			}
			piContentExt.setContentId(tempContentId);
			piContentExt.setAddUserId(getCurrentUser().getId());
			resultModel = piContentExtUntBll.add(piContentExt);

			// 插入pir_content_image表
			PirContentImage contentImage = new PirContentImage();
			contentImage.setContentId(tempContentId);
			if (jsonObject.containsKey("titleImageResourceId") && StringUtils.isNotBlank(jsonObject.getString("titleImageResourceId"))) {
				contentImage.setResourceId(jsonObject.getString("titleImageResourceId"));
				contentImage.setIsTitleImgCode(CustomSymbolic.DCODE_BOOLEAN_T);
			}
			resultModel = pirContentImageUntBll.add(contentImage);

			// 第二步：查找出没有子栏目的栏目的channelId
			if (jsonObject.containsKey("channelId")) {
				tempChannelId = jsonObject.getString("channelId");
				String[] channelIdArr = tempChannelId.split(",");
				for (int i = 0; i < channelIdArr.length; i++) {
					if (!isParentChannel(channelIdArr[i])) {
						// 第三步：如果一个栏目没有子栏目，那么在该栏目下增加一篇文章
						addContentToChannel(tempContentId, channelIdArr[i], resultModel);
					}
				}
			}
		}

		return resultModel;
	}

	private boolean isParentChannel(String tempcId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.id id 												   ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("   WHERE parent_id =        									   ");
		selectBuffer.append("'");
		selectBuffer.append(tempcId);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);

		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if (object != null)
			objectList = (List<Map<String, Object>>) object;
		if (objectList != null && objectList.size() >= 1) {
			return true;
		}
		return false;
	}

	private void addContentToChannel(String tempConId, String tempChaId, ResultModel resultModel) {
		PirContentChannel pirContentChannel = new PirContentChannel();
		pirContentChannel.setChannelId(tempChaId);
		pirContentChannel.setContentId(tempConId);
		resultModel = pirContentChannelUntBll.add(pirContentChannel);
	}

	@ApiOperation(value = "获取树状结构channel数据", notes = "获取树状结构channel数据")
	@RequestMapping(value = "/getChannelNodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannelNodes() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                               		");
		selectBuffer.append("		A.id id,                                         		");
		selectBuffer.append("		A.parent_id fartherId,                                  ");
		selectBuffer.append("		B.channel_name name                              		");
		selectBuffer.append("	FROM                                                 		");
		selectBuffer.append("		pi_channel A                                      		");
		selectBuffer.append("	LEFT JOIN pi_channel_ext B ON A.id = B.channel_id           ");
		selectBuffer.append("	WHERE channel_name IS NOT NULL       ");
		// JSONObject jsonObject = JSONObject.fromObject(jsonData);
		// if(jsonObject.containsKey("id") &&
		// StringUtils.isNotBlank(jsonObject.getString("id"))) {
		// selectBuffer.append(" AND A.id != ");
		// selectBuffer.append("'");
		// selectBuffer.append(jsonObject.getString("id"));
		// selectBuffer.append("'");
		// }

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map2 : modelList) {
			Map<String, Object> treeMap = new HashMap<String, Object>();
			if (!CustomSymbolic.CHANNEL_TRACE.equals(map2.get("id"))) {
				treeMap.put("id", map2.get("id"));
				treeMap.put("pId", map2.get("fartherId"));
				treeMap.put("name", map2.get("name"));

				treeMap.put("open", true);

				treeNodes.add(treeMap);
			}
		}
		resultModel.setData(treeNodes);
		return resultModel;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询文章详细内容")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getContentDetailsById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getContentDetailsById(@PathVariable("id") String id) {
		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    								");
		selectBuffer.append("	a.id AS aId,                                                 						");

		selectBuffer.append("	a.is_recommend AS isRecommend,                                                	 	");
		selectBuffer.append("	a.has_title_img AS hasTitleImg,                                                 	");
		selectBuffer.append("	a.views_day AS viewsDay,                                                 			");
		selectBuffer.append("	a.comments_day AS commentsDay,                                                	 	");
		selectBuffer.append("	a.ups_day AS upsDay,                                                 				");
		selectBuffer.append("	a.downloads_day AS downloadsDay,                                                 	");
		selectBuffer.append("	b.title AS title,                                                 					");
		selectBuffer.append("	b.author AS author,                                                 				");
		selectBuffer.append("	b.description AS description,                                                 		");
		selectBuffer.append("	a.score AS score,                                                 					");
		selectBuffer.append("	DATE_FORMAT(b.release_date,'%Y-%m-%d %H:%i') AS sortDate,    						");
		selectBuffer.append("	a.status AS status,                                                 				");

		selectBuffer.append("	d.channel_id AS channelId,                                                  	    ");
		selectBuffer.append("	f.channel_name AS channelName,                                                      ");
		selectBuffer.append("	t.name AS typeName,                                                 				");
		selectBuffer.append("	e.relation_id AS relationId,                                                	    ");

		selectBuffer.append("	c.txt AS txt                                                  	    				");

		selectBuffer.append("FROM                                                      		        				");
		selectBuffer.append("	pi_content a                                             	       				    ");
		selectBuffer.append("LEFT JOIN pi_content_type t ON a.type_id = t.id         		       					");
		selectBuffer.append("LEFT JOIN pi_content_ext b ON a.id = b.content_id         		      				    ");
		selectBuffer.append("LEFT JOIN pi_content_txt c ON a.id = c.content_id         		        				");
		selectBuffer.append("LEFT JOIN pir_content_channel d ON a.id = d.content_id         		        		");
		selectBuffer.append("LEFT JOIN pi_channel e ON d.channel_id = e.id ");
		selectBuffer.append("LEFT JOIN pi_channel_ext f ON f.channel_id = e.id ");

		selectBuffer.append("WHERE a.id =      												        				");
		selectBuffer.append("'");
		selectBuffer.append(id);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		Map<String, Object> formalObjMap = null;
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			if (objectList.size() >= 1) {
				formalObjMap = objectList.get(0);
				String formalChannelName = "";
				for (int i = 0; i < objectList.size(); i++) {
					if (objectList.get(i) != null) {
						objMap = objectList.get(i);
						// 解析出需要的channelId值
						JSONObject jsonData = JSONObject.fromObject(objMap);
						if (jsonData.containsKey("channelName")) {
							String tempChannelName = jsonData.getString("channelName");
							if (i != objectList.size() - 1) {
								formalChannelName += tempChannelName + "，";
							} else {
								formalChannelName += tempChannelName;
							}
						}
					}
				}
				// 替换channelName的值
				formalObjMap.put("channelName", formalChannelName);
				JSONObject jsonData = JSONObject.fromObject(objectList.get(0));
				if (jsonData.containsKey("isRecommend")) {
					String tempIsRecommend = jsonData.getString("isRecommend");
					String isRecommend = transfSqlData(CustomSymbolic.DI_BOOLEAN, tempIsRecommend);
					formalObjMap.put("isRecommend", isRecommend);
				}
				if (jsonData.containsKey("hasTitleImg")) {
					String tempHasTitleImg = jsonData.getString("hasTitleImg");
					String hasTitleImg = transfSqlData(CustomSymbolic.DI_BOOLEAN, tempHasTitleImg);
					formalObjMap.put("hasTitleImg", hasTitleImg);
				}
				if (jsonData.containsKey("status")) {
					String tempStatus = jsonData.getString("status");
					String status = transfSqlData(CustomSymbolic.DI_CONTENT_STATUS, tempStatus);
					formalObjMap.put("status", status);
				}
			}
		}
		resultModel.setData(formalObjMap);

		return resultModel;
	}

	private String transfSqlData(String dictType, String isRecommend) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         			");
		selectBuffer.append("		d.text AS text 												   		");
		selectBuffer.append("	FROM                                                           			");
		selectBuffer.append("		gp_dictionary d                                               		");
		selectBuffer.append("   WHERE d.type_id =        											   	");
		selectBuffer.append("'");
		selectBuffer.append(dictType);
		selectBuffer.append("' AND");
		selectBuffer.append(" d.code = '");
		selectBuffer.append(isRecommend);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());

		resultModel = gpDictionaryUntBll.getListBySQL(map);
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		String text = "";
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			if (objectList.size() >= 1) {
				objMap = objectList.get(0);
				JSONObject jsonData = JSONObject.fromObject(objMap);
				if (jsonData.containsKey("text")) {
					text = jsonData.getString("text");
				}
			}
		}
		return text;
	}

	@RequestMapping(value = "/imageUpload")
	public String imageUpload(@RequestParam("upload") MultipartFile file, @RequestParam("CKEditorFuncNum") String funcNumber) {
		System.out.println(file.getOriginalFilename());

		PrintWriter out = null;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		String filePath = null;
		try {
			out = response.getWriter();
			fis = (FileInputStream) file.getInputStream();
			filePath = "D:\\Workspace\\Eclipse\\Jusfoun\\Mango\\src\\main\\resources\\static\\image\\" + file.getOriginalFilename();
			// filePath =
			// "D:\\Workspace\\Eclipse\\Jusfoun\\Mango\\src\\main\\java\\com\\jusfoun\\app\\extend\\swagger\\pi\\"+file.getOriginalFilename();
			// filePath =
			// request.getServletContext().getRealPath("/img/uploadImg");
			// File f = new File(filePath);
			// if(!f.exists()){ //如果路径不存在，创建
			// f.mkdirs();
			// }
			File toFile = new File(filePath);

			fos = new FileOutputStream(toFile);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = fis.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// String url = "http://" + request.getServerName() + ":" +
		// request.getServerPort() + request.getContextPath() +
		// "/src/main/resources/static/image/"+file.getOriginalFilename();
		// url = "http://scimg.jb51.net/allimg/160706/103-160F6095531355.jpg";

		String callback = request.getParameter("CKEditorFuncNum");
		out.println("<script type=\"text/javascript\">");
		/*
		 * out.println("window.parent.CKEDITOR.tools.callFunction(" + funcNumber
		 * + ",'" + file.getOriginalFilename() + "','')");
		 */
		out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + "../../../../../src/main/resources/static/image/" + file.getOriginalFilename() + "','')");
		out.println("</script>");

		return null;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		// 需要删除pi_content、pi_content_ext、pi_content_txt、pi_content_picture、pir_content_channel表中的数据
		ResultModel result = new ResultModel();
		if (StringUtils.isNotBlank(id)) {
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();

			selectBuffer.append("   SELECT                                                                              		");
			selectBuffer.append("   a.id AS aId,                                                     							");
			selectBuffer.append("   b.id AS bId,                                                     							");
			selectBuffer.append("   c.id AS cId,                                                    							");
			selectBuffer.append("   d.id AS dId,                                                     							");
			selectBuffer.append("   e.id AS eId                                                     							");
			selectBuffer.append("   FROM                                                                                		");
			selectBuffer.append(" pir_content_channel a              															");
			selectBuffer.append("   INNER JOIN pi_content b ON a.content_id = b.id                                      		");
			selectBuffer.append("   LEFT JOIN pi_content_ext c ON a.content_id = c.content_id                                   ");
			selectBuffer.append("   LEFT JOIN pi_content_txt d ON a.content_id = d.content_id                                   ");
			selectBuffer.append("   LEFT JOIN pi_content_picture e ON a.content_id = e.content_id                               ");
			selectBuffer.append("   WHERE a.content_id='");
			selectBuffer.append(id);
			selectBuffer.append("'");

			map.put("Sql", selectBuffer.toString());
			result = pirContentChannelUntBll.getListBySQL(map);

			List<Map<String, Object>> resultList = (List<Map<String, Object>>) result.getData();
			Map<String, Object> resultMap = new HashMap<String, Object>();
			if (resultList.size() >= 1) {
				for (int i = 0; i < resultList.size(); i++) {
					if (i == 0) {
						resultMap = resultList.get(i);
						result = pirContentChannelUntBll.delete((String) (resultMap.get("aId")));
						result = piContentUntBll.delete((String) (resultMap.get("bId")));
						result = piContentExtUntBll.delete((String) (resultMap.get("cId")));
						result = piContentTxtUntBll.delete((String) (resultMap.get("dId")));
						result = piContentPictureUntBll.delete((String) (resultMap.get("eId")));
					} else {
						resultMap = resultList.get(i);
						result = pirContentChannelUntBll.delete((String) (resultMap.get("aId")));
					}
				}
			}
		}
		return result;
	}

	@RequestMapping(value = "/batchUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultModel batchUpdate() throws Exception {
		ResultModel resultModel = new ResultModel();

		String strJson = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		String jsonData = new String(strJson);

		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		JSONObject entityJSONObject = null;
		JSONArray idListArray = null;

		// 获取需要更新的数据
		if (jsonObject.containsKey("entity")) {
			entityJSONObject = jsonObject.getJSONObject("entity");
		}
		// 解析出需要批量修改的文章的id
		if (jsonObject.containsKey("idList")) {
			idListArray = jsonObject.getJSONArray("idList");
			int len = idListArray.size();
			for (int i = 0; i < len; i++) {
				String contentId = (String) idListArray.get(i);
				// 针对每个content id 分别进行更新操作
				severallyUpdateByContentId(contentId, entityJSONObject, resultModel);
			}
		}
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}

	private void severallyUpdateByContentId(String contentId, JSONObject entityJSONObject, ResultModel resultModel) {
		// 首先根据获取到的需要批量更改的文章的id 获取相关的id值 包括
		// contentExtId、contentTxtId、contentPictureId、contentChannelIds
		getRelatedInfosByContentId(contentId);

		// 如果批量修改时所属栏目没有被选中，则pir_content_channel表中的数据不删除
		if (entityJSONObject.containsKey("channelId")) {
			String channelId = entityJSONObject.getString("channelId");
			if (StringUtils.isNotBlank(channelId)) {
				String[] pirConChaArray = channelId.split(",");
				for (String str : pirConChaArray) {
					// 删除pir_content_channel表内容
					pirContentChannelUntBll.delete(str);
				}
			}
		}

		// pi_content表相关
		int hasTitleImg = 0;
		int isRecommend = 0;
		String typeId = null;

		// pi_content_txt表相关
		String txt = null;

		// pi_content_ext表相关
		String title = null;
		String shortTitle = null;
		String author = null;
		String description = null;
		String releaseDate = null;
		String origin = null;
		String originUrl = null;
		String titleImg = null;
		String contentImg = null;

		// pi_content_picture表相关
		String imgPath = null;

		PiContent piContent = new PiContent();
		// 解析pi_content表相关数据
		if (entityJSONObject.containsKey("hasTitleImg")) {
			hasTitleImg = Integer.parseInt(entityJSONObject.getString("hasTitleImg"));
			piContent.setHasTitleImg((byte) hasTitleImg);
		}
		if (entityJSONObject.containsKey("isRecommend")) {
			String recommend = entityJSONObject.getString("isRecommend");
			if (StringUtils.isNotBlank(recommend)) {
				isRecommend = Integer.parseInt(recommend);
			} else {
				isRecommend = 1;
			}
			piContent.setIsRecommend((byte) isRecommend);
		}
		if (entityJSONObject.containsKey("contentType")) {
			typeId = entityJSONObject.getString("contentType");
			piContent.setTypeId(typeId);
		}
		piContent.setId(contentId);
		resultModel = piContentUntBll.update(piContent);

		PiContentExt piContentExt = new PiContentExt();
		// 解析pi_content_ext表相关数据
		if (entityJSONObject.containsKey("title")) {
			title = entityJSONObject.getString("title");
			piContentExt.setTitle(title);
		}
		if (entityJSONObject.containsKey("shortTitle")) {
			shortTitle = entityJSONObject.getString("shortTitle");
			piContentExt.setShortTitle(shortTitle);
		}
		if (entityJSONObject.containsKey("author")) {
			author = entityJSONObject.getString("author");
			piContentExt.setAuthor(author);
		}
		if (entityJSONObject.containsKey("description")) {
			description = entityJSONObject.getString("description");
			piContentExt.setDescription(description);
		}

		if (entityJSONObject.containsKey("releaseDate")) {
			releaseDate = entityJSONObject.getString("releaseDate");
			if (StringUtils.isNotBlank(releaseDate)) {
				piContentExt.setReleaseDate(DateUtils.string2Date(releaseDate, "yyyy-MM-dd HH:mm"));
			} else {
				String curDate = DateUtils.getCurrentTimeStr();
				piContentExt.setReleaseDate(DateUtils.string2Date(curDate, "yyyy-MM-dd HH:mm"));
			}
		} else {
			String curDate = DateUtils.getCurrentTimeStr();
			piContentExt.setReleaseDate(DateUtils.string2Date(curDate, "yyyy-MM-dd HH:mm"));
		}
		if (entityJSONObject.containsKey("origin")) {
			origin = entityJSONObject.getString("origin");
			piContentExt.setDescription(origin);
		}
		if (entityJSONObject.containsKey("originUrl")) {
			originUrl = entityJSONObject.getString("originUrl");
			piContentExt.setDescription(originUrl);
		}
		if (entityJSONObject.containsKey("titleImg")) {
			titleImg = entityJSONObject.getString("titleImg");
			piContentExt.setDescription(titleImg);
		}
		if (entityJSONObject.containsKey("contentImg")) {
			contentImg = entityJSONObject.getString("contentImg");
			piContentExt.setDescription(contentImg);
		}
		if (entityJSONObject.containsKey("hiddenContentExtId")) {
			piContentExt.setId(entityJSONObject.getString("hiddenContentExtId"));
		}
		piContentExt.setContentId(contentId);
		resultModel = piContentExtUntBll.update(piContentExt);

		PiContentTxt piContentTxt = new PiContentTxt();
		// 解析pi_content_txt表相关数据
		if (entityJSONObject.containsKey("txt")) {
			txt = entityJSONObject.getString("txt");
			piContentTxt.setTxt(txt);
		}
		if (entityJSONObject.containsKey("hiddenContentTxtId")) {
			piContentTxt.setId(entityJSONObject.getString("hiddenContentTxtId"));
		}
		piContentTxt.setContentId(contentId);
		resultModel = piContentTxtUntBll.update(piContentTxt);

		PiContentPicture piContentPicture = new PiContentPicture();
		// 解析pi_content_picture表相关数据
		if (entityJSONObject.containsKey("imgPath")) {
			imgPath = entityJSONObject.getString("imgPath");
			String[] strArray = imgPath.split("/");
			int len = strArray.length;
			imgPath = strArray[len - 1];
			piContentPicture.setImgPath(imgPath);
		}
		piContentPicture.setContentId(contentId);
		resultModel = piContentPictureUntBll.update(piContentPicture);

		// 获取最新选中的channelId
		if (entityJSONObject.containsKey("channelId")) {
			String channelIds = entityJSONObject.getString("channelId");
			String[] channelIdArr = channelIds.split(",");
			for (int i = 0; i < channelIdArr.length; i++) {
				if (StringUtils.isNotBlank(channelIdArr[i])) {
					if (!isParentChannel(channelIdArr[i])) {
						// 如果一个栏目没有子栏目，那么在该栏目下增加一篇文章
						addContentToChannel(contentId, channelIdArr[i], resultModel);
					}
				}

			}
		}
	}

	private void getRelatedInfosByContentId(String id) {
		String contentChannelIds = "";
		String channelIds = "";
		String relationIds = "";

		// 1、查询数据库，回显修改页面需要的数据
		// 2、查询 修改pi_content\pi_content_ext\pi_content_txt\pi_content_picture
		// 及删除pir_content_channel数据所需的id值

		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    							");
		selectBuffer.append("	a.id AS aId,                                                 					");
		selectBuffer.append("	a.has_title_img AS hasTitleImg,                                                 ");
		selectBuffer.append("	a.is_recommend AS isRecommend,                                                 	");
		selectBuffer.append("	a.type_id AS contentType,                                                 		");
		selectBuffer.append("	DATE_FORMAT(b.release_date,'%Y-%m-%d %H:%i') AS releaseDate,    				");
		selectBuffer.append("	b.id AS bId,                                                 					");
		selectBuffer.append("	b.title AS title,                                                 				");
		selectBuffer.append("	b.short_title AS shortTitle,                                                 	");
		selectBuffer.append("	b.description  AS description,                                                 	");
		selectBuffer.append("	b.author AS author,                                                				");
		selectBuffer.append("	b.origin AS origin,                                                				");
		selectBuffer.append("	b.origin_url  AS originUrl,                                                 	");
		selectBuffer.append("	b.title_img  AS titleImg,                                                 		");
		selectBuffer.append("	b.content_img  AS contentImg,                                                 	");
		selectBuffer.append("	c.id AS cId,                                                  	    			");
		selectBuffer.append("	c.txt AS txt,                                                  	    			");
		selectBuffer.append("	d.id AS dId,                                                 	    			");
		selectBuffer.append("	d.channel_id AS channelId,                                                  	");
		selectBuffer.append("	e.relation_id AS relationId,                                                  	");
		selectBuffer.append("	f.id AS fId,                                                  					");
		selectBuffer.append("	f.img_path AS imgPath                                                  			");
		selectBuffer.append("FROM                                                      		        			");
		selectBuffer.append("	pi_content a                                             	        			");
		selectBuffer.append("LEFT JOIN pi_content_ext b ON a.id = b.content_id         		        			");
		selectBuffer.append("LEFT JOIN pi_content_txt c ON a.id = c.content_id         		        			");
		selectBuffer.append("LEFT JOIN pir_content_channel d ON a.id = d.content_id         		        	");
		selectBuffer.append("LEFT JOIN pi_channel e ON d.channel_id = e.id 									 	");
		selectBuffer.append("LEFT JOIN pi_content_picture f ON a.id = f.content_id         		        		");
		selectBuffer.append("WHERE a.id =      												        			");
		selectBuffer.append("'");
		selectBuffer.append(id);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		// 由于一篇文章可以发布到多个栏目下，所以该查询结果为多条数据，这些数据中
		// 和 pi_content pi_content_ext pi_content_txt pi_content_picture
		// 表相关的字段的值完全相同
		// 和 pi_channel pir_content_channel表相关的字段的值则不相同
		// 获取每条数据中的pir_content_channel的id用于删除原有文章和栏目的关联数据
		// pir_content_channel的channel_id用于回显数据，在做批量修改时不需要
		// pi_channel 的relation_id用于得到文章所属栏目的所有父级目录进行回显，在做批量修改时不需要

		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		String imgPath = "";
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			for (int i = 0; i < objectList.size(); i++) {
				if (objectList.get(i) != null) {
					objMap = objectList.get(i);
					// 解析出需要的channelId值
					JSONObject jsonData = JSONObject.fromObject(objMap);
					if (jsonData.containsKey("channelId")) {
						String tempChannelId = jsonData.getString("channelId");
						if (i != objectList.size() - 1) {
							channelIds += tempChannelId + ",";
						} else {
							channelIds += tempChannelId;
						}
					}
					// 解析出需要的contentChannelId值
					if (jsonData.containsKey("dId")) {
						String tempContentChannelId = jsonData.getString("dId");
						if (i != objectList.size() - 1) {
							contentChannelIds += tempContentChannelId + ",";
						} else {
							contentChannelIds += tempContentChannelId;
						}
					}
					// 解析出需要的relation_id值
					if (jsonData.containsKey("relationId")) {
						String tempRelationIds = jsonData.getString("relationId");
						if (i != objectList.size() - 1) {
							relationIds += tempRelationIds + ",";
						} else {
							relationIds += tempRelationIds;
						}
					}
					// 解析出需要的fileName值
					if (jsonData.containsKey("imgPath")) {
						imgPath = fileUtil.getPathByFileName(jsonData.getString("imgPath")).get("linkPath") + jsonData.getString("imgPath");
					}
				}
			}

			// 由于点击修改时，需要将文章所属栏目的父级栏目进行回显（批量修改时不需要），
			// 所以需要根据relation_id的值获取文章所属栏目的父级栏目id
			// channelIds = getChannelIdsByRelationIds(relationIds);
			// 这里获取的channelIds有很多重复值
			// 比如，保险和认证同属于政策-服务-首页，channelIds里分别有两个政策-服务-首页的值，需去除重复项
			String[] tempChannelIds = channelIds.split(",");
			String formalChannelIds = "";
			for (int i = 0; i < tempChannelIds.length; i++) {
				if (!formalChannelIds.contains(tempChannelIds[i])) {
					formalChannelIds += tempChannelIds[i] + ",";
				}
			}

			objMap.put("channelId", formalChannelIds.substring(0, formalChannelIds.length() - 1));
			objMap.put("imgPath", imgPath);
			resultModel.setData(objMap);
		}

	}

	@ApiOperation(value = "查询内容列表(App接口)", notes = "查询内容列表(App接口)")
	@RequestMapping(value = "/getContentListForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getContentListForApp() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		String relationId = null;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			String cId = "";
			JSONObject entityRelatedObject = null;
			if (json.containsKey("entityRelated")) {
				entityRelatedObject = json.getJSONObject("entityRelated");
			}
			if (entityRelatedObject.containsKey("channelId")) {
				cId = entityRelatedObject.getString("channelId");
			}

			selectBuffer.append("   SELECT                                                                              ");
			selectBuffer.append("   	a.channel_id AS channel_id,                                                     ");
			selectBuffer.append("   	e.channel_name AS channel_name,                                                 ");
			selectBuffer.append("   	b.relation_id AS relation_id,                                                   ");
			selectBuffer.append("   	a.content_id AS content_id,                                                     ");
			selectBuffer.append("   	d.title AS title,                                                   			");
			selectBuffer.append("   	d.author AS author,                                                   			");
			selectBuffer.append("   	d.description AS description,                                                   ");
			selectBuffer.append("   	DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') AS release_date,                   ");
			selectBuffer.append("   	c.is_recommend AS is_recommend,                                                 ");
			selectBuffer.append("   	i.path AS img_path                                                              ");

			selectBuffer.append("   FROM                                                                                ");

			// 如果一个栏目有子栏目，展示文章列表时需要去除重复项
			if (isParentChannel(cId)) {
				selectBuffer.append("   (SELECT * FROM pir_content_channel GROUP BY content_id)  a              ");
			} else {
				selectBuffer.append(" pir_content_channel a              										");
			}

			selectBuffer.append("   LEFT JOIN pi_channel b ON a.channel_id = b.id                                      ");
			selectBuffer.append("   LEFT JOIN pi_content c ON a.content_id = c.id                                      ");
			selectBuffer.append("   LEFT JOIN pi_content_ext d ON d.content_id = c.id                                   ");
			selectBuffer.append("   LEFT JOIN pi_channel_ext e ON e.channel_id = a.channel_id                           ");
			selectBuffer.append("   LEFT JOIN pi_content_txt g ON g.content_id = c.id                                   ");
			selectBuffer.append("	LEFT JOIN                                                                           ");
			selectBuffer.append("	pir_content_image h ON h.content_id = c.id                                          ");
			selectBuffer.append("	LEFT JOIN                                                                           ");
			selectBuffer.append("	gp_resource i ON h.resource_id = i.id                                               ");
			selectBuffer.append("   WHERE 1=1                                                                           ");

			if (entityRelatedObject.containsKey("relationId")) {
				relationId = entityRelatedObject.getString("relationId");
				selectBuffer.append("   AND relation_id LIKE '");
				selectBuffer.append(relationId);
				selectBuffer.append("%'");
			}
			// 企业微网站的数据不应显示在首页和市场页面的轮播中
			if (!CustomSymbolic.CHANNEL_WEBSIT.equals(cId)) {
				selectBuffer.append("AND a.channel_id !='");
				selectBuffer.append(CustomSymbolic.CHANNEL_WEBSIT);
				selectBuffer.append("'");
			}
			/*
			 * if (entityRelatedObject.containsKey("channelId") &&
			 * StringUtils.isNotBlank(entityRelatedObject.getString("channelId")
			 * )) { selectBuffer.append(
			 * "AND a.channel_id IN (SELECT pc1.id FROM pi_channel pc1 WHERE pc1.relation_id LIKE (SELECT CONCAT(pc2.relation_id,'%') FROM pi_channel pc2 WHERE pc2.id = '"
			 * +entityRelatedObject.getString("channelId")+"'))"); }
			 */
			/*
			 * if (entityRelatedObject.containsKey("contentType") &&
			 * StringUtils.isNotBlank(entityRelatedObject.getString(
			 * "contentType"))) { selectBuffer.append("AND c.type_id='");
			 * selectBuffer.append(entityRelatedObject.getString("contentType"))
			 * ; selectBuffer.append("'"); } if
			 * (entityRelatedObject.containsKey("typeId") &&
			 * StringUtils.isNotBlank(entityRelatedObject.getString("typeId")))
			 * { selectBuffer.append("AND c.type_id = '" +
			 * entityRelatedObject.getString("typeId") + "'"); }
			 */
			if (entityRelatedObject.containsKey("isRecommend")) {
				String recommend = entityRelatedObject.getString("isRecommend");
				if (StringUtils.isNotBlank(recommend)) {
					if ("0".equals(recommend)) {
						selectBuffer.append("   AND is_recommend=0");
						selectBuffer.append("   AND path IS NOT NULL ");
					}
				}
			}
			/*
			 * if (entityRelatedObject.containsKey("keyword") &&
			 * StringUtils.isNotBlank(entityRelatedObject.getString("keyword")))
			 * { String keyword = entityRelatedObject.getString("keyword");
			 * selectBuffer.append("AND (d.title LIKE '%" + keyword + "%')"); }
			 * if (entityRelatedObject.containsKey("beginTime") &&
			 * StringUtils.isNotBlank(entityRelatedObject.getString("beginTime")
			 * )) { String beginTime =
			 * entityRelatedObject.getString("beginTime"); selectBuffer.append(
			 * "AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') >= '" +
			 * beginTime + "'"); } if
			 * (entityRelatedObject.containsKey("endTime") &&
			 * StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
			 * { String endTime = entityRelatedObject.getString("endTime");
			 * selectBuffer.append(
			 * "AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') <= '" + endTime
			 * + "'"); }
			 * 
			 * if (json.containsKey("page")) { JSONObject pageObject =
			 * json.getJSONObject("page"); map.put("Page", pageObject); }
			 */
			/*
			 * if (json.containsKey("orderList")) { JSONArray orderListArray =
			 * json.getJSONArray("orderList"); if (orderListArray.size() != 0)
			 * selectBuffer.append(" order by "); for (int i = 0; i <
			 * orderListArray.size(); i++) { JSONObject orderColumnObject =
			 * orderListArray.getJSONObject(i); selectBuffer.append("d." +
			 * orderColumnObject.getString("columnName"));
			 * selectBuffer.append(orderColumnObject.getBoolean("isASC") ?
			 * " ASC" : " DESC"); selectBuffer.append((i + 1) ==
			 * orderListArray.size() ? " " : " ,"); } }
			 */

			// 还没到发布时间的的文章暂时不显示
			String curDate = DateUtils.getCurrentTimeStr();
			selectBuffer.append("AND DATE_FORMAT(d.release_date,'%Y-%m-%d %H:%i') <= '" + curDate + "'");

			selectBuffer.append(" order by release_date DESC");
			
			if (json.containsKey("page")) {
				JSONObject pageObject = json.getJSONObject("page");
				map.put("Page", pageObject);
			}
			
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		List<Map<String, Object>> resultList = CastObjectUtil.cast(resultModel.getData());
		List<Map<String, Object>> rList = new ArrayList<Map<String, Object>>();
		// List<String> contentIdList = new ArrayList<String>();
		for (Map<String, Object> resultMap : resultList) {
			// String tempContentId = (String) resultMap.get("content_id");
			String tempPath = (String) resultMap.get("img_path");
			if (StringUtils.isNotBlank(tempPath)) {
				tempPath = linkPath + tempPath;
				resultMap.put("img_path", tempPath);
			}
			String tempTypeId = (String) resultMap.get("type_id");
			if (StringUtils.isNotBlank(tempTypeId)) {
				if (CustomSymbolic.CONTENT_TYPE_SEE.equals(tempTypeId)) {
					tempTypeId = "1";
				}
				if (CustomSymbolic.CONTENT_TYPE_HEAR.equals(tempTypeId)) {
					tempTypeId = "2";
				}
				if (CustomSymbolic.CONTENT_TYPE_IMAGE.equals(tempTypeId)) {
					tempTypeId = "3";
				}
				if (CustomSymbolic.CONTENT_TYPE_SAY.equals(tempTypeId)) {
					tempTypeId = "4";
				}
				resultMap.put("type_id", tempTypeId);
			}
			rList.add(resultMap);
			// if(!contentIdList.contains(tempContentId)) {
			// 将查询到的imgPath拼接为完整路径
			// contentIdList.add(tempContentId);
			// }
		}
		resultModel.setData(rList);
		return resultModel;
	}

	@ApiOperation(value = "查询内容详情", notes = "根据文章id查询内容详情")
	@RequestMapping(value = "/getContentForApp", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getContentForApp() {

		String contentId = null;
		String userId = null;

		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    				");
		selectBuffer.append("	DATE_FORMAT(b.release_date,'%Y-%m-%d %H:%i') AS release_date,    ");
		selectBuffer.append("	b.title AS title,                                                 	");
		selectBuffer.append("	b.author AS author,                                                	");
		selectBuffer.append("	b.origin AS origin,                                                	");
		selectBuffer.append("	d.id AS id,                                                 		");
		selectBuffer.append("	d.views AS views,                                                 	");
		selectBuffer.append("	replace (replace(replace(c.txt, 'style', 'styled'),'styled=\"max','style=\"max'), 'styled=\"text-align: center;', 'style=\"text-align: center;' ) As txt,      	");
//		selectBuffer.append("	c.txt AS txt,                                                 	    ");
		selectBuffer.append("	e.img_path AS img_path                                               ");
		selectBuffer.append("FROM                                                      		        ");
		selectBuffer.append("	pi_content a                                             	        ");
		selectBuffer.append("LEFT JOIN pi_content_ext b ON a.id = b.content_id         		        ");
		selectBuffer.append("LEFT JOIN pi_content_txt c ON a.id = c.content_id         		        ");
		selectBuffer.append("LEFT JOIN pi_content_count d ON a.id = d.content_id       		        ");
		selectBuffer.append("LEFT JOIN pi_content_picture e ON a.id = e.content_id       		    ");
		selectBuffer.append("WHERE 1=1       												        ");
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("contentId") && StringUtils.isNotBlank(entityRelatedObject.getString("contentId"))) {
					contentId = entityRelatedObject.getString("contentId");
					selectBuffer.append("AND a.id = '" + contentId + "'");
				}
				if (entityRelatedObject.containsKey("userId") && StringUtils.isNotBlank(entityRelatedObject.getString("userId"))) {
					userId = entityRelatedObject.getString("userId");
				}
			}
			/*
			 * if (json.containsKey("page")) { JSONObject pageObject =
			 * json.getJSONObject("page"); map.put("Page", pageObject); } if
			 * (json.containsKey("orderList")) { JSONArray orderListArray =
			 * json.getJSONArray("orderList"); if (orderListArray.size() != 0)
			 * selectBuffer.append(" order by "); for (int i = 0; i <
			 * orderListArray.size(); i++) { JSONObject orderColumnObject =
			 * orderListArray.getJSONObject(i); selectBuffer.append("b." +
			 * orderColumnObject.getString("columnName"));
			 * selectBuffer.append(orderColumnObject.getBoolean("isASC") ?
			 * " ASC" : " DESC"); selectBuffer.append((i + 1) ==
			 * orderListArray.size() ? " " : " ,"); } }
			 */
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = piContentGenUntBll.getListBySQL(map);

		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (modelList.size() >= 1) {
			resultMap = modelList.get(0);
			if (resultMap.containsKey("views")) {
				if (resultMap.get("views") == null) {
					// 说明是第一次访问
					PiContentCount piContentCount = new PiContentCount();
					piContentCount.setContentId(contentId);
					piContentCount.setViews(1);
					piContentCountUntBll.add(piContentCount);
					resultMap.put("views", 1);
				} else {
					String tempContentCountId = (String) resultMap.get("id");
					PiContentCount piContentCount = new PiContentCount();
					piContentCount.setId(tempContentCountId);
					int views = (Integer) (resultMap.get("views")) + 1;
					piContentCount.setViews(views);
					piContentCount.setContentId(contentId);
					piContentCountUntBll.update(piContentCount);
					resultMap.put("views", views);
				}
			}
			String tempPath = (String) resultMap.get("img_path");
			if (StringUtils.isNotBlank(tempPath)) {
				tempPath = fileUtil.getPathByFileName(tempPath).get("linkPath") + tempPath;
				resultMap.put("img_path", tempPath);
			}

			// 判断该篇文章是否已经收藏过
			String isInFavorite = isInFavorite(contentId,userId) ? "0" : "1";
			resultMap.put("isInFavorite", isInFavorite);

		}
		// modelList.clear();
		// modelList.add(resultMap);
		resultModel.setData(resultMap);

		return resultModel;
	}

	private boolean isInFavorite(String contentId,String userId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT * FROM pi_user_favorite WHERE content_id =       				");
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(contentId)) {
			selectBuffer.append(contentId);
		}
		selectBuffer.append("'");
		if (StringUtils.isNotBlank(userId)) {
			selectBuffer.append("AND user_id = '" + userId + "'");
		}

		map.put("Sql", selectBuffer.toString());
		resultModel = piUserFavoriteUntBll.getListBySQL(map);
		Object obj = resultModel.getData();
		List<Map<String, Object>> resultList = null;


		List<Map<String,Object>> list = null;
		if(obj!=null) {
			resultList = (List<Map<String, Object>>) obj;
			if(resultList.size()>=1) {
				Map<String,Object> tempMap = resultList.get(0);
				if(tempMap!= null){
					return true;
				}
			}
		}
	
//		if (obj != null) {
//			resultList = (List<Map<String, Object>>) obj;
//			if (resultList.size() >= 1) {
//				return true;
//			}
//		}
		return false;
	}
	
	@ApiOperation(value = "获取网站声明、关于我们、联系我们的文章详情", notes = "获取网站声明、关于我们、联系我们的文章详情")
	@RequestMapping(value = "/getWebSiteBaseInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getWebSiteBaseInfo() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		String contentId = "";

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			JSONObject entityRelatedObject = null;
			if (json.containsKey("entityRelated")) {
				entityRelatedObject = json.getJSONObject("entityRelated");
			}
			if (entityRelatedObject.containsKey("contentId")) {
				contentId = entityRelatedObject.getString("contentId");
			}
		}
		
		/*SELECT 
		A.`id` AS id,
		B.title AS title,
		B.`author` AS author,
		B.`release_date` AS releaseDate,
		C.`txt` AS txt,
		D.`views` AS views
		FROM 
		pi_content A 
		LEFT JOIN pi_content_ext B ON A.`id` = B.content_id
		LEFT JOIN pi_content_txt C ON A.`id` = C.content_id
		LEFT JOIN pi_content_count D ON A.`id` = D.content_id
		WHERE A.`id`='aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa'*/
		
		selectBuffer.append(" SELECT 																		");
		selectBuffer.append(" A.`id` AS id,																	");
		selectBuffer.append(" B.title AS title,																");
		selectBuffer.append(" B.`author` AS author,															");
		selectBuffer.append(" B.`release_date` AS releaseDate,												");
		selectBuffer.append(" C.`txt` AS txt,																");
		selectBuffer.append(" D.`views` AS views															");
		selectBuffer.append(" FROM 																			");
		selectBuffer.append(" pi_content A 																	");
		selectBuffer.append(" LEFT JOIN pi_content_ext B ON A.`id` = B.content_id							");
		selectBuffer.append(" LEFT JOIN pi_content_txt C ON A.`id` = C.content_id							");
		selectBuffer.append(" LEFT JOIN pi_content_count D ON A.`id` = D.content_id							");
		selectBuffer.append(" WHERE A.`id`=");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(contentId)) {
			selectBuffer.append(contentId);
		}
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = piContentUntBll.getListBySQL(map);
		return resultModel;
	}

}
