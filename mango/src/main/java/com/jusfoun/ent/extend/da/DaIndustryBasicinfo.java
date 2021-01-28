package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaIndustryBasicinfoGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaIndustryBasicinfoGenEnt，可手动更改。产业基本情况表
 */

@ApiModel(value = "DaIndustryBasicinfo", description = "产业基本情况表")
public class DaIndustryBasicinfo extends DaIndustryBasicinfoGenEnt {

	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







