package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:53
 * @description 实体类PiChannelModelGenEnt，自动生成。CMS栏目可选内容模型表
 */

public class PiChannelModelGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String modelId;
    @ApiModelProperty(value="排序",hidden=false,required=true)
    private Integer priority;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tplContent;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。
	 */
	public String getTplContent() {
		return this.tplContent;
	}

	/**
	 * set方法。
	 */
	public void setTplContent(String tplContent) {
		this.tplContent = tplContent;
	}
    










}







