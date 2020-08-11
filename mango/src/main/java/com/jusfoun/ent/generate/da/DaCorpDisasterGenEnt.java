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
 * @updateDate 2020/8/11 11:43:11
 * @description 实体类DaCorpDisasterGenEnt，自动生成。作物受灾数据表
 */

public class DaCorpDisasterGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterAreaUnit;
    @ApiModelProperty(value="受灾面积单位:对应数据字典表（dictionary）中的编码字段（code）  1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterAreaUnitCode;
    @ApiModelProperty(value="受灾面积单位文本:文本，对应数据字典表（dictionary）中的文本字段（text） 1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String disasterAreaUnitText;
    @ApiModelProperty(value="灾情:对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterLevelCode;
    @ApiModelProperty(value="灾情文本:文本，对应数据字典表（dictionary）中的文本字段（text）",hidden=false,required=false)
    private String disasterLevelText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterLose;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterLoseUnit;
    @ApiModelProperty(value="经济损失单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterLoseUnitCode;
    @ApiModelProperty(value="经济损失单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String disasterLoseUnitText;
    @ApiModelProperty(value="受灾类型：对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterTypeCode;
    @ApiModelProperty(value="受灾类型文本:文本，对应数据字典表（dictionary）中的文本字段（text）",hidden=false,required=false)
    private String disasterTypeText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="气象",hidden=false,required=false)
    private String weather;
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
	public BigDecimal getDisasterArea() {
		return this.disasterArea;
	}

	/**
	 * set方法。
	 */
	public void setDisasterArea(BigDecimal disasterArea) {
		this.disasterArea = disasterArea;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getDisasterAreaUnit() {
		return this.disasterAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setDisasterAreaUnit(BigDecimal disasterAreaUnit) {
		this.disasterAreaUnit = disasterAreaUnit;
	}
    
	/**
	 * get方法。受灾面积单位:对应数据字典表（dictionary）中的编码字段（code）  1：亩，2：万亩，3：公顷
	 */
	public Byte getDisasterAreaUnitCode() {
		return this.disasterAreaUnitCode;
	}

	/**
	 * set方法。受灾面积单位:对应数据字典表（dictionary）中的编码字段（code）  1：亩，2：万亩，3：公顷
	 */
	public void setDisasterAreaUnitCode(Byte disasterAreaUnitCode) {
		this.disasterAreaUnitCode = disasterAreaUnitCode;
	}
    
	/**
	 * get方法。受灾面积单位文本:文本，对应数据字典表（dictionary）中的文本字段（text） 1：亩，2：万亩，3：公顷
	 */
	public String getDisasterAreaUnitText() {
		return this.disasterAreaUnitText;
	}

	/**
	 * set方法。受灾面积单位文本:文本，对应数据字典表（dictionary）中的文本字段（text） 1：亩，2：万亩，3：公顷
	 */
	public void setDisasterAreaUnitText(String disasterAreaUnitText) {
		this.disasterAreaUnitText = disasterAreaUnitText;
	}
    
	/**
	 * get方法。灾情:对应数据字典表（dictionary）中的编码字段（code）
	 */
	public Byte getDisasterLevelCode() {
		return this.disasterLevelCode;
	}

	/**
	 * set方法。灾情:对应数据字典表（dictionary）中的编码字段（code）
	 */
	public void setDisasterLevelCode(Byte disasterLevelCode) {
		this.disasterLevelCode = disasterLevelCode;
	}
    
	/**
	 * get方法。灾情文本:文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public String getDisasterLevelText() {
		return this.disasterLevelText;
	}

	/**
	 * set方法。灾情文本:文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public void setDisasterLevelText(String disasterLevelText) {
		this.disasterLevelText = disasterLevelText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getDisasterLose() {
		return this.disasterLose;
	}

	/**
	 * set方法。
	 */
	public void setDisasterLose(BigDecimal disasterLose) {
		this.disasterLose = disasterLose;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getDisasterLoseUnit() {
		return this.disasterLoseUnit;
	}

	/**
	 * set方法。
	 */
	public void setDisasterLoseUnit(BigDecimal disasterLoseUnit) {
		this.disasterLoseUnit = disasterLoseUnit;
	}
    
	/**
	 * get方法。经济损失单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getDisasterLoseUnitCode() {
		return this.disasterLoseUnitCode;
	}

	/**
	 * set方法。经济损失单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setDisasterLoseUnitCode(Byte disasterLoseUnitCode) {
		this.disasterLoseUnitCode = disasterLoseUnitCode;
	}
    
	/**
	 * get方法。经济损失单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getDisasterLoseUnitText() {
		return this.disasterLoseUnitText;
	}

	/**
	 * set方法。经济损失单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setDisasterLoseUnitText(String disasterLoseUnitText) {
		this.disasterLoseUnitText = disasterLoseUnitText;
	}
    
	/**
	 * get方法。受灾类型：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public Byte getDisasterTypeCode() {
		return this.disasterTypeCode;
	}

	/**
	 * set方法。受灾类型：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public void setDisasterTypeCode(Byte disasterTypeCode) {
		this.disasterTypeCode = disasterTypeCode;
	}
    
	/**
	 * get方法。受灾类型文本:文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public String getDisasterTypeText() {
		return this.disasterTypeText;
	}

	/**
	 * set方法。受灾类型文本:文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public void setDisasterTypeText(String disasterTypeText) {
		this.disasterTypeText = disasterTypeText;
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
	 * get方法。气象
	 */
	public String getWeather() {
		return this.weather;
	}

	/**
	 * set方法。气象
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
    

    //一对多关系中，多端数据列表

}







