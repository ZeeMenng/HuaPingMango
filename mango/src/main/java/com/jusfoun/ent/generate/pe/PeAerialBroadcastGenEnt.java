package com.jusfoun.ent.generate.pe;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:04
 * @description 实体类PeAerialBroadcastGenEnt，自动生成。鸟瞰华坪轮播图
 */

public class PeAerialBroadcastGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="活跃度",hidden=false,required=true)
    private Integer activity;
    @ApiModelProperty(value="内容",hidden=false,required=false)
    private String contet;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="访问量",hidden=false,required=false)
    private Integer pageView;
    @ApiModelProperty(value="文件信息表id",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="文件信息表id 小图标",hidden=false,required=false)
    private String smallResourceId;
    @ApiModelProperty(value="标题",hidden=false,required=false)
    private String title;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。内容
	 */
	public String getContet() {
		return this.contet;
	}

	/**
	 * set方法。内容
	 */
	public void setContet(String contet) {
		this.contet = contet;
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
	 * get方法。文件信息表id
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。文件信息表id
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    
	/**
	 * get方法。文件信息表id 小图标
	 */
	public String getSmallResourceId() {
		return this.smallResourceId;
	}

	/**
	 * set方法。文件信息表id 小图标
	 */
	public void setSmallResourceId(String smallResourceId) {
		this.smallResourceId = smallResourceId;
	}
    
	/**
	 * get方法。标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * set方法。标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
    

    //一对多关系中，多端数据列表

}







