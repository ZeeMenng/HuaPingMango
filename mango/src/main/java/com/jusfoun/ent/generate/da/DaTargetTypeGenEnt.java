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
 * @updateDate 2020/8/11 11:43:34
 * @description 实体类DaTargetTypeGenEnt，自动生成。指标类型
 */

public class DaTargetTypeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="创建人",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="常量名称",hidden=false,required=false)
    private String constantName;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排序",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="指标名称",hidden=false,required=false)
    private String targetName;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。创建人
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。创建人
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。常量名称
	 */
	public String getConstantName() {
		return this.constantName;
	}

	/**
	 * set方法。常量名称
	 */
	public void setConstantName(String constantName) {
		this.constantName = constantName;
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
	 * get方法。备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。指标名称
	 */
	public String getTargetName() {
		return this.targetName;
	}

	/**
	 * set方法。指标名称
	 */
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
    

    //一对多关系中，多端数据列表

}







