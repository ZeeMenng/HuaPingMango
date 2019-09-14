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
 * @updateDate 2018-6-13 19:04:29
 * @description 实体类DaCommonFieldGenEnt，自动生成。通用字段表
 */

public class DaCommonFieldGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="记录创建时间",hidden=false,required=false)
    private Date addTime;
    @ApiModelProperty(value="创建人，对应用户表userId",hidden=false,required=false)
    private String addUserId;
    @ApiModelProperty(value="区域维度：对应数据字典表（dictionary）中的编码字段（code）1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体",allowableValues="0,1",hidden=false,required=false)
    private Byte areaLatitudeTypeCode;
    @ApiModelProperty(value="区域维度：文本，对应数据字典表（dictionary）中的文本字段（text）1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体",hidden=false,required=false)
    private String areaLatitudeTypeText;
    @ApiModelProperty(value="审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过",allowableValues="0,1",hidden=false,required=false)
    private Byte auditStatusCode;
    @ApiModelProperty(value="审核状态文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证0：待审核，1:审核通过，2：审核不通过",hidden=false,required=false)
    private String auditStatusText;
    @ApiModelProperty(value="审核意见",hidden=false,required=false)
    private String auditerSuggestion;
    @ApiModelProperty(value="审核时间",hidden=false,required=false)
    private Date auditerTime;
    @ApiModelProperty(value="审核人，对应用户表userId",hidden=false,required=false)
    private String auditerUserId;
    @ApiModelProperty(value="数据来源",hidden=false,required=false)
    private String dataSources;
    @ApiModelProperty(value="数据采集维度：对应数据字典表（dictionary）中的编码字段（code）,1：年，2：季度，3：月，4：周，5：日，6：实时",allowableValues="0,1",hidden=false,required=false)
    private Byte dataTimeTypeCode;
    @ApiModelProperty(value="数据采集维度：文本，对应数据字典表（dictionary）中的文本字段（text）1：年，2：季度，3：月，4：周，5：日，6：实时",hidden=false,required=false)
    private String dataTimeTypeText;
    @ApiModelProperty(value="结束时间",hidden=false,required=false)
    private Date endTime;
    @ApiModelProperty(value="主键id",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="地理区域 :对应区域表",hidden=false,required=false)
    private String regionId;
    @ApiModelProperty(value="地理区域名称",hidden=false,required=false)
    private String regionName;
    @ApiModelProperty(value="备注",hidden=false,required=false)
    private String remark;
    @ApiModelProperty(value="来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报",allowableValues="0,1",hidden=false,required=false)
    private Byte sourceChannelTypeCode;
    @ApiModelProperty(value="来源渠道：文本，对应数据字典表（dictionary）中的文本字段（text）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报",hidden=false,required=false)
    private String sourceChannelTypeText;
    @ApiModelProperty(value="开始时间",hidden=false,required=false)
    private Date startTime;
    @ApiModelProperty(value="记录最后一次修改时间",hidden=false,required=false)
    private Date updateTime;
    //多对一关系中，一端实体对象

    //一对多关系中，多端数据列表

	/**
	 * get方法。记录创建时间
	 */
	public Date getAddTime() {
		return this.addTime;
	}

	/**
	 * set方法。记录创建时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
    
	/**
	 * get方法。创建人，对应用户表userId
	 */
	public String getAddUserId() {
		return this.addUserId;
	}

	/**
	 * set方法。创建人，对应用户表userId
	 */
	public void setAddUserId(String addUserId) {
		this.addUserId = addUserId;
	}
    
	/**
	 * get方法。区域维度：对应数据字典表（dictionary）中的编码字段（code）1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体
	 */
	public Byte getAreaLatitudeTypeCode() {
		return this.areaLatitudeTypeCode;
	}

	/**
	 * set方法。区域维度：对应数据字典表（dictionary）中的编码字段（code）1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体
	 */
	public void setAreaLatitudeTypeCode(Byte areaLatitudeTypeCode) {
		this.areaLatitudeTypeCode = areaLatitudeTypeCode;
	}
    
	/**
	 * get方法。区域维度：文本，对应数据字典表（dictionary）中的文本字段（text）1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体
	 */
	public String getAreaLatitudeTypeText() {
		return this.areaLatitudeTypeText;
	}

	/**
	 * set方法。区域维度：文本，对应数据字典表（dictionary）中的文本字段（text）1：国家，2：省，3：市，4：县/区，5：乡/镇，6：村/组/社区，7：农户个体，8：企业个体
	 */
	public void setAreaLatitudeTypeText(String areaLatitudeTypeText) {
		this.areaLatitudeTypeText = areaLatitudeTypeText;
	}
    
	/**
	 * get方法。审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过
	 */
	public Byte getAuditStatusCode() {
		return this.auditStatusCode;
	}

	/**
	 * set方法。审核状态：对应数据字典表（dictionary）中的编码字段（code）0：待审核，1:审核通过，2：审核不通过
	 */
	public void setAuditStatusCode(Byte auditStatusCode) {
		this.auditStatusCode = auditStatusCode;
	}
    
	/**
	 * get方法。审核状态文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证0：待审核，1:审核通过，2：审核不通过
	 */
	public String getAuditStatusText() {
		return this.auditStatusText;
	}

	/**
	 * set方法。审核状态文本：文本，对应数据字典表（dictionary）中的文本字段（text） 1：无公害认证，2：有机认证，3：绿色认证，4：其他认证0：待审核，1:审核通过，2：审核不通过
	 */
	public void setAuditStatusText(String auditStatusText) {
		this.auditStatusText = auditStatusText;
	}
    
	/**
	 * get方法。审核意见
	 */
	public String getAuditerSuggestion() {
		return this.auditerSuggestion;
	}

	/**
	 * set方法。审核意见
	 */
	public void setAuditerSuggestion(String auditerSuggestion) {
		this.auditerSuggestion = auditerSuggestion;
	}
    
	/**
	 * get方法。审核时间
	 */
	public Date getAuditerTime() {
		return this.auditerTime;
	}

	/**
	 * set方法。审核时间
	 */
	public void setAuditerTime(Date auditerTime) {
		this.auditerTime = auditerTime;
	}
    
	/**
	 * get方法。审核人，对应用户表userId
	 */
	public String getAuditerUserId() {
		return this.auditerUserId;
	}

	/**
	 * set方法。审核人，对应用户表userId
	 */
	public void setAuditerUserId(String auditerUserId) {
		this.auditerUserId = auditerUserId;
	}
    
	/**
	 * get方法。数据来源
	 */
	public String getDataSources() {
		return this.dataSources;
	}

	/**
	 * set方法。数据来源
	 */
	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}
    
	/**
	 * get方法。数据采集维度：对应数据字典表（dictionary）中的编码字段（code）,1：年，2：季度，3：月，4：周，5：日，6：实时
	 */
	public Byte getDataTimeTypeCode() {
		return this.dataTimeTypeCode;
	}

	/**
	 * set方法。数据采集维度：对应数据字典表（dictionary）中的编码字段（code）,1：年，2：季度，3：月，4：周，5：日，6：实时
	 */
	public void setDataTimeTypeCode(Byte dataTimeTypeCode) {
		this.dataTimeTypeCode = dataTimeTypeCode;
	}
    
	/**
	 * get方法。数据采集维度：文本，对应数据字典表（dictionary）中的文本字段（text）1：年，2：季度，3：月，4：周，5：日，6：实时
	 */
	public String getDataTimeTypeText() {
		return this.dataTimeTypeText;
	}

	/**
	 * set方法。数据采集维度：文本，对应数据字典表（dictionary）中的文本字段（text）1：年，2：季度，3：月，4：周，5：日，6：实时
	 */
	public void setDataTimeTypeText(String dataTimeTypeText) {
		this.dataTimeTypeText = dataTimeTypeText;
	}
    
	/**
	 * get方法。结束时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}

	/**
	 * set方法。结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	 * get方法。地理区域 :对应区域表
	 */
	public String getRegionId() {
		return this.regionId;
	}

	/**
	 * set方法。地理区域 :对应区域表
	 */
	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}
    
	/**
	 * get方法。地理区域名称
	 */
	public String getRegionName() {
		return this.regionName;
	}

	/**
	 * set方法。地理区域名称
	 */
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
    
	/**
	 * get方法。备注
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * set方法。备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	/**
	 * get方法。来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public Byte getSourceChannelTypeCode() {
		return this.sourceChannelTypeCode;
	}

	/**
	 * set方法。来源渠道：对应数据字典表（dictionary）中的编码字段（code）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public void setSourceChannelTypeCode(Byte sourceChannelTypeCode) {
		this.sourceChannelTypeCode = sourceChannelTypeCode;
	}
    
	/**
	 * get方法。来源渠道：文本，对应数据字典表（dictionary）中的文本字段（text）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public String getSourceChannelTypeText() {
		return this.sourceChannelTypeText;
	}

	/**
	 * set方法。来源渠道：文本，对应数据字典表（dictionary）中的文本字段（text）1：农户APP填报，2：农户web填报，3：政府人员采集，4：网络采集，5：第三方对接采集，6：其他，7：企业web填报
	 */
	public void setSourceChannelTypeText(String sourceChannelTypeText) {
		this.sourceChannelTypeText = sourceChannelTypeText;
	}
    
	/**
	 * get方法。开始时间
	 */
	public Date getStartTime() {
		return this.startTime;
	}

	/**
	 * set方法。开始时间
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
    
	/**
	 * get方法。记录最后一次修改时间
	 */
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * set方法。记录最后一次修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
    

    //一对多关系中，多端数据列表

}







