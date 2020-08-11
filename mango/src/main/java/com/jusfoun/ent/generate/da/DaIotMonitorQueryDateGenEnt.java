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
 * @updateDate 2020/8/11 11:43:25
 * @description 实体类DaIotMonitorQueryDateGenEnt，自动生成。物联网数据最新查询时间表
 */

public class DaIotMonitorQueryDateGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private Date lastQueryDate;
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
	 * get方法。
	 */
	public Date getLastQueryDate() {
		return this.lastQueryDate;
	}

	/**
	 * set方法。
	 */
	public void setLastQueryDate(Date lastQueryDate) {
		this.lastQueryDate = lastQueryDate;
	}
    

    //一对多关系中，多端数据列表

}







