package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:49
 * @description 实体类PiAcquisitionGenEnt，自动生成。CMS采集任务表
 */

public class PiAcquisitionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String acqName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String authorEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String authorStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentPrefix;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentStart;
    @ApiModelProperty(value="当前条数",hidden=false,required=false)
    private Integer currItem;
    @ApiModelProperty(value="当前号码",hidden=false,required=false)
    private Integer currNum;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String descriptionEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String descriptionStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String dynamicAddr;
    @ApiModelProperty(value="页码结束",hidden=false,required=false)
    private Integer dynamicEnd;
    @ApiModelProperty(value="页码开始",hidden=false,required=false)
    private Integer dynamicStart;
    @ApiModelProperty(value="停止时间",hidden=false,required=false)
    private Date endTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否采集图片",hidden=false,required=false)
    private Byte imgAcqu;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String imgPrefix;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String keywordsEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String keywordsStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String linkEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String linkStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String linksetEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String linksetStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String originEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String originStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String pageEncoding;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String paginationEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String paginationStart;
    @ApiModelProperty(value="暂停时间(毫秒)",hidden=false,required=false)
    private Integer pauseTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String planList;
    @ApiModelProperty(value="队列",hidden=false,required=false)
    private Integer queue;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String releaseTimeEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String releaseTimeFormat;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String releaseTimeStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String repeatCheckType;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String siteId;
    @ApiModelProperty(value="开始时间",hidden=false,required=false)
    private Date startTime;
    @ApiModelProperty(value="当前状态(0:静止;1:采集;2:暂停)",hidden=false,required=false)
    private Integer status;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String titleEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String titleStart;
    @ApiModelProperty(value="每页总条数",hidden=false,required=false)
    private Integer totalItem;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String typeId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String viewEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String viewIdEnd;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String viewIdStart;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String viewLink;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String viewStart;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAcqName() {
		return this.acqName;
	}

	/**
	 * set方法。
	 */
	public void setAcqName(String acqName) {
		this.acqName = acqName;
	}
    
	/**
	 * get方法。
	 */
	public String getAuthorEnd() {
		return this.authorEnd;
	}

	/**
	 * set方法。
	 */
	public void setAuthorEnd(String authorEnd) {
		this.authorEnd = authorEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getAuthorStart() {
		return this.authorStart;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getContentEnd() {
		return this.contentEnd;
	}

	/**
	 * set方法。
	 */
	public void setContentEnd(String contentEnd) {
		this.contentEnd = contentEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getContentPrefix() {
		return this.contentPrefix;
	}

	/**
	 * set方法。
	 */
	public void setContentPrefix(String contentPrefix) {
		this.contentPrefix = contentPrefix;
	}
    
	/**
	 * get方法。
	 */
	public String getContentStart() {
		return this.contentStart;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getDescriptionEnd() {
		return this.descriptionEnd;
	}

	/**
	 * set方法。
	 */
	public void setDescriptionEnd(String descriptionEnd) {
		this.descriptionEnd = descriptionEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getDescriptionStart() {
		return this.descriptionStart;
	}

	/**
	 * set方法。
	 */
	public void setDescriptionStart(String descriptionStart) {
		this.descriptionStart = descriptionStart;
	}
    
	/**
	 * get方法。
	 */
	public String getDynamicAddr() {
		return this.dynamicAddr;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getImgPrefix() {
		return this.imgPrefix;
	}

	/**
	 * set方法。
	 */
	public void setImgPrefix(String imgPrefix) {
		this.imgPrefix = imgPrefix;
	}
    
	/**
	 * get方法。
	 */
	public String getKeywordsEnd() {
		return this.keywordsEnd;
	}

	/**
	 * set方法。
	 */
	public void setKeywordsEnd(String keywordsEnd) {
		this.keywordsEnd = keywordsEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getKeywordsStart() {
		return this.keywordsStart;
	}

	/**
	 * set方法。
	 */
	public void setKeywordsStart(String keywordsStart) {
		this.keywordsStart = keywordsStart;
	}
    
	/**
	 * get方法。
	 */
	public String getLinkEnd() {
		return this.linkEnd;
	}

	/**
	 * set方法。
	 */
	public void setLinkEnd(String linkEnd) {
		this.linkEnd = linkEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getLinkStart() {
		return this.linkStart;
	}

	/**
	 * set方法。
	 */
	public void setLinkStart(String linkStart) {
		this.linkStart = linkStart;
	}
    
	/**
	 * get方法。
	 */
	public String getLinksetEnd() {
		return this.linksetEnd;
	}

	/**
	 * set方法。
	 */
	public void setLinksetEnd(String linksetEnd) {
		this.linksetEnd = linksetEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getLinksetStart() {
		return this.linksetStart;
	}

	/**
	 * set方法。
	 */
	public void setLinksetStart(String linksetStart) {
		this.linksetStart = linksetStart;
	}
    
	/**
	 * get方法。
	 */
	public String getOriginEnd() {
		return this.originEnd;
	}

	/**
	 * set方法。
	 */
	public void setOriginEnd(String originEnd) {
		this.originEnd = originEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getOriginStart() {
		return this.originStart;
	}

	/**
	 * set方法。
	 */
	public void setOriginStart(String originStart) {
		this.originStart = originStart;
	}
    
	/**
	 * get方法。
	 */
	public String getPageEncoding() {
		return this.pageEncoding;
	}

	/**
	 * set方法。
	 */
	public void setPageEncoding(String pageEncoding) {
		this.pageEncoding = pageEncoding;
	}
    
	/**
	 * get方法。
	 */
	public String getPaginationEnd() {
		return this.paginationEnd;
	}

	/**
	 * set方法。
	 */
	public void setPaginationEnd(String paginationEnd) {
		this.paginationEnd = paginationEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getPaginationStart() {
		return this.paginationStart;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getPlanList() {
		return this.planList;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getReleaseTimeEnd() {
		return this.releaseTimeEnd;
	}

	/**
	 * set方法。
	 */
	public void setReleaseTimeEnd(String releaseTimeEnd) {
		this.releaseTimeEnd = releaseTimeEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getReleaseTimeFormat() {
		return this.releaseTimeFormat;
	}

	/**
	 * set方法。
	 */
	public void setReleaseTimeFormat(String releaseTimeFormat) {
		this.releaseTimeFormat = releaseTimeFormat;
	}
    
	/**
	 * get方法。
	 */
	public String getReleaseTimeStart() {
		return this.releaseTimeStart;
	}

	/**
	 * set方法。
	 */
	public void setReleaseTimeStart(String releaseTimeStart) {
		this.releaseTimeStart = releaseTimeStart;
	}
    
	/**
	 * get方法。
	 */
	public String getRepeatCheckType() {
		return this.repeatCheckType;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getTitleEnd() {
		return this.titleEnd;
	}

	/**
	 * set方法。
	 */
	public void setTitleEnd(String titleEnd) {
		this.titleEnd = titleEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getTitleStart() {
		return this.titleStart;
	}

	/**
	 * set方法。
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
	 * get方法。
	 */
	public String getViewEnd() {
		return this.viewEnd;
	}

	/**
	 * set方法。
	 */
	public void setViewEnd(String viewEnd) {
		this.viewEnd = viewEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getViewIdEnd() {
		return this.viewIdEnd;
	}

	/**
	 * set方法。
	 */
	public void setViewIdEnd(String viewIdEnd) {
		this.viewIdEnd = viewIdEnd;
	}
    
	/**
	 * get方法。
	 */
	public String getViewIdStart() {
		return this.viewIdStart;
	}

	/**
	 * set方法。
	 */
	public void setViewIdStart(String viewIdStart) {
		this.viewIdStart = viewIdStart;
	}
    
	/**
	 * get方法。
	 */
	public String getViewLink() {
		return this.viewLink;
	}

	/**
	 * set方法。
	 */
	public void setViewLink(String viewLink) {
		this.viewLink = viewLink;
	}
    
	/**
	 * get方法。
	 */
	public String getViewStart() {
		return this.viewStart;
	}

	/**
	 * set方法。
	 */
	public void setViewStart(String viewStart) {
		this.viewStart = viewStart;
	}
    










}







