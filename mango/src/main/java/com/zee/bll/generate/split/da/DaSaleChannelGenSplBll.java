package com.zee.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.da.IDaSaleChannelSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 销售渠道数据表 业务逻辑处理类，扩展自BaseSplBll<DaSaleChannel>，自动生成。
 */
public class DaSaleChannelGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaSaleChannelSplDal daSaleChannelSplDal;

}





