package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:48
 * @description 实体类DarTemplateTargetGenEnt，自动生成。模板指标中间表
 */

public class DarTemplateTargetGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排序",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String targetId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String templateId;

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
	public String getTargetId() {
		return this.targetId;
	}

	/**
	 * set方法。
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
    
	/**
	 * get方法。
	 */
	public String getTemplateId() {
		return this.templateId;
	}

	/**
	 * set方法。
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
    










}







