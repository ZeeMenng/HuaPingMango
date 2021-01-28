package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfForprice;
import com.zee.ent.generate.mf.MfForpriceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfForpriceParameter，方法参数，自动生成。价格预测
 */

public class MfForpriceParameter extends BaseParameter {

	@ApiModel(value = "MfForpriceAddList", description = "批量添加MfForprice所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfForprice> entityList = new ArrayList<MfForprice>();

		public ArrayList<MfForprice> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfForprice> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfForpriceDeleteByIdList", description = "批量删除MfForprice所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfForpriceUpdateList", description = "批量修改MfForprice所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfForprice entity = new MfForprice();

		public MfForprice getEntity() {
			return entity;
		}

		public void setEntity(MfForprice entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfForpriceGetList", description = "模糊查询MfForprice所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfForpriceGetListEntityRelated", description = "模糊查询MfForprice所需的参数，实体类相关。")
		public static class EntityRelated extends MfForpriceGenEnt{
        
		}
	}

}







