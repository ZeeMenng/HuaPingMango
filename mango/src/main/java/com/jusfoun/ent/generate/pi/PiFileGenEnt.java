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
 * @updateDate 2020/8/11 11:44:17
 * @description 实体类PiFileGenEnt，自动生成。CMS文章相关文件表
 */

public class PiFileGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="内容id",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="是否有效",hidden=false,required=true)
    private Byte fileIsvalid;
    @ApiModelProperty(value="文件名字",hidden=false,required=false)
    private String fileName;
    @ApiModelProperty(value="文件路径",hidden=false,required=true)
    private String filePath;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。内容id
	 */
	public String getContentId() {
		return this.contentId;
	}

	/**
	 * set方法。内容id
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
    
	/**
	 * get方法。是否有效
	 */
	public Byte getFileIsvalid() {
		return this.fileIsvalid;
	}

	/**
	 * set方法。是否有效
	 */
	public void setFileIsvalid(Byte fileIsvalid) {
		this.fileIsvalid = fileIsvalid;
	}
    
	/**
	 * get方法。文件名字
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * set方法。文件名字
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
	/**
	 * get方法。文件路径
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * set方法。文件路径
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
    

    //一对多关系中，多端数据列表

}







