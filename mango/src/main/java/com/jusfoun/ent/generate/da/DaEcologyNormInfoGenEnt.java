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
 * @updateDate 2020/8/11 11:43:12
 * @description 实体类DaEcologyNormInfoGenEnt，自动生成。种植生态指标表
 */

public class DaEcologyNormInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="适宜区",hidden=false,required=false)
    private String appropriateArea;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="次适宜区",hidden=false,required=false)
    private String ecumenicArea;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="不适宜区",hidden=false,required=false)
    private String inadequateArea;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="指标名称",hidden=false,required=false)
    private String normName;
    @ApiModelProperty(value="最适宜区域",hidden=false,required=false)
    private String optimumArea;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。适宜区
	 */
	public String getAppropriateArea() {
		return this.appropriateArea;
	}

	/**
	 * set方法。适宜区
	 */
	public void setAppropriateArea(String appropriateArea) {
		this.appropriateArea = appropriateArea;
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
	 * get方法。次适宜区
	 */
	public String getEcumenicArea() {
		return this.ecumenicArea;
	}

	/**
	 * set方法。次适宜区
	 */
	public void setEcumenicArea(String ecumenicArea) {
		this.ecumenicArea = ecumenicArea;
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
	 * get方法。不适宜区
	 */
	public String getInadequateArea() {
		return this.inadequateArea;
	}

	/**
	 * set方法。不适宜区
	 */
	public void setInadequateArea(String inadequateArea) {
		this.inadequateArea = inadequateArea;
	}
    
	/**
	 * get方法。名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。指标名称
	 */
	public String getNormName() {
		return this.normName;
	}

	/**
	 * set方法。指标名称
	 */
	public void setNormName(String normName) {
		this.normName = normName;
	}
    
	/**
	 * get方法。最适宜区域
	 */
	public String getOptimumArea() {
		return this.optimumArea;
	}

	/**
	 * set方法。最适宜区域
	 */
	public void setOptimumArea(String optimumArea) {
		this.optimumArea = optimumArea;
	}
    

    //一对多关系中，多端数据列表

}







