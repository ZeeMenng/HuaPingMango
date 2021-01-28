package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirProductResource;
import com.zee.ent.generate.pi.PirProductResourceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/11 17:38:33
 * @description 实体类PirProductResourceParameter，方法参数，自动生成。产品和资源中间表
 */

public class PirProductResourceParameter extends BaseParameter {

	@ApiModel(value = "PirProductResourceAddList", description = "批量添加PirProductResource所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirProductResource> entityList = new ArrayList<PirProductResource>();

		public ArrayList<PirProductResource> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirProductResource> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirProductResourceDeleteByIdList", description = "批量删除PirProductResource所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirProductResourceUpdateList", description = "批量修改PirProductResource所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirProductResource entity = new PirProductResource();

		public PirProductResource getEntity() {
			return entity;
		}

		public void setEntity(PirProductResource entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirProductResourceGetList", description = "模糊查询PirProductResource所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirProductResourceGetListEntityRelated", description = "模糊查询PirProductResource所需的参数，实体类相关。")
		public static class EntityRelated extends PirProductResourceGenEnt{
        
		}
	}

}







