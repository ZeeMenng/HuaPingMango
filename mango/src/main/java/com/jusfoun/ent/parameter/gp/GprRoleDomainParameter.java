package com.jusfoun.ent.parameter.gp;

import java.util.*;

import com.jusfoun.ent.extend.gp.GprRoleDomain;
import com.jusfoun.ent.generate.gp.GprRoleDomainGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2020/8/11 11:39:56
 * @description 实体类GprRoleDomainParameter，方法参数，自动生成。角色拥有的功能模块权限。
 */

public class GprRoleDomainParameter extends BaseParameter {

	@ApiModel(value = "GprRoleDomainAddList", description = "批量添加GprRoleDomain所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<GprRoleDomain> entityList = new ArrayList<GprRoleDomain>();

		public ArrayList<GprRoleDomain> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<GprRoleDomain> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "GprRoleDomainDeleteByIdList", description = "批量删除GprRoleDomain所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "GprRoleDomainUpdateList", description = "批量修改GprRoleDomain所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private GprRoleDomain entity = new GprRoleDomain();

		public GprRoleDomain getEntity() {
			return entity;
		}

		public void setEntity(GprRoleDomain entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "GprRoleDomainGetList", description = "模糊查询GprRoleDomain所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "GprRoleDomainGetListEntityRelated", description = "模糊查询GprRoleDomain所需的参数，实体类相关。")
		public static class EntityRelated extends GprRoleDomainGenEnt{
        
		}
	}

}







