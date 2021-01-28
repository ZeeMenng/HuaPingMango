package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaIndustryFresh;
import com.zee.ent.generate.da.DaIndustryFreshGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIndustryFreshParameter，方法参数，自动生成。鲜果产值数据表
 */

public class DaIndustryFreshParameter extends BaseParameter {

	@ApiModel(value = "DaIndustryFreshAddList", description = "批量添加DaIndustryFresh所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIndustryFresh> entityList = new ArrayList<DaIndustryFresh>();

		public ArrayList<DaIndustryFresh> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIndustryFresh> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIndustryFreshDeleteByIdList", description = "批量删除DaIndustryFresh所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIndustryFreshUpdateList", description = "批量修改DaIndustryFresh所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIndustryFresh entity = new DaIndustryFresh();

		public DaIndustryFresh getEntity() {
			return entity;
		}

		public void setEntity(DaIndustryFresh entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIndustryFreshGetList", description = "模糊查询DaIndustryFresh所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIndustryFreshGetListEntityRelated", description = "模糊查询DaIndustryFresh所需的参数，实体类相关。")
		public static class EntityRelated extends DaIndustryFreshGenEnt{
        
		}
	}

}







