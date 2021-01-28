package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:52
 * @description 实体类PiChannelExtGenEnt，自动生成。CMS栏目内容表
 */

public class PiChannelExtGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="审核后(1:不能修改删除;2:修改后退回;3:修改后不变)",hidden=false,required=false)
    private Byte afterCheck;
    @ApiModelProperty(value="评分(true:开放;false:关闭)",hidden=false,required=true)
    private Byte allowScore;
    @ApiModelProperty(value="分享(true:开放;false:关闭)",hidden=false,required=true)
    private Byte allowShare;
    @ApiModelProperty(value="顶踩(true:开放;false:关闭)",hidden=false,required=true)
    private Byte allowUpdown;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelRule;
    @ApiModelProperty(value="评论(0:匿名;1:会员;2:关闭)",hidden=false,required=true)
    private Integer commentControl;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentImg;
    @ApiModelProperty(value="内容内容图高度",hidden=false,required=true)
    private Integer contentImgHeight;
    @ApiModelProperty(value="内容内容图宽度",hidden=false,required=true)
    private Integer contentImgWidth;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentRule;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String description;
    @ApiModelProperty(value="终审级别",hidden=false,required=false)
    private Byte finalStep;
    @ApiModelProperty(value="内容是否有内容图",hidden=false,required=true)
    private Byte hasContentImg;
    @ApiModelProperty(value="内容是否有缩略图",hidden=false,required=true)
    private Byte hasTitleImg;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String isAccessByDir;
    @ApiModelProperty(value="是否新窗口打开",hidden=false,required=true)
    private Byte isBlank;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String isListChild;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String isStaticChannel;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String isStaticContent;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String keywords;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String link;
    @ApiModelProperty(value="每页多少条记录",hidden=false,required=true)
    private Integer pageSize;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String title;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String titleImg;
    @ApiModelProperty(value="内容标题图高度",hidden=false,required=true)
    private Integer titleImgHeight;
    @ApiModelProperty(value="内容标题图宽度",hidden=false,required=true)
    private Integer titleImgWidth;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tplChannel;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tplContent;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。审核后(1:不能修改删除;2:修改后退回;3:修改后不变)
	 */
	public Byte getAfterCheck() {
		return this.afterCheck;
	}

	/**
	 * set方法。审核后(1:不能修改删除;2:修改后退回;3:修改后不变)
	 */
	public void setAfterCheck(Byte afterCheck) {
		this.afterCheck = afterCheck;
	}
    
	/**
	 * get方法。评分(true:开放;false:关闭)
	 */
	public Byte getAllowScore() {
		return this.allowScore;
	}

	/**
	 * set方法。评分(true:开放;false:关闭)
	 */
	public void setAllowScore(Byte allowScore) {
		this.allowScore = allowScore;
	}
    
	/**
	 * get方法。分享(true:开放;false:关闭)
	 */
	public Byte getAllowShare() {
		return this.allowShare;
	}

	/**
	 * set方法。分享(true:开放;false:关闭)
	 */
	public void setAllowShare(Byte allowShare) {
		this.allowShare = allowShare;
	}
    
	/**
	 * get方法。顶踩(true:开放;false:关闭)
	 */
	public Byte getAllowUpdown() {
		return this.allowUpdown;
	}

	/**
	 * set方法。顶踩(true:开放;false:关闭)
	 */
	public void setAllowUpdown(Byte allowUpdown) {
		this.allowUpdown = allowUpdown;
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
	public String getChannelName() {
		return this.channelName;
	}

	/**
	 * set方法。
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
    
	/**
	 * get方法。
	 */
	public String getChannelRule() {
		return this.channelRule;
	}

	/**
	 * set方法。
	 */
	public void setChannelRule(String channelRule) {
		this.channelRule = channelRule;
	}
    
	/**
	 * get方法。评论(0:匿名;1:会员;2:关闭)
	 */
	public Integer getCommentControl() {
		return this.commentControl;
	}

	/**
	 * set方法。评论(0:匿名;1:会员;2:关闭)
	 */
	public void setCommentControl(Integer commentControl) {
		this.commentControl = commentControl;
	}
    
	/**
	 * get方法。
	 */
	public String getContentImg() {
		return this.contentImg;
	}

	/**
	 * set方法。
	 */
	public void setContentImg(String contentImg) {
		this.contentImg = contentImg;
	}
    
	/**
	 * get方法。内容内容图高度
	 */
	public Integer getContentImgHeight() {
		return this.contentImgHeight;
	}

	/**
	 * set方法。内容内容图高度
	 */
	public void setContentImgHeight(Integer contentImgHeight) {
		this.contentImgHeight = contentImgHeight;
	}
    
	/**
	 * get方法。内容内容图宽度
	 */
	public Integer getContentImgWidth() {
		return this.contentImgWidth;
	}

	/**
	 * set方法。内容内容图宽度
	 */
	public void setContentImgWidth(Integer contentImgWidth) {
		this.contentImgWidth = contentImgWidth;
	}
    
	/**
	 * get方法。
	 */
	public String getContentRule() {
		return this.contentRule;
	}

	/**
	 * set方法。
	 */
	public void setContentRule(String contentRule) {
		this.contentRule = contentRule;
	}
    
	/**
	 * get方法。
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * set方法。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    
	/**
	 * get方法。终审级别
	 */
	public Byte getFinalStep() {
		return this.finalStep;
	}

	/**
	 * set方法。终审级别
	 */
	public void setFinalStep(Byte finalStep) {
		this.finalStep = finalStep;
	}
    
	/**
	 * get方法。内容是否有内容图
	 */
	public Byte getHasContentImg() {
		return this.hasContentImg;
	}

	/**
	 * set方法。内容是否有内容图
	 */
	public void setHasContentImg(Byte hasContentImg) {
		this.hasContentImg = hasContentImg;
	}
    
	/**
	 * get方法。内容是否有缩略图
	 */
	public Byte getHasTitleImg() {
		return this.hasTitleImg;
	}

	/**
	 * set方法。内容是否有缩略图
	 */
	public void setHasTitleImg(Byte hasTitleImg) {
		this.hasTitleImg = hasTitleImg;
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
	 * get方法。
	 */
	public String getIsAccessByDir() {
		return this.isAccessByDir;
	}

	/**
	 * set方法。
	 */
	public void setIsAccessByDir(String isAccessByDir) {
		this.isAccessByDir = isAccessByDir;
	}
    
	/**
	 * get方法。是否新窗口打开
	 */
	public Byte getIsBlank() {
		return this.isBlank;
	}

	/**
	 * set方法。是否新窗口打开
	 */
	public void setIsBlank(Byte isBlank) {
		this.isBlank = isBlank;
	}
    
	/**
	 * get方法。
	 */
	public String getIsListChild() {
		return this.isListChild;
	}

	/**
	 * set方法。
	 */
	public void setIsListChild(String isListChild) {
		this.isListChild = isListChild;
	}
    
	/**
	 * get方法。
	 */
	public String getIsStaticChannel() {
		return this.isStaticChannel;
	}

	/**
	 * set方法。
	 */
	public void setIsStaticChannel(String isStaticChannel) {
		this.isStaticChannel = isStaticChannel;
	}
    
	/**
	 * get方法。
	 */
	public String getIsStaticContent() {
		return this.isStaticContent;
	}

	/**
	 * set方法。
	 */
	public void setIsStaticContent(String isStaticContent) {
		this.isStaticContent = isStaticContent;
	}
    
	/**
	 * get方法。
	 */
	public String getKeywords() {
		return this.keywords;
	}

	/**
	 * set方法。
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
    
	/**
	 * get方法。
	 */
	public String getLink() {
		return this.link;
	}

	/**
	 * set方法。
	 */
	public void setLink(String link) {
		this.link = link;
	}
    
	/**
	 * get方法。每页多少条记录
	 */
	public Integer getPageSize() {
		return this.pageSize;
	}

	/**
	 * set方法。每页多少条记录
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
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
    
	/**
	 * get方法。
	 */
	public String getTitleImg() {
		return this.titleImg;
	}

	/**
	 * set方法。
	 */
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
    
	/**
	 * get方法。内容标题图高度
	 */
	public Integer getTitleImgHeight() {
		return this.titleImgHeight;
	}

	/**
	 * set方法。内容标题图高度
	 */
	public void setTitleImgHeight(Integer titleImgHeight) {
		this.titleImgHeight = titleImgHeight;
	}
    
	/**
	 * get方法。内容标题图宽度
	 */
	public Integer getTitleImgWidth() {
		return this.titleImgWidth;
	}

	/**
	 * set方法。内容标题图宽度
	 */
	public void setTitleImgWidth(Integer titleImgWidth) {
		this.titleImgWidth = titleImgWidth;
	}
    
	/**
	 * get方法。
	 */
	public String getTplChannel() {
		return this.tplChannel;
	}

	/**
	 * set方法。
	 */
	public void setTplChannel(String tplChannel) {
		this.tplChannel = tplChannel;
	}
    
	/**
	 * get方法。
	 */
	public String getTplContent() {
		return this.tplContent;
	}

	/**
	 * set方法。
	 */
	public void setTplContent(String tplContent) {
		this.tplContent = tplContent;
	}
    










}







