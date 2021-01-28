package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaMarketPrice;
import com.zee.ent.generate.da.DaMarketPriceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaMarketPriceParameter，方法参数，自动生成。市场价格数据表
 */

public class DaMarketPriceParameter extends BaseParameter {

	@ApiModel(value = "DaMarketPriceAddList", description = "批量添加DaMarketPrice所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaMarketPrice> entityList = new ArrayList<DaMarketPrice>();

		public ArrayList<DaMarketPrice> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaMarketPrice> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaMarketPriceDeleteByIdList", description = "批量删除DaMarketPrice所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaMarketPriceUpdateList", description = "批量修改DaMarketPrice所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaMarketPrice entity = new DaMarketPrice();

		public DaMarketPrice getEntity() {
			return entity;
		}

		public void setEntity(DaMarketPrice entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaMarketPriceGetList", description = "模糊查询DaMarketPrice所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaMarketPriceGetListEntityRelated", description = "模糊查询DaMarketPrice所需的参数，实体类相关。")
		public static class EntityRelated extends DaMarketPriceGenEnt{
        
		}
	}

}







