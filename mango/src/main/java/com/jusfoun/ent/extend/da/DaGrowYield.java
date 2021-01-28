package com.zee.ent.extend.da;

import java.util.List;

import com.zee.ent.generate.da.DaGrowYieldGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-26 18:49:08
 * @description 扩展自实体类DaGrowYieldGenEnt，可手动更改。种植数据表
 */

@ApiModel(value = "DaGrowYield", description = "种植数据表")
public class DaGrowYield extends DaGrowYieldGenEnt {
  
	private static final long serialVersionUID = 1L;
	
	DaCommonField daCommonField = new DaCommonField();
	
	List<DaGrowYield> daGrowYieldList;

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

	public List<DaGrowYield> getDaGrowYieldList() {
		return daGrowYieldList;
	}

	public void setDaGrowYieldList(List<DaGrowYield> daGrowYieldList) {
		this.daGrowYieldList = daGrowYieldList;
	}
}







