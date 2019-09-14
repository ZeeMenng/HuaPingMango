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
 * @description 实体类GprUserOrganizationGenEnt，自动生成。用户所属组织机构。
 */

public class GprUserOrganizationGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="用户组织机构。外键，引用组织机构表（organization）的主键。",hidden=false,required=false)
    private String organizationId;
    @ApiModelProperty(value="系统用户。外键，引用系统用户表（user）的主键。",hidden=false,required=false)
    private String userId;
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
	 * get方法。用户组织机构。外键，引用组织机构表（organization）的主键。
	 */
	public String getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * set方法。用户组织机构。外键，引用组织机构表（organization）的主键。
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
    
	/**
	 * get方法。系统用户。外键，引用系统用户表（user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。系统用户。外键，引用系统用户表（user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    

    //一对多关系中，多端数据列表

}







