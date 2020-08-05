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
 * @updateDate 2020/7/4 16:32:22
 * @description 实体类GprResourceGenEnt，自动生成。附件关联表。只要存有附件字段的表，都会通过此表于gp_resource表关联。
 */

public class GprResourceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="业务表。外键，引用业务表的主键",hidden=false,required=false)
    private String businessId;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否为默认。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",hidden=false,required=false)
    private Byte isDefault;
    @ApiModelProperty(value="附件。外键，引用资源（resource）表的主键",hidden=false,required=false)
    private String resourceId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。业务表。外键，引用业务表的主键
	 */
	public String getBusinessId() {
		return this.businessId;
	}

	/**
	 * set方法。业务表。外键，引用业务表的主键
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
    
	/**
	 * get方法。主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。是否为默认。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsDefault() {
		return this.isDefault;
	}

	/**
	 * set方法。是否为默认。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}
    
	/**
	 * get方法。附件。外键，引用资源（resource）表的主键
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。附件。外键，引用资源（resource）表的主键
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    

    //一对多关系中，多端数据列表

}







