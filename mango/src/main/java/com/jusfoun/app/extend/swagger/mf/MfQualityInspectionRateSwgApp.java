package com.jusfoun.app.extend.swagger.mf;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.mf.MfQualityInspectionRateGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  对外接口，扩展自MfQualityInspectionRateGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfQualityInspectionRate")
public class  MfQualityInspectionRateSwgApp extends  MfQualityInspectionRateGenSwgApp {

}



