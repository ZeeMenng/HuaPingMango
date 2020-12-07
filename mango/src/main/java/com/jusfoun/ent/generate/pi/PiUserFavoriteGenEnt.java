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
 * @updateDate 2020/8/11 11:44:19
 * @description 实体类PiUserFavoriteGenEnt，自动生成。pi_user_favorite
 */

public class PiUserFavoriteGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="栏目id",hidden=false,required=false)
    private String channelId;
    @ApiModelProperty(value="栏目名称",hidden=false,required=false)
    private String channelName;
    @ApiModelProperty(value="内容id",hidden=false,required=false)
    private String contentId;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="用户id",hidden=false,required=false)
    private String userId;
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
	 * get方法。栏目id
	 */
	public String getChannelId() {
		return this.channelId;
	}

	/**
	 * set方法。栏目id
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
    
	/**
	 * get方法。栏目名称
	 */
	public String getChannelName() {
		return this.channelName;
	}

	/**
	 * set方法。栏目名称
	 */
	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}
    
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
	 * get方法。用户id
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * set方法。用户id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    

    //一对多关系中，多端数据列表

}







