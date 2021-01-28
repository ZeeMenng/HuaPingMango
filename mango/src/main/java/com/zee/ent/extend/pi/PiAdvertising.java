package com.zee.ent.extend.pi;

import com.zee.ent.generate.pi.PiAdvertisingGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018/7/12 14:46:55
 * @description 扩展自实体类PiAdvertisingGenEnt，可手动更改。CMS广告表
 */

@ApiModel(value = "PiAdvertising", description = "CMS广告表")
public class PiAdvertising extends PiAdvertisingGenEnt {

	private String[] resourceIdArray = new String[] {};
	private String[] resourcePathArray = new String[] {};

	public String[] getResourceIdArray() {
		return resourceIdArray;
	}

	public void setResourceIdArray(String[] resouceIdArray) {
		this.resourceIdArray = resouceIdArray;
	}

	public String[] getResourcePathArray() {
		return resourcePathArray;
	}

	public void setResourcePathArray(String[] resoucePathArray) {
		this.resourcePathArray = resoucePathArray;
	}

}
