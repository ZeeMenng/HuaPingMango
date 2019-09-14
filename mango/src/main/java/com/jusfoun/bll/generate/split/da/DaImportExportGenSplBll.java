package com.jusfoun.bll.generate.split.da;

import org.springframework.beans.factory.annotation.Autowired;

import com.jusfoun.bll.generate.split.base.BaseSplBll;
import com.jusfoun.dao.split.da.IDaImportExportSplDal;



/**
 * @author Zee
 * @createDate 2017/05/22 14:35:56
 * @updateDate 2018-6-13 19:04:24
 * @description 进出口数据表，用于存放直报数据的进出口数据 业务逻辑处理类，扩展自BaseSplBll<DaImportExport>，自动生成。
 */
public class DaImportExportGenSplBll extends BaseSplBll {

	@Autowired
	protected IDaImportExportSplDal daImportExportSplDal;

}





