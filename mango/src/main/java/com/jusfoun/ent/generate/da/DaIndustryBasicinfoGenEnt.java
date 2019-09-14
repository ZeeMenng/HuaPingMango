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
 * @updateDate 2018-6-13 19:04:32
 * @description 实体类DaIndustryBasicinfoGenEnt，自动生成。产业基本情况表
 */

public class DaIndustryBasicinfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cropFruitArea;
    @ApiModelProperty(value="作物挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte cropFruitAreaCode;
    @ApiModelProperty(value="作物挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String cropFruitAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cropFruitAreaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cropGraftArea;
    @ApiModelProperty(value="作物嫁接面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte cropGraftAreaCode;
    @ApiModelProperty(value="作物嫁接面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String cropGraftAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cropGraftAreaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cropTotalArea;
    @ApiModelProperty(value="作物总面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTotalAreaCode;
    @ApiModelProperty(value="作物总面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String cropTotalAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal cropTotalAreaUnit;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="拟发展品种",hidden=false,required=false)
    private String developVariety;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal growArea;
    @ApiModelProperty(value="海拔1500米以下准备种植品种面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte growAreaCode;
    @ApiModelProperty(value="海拔1500米以下准备种植品种面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String growAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal growAreaUnit;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal landArea;
    @ApiModelProperty(value="海拔1500米以下土地面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte landAreaCode;
    @ApiModelProperty(value="海拔1500米以下土地面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String landAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal landAreaUnit;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal ngraftedTreeArea;
    @ApiModelProperty(value="未嫁接大树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte ngraftedTreeAreaCode;
    @ApiModelProperty(value="未嫁接大树面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String ngraftedTreeAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal ngraftedTreeAreaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal ngraftedYoungtreeArea;
    @ApiModelProperty(value="未嫁接幼树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte ngraftedYoungtreeAreaCode;
    @ApiModelProperty(value="未嫁接幼树面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String ngraftedYoungtreeAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal ngraftedYoungtreeAreaUnit;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物品种  1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物品种文本  1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒",hidden=false,required=false)
    private String strainsText;
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
	 * get方法。
	 */
	public BigDecimal getCropFruitArea() {
		return this.cropFruitArea;
	}

	/**
	 * set方法。
	 */
	public void setCropFruitArea(BigDecimal cropFruitArea) {
		this.cropFruitArea = cropFruitArea;
	}
    
	/**
	 * get方法。作物挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getCropFruitAreaCode() {
		return this.cropFruitAreaCode;
	}

	/**
	 * set方法。作物挂果面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setCropFruitAreaCode(Byte cropFruitAreaCode) {
		this.cropFruitAreaCode = cropFruitAreaCode;
	}
    
	/**
	 * get方法。作物挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getCropFruitAreaText() {
		return this.cropFruitAreaText;
	}

	/**
	 * set方法。作物挂果面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setCropFruitAreaText(String cropFruitAreaText) {
		this.cropFruitAreaText = cropFruitAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCropFruitAreaUnit() {
		return this.cropFruitAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setCropFruitAreaUnit(BigDecimal cropFruitAreaUnit) {
		this.cropFruitAreaUnit = cropFruitAreaUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCropGraftArea() {
		return this.cropGraftArea;
	}

	/**
	 * set方法。
	 */
	public void setCropGraftArea(BigDecimal cropGraftArea) {
		this.cropGraftArea = cropGraftArea;
	}
    
	/**
	 * get方法。作物嫁接面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getCropGraftAreaCode() {
		return this.cropGraftAreaCode;
	}

	/**
	 * set方法。作物嫁接面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setCropGraftAreaCode(Byte cropGraftAreaCode) {
		this.cropGraftAreaCode = cropGraftAreaCode;
	}
    
	/**
	 * get方法。作物嫁接面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getCropGraftAreaText() {
		return this.cropGraftAreaText;
	}

	/**
	 * set方法。作物嫁接面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setCropGraftAreaText(String cropGraftAreaText) {
		this.cropGraftAreaText = cropGraftAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCropGraftAreaUnit() {
		return this.cropGraftAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setCropGraftAreaUnit(BigDecimal cropGraftAreaUnit) {
		this.cropGraftAreaUnit = cropGraftAreaUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCropTotalArea() {
		return this.cropTotalArea;
	}

	/**
	 * set方法。
	 */
	public void setCropTotalArea(BigDecimal cropTotalArea) {
		this.cropTotalArea = cropTotalArea;
	}
    
	/**
	 * get方法。作物总面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getCropTotalAreaCode() {
		return this.cropTotalAreaCode;
	}

	/**
	 * set方法。作物总面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setCropTotalAreaCode(Byte cropTotalAreaCode) {
		this.cropTotalAreaCode = cropTotalAreaCode;
	}
    
	/**
	 * get方法。作物总面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getCropTotalAreaText() {
		return this.cropTotalAreaText;
	}

	/**
	 * set方法。作物总面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setCropTotalAreaText(String cropTotalAreaText) {
		this.cropTotalAreaText = cropTotalAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCropTotalAreaUnit() {
		return this.cropTotalAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setCropTotalAreaUnit(BigDecimal cropTotalAreaUnit) {
		this.cropTotalAreaUnit = cropTotalAreaUnit;
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
	 * get方法。拟发展品种
	 */
	public String getDevelopVariety() {
		return this.developVariety;
	}

	/**
	 * set方法。拟发展品种
	 */
	public void setDevelopVariety(String developVariety) {
		this.developVariety = developVariety;
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
	 * get方法。海拔1500米以下准备种植品种面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getGrowAreaCode() {
		return this.growAreaCode;
	}

	/**
	 * set方法。海拔1500米以下准备种植品种面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setGrowAreaCode(Byte growAreaCode) {
		this.growAreaCode = growAreaCode;
	}
    
	/**
	 * get方法。海拔1500米以下准备种植品种面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getGrowAreaText() {
		return this.growAreaText;
	}

	/**
	 * set方法。海拔1500米以下准备种植品种面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
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
	public BigDecimal getLandArea() {
		return this.landArea;
	}

	/**
	 * set方法。
	 */
	public void setLandArea(BigDecimal landArea) {
		this.landArea = landArea;
	}
    
	/**
	 * get方法。海拔1500米以下土地面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getLandAreaCode() {
		return this.landAreaCode;
	}

	/**
	 * set方法。海拔1500米以下土地面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setLandAreaCode(Byte landAreaCode) {
		this.landAreaCode = landAreaCode;
	}
    
	/**
	 * get方法。海拔1500米以下土地面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getLandAreaText() {
		return this.landAreaText;
	}

	/**
	 * set方法。海拔1500米以下土地面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setLandAreaText(String landAreaText) {
		this.landAreaText = landAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getLandAreaUnit() {
		return this.landAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setLandAreaUnit(BigDecimal landAreaUnit) {
		this.landAreaUnit = landAreaUnit;
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
	public BigDecimal getNgraftedTreeArea() {
		return this.ngraftedTreeArea;
	}

	/**
	 * set方法。
	 */
	public void setNgraftedTreeArea(BigDecimal ngraftedTreeArea) {
		this.ngraftedTreeArea = ngraftedTreeArea;
	}
    
	/**
	 * get方法。未嫁接大树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getNgraftedTreeAreaCode() {
		return this.ngraftedTreeAreaCode;
	}

	/**
	 * set方法。未嫁接大树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setNgraftedTreeAreaCode(Byte ngraftedTreeAreaCode) {
		this.ngraftedTreeAreaCode = ngraftedTreeAreaCode;
	}
    
	/**
	 * get方法。未嫁接大树面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getNgraftedTreeAreaText() {
		return this.ngraftedTreeAreaText;
	}

	/**
	 * set方法。未嫁接大树面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setNgraftedTreeAreaText(String ngraftedTreeAreaText) {
		this.ngraftedTreeAreaText = ngraftedTreeAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getNgraftedTreeAreaUnit() {
		return this.ngraftedTreeAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setNgraftedTreeAreaUnit(BigDecimal ngraftedTreeAreaUnit) {
		this.ngraftedTreeAreaUnit = ngraftedTreeAreaUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getNgraftedYoungtreeArea() {
		return this.ngraftedYoungtreeArea;
	}

	/**
	 * set方法。
	 */
	public void setNgraftedYoungtreeArea(BigDecimal ngraftedYoungtreeArea) {
		this.ngraftedYoungtreeArea = ngraftedYoungtreeArea;
	}
    
	/**
	 * get方法。未嫁接幼树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getNgraftedYoungtreeAreaCode() {
		return this.ngraftedYoungtreeAreaCode;
	}

	/**
	 * set方法。未嫁接幼树面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setNgraftedYoungtreeAreaCode(Byte ngraftedYoungtreeAreaCode) {
		this.ngraftedYoungtreeAreaCode = ngraftedYoungtreeAreaCode;
	}
    
	/**
	 * get方法。未嫁接幼树面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getNgraftedYoungtreeAreaText() {
		return this.ngraftedYoungtreeAreaText;
	}

	/**
	 * set方法。未嫁接幼树面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setNgraftedYoungtreeAreaText(String ngraftedYoungtreeAreaText) {
		this.ngraftedYoungtreeAreaText = ngraftedYoungtreeAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getNgraftedYoungtreeAreaUnit() {
		return this.ngraftedYoungtreeAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setNgraftedYoungtreeAreaUnit(BigDecimal ngraftedYoungtreeAreaUnit) {
		this.ngraftedYoungtreeAreaUnit = ngraftedYoungtreeAreaUnit;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）作物品种  1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）作物品种  1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的文本字段（text）作物品种文本  1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的文本字段（text）作物品种文本  1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    

    //一对多关系中，多端数据列表

}







