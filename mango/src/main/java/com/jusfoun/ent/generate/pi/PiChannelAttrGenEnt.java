package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:24
 * @description 实体类PiChannelAttrGenEnt，自动生成。CMS栏目扩展属性表
 */

public class PiChannelAttrGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String attrName;
    @ApiModelProperty(value="值",hidden=false,required=false)
    private String attrValue;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。名称
	 */
	public String getAttrName() {
		return this.attrName;
	}

	/**
	 * set方法。名称
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
    
	/**
	 * get方法。值
	 */
	public String getAttrValue() {
		return this.attrValue;
	}

	/**
	 * set方法。值
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
	 * get方法。主键
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键
	 */
	public void setId(String id) {
		this.id = id;
	}
    

    //一对多关系中，多端数据列表

}







