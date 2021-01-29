package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaMainProductionArea;
import com.zee.ent.generate.da.DaMainProductionAreaGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-25 10:47:17
 * @description 实体类DaMainProductionAreaParameter，方法参数，自动生成。芒果主产区
 */

public class DaMainProductionAreaParameter extends BaseParameter {

	@ApiModel(value = "DaMainProductionAreaAddList", description = "批量添加DaMainProductionArea所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaMainProductionArea> entityList = new ArrayList<DaMainProductionArea>();

		public ArrayList<DaMainProductionArea> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaMainProductionArea> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaMainProductionAreaDeleteByIdList", description = "批量删除DaMainProductionArea所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaMainProductionAreaUpdateList", description = "批量修改DaMainProductionArea所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaMainProductionArea entity = new DaMainProductionArea();

		public DaMainProductionArea getEntity() {
			return entity;
		}

		public void setEntity(DaMainProductionArea entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaMainProductionAreaGetList", description = "模糊查询DaMainProductionArea所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaMainProductionAreaGetListEntityRelated", description = "模糊查询DaMainProductionArea所需的参数，实体类相关。")
		public static class EntityRelated extends DaMainProductionAreaGenEnt{
        
		}
	}

}






