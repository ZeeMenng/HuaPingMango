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
 * @description 实体类DaEnterpriseCertificateInfoGenEnt，自动生成。企业证书
 */

public class DaEnterpriseCertificateInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cerName;
    @ApiModelProperty(value="证书状态。编码，对应数据字典表（dictionary）中的编码字段（code） 目前定义两种状态   0：有效期内 1：证件过期",allowableValues="0,1",hidden=false,required=false)
    private Byte certificateStatusCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String certificateStatusText;
    @ApiModelProperty(value="证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志",allowableValues="0,1",hidden=false,required=false)
    private Byte certificateTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String certificateTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String content;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String detail;
    @ApiModelProperty(value="有效期至",hidden=false,required=false)
    private Date endDate;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String fileNo;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String fileResourceId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String fileResourcePath;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String orderBy;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String organ;
    @ApiModelProperty(value="公示时间",hidden=false,required=false)
    private Date publicityTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="有效期自",hidden=false,required=false)
    private Date startDate;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getCerName() {
		return this.cerName;
	}

	/**
	 * set方法。
	 */
	public void setCerName(String cerName) {
		this.cerName = cerName;
	}
    
	/**
	 * get方法。证书状态。编码，对应数据字典表（dictionary）中的编码字段（code） 目前定义两种状态   0：有效期内 1：证件过期
	 */
	public Byte getCertificateStatusCode() {
		return this.certificateStatusCode;
	}

	/**
	 * set方法。证书状态。编码，对应数据字典表（dictionary）中的编码字段（code） 目前定义两种状态   0：有效期内 1：证件过期
	 */
	public void setCertificateStatusCode(Byte certificateStatusCode) {
		this.certificateStatusCode = certificateStatusCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCertificateStatusText() {
		return this.certificateStatusText;
	}

	/**
	 * set方法。
	 */
	public void setCertificateStatusText(String certificateStatusText) {
		this.certificateStatusText = certificateStatusText;
	}
    
	/**
	 * get方法。证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志
	 */
	public Byte getCertificateTypeCode() {
		return this.certificateTypeCode;
	}

	/**
	 * set方法。证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志
	 */
	public void setCertificateTypeCode(Byte certificateTypeCode) {
		this.certificateTypeCode = certificateTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCertificateTypeText() {
		return this.certificateTypeText;
	}

	/**
	 * set方法。
	 */
	public void setCertificateTypeText(String certificateTypeText) {
		this.certificateTypeText = certificateTypeText;
	}
    
	/**
	 * get方法。
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * set方法。
	 */
	public void setContent(String content) {
		this.content = content;
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
	public String getDetail() {
		return this.detail;
	}

	/**
	 * set方法。
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
    
	/**
	 * get方法。有效期至
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * set方法。有效期至
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	public String getFileNo() {
		return this.fileNo;
	}

	/**
	 * set方法。
	 */
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
    
	/**
	 * get方法。
	 */
	public String getFileResourceId() {
		return this.fileResourceId;
	}

	/**
	 * set方法。
	 */
	public void setFileResourceId(String fileResourceId) {
		this.fileResourceId = fileResourceId;
	}
    
	/**
	 * get方法。
	 */
	public String getFileResourcePath() {
		return this.fileResourcePath;
	}

	/**
	 * set方法。
	 */
	public void setFileResourcePath(String fileResourcePath) {
		this.fileResourcePath = fileResourcePath;
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
	public String getOrderBy() {
		return this.orderBy;
	}

	/**
	 * set方法。
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    
	/**
	 * get方法。
	 */
	public String getOrgan() {
		return this.organ;
	}

	/**
	 * set方法。
	 */
	public void setOrgan(String organ) {
		this.organ = organ;
	}
    
	/**
	 * get方法。公示时间
	 */
	public Date getPublicityTime() {
		return this.publicityTime;
	}

	/**
	 * set方法。公示时间
	 */
	public void setPublicityTime(Date publicityTime) {
		this.publicityTime = publicityTime;
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
	 * get方法。有效期自
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * set方法。有效期自
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
    










}







