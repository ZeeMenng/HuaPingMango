package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaCorpDisaster;
import com.jusfoun.ent.generate.da.DaCorpDisasterGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaCorpDisasterParameter，方法参数，自动生成。作物受灾数据表
 */

public class DaCorpDisasterParameter extends BaseParameter {

	@ApiModel(value = "DaCorpDisasterAddList", description = "批量添加DaCorpDisaster所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaCorpDisaster> entityList = new ArrayList<DaCorpDisaster>();

		public ArrayList<DaCorpDisaster> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaCorpDisaster> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaCorpDisasterDeleteByIdList", description = "批量删除DaCorpDisaster所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaCorpDisasterUpdateList", description = "批量修改DaCorpDisaster所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaCorpDisaster entity = new DaCorpDisaster();

		public DaCorpDisaster getEntity() {
			return entity;
		}

		public void setEntity(DaCorpDisaster entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaCorpDisasterGetList", description = "模糊查询DaCorpDisaster所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaCorpDisasterGetListEntityRelated", description = "模糊查询DaCorpDisaster所需的参数，实体类相关。")
		public static class EntityRelated extends DaCorpDisasterGenEnt{
        
		}
	}

}







