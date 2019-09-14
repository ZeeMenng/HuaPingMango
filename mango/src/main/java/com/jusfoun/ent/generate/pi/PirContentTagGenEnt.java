package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:23
 * @description 实体类PirContentTagGenEnt，自动生成。CMS内容标签关联表
 */

public class PirContentTagGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tagId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。
	 */
	public String getContentId() {
		return this.contentId;
	}

	/**
	 * set方法。
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
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
    
	/**
	 * get方法。
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。
	 */
	public String getTagId() {
		return this.tagId;
	}

	/**
	 * set方法。
	 */
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
    

    //一对多关系中，多端数据列表

}







