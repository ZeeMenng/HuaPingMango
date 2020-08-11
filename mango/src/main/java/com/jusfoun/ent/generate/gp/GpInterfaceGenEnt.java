package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:49
 * @description 实体类GpInterfaceGenEnt，自动生成。系统接口。
 */

public class GpInterfaceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=true)
    private String domainId;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否为公共接口。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。",allowableValues="0,1",hidden=false,required=false)
    private Byte isPublicCode;
    @ApiModelProperty(value="备注字段。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="操作主表。",hidden=false,required=false)
    private String tableName;
    @ApiModelProperty(value="接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：0GET，1POST。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private String typeCode;
    @ApiModelProperty(value="访问路径。",hidden=false,required=true)
    private String url;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。记录创建时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录创建时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public String getDomainId() {
		return this.domainId;
	}

	/**
	 * set方法。应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
    
	/**
	 * get方法。主键。
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键。
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。是否为公共接口。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public Byte getIsPublicCode() {
		return this.isPublicCode;
	}

	/**
	 * set方法。是否为公共接口。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public void setIsPublicCode(Byte isPublicCode) {
		this.isPublicCode = isPublicCode;
	}
    
	/**
	 * get方法。备注字段。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注字段。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。操作主表。
	 */
	public String getTableName() {
		return this.tableName;
	}

	/**
	 * set方法。操作主表。
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
    
	/**
	 * get方法。接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：0GET，1POST。默认值0。
	 */
	public String getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set方法。接口调用方式。对应数据字典表（dictionary）中的编码字段（code）。目前两种类型：0GET，1POST。默认值0。
	 */
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
    
	/**
	 * get方法。访问路径。
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * set方法。访问路径。
	 */
	public void setUrl(String url) {
		this.url = url;
	}
    

    //一对多关系中，多端数据列表

}







