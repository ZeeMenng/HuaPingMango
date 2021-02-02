package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:00
 * @description 实体类DaIndustryProcessGenEnt，自动生成。加工品产值数据表
 */

public class DaIndustryProcessGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal area;
    @ApiModelProperty(value="面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte areaCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String areaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal areaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte consumeTotalCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String consumeTotalText;
    @ApiModelProperty(value="原材料消耗总量数值",hidden=false,required=false)
    private BigDecimal consumeTotalUnit;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputValue;
    @ApiModelProperty(value="产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元",allowableValues="0,1",hidden=false,required=false)
    private Byte outputValueCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String outputValueText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputValueUnit;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱",allowableValues="0,1",hidden=false,required=false)
    private Byte processStrainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String processStrainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal proportion;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal yield;
    @ApiModelProperty(value="产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte yieldCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String yieldText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal yieldUnit;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public BigDecimal getArea() {
		return this.area;
	}

	/**
	 * set方法。
	 */
	public void setArea(BigDecimal area) {
		this.area = area;
	}
    
	/**
	 * get方法。面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getAreaCode() {
		return this.areaCode;
	}

	/**
	 * set方法。面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setAreaCode(Byte areaCode) {
		this.areaCode = areaCode;
	}
    
	/**
	 * get方法。
	 */
	public String getAreaText() {
		return this.areaText;
	}

	/**
	 * set方法。
	 */
	public void setAreaText(String areaText) {
		this.areaText = areaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getAreaUnit() {
		return this.areaUnit;
	}

	/**
	 * set方法。
	 */
	public void setAreaUnit(BigDecimal areaUnit) {
		this.areaUnit = areaUnit;
	}
    
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
	 * get方法。原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getConsumeTotalCode() {
		return this.consumeTotalCode;
	}

	/**
	 * set方法。原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setConsumeTotalCode(Byte consumeTotalCode) {
		this.consumeTotalCode = consumeTotalCode;
	}
    
	/**
	 * get方法。
	 */
	public String getConsumeTotalText() {
		return this.consumeTotalText;
	}

	/**
	 * set方法。
	 */
	public void setConsumeTotalText(String consumeTotalText) {
		this.consumeTotalText = consumeTotalText;
	}
    
	/**
	 * get方法。原材料消耗总量数值
	 */
	public BigDecimal getConsumeTotalUnit() {
		return this.consumeTotalUnit;
	}

	/**
	 * set方法。原材料消耗总量数值
	 */
	public void setConsumeTotalUnit(BigDecimal consumeTotalUnit) {
		this.consumeTotalUnit = consumeTotalUnit;
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
	 * get方法。产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元
	 */
	public Byte getOutputValueCode() {
		return this.outputValueCode;
	}

	/**
	 * set方法。产值单位，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元
	 */
	public void setOutputValueCode(Byte outputValueCode) {
		this.outputValueCode = outputValueCode;
	}
    
	/**
	 * get方法。
	 */
	public String getOutputValueText() {
		return this.outputValueText;
	}

	/**
	 * set方法。
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
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public Byte getProcessStrainsCode() {
		return this.processStrainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public void setProcessStrainsCode(Byte processStrainsCode) {
		this.processStrainsCode = processStrainsCode;
	}
    
	/**
	 * get方法。
	 */
	public String getProcessStrainsText() {
		return this.processStrainsText;
	}

	/**
	 * set方法。
	 */
	public void setProcessStrainsText(String processStrainsText) {
		this.processStrainsText = processStrainsText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getProportion() {
		return this.proportion;
	}

	/**
	 * set方法。
	 */
	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getYield() {
		return this.yield;
	}

	/**
	 * set方法。
	 */
	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}
    
	/**
	 * get方法。产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getYieldCode() {
		return this.yieldCode;
	}

	/**
	 * set方法。产量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setYieldCode(Byte yieldCode) {
		this.yieldCode = yieldCode;
	}
    
	/**
	 * get方法。
	 */
	public String getYieldText() {
		return this.yieldText;
	}

	/**
	 * set方法。
	 */
	public void setYieldText(String yieldText) {
		this.yieldText = yieldText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getYieldUnit() {
		return this.yieldUnit;
	}

	/**
	 * set方法。
	 */
	public void setYieldUnit(BigDecimal yieldUnit) {
		this.yieldUnit = yieldUnit;
	}
    










}







