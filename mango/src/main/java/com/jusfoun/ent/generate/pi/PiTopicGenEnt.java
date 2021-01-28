package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:10:03
 * @description 实体类PiTopicGenEnt，自动生成。CMS专题表
 */

public class PiTopicGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentImg;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String description;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否推??",hidden=false,required=true)
    private Byte isRecommend;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String keywords;
    @ApiModelProperty(value="排列顺序",hidden=false,required=true)
    private Integer priority;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String shortName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String titleImg;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String topicName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tplContent;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。是否推??
	 */
	public Byte getIsRecommend() {
		return this.isRecommend;
	}

	/**
	 * set方法。是否推??
	 */
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
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
	 * get方法。排列顺序
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排列顺序
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。
	 */
	public String getShortName() {
		return this.shortName;
	}

	/**
	 * set方法。
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
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
	 * get方法。
	 */
	public String getTopicName() {
		return this.topicName;
	}

	/**
	 * set方法。
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
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







