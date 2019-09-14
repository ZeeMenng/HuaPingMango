package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaUserContributionSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-19 16:50:20
 * @description 用户贡献 业务逻辑处理类，扩展自BaseSplBll<DaUserContribution>，自动生成。
 */
public class DaUserContributionGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaUserContributionSplDal daUserContributionSplDal;

}





