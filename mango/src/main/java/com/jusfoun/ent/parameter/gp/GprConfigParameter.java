package com.jusfoun.ent.parameter.gp;

import java.util.*;

import com.jusfoun.ent.extend.gp.GprConfig;
import com.jusfoun.ent.generate.gp.GprConfigGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/18 19:49:24
 * @description 实体类GprConfigParameter，方法参数，自动生成。应用领域配置信息。
 */

public class GprConfigParameter extends BaseParameter {

	@ApiModel(value = "GprConfigAddList", description = "批量添加GprConfig所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprConfig> entityList = new ArrayList<GprConfig>();

		public ArrayList<GprConfig> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprConfig> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprConfigDeleteByIdList", description = "批量删除GprConfig所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprConfigUpdateList", description = "批量修改GprConfig所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprConfig entity = new GprConfig();

		public GprConfig getEntity() {
			return entity;
		}

		public void setEntity(GprConfig entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprConfigGetList", description = "模糊查询GprConfig所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprConfigGetListEntityRelated", description = "模糊查询GprConfig所需的参数，实体类相关。")
		public static class EntityRelated extends GprConfigGenEnt{
        
		}
	}

}







