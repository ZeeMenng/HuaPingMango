package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaSalePeasantGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaSalePeasantGenEnt，可手动更改。农户销售数据表
 */

@ApiModel(value = "DaSalePeasant", description = "农户销售数据表")
public class DaSalePeasant extends DaSalePeasantGenEnt {
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







