package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:47
 * @description 实体类DarEnterpriseResourceGenEnt，自动生成。企业和资源中间表
 */

public class DarEnterpriseResourceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="资源用途：对应数据字典表（dictionary）中的编码字段（code） 1：推介轮播，2，企业logo及资质证书，3、视频介绍",allowableValues="0,1",hidden=false,required=false)
    private Byte typeCode;

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
    










}







