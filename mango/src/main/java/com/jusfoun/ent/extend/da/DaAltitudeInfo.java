package com.zee.ent.extend.da;

import io.swagger.annotations.ApiModel;

import com.zee.ent.generate.da.DaAltitudeInfoGenEnt;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaAltitudeInfoGenEnt，可手动更改。海拔数据表
 */

@ApiModel(value = "DaAltitudeInfo", description = "海拔数据表")
public class DaAltitudeInfo extends DaAltitudeInfoGenEnt {
  
	private static final long serialVersionUID = 1L;
	
	private DaCommonField daCommonField = new DaCommonField();

	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}
	
}







