package com.jusfoun.app.extend.swagger.pi;

import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.extend.swagger.gp.GpResourceSwgApp;
import com.jusfoun.app.generate.swagger.pi.PiExpertGenSwgApp;
import com.jusfoun.bll.extend.split.gp.GprUserIconSplBll;
import com.jusfoun.bll.extend.unity.gp.GpResourceUntBll;
import com.jusfoun.bll.extend.unity.gp.GpUserUntBll;
import com.jusfoun.bll.extend.unity.gp.GprUserIconUntBll;
import com.jusfoun.bll.extend.unity.gp.GprUserRoleUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpResource;
import com.jusfoun.ent.extend.gp.GpUser;
import com.jusfoun.ent.extend.gp.GprUserIcon;
import com.jusfoun.ent.extend.gp.GprUserRole;
import com.jusfoun.ent.extend.pi.PiExpert;
import com.jusfoun.ent.parameter.pi.PiExpertParameter;
import com.jusfoun.set.enumer.RoleEnum;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/7/11 17:38:33
 * @description 专家表 对外接口，扩展自PiExpertGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piExpert")
public class  PiExpertSwgApp extends  PiExpertGenSwgApp {
	
	@Autowired
	@Qualifier("gpUserUntBll")
	protected GpUserUntBll gpUserUntBll;

	@Autowired
	@Qualifier("gprUserRoleUntBll")
	protected GprUserRoleUntBll gprUserRoleUntBll;
	
	@Autowired
	@Qualifier("gprUserIconUntBll")
	protected GprUserIconUntBll gprUserIconUntBll;
	

	@Autowired
	@Qualifier("gprUserIconSplBll")
	protected GprUserIconSplBll gprUserIconSplBll;
	
	
	@Autowired
	@Qualifier("gpResourceUntBll")
	protected GpResourceUntBll gpResourceUntBll;

	@Autowired
	protected GpResourceSwgApp gpResourceSwgApp;
	
