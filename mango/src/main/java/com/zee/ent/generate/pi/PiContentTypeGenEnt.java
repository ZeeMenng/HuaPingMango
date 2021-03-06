package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:10:00
 * @description 实体类PiContentTypeGenEnt，自动生成。CMS内容类型表
 */

public class PiContentTypeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="是否有图片",hidden=false,required=false)
    private Byte hasImage;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="图片高",hidden=false,required=false)
    private Integer imgHeight;
    @ApiModelProperty(value="图片宽",hidden=false,required=false)
    private Integer imgWidth;
    @ApiModelProperty(value="是否禁用",hidden=false,required=false)
    private Byte isDisabled;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。是否有图片
	 */
	public Byte getHasImage() {
		return this.hasImage;
	}

	/**
	 * set方法。是否有图片
	 */
	public void setHasImage(Byte hasImage) {
		this.hasImage = hasImage;
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
	 * get方法。图片高
	 */
	public Integer getImgHeight() {
		return this.imgHeight;
	}

	/**
	 * set方法。图片高
	 */
	public void setImgHeight(Integer imgHeight) {
		this.imgHeight = imgHeight;
	}
    
	/**
	 * get方法。图片宽
	 */
	public Integer getImgWidth() {
		return this.imgWidth;
	}

	/**
	 * set方法。图片宽
	 */
	public void setImgWidth(Integer imgWidth) {
		this.imgWidth = imgWidth;
	}
    
	/**
	 * get方法。是否禁用
	 */
	public Byte getIsDisabled() {
		return this.isDisabled;
	}

	/**
	 * set方法。是否禁用
	 */
	public void setIsDisabled(Byte isDisabled) {
		this.isDisabled = isDisabled;
	}
    
	/**
	 * get方法。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。
	 */
	public void setName(String name) {
		this.name = name;
	}
    










}







