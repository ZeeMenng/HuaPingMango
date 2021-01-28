package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:40
 * @description 实体类MfSaleEcommerceCrawGenEnt，自动生成。电商数据表,采集
 */

public class MfSaleEcommerceCrawGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网",allowableValues="0,1",hidden=false,required=false)
    private Byte ecommerceCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String ecommerceText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/公斤，2：5-10元/公斤，3：10-15元/公斤，4：15-20元/公斤，5：20元以上/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte priceRangeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String priceRangeText;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String productAreaCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String productAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmount;
    @ApiModelProperty(value="销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte saleAmountCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String saleAmountText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmountUnit;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String saleAreaCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String saleAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal salePrice;
    @ApiModelProperty(value="售价单位，对应数据字典表（dictionary）中的编码字段（code），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte salePriceCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String salePriceText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal salePriceUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleVolume;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleVolumeUnit;
    @ApiModelProperty(value="销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte saleVolumeUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String saleVolumeUnitText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="重量规格，对应数据字典表（dictionary）中的编码字段（code） 1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上",allowableValues="0,1",hidden=false,required=false)
    private Byte weightSpecificationCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String weightSpecificationText;

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
	 * get方法。平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网
	 */
	public Byte getEcommerceCode() {
		return this.ecommerceCode;
	}

	/**
	 * set方法。平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网
	 */
	public void setEcommerceCode(Byte ecommerceCode) {
		this.ecommerceCode = ecommerceCode;
	}
    
	/**
	 * get方法。
	 */
	public String getEcommerceText() {
		return this.ecommerceText;
	}

	/**
	 * set方法。
	 */
	public void setEcommerceText(String ecommerceText) {
		this.ecommerceText = ecommerceText;
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
	 * get方法。价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/公斤，2：5-10元/公斤，3：10-15元/公斤，4：15-20元/公斤，5：20元以上/公斤
	 */
	public Byte getPriceRangeCode() {
		return this.priceRangeCode;
	}

	/**
	 * set方法。价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/公斤，2：5-10元/公斤，3：10-15元/公斤，4：15-20元/公斤，5：20元以上/公斤
	 */
	public void setPriceRangeCode(Byte priceRangeCode) {
		this.priceRangeCode = priceRangeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getPriceRangeText() {
		return this.priceRangeText;
	}

	/**
	 * set方法。
	 */
	public void setPriceRangeText(String priceRangeText) {
		this.priceRangeText = priceRangeText;
	}
    
	/**
	 * get方法。
	 */
	public String getProductAreaCode() {
		return this.productAreaCode;
	}

	/**
	 * set方法。
	 */
	public void setProductAreaCode(String productAreaCode) {
		this.productAreaCode = productAreaCode;
	}
    
	/**
	 * get方法。
	 */
	public String getProductAreaText() {
		return this.productAreaText;
	}

	/**
	 * set方法。
	 */
	public void setProductAreaText(String productAreaText) {
		this.productAreaText = productAreaText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getSaleAmount() {
		return this.saleAmount;
	}

	/**
	 * set方法。
	 */
	public void setSaleAmount(BigDecimal saleAmount) {
		this.saleAmount = saleAmount;
	}
    
	/**
	 * get方法。销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getSaleAmountCode() {
		return this.saleAmountCode;
	}

	/**
	 * set方法。销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setSaleAmountCode(Byte saleAmountCode) {
		this.saleAmountCode = saleAmountCode;
	}
    
	/**
	 * get方法。
	 */
	public String getSaleAmountText() {
		return this.saleAmountText;
	}

	/**
	 * set方法。
	 */
	public void setSaleAmountText(String saleAmountText) {
		this.saleAmountText = saleAmountText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getSaleAmountUnit() {
		return this.saleAmountUnit;
	}

	/**
	 * set方法。
	 */
	public void setSaleAmountUnit(BigDecimal saleAmountUnit) {
		this.saleAmountUnit = saleAmountUnit;
	}
    
	/**
	 * get方法。
	 */
	public String getSaleAreaCode() {
		return this.saleAreaCode;
	}

	/**
	 * set方法。
	 */
	public void setSaleAreaCode(String saleAreaCode) {
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
	 * get方法。
	 */
	public BigDecimal getSalePrice() {
		return this.salePrice;
	}

	/**
	 * set方法。
	 */
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
    
	/**
	 * get方法。售价单位，对应数据字典表（dictionary）中的编码字段（code），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getSalePriceCode() {
		return this.salePriceCode;
	}

	/**
	 * set方法。售价单位，对应数据字典表（dictionary）中的编码字段（code），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setSalePriceCode(Byte salePriceCode) {
		this.salePriceCode = salePriceCode;
	}
    
	/**
	 * get方法。
	 */
	public String getSalePriceText() {
		return this.salePriceText;
	}

	/**
	 * set方法。
	 */
	public void setSalePriceText(String salePriceText) {
		this.salePriceText = salePriceText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getSalePriceUnit() {
		return this.salePriceUnit;
	}

	/**
	 * set方法。
	 */
	public void setSalePriceUnit(BigDecimal salePriceUnit) {
		this.salePriceUnit = salePriceUnit;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getSaleVolume() {
		return this.saleVolume;
	}

	/**
	 * set方法。
	 */
	public void setSaleVolume(BigDecimal saleVolume) {
		this.saleVolume = saleVolume;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getSaleVolumeUnit() {
		return this.saleVolumeUnit;
	}

	/**
	 * set方法。
	 */
	public void setSaleVolumeUnit(BigDecimal saleVolumeUnit) {
		this.saleVolumeUnit = saleVolumeUnit;
	}
    
	/**
	 * get方法。销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getSaleVolumeUnitCode() {
		return this.saleVolumeUnitCode;
	}

	/**
	 * set方法。销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setSaleVolumeUnitCode(Byte saleVolumeUnitCode) {
		this.saleVolumeUnitCode = saleVolumeUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getSaleVolumeUnitText() {
		return this.saleVolumeUnitText;
	}

	/**
	 * set方法。
	 */
	public void setSaleVolumeUnitText(String saleVolumeUnitText) {
		this.saleVolumeUnitText = saleVolumeUnitText;
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
	 * get方法。重量规格，对应数据字典表（dictionary）中的编码字段（code） 1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上
	 */
	public Byte getWeightSpecificationCode() {
		return this.weightSpecificationCode;
	}

	/**
	 * set方法。重量规格，对应数据字典表（dictionary）中的编码字段（code） 1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上
	 */
	public void setWeightSpecificationCode(Byte weightSpecificationCode) {
		this.weightSpecificationCode = weightSpecificationCode;
	}
    
	/**
	 * get方法。
	 */
	public String getWeightSpecificationText() {
		return this.weightSpecificationText;
	}

	/**
	 * set方法。
	 */
	public void setWeightSpecificationText(String weightSpecificationText) {
		this.weightSpecificationText = weightSpecificationText;
	}
    










}







