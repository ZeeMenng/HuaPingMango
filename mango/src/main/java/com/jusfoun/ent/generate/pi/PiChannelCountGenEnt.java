﻿package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:12
 * @description 实体类PiChannelCountGenEnt，自动生成。CMS栏目访问量计数表
 */

public class PiChannelCountGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String channelId;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="总访问数",hidden=false,required=true)
    private Integer views;
    @ApiModelProperty(value="日访问数",hidden=false,required=true)
    private Integer viewsDay;
    @ApiModelProperty(value="月访问数",hidden=false,required=true)
    private Integer viewsMonth;
    @ApiModelProperty(value="周访问数",hidden=false,required=true)
    private Integer viewsWeek;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。
	 */
	public String getChannelId() {
		return this.channelId;
	}

	/**
	 * set方法。
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
    
	/**
	 * get方法。主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。总访问数
	 */
	public Integer getViews() {
		return this.views;
	}

	/**
	 * set方法。总访问数
	 */
	public void setViews(Integer views) {
		this.views = views;
	}
    
	/**
	 * get方法。日访问数
	 */
	public Integer getViewsDay() {
		return this.viewsDay;
	}

	/**
	 * set方法。日访问数
	 */
	public void setViewsDay(Integer viewsDay) {
		this.viewsDay = viewsDay;
	}
    
	/**
	 * get方法。月访问数
	 */
	public Integer getViewsMonth() {
		return this.viewsMonth;
	}

	/**
	 * set方法。月访问数
	 */
	public void setViewsMonth(Integer viewsMonth) {
		this.viewsMonth = viewsMonth;
	}
    
	/**
	 * get方法。周访问数
	 */
	public Integer getViewsWeek() {
		return this.viewsWeek;
	}

	/**
	 * set方法。周访问数
	 */
	public void setViewsWeek(Integer viewsWeek) {
		this.viewsWeek = viewsWeek;
	}
    

    //一对多关系中，多端数据列表

}







