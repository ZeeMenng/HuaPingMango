package com.jusfoun.ent.generate.pi;

import java.io.Serializable;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-8-19 10:55:31
 * @description 实体类PirProductResourceGenEnt，自动生成。产品和资源中间表
 */

public class PirProductResourceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="引用产品（pi_product_recommend）表的主键",hidden=false,required=false)
    private String productId;
    @ApiModelProperty(value="引用资源（resource）表的主键",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="0：证书图片，1：默认产品图片",hidden=false,required=false)
    private Byte type;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。引用产品（pi_product_recommend）表的主键
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * set方法。引用产品（pi_product_recommend）表的主键
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
    
	/**
	 * get方法。引用资源（resource）表的主键
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。引用资源（resource）表的主键
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    
	/**
	 * get方法。0：证书图片，1：默认产品图片
	 */
	public Byte getType() {
		return this.type;
	}

	/**
	 * set方法。0：证书图片，1：默认产品图片
	 */
	public void setType(Byte type) {
		this.type = type;
	}
    

    //一对多关系中，多端数据列表

}







