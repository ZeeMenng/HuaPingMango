package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirContentGroupView;
import com.zee.ent.generate.pi.PirContentGroupViewGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PirContentGroupViewParameter，方法参数，自动生成。CMS内容浏览会员组关联表
 */

public class PirContentGroupViewParameter extends BaseParameter {

	@ApiModel(value = "PirContentGroupViewAddList", description = "批量添加PirContentGroupView所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirContentGroupView> entityList = new ArrayList<PirContentGroupView>();

		public ArrayList<PirContentGroupView> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirContentGroupView> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirContentGroupViewDeleteByIdList", description = "批量删除PirContentGroupView所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirContentGroupViewUpdateList", description = "批量修改PirContentGroupView所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirContentGroupView entity = new PirContentGroupView();

		public PirContentGroupView getEntity() {
			return entity;
		}

		public void setEntity(PirContentGroupView entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirContentGroupViewGetList", description = "模糊查询PirContentGroupView所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirContentGroupViewGetListEntityRelated", description = "模糊查询PirContentGroupView所需的参数，实体类相关。")
		public static class EntityRelated extends PirContentGroupViewGenEnt{
        
		}
	}

}







