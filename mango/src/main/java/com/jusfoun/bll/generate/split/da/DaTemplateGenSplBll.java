package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaTemplateSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 指标模板表 业务逻辑处理类，扩展自BaseSplBll<DaTemplate>，自动生成。
 */
public class DaTemplateGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaTemplateSplDal daTemplateSplDal;

}





