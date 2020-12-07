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
 * @updateDate 2020/8/11 11:43:10
 * @description 实体类DaBaseInfoGenEnt，自动生成。基地信息表
 */

public class DaBaseInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="地址",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="通用面积（用于存放换算为平方米之后的面积数值）",hidden=false,required=false)
    private BigDecimal areaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal baseArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private Byte baseAreaUnit;
    @ApiModelProperty(value="基地编码",allowableValues="0,1",hidden=false,required=false)
    private String baseCode;
    @ApiModelProperty(value="基地坐标维度",hidden=false,required=false)
    private String baseLatitude;
    @ApiModelProperty(value="基地坐标经度",hidden=false,required=false)
    private String baseLongitude;
    @ApiModelProperty(value="基地名称",hidden=false,required=false)
    private String baseName;
    @ApiModelProperty(value="基地类型 1：大棚，2：其他",allowableValues="0,1",hidden=false,required=false)
    private Byte baseTypeCode;
    @ApiModelProperty(value="基地类型文本 1：大棚，2：其他",hidden=false,required=false)
    private String baseTypeText;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="基地所属企业id",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="认证类型 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证",allowableValues="0,1",hidden=false,required=false)
    private Byte identificationTypeCode;
    @ApiModelProperty(value="认证类型文本 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证",hidden=false,required=false)
    private String identificationTypeText;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="所属主体",hidden=false,required=false)
    private String owner;
    @ApiModelProperty(value="种植户数量",hidden=false,required=false)
    private BigDecimal peasantCount;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set方法。地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
    
	/**
	 * get方法。通用面积（用于存放换算为平方米之后的面积数值）
	 */
	public BigDecimal getAreaUnit() {
		return this.areaUnit;
	}

	/**
	 * set方法。通用面积（用于存放换算为平方米之后的面积数值）
	 */
	public void setAreaUnit(BigDecimal areaUnit) {
		this.areaUnit = areaUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getBaseArea() {
		return this.baseArea;
	}

	/**
	 * set方法。
	 */
	public void setBaseArea(BigDecimal baseArea) {
		this.baseArea = baseArea;
	}
    
	/**
	 * get方法。
	 */
	public Byte getBaseAreaUnit() {
		return this.baseAreaUnit;
	}

	/**
	 * set方法。
	 */
	public void setBaseAreaUnit(Byte baseAreaUnit) {
		this.baseAreaUnit = baseAreaUnit;
	}
    
	/**
	 * get方法。基地编码
	 */
	public String getBaseCode() {
		return this.baseCode;
	}

	/**
	 * set方法。基地编码
	 */
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
    
	/**
	 * get方法。基地坐标维度
	 */
	public String getBaseLatitude() {
		return this.baseLatitude;
	}

	/**
	 * set方法。基地坐标维度
	 */
	public void setBaseLatitude(String baseLatitude) {
		this.baseLatitude = baseLatitude;
	}
    
	/**
	 * get方法。基地坐标经度
	 */
	public String getBaseLongitude() {
		return this.baseLongitude;
	}

	/**
	 * set方法。基地坐标经度
	 */
	public void setBaseLongitude(String baseLongitude) {
		this.baseLongitude = baseLongitude;
	}
    
	/**
	 * get方法。基地名称
	 */
	public String getBaseName() {
		return this.baseName;
	}

	/**
	 * set方法。基地名称
	 */
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
    
	/**
	 * get方法。基地类型 1：大棚，2：其他
	 */
	public Byte getBaseTypeCode() {
		return this.baseTypeCode;
	}

	/**
	 * set方法。基地类型 1：大棚，2：其他
	 */
	public void setBaseTypeCode(Byte baseTypeCode) {
		this.baseTypeCode = baseTypeCode;
	}
    
	/**
	 * get方法。基地类型文本 1：大棚，2：其他
	 */
	public String getBaseTypeText() {
		return this.baseTypeText;
	}

	/**
	 * set方法。基地类型文本 1：大棚，2：其他
	 */
	public void setBaseTypeText(String baseTypeText) {
		this.baseTypeText = baseTypeText;
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
	 * get方法。基地所属企业id
	 */
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	/**
	 * set方法。基地所属企业id
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	 * get方法。认证类型 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public Byte getIdentificationTypeCode() {
		return this.identificationTypeCode;
	}

	/**
	 * set方法。认证类型 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public void setIdentificationTypeCode(Byte identificationTypeCode) {
		this.identificationTypeCode = identificationTypeCode;
	}
    
	/**
	 * get方法。认证类型文本 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public String getIdentificationTypeText() {
		return this.identificationTypeText;
	}

	/**
	 * set方法。认证类型文本 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证
	 */
	public void setIdentificationTypeText(String identificationTypeText) {
		this.identificationTypeText = identificationTypeText;
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
	 * get方法。所属主体
	 */
	public String getOwner() {
		return this.owner;
	}

	/**
	 * set方法。所属主体
	 */
	public void setOwner(String owner) {
		this.owner = owner;
	}
    
	/**
	 * get方法。种植户数量
	 */
	public BigDecimal getPeasantCount() {
		return this.peasantCount;
	}

	/**
	 * set方法。种植户数量
	 */
	public void setPeasantCount(BigDecimal peasantCount) {
		this.peasantCount = peasantCount;
	}
    

    //一对多关系中，多端数据列表

}







