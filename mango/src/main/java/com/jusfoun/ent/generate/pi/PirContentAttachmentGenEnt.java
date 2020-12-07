package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:44:07
 * @description 实体类PirContentAttachmentGenEnt，自动生成。CMS文章内容包含附件信息表。
 */

public class PirContentAttachmentGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="CMS文章。外键，引用CMS文章内容（content）表的主键",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="下载次数",hidden=false,required=false)
    private Integer downloadCount;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="文章附件。外键，引用资源（resource）表的主键",hidden=false,required=false)
    private String resourceId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。CMS文章。外键，引用CMS文章内容（content）表的主键
	 */
	public String getContentId() {
		return this.contentId;
	}

	/**
	 * set方法。CMS文章。外键，引用CMS文章内容（content）表的主键
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
	 * get方法。文章附件。外键，引用资源（resource）表的主键
	 */
	public String getResourceId() {
		return this.resourceId;
	}

	/**
	 * set方法。文章附件。外键，引用资源（resource）表的主键
	 */
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}
    

    //一对多关系中，多端数据列表

}







