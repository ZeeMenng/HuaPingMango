package com.jusfoun.ent.generate.mf;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:57
 * @description 实体类MfQualityInspectionRateGenEnt，自动生成。质量检测表
 */

public class MfQualityInspectionRateGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="区域编码",allowableValues="0,1",hidden=false,required=false)
    private String areaCode;
    @ApiModelProperty(value="区域名称",hidden=false,required=false)
    private String areaName;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="质量检测时间",hidden=false,required=false)
    private Date creatTime;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="抽检次数",hidden=false,required=false)
    private Integer inspection;
    @ApiModelProperty(value="抽检合格率",hidden=false,required=false)
    private String inspectionQualified;
    @ApiModelProperty(value="质量问题反馈次数",hidden=false,required=false)
    private Integer issueTimes;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。区域编码
	 */
	public String getAreaCode() {
		return this.areaCode;
	}

	/**
	 * set方法。区域编码
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
    
	/**
	 * get方法。区域名称
	 */
	public String getAreaName() {
		return this.areaName;
	}

	/**
	 * set方法。区域名称
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
    
	/**
	 * get方法。对应通用字段表id
	 */
	public String getCommonFieldId() {
		return this.commonFieldId;
	}

	/**
	 * set方法。对应通用字段表id
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
	 * get方法。主键id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键id
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
	 * get方法。抽检合格率
	 */
	public String getInspectionQualified() {
		return this.inspectionQualified;
	}

	/**
	 * set方法。抽检合格率
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
    

    //一对多关系中，多端数据列表

}







