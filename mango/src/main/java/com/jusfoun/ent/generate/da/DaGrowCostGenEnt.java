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
 * @description 实体类DaGrowCostGenEnt，自动生成。种植成本
 */

public class DaGrowCostGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal acreCost;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal acreCostUnit;
    @ApiModelProperty(value="亩成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte acreCostUnitCode;
    @ApiModelProperty(value="亩成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩",hidden=false,required=false)
    private String acreCostUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal artificialCost;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal artificialCostUnit;
    @ApiModelProperty(value="人工总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte artificialCostUnitCode;
    @ApiModelProperty(value="人工总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩",hidden=false,required=false)
    private String artificialCostUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cattyCost;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cattyCostUnit;
    @ApiModelProperty(value="市斤成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte cattyCostUnitCode;
    @ApiModelProperty(value="市斤成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩",hidden=false,required=false)
    private String cattyCostUnitText;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal inputCost;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal inputCostUnit;
    @ApiModelProperty(value="投入品总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte inputCostUnitCode;
    @ApiModelProperty(value="投入品总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩",hidden=false,required=false)
    private String inputCostUnitText;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal otherCost;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal otherCostUnit;
    @ApiModelProperty(value="其他总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte otherCostUnitCode;
    @ApiModelProperty(value="其他总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩",hidden=false,required=false)
    private String otherCostUnitText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalCost;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal totalCostUnit;
    @ApiModelProperty(value="总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte totalCostUnitCode;
    @ApiModelProperty(value="总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩",hidden=false,required=false)
    private String totalCostUnitText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。
	 */
	public BigDecimal getAcreCost() {
		return this.acreCost;
	}

	/**
	 * set方法。
	 */
	public void setAcreCost(BigDecimal acreCost) {
		this.acreCost = acreCost;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getAcreCostUnit() {
		return this.acreCostUnit;
	}

	/**
	 * set方法。
	 */
	public void setAcreCostUnit(BigDecimal acreCostUnit) {
		this.acreCostUnit = acreCostUnit;
	}
    
	/**
	 * get方法。亩成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public Byte getAcreCostUnitCode() {
		return this.acreCostUnitCode;
	}

	/**
	 * set方法。亩成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public void setAcreCostUnitCode(Byte acreCostUnitCode) {
		this.acreCostUnitCode = acreCostUnitCode;
	}
    
	/**
	 * get方法。亩成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public String getAcreCostUnitText() {
		return this.acreCostUnitText;
	}

	/**
	 * set方法。亩成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public void setAcreCostUnitText(String acreCostUnitText) {
		this.acreCostUnitText = acreCostUnitText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getArtificialCost() {
		return this.artificialCost;
	}

	/**
	 * set方法。
	 */
	public void setArtificialCost(BigDecimal artificialCost) {
		this.artificialCost = artificialCost;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getArtificialCostUnit() {
		return this.artificialCostUnit;
	}

	/**
	 * set方法。
	 */
	public void setArtificialCostUnit(BigDecimal artificialCostUnit) {
		this.artificialCostUnit = artificialCostUnit;
	}
    
	/**
	 * get方法。人工总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public Byte getArtificialCostUnitCode() {
		return this.artificialCostUnitCode;
	}

	/**
	 * set方法。人工总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public void setArtificialCostUnitCode(Byte artificialCostUnitCode) {
		this.artificialCostUnitCode = artificialCostUnitCode;
	}
    
	/**
	 * get方法。人工总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public String getArtificialCostUnitText() {
		return this.artificialCostUnitText;
	}

	/**
	 * set方法。人工总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public void setArtificialCostUnitText(String artificialCostUnitText) {
		this.artificialCostUnitText = artificialCostUnitText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCattyCost() {
		return this.cattyCost;
	}

	/**
	 * set方法。
	 */
	public void setCattyCost(BigDecimal cattyCost) {
		this.cattyCost = cattyCost;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCattyCostUnit() {
		return this.cattyCostUnit;
	}

	/**
	 * set方法。
	 */
	public void setCattyCostUnit(BigDecimal cattyCostUnit) {
		this.cattyCostUnit = cattyCostUnit;
	}
    
	/**
	 * get方法。市斤成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public Byte getCattyCostUnitCode() {
		return this.cattyCostUnitCode;
	}

	/**
	 * set方法。市斤成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public void setCattyCostUnitCode(Byte cattyCostUnitCode) {
		this.cattyCostUnitCode = cattyCostUnitCode;
	}
    
	/**
	 * get方法。市斤成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public String getCattyCostUnitText() {
		return this.cattyCostUnitText;
	}

	/**
	 * set方法。市斤成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public void setCattyCostUnitText(String cattyCostUnitText) {
		this.cattyCostUnitText = cattyCostUnitText;
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
	public BigDecimal getInputCost() {
		return this.inputCost;
	}

	/**
	 * set方法。
	 */
	public void setInputCost(BigDecimal inputCost) {
		this.inputCost = inputCost;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getInputCostUnit() {
		return this.inputCostUnit;
	}

	/**
	 * set方法。
	 */
	public void setInputCostUnit(BigDecimal inputCostUnit) {
		this.inputCostUnit = inputCostUnit;
	}
    
	/**
	 * get方法。投入品总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public Byte getInputCostUnitCode() {
		return this.inputCostUnitCode;
	}

	/**
	 * set方法。投入品总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public void setInputCostUnitCode(Byte inputCostUnitCode) {
		this.inputCostUnitCode = inputCostUnitCode;
	}
    
	/**
	 * get方法。投入品总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public String getInputCostUnitText() {
		return this.inputCostUnitText;
	}

	/**
	 * set方法。投入品总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public void setInputCostUnitText(String inputCostUnitText) {
		this.inputCostUnitText = inputCostUnitText;
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
	public BigDecimal getOtherCost() {
		return this.otherCost;
	}

	/**
	 * set方法。
	 */
	public void setOtherCost(BigDecimal otherCost) {
		this.otherCost = otherCost;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOtherCostUnit() {
		return this.otherCostUnit;
	}

	/**
	 * set方法。
	 */
	public void setOtherCostUnit(BigDecimal otherCostUnit) {
		this.otherCostUnit = otherCostUnit;
	}
    
	/**
	 * get方法。其他总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public Byte getOtherCostUnitCode() {
		return this.otherCostUnitCode;
	}

	/**
	 * set方法。其他总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public void setOtherCostUnitCode(Byte otherCostUnitCode) {
		this.otherCostUnitCode = otherCostUnitCode;
	}
    
	/**
	 * get方法。其他总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public String getOtherCostUnitText() {
		return this.otherCostUnitText;
	}

	/**
	 * set方法。其他总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public void setOtherCostUnitText(String otherCostUnitText) {
		this.otherCostUnitText = otherCostUnitText;
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
	public BigDecimal getTotalCost() {
		return this.totalCost;
	}

	/**
	 * set方法。
	 */
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTotalCostUnit() {
		return this.totalCostUnit;
	}

	/**
	 * set方法。
	 */
	public void setTotalCostUnit(BigDecimal totalCostUnit) {
		this.totalCostUnit = totalCostUnit;
	}
    
	/**
	 * get方法。总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public Byte getTotalCostUnitCode() {
		return this.totalCostUnitCode;
	}

	/**
	 * set方法。总成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public void setTotalCostUnitCode(Byte totalCostUnitCode) {
		this.totalCostUnitCode = totalCostUnitCode;
	}
    
	/**
	 * get方法。总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public String getTotalCostUnitText() {
		return this.totalCostUnitText;
	}

	/**
	 * set方法。总成本单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/亩
	 */
	public void setTotalCostUnitText(String totalCostUnitText) {
		this.totalCostUnitText = totalCostUnitText;
	}
    

    //一对多关系中，多端数据列表

}







