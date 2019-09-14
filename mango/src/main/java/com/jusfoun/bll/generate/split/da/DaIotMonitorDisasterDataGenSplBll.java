package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaIotMonitorDisasterDataSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description da_iot_monitor_data表中满足预警条件的数据 业务逻辑处理类，扩展自BaseSplBll<DaIotMonitorDisasterData>，自动生成。
 */
public class DaIotMonitorDisasterDataGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaIotMonitorDisasterDataSplDal daIotMonitorDisasterDataSplDal;

}





