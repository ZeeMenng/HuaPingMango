package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaCorpDisasterGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaCorpDisasterGenEnt，可手动更改。作物受灾数据表
 */

@ApiModel(value = "DaCorpDisaster", description = "作物受灾数据表")
public class DaCorpDisaster extends DaCorpDisasterGenEnt {
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}


}







