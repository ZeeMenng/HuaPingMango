package com.jusfoun.app.extend.swagger.pi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.pi.PiChannelGenSwgApp;
import com.jusfoun.bll.extend.unity.gp.GpDictionaryUntBll;
import com.jusfoun.bll.extend.unity.pi.PiChannelExtUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.pi.PiChannel;
import com.jusfoun.ent.extend.pi.PiChannelExt;
import com.jusfoun.set.exception.GlobalException;
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
 * @description CMS栏目表 对外接口，扩展自PiChannelGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piChannel")
public class PiChannelSwgApp extends PiChannelGenSwgApp {

	@Autowired
	@Qualifier("piChannelExtUntBll")
	protected PiChannelExtUntBll piChannelExtUntBll;
	@Autowired
	protected GpDictionaryUntBll gpDictionaryUntBll;

	private String invariableChannelId;
	private String currentChannelExtId;
	private String oldParentChannelId;
	private String oldRelationChannelId;
	private String newRelationChannelId;
	
	@ApiOperation(value = "新增记录", notes = "新增一条栏目")
	@RequestMapping(value = "/addChannel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultModel addChannel() throws Exception {
		ResultModel resultModel = new ResultModel();
//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		String strJson = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		String jsonData = new String(strJson.getBytes("ISO-8859-1"),"UTF-8");
//		System.out.println(jsonData);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}

		String channelName = null;
		String channelPath = null;
		String channelId = null;
		int isDisplay = 0;
		int hasContent = 0;

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("channelName")) {
				channelName = jsonObject.getString("channelName");
			}
			if (jsonObject.containsKey("channelPath")) {
				channelPath = jsonObject.getString("channelPath");
			}
			if (jsonObject.containsKey("channelId")) {
				channelId = jsonObject.getString("channelId");
			}
			if (jsonObject.containsKey("isDisplay")) {
				isDisplay = Integer.parseInt(jsonObject.getString("isDisplay"));
			}
			if (jsonObject.containsKey("hasContent")) {
				hasContent = Integer.parseInt(jsonObject.getString("hasContent"));
			}
			
			//1、根据当前获取的channelId查询出relation_id(都是所要新增的栏目的父栏目的数据)
			String tempRelationId = getRelationId(channelId);
			//2、计算出需要插入的channel的relation_id
			String relationId = CalculatRelationId(tempRelationId);
			//3、往pi_channel表中插入数据
			PiChannel piChannel = new PiChannel();
			piChannel.setParentId(channelId);
			piChannel.setChannelPath(channelPath);
			piChannel.setIsDisplay((byte)isDisplay);
			piChannel.setHasContent((byte)hasContent);
			piChannel.setRelationId(relationId);
			
			resultModel = piChannelUntBll.add(piChannel);
			String tempChannelId = resultModel.getObjectId();
			System.out.println(tempChannelId);
			
