package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiContentCount;
import com.zee.ent.generate.pi.PiContentCountGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentCountParameter，方法参数，自动生成。CMS内容计数表
 */

public class PiContentCountParameter extends BaseParameter {

	@ApiModel(value = "PiContentCountAddList", description = "批量添加PiContentCount所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentCount> entityList = new ArrayList<PiContentCount>();

		public ArrayList<PiContentCount> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentCount> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentCountDeleteByIdList", description = "批量删除PiContentCount所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentCountUpdateList", description = "批量修改PiContentCount所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentCount entity = new PiContentCount();

		public PiContentCount getEntity() {
			return entity;
		}

		public void setEntity(PiContentCount entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentCountGetList", description = "模糊查询PiContentCount所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentCountGetListEntityRelated", description = "模糊查询PiContentCount所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentCountGenEnt{
        
		}
	}

}







