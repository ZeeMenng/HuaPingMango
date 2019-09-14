package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaTemplate;
import com.jusfoun.ent.generate.da.DaTemplateGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaTemplateParameter，方法参数，自动生成。指标模板表
 */

public class DaTemplateParameter extends BaseParameter {

	@ApiModel(value = "DaTemplateAddList", description = "批量添加DaTemplate所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaTemplate> entityList = new ArrayList<DaTemplate>();

		public ArrayList<DaTemplate> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaTemplate> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaTemplateDeleteByIdList", description = "批量删除DaTemplate所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaTemplateUpdateList", description = "批量修改DaTemplate所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaTemplate entity = new DaTemplate();

		public DaTemplate getEntity() {
			return entity;
		}

		public void setEntity(DaTemplate entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaTemplateGetList", description = "模糊查询DaTemplate所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaTemplateGetListEntityRelated", description = "模糊查询DaTemplate所需的参数，实体类相关。")
		public static class EntityRelated extends DaTemplateGenEnt{
        
		}
	}

}







