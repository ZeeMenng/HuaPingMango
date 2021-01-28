package com.zee.ent.extend.gp;

import com.zee.ent.generate.gp.GpOperLogGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpOperLogGenEnt，可手动更改。操作日志。
 */

@ApiModel(value = "GpOperLog", description = "操作日志。")
public class GpOperLog extends GpOperLogGenEnt {
  
	@ApiModelProperty(value="应用领域",hidden=false,required=false)
    private String domainName;
	
	@ApiModelProperty(value="操作是否成功",hidden=false,required=false)
    private String isSuccessValue;

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getIsSuccessValue() {
		return isSuccessValue;
	}

	public void setIsSuccessValue(String isSuccessValue) {
		this.isSuccessValue = isSuccessValue;
	}

}







