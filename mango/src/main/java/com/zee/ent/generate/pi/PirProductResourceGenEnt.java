package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:49
 * @description 实体类PirProductResourceGenEnt，自动生成。产品和资源中间表
 */

public class PirProductResourceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String productId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="0：产品图片，1：证书图片",hidden=false,required=false)
    private Byte type;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	public String getProductId() {
		return this.productId;
	}

	/**
	 * set方法。
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
    
	/**
	 * get方法。
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    
	/**
	 * get方法。0：产品图片，1：证书图片
	 */
	public Byte getType() {
		return this.type;
	}

	/**
	 * set方法。0：产品图片，1：证书图片
	 */
	public void setType(Byte type) {
		this.type = type;
	}
    










}







