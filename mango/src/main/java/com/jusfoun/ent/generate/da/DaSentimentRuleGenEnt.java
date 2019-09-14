package com.jusfoun.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-22 16:33:33
 * @description 实体类DaSentimentRuleGenEnt，自动生成。舆情采集规则表
 */

public class DaSentimentRuleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="创建人",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="结束时间",hidden=false,required=true)
    private String endTime;
    @ApiModelProperty(value="采集频率（N条/小时）",hidden=false,required=true)
    private Integer frequency;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="媒体类型code集合，用逗号隔开",allowableValues="0,1",hidden=false,required=false)
    private String mediaTypeCodeSet;
    @ApiModelProperty(value="媒体类型text集合，用逗号隔开",hidden=false,required=false)
    private String mediaTypeTextSet;
    @ApiModelProperty(value="描述",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="规则名称",hidden=false,required=false)
    private String ruleName;
    @ApiModelProperty(value="开始时间",hidden=false,required=true)
    private String startTime;
    @ApiModelProperty(value="状态code，对应数据字典表,状态类型字典",allowableValues="0,1",hidden=false,required=false)
    private Byte statusCode;
    @ApiModelProperty(value="状态text，对应数据字典表,状态类型字典",hidden=false,required=false)
    private String statusText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。创建人
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。创建人
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。结束时间
	 */
	public String getEndTime() {
		return this.endTime;
	}

	/**
	 * set方法。结束时间
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
	 * get方法。主键id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键id
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。媒体类型code集合，用逗号隔开
	 */
	public String getMediaTypeCodeSet() {
		return this.mediaTypeCodeSet;
	}

	/**
	 * set方法。媒体类型code集合，用逗号隔开
	 */
	public void setMediaTypeCodeSet(String mediaTypeCodeSet) {
		this.mediaTypeCodeSet = mediaTypeCodeSet;
	}
    
	/**
	 * get方法。媒体类型text集合，用逗号隔开
	 */
	public String getMediaTypeTextSet() {
		return this.mediaTypeTextSet;
	}

	/**
	 * set方法。媒体类型text集合，用逗号隔开
	 */
	public void setMediaTypeTextSet(String mediaTypeTextSet) {
		this.mediaTypeTextSet = mediaTypeTextSet;
	}
    
	/**
	 * get方法。描述
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。规则名称
	 */
	public String getRuleName() {
		return this.ruleName;
	}

	/**
	 * set方法。规则名称
	 */
	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}
    
	/**
	 * get方法。开始时间
	 */
	public String getStartTime() {
		return this.startTime;
	}

	/**
	 * set方法。开始时间
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
	 * get方法。状态text，对应数据字典表,状态类型字典
	 */
	public String getStatusText() {
		return this.statusText;
	}

	/**
	 * set方法。状态text，对应数据字典表,状态类型字典
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
    

    //一对多关系中，多端数据列表

}







