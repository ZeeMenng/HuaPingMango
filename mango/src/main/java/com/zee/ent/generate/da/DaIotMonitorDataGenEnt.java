package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:02
 * @description 实体类DaIotMonitorDataGenEnt，自动生成。物联网设备监控数据
 */

public class DaIotMonitorDataGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="添加记录时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String displayName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String hid;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String sensorName;
    @ApiModelProperty(value="传感器类型",hidden=false,required=false)
    private Integer sensorType;
    @ApiModelProperty(value="监控时间",hidden=false,required=false)
    private Date time;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String unit;
    @ApiModelProperty(value="传感器的采集的值",hidden=false,required=false)
    private Double val;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。添加记录时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。添加记录时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
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
	public String getHid() {
		return this.hid;
	}

	/**
	 * set方法。
	 */
	public void setHid(String hid) {
		this.hid = hid;
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
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。
	 */
	public String getSensorName() {
		return this.sensorName;
	}

	/**
	 * set方法。
	 */
	public void setSensorName(String sensorName) {
		this.sensorName = sensorName;
	}
    
	/**
	 * get方法。传感器类型
	 */
	public Integer getSensorType() {
		return this.sensorType;
	}

	/**
	 * set方法。传感器类型
	 */
	public void setSensorType(Integer sensorType) {
		this.sensorType = sensorType;
	}
    
	/**
	 * get方法。监控时间
	 */
	public Date getTime() {
		return this.time;
	}

	/**
	 * set方法。监控时间
	 */
	public void setTime(Date time) {
		this.time = time;
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
    
	/**
	 * get方法。传感器的采集的值
	 */
	public Double getVal() {
		return this.val;
	}

	/**
	 * set方法。传感器的采集的值
	 */
	public void setVal(Double val) {
		this.val = val;
	}
    










}







