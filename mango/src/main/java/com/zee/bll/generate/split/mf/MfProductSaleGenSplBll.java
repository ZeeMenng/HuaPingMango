package com.zee.bll.generate.split.mf;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.mf.IMfProductSaleSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:07:53
 * @description 产销预测表 业务逻辑处理类，扩展自BaseSplBll<MfProductSale>，自动生成。
 */
public class MfProductSaleGenSplBll extends BaseSplBll {

	@Autowired
	protected IMfProductSaleSplDal mfProductSaleSplDal;

}





