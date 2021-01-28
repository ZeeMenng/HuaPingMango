package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaEnterpriseDistribution;
import com.zee.ent.generate.da.DaEnterpriseDistributionGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-25 10:47:17
 * @description 实体类DaEnterpriseDistributionParameter，方法参数，自动生成。芒果相关企业分布
 */

public class DaEnterpriseDistributionParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseDistributionAddList", description = "批量添加DaEnterpriseDistribution所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseDistribution> entityList = new ArrayList<DaEnterpriseDistribution>();

		public ArrayList<DaEnterpriseDistribution> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseDistribution> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseDistributionDeleteByIdList", description = "批量删除DaEnterpriseDistribution所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseDistributionUpdateList", description = "批量修改DaEnterpriseDistribution所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseDistribution entity = new DaEnterpriseDistribution();

		public DaEnterpriseDistribution getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseDistribution entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseDistributionGetList", description = "模糊查询DaEnterpriseDistribution所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseDistributionGetListEntityRelated", description = "模糊查询DaEnterpriseDistribution所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseDistributionGenEnt{
        
		}
	}

}







