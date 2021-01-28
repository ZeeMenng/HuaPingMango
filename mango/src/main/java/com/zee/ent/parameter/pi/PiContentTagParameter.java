package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiContentTag;
import com.zee.ent.generate.pi.PiContentTagGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentTagParameter，方法参数，自动生成。CMS内容TAG表
 */

public class PiContentTagParameter extends BaseParameter {

	@ApiModel(value = "PiContentTagAddList", description = "批量添加PiContentTag所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentTag> entityList = new ArrayList<PiContentTag>();

		public ArrayList<PiContentTag> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentTag> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentTagDeleteByIdList", description = "批量删除PiContentTag所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentTagUpdateList", description = "批量修改PiContentTag所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentTag entity = new PiContentTag();

		public PiContentTag getEntity() {
			return entity;
		}

		public void setEntity(PiContentTag entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentTagGetList", description = "模糊查询PiContentTag所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentTagGetListEntityRelated", description = "模糊查询PiContentTag所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentTagGenEnt{
        
		}
	}

}







