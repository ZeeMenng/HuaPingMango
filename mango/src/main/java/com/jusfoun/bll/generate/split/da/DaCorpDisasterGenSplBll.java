package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaCorpDisasterSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 作物受灾数据表 业务逻辑处理类，扩展自BaseSplBll<DaCorpDisaster>，自动生成。
 */
public class DaCorpDisasterGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaCorpDisasterSplDal daCorpDisasterSplDal;

}





