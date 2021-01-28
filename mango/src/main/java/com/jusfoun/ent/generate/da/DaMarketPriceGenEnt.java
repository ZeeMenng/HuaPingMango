package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:03
 * @description 实体类DaMarketPriceGenEnt，自动生成。市场价格数据表
 */

public class DaMarketPriceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="亩成本价",hidden=false,required=false)
    private BigDecimal acreCostPrice;
    @ApiModelProperty(value="通用价格，单位：元/千克",hidden=false,required=false)
    private BigDecimal acreCostPriceUnit;
    @ApiModelProperty(value="亩成本价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte acreCostPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String acreCostPriceUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal averagePrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal averagePriceUnit;
    @ApiModelProperty(value="平均价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte averagePriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String averagePriceUnitText;
    @ApiModelProperty(value="芒果平均单颗重（g）",hidden=false,required=false)
    private BigDecimal averageWeight;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal bottomPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal bottomPriceUnit;
    @ApiModelProperty(value="最低价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte bottomPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String bottomPriceUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="电商价格",hidden=false,required=false)
    private BigDecimal eCommercePrice;
    @ApiModelProperty(value="通用价格，单位：元/千克",hidden=false,required=false)
    private BigDecimal eCommercePriceUnit;
    @ApiModelProperty(value="电商价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte eCommercePriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String eCommercePriceUnitText;
    @ApiModelProperty(value="田头价格",hidden=false,required=false)
    private BigDecimal fieldPrice;
    @ApiModelProperty(value="通用价格，单位：元/千克",hidden=false,required=false)
    private BigDecimal fieldPriceUnit;
    @ApiModelProperty(value="田头价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte fieldPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String fieldPriceUnitText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String mangoGrade;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="价格",hidden=false,required=false)
    private BigDecimal perPrice;
    @ApiModelProperty(value="通用价格，单位：元/千克",hidden=false,required=false)
    private BigDecimal perPriceUnit;
    @ApiModelProperty(value="成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte perPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String perPriceUnitText;
    @ApiModelProperty(value="价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤",allowableValues="0,1",hidden=false,required=false)
    private Byte priceRangeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String priceRangeText;
    @ApiModelProperty(value="价格类型:对应数据字典表（dictionary）中的编码字段（code）1：田头价，2：批发价，3:零售价",allowableValues="0,1",hidden=false,required=false)
    private Byte priceTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String priceTypeText;
    @ApiModelProperty(value="零售价格",hidden=false,required=false)
    private BigDecimal retailPrice;
    @ApiModelProperty(value="通用价格，单位：元/千克",hidden=false,required=false)
    private BigDecimal retailPriceUnit;
    @ApiModelProperty(value="零售价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte retailPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String retailPriceUnitText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal topPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal topPriceUnit;
    @ApiModelProperty(value="最高价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte topPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String topPriceUnitText;
    @ApiModelProperty(value="批发价格",hidden=false,required=false)
    private BigDecimal tradePrice;
    @ApiModelProperty(value="通用价格，单位：元/千克",hidden=false,required=false)
    private BigDecimal tradePriceUnit;
    @ApiModelProperty(value="批发价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte tradePriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tradePriceUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String year;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。亩成本价
	 */
	public BigDecimal getAcreCostPrice() {
		return this.acreCostPrice;
	}

	/**
	 * set方法。亩成本价
	 */
	public void setAcreCostPrice(BigDecimal acreCostPrice) {
		this.acreCostPrice = acreCostPrice;
	}
    
	/**
	 * get方法。通用价格，单位：元/千克
	 */
	public BigDecimal getAcreCostPriceUnit() {
		return this.acreCostPriceUnit;
	}

	/**
	 * set方法。通用价格，单位：元/千克
	 */
	public void setAcreCostPriceUnit(BigDecimal acreCostPriceUnit) {
		this.acreCostPriceUnit = acreCostPriceUnit;
	}
    
	/**
	 * get方法。亩成本价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getAcreCostPriceUnitCode() {
		return this.acreCostPriceUnitCode;
	}

	/**
	 * set方法。亩成本价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setAcreCostPriceUnitCode(Byte acreCostPriceUnitCode) {
		this.acreCostPriceUnitCode = acreCostPriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getAcreCostPriceUnitText() {
		return this.acreCostPriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setAcreCostPriceUnitText(String acreCostPriceUnitText) {
		this.acreCostPriceUnitText = acreCostPriceUnitText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getAveragePrice() {
		return this.averagePrice;
	}

	/**
	 * set方法。
	 */
	public void setAveragePrice(BigDecimal averagePrice) {
		this.averagePrice = averagePrice;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getAveragePriceUnit() {
		return this.averagePriceUnit;
	}

	/**
	 * set方法。
	 */
	public void setAveragePriceUnit(BigDecimal averagePriceUnit) {
		this.averagePriceUnit = averagePriceUnit;
	}
    
	/**
	 * get方法。平均价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getAveragePriceUnitCode() {
		return this.averagePriceUnitCode;
	}

	/**
	 * set方法。平均价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setAveragePriceUnitCode(Byte averagePriceUnitCode) {
		this.averagePriceUnitCode = averagePriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getAveragePriceUnitText() {
		return this.averagePriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setAveragePriceUnitText(String averagePriceUnitText) {
		this.averagePriceUnitText = averagePriceUnitText;
	}
    
	/**
	 * get方法。芒果平均单颗重（g）
	 */
	public BigDecimal getAverageWeight() {
		return this.averageWeight;
	}

	/**
	 * set方法。芒果平均单颗重（g）
	 */
	public void setAverageWeight(BigDecimal averageWeight) {
		this.averageWeight = averageWeight;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getBottomPrice() {
		return this.bottomPrice;
	}

	/**
	 * set方法。
	 */
	public void setBottomPrice(BigDecimal bottomPrice) {
		this.bottomPrice = bottomPrice;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getBottomPriceUnit() {
		return this.bottomPriceUnit;
	}

	/**
	 * set方法。
	 */
	public void setBottomPriceUnit(BigDecimal bottomPriceUnit) {
		this.bottomPriceUnit = bottomPriceUnit;
	}
    
	/**
	 * get方法。最低价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getBottomPriceUnitCode() {
		return this.bottomPriceUnitCode;
	}

	/**
	 * set方法。最低价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setBottomPriceUnitCode(Byte bottomPriceUnitCode) {
		this.bottomPriceUnitCode = bottomPriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getBottomPriceUnitText() {
		return this.bottomPriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setBottomPriceUnitText(String bottomPriceUnitText) {
		this.bottomPriceUnitText = bottomPriceUnitText;
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
	 * get方法。电商价格
	 */
	public BigDecimal getECommercePrice() {
		return this.eCommercePrice;
	}

	/**
	 * set方法。电商价格
	 */
	public void setECommercePrice(BigDecimal eCommercePrice) {
		this.eCommercePrice = eCommercePrice;
	}
    
	/**
	 * get方法。通用价格，单位：元/千克
	 */
	public BigDecimal getECommercePriceUnit() {
		return this.eCommercePriceUnit;
	}

	/**
	 * set方法。通用价格，单位：元/千克
	 */
	public void setECommercePriceUnit(BigDecimal eCommercePriceUnit) {
		this.eCommercePriceUnit = eCommercePriceUnit;
	}
    
	/**
	 * get方法。电商价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getECommercePriceUnitCode() {
		return this.eCommercePriceUnitCode;
	}

	/**
	 * set方法。电商价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setECommercePriceUnitCode(Byte eCommercePriceUnitCode) {
		this.eCommercePriceUnitCode = eCommercePriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getECommercePriceUnitText() {
		return this.eCommercePriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setECommercePriceUnitText(String eCommercePriceUnitText) {
		this.eCommercePriceUnitText = eCommercePriceUnitText;
	}
    
	/**
	 * get方法。田头价格
	 */
	public BigDecimal getFieldPrice() {
		return this.fieldPrice;
	}

	/**
	 * set方法。田头价格
	 */
	public void setFieldPrice(BigDecimal fieldPrice) {
		this.fieldPrice = fieldPrice;
	}
    
	/**
	 * get方法。通用价格，单位：元/千克
	 */
	public BigDecimal getFieldPriceUnit() {
		return this.fieldPriceUnit;
	}

	/**
	 * set方法。通用价格，单位：元/千克
	 */
	public void setFieldPriceUnit(BigDecimal fieldPriceUnit) {
		this.fieldPriceUnit = fieldPriceUnit;
	}
    
	/**
	 * get方法。田头价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getFieldPriceUnitCode() {
		return this.fieldPriceUnitCode;
	}

	/**
	 * set方法。田头价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setFieldPriceUnitCode(Byte fieldPriceUnitCode) {
		this.fieldPriceUnitCode = fieldPriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getFieldPriceUnitText() {
		return this.fieldPriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setFieldPriceUnitText(String fieldPriceUnitText) {
		this.fieldPriceUnitText = fieldPriceUnitText;
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
	public String getMangoGrade() {
		return this.mangoGrade;
	}

	/**
	 * set方法。
	 */
	public void setMangoGrade(String mangoGrade) {
		this.mangoGrade = mangoGrade;
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
	 * get方法。价格
	 */
	public BigDecimal getPerPrice() {
		return this.perPrice;
	}

	/**
	 * set方法。价格
	 */
	public void setPerPrice(BigDecimal perPrice) {
		this.perPrice = perPrice;
	}
    
	/**
	 * get方法。通用价格，单位：元/千克
	 */
	public BigDecimal getPerPriceUnit() {
		return this.perPriceUnit;
	}

	/**
	 * set方法。通用价格，单位：元/千克
	 */
	public void setPerPriceUnit(BigDecimal perPriceUnit) {
		this.perPriceUnit = perPriceUnit;
	}
    
	/**
	 * get方法。成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getPerPriceUnitCode() {
		return this.perPriceUnitCode;
	}

	/**
	 * set方法。成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
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
	 * get方法。价格类型:对应数据字典表（dictionary）中的编码字段（code）1：田头价，2：批发价，3:零售价
	 */
	public Byte getPriceTypeCode() {
		return this.priceTypeCode;
	}

	/**
	 * set方法。价格类型:对应数据字典表（dictionary）中的编码字段（code）1：田头价，2：批发价，3:零售价
	 */
	public void setPriceTypeCode(Byte priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getPriceTypeText() {
		return this.priceTypeText;
	}

	/**
	 * set方法。
	 */
	public void setPriceTypeText(String priceTypeText) {
		this.priceTypeText = priceTypeText;
	}
    
	/**
	 * get方法。零售价格
	 */
	public BigDecimal getRetailPrice() {
		return this.retailPrice;
	}

	/**
	 * set方法。零售价格
	 */
	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}
    
	/**
	 * get方法。通用价格，单位：元/千克
	 */
	public BigDecimal getRetailPriceUnit() {
		return this.retailPriceUnit;
	}

	/**
	 * set方法。通用价格，单位：元/千克
	 */
	public void setRetailPriceUnit(BigDecimal retailPriceUnit) {
		this.retailPriceUnit = retailPriceUnit;
	}
    
	/**
	 * get方法。零售价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getRetailPriceUnitCode() {
		return this.retailPriceUnitCode;
	}

	/**
	 * set方法。零售价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setRetailPriceUnitCode(Byte retailPriceUnitCode) {
		this.retailPriceUnitCode = retailPriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRetailPriceUnitText() {
		return this.retailPriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setRetailPriceUnitText(String retailPriceUnitText) {
		this.retailPriceUnitText = retailPriceUnitText;
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
	public BigDecimal getTopPrice() {
		return this.topPrice;
	}

	/**
	 * set方法。
	 */
	public void setTopPrice(BigDecimal topPrice) {
		this.topPrice = topPrice;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getTopPriceUnit() {
		return this.topPriceUnit;
	}

	/**
	 * set方法。
	 */
	public void setTopPriceUnit(BigDecimal topPriceUnit) {
		this.topPriceUnit = topPriceUnit;
	}
    
	/**
	 * get方法。最高价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getTopPriceUnitCode() {
		return this.topPriceUnitCode;
	}

	/**
	 * set方法。最高价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setTopPriceUnitCode(Byte topPriceUnitCode) {
		this.topPriceUnitCode = topPriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getTopPriceUnitText() {
		return this.topPriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setTopPriceUnitText(String topPriceUnitText) {
		this.topPriceUnitText = topPriceUnitText;
	}
    
	/**
	 * get方法。批发价格
	 */
	public BigDecimal getTradePrice() {
		return this.tradePrice;
	}

	/**
	 * set方法。批发价格
	 */
	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}
    
	/**
	 * get方法。通用价格，单位：元/千克
	 */
	public BigDecimal getTradePriceUnit() {
		return this.tradePriceUnit;
	}

	/**
	 * set方法。通用价格，单位：元/千克
	 */
	public void setTradePriceUnit(BigDecimal tradePriceUnit) {
		this.tradePriceUnit = tradePriceUnit;
	}
    
	/**
	 * get方法。批发价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public Byte getTradePriceUnitCode() {
		return this.tradePriceUnitCode;
	}

	/**
	 * set方法。批发价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setTradePriceUnitCode(Byte tradePriceUnitCode) {
		this.tradePriceUnitCode = tradePriceUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getTradePriceUnitText() {
		return this.tradePriceUnitText;
	}

	/**
	 * set方法。
	 */
	public void setTradePriceUnitText(String tradePriceUnitText) {
		this.tradePriceUnitText = tradePriceUnitText;
	}
    
	/**
	 * get方法。
	 */
	public String getYear() {
		return this.year;
	}

	/**
	 * set方法。
	 */
	public void setYear(String year) {
		this.year = year;
	}
    










}







