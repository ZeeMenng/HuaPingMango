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
 * @updateDate 2020/8/11 11:43:34
 * @description 实体类DaTargetGenEnt，自动生成。指标数据
 */

public class DaTargetGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="创建人",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="标签选项，如果是下拉列表（单选或复选）需要填写此字段，以逗号隔开",hidden=false,required=false)
    private String htmlValue;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="指标说明，备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="指标编号",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="状态，对应数据字典表（dictionary）中的编码字段（code）1：新建（新建状态可编辑，使用后无法编辑），2：启用，3：禁用",allowableValues="0,1",hidden=false,required=false)
    private Byte statusCode;
    @ApiModelProperty(value="状态，对应数据字典表（dictionary）中的编码字段（text）1：新建（新建状态可编辑，使用后无法编辑），2：启用，3：禁用",hidden=false,required=false)
    private String statusText;
    @ApiModelProperty(value="html类型，对应数据字典表（dictionary）中的编码字段（code）1：文本框，2、单选下拉列表，3、复选下拉列表",allowableValues="0,1",hidden=false,required=false)
    private Byte targetHtmlCode;
    @ApiModelProperty(value="html类型，对应数据字典表（dictionary）中的编码字段（text）1：文本框，2、单选下拉列表，3、复选下拉列表",hidden=false,required=false)
    private String targetHtmlText;
    @ApiModelProperty(value="指标名称",hidden=false,required=false)
    private String targetName;
    @ApiModelProperty(value="指标单位,对应数据字典表（dictionary）中的编码字段（code）1：元，2：千克，3：平方米",allowableValues="0,1",hidden=false,required=false)
    private Byte targetUnitCode;
    @ApiModelProperty(value="指标单位,对应数据字典表（dictionary）中的编码字段（text）1：元，2：千克，3：平方米",hidden=false,required=false)
    private String targetUnitText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。创建人
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。创建人
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。标签选项，如果是下拉列表（单选或复选）需要填写此字段，以逗号隔开
	 */
	public String getHtmlValue() {
		return this.htmlValue;
	}

	/**
	 * set方法。标签选项，如果是下拉列表（单选或复选）需要填写此字段，以逗号隔开
	 */
	public void setHtmlValue(String htmlValue) {
		this.htmlValue = htmlValue;
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
	 * get方法。指标说明，备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。指标说明，备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。指标编号
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。指标编号
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
    
	/**
	 * get方法。状态，对应数据字典表（dictionary）中的编码字段（code）1：新建（新建状态可编辑，使用后无法编辑），2：启用，3：禁用
	 */
	public Byte getStatusCode() {
		return this.statusCode;
	}

	/**
	 * set方法。状态，对应数据字典表（dictionary）中的编码字段（code）1：新建（新建状态可编辑，使用后无法编辑），2：启用，3：禁用
	 */
	public void setStatusCode(Byte statusCode) {
		this.statusCode = statusCode;
	}
    
	/**
	 * get方法。状态，对应数据字典表（dictionary）中的编码字段（text）1：新建（新建状态可编辑，使用后无法编辑），2：启用，3：禁用
	 */
	public String getStatusText() {
		return this.statusText;
	}

	/**
	 * set方法。状态，对应数据字典表（dictionary）中的编码字段（text）1：新建（新建状态可编辑，使用后无法编辑），2：启用，3：禁用
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
    
	/**
	 * get方法。html类型，对应数据字典表（dictionary）中的编码字段（code）1：文本框，2、单选下拉列表，3、复选下拉列表
	 */
	public Byte getTargetHtmlCode() {
		return this.targetHtmlCode;
	}

	/**
	 * set方法。html类型，对应数据字典表（dictionary）中的编码字段（code）1：文本框，2、单选下拉列表，3、复选下拉列表
	 */
	public void setTargetHtmlCode(Byte targetHtmlCode) {
		this.targetHtmlCode = targetHtmlCode;
	}
    
	/**
	 * get方法。html类型，对应数据字典表（dictionary）中的编码字段（text）1：文本框，2、单选下拉列表，3、复选下拉列表
	 */
	public String getTargetHtmlText() {
		return this.targetHtmlText;
	}

	/**
	 * set方法。html类型，对应数据字典表（dictionary）中的编码字段（text）1：文本框，2、单选下拉列表，3、复选下拉列表
	 */
	public void setTargetHtmlText(String targetHtmlText) {
		this.targetHtmlText = targetHtmlText;
	}
    
	/**
	 * get方法。指标名称
	 */
	public String getTargetName() {
		return this.targetName;
	}

	/**
	 * set方法。指标名称
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
    
	/**
	 * get方法。指标单位,对应数据字典表（dictionary）中的编码字段（code）1：元，2：千克，3：平方米
	 */
	public Byte getTargetUnitCode() {
		return this.targetUnitCode;
	}

	/**
	 * set方法。指标单位,对应数据字典表（dictionary）中的编码字段（code）1：元，2：千克，3：平方米
	 */
	public void setTargetUnitCode(Byte targetUnitCode) {
		this.targetUnitCode = targetUnitCode;
	}
    
	/**
	 * get方法。指标单位,对应数据字典表（dictionary）中的编码字段（text）1：元，2：千克，3：平方米
	 */
	public String getTargetUnitText() {
		return this.targetUnitText;
	}

	/**
	 * set方法。指标单位,对应数据字典表（dictionary）中的编码字段（text）1：元，2：千克，3：平方米
	 */
	public void setTargetUnitText(String targetUnitText) {
		this.targetUnitText = targetUnitText;
	}
    

    //一对多关系中，多端数据列表

}







