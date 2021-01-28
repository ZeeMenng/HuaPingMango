package com.zee.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PirContentAttachmentGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018/5/7 15:00:36
 * @description CMS文章内容包含附件信息表。 对外接口，扩展自PirContentAttachmentGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/pirContentAttachment")
public class  PirContentAttachmentSwgApp extends  PirContentAttachmentGenSwgApp {

}



