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
 * @description 实体类PiContentAttachmentGenEnt，自动生成。CMS内容附件表
 */

public class PiContentAttachmentGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="附件名称",hidden=false,required=true)
    private String attachmentName;
    @ApiModelProperty(value="附件路径",hidden=false,required=true)
    private String attachmentPath;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String contentId;
    @ApiModelProperty(value="下载次数",hidden=false,required=true)
    private Integer downloadCount;
    @ApiModelProperty(value="文件名",hidden=false,required=false)
    private String filename;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="排列顺序",hidden=false,required=true)
    private Integer priority;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。附件名称
	 */
	public String getAttachmentName() {
		return this.attachmentName;
	}

	/**
	 * set方法。附件名称
	 */
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
    
	/**
	 * get方法。附件路径
	 */
	public String getAttachmentPath() {
		return this.attachmentPath;
	}

	/**
	 * set方法。附件路径
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
	 * get方法。文件名
	 */
	public String getFilename() {
		return this.filename;
	}

	/**
	 * set方法。文件名
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
    

    //一对多关系中，多端数据列表

}







