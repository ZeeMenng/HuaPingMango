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
 * @updateDate 2020/8/11 11:44:13
 * @description 实体类PiChannelModelGenEnt，自动生成。CMS栏目可选内容模型表
 */

public class PiChannelModelGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String channelId;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="模型id",hidden=false,required=true)
    private String modelId;
    @ApiModelProperty(value="排序",hidden=false,required=true)
    private Integer priority;
    @ApiModelProperty(value="内容模板",hidden=false,required=false)
    private String tplContent;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
    
	/**
	 * get方法。模型id
	 */
	public String getModelId() {
		return this.modelId;
	}

	/**
	 * set方法。模型id
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
    
	/**
	 * get方法。排序
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排序
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。内容模板
	 */
	public String getTplContent() {
		return this.tplContent;
	}

	/**
	 * set方法。内容模板
	 */
	public void setTplContent(String tplContent) {
		this.tplContent = tplContent;
	}
    

    //一对多关系中，多端数据列表

}







