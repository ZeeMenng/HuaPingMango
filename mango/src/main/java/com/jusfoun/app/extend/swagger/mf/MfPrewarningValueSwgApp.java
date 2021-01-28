package com.zee.app.extend.swagger.mf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfPrewarningValueGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-9-26 15:07:44
 * @description 质量安全预警值设置 对外接口，扩展自MfPrewarningValueGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfPrewarningValue")
public class  MfPrewarningValueSwgApp extends  MfPrewarningValueGenSwgApp {

}



