package com.jusfoun.bll.generate.split.gp;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.gp.IGprUserStationSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:53
 * @description 用户所属岗位。 业务逻辑处理类，扩展自BaseSplBll<GprUserStation>，自动生成。
 */
public class GprUserStationGenSplBll extends BaseSplBll {

	@Autowired
	protected IGprUserStationSplDal gprUserStationSplDal;

}





