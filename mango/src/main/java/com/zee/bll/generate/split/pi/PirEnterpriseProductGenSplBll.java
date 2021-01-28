package com.zee.bll.generate.split.pi;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.pi.IPirEnterpriseProductSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/7/11 17:38:34
 * @description 企业和产品中间表 业务逻辑处理类，扩展自BaseSplBll<PirEnterpriseProduct>，自动生成。
 */
public class PirEnterpriseProductGenSplBll extends BaseSplBll {

	@Autowired
	protected IPirEnterpriseProductSplDal pirEnterpriseProductSplDal;

}





