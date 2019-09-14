package com.jusfoun.ent.extend.gp;

import com.jusfoun.ent.generate.gp.GpDomainGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpDomainGenEnt，可手动更改。应用领域。
 */

@ApiModel(value = "GpDomain", description = "应用领域。")
public class GpDomain extends GpDomainGenEnt {
	private String modules;

	public String getModules() {
		return modules;
	}

	public void setModules(String modules) {
		this.modules = modules;
	}

}
