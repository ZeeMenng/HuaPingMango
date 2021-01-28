package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaOnlineRetailers;
import com.zee.ent.generate.da.DaOnlineRetailersGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-25 10:47:17
 * @description 实体类DaOnlineRetailersParameter，方法参数，自动生成。芒果电商分部表
 */

public class DaOnlineRetailersParameter extends BaseParameter {

	@ApiModel(value = "DaOnlineRetailersAddList", description = "批量添加DaOnlineRetailers所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaOnlineRetailers> entityList = new ArrayList<DaOnlineRetailers>();

		public ArrayList<DaOnlineRetailers> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaOnlineRetailers> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaOnlineRetailersDeleteByIdList", description = "批量删除DaOnlineRetailers所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaOnlineRetailersUpdateList", description = "批量修改DaOnlineRetailers所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaOnlineRetailers entity = new DaOnlineRetailers();

		public DaOnlineRetailers getEntity() {
			return entity;
		}

		public void setEntity(DaOnlineRetailers entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaOnlineRetailersGetList", description = "模糊查询DaOnlineRetailers所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaOnlineRetailersGetListEntityRelated", description = "模糊查询DaOnlineRetailers所需的参数，实体类相关。")
		public static class EntityRelated extends DaOnlineRetailersGenEnt{
        
		}
	}

}







