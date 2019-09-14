package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaBaseInfoGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-20 14:12:54
 * @description 扩展自实体类DaBaseInfoGenEnt，可手动更改。基地信息表
 */

@ApiModel(value = "DaBaseInfo", description = "基地信息表")
public class DaBaseInfo extends DaBaseInfoGenEnt {
	
	private static final long serialVersionUID = 1L;
	
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







