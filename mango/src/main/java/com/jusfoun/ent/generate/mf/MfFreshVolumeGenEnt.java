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
 * @updateDate 2020/8/11 11:43:57
 * @description 实体类MfFreshVolumeGenEnt，自动生成。mf_fresh_volume
 */

public class MfFreshVolumeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="实际销售金额，单位：元",hidden=false,required=false)
    private BigDecimal actualAmount;
    @ApiModelProperty(value="实际销售量，单位：千克",hidden=false,required=false)
    private BigDecimal actualVolume;
    @ApiModelProperty(value="日期，年份，存YYYY",hidden=false,required=true)
    private String dateTime;
    @ApiModelProperty(value="预测销售金额，单位：元",hidden=false,required=false)
    private BigDecimal forecastAmount;
    @ApiModelProperty(value="预测销售量，单位：千克",hidden=false,required=false)
    private BigDecimal forecastVolume;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。实际销售金额，单位：元
	 */
	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	/**
	 * set方法。实际销售金额，单位：元
	 */
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
    
	/**
	 * get方法。实际销售量，单位：千克
	 */
	public BigDecimal getActualVolume() {
		return this.actualVolume;
	}

	/**
	 * set方法。实际销售量，单位：千克
	 */
	public void setActualVolume(BigDecimal actualVolume) {
		this.actualVolume = actualVolume;
	}
    
	/**
	 * get方法。日期，年份，存YYYY
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。日期，年份，存YYYY
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
    
	/**
	 * get方法。预测销售金额，单位：元
	 */
	public BigDecimal getForecastAmount() {
		return this.forecastAmount;
	}

	/**
	 * set方法。预测销售金额，单位：元
	 */
	public void setForecastAmount(BigDecimal forecastAmount) {
		this.forecastAmount = forecastAmount;
	}
    
	/**
	 * get方法。预测销售量，单位：千克
	 */
	public BigDecimal getForecastVolume() {
		return this.forecastVolume;
	}

	/**
	 * set方法。预测销售量，单位：千克
	 */
	public void setForecastVolume(BigDecimal forecastVolume) {
		this.forecastVolume = forecastVolume;
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
    

    //一对多关系中，多端数据列表

}







