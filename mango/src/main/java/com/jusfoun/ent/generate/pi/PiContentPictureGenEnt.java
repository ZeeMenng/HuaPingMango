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
 * @description 实体类PiContentPictureGenEnt，自动生成。CMS内容图片表
 */

public class PiContentPictureGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="描述",hidden=false,required=false)
    private String description;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="图片地址",hidden=false,required=false)
    private String imgPath;
    @ApiModelProperty(value="排列顺序",hidden=false,required=false)
    private Integer priority;
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
	 * get方法。描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * set方法。描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * get方法。图片地址
	 */
	public String getImgPath() {
		return this.imgPath;
	}

	/**
	 * set方法。图片地址
	 */
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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
    

    //一对多关系中，多端数据列表

}







