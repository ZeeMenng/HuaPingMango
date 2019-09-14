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
 * @updateDate 2019/1/4 9:29:52
 * @description 实体类DaPeasantInfoGenEnt，自动生成。
 */

public class DaPeasantInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String idNumber;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte mobileOperateCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String mobileOperateText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String mobileType;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte peasantTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String peasantTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String userId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * set方法。
	 */
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
    
	/**
	 * get方法。
	 */
	public Byte getMobileOperateCode() {
		return this.mobileOperateCode;
	}

	/**
	 * set方法。
	 */
	public void setMobileOperateCode(Byte mobileOperateCode) {
		this.mobileOperateCode = mobileOperateCode;
	}
    
	/**
	 * get方法。
	 */
	public String getMobileOperateText() {
		return this.mobileOperateText;
	}

	/**
	 * set方法。
	 */
	public void setMobileOperateText(String mobileOperateText) {
		this.mobileOperateText = mobileOperateText;
	}
    
	/**
	 * get方法。
	 */
	public String getMobileType() {
		return this.mobileType;
	}

	/**
	 * set方法。
	 */
	public void setMobileType(String mobileType) {
		this.mobileType = mobileType;
	}
    
	/**
	 * get方法。
	 */
	public Byte getPeasantTypeCode() {
		return this.peasantTypeCode;
	}

	/**
	 * set方法。
	 */
	public void setPeasantTypeCode(Byte peasantTypeCode) {
		this.peasantTypeCode = peasantTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getPeasantTypeText() {
		return this.peasantTypeText;
	}

	/**
	 * set方法。
	 */
	public void setPeasantTypeText(String peasantTypeText) {
		this.peasantTypeText = peasantTypeText;
	}
    
	/**
	 * get方法。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    

    //一对多关系中，多端数据列表

}







