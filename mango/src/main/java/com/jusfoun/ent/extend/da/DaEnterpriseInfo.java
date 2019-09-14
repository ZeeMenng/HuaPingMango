package com.jusfoun.ent.extend.da;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jusfoun.ent.generate.da.DaEnterpriseInfoGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-20 14:12:54
 * @description 扩展自实体类DaEnterpriseInfoGenEnt，可手动更改。企业基本信息表
 */

@ApiModel(value = "DaEnterpriseInfo", description = "企业基本信息表")
@JsonIgnoreProperties(ignoreUnknown = true)
public class DaEnterpriseInfo extends DaEnterpriseInfoGenEnt {
  
	DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}
}







