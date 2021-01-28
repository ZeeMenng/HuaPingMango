package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:10:00
 * @description 实体类PiExpertGenEnt，自动生成。专家表
 */

public class PiExpertGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String introduction;
    @ApiModelProperty(value="是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。",hidden=false,required=true)
    private Byte isRecommend;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String userId;

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
	public String getIntroduction() {
		return this.introduction;
	}

	/**
	 * set方法。
	 */
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
    
	/**
	 * get方法。是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public Byte getIsRecommend() {
		return this.isRecommend;
	}

	/**
	 * set方法。是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值1。
	 */
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}
    
	/**
	 * get方法。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    










}







