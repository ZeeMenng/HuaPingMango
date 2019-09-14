package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:24
 * @description 实体类PiAcquisitionHistoryGenEnt，自动生成。CMS采集的文章历史记录表
 */

public class PiAcquisitionHistoryGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="对应采集记录id",hidden=false,required=false)
    private String acquisitionId;
    @ApiModelProperty(value="栏目地址",hidden=false,required=true)
    private String channelUrl;
    @ApiModelProperty(value="内容",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="内容地址",hidden=false,required=true)
    private String contentUrl;
    @ApiModelProperty(value="描述",hidden=false,required=true)
    private String description;
    @ApiModelProperty(value="",hidden=false,required=true)
    private Integer id;
    @ApiModelProperty(value="标题",hidden=false,required=false)
    private String title;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。对应采集记录id
	 */
	public String getAcquisitionId() {
		return this.acquisitionId;
	}

	/**
	 * set方法。对应采集记录id
	 */
	public void setAcquisitionId(String acquisitionId) {
		this.acquisitionId = acquisitionId;
	}
    
	/**
	 * get方法。栏目地址
	 */
	public String getChannelUrl() {
		return this.channelUrl;
	}

	/**
	 * set方法。栏目地址
	 */
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
	}
    
	/**
	 * get方法。内容
	 */
	public String getContentId() {
		return this.contentId;
	}

	/**
	 * set方法。内容
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
    
	/**
	 * get方法。内容地址
	 */
	public String getContentUrl() {
		return this.contentUrl;
	}

	/**
	 * set方法。内容地址
	 */
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
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
	 * get方法。
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * set方法。
	 */
	public void setId(Integer id) {
		this.id = id;
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







