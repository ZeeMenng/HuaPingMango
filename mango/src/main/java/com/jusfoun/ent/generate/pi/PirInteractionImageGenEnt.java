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
 * @updateDate 2020/8/11 11:44:09
 * @description 实体类PirInteractionImageGenEnt，自动生成。pir_interaction_image
 */

public class PirInteractionImageGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="互动表id",hidden=false,required=false)
    private String interactionId;
    @ApiModelProperty(value="是否为标题图。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。",allowableValues="0,1",hidden=false,required=false)
    private Byte isTitleImgCode;
    @ApiModelProperty(value="资源表id",hidden=false,required=false)
    private String resourceId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。互动表id
	 */
	public String getInteractionId() {
		return this.interactionId;
	}

	/**
	 * set方法。互动表id
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
	 * get方法。资源表id
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。资源表id
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    

    //一对多关系中，多端数据列表

}







