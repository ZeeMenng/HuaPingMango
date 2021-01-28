package com.zee.app.extend.swagger.da;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaWholesaleMarketGenSwgApp;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-25 10:47:17
 * @description 批发市场 对外接口，扩展自DaWholesaleMarketGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daWholesaleMarket")
public class  DaWholesaleMarketSwgApp extends  DaWholesaleMarketGenSwgApp {

}



