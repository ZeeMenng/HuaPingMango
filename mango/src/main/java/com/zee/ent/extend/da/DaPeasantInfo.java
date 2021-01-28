package com.zee.ent.extend.da;

import com.zee.ent.extend.gp.GpUser;
import com.zee.ent.generate.da.DaPeasantInfoGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-21 10:22:07
 * @description 扩展自实体类DaPeasantInfoGenEnt，可手动更改。农户信息表
 */

@ApiModel(value = "DaPeasantInfo", description = "农户信息表")
public class DaPeasantInfo extends DaPeasantInfoGenEnt {
private static final long serialVersionUID = 1L;
	
	DaCommonField daCommonField = new DaCommonField();
	 GpUser user = new GpUser();
	public DaCommonField getDaCommonField() {
		return daCommonField;
	}

	public void setDaCommonField(DaCommonField daCommonField) {
		this.daCommonField = daCommonField;
	}

	public GpUser getUser() {
		return user;
	}

	public void setUser(GpUser user) {
		this.user = user;
	}

}







