package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEnterprisePropertyInfo;
import com.jusfoun.ent.generate.da.DaEnterprisePropertyInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaEnterprisePropertyInfoParameter，方法参数，自动生成。企业财产信息表
 */

public class DaEnterprisePropertyInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterprisePropertyInfoAddList", description = "批量添加DaEnterprisePropertyInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterprisePropertyInfo> entityList = new ArrayList<DaEnterprisePropertyInfo>();

		public ArrayList<DaEnterprisePropertyInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterprisePropertyInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterprisePropertyInfoDeleteByIdList", description = "批量删除DaEnterprisePropertyInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterprisePropertyInfoUpdateList", description = "批量修改DaEnterprisePropertyInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterprisePropertyInfo entity = new DaEnterprisePropertyInfo();

		public DaEnterprisePropertyInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterprisePropertyInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterprisePropertyInfoGetList", description = "模糊查询DaEnterprisePropertyInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterprisePropertyInfoGetListEntityRelated", description = "模糊查询DaEnterprisePropertyInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterprisePropertyInfoGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreatedTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreatedTime;

        @ApiModelProperty(value="源创建时间查询起止时间。",required=false)
		private Date beginSourceCreateTime;

        @ApiModelProperty(value="源创建时间查询结束时间。",required=false)
		private Date endSourceCreateTime;

        @ApiModelProperty(value="更新时间查询起止时间。",required=false)
		private Date beginUpdateTime;

        @ApiModelProperty(value="更新时间查询结束时间。",required=false)
		private Date endUpdateTime;

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
        
		public Date getBeginSourceCreateTime() {
			return this.beginSourceCreateTime;
		}
        
		public void setBeginSourceCreateTime(Date beginSourceCreateTime) {
			this.beginSourceCreateTime = beginSourceCreateTime;
		}
        
        public Date getEndSourceCreateTime() {
			return this.endSourceCreateTime;
		}
        
		public void setEndSourceCreateTime(Date endSourceCreateTime) {
			this.endSourceCreateTime = endSourceCreateTime;
		}
        
		public Date getBeginUpdateTime() {
			return this.beginUpdateTime;
		}
        
		public void setBeginUpdateTime(Date beginUpdateTime) {
			this.beginUpdateTime = beginUpdateTime;
		}
        
        public Date getEndUpdateTime() {
			return this.endUpdateTime;
		}
        
		public void setEndUpdateTime(Date endUpdateTime) {
			this.endUpdateTime = endUpdateTime;
		}
        
		}
	}

}







