package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaCheckCollection;
import com.jusfoun.ent.generate.da.DaCheckCollectionGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaCheckCollectionParameter，方法参数，自动生成。采样数据表
 */

public class DaCheckCollectionParameter extends BaseParameter {

	@ApiModel(value = "DaCheckCollectionAddList", description = "批量添加DaCheckCollection所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaCheckCollection> entityList = new ArrayList<DaCheckCollection>();

		public ArrayList<DaCheckCollection> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaCheckCollection> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaCheckCollectionDeleteByIdList", description = "批量删除DaCheckCollection所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaCheckCollectionUpdateList", description = "批量修改DaCheckCollection所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaCheckCollection entity = new DaCheckCollection();

		public DaCheckCollection getEntity() {
			return entity;
		}

		public void setEntity(DaCheckCollection entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaCheckCollectionGetList", description = "模糊查询DaCheckCollection所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaCheckCollectionGetListEntityRelated", description = "模糊查询DaCheckCollection所需的参数，实体类相关。")
		public static class EntityRelated extends DaCheckCollectionGenEnt{
        
        @ApiModelProperty(value="采样时间查询起止时间。",required=false)
		private Date beginSampleDate;

        @ApiModelProperty(value="采样时间查询结束时间。",required=false)
		private Date endSampleDate;

		public Date getBeginSampleDate() {
			return this.beginSampleDate;
		}
        
		public void setBeginSampleDate(Date beginSampleDate) {
			this.beginSampleDate = beginSampleDate;
		}
        
        public Date getEndSampleDate() {
			return this.endSampleDate;
		}
        
		public void setEndSampleDate(Date endSampleDate) {
			this.endSampleDate = endSampleDate;
		}
        
		}
	}

}







