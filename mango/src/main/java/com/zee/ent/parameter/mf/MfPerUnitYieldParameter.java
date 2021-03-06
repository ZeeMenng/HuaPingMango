package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfPerUnitYield;
import com.zee.ent.generate.mf.MfPerUnitYieldGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfPerUnitYieldParameter，方法参数，自动生成。单产预测建模表
 */

public class MfPerUnitYieldParameter extends BaseParameter {

	@ApiModel(value = "MfPerUnitYieldAddList", description = "批量添加MfPerUnitYield所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfPerUnitYield> entityList = new ArrayList<MfPerUnitYield>();

		public ArrayList<MfPerUnitYield> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfPerUnitYield> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfPerUnitYieldDeleteByIdList", description = "批量删除MfPerUnitYield所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfPerUnitYieldUpdateList", description = "批量修改MfPerUnitYield所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfPerUnitYield entity = new MfPerUnitYield();

		public MfPerUnitYield getEntity() {
			return entity;
		}

		public void setEntity(MfPerUnitYield entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfPerUnitYieldGetList", description = "模糊查询MfPerUnitYield所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfPerUnitYieldGetListEntityRelated", description = "模糊查询MfPerUnitYield所需的参数，实体类相关。")
		public static class EntityRelated extends MfPerUnitYieldGenEnt{
        
		}
	}

}







