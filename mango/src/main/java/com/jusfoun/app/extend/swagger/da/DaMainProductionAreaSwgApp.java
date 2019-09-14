package com.jusfoun.app.extend.swagger.da;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.da.DaMainProductionAreaGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-25 10:47:17
 * @description 芒果主产区 对外接口，扩展自DaMainProductionAreaGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daMainProductionArea")
public class  DaMainProductionAreaSwgApp extends  DaMainProductionAreaGenSwgApp {

}



