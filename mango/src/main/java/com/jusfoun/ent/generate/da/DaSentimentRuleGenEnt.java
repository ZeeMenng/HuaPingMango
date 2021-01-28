package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:09
 * @description 实体类DaSentimentRuleGenEnt，自动生成。舆情采集规则表
 */

public class DaSentimentRuleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String endTime;
    @ApiModelProperty(value="采集频率（N条/小时）",hidden=false,required=true)
    private Integer frequency;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String mediaTypeCodeSet;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String mediaTypeTextSet;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String ruleName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String startTime;
    @ApiModelProperty(value="状态code，对应数据字典表,状态类型字典",allowableValues="0,1",hidden=false,required=false)
    private Byte statusCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String statusText;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。
	 */
	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * set方法。
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
    
	/**
	 * get方法。采集频率（N条/小时）
	 */
	public Integer getFrequency() {
		return this.frequency;
	}

	/**
	 * set方法。采集频率（N条/小时）
	 */
	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
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
	public String getMediaTypeCodeSet() {
		return this.mediaTypeCodeSet;
	}

	/**
	 * set方法。
	 */
	public void setMediaTypeCodeSet(String mediaTypeCodeSet) {
		this.mediaTypeCodeSet = mediaTypeCodeSet;
	}
    
	/**
	 * get方法。
	 */
	public String getMediaTypeTextSet() {
		return this.mediaTypeTextSet;
	}

	/**
	 * set方法。
	 */
	public void setMediaTypeTextSet(String mediaTypeTextSet) {
		this.mediaTypeTextSet = mediaTypeTextSet;
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
	public String getRuleName() {
		return this.ruleName;
	}

	/**
	 * set方法。
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
    
	/**
	 * get方法。
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * set方法。
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
    
	/**
	 * get方法。状态code，对应数据字典表,状态类型字典
	 */
	public Byte getStatusCode() {
		return this.statusCode;
	}

	/**
	 * set方法。状态code，对应数据字典表,状态类型字典
	 */
	public void setStatusCode(Byte statusCode) {
		this.statusCode = statusCode;
	}
    
	/**
	 * get方法。
	 */
	public String getStatusText() {
		return this.statusText;
	}

	/**
	 * set方法。
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
    










}







