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
 * @updateDate 2018/11/23 10:58:08
 * @description 实体类DaCorpDisasterGenEnt，自动生成。
 */

public class DaCorpDisasterGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterAreaUnit;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterAreaUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterAreaUnitText;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterLevelCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterLevelText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterLose;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal disasterLoseUnit;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterLoseUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterLoseUnitText;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte disasterTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String disasterTypeText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String weather;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。
	 */
	public Byte getCropTypeCode() {
		return this.cropTypeCode;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public Byte getDisasterAreaUnitCode() {
		return this.disasterAreaUnitCode;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public Byte getDisasterLevelCode() {
		return this.disasterLevelCode;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public Byte getDisasterLoseUnitCode() {
		return this.disasterLoseUnitCode;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public Byte getDisasterTypeCode() {
		return this.disasterTypeCode;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。
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
    

    //一对多关系中，多端数据列表

}







