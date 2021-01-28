package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiChannelCount;
import com.zee.ent.generate.pi.PiChannelCountGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiChannelCountParameter，方法参数，自动生成。CMS栏目访问量计数表
 */

public class PiChannelCountParameter extends BaseParameter {

	@ApiModel(value = "PiChannelCountAddList", description = "批量添加PiChannelCount所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiChannelCount> entityList = new ArrayList<PiChannelCount>();

		public ArrayList<PiChannelCount> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiChannelCount> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiChannelCountDeleteByIdList", description = "批量删除PiChannelCount所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiChannelCountUpdateList", description = "批量修改PiChannelCount所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiChannelCount entity = new PiChannelCount();

		public PiChannelCount getEntity() {
			return entity;
		}

		public void setEntity(PiChannelCount entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiChannelCountGetList", description = "模糊查询PiChannelCount所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiChannelCountGetListEntityRelated", description = "模糊查询PiChannelCount所需的参数，实体类相关。")
		public static class EntityRelated extends PiChannelCountGenEnt{
        
		}
	}

}







