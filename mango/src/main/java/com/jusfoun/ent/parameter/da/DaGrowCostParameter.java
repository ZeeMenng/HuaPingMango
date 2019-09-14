package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaGrowCost;
import com.jusfoun.ent.generate.da.DaGrowCostGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaGrowCostParameter，方法参数，自动生成。种植成本
 */

public class DaGrowCostParameter extends BaseParameter {

	@ApiModel(value = "DaGrowCostAddList", description = "批量添加DaGrowCost所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaGrowCost> entityList = new ArrayList<DaGrowCost>();

		public ArrayList<DaGrowCost> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaGrowCost> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaGrowCostDeleteByIdList", description = "批量删除DaGrowCost所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaGrowCostUpdateList", description = "批量修改DaGrowCost所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaGrowCost entity = new DaGrowCost();

		public DaGrowCost getEntity() {
			return entity;
		}

		public void setEntity(DaGrowCost entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaGrowCostGetList", description = "模糊查询DaGrowCost所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaGrowCostGetListEntityRelated", description = "模糊查询DaGrowCost所需的参数，实体类相关。")
		public static class EntityRelated extends DaGrowCostGenEnt{
        
		}
	}

}







