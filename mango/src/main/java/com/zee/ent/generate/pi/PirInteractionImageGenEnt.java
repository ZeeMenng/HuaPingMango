package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:49
 * @description 实体类PirInteractionImageGenEnt，自动生成。pir_interaction_image
 */

public class PirInteractionImageGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String interactionId;
    @ApiModelProperty(value="是否为标题图。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。",allowableValues="0,1",hidden=false,required=false)
    private Byte isTitleImgCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resourceId;

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
	 * get方法。
	 */
	public String getInteractionId() {
		return this.interactionId;
	}

	/**
	 * set方法。
	 */
	public void setInteractionId(String interactionId) {
		this.interactionId = interactionId;
	}
    
	/**
	 * get方法。是否为标题图。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。
	 */
	public Byte getIsTitleImgCode() {
		return this.isTitleImgCode;
	}

	/**
	 * set方法。是否为标题图。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。
	 */
	public void setIsTitleImgCode(Byte isTitleImgCode) {
		this.isTitleImgCode = isTitleImgCode;
	}
    
	/**
	 * get方法。
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    










}







