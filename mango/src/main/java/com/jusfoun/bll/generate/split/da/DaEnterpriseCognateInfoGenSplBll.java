package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaEnterpriseCognateInfoSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 企业关联企业信息表，企业与企业之间关系。 业务逻辑处理类，扩展自BaseSplBll<DaEnterpriseCognateInfo>，自动生成。
 */
public class DaEnterpriseCognateInfoGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaEnterpriseCognateInfoSplDal daEnterpriseCognateInfoSplDal;

}





