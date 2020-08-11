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
 * @updateDate 2020/8/11 11:44:14
 * @description 实体类PiContentCheckGenEnt，自动生成。CMS内容审核信息表
 */

public class PiContentCheckGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="终审时间",hidden=false,required=false)
    private Date checkDate;
    @ApiModelProperty(value="审核意见",hidden=false,required=false)
    private String checkOpinion;
    @ApiModelProperty(value="审核步数",hidden=false,required=false)
    private Byte checkStep;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否退回",hidden=false,required=false)
    private Byte isRejected;
    @ApiModelProperty(value="终审者",hidden=false,required=false)
    private Integer reviewer;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。终审时间
	 */
	public Date getCheckDate() {
		return this.checkDate;
	}

	/**
	 * set方法。终审时间
	 */
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
    
	/**
	 * get方法。审核意见
	 */
	public String getCheckOpinion() {
		return this.checkOpinion;
	}

	/**
	 * set方法。审核意见
	 */
	public void setCheckOpinion(String checkOpinion) {
		this.checkOpinion = checkOpinion;
	}
    
	/**
	 * get方法。审核步数
	 */
	public Byte getCheckStep() {
		return this.checkStep;
	}

	/**
	 * set方法。审核步数
	 */
	public void setCheckStep(Byte checkStep) {
		this.checkStep = checkStep;
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
	 * get方法。是否退回
	 */
	public Byte getIsRejected() {
		return this.isRejected;
	}

	/**
	 * set方法。是否退回
	 */
	public void setIsRejected(Byte isRejected) {
		this.isRejected = isRejected;
	}
    
	/**
	 * get方法。终审者
	 */
	public Integer getReviewer() {
		return this.reviewer;
	}

	/**
	 * set方法。终审者
	 */
	public void setReviewer(Integer reviewer) {
		this.reviewer = reviewer;
	}
    

    //一对多关系中，多端数据列表

}







