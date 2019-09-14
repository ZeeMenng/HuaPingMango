package com.jusfoun.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.pi.PiChannelTxtGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS栏目文本表 对外接口，扩展自PiChannelTxtGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piChannelTxt")
public class PiChannelTxtSwgApp extends PiChannelTxtGenSwgApp {

}



