package com.jusfoun.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:36
 * @description 实体类DaTemplateDataGenEnt，自动生成。模板数据表
 */

public class DaTemplateDataGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="创建人",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过",allowableValues="0,1",hidden=false,required=false)
    private Byte auditStatusCode;
    @ApiModelProperty(value="审核状态文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证0：待审核，1:审核通过，2：审核不通过",hidden=false,required=false)
    private String auditStatusText;
    @ApiModelProperty(value="审核意见",hidden=false,required=false)
    private String auditerSuggestion;
    @ApiModelProperty(value="审核人，对应用户表userId",hidden=false,required=false)
    private String auditerUserId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="模板指标中间表id",hidden=false,required=false)
    private String templateTargetId;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。创建人
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。创建人
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
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
	 * get方法。模板指标中间表id
	 */
	public String getTemplateTargetId() {
		return this.templateTargetId;
	}

	/**
	 * set方法。模板指标中间表id
	 */
	public void setTemplateTargetId(String templateTargetId) {
		this.templateTargetId = templateTargetId;
	}
    

    //一对多关系中，多端数据列表

}







