package com.jusfoun.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:26
 * @description 实体类PiContentTxtGenEnt，自动生成。CMS内容文本表
 */

public class PiContentTxtGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String contentId;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="文章内容",hidden=false,required=false)
    private String txt;
    @ApiModelProperty(value="扩展内容1",hidden=false,required=false)
    private String txt1;
    @ApiModelProperty(value="扩展内容2",hidden=false,required=false)
    private String txt2;
    @ApiModelProperty(value="扩展内容3",hidden=false,required=false)
    private String txt3;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。文章内容
	 */
	public String getTxt() {
		return this.txt;
	}

	/**
	 * set方法。文章内容
	 */
	public void setTxt(String txt) {
		this.txt = txt;
	}
    
	/**
	 * get方法。扩展内容1
	 */
	public String getTxt1() {
		return this.txt1;
	}

	/**
	 * set方法。扩展内容1
	 */
	public void setTxt1(String txt1) {
		this.txt1 = txt1;
	}
    
	/**
	 * get方法。扩展内容2
	 */
	public String getTxt2() {
		return this.txt2;
	}

	/**
	 * set方法。扩展内容2
	 */
	public void setTxt2(String txt2) {
		this.txt2 = txt2;
	}
    
	/**
	 * get方法。扩展内容3
	 */
	public String getTxt3() {
		return this.txt3;
	}

	/**
	 * set方法。扩展内容3
	 */
	public void setTxt3(String txt3) {
		this.txt3 = txt3;
	}
    

    //一对多关系中，多端数据列表

}







