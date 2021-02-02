package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:50
 * @description 实体类PiAdvertisingGenEnt，自动生成。CMS广告表
 */

public class PiAdvertisingGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录添加时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="广告类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义三种类型：1图片，2Flash，3文字。",allowableValues="0,1",hidden=false,required=true)
    private Byte categoryCode;
    @ApiModelProperty(value="点击次数。",hidden=false,required=false)
    private long clickCount;
    @ApiModelProperty(value="展现次数。",hidden=false,required=true)
    private long displayCount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="结束时间。",hidden=false,required=false)
    private Date endTime;
    @ApiModelProperty(value="展示高度。",hidden=false,required=false)
    private Integer height;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=true)
    private Byte isEnabledCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resouceId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resourcePath;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="广告版位。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种版位：1资讯版热点中的通栏广告，2数据版首页中的导航栏。",allowableValues="0,1",hidden=false,required=true)
    private Byte spaceCode;
    @ApiModelProperty(value="开始时间。",hidden=false,required=false)
    private Date startTime;
    @ApiModelProperty(value="打开方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种方式：1_self相同的框架或者窗口中打开，2_blank在新窗口中打开。",allowableValues="0,1",hidden=false,required=false)
    private Byte targetCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String targetUrl;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String title;
    @ApiModelProperty(value="记录修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="广告权重。",hidden=false,required=true)
    private Integer weight;
    @ApiModelProperty(value="展示宽度。",hidden=false,required=false)
    private Integer width;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。记录添加时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录添加时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。广告类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义三种类型：1图片，2Flash，3文字。
	 */
	public Byte getCategoryCode() {
		return this.categoryCode;
	}

	/**
	 * set方法。广告类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义三种类型：1图片，2Flash，3文字。
	 */
	public void setCategoryCode(Byte categoryCode) {
		this.categoryCode = categoryCode;
	}
    
	/**
	 * get方法。点击次数。
	 */
	public long getClickCount() {
		return this.clickCount;
	}

	/**
	 * set方法。点击次数。
	 */
	public void setClickCount(long clickCount) {
		this.clickCount = clickCount;
	}
    
	/**
	 * get方法。展现次数。
	 */
	public long getDisplayCount() {
		return this.displayCount;
	}

	/**
	 * set方法。展现次数。
	 */
	public void setDisplayCount(long displayCount) {
		this.displayCount = displayCount;
	}
    
	/**
	 * get方法。
	 */
	public String getDomainId() {
		return this.domainId;
	}

	/**
	 * set方法。
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
    
	/**
	 * get方法。结束时间。
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * set方法。结束时间。
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
    
	/**
	 * get方法。展示高度。
	 */
	public Integer getHeight() {
		return this.height;
	}

	/**
	 * set方法。展示高度。
	 */
	public void setHeight(Integer height) {
		this.height = height;
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
	 * get方法。是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsEnabledCode() {
		return this.isEnabledCode;
	}

	/**
	 * set方法。是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsEnabledCode(Byte isEnabledCode) {
		this.isEnabledCode = isEnabledCode;
	}
    
	/**
	 * get方法。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。
	 */
	public String getResouceId() {
		return this.resouceId;
	}

	/**
	 * set方法。
	 */
	public void setResouceId(String resouceId) {
		this.resouceId = resouceId;
	}
    
	/**
	 * get方法。
	 */
	public String getResourcePath() {
		return this.resourcePath;
	}

	/**
	 * set方法。
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
    
	/**
	 * get方法。
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
    
	/**
	 * get方法。广告版位。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种版位：1资讯版热点中的通栏广告，2数据版首页中的导航栏。
	 */
	public Byte getSpaceCode() {
		return this.spaceCode;
	}

	/**
	 * set方法。广告版位。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种版位：1资讯版热点中的通栏广告，2数据版首页中的导航栏。
	 */
	public void setSpaceCode(Byte spaceCode) {
		this.spaceCode = spaceCode;
	}
    
	/**
	 * get方法。开始时间。
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * set方法。开始时间。
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
    
	/**
	 * get方法。打开方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种方式：1_self相同的框架或者窗口中打开，2_blank在新窗口中打开。
	 */
	public Byte getTargetCode() {
		return this.targetCode;
	}

	/**
	 * set方法。打开方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种方式：1_self相同的框架或者窗口中打开，2_blank在新窗口中打开。
	 */
	public void setTargetCode(Byte targetCode) {
		this.targetCode = targetCode;
	}
    
	/**
	 * get方法。
	 */
	public String getTargetUrl() {
		return this.targetUrl;
	}

	/**
	 * set方法。
	 */
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
    
	/**
	 * get方法。
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * set方法。
	 */
	public void setTitle(String title) {
		this.title = title;
	}
    
	/**
	 * get方法。记录修改时间。
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。记录修改时间。
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	/**
	 * get方法。广告权重。
	 */
	public Integer getWeight() {
		return this.weight;
	}

	/**
	 * set方法。广告权重。
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
    
	/**
	 * get方法。展示宽度。
	 */
	public Integer getWidth() {
		return this.width;
	}

	/**
	 * set方法。展示宽度。
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}
    










}







