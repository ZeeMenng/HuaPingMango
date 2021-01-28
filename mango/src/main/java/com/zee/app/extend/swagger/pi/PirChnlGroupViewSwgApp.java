package com.zee.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PirChnlGroupViewGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS栏目浏览会员组关联表 对外接口，扩展自PirChnlGroupViewGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/pirChnlGroupView")
public class PirChnlGroupViewSwgApp extends PirChnlGroupViewGenSwgApp {

}



