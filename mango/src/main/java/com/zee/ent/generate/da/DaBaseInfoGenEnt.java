package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:49
 * @description 实体类DaBaseInfoGenEnt，自动生成。基地信息表
 */

public class DaBaseInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="通用面积（用于存放换算为平方米之后的面积数值）",hidden=false,required=false)
    private BigDecimal areaUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal baseArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private Byte baseAreaUnit;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String baseCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String baseLatitude;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String baseLongitude;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String baseName;
    @ApiModelProperty(value="基地类型 1：大棚，2：其他",allowableValues="0,1",hidden=false,required=false)
    private Byte baseTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String baseTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="认证类型 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证",allowableValues="0,1",hidden=false,required=false)
    private Byte identificationTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String identificationTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String owner;
    @ApiModelProperty(value="种植户数量",hidden=false,required=false)
    private BigDecimal peasantCount;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getBaseCode() {
		return this.baseCode;
	}

	/**
	 * set方法。
	 */
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
    
	/**
	 * get方法。
	 */
	public String getBaseLatitude() {
		return this.baseLatitude;
	}

	/**
	 * set方法。
	 */
	public void setBaseLatitude(String baseLatitude) {
		this.baseLatitude = baseLatitude;
	}
    
	/**
	 * get方法。
	 */
	public String getBaseLongitude() {
		return this.baseLongitude;
	}

	/**
	 * set方法。
	 */
	public void setBaseLongitude(String baseLongitude) {
		this.baseLongitude = baseLongitude;
	}
    
	/**
	 * get方法。
	 */
	public String getBaseName() {
		return this.baseName;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getBaseTypeText() {
		return this.baseTypeText;
	}

	/**
	 * set方法。
	 */
	public void setBaseTypeText(String baseTypeText) {
		this.baseTypeText = baseTypeText;
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
	 * get方法。
	 */
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
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
	 * get方法。
	 */
	public String getIdentificationTypeText() {
		return this.identificationTypeText;
	}

	/**
	 * set方法。
	 */
	public void setIdentificationTypeText(String identificationTypeText) {
		this.identificationTypeText = identificationTypeText;
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
	public String getOwner() {
		return this.owner;
	}

	/**
	 * set方法。
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
    










}







