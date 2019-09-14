package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEnterpriseCertificateInfo;
import com.jusfoun.ent.generate.da.DaEnterpriseCertificateInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-9-14 13:59:04
 * @description 实体类DaEnterpriseCertificateInfoParameter，方法参数，自动生成。企业证书
 */

public class DaEnterpriseCertificateInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseCertificateInfoAddList", description = "批量添加DaEnterpriseCertificateInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseCertificateInfo> entityList = new ArrayList<DaEnterpriseCertificateInfo>();

		public ArrayList<DaEnterpriseCertificateInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseCertificateInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseCertificateInfoDeleteByIdList", description = "批量删除DaEnterpriseCertificateInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseCertificateInfoUpdateList", description = "批量修改DaEnterpriseCertificateInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseCertificateInfo entity = new DaEnterpriseCertificateInfo();

		public DaEnterpriseCertificateInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseCertificateInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseCertificateInfoGetList", description = "模糊查询DaEnterpriseCertificateInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseCertificateInfoGetListEntityRelated", description = "模糊查询DaEnterpriseCertificateInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseCertificateInfoGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreatedTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreatedTime;

        @ApiModelProperty(value="有效期至查询起止时间。",required=false)
		private Date beginEndDate;

        @ApiModelProperty(value="有效期至查询结束时间。",required=false)
		private Date endEndDate;

        @ApiModelProperty(value="公示时间查询起止时间。",required=false)
		private Date beginPublicityTime;

        @ApiModelProperty(value="公示时间查询结束时间。",required=false)
		private Date endPublicityTime;

        @ApiModelProperty(value="有效期自查询起止时间。",required=false)
		private Date beginStartDate;

        @ApiModelProperty(value="有效期自查询结束时间。",required=false)
		private Date endStartDate;

		public Date getBeginCreatedTime() {
			return this.beginCreatedTime;
		}
        
		public void setBeginCreatedTime(Date beginCreatedTime) {
			this.beginCreatedTime = beginCreatedTime;
		}
        
        public Date getEndCreatedTime() {
			return this.endCreatedTime;
		}
        
		public void setEndCreatedTime(Date endCreatedTime) {
			this.endCreatedTime = endCreatedTime;
		}
        
		public Date getBeginEndDate() {
			return this.beginEndDate;
		}
        
		public void setBeginEndDate(Date beginEndDate) {
			this.beginEndDate = beginEndDate;
		}
        
        public Date getEndEndDate() {
			return this.endEndDate;
		}
        
		public void setEndEndDate(Date endEndDate) {
			this.endEndDate = endEndDate;
		}
        
		public Date getBeginPublicityTime() {
			return this.beginPublicityTime;
		}
        
		public void setBeginPublicityTime(Date beginPublicityTime) {
			this.beginPublicityTime = beginPublicityTime;
		}
        
        public Date getEndPublicityTime() {
			return this.endPublicityTime;
		}
        
		public void setEndPublicityTime(Date endPublicityTime) {
			this.endPublicityTime = endPublicityTime;
		}
        
		public Date getBeginStartDate() {
			return this.beginStartDate;
		}
        
		public void setBeginStartDate(Date beginStartDate) {
			this.beginStartDate = beginStartDate;
		}
        
        public Date getEndStartDate() {
			return this.endStartDate;
		}
        
		public void setEndStartDate(Date endStartDate) {
			this.endStartDate = endStartDate;
		}
        
		}
	}

}







