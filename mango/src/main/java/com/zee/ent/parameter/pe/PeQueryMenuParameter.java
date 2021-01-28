package com.zee.ent.parameter.pe;

import java.util.*;

import com.zee.ent.extend.pe.PeQueryMenu;
import com.zee.ent.generate.pe.PeQueryMenuGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-2 16:58:02
 * @description 实体类PeQueryMenuParameter，方法参数，自动生成。数据版门户-数据资源-查询菜单
 */

public class PeQueryMenuParameter extends BaseParameter {

	@ApiModel(value = "PeQueryMenuAddList", description = "批量添加PeQueryMenu所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PeQueryMenu> entityList = new ArrayList<PeQueryMenu>();

		public ArrayList<PeQueryMenu> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PeQueryMenu> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PeQueryMenuDeleteByIdList", description = "批量删除PeQueryMenu所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PeQueryMenuUpdateList", description = "批量修改PeQueryMenu所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PeQueryMenu entity = new PeQueryMenu();

		public PeQueryMenu getEntity() {
			return entity;
		}

		public void setEntity(PeQueryMenu entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PeQueryMenuGetList", description = "模糊查询PeQueryMenu所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PeQueryMenuGetListEntityRelated", description = "模糊查询PeQueryMenu所需的参数，实体类相关。")
		public static class EntityRelated extends PeQueryMenuGenEnt{
        
		}
	}

}







