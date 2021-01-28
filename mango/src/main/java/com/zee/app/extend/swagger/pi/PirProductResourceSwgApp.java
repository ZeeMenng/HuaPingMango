package com.zee.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PirProductResourceGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/7/11 17:38:33
 * @description 产品和资源中间表 对外接口，扩展自PirProductResourceGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/pirProductResource")
public class  PirProductResourceSwgApp extends  PirProductResourceGenSwgApp {

}



