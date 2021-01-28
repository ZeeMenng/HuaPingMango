package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfForreturn;
import com.zee.ent.generate.mf.MfForreturnGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfForreturnParameter，方法参数，自动生成。批发价格波动性预测
 */

public class MfForreturnParameter extends BaseParameter {

	@ApiModel(value = "MfForreturnAddList", description = "批量添加MfForreturn所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfForreturn> entityList = new ArrayList<MfForreturn>();

		public ArrayList<MfForreturn> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfForreturn> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfForreturnDeleteByIdList", description = "批量删除MfForreturn所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfForreturnUpdateList", description = "批量修改MfForreturn所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfForreturn entity = new MfForreturn();

		public MfForreturn getEntity() {
			return entity;
		}

		public void setEntity(MfForreturn entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfForreturnGetList", description = "模糊查询MfForreturn所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfForreturnGetListEntityRelated", description = "模糊查询MfForreturn所需的参数，实体类相关。")
		public static class EntityRelated extends MfForreturnGenEnt{
        
		}
	}

}







