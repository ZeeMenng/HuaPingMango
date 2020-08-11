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
 * @updateDate 2020/8/11 11:43:08
 * @description 实体类DarEnterpriseResourceGenEnt，自动生成。企业和资源中间表
 */

public class DarEnterpriseResourceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应企业表id",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="引用资源（resource）表的主键",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="资源用途：对应数据字典表（dictionary）中的编码字段（code） 1：推介轮播，2，企业logo及资质证书，3、视频介绍",allowableValues="0,1",hidden=false,required=false)
    private Byte typeCode;
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
	 * get方法。资源用途：对应数据字典表（dictionary）中的编码字段（code） 1：推介轮播，2，企业logo及资质证书，3、视频介绍
	 */
	public Byte getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set方法。资源用途：对应数据字典表（dictionary）中的编码字段（code） 1：推介轮播，2，企业logo及资质证书，3、视频介绍
	 */
	public void setTypeCode(Byte typeCode) {
		this.typeCode = typeCode;
	}
    

    //一对多关系中，多端数据列表

}







