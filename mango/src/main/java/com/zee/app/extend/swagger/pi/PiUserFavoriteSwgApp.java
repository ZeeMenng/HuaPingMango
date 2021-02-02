package com.zee.app.extend.swagger.pi;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiUserFavoriteGenSwgApp;
import com.zee.bll.extend.unity.pi.PiUserFavoriteUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiUserFavorite;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.TimesView;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-15 13:54:46
 * @description pi_user_favorite 对外接口，扩展自PiUserFavoriteGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piUserFavorite")
public class  PiUserFavoriteSwgApp extends  PiUserFavoriteGenSwgApp {

	@Autowired
	protected PiUserFavoriteUntBll piUserFavoriteUntBll;
	
	@ApiOperation(value = "将文章增加到我的收藏", notes = "将文章增加到我的收藏")
	@RequestMapping(value = "/addContentToUserFavorite", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addContentToUserFavorite() {

		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}
		
		String userId = "";
		String channelId = "";
		String channelName = "";
		String contentId = "";
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("channelId")) {
					channelId = entityRelatedObject.getString("channelId");
				}
				if (entityRelatedObject.containsKey("channelName")) {
					channelName = entityRelatedObject.getString("channelName");
				}
				if (entityRelatedObject.containsKey("contentId")) {
					contentId = entityRelatedObject.getString("contentId");
				}
				if (entityRelatedObject.containsKey("userId")) {
					userId = entityRelatedObject.getString("userId");
				}else{
					userId = this.getCurrentUser().getId();
				}
			}
		}
		
		//同一个栏目下的同一篇文章不能重复收藏
		if(StringUtils.isBlank(getId(userId,channelId,contentId))) {
			PiUserFavorite piUserFavorite = new PiUserFavorite();
			piUserFavorite.setUserId(userId);
			piUserFavorite.setChannelId(channelId);
			piUserFavorite.setChannelName(channelName);
			piUserFavorite.setContentId(contentId);
			piUserFavorite.setAddTime(new Date());
			resultModel = piUserFavoriteUntBll.add(piUserFavorite);
		}else {
			resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		}
		return 	resultModel;
	}
	
	@ApiOperation(value = "将文章从我的收藏移除", notes = "将文章从我的收藏移除")
	@RequestMapping(value = "/deleteContentFromUserFavorite", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteContentFromUserFavorite() {
		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String userId = "";
		String channelId = "";
		String contentId = "";
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("contentId") && StringUtils.isNotBlank(entityRelatedObject.getString("contentId")))
					contentId = entityRelatedObject.getString("contentId");
				if (entityRelatedObject.containsKey("channelId") && StringUtils.isNotBlank(entityRelatedObject.getString("channelId")))
					channelId = entityRelatedObject.getString("channelId");
				if (entityRelatedObject.containsKey("userId")) {
					userId = entityRelatedObject.getString("userId");
				}else{
					userId = this.getCurrentUser().getId();
				}
			}
		}
		
		String id = getId(userId,channelId,contentId);
		resultModel = piUserFavoriteUntBll.delete(id);
		return resultModel;
	}
	
	private String getId( String userId,String channelId, String contentId) {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		//由于一篇文章可以发布到多个栏目下，所以，删除收藏时，不能只看content_id，还应该同时考虑channel_id 
		
		/*SELECT * FROM pi_user_favorite WHERE
		user_id = 'aasfdssdfsd189hfghhfg05ffghghfgh'
		AND channel_id = '061f1ee79c1ae60be0a089227de9af7d'
		AND content_id = 'ce29366ba1d84dc4bdf6e7920583bc63'*/
		
		selectBuffer.append(" SELECT * FROM pi_user_favorite WHERE				");
		selectBuffer.append(" user_id =											");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(userId)) {
			selectBuffer.append(userId);
		}
		selectBuffer.append("'");
