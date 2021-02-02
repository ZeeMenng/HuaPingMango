package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:51
 * @description 实体类DaEcologyNormInfoGenEnt，自动生成。种植生态指标表
 */

public class DaEcologyNormInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String appropriateArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String ecumenicArea;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String inadequateArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String normName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String optimumArea;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAppropriateArea() {
		return this.appropriateArea;
	}

	/**
	 * set方法。
	 */
	public void setAppropriateArea(String appropriateArea) {
		this.appropriateArea = appropriateArea;
	}
    
	/**
	 * get方法。
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。
	 */
	public void setCommonFieldId(String commonFieldId) {
		this.commonFieldId = commonFieldId;
	}
    
	/**
	 * get方法。
	 */
	public String getEcumenicArea() {
		return this.ecumenicArea;
	}

	/**
	 * set方法。
	 */
	public void setEcumenicArea(String ecumenicArea) {
		this.ecumenicArea = ecumenicArea;
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
	public String getInadequateArea() {
		return this.inadequateArea;
	}

	/**
	 * set方法。
	 */
	public void setInadequateArea(String inadequateArea) {
		this.inadequateArea = inadequateArea;
	}
    
	/**
	 * get方法。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。
	 */
	public String getNormName() {
		return this.normName;
	}

	/**
	 * set方法。
	 */
	public void setNormName(String normName) {
		this.normName = normName;
	}
    
	/**
	 * get方法。
	 */
	public String getOptimumArea() {
		return this.optimumArea;
	}

	/**
	 * set方法。
	 */
	public void setOptimumArea(String optimumArea) {
		this.optimumArea = optimumArea;
	}
    










}







