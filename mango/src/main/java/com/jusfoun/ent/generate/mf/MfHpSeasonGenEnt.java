package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:32
 * @description 实体类MfHpSeasonGenEnt，自动生成。批发价格季节性波动规律分析
 */

public class MfHpSeasonGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="规律",hidden=false,required=false)
    private BigDecimal undulation;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	/**
	 * get方法。规律
	 */
	public BigDecimal getUndulation() {
		return this.undulation;
	}

	/**
	 * set方法。规律
	 */
	public void setUndulation(BigDecimal undulation) {
		this.undulation = undulation;
	}
    










}







