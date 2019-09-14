package com.jusfoun.ent.extend.gp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.jusfoun.ent.generate.gp.GpRoleGenEnt;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类GpRoleGenEnt，可手动更改。系统角色。
 */

@ApiModel(value = "GpRole", description = "系统角色。")
public class GpRole extends GpRoleGenEnt {
	
	@ApiModelProperty(value="模块权限id",hidden=false,required=false)
    private String moduleIds;
	@ApiModelProperty(value="菜单名称",hidden=false,required=false)
    private String menuNames;
	
	@ApiModelProperty(value="应用领域名称",hidden=false,required=false)
    private String domainName;

	public String getModuleIds() {
		return moduleIds;
	}

	public void setModuleIds(String moduleIds) {
		this.moduleIds = moduleIds;
	}

	public String getMenuNames() {
		return menuNames;
	}

	public void setMenuNames(String menuNames) {
		this.menuNames = menuNames;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

}







