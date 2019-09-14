package com.jusfoun.ent.extend.gp;

import com.jusfoun.ent.generate.gp.GpLoginLogGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpLoginLogGenEnt，可手动更改。登录日志。
 */

@ApiModel(value = "GpLoginLog", description = "登录日志。")
public class GpLoginLog extends GpLoginLogGenEnt {
	
	@ApiModelProperty(value="应用领域",hidden=false,required=false)
    private String domainName;
	
    @ApiModelProperty(value="登录是否成功",hidden=false,required=false)
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







