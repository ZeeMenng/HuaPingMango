package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaGrowYield;
import com.jusfoun.ent.generate.da.DaGrowYieldGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-26 18:49:08
 * @description 实体类DaGrowYieldParameter，方法参数，自动生成。种植数据表
 */

public class DaGrowYieldParameter extends BaseParameter {

	@ApiModel(value = "DaGrowYieldAddList", description = "批量添加DaGrowYield所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaGrowYield> entityList = new ArrayList<DaGrowYield>();

		public ArrayList<DaGrowYield> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaGrowYield> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaGrowYieldDeleteByIdList", description = "批量删除DaGrowYield所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaGrowYieldUpdateList", description = "批量修改DaGrowYield所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaGrowYield entity = new DaGrowYield();

		public DaGrowYield getEntity() {
			return entity;
		}

		public void setEntity(DaGrowYield entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaGrowYieldGetList", description = "模糊查询DaGrowYield所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaGrowYieldGetListEntityRelated", description = "模糊查询DaGrowYield所需的参数，实体类相关。")
		public static class EntityRelated extends DaGrowYieldGenEnt{
        
		}
	}

}







