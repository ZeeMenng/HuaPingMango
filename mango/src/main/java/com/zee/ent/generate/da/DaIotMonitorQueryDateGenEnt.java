package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:03
 * @description 实体类DaIotMonitorQueryDateGenEnt，自动生成。物联网数据最新查询时间表
 */

public class DaIotMonitorQueryDateGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private Date lastQueryDate;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	public Date getLastQueryDate() {
		return this.lastQueryDate;
	}

	/**
	 * set方法。
	 */
	public void setLastQueryDate(Date lastQueryDate) {
		this.lastQueryDate = lastQueryDate;
	}
    










}







