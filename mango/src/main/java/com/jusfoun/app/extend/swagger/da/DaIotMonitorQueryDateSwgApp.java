package com.jusfoun.app.extend.swagger.da;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.da.DaIotMonitorQueryDateGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-6 14:42:27
 * @description  对外接口，扩展自DaIotMonitorQueryDateGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daIotMonitorQueryDate")
public class  DaIotMonitorQueryDateSwgApp extends  DaIotMonitorQueryDateGenSwgApp {

}



