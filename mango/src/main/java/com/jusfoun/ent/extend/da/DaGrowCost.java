package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaGrowCostGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaGrowCostGenEnt，可手动更改。种植成本
 */

@ApiModel(value = "DaGrowCost", description = "种植成本")
public class DaGrowCost extends DaGrowCostGenEnt {
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







