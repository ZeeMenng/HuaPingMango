package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfInputType;
import com.jusfoun.ent.generate.mf.MfInputTypeGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfInputTypeParameter，方法参数，自动生成。投入品种类监管
 */

public class MfInputTypeParameter extends BaseParameter {

	@ApiModel(value = "MfInputTypeAddList", description = "批量添加MfInputType所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfInputType> entityList = new ArrayList<MfInputType>();

		public ArrayList<MfInputType> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfInputType> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfInputTypeDeleteByIdList", description = "批量删除MfInputType所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfInputTypeUpdateList", description = "批量修改MfInputType所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfInputType entity = new MfInputType();

		public MfInputType getEntity() {
			return entity;
		}

		public void setEntity(MfInputType entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfInputTypeGetList", description = "模糊查询MfInputType所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfInputTypeGetListEntityRelated", description = "模糊查询MfInputType所需的参数，实体类相关。")
		public static class EntityRelated extends MfInputTypeGenEnt{
        
		}
	}

}







