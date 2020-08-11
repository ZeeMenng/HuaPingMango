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
 * @updateDate 2020/8/11 11:43:20
 * @description 实体类DaIdentificationInfoGenEnt，自动生成。三品一标认证表
 */

public class DaIdentificationInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="产地地址",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="认证基地名称",hidden=false,required=false)
    private String baseName;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="认证面积",hidden=false,required=false)
    private BigDecimal identificationArea;
    @ApiModelProperty(value="认证面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷",allowableValues="0,1",hidden=false,required=false)
    private Byte identificationAreaCode;
    @ApiModelProperty(value="认证面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷",hidden=false,required=false)
    private String identificationAreaText;
    @ApiModelProperty(value="面积通用单位：平方米",hidden=false,required=false)
    private BigDecimal identificationAreaUnit;
    @ApiModelProperty(value="认证类型：对应数据字典表（dictionary）中的编码字段（code） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证",allowableValues="0,1",hidden=false,required=false)
    private Byte identificationTypeCode;
    @ApiModelProperty(value="认证类型文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证",hidden=false,required=false)
    private String identificationTypeText;
    @ApiModelProperty(value="地区纬度。",hidden=false,required=false)
    private String latitude;
    @ApiModelProperty(value="地区经度。",hidden=false,required=false)
    private String longitude;
    @ApiModelProperty(value="获证单位名称，农户或企业。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="加工时间",hidden=false,required=false)
    private Date processTime;
    @ApiModelProperty(value="产品名称",hidden=false,required=false)
    private String produceName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal produceSummation;
    @ApiModelProperty(value="重量通用单位：千克",hidden=false,required=false)
    private BigDecimal produceSummationUnit;
    @ApiModelProperty(value="产品产量单位，1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte produceSummationUnitCode;
    @ApiModelProperty(value="产品产量单位文本，1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String produceSummationUnitText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",hidden=false,required=false)
    private String strainsText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。产地地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set方法。产地地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
    
	/**
	 * get方法。认证基地名称
	 */
	public String getBaseName() {
		return this.baseName;
	}

	/**
	 * set方法。认证基地名称
	 */
	public void setBaseName(String baseName) {
		this.baseName = baseName;
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
	 * get方法。认证面积
	 */
	public BigDecimal getIdentificationArea() {
		return this.identificationArea;
	}

	/**
	 * set方法。认证面积
	 */
	public void setIdentificationArea(BigDecimal identificationArea) {
		this.identificationArea = identificationArea;
	}
    
	/**
	 * get方法。认证面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public Byte getIdentificationAreaCode() {
		return this.identificationAreaCode;
	}

	/**
	 * set方法。认证面积单位,对应数据字典表（dictionary）中的编码字段（code）1：亩，2：万亩，3：公顷
	 */
	public void setIdentificationAreaCode(Byte identificationAreaCode) {
		this.identificationAreaCode = identificationAreaCode;
	}
    
	/**
	 * get方法。认证面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public String getIdentificationAreaText() {
		return this.identificationAreaText;
	}

	/**
	 * set方法。认证面积单位文本，对应数据字典表（dictionary）中的文本字段（text）1：亩，2：万亩，3：公顷
	 */
	public void setIdentificationAreaText(String identificationAreaText) {
		this.identificationAreaText = identificationAreaText;
	}
    
	/**
	 * get方法。面积通用单位：平方米
	 */
	public BigDecimal getIdentificationAreaUnit() {
		return this.identificationAreaUnit;
	}

	/**
	 * set方法。面积通用单位：平方米
	 */
	public void setIdentificationAreaUnit(BigDecimal identificationAreaUnit) {
		this.identificationAreaUnit = identificationAreaUnit;
	}
    
	/**
	 * get方法。认证类型：对应数据字典表（dictionary）中的编码字段（code） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public Byte getIdentificationTypeCode() {
		return this.identificationTypeCode;
	}

	/**
	 * set方法。认证类型：对应数据字典表（dictionary）中的编码字段（code） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public void setIdentificationTypeCode(Byte identificationTypeCode) {
		this.identificationTypeCode = identificationTypeCode;
	}
    
	/**
	 * get方法。认证类型文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public String getIdentificationTypeText() {
		return this.identificationTypeText;
	}

	/**
	 * set方法。认证类型文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public void setIdentificationTypeText(String identificationTypeText) {
		this.identificationTypeText = identificationTypeText;
	}
    
	/**
	 * get方法。地区纬度。
	 */
	public String getLatitude() {
		return this.latitude;
	}

	/**
	 * set方法。地区纬度。
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    
	/**
	 * get方法。地区经度。
	 */
	public String getLongitude() {
		return this.longitude;
	}

	/**
	 * set方法。地区经度。
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    
	/**
	 * get方法。获证单位名称，农户或企业。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。获证单位名称，农户或企业。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。加工时间
	 */
	public Date getProcessTime() {
		return this.processTime;
	}

	/**
	 * set方法。加工时间
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
	}
    
	/**
	 * get方法。产品名称
	 */
	public String getProduceName() {
		return this.produceName;
	}

	/**
	 * set方法。产品名称
	 */
	public void setProduceName(String produceName) {
		this.produceName = produceName;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getProduceSummation() {
		return this.produceSummation;
	}

	/**
	 * set方法。
	 */
	public void setProduceSummation(BigDecimal produceSummation) {
		this.produceSummation = produceSummation;
	}
    
	/**
	 * get方法。重量通用单位：千克
	 */
	public BigDecimal getProduceSummationUnit() {
		return this.produceSummationUnit;
	}

	/**
	 * set方法。重量通用单位：千克
	 */
	public void setProduceSummationUnit(BigDecimal produceSummationUnit) {
		this.produceSummationUnit = produceSummationUnit;
	}
    
	/**
	 * get方法。产品产量单位，1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getProduceSummationUnitCode() {
		return this.produceSummationUnitCode;
	}

	/**
	 * set方法。产品产量单位，1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setProduceSummationUnitCode(Byte produceSummationUnitCode) {
		this.produceSummationUnitCode = produceSummationUnitCode;
	}
    
	/**
	 * get方法。产品产量单位文本，1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getProduceSummationUnitText() {
		return this.produceSummationUnitText;
	}

	/**
	 * set方法。产品产量单位文本，1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setProduceSummationUnitText(String produceSummationUnitText) {
		this.produceSummationUnitText = produceSummationUnitText;
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
    

    //一对多关系中，多端数据列表

}







