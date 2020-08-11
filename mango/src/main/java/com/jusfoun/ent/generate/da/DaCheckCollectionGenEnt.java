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
 * @updateDate 2020/8/11 11:43:10
 * @description 实体类DaCheckCollectionGenEnt，自动生成。采样数据表
 */

public class DaCheckCollectionGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="检测项目",hidden=false,required=false)
    private String checkProject;
    @ApiModelProperty(value="检测项目编码",allowableValues="0,1",hidden=false,required=false)
    private Byte checkProjectCode;
    @ApiModelProperty(value="对应通用字段表id",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果",allowableValues="0,1",hidden=false,required=false)
    private Byte cropTypeCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果",hidden=false,required=false)
    private String cropTypeText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="数据主体名称",hidden=false,required=false)
    private String name;
    @ApiModelProperty(value="采样时间",hidden=false,required=false)
    private Date sampleDate;
    @ApiModelProperty(value="样品名称",hidden=false,required=false)
    private String sampleName;
    @ApiModelProperty(value="样品编号",hidden=false,required=false)
    private String sampleNo;
    @ApiModelProperty(value="采样人员",hidden=false,required=false)
    private String samplePersonnel;
    @ApiModelProperty(value="采样地点",hidden=false,required=false)
    private String samplePlace;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",allowableValues="0,1",hidden=false,required=false)
    private Byte strainsCode;
    @ApiModelProperty(value="对应数据字典表（dictionary）中的作物品种",hidden=false,required=false)
    private String strainsText;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。检测项目
	 */
	public String getCheckProject() {
		return this.checkProject;
	}

	/**
	 * set方法。检测项目
	 */
	public void setCheckProject(String checkProject) {
		this.checkProject = checkProject;
	}
    
	/**
	 * get方法。检测项目编码
	 */
	public Byte getCheckProjectCode() {
		return this.checkProjectCode;
	}

	/**
	 * set方法。检测项目编码
	 */
	public void setCheckProjectCode(Byte checkProjectCode) {
		this.checkProjectCode = checkProjectCode;
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
	 * get方法。对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果
	 */
	public Byte getCropTypeCode() {
		return this.cropTypeCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的编码字段（code）作物种类，1：芒果
	 */
	public void setCropTypeCode(Byte cropTypeCode) {
		this.cropTypeCode = cropTypeCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果
	 */
	public String getCropTypeText() {
		return this.cropTypeText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的文本字段（text）作物种类文本，1：芒果
	 */
	public void setCropTypeText(String cropTypeText) {
		this.cropTypeText = cropTypeText;
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
	 * get方法。数据主体名称
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * set方法。数据主体名称
	 */
	public void setName(String name) {
		this.name = name;
	}
    
	/**
	 * get方法。采样时间
	 */
	public Date getSampleDate() {
		return this.sampleDate;
	}

	/**
	 * set方法。采样时间
	 */
	public void setSampleDate(Date sampleDate) {
		this.sampleDate = sampleDate;
	}
    
	/**
	 * get方法。样品名称
	 */
	public String getSampleName() {
		return this.sampleName;
	}

	/**
	 * set方法。样品名称
	 */
	public void setSampleName(String sampleName) {
		this.sampleName = sampleName;
	}
    
	/**
	 * get方法。样品编号
	 */
	public String getSampleNo() {
		return this.sampleNo;
	}

	/**
	 * set方法。样品编号
	 */
	public void setSampleNo(String sampleNo) {
		this.sampleNo = sampleNo;
	}
    
	/**
	 * get方法。采样人员
	 */
	public String getSamplePersonnel() {
		return this.samplePersonnel;
	}

	/**
	 * set方法。采样人员
	 */
	public void setSamplePersonnel(String samplePersonnel) {
		this.samplePersonnel = samplePersonnel;
	}
    
	/**
	 * get方法。采样地点
	 */
	public String getSamplePlace() {
		return this.samplePlace;
	}

	/**
	 * set方法。采样地点
	 */
	public void setSamplePlace(String samplePlace) {
		this.samplePlace = samplePlace;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的作物品种
	 */
	public Byte getStrainsCode() {
		return this.strainsCode;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种
	 */
	public void setStrainsCode(Byte strainsCode) {
		this.strainsCode = strainsCode;
	}
    
	/**
	 * get方法。对应数据字典表（dictionary）中的作物品种
	 */
	public String getStrainsText() {
		return this.strainsText;
	}

	/**
	 * set方法。对应数据字典表（dictionary）中的作物品种
	 */
	public void setStrainsText(String strainsText) {
		this.strainsText = strainsText;
	}
    

    //一对多关系中，多端数据列表

}







