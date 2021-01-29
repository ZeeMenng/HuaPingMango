package com.zee.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.da.IDaIotMonitorQueryDateSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 物联网数据最新查询时间表 业务逻辑处理类，扩展自BaseSplBll<DaIotMonitorQueryDate>，自动生成。
 */
public class DaIotMonitorQueryDateGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaIotMonitorQueryDateSplDal daIotMonitorQueryDateSplDal;

}




