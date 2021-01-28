package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:34
 * @description 实体类MfInputTypeGenEnt，自动生成。投入品种类监管
 */

public class MfInputTypeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String issue;
    @ApiModelProperty(value="质量检测阀值",hidden=false,required=false)
    private Integer qualityThreshold;
    @ApiModelProperty(value="投入品种类",hidden=false,required=false)
    private Byte typeInput;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。
	 */
	public void setCommonFieldId(String commonFieldId) {
		this.commonFieldId = commonFieldId;
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
	public String getIssue() {
		return this.issue;
	}

	/**
	 * set方法。
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
    










}







