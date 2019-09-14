package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaIndustryFreshGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaIndustryFreshGenEnt，可手动更改。鲜果产值数据表
 */

@ApiModel(value = "DaIndustryFresh", description = "鲜果产值数据表")
public class DaIndustryFresh extends DaIndustryFreshGenEnt {

	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







