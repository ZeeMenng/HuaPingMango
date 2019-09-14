package com.jusfoun.ent.parameter.pe;

import java.util.*;

import com.jusfoun.ent.extend.pe.PeAerialBroadcast;
import com.jusfoun.ent.generate.pe.PeAerialBroadcastGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-27 10:25:42
 * @description 实体类PeAerialBroadcastParameter，方法参数，自动生成。鸟瞰华坪轮播图
 */

public class PeAerialBroadcastParameter extends BaseParameter {

	@ApiModel(value = "PeAerialBroadcastAddList", description = "批量添加PeAerialBroadcast所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PeAerialBroadcast> entityList = new ArrayList<PeAerialBroadcast>();

		public ArrayList<PeAerialBroadcast> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PeAerialBroadcast> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PeAerialBroadcastDeleteByIdList", description = "批量删除PeAerialBroadcast所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PeAerialBroadcastUpdateList", description = "批量修改PeAerialBroadcast所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PeAerialBroadcast entity = new PeAerialBroadcast();

		public PeAerialBroadcast getEntity() {
			return entity;
		}

		public void setEntity(PeAerialBroadcast entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PeAerialBroadcastGetList", description = "模糊查询PeAerialBroadcast所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PeAerialBroadcastGetListEntityRelated", description = "模糊查询PeAerialBroadcast所需的参数，实体类相关。")
		public static class EntityRelated extends PeAerialBroadcastGenEnt{
        
		}
	}

}







