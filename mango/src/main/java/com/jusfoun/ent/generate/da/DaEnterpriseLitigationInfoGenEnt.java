package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:54
 * @description 实体类DaEnterpriseLitigationInfoGenEnt，自动生成。企业诉讼表
 */

public class DaEnterpriseLitigationInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String accuser;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cardId;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String defendant;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String executionCourt;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String executionName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String executionObject;
    @ApiModelProperty(value="立案时间",hidden=false,required=false)
    private Date filingTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String lawState;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String litigationContent;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String litigationUrl;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String referenceNumber;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="发生年份",hidden=false,required=false)
    private Integer year;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAccuser() {
		return this.accuser;
	}

	/**
	 * set方法。
	 */
	public void setAccuser(String accuser) {
		this.accuser = accuser;
	}
    
	/**
	 * get方法。
	 */
	public String getCardId() {
		return this.cardId;
	}

	/**
	 * set方法。
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
    
	/**
	 * get方法。创建时间
	 */
	public Date getCreatedTime() {
		return this.createdTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
    
	/**
	 * get方法。数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入
	 */
	public Byte getDatasourceCode() {
		return this.datasourceCode;
	}

	/**
	 * set方法。数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入
	 */
	public void setDatasourceCode(Byte datasourceCode) {
		this.datasourceCode = datasourceCode;
	}
    
	/**
	 * get方法。
	 */
	public String getDefendant() {
		return this.defendant;
	}

	/**
	 * set方法。
	 */
	public void setDefendant(String defendant) {
		this.defendant = defendant;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
    
	/**
	 * get方法。
	 */
	public String getExecutionCourt() {
		return this.executionCourt;
	}

	/**
	 * set方法。
	 */
	public void setExecutionCourt(String executionCourt) {
		this.executionCourt = executionCourt;
	}
    
	/**
	 * get方法。
	 */
	public String getExecutionName() {
		return this.executionName;
	}

	/**
	 * set方法。
	 */
	public void setExecutionName(String executionName) {
		this.executionName = executionName;
	}
    
	/**
	 * get方法。
	 */
	public String getExecutionObject() {
		return this.executionObject;
	}

	/**
	 * set方法。
	 */
	public void setExecutionObject(String executionObject) {
		this.executionObject = executionObject;
	}
    
	/**
	 * get方法。立案时间
	 */
	public Date getFilingTime() {
		return this.filingTime;
	}

	/**
	 * set方法。立案时间
	 */
	public void setFilingTime(Date filingTime) {
		this.filingTime = filingTime;
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
	public String getLawState() {
		return this.lawState;
	}

	/**
	 * set方法。
	 */
	public void setLawState(String lawState) {
		this.lawState = lawState;
	}
    
	/**
	 * get方法。
	 */
	public String getLitigationContent() {
		return this.litigationContent;
	}

	/**
	 * set方法。
	 */
	public void setLitigationContent(String litigationContent) {
		this.litigationContent = litigationContent;
	}
    
	/**
	 * get方法。
	 */
	public String getLitigationUrl() {
		return this.litigationUrl;
	}

	/**
	 * set方法。
	 */
	public void setLitigationUrl(String litigationUrl) {
		this.litigationUrl = litigationUrl;
	}
    
	/**
	 * get方法。
	 */
	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	/**
	 * set方法。
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionText() {
		return this.regionText;
	}

	/**
	 * set方法。
	 */
	public void setRegionText(String regionText) {
		this.regionText = regionText;
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
	 * get方法。发生年份
	 */
	public Integer getYear() {
		return this.year;
	}

	/**
	 * set方法。发生年份
	 */
	public void setYear(Integer year) {
		this.year = year;
	}
    










}