	@Value("${upload.linkPath}")
	public String linkPath;// 访问地址
	
	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "PiExpert") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody PiExpert jsonData) {
		
		GpUser gpUser = new GpUser();
		
		gpUser.setUserName(jsonData.getUserName());
		gpUser.setLoginCount(jsonData.getLoginCount());
		gpUser.setPassword(jsonData.getPassword());
		gpUser.setRealName(jsonData.getRealName());
		gpUser.setGenderCode(jsonData.getGenderCode());
		gpUser.setGenderValue(jsonData.getGenderValue());
		gpUser.setBirthTime(jsonData.getBirthTime());
		gpUser.setAge(jsonData.getAge());
		gpUser.setIsMarriageCode(jsonData.getIsMarriageCode());
		gpUser.setIsMarriageValue(jsonData.getIsMarriageValue());
		gpUser.setPhone(jsonData.getPhone());
		gpUser.setEmail(jsonData.getEmail());
		gpUser.setQq(jsonData.getQq());
		gpUser.setAddTime(new Date());
		gpUser.setUpdateTime(new Date());
		gpUser.setRemark(jsonData.getRemark());
		gpUser.setIcon(jsonData.getIcon());
		Byte isAdminCode = 0;
		gpUser.setIsAdminCode(isAdminCode);
		Byte isDisabledCode = 1;
		gpUser.setIsDisabledCode(isDisabledCode);
		gpUser.setIsDisabledValue(jsonData.getIsDisabledValue());
		gpUser.setLastLoginIp(jsonData.getLastLoginIp());
		gpUser.setUpdateTime(jsonData.getUpdateTime());
		gpUser.setRegisterIp(jsonData.getRegisterIp());
		gpUser.setLastLoginTime(jsonData.getLastLoginTime());
		gpUser.setCurrentDomain(jsonData.getCurrentDomain());
		
		
		ResultModel gpUserResult = gpUserUntBll.add(gpUser);
		
		// 设置为管理员 专家角色 
		ArrayList<GprUserRole> arrayList = new ArrayList<GprUserRole>();
		GprUserRole gprUserRole = new GprUserRole();
		gprUserRole.setUserId(gpUserResult.getObjectId());
		gprUserRole.setRoleId(RoleEnum.EXPERT.getId());
		arrayList.add(gprUserRole);
		gprUserRoleUntBll.add(arrayList);
		
		// 若头像不为空，修改recourse表userid为专家，并插入用户头像关系表。
//		GpResource gpResource = new GpResource();
//		gpResource.setId(jsonData.getResourceId());
//		gpResource.setUserId(gpUserResult.getObjectId());
//		gpResourceUntBll.update(gpResource);
		
		GprUserIcon gprUserIcon;
		if(!(jsonData.getResourceId()== null || jsonData.getResourceId().toString().equals(""))){
			gpUser.setIconIds(jsonData.getResourceId());
		}
		if (StringUtils.isNotBlank(jsonData.getResourceId().toString())) {
			gprUserIcon = new GprUserIcon();
			gprUserIcon.setUserId(gpUserResult.getObjectId());
			gprUserIcon.setResourceId(jsonData.getResourceId());
			gprUserIcon.setIsDefault(SymbolicConstant.DCODE_BOOLEAN_T);
			gprUserIconUntBll.add(gprUserIcon);
		}

		// 添加专家表信息
		PiExpert piExpert = new PiExpert();
		piExpert.setId(gpUserResult.getObjectId());
		piExpert.setIntroduction(jsonData.getIntroduction());
		piExpert.setUserId(gpUserResult.getObjectId());
		piExpert.setIsRecommend(jsonData.getIsRecommend());
		ResultModel result = piExpertUntBll.add(piExpert);

		return result;
	}
	
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "PiExpert") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody PiExpert jsonData) {
		
		String userId=jsonData.getId();
		
		// 根据userId修改用户真实姓名
		GpUser gpUser = (GpUser)gpUserUntBll.getModel(userId).getData();
		gpUser.setRealName(jsonData.getRealName());
		gpUser.setUserName(jsonData.getUserName());
		gpUser.setPassword(jsonData.getPassword());
		gpUser.setGenderCode(jsonData.getGenderCode());
		gpUser.setBirthTime(jsonData.getBirthTime());
		gpUser.setAge(jsonData.getAge());
		gpUser.setIsMarriageCode(jsonData.getIsMarriageCode());
		gpUser.setPhone(jsonData.getPhone());
		gpUser.setEmail(jsonData.getEmail());
		gpUser.setQq(jsonData.getQq());
		gpUser.setRemark(jsonData.getRemark());
		
		gpUserUntBll.update(gpUser);
				
		GprUserIcon gprUserIcon=new GprUserIcon();
		if(!(jsonData.getResourceId()== null || jsonData.getResourceId().toString().equals(""))){
			gprUserIconSplBll.deleteByUserId(userId);
			gprUserIcon.setIsDefault(SymbolicConstant.DCODE_BOOLEAN_T);
			gprUserIcon.setResourceId(jsonData.getResourceId());
			gprUserIcon.setUserId(userId);
			gprUserIconUntBll.add(gprUserIcon);
		}

		ResultModel result = piExpertUntBll.update(jsonData);

		return result;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = piExpertUntBll.getModel(id);
		// 根据userId查询userName
		PiExpert piExpert = (PiExpert) result.getData();
		String userId = piExpert.getUserId();
		Byte isRecommend = piExpert.getIsRecommend();
		if(! (isRecommend== null)){
			if(isRecommend.byteValue()==0){
				piExpert.setIsRecommendValue("是");
			}else{
				piExpert.setIsRecommendValue("否");
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT t.id id,t.real_name realName,t.user_name userName,"); 
		selectBuffer.append("t.gender_code genderCode,case when t.gender_code = 0 then '男' when t.gender_code = 1 then '女' else '' end genderValue,");  
		selectBuffer.append("t.birth_time birthTime,t.phone,C.path icon,t.login_count loginCount,t.password password,t.qq qq,t.add_time addTime,t.age age,"); 
		selectBuffer.append("t.email email,t.last_login_time lastLoginTime,t.register_ip registerIp,t.update_time updateTime,");  
		selectBuffer.append("t.is_marriage_code isMarriageCode,case when t.is_marriage_code = 0 then '是' when t.is_marriage_code = 1 then '否' else '' end isMarriageValue,"); 
		selectBuffer.append("t.is_admin_code isAdminCode,case when t.is_admin_code = 0 then '是' when t.is_admin_code = 1 then '否' else '' end isAdminValue,t.remark remark,"); 
		selectBuffer.append("t.is_disabled_code isDisabledCode,case when t.is_disabled_code  = 0 then '是' when t.is_disabled_code = 1 then '否' else '' end isDisabledValue "); 
		selectBuffer.append("FROM gp_user t left join gpr_user_icon D on t.id=D.user_id left join gp_resource C on D.resource_id=C.id ");
		selectBuffer.append(" where D.is_default = '0' and t.id = '" + userId + "' ");
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gpUserUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		
		String userName = modelList.get(0).get("userName").toString();
		String realName = modelList.get(0).get("realName").toString();
		Date birthTime = (Date) modelList.get(0).get("birthTime");
		String phone = modelList.get(0).get("phone").toString();
		String icon = "";
		if(modelList.get(0).get("icon") != null){
			icon = linkPath + modelList.get(0).get("icon").toString();
		}
		Integer loginCount = (Integer) modelList.get(0).get("loginCount");
		String password = modelList.get(0).get("password").toString();
		Date addTime = (Date) modelList.get(0).get("addTime");
		String qq = "";
		if(modelList.get(0).get("qq") != null){
			qq = modelList.get(0).get("qq").toString();
		}
		Integer age = (Integer) modelList.get(0).get("age");
		String email = "";
		if(modelList.get(0).get("email") != null){
			email = modelList.get(0).get("email").toString();
		}
		String remark = "";
		if(modelList.get(0).get("remark") != null){
			remark = modelList.get(0).get("remark").toString();
		}
		Date lastLoginTime = (Date) modelList.get(0).get("lastLoginTime");
		Date updateTime = (Date) modelList.get(0).get("updateTime");
		String registerIp = "";
		if(modelList.get(0).get("registerIp") != null){
			registerIp = modelList.get(0).get("registerIp").toString();
		}
		String genderValue = modelList.get(0).get("genderValue").toString();
		Integer genderCode = (Integer) modelList.get(0).get("genderCode");
		String isDisabledValue = "";
		if(modelList.get(0).get("isDisabledValue") != null){
			isDisabledValue = modelList.get(0).get("isDisabledValue").toString();
		}
		Integer isDisabledCode = (Integer) modelList.get(0).get("isDisabledCode");
		String isAdminValue = "";
		if(modelList.get(0).get("isAdminValue") != null){
			isAdminValue = modelList.get(0).get("isAdminValue").toString();
		}
		Integer isAdminCode = (Integer) modelList.get(0).get("isAdminCode");
		String isMarriageValue = "";
		if(modelList.get(0).get("isMarriageValue") != null){
			isMarriageValue = modelList.get(0).get("isMarriageValue").toString();
		}
		Integer isMarriageCode = (Integer) modelList.get(0).get("isMarriageCode");

		piExpert.setId(id);
		piExpert.setUserId(userId);
		piExpert.setUserName(userName);
		piExpert.setRealName(realName);
		piExpert.setGenderValue(genderValue);
		piExpert.setBirthTime(birthTime);
		piExpert.setPhone(phone);
		piExpert.setIcon(icon);
		piExpert.setLoginCount(loginCount);
		piExpert.setPassword(password);
		piExpert.setQq(qq);
		piExpert.setAddTime(addTime);
		if(! (age== null)){
			piExpert.setAge(age.byteValue());
		}
		piExpert.setRemark(remark);
		piExpert.setEmail(email);
		piExpert.setLastLoginTime(lastLoginTime);
		piExpert.setUpdateTime(updateTime);
		piExpert.setRegisterIp(registerIp);
		piExpert.setIsAdminValue(isAdminValue);
		piExpert.setIsMarriageValue(isMarriageValue);
		piExpert.setIsDisabledValue(isDisabledValue);
		if(! (genderCode== null)){
			piExpert.setGenderCode(genderCode.byteValue());
		}
		if(! (isDisabledCode== null)){
			piExpert.setIsDisabledCode(isDisabledCode.byteValue());
		}
		if(! (isAdminCode== null)){
			piExpert.setIsAdminCode(isAdminCode.byteValue());
		}
		if(! (isMarriageCode== null)){
			piExpert.setIsMarriageCode(isMarriageCode.byteValue());
		}
		result.setData(piExpert);
		
		return result;

	}
	
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "PiExpertGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody PiExpertParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = piExpertUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.user_id userId,A.introduction introduction,B.real_name userName,case when B.gender_code = 0 then '男' "); 
		selectBuffer.append("when B.gender_code = 1 then '女' else '' end genderValue,B.birth_time birthTime,B.phone,concat('"+linkPath+"',D.path) icon ");
		selectBuffer.append(",case when A.is_recommend = 0 then '是' else '否' end isRecommend "); 
		selectBuffer.append("from pi_expert A left join gp_user B on A.user_id=B.id left join gpr_user_icon C on A.user_id=C.user_id left join ");
		selectBuffer.append("gp_resource D on C.resource_id=D.id where 1=1 and C.is_default = '").append(SymbolicConstant.DCODE_BOOLEAN_T).append("' ");
        
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
					selectBuffer.append(" and B.real_name like '%").append(entityRelatedObject.getString("userName")).append("%'");
				if (entityRelatedObject.containsKey("genderValue") && StringUtils.isNotBlank(entityRelatedObject.getString("genderValue")))
					selectBuffer.append(" and (case when B.gender_code = 0 then '男' when B.gender_code = 1 then '女' else '' end) = '").append(entityRelatedObject.getString("genderValue")).append("'");
				if (entityRelatedObject.containsKey("birthTime") && StringUtils.isNotBlank(entityRelatedObject.getString("birthTime")))
					selectBuffer.append(" and B.birth_time like '%").append(entityRelatedObject.getString("birthTime")).append("%'");
				if (entityRelatedObject.containsKey("phone") && StringUtils.isNotBlank(entityRelatedObject.getString("phone")))
					selectBuffer.append(" and B.phone like '%").append(entityRelatedObject.getString("phone")).append("%'");
				if (entityRelatedObject.containsKey("introduction") && StringUtils.isNotBlank(entityRelatedObject.getString("introduction")))
					selectBuffer.append(" and A.introduction like '%").append(entityRelatedObject.getString("introduction")).append("%'");
				if (entityRelatedObject.containsKey("isRecommend") && StringUtils.isNotBlank(entityRelatedObject.getString("isRecommend"))){
					selectBuffer.append(" and (A.is_recommend like '%").append(entityRelatedObject.getString("isRecommend")).append("%'");
					selectBuffer.append(" or (case when A.is_recommend = 0 then '是' else '否' end) = '");
					selectBuffer.append(entityRelatedObject.getString("isRecommend")).append("' )");
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
					selectBuffer.append(orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = piExpertUntBll.getListBySQL(map);

		return resultModel;
	}

}



