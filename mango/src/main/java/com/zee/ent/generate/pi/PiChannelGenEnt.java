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
 * @description 实体类PiChannelGenEnt，自动生成。CMS栏目表
 */

public class PiChannelGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelPath;
    @ApiModelProperty(value="是否有内容",hidden=false,required=true)
    private Byte hasContent;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否显示",hidden=false,required=true)
    private Byte isDisplay;
    @ApiModelProperty(value="树左边",hidden=false,required=false)
    private Integer lft;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String modelId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String parentId;
    @ApiModelProperty(value="排列顺序",hidden=false,required=true)
    private Integer priority;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String relationId;
    @ApiModelProperty(value="树右边",hidden=false,required=false)
    private Integer rgt;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String siteId;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getChannelPath() {
		return this.channelPath;
	}

	/**
	 * set方法。
	 */
	public void setChannelPath(String channelPath) {
		this.channelPath = channelPath;
	}
    
	/**
	 * get方法。是否有内容
	 */
	public Byte getHasContent() {
		return this.hasContent;
	}

	/**
	 * set方法。是否有内容
	 */
	public void setHasContent(Byte hasContent) {
		this.hasContent = hasContent;
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
	 * get方法。是否显示
	 */
	public Byte getIsDisplay() {
		return this.isDisplay;
	}

	/**
	 * set方法。是否显示
	 */
	public void setIsDisplay(Byte isDisplay) {
		this.isDisplay = isDisplay;
	}
    
	/**
	 * get方法。树左边
	 */
	public Integer getLft() {
		return this.lft;
	}

	/**
	 * set方法。树左边
	 */
	public void setLft(Integer lft) {
		this.lft = lft;
	}
    
	/**
	 * get方法。
	 */
	public String getModelId() {
		return this.modelId;
	}

	/**
	 * set方法。
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
    
	/**
	 * get方法。
	 */
	public String getParentId() {
		return this.parentId;
	}

	/**
	 * set方法。
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
    
	/**
	 * get方法。排列顺序
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排列顺序
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。
	 */
	public String getRelationId() {
		return this.relationId;
	}

	/**
	 * set方法。
	 */
	public void setRelationId(String relationId) {
		this.relationId = relationId;
	}
    
	/**
	 * get方法。树右边
	 */
	public Integer getRgt() {
		return this.rgt;
	}

	/**
	 * set方法。树右边
	 */
	public void setRgt(Integer rgt) {
		this.rgt = rgt;
	}
    
	/**
	 * get方法。
	 */
	public String getSiteId() {
		return this.siteId;
	}

	/**
	 * set方法。
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
    










}







