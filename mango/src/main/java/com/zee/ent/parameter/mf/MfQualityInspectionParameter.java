package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfQualityInspection;
import com.zee.ent.generate.mf.MfQualityInspectionGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfQualityInspectionParameter，方法参数，自动生成。质量抽检表
 */

public class MfQualityInspectionParameter extends BaseParameter {

	@ApiModel(value = "MfQualityInspectionAddList", description = "批量添加MfQualityInspection所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfQualityInspection> entityList = new ArrayList<MfQualityInspection>();

		public ArrayList<MfQualityInspection> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfQualityInspection> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfQualityInspectionDeleteByIdList", description = "批量删除MfQualityInspection所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfQualityInspectionUpdateList", description = "批量修改MfQualityInspection所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfQualityInspection entity = new MfQualityInspection();

		public MfQualityInspection getEntity() {
			return entity;
		}

		public void setEntity(MfQualityInspection entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfQualityInspectionGetList", description = "模糊查询MfQualityInspection所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfQualityInspectionGetListEntityRelated", description = "模糊查询MfQualityInspection所需的参数，实体类相关。")
		public static class EntityRelated extends MfQualityInspectionGenEnt{
        
		}
	}

}







