package com.jusfoun.ent.extend.da;

import com.jusfoun.ent.generate.da.DaEcologyNormInfoGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaEcologyNormInfoGenEnt，可手动更改。种植生态指标表
 */

@ApiModel(value = "DaEcologyNormInfo", description = "种植生态指标表")
public class DaEcologyNormInfo extends DaEcologyNormInfoGenEnt {
private static final long serialVersionUID = 1L;
	
	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







