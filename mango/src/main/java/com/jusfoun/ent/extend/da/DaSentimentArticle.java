package com.zee.ent.extend.da;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.generate.da.DaSentimentArticleGenEnt;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2018-6-13 19:04:23
 * @description 扩展自实体类DaSentimentArticleGenEnt，可手动更改。舆情文章表
 */

@ApiModel(value = "DaSentimentArticle", description = "舆情文章表")
public class DaSentimentArticle extends DaSentimentArticleGenEnt {
	
	@ApiModelProperty(value="主题名称",hidden=false,required=false)
    private String themeName;
	
	@ApiModelProperty(value="关键词，逗号隔开",hidden=false,required=false)
    private String keyName;
  
	private DaSentimentContent daSentimentContent = new DaSentimentContent();

	public DaSentimentContent getDaSentimentContent() {
		return daSentimentContent;
	}

	public void setDaSentimentContent(DaSentimentContent daSentimentContent) {
		this.daSentimentContent = daSentimentContent;
	}

	public String getThemeName() {
		return themeName;
	}

	public void setThemeName(String themeName) {
		this.themeName = themeName;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}
	
}







