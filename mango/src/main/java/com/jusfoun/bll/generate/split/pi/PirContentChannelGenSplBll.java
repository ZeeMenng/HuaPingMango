package com.jusfoun.bll.generate.split.pi;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.pi.IPirContentChannelSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:54
 * @description CMS内容栏目关联表 业务逻辑处理类，扩展自BaseSplBll<PirContentChannel>，自动生成。
 */
public class PirContentChannelGenSplBll extends BaseSplBll {

	@Autowired
	protected IPirContentChannelSplDal pirContentChannelSplDal;

}





