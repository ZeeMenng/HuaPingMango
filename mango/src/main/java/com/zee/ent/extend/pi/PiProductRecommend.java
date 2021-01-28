package com.zee.ent.extend.pi;

import com.zee.ent.generate.pi.PiProductRecommendGenEnt;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018/7/11 17:38:33
 * @description 扩展自实体类PiProductRecommendGenEnt，可手动更改。企业推介产品表
 */

@ApiModel(value = "PiProductRecommend", description = "企业推介产品表")
public class PiProductRecommend extends PiProductRecommendGenEnt {
	
	@ApiModelProperty(value="图标。外键，引用资源表（Resource）的主键。",hidden=false,required=false)
    private String resourceId;
    @ApiModelProperty(value="资源路径。和资源表（resource）的路径字段（path）对应。",hidden=false,required=false)
    private String resourcePath;
	private String[] resourceIdArray = new String[] {};
	private String[] resourcePathArray = new String[] {};

	@ApiModelProperty(value="所属企业id",hidden=false,required=true)
    private String enterpriseId;

    @ApiModelProperty(value="产品图片titleResourceId",hidden=false,required=true)
    private String titleResourceId;
    @ApiModelProperty(value="产品图片titleResourcePath",hidden=false,required=true)
    private String titleResourcePath;
    
	public String getTitleResourcePath() {
		return titleResourcePath;
	}

	public void setTitleResourcePath(String titleResourcePath) {
		this.titleResourcePath = titleResourcePath;
	}

	public String getTitleResourceId() {
		return titleResourceId;
	}

	public void setTitleResourceId(String titleResourceId) {
		this.titleResourceId = titleResourceId;
	}

	public String getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(String enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String[] getResourceIdArray() {
		return resourceIdArray;
	}

	public void setResourceIdArray(String[] resouceIdArray) {
		this.resourceIdArray = resouceIdArray;
	}

	public String[] getResourcePathArray() {
		return resourcePathArray;
	}

	public void setResourcePathArray(String[] resoucePathArray) {
		this.resourcePathArray = resoucePathArray;
	}
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}
	
}







