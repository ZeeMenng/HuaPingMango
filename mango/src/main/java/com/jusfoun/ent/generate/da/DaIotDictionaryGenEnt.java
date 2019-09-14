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
 * @updateDate 2018-6-13 19:04:33
 * @description 实体类DaIotDictionaryGenEnt，自动生成。物联网传感器设备字典
 */

public class DaIotDictionaryGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="显示名称",hidden=false,required=false)
    private String displayName;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="传感器名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="传感器类型",hidden=false,required=false)
    private Integer type;
    @ApiModelProperty(value="单位",hidden=false,required=false)
    private String unit;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。显示名称
	 */
	public String getDisplayName() {
		return this.displayName;
	}

	/**
	 * set方法。显示名称
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
    
	/**
	 * get方法。主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。传感器名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。传感器名称
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
	 * get方法。单位
	 */
	public String getUnit() {
		return this.unit;
	}

	/**
	 * set方法。单位
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
    

    //一对多关系中，多端数据列表

}







