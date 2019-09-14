package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DarTemplateTarget;
import com.jusfoun.ent.generate.da.DarTemplateTargetGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DarTemplateTargetParameter，方法参数，自动生成。模板指标中间表
 */

public class DarTemplateTargetParameter extends BaseParameter {

	@ApiModel(value = "DarTemplateTargetAddList", description = "批量添加DarTemplateTarget所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DarTemplateTarget> entityList = new ArrayList<DarTemplateTarget>();

		public ArrayList<DarTemplateTarget> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DarTemplateTarget> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DarTemplateTargetDeleteByIdList", description = "批量删除DarTemplateTarget所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DarTemplateTargetUpdateList", description = "批量修改DarTemplateTarget所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DarTemplateTarget entity = new DarTemplateTarget();

		public DarTemplateTarget getEntity() {
			return entity;
		}

		public void setEntity(DarTemplateTarget entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DarTemplateTargetGetList", description = "模糊查询DarTemplateTarget所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DarTemplateTargetGetListEntityRelated", description = "模糊查询DarTemplateTarget所需的参数，实体类相关。")
		public static class EntityRelated extends DarTemplateTargetGenEnt{
        
		}
	}

}







