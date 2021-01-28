package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaInputGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaInputGenEnt，可手动更改。投入品信息表
 */

@ApiModel(value = "DaInput", description = "投入品信息表")
public class DaInput extends DaInputGenEnt {
	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







