package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfScavenging;
import com.jusfoun.ent.generate.mf.MfScavengingGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfScavengingParameter，方法参数，自动生成。扫码反馈表
 */

public class MfScavengingParameter extends BaseParameter {

	@ApiModel(value = "MfScavengingAddList", description = "批量添加MfScavenging所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfScavenging> entityList = new ArrayList<MfScavenging>();

		public ArrayList<MfScavenging> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfScavenging> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfScavengingDeleteByIdList", description = "批量删除MfScavenging所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfScavengingUpdateList", description = "批量修改MfScavenging所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfScavenging entity = new MfScavenging();

		public MfScavenging getEntity() {
			return entity;
		}

		public void setEntity(MfScavenging entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfScavengingGetList", description = "模糊查询MfScavenging所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfScavengingGetListEntityRelated", description = "模糊查询MfScavenging所需的参数，实体类相关。")
		public static class EntityRelated extends MfScavengingGenEnt{
        
		}
	}

}







