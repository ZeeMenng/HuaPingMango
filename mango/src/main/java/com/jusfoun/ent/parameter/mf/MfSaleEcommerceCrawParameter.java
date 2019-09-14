package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfSaleEcommerceCraw;
import com.jusfoun.ent.generate.mf.MfSaleEcommerceCrawGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfSaleEcommerceCrawParameter，方法参数，自动生成。电商数据表,采集
 */

public class MfSaleEcommerceCrawParameter extends BaseParameter {

	@ApiModel(value = "MfSaleEcommerceCrawAddList", description = "批量添加MfSaleEcommerceCraw所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfSaleEcommerceCraw> entityList = new ArrayList<MfSaleEcommerceCraw>();

		public ArrayList<MfSaleEcommerceCraw> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfSaleEcommerceCraw> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfSaleEcommerceCrawDeleteByIdList", description = "批量删除MfSaleEcommerceCraw所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfSaleEcommerceCrawUpdateList", description = "批量修改MfSaleEcommerceCraw所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfSaleEcommerceCraw entity = new MfSaleEcommerceCraw();

		public MfSaleEcommerceCraw getEntity() {
			return entity;
		}

		public void setEntity(MfSaleEcommerceCraw entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfSaleEcommerceCrawGetList", description = "模糊查询MfSaleEcommerceCraw所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfSaleEcommerceCrawGetListEntityRelated", description = "模糊查询MfSaleEcommerceCraw所需的参数，实体类相关。")
		public static class EntityRelated extends MfSaleEcommerceCrawGenEnt{
        
		}
	}

}







