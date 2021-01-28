package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaIdentificationInfoGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaIdentificationInfoGenEnt，可手动更改。三品一标认证表
 */

@ApiModel(value = "DaIdentificationInfo", description = "三品一标认证表")
public class DaIdentificationInfo extends DaIdentificationInfoGenEnt {
	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

}







