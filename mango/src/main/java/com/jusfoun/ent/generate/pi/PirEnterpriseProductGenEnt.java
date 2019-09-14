package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/11 17:38:34
 * @description 实体类PirEnterpriseProductGenEnt，自动生成。企业和产品中间表
 */

public class PirEnterpriseProductGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应企业表id",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排序依据",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="引用产品（pi_product_recommend）表的主键",hidden=false,required=false)
    private String productId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。对应企业表id
	 */
	public String getEnterpriseId() {
		return this.enterpriseId;
	}

	/**
	 * set方法。对应企业表id
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
    

    //一对多关系中，多端数据列表

}







