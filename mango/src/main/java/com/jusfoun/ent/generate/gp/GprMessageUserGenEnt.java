package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;

import com.jusfoun.ent.base.BaseEnt;

import io.swagger.annotations.ApiModelProperty;


/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/24 0:41:16
 * @description 实体类GprMessageUserGenEnt，自动生成。消息队列。
 */

public class GprMessageUserGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否已读。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 ",allowableValues="0,1",hidden=false,required=false)
    private Byte isReadCode;
    @ApiModelProperty(value="消息内容。外键，引用站内信（message）的主键。",hidden=false,required=false)
    private String messageId;
    @ApiModelProperty(value="消息接收方读取时间。",hidden=false,required=false)
    private Date readTime;
    @ApiModelProperty(value="消息接收者。外键，引用系统用户表（user）的主键。",hidden=false,required=false)
    private String userId;
    @ApiModelProperty(value="消息接收者。登录名称，和系统用户表（user）的登录名称（user_name）对应。",hidden=false,required=false)
    private String userName;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。记录创建时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录创建时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。主键。
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * set方法。主键。
	 */
	public void setId(String id) {
		this.id = id;
	}
    
	/**
	 * get方法。是否已读。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 
	 */
	public Byte getIsReadCode() {
		return this.isReadCode;
	}

	/**
	 * set方法。是否已读。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。 
	 */
	public void setIsReadCode(Byte isReadCode) {
		this.isReadCode = isReadCode;
	}
    
	/**
	 * get方法。消息内容。外键，引用站内信（message）的主键。
	 */
	public String getMessageId() {
		return this.messageId;
	}

	/**
	 * set方法。消息内容。外键，引用站内信（message）的主键。
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
    
	/**
	 * get方法。消息接收方读取时间。
	 */
	public Date getReadTime() {
		return this.readTime;
	}

	/**
	 * set方法。消息接收方读取时间。
	 */
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
    
	/**
	 * get方法。消息接收者。外键，引用系统用户表（user）的主键。
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。消息接收者。外键，引用系统用户表（user）的主键。
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
	/**
	 * get方法。消息接收者。登录名称，和系统用户表（user）的登录名称（user_name）对应。
	 */
	public String getUserName() {
		return this.userName;
	}

	/**
	 * set方法。消息接收者。登录名称，和系统用户表（user）的登录名称（user_name）对应。
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
    

    //一对多关系中，多端数据列表

}







