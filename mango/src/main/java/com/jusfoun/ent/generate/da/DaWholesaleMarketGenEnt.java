package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:12
 * @description 实体类DaWholesaleMarketGenEnt，自动生成。批发市场
 */

public class DaWholesaleMarketGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String content;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String coverArea;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String daysVolume;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String latitude;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String longitude;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String merchantsNum;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String stallsNum;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tel;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String yearsAmount;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set方法。
	 */
	public void setAddress(String address) {
		this.address = address;
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
	public String getContent() {
		return this.content;
	}

	/**
	 * set方法。
	 */
	public void setContent(String content) {
		this.content = content;
	}
    
	/**
	 * get方法。
	 */
	public String getCoverArea() {
		return this.coverArea;
	}

	/**
	 * set方法。
	 */
	public void setCoverArea(String coverArea) {
		this.coverArea = coverArea;
	}
    
	/**
	 * get方法。
	 */
	public String getDaysVolume() {
		return this.daysVolume;
	}

	/**
	 * set方法。
	 */
	public void setDaysVolume(String daysVolume) {
		this.daysVolume = daysVolume;
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
	public String getLatitude() {
		return this.latitude;
	}

	/**
	 * set方法。
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    
	/**
	 * get方法。
	 */
	public String getLongitude() {
		return this.longitude;
	}

	/**
	 * set方法。
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
    
	/**
	 * get方法。
	 */
	public String getMerchantsNum() {
		return this.merchantsNum;
	}

	/**
	 * set方法。
	 */
	public void setMerchantsNum(String merchantsNum) {
		this.merchantsNum = merchantsNum;
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
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionText() {
		return this.regionText;
	}

	/**
	 * set方法。
	 */
	public void setRegionText(String regionText) {
		this.regionText = regionText;
	}
    
	/**
	 * get方法。
	 */
	public String getStallsNum() {
		return this.stallsNum;
	}

	/**
	 * set方法。
	 */
	public void setStallsNum(String stallsNum) {
		this.stallsNum = stallsNum;
	}
    
	/**
	 * get方法。
	 */
	public String getTel() {
		return this.tel;
	}

	/**
	 * set方法。
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
    
	/**
	 * get方法。
	 */
	public String getYearsAmount() {
		return this.yearsAmount;
	}

	/**
	 * set方法。
	 */
	public void setYearsAmount(String yearsAmount) {
		this.yearsAmount = yearsAmount;
	}
    










}







