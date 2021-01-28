package com.zee.ent.generate.da;

import java.io.Serializable;
import java.math.BigDecimal;

import java.util.*;
import io.swagger.annotations.ApiModelProperty;

import com.zee.ent.base.BaseEnt;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/28 16:08:53
 * @description 实体类DaEnterpriseInfoGenEnt，自动生成。da_enterprise_info
 */

public class DaEnterpriseInfoGenEnt extends BaseEnt implements Serializable {
    
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String address;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String administrationNumber;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String administrativeArea;
    @ApiModelProperty(value="核准日期",hidden=false,required=false)
    private Date approvalDate;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String archivesNumber;
    @ApiModelProperty(value="营业期限",hidden=false,required=false)
    private Date businessDuetime;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String businessScope;
    @ApiModelProperty(value="评估类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前11种评估类型：1企业综合风险评估，2成长风险综合评估，3行业地位与竞争力评估，4偿债风险综合评估，5运营风险综合评估，6盈利风险综合评估，7竞争能力综合评估，8诉讼处罚风险评估，9资本运作风险评估，10舆情风险评估，11核心团队风险评估。",allowableValues="0,1",hidden=false,required=false)
    private Byte categoryCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String categoryText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String commonFieldId;
    @ApiModelProperty(value="核心企业。编码，对应数据字典表（dictionary）中的编码字段（code） 0：核心企业 1：关联企业",allowableValues="0,1",hidden=false,required=false)
    private Byte coreCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String coreText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String corporationIdentityCard;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String corporationMobile;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String corporationName;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String description;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String email;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseAddress;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String enterpriseCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseControl;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseName;
    @ApiModelProperty(value="企业性质。编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：国营  2:私人",allowableValues="0,1",hidden=false,required=false)
    private Byte enterpriseNatureCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseNatureText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterprisePhone;
    @ApiModelProperty(value="企业类型，编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。5：生产企业,6:加工企业7:销售企业,8:投入品经营企业",allowableValues="0,1",hidden=false,required=false)
    private Byte enterpriseTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String enterpriseTypeText;
    @ApiModelProperty(value="成立日期",hidden=false,required=false)
    private Date establishDate;
    @ApiModelProperty(value="成立时间",hidden=false,required=false)
    private Date foundDate;
    @ApiModelProperty(value="漏斗标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：资金缺口 2：业务稳定 3：诚信良好 4：舆情正常 5：市场预期良好。可以有多个用|进行分隔。",allowableValues="0,1",hidden=false,required=false)
    private Byte funnelTagsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String funnelTagsText;
    @ApiModelProperty(value="",hidden=false,required=true)
    private String id;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String industryActivity;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String industryCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String industryText;
    @ApiModelProperty(value="企业产业类型.编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：种植企业  2:畜牧养殖",allowableValues="0,1",hidden=false,required=false)
    private Byte industryTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String industryTypeText;
    @ApiModelProperty(value="是否合作社编码，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否",allowableValues="0,1",hidden=false,required=false)
    private Byte isCooperativeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String isCooperativeText;
    @ApiModelProperty(value="是否是龙头企业，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否",allowableValues="0,1",hidden=false,required=false)
    private Byte isCorporateChampionTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String isCorporateChampionTypeText;
    @ApiModelProperty(value="是否推介，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否",hidden=false,required=false)
    private Byte isRecommend;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String landlineTelephone;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String linkman;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String linkmanMobile;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String logoResourceId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String logoResourcePath;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String longdistanceNumber;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String manageStatus;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String measureLatitude;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String measureLongitude;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String moneyScale;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String organizationCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String organizationId;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String organizationTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String organizationTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal paidinCapital;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String personScale;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String professionalActivity;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String regionCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String regionText;
    @ApiModelProperty(value="登记状态",allowableValues="0,1",hidden=false,required=false)
    private Byte registerStatusCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String registerStatusText;
    @ApiModelProperty(value="登记注册类型",allowableValues="0,1",hidden=false,required=false)
    private Integer registerTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String registerTypeText;
    @ApiModelProperty(value="注册资金",hidden=false,required=false)
    private BigDecimal registeredCapital;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String registrationMark;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String registryOffice;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String riskGrade;
    @ApiModelProperty(value="风险标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型    1：交易额持续下滑 2：拖欠工资 3：债务拖欠 4：履约不利 5：违法侵权    
            可以有多个用|进行分隔",allowableValues="0,1",hidden=false,required=false)
    private Byte riskTagsCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String riskTagsText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private BigDecimal riskValue;
    @ApiModelProperty(value="规模企业类型，对应数据字典表（dictionary）中的文本字段（name） 1：规模企业",allowableValues="0,1",hidden=false,required=false)
    private Byte scaleEnterpriseTypeCode;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String scaleEnterpriseTypeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String societyCreditId;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String statisticsDepartmentNumber;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String summary;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String telautogram;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String tradeText;
    @ApiModelProperty(value="",hidden=false,required=false)
    private String yearThey;
    @ApiModelProperty(value="",allowableValues="0,1",hidden=false,required=false)
    private String zipCode;

   //本表做为子表时，父表实体对象

    //本表做为父表时，子表数据列表

    //父子表均为自身时


	/**
	 * get方法。
	 */
	public String getAddress() {
		return this.address;
	}

	/**
	 * set方法。
	 */
	public void setAddress(String address) {
		this.address = address;
	}
    
	/**
	 * get方法。
	 */
	public String getAdministrationNumber() {
		return this.administrationNumber;
	}

	/**
	 * set方法。
	 */
	public void setAdministrationNumber(String administrationNumber) {
		this.administrationNumber = administrationNumber;
	}
    
	/**
	 * get方法。
	 */
	public String getAdministrativeArea() {
		return this.administrativeArea;
	}

	/**
	 * set方法。
	 */
	public void setAdministrativeArea(String administrativeArea) {
		this.administrativeArea = administrativeArea;
	}
    
	/**
	 * get方法。核准日期
	 */
	public Date getApprovalDate() {
		return this.approvalDate;
	}

	/**
	 * set方法。核准日期
	 */
	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}
    
