package com.jusfoun.bll.generate.split.pi;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.pi.IPiFileSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018/5/24 0:40:54
 * @description CMS文章相关文件表 业务逻辑处理类，扩展自BaseSplBll<PiFile>，自动生成。
 */
public class PiFileGenSplBll extends BaseSplBll {

	@Autowired
	protected IPiFileSplDal piFileSplDal;

}





