package com.jusfoun.app.extend.swagger.gp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GprConfigUserGenSwgApp;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.gp.GpUser;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.ApiOperation;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2021/1/19 11:23:52
 * @description 用户配置信息。 对外接口，扩展自GprConfigUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprConfigUser")
public class  GprConfigUserSwgApp extends  GprConfigUserGenSwgApp {

	@ApiOperation(value = "查询应用配置", notes = "查询当前用户在当前应用领域下的配置项")
	@RequestMapping(value = "/getCurrentUserConfig", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getCurrentUserConfig() {

		ResultModel resultModel = new ResultModel();

		GpUser user = getCurrentUser();
		if (user == null)
			throw new GlobalException("未能获取到登录用户信息……");
		String userId = user.getId();
		String domainId = user.getCurrentDomain().getId();
		Map<String, Object> map = new HashMap<String, Object>();

		String sql = String.format(SymbolicConstant.SQL_SELECT_USER_CONFIG_LIST, domainId, userId);

		map.put("Sql", sql);

		resultModel = gprConfigUserUntBll.getListBySQL(map);

		return resultModel;
	}

	
}



