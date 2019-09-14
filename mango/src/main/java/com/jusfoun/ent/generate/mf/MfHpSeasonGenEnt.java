package com.jusfoun.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:55
 * @description 实体类MfHpSeasonGenEnt，自动生成。批发价格季节性波动规律分析
 */

public class MfHpSeasonGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="日期，月份，从1月到12月，存MM",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="地区code",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="区域名，包括全国和各省",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="规律",hidden=false,required=false)
    private BigDecimal undulation;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。日期，月份，从1月到12月，存MM
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。日期，月份，从1月到12月，存MM
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
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
	 * get方法。地区code
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。地区code
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。区域名，包括全国和各省
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。区域名，包括全国和各省
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
    

    //一对多关系中，多端数据列表

}







