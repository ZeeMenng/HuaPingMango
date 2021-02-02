package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:09
 * @description 实体类DaSentimentThemeGenEnt，自动生成。舆情主题名称
 */

public class DaSentimentThemeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String excludeName;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String keyName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String ruleId;
    @ApiModelProperty(value="状态code，对应数据字典表，0：关闭，1：开启",allowableValues="0,1",hidden=false,required=false)
    private Byte statusCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String statusText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String themeName;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。
	 */
	public String getExcludeName() {
		return this.excludeName;
	}

	/**
	 * set方法。
	 */
	public void setExcludeName(String excludeName) {
		this.excludeName = excludeName;
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
	public String getKeyName() {
		return this.keyName;
	}

	/**
	 * set方法。
	 */
	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
    
	/**
	 * get方法。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。
	 */
	public String getRuleId() {
		return this.ruleId;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getStatusText() {
		return this.statusText;
	}

	/**
	 * set方法。
	 */
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
    
	/**
	 * get方法。
	 */
	public String getThemeName() {
		return this.themeName;
	}

	/**
	 * set方法。
	 */
	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}
    










}







