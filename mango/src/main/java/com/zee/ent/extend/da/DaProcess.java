package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaProcessGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaProcessGenEnt，可手动更改。加工品数据表
 */

@ApiModel(value = "DaProcess", description = "加工品数据表")
public class DaProcess extends DaProcessGenEnt {
  
private static final long serialVersionUID = 1L;
	
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}
}







