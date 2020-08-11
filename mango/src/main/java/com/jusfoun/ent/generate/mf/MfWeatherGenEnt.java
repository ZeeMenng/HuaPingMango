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
 * @updateDate 2020/8/11 11:44:04
 * @description 实体类MfWeatherGenEnt，自动生成。气象建模,气象阈值表
 */

public class MfWeatherGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="按月，存YYYY-mm，如2018-08",hidden=false,required=true)
    private String dateTime;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="预警次数",hidden=false,required=false)
    private Integer warningNum;
    @ApiModelProperty(value="气象阈值",hidden=false,required=false)
    private Integer weatherThreshold;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。按月，存YYYY-mm，如2018-08
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。按月，存YYYY-mm，如2018-08
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
	 * get方法。预警次数
	 */
	public Integer getWarningNum() {
		return this.warningNum;
	}

	/**
	 * set方法。预警次数
	 */
	public void setWarningNum(Integer warningNum) {
		this.warningNum = warningNum;
	}
    
	/**
	 * get方法。气象阈值
	 */
	public Integer getWeatherThreshold() {
		return this.weatherThreshold;
	}

	/**
	 * set方法。气象阈值
	 */
	public void setWeatherThreshold(Integer weatherThreshold) {
		this.weatherThreshold = weatherThreshold;
	}
    

    //一对多关系中，多端数据列表

}







