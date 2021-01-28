package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:34
 * @description 实体类MfPrewarningValueGenEnt，自动生成。质量安全预警值设置
 */

public class MfPrewarningValueGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="农资处罚月预警值",hidden=false,required=false)
    private Integer agriculturalCapitalPunishment;
    @ApiModelProperty(value="创建日期",hidden=false,required=false)
    private Date createDate;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String creator;
    @ApiModelProperty(value="违法案件月预警值",hidden=false,required=false)
    private Integer entryViolation;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="投诉反馈月预警值",hidden=false,required=false)
    private Integer qualityFeedback;
    @ApiModelProperty(value="质量抽检不合格月预警值",hidden=false,required=false)
    private Integer qualitySpotCheckDisqualification;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。农资处罚月预警值
	 */
	public Integer getAgriculturalCapitalPunishment() {
		return this.agriculturalCapitalPunishment;
	}

	/**
	 * set方法。农资处罚月预警值
	 */
	public void setAgriculturalCapitalPunishment(Integer agriculturalCapitalPunishment) {
		this.agriculturalCapitalPunishment = agriculturalCapitalPunishment;
	}
    
	/**
	 * get方法。创建日期
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * set方法。创建日期
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
	/**
	 * get方法。
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * set方法。
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
    
	/**
	 * get方法。违法案件月预警值
	 */
	public Integer getEntryViolation() {
		return this.entryViolation;
	}

	/**
	 * set方法。违法案件月预警值
	 */
	public void setEntryViolation(Integer entryViolation) {
		this.entryViolation = entryViolation;
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
	 * get方法。投诉反馈月预警值
	 */
	public Integer getQualityFeedback() {
		return this.qualityFeedback;
	}

	/**
	 * set方法。投诉反馈月预警值
	 */
	public void setQualityFeedback(Integer qualityFeedback) {
		this.qualityFeedback = qualityFeedback;
	}
    
	/**
	 * get方法。质量抽检不合格月预警值
	 */
	public Integer getQualitySpotCheckDisqualification() {
		return this.qualitySpotCheckDisqualification;
	}

	/**
	 * set方法。质量抽检不合格月预警值
	 */
	public void setQualitySpotCheckDisqualification(Integer qualitySpotCheckDisqualification) {
		this.qualitySpotCheckDisqualification = qualitySpotCheckDisqualification;
	}
    










}







