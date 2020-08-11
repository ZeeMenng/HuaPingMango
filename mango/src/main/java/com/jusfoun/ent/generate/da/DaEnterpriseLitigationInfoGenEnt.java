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
 * @updateDate 2020/8/11 11:43:15
 * @description 实体类DaEnterpriseLitigationInfoGenEnt，自动生成。企业诉讼表
 */

public class DaEnterpriseLitigationInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="原告",hidden=false,required=false)
    private String accuser;
    @ApiModelProperty(value="身份证号码",hidden=false,required=false)
    private String cardId;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="被告",hidden=false,required=false)
    private String defendant;
    @ApiModelProperty(value="企业id。外键。对应企业信息表enterprise主键。",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="企业名称。对应企业信息表（enterprise）字段（enterprise_name）。",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="执行法院",hidden=false,required=false)
    private String executionCourt;
    @ApiModelProperty(value="被执行人姓名",hidden=false,required=false)
    private String executionName;
    @ApiModelProperty(value="案件标的",hidden=false,required=false)
    private String executionObject;
    @ApiModelProperty(value="立案时间",hidden=false,required=false)
    private Date filingTime;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="案件",hidden=false,required=false)
    private String lawState;
    @ApiModelProperty(value="诉讼内容",hidden=false,required=false)
    private String litigationContent;
    @ApiModelProperty(value="诉讼查询URL地址",hidden=false,required=false)
    private String litigationUrl;
    @ApiModelProperty(value="案号",hidden=false,required=false)
    private String referenceNumber;
    @ApiModelProperty(value="地区代码",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="地区名称",hidden=false,required=false)
    private String regionText;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="发生年份",hidden=false,required=false)
    private Integer year;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。原告
	 */
	public String getAccuser() {
		return this.accuser;
	}

	/**
	 * set方法。原告
	 */
	public void setAccuser(String accuser) {
		this.accuser = accuser;
	}
    
	/**
	 * get方法。身份证号码
	 */
	public String getCardId() {
		return this.cardId;
	}

	/**
	 * set方法。身份证号码
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
	 * get方法。被告
	 */
	public String getDefendant() {
		return this.defendant;
	}

	/**
	 * set方法。被告
	 */
	public void setDefendant(String defendant) {
		this.defendant = defendant;
	}
    
	/**
	 * get方法。企业id。外键。对应企业信息表enterprise主键。
	 */
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	/**
	 * set方法。企业id。外键。对应企业信息表enterprise主键。
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}
    
	/**
	 * get方法。企业名称。对应企业信息表（enterprise）字段（enterprise_name）。
	 */
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * set方法。企业名称。对应企业信息表（enterprise）字段（enterprise_name）。
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
    
	/**
	 * get方法。执行法院
	 */
	public String getExecutionCourt() {
		return this.executionCourt;
	}

	/**
	 * set方法。执行法院
	 */
	public void setExecutionCourt(String executionCourt) {
		this.executionCourt = executionCourt;
	}
    
	/**
	 * get方法。被执行人姓名
	 */
	public String getExecutionName() {
		return this.executionName;
	}

	/**
	 * set方法。被执行人姓名
	 */
	public void setExecutionName(String executionName) {
		this.executionName = executionName;
	}
    
	/**
	 * get方法。案件标的
	 */
	public String getExecutionObject() {
		return this.executionObject;
	}

	/**
	 * set方法。案件标的
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
	 * get方法。案件
	 */
	public String getLawState() {
		return this.lawState;
	}

	/**
	 * set方法。案件
	 */
	public void setLawState(String lawState) {
		this.lawState = lawState;
	}
    
	/**
	 * get方法。诉讼内容
	 */
	public String getLitigationContent() {
		return this.litigationContent;
	}

	/**
	 * set方法。诉讼内容
	 */
	public void setLitigationContent(String litigationContent) {
		this.litigationContent = litigationContent;
	}
    
	/**
	 * get方法。诉讼查询URL地址
	 */
	public String getLitigationUrl() {
		return this.litigationUrl;
	}

	/**
	 * set方法。诉讼查询URL地址
	 */
	public void setLitigationUrl(String litigationUrl) {
		this.litigationUrl = litigationUrl;
	}
    
	/**
	 * get方法。案号
	 */
	public String getReferenceNumber() {
		return this.referenceNumber;
	}

	/**
	 * set方法。案号
	 */
	public void setReferenceNumber(String referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
    
	/**
	 * get方法。地区代码
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。地区代码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。地区名称
	 */
	public String getRegionText() {
		return this.regionText;
	}

	/**
	 * set方法。地区名称
	 */
	public void setRegionText(String regionText) {
		this.regionText = regionText;
	}
    
	/**
	 * get方法。备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注
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
    

    //一对多关系中，多端数据列表

}







