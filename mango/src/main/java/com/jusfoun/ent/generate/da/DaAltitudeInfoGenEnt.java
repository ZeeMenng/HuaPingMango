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
 * @updateDate 2018-6-13 19:04:28
 * @description 实体类DaAltitudeInfoGenEnt，自动生成。海拔数据表
 */

public class DaAltitudeInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="海拔（米）",hidden=false,required=false)
    private BigDecimal altitude;
    @ApiModelProperty(value="平均气温（摄氏度）",hidden=false,required=false)
    private BigDecimal averageTemperature;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。海拔（米）
	 */
	public BigDecimal getAltitude() {
		return this.altitude;
	}

	/**
	 * set方法。海拔（米）
	 */
	public void setAltitude(BigDecimal altitude) {
		this.altitude = altitude;
	}
    
	/**
	 * get方法。平均气温（摄氏度）
	 */
	public BigDecimal getAverageTemperature() {
		return this.averageTemperature;
	}

	/**
	 * set方法。平均气温（摄氏度）
	 */
	public void setAverageTemperature(BigDecimal averageTemperature) {
		this.averageTemperature = averageTemperature;
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
    

    //一对多关系中，多端数据列表

}







