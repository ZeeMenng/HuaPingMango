package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:41
 * @description 实体类MfWeatherGenEnt，自动生成。气象建模,气象阈值表
 */

public class MfWeatherGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="预警次数",hidden=false,required=false)
    private Integer warningNum;
    @ApiModelProperty(value="气象阈值",hidden=false,required=false)
    private Integer weatherThreshold;

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
    










}







