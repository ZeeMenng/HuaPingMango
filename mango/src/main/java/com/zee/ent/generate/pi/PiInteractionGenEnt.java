package com.zee.ent.generate.pi;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:10:02
 * @description 实体类PiInteractionGenEnt，自动生成。pi_interaction
 */

public class PiInteractionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过",allowableValues="0,1",hidden=false,required=false)
    private Byte auditStatusCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String auditStatusText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String auditerSuggestion;
    @ApiModelProperty(value="审核时间",hidden=false,required=false)
    private Date auditerTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String auditerUserId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String email;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="类型：1：问专家，2：留建言",hidden=false,required=false)
    private Byte interactionType;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String job;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String nickName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String phone;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String qq;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String realName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String reqContent;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resContent;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String resUserId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String title;
    @ApiModelProperty(value="记录最后一次修改时间",hidden=false,required=false)
    private Date updateTime;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。记录创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过
	 */
	public Byte getAuditStatusCode() {
		return this.auditStatusCode;
	}

	/**
	 * set方法。审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过
	 */
	public void setAuditStatusCode(Byte auditStatusCode) {
		this.auditStatusCode = auditStatusCode;
	}
    
	/**
	 * get方法。
	 */
	public String getAuditStatusText() {
		return this.auditStatusText;
	}

	/**
	 * set方法。
	 */
	public void setAuditStatusText(String auditStatusText) {
		this.auditStatusText = auditStatusText;
	}
    
	/**
	 * get方法。
	 */
	public String getAuditerSuggestion() {
		return this.auditerSuggestion;
	}

	/**
	 * set方法。
	 */
	public void setAuditerSuggestion(String auditerSuggestion) {
		this.auditerSuggestion = auditerSuggestion;
	}
    
	/**
	 * get方法。审核时间
	 */
	public Date getAuditerTime() {
		return this.auditerTime;
	}

	/**
	 * set方法。审核时间
	 */
	public void setAuditerTime(Date auditerTime) {
		this.auditerTime = auditerTime;
	}
    
	/**
	 * get方法。
	 */
	public String getAuditerUserId() {
		return this.auditerUserId;
	}

	/**
	 * set方法。
	 */
	public void setAuditerUserId(String auditerUserId) {
		this.auditerUserId = auditerUserId;
	}
    
	/**
	 * get方法。
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * set方法。
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * get方法。类型：1：问专家，2：留建言
	 */
	public Byte getInteractionType() {
		return this.interactionType;
	}

	/**
	 * set方法。类型：1：问专家，2：留建言
	 */
	public void setInteractionType(Byte interactionType) {
		this.interactionType = interactionType;
	}
    
	/**
	 * get方法。
	 */
	public String getJob() {
		return this.job;
	}

	/**
	 * set方法。
	 */
	public void setJob(String job) {
		this.job = job;
	}
    
	/**
	 * get方法。
	 */
	public String getNickName() {
		return this.nickName;
	}

	/**
	 * set方法。
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
	/**
	 * get方法。
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * set方法。
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	/**
	 * get方法。
	 */
	public String getQq() {
		return this.qq;
	}

	/**
	 * set方法。
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
    
	/**
	 * get方法。
	 */
	public String getRealName() {
		return this.realName;
	}

	/**
	 * set方法。
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionId() {
		return this.regionId;
	}

	/**
	 * set方法。
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	/**
	 * get方法。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。
	 */
	public String getReqContent() {
		return this.reqContent;
	}

	/**
	 * set方法。
	 */
	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}
    
	/**
	 * get方法。
	 */
	public String getResContent() {
		return this.resContent;
	}

	/**
	 * set方法。
	 */
	public void setResContent(String resContent) {
		this.resContent = resContent;
	}
    
	/**
	 * get方法。
	 */
	public String getResUserId() {
		return this.resUserId;
	}

	/**
	 * set方法。
	 */
	public void setResUserId(String resUserId) {
		this.resUserId = resUserId;
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
    
	/**
	 * get方法。记录最后一次修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。记录最后一次修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    










}







