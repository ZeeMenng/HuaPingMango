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
 * @updateDate 2020/8/11 11:43:35
 * @description 实体类DaTemplateGenEnt，自动生成。指标模板表
 */

public class DaTemplateGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="通用字段id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="编号",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="业务类型,对应数据字典表（dictionary）中的编码字段（code）1：产业，2：统计",allowableValues="0,1",hidden=false,required=false)
    private Byte serviceTypeCode;
    @ApiModelProperty(value="业务类型,对应数据字典表（dictionary）中的编码字段（text）1：产业，2：统计",hidden=false,required=false)
    private String serviceTypeText;
    @ApiModelProperty(value="模板名称",hidden=false,required=false)
    private String templateName;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。通用字段id
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。通用字段id
	 */
	public void setCommonFieldId(String commonFieldId) {
		this.commonFieldId = commonFieldId;
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
	 * get方法。编号
	 */
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。编号
	 */
	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}
    
	/**
	 * get方法。业务类型,对应数据字典表（dictionary）中的编码字段（code）1：产业，2：统计
	 */
	public Byte getServiceTypeCode() {
		return this.serviceTypeCode;
	}

	/**
	 * set方法。业务类型,对应数据字典表（dictionary）中的编码字段（code）1：产业，2：统计
	 */
	public void setServiceTypeCode(Byte serviceTypeCode) {
		this.serviceTypeCode = serviceTypeCode;
	}
    
	/**
	 * get方法。业务类型,对应数据字典表（dictionary）中的编码字段（text）1：产业，2：统计
	 */
	public String getServiceTypeText() {
		return this.serviceTypeText;
	}

	/**
	 * set方法。业务类型,对应数据字典表（dictionary）中的编码字段（text）1：产业，2：统计
	 */
	public void setServiceTypeText(String serviceTypeText) {
		this.serviceTypeText = serviceTypeText;
	}
    
	/**
	 * get方法。模板名称
	 */
	public String getTemplateName() {
		return this.templateName;
	}

	/**
	 * set方法。模板名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
    

    //一对多关系中，多端数据列表

}







