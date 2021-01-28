package com.zee.app.extend.swagger.da;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaEnterpriseLoanInfoGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaEnterpriseLoanInfoGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daEnterpriseLoanInfo")
public class  DaEnterpriseLoanInfoSwgApp extends  DaEnterpriseLoanInfoGenSwgApp {

}



