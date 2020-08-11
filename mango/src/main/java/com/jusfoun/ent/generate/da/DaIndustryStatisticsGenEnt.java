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
 * @updateDate 2020/8/11 11:43:23
 * @description 实体类DaIndustryStatisticsGenEnt，自动生成。产业统计数据表
 */

public class DaIndustryStatisticsGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal fruithangingArea;
    @ApiModelProperty(value="挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte fruithangingAreaCode;
    @ApiModelProperty(value="挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String fruithangingAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal fruithangingAreaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal growArea;
    @ApiModelProperty(value="种植单位面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte growAreaCode;
    @ApiModelProperty(value="种植单位面积，文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String growAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal growAreaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal growHousehold;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal onlineSaleamount;
    @ApiModelProperty(value="电商销售量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte onlineSaleamountCode;
    @ApiModelProperty(value="电商销售量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String onlineSaleamountText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal onlineSaleamountlUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal onlineSales;
    @ApiModelProperty(value="电商销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte onlineSalesCode;
    @ApiModelProperty(value="电商销售额文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String onlineSalesText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal onlineSalesUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal organicArea;
    @ApiModelProperty(value="有机认证面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte organicAreaCode;
    @ApiModelProperty(value="种植面积，文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String organicAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal organicAreaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal output;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputPer;
    @ApiModelProperty(value="产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte outputPerTotalCode;
    @ApiModelProperty(value="产品产量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克/亩，2：克/亩",hidden=false,required=false)
    private String outputPerTotalText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputPerTotalUnit;
    @ApiModelProperty(value="产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte outputTotalCode;
    @ApiModelProperty(value="产品产量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String outputTotalText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputTotalUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputValue;
    @ApiModelProperty(value="产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte outputValueCode;
    @ApiModelProperty(value="产值文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String outputValueText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputValueUnit;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal wholesaleSales;
    @ApiModelProperty(value="批发销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte wholesaleSalesCode;
    @ApiModelProperty(value="批发销售额文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String wholesaleSalesText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal wholesaleSalesUnit;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。
	 */
	public BigDecimal getFruithangingArea() {
		return this.fruithangingArea;
	}

	/**
	 * set方法。
	 */
	public void setFruithangingArea(BigDecimal fruithangingArea) {
		this.fruithangingArea = fruithangingArea;
	}
    
	/**
	 * get方法。挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getFruithangingAreaCode() {
		return this.fruithangingAreaCode;
	}

	/**
	 * set方法。挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setFruithangingAreaCode(Byte fruithangingAreaCode) {
		this.fruithangingAreaCode = fruithangingAreaCode;
	}
    
	/**
	 * get方法。挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getFruithangingAreaText() {
		return this.fruithangingAreaText;
	}

	/**
	 * set方法。挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setFruithangingAreaText(String fruithangingAreaText) {
		this.fruithangingAreaText = fruithangingAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getFruithangingAreaUnit() {
		return this.fruithangingAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setFruithangingAreaUnit(BigDecimal fruithangingAreaUnit) {
		this.fruithangingAreaUnit = fruithangingAreaUnit;
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
	 * get方法。种植单位面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getGrowAreaCode() {
		return this.growAreaCode;
	}

	/**
	 * set方法。种植单位面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setGrowAreaCode(Byte growAreaCode) {
		this.growAreaCode = growAreaCode;
	}
    
	/**
	 * get方法。种植单位面积，文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getGrowAreaText() {
		return this.growAreaText;
	}

	/**
	 * set方法。种植单位面积，文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
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
	 * get方法。
	 */
	public BigDecimal getGrowHousehold() {
		return this.growHousehold;
	}

	/**
	 * set方法。
	 */
	public void setGrowHousehold(BigDecimal growHousehold) {
		this.growHousehold = growHousehold;
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
	 * get方法。
	 */
	public BigDecimal getOnlineSaleamount() {
		return this.onlineSaleamount;
	}

	/**
	 * set方法。
	 */
	public void setOnlineSaleamount(BigDecimal onlineSaleamount) {
		this.onlineSaleamount = onlineSaleamount;
	}
    
	/**
	 * get方法。电商销售量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getOnlineSaleamountCode() {
		return this.onlineSaleamountCode;
	}

	/**
	 * set方法。电商销售量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setOnlineSaleamountCode(Byte onlineSaleamountCode) {
		this.onlineSaleamountCode = onlineSaleamountCode;
	}
    
	/**
	 * get方法。电商销售量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getOnlineSaleamountText() {
		return this.onlineSaleamountText;
	}

	/**
	 * set方法。电商销售量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setOnlineSaleamountText(String onlineSaleamountText) {
		this.onlineSaleamountText = onlineSaleamountText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOnlineSaleamountlUnit() {
		return this.onlineSaleamountlUnit;
	}

	/**
	 * set方法。
	 */
	public void setOnlineSaleamountlUnit(BigDecimal onlineSaleamountlUnit) {
		this.onlineSaleamountlUnit = onlineSaleamountlUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOnlineSales() {
		return this.onlineSales;
	}

	/**
	 * set方法。
	 */
	public void setOnlineSales(BigDecimal onlineSales) {
		this.onlineSales = onlineSales;
	}
    
	/**
	 * get方法。电商销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getOnlineSalesCode() {
		return this.onlineSalesCode;
	}

	/**
	 * set方法。电商销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOnlineSalesCode(Byte onlineSalesCode) {
		this.onlineSalesCode = onlineSalesCode;
	}
    
	/**
	 * get方法。电商销售额文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getOnlineSalesText() {
		return this.onlineSalesText;
	}

	/**
	 * set方法。电商销售额文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOnlineSalesText(String onlineSalesText) {
		this.onlineSalesText = onlineSalesText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOnlineSalesUnit() {
		return this.onlineSalesUnit;
	}

	/**
	 * set方法。
	 */
	public void setOnlineSalesUnit(BigDecimal onlineSalesUnit) {
		this.onlineSalesUnit = onlineSalesUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOrganicArea() {
		return this.organicArea;
	}

	/**
	 * set方法。
	 */
	public void setOrganicArea(BigDecimal organicArea) {
		this.organicArea = organicArea;
	}
    
	/**
	 * get方法。有机认证面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getOrganicAreaCode() {
		return this.organicAreaCode;
	}

	/**
	 * set方法。有机认证面积,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setOrganicAreaCode(Byte organicAreaCode) {
		this.organicAreaCode = organicAreaCode;
	}
    
	/**
	 * get方法。种植面积，文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getOrganicAreaText() {
		return this.organicAreaText;
	}

	/**
	 * set方法。种植面积，文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setOrganicAreaText(String organicAreaText) {
		this.organicAreaText = organicAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOrganicAreaUnit() {
		return this.organicAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setOrganicAreaUnit(BigDecimal organicAreaUnit) {
		this.organicAreaUnit = organicAreaUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOutput() {
		return this.output;
	}

	/**
	 * set方法。
	 */
	public void setOutput(BigDecimal output) {
		this.output = output;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOutputPer() {
		return this.outputPer;
	}

	/**
	 * set方法。
	 */
	public void setOutputPer(BigDecimal outputPer) {
		this.outputPer = outputPer;
	}
    
	/**
	 * get方法。产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩
	 */
	public Byte getOutputPerTotalCode() {
		return this.outputPerTotalCode;
	}

	/**
	 * set方法。产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩
	 */
	public void setOutputPerTotalCode(Byte outputPerTotalCode) {
		this.outputPerTotalCode = outputPerTotalCode;
	}
    
	/**
	 * get方法。产品产量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克/亩，2：克/亩
	 */
	public String getOutputPerTotalText() {
		return this.outputPerTotalText;
	}

	/**
	 * set方法。产品产量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克/亩，2：克/亩
	 */
	public void setOutputPerTotalText(String outputPerTotalText) {
		this.outputPerTotalText = outputPerTotalText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOutputPerTotalUnit() {
		return this.outputPerTotalUnit;
	}

	/**
	 * set方法。
	 */
	public void setOutputPerTotalUnit(BigDecimal outputPerTotalUnit) {
		this.outputPerTotalUnit = outputPerTotalUnit;
	}
    
	/**
	 * get方法。产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getOutputTotalCode() {
		return this.outputTotalCode;
	}

	/**
	 * set方法。产品产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setOutputTotalCode(Byte outputTotalCode) {
		this.outputTotalCode = outputTotalCode;
	}
    
	/**
	 * get方法。产品产量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getOutputTotalText() {
		return this.outputTotalText;
	}

	/**
	 * set方法。产品产量单位文本，:对应数据字典表（dictionary）中的编码字段（text）1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setOutputTotalText(String outputTotalText) {
		this.outputTotalText = outputTotalText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOutputTotalUnit() {
		return this.outputTotalUnit;
	}

	/**
	 * set方法。
	 */
	public void setOutputTotalUnit(BigDecimal outputTotalUnit) {
		this.outputTotalUnit = outputTotalUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOutputValue() {
		return this.outputValue;
	}

	/**
	 * set方法。
	 */
	public void setOutputValue(BigDecimal outputValue) {
		this.outputValue = outputValue;
	}
    
	/**
	 * get方法。产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getOutputValueCode() {
		return this.outputValueCode;
	}

	/**
	 * set方法。产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOutputValueCode(Byte outputValueCode) {
		this.outputValueCode = outputValueCode;
	}
    
	/**
	 * get方法。产值文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getOutputValueText() {
		return this.outputValueText;
	}

	/**
	 * set方法。产值文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOutputValueText(String outputValueText) {
		this.outputValueText = outputValueText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOutputValueUnit() {
		return this.outputValueUnit;
	}

	/**
	 * set方法。
	 */
	public void setOutputValueUnit(BigDecimal outputValueUnit) {
		this.outputValueUnit = outputValueUnit;
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
	 * get方法。
	 */
	public BigDecimal getWholesaleSales() {
		return this.wholesaleSales;
	}

	/**
	 * set方法。
	 */
	public void setWholesaleSales(BigDecimal wholesaleSales) {
		this.wholesaleSales = wholesaleSales;
	}
    
	/**
	 * get方法。批发销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getWholesaleSalesCode() {
		return this.wholesaleSalesCode;
	}

	/**
	 * set方法。批发销售额，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setWholesaleSalesCode(Byte wholesaleSalesCode) {
		this.wholesaleSalesCode = wholesaleSalesCode;
	}
    
	/**
	 * get方法。批发销售额文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getWholesaleSalesText() {
		return this.wholesaleSalesText;
	}

	/**
	 * set方法。批发销售额文本，对应数据字典表（dictionary）中的编码字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setWholesaleSalesText(String wholesaleSalesText) {
		this.wholesaleSalesText = wholesaleSalesText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getWholesaleSalesUnit() {
		return this.wholesaleSalesUnit;
	}

	/**
	 * set方法。
	 */
	public void setWholesaleSalesUnit(BigDecimal wholesaleSalesUnit) {
		this.wholesaleSalesUnit = wholesaleSalesUnit;
	}
    

    //一对多关系中，多端数据列表

}







