package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiContentAttr;
import com.jusfoun.ent.generate.pi.PiContentAttrGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentAttrParameter，方法参数，自动生成。CMS内容扩展属性表
 */

public class PiContentAttrParameter extends BaseParameter {

	@ApiModel(value = "PiContentAttrAddList", description = "批量添加PiContentAttr所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentAttr> entityList = new ArrayList<PiContentAttr>();

		public ArrayList<PiContentAttr> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentAttr> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentAttrDeleteByIdList", description = "批量删除PiContentAttr所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentAttrUpdateList", description = "批量修改PiContentAttr所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentAttr entity = new PiContentAttr();

		public PiContentAttr getEntity() {
			return entity;
		}

		public void setEntity(PiContentAttr entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentAttrGetList", description = "模糊查询PiContentAttr所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentAttrGetListEntityRelated", description = "模糊查询PiContentAttr所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentAttrGenEnt{
        
		}
	}

}







