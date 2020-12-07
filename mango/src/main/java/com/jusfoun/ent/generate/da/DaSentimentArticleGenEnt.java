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
 * @updateDate 2020/8/11 11:43:31
 * @description 实体类DaSentimentArticleGenEnt，自动生成。舆情文章表
 */

public class DaSentimentArticleGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="增加时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="文章标题",hidden=false,required=false)
    private String articleName;
    @ApiModelProperty(value="文章内容。外键，引用文章内容表（artical_content）的主键（id）。",hidden=false,required=false)
    private String articleTxtId;
    @ApiModelProperty(value="来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报",allowableValues="0,1",hidden=false,required=false)
    private Byte datasourceCode;
    @ApiModelProperty(value="来源渠道：文本，对应数据字典表（dictionary）中的文本字段（text）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报",hidden=false,required=false)
    private String datasourceText;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="媒体类型。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。",allowableValues="0,1",hidden=false,required=false)
    private Byte mediaTypeCode;
    @ApiModelProperty(value="媒体类型。文本，对应数据字典表（dictionary）中的编码字段（name）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。",hidden=false,required=false)
    private String mediaTypeText;
    @ApiModelProperty(value="文章发布时间",hidden=false,required=false)
    private Date publishTime;
    @ApiModelProperty(value="备注。",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="情感值（类型）。编码，对应数据字典表（dictionary）中的编码字段（code）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。",allowableValues="0,1",hidden=false,required=false)
    private Byte sentimentTypeCode;
    @ApiModelProperty(value="情感值（类型）。文本，对应数据字典表（dictionary）中的编码字段（name）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。",hidden=false,required=false)
    private String sentimentTypeText;
    @ApiModelProperty(value="编号。非空唯一，由数据提供方制定，标志此条文章记录唯一。",hidden=false,required=true)
    private String serialNumber;
    @ApiModelProperty(value="对应主题表的id",hidden=false,required=false)
    private String themeId;
    @ApiModelProperty(value="媒体名称。",hidden=false,required=false)
    private String threadStarter;
    @ApiModelProperty(value="审核状态：0：原文，1：审核文章",allowableValues="0,1",hidden=false,required=false)
    private Byte updateStatusCode;
    @ApiModelProperty(value="审核人，对应用户表userId",hidden=false,required=false)
    private String updateUserId;
    @ApiModelProperty(value="访问路径",hidden=false,required=false)
    private String url;
    @ApiModelProperty(value="浏览数。",hidden=false,required=false)
    private Integer viewCount;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

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
	 * get方法。文章标题
	 */
	public String getArticleName() {
		return this.articleName;
	}

	/**
	 * set方法。文章标题
	 */
	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}
    
	/**
	 * get方法。文章内容。外键，引用文章内容表（artical_content）的主键（id）。
	 */
	public String getArticleTxtId() {
		return this.articleTxtId;
	}

	/**
	 * set方法。文章内容。外键，引用文章内容表（artical_content）的主键（id）。
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
	 * get方法。来源渠道：文本，对应数据字典表（dictionary）中的文本字段（text）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public String getDatasourceText() {
		return this.datasourceText;
	}

	/**
	 * set方法。来源渠道：文本，对应数据字典表（dictionary）中的文本字段（text）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public void setDatasourceText(String datasourceText) {
		this.datasourceText = datasourceText;
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
	 * get方法。媒体类型。文本，对应数据字典表（dictionary）中的编码字段（name）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。
	 */
	public String getMediaTypeText() {
		return this.mediaTypeText;
	}

	/**
	 * set方法。媒体类型。文本，对应数据字典表（dictionary）中的编码字段（name）。和舆情团队提供的接口说明中一至，目前5种类型：1新闻，3论坛，4微博，6博客，10微信公众号。
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
	 * get方法。情感值（类型）。文本，对应数据字典表（dictionary）中的编码字段（name）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。
	 */
	public String getSentimentTypeText() {
		return this.sentimentTypeText;
	}

	/**
	 * set方法。情感值（类型）。文本，对应数据字典表（dictionary）中的编码字段（name）。和舆情团队提供的接口说明中一至，目前3种类型：1负面，2中性，3正面。
	 */
	public void setSentimentTypeText(String sentimentTypeText) {
		this.sentimentTypeText = sentimentTypeText;
	}
    
	/**
	 * get方法。编号。非空唯一，由数据提供方制定，标志此条文章记录唯一。
	 */
	public String getSerialNumber() {
		return this.serialNumber;
	}

	/**
	 * set方法。编号。非空唯一，由数据提供方制定，标志此条文章记录唯一。
	 */
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
    
	/**
	 * get方法。对应主题表的id
	 */
	public String getThemeId() {
		return this.themeId;
	}

	/**
	 * set方法。对应主题表的id
	 */
	public void setThemeId(String themeId) {
		this.themeId = themeId;
	}
    
	/**
	 * get方法。媒体名称。
	 */
	public String getThreadStarter() {
		return this.threadStarter;
	}

	/**
	 * set方法。媒体名称。
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
	 * get方法。审核人，对应用户表userId
	 */
	public String getUpdateUserId() {
		return this.updateUserId;
	}

	/**
	 * set方法。审核人，对应用户表userId
	 */
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
    
	/**
	 * get方法。访问路径
	 */
	public String getUrl() {
		return this.url;
	}

	/**
	 * set方法。访问路径
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
    

    //一对多关系中，多端数据列表

}







