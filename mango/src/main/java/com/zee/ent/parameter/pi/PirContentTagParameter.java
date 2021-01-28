package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirContentTag;
import com.zee.ent.generate.pi.PirContentTagGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PirContentTagParameter，方法参数，自动生成。CMS内容标签关联表
 */

public class PirContentTagParameter extends BaseParameter {

	@ApiModel(value = "PirContentTagAddList", description = "批量添加PirContentTag所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirContentTag> entityList = new ArrayList<PirContentTag>();

		public ArrayList<PirContentTag> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirContentTag> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirContentTagDeleteByIdList", description = "批量删除PirContentTag所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirContentTagUpdateList", description = "批量修改PirContentTag所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirContentTag entity = new PirContentTag();

		public PirContentTag getEntity() {
			return entity;
		}

		public void setEntity(PirContentTag entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirContentTagGetList", description = "模糊查询PirContentTag所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirContentTagGetListEntityRelated", description = "模糊查询PirContentTag所需的参数，实体类相关。")
		public static class EntityRelated extends PirContentTagGenEnt{
        
		}
	}

}







