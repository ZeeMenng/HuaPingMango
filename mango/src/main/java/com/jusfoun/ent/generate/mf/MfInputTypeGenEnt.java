package com.jusfoun.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:59
 * @description 实体类MfInputTypeGenEnt，自动生成。投入品种类监管
 */

public class MfInputTypeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否有问题,0有问题，1没问题",hidden=false,required=false)
    private String issue;
    @ApiModelProperty(value="质量检测阀值",hidden=false,required=false)
    private Integer qualityThreshold;
    @ApiModelProperty(value="投入品种类",hidden=false,required=false)
    private Byte typeInput;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。对应通用字段表id
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。对应通用字段表id
	 */
	public void setCommonFieldId(String commonFieldId) {
		this.commonFieldId = commonFieldId;
	}
    
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
	 * get方法。是否有问题,0有问题，1没问题
	 */
	public String getIssue() {
		return this.issue;
	}

	/**
	 * set方法。是否有问题,0有问题，1没问题
	 */
	public void setIssue(String issue) {
		this.issue = issue;
	}
    
	/**
	 * get方法。质量检测阀值
	 */
	public Integer getQualityThreshold() {
		return this.qualityThreshold;
	}

	/**
	 * set方法。质量检测阀值
	 */
	public void setQualityThreshold(Integer qualityThreshold) {
		this.qualityThreshold = qualityThreshold;
	}
    
	/**
	 * get方法。投入品种类
	 */
	public Byte getTypeInput() {
		return this.typeInput;
	}

	/**
	 * set方法。投入品种类
	 */
	public void setTypeInput(Byte typeInput) {
		this.typeInput = typeInput;
	}
    

    //一对多关系中，多端数据列表

}







