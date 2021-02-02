package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:58
 * @description 实体类PiContentTagGenEnt，自动生成。CMS内容TAG表
 */

public class PiContentTagGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="被引用的次数",hidden=false,required=false)
    private Integer refCounter;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tagName;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。被引用的次数
	 */
	public Integer getRefCounter() {
		return this.refCounter;
	}

	/**
	 * set方法。被引用的次数
	 */
	public void setRefCounter(Integer refCounter) {
		this.refCounter = refCounter;
	}
    
	/**
	 * get方法。
	 */
	public String getTagName() {
		return this.tagName;
	}

	/**
	 * set方法。
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
    










}







