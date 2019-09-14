package com.jusfoun.app.extend.swagger.pe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.pe.PeAerialBroadcastGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-27 10:25:42
 * @description 鸟瞰华坪轮播图 对外接口，扩展自PeAerialBroadcastGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pe/peAerialBroadcast")
public class  PeAerialBroadcastSwgApp extends  PeAerialBroadcastGenSwgApp {

}



