package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaIndustryProcess;
import com.jusfoun.ent.generate.da.DaIndustryProcessGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIndustryProcessParameter，方法参数，自动生成。加工品产值数据表
 */

public class DaIndustryProcessParameter extends BaseParameter {

	@ApiModel(value = "DaIndustryProcessAddList", description = "批量添加DaIndustryProcess所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIndustryProcess> entityList = new ArrayList<DaIndustryProcess>();

		public ArrayList<DaIndustryProcess> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIndustryProcess> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIndustryProcessDeleteByIdList", description = "批量删除DaIndustryProcess所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIndustryProcessUpdateList", description = "批量修改DaIndustryProcess所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIndustryProcess entity = new DaIndustryProcess();

		public DaIndustryProcess getEntity() {
			return entity;
		}

		public void setEntity(DaIndustryProcess entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIndustryProcessGetList", description = "模糊查询DaIndustryProcess所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIndustryProcessGetListEntityRelated", description = "模糊查询DaIndustryProcess所需的参数，实体类相关。")
		public static class EntityRelated extends DaIndustryProcessGenEnt{
        
		}
	}

}







