package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PirEnterpriseProduct;
import com.jusfoun.ent.generate.pi.PirEnterpriseProductGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/11 17:38:33
 * @description 实体类PirEnterpriseProductParameter，方法参数，自动生成。企业和产品中间表
 */

public class PirEnterpriseProductParameter extends BaseParameter {

	@ApiModel(value = "PirEnterpriseProductAddList", description = "批量添加PirEnterpriseProduct所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirEnterpriseProduct> entityList = new ArrayList<PirEnterpriseProduct>();

		public ArrayList<PirEnterpriseProduct> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirEnterpriseProduct> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirEnterpriseProductDeleteByIdList", description = "批量删除PirEnterpriseProduct所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirEnterpriseProductUpdateList", description = "批量修改PirEnterpriseProduct所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirEnterpriseProduct entity = new PirEnterpriseProduct();

		public PirEnterpriseProduct getEntity() {
			return entity;
		}

		public void setEntity(PirEnterpriseProduct entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirEnterpriseProductGetList", description = "模糊查询PirEnterpriseProduct所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirEnterpriseProductGetListEntityRelated", description = "模糊查询PirEnterpriseProduct所需的参数，实体类相关。")
		public static class EntityRelated extends PirEnterpriseProductGenEnt{
        
		}
	}

}







