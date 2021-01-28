package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:48
 * @description 实体类DaAltitudeInfoGenEnt，自动生成。海拔数据表
 */

public class DaAltitudeInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="海拔（米）",hidden=false,required=false)
    private BigDecimal altitude;
    @ApiModelProperty(value="平均气温（摄氏度）",hidden=false,required=false)
    private BigDecimal averageTemperature;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
    










}







