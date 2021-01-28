package com.zee.app.extend.swagger.pi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiAcquisitionHistoryGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS采集的文章历史记录表 对外接口，扩展自PiAcquisitionHistoryGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piAcquisitionHistory")
public class PiAcquisitionHistorySwgApp extends PiAcquisitionHistoryGenSwgApp {

}



