package com.jusfoun.app.extend.swagger.da;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.da.DarEnterpriseResourceGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/7/11 17:38:33
 * @description 企业和资源中间表 对外接口，扩展自DarEnterpriseResourceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/darEnterpriseResource")
public class  DarEnterpriseResourceSwgApp extends  DarEnterpriseResourceGenSwgApp {

}



