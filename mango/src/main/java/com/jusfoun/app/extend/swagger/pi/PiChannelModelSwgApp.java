package com.jusfoun.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.pi.PiChannelModelGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS栏目可选内容模型表 对外接口，扩展自PiChannelModelGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piChannelModel")
public class PiChannelModelSwgApp extends PiChannelModelGenSwgApp {

}



