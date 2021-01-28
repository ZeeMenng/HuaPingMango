package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:09:08
 * @description 实体类DaSentimentArticleGenEnt，自动生成。舆情文章表
 */

public class DaSentimentArticleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="增加时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String articleName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String articleTxtId;
    @ApiModelProperty(value="来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String datasourceText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="媒体类型。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。",allowableValues="0,1",hidden=false,required=false)
    private Byte mediaTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String mediaTypeText;
    @ApiModelProperty(value="文章发布时间",hidden=false,required=false)
    private Date publishTime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="情感值（类型）。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。",allowableValues="0,1",hidden=false,required=false)
    private Byte sentimentTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String sentimentTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String serialNumber;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String themeId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String threadStarter;
    @ApiModelProperty(value="审核状态：0：原文，1：审核文章",allowableValues="0,1",hidden=false,required=false)
    private Byte updateStatusCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String updateUserId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String url;
    @ApiModelProperty(value="浏览数。",hidden=false,required=false)
    private Integer viewCount;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。增加时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。增加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。
	 */
	public String getArticleName() {
		return this.articleName;
	}

	/**
	 * set方法。
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
    
	/**
	 * get方法。
	 */
	public String getArticleTxtId() {
		return this.articleTxtId;
	}

	/**
	 * set方法。
	 */
	public void setArticleTxtId(String articleTxtId) {
		this.articleTxtId = articleTxtId;
	}
    
	/**
	 * get方法。来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public Byte getDatasourceCode() {
		return this.datasourceCode;
	}

	/**
	 * set方法。来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public void setDatasourceCode(Byte datasourceCode) {
		this.datasourceCode = datasourceCode;
	}
    
	/**
	 * get方法。
	 */
	public String getDatasourceText() {
		return this.datasourceText;
	}

	/**
	 * set方法。
	 */
	public void setDatasourceText(String datasourceText) {
		this.datasourceText = datasourceText;
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
	 * get方法。媒体类型。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。
	 */
	public Byte getMediaTypeCode() {
		return this.mediaTypeCode;
	}

	/**
	 * set方法。媒体类型。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。
	 */
	public void setMediaTypeCode(Byte mediaTypeCode) {
		this.mediaTypeCode = mediaTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getMediaTypeText() {
		return this.mediaTypeText;
	}

	/**
	 * set方法。
	 */
	public void setMediaTypeText(String mediaTypeText) {
		this.mediaTypeText = mediaTypeText;
	}
    
	/**
	 * get方法。文章发布时间
	 */
	public Date getPublishTime() {
		return this.publishTime;
	}

	/**
	 * set方法。文章发布时间
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
    
	/**
	 * get方法。
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。情感值（类型）。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。
	 */
	public Byte getSentimentTypeCode() {
		return this.sentimentTypeCode;
	}

	/**
	 * set方法。情感值（类型）。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。
	 */
	public void setSentimentTypeCode(Byte sentimentTypeCode) {
		this.sentimentTypeCode = sentimentTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getSentimentTypeText() {
		return this.sentimentTypeText;
	}

	/**
	 * set方法。
	 */
	public void setSentimentTypeText(String sentimentTypeText) {
		this.sentimentTypeText = sentimentTypeText;
	}
    
	/**
	 * get方法。
	 */
	public String getSerialNumber() {
		return this.serialNumber;
	}

	/**
	 * set方法。
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
    
	/**
	 * get方法。
	 */
	public String getThemeId() {
		return this.themeId;
	}

	/**
	 * set方法。
	 */
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
    
	/**
	 * get方法。
	 */
	public String getThreadStarter() {
		return this.threadStarter;
	}

	/**
	 * set方法。
	 */
	public void setThreadStarter(String threadStarter) {
		this.threadStarter = threadStarter;
	}
    
	/**
	 * get方法。审核状态：0：原文，1：审核文章
	 */
	public Byte getUpdateStatusCode() {
		return this.updateStatusCode;
	}

	/**
	 * set方法。审核状态：0：原文，1：审核文章
	 */
	public void setUpdateStatusCode(Byte updateStatusCode) {
		this.updateStatusCode = updateStatusCode;
	}
    
	/**
	 * get方法。
	 */
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * set方法。
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
    
	/**
	 * get方法。
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * set方法。
	 */
	public void setUrl(String url) {
		this.url = url;
	}
    
	/**
	 * get方法。浏览数。
	 */
	public Integer getViewCount() {
		return this.viewCount;
	}

	/**
	 * set方法。浏览数。
	 */
	public void setViewCount(Integer viewCount) {
		this.viewCount = viewCount;
	}
    










}







