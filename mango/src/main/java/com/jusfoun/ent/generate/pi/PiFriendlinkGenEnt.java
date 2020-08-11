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
 * @updateDate 2020/8/11 11:44:17
 * @description 实体类PiFriendlinkGenEnt，自动生成。CMS友情链接
 */

public class PiFriendlinkGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录填加时间。",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="应用领域。外键，引用应用领域表（domain）的主键。",hidden=false,required=false)
    private String domainId;
    @ApiModelProperty(value="站长邮箱。",hidden=false,required=false)
    private String email;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。",allowableValues="0,1",hidden=false,required=true)
    private Byte isEnabledCode;
    @ApiModelProperty(value="图标。外键，引用资源表（Resource）的主键。",hidden=false,required=false)
    private String logoResourceId;
    @ApiModelProperty(value="图标。资源路径，和资源表（resource）的路径字段（path）对应。",hidden=false,required=false)
    private String logoResourcePath;
    @ApiModelProperty(value="链接网站名称。",hidden=false,required=true)
    private String name;
    @ApiModelProperty(value="排列顺序",hidden=false,required=true)
    private Integer priority;
    @ApiModelProperty(value="备注。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="记录修改时间。",hidden=false,required=false)
    private Date updateTime;
    @ApiModelProperty(value="网站地址。",hidden=false,required=true)
    private String url;
    @ApiModelProperty(value="点击次数。",hidden=false,required=true)
    private Integer views;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。记录填加时间。
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录填加时间。
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public String getDomainId() {
		return this.domainId;
	}

	/**
	 * set方法。应用领域。外键，引用应用领域表（domain）的主键。
	 */
	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}
    
	/**
	 * get方法。站长邮箱。
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * set方法。站长邮箱。
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * get方法。是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public Byte getIsEnabledCode() {
		return this.isEnabledCode;
	}

	/**
	 * set方法。是否启用。对应数据字典表（dictionary）中的编码字段（code）。布尔型字段，两种类型：0是，1否。默认值0。
	 */
	public void setIsEnabledCode(Byte isEnabledCode) {
		this.isEnabledCode = isEnabledCode;
	}
    
	/**
	 * get方法。图标。外键，引用资源表（Resource）的主键。
	 */
	public String getLogoResourceId() {
		return this.logoResourceId;
	}

	/**
	 * set方法。图标。外键，引用资源表（Resource）的主键。
	 */
	public void setLogoResourceId(String logoResourceId) {
		this.logoResourceId = logoResourceId;
	}
    
	/**
	 * get方法。图标。资源路径，和资源表（resource）的路径字段（path）对应。
	 */
	public String getLogoResourcePath() {
		return this.logoResourcePath;
	}

	/**
	 * set方法。图标。资源路径，和资源表（resource）的路径字段（path）对应。
	 */
	public void setLogoResourcePath(String logoResourcePath) {
		this.logoResourcePath = logoResourcePath;
	}
    
	/**
	 * get方法。链接网站名称。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。链接网站名称。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。排列顺序
	 */
	public Integer getPriority() {
		return this.priority;
	}

	/**
	 * set方法。排列顺序
	 */
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
    
	/**
	 * get方法。备注。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。记录修改时间。
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。记录修改时间。
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    
	/**
	 * get方法。网站地址。
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * set方法。网站地址。
	 */
	public void setUrl(String url) {
		this.url = url;
	}
    
	/**
	 * get方法。点击次数。
	 */
	public Integer getViews() {
		return this.views;
	}

	/**
	 * set方法。点击次数。
	 */
	public void setViews(Integer views) {
		this.views = views;
	}
    

    //一对多关系中，多端数据列表

}







