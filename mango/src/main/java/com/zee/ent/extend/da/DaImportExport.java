package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaImportExportGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaImportExportGenEnt，可手动更改。进出口数据表，用于存放直报数据的进出口数据
 */

@ApiModel(value = "DaImportExport", description = "进出口数据表，用于存放直报数据的进出口数据")
public class DaImportExport extends DaImportExportGenEnt {
	
	private static final long serialVersionUID = 1L;
	
	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







