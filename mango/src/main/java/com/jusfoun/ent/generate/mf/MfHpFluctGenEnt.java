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
 * @updateDate 2020/8/11 11:43:58
 * @description 实体类MfHpFluctGenEnt，自动生成。批发价格周期性波动规律分析
 */

public class MfHpFluctGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="日期，按月，存YYYY-MM-01",hidden=false,required=true)
    private String dateTime;
    @ApiModelProperty(value="价格，单位：元/千克",hidden=false,required=false)
    private BigDecimal datelyPrice;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="地区code",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="地区名",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="长期趋势",hidden=false,required=false)
    private BigDecimal trend;
    @ApiModelProperty(value="短周期波动",hidden=false,required=false)
    private BigDecimal undulation;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。日期，按月，存YYYY-MM-01
	 */
	public String getDateTime() {
		return this.dateTime;
	}

	/**
	 * set方法。日期，按月，存YYYY-MM-01
	 */
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
    
	/**
	 * get方法。价格，单位：元/千克
	 */
	public BigDecimal getDatelyPrice() {
		return this.datelyPrice;
	}

	/**
	 * set方法。价格，单位：元/千克
	 */
	public void setDatelyPrice(BigDecimal datelyPrice) {
		this.datelyPrice = datelyPrice;
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
	 * get方法。地区code
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。地区code
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。地区名
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。地区名
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	/**
	 * get方法。长期趋势
	 */
	public BigDecimal getTrend() {
		return this.trend;
	}

	/**
	 * set方法。长期趋势
	 */
	public void setTrend(BigDecimal trend) {
		this.trend = trend;
	}
    
	/**
	 * get方法。短周期波动
	 */
	public BigDecimal getUndulation() {
		return this.undulation;
	}

	/**
	 * set方法。短周期波动
	 */
	public void setUndulation(BigDecimal undulation) {
		this.undulation = undulation;
	}
    

    //一对多关系中，多端数据列表

}







