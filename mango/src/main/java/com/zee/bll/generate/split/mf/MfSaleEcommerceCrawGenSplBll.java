package com.zee.bll.generate.split.mf;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.mf.IMfSaleEcommerceCrawSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:07:53
 * @description 电商数据表,采集 业务逻辑处理类，扩展自BaseSplBll<MfSaleEcommerceCraw>，自动生成。
 */
public class MfSaleEcommerceCrawGenSplBll extends BaseSplBll {

	@Autowired
	protected IMfSaleEcommerceCrawSplDal mfSaleEcommerceCrawSplDal;

}





