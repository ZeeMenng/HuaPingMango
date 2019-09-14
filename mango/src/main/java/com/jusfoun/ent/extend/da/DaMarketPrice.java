package com.jusfoun.ent.extend.da;

import io.swagger.annotations.ApiModel;

import com.jusfoun.ent.generate.da.DaMarketPriceGenEnt;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaMarketPriceGenEnt，可手动更改。市场价格数据表
 */

@ApiModel(value = "DaMarketPrice", description = "市场价格数据表")
public class DaMarketPrice extends DaMarketPriceGenEnt {
  
	private static final long serialVersionUID = 1L;
	
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







