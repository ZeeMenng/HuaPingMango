package com.zee.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.da.IDaSentimentRuleSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-7-22 16:33:32
 * @description 舆情采集规则表 业务逻辑处理类，扩展自BaseSplBll<DaSentimentRule>，自动生成。
 */
public class DaSentimentRuleGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaSentimentRuleSplDal daSentimentRuleSplDal;

}





