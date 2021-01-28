package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:54
 * @description 实体类PiContentAttachmentGenEnt，自动生成。CMS内容附件表
 */

public class PiContentAttachmentGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String attachmentName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String attachmentPath;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="下载次数",hidden=false,required=true)
    private Integer downloadCount;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String filename;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排列顺序",hidden=false,required=true)
    private Integer priority;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAttachmentName() {
		return this.attachmentName;
	}

	/**
	 * set方法。
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
    
	/**
	 * get方法。
	 */
	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	/**
	 * set方法。
	 */
	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
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
	 * get方法。下载次数
	 */
	public Integer getDownloadCount() {
		return this.downloadCount;
	}

	/**
	 * set方法。下载次数
	 */
	public void setDownloadCount(Integer downloadCount) {
		this.downloadCount = downloadCount;
	}
    
	/**
	 * get方法。
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * set方法。
	 */
	public void setFilename(String filename) {
		this.filename = filename;
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
    










}







