package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfProcessYield;
import com.zee.ent.generate.mf.MfProcessYieldGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfProcessYieldParameter，方法参数，自动生成。加工品产量建模表
 */

public class MfProcessYieldParameter extends BaseParameter {

	@ApiModel(value = "MfProcessYieldAddList", description = "批量添加MfProcessYield所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfProcessYield> entityList = new ArrayList<MfProcessYield>();

		public ArrayList<MfProcessYield> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfProcessYield> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfProcessYieldDeleteByIdList", description = "批量删除MfProcessYield所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfProcessYieldUpdateList", description = "批量修改MfProcessYield所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfProcessYield entity = new MfProcessYield();

		public MfProcessYield getEntity() {
			return entity;
		}

		public void setEntity(MfProcessYield entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfProcessYieldGetList", description = "模糊查询MfProcessYield所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfProcessYieldGetListEntityRelated", description = "模糊查询MfProcessYield所需的参数，实体类相关。")
		public static class EntityRelated extends MfProcessYieldGenEnt{
        
		}
	}

}







