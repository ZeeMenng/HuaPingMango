package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:01
 * @description 实体类DaInputGenEnt，自动生成。投入品信息表
 */

public class DaInputGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String baseName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String brandName;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String inputName;
    @ApiModelProperty(value="投入品类型:对应数据字典表（dictionary）中的编码字段（code）1：种子，2：农药，3：化肥",allowableValues="0,1",hidden=false,required=false)
    private Byte inputTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String inputTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String objectName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal perCost;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal perCostUnit;
    @ApiModelProperty(value="成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte perCostUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String perCostUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal perPrice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal perPriceUnit;
    @ApiModelProperty(value="单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：元/吨，3：元/千克，4：美元/吨",allowableValues="0,1",hidden=false,required=false)
    private Byte perPriceUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String perPriceUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal procurementTotal;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal procurementTotalUnit;
    @ApiModelProperty(value="采购总量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte procurementTotalUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String procurementTotalUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String purchasePoint;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal useAmount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal useAmountUnit;
    @ApiModelProperty(value="使用量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩",allowableValues="0,1",hidden=false,required=false)
    private Byte useAmountUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String useAmountUnitText;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getBaseName() {
		return this.baseName;
	}

	/**
	 * set方法。
	 */
	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}
    
	/**
	 * get方法。
	 */
	public String getBrandName() {
		return this.brandName;
	}

	/**
	 * set方法。
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
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
	public String getInputName() {
		return this.inputName;
	}

	/**
	 * set方法。
	 */
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
    
	/**
	 * get方法。投入品类型:对应数据字典表（dictionary）中的编码字段（code）1：种子，2：农药，3：化肥
	 */
	public Byte getInputTypeCode() {
		return this.inputTypeCode;
	}

	/**
	 * set方法。投入品类型:对应数据字典表（dictionary）中的编码字段（code）1：种子，2：农药，3：化肥
	 */
	public void setInputTypeCode(Byte inputTypeCode) {
		this.inputTypeCode = inputTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getInputTypeText() {
		return this.inputTypeText;
	}

	/**
	 * set方法。
	 */
	public void setInputTypeText(String inputTypeText) {
		this.inputTypeText = inputTypeText;
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
	public String getObjectName() {
		return this.objectName;
	}

	/**
	 * set方法。
	 */
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getPerCost() {
		return this.perCost;
	}

	/**
	 * set方法。
	 */
	public void setPerCost(BigDecimal perCost) {
		this.perCost = perCost;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getPerCostUnit() {
		return this.perCostUnit;
	}

	/**
	 * set方法。
	 */
	public void setPerCostUnit(BigDecimal perCostUnit) {
		this.perCostUnit = perCostUnit;
	}
    
	/**
	 * get方法。成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public Byte getPerCostUnitCode() {
		return this.perCostUnitCode;
	}

	/**
	 * set方法。成本单位:对应数据字典表（dictionary）中的编码字段（code）1：元/亩
	 */
	public void setPerCostUnitCode(Byte perCostUnitCode) {
		this.perCostUnitCode = perCostUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getPerCostUnitText() {
		return this.perCostUnitText;
	}

	/**
	 * set方法。
	 */
	public void setPerCostUnitText(String perCostUnitText) {
		this.perCostUnitText = perCostUnitText;
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
	 * get方法。单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：元/吨，3：元/千克，4：美元/吨
	 */
	public Byte getPerPriceUnitCode() {
		return this.perPriceUnitCode;
	}

	/**
	 * set方法。单价单位:对应数据字典表（dictionary）中的编码字段（code）1：元/公斤，2：元/吨，3：元/千克，4：美元/吨
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
	public BigDecimal getProcurementTotal() {
		return this.procurementTotal;
	}

	/**
	 * set方法。
	 */
	public void setProcurementTotal(BigDecimal procurementTotal) {
		this.procurementTotal = procurementTotal;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getProcurementTotalUnit() {
		return this.procurementTotalUnit;
	}

	/**
	 * set方法。
	 */
	public void setProcurementTotalUnit(BigDecimal procurementTotalUnit) {
		this.procurementTotalUnit = procurementTotalUnit;
	}
    
	/**
	 * get方法。采购总量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getProcurementTotalUnitCode() {
		return this.procurementTotalUnitCode;
	}

	/**
	 * set方法。采购总量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setProcurementTotalUnitCode(Byte procurementTotalUnitCode) {
		this.procurementTotalUnitCode = procurementTotalUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getProcurementTotalUnitText() {
		return this.procurementTotalUnitText;
	}

	/**
	 * set方法。
	 */
	public void setProcurementTotalUnitText(String procurementTotalUnitText) {
		this.procurementTotalUnitText = procurementTotalUnitText;
	}
    
	/**
	 * get方法。
	 */
	public String getPurchasePoint() {
		return this.purchasePoint;
	}

	/**
	 * set方法。
	 */
	public void setPurchasePoint(String purchasePoint) {
		this.purchasePoint = purchasePoint;
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
	public BigDecimal getUseAmount() {
		return this.useAmount;
	}

	/**
	 * set方法。
	 */
	public void setUseAmount(BigDecimal useAmount) {
		this.useAmount = useAmount;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getUseAmountUnit() {
		return this.useAmountUnit;
	}

	/**
	 * set方法。
	 */
	public void setUseAmountUnit(BigDecimal useAmountUnit) {
		this.useAmountUnit = useAmountUnit;
	}
    
	/**
	 * get方法。使用量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩
	 */
	public Byte getUseAmountUnitCode() {
		return this.useAmountUnitCode;
	}

	/**
	 * set方法。使用量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克/亩，2：克/亩
	 */
	public void setUseAmountUnitCode(Byte useAmountUnitCode) {
		this.useAmountUnitCode = useAmountUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getUseAmountUnitText() {
		return this.useAmountUnitText;
	}

	/**
	 * set方法。
	 */
	public void setUseAmountUnitText(String useAmountUnitText) {
		this.useAmountUnitText = useAmountUnitText;
	}
    










}







