package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/3 14:15:10
 * @description 实体类PiContentExtGenEnt，自动生成。CMS内容扩展表
 */

public class PiContentExtGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="记录创建人。外键，引用系统用户表（user）的主键。",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="作者",hidden=false,required=false)
    private String author;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="内容图片",hidden=false,required=false)
    private String contentImg;
    @ApiModelProperty(value="描述",hidden=false,required=false)
    private String description;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否加粗",hidden=false,required=true)
    private Byte isBold;
    @ApiModelProperty(value="外部链接",hidden=false,required=false)
    private String link;
    @ApiModelProperty(value="媒体路径",hidden=false,required=false)
    private String mediaPath;
    @ApiModelProperty(value="媒体类型",hidden=false,required=false)
    private String mediaType;
    @ApiModelProperty(value="需要重新生成静态页",hidden=false,required=true)
    private Byte needRegenerate;
    @ApiModelProperty(value="来源",hidden=false,required=false)
    private String origin;
    @ApiModelProperty(value="来源链接",hidden=false,required=false)
    private String originUrl;
    @ApiModelProperty(value="发布日期",hidden=false,required=true)
    private Date releaseDate;
    @ApiModelProperty(value="简短标题",hidden=false,required=false)
    private String shortTitle;
    @ApiModelProperty(value="标题",hidden=false,required=false)
    private String title;
    @ApiModelProperty(value="标题颜色",hidden=false,required=false)
    private String titleColor;
    @ApiModelProperty(value="标题图片",hidden=false,required=false)
    private String titleImg;
    @ApiModelProperty(value="指定模板",hidden=false,required=false)
    private String tplContent;
    @ApiModelProperty(value="类型图片",hidden=false,required=false)
    private String typeImg;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。记录创建时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录创建时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。记录创建人。外键，引用系统用户表（user）的主键。
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。记录创建人。外键，引用系统用户表（user）的主键。
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。作者
	 */
	public String getAuthor() {
		return this.author;
	}

	/**
	 * set方法。作者
	 */
	public void setAuthor(String author) {
		this.author = author;
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
	 * get方法。内容图片
	 */
	public String getContentImg() {
		return this.contentImg;
	}

	/**
	 * set方法。内容图片
	 */
	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}
    
	/**
	 * get方法。描述
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * set方法。描述
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * get方法。是否加粗
	 */
	public Byte getIsBold() {
		return this.isBold;
	}

	/**
	 * set方法。是否加粗
	 */
	public void setIsBold(Byte isBold) {
		this.isBold = isBold;
	}
    
	/**
	 * get方法。外部链接
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * set方法。外部链接
	 */
	public void setLink(String link) {
		this.link = link;
	}
    
	/**
	 * get方法。媒体路径
	 */
	public String getMediaPath() {
		return this.mediaPath;
	}

	/**
	 * set方法。媒体路径
	 */
	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}
    
	/**
	 * get方法。媒体类型
	 */
	public String getMediaType() {
		return this.mediaType;
	}

	/**
	 * set方法。媒体类型
	 */
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
    
	/**
	 * get方法。需要重新生成静态页
	 */
	public Byte getNeedRegenerate() {
		return this.needRegenerate;
	}

	/**
	 * set方法。需要重新生成静态页
	 */
	public void setNeedRegenerate(Byte needRegenerate) {
		this.needRegenerate = needRegenerate;
	}
    
	/**
	 * get方法。来源
	 */
	public String getOrigin() {
		return this.origin;
	}

	/**
	 * set方法。来源
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
    
	/**
	 * get方法。来源链接
	 */
	public String getOriginUrl() {
		return this.originUrl;
	}

	/**
	 * set方法。来源链接
	 */
	public void setOriginUrl(String originUrl) {
		this.originUrl = originUrl;
	}
    
	/**
	 * get方法。发布日期
	 */
	public Date getReleaseDate() {
		return this.releaseDate;
	}

	/**
	 * set方法。发布日期
	 */
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
    
	/**
	 * get方法。简短标题
	 */
	public String getShortTitle() {
		return this.shortTitle;
	}

	/**
	 * set方法。简短标题
	 */
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
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
	 * get方法。标题颜色
	 */
	public String getTitleColor() {
		return this.titleColor;
	}

	/**
	 * set方法。标题颜色
	 */
	public void setTitleColor(String titleColor) {
		this.titleColor = titleColor;
	}
    
	/**
	 * get方法。标题图片
	 */
	public String getTitleImg() {
		return this.titleImg;
	}

	/**
	 * set方法。标题图片
	 */
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
    
	/**
	 * get方法。指定模板
	 */
	public String getTplContent() {
		return this.tplContent;
	}

	/**
	 * set方法。指定模板
	 */
	public void setTplContent(String tplContent) {
		this.tplContent = tplContent;
	}
    
	/**
	 * get方法。类型图片
	 */
	public String getTypeImg() {
		return this.typeImg;
	}

	/**
	 * set方法。类型图片
	 */
	public void setTypeImg(String typeImg) {
		this.typeImg = typeImg;
	}
    

    //一对多关系中，多端数据列表

}







