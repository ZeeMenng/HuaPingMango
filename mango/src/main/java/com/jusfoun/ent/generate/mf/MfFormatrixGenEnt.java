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
 * @description 实体类MfFormatrixGenEnt，自动生成。波士顿矩阵
 */

public class MfFormatrixGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="价格涨跌,单位：元/千克",hidden=false,required=false)
    private BigDecimal priceChange;
    @ApiModelProperty(value="地区名code 0-全国",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="地区名  0-全国",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="价格变化率",hidden=false,required=false)
    private BigDecimal returnChange;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。地区名code 0-全国
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。地区名code 0-全国
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。地区名  0-全国
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。地区名  0-全国
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
    

    //一对多关系中，多端数据列表

}







