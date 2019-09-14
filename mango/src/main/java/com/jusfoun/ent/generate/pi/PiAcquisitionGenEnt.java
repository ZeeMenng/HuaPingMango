package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:23
 * @description 实体类PiAcquisitionGenEnt，自动生成。CMS采集任务表
 */

public class PiAcquisitionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="采集名称",hidden=false,required=false)
    private String acqName;
    @ApiModelProperty(value="作者结束",hidden=false,required=false)
    private String authorEnd;
    @ApiModelProperty(value="作者开始",hidden=false,required=false)
    private String authorStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="内容结束",hidden=false,required=false)
    private String contentEnd;
    @ApiModelProperty(value="内容地址补全url",hidden=false,required=false)
    private String contentPrefix;
    @ApiModelProperty(value="内容开始",hidden=false,required=false)
    private String contentStart;
    @ApiModelProperty(value="当前条数",hidden=false,required=false)
    private Integer currItem;
    @ApiModelProperty(value="当前号码",hidden=false,required=false)
    private Integer currNum;
    @ApiModelProperty(value="描述结束",hidden=false,required=false)
    private String descriptionEnd;
    @ApiModelProperty(value="描述开始",hidden=false,required=false)
    private String descriptionStart;
    @ApiModelProperty(value="动态地址",hidden=false,required=false)
    private String dynamicAddr;
    @ApiModelProperty(value="页码结束",hidden=false,required=false)
    private Integer dynamicEnd;
    @ApiModelProperty(value="页码开始",hidden=false,required=false)
    private Integer dynamicStart;
    @ApiModelProperty(value="停止时间",hidden=false,required=false)
    private Date endTime;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否采集图片",hidden=false,required=false)
    private Byte imgAcqu;
    @ApiModelProperty(value="图片地址补全url",hidden=false,required=false)
    private String imgPrefix;
    @ApiModelProperty(value="关键字结束",hidden=false,required=false)
    private String keywordsEnd;
    @ApiModelProperty(value="关键字开始",hidden=false,required=false)
    private String keywordsStart;
    @ApiModelProperty(value="内容链接结束",hidden=false,required=false)
    private String linkEnd;
    @ApiModelProperty(value="内容链接开始",hidden=false,required=false)
    private String linkStart;
    @ApiModelProperty(value="内容链接区结束",hidden=false,required=false)
    private String linksetEnd;
    @ApiModelProperty(value="内容链接区开始",hidden=false,required=false)
    private String linksetStart;
    @ApiModelProperty(value="来源结束",hidden=false,required=false)
    private String originEnd;
    @ApiModelProperty(value="来源开始",hidden=false,required=false)
    private String originStart;
    @ApiModelProperty(value="页面编码",hidden=false,required=false)
    private String pageEncoding;
    @ApiModelProperty(value="内容分页结束",hidden=false,required=false)
    private String paginationEnd;
    @ApiModelProperty(value="内容分页开始",hidden=false,required=false)
    private String paginationStart;
    @ApiModelProperty(value="暂停时间(毫秒)",hidden=false,required=false)
    private Integer pauseTime;
    @ApiModelProperty(value="采集列表",hidden=false,required=false)
    private String planList;
    @ApiModelProperty(value="队列",hidden=false,required=false)
    private Integer queue;
    @ApiModelProperty(value="发布时间结束",hidden=false,required=false)
    private String releaseTimeEnd;
    @ApiModelProperty(value="发布时间格式",hidden=false,required=false)
    private String releaseTimeFormat;
    @ApiModelProperty(value="发布时间开始",hidden=false,required=false)
    private String releaseTimeStart;
    @ApiModelProperty(value="重复类型",hidden=false,required=false)
    private String repeatCheckType;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String siteId;
    @ApiModelProperty(value="开始时间",hidden=false,required=false)
    private Date startTime;
    @ApiModelProperty(value="当前状态(0:静止;1:采集;2:暂停)",hidden=false,required=false)
    private Integer status;
    @ApiModelProperty(value="标题结束",hidden=false,required=false)
    private String titleEnd;
    @ApiModelProperty(value="标题开始",hidden=false,required=false)
    private String titleStart;
    @ApiModelProperty(value="每页总条数",hidden=false,required=false)
    private Integer totalItem;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String typeId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="浏览量结束",hidden=false,required=false)
    private String viewEnd;
    @ApiModelProperty(value="id后缀",hidden=false,required=false)
    private String viewIdEnd;
    @ApiModelProperty(value="id前缀",hidden=false,required=false)
    private String viewIdStart;
    @ApiModelProperty(value="浏览量动态访问地址",hidden=false,required=false)
    private String viewLink;
    @ApiModelProperty(value="浏览量开始",hidden=false,required=false)
    private String viewStart;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。采集名称
	 */
	public String getAcqName() {
		return this.acqName;
	}

	/**
	 * set方法。采集名称
	 */
	public void setAcqName(String acqName) {
		this.acqName = acqName;
	}
    
	/**
	 * get方法。作者结束
	 */
	public String getAuthorEnd() {
		return this.authorEnd;
	}

	/**
	 * set方法。作者结束
	 */
	public void setAuthorEnd(String authorEnd) {
		this.authorEnd = authorEnd;
	}
    
	/**
	 * get方法。作者开始
	 */
	public String getAuthorStart() {
		return this.authorStart;
	}

	/**
	 * set方法。作者开始
	 */
	public void setAuthorStart(String authorStart) {
		this.authorStart = authorStart;
	}
    
	/**
	 * get方法。
	 */
	public String getChannelId() {
		return this.channelId;
	}

	/**
	 * set方法。
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
    
	/**
	 * get方法。内容结束
	 */
	public String getContentEnd() {
		return this.contentEnd;
	}

	/**
	 * set方法。内容结束
	 */
	public void setContentEnd(String contentEnd) {
		this.contentEnd = contentEnd;
	}
    
	/**
	 * get方法。内容地址补全url
	 */
	public String getContentPrefix() {
		return this.contentPrefix;
	}

	/**
	 * set方法。内容地址补全url
	 */
	public void setContentPrefix(String contentPrefix) {
		this.contentPrefix = contentPrefix;
	}
    
	/**
	 * get方法。内容开始
	 */
	public String getContentStart() {
		return this.contentStart;
	}

	/**
	 * set方法。内容开始
	 */
	public void setContentStart(String contentStart) {
		this.contentStart = contentStart;
	}
    
	/**
	 * get方法。当前条数
	 */
	public Integer getCurrItem() {
		return this.currItem;
	}

	/**
	 * set方法。当前条数
	 */
	public void setCurrItem(Integer currItem) {
		this.currItem = currItem;
	}
    
	/**
	 * get方法。当前号码
	 */
	public Integer getCurrNum() {
		return this.currNum;
	}

	/**
	 * set方法。当前号码
	 */
	public void setCurrNum(Integer currNum) {
		this.currNum = currNum;
	}
    
	/**
	 * get方法。描述结束
	 */
	public String getDescriptionEnd() {
		return this.descriptionEnd;
	}

	/**
	 * set方法。描述结束
	 */
	public void setDescriptionEnd(String descriptionEnd) {
		this.descriptionEnd = descriptionEnd;
	}
    
	/**
	 * get方法。描述开始
	 */
	public String getDescriptionStart() {
		return this.descriptionStart;
	}

	/**
	 * set方法。描述开始
	 */
	public void setDescriptionStart(String descriptionStart) {
		this.descriptionStart = descriptionStart;
	}
    
	/**
	 * get方法。动态地址
	 */
	public String getDynamicAddr() {
		return this.dynamicAddr;
	}

	/**
	 * set方法。动态地址
	 */
	public void setDynamicAddr(String dynamicAddr) {
		this.dynamicAddr = dynamicAddr;
	}
    
	/**
	 * get方法。页码结束
	 */
	public Integer getDynamicEnd() {
		return this.dynamicEnd;
	}

	/**
	 * set方法。页码结束
	 */
	public void setDynamicEnd(Integer dynamicEnd) {
		this.dynamicEnd = dynamicEnd;
	}
    
	/**
	 * get方法。页码开始
	 */
	public Integer getDynamicStart() {
		return this.dynamicStart;
	}

	/**
	 * set方法。页码开始
	 */
	public void setDynamicStart(Integer dynamicStart) {
		this.dynamicStart = dynamicStart;
	}
    
	/**
	 * get方法。停止时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * set方法。停止时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * get方法。是否采集图片
	 */
	public Byte getImgAcqu() {
		return this.imgAcqu;
	}

	/**
	 * set方法。是否采集图片
	 */
	public void setImgAcqu(Byte imgAcqu) {
		this.imgAcqu = imgAcqu;
	}
    
	/**
	 * get方法。图片地址补全url
	 */
	public String getImgPrefix() {
		return this.imgPrefix;
	}

	/**
	 * set方法。图片地址补全url
	 */
	public void setImgPrefix(String imgPrefix) {
		this.imgPrefix = imgPrefix;
	}
    
	/**
	 * get方法。关键字结束
	 */
	public String getKeywordsEnd() {
		return this.keywordsEnd;
	}

	/**
	 * set方法。关键字结束
	 */
	public void setKeywordsEnd(String keywordsEnd) {
		this.keywordsEnd = keywordsEnd;
	}
    
	/**
	 * get方法。关键字开始
	 */
	public String getKeywordsStart() {
		return this.keywordsStart;
	}

	/**
	 * set方法。关键字开始
	 */
	public void setKeywordsStart(String keywordsStart) {
		this.keywordsStart = keywordsStart;
	}
    
	/**
	 * get方法。内容链接结束
	 */
	public String getLinkEnd() {
		return this.linkEnd;
	}

	/**
	 * set方法。内容链接结束
	 */
	public void setLinkEnd(String linkEnd) {
		this.linkEnd = linkEnd;
	}
    
	/**
	 * get方法。内容链接开始
	 */
	public String getLinkStart() {
		return this.linkStart;
	}

	/**
	 * set方法。内容链接开始
	 */
	public void setLinkStart(String linkStart) {
		this.linkStart = linkStart;
	}
    
	/**
	 * get方法。内容链接区结束
	 */
	public String getLinksetEnd() {
		return this.linksetEnd;
	}

	/**
	 * set方法。内容链接区结束
	 */
	public void setLinksetEnd(String linksetEnd) {
		this.linksetEnd = linksetEnd;
	}
    
	/**
	 * get方法。内容链接区开始
	 */
	public String getLinksetStart() {
		return this.linksetStart;
	}

	/**
	 * set方法。内容链接区开始
	 */
	public void setLinksetStart(String linksetStart) {
		this.linksetStart = linksetStart;
	}
    
	/**
	 * get方法。来源结束
	 */
	public String getOriginEnd() {
		return this.originEnd;
	}

	/**
	 * set方法。来源结束
	 */
	public void setOriginEnd(String originEnd) {
		this.originEnd = originEnd;
	}
    
	/**
	 * get方法。来源开始
	 */
	public String getOriginStart() {
		return this.originStart;
	}

	/**
	 * set方法。来源开始
	 */
	public void setOriginStart(String originStart) {
		this.originStart = originStart;
	}
    
	/**
	 * get方法。页面编码
	 */
	public String getPageEncoding() {
		return this.pageEncoding;
	}

	/**
	 * set方法。页面编码
	 */
	public void setPageEncoding(String pageEncoding) {
		this.pageEncoding = pageEncoding;
	}
    
	/**
	 * get方法。内容分页结束
	 */
	public String getPaginationEnd() {
		return this.paginationEnd;
	}

	/**
	 * set方法。内容分页结束
	 */
	public void setPaginationEnd(String paginationEnd) {
		this.paginationEnd = paginationEnd;
	}
    
	/**
	 * get方法。内容分页开始
	 */
	public String getPaginationStart() {
		return this.paginationStart;
	}

	/**
	 * set方法。内容分页开始
	 */
	public void setPaginationStart(String paginationStart) {
		this.paginationStart = paginationStart;
	}
    
	/**
	 * get方法。暂停时间(毫秒)
	 */
	public Integer getPauseTime() {
		return this.pauseTime;
	}

	/**
	 * set方法。暂停时间(毫秒)
	 */
	public void setPauseTime(Integer pauseTime) {
		this.pauseTime = pauseTime;
	}
    
	/**
	 * get方法。采集列表
	 */
	public String getPlanList() {
		return this.planList;
	}

	/**
	 * set方法。采集列表
	 */
	public void setPlanList(String planList) {
		this.planList = planList;
	}
    
	/**
	 * get方法。队列
	 */
	public Integer getQueue() {
		return this.queue;
	}

	/**
	 * set方法。队列
	 */
	public void setQueue(Integer queue) {
		this.queue = queue;
	}
    
	/**
	 * get方法。发布时间结束
	 */
	public String getReleaseTimeEnd() {
		return this.releaseTimeEnd;
	}

	/**
	 * set方法。发布时间结束
	 */
	public void setReleaseTimeEnd(String releaseTimeEnd) {
		this.releaseTimeEnd = releaseTimeEnd;
	}
    
	/**
	 * get方法。发布时间格式
	 */
	public String getReleaseTimeFormat() {
		return this.releaseTimeFormat;
	}

	/**
	 * set方法。发布时间格式
	 */
	public void setReleaseTimeFormat(String releaseTimeFormat) {
		this.releaseTimeFormat = releaseTimeFormat;
	}
    
	/**
	 * get方法。发布时间开始
	 */
	public String getReleaseTimeStart() {
		return this.releaseTimeStart;
	}

	/**
	 * set方法。发布时间开始
	 */
	public void setReleaseTimeStart(String releaseTimeStart) {
		this.releaseTimeStart = releaseTimeStart;
	}
    
	/**
	 * get方法。重复类型
	 */
	public String getRepeatCheckType() {
		return this.repeatCheckType;
	}

	/**
	 * set方法。重复类型
	 */
	public void setRepeatCheckType(String repeatCheckType) {
		this.repeatCheckType = repeatCheckType;
	}
    
	/**
	 * get方法。
	 */
	public String getSiteId() {
		return this.siteId;
	}

	/**
	 * set方法。
	 */
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
    
	/**
	 * get方法。开始时间
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * set方法。开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
    
	/**
	 * get方法。当前状态(0:静止;1:采集;2:暂停)
	 */
	public Integer getStatus() {
		return this.status;
	}

	/**
	 * set方法。当前状态(0:静止;1:采集;2:暂停)
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
    
	/**
	 * get方法。标题结束
	 */
	public String getTitleEnd() {
		return this.titleEnd;
	}

	/**
	 * set方法。标题结束
	 */
	public void setTitleEnd(String titleEnd) {
		this.titleEnd = titleEnd;
	}
    
	/**
	 * get方法。标题开始
	 */
	public String getTitleStart() {
		return this.titleStart;
	}

	/**
	 * set方法。标题开始
	 */
	public void setTitleStart(String titleStart) {
		this.titleStart = titleStart;
	}
    
	/**
	 * get方法。每页总条数
	 */
	public Integer getTotalItem() {
		return this.totalItem;
	}

	/**
	 * set方法。每页总条数
	 */
	public void setTotalItem(Integer totalItem) {
		this.totalItem = totalItem;
	}
    
	/**
	 * get方法。
	 */
	public String getTypeId() {
		return this.typeId;
	}

	/**
	 * set方法。
	 */
	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
    
	/**
	 * get方法。浏览量结束
	 */
	public String getViewEnd() {
		return this.viewEnd;
	}

	/**
	 * set方法。浏览量结束
	 */
	public void setViewEnd(String viewEnd) {
		this.viewEnd = viewEnd;
	}
    
	/**
	 * get方法。id后缀
	 */
	public String getViewIdEnd() {
		return this.viewIdEnd;
	}

	/**
	 * set方法。id后缀
	 */
	public void setViewIdEnd(String viewIdEnd) {
		this.viewIdEnd = viewIdEnd;
	}
    
	/**
	 * get方法。id前缀
	 */
	public String getViewIdStart() {
		return this.viewIdStart;
	}

	/**
	 * set方法。id前缀
	 */
	public void setViewIdStart(String viewIdStart) {
		this.viewIdStart = viewIdStart;
	}
    
	/**
	 * get方法。浏览量动态访问地址
	 */
	public String getViewLink() {
		return this.viewLink;
	}

	/**
	 * set方法。浏览量动态访问地址
	 */
	public void setViewLink(String viewLink) {
		this.viewLink = viewLink;
	}
    
	/**
	 * get方法。浏览量开始
	 */
	public String getViewStart() {
		return this.viewStart;
	}

	/**
	 * set方法。浏览量开始
	 */
	public void setViewStart(String viewStart) {
		this.viewStart = viewStart;
	}
    

    //一对多关系中，多端数据列表

}







