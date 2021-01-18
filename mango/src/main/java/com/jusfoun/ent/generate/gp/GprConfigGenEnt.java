package com.jusfoun.ent.generate.gp;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.jusfoun.ent.base.BaseEnt;
import com.jusfoun.ent.extend.gp.GpDomain;
import com.jusfoun.ent.extend.gp.GpConfig;
import com.jusfoun.ent.extend.gp.GpPage;
import com.jusfoun.ent.extend.gp.GpControl;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/18 19:49:35
 * @description 实体类GprConfigGenEnt，自动生成。应用领域配置信息。
 */

public class GprConfigGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="业务。外键，引用应用领域表（domain）、页面面（page）或控件表（control）等的主键。",hidden=false,required=false)
    private String businessId;
    @ApiModelProperty(value="配置项。外键，引用配置项表（config）的主键。",hidden=false,required=false)
    private String configId;
    @ApiModelProperty(value="配置项值。",hidden=false,required=false)
    private String configValue;
    @ApiModelProperty(value="主键。",hidden=false,required=true)
    private String id;

   //本表做为子表时，父表实体对象
    private  GpDomain gpDomain;
    private  GpConfig gpConfig;
    private  GpPage gpPage;
    private  GpControl gpControl;

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。业务。外键，引用应用领域表（domain）、页面面（page）或控件表（control）等的主键。
	 */
	public String getBusinessId() {
		return this.businessId;
	}

	/**
	 * set方法。业务。外键，引用应用领域表（domain）、页面面（page）或控件表（control）等的主键。
	 */
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
    
	/**
	 * get方法。配置项。外键，引用配置项表（config）的主键。
	 */
	public String getConfigId() {
		return this.configId;
	}

	/**
	 * set方法。配置项。外键，引用配置项表（config）的主键。
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}
    
	/**
	 * get方法。配置项值。
	 */
	public String getConfigValue() {
		return this.configValue;
	}

	/**
	 * set方法。配置项值。
	 */
	public void setConfigValue(String configValue) {
		this.configValue = configValue;
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
	 * get方法。本表做为子表时，父表实体对象。应用领域。
	 */
	public GpDomain getGpDomain() {
		return this.gpDomain;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。应用领域。
	 */
	public void setGpDomain(GpDomain gpDomain) {
		this.gpDomain = gpDomain;
	}

	/**
	 * get方法。本表做为子表时，父表实体对象。配置项信息。
	 */
	public GpConfig getGpConfig() {
		return this.gpConfig;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。配置项信息。
	 */
	public void setGpConfig(GpConfig gpConfig) {
		this.gpConfig = gpConfig;
	}

	/**
	 * get方法。本表做为子表时，父表实体对象。系统页面。
	 */
	public GpPage getGpPage() {
		return this.gpPage;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统页面。
	 */
	public void setGpPage(GpPage gpPage) {
		this.gpPage = gpPage;
	}

	/**
	 * get方法。本表做为子表时，父表实体对象。系统控件。
	 */
	public GpControl getGpControl() {
		return this.gpControl;
	}

	/**
	 * set方法。本表做为子表时，父表实体对象。系统控件。
	 */
	public void setGpControl(GpControl gpControl) {
		this.gpControl = gpControl;
	}





}







