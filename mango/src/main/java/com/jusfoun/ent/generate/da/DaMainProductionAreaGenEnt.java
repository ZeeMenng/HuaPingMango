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
 * @updateDate 2020/8/11 11:43:26
 * @description 实体类DaMainProductionAreaGenEnt，自动生成。芒果主产区
 */

public class DaMainProductionAreaGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="介绍",hidden=false,required=false)
    private String content;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="纬度",hidden=false,required=false)
    private String latitude;
    @ApiModelProperty(value="经度",hidden=false,required=false)
    private String longitude;
    @ApiModelProperty(value="主产区名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="所属区域代码",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="所属区域名称",hidden=false,required=false)
    private String regionText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。介绍
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * set方法。介绍
	 */
	public void setContent(String content) {
		this.content = content;
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
	 * get方法。纬度
	 */
	public String getLatitude() {
		return this.latitude;
	}

	/**
	 * set方法。纬度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    
	/**
	 * get方法。经度
	 */
	public String getLongitude() {
		return this.longitude;
	}

	/**
	 * set方法。经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    
	/**
	 * get方法。主产区名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。主产区名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。所属区域代码
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。所属区域代码
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。所属区域名称
	 */
	public String getRegionText() {
		return this.regionText;
	}

	/**
	 * set方法。所属区域名称
	 */
	public void setRegionText(String regionText) {
		this.regionText = regionText;
	}
    

    //一对多关系中，多端数据列表

}







