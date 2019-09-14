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
 * @description 实体类DaIotMonitorBasicGenEnt，自动生成。物联网设备基本信息
 */

public class DaIotMonitorBasicGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="地址",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="基地编码",allowableValues="0,1",hidden=false,required=false)
    private String bascCode;
    @ApiModelProperty(value="基地名称",hidden=false,required=false)
    private String bascName;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createTime;
    @ApiModelProperty(value="设备名称",hidden=false,required=false)
    private String deviceName;
    @ApiModelProperty(value="种植农户",hidden=false,required=false)
    private String farmers;
    @ApiModelProperty(value="硬件主机id",hidden=false,required=false)
    private String hid;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="纬度",hidden=false,required=false)
    private String latitude;
    @ApiModelProperty(value="经度",hidden=false,required=false)
    private String longitude;
    @ApiModelProperty(value="监测的芒果品种，多个品种用逗号隔开",hidden=false,required=false)
    private String mangoType;
    @ApiModelProperty(value="负责人",hidden=false,required=false)
    private String person;
    @ApiModelProperty(value="种植面积",hidden=false,required=false)
    private String plantArea;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="负责人电话",hidden=false,required=false)
    private String telephone;
    @ApiModelProperty(value="更新时间",hidden=false,required=false)
    private Date updateTime;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。地址
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set方法。地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
    
	/**
	 * get方法。基地编码
	 */
	public String getBascCode() {
		return this.bascCode;
	}

	/**
	 * set方法。基地编码
	 */
	public void setBascCode(String bascCode) {
		this.bascCode = bascCode;
	}
    
	/**
	 * get方法。基地名称
	 */
	public String getBascName() {
		return this.bascName;
	}

	/**
	 * set方法。基地名称
	 */
	public void setBascName(String bascName) {
		this.bascName = bascName;
	}
    
	/**
	 * get方法。创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
    
	/**
	 * get方法。设备名称
	 */
	public String getDeviceName() {
		return this.deviceName;
	}

	/**
	 * set方法。设备名称
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
    
	/**
	 * get方法。种植农户
	 */
	public String getFarmers() {
		return this.farmers;
	}

	/**
	 * set方法。种植农户
	 */
	public void setFarmers(String farmers) {
		this.farmers = farmers;
	}
    
	/**
	 * get方法。硬件主机id
	 */
	public String getHid() {
		return this.hid;
	}

	/**
	 * set方法。硬件主机id
	 */
	public void setHid(String hid) {
		this.hid = hid;
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
	 * get方法。监测的芒果品种，多个品种用逗号隔开
	 */
	public String getMangoType() {
		return this.mangoType;
	}

	/**
	 * set方法。监测的芒果品种，多个品种用逗号隔开
	 */
	public void setMangoType(String mangoType) {
		this.mangoType = mangoType;
	}
    
	/**
	 * get方法。负责人
	 */
	public String getPerson() {
		return this.person;
	}

	/**
	 * set方法。负责人
	 */
	public void setPerson(String person) {
		this.person = person;
	}
    
	/**
	 * get方法。种植面积
	 */
	public String getPlantArea() {
		return this.plantArea;
	}

	/**
	 * set方法。种植面积
	 */
	public void setPlantArea(String plantArea) {
		this.plantArea = plantArea;
	}
    
	/**
	 * get方法。备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。负责人电话
	 */
	public String getTelephone() {
		return this.telephone;
	}

	/**
	 * set方法。负责人电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    
	/**
	 * get方法。更新时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    

    //一对多关系中，多端数据列表

}







