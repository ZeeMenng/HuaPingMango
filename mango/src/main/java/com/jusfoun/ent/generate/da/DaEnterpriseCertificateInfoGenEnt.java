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
 * @updateDate 2020/8/11 11:43:12
 * @description 实体类DaEnterpriseCertificateInfoGenEnt，自动生成。企业证书
 */

public class DaEnterpriseCertificateInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="证书名称",hidden=false,required=false)
    private String cerName;
    @ApiModelProperty(value="证书状态。编码，对应数据字典表（dictionary）中的编码字段（code） 目前定义两种状态   0：有效期内 1：证件过期",allowableValues="0,1",hidden=false,required=false)
    private Byte certificateStatusCode;
    @ApiModelProperty(value="证书状态。文本，对应数据字典表（dictionary）中的文本字段（name）目前定义两种状态   0：有效期内 1：证件过期",hidden=false,required=false)
    private String certificateStatusText;
    @ApiModelProperty(value="证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志",allowableValues="0,1",hidden=false,required=false)
    private Byte certificateTypeCode;
    @ApiModelProperty(value="证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志",hidden=false,required=false)
    private String certificateTypeText;
    @ApiModelProperty(value="许可内容",hidden=false,required=false)
    private String content;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="详情",hidden=false,required=false)
    private String detail;
    @ApiModelProperty(value="有效期至",hidden=false,required=false)
    private Date endDate;
    @ApiModelProperty(value="企业id。外键。对应企业信息表enterprise主键。",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="企业名称。对应企业信息表（enterprise）字段（enterprise_name）。",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="许可文件编号",hidden=false,required=false)
    private String fileNo;
    @ApiModelProperty(value="证书图片信息。外键，对应通用资源表（Resource）的主键。",hidden=false,required=false)
    private String fileResourceId;
    @ApiModelProperty(value="证书图片信息。路径，对应通用资源表（Resource）的路径（Path）字段。",hidden=false,required=false)
    private String fileResourcePath;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="序号",hidden=false,required=false)
    private String orderBy;
    @ApiModelProperty(value="许可机关",hidden=false,required=false)
    private String organ;
    @ApiModelProperty(value="公示时间",hidden=false,required=false)
    private Date publicityTime;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="有效期自",hidden=false,required=false)
    private Date startDate;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。证书名称
	 */
	public String getCerName() {
		return this.cerName;
	}

	/**
	 * set方法。证书名称
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
	 * get方法。证书状态。文本，对应数据字典表（dictionary）中的文本字段（name）目前定义两种状态   0：有效期内 1：证件过期
	 */
	public String getCertificateStatusText() {
		return this.certificateStatusText;
	}

	/**
	 * set方法。证书状态。文本，对应数据字典表（dictionary）中的文本字段（name）目前定义两种状态   0：有效期内 1：证件过期
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
	 * get方法。证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志
	 */
	public String getCertificateTypeText() {
		return this.certificateTypeText;
	}

	/**
	 * set方法。证书类型。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：通用资质 2：无公害农产品 3：绿色食品 4：有机农产品 5：农产品地理标志
	 */
	public void setCertificateTypeText(String certificateTypeText) {
		this.certificateTypeText = certificateTypeText;
	}
    
	/**
	 * get方法。许可内容
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * set方法。许可内容
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
	 * get方法。详情
	 */
	public String getDetail() {
		return this.detail;
	}

	/**
	 * set方法。详情
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
	 * get方法。许可文件编号
	 */
	public String getFileNo() {
		return this.fileNo;
	}

	/**
	 * set方法。许可文件编号
	 */
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
    
	/**
	 * get方法。证书图片信息。外键，对应通用资源表（Resource）的主键。
	 */
	public String getFileResourceId() {
		return this.fileResourceId;
	}

	/**
	 * set方法。证书图片信息。外键，对应通用资源表（Resource）的主键。
	 */
	public void setFileResourceId(String fileResourceId) {
		this.fileResourceId = fileResourceId;
	}
    
	/**
	 * get方法。证书图片信息。路径，对应通用资源表（Resource）的路径（Path）字段。
	 */
	public String getFileResourcePath() {
		return this.fileResourcePath;
	}

	/**
	 * set方法。证书图片信息。路径，对应通用资源表（Resource）的路径（Path）字段。
	 */
	public void setFileResourcePath(String fileResourcePath) {
		this.fileResourcePath = fileResourcePath;
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
	 * get方法。序号
	 */
	public String getOrderBy() {
		return this.orderBy;
	}

	/**
	 * set方法。序号
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
    
	/**
	 * get方法。许可机关
	 */
	public String getOrgan() {
		return this.organ;
	}

	/**
	 * set方法。许可机关
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
    

    //一对多关系中，多端数据列表

}







