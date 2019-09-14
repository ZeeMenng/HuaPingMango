package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaGrowCostSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 种植成本 业务逻辑处理类，扩展自BaseSplBll<DaGrowCost>，自动生成。
 */
public class DaGrowCostGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaGrowCostSplDal daGrowCostSplDal;

}





