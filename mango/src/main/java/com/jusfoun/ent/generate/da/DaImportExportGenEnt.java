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
 * @updateDate 2020/8/11 11:43:20
 * @description 实体类DaImportExportGenEnt，自动生成。进出口数据表，用于存放直报数据的进出口数据
 */

public class DaImportExportGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal exportAmount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal exportAmountUnit;
    @ApiModelProperty(value="出口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte exportAmountUnitCode;
    @ApiModelProperty(value="出口量单位文本:文本，对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String exportAmountUnitText;
    @ApiModelProperty(value="出口国家：对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private String exportCountryCode;
    @ApiModelProperty(value="出口国家文本：文本，对应数据字典表（dictionary）中的文本字段（text）",hidden=false,required=false)
    private String exportCountryText;
    @ApiModelProperty(value="出口企业",hidden=false,required=false)
    private String exportEnterprise;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal exportPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal exportPriceUnit;
    @ApiModelProperty(value="出口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，5：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte exportPriceUnitCode;
    @ApiModelProperty(value="出口价格单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，5：百万美元/公斤",hidden=false,required=false)
    private String exportPriceUnitText;
    @ApiModelProperty(value="出口日期",hidden=false,required=false)
    private Date exportTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal exportVolume;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal exportVolumeUnit;
    @ApiModelProperty(value="出口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte exportVolumeUnitCode;
    @ApiModelProperty(value="出口额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元",hidden=false,required=false)
    private String exportVolumeUnitText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal importAmount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal importAmountUnit;
    @ApiModelProperty(value="进口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte importAmountUnitCode;
    @ApiModelProperty(value="进口量单位文本:文本，对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String importAmountUnitText;
    @ApiModelProperty(value="进口国家：对应数据字典表（dictionary）中的编码字段（code）",allowableValues="0,1",hidden=false,required=false)
    private String importCountryCode;
    @ApiModelProperty(value="进口国家文本：文本，对应数据字典表（dictionary）中的文本字段（text）",hidden=false,required=false)
    private String importCountryText;
    @ApiModelProperty(value="进口企业",hidden=false,required=false)
    private String importEnterprise;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal importPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal importPriceUnit;
    @ApiModelProperty(value="进口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，6：百万美元/公斤",allowableValues="0,1",hidden=false,required=false)
    private Byte importPriceUnitCode;
    @ApiModelProperty(value="进口价格单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，6：百万美元/公斤",hidden=false,required=false)
    private String importPriceUnitText;
    @ApiModelProperty(value="进口日期",hidden=false,required=false)
    private Date importTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal importVolume;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal importVolumeUnit;
    @ApiModelProperty(value="进口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte importVolumeUnitCode;
    @ApiModelProperty(value="进口额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元",hidden=false,required=false)
    private String importVolumeUnitText;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="产品名称",hidden=false,required=false)
    private String productName;
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
	 * get方法。
	 */
	public BigDecimal getExportAmount() {
		return this.exportAmount;
	}

	/**
	 * set方法。
	 */
	public void setExportAmount(BigDecimal exportAmount) {
		this.exportAmount = exportAmount;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getExportAmountUnit() {
		return this.exportAmountUnit;
	}

	/**
	 * set方法。
	 */
	public void setExportAmountUnit(BigDecimal exportAmountUnit) {
		this.exportAmountUnit = exportAmountUnit;
	}
    
	/**
	 * get方法。出口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getExportAmountUnitCode() {
		return this.exportAmountUnitCode;
	}

	/**
	 * set方法。出口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setExportAmountUnitCode(Byte exportAmountUnitCode) {
		this.exportAmountUnitCode = exportAmountUnitCode;
	}
    
	/**
	 * get方法。出口量单位文本:文本，对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getExportAmountUnitText() {
		return this.exportAmountUnitText;
	}

	/**
	 * set方法。出口量单位文本:文本，对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setExportAmountUnitText(String exportAmountUnitText) {
		this.exportAmountUnitText = exportAmountUnitText;
	}
    
	/**
	 * get方法。出口国家：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public String getExportCountryCode() {
		return this.exportCountryCode;
	}

	/**
	 * set方法。出口国家：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public void setExportCountryCode(String exportCountryCode) {
		this.exportCountryCode = exportCountryCode;
	}
    
	/**
	 * get方法。出口国家文本：文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public String getExportCountryText() {
		return this.exportCountryText;
	}

	/**
	 * set方法。出口国家文本：文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public void setExportCountryText(String exportCountryText) {
		this.exportCountryText = exportCountryText;
	}
    
	/**
	 * get方法。出口企业
	 */
	public String getExportEnterprise() {
		return this.exportEnterprise;
	}

	/**
	 * set方法。出口企业
	 */
	public void setExportEnterprise(String exportEnterprise) {
		this.exportEnterprise = exportEnterprise;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getExportPrice() {
		return this.exportPrice;
	}

	/**
	 * set方法。
	 */
	public void setExportPrice(BigDecimal exportPrice) {
		this.exportPrice = exportPrice;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getExportPriceUnit() {
		return this.exportPriceUnit;
	}

	/**
	 * set方法。
	 */
	public void setExportPriceUnit(BigDecimal exportPriceUnit) {
		this.exportPriceUnit = exportPriceUnit;
	}
    
	/**
	 * get方法。出口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，5：百万美元/公斤
	 */
	public Byte getExportPriceUnitCode() {
		return this.exportPriceUnitCode;
	}

	/**
	 * set方法。出口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，5：百万美元/公斤
	 */
	public void setExportPriceUnitCode(Byte exportPriceUnitCode) {
		this.exportPriceUnitCode = exportPriceUnitCode;
	}
    
	/**
	 * get方法。出口价格单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，5：百万美元/公斤
	 */
	public String getExportPriceUnitText() {
		return this.exportPriceUnitText;
	}

	/**
	 * set方法。出口价格单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，5：百万美元/公斤
	 */
	public void setExportPriceUnitText(String exportPriceUnitText) {
		this.exportPriceUnitText = exportPriceUnitText;
	}
    
	/**
	 * get方法。出口日期
	 */
	public Date getExportTime() {
		return this.exportTime;
	}

	/**
	 * set方法。出口日期
	 */
	public void setExportTime(Date exportTime) {
		this.exportTime = exportTime;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getExportVolume() {
		return this.exportVolume;
	}

	/**
	 * set方法。
	 */
	public void setExportVolume(BigDecimal exportVolume) {
		this.exportVolume = exportVolume;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getExportVolumeUnit() {
		return this.exportVolumeUnit;
	}

	/**
	 * set方法。
	 */
	public void setExportVolumeUnit(BigDecimal exportVolumeUnit) {
		this.exportVolumeUnit = exportVolumeUnit;
	}
    
	/**
	 * get方法。出口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public Byte getExportVolumeUnitCode() {
		return this.exportVolumeUnitCode;
	}

	/**
	 * set方法。出口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public void setExportVolumeUnitCode(Byte exportVolumeUnitCode) {
		this.exportVolumeUnitCode = exportVolumeUnitCode;
	}
    
	/**
	 * get方法。出口额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public String getExportVolumeUnitText() {
		return this.exportVolumeUnitText;
	}

	/**
	 * set方法。出口额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public void setExportVolumeUnitText(String exportVolumeUnitText) {
		this.exportVolumeUnitText = exportVolumeUnitText;
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
	 * get方法。
	 */
	public BigDecimal getImportAmount() {
		return this.importAmount;
	}

	/**
	 * set方法。
	 */
	public void setImportAmount(BigDecimal importAmount) {
		this.importAmount = importAmount;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getImportAmountUnit() {
		return this.importAmountUnit;
	}

	/**
	 * set方法。
	 */
	public void setImportAmountUnit(BigDecimal importAmountUnit) {
		this.importAmountUnit = importAmountUnit;
	}
    
	/**
	 * get方法。进口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getImportAmountUnitCode() {
		return this.importAmountUnitCode;
	}

	/**
	 * set方法。进口量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setImportAmountUnitCode(Byte importAmountUnitCode) {
		this.importAmountUnitCode = importAmountUnitCode;
	}
    
	/**
	 * get方法。进口量单位文本:文本，对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getImportAmountUnitText() {
		return this.importAmountUnitText;
	}

	/**
	 * set方法。进口量单位文本:文本，对应数据字典表（dictionary）中的文本字段（text），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setImportAmountUnitText(String importAmountUnitText) {
		this.importAmountUnitText = importAmountUnitText;
	}
    
	/**
	 * get方法。进口国家：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public String getImportCountryCode() {
		return this.importCountryCode;
	}

	/**
	 * set方法。进口国家：对应数据字典表（dictionary）中的编码字段（code）
	 */
	public void setImportCountryCode(String importCountryCode) {
		this.importCountryCode = importCountryCode;
	}
    
	/**
	 * get方法。进口国家文本：文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public String getImportCountryText() {
		return this.importCountryText;
	}

	/**
	 * set方法。进口国家文本：文本，对应数据字典表（dictionary）中的文本字段（text）
	 */
	public void setImportCountryText(String importCountryText) {
		this.importCountryText = importCountryText;
	}
    
	/**
	 * get方法。进口企业
	 */
	public String getImportEnterprise() {
		return this.importEnterprise;
	}

	/**
	 * set方法。进口企业
	 */
	public void setImportEnterprise(String importEnterprise) {
		this.importEnterprise = importEnterprise;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getImportPrice() {
		return this.importPrice;
	}

	/**
	 * set方法。
	 */
	public void setImportPrice(BigDecimal importPrice) {
		this.importPrice = importPrice;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getImportPriceUnit() {
		return this.importPriceUnit;
	}

	/**
	 * set方法。
	 */
	public void setImportPriceUnit(BigDecimal importPriceUnit) {
		this.importPriceUnit = importPriceUnit;
	}
    
	/**
	 * get方法。进口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，6：百万美元/公斤
	 */
	public Byte getImportPriceUnitCode() {
		return this.importPriceUnitCode;
	}

	/**
	 * set方法。进口价格单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，6：百万美元/公斤
	 */
	public void setImportPriceUnitCode(Byte importPriceUnitCode) {
		this.importPriceUnitCode = importPriceUnitCode;
	}
    
	/**
	 * get方法。进口价格单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，6：百万美元/公斤
	 */
	public String getImportPriceUnitText() {
		return this.importPriceUnitText;
	}

	/**
	 * set方法。进口价格单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元/公斤，2：万元/公斤，3：亿元/公斤，4：美元/公斤，5：万美元/公斤，6：百万美元/公斤
	 */
	public void setImportPriceUnitText(String importPriceUnitText) {
		this.importPriceUnitText = importPriceUnitText;
	}
    
	/**
	 * get方法。进口日期
	 */
	public Date getImportTime() {
		return this.importTime;
	}

	/**
	 * set方法。进口日期
	 */
	public void setImportTime(Date importTime) {
		this.importTime = importTime;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getImportVolume() {
		return this.importVolume;
	}

	/**
	 * set方法。
	 */
	public void setImportVolume(BigDecimal importVolume) {
		this.importVolume = importVolume;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getImportVolumeUnit() {
		return this.importVolumeUnit;
	}

	/**
	 * set方法。
	 */
	public void setImportVolumeUnit(BigDecimal importVolumeUnit) {
		this.importVolumeUnit = importVolumeUnit;
	}
    
	/**
	 * get方法。进口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public Byte getImportVolumeUnitCode() {
		return this.importVolumeUnitCode;
	}

	/**
	 * set方法。进口额单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public void setImportVolumeUnitCode(Byte importVolumeUnitCode) {
		this.importVolumeUnitCode = importVolumeUnitCode;
	}
    
	/**
	 * get方法。进口额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public String getImportVolumeUnitText() {
		return this.importVolumeUnitText;
	}

	/**
	 * set方法。进口额单位文本:文本，对应数据字典表（dictionary）中的文本字段（text）1：元，2：万元，3：亿元，4：美元，5：万美元，6：百万美元
	 */
	public void setImportVolumeUnitText(String importVolumeUnitText) {
		this.importVolumeUnitText = importVolumeUnitText;
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







