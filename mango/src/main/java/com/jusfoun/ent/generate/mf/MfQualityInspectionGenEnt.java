package com.jusfoun.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:02
 * @description 实体类MfQualityInspectionGenEnt，自动生成。质量抽检表
 */

public class MfQualityInspectionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="区域编码",allowableValues="0,1",hidden=false,required=false)
    private String areaCode;
    @ApiModelProperty(value="区域名称",hidden=false,required=false)
    private String areaName;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="抽检次数",hidden=false,required=false)
    private Integer inspection;
    @ApiModelProperty(value="抽检合格率",hidden=false,required=false)
    private String inspectionQualified;
    @ApiModelProperty(value="质量问题反馈次数",hidden=false,required=false)
    private Integer issueTimes;
    @ApiModelProperty(value="纬度",hidden=false,required=false)
    private String lat;
    @ApiModelProperty(value="经度",hidden=false,required=false)
    private String lng;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal organicArea;
    @ApiModelProperty(value="有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte organicAreaCode;
    @ApiModelProperty(value="有机种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String organicAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal organicAreaUnit;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。区域编码
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * set方法。区域编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
    
	/**
	 * get方法。区域名称
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * set方法。区域名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
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
	 * get方法。抽检次数
	 */
	public Integer getInspection() {
		return this.inspection;
	}

	/**
	 * set方法。抽检次数
	 */
	public void setInspection(Integer inspection) {
		this.inspection = inspection;
	}
    
	/**
	 * get方法。抽检合格率
	 */
	public String getInspectionQualified() {
		return this.inspectionQualified;
	}

	/**
	 * set方法。抽检合格率
	 */
	public void setInspectionQualified(String inspectionQualified) {
		this.inspectionQualified = inspectionQualified;
	}
    
	/**
	 * get方法。质量问题反馈次数
	 */
	public Integer getIssueTimes() {
		return this.issueTimes;
	}

	/**
	 * set方法。质量问题反馈次数
	 */
	public void setIssueTimes(Integer issueTimes) {
		this.issueTimes = issueTimes;
	}
    
	/**
	 * get方法。纬度
	 */
	public String getLat() {
		return this.lat;
	}

	/**
	 * set方法。纬度
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}
    
	/**
	 * get方法。经度
	 */
	public String getLng() {
		return this.lng;
	}

	/**
	 * set方法。经度
	 */
	public void setLng(String lng) {
		this.lng = lng;
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
	 * get方法。有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getOrganicAreaCode() {
		return this.organicAreaCode;
	}

	/**
	 * set方法。有机种植面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setOrganicAreaCode(Byte organicAreaCode) {
		this.organicAreaCode = organicAreaCode;
	}
    
	/**
	 * get方法。有机种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getOrganicAreaText() {
		return this.organicAreaText;
	}

	/**
	 * set方法。有机种植面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
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
    

    //一对多关系中，多端数据列表

}







