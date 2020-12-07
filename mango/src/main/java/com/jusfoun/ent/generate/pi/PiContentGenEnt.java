package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:14
 * @description 实体类PiContentGenEnt，自动生成。CMS内容表
 */

public class PiContentGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="日评论数",hidden=false,required=true)
    private Integer commentsDay;
    @ApiModelProperty(value="日下载数",hidden=false,required=true)
    private Integer downloadsDay;
    @ApiModelProperty(value="是否有标题图",hidden=false,required=true)
    private Byte hasTitleImg;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否推荐,0:否，1：是",hidden=false,required=true)
    private Byte isRecommend;
    @ApiModelProperty(value="模型ID",hidden=false,required=false)
    private String modelId;
    @ApiModelProperty(value="得分",hidden=false,required=true)
    private Integer score;
    @ApiModelProperty(value="站点ID",hidden=false,required=false)
    private String siteId;
    @ApiModelProperty(value="排序日期",hidden=false,required=false)
    private Date sortDate;
    @ApiModelProperty(value="状态(0:草稿;1:审核中;2:审核通过;3:回收站；4:投稿)",hidden=false,required=true)
    private Byte status;
    @ApiModelProperty(value="固顶级别",hidden=false,required=true)
    private Byte topLevel;
    @ApiModelProperty(value="属性ID",hidden=false,required=false)
    private String typeId;
    @ApiModelProperty(value="日顶数",hidden=false,required=true)
    private Integer upsDay;
    @ApiModelProperty(value="用户ID",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="日访问数",hidden=false,required=true)
    private Integer viewsDay;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。日评论数
	 */
	public Integer getCommentsDay() {
		return this.commentsDay;
	}

	/**
	 * set方法。日评论数
	 */
	public void setCommentsDay(Integer commentsDay) {
		this.commentsDay = commentsDay;
	}
    
	/**
	 * get方法。日下载数
	 */
	public Integer getDownloadsDay() {
		return this.downloadsDay;
	}

	/**
	 * set方法。日下载数
	 */
	public void setDownloadsDay(Integer downloadsDay) {
		this.downloadsDay = downloadsDay;
	}
    
	/**
	 * get方法。是否有标题图
	 */
	public Byte getHasTitleImg() {
		return this.hasTitleImg;
	}

	/**
	 * set方法。是否有标题图
	 */
	public void setHasTitleImg(Byte hasTitleImg) {
		this.hasTitleImg = hasTitleImg;
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
	 * get方法。是否推荐,0:否，1：是
	 */
	public Byte getIsRecommend() {
		return this.isRecommend;
	}

	/**
	 * set方法。是否推荐,0:否，1：是
	 */
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}
    
	/**
	 * get方法。模型ID
	 */
	public String getModelId() {
		return this.modelId;
	}

	/**
	 * set方法。模型ID
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}
    
	/**
	 * get方法。得分
	 */
	public Integer getScore() {
		return this.score;
	}

	/**
	 * set方法。得分
	 */
	public void setScore(Integer score) {
		this.score = score;
	}
    
	/**
	 * get方法。站点ID
	 */
	public String getSiteId() {
		return this.siteId;
	}

	/**
	 * set方法。站点ID
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
    
	/**
	 * get方法。排序日期
	 */
	public Date getSortDate() {
		return this.sortDate;
	}

	/**
	 * set方法。排序日期
	 */
	public void setSortDate(Date sortDate) {
		this.sortDate = sortDate;
	}
    
	/**
	 * get方法。状态(0:草稿;1:审核中;2:审核通过;3:回收站；4:投稿)
	 */
	public Byte getStatus() {
		return this.status;
	}

	/**
	 * set方法。状态(0:草稿;1:审核中;2:审核通过;3:回收站；4:投稿)
	 */
	public void setStatus(Byte status) {
		this.status = status;
	}
    
	/**
	 * get方法。固顶级别
	 */
	public Byte getTopLevel() {
		return this.topLevel;
	}

	/**
	 * set方法。固顶级别
	 */
	public void setTopLevel(Byte topLevel) {
		this.topLevel = topLevel;
	}
    
	/**
	 * get方法。属性ID
	 */
	public String getTypeId() {
		return this.typeId;
	}

	/**
	 * set方法。属性ID
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
    
	/**
	 * get方法。日顶数
	 */
	public Integer getUpsDay() {
		return this.upsDay;
	}

	/**
	 * set方法。日顶数
	 */
	public void setUpsDay(Integer upsDay) {
		this.upsDay = upsDay;
	}
    
	/**
	 * get方法。用户ID
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。用户ID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	/**
	 * get方法。日访问数
	 */
	public Integer getViewsDay() {
		return this.viewsDay;
	}

	/**
	 * set方法。日访问数
	 */
	public void setViewsDay(Integer viewsDay) {
		this.viewsDay = viewsDay;
	}
    

    //一对多关系中，多端数据列表

}







