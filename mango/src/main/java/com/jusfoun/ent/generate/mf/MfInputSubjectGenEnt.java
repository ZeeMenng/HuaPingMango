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
 * @updateDate 2020/8/11 11:43:58
 * @description 实体类MfInputSubjectGenEnt，自动生成。投入品主体监管表
 */

public class MfInputSubjectGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否被处罚，0没处罚，1处罚",hidden=false,required=false)
    private Byte punish;
    @ApiModelProperty(value="投入品主体种类",hidden=false,required=false)
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
	 * get方法。是否被处罚，0没处罚，1处罚
	 */
	public Byte getPunish() {
		return this.punish;
	}

	/**
	 * set方法。是否被处罚，0没处罚，1处罚
	 */
	public void setPunish(Byte punish) {
		this.punish = punish;
	}
    
	/**
	 * get方法。投入品主体种类
	 */
	public Byte getTypeInput() {
		return this.typeInput;
	}

	/**
	 * set方法。投入品主体种类
	 */
	public void setTypeInput(Byte typeInput) {
		this.typeInput = typeInput;
	}
    

    //一对多关系中，多端数据列表

}







