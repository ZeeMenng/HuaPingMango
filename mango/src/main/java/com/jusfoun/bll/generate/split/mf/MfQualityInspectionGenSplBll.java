package com.jusfoun.bll.generate.split.mf;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.mf.IMfQualityInspectionSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:07:53
 * @description 质量抽检表 业务逻辑处理类，扩展自BaseSplBll<MfQualityInspection>，自动生成。
 */
public class MfQualityInspectionGenSplBll extends BaseSplBll {

	@Autowired
	protected IMfQualityInspectionSplDal mfQualityInspectionSplDal;

}





