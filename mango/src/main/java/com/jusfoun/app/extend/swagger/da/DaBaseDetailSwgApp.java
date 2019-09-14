package com.jusfoun.app.extend.swagger.da;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.da.DaBaseDetailGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-20 18:00:32
 * @description 基地细分表-地块表 对外接口，扩展自DaBaseDetailGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daBaseDetail")
public class  DaBaseDetailSwgApp extends  DaBaseDetailGenSwgApp {

}



