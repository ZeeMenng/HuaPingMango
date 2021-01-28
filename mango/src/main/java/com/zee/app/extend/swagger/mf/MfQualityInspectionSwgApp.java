package com.zee.app.extend.swagger.mf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfQualityInspectionGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  对外接口，扩展自MfQualityInspectionGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfQualityInspection")
public class  MfQualityInspectionSwgApp extends  MfQualityInspectionGenSwgApp {

}



