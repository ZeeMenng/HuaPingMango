package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:50
 * @description 实体类DaCorpDisasterGenEnt，自动生成。作物受灾数据表
 */

public class DaCorpDisasterGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterAreaUnit;
    @ApiModelProperty(value="受灾面积单位:对应数据字典表（dictionary）中的编码字段（code）  1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterAreaUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterAreaUnitText;
    @ApiModelProperty(value="灾情:对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterLevelCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterLevelText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterLose;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterLoseUnit;
    @ApiModelProperty(value="经济损失单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterLoseUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterLoseUnitText;
    @ApiModelProperty(value="受灾类型：对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterTypeText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String weather;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getCropTypeText() {
		return this.cropTypeText;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getDisasterAreaUnitText() {
		return this.disasterAreaUnitText;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getDisasterLevelText() {
		return this.disasterLevelText;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getDisasterLoseUnitText() {
		return this.disasterLoseUnitText;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getDisasterTypeText() {
		return this.disasterTypeText;
	}

	/**
	 * set方法。
	 */
	public void setDisasterTypeText(String disasterTypeText) {
		this.disasterTypeText = disasterTypeText;
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
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    
	/**
	 * get方法。
	 */
	public String getWeather() {
		return this.weather;
	}

	/**
	 * set方法。
	 */
	public void setWeather(String weather) {
		this.weather = weather;
	}
    










}







