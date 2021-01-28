package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaEnterpriseUpdownstreamInfo;
import com.zee.ent.generate.da.DaEnterpriseUpdownstreamInfoGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaEnterpriseUpdownstreamInfoParameter，方法参数，自动生成。企业关联企业表，企业上下游关系记录。
 */

public class DaEnterpriseUpdownstreamInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseUpdownstreamInfoAddList", description = "批量添加DaEnterpriseUpdownstreamInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseUpdownstreamInfo> entityList = new ArrayList<DaEnterpriseUpdownstreamInfo>();

		public ArrayList<DaEnterpriseUpdownstreamInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseUpdownstreamInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseUpdownstreamInfoDeleteByIdList", description = "批量删除DaEnterpriseUpdownstreamInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseUpdownstreamInfoUpdateList", description = "批量修改DaEnterpriseUpdownstreamInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseUpdownstreamInfo entity = new DaEnterpriseUpdownstreamInfo();

		public DaEnterpriseUpdownstreamInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseUpdownstreamInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseUpdownstreamInfoGetList", description = "模糊查询DaEnterpriseUpdownstreamInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseUpdownstreamInfoGetListEntityRelated", description = "模糊查询DaEnterpriseUpdownstreamInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseUpdownstreamInfoGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreatedTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreatedTime;

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
        
		}
	}

}







