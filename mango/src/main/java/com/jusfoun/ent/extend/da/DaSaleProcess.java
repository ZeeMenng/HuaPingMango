package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaSaleProcessGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaSaleProcessGenEnt，可手动更改。加工销售数据表
 */

@ApiModel(value = "DaSaleProcess", description = "加工销售数据表")
public class DaSaleProcess extends DaSaleProcessGenEnt {
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







