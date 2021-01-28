package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaIndustryBasicinfo;
import com.zee.ent.generate.da.DaIndustryBasicinfoGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIndustryBasicinfoParameter，方法参数，自动生成。产业基本情况表
 */

public class DaIndustryBasicinfoParameter extends BaseParameter {

	@ApiModel(value = "DaIndustryBasicinfoAddList", description = "批量添加DaIndustryBasicinfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIndustryBasicinfo> entityList = new ArrayList<DaIndustryBasicinfo>();

		public ArrayList<DaIndustryBasicinfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIndustryBasicinfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIndustryBasicinfoDeleteByIdList", description = "批量删除DaIndustryBasicinfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIndustryBasicinfoUpdateList", description = "批量修改DaIndustryBasicinfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIndustryBasicinfo entity = new DaIndustryBasicinfo();

		public DaIndustryBasicinfo getEntity() {
			return entity;
		}

		public void setEntity(DaIndustryBasicinfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIndustryBasicinfoGetList", description = "模糊查询DaIndustryBasicinfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIndustryBasicinfoGetListEntityRelated", description = "模糊查询DaIndustryBasicinfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaIndustryBasicinfoGenEnt{
        
		}
	}

}







