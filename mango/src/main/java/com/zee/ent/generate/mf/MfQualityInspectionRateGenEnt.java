package com.zee.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:39
 * @description 实体类MfQualityInspectionRateGenEnt，自动生成。质量检测表
 */

public class MfQualityInspectionRateGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String areaCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String areaName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="质量检测时间",hidden=false,required=false)
    private Date creatTime;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="抽检次数",hidden=false,required=false)
    private Integer inspection;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String inspectionQualified;
    @ApiModelProperty(value="质量问题反馈次数",hidden=false,required=false)
    private Integer issueTimes;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * set方法。
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
    
	/**
	 * get方法。
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * set方法。
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
    
	/**
	 * get方法。
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。
	 */
	public void setCommonFieldId(String commonFieldId) {
		this.commonFieldId = commonFieldId;
	}
    
	/**
	 * get方法。质量检测时间
	 */
	public Date getCreatTime() {
		return this.creatTime;
	}

	/**
	 * set方法。质量检测时间
	 */
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
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
	 * get方法。抽检次数
	 */
	public Integer getInspection() {
		return this.inspection;
	}

	/**
	 * set方法。抽检次数
	 */
	public void setInspection(Integer inspection) {
		this.inspection = inspection;
	}
    
	/**
	 * get方法。
	 */
	public String getInspectionQualified() {
		return this.inspectionQualified;
	}

	/**
	 * set方法。
	 */
	public void setInspectionQualified(String inspectionQualified) {
		this.inspectionQualified = inspectionQualified;
	}
    
	/**
	 * get方法。质量问题反馈次数
	 */
	public Integer getIssueTimes() {
		return this.issueTimes;
	}

	/**
	 * set方法。质量问题反馈次数
	 */
	public void setIssueTimes(Integer issueTimes) {
		this.issueTimes = issueTimes;
	}
    










}







