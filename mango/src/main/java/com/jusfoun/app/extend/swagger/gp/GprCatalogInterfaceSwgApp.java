package com.jusfoun.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GprCatalogInterfaceGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/10/21 21:21:11
 * @description 后台接口所属分类。 对外接口，扩展自GprCatalogInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gprCatalogInterface")
public class  GprCatalogInterfaceSwgApp extends  GprCatalogInterfaceGenSwgApp {

}



