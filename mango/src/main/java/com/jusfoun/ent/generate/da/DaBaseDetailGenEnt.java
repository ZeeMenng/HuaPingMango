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
 * @updateDate 2020/8/11 11:43:09
 * @description 实体类DaBaseDetailGenEnt，自动生成。基地细分表-地块表
 */

public class DaBaseDetailGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="地块面积",hidden=false,required=false)
    private String areaDetail;
    @ApiModelProperty(value="对应基地表code",allowableValues="0,1",hidden=false,required=false)
    private String baseCode;
    @ApiModelProperty(value="基地id",hidden=false,required=true)
    private String baseId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date createDate;
    @ApiModelProperty(value="创建者",hidden=false,required=false)
    private String creator;
    @ApiModelProperty(value="删除标志：0正常，1删除",hidden=false,required=true)
    private Byte delFlag;
    @ApiModelProperty(value="地块编号",allowableValues="0,1",hidden=false,required=false)
    private String detailCode;
    @ApiModelProperty(value="地块名称",hidden=false,required=false)
    private String detailName;
    @ApiModelProperty(value="备用字段",hidden=false,required=false)
    private String extra;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="产量",hidden=false,required=false)
    private String output;
    @ApiModelProperty(value="是否种植：0未种植，1已种植",hidden=false,required=true)
    private Byte planted;
    @ApiModelProperty(value="产品标识",hidden=false,required=false)
    private String productId;
    @ApiModelProperty(value="产品名称",hidden=false,required=false)
    private String productName;
    @ApiModelProperty(value="更新者",hidden=false,required=false)
    private String regenerator;
    @ApiModelProperty(value="更新时间",hidden=false,required=false)
    private Date updateDate;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。地块面积
	 */
	public String getAreaDetail() {
		return this.areaDetail;
	}

	/**
	 * set方法。地块面积
	 */
	public void setAreaDetail(String areaDetail) {
		this.areaDetail = areaDetail;
	}
    
	/**
	 * get方法。对应基地表code
	 */
	public String getBaseCode() {
		return this.baseCode;
	}

	/**
	 * set方法。对应基地表code
	 */
	public void setBaseCode(String baseCode) {
		this.baseCode = baseCode;
	}
    
	/**
	 * get方法。基地id
	 */
	public String getBaseId() {
		return this.baseId;
	}

	/**
	 * set方法。基地id
	 */
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
    
	/**
	 * get方法。
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。
	 */
	public void setCommonFieldId(String commonFieldId) {
		this.commonFieldId = commonFieldId;
	}
    
	/**
	 * get方法。创建时间
	 */
	public Date getCreateDate() {
		return this.createDate;
	}

	/**
	 * set方法。创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
	/**
	 * get方法。创建者
	 */
	public String getCreator() {
		return this.creator;
	}

	/**
	 * set方法。创建者
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}
    
	/**
	 * get方法。删除标志：0正常，1删除
	 */
	public Byte getDelFlag() {
		return this.delFlag;
	}

	/**
	 * set方法。删除标志：0正常，1删除
	 */
	public void setDelFlag(Byte delFlag) {
		this.delFlag = delFlag;
	}
    
	/**
	 * get方法。地块编号
	 */
	public String getDetailCode() {
		return this.detailCode;
	}

	/**
	 * set方法。地块编号
	 */
	public void setDetailCode(String detailCode) {
		this.detailCode = detailCode;
	}
    
	/**
	 * get方法。地块名称
	 */
	public String getDetailName() {
		return this.detailName;
	}

	/**
	 * set方法。地块名称
	 */
	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}
    
	/**
	 * get方法。备用字段
	 */
	public String getExtra() {
		return this.extra;
	}

	/**
	 * set方法。备用字段
	 */
	public void setExtra(String extra) {
		this.extra = extra;
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
	 * get方法。产量
	 */
	public String getOutput() {
		return this.output;
	}

	/**
	 * set方法。产量
	 */
	public void setOutput(String output) {
		this.output = output;
	}
    
	/**
	 * get方法。是否种植：0未种植，1已种植
	 */
	public Byte getPlanted() {
		return this.planted;
	}

	/**
	 * set方法。是否种植：0未种植，1已种植
	 */
	public void setPlanted(Byte planted) {
		this.planted = planted;
	}
    
	/**
	 * get方法。产品标识
	 */
	public String getProductId() {
		return this.productId;
	}

	/**
	 * set方法。产品标识
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
    
	/**
	 * get方法。产品名称
	 */
	public String getProductName() {
		return this.productName;
	}

	/**
	 * set方法。产品名称
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
    
	/**
	 * get方法。更新者
	 */
	public String getRegenerator() {
		return this.regenerator;
	}

	/**
	 * set方法。更新者
	 */
	public void setRegenerator(String regenerator) {
		this.regenerator = regenerator;
	}
    
	/**
	 * get方法。更新时间
	 */
	public Date getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * set方法。更新时间
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
    

    //一对多关系中，多端数据列表

}







