package com.zee.app.extend.swagger.pi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.extend.swagger.gp.GpResourceSwgApp;
import com.zee.app.generate.swagger.pi.PiInteractionGenSwgApp;
import com.zee.bll.extend.unity.gp.GpUserUntBll;
import com.zee.bll.extend.unity.pi.PiInteractionUntBll;
import com.zee.bll.extend.unity.pi.PirInteractionImageUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.extend.pi.PiInteraction;
import com.zee.ent.extend.pi.PirInteractionImage;
import com.zee.ent.parameter.pi.PiInteractionParameter;
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
 * @updateDate 2018-7-5 11:35:44
 * @description pi_interaction 对外接口，扩展自PiInteractionGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piInteraction")
public class  PiInteractionSwgApp extends  PiInteractionGenSwgApp {
	@Autowired
	@Qualifier("piInteractionUntBll")
	protected PiInteractionUntBll piInteractionUntBll;
	
	@Autowired
	@Qualifier("pirInteractionImageUntBll")
	protected PirInteractionImageUntBll pirInteractionImageUntBll;
	
	@Autowired
	@Qualifier("gpUserUntBll")
	protected GpUserUntBll gpUserUntBll;
	
	@Autowired
	protected GpResourceSwgApp gpResourceSwgApp;
	
	@Value("${upload.linkPath}")
	public String linkPath;// 访问地址

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "PiInteraction") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody PiInteraction jsonData) {
		
		GpUser currentUser = this.getCurrentUser();
		String id = currentUser.getId();
		
		jsonData.setResUserId(id);
		
		ResultModel result = piInteractionUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "PiInteractionUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody PiInteractionParameter.UpdateList jsonData) {
		ResultModel result = piInteractionUntBll.updateList(jsonData);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = piInteractionUntBll.getModel(id);

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append("select A.id id,A.interaction_type interactionType,case when A.interaction_type = '1' then '问专家' when "); 
		selectBuffer.append("A.interaction_type = '2' then '留建言' else '其他' end interactionText,A.nick_name nickName,A.real_name realName, ");
		selectBuffer.append("A.job job,A.phone phone,A.email email,A.qq qq,A.region_id regionId,A.region_name regionName,A.title title, ");
		selectBuffer.append("A.req_content reqContent,A.res_user_id resUserId,A.res_content resContent,A.audit_status_code auditStatusCode, ");
		selectBuffer.append("A.audit_status_text auditStatusText,A.auditer_suggestion auditerSuggestion,A.auditer_user_id auditerUserId, ");
		selectBuffer.append("A.auditer_time auditerTime,A.add_time addTime,A.update_time updateTime,A.remark remark  from pi_interaction A ");
		selectBuffer.append("where A.id = '"+id+"'");
		
		map.put("Sql", selectBuffer.toString());
		result = piInteractionUntBll.getListBySQL(map);
		List<Map<String, Object>> list=(List<Map<String, Object>>)result.getData();
		
		if(list.get(0).get("resUserId")== null || list.get(0).get("resUserId").toString().equals("")){
			result.setData(list.get(0));
		}else{
			String userId = list.get(0).get("resUserId").toString();
			
			selectBuffer = new StringBuffer();
			selectBuffer.append("SELECT t.user_name userName FROM gp_user t WHERE t.id = '" + userId + "'");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpUserUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
			String userName = modelList.get(0).get("userName").toString();
			
			list.get(0).put("resUserId", userName);
			result.setData(list.get(0));
		}
		
		return result;
	
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = piInteractionUntBll.getModel(id);

		PiInteraction piInteraction = (PiInteraction)result.getData();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append("select A.id id,A.interaction_type interactionType,case when A.interaction_type = '1' then '问专家' when "); 
		selectBuffer.append("A.interaction_type = '2' then '留建言' else '其他' end interactionText,A.nick_name nickName,A.real_name realName, ");
		selectBuffer.append("A.job job,A.phone phone,A.email email,A.qq qq,A.region_id regionId,A.region_name regionName,A.title title, ");
		selectBuffer.append("A.req_content reqContent,A.res_user_id resUserId,A.res_content resContent,A.audit_status_code auditStatusCode, ");
		selectBuffer.append("A.audit_status_text auditStatusText,A.auditer_suggestion auditerSuggestion,A.auditer_user_id auditerUserId, ");
		selectBuffer.append("A.auditer_time auditerTime,A.add_time addTime,A.update_time updateTime,A.remark remark  from pi_interaction A ");
		selectBuffer.append("where A.id = '"+piInteraction.getId()+"'");
		
		map.put("Sql", selectBuffer.toString());
		result = piInteractionUntBll.getListBySQL(map);
		List<Map<String, Object>> list=(List<Map<String, Object>>)result.getData();

		if(list.get(0).get("resUserId")== null || list.get(0).get("resUserId").toString().equals("")){
			result.setData(list.get(0));
		}else{
			String userId = list.get(0).get("resUserId").toString();
			
			selectBuffer = new StringBuffer();
			selectBuffer.append("SELECT t.user_name userName FROM gp_user t WHERE t.id = '" + userId + "'");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = gpUserUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
			String userName = modelList.get(0).get("userName").toString();
			
			list.get(0).put("resUserId", userName);
			result.setData(list.get(0));
		}
		
		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "PiInteractionGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody PiInteractionParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = piInteractionUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.interaction_type interactionType,case when A.interaction_type = '1' then '问专家' when "); 
		selectBuffer.append("A.interaction_type = '2' then '留建言' else '其他' end interactionText,A.nick_name nickName,A.real_name realName, ");
		selectBuffer.append("A.job job,A.phone phone,A.email email,A.qq qq,A.region_id regionId,A.region_name regionName,A.title title, ");
		selectBuffer.append("A.req_content reqContent,A.res_user_id resUserId,A.res_content resContent,A.audit_status_code auditStatusCode, ");
		selectBuffer.append("A.audit_status_text auditStatusText,A.auditer_suggestion auditerSuggestion,A.auditer_user_id auditerUserId, ");
		selectBuffer.append("A.auditer_time auditerTime,A.add_time addTime,A.update_time updateTime,A.remark remark,case when A.res_content is not null "); 
		selectBuffer.append("then '已回复' else '待回复' end isReplied from pi_interaction A ");
		selectBuffer.append("inner join pi_interaction B on A.id=B.id where 1=1");
        
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
                
				if (entityRelatedObject.containsKey("interactionType") && StringUtils.isNotBlank(entityRelatedObject.getString("interactionType")))
					selectBuffer.append(" and A.interaction_type like '%").append(entityRelatedObject.getString("interactionType")).append("%'");
				if (entityRelatedObject.containsKey("title") && StringUtils.isNotBlank(entityRelatedObject.getString("title")))
					selectBuffer.append(" and A.title like '%").append(entityRelatedObject.getString("title")).append("%'");
				if (entityRelatedObject.containsKey("realName") && StringUtils.isNotBlank(entityRelatedObject.getString("realName")))
					selectBuffer.append(" and A.real_name like '%").append(entityRelatedObject.getString("realName")).append("%'");
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

		resultModel = piInteractionUntBll.getListBySQL(map);

		return resultModel;
	}
	/**
	 * 添加 问专家/留建言 记录
	 * @param jsonData
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "添加记录", notes = "添加 问专家/建留言 记录")
	@RequestMapping(value = "/addInteraction", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addInteraction() {
		
		ResultModel resultModel = new ResultModel();
		
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		
		PiInteraction piInteraction = new PiInteraction();
		
		String interactionId = Tools.getUUID();
		String interactionType = "";
		String nickName = "";
		String realName = "";
		String job = "";
		String phone = "";
		String email = "";
		String regionId = "";
		String regionName = "";
		String title = "";
		String reqContent = "";
		String fileIdStr = "";
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
				interactionType = entityRelatedObject.getString("interactionType");
				nickName = entityRelatedObject.getString("nickName");
				realName = entityRelatedObject.getString("realName");
				job = entityRelatedObject.getString("job");
				phone = entityRelatedObject.getString("phone");
				email = entityRelatedObject.getString("email");
				regionId = entityRelatedObject.getString("regionId");
				regionName = entityRelatedObject.getString("regionName");
				title = entityRelatedObject.getString("title");
				reqContent = entityRelatedObject.getString("reqContent");
				fileIdStr = entityRelatedObject.getString("fileIdStr");
			}
		}
		
		String[] fileIdArray = fileIdStr.split(",");
		
		piInteraction.setId(interactionId);
		piInteraction.setInteractionType((byte) Integer.parseInt(interactionType));
		piInteraction.setNickName(nickName);
		piInteraction.setRealName(realName);
		piInteraction.setJob(job);
		piInteraction.setPhone(phone);;
		piInteraction.setEmail(email);
		piInteraction.setRegionId(regionId);
		piInteraction.setRegionName(regionName);
		piInteraction.setTitle(title);
		piInteraction.setReqContent(reqContent);
		resultModel = piInteractionUntBll.add(piInteraction);
		
		if(fileIdArray!= null || (fileIdArray== null && fileIdArray.length != 0)){
			//新增互动图片关系表
			for (int i = 0; i < fileIdArray.length; i++) {
				addInteractionImage(interactionId,fileIdArray[i]);
			}
		}
		
		return resultModel;
	}
	
	/**
	 * 添加 互动图片关系表 数据
	 * @param interactionId
	 * @param resourceId
	 */
	private void addInteractionImage(String interactionId, String resourceId) {
		// 给 互动图片关系表 添加数据
		PirInteractionImage pirInteractionImage;
		if (StringUtils.isNotBlank(resourceId)) {
			pirInteractionImage = new PirInteractionImage();
			pirInteractionImage.setId(Tools.getUUID());
			pirInteractionImage.setInteractionId(interactionId);
			pirInteractionImage.setResourceId(resourceId);
//			pirInteractionImage.setIsTitleImgCode(SymbolicConstant.DCODE_BOOLEAN_T);
			pirInteractionImageUntBll.add(pirInteractionImage);
		}
	}
	
	/**
	 * 查询问专家/留建言列表
	 * @param jsonData
	 */
	@ApiOperation(value = "查询问专家/留建言列表", notes = "根据查询条件查询问专家/留建言列表")
	@RequestMapping(value = "/getInteractionList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getInteractionList() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select T1.*,T2.user_name resUserName from (");
		selectBuffer.append("select A.id id,A.interaction_type interactionType,A.nick_name nickName,A.real_name realName,A.job job,A.phone phone,");
		selectBuffer.append("A.email email,A.qq qq,A.region_id regionId,A.region_name regionName,A.title title,A.req_content reqContent,");
		selectBuffer.append("A.res_user_id resUserId,A.res_content resContent,A.audit_status_code auditStatusCode,A.audit_status_text auditStatusText,");
		selectBuffer.append("A.auditer_suggestion auditerSuggestion,A.auditer_user_id auditerUserId,A.auditer_time auditerTime,A.add_time addTime,");
		selectBuffer.append("A.update_time updateTime,A.remark remark  from pi_interaction A inner join pi_interaction B on A.id=B.id where 1=1 ");
