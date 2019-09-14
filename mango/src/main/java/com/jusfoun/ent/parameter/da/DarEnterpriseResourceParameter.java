package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DarEnterpriseResource;
import com.jusfoun.ent.generate.da.DarEnterpriseResourceGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/11 17:38:33
 * @description 实体类DarEnterpriseResourceParameter，方法参数，自动生成。企业和资源中间表
 */

public class DarEnterpriseResourceParameter extends BaseParameter {

	@ApiModel(value = "DarEnterpriseResourceAddList", description = "批量添加DarEnterpriseResource所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DarEnterpriseResource> entityList = new ArrayList<DarEnterpriseResource>();

		public ArrayList<DarEnterpriseResource> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DarEnterpriseResource> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DarEnterpriseResourceDeleteByIdList", description = "批量删除DarEnterpriseResource所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DarEnterpriseResourceUpdateList", description = "批量修改DarEnterpriseResource所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DarEnterpriseResource entity = new DarEnterpriseResource();

		public DarEnterpriseResource getEntity() {
			return entity;
		}

		public void setEntity(DarEnterpriseResource entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DarEnterpriseResourceGetList", description = "模糊查询DarEnterpriseResource所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DarEnterpriseResourceGetListEntityRelated", description = "模糊查询DarEnterpriseResource所需的参数，实体类相关。")
		public static class EntityRelated extends DarEnterpriseResourceGenEnt{
        
		}
	}

}







