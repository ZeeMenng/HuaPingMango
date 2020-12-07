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
 * @updateDate 2020/8/11 11:43:17
 * @description 实体类DaEnterpriseShareholderInfoGenEnt，自动生成。企业股东信息表
 */

public class DaEnterpriseShareholderInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="企业id。外键。对应企业信息表enterprise主键。",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="企业名称。对应企业信息表（enterprise）字段（enterprise_name）。",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="股东类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：自然人股东  2：法人股东",allowableValues="0,1",hidden=false,required=false)
    private Byte natureCode;
    @ApiModelProperty(value="股东类型。文本，对应数据字典表（dictionary）中的编码字段（name）。目前有2中类型 1：自然人股东  2：法人股东",hidden=false,required=false)
    private String natureText;
    @ApiModelProperty(value="实缴出资额",hidden=false,required=false)
    private String paidAmountSum;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal shareRate;
    @ApiModelProperty(value="股东名称",hidden=false,required=false)
    private String shareholderName;
    @ApiModelProperty(value="认缴出资额",hidden=false,required=false)
    private String subscribedAmountSum;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。股东类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：自然人股东  2：法人股东
	 */
	public Byte getNatureCode() {
		return this.natureCode;
	}

	/**
	 * set方法。股东类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：自然人股东  2：法人股东
	 */
	public void setNatureCode(Byte natureCode) {
		this.natureCode = natureCode;
	}
    
	/**
	 * get方法。股东类型。文本，对应数据字典表（dictionary）中的编码字段（name）。目前有2中类型 1：自然人股东  2：法人股东
	 */
	public String getNatureText() {
		return this.natureText;
	}

	/**
	 * set方法。股东类型。文本，对应数据字典表（dictionary）中的编码字段（name）。目前有2中类型 1：自然人股东  2：法人股东
	 */
	public void setNatureText(String natureText) {
		this.natureText = natureText;
	}
    
	/**
	 * get方法。实缴出资额
	 */
	public String getPaidAmountSum() {
		return this.paidAmountSum;
	}

	/**
	 * set方法。实缴出资额
	 */
	public void setPaidAmountSum(String paidAmountSum) {
		this.paidAmountSum = paidAmountSum;
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
	 * get方法。
	 */
	public BigDecimal getShareRate() {
		return this.shareRate;
	}

	/**
	 * set方法。
	 */
	public void setShareRate(BigDecimal shareRate) {
		this.shareRate = shareRate;
	}
    
	/**
	 * get方法。股东名称
	 */
	public String getShareholderName() {
		return this.shareholderName;
	}

	/**
	 * set方法。股东名称
	 */
	public void setShareholderName(String shareholderName) {
		this.shareholderName = shareholderName;
	}
    
	/**
	 * get方法。认缴出资额
	 */
	public String getSubscribedAmountSum() {
		return this.subscribedAmountSum;
	}

	/**
	 * set方法。认缴出资额
	 */
	public void setSubscribedAmountSum(String subscribedAmountSum) {
		this.subscribedAmountSum = subscribedAmountSum;
	}
    

    //一对多关系中，多端数据列表

}







