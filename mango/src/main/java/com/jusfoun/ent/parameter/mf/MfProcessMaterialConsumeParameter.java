package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfProcessMaterialConsume;
import com.zee.ent.generate.mf.MfProcessMaterialConsumeGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfProcessMaterialConsumeParameter，方法参数，自动生成。加工品原料消耗情况预测表
 */

public class MfProcessMaterialConsumeParameter extends BaseParameter {

	@ApiModel(value = "MfProcessMaterialConsumeAddList", description = "批量添加MfProcessMaterialConsume所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfProcessMaterialConsume> entityList = new ArrayList<MfProcessMaterialConsume>();

		public ArrayList<MfProcessMaterialConsume> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfProcessMaterialConsume> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfProcessMaterialConsumeDeleteByIdList", description = "批量删除MfProcessMaterialConsume所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfProcessMaterialConsumeUpdateList", description = "批量修改MfProcessMaterialConsume所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfProcessMaterialConsume entity = new MfProcessMaterialConsume();

		public MfProcessMaterialConsume getEntity() {
			return entity;
		}

		public void setEntity(MfProcessMaterialConsume entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfProcessMaterialConsumeGetList", description = "模糊查询MfProcessMaterialConsume所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfProcessMaterialConsumeGetListEntityRelated", description = "模糊查询MfProcessMaterialConsume所需的参数，实体类相关。")
		public static class EntityRelated extends MfProcessMaterialConsumeGenEnt{
        
		}
	}

}







