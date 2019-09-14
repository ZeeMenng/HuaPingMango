package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaIndustryStatisticsGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaIndustryStatisticsGenEnt，可手动更改。产业统计数据表
 */

@ApiModel(value = "DaIndustryStatistics", description = "产业统计数据表")
public class DaIndustryStatistics extends DaIndustryStatisticsGenEnt {
  

	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}
}







