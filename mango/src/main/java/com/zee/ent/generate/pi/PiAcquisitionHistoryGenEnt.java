package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:50
 * @description 实体类PiAcquisitionHistoryGenEnt，自动生成。CMS采集的文章历史记录表
 */

public class PiAcquisitionHistoryGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String acquisitionId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String channelUrl;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentUrl;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String description;
    @ApiModelProperty(value="",hidden=false,required=true)
    private Integer id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String title;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAcquisitionId() {
		return this.acquisitionId;
	}

	/**
	 * set方法。
	 */
	public void setAcquisitionId(String acquisitionId) {
		this.acquisitionId = acquisitionId;
	}
    
	/**
	 * get方法。
	 */
	public String getChannelUrl() {
		return this.channelUrl;
	}

	/**
	 * set方法。
	 */
	public void setChannelUrl(String channelUrl) {
		this.channelUrl = channelUrl;
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
	 * get方法。
	 */
	public String getContentUrl() {
		return this.contentUrl;
	}

	/**
	 * set方法。
	 */
	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
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
    










}







