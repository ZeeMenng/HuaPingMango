package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfProductSale;
import com.zee.ent.generate.mf.MfProductSaleGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfProductSaleParameter，方法参数，自动生成。产销预测表
 */

public class MfProductSaleParameter extends BaseParameter {

	@ApiModel(value = "MfProductSaleAddList", description = "批量添加MfProductSale所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfProductSale> entityList = new ArrayList<MfProductSale>();

		public ArrayList<MfProductSale> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfProductSale> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfProductSaleDeleteByIdList", description = "批量删除MfProductSale所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfProductSaleUpdateList", description = "批量修改MfProductSale所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfProductSale entity = new MfProductSale();

		public MfProductSale getEntity() {
			return entity;
		}

		public void setEntity(MfProductSale entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfProductSaleGetList", description = "模糊查询MfProductSale所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfProductSaleGetListEntityRelated", description = "模糊查询MfProductSale所需的参数，实体类相关。")
		public static class EntityRelated extends MfProductSaleGenEnt{
        
		}
	}

}







