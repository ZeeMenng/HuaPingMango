package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:18
 * @description 实体类GpDictionaryGenEnt，自动生成。字典信息。
 */

public class GpDictionaryGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="字典编码",hidden=false,required=false)
    private Byte code;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排列顺序",hidden=false,required=false)
    private Integer priority;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="字典名称",hidden=false,required=false)
    private String text;
    @ApiModelProperty(value="外键，对应字典类型表(dictionary)主键",hidden=false,required=true)
    private String typeId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。字典编码
	 */
	public Byte getCode() {
		return this.code;
	}

	/**
	 * set方法。字典编码
	 */
	public void setCode(Byte code) {
		this.code = code;
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
	 * get方法。排列顺序
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排列顺序
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
	 * get方法。字典名称
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * set方法。字典名称
	 */
	public void setText(String text) {
		this.text = text;
	}
    
	/**
	 * get方法。外键，对应字典类型表(dictionary)主键
	 */
	public String getTypeId() {
		return this.typeId;
	}

	/**
	 * set方法。外键，对应字典类型表(dictionary)主键
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
    

    //一对多关系中，多端数据列表

}







