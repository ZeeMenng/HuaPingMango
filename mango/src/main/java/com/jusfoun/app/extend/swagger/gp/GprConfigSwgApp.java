package com.jusfoun.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GprConfigGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2021/1/18 19:49:24
 * @description 应用领域配置信息。 对外接口，扩展自GprConfigGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprConfig")
public class  GprConfigSwgApp extends  GprConfigGenSwgApp {

}



