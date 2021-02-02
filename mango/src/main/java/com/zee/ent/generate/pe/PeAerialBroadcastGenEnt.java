package com.zee.ent.generate.pe;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:41
 * @description 实体类PeAerialBroadcastGenEnt，自动生成。鸟瞰华坪轮播图
 */

public class PeAerialBroadcastGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="活跃度",hidden=false,required=true)
    private Integer activity;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contet;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="访问量",hidden=false,required=false)
    private Integer pageView;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String smallResourceId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String title;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。活跃度
	 */
	public Integer getActivity() {
		return this.activity;
	}

	/**
	 * set方法。活跃度
	 */
	public void setActivity(Integer activity) {
		this.activity = activity;
	}
    
	/**
	 * get方法。
	 */
	public String getContet() {
		return this.contet;
	}

	/**
	 * set方法。
	 */
	public void setContet(String contet) {
		this.contet = contet;
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
	 * get方法。访问量
	 */
	public Integer getPageView() {
		return this.pageView;
	}

	/**
	 * set方法。访问量
	 */
	public void setPageView(Integer pageView) {
		this.pageView = pageView;
	}
    
	/**
	 * get方法。
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    
	/**
	 * get方法。
	 */
	public String getSmallResourceId() {
		return this.smallResourceId;
	}

	/**
	 * set方法。
	 */
	public void setSmallResourceId(String smallResourceId) {
		this.smallResourceId = smallResourceId;
	}
    
	/**
	 * get方法。
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * set方法。
	 */
	public void setTitle(String title) {
		this.title = title;
	}
    










}







