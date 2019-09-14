package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:25
 * @description 实体类PiContentCountGenEnt，自动生成。CMS内容计数表
 */

public class PiContentCountGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="总评论数",hidden=false,required=true)
    private Integer comments;
    @ApiModelProperty(value="日评论数",hidden=false,required=true)
    private Integer commentsDay;
    @ApiModelProperty(value="月评论数",hidden=false,required=true)
    private Integer commentsMonth;
    @ApiModelProperty(value="周评论数",hidden=false,required=true)
    private Integer commentsWeek;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String contentId;
    @ApiModelProperty(value="总下载数",hidden=false,required=true)
    private Integer downloads;
    @ApiModelProperty(value="日下载数",hidden=false,required=true)
    private Integer downloadsDay;
    @ApiModelProperty(value="月下载数",hidden=false,required=true)
    private Integer downloadsMonth;
    @ApiModelProperty(value="周下载数",hidden=false,required=true)
    private Integer downloadsWeek;
    @ApiModelProperty(value="总踩数",hidden=false,required=true)
    private Integer downs;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="总顶数",hidden=false,required=true)
    private Integer ups;
    @ApiModelProperty(value="日顶数",hidden=false,required=true)
    private Integer upsDay;
    @ApiModelProperty(value="月顶数",hidden=false,required=true)
    private Integer upsMonth;
    @ApiModelProperty(value="周顶数",hidden=false,required=true)
    private Integer upsWeek;
    @ApiModelProperty(value="总访问数",hidden=false,required=true)
    private Integer views;
    @ApiModelProperty(value="日访问数",hidden=false,required=true)
    private Integer viewsDay;
    @ApiModelProperty(value="月访问数",hidden=false,required=true)
    private Integer viewsMonth;
    @ApiModelProperty(value="周访问数",hidden=false,required=true)
    private Integer viewsWeek;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。总评论数
	 */
	public Integer getComments() {
		return this.comments;
	}

	/**
	 * set方法。总评论数
	 */
	public void setComments(Integer comments) {
		this.comments = comments;
	}
    
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
	 * get方法。月评论数
	 */
	public Integer getCommentsMonth() {
		return this.commentsMonth;
	}

	/**
	 * set方法。月评论数
	 */
	public void setCommentsMonth(Integer commentsMonth) {
		this.commentsMonth = commentsMonth;
	}
    
	/**
	 * get方法。周评论数
	 */
	public Integer getCommentsWeek() {
		return this.commentsWeek;
	}

	/**
	 * set方法。周评论数
	 */
	public void setCommentsWeek(Integer commentsWeek) {
		this.commentsWeek = commentsWeek;
	}
    
	/**
	 * get方法。
	 */
	public String getContentId() {
		return this.contentId;
	}

	/**
	 * set方法。
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
    
	/**
	 * get方法。总下载数
	 */
	public Integer getDownloads() {
		return this.downloads;
	}

	/**
	 * set方法。总下载数
	 */
	public void setDownloads(Integer downloads) {
		this.downloads = downloads;
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
	 * get方法。月下载数
	 */
	public Integer getDownloadsMonth() {
		return this.downloadsMonth;
	}

	/**
	 * set方法。月下载数
	 */
	public void setDownloadsMonth(Integer downloadsMonth) {
		this.downloadsMonth = downloadsMonth;
	}
    
	/**
	 * get方法。周下载数
	 */
	public Integer getDownloadsWeek() {
		return this.downloadsWeek;
	}

	/**
	 * set方法。周下载数
	 */
	public void setDownloadsWeek(Integer downloadsWeek) {
		this.downloadsWeek = downloadsWeek;
	}
    
	/**
	 * get方法。总踩数
	 */
	public Integer getDowns() {
		return this.downs;
	}

	/**
	 * set方法。总踩数
	 */
	public void setDowns(Integer downs) {
		this.downs = downs;
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
	 * get方法。总顶数
	 */
	public Integer getUps() {
		return this.ups;
	}

	/**
	 * set方法。总顶数
	 */
	public void setUps(Integer ups) {
		this.ups = ups;
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
	 * get方法。月顶数
	 */
	public Integer getUpsMonth() {
		return this.upsMonth;
	}

	/**
	 * set方法。月顶数
	 */
	public void setUpsMonth(Integer upsMonth) {
		this.upsMonth = upsMonth;
	}
    
	/**
	 * get方法。周顶数
	 */
	public Integer getUpsWeek() {
		return this.upsWeek;
	}

	/**
	 * set方法。周顶数
	 */
	public void setUpsWeek(Integer upsWeek) {
		this.upsWeek = upsWeek;
	}
    
	/**
	 * get方法。总访问数
	 */
	public Integer getViews() {
		return this.views;
	}

	/**
	 * set方法。总访问数
	 */
	public void setViews(Integer views) {
		this.views = views;
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
    
	/**
	 * get方法。月访问数
	 */
	public Integer getViewsMonth() {
		return this.viewsMonth;
	}

	/**
	 * set方法。月访问数
	 */
	public void setViewsMonth(Integer viewsMonth) {
		this.viewsMonth = viewsMonth;
	}
    
	/**
	 * get方法。周访问数
	 */
	public Integer getViewsWeek() {
		return this.viewsWeek;
	}

	/**
	 * set方法。周访问数
	 */
	public void setViewsWeek(Integer viewsWeek) {
		this.viewsWeek = viewsWeek;
	}
    

    //一对多关系中，多端数据列表

}







