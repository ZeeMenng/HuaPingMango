package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:06
 * @description 实体类DaSaleEcommerceOrderGenEnt，自动生成。电商数据表，用于存放直报的电商数据
 */

public class DaSaleEcommerceOrderGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal actualIncome;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal actualIncomeUnit;
    @ApiModelProperty(value="实收单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte actualIncomeUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String actualIncomeUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String addressee;
    @ApiModelProperty(value="收件区域code",allowableValues="0,1",hidden=false,required=false)
    private Byte areaCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String areaText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contactPhone;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String courier;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal courierFee;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal courierFeeUnit;
    @ApiModelProperty(value="快递费用单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte courierFeeUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String courierFeeUnitText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String courierNumber;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String deliveryAddress;
    @ApiModelProperty(value="平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东",allowableValues="0,1",hidden=false,required=false)
    private Byte ecommerceCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String ecommerceText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String ecommerceType;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String orderId;
    @ApiModelProperty(value="订单日期",hidden=false,required=false)
    private Date orderTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String payAccount;
    @ApiModelProperty(value="支付方式:对应数据字典表（dictionary）中的编码字段（code） 1；银行卡，2：支付宝:，3：微信:，4：其他:",allowableValues="0,1",hidden=false,required=false)
    private Byte payTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String payTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal saleAmountUnit;
    @ApiModelProperty(value="销售数量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte saleAmountUnitCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String saleAmountUnitText;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public BigDecimal getActualIncome() {
		return this.actualIncome;
	}

	/**
	 * set方法。
	 */
	public void setActualIncome(BigDecimal actualIncome) {
		this.actualIncome = actualIncome;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getActualIncomeUnit() {
		return this.actualIncomeUnit;
	}

	/**
	 * set方法。
	 */
	public void setActualIncomeUnit(BigDecimal actualIncomeUnit) {
		this.actualIncomeUnit = actualIncomeUnit;
	}
    
	/**
	 * get方法。实收单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getActualIncomeUnitCode() {
		return this.actualIncomeUnitCode;
	}

	/**
	 * set方法。实收单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setActualIncomeUnitCode(Byte actualIncomeUnitCode) {
		this.actualIncomeUnitCode = actualIncomeUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getActualIncomeUnitText() {
		return this.actualIncomeUnitText;
	}

	/**
	 * set方法。
	 */
	public void setActualIncomeUnitText(String actualIncomeUnitText) {
		this.actualIncomeUnitText = actualIncomeUnitText;
	}
    
	/**
	 * get方法。
	 */
	public String getAddressee() {
		return this.addressee;
	}

	/**
	 * set方法。
	 */
	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}
    
	/**
	 * get方法。收件区域code
	 */
	public Byte getAreaCode() {
		return this.areaCode;
	}

	/**
	 * set方法。收件区域code
	 */
	public void setAreaCode(Byte areaCode) {
		this.areaCode = areaCode;
	}
    
	/**
	 * get方法。
	 */
	public String getAreaText() {
		return this.areaText;
	}

	/**
	 * set方法。
	 */
	public void setAreaText(String areaText) {
		this.areaText = areaText;
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
	 * get方法。
	 */
	public String getContactPhone() {
		return this.contactPhone;
	}

	/**
	 * set方法。
	 */
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
    
	/**
	 * get方法。
	 */
	public String getCourier() {
		return this.courier;
	}

	/**
	 * set方法。
	 */
	public void setCourier(String courier) {
		this.courier = courier;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCourierFee() {
		return this.courierFee;
	}

	/**
	 * set方法。
	 */
	public void setCourierFee(BigDecimal courierFee) {
		this.courierFee = courierFee;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getCourierFeeUnit() {
		return this.courierFeeUnit;
	}

	/**
	 * set方法。
	 */
	public void setCourierFeeUnit(BigDecimal courierFeeUnit) {
		this.courierFeeUnit = courierFeeUnit;
	}
    
	/**
	 * get方法。快递费用单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getCourierFeeUnitCode() {
		return this.courierFeeUnitCode;
	}

	/**
	 * set方法。快递费用单位:对应数据字典表（dictionary）中的编码字段（code）1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setCourierFeeUnitCode(Byte courierFeeUnitCode) {
		this.courierFeeUnitCode = courierFeeUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCourierFeeUnitText() {
		return this.courierFeeUnitText;
	}

	/**
	 * set方法。
	 */
	public void setCourierFeeUnitText(String courierFeeUnitText) {
		this.courierFeeUnitText = courierFeeUnitText;
	}
    
	/**
	 * get方法。
	 */
	public String getCourierNumber() {
		return this.courierNumber;
	}

	/**
	 * set方法。
	 */
	public void setCourierNumber(String courierNumber) {
		this.courierNumber = courierNumber;
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
	public String getDeliveryAddress() {
		return this.deliveryAddress;
	}

	/**
	 * set方法。
	 */
	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
    
	/**
	 * get方法。平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东
	 */
	public Byte getEcommerceCode() {
		return this.ecommerceCode;
	}

	/**
	 * set方法。平台号：对应数据字典表（dictionary）中的编码字段（code）1：天猫，2：淘宝，3：京东
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
	public String getEcommerceType() {
		return this.ecommerceType;
	}

	/**
	 * set方法。
	 */
	public void setEcommerceType(String ecommerceType) {
		this.ecommerceType = ecommerceType;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
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
	public String getOrderId() {
		return this.orderId;
	}

	/**
	 * set方法。
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
    
	/**
	 * get方法。订单日期
	 */
	public Date getOrderTime() {
		return this.orderTime;
	}

	/**
	 * set方法。订单日期
	 */
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
    
	/**
	 * get方法。
	 */
	public String getPayAccount() {
		return this.payAccount;
	}

	/**
	 * set方法。
	 */
	public void setPayAccount(String payAccount) {
		this.payAccount = payAccount;
	}
    
	/**
	 * get方法。支付方式:对应数据字典表（dictionary）中的编码字段（code） 1；银行卡，2：支付宝:，3：微信:，4：其他:
	 */
	public Byte getPayTypeCode() {
		return this.payTypeCode;
	}

	/**
	 * set方法。支付方式:对应数据字典表（dictionary）中的编码字段（code） 1；银行卡，2：支付宝:，3：微信:，4：其他:
	 */
	public void setPayTypeCode(Byte payTypeCode) {
		this.payTypeCode = payTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getPayTypeText() {
		return this.payTypeText;
	}

	/**
	 * set方法。
	 */
	public void setPayTypeText(String payTypeText) {
		this.payTypeText = payTypeText;
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
	 * get方法。销售数量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getSaleAmountUnitCode() {
		return this.saleAmountUnitCode;
	}

	/**
	 * set方法。销售数量单位:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setSaleAmountUnitCode(Byte saleAmountUnitCode) {
		this.saleAmountUnitCode = saleAmountUnitCode;
	}
    
	/**
	 * get方法。
	 */
	public String getSaleAmountUnitText() {
		return this.saleAmountUnitText;
	}

	/**
	 * set方法。
	 */
	public void setSaleAmountUnitText(String saleAmountUnitText) {
		this.saleAmountUnitText = saleAmountUnitText;
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
    










}







