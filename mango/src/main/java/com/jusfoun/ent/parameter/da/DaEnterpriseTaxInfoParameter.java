package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEnterpriseTaxInfo;
import com.jusfoun.ent.generate.da.DaEnterpriseTaxInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaEnterpriseTaxInfoParameter，方法参数，自动生成。企业税务信息表
 */

public class DaEnterpriseTaxInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseTaxInfoAddList", description = "批量添加DaEnterpriseTaxInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseTaxInfo> entityList = new ArrayList<DaEnterpriseTaxInfo>();

		public ArrayList<DaEnterpriseTaxInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseTaxInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseTaxInfoDeleteByIdList", description = "批量删除DaEnterpriseTaxInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseTaxInfoUpdateList", description = "批量修改DaEnterpriseTaxInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseTaxInfo entity = new DaEnterpriseTaxInfo();

		public DaEnterpriseTaxInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseTaxInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseTaxInfoGetList", description = "模糊查询DaEnterpriseTaxInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseTaxInfoGetListEntityRelated", description = "模糊查询DaEnterpriseTaxInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseTaxInfoGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreatedTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreatedTime;

        @ApiModelProperty(value="纳税时间查询起止时间。",required=false)
		private Date beginTaxTime;

        @ApiModelProperty(value="纳税时间查询结束时间。",required=false)
		private Date endTaxTime;

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
        
		public Date getBeginTaxTime() {
			return this.beginTaxTime;
		}
        
		public void setBeginTaxTime(Date beginTaxTime) {
			this.beginTaxTime = beginTaxTime;
		}
        
        public Date getEndTaxTime() {
			return this.endTaxTime;
		}
        
		public void setEndTaxTime(Date endTaxTime) {
			this.endTaxTime = endTaxTime;
		}
        
		}
	}

}







