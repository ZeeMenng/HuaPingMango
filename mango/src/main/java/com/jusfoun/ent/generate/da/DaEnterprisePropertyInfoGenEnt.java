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
 * @updateDate 2020/8/11 11:43:16
 * @description 实体类DaEnterprisePropertyInfoGenEnt，自动生成。企业财产信息表
 */

public class DaEnterprisePropertyInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal assetsInterestRate;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal beforeYearOperatingIncome;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal beforeYearTotalAssets;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal beforeYearTotalLiabilities;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="币种",hidden=false,required=false)
    private String currency;
    @ApiModelProperty(value="数据来源。编码，对应数据字典表（dictionary）中的编码字段（code）。目前有2中类型 1：数据中心  2：手工输入",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="企业id。外键。对应企业信息表enterprise主键。",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="企业名称。对应企业信息表（enterprise）字段（enterprise_name）。",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal equityInterestRate;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal fixedAssets;
    @ApiModelProperty(value="是否有效。  编码。对应数据字典表（dictionary）中的编码字段（code）1.有效，0.无效，2.删除        ",allowableValues="0,1",hidden=false,required=false)
    private Byte flagCode;
    @ApiModelProperty(value="是否有效。 文本。对应数据字典表（dictionary）中的文本字段（name）1.有效，0.无效，2.删除  ",hidden=false,required=false)
    private String flagText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal grossProfitMargin;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal invisibleAssetsNetAlue;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal netProfit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal operatingCosts;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal operatingIncome;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal operatingProfit;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="源创建时间",hidden=false,required=false)
    private Date sourceCreateTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalAssets;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalCurrentAssets;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalCurrentQuick;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalLiabilities;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalOwnersEquity;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalProfit;
    @ApiModelProperty(value="更新时间",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="更新人",hidden=false,required=false)
    private String updateUserId;
    @ApiModelProperty(value="发生年份",hidden=false,required=false)
    private Integer year;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。
	 */
	public BigDecimal getAssetsInterestRate() {
		return this.assetsInterestRate;
	}

	/**
	 * set方法。
	 */
	public void setAssetsInterestRate(BigDecimal assetsInterestRate) {
		this.assetsInterestRate = assetsInterestRate;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getBeforeYearOperatingIncome() {
		return this.beforeYearOperatingIncome;
	}

	/**
	 * set方法。
	 */
	public void setBeforeYearOperatingIncome(BigDecimal beforeYearOperatingIncome) {
		this.beforeYearOperatingIncome = beforeYearOperatingIncome;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getBeforeYearTotalAssets() {
		return this.beforeYearTotalAssets;
	}

	/**
	 * set方法。
	 */
	public void setBeforeYearTotalAssets(BigDecimal beforeYearTotalAssets) {
		this.beforeYearTotalAssets = beforeYearTotalAssets;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getBeforeYearTotalLiabilities() {
		return this.beforeYearTotalLiabilities;
	}

	/**
	 * set方法。
	 */
	public void setBeforeYearTotalLiabilities(BigDecimal beforeYearTotalLiabilities) {
		this.beforeYearTotalLiabilities = beforeYearTotalLiabilities;
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
	 * get方法。币种
	 */
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * set方法。币种
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
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
	 * get方法。
	 */
	public BigDecimal getEquityInterestRate() {
		return this.equityInterestRate;
	}

	/**
	 * set方法。
	 */
	public void setEquityInterestRate(BigDecimal equityInterestRate) {
		this.equityInterestRate = equityInterestRate;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getFixedAssets() {
		return this.fixedAssets;
	}

	/**
	 * set方法。
	 */
	public void setFixedAssets(BigDecimal fixedAssets) {
		this.fixedAssets = fixedAssets;
	}
    
	/**
	 * get方法。是否有效。  编码。对应数据字典表（dictionary）中的编码字段（code）1.有效，0.无效，2.删除        
	 */
	public Byte getFlagCode() {
		return this.flagCode;
	}

	/**
	 * set方法。是否有效。  编码。对应数据字典表（dictionary）中的编码字段（code）1.有效，0.无效，2.删除        
	 */
	public void setFlagCode(Byte flagCode) {
		this.flagCode = flagCode;
	}
    
	/**
	 * get方法。是否有效。 文本。对应数据字典表（dictionary）中的文本字段（name）1.有效，0.无效，2.删除  
	 */
	public String getFlagText() {
		return this.flagText;
	}

	/**
	 * set方法。是否有效。 文本。对应数据字典表（dictionary）中的文本字段（name）1.有效，0.无效，2.删除  
	 */
	public void setFlagText(String flagText) {
		this.flagText = flagText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getGrossProfitMargin() {
		return this.grossProfitMargin;
	}

	/**
	 * set方法。
	 */
	public void setGrossProfitMargin(BigDecimal grossProfitMargin) {
		this.grossProfitMargin = grossProfitMargin;
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
	 * get方法。
	 */
	public BigDecimal getInvisibleAssetsNetAlue() {
		return this.invisibleAssetsNetAlue;
	}

	/**
	 * set方法。
	 */
	public void setInvisibleAssetsNetAlue(BigDecimal invisibleAssetsNetAlue) {
		this.invisibleAssetsNetAlue = invisibleAssetsNetAlue;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getNetProfit() {
		return this.netProfit;
	}

	/**
	 * set方法。
	 */
	public void setNetProfit(BigDecimal netProfit) {
		this.netProfit = netProfit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOperatingCosts() {
		return this.operatingCosts;
	}

	/**
	 * set方法。
	 */
	public void setOperatingCosts(BigDecimal operatingCosts) {
		this.operatingCosts = operatingCosts;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOperatingIncome() {
		return this.operatingIncome;
	}

	/**
	 * set方法。
	 */
	public void setOperatingIncome(BigDecimal operatingIncome) {
		this.operatingIncome = operatingIncome;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOperatingProfit() {
		return this.operatingProfit;
	}

	/**
	 * set方法。
	 */
	public void setOperatingProfit(BigDecimal operatingProfit) {
		this.operatingProfit = operatingProfit;
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
	 * get方法。源创建时间
	 */
	public Date getSourceCreateTime() {
		return this.sourceCreateTime;
	}

	/**
	 * set方法。源创建时间
	 */
	public void setSourceCreateTime(Date sourceCreateTime) {
		this.sourceCreateTime = sourceCreateTime;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTotalAssets() {
		return this.totalAssets;
	}

	/**
	 * set方法。
	 */
	public void setTotalAssets(BigDecimal totalAssets) {
		this.totalAssets = totalAssets;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTotalCurrentAssets() {
		return this.totalCurrentAssets;
	}

	/**
	 * set方法。
	 */
	public void setTotalCurrentAssets(BigDecimal totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTotalCurrentQuick() {
		return this.totalCurrentQuick;
	}

	/**
	 * set方法。
	 */
	public void setTotalCurrentQuick(BigDecimal totalCurrentQuick) {
		this.totalCurrentQuick = totalCurrentQuick;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTotalLiabilities() {
		return this.totalLiabilities;
	}

	/**
	 * set方法。
	 */
	public void setTotalLiabilities(BigDecimal totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTotalOwnersEquity() {
		return this.totalOwnersEquity;
	}

	/**
	 * set方法。
	 */
	public void setTotalOwnersEquity(BigDecimal totalOwnersEquity) {
		this.totalOwnersEquity = totalOwnersEquity;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTotalProfit() {
		return this.totalProfit;
	}

	/**
	 * set方法。
	 */
	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}
    
	/**
	 * get方法。更新时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	/**
	 * get方法。更新人
	 */
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * set方法。更新人
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
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







