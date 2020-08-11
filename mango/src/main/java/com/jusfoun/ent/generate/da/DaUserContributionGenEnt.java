package com.jusfoun.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:35
 * @description 实体类DaUserContributionGenEnt，自动生成。用户贡献
 */

public class DaUserContributionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="农事操作上传数量",hidden=false,required=false)
    private Integer farmOperNum;
    @ApiModelProperty(value="农事操作上传次数",hidden=false,required=false)
    private Integer farmOperTimes;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="价格上传数量",hidden=false,required=false)
    private Integer priceNum;
    @ApiModelProperty(value="价格上传次数",hidden=false,required=false)
    private Integer priceTimes;
    @ApiModelProperty(value="修改时间",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="上传用户",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="产量上传数量",hidden=false,required=false)
    private Integer yieldNum;
    @ApiModelProperty(value="产量上传次数",hidden=false,required=false)
    private Integer yieldTimes;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。农事操作上传数量
	 */
	public Integer getFarmOperNum() {
		return this.farmOperNum;
	}

	/**
	 * set方法。农事操作上传数量
	 */
	public void setFarmOperNum(Integer farmOperNum) {
		this.farmOperNum = farmOperNum;
	}
    
	/**
	 * get方法。农事操作上传次数
	 */
	public Integer getFarmOperTimes() {
		return this.farmOperTimes;
	}

	/**
	 * set方法。农事操作上传次数
	 */
	public void setFarmOperTimes(Integer farmOperTimes) {
		this.farmOperTimes = farmOperTimes;
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
	 * get方法。价格上传数量
	 */
	public Integer getPriceNum() {
		return this.priceNum;
	}

	/**
	 * set方法。价格上传数量
	 */
	public void setPriceNum(Integer priceNum) {
		this.priceNum = priceNum;
	}
    
	/**
	 * get方法。价格上传次数
	 */
	public Integer getPriceTimes() {
		return this.priceTimes;
	}

	/**
	 * set方法。价格上传次数
	 */
	public void setPriceTimes(Integer priceTimes) {
		this.priceTimes = priceTimes;
	}
    
	/**
	 * get方法。修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	/**
	 * get方法。上传用户
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。上传用户
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	/**
	 * get方法。产量上传数量
	 */
	public Integer getYieldNum() {
		return this.yieldNum;
	}

	/**
	 * set方法。产量上传数量
	 */
	public void setYieldNum(Integer yieldNum) {
		this.yieldNum = yieldNum;
	}
    
	/**
	 * get方法。产量上传次数
	 */
	public Integer getYieldTimes() {
		return this.yieldTimes;
	}

	/**
	 * set方法。产量上传次数
	 */
	public void setYieldTimes(Integer yieldTimes) {
		this.yieldTimes = yieldTimes;
	}
    

    //一对多关系中，多端数据列表

}







