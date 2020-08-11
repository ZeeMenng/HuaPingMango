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
 * @updateDate 2020/8/11 11:44:18
 * @description 实体类PiInteractionGenEnt，自动生成。pi_interaction
 */

public class PiInteractionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过",allowableValues="0,1",hidden=false,required=false)
    private Byte auditStatusCode;
    @ApiModelProperty(value="审核状态文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证0：待审核，1:审核通过，2：审核不通过",hidden=false,required=false)
    private String auditStatusText;
    @ApiModelProperty(value="审核意见",hidden=false,required=false)
    private String auditerSuggestion;
    @ApiModelProperty(value="审核时间",hidden=false,required=false)
    private Date auditerTime;
    @ApiModelProperty(value="审核人，对应用户表userId",hidden=false,required=false)
    private String auditerUserId;
    @ApiModelProperty(value="邮箱",hidden=false,required=false)
    private String email;
    @ApiModelProperty(value="主键",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="类型：1：问专家，2：留建言",hidden=false,required=false)
    private Byte interactionType;
    @ApiModelProperty(value="工作",hidden=false,required=false)
    private String job;
    @ApiModelProperty(value="昵称，网名",hidden=false,required=false)
    private String nickName;
    @ApiModelProperty(value="电话",hidden=false,required=false)
    private String phone;
    @ApiModelProperty(value="qq号码",hidden=false,required=false)
    private String qq;
    @ApiModelProperty(value="真实名称",hidden=false,required=true)
    private String realName;
    @ApiModelProperty(value="地理区域 :对应区域表code",hidden=false,required=false)
    private String regionId;
    @ApiModelProperty(value="详细地址",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="问题或留言的内容",hidden=false,required=true)
    private String reqContent;
    @ApiModelProperty(value="回答的内容",hidden=false,required=false)
    private String resContent;
    @ApiModelProperty(value="回答人userId",hidden=false,required=false)
    private String resUserId;
    @ApiModelProperty(value="标题",hidden=false,required=true)
    private String title;
    @ApiModelProperty(value="记录最后一次修改时间",hidden=false,required=false)
    private Date updateTime;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。审核状态文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证0：待审核，1:审核通过，2：审核不通过
	 */
	public String getAuditStatusText() {
		return this.auditStatusText;
	}

	/**
	 * set方法。审核状态文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证0：待审核，1:审核通过，2：审核不通过
	 */
	public void setAuditStatusText(String auditStatusText) {
		this.auditStatusText = auditStatusText;
	}
    
	/**
	 * get方法。审核意见
	 */
	public String getAuditerSuggestion() {
		return this.auditerSuggestion;
	}

	/**
	 * set方法。审核意见
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
	 * get方法。审核人，对应用户表userId
	 */
	public String getAuditerUserId() {
		return this.auditerUserId;
	}

	/**
	 * set方法。审核人，对应用户表userId
	 */
	public void setAuditerUserId(String auditerUserId) {
		this.auditerUserId = auditerUserId;
	}
    
	/**
	 * get方法。邮箱
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * set方法。邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * get方法。工作
	 */
	public String getJob() {
		return this.job;
	}

	/**
	 * set方法。工作
	 */
	public void setJob(String job) {
		this.job = job;
	}
    
	/**
	 * get方法。昵称，网名
	 */
	public String getNickName() {
		return this.nickName;
	}

	/**
	 * set方法。昵称，网名
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
    
	/**
	 * get方法。电话
	 */
	public String getPhone() {
		return this.phone;
	}

	/**
	 * set方法。电话
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	/**
	 * get方法。qq号码
	 */
	public String getQq() {
		return this.qq;
	}

	/**
	 * set方法。qq号码
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}
    
	/**
	 * get方法。真实名称
	 */
	public String getRealName() {
		return this.realName;
	}

	/**
	 * set方法。真实名称
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
    
	/**
	 * get方法。地理区域 :对应区域表code
	 */
	public String getRegionId() {
		return this.regionId;
	}

	/**
	 * set方法。地理区域 :对应区域表code
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
    
	/**
	 * get方法。详细地址
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。详细地址
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	/**
	 * get方法。备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。问题或留言的内容
	 */
	public String getReqContent() {
		return this.reqContent;
	}

	/**
	 * set方法。问题或留言的内容
	 */
	public void setReqContent(String reqContent) {
		this.reqContent = reqContent;
	}
    
	/**
	 * get方法。回答的内容
	 */
	public String getResContent() {
		return this.resContent;
	}

	/**
	 * set方法。回答的内容
	 */
	public void setResContent(String resContent) {
		this.resContent = resContent;
	}
    
	/**
	 * get方法。回答人userId
	 */
	public String getResUserId() {
		return this.resUserId;
	}

	/**
	 * set方法。回答人userId
	 */
	public void setResUserId(String resUserId) {
		this.resUserId = resUserId;
	}
    
	/**
	 * get方法。标题
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * set方法。标题
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
    

    //一对多关系中，多端数据列表

}







