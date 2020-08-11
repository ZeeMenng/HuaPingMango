package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:39
 * @description 实体类GprModulePageGenEnt，自动生成。功能模块所包含的页面。
 */

public class GprModulePageGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="功能模块。外键，引用功能模块表（module）的主键。",hidden=false,required=false)
    private String moduleId;
    @ApiModelProperty(value="系统页面。外键，引用应用系统页面表（page）的主键。",hidden=false,required=false)
    private String pageId;
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
	 * get方法。系统页面。外键，引用应用系统页面表（page）的主键。
	 */
	public String getPageId() {
		return this.pageId;
	}

	/**
	 * set方法。系统页面。外键，引用应用系统页面表（page）的主键。
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}
    

    //一对多关系中，多端数据列表

}







