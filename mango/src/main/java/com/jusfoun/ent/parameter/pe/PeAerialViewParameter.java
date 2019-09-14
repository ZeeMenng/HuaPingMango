package com.jusfoun.ent.parameter.pe;

import java.util.*;

import com.jusfoun.ent.extend.pe.PeAerialView;
import com.jusfoun.ent.generate.pe.PeAerialViewGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-26 15:20:30
 * @description 实体类PeAerialViewParameter，方法参数，自动生成。鸟瞰图
 */

public class PeAerialViewParameter extends BaseParameter {

	@ApiModel(value = "PeAerialViewAddList", description = "批量添加PeAerialView所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PeAerialView> entityList = new ArrayList<PeAerialView>();

		public ArrayList<PeAerialView> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PeAerialView> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PeAerialViewDeleteByIdList", description = "批量删除PeAerialView所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PeAerialViewUpdateList", description = "批量修改PeAerialView所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PeAerialView entity = new PeAerialView();

		public PeAerialView getEntity() {
			return entity;
		}

		public void setEntity(PeAerialView entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PeAerialViewGetList", description = "模糊查询PeAerialView所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PeAerialViewGetListEntityRelated", description = "模糊查询PeAerialView所需的参数，实体类相关。")
		public static class EntityRelated extends PeAerialViewGenEnt{
        
		}
	}

}







