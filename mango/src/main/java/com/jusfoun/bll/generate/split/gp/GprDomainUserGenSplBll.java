package com.zee.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.gp.IGprDomainUserSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:53
 * @description 应用领域拥有的用户。 业务逻辑处理类，扩展自BaseSplBll<GprDomainUser>，自动生成。
 */
public class GprDomainUserGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprDomainUserSplDal gprDomainUserSplDal;

}





