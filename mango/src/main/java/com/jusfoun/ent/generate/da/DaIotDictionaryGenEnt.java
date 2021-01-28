package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:01
 * @description 实体类DaIotDictionaryGenEnt，自动生成。物联网传感器设备字典
 */

public class DaIotDictionaryGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String displayName;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="传感器类型",hidden=false,required=false)
    private Integer type;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String unit;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * set方法。
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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
	 * get方法。传感器类型
	 */
	public Integer getType() {
		return this.type;
	}

	/**
	 * set方法。传感器类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
    
	/**
	 * get方法。
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * set方法。
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
    










}







