package com.zee.ent.extend.pi;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.generate.pi.PiContentAttachmentGenEnt;

/**
 * @author Zee
 * @createDate 2017/05/19 15:34:09
 * @updateDate 2017/12/15 23:42:00
 * @description 扩展自实体类PiContentAttachmentGenEnt，可手动更改。CMS内容附件表
 */

@ApiModel(value = "PiContentAttachment", description = "CMS内容附件表")
public class PiContentAttachment extends PiContentAttachmentGenEnt {
  
	@ApiModelProperty(value="内容名称",hidden=false,required=true)
    private String title;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}







