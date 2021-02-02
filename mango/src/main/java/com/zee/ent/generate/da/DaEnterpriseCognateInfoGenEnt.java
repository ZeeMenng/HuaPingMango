package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:52
 * @description 实体类DaEnterpriseCognateInfoGenEnt，自动生成。企业关联企业信息表，企业与企业之间关系。
 */

public class DaEnterpriseCognateInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cognateEnterpriseId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cognateEnterpriseName;
    @ApiModelProperty(value="关联关系类型。  编码，对应数据字典表（dictionary）中的编码字段（code）目前定义2种关系  1：主动关联 2：被动关联",allowableValues="0,1",hidden=false,required=false)
    private Byte cognateTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cognateTypeText;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getCognateEnterpriseId() {
		return this.cognateEnterpriseId;
	}

	/**
	 * set方法。
	 */
	public void setCognateEnterpriseId(String cognateEnterpriseId) {
		this.cognateEnterpriseId = cognateEnterpriseId;
	}
    
	/**
	 * get方法。
	 */
	public String getCognateEnterpriseName() {
		return this.cognateEnterpriseName;
	}

	/**
	 * set方法。
	 */
	public void setCognateEnterpriseName(String cognateEnterpriseName) {
		this.cognateEnterpriseName = cognateEnterpriseName;
	}
    
	/**
	 * get方法。关联关系类型。  编码，对应数据字典表（dictionary）中的编码字段（code）目前定义2种关系  1：主动关联 2：被动关联
	 */
	public Byte getCognateTypeCode() {
		return this.cognateTypeCode;
	}

	/**
	 * set方法。关联关系类型。  编码，对应数据字典表（dictionary）中的编码字段（code）目前定义2种关系  1：主动关联 2：被动关联
	 */
	public void setCognateTypeCode(Byte cognateTypeCode) {
		this.cognateTypeCode = cognateTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCognateTypeText() {
		return this.cognateTypeText;
	}

	/**
	 * set方法。
	 */
	public void setCognateTypeText(String cognateTypeText) {
		this.cognateTypeText = cognateTypeText;
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
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    










}







