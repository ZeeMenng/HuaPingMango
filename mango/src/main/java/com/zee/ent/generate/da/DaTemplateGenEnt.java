package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:11
 * @description 实体类DaTemplateGenEnt，自动生成。指标模板表
 */

public class DaTemplateGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String serialNo;
    @ApiModelProperty(value="业务类型,对应数据字典表（dictionary）中的编码字段（code）1：产业，2：统计",allowableValues="0,1",hidden=false,required=false)
    private Byte serviceTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String serviceTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String templateName;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	public String getSerialNo() {
		return this.serialNo;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getServiceTypeText() {
		return this.serviceTypeText;
	}

	/**
	 * set方法。
	 */
	public void setServiceTypeText(String serviceTypeText) {
		this.serviceTypeText = serviceTypeText;
	}
    
	/**
	 * get方法。
	 */
	public String getTemplateName() {
		return this.templateName;
	}

	/**
	 * set方法。
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
    










}







