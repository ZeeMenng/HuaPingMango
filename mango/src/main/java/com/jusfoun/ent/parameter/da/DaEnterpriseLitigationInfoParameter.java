package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEnterpriseLitigationInfo;
import com.jusfoun.ent.generate.da.DaEnterpriseLitigationInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaEnterpriseLitigationInfoParameter，方法参数，自动生成。企业诉讼表
 */

public class DaEnterpriseLitigationInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseLitigationInfoAddList", description = "批量添加DaEnterpriseLitigationInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseLitigationInfo> entityList = new ArrayList<DaEnterpriseLitigationInfo>();

		public ArrayList<DaEnterpriseLitigationInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseLitigationInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseLitigationInfoDeleteByIdList", description = "批量删除DaEnterpriseLitigationInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseLitigationInfoUpdateList", description = "批量修改DaEnterpriseLitigationInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseLitigationInfo entity = new DaEnterpriseLitigationInfo();

		public DaEnterpriseLitigationInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseLitigationInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseLitigationInfoGetList", description = "模糊查询DaEnterpriseLitigationInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseLitigationInfoGetListEntityRelated", description = "模糊查询DaEnterpriseLitigationInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseLitigationInfoGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreatedTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreatedTime;

        @ApiModelProperty(value="立案时间查询起止时间。",required=false)
		private Date beginFilingTime;

        @ApiModelProperty(value="立案时间查询结束时间。",required=false)
		private Date endFilingTime;

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
        
		public Date getBeginFilingTime() {
			return this.beginFilingTime;
		}
        
		public void setBeginFilingTime(Date beginFilingTime) {
			this.beginFilingTime = beginFilingTime;
		}
        
        public Date getEndFilingTime() {
			return this.endFilingTime;
		}
        
		public void setEndFilingTime(Date endFilingTime) {
			this.endFilingTime = endFilingTime;
		}
        
		}
	}

}







