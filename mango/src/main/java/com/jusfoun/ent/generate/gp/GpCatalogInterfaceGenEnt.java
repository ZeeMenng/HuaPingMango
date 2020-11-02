package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.jusfoun.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/10/21 21:21:14
 * @description 实体类GpCatalogInterfaceGenEnt，自动生成。接口分类字典管理存放接口分类信息，支持树形分级分类，主要但不限于业务上的分类方式，支持同时对接口进行多种分类。
 */

public class GpCatalogInterfaceGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="分类方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。",allowableValues="0,1",hidden=false,required=false)
    private Byte categoryCode;
    @ApiModelProperty(value="类别编码。非空唯一，十位字符串，0000000000，可继续扩展，每两位代表一级编码，目前最多支持到五级分类。",hidden=false,required=false)
    private String code;
    @ApiModelProperty(value="父级编码。父级类别编码，顶级类别的父级编码为0000000000。",allowableValues="0,1",hidden=false,required=false)
    private String fartherCode;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="级别。类别在层级关系中所属等级。",hidden=false,required=false)
    private Byte level;
    @ApiModelProperty(value="类别名称。",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="备注。",hidden=false,required=false)
    private String remark;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。分类方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。
	 */
	public Byte getCategoryCode() {
		return this.categoryCode;
	}

	/**
	 * set方法。分类方式。编码，对应数据字典表（dictionary）中的编码字段（code）。目前先定义两种分类方式：1按业务分类，2按请求方式分类。
	 */
	public void setCategoryCode(Byte categoryCode) {
		this.categoryCode = categoryCode;
	}
    
	/**
	 * get方法。类别编码。非空唯一，十位字符串，0000000000，可继续扩展，每两位代表一级编码，目前最多支持到五级分类。
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * set方法。类别编码。非空唯一，十位字符串，0000000000，可继续扩展，每两位代表一级编码，目前最多支持到五级分类。
	 */
	public void setCode(String code) {
		this.code = code;
	}
    
	/**
	 * get方法。父级编码。父级类别编码，顶级类别的父级编码为0000000000。
	 */
	public String getFartherCode() {
		return this.fartherCode;
	}

	/**
	 * set方法。父级编码。父级类别编码，顶级类别的父级编码为0000000000。
	 */
	public void setFartherCode(String fartherCode) {
		this.fartherCode = fartherCode;
	}
    
	/**
	 * get方法。主键。
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键。
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。级别。类别在层级关系中所属等级。
	 */
	public Byte getLevel() {
		return this.level;
	}

	/**
	 * set方法。级别。类别在层级关系中所属等级。
	 */
	public void setLevel(Byte level) {
		this.level = level;
	}
    
	/**
	 * get方法。类别名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。类别名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。备注。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    










}







