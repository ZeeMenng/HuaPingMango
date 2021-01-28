package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaCheckTestingGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaCheckTestingGenEnt，可手动更改。检测数据表
 */

@ApiModel(value = "DaCheckTesting", description = "检测数据表")
public class DaCheckTesting extends DaCheckTestingGenEnt {
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







