package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaIndustryProcessGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaIndustryProcessGenEnt，可手动更改。加工品产值数据表
 */

@ApiModel(value = "DaIndustryProcess", description = "加工品产值数据表")
public class DaIndustryProcess extends DaIndustryProcessGenEnt {
	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







