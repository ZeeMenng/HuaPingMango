package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaIndustryStatistics;
import com.zee.ent.generate.da.DaIndustryStatisticsGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIndustryStatisticsParameter，方法参数，自动生成。产业统计数据表
 */

public class DaIndustryStatisticsParameter extends BaseParameter {

	@ApiModel(value = "DaIndustryStatisticsAddList", description = "批量添加DaIndustryStatistics所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIndustryStatistics> entityList = new ArrayList<DaIndustryStatistics>();

		public ArrayList<DaIndustryStatistics> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIndustryStatistics> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIndustryStatisticsDeleteByIdList", description = "批量删除DaIndustryStatistics所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIndustryStatisticsUpdateList", description = "批量修改DaIndustryStatistics所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIndustryStatistics entity = new DaIndustryStatistics();

		public DaIndustryStatistics getEntity() {
			return entity;
		}

		public void setEntity(DaIndustryStatistics entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIndustryStatisticsGetList", description = "模糊查询DaIndustryStatistics所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIndustryStatisticsGetListEntityRelated", description = "模糊查询DaIndustryStatistics所需的参数，实体类相关。")
		public static class EntityRelated extends DaIndustryStatisticsGenEnt{
        
		}
	}

}







