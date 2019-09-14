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
 * @updateDate 2018-6-13 19:04:34
 * @description 实体类DaProcessGenEnt，自动生成。加工品数据表
 */

public class DaProcessGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal consumeTotal;
    @ApiModelProperty(value="原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte consumeTotalCode;
    @ApiModelProperty(value="原材料消耗总量文本:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String consumeTotalText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal consumeTotalUnit;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputValue;
    @ApiModelProperty(value="产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元",allowableValues="0,1",hidden=false,required=false)
    private Byte outputValueCode;
    @ApiModelProperty(value="产值文本，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元",hidden=false,required=false)
    private String outputValueText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal outputValueUnit;
    @ApiModelProperty(value="加工品品种:对应数据字典表（dictionary）中的编码字段（code）  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱",allowableValues="0,1",hidden=false,required=false)
    private Byte processStrainsCode;
    @ApiModelProperty(value="加工品品种文本:对应数据字典表（dictionary）中的文本字段（text）   1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱",hidden=false,required=false)
    private String processStrainsText;
    @ApiModelProperty(value="加工时间",hidden=false,required=false)
    private Date processTime;
    @ApiModelProperty(value="产品名称",hidden=false,required=false)
    private String productName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal productTotal;
    @ApiModelProperty(value="产品产量，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",allowableValues="0,1",hidden=false,required=false)
    private Byte productTotalCode;
    @ApiModelProperty(value="产品产量文本，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨",hidden=false,required=false)
    private String productTotalText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal productTotalUnit;
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
	 * get方法。
	 */
	public BigDecimal getConsumeTotal() {
		return this.consumeTotal;
	}

	/**
	 * set方法。
	 */
	public void setConsumeTotal(BigDecimal consumeTotal) {
		this.consumeTotal = consumeTotal;
	}
    
	/**
	 * get方法。原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getConsumeTotalCode() {
		return this.consumeTotalCode;
	}

	/**
	 * set方法。原材料消耗总量:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setConsumeTotalCode(Byte consumeTotalCode) {
		this.consumeTotalCode = consumeTotalCode;
	}
    
	/**
	 * get方法。原材料消耗总量文本:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getConsumeTotalText() {
		return this.consumeTotalText;
	}

	/**
	 * set方法。原材料消耗总量文本:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setConsumeTotalText(String consumeTotalText) {
		this.consumeTotalText = consumeTotalText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getConsumeTotalUnit() {
		return this.consumeTotalUnit;
	}

	/**
	 * set方法。
	 */
	public void setConsumeTotalUnit(BigDecimal consumeTotalUnit) {
		this.consumeTotalUnit = consumeTotalUnit;
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
	public BigDecimal getOutputValue() {
		return this.outputValue;
	}

	/**
	 * set方法。
	 */
	public void setOutputValue(BigDecimal outputValue) {
		this.outputValue = outputValue;
	}
    
	/**
	 * get方法。产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public Byte getOutputValueCode() {
		return this.outputValueCode;
	}

	/**
	 * set方法。产值，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOutputValueCode(Byte outputValueCode) {
		this.outputValueCode = outputValueCode;
	}
    
	/**
	 * get方法。产值文本，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public String getOutputValueText() {
		return this.outputValueText;
	}

	/**
	 * set方法。产值文本，对应数据字典表（dictionary）中的编码字段（code），1：元，2：万元，3：亿元，4：美元，5：百万美元
	 */
	public void setOutputValueText(String outputValueText) {
		this.outputValueText = outputValueText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getOutputValueUnit() {
		return this.outputValueUnit;
	}

	/**
	 * set方法。
	 */
	public void setOutputValueUnit(BigDecimal outputValueUnit) {
		this.outputValueUnit = outputValueUnit;
	}
    
	/**
	 * get方法。加工品品种:对应数据字典表（dictionary）中的编码字段（code）  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public Byte getProcessStrainsCode() {
		return this.processStrainsCode;
	}

	/**
	 * set方法。加工品品种:对应数据字典表（dictionary）中的编码字段（code）  1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public void setProcessStrainsCode(Byte processStrainsCode) {
		this.processStrainsCode = processStrainsCode;
	}
    
	/**
	 * get方法。加工品品种文本:对应数据字典表（dictionary）中的文本字段（text）   1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public String getProcessStrainsText() {
		return this.processStrainsText;
	}

	/**
	 * set方法。加工品品种文本:对应数据字典表（dictionary）中的文本字段（text）   1：芒果汁，2：芒果干，3：芒果醋，4：芒果果酒，5：芒果果粉，6：芒果冻，7：芒果酱
	 */
	public void setProcessStrainsText(String processStrainsText) {
		this.processStrainsText = processStrainsText;
	}
    
	/**
	 * get方法。加工时间
	 */
	public Date getProcessTime() {
		return this.processTime;
	}

	/**
	 * set方法。加工时间
	 */
	public void setProcessTime(Date processTime) {
		this.processTime = processTime;
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
	 * get方法。
	 */
	public BigDecimal getProductTotal() {
		return this.productTotal;
	}

	/**
	 * set方法。
	 */
	public void setProductTotal(BigDecimal productTotal) {
		this.productTotal = productTotal;
	}
    
	/**
	 * get方法。产品产量，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public Byte getProductTotalCode() {
		return this.productTotalCode;
	}

	/**
	 * set方法。产品产量，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setProductTotalCode(Byte productTotalCode) {
		this.productTotalCode = productTotalCode;
	}
    
	/**
	 * get方法。产品产量文本，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public String getProductTotalText() {
		return this.productTotalText;
	}

	/**
	 * set方法。产品产量文本，:对应数据字典表（dictionary）中的编码字段（code），1：千克，2：吨，3：公斤，4：万吨
	 */
	public void setProductTotalText(String productTotalText) {
		this.productTotalText = productTotalText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getProductTotalUnit() {
		return this.productTotalUnit;
	}

	/**
	 * set方法。
	 */
	public void setProductTotalUnit(BigDecimal productTotalUnit) {
		this.productTotalUnit = productTotalUnit;
	}
    

    //一对多关系中，多端数据列表

}







