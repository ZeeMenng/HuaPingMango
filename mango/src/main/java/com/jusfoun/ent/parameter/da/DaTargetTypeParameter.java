package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaTargetType;
import com.jusfoun.ent.generate.da.DaTargetTypeGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaTargetTypeParameter，方法参数，自动生成。指标类型
 */

public class DaTargetTypeParameter extends BaseParameter {

	@ApiModel(value = "DaTargetTypeAddList", description = "批量添加DaTargetType所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaTargetType> entityList = new ArrayList<DaTargetType>();

		public ArrayList<DaTargetType> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaTargetType> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaTargetTypeDeleteByIdList", description = "批量删除DaTargetType所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaTargetTypeUpdateList", description = "批量修改DaTargetType所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaTargetType entity = new DaTargetType();

		public DaTargetType getEntity() {
			return entity;
		}

		public void setEntity(DaTargetType entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaTargetTypeGetList", description = "模糊查询DaTargetType所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaTargetTypeGetListEntityRelated", description = "模糊查询DaTargetType所需的参数，实体类相关。")
		public static class EntityRelated extends DaTargetTypeGenEnt{
        
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







