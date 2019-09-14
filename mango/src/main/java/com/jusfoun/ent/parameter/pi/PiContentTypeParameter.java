package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiContentType;
import com.jusfoun.ent.generate.pi.PiContentTypeGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentTypeParameter，方法参数，自动生成。CMS内容类型表
 */

public class PiContentTypeParameter extends BaseParameter {

	@ApiModel(value = "PiContentTypeAddList", description = "批量添加PiContentType所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentType> entityList = new ArrayList<PiContentType>();

		public ArrayList<PiContentType> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentType> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentTypeDeleteByIdList", description = "批量删除PiContentType所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentTypeUpdateList", description = "批量修改PiContentType所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentType entity = new PiContentType();

		public PiContentType getEntity() {
			return entity;
		}

		public void setEntity(PiContentType entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentTypeGetList", description = "模糊查询PiContentType所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentTypeGetListEntityRelated", description = "模糊查询PiContentType所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentTypeGenEnt{
        
		}
	}

}







