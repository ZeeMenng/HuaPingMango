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
 * @updateDate 2018-6-13 19:07:57
 * @description 实体类MfSaleEcommerceCrawGenEnt，自动生成。电商数据表,采集
 */

public class MfSaleEcommerceCrawGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东，4：惠农网",allowableValues="0,1",hidden=false,required=false)
    private Byte ecommerceCode;
    @ApiModelProperty(value="平台号文本：文本，对应数据字典表（dictionary）中的文本字段（text）1：天猫，2：淘宝，3：京东，4：惠农网",hidden=false,required=false)
    private String ecommerceText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤",allowableValues="0,1",hidden=false,required=false)
    private Byte priceRangeCode;
    @ApiModelProperty(value="价格区间文本，对应数据字典表（dictionary）中的文本字段（text） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤",hidden=false,required=false)
    private String priceRangeText;
    @ApiModelProperty(value="原产区：对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private String productAreaCode;
    @ApiModelProperty(value="原产区文本，对应数据字典表（dictionary）中的文本字段（text）",hidden=false,required=false)
    private String productAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmount;
    @ApiModelProperty(value="销量单位，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte saleAmountCode;
    @ApiModelProperty(value="销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String saleAmountText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmountUnit;
    @ApiModelProperty(value="销区：对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private String saleAreaCode;
    @ApiModelProperty(value="销区：文本，对应数据字典表（dictionary）中的文本字段（text）",hidden=false,required=false)
    private String saleAreaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal salePrice;
    @ApiModelProperty(value="售价单位，对应数据字典表（dictionary）中的编码字段（code），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte salePriceCode;
    @ApiModelProperty(value="售价单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",hidden=false,required=false)
    private String salePriceText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal salePriceUnit;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleVolume;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleVolumeUnit;
    @ApiModelProperty(value="销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte saleVolumeUnitCode;
    @ApiModelProperty(value="销售额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String saleVolumeUnitText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物品种（鲜果）   0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物品种文本（鲜果）  0：全品种， 1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="单颗重量规格，对应数据字典表（dictionary）中的编码字段（code） 1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上",allowableValues="0,1",hidden=false,required=false)
    private Byte weightSpecificationCode;
    @ApiModelProperty(value="单颗重量规格文本，对应数据字典表（dictionary）中的文本字段（text）1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上",hidden=false,required=false)
    private String weightSpecificationText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。对应通用字段表id
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。对应通用字段表id
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
	 * get方法。对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果
	 */
	public String getCropTypeText() {
		return this.cropTypeText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果
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
	 * get方法。平台号文本：文本，对应数据字典表（dictionary）中的文本字段（text）1：天猫，2：淘宝，3：京东，4：惠农网
	 */
	public String getEcommerceText() {
		return this.ecommerceText;
	}

	/**
	 * set方法。平台号文本：文本，对应数据字典表（dictionary）中的文本字段（text）1：天猫，2：淘宝，3：京东，4：惠农网
	 */
	public void setEcommerceText(String ecommerceText) {
		this.ecommerceText = ecommerceText;
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
	 * get方法。价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤
	 */
	public Byte getPriceRangeCode() {
		return this.priceRangeCode;
	}

	/**
	 * set方法。价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤
	 */
	public void setPriceRangeCode(Byte priceRangeCode) {
		this.priceRangeCode = priceRangeCode;
	}
    
	/**
	 * get方法。价格区间文本，对应数据字典表（dictionary）中的文本字段（text） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤
	 */
	public String getPriceRangeText() {
		return this.priceRangeText;
	}

	/**
	 * set方法。价格区间文本，对应数据字典表（dictionary）中的文本字段（text） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤
	 */
	public void setPriceRangeText(String priceRangeText) {
		this.priceRangeText = priceRangeText;
	}
    
	/**
	 * get方法。原产区：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public String getProductAreaCode() {
		return this.productAreaCode;
	}

	/**
	 * set方法。原产区：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public void setProductAreaCode(String productAreaCode) {
		this.productAreaCode = productAreaCode;
	}
    
	/**
	 * get方法。原产区文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public String getProductAreaText() {
		return this.productAreaText;
	}

	/**
	 * set方法。原产区文本，对应数据字典表（dictionary）中的文本字段（text）
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
	 * get方法。销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getSaleAmountText() {
		return this.saleAmountText;
	}

	/**
	 * set方法。销量单位文本，:对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
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
	 * get方法。销区：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public String getSaleAreaCode() {
		return this.saleAreaCode;
	}

	/**
	 * set方法。销区：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public void setSaleAreaCode(String saleAreaCode) {
		this.saleAreaCode = saleAreaCode;
	}
    
	/**
	 * get方法。销区：文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public String getSaleAreaText() {
		return this.saleAreaText;
	}

	/**
	 * set方法。销区：文本，对应数据字典表（dictionary）中的文本字段（text）
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
	 * get方法。售价单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public String getSalePriceText() {
		return this.salePriceText;
	}

	/**
	 * set方法。售价单位文本，对应数据字典表（dictionary）中的文本字段（text），1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
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
	 * get方法。销售额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getSaleVolumeUnitText() {
		return this.saleVolumeUnitText;
	}

	/**
	 * set方法。销售额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setSaleVolumeUnitText(String saleVolumeUnitText) {
		this.saleVolumeUnitText = saleVolumeUnitText;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）作物品种（鲜果）   0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）作物品种（鲜果）   0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的文本字段（text）作物品种文本（鲜果）  0：全品种， 1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的文本字段（text）作物品种文本（鲜果）  0：全品种， 1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    
	/**
	 * get方法。单颗重量规格，对应数据字典表（dictionary）中的编码字段（code） 1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上
	 */
	public Byte getWeightSpecificationCode() {
		return this.weightSpecificationCode;
	}

	/**
	 * set方法。单颗重量规格，对应数据字典表（dictionary）中的编码字段（code） 1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上
	 */
	public void setWeightSpecificationCode(Byte weightSpecificationCode) {
		this.weightSpecificationCode = weightSpecificationCode;
	}
    
	/**
	 * get方法。单颗重量规格文本，对应数据字典表（dictionary）中的文本字段（text）1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上
	 */
	public String getWeightSpecificationText() {
		return this.weightSpecificationText;
	}

	/**
	 * set方法。单颗重量规格文本，对应数据字典表（dictionary）中的文本字段（text）1：1kg以下，2：1kg-2kg，3：2kg-3kg，4:3kg以上
	 */
	public void setWeightSpecificationText(String weightSpecificationText) {
		this.weightSpecificationText = weightSpecificationText;
	}
    

    //一对多关系中，多端数据列表

}







