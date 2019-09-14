package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:17
 * @description 实体类GprRoleModuleGenEnt，自动生成。角色拥有的功能模块权限。
 */

public class GprRoleModuleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private Byte isEnableCode;
    @ApiModelProperty(value="功能模块。外键，引用功能模块表（module）的主键。",hidden=false,required=false)
    private String moduleId;
    @ApiModelProperty(value="系统角色。外键，引用应用角色表（role）的主键。",hidden=false,required=false)
    private String roleId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。主键。
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键。
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsEnableCode() {
		return this.isEnableCode;
	}

	/**
	 * set方法。是否有权。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsEnableCode(Byte isEnableCode) {
		this.isEnableCode = isEnableCode;
	}
    
	/**
	 * get方法。功能模块。外键，引用功能模块表（module）的主键。
	 */
	public String getModuleId() {
		return this.moduleId;
	}

	/**
	 * set方法。功能模块。外键，引用功能模块表（module）的主键。
	 */
	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}
    
	/**
	 * get方法。系统角色。外键，引用应用角色表（role）的主键。
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * set方法。系统角色。外键，引用应用角色表（role）的主键。
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    

    //一对多关系中，多端数据列表

}







