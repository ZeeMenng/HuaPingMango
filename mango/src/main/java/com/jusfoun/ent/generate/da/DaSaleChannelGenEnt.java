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
 * @updateDate 2020/8/11 11:43:28
 * @description 实体类DaSaleChannelGenEnt，自动生成。销售渠道数据表
 */

public class DaSaleChannelGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmountUnit;
    @ApiModelProperty(value="销售量单位，1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte saleAmountUnitCode;
    @ApiModelProperty(value="销售量单位文本，1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String saleAmountUnitText;
    @ApiModelProperty(value="销售渠道code，对应数据字典表（dictionary）中的编码字段（code）0：全部，1：电商平台，2：微商平台，3：批发市场，4：零售，5：田头销售，6：农超对接，7：农商对接，8：其他",allowableValues="0,1",hidden=false,required=false)
    private String saleChannelTypeCode;
    @ApiModelProperty(value="销售渠道文本，对应数据字典表（dictionary）中的文本字段（text）0：全部，1：电商平台，2：微商平台，3：批发市场，4：零售，5：田头销售，6：农超对接，7：农商对接，8：其他",hidden=false,required=false)
    private String saleChannelTypeText;
    @ApiModelProperty(value="销售城市",hidden=false,required=false)
    private String saleCity;
    @ApiModelProperty(value="销售区域",allowableValues="0,1",hidden=false,required=false)
    private String saleRegionCode;
    @ApiModelProperty(value="销售区域文本",hidden=false,required=false)
    private String saleRegionText;
    @ApiModelProperty(value="销售日期",hidden=false,required=false)
    private Date saleTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleVolume;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleVolumeUnit;
    @ApiModelProperty(value="销售额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte saleVolumeUnitCode;
    @ApiModelProperty(value="销售额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String saleVolumeUnitText;
    @ApiModelProperty(value="销售商",hidden=false,required=false)
    private String seller;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",hidden=false,required=false)
    private String strainsText;
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
	 * get方法。销售量单位，1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getSaleAmountUnitCode() {
		return this.saleAmountUnitCode;
	}

	/**
	 * set方法。销售量单位，1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setSaleAmountUnitCode(Byte saleAmountUnitCode) {
		this.saleAmountUnitCode = saleAmountUnitCode;
	}
    
	/**
	 * get方法。销售量单位文本，1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getSaleAmountUnitText() {
		return this.saleAmountUnitText;
	}

	/**
	 * set方法。销售量单位文本，1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setSaleAmountUnitText(String saleAmountUnitText) {
		this.saleAmountUnitText = saleAmountUnitText;
	}
    
	/**
	 * get方法。销售渠道code，对应数据字典表（dictionary）中的编码字段（code）0：全部，1：电商平台，2：微商平台，3：批发市场，4：零售，5：田头销售，6：农超对接，7：农商对接，8：其他
	 */
	public String getSaleChannelTypeCode() {
		return this.saleChannelTypeCode;
	}

	/**
	 * set方法。销售渠道code，对应数据字典表（dictionary）中的编码字段（code）0：全部，1：电商平台，2：微商平台，3：批发市场，4：零售，5：田头销售，6：农超对接，7：农商对接，8：其他
	 */
	public void setSaleChannelTypeCode(String saleChannelTypeCode) {
		this.saleChannelTypeCode = saleChannelTypeCode;
	}
    
	/**
	 * get方法。销售渠道文本，对应数据字典表（dictionary）中的文本字段（text）0：全部，1：电商平台，2：微商平台，3：批发市场，4：零售，5：田头销售，6：农超对接，7：农商对接，8：其他
	 */
	public String getSaleChannelTypeText() {
		return this.saleChannelTypeText;
	}

	/**
	 * set方法。销售渠道文本，对应数据字典表（dictionary）中的文本字段（text）0：全部，1：电商平台，2：微商平台，3：批发市场，4：零售，5：田头销售，6：农超对接，7：农商对接，8：其他
	 */
	public void setSaleChannelTypeText(String saleChannelTypeText) {
		this.saleChannelTypeText = saleChannelTypeText;
	}
    
	/**
	 * get方法。销售城市
	 */
	public String getSaleCity() {
		return this.saleCity;
	}

	/**
	 * set方法。销售城市
	 */
	public void setSaleCity(String saleCity) {
		this.saleCity = saleCity;
	}
    
	/**
	 * get方法。销售区域
	 */
	public String getSaleRegionCode() {
		return this.saleRegionCode;
	}

	/**
	 * set方法。销售区域
	 */
	public void setSaleRegionCode(String saleRegionCode) {
		this.saleRegionCode = saleRegionCode;
	}
    
	/**
	 * get方法。销售区域文本
	 */
	public String getSaleRegionText() {
		return this.saleRegionText;
	}

	/**
	 * set方法。销售区域文本
	 */
	public void setSaleRegionText(String saleRegionText) {
		this.saleRegionText = saleRegionText;
	}
    
	/**
	 * get方法。销售日期
	 */
	public Date getSaleTime() {
		return this.saleTime;
	}

	/**
	 * set方法。销售日期
	 */
	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
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
	 * get方法。销售商
	 */
	public String getSeller() {
		return this.seller;
	}

	/**
	 * set方法。销售商
	 */
	public void setSeller(String seller) {
		this.seller = seller;
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
	 * get方法。对应数据字典表（dictionary）中的作物品种
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    

    //一对多关系中，多端数据列表

}







