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
 * @updateDate 2020/8/11 11:43:12
 * @description 实体类DaCrawlerGenEnt，自动生成。爬虫
 */

public class DaCrawlerGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createdTime;
    @ApiModelProperty(value="发货地",hidden=false,required=false)
    private String from;
    @ApiModelProperty(value="商品主键",hidden=false,required=false)
    private String goodsId;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="库存",hidden=false,required=false)
    private String inventory;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="参数",hidden=false,required=false)
    private String parameter;
    @ApiModelProperty(value="价格",hidden=false,required=false)
    private BigDecimal price;
    @ApiModelProperty(value="促销价格",hidden=false,required=false)
    private BigDecimal promoPrice;
    @ApiModelProperty(value="累计评论",hidden=false,required=false)
    private String rateCounter;
    @ApiModelProperty(value="累计销售",hidden=false,required=false)
    private String sellCounter;
    @ApiModelProperty(value="数据来源：'平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网'",hidden=false,required=false)
    private Byte source;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。创建时间
	 */
	public Date getCreatedTime() {
		return this.createdTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
    
	/**
	 * get方法。发货地
	 */
	public String getFrom() {
		return this.from;
	}

	/**
	 * set方法。发货地
	 */
	public void setFrom(String from) {
		this.from = from;
	}
    
	/**
	 * get方法。商品主键
	 */
	public String getGoodsId() {
		return this.goodsId;
	}

	/**
	 * set方法。商品主键
	 */
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
    
	/**
	 * get方法。主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。库存
	 */
	public String getInventory() {
		return this.inventory;
	}

	/**
	 * set方法。库存
	 */
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
    
	/**
	 * get方法。名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。参数
	 */
	public String getParameter() {
		return this.parameter;
	}

	/**
	 * set方法。参数
	 */
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
    
	/**
	 * get方法。价格
	 */
	public BigDecimal getPrice() {
		return this.price;
	}

	/**
	 * set方法。价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
    
	/**
	 * get方法。促销价格
	 */
	public BigDecimal getPromoPrice() {
		return this.promoPrice;
	}

	/**
	 * set方法。促销价格
	 */
	public void setPromoPrice(BigDecimal promoPrice) {
		this.promoPrice = promoPrice;
	}
    
	/**
	 * get方法。累计评论
	 */
	public String getRateCounter() {
		return this.rateCounter;
	}

	/**
	 * set方法。累计评论
	 */
	public void setRateCounter(String rateCounter) {
		this.rateCounter = rateCounter;
	}
    
	/**
	 * get方法。累计销售
	 */
	public String getSellCounter() {
		return this.sellCounter;
	}

	/**
	 * set方法。累计销售
	 */
	public void setSellCounter(String sellCounter) {
		this.sellCounter = sellCounter;
	}
    
	/**
	 * get方法。数据来源：'平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网'
	 */
	public Byte getSource() {
		return this.source;
	}

	/**
	 * set方法。数据来源：'平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网'
	 */
	public void setSource(Byte source) {
		this.source = source;
	}
    

    //一对多关系中，多端数据列表

}







