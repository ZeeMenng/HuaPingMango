package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:48
 * @description 实体类PirEnterpriseProductGenEnt，自动生成。企业和产品中间表
 */

public class PirEnterpriseProductGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排序依据",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String productId;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。排序依据
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排序依据
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
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
    










}







