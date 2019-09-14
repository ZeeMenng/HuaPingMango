package com.jusfoun.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GprUserIconGenSwgApp;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.set.enumer.OperResult;
import com.jusfoun.set.enumer.OperType;
import com.jusfoun.set.exception.GlobalException;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.Tools;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/5/7 15:00:36
 * @description 用户头像表 对外接口，扩展自GprUserIconGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprUserIcon")
public class  GprUserIconSwgApp extends  GprUserIconGenSwgApp {
	

	

}



