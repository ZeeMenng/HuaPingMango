package com.jusfoun.bll.generate.split.pi;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.pi.IPiExpertSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/7/11 17:38:34
 * @description 专家表 业务逻辑处理类，扩展自BaseSplBll<PiExpert>，自动生成。
 */
public class PiExpertGenSplBll extends BaseSplBll {

	@Autowired
	protected IPiExpertSplDal piExpertSplDal;

}





