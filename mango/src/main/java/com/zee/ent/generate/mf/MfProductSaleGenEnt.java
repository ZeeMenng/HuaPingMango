package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:38
 * @description 实体类MfProductSaleGenEnt，自动生成。产销预测表
 */

public class MfProductSaleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="销量实际值， 单位：千克",hidden=false,required=false)
    private BigDecimal saleActual;
    @ApiModelProperty(value="销量预测值， 单位：千克",hidden=false,required=false)
    private BigDecimal saleForecast;
    @ApiModelProperty(value="产量实际值， 单位：千克",hidden=false,required=false)
    private BigDecimal yieldActual;
    @ApiModelProperty(value="产量预测值， 单位：千克",hidden=false,required=false)
    private BigDecimal yieldForecast;

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
	 * get方法。销量实际值， 单位：千克
	 */
	public BigDecimal getSaleActual() {
		return this.saleActual;
	}

	/**
	 * set方法。销量实际值， 单位：千克
	 */
	public void setSaleActual(BigDecimal saleActual) {
		this.saleActual = saleActual;
	}
    
	/**
	 * get方法。销量预测值， 单位：千克
	 */
	public BigDecimal getSaleForecast() {
		return this.saleForecast;
	}

	/**
	 * set方法。销量预测值， 单位：千克
	 */
	public void setSaleForecast(BigDecimal saleForecast) {
		this.saleForecast = saleForecast;
	}
    
	/**
	 * get方法。产量实际值， 单位：千克
	 */
	public BigDecimal getYieldActual() {
		return this.yieldActual;
	}

	/**
	 * set方法。产量实际值， 单位：千克
	 */
	public void setYieldActual(BigDecimal yieldActual) {
		this.yieldActual = yieldActual;
	}
    
	/**
	 * get方法。产量预测值， 单位：千克
	 */
	public BigDecimal getYieldForecast() {
		return this.yieldForecast;
	}

	/**
	 * set方法。产量预测值， 单位：千克
	 */
	public void setYieldForecast(BigDecimal yieldForecast) {
		this.yieldForecast = yieldForecast;
	}
    










}







