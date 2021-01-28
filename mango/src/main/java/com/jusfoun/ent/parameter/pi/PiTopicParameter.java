package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiTopic;
import com.zee.ent.generate.pi.PiTopicGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiTopicParameter，方法参数，自动生成。CMS专题表
 */

public class PiTopicParameter extends BaseParameter {

	@ApiModel(value = "PiTopicAddList", description = "批量添加PiTopic所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiTopic> entityList = new ArrayList<PiTopic>();

		public ArrayList<PiTopic> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiTopic> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiTopicDeleteByIdList", description = "批量删除PiTopic所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiTopicUpdateList", description = "批量修改PiTopic所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiTopic entity = new PiTopic();

		public PiTopic getEntity() {
			return entity;
		}

		public void setEntity(PiTopic entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiTopicGetList", description = "模糊查询PiTopic所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiTopicGetListEntityRelated", description = "模糊查询PiTopic所需的参数，实体类相关。")
		public static class EntityRelated extends PiTopicGenEnt{
        
		}
	}

}







