package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:37
 * @description 实体类MfProcessYieldGenEnt，自动生成。加工品产量建模表
 */

public class MfProcessYieldGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dateTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="产值实际值，单位，元",hidden=false,required=false)
    private BigDecimal outputValueActual;
    @ApiModelProperty(value="产值预测值，单位，元",hidden=false,required=false)
    private BigDecimal outputValueForecast;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱",allowableValues="0,1",hidden=false,required=false)
    private Byte processStrainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String processStrainsText;
    @ApiModelProperty(value="产量实际值，单位 千克",hidden=false,required=false)
    private BigDecimal yieldActual;
    @ApiModelProperty(value="产量预测值，单位，千克",hidden=false,required=false)
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
	 * get方法。产值实际值，单位，元
	 */
	public BigDecimal getOutputValueActual() {
		return this.outputValueActual;
	}

	/**
	 * set方法。产值实际值，单位，元
	 */
	public void setOutputValueActual(BigDecimal outputValueActual) {
		this.outputValueActual = outputValueActual;
	}
    
	/**
	 * get方法。产值预测值，单位，元
	 */
	public BigDecimal getOutputValueForecast() {
		return this.outputValueForecast;
	}

	/**
	 * set方法。产值预测值，单位，元
	 */
	public void setOutputValueForecast(BigDecimal outputValueForecast) {
		this.outputValueForecast = outputValueForecast;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public Byte getProcessStrainsCode() {
		return this.processStrainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）加工品品种  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public void setProcessStrainsCode(Byte processStrainsCode) {
		this.processStrainsCode = processStrainsCode;
	}
    
	/**
	 * get方法。
	 */
	public String getProcessStrainsText() {
		return this.processStrainsText;
	}

	/**
	 * set方法。
	 */
	public void setProcessStrainsText(String processStrainsText) {
		this.processStrainsText = processStrainsText;
	}
    
	/**
	 * get方法。产量实际值，单位 千克
	 */
	public BigDecimal getYieldActual() {
		return this.yieldActual;
	}

	/**
	 * set方法。产量实际值，单位 千克
	 */
	public void setYieldActual(BigDecimal yieldActual) {
		this.yieldActual = yieldActual;
	}
    
	/**
	 * get方法。产量预测值，单位，千克
	 */
	public BigDecimal getYieldForecast() {
		return this.yieldForecast;
	}

	/**
	 * set方法。产量预测值，单位，千克
	 */
	public void setYieldForecast(BigDecimal yieldForecast) {
		this.yieldForecast = yieldForecast;
	}
    










}







