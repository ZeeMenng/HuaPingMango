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
 * @updateDate 2018/11/23 19:41:13
 * @description 实体类DaCheckCollectionGenEnt，自动生成。
 */

public class DaCheckCollectionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String checkProject;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte checkProjectCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="",hidden=false,required=false)
    private Date sampleDate;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String sampleName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String sampleNo;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String samplePersonnel;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String samplePlace;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String strainsText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。
	 */
	public String getCheckProject() {
		return this.checkProject;
	}

	/**
	 * set方法。
	 */
	public void setCheckProject(String checkProject) {
		this.checkProject = checkProject;
	}
    
	/**
	 * get方法。
	 */
	public Byte getCheckProjectCode() {
		return this.checkProjectCode;
	}

	/**
	 * set方法。
	 */
	public void setCheckProjectCode(Byte checkProjectCode) {
		this.checkProjectCode = checkProjectCode;
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
	 * get方法。
	 */
	public Byte getCropTypeCode() {
		return this.cropTypeCode;
	}

	/**
	 * set方法。
	 */
	public void setCropTypeCode(Byte cropTypeCode) {
		this.cropTypeCode = cropTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCropTypeText() {
		return this.cropTypeText;
	}

	/**
	 * set方法。
	 */
	public void setCropTypeText(String cropTypeText) {
		this.cropTypeText = cropTypeText;
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
	 * get方法。
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。
	 */
	public Date getSampleDate() {
		return this.sampleDate;
	}

	/**
	 * set方法。
	 */
	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}
    
	/**
	 * get方法。
	 */
	public String getSampleName() {
		return this.sampleName;
	}

	/**
	 * set方法。
	 */
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
    
	/**
	 * get方法。
	 */
	public String getSampleNo() {
		return this.sampleNo;
	}

	/**
	 * set方法。
	 */
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
    
	/**
	 * get方法。
	 */
	public String getSamplePersonnel() {
		return this.samplePersonnel;
	}

	/**
	 * set方法。
	 */
	public void setSamplePersonnel(String samplePersonnel) {
		this.samplePersonnel = samplePersonnel;
	}
    
	/**
	 * get方法。
	 */
	public String getSamplePlace() {
		return this.samplePlace;
	}

	/**
	 * set方法。
	 */
	public void setSamplePlace(String samplePlace) {
		this.samplePlace = samplePlace;
	}
    
	/**
	 * get方法。
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    

    //一对多关系中，多端数据列表

}







