package com.jusfoun.app.extend.swagger.gp;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.gp.GpCatalogInterfaceGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2020/10/21 21:21:11
 * @description 说明：接口分类字典。存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。 对外接口，扩展自GpCatalogInterfaceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/gpCatalogInterface")
public class  GpCatalogInterfaceSwgApp extends  GpCatalogInterfaceGenSwgApp {

}



