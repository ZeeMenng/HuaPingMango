package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:07
 * @description 实体类DaSalePeasantGenEnt，自动生成。农户销售数据表
 */

public class DaSalePeasantGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal perPriceUnit;
    @ApiModelProperty(value="成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte perPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String perPriceUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String sale;
    @ApiModelProperty(value="销地",allowableValues="0,1",hidden=false,required=false)
    private Byte saleAreaCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String saleAreaText;
    @ApiModelProperty(value="销区",allowableValues="0,1",hidden=false,required=false)
    private Byte saleRegionCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String saleRegionText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal tradeAmount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal tradeAmountUnit;
    @ApiModelProperty(value="交易量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte tradeAmountUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tradeAmountUnitText;
    @ApiModelProperty(value="交易日期",hidden=false,required=false)
    private Date tradeTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal tradeVolume;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal tradeVolumeUnit;
    @ApiModelProperty(value="交易额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte tradeVolumeUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tradeVolumeUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal unitPrice;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果
	 */
	public Byte getCropTypeCode() {
		return this.cropTypeCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果
	 */
	public void setCropTypeCode(Byte cropTypeCode) {
		this.cropTypeCode = cropTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCropTypeText() {
		return this.cropTypeText;
	}

	/**
	 * set方法。
	 */
	public void setCropTypeText(String cropTypeText) {
		this.cropTypeText = cropTypeText;
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
	 * get方法。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getPerPriceUnit() {
		return this.perPriceUnit;
	}

	/**
	 * set方法。
	 */
	public void setPerPriceUnit(BigDecimal perPriceUnit) {
		this.perPriceUnit = perPriceUnit;
	}
    
	/**
	 * get方法。成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getPerPriceUnitCode() {
		return this.perPriceUnitCode;
	}

	/**
	 * set方法。成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setPerPriceUnitCode(Byte perPriceUnitCode) {
		this.perPriceUnitCode = perPriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getPerPriceUnitText() {
		return this.perPriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setPerPriceUnitText(String perPriceUnitText) {
		this.perPriceUnitText = perPriceUnitText;
	}
    
	/**
	 * get方法。
	 */
	public String getSale() {
		return this.sale;
	}

	/**
	 * set方法。
	 */
	public void setSale(String sale) {
		this.sale = sale;
	}
    
	/**
	 * get方法。销地
	 */
	public Byte getSaleAreaCode() {
		return this.saleAreaCode;
	}

	/**
	 * set方法。销地
	 */
	public void setSaleAreaCode(Byte saleAreaCode) {
		this.saleAreaCode = saleAreaCode;
	}
    
	/**
	 * get方法。
	 */
	public String getSaleAreaText() {
		return this.saleAreaText;
	}

	/**
	 * set方法。
	 */
	public void setSaleAreaText(String saleAreaText) {
		this.saleAreaText = saleAreaText;
	}
    
	/**
	 * get方法。销区
	 */
	public Byte getSaleRegionCode() {
		return this.saleRegionCode;
	}

	/**
	 * set方法。销区
	 */
	public void setSaleRegionCode(Byte saleRegionCode) {
		this.saleRegionCode = saleRegionCode;
	}
    
	/**
	 * get方法。
	 */
	public String getSaleRegionText() {
		return this.saleRegionText;
	}

	/**
	 * set方法。
	 */
	public void setSaleRegionText(String saleRegionText) {
		this.saleRegionText = saleRegionText;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的作物品种
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTradeAmount() {
		return this.tradeAmount;
	}

	/**
	 * set方法。
	 */
	public void setTradeAmount(BigDecimal tradeAmount) {
		this.tradeAmount = tradeAmount;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTradeAmountUnit() {
		return this.tradeAmountUnit;
	}

	/**
	 * set方法。
	 */
	public void setTradeAmountUnit(BigDecimal tradeAmountUnit) {
		this.tradeAmountUnit = tradeAmountUnit;
	}
    
	/**
	 * get方法。交易量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getTradeAmountUnitCode() {
		return this.tradeAmountUnitCode;
	}

	/**
	 * set方法。交易量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setTradeAmountUnitCode(Byte tradeAmountUnitCode) {
		this.tradeAmountUnitCode = tradeAmountUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getTradeAmountUnitText() {
		return this.tradeAmountUnitText;
	}

	/**
	 * set方法。
	 */
	public void setTradeAmountUnitText(String tradeAmountUnitText) {
		this.tradeAmountUnitText = tradeAmountUnitText;
	}
    
	/**
	 * get方法。交易日期
	 */
	public Date getTradeTime() {
		return this.tradeTime;
	}

	/**
	 * set方法。交易日期
	 */
	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTradeVolume() {
		return this.tradeVolume;
	}

	/**
	 * set方法。
	 */
	public void setTradeVolume(BigDecimal tradeVolume) {
		this.tradeVolume = tradeVolume;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTradeVolumeUnit() {
		return this.tradeVolumeUnit;
	}

	/**
	 * set方法。
	 */
	public void setTradeVolumeUnit(BigDecimal tradeVolumeUnit) {
		this.tradeVolumeUnit = tradeVolumeUnit;
	}
    
	/**
	 * get方法。交易额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getTradeVolumeUnitCode() {
		return this.tradeVolumeUnitCode;
	}

	/**
	 * set方法。交易额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setTradeVolumeUnitCode(Byte tradeVolumeUnitCode) {
		this.tradeVolumeUnitCode = tradeVolumeUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getTradeVolumeUnitText() {
		return this.tradeVolumeUnitText;
	}

	/**
	 * set方法。
	 */
	public void setTradeVolumeUnitText(String tradeVolumeUnitText) {
		this.tradeVolumeUnitText = tradeVolumeUnitText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getUnitPrice() {
		return this.unitPrice;
	}

	/**
	 * set方法。
	 */
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
    










}







