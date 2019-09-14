package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfQuality;
import com.jusfoun.ent.generate.mf.MfQualityGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfQualityParameter，方法参数，自动生成。质量安全综合指数表,建模
 */

public class MfQualityParameter extends BaseParameter {

	@ApiModel(value = "MfQualityAddList", description = "批量添加MfQuality所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfQuality> entityList = new ArrayList<MfQuality>();

		public ArrayList<MfQuality> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfQuality> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfQualityDeleteByIdList", description = "批量删除MfQuality所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfQualityUpdateList", description = "批量修改MfQuality所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfQuality entity = new MfQuality();

		public MfQuality getEntity() {
			return entity;
		}

		public void setEntity(MfQuality entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfQualityGetList", description = "模糊查询MfQuality所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfQualityGetListEntityRelated", description = "模糊查询MfQuality所需的参数，实体类相关。")
		public static class EntityRelated extends MfQualityGenEnt{
        
		}
	}

}







