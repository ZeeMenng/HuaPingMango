package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:35
 * @description 实体类MfProcessMaterialConsumeGenEnt，自动生成。加工品原料消耗情况预测表
 */

public class MfProcessMaterialConsumeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="鲜果消耗量实际值，单位：万吨",hidden=false,required=false)
    private BigDecimal actualAmount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="鲜果消耗量预测值，单位：万吨",hidden=false,required=false)
    private BigDecimal forecastAmount;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="加工品消耗鲜果量占鲜果产量比例实际值",hidden=false,required=false)
    private BigDecimal scaleActual;
    @ApiModelProperty(value="加工品消耗鲜果量占鲜果产量比例预测值",hidden=false,required=false)
    private BigDecimal scaleForecast;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。鲜果消耗量实际值，单位：万吨
	 */
	public BigDecimal getActualAmount() {
		return this.actualAmount;
	}

	/**
	 * set方法。鲜果消耗量实际值，单位：万吨
	 */
	public void setActualAmount(BigDecimal actualAmount) {
		this.actualAmount = actualAmount;
	}
    
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
	 * get方法。鲜果消耗量预测值，单位：万吨
	 */
	public BigDecimal getForecastAmount() {
		return this.forecastAmount;
	}

	/**
	 * set方法。鲜果消耗量预测值，单位：万吨
	 */
	public void setForecastAmount(BigDecimal forecastAmount) {
		this.forecastAmount = forecastAmount;
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
	 * get方法。加工品消耗鲜果量占鲜果产量比例实际值
	 */
	public BigDecimal getScaleActual() {
		return this.scaleActual;
	}

	/**
	 * set方法。加工品消耗鲜果量占鲜果产量比例实际值
	 */
	public void setScaleActual(BigDecimal scaleActual) {
		this.scaleActual = scaleActual;
	}
    
	/**
	 * get方法。加工品消耗鲜果量占鲜果产量比例预测值
	 */
	public BigDecimal getScaleForecast() {
		return this.scaleForecast;
	}

	/**
	 * set方法。加工品消耗鲜果量占鲜果产量比例预测值
	 */
	public void setScaleForecast(BigDecimal scaleForecast) {
		this.scaleForecast = scaleForecast;
	}
    










}







