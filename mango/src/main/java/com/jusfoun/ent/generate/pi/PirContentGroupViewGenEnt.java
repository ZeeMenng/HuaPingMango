﻿package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:45
 * @description 实体类PirContentGroupViewGenEnt，自动生成。CMS内容浏览会员组关联表
 */

public class PirContentGroupViewGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String roleId;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getContentId() {
		return this.contentId;
	}

	/**
	 * set方法。
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
    
	/**
	 * get方法。
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。
	 */
	public String getRoleId() {
		return this.roleId;
	}

	/**
	 * set方法。
	 */
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
    










}







