package com.jusfoun.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.pi.PirContentImageGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/5/7 15:00:36
 * @description CMS文章内容所包含图片信息表 对外接口，扩展自PirContentImageGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/pirContentImage")
public class  PirContentImageSwgApp extends  PirContentImageGenSwgApp {

}



