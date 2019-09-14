package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDarEnterpriseResourceSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/7/11 17:38:34
 * @description 企业和资源中间表 业务逻辑处理类，扩展自BaseSplBll<DarEnterpriseResource>，自动生成。
 */
public class DarEnterpriseResourceGenSplBll extends BaseSplBll {

	@Autowired
	protected IDarEnterpriseResourceSplDal darEnterpriseResourceSplDal;

}





