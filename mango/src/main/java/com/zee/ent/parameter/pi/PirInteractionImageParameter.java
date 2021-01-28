package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirInteractionImage;
import com.zee.ent.generate.pi.PirInteractionImageGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-5 11:35:44
 * @description 实体类PirInteractionImageParameter，方法参数，自动生成。pir_interaction_image
 */

public class PirInteractionImageParameter extends BaseParameter {

	@ApiModel(value = "PirInteractionImageAddList", description = "批量添加PirInteractionImage所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirInteractionImage> entityList = new ArrayList<PirInteractionImage>();

		public ArrayList<PirInteractionImage> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirInteractionImage> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirInteractionImageDeleteByIdList", description = "批量删除PirInteractionImage所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirInteractionImageUpdateList", description = "批量修改PirInteractionImage所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirInteractionImage entity = new PirInteractionImage();

		public PirInteractionImage getEntity() {
			return entity;
		}

		public void setEntity(PirInteractionImage entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirInteractionImageGetList", description = "模糊查询PirInteractionImage所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirInteractionImageGetListEntityRelated", description = "模糊查询PirInteractionImage所需的参数，实体类相关。")
		public static class EntityRelated extends PirInteractionImageGenEnt{
        
		}
	}

}







