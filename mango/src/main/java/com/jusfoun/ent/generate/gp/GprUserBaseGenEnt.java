﻿package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:44
 * @description 实体类GprUserBaseGenEnt，自动生成。用户归属的基地。
 */

public class GprUserBaseGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="基地。外键，引用应用基地信息表（da_base_info）的主键。",hidden=false,required=false)
    private String baseId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="系统用户。外键，引用系统用户表（gp_user）的主键。",hidden=false,required=false)
    private String userId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。基地。外键，引用应用基地信息表（da_base_info）的主键。
	 */
	public String getBaseId() {
		return this.baseId;
	}

	/**
	 * set方法。基地。外键，引用应用基地信息表（da_base_info）的主键。
	 */
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
    
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
	 * get方法。系统用户。外键，引用系统用户表（gp_user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。系统用户。外键，引用系统用户表（gp_user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    

    //一对多关系中，多端数据列表

}







