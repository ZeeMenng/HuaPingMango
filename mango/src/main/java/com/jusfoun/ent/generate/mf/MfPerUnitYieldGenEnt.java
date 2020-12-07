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
 * @updateDate 2020/8/11 11:43:59
 * @description 实体类MfPerUnitYieldGenEnt，自动生成。单产预测建模表
 */

public class MfPerUnitYieldGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="实际值，单位：千克/亩",hidden=false,required=false)
    private BigDecimal actualVolume;
    @ApiModelProperty(value="时间，按年统计，YYYY",hidden=false,required=true)
    private String dateTime;
    @ApiModelProperty(value="预测值，单位：千克/亩",hidden=false,required=false)
    private BigDecimal forecastVolume;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。时间，按年统计，YYYY
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。时间，按年统计，YYYY
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
    

    //一对多关系中，多端数据列表

}







