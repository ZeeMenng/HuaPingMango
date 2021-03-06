package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirContentTopic;
import com.zee.ent.generate.pi.PirContentTopicGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PirContentTopicParameter，方法参数，自动生成。CMS专题内容关联表
 */

public class PirContentTopicParameter extends BaseParameter {

	@ApiModel(value = "PirContentTopicAddList", description = "批量添加PirContentTopic所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirContentTopic> entityList = new ArrayList<PirContentTopic>();

		public ArrayList<PirContentTopic> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirContentTopic> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirContentTopicDeleteByIdList", description = "批量删除PirContentTopic所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirContentTopicUpdateList", description = "批量修改PirContentTopic所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirContentTopic entity = new PirContentTopic();

		public PirContentTopic getEntity() {
			return entity;
		}

		public void setEntity(PirContentTopic entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirContentTopicGetList", description = "模糊查询PirContentTopic所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirContentTopicGetListEntityRelated", description = "模糊查询PirContentTopic所需的参数，实体类相关。")
		public static class EntityRelated extends PirContentTopicGenEnt{
        
		}
	}

}







