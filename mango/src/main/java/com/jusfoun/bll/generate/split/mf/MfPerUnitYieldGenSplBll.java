package com.jusfoun.bll.generate.split.mf;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.mf.IMfPerUnitYieldSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:07:53
 * @description 单产预测建模表 业务逻辑处理类，扩展自BaseSplBll<MfPerUnitYield>，自动生成。
 */
public class MfPerUnitYieldGenSplBll extends BaseSplBll {

	@Autowired
	protected IMfPerUnitYieldSplDal mfPerUnitYieldSplDal;

}





