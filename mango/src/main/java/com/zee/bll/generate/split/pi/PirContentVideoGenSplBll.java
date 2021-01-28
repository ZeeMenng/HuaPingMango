package com.zee.bll.generate.split.pi;

import org.springframework.beans.factory.annotation.Autowired;

import com.zee.bll.generate.split.base.BaseSplBll;
import com.zee.dao.split.pi.IPirContentVideoSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:54
 * @description CMS文章内容所包含视频信息表 业务逻辑处理类，扩展自BaseSplBll<PirContentVideo>，自动生成。
 */
public class PirContentVideoGenSplBll extends BaseSplBll {

	@Autowired
	protected IPirContentVideoSplDal pirContentVideoSplDal;

}





