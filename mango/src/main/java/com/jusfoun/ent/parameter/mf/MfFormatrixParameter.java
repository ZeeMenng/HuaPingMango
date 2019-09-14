package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfFormatrix;
import com.jusfoun.ent.generate.mf.MfFormatrixGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfFormatrixParameter，方法参数，自动生成。波士顿矩阵
 */

public class MfFormatrixParameter extends BaseParameter {

	@ApiModel(value = "MfFormatrixAddList", description = "批量添加MfFormatrix所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfFormatrix> entityList = new ArrayList<MfFormatrix>();

		public ArrayList<MfFormatrix> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfFormatrix> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfFormatrixDeleteByIdList", description = "批量删除MfFormatrix所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfFormatrixUpdateList", description = "批量修改MfFormatrix所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfFormatrix entity = new MfFormatrix();

		public MfFormatrix getEntity() {
			return entity;
		}

		public void setEntity(MfFormatrix entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfFormatrixGetList", description = "模糊查询MfFormatrix所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfFormatrixGetListEntityRelated", description = "模糊查询MfFormatrix所需的参数，实体类相关。")
		public static class EntityRelated extends MfFormatrixGenEnt{
        
		}
	}

}







