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
 * @description 实体类DaEnterpriseLoanInfoGenEnt，自动生成。企业贷款记录表
 */

public class DaEnterpriseLoanInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
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
    @ApiModelProperty(value="贷款时间",hidden=false,required=false)
    private Date loanDate;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal loanMoney;
    @ApiModelProperty(value="当前状态。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义2种状态   1：还息中 2：其他",hidden=false,required=false)
    private Byte loanStatus;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String loanStatusText;
    @ApiModelProperty(value="贷款方式。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义4种方式：1等额本息还款； 2等额本金还款； 3一次性还本付息；4按期付息还本",allowableValues="0,1",hidden=false,required=false)
    private Byte loanTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String loanTypeText;
    @ApiModelProperty(value="是否逾期标识。编码，对应数据字典表（dictionary）中的编码字段（code）  字段类型  0：是  1：否",allowableValues="0,1",hidden=false,required=false)
    private Byte overdueStatusCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String overdueStatusText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。贷款时间
	 */
	public Date getLoanDate() {
		return this.loanDate;
	}

	/**
	 * set方法。贷款时间
	 */
	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getLoanMoney() {
		return this.loanMoney;
	}

	/**
	 * set方法。
	 */
	public void setLoanMoney(BigDecimal loanMoney) {
		this.loanMoney = loanMoney;
	}
    
	/**
	 * get方法。当前状态。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义2种状态   1：还息中 2：其他
	 */
	public Byte getLoanStatus() {
		return this.loanStatus;
	}

	/**
	 * set方法。当前状态。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义2种状态   1：还息中 2：其他
	 */
	public void setLoanStatus(Byte loanStatus) {
		this.loanStatus = loanStatus;
	}
    
	/**
	 * get方法。
	 */
	public String getLoanStatusText() {
		return this.loanStatusText;
	}

	/**
	 * set方法。
	 */
	public void setLoanStatusText(String loanStatusText) {
		this.loanStatusText = loanStatusText;
	}
    
	/**
	 * get方法。贷款方式。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义4种方式：1等额本息还款； 2等额本金还款； 3一次性还本付息；4按期付息还本
	 */
	public Byte getLoanTypeCode() {
		return this.loanTypeCode;
	}

	/**
	 * set方法。贷款方式。编码，对应数据字典表（dictionary）中的编码字段（code）目前先定义4种方式：1等额本息还款； 2等额本金还款； 3一次性还本付息；4按期付息还本
	 */
	public void setLoanTypeCode(Byte loanTypeCode) {
		this.loanTypeCode = loanTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getLoanTypeText() {
		return this.loanTypeText;
	}

	/**
	 * set方法。
	 */
	public void setLoanTypeText(String loanTypeText) {
		this.loanTypeText = loanTypeText;
	}
    
	/**
	 * get方法。是否逾期标识。编码，对应数据字典表（dictionary）中的编码字段（code）  字段类型  0：是  1：否
	 */
	public Byte getOverdueStatusCode() {
		return this.overdueStatusCode;
	}

	/**
	 * set方法。是否逾期标识。编码，对应数据字典表（dictionary）中的编码字段（code）  字段类型  0：是  1：否
	 */
	public void setOverdueStatusCode(Byte overdueStatusCode) {
		this.overdueStatusCode = overdueStatusCode;
	}
    
	/**
	 * get方法。
	 */
	public String getOverdueStatusText() {
		return this.overdueStatusText;
	}

	/**
	 * set方法。
	 */
	public void setOverdueStatusText(String overdueStatusText) {
		this.overdueStatusText = overdueStatusText;
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