//		selectBuffer.append(" and A.audit_status_code = '1' "); 去掉审核限制

        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("interactionType") && StringUtils.isNotBlank(entityRelatedObject.getString("interactionType")))
					if(entityRelatedObject.getString("interactionType")=="1"){
						selectBuffer.append(" and A.interaction_type like '%").append(entityRelatedObject.getString("interactionType")).append("%'");
						selectBuffer.append(" and A.res_content is not null");
					}else{
						selectBuffer.append(" and A.interaction_type like '%").append(entityRelatedObject.getString("interactionType")).append("%'");
					}
				if (entityRelatedObject.containsKey("id") && StringUtils.isNotBlank(entityRelatedObject.getString("id")))
					selectBuffer.append(" and A.id in ('").append(entityRelatedObject.getString("id")).append("')");
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

		selectBuffer.append(") T1 left join gp_user T2 on T1.resUserId = T2.id ");
		
		map.put("Sql", selectBuffer.toString());

		resultModel = piInteractionUntBll.getListBySQL(map);
		maps.put("data", (List<Map<String, Object>>) resultModel.getData());
		
		Map<String, Object> basicdataMap;
		List<Object> basicdataList = new ArrayList<Object>();;
		List<Object> imageList;

		for (Map<String, Object> map1 : maps.get("data")) {
			basicdataMap = new HashMap<String, Object>();
			basicdataMap.put("id", map1.get("id").toString());
			basicdataMap.put("title", map1.get("title").toString());
			basicdataMap.put("addTime", map1.get("addTime").toString());
			basicdataMap.put("nickName", map1.get("nickName").toString());
			basicdataMap.put("reqContent", map1.get("reqContent")==null?"":map1.get("reqContent").toString());
			basicdataMap.put("resUserName", map1.get("resUserName")==null?"":map1.get("resUserName").toString());
			basicdataMap.put("resContent", map1.get("resContent")==null?"":map1.get("resContent").toString());
			
			String interactionId = map1.get("id").toString();
			Map<String, Object> imageMap = new HashMap<String, Object>();
			Map<String, List<Map<String, Object>>> imageMaps = new HashMap<String, List<Map<String, Object>>>();
			selectBuffer = new StringBuffer();
			selectBuffer.append("select C.path imagePath from pi_interaction A,pir_interaction_image B,gp_resource C where ");
			selectBuffer.append("A.id = B.interaction_id and B.resource_id = C.id and A.id = '"+interactionId+"' order by C.priority ");

			imageMap.put("Sql", selectBuffer.toString());

			ResultModel resultModel1 = piInteractionUntBll.getListBySQL(imageMap);
			imageMaps.put("imageData", (List<Map<String, Object>>) resultModel1.getData());
			
			imageList = new ArrayList<Object>();
			for (Map<String, Object> map2 : imageMaps.get("imageData")) {
				String imagePath = linkPath + map2.get("imagePath").toString();
				imageList.add(imagePath);
			}
			basicdataMap.put("imagePath", imageList);
			basicdataList.add(basicdataMap);
		}

		resultModel.setData(basicdataList);

		return resultModel;
	}
	
	/**
	 * 查询互动次数
	 * @param 
	 */
	@ApiOperation(value = "查询互动次数", notes = "查询互动次数")
	@RequestMapping(value = "/getInteractionNum", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getInteractionNum() {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select count(*) num from (");
		selectBuffer.append("select A.id id,A.interaction_type interactionType,A.nick_name nickName,A.real_name realName,A.job job,A.phone phone,");
		selectBuffer.append("A.email email,A.qq qq,A.region_id regionId,A.region_name regionName,A.title title,A.req_content reqContent,");
		selectBuffer.append("A.res_user_id resUserId,A.res_content resContent,A.audit_status_code auditStatusCode,A.audit_status_text auditStatusText,");
		selectBuffer.append("A.auditer_suggestion auditerSuggestion,A.auditer_user_id auditerUserId,A.auditer_time auditerTime,A.add_time addTime,");
		selectBuffer.append("A.update_time updateTime,A.remark remark  from pi_interaction A inner join pi_interaction B on A.id=B.id where ");
		selectBuffer.append("(A.interaction_type = '1' and A.res_content is not null) or A.interaction_type = '2' ) T1 left join gp_user T2 on T1.resUserId = T2.id ");
		
		map.put("Sql", selectBuffer.toString());

		resultModel = piInteractionUntBll.getListBySQL(map);

		return resultModel;
	}
}



