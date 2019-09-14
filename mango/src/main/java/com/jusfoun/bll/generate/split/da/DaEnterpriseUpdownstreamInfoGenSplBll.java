package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaEnterpriseUpdownstreamInfoSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 企业关联企业表，企业上下游关系记录。 业务逻辑处理类，扩展自BaseSplBll<DaEnterpriseUpdownstreamInfo>，自动生成。
 */
public class DaEnterpriseUpdownstreamInfoGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaEnterpriseUpdownstreamInfoSplDal daEnterpriseUpdownstreamInfoSplDal;

}





