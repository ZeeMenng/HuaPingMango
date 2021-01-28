package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.zee.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:56
 * @description 实体类MfMarketPriceCrawGenEnt，自动生成。市场价格数据表（采集）
 */

public class MfMarketPriceCrawGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal averagePrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal averagePriceUnit;
    @ApiModelProperty(value="平均价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte averagePriceUnitCode;
    @ApiModelProperty(value="平均价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",hidden=false,required=false)
    private String averagePriceUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal bottomPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal bottomPriceUnit;
    @ApiModelProperty(value="最低价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte bottomPriceUnitCode;
    @ApiModelProperty(value="最低价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",hidden=false,required=false)
    private String bottomPriceUnitText;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称（可存放批发市场名称）",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal perPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal perPriceUnit;
    @ApiModelProperty(value="成交单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte perPriceUnitCode;
    @ApiModelProperty(value="成交单价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",hidden=false,required=false)
    private String perPriceUnitText;
    @ApiModelProperty(value="价格区间，对应数据字典表（dictionary）中的编码字段（code） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤",allowableValues="0,1",hidden=false,required=false)
    private Byte priceRangeCode;
    @ApiModelProperty(value="价格区间文本，对应数据字典表（dictionary）中的文本字段（text） 1：5元以下/斤，2：5-10元/斤，3：10-15元/斤，4：15-20元/斤，5：20元以上/斤",hidden=false,required=false)
    private String priceRangeText;
    @ApiModelProperty(value="价格类型:对应数据字典表（dictionary）中的编码字段（code）1：田头价，2：批发价，3：市场价，4：出口价:，5：进口价，6：电商价，7：销售价",allowableValues="0,1",hidden=false,required=false)
    private Byte priceTypeCode;
    @ApiModelProperty(value="价格类型文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：田头价，2：批发价，3：市场价，4：出口价:，5：进口价，6：电商价，7：销售价",hidden=false,required=false)
    private String priceTypeText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物品种（鲜果）  0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物品种文本（鲜果）  0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal topPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal topPriceUnit;
    @ApiModelProperty(value="最高价单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte topPriceUnitCode;
    @ApiModelProperty(value="最高价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤",hidden=false,required=false)
    private String topPriceUnitText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。平均价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public String getAveragePriceUnitText() {
		return this.averagePriceUnitText;
	}

	/**
	 * set方法。平均价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setAveragePriceUnitText(String averagePriceUnitText) {
		this.averagePriceUnitText = averagePriceUnitText;
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
	 * get方法。最低价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public String getBottomPriceUnitText() {
		return this.bottomPriceUnitText;
	}

	/**
	 * set方法。最低价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setBottomPriceUnitText(String bottomPriceUnitText) {
		this.bottomPriceUnitText = bottomPriceUnitText;
	}
    
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
	 * get方法。名称（可存放批发市场名称）
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。名称（可存放批发市场名称）
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getPerPrice() {
		return this.perPrice;
	}

	/**
	 * set方法。
	 */
	public void setPerPrice(BigDecimal perPrice) {
		this.perPrice = perPrice;
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
	 * get方法。成交单价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public String getPerPriceUnitText() {
		return this.perPriceUnitText;
	}

	/**
	 * set方法。成交单价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
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
	 * get方法。价格类型:对应数据字典表（dictionary）中的编码字段（code）1：田头价，2：批发价，3：市场价，4：出口价:，5：进口价，6：电商价，7：销售价
	 */
	public Byte getPriceTypeCode() {
		return this.priceTypeCode;
	}

	/**
	 * set方法。价格类型:对应数据字典表（dictionary）中的编码字段（code）1：田头价，2：批发价，3：市场价，4：出口价:，5：进口价，6：电商价，7：销售价
	 */
	public void setPriceTypeCode(Byte priceTypeCode) {
		this.priceTypeCode = priceTypeCode;
	}
    
	/**
	 * get方法。价格类型文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：田头价，2：批发价，3：市场价，4：出口价:，5：进口价，6：电商价，7：销售价
	 */
	public String getPriceTypeText() {
		return this.priceTypeText;
	}

	/**
	 * set方法。价格类型文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：田头价，2：批发价，3：市场价，4：出口价:，5：进口价，6：电商价，7：销售价
	 */
	public void setPriceTypeText(String priceTypeText) {
		this.priceTypeText = priceTypeText;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）作物品种（鲜果）  0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）作物品种（鲜果）  0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的文本字段（text）作物品种文本（鲜果）  0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的文本字段（text）作物品种文本（鲜果）  0：全品种，1：凯特芒，2：水仙芒，3：贵妃芒，4：台农芒，5：金辉芒，6：红象芒，7：爱文芒，8：圣心芒
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
	 * get方法。最高价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public String getTopPriceUnitText() {
		return this.topPriceUnitText;
	}

	/**
	 * set方法。最高价单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：百万美元/公斤
	 */
	public void setTopPriceUnitText(String topPriceUnitText) {
		this.topPriceUnitText = topPriceUnitText;
	}
    

    //一对多关系中，多端数据列表

}