			//4、往pi_channel_ext表中插入数据
			PiChannelExt piChannelExt = new PiChannelExt();
			piChannelExt.setChannelId(tempChannelId);
			piChannelExt.setChannelName(channelName);
			resultModel = piChannelExtUntBll.add(piChannelExt);
		}

		return resultModel;
	}

	private String CalculatRelationId(String tempRelationId) {
		//首先需要根据当前relationId获取子栏目中需要使用的relationId
		String maxRelationId = getMaxRelationId(tempRelationId);
		return maxRelationId;
	}

	private String getMaxRelationId(String tempRelationId) {
		
		int len = tempRelationId.length()+2;
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.relation_id relationId 												   ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("   WHERE relation_id like        											   ");
		selectBuffer.append("'");
		selectBuffer.append(tempRelationId														);
		selectBuffer.append("%'");
		selectBuffer.append("   AND															   ");
		selectBuffer.append("   LENGTH(relation_id) = 				   ");
		selectBuffer.append(len);
		
		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);

		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if (object != null)
			objectList = (List<Map<String, Object>>) object;
		if (objectList != null) {
			if(objectList.size()==0) {
				return tempRelationId+"01";
			}
			int maxRelId = 0;
			for(int i = 0;i<objectList.size();i++) {
				objMap = objectList.get(i);
				JSONObject jsonData = JSONObject.fromObject(objMap);
				// 解析出需要的id值
				// relation_id
				if (jsonData.containsKey("relationId")) {
					String relId = jsonData.getString("relationId");
					relId = relId.substring(1, relId.length());
					if(Integer.parseInt(relId)>maxRelId) {
						maxRelId = Integer.parseInt(relId);
					}
				}
			}
			return "0"+(maxRelId+1);
		}
		return "";
	}

	@ApiOperation(value = "查询栏目", notes = "查询栏目")
	@RequestMapping(value = "/getChannel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannel() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.id id, 												   ");
		selectBuffer.append("		a.id channel_id,                                           ");
		selectBuffer.append("		a.channel_path,                                            ");
		selectBuffer.append(" 		a.relation_id AS relationId,							   ");
		selectBuffer.append("		b.channel_name                                             ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("	INNER JOIN pi_channel_ext b ON a.id = b.channel_id             ");
		selectBuffer.append("   WHERE 1=1       											   ");
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("channelLevel")
						&& StringUtils.isNotBlank(entityRelatedObject.getString("channelLevel"))) {
					String channelLevel = entityRelatedObject.getString("channelLevel");
					Integer levelLength = Integer.parseInt(channelLevel) * 2;
					selectBuffer.append("AND LENGTH(a.relation_id) = " + levelLength);
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
					selectBuffer.append(orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		return resultModel;
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
		// selectBuffer.append("select A.id id,A.model_id modelId,A.site_id
		// siteId,A.parent_id parentId,A.channel_path channelPath,A.lft lft,A.rgt
		// rgt,A.priority priority,A.has_content hasContent,A.is_display isDisplay from
		// pi_channel A inner join pi_channel B on A.id=B.id where 1=1 ");

		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.id id, 												   ");
		selectBuffer.append("		a.id channelId,                                            ");
		selectBuffer.append("		a.channel_path channelPath,                                ");
		selectBuffer.append("		e.channel_name channelName                                 ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("	LEFT JOIN pi_channel_ext e ON e.`channel_id`=a.`id`            ");
		selectBuffer.append("   WHERE 1=1       											   ");

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and a.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'"
								: selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");

				if (entityRelatedObject.containsKey("channelPath")
						&& StringUtils.isNotBlank(entityRelatedObject.getString("channelPath")))
					selectBuffer.append(" and a.channel_path like '%")
							.append(entityRelatedObject.getString("channelPath")).append("%'");
				if (entityRelatedObject.containsKey("channelName")
						&& StringUtils.isNotBlank(entityRelatedObject.getString("channelName")))
					selectBuffer.append(" and e.channel_name like '%").append(entityRelatedObject.getString("channelName")).append("%'");
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

		resultModel = piChannelUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "查询", notes = "查询一级栏目信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "PiContentTypeGetList") })
	@RequestMapping(value = "/getFirChannelList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFirChannelList() {
		ResultModel resultModel = new ResultModel();

		// String jsonData =
		// request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		// if (StringUtils.isBlank(jsonData))
		// return resultModel;
		// JSONObject json = JSONObject.fromObject(jsonData);
		//
		// if (json.containsKey("fartherId")) {
		// String fartherId = json.getString("fartherId");
		// System.out.println(fartherId);
		// }

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		// selectBuffer.append("select e.channel_id id,e.channel_name name from
		// pi_channel_ext e where 1=1 ");

		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.id channelId,                                            ");
		selectBuffer.append("		a.relation_id relationId, 								   ");
		selectBuffer.append("		e.channel_name channelName                                 ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("	LEFT JOIN pi_channel_ext e ON e.`channel_id`=a.`id`            ");

		// LENGTH(relation_id) < 5;
		selectBuffer.append("   WHERE LENGTH(relation_id) = 4      											   ");

		map.put("Sql", selectBuffer.toString());

		resultModel = piChannelUntBll.getListBySQL(map);

		return resultModel;
	}

	// @ApiOperation(value = "单条查询", notes =
	// "根据id查询pi_content、pi_content_ext、pi_content_txt的内容")
	// @ApiImplicitParam(paramType = "path", name = "id", value = "contentId",
	// required = true, dataType = "String")
	// @RequestMapping(value = "/getContentById/{id}", method = RequestMethod.GET,
	// produces = MediaType.APPLICATION_JSON_VALUE)
	// public ResultModel getContentById(@PathVariable("id") String id) {

	@ApiOperation(value = "查询", notes = "查询二级栏目信息")
	@ApiImplicitParam(paramType = "path", name = "firId", value = "firId", required = true, dataType = "String")
	@RequestMapping(value = "/getSecChannelList/{firId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getSecChannelList(@PathVariable("firId") String firId) {
		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(firId)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.id channelId,                                            ");
		selectBuffer.append("		a.parent_id parentId, 								   ");
		selectBuffer.append("		e.channel_name channelName                                 ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("	LEFT JOIN pi_channel_ext e ON e.`channel_id`=a.`id`            ");
		selectBuffer.append("   WHERE parent_id =     											   ");
		selectBuffer.append("'");
		selectBuffer.append(firId.split("=")[1]);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());

		resultModel = piChannelUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "查询", notes = "查询所在位置的完整级别栏目信息")
	@ApiImplicitParam(paramType = "path", name = "channel_id", value = "channelId", required = true, dataType = "String")
	@RequestMapping(value = "/getNavigationChannelList/{channel_id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getNavigationChannelList(@PathVariable("channel_id") String channelId) {
		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(channelId)) {
			return resultModel;
		}
		String relationId = getRelationId(channelId);
		if (StringUtils.isBlank(relationId)) {
			return resultModel;
		}
		int relationIdLen = relationId.length();

		List<Map<String, Object>> channelInfoList = new ArrayList<Map<String, Object>>();

		while (relationIdLen >= 2) {
			ResultModel tempResultModel = getChannelInfo(relationId);

			Object object = tempResultModel.getData();
			List<Map<String, Object>> objectList = null;
			Map<String, Object> objMap = null;
			if (object != null)
				objectList = (List<Map<String, Object>>) object;
			if (objectList != null && objectList.size() == 1) {
				objMap = objectList.get(0);
				channelInfoList.add(objMap);
			}
			relationIdLen -= 2;
			relationId = relationId.substring(0, relationIdLen);
		}
		//您的位置处，首页不用显示
//		channelInfoList.remove(channelInfoList.size()-1);
		resultModel.setIsSuccessCode(SymbolicConstant.DCODE_BOOLEAN_T);
		resultModel.setData(channelInfoList);
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
		if(channelId.contains("=")) {
			selectBuffer.append(channelId.split("=")[1]);
		}else {
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

	private ResultModel getChannelInfo(String relationId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.id id, 												   ");
		selectBuffer.append("		a.relation_id relationId, 								   ");
		selectBuffer.append("		a.channel_path channelPath, 							   ");
		selectBuffer.append("		e.channel_name channelName                                 ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("	LEFT JOIN pi_channel_ext e ON e.`channel_id`=a.`id`            ");
		selectBuffer.append("   WHERE a.relation_id =        								   ");
		selectBuffer.append("'");
		selectBuffer.append(relationId);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);

		return resultModel;
	}

	
	@ApiOperation(value = "单条查询", notes = "根据channelId查询pi_channel和pi_channel_ext的内容")
	@ApiImplicitParam(paramType = "path", name = "id", value = "channelId", required = true, dataType = "String")
	@RequestMapping(value = "/getChannelInfoById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannelInfoById(@PathVariable("id") String id) {
		ResultModel resultModel = getListByJsonData();
		if (StringUtils.isBlank(id)) {
			return resultModel;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		a.parent_id channelId,                                      ");
		selectBuffer.append("		a.channel_path channelPath,                                      ");
		selectBuffer.append("		a.relation_id relationId,                                    ");
		selectBuffer.append("		a.has_content hasContent,                                  ");
		selectBuffer.append("		a.is_display isDisplay,                                    ");
		selectBuffer.append("		b.channel_name channelName                                 ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel a                                               ");
		selectBuffer.append("	INNER JOIN pi_channel_ext b ON a.id = b.channel_id             ");
		selectBuffer.append("   WHERE a.id= '");
		selectBuffer.append(id);
		selectBuffer.append("'");
		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		
		
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			if (objectList != null && objectList.size()==1) {
				objMap = objectList.get(0);
				if(objMap.containsKey("channelId")) {
					oldParentChannelId = (String) objMap.get("channelId");
				}
				if(objMap.containsKey("relationId")) {
					oldRelationChannelId = (String) objMap.get("relationId");
				}
				resultModel.setData(objMap);
			}
		}
					
					
		/*if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("channelLevel")
						&& StringUtils.isNotBlank(entityRelatedObject.getString("channelLevel"))) {
					String channelLevel = entityRelatedObject.getString("channelLevel");
					Integer levelLength = Integer.parseInt(channelLevel) * 2;
					selectBuffer.append("AND LENGTH(a.relation_id) = " + levelLength);
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
					selectBuffer.append("a." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}
		map.put("Sql", selectBuffer.toString());*/
		
		return resultModel;
	}
	
	@ApiOperation(value = "修改栏目", notes = "修改指定栏目")
	@RequestMapping(value = "/updateChannel", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateChannel() throws Exception {
		ResultModel resultModel = new ResultModel();
		
		//首先获取需要更新的数据
		String strJson = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		String jsonData = new String(strJson.getBytes("ISO-8859-1"),"UTF-8");
		
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}
		
		//由于栏目和文章有关联，所以当前channel的id，即invariableChannelId的值，在操作过程中不能改变
		//首先考虑计算新增栏目的relation_id值
		//如果所选择的父栏目id即oldParentChannelId没变，则新增栏目还用原来的relation_id值
		//如果重新选择了所属父栏目，则新增栏目的relation_id值需要重新计算
		
		//根据currentChannelId获取pi_channel_ext的id值
		currentChannelExtId = getChannelExtId(invariableChannelId);
		
		//删除pi_channel_ext表内容
		piChannelExtUntBll.delete(currentChannelExtId);
		
		String newChannelName = null;
		String newChannelPath = null;
		String newParentChannelId = null;
		int newIsDisplay = 0;
		int newHasContent = 0;

		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("channelName")) {
				newChannelName = jsonObject.getString("channelName");
			}
			if (jsonObject.containsKey("channelPath")) {
				newChannelPath = jsonObject.getString("channelPath");
			}
			if (jsonObject.containsKey("isDisplay")) {
				newIsDisplay = Integer.parseInt(jsonObject.getString("isDisplay"));
			}
			if (jsonObject.containsKey("hasContent")) {
				newHasContent = Integer.parseInt(jsonObject.getString("hasContent"));
			}
			//获取新的父栏目id
			if (jsonObject.containsKey("channelId")) {
				newParentChannelId = jsonObject.getString("channelId");
			}
			
			PiChannel piChannel = new PiChannel();
			piChannel.setId(invariableChannelId);
			piChannel.setChannelPath(newChannelPath);
			piChannel.setParentId(newParentChannelId);
			piChannel.setIsDisplay((byte)newIsDisplay);
			piChannel.setHasContent((byte)newHasContent);
			
			if(StringUtils.isNotBlank(newParentChannelId) && newParentChannelId.equals(oldParentChannelId)) {
				//说明栏目所属的父栏目未改变
				piChannel.setRelationId(oldRelationChannelId);
				resultModel = piChannelUntBll.update(piChannel);
			}else {
				//说明重新选择了所属父栏目
				//1、根据当前获取的channelId查询出relation_id(都是所要新增的栏目的父栏目的数据)
				String tempRelationId = getRelationId(newParentChannelId);
				//2、计算出需要插入的channel的relation_id
				newRelationChannelId = CalculatRelationId(tempRelationId);
				//3、更新pi_channel表数据
				piChannel.setRelationId(newRelationChannelId);
				resultModel = piChannelUntBll.update(piChannel);
			}
			
			//3、往pi_channel_ext表中插入数据
			PiChannelExt piChannelExt = new PiChannelExt();
			piChannelExt.setChannelId(invariableChannelId);
			piChannelExt.setChannelName(newChannelName);
			resultModel = piChannelExtUntBll.add(piChannelExt);
		}

		return resultModel;
	}
	
	private ResultModel modifyRelationId(String currentChannelId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();

		selectBuffer.append("	UPDATE pi_channel SET relation_id=                                     ");
		selectBuffer.append("'");
		selectBuffer.append("0101");
		selectBuffer.append("'");
		selectBuffer.append("	WHERE id=                                                           ");
		selectBuffer.append("'");
		selectBuffer.append(currentChannelId);
		selectBuffer.append("'");
		
		map.put("Sql", selectBuffer.toString());

		resultModel = piChannelUntBll.getListBySQL(map);
		return resultModel;
	}

	private String getChannelExtId(String channelId) {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		e.id eId 												   ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		pi_channel_ext e                                               ");
		selectBuffer.append("   WHERE e.channel_id =        											   ");
		selectBuffer.append("'");
		if(channelId.contains("=")) {
			selectBuffer.append(channelId.split("=")[1]);
		}else {
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
			// eId
			if (jsonData.containsKey("eId")) {
				return jsonData.getString("eId");
			}
		}
		return null;
	}
	
	@ApiOperation(value = "获取树状结构channel数据", notes = "获取树状结构channel数据")
	@RequestMapping(value = "/getChannelNodes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannelNodes() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                               ");
		selectBuffer.append("		A.id id,                                         ");
		selectBuffer.append("		A.parent_id fartherId,                                  ");
		selectBuffer.append("		B.channel_name name,                              ");
		selectBuffer.append("		B.id bId                              ");
		selectBuffer.append("	FROM                                                 ");
		selectBuffer.append("		pi_channel A                                      ");
		selectBuffer.append("	LEFT JOIN pi_channel_ext B ON A.id = B.channel_id         ");
		selectBuffer.append("	WHERE channel_name IS NOT NULL       ");
		JSONObject jsonObject = JSONObject.fromObject(jsonData);
		if(jsonObject.containsKey("id") && StringUtils.isNotBlank(jsonObject.getString("id"))) {
			invariableChannelId = jsonObject.getString("id");
			selectBuffer.append("	AND A.id != ");
			selectBuffer.append("'");
			selectBuffer.append(jsonObject.getString("id"));
			selectBuffer.append("'");
		}
		
		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		List<Map<String, Object>> treeNodes = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map2 : modelList) {
			Map<String, Object> treeMap = new HashMap<String, Object>();
			treeMap.put("id", map2.get("id"));
			treeMap.put("pId", map2.get("fartherId"));
			treeMap.put("name", map2.get("name"));
			treeMap.put("open", true);
			treeNodes.add(treeMap);
		}
		resultModel.setData(treeNodes);
		return resultModel;
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "CMS栏目表列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询栏目的详细信息")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getChannelDetailsById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannelDetailsById(@PathVariable("id") String id) {
		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    								");
		selectBuffer.append("	a.id AS id,                                                 						");
		selectBuffer.append("	a.channel_path AS channelPath,                                                  	    				");
		selectBuffer.append("	a.has_content AS hasContent,                                                 	    				");
		selectBuffer.append("	a.is_display AS isDisplay,                                                 	    				");
		selectBuffer.append("	t.channel_name AS parentChannelName                                                 	    				");
		
		selectBuffer.append("FROM                                                      		        				");
		selectBuffer.append("	pi_channel a                                             	       				    ");
		selectBuffer.append("LEFT JOIN pi_channel_ext t ON a.parent_id = t.channel_id         		       					");
		
		selectBuffer.append("WHERE a.id =      												        				");
		selectBuffer.append("'");
		selectBuffer.append(id);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if(object!=null) {
			objectList = (List<Map<String, Object>>)object;
			if(objectList!=null && objectList.size()>=1) {
				objMap = (Map<String, Object>)objectList.get(0);
				
				JSONObject jsonData = JSONObject.fromObject(objMap);
				if (jsonData.containsKey("hasContent")) {
					String tempHasContent = jsonData.getString("hasContent");
					String hasContent = transfSqlData(SymbolicConstant.DI_BOOLEAN,tempHasContent);
					objMap.put("hasContent", hasContent);
				}
				if (jsonData.containsKey("isDisplay")) {
					String tempIsDisplay = jsonData.getString("isDisplay");
					String isDisplay = transfSqlData(SymbolicConstant.DI_BOOLEAN,tempIsDisplay);
					objMap.put("isDisplay", isDisplay);
				}
				
				resultModel.setData(objMap);
			}
		}
		return resultModel;
	}
	
	private String transfSqlData(String dictType,String isRecommend) {
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         		"); 
		selectBuffer.append("		d.text AS text 												   	"); 
		selectBuffer.append("	FROM                                                           		"); 
		selectBuffer.append("		gp_dictionary d                                               	");
		selectBuffer.append("   WHERE d.type_id =        											");
		selectBuffer.append("'");
		selectBuffer.append(dictType);
		selectBuffer.append("'");
		selectBuffer.append(" AND ");
		selectBuffer.append(" d.code = ");
		selectBuffer.append(isRecommend);

		map.put("Sql", selectBuffer.toString());
		
		resultModel = gpDictionaryUntBll.getListBySQL(map);
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		String text = "";
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			if(objectList.size()>=1) {
				objMap = objectList.get(0);
				JSONObject jsonData = JSONObject.fromObject(objMap);
				if (jsonData.containsKey("text")) {
					text = jsonData.getString("text");
				}
			}
		}	
		return text;
	}
	
	@ApiOperation(value = "获取栏目结构", notes = "获取栏目结构")
	@RequestMapping(value = "/getChannelStructure", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannelStructure() {
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		a.`id` AS channelId,
		a.`relation_id` AS relationId,
		b.`channel_name` AS channelName
		 FROM 
		pi_channel a 
		LEFT JOIN pi_channel_ext b ON a.`id`= b.`channel_id`
		ORDER BY relationId;*/
		
		selectBuffer.append(" SELECT 																");
		selectBuffer.append(" a.`id` AS channelId,													");
		selectBuffer.append(" a.`relation_id` AS relationId,										");
		selectBuffer.append(" b.`channel_name` AS channelName										");
		selectBuffer.append(" FROM 																	");
		selectBuffer.append(" pi_channel a 															");
		selectBuffer.append(" LEFT JOIN pi_channel_ext b ON a.`id`= b.`channel_id`					");
		selectBuffer.append(" ORDER BY relationId													");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelUntBll.getListBySQL(map);
		
		Object obj = resultModel.getData();
		List<Map<String,Object>> objList = null;
		Map<String,Object> objMap = null;
		if(obj!=null) {
			objList = (List<Map<String, Object>>) obj;
			objList.remove(0);
			if(objList.size()>=1) {
				for(Map<String,Object> tempMap : objList) {
					if(tempMap.containsKey("relationId")) {
						String relationId = (String) tempMap.get("relationId");
						int len = 0;
						if(StringUtils.isNotBlank(relationId) && !relationId.equals("01")) {
							len = relationId.length();
							String channelName = (String) tempMap.get("channelName");
							String cName = getChannelName(channelName,len);
							tempMap.put("channelName", cName);
						}
					}
				}
			}
		}
		
		return resultModel;
	}

	private String getChannelName(String channelName, int len) {
		int num = len/2;
		String cName = "|";
		for(int i = 0;i<num-1;i++) {
			cName+="--";
		}
		cName+=channelName;
		return cName;
	}

}
