package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaTarget;
import com.zee.ent.generate.da.DaTargetGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaTargetParameter，方法参数，自动生成。指标数据
 */

public class DaTargetParameter extends BaseParameter {

	@ApiModel(value = "DaTargetAddList", description = "批量添加DaTarget所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaTarget> entityList = new ArrayList<DaTarget>();

		public ArrayList<DaTarget> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaTarget> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaTargetDeleteByIdList", description = "批量删除DaTarget所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaTargetUpdateList", description = "批量修改DaTarget所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaTarget entity = new DaTarget();

		public DaTarget getEntity() {
			return entity;
		}

		public void setEntity(DaTarget entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaTargetGetList", description = "模糊查询DaTarget所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaTargetGetListEntityRelated", description = "模糊查询DaTarget所需的参数，实体类相关。")
		public static class EntityRelated extends DaTargetGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endAddTime;

		public Date getBeginAddTime() {
			return this.beginAddTime;
		}
        
		public void setBeginAddTime(Date beginAddTime) {
			this.beginAddTime = beginAddTime;
		}
        
        public Date getEndAddTime() {
			return this.endAddTime;
		}
        
		public void setEndAddTime(Date endAddTime) {
			this.endAddTime = endAddTime;
		}
        
		}
	}

}







