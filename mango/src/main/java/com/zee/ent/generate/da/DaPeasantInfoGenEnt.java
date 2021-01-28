package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:04
 * @description 实体类DaPeasantInfoGenEnt，自动生成。农户信息表
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
    @ApiModelProperty(value="智能手机操作熟练程度：对应数据字典表（dictionary）中的编码字段（code） 1代表好，2代表一般，3代表差，4代表完全不会",allowableValues="0,1",hidden=false,required=false)
    private Byte mobileOperateCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String mobileOperateText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String mobileType;
    @ApiModelProperty(value="农户类型：对应数据字典表（dictionary）中的编码字段（code） 1：示范户，2：重点户，3：其他",allowableValues="0,1",hidden=false,required=false)
    private Byte peasantTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String peasantTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String userId;

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
	 * get方法。智能手机操作熟练程度：对应数据字典表（dictionary）中的编码字段（code） 1代表好，2代表一般，3代表差，4代表完全不会
	 */
	public Byte getMobileOperateCode() {
		return this.mobileOperateCode;
	}

	/**
	 * set方法。智能手机操作熟练程度：对应数据字典表（dictionary）中的编码字段（code） 1代表好，2代表一般，3代表差，4代表完全不会
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
	 * get方法。农户类型：对应数据字典表（dictionary）中的编码字段（code） 1：示范户，2：重点户，3：其他
	 */
	public Byte getPeasantTypeCode() {
		return this.peasantTypeCode;
	}

	/**
	 * set方法。农户类型：对应数据字典表（dictionary）中的编码字段（code） 1：示范户，2：重点户，3：其他
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
    










}







