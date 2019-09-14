package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaInput;
import com.jusfoun.ent.generate.da.DaInputGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaInputParameter，方法参数，自动生成。投入品信息表
 */

public class DaInputParameter extends BaseParameter {

	@ApiModel(value = "DaInputAddList", description = "批量添加DaInput所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaInput> entityList = new ArrayList<DaInput>();

		public ArrayList<DaInput> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaInput> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaInputDeleteByIdList", description = "批量删除DaInput所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaInputUpdateList", description = "批量修改DaInput所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaInput entity = new DaInput();

		public DaInput getEntity() {
			return entity;
		}

		public void setEntity(DaInput entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaInputGetList", description = "模糊查询DaInput所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaInputGetListEntityRelated", description = "模糊查询DaInput所需的参数，实体类相关。")
		public static class EntityRelated extends DaInputGenEnt{
        
		}
	}

}







