package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEnterpriseShareholderInfo;
import com.jusfoun.ent.generate.da.DaEnterpriseShareholderInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaEnterpriseShareholderInfoParameter，方法参数，自动生成。企业股东信息表
 */

public class DaEnterpriseShareholderInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseShareholderInfoAddList", description = "批量添加DaEnterpriseShareholderInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseShareholderInfo> entityList = new ArrayList<DaEnterpriseShareholderInfo>();

		public ArrayList<DaEnterpriseShareholderInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseShareholderInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseShareholderInfoDeleteByIdList", description = "批量删除DaEnterpriseShareholderInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseShareholderInfoUpdateList", description = "批量修改DaEnterpriseShareholderInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseShareholderInfo entity = new DaEnterpriseShareholderInfo();

		public DaEnterpriseShareholderInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseShareholderInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseShareholderInfoGetList", description = "模糊查询DaEnterpriseShareholderInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseShareholderInfoGetListEntityRelated", description = "模糊查询DaEnterpriseShareholderInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseShareholderInfoGenEnt{
        
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







