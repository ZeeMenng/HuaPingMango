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
 * @updateDate 2020/8/11 11:43:18
 * @description 实体类DaEnterpriseTaxInfoGenEnt，自动生成。企业税务信息表
 */

public class DaEnterpriseTaxInfoGenEnt extends BaseEnt implements Serializable {
    
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
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="纳税人识别号",allowableValues="0,1",hidden=false,required=false)
    private String taxCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal taxMoney;
    @ApiModelProperty(value="纳税机关",hidden=false,required=false)
    private String taxOrgan;
    @ApiModelProperty(value="纳税人状态。   编码，对应数据字典表（dictionary）中的编码字段（code)  不确定具体类型   0：其他",allowableValues="0,1",hidden=false,required=false)
    private Byte taxPersonStatusCode;
    @ApiModelProperty(value="纳税人状态。文本，对应数据字典表（dictionary）中的文本字段（name） 不确定具体类型   0：其他",hidden=false,required=false)
    private String taxPersonStatusText;
    @ApiModelProperty(value="纳税状态。  编码，对应数据字典表（dictionary）中的编码字段（code）   不确定具体类型    0：其他",allowableValues="0,1",hidden=false,required=false)
    private Byte taxStatusCode;
    @ApiModelProperty(value="纳税状态。文本，对应数据字典表（dictionary）中的文本字段（name）     不确定具体类型   0：其他",hidden=false,required=false)
    private String taxStatusText;
    @ApiModelProperty(value="纳税时间",hidden=false,required=false)
    private Date taxTime;
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
	 * get方法。纳税人识别号
	 */
	public String getTaxCode() {
		return this.taxCode;
	}

	/**
	 * set方法。纳税人识别号
	 */
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTaxMoney() {
		return this.taxMoney;
	}

	/**
	 * set方法。
	 */
	public void setTaxMoney(BigDecimal taxMoney) {
		this.taxMoney = taxMoney;
	}
    
	/**
	 * get方法。纳税机关
	 */
	public String getTaxOrgan() {
		return this.taxOrgan;
	}

	/**
	 * set方法。纳税机关
	 */
	public void setTaxOrgan(String taxOrgan) {
		this.taxOrgan = taxOrgan;
	}
    
	/**
	 * get方法。纳税人状态。   编码，对应数据字典表（dictionary）中的编码字段（code)  不确定具体类型   0：其他
	 */
	public Byte getTaxPersonStatusCode() {
		return this.taxPersonStatusCode;
	}

	/**
	 * set方法。纳税人状态。   编码，对应数据字典表（dictionary）中的编码字段（code)  不确定具体类型   0：其他
	 */
	public void setTaxPersonStatusCode(Byte taxPersonStatusCode) {
		this.taxPersonStatusCode = taxPersonStatusCode;
	}
    
	/**
	 * get方法。纳税人状态。文本，对应数据字典表（dictionary）中的文本字段（name） 不确定具体类型   0：其他
	 */
	public String getTaxPersonStatusText() {
		return this.taxPersonStatusText;
	}

	/**
	 * set方法。纳税人状态。文本，对应数据字典表（dictionary）中的文本字段（name） 不确定具体类型   0：其他
	 */
	public void setTaxPersonStatusText(String taxPersonStatusText) {
		this.taxPersonStatusText = taxPersonStatusText;
	}
    
	/**
	 * get方法。纳税状态。  编码，对应数据字典表（dictionary）中的编码字段（code）   不确定具体类型    0：其他
	 */
	public Byte getTaxStatusCode() {
		return this.taxStatusCode;
	}

	/**
	 * set方法。纳税状态。  编码，对应数据字典表（dictionary）中的编码字段（code）   不确定具体类型    0：其他
	 */
	public void setTaxStatusCode(Byte taxStatusCode) {
		this.taxStatusCode = taxStatusCode;
	}
    
	/**
	 * get方法。纳税状态。文本，对应数据字典表（dictionary）中的文本字段（name）     不确定具体类型   0：其他
	 */
	public String getTaxStatusText() {
		return this.taxStatusText;
	}

	/**
	 * set方法。纳税状态。文本，对应数据字典表（dictionary）中的文本字段（name）     不确定具体类型   0：其他
	 */
	public void setTaxStatusText(String taxStatusText) {
		this.taxStatusText = taxStatusText;
	}
    
	/**
	 * get方法。纳税时间
	 */
	public Date getTaxTime() {
		return this.taxTime;
	}

	/**
	 * set方法。纳税时间
	 */
	public void setTaxTime(Date taxTime) {
		this.taxTime = taxTime;
	}
    

    //一对多关系中，多端数据列表

}







