package com.jusfoun.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GprConfigUserGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2021/1/19 11:23:52
 * @description 用户配置信息。 对外接口，扩展自GprConfigUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprConfigUser")
public class  GprConfigUserSwgApp extends  GprConfigUserGenSwgApp {

}



