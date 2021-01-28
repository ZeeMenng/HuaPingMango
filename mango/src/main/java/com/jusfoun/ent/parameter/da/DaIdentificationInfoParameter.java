package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaIdentificationInfo;
import com.zee.ent.generate.da.DaIdentificationInfoGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIdentificationInfoParameter，方法参数，自动生成。三品一标认证表
 */

public class DaIdentificationInfoParameter extends BaseParameter {

	@ApiModel(value = "DaIdentificationInfoAddList", description = "批量添加DaIdentificationInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIdentificationInfo> entityList = new ArrayList<DaIdentificationInfo>();

		public ArrayList<DaIdentificationInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIdentificationInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIdentificationInfoDeleteByIdList", description = "批量删除DaIdentificationInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIdentificationInfoUpdateList", description = "批量修改DaIdentificationInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIdentificationInfo entity = new DaIdentificationInfo();

		public DaIdentificationInfo getEntity() {
			return entity;
		}

		public void setEntity(DaIdentificationInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIdentificationInfoGetList", description = "模糊查询DaIdentificationInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIdentificationInfoGetListEntityRelated", description = "模糊查询DaIdentificationInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaIdentificationInfoGenEnt{
        
        @ApiModelProperty(value="加工时间查询起止时间。",required=false)
		private Date beginProcessTime;

        @ApiModelProperty(value="加工时间查询结束时间。",required=false)
		private Date endProcessTime;

		public Date getBeginProcessTime() {
			return this.beginProcessTime;
		}
        
		public void setBeginProcessTime(Date beginProcessTime) {
			this.beginProcessTime = beginProcessTime;
		}
        
        public Date getEndProcessTime() {
			return this.endProcessTime;
		}
        
		public void setEndProcessTime(Date endProcessTime) {
			this.endProcessTime = endProcessTime;
		}
        
		}
	}

}







