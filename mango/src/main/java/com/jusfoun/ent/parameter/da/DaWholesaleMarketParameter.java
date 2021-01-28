package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaWholesaleMarket;
import com.zee.ent.generate.da.DaWholesaleMarketGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-25 10:47:17
 * @description 实体类DaWholesaleMarketParameter，方法参数，自动生成。批发市场
 */

public class DaWholesaleMarketParameter extends BaseParameter {

	@ApiModel(value = "DaWholesaleMarketAddList", description = "批量添加DaWholesaleMarket所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaWholesaleMarket> entityList = new ArrayList<DaWholesaleMarket>();

		public ArrayList<DaWholesaleMarket> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaWholesaleMarket> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaWholesaleMarketDeleteByIdList", description = "批量删除DaWholesaleMarket所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaWholesaleMarketUpdateList", description = "批量修改DaWholesaleMarket所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaWholesaleMarket entity = new DaWholesaleMarket();

		public DaWholesaleMarket getEntity() {
			return entity;
		}

		public void setEntity(DaWholesaleMarket entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaWholesaleMarketGetList", description = "模糊查询DaWholesaleMarket所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaWholesaleMarketGetListEntityRelated", description = "模糊查询DaWholesaleMarket所需的参数，实体类相关。")
		public static class EntityRelated extends DaWholesaleMarketGenEnt{
        
		}
	}

}







