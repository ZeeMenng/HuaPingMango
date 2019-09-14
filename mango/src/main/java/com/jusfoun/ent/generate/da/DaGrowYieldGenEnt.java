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
 * @updateDate 2018/11/20 11:45:13
 * @description 实体类DaGrowYieldGenEnt，自动生成。种植数据表
 */

public class DaGrowYieldGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="基地code",allowableValues="0,1",hidden=false,required=false)
    private String baseCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String baseName;
    @ApiModelProperty(value="地块code",allowableValues="0,1",hidden=false,required=false)
    private String blockCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String blockName;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="电商产值",hidden=false,required=false)
    private BigDecimal eCommerceOutputValue;
    @ApiModelProperty(value="电商产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte eCommerceOutputValueCode;
    @ApiModelProperty(value="电商产值单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String eCommerceOutputValueText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal eCommerceOutputValueUnit;
    @ApiModelProperty(value="电商销量",hidden=false,required=false)
    private BigDecimal eCommerceSaleTotal;
    @ApiModelProperty(value="电商销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte eCommerceSaleTotalCode;
    @ApiModelProperty(value="电商销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String eCommerceSaleTotalText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal eCommerceSaleTotalUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal fruitArea;
    @ApiModelProperty(value="挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte fruitAreaCode;
    @ApiModelProperty(value="挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String fruitAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal fruitAreaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal growArea;
    @ApiModelProperty(value="种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte growAreaCode;
    @ApiModelProperty(value="种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String growAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal growAreaUnit;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal improveArea;
    @ApiModelProperty(value="改良面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte improveAreaCode;
    @ApiModelProperty(value="改良面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String improveAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal improveAreaUnit;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="有机种植面积",hidden=false,required=false)
    private BigDecimal organicGrowArea;
    @ApiModelProperty(value="有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte organicGrowAreaCode;
    @ApiModelProperty(value="有机种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String organicGrowAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal organicGrowAreaUnit;
    @ApiModelProperty(value="产值",hidden=false,required=false)
    private BigDecimal outputValue;
    @ApiModelProperty(value="产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte outputValueCode;
    @ApiModelProperty(value="产值单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String outputValueText;
    @ApiModelProperty(value="标准单位：元",hidden=false,required=false)
    private BigDecimal outputValueUnit;
    @ApiModelProperty(value="种植户数",hidden=false,required=false)
    private BigDecimal peasantCount;
    @ApiModelProperty(value="产品产量",hidden=false,required=false)
    private BigDecimal productTotal;
    @ApiModelProperty(value="产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte productTotalCode;
    @ApiModelProperty(value="产品产量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String productTotalText;
    @ApiModelProperty(value="标准单位：千克",hidden=false,required=false)
    private BigDecimal productTotalUnit;
    @ApiModelProperty(value="销量",hidden=false,required=false)
    private BigDecimal saleTotal;
    @ApiModelProperty(value="产品销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte saleTotalCode;
    @ApiModelProperty(value="产品销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String saleTotalText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleTotalUnit;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="年份",hidden=false,required=false)
    private String year;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。基地code
	 */
	public String getBaseCode() {
		return this.baseCode;
	}

	/**
	 * set方法。基地code
	 */
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
    
	/**
	 * get方法。
	 */
	public String getBaseName() {
		return this.baseName;
	}

	/**
	 * set方法。
	 */
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
    
	/**
	 * get方法。地块code
	 */
	public String getBlockCode() {
		return this.blockCode;
	}

	/**
	 * set方法。地块code
	 */
	public void setBlockCode(String blockCode) {
		this.blockCode = blockCode;
	}
    
	/**
	 * get方法。
	 */
	public String getBlockName() {
		return this.blockName;
	}

	/**
	 * set方法。
	 */
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
    
	/**
	 * get方法。对应通用字段表id
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。对应通用字段表id
	 */
	public void setCommonFieldId(String commonFieldId) {
		this.commonFieldId = commonFieldId;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果
	 */
	public Byte getCropTypeCode() {
		return this.cropTypeCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果
	 */
	public void setCropTypeCode(Byte cropTypeCode) {
		this.cropTypeCode = cropTypeCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果
	 */
	public String getCropTypeText() {
		return this.cropTypeText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果
	 */
	public void setCropTypeText(String cropTypeText) {
		this.cropTypeText = cropTypeText;
	}
    
	/**
	 * get方法。电商产值
	 */
	public BigDecimal getECommerceOutputValue() {
		return this.eCommerceOutputValue;
	}

	/**
	 * set方法。电商产值
	 */
	public void setECommerceOutputValue(BigDecimal eCommerceOutputValue) {
		this.eCommerceOutputValue = eCommerceOutputValue;
	}
    
	/**
	 * get方法。电商产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getECommerceOutputValueCode() {
		return this.eCommerceOutputValueCode;
	}

	/**
	 * set方法。电商产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setECommerceOutputValueCode(Byte eCommerceOutputValueCode) {
		this.eCommerceOutputValueCode = eCommerceOutputValueCode;
	}
    
	/**
	 * get方法。电商产值单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getECommerceOutputValueText() {
		return this.eCommerceOutputValueText;
	}

	/**
	 * set方法。电商产值单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setECommerceOutputValueText(String eCommerceOutputValueText) {
		this.eCommerceOutputValueText = eCommerceOutputValueText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getECommerceOutputValueUnit() {
		return this.eCommerceOutputValueUnit;
	}

	/**
	 * set方法。
	 */
	public void setECommerceOutputValueUnit(BigDecimal eCommerceOutputValueUnit) {
		this.eCommerceOutputValueUnit = eCommerceOutputValueUnit;
	}
    
	/**
	 * get方法。电商销量
	 */
	public BigDecimal getECommerceSaleTotal() {
		return this.eCommerceSaleTotal;
	}

	/**
	 * set方法。电商销量
	 */
	public void setECommerceSaleTotal(BigDecimal eCommerceSaleTotal) {
		this.eCommerceSaleTotal = eCommerceSaleTotal;
	}
    
	/**
	 * get方法。电商销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getECommerceSaleTotalCode() {
		return this.eCommerceSaleTotalCode;
	}

	/**
	 * set方法。电商销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setECommerceSaleTotalCode(Byte eCommerceSaleTotalCode) {
		this.eCommerceSaleTotalCode = eCommerceSaleTotalCode;
	}
    
	/**
	 * get方法。电商销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getECommerceSaleTotalText() {
		return this.eCommerceSaleTotalText;
	}

	/**
	 * set方法。电商销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setECommerceSaleTotalText(String eCommerceSaleTotalText) {
		this.eCommerceSaleTotalText = eCommerceSaleTotalText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getECommerceSaleTotalUnit() {
		return this.eCommerceSaleTotalUnit;
	}

	/**
	 * set方法。
	 */
	public void setECommerceSaleTotalUnit(BigDecimal eCommerceSaleTotalUnit) {
		this.eCommerceSaleTotalUnit = eCommerceSaleTotalUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getFruitArea() {
		return this.fruitArea;
	}

	/**
	 * set方法。
	 */
	public void setFruitArea(BigDecimal fruitArea) {
		this.fruitArea = fruitArea;
	}
    
	/**
	 * get方法。挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getFruitAreaCode() {
		return this.fruitAreaCode;
	}

	/**
	 * set方法。挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setFruitAreaCode(Byte fruitAreaCode) {
		this.fruitAreaCode = fruitAreaCode;
	}
    
	/**
	 * get方法。挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getFruitAreaText() {
		return this.fruitAreaText;
	}

	/**
	 * set方法。挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setFruitAreaText(String fruitAreaText) {
		this.fruitAreaText = fruitAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getFruitAreaUnit() {
		return this.fruitAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setFruitAreaUnit(BigDecimal fruitAreaUnit) {
		this.fruitAreaUnit = fruitAreaUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getGrowArea() {
		return this.growArea;
	}

	/**
	 * set方法。
	 */
	public void setGrowArea(BigDecimal growArea) {
		this.growArea = growArea;
	}
    
	/**
	 * get方法。种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getGrowAreaCode() {
		return this.growAreaCode;
	}

	/**
	 * set方法。种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setGrowAreaCode(Byte growAreaCode) {
		this.growAreaCode = growAreaCode;
	}
    
	/**
	 * get方法。种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getGrowAreaText() {
		return this.growAreaText;
	}

	/**
	 * set方法。种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setGrowAreaText(String growAreaText) {
		this.growAreaText = growAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getGrowAreaUnit() {
		return this.growAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setGrowAreaUnit(BigDecimal growAreaUnit) {
		this.growAreaUnit = growAreaUnit;
	}
    
	/**
	 * get方法。主键id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键id
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getImproveArea() {
		return this.improveArea;
	}

	/**
	 * set方法。
	 */
	public void setImproveArea(BigDecimal improveArea) {
		this.improveArea = improveArea;
	}
    
	/**
	 * get方法。改良面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getImproveAreaCode() {
		return this.improveAreaCode;
	}

	/**
	 * set方法。改良面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setImproveAreaCode(Byte improveAreaCode) {
		this.improveAreaCode = improveAreaCode;
	}
    
	/**
	 * get方法。改良面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getImproveAreaText() {
		return this.improveAreaText;
	}

	/**
	 * set方法。改良面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setImproveAreaText(String improveAreaText) {
		this.improveAreaText = improveAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getImproveAreaUnit() {
		return this.improveAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setImproveAreaUnit(BigDecimal improveAreaUnit) {
		this.improveAreaUnit = improveAreaUnit;
	}
    
	/**
	 * get方法。名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。有机种植面积
	 */
	public BigDecimal getOrganicGrowArea() {
		return this.organicGrowArea;
	}

	/**
	 * set方法。有机种植面积
	 */
	public void setOrganicGrowArea(BigDecimal organicGrowArea) {
		this.organicGrowArea = organicGrowArea;
	}
    
	/**
	 * get方法。有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getOrganicGrowAreaCode() {
		return this.organicGrowAreaCode;
	}

	/**
	 * set方法。有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setOrganicGrowAreaCode(Byte organicGrowAreaCode) {
		this.organicGrowAreaCode = organicGrowAreaCode;
	}
    
	/**
	 * get方法。有机种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getOrganicGrowAreaText() {
		return this.organicGrowAreaText;
	}

	/**
	 * set方法。有机种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setOrganicGrowAreaText(String organicGrowAreaText) {
		this.organicGrowAreaText = organicGrowAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOrganicGrowAreaUnit() {
		return this.organicGrowAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setOrganicGrowAreaUnit(BigDecimal organicGrowAreaUnit) {
		this.organicGrowAreaUnit = organicGrowAreaUnit;
	}
    
	/**
	 * get方法。产值
	 */
	public BigDecimal getOutputValue() {
		return this.outputValue;
	}

	/**
	 * set方法。产值
	 */
	public void setOutputValue(BigDecimal outputValue) {
		this.outputValue = outputValue;
	}
    
	/**
	 * get方法。产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getOutputValueCode() {
		return this.outputValueCode;
	}

	/**
	 * set方法。产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOutputValueCode(Byte outputValueCode) {
		this.outputValueCode = outputValueCode;
	}
    
	/**
	 * get方法。产值单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getOutputValueText() {
		return this.outputValueText;
	}

	/**
	 * set方法。产值单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOutputValueText(String outputValueText) {
		this.outputValueText = outputValueText;
	}
    
	/**
	 * get方法。标准单位：元
	 */
	public BigDecimal getOutputValueUnit() {
		return this.outputValueUnit;
	}

	/**
	 * set方法。标准单位：元
	 */
	public void setOutputValueUnit(BigDecimal outputValueUnit) {
		this.outputValueUnit = outputValueUnit;
	}
    
	/**
	 * get方法。种植户数
	 */
	public BigDecimal getPeasantCount() {
		return this.peasantCount;
	}

	/**
	 * set方法。种植户数
	 */
	public void setPeasantCount(BigDecimal peasantCount) {
		this.peasantCount = peasantCount;
	}
    
	/**
	 * get方法。产品产量
	 */
	public BigDecimal getProductTotal() {
		return this.productTotal;
	}

	/**
	 * set方法。产品产量
	 */
	public void setProductTotal(BigDecimal productTotal) {
		this.productTotal = productTotal;
	}
    
	/**
	 * get方法。产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getProductTotalCode() {
		return this.productTotalCode;
	}

	/**
	 * set方法。产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setProductTotalCode(Byte productTotalCode) {
		this.productTotalCode = productTotalCode;
	}
    
	/**
	 * get方法。产品产量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getProductTotalText() {
		return this.productTotalText;
	}

	/**
	 * set方法。产品产量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setProductTotalText(String productTotalText) {
		this.productTotalText = productTotalText;
	}
    
	/**
	 * get方法。标准单位：千克
	 */
	public BigDecimal getProductTotalUnit() {
		return this.productTotalUnit;
	}

	/**
	 * set方法。标准单位：千克
	 */
	public void setProductTotalUnit(BigDecimal productTotalUnit) {
		this.productTotalUnit = productTotalUnit;
	}
    
	/**
	 * get方法。销量
	 */
	public BigDecimal getSaleTotal() {
		return this.saleTotal;
	}

	/**
	 * set方法。销量
	 */
	public void setSaleTotal(BigDecimal saleTotal) {
		this.saleTotal = saleTotal;
	}
    
	/**
	 * get方法。产品销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getSaleTotalCode() {
		return this.saleTotalCode;
	}

	/**
	 * set方法。产品销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setSaleTotalCode(Byte saleTotalCode) {
		this.saleTotalCode = saleTotalCode;
	}
    
	/**
	 * get方法。产品销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getSaleTotalText() {
		return this.saleTotalText;
	}

	/**
	 * set方法。产品销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setSaleTotalText(String saleTotalText) {
		this.saleTotalText = saleTotalText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getSaleTotalUnit() {
		return this.saleTotalUnit;
	}

	/**
	 * set方法。
	 */
	public void setSaleTotalUnit(BigDecimal saleTotalUnit) {
		this.saleTotalUnit = saleTotalUnit;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的作物品种
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的作物品种
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    
	/**
	 * get方法。年份
	 */
	public String getYear() {
		return this.year;
	}

	/**
	 * set方法。年份
	 */
	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal geteCommerceOutputValue() {
		return eCommerceOutputValue;
	}

	public void seteCommerceOutputValue(BigDecimal eCommerceOutputValue) {
		this.eCommerceOutputValue = eCommerceOutputValue;
	}

	public Byte geteCommerceOutputValueCode() {
		return eCommerceOutputValueCode;
	}

	public void seteCommerceOutputValueCode(Byte eCommerceOutputValueCode) {
		this.eCommerceOutputValueCode = eCommerceOutputValueCode;
	}

	public String geteCommerceOutputValueText() {
		return eCommerceOutputValueText;
	}

	public void seteCommerceOutputValueText(String eCommerceOutputValueText) {
		this.eCommerceOutputValueText = eCommerceOutputValueText;
	}

	public BigDecimal geteCommerceOutputValueUnit() {
		return eCommerceOutputValueUnit;
	}

	public void seteCommerceOutputValueUnit(BigDecimal eCommerceOutputValueUnit) {
		this.eCommerceOutputValueUnit = eCommerceOutputValueUnit;
	}

	public BigDecimal geteCommerceSaleTotal() {
		return eCommerceSaleTotal;
	}

	public void seteCommerceSaleTotal(BigDecimal eCommerceSaleTotal) {
		this.eCommerceSaleTotal = eCommerceSaleTotal;
	}

	public Byte geteCommerceSaleTotalCode() {
		return eCommerceSaleTotalCode;
	}

	public void seteCommerceSaleTotalCode(Byte eCommerceSaleTotalCode) {
		this.eCommerceSaleTotalCode = eCommerceSaleTotalCode;
	}

	public String geteCommerceSaleTotalText() {
		return eCommerceSaleTotalText;
	}

	public void seteCommerceSaleTotalText(String eCommerceSaleTotalText) {
		this.eCommerceSaleTotalText = eCommerceSaleTotalText;
	}

	public BigDecimal geteCommerceSaleTotalUnit() {
		return eCommerceSaleTotalUnit;
	}

	public void seteCommerceSaleTotalUnit(BigDecimal eCommerceSaleTotalUnit) {
		this.eCommerceSaleTotalUnit = eCommerceSaleTotalUnit;
	}
    
	

    //一对多关系中，多端数据列表

}







