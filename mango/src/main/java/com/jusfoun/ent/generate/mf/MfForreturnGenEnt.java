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
 * @description 实体类MfForreturnGenEnt，自动生成。批发价格波动性预测
 */

public class MfForreturnGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="价格波动性，实际",hidden=false,required=false)
    private BigDecimal actualAmount;
    @ApiModelProperty(value="日期，按周，存YYYY-MM-DD",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="价格波动性，预测",hidden=false,required=false)
    private BigDecimal forecastAmount;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="地区code  0-全国",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="地区名，0-全国",hidden=false,required=false)
    private String regionName;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。价格波动性，实际
	 */
	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	/**
	 * set方法。价格波动性，实际
	 */
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
    
	/**
	 * get方法。日期，按周，存YYYY-MM-DD
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。日期，按周，存YYYY-MM-DD
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
    
	/**
	 * get方法。价格波动性，预测
	 */
	public BigDecimal getForecastAmount() {
		return this.forecastAmount;
	}

	/**
	 * set方法。价格波动性，预测
	 */
	public void setForecastAmount(BigDecimal forecastAmount) {
		this.forecastAmount = forecastAmount;
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
	 * get方法。地区code  0-全国
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。地区code  0-全国
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。地区名，0-全国
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。地区名，0-全国
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    

    //一对多关系中，多端数据列表

}







