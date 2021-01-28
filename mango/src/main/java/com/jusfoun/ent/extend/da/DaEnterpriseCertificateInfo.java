package com.zee.ent.extend.da;

import com.zee.ent.generate.da.DaEnterpriseCertificateInfoGenEnt;

import io.swagger.annotations.ApiModel;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-9-14 13:59:04
 * @description 扩展自实体类DaEnterpriseCertificateInfoGenEnt，可手动更改。企业证书
 */

@ApiModel(value = "DaEnterpriseCertificateInfo", description = "企业证书")
public class DaEnterpriseCertificateInfo extends DaEnterpriseCertificateInfoGenEnt {
	
    private String[] fileResourceIdArray = new String[] {};
	private String[] fileResourcePathArray = new String[] {};
	
	public String[] getFileResourceIdArray() {
		return fileResourceIdArray;
	}

	public void setFileResourceIdArray(String[] fileResourceIdArray) {
		this.fileResourceIdArray = fileResourceIdArray;
	}

	public String[] getFileResourcePathArray() {
		return fileResourcePathArray;
	}

	public void setFileResourcePathArray(String[] fileResourcePathArray) {
		this.fileResourcePathArray = fileResourcePathArray;
	}
}







