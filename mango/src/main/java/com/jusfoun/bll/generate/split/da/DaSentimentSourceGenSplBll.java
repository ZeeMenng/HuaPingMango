package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaSentimentSourceSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-7-19 11:37:09
 * @description 舆情数据源表 业务逻辑处理类，扩展自BaseSplBll<DaSentimentSource>，自动生成。
 */
public class DaSentimentSourceGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaSentimentSourceSplDal daSentimentSourceSplDal;

}





