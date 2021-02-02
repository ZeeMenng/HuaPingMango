package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:34
 * @description 实体类MfPerUnitYieldGenEnt，自动生成。单产预测建模表
 */

public class MfPerUnitYieldGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="实际值，单位：千克/亩",hidden=false,required=false)
    private BigDecimal actualVolume;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="预测值，单位：千克/亩",hidden=false,required=false)
    private BigDecimal forecastVolume;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。实际值，单位：千克/亩
	 */
	public BigDecimal getActualVolume() {
		return this.actualVolume;
	}

	/**
	 * set方法。实际值，单位：千克/亩
	 */
	public void setActualVolume(BigDecimal actualVolume) {
		this.actualVolume = actualVolume;
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
	 * get方法。预测值，单位：千克/亩
	 */
	public BigDecimal getForecastVolume() {
		return this.forecastVolume;
	}

	/**
	 * set方法。预测值，单位：千克/亩
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
    










}







