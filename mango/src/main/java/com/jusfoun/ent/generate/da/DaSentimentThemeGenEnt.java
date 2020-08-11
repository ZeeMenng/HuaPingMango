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
 * @updateDate 2020/8/11 11:43:33
 * @description 实体类DaSentimentThemeGenEnt，自动生成。舆情主题名称
 */

public class DaSentimentThemeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="创建人",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="排除词，逗号隔开",hidden=false,required=false)
    private String excludeName;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="关键词，逗号隔开",hidden=false,required=false)
    private String keyName;
    @ApiModelProperty(value="描述",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="采集规则，对应规则表id",hidden=false,required=false)
    private String ruleId;
    @ApiModelProperty(value="状态code，对应数据字典表，0：关闭，1：开启",allowableValues="0,1",hidden=false,required=false)
    private Byte statusCode;
    @ApiModelProperty(value="状态text，对应数据字典表，0：关闭，1：开启",hidden=false,required=false)
    private String statusText;
    @ApiModelProperty(value="主题名称",hidden=false,required=false)
    private String themeName;
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
	 * get方法。排除词，逗号隔开
	 */
	public String getExcludeName() {
		return this.excludeName;
	}

	/**
	 * set方法。排除词，逗号隔开
	 */
	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
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
	 * get方法。关键词，逗号隔开
	 */
	public String getKeyName() {
		return this.keyName;
	}

	/**
	 * set方法。关键词，逗号隔开
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
    
	/**
	 * get方法。描述
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。描述
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。采集规则，对应规则表id
	 */
	public String getRuleId() {
		return this.ruleId;
	}

	/**
	 * set方法。采集规则，对应规则表id
	 */
	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}
    
	/**
	 * get方法。状态code，对应数据字典表，0：关闭，1：开启
	 */
	public Byte getStatusCode() {
		return this.statusCode;
	}

	/**
	 * set方法。状态code，对应数据字典表，0：关闭，1：开启
	 */
	public void setStatusCode(Byte statusCode) {
		this.statusCode = statusCode;
	}
    
	/**
	 * get方法。状态text，对应数据字典表，0：关闭，1：开启
	 */
	public String getStatusText() {
		return this.statusText;
	}

	/**
	 * set方法。状态text，对应数据字典表，0：关闭，1：开启
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
    
	/**
	 * get方法。主题名称
	 */
	public String getThemeName() {
		return this.themeName;
	}

	/**
	 * set方法。主题名称
	 */
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
    

    //一对多关系中，多端数据列表

}







