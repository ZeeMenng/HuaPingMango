package com.jusfoun.ent.parameter.gp;

import java.util.*;

import com.jusfoun.ent.extend.gp.GprConfigUser;
import com.jusfoun.ent.generate.gp.GprConfigUserGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2021/1/18 19:49:24
 * @description 实体类GprConfigUserParameter，方法参数，自动生成。用户配置信息。
 */

public class GprConfigUserParameter extends BaseParameter {

	@ApiModel(value = "GprConfigUserAddList", description = "批量添加GprConfigUser所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprConfigUser> entityList = new ArrayList<GprConfigUser>();

		public ArrayList<GprConfigUser> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprConfigUser> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprConfigUserDeleteByIdList", description = "批量删除GprConfigUser所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprConfigUserUpdateList", description = "批量修改GprConfigUser所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprConfigUser entity = new GprConfigUser();

		public GprConfigUser getEntity() {
			return entity;
		}

		public void setEntity(GprConfigUser entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprConfigUserGetList", description = "模糊查询GprConfigUser所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprConfigUserGetListEntityRelated", description = "模糊查询GprConfigUser所需的参数，实体类相关。")
		public static class EntityRelated extends GprConfigUserGenEnt{
        
		}
	}

}







