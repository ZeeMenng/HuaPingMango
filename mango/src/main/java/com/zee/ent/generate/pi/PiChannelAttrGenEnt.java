package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:51
 * @description 实体类PiChannelAttrGenEnt，自动生成。CMS栏目扩展属性表
 */

public class PiChannelAttrGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String attrName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String attrValue;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAttrName() {
		return this.attrName;
	}

	/**
	 * set方法。
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
    
	/**
	 * get方法。
	 */
	public String getAttrValue() {
		return this.attrValue;
	}

	/**
	 * set方法。
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
    
	/**
	 * get方法。
	 */
	public String getChannelId() {
		return this.channelId;
	}

	/**
	 * set方法。
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
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
    










}







