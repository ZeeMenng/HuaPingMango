package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:43:47
 * @description 实体类GpDictionaryTypeGenEnt，自动生成。字典类型。
 */

public class GpDictionaryTypeGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="常量名称",hidden=false,required=false)
    private String constantName;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。名称
	 */
	public void setName(String name) {
		this.name = name;
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
    

    //一对多关系中，多端数据列表

}







