package com.jusfoun.bll.generate.split.pi;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.pi.IPiProductRecommendSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-8-19 10:15:47
 * @description 企业推介产品表 业务逻辑处理类，扩展自BaseSplBll<PiProductRecommend>，自动生成。
 */
public class PiProductRecommendGenSplBll extends BaseSplBll {

	@Autowired
	protected IPiProductRecommendSplDal piProductRecommendSplDal;

}





