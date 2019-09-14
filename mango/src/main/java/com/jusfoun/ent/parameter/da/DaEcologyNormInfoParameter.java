package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEcologyNormInfo;
import com.jusfoun.ent.generate.da.DaEcologyNormInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaEcologyNormInfoParameter，方法参数，自动生成。种植生态指标表
 */

public class DaEcologyNormInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEcologyNormInfoAddList", description = "批量添加DaEcologyNormInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEcologyNormInfo> entityList = new ArrayList<DaEcologyNormInfo>();

		public ArrayList<DaEcologyNormInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEcologyNormInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEcologyNormInfoDeleteByIdList", description = "批量删除DaEcologyNormInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEcologyNormInfoUpdateList", description = "批量修改DaEcologyNormInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEcologyNormInfo entity = new DaEcologyNormInfo();

		public DaEcologyNormInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEcologyNormInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEcologyNormInfoGetList", description = "模糊查询DaEcologyNormInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEcologyNormInfoGetListEntityRelated", description = "模糊查询DaEcologyNormInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEcologyNormInfoGenEnt{
        
		}
	}

}







