package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:18
 * @description 实体类PiProductRecommendGenEnt，自动生成。企业推介产品表
 */

public class PiProductRecommendGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="数据入库时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="产品品牌",hidden=false,required=false)
    private String brand;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否推荐,0:否，1：是",hidden=false,required=true)
    private Byte isRecommend;
    @ApiModelProperty(value="电商链接",hidden=false,required=false)
    private String mallInterlinkage;
    @ApiModelProperty(value="产品名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="单价，标准单位：元/千克",hidden=false,required=false)
    private BigDecimal perPriceUnit;
    @ApiModelProperty(value="发布时间",hidden=false,required=false)
    private Date releaseTime;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="售价，标准单位：元",hidden=false,required=false)
    private BigDecimal sellingPrice;
    @ApiModelProperty(value="产品类型：对应数据字典表（dictionary）中的编码字段（code） 1：鲜果，2，加工品",allowableValues="0,1",hidden=false,required=false)
    private Byte typeCode;
    @ApiModelProperty(value="产品类型：对应数据字典表（dictionary）中的编码字段（code） 1：鲜果，2，加工品",hidden=false,required=false)
    private String typeText;
    @ApiModelProperty(value="产品重量，标准单位：千克",hidden=false,required=false)
    private BigDecimal weight;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。数据入库时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。数据入库时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。产品品牌
	 */
	public String getBrand() {
		return this.brand;
	}

	/**
	 * set方法。产品品牌
	 */
	public void setBrand(String brand) {
		this.brand = brand;
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
	 * get方法。是否推荐,0:否，1：是
	 */
	public Byte getIsRecommend() {
		return this.isRecommend;
	}

	/**
	 * set方法。是否推荐,0:否，1：是
	 */
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}
    
	/**
	 * get方法。电商链接
	 */
	public String getMallInterlinkage() {
		return this.mallInterlinkage;
	}

	/**
	 * set方法。电商链接
	 */
	public void setMallInterlinkage(String mallInterlinkage) {
		this.mallInterlinkage = mallInterlinkage;
	}
    
	/**
	 * get方法。产品名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。产品名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。单价，标准单位：元/千克
	 */
	public BigDecimal getPerPriceUnit() {
		return this.perPriceUnit;
	}

	/**
	 * set方法。单价，标准单位：元/千克
	 */
	public void setPerPriceUnit(BigDecimal perPriceUnit) {
		this.perPriceUnit = perPriceUnit;
	}
    
	/**
	 * get方法。发布时间
	 */
	public Date getReleaseTime() {
		return this.releaseTime;
	}

	/**
	 * set方法。发布时间
	 */
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
    
	/**
	 * get方法。备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。售价，标准单位：元
	 */
	public BigDecimal getSellingPrice() {
		return this.sellingPrice;
	}

	/**
	 * set方法。售价，标准单位：元
	 */
	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}
    
	/**
	 * get方法。产品类型：对应数据字典表（dictionary）中的编码字段（code） 1：鲜果，2，加工品
	 */
	public Byte getTypeCode() {
		return this.typeCode;
	}

	/**
	 * set方法。产品类型：对应数据字典表（dictionary）中的编码字段（code） 1：鲜果，2，加工品
	 */
	public void setTypeCode(Byte typeCode) {
		this.typeCode = typeCode;
	}
    
	/**
	 * get方法。产品类型：对应数据字典表（dictionary）中的编码字段（code） 1：鲜果，2，加工品
	 */
	public String getTypeText() {
		return this.typeText;
	}

	/**
	 * set方法。产品类型：对应数据字典表（dictionary）中的编码字段（code） 1：鲜果，2，加工品
	 */
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}
    
	/**
	 * get方法。产品重量，标准单位：千克
	 */
	public BigDecimal getWeight() {
		return this.weight;
	}

	/**
	 * set方法。产品重量，标准单位：千克
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
    

    //一对多关系中，多端数据列表

}







