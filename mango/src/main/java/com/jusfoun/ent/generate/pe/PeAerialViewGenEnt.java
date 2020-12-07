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
 * @description 实体类PeAerialViewGenEnt，自动生成。鸟瞰图
 */

public class PeAerialViewGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="活跃度",hidden=false,required=false)
    private Integer activity;
    @ApiModelProperty(value="记录添加时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="内容",hidden=false,required=false)
    private String contet;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private Byte isRecommendCode;
    @ApiModelProperty(value="是否轮播。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=false)
    private Byte isSlideshowCode;
    @ApiModelProperty(value="访问量",hidden=false,required=false)
    private Integer pageView;
    @ApiModelProperty(value="图片信息。外键，对应通用资源表（Resource）的主键。",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="图片信息。路径，对应通用资源表（Resource）的路径（Path）字段。",hidden=false,required=false)
    private String resourcePath;
    @ApiModelProperty(value="标题",hidden=false,required=false)
    private String title;
    @ApiModelProperty(value="记录修改时间。",hidden=false,required=false)
    private Date updateTime;
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
	 * get方法。记录添加时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录添加时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
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
	 * get方法。是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsRecommendCode() {
		return this.isRecommendCode;
	}

	/**
	 * set方法。是否推荐。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsRecommendCode(Byte isRecommendCode) {
		this.isRecommendCode = isRecommendCode;
	}
    
	/**
	 * get方法。是否轮播。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsSlideshowCode() {
		return this.isSlideshowCode;
	}

	/**
	 * set方法。是否轮播。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsSlideshowCode(Byte isSlideshowCode) {
		this.isSlideshowCode = isSlideshowCode;
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
	 * get方法。图片信息。外键，对应通用资源表（Resource）的主键。
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。图片信息。外键，对应通用资源表（Resource）的主键。
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    
	/**
	 * get方法。图片信息。路径，对应通用资源表（Resource）的路径（Path）字段。
	 */
	public String getResourcePath() {
		return this.resourcePath;
	}

	/**
	 * set方法。图片信息。路径，对应通用资源表（Resource）的路径（Path）字段。
	 */
	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
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
    
	/**
	 * get方法。记录修改时间。
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。记录修改时间。
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    

    //一对多关系中，多端数据列表

}







