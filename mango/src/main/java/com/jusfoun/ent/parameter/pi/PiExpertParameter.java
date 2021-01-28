package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiExpert;
import com.zee.ent.generate.pi.PiExpertGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/11 17:38:34
 * @description 实体类PiExpertParameter，方法参数，自动生成。专家表
 */

public class PiExpertParameter extends BaseParameter {

	@ApiModel(value = "PiExpertAddList", description = "批量添加PiExpert所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiExpert> entityList = new ArrayList<PiExpert>();

		public ArrayList<PiExpert> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiExpert> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiExpertDeleteByIdList", description = "批量删除PiExpert所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiExpertUpdateList", description = "批量修改PiExpert所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiExpert entity = new PiExpert();

		public PiExpert getEntity() {
			return entity;
		}

		public void setEntity(PiExpert entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiExpertGetList", description = "模糊查询PiExpert所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiExpertGetListEntityRelated", description = "模糊查询PiExpert所需的参数，实体类相关。")
		public static class EntityRelated extends PiExpertGenEnt{
        
		}
	}

}







