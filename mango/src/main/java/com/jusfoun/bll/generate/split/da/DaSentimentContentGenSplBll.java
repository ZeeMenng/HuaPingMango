package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaSentimentContentSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 舆情文章内容表 业务逻辑处理类，扩展自BaseSplBll<DaSentimentContent>，自动生成。
 */
public class DaSentimentContentGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaSentimentContentSplDal daSentimentContentSplDal;

}