//		selectBuffer.append(" AND channel_id =									");
//		selectBuffer.append("'");
//		if(StringUtils.isNotBlank(channelId)) {
//			selectBuffer.append(channelId);
//		}
//		selectBuffer.append("'");
		selectBuffer.append(" AND content_id =									");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(contentId)) {
			selectBuffer.append(contentId);
		}
		selectBuffer.append("'");
		map.put("Sql", selectBuffer.toString());
		resultModel = piUserFavoriteUntBll.getListBySQL(map);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		
		Object obj = resultModel.getData();
		List<Map<String,Object>> list = null;
		if(obj!=null) {
			list = (List<Map<String, Object>>) obj;
			if(list.size()>=1) {
				Map<String,Object> tempMap = list.get(0);
				if(tempMap.containsKey("id")) {
					String id = (String) tempMap.get("id");
					return id;
				}
			}
		}
		return null;
	}

	@ApiOperation(value = "获取我的收藏的栏目列表", notes = "获取我的收藏的栏目列表")
	@RequestMapping(value = "/getUserFavoriteChannelList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getUserFavoriteChannelList() {
		
		ResultModel resultModel = new ResultModel();
		
		/*SELECT A.channel_name AS channelName
		 FROM pi_user_favorite A
		 WHERE A.user_id='aasfdssdfsd189hfghhfg05ffghghfgh'
		 GROUP BY channel_id;*/
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append(" SELECT A.channel_name AS channelName,							");
		selectBuffer.append(" A.channel_id AS channelId										");
		selectBuffer.append(" FROM pi_user_favorite A										");
		selectBuffer.append(" WHERE A.user_id=												");
		selectBuffer.append("'");
		selectBuffer.append(this.getCurrentUser().getId());
		selectBuffer.append("'");
		selectBuffer.append(" GROUP BY channel_id											");

		map.put("Sql", selectBuffer.toString());
		resultModel = piUserFavoriteUntBll.getListBySQL(map);
		
		/*List<String> resultList = new ArrayList<String>();
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if (object != null)
			objectList = (List<Map<String, Object>>) object;
		if (objectList != null && objectList.size() >= 1) {
			for(Map<String, Object> tempMap : objectList) {
				if(tempMap.containsKey("channelName")) {
					String channelName = (String) tempMap.get("channelName");
					resultList.add(channelName);
				}
			}
		}
		resultModel.setData(resultList);*/
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return 	resultModel;
	}
	
	@ApiOperation(value = "获取我的收藏中某个栏目下的文章列表", notes = "获取我的收藏中某个栏目下的文章列表")
	@RequestMapping(value = "/getContentListFromUserFavorite", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getContentListFromUserFavorite() {
		
		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		String channelId = "";
		String userId = "";
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("channelId") && StringUtils.isNotBlank(entityRelatedObject.getString("channelId")))
					channelId = entityRelatedObject.getString("channelId");
				if (entityRelatedObject.containsKey("userId") && StringUtils.isNotBlank(entityRelatedObject.getString("userId")))
					userId = entityRelatedObject.getString("userId");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*
		 SELECT A.content_id AS contentId,
			B.`title` AS title
		 FROM pi_user_favorite A,pi_content_ext B
		 WHERE A.content_id = B.`content_id`
		 AND A.channel_id = '1da6e5de08b7eabeb97bff0ad7384c7c'; 
		*/
		selectBuffer.append(" SELECT A.content_id AS contentId,								");
		selectBuffer.append(" B.`title` AS title											");
		selectBuffer.append(" FROM pi_user_favorite A,pi_content_ext B						");
		selectBuffer.append(" WHERE A.content_id = B.`content_id`							");
		selectBuffer.append(" AND A.channel_id =											");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(channelId)) {
			selectBuffer.append(channelId);
		}
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(channelId)) {
			selectBuffer.append("AND A.user_id = '" + userId + "'");
		}
		map.put("Sql", selectBuffer.toString());
		resultModel = piUserFavoriteUntBll.getListBySQL(map);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return 	resultModel;
	}
	
}



