package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:30
 * @description 实体类MfFormatrixGenEnt，自动生成。波士顿矩阵
 */

public class MfFormatrixGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="价格涨跌,单位：元/千克",hidden=false,required=false)
    private BigDecimal priceChange;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="价格变化率",hidden=false,required=false)
    private BigDecimal returnChange;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。价格涨跌,单位：元/千克
	 */
	public BigDecimal getPriceChange() {
		return this.priceChange;
	}

	/**
	 * set方法。价格涨跌,单位：元/千克
	 */
	public void setPriceChange(BigDecimal priceChange) {
		this.priceChange = priceChange;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	/**
	 * get方法。价格变化率
	 */
	public BigDecimal getReturnChange() {
		return this.returnChange;
	}

	/**
	 * set方法。价格变化率
	 */
	public void setReturnChange(BigDecimal returnChange) {
		this.returnChange = returnChange;
	}
    










}







