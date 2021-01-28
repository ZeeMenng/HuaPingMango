package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfMarketPriceCraw;
import com.zee.ent.generate.mf.MfMarketPriceCrawGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfMarketPriceCrawParameter，方法参数，自动生成。市场价格数据表（采集）
 */

public class MfMarketPriceCrawParameter extends BaseParameter {

	@ApiModel(value = "MfMarketPriceCrawAddList", description = "批量添加MfMarketPriceCraw所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfMarketPriceCraw> entityList = new ArrayList<MfMarketPriceCraw>();

		public ArrayList<MfMarketPriceCraw> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfMarketPriceCraw> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfMarketPriceCrawDeleteByIdList", description = "批量删除MfMarketPriceCraw所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfMarketPriceCrawUpdateList", description = "批量修改MfMarketPriceCraw所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfMarketPriceCraw entity = new MfMarketPriceCraw();

		public MfMarketPriceCraw getEntity() {
			return entity;
		}

		public void setEntity(MfMarketPriceCraw entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfMarketPriceCrawGetList", description = "模糊查询MfMarketPriceCraw所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfMarketPriceCrawGetListEntityRelated", description = "模糊查询MfMarketPriceCraw所需的参数，实体类相关。")
		public static class EntityRelated extends MfMarketPriceCrawGenEnt{
        
		}
	}

}







