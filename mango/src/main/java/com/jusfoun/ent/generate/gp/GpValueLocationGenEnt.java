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
 * @updateDate 2020/8/11 12:00:43
 * @description 实体类GpValueLocationGenEnt，自动生成。调用存储过程查询某个值在本数据库中的位置，记录相关信息到本表中。
 */

public class GpValueLocationGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="添加时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="值。",hidden=false,required=false)
    private String columnValue;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="原始表名",hidden=false,required=false)
    private String originTableName;
    @ApiModelProperty(value=" 备注。",hidden=false,required=false)
    private String remarks;
    @ApiModelProperty(value="值所在列名。",hidden=false,required=false)
    private String targetColumnName;
    @ApiModelProperty(value="值所属记录主键。",hidden=false,required=false)
    private String targetRecordId;
    @ApiModelProperty(value="值所在表名。",hidden=false,required=false)
    private String targetTableName;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。添加时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。值。
	 */
	public String getColumnValue() {
		return this.columnValue;
	}

	/**
	 * set方法。值。
	 */
	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
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
	 * get方法。原始表名
	 */
	public String getOriginTableName() {
		return this.originTableName;
	}

	/**
	 * set方法。原始表名
	 */
	public void setOriginTableName(String originTableName) {
		this.originTableName = originTableName;
	}
    
	/**
	 * get方法。 备注。
	 */
	public String getRemarks() {
		return this.remarks;
	}

	/**
	 * set方法。 备注。
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
    
	/**
	 * get方法。值所在列名。
	 */
	public String getTargetColumnName() {
		return this.targetColumnName;
	}

	/**
	 * set方法。值所在列名。
	 */
	public void setTargetColumnName(String targetColumnName) {
		this.targetColumnName = targetColumnName;
	}
    
	/**
	 * get方法。值所属记录主键。
	 */
	public String getTargetRecordId() {
		return this.targetRecordId;
	}

	/**
	 * set方法。值所属记录主键。
	 */
	public void setTargetRecordId(String targetRecordId) {
		this.targetRecordId = targetRecordId;
	}
    
	/**
	 * get方法。值所在表名。
	 */
	public String getTargetTableName() {
		return this.targetTableName;
	}

	/**
	 * set方法。值所在表名。
	 */
	public void setTargetTableName(String targetTableName) {
		this.targetTableName = targetTableName;
	}
    

    //一对多关系中，多端数据列表

}







