package com.jusfoun.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:08
 * @description 实体类DarTemplateTargetGenEnt，自动生成。模板指标中间表
 */

public class DarTemplateTargetGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排序",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="指标id",hidden=false,required=false)
    private String targetId;
    @ApiModelProperty(value="模板id",hidden=false,required=false)
    private String templateId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。主键id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键id
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
	 * get方法。指标id
	 */
	public String getTargetId() {
		return this.targetId;
	}

	/**
	 * set方法。指标id
	 */
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
    
	/**
	 * get方法。模板id
	 */
	public String getTemplateId() {
		return this.templateId;
	}

	/**
	 * set方法。模板id
	 */
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
    

    //一对多关系中，多端数据列表

}







