package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:16
 * @description 实体类PiContentTagGenEnt，自动生成。CMS内容TAG表
 */

public class PiContentTagGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="被引用的次数",hidden=false,required=false)
    private Integer refCounter;
    @ApiModelProperty(value="tag名称",hidden=false,required=false)
    private String tagName;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。tag名称
	 */
	public String getTagName() {
		return this.tagName;
	}

	/**
	 * set方法。tag名称
	 */
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
    

    //一对多关系中，多端数据列表

}







