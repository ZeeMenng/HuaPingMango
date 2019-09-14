package com.jusfoun.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.gp.IGpMessageSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:53
 * @description 系统消息。 业务逻辑处理类，扩展自BaseSplBll<GpMessage>，自动生成。
 */
public class GpMessageGenSplBll extends BaseSplBll {

	@Autowired
	protected IGpMessageSplDal gpMessageSplDal;

}





