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
 * @updateDate 2020/8/11 11:43:27
 * @description 实体类DaPeasantInfoGenEnt，自动生成。农户信息表
 */

public class DaPeasantInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="地址",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="所属合作社",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="身份证号",hidden=false,required=false)
    private String idNumber;
    @ApiModelProperty(value="智能手机操作熟练程度：对应数据字典表（dictionary）中的编码字段（code） 1代表好，2代表一般，3代表差，4代表完全不会",allowableValues="0,1",hidden=false,required=false)
    private Byte mobileOperateCode;
    @ApiModelProperty(value="智能手机操作熟练程度文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1代表好，2代表一般，3代表差，4代表完全不会",hidden=false,required=false)
    private String mobileOperateText;
    @ApiModelProperty(value="智能手机型号",hidden=false,required=false)
    private String mobileType;
    @ApiModelProperty(value="农户类型：对应数据字典表（dictionary）中的编码字段（code） 1：示范户，2：重点户，3：其他",allowableValues="0,1",hidden=false,required=false)
    private Byte peasantTypeCode;
    @ApiModelProperty(value="农户类型文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证1：示范户，2：重点户，3：其他",hidden=false,required=false)
    private String peasantTypeText;
    @ApiModelProperty(value="用户id",hidden=false,required=false)
    private String userId;
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
	 * get方法。所属合作社
	 */
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * set方法。所属合作社
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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
	 * get方法。身份证号
	 */
	public String getIdNumber() {
		return this.idNumber;
	}

	/**
	 * set方法。身份证号
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
	 * get方法。智能手机操作熟练程度文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1代表好，2代表一般，3代表差，4代表完全不会
	 */
	public String getMobileOperateText() {
		return this.mobileOperateText;
	}

	/**
	 * set方法。智能手机操作熟练程度文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1代表好，2代表一般，3代表差，4代表完全不会
	 */
	public void setMobileOperateText(String mobileOperateText) {
		this.mobileOperateText = mobileOperateText;
	}
    
	/**
	 * get方法。智能手机型号
	 */
	public String getMobileType() {
		return this.mobileType;
	}

	/**
	 * set方法。智能手机型号
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
	 * get方法。农户类型文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证1：示范户，2：重点户，3：其他
	 */
	public String getPeasantTypeText() {
		return this.peasantTypeText;
	}

	/**
	 * set方法。农户类型文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证1：示范户，2：重点户，3：其他
	 */
	public void setPeasantTypeText(String peasantTypeText) {
		this.peasantTypeText = peasantTypeText;
	}
    
	/**
	 * get方法。用户id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    

    //一对多关系中，多端数据列表

}