	/**
	 * get方法。
	 */
	public String getArchivesNumber() {
		return this.archivesNumber;
	}

	/**
	 * set方法。
	 */
	public void setArchivesNumber(String archivesNumber) {
		this.archivesNumber = archivesNumber;
	}
    
	/**
	 * get方法。营业期限
	 */
	public Date getBusinessDuetime() {
		return this.businessDuetime;
	}

	/**
	 * set方法。营业期限
	 */
	public void setBusinessDuetime(Date businessDuetime) {
		this.businessDuetime = businessDuetime;
	}
    
	/**
	 * get方法。
	 */
	public String getBusinessScope() {
		return this.businessScope;
	}

	/**
	 * set方法。
	 */
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
    
	/**
	 * get方法。评估类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前11种评估类型：1企业综合风险评估，2成长风险综合评估，3行业地位与竞争力评估，4偿债风险综合评估，5运营风险综合评估，6盈利风险综合评估，7竞争能力综合评估，8诉讼处罚风险评估，9资本运作风险评估，10舆情风险评估，11核心团队风险评估。
	 */
	public Byte getCategoryCode() {
		return this.categoryCode;
	}

	/**
	 * set方法。评估类型。编码，对应数据字典表（dictionary）中的编码字段（code）。目前11种评估类型：1企业综合风险评估，2成长风险综合评估，3行业地位与竞争力评估，4偿债风险综合评估，5运营风险综合评估，6盈利风险综合评估，7竞争能力综合评估，8诉讼处罚风险评估，9资本运作风险评估，10舆情风险评估，11核心团队风险评估。
	 */
	public void setCategoryCode(Byte categoryCode) {
		this.categoryCode = categoryCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCategoryText() {
		return this.categoryText;
	}

	/**
	 * set方法。
	 */
	public void setCategoryText(String categoryText) {
		this.categoryText = categoryText;
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
	 * get方法。核心企业。编码，对应数据字典表（dictionary）中的编码字段（code） 0：核心企业 1：关联企业
	 */
	public Byte getCoreCode() {
		return this.coreCode;
	}

	/**
	 * set方法。核心企业。编码，对应数据字典表（dictionary）中的编码字段（code） 0：核心企业 1：关联企业
	 */
	public void setCoreCode(Byte coreCode) {
		this.coreCode = coreCode;
	}
    
	/**
	 * get方法。
	 */
	public String getCoreText() {
		return this.coreText;
	}

	/**
	 * set方法。
	 */
	public void setCoreText(String coreText) {
		this.coreText = coreText;
	}
    
	/**
	 * get方法。
	 */
	public String getCorporationIdentityCard() {
		return this.corporationIdentityCard;
	}

	/**
	 * set方法。
	 */
	public void setCorporationIdentityCard(String corporationIdentityCard) {
		this.corporationIdentityCard = corporationIdentityCard;
	}
    
	/**
	 * get方法。
	 */
	public String getCorporationMobile() {
		return this.corporationMobile;
	}

	/**
	 * set方法。
	 */
	public void setCorporationMobile(String corporationMobile) {
		this.corporationMobile = corporationMobile;
	}
    
	/**
	 * get方法。
	 */
	public String getCorporationName() {
		return this.corporationName;
	}

	/**
	 * set方法。
	 */
	public void setCorporationName(String corporationName) {
		this.corporationName = corporationName;
	}
    
	/**
	 * get方法。
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * set方法。
	 */
	public void setDescription(String description) {
		this.description = description;
	}
    
	/**
	 * get方法。
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * set方法。
	 */
	public void setEmail(String email) {
		this.email = email;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseAddress() {
		return this.enterpriseAddress;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseAddress(String enterpriseAddress) {
		this.enterpriseAddress = enterpriseAddress;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseCode() {
		return this.enterpriseCode;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseCode(String enterpriseCode) {
		this.enterpriseCode = enterpriseCode;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseControl() {
		return this.enterpriseControl;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseControl(String enterpriseControl) {
		this.enterpriseControl = enterpriseControl;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseName() {
		return this.enterpriseName;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
    
	/**
	 * get方法。企业性质。编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：国营  2:私人
	 */
	public Byte getEnterpriseNatureCode() {
		return this.enterpriseNatureCode;
	}

	/**
	 * set方法。企业性质。编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：国营  2:私人
	 */
	public void setEnterpriseNatureCode(Byte enterpriseNatureCode) {
		this.enterpriseNatureCode = enterpriseNatureCode;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseNatureText() {
		return this.enterpriseNatureText;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseNatureText(String enterpriseNatureText) {
		this.enterpriseNatureText = enterpriseNatureText;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterprisePhone() {
		return this.enterprisePhone;
	}

	/**
	 * set方法。
	 */
	public void setEnterprisePhone(String enterprisePhone) {
		this.enterprisePhone = enterprisePhone;
	}
    
	/**
	 * get方法。企业类型，编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。5：生产企业,6:加工企业7:销售企业,8:投入品经营企业
	 */
	public Byte getEnterpriseTypeCode() {
		return this.enterpriseTypeCode;
	}

	/**
	 * set方法。企业类型，编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。5：生产企业,6:加工企业7:销售企业,8:投入品经营企业
	 */
	public void setEnterpriseTypeCode(Byte enterpriseTypeCode) {
		this.enterpriseTypeCode = enterpriseTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getEnterpriseTypeText() {
		return this.enterpriseTypeText;
	}

	/**
	 * set方法。
	 */
	public void setEnterpriseTypeText(String enterpriseTypeText) {
		this.enterpriseTypeText = enterpriseTypeText;
	}
    
	/**
	 * get方法。成立日期
	 */
	public Date getEstablishDate() {
		return this.establishDate;
	}

	/**
	 * set方法。成立日期
	 */
	public void setEstablishDate(Date establishDate) {
		this.establishDate = establishDate;
	}
    
	/**
	 * get方法。成立时间
	 */
	public Date getFoundDate() {
		return this.foundDate;
	}

	/**
	 * set方法。成立时间
	 */
	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}
    
	/**
	 * get方法。漏斗标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：资金缺口 2：业务稳定 3：诚信良好 4：舆情正常 5：市场预期良好。可以有多个用|进行分隔。
	 */
	public Byte getFunnelTagsCode() {
		return this.funnelTagsCode;
	}

	/**
	 * set方法。漏斗标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型   1：资金缺口 2：业务稳定 3：诚信良好 4：舆情正常 5：市场预期良好。可以有多个用|进行分隔。
	 */
	public void setFunnelTagsCode(Byte funnelTagsCode) {
		this.funnelTagsCode = funnelTagsCode;
	}
    
	/**
	 * get方法。
	 */
	public String getFunnelTagsText() {
		return this.funnelTagsText;
	}

	/**
	 * set方法。
	 */
	public void setFunnelTagsText(String funnelTagsText) {
		this.funnelTagsText = funnelTagsText;
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
	public String getIndustryActivity() {
		return this.industryActivity;
	}

	/**
	 * set方法。
	 */
	public void setIndustryActivity(String industryActivity) {
		this.industryActivity = industryActivity;
	}
    
	/**
	 * get方法。
	 */
	public String getIndustryCode() {
		return this.industryCode;
	}

	/**
	 * set方法。
	 */
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
    
	/**
	 * get方法。
	 */
	public String getIndustryText() {
		return this.industryText;
	}

	/**
	 * set方法。
	 */
	public void setIndustryText(String industryText) {
		this.industryText = industryText;
	}
    
	/**
	 * get方法。企业产业类型.编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：种植企业  2:畜牧养殖
	 */
	public Byte getIndustryTypeCode() {
		return this.industryTypeCode;
	}

	/**
	 * set方法。企业产业类型.编码，对应数据字典表（dictionary）中的编码字段（code）。 目前定义2种类型。1：种植企业  2:畜牧养殖
	 */
	public void setIndustryTypeCode(Byte industryTypeCode) {
		this.industryTypeCode = industryTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getIndustryTypeText() {
		return this.industryTypeText;
	}

	/**
	 * set方法。
	 */
	public void setIndustryTypeText(String industryTypeText) {
		this.industryTypeText = industryTypeText;
	}
    
	/**
	 * get方法。是否合作社编码，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否
	 */
	public Byte getIsCooperativeCode() {
		return this.isCooperativeCode;
	}

	/**
	 * set方法。是否合作社编码，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否
	 */
	public void setIsCooperativeCode(Byte isCooperativeCode) {
		this.isCooperativeCode = isCooperativeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getIsCooperativeText() {
		return this.isCooperativeText;
	}

	/**
	 * set方法。
	 */
	public void setIsCooperativeText(String isCooperativeText) {
		this.isCooperativeText = isCooperativeText;
	}
    
	/**
	 * get方法。是否是龙头企业，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否
	 */
	public Byte getIsCorporateChampionTypeCode() {
		return this.isCorporateChampionTypeCode;
	}

	/**
	 * set方法。是否是龙头企业，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否
	 */
	public void setIsCorporateChampionTypeCode(Byte isCorporateChampionTypeCode) {
		this.isCorporateChampionTypeCode = isCorporateChampionTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getIsCorporateChampionTypeText() {
		return this.isCorporateChampionTypeText;
	}

	/**
	 * set方法。
	 */
	public void setIsCorporateChampionTypeText(String isCorporateChampionTypeText) {
		this.isCorporateChampionTypeText = isCorporateChampionTypeText;
	}
    
	/**
	 * get方法。是否推介，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否
	 */
	public Byte getIsRecommend() {
		return this.isRecommend;
	}

	/**
	 * set方法。是否推介，对应数据字典表（dictionary）中的文本字段（name）0：是 1：否
	 */
	public void setIsRecommend(Byte isRecommend) {
		this.isRecommend = isRecommend;
	}
    
	/**
	 * get方法。
	 */
	public String getLandlineTelephone() {
		return this.landlineTelephone;
	}

	/**
	 * set方法。
	 */
	public void setLandlineTelephone(String landlineTelephone) {
		this.landlineTelephone = landlineTelephone;
	}
    
	/**
	 * get方法。
	 */
	public String getLinkman() {
		return this.linkman;
	}

	/**
	 * set方法。
	 */
	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}
    
	/**
	 * get方法。
	 */
	public String getLinkmanMobile() {
		return this.linkmanMobile;
	}

	/**
	 * set方法。
	 */
	public void setLinkmanMobile(String linkmanMobile) {
		this.linkmanMobile = linkmanMobile;
	}
    
	/**
	 * get方法。
	 */
	public String getLogoResourceId() {
		return this.logoResourceId;
	}

	/**
	 * set方法。
	 */
	public void setLogoResourceId(String logoResourceId) {
		this.logoResourceId = logoResourceId;
	}
    
	/**
	 * get方法。
	 */
	public String getLogoResourcePath() {
		return this.logoResourcePath;
	}

	/**
	 * set方法。
	 */
	public void setLogoResourcePath(String logoResourcePath) {
		this.logoResourcePath = logoResourcePath;
	}
    
	/**
	 * get方法。
	 */
	public String getLongdistanceNumber() {
		return this.longdistanceNumber;
	}

	/**
	 * set方法。
	 */
	public void setLongdistanceNumber(String longdistanceNumber) {
		this.longdistanceNumber = longdistanceNumber;
	}
    
	/**
	 * get方法。
	 */
	public String getManageStatus() {
		return this.manageStatus;
	}

	/**
	 * set方法。
	 */
	public void setManageStatus(String manageStatus) {
		this.manageStatus = manageStatus;
	}
    
	/**
	 * get方法。
	 */
	public String getMeasureLatitude() {
		return this.measureLatitude;
	}

	/**
	 * set方法。
	 */
	public void setMeasureLatitude(String measureLatitude) {
		this.measureLatitude = measureLatitude;
	}
    
	/**
	 * get方法。
	 */
	public String getMeasureLongitude() {
		return this.measureLongitude;
	}

	/**
	 * set方法。
	 */
	public void setMeasureLongitude(String measureLongitude) {
		this.measureLongitude = measureLongitude;
	}
    
	/**
	 * get方法。
	 */
	public String getMoneyScale() {
		return this.moneyScale;
	}

	/**
	 * set方法。
	 */
	public void setMoneyScale(String moneyScale) {
		this.moneyScale = moneyScale;
	}
    
	/**
	 * get方法。
	 */
	public String getOrganizationCode() {
		return this.organizationCode;
	}

	/**
	 * set方法。
	 */
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
    
	/**
	 * get方法。
	 */
	public String getOrganizationId() {
		return this.organizationId;
	}

	/**
	 * set方法。
	 */
	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}
    
	/**
	 * get方法。
	 */
	public String getOrganizationTypeCode() {
		return this.organizationTypeCode;
	}

	/**
	 * set方法。
	 */
	public void setOrganizationTypeCode(String organizationTypeCode) {
		this.organizationTypeCode = organizationTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getOrganizationTypeText() {
		return this.organizationTypeText;
	}

	/**
	 * set方法。
	 */
	public void setOrganizationTypeText(String organizationTypeText) {
		this.organizationTypeText = organizationTypeText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getPaidinCapital() {
		return this.paidinCapital;
	}

	/**
	 * set方法。
	 */
	public void setPaidinCapital(BigDecimal paidinCapital) {
		this.paidinCapital = paidinCapital;
	}
    
	/**
	 * get方法。
	 */
	public String getPersonScale() {
		return this.personScale;
	}

	/**
	 * set方法。
	 */
	public void setPersonScale(String personScale) {
		this.personScale = personScale;
	}
    
	/**
	 * get方法。
	 */
	public String getProfessionalActivity() {
		return this.professionalActivity;
	}

	/**
	 * set方法。
	 */
	public void setProfessionalActivity(String professionalActivity) {
		this.professionalActivity = professionalActivity;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionCode() {
		return this.regionCode;
	}

	/**
	 * set方法。
	 */
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRegionText() {
		return this.regionText;
	}

	/**
	 * set方法。
	 */
	public void setRegionText(String regionText) {
		this.regionText = regionText;
	}
    
	/**
	 * get方法。登记状态
	 */
	public Byte getRegisterStatusCode() {
		return this.registerStatusCode;
	}

	/**
	 * set方法。登记状态
	 */
	public void setRegisterStatusCode(Byte registerStatusCode) {
		this.registerStatusCode = registerStatusCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRegisterStatusText() {
		return this.registerStatusText;
	}

	/**
	 * set方法。
	 */
	public void setRegisterStatusText(String registerStatusText) {
		this.registerStatusText = registerStatusText;
	}
    
	/**
	 * get方法。登记注册类型
	 */
	public Integer getRegisterTypeCode() {
		return this.registerTypeCode;
	}

	/**
	 * set方法。登记注册类型
	 */
	public void setRegisterTypeCode(Integer registerTypeCode) {
		this.registerTypeCode = registerTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRegisterTypeText() {
		return this.registerTypeText;
	}

	/**
	 * set方法。
	 */
	public void setRegisterTypeText(String registerTypeText) {
		this.registerTypeText = registerTypeText;
	}
    
	/**
	 * get方法。注册资金
	 */
	public BigDecimal getRegisteredCapital() {
		return this.registeredCapital;
	}

	/**
	 * set方法。注册资金
	 */
	public void setRegisteredCapital(BigDecimal registeredCapital) {
		this.registeredCapital = registeredCapital;
	}
    
	/**
	 * get方法。
	 */
	public String getRegistrationMark() {
		return this.registrationMark;
	}

	/**
	 * set方法。
	 */
	public void setRegistrationMark(String registrationMark) {
		this.registrationMark = registrationMark;
	}
    
	/**
	 * get方法。
	 */
	public String getRegistryOffice() {
		return this.registryOffice;
	}

	/**
	 * set方法。
	 */
	public void setRegistryOffice(String registryOffice) {
		this.registryOffice = registryOffice;
	}
    
	/**
	 * get方法。
	 */
	public String getRiskGrade() {
		return this.riskGrade;
	}

	/**
	 * set方法。
	 */
	public void setRiskGrade(String riskGrade) {
		this.riskGrade = riskGrade;
	}
    
	/**
	 * get方法。风险标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型    1：交易额持续下滑 2：拖欠工资 3：债务拖欠 4：履约不利 5：违法侵权    
            可以有多个用|进行分隔
	 */
	public Byte getRiskTagsCode() {
		return this.riskTagsCode;
	}

	/**
	 * set方法。风险标签。编码，对应数据字典表（dictionary）中的编码字段（code）目前定义5种类型    1：交易额持续下滑 2：拖欠工资 3：债务拖欠 4：履约不利 5：违法侵权    
            可以有多个用|进行分隔
	 */
	public void setRiskTagsCode(Byte riskTagsCode) {
		this.riskTagsCode = riskTagsCode;
	}
    
	/**
	 * get方法。
	 */
	public String getRiskTagsText() {
		return this.riskTagsText;
	}

	/**
	 * set方法。
	 */
	public void setRiskTagsText(String riskTagsText) {
		this.riskTagsText = riskTagsText;
	}
    
	/**
	 * get方法。
	 */
	public BigDecimal getRiskValue() {
		return this.riskValue;
	}

	/**
	 * set方法。
	 */
	public void setRiskValue(BigDecimal riskValue) {
		this.riskValue = riskValue;
	}
    
	/**
	 * get方法。规模企业类型，对应数据字典表（dictionary）中的文本字段（name） 1：规模企业
	 */
	public Byte getScaleEnterpriseTypeCode() {
		return this.scaleEnterpriseTypeCode;
	}

	/**
	 * set方法。规模企业类型，对应数据字典表（dictionary）中的文本字段（name） 1：规模企业
	 */
	public void setScaleEnterpriseTypeCode(Byte scaleEnterpriseTypeCode) {
		this.scaleEnterpriseTypeCode = scaleEnterpriseTypeCode;
	}
    
	/**
	 * get方法。
	 */
	public String getScaleEnterpriseTypeText() {
		return this.scaleEnterpriseTypeText;
	}

	/**
	 * set方法。
	 */
	public void setScaleEnterpriseTypeText(String scaleEnterpriseTypeText) {
		this.scaleEnterpriseTypeText = scaleEnterpriseTypeText;
	}
    
	/**
	 * get方法。
	 */
	public String getSocietyCreditId() {
		return this.societyCreditId;
	}

	/**
	 * set方法。
	 */
	public void setSocietyCreditId(String societyCreditId) {
		this.societyCreditId = societyCreditId;
	}
    
	/**
	 * get方法。
	 */
	public String getStatisticsDepartmentNumber() {
		return this.statisticsDepartmentNumber;
	}

	/**
	 * set方法。
	 */
	public void setStatisticsDepartmentNumber(String statisticsDepartmentNumber) {
		this.statisticsDepartmentNumber = statisticsDepartmentNumber;
	}
    
	/**
	 * get方法。
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * set方法。
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
    
	/**
	 * get方法。
	 */
	public String getTelautogram() {
		return this.telautogram;
	}

	/**
	 * set方法。
	 */
	public void setTelautogram(String telautogram) {
		this.telautogram = telautogram;
	}
    
	/**
	 * get方法。
	 */
	public String getTradeText() {
		return this.tradeText;
	}

	/**
	 * set方法。
	 */
	public void setTradeText(String tradeText) {
		this.tradeText = tradeText;
	}
    
	/**
	 * get方法。
	 */
	public String getYearThey() {
		return this.yearThey;
	}

	/**
	 * set方法。
	 */
	public void setYearThey(String yearThey) {
		this.yearThey = yearThey;
	}
    
	/**
	 * get方法。
	 */
	public String getZipCode() {
		return this.zipCode;
	}

	/**
	 * set方法。
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
    










}







