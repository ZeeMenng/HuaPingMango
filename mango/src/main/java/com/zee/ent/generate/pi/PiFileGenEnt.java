package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:10:01
 * @description 实体类PiFileGenEnt，自动生成。CMS文章相关文件表
 */

public class PiFileGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="是否有效",hidden=false,required=true)
    private Byte fileIsvalid;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String fileName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String filePath;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


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
	 * get方法。
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * set方法。
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
    
	/**
	 * get方法。
	 */
	public String getFilePath() {
		return this.filePath;
	}

	/**
	 * set方法。
	 */
	public void setFilePath(String filePath) {
		this.filePath = filePath;
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
    










}







