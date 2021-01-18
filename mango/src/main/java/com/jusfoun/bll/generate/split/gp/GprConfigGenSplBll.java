package com.jusfoun.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.gp.IGprConfigSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2021/1/18 19:49:24
 * @description 应用领域配置信息。 业务逻辑处理类，扩展自BaseSplBll<GprConfig>，自动生成。
 */
public class GprConfigGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprConfigSplDal gprConfigSplDal;

}





