package com.zee.bll.generate.split.pi;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.pi.IPirChannelUserSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:54
 * @description CMS栏目用户关联表 业务逻辑处理类，扩展自BaseSplBll<PirChannelUser>，自动生成。
 */
public class PirChannelUserGenSplBll extends BaseSplBll {

	@Autowired
	protected IPirChannelUserSplDal pirChannelUserSplDal;

}





