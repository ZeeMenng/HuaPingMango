package com.zee.bll.generate.split.mf;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.mf.IMfProcessYieldSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:07:53
 * @description 加工品产量建模表 业务逻辑处理类，扩展自BaseSplBll<MfProcessYield>，自动生成。
 */
public class MfProcessYieldGenSplBll extends BaseSplBll {

	@Autowired
	protected IMfProcessYieldSplDal mfProcessYieldSplDal;

}





