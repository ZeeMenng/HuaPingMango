package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PirChnlGroupView;
import com.jusfoun.ent.generate.pi.PirChnlGroupViewGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PirChnlGroupViewParameter，方法参数，自动生成。CMS栏目浏览会员组关联表
 */

public class PirChnlGroupViewParameter extends BaseParameter {

	@ApiModel(value = "PirChnlGroupViewAddList", description = "批量添加PirChnlGroupView所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirChnlGroupView> entityList = new ArrayList<PirChnlGroupView>();

		public ArrayList<PirChnlGroupView> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirChnlGroupView> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirChnlGroupViewDeleteByIdList", description = "批量删除PirChnlGroupView所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirChnlGroupViewUpdateList", description = "批量修改PirChnlGroupView所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirChnlGroupView entity = new PirChnlGroupView();

		public PirChnlGroupView getEntity() {
			return entity;
		}

		public void setEntity(PirChnlGroupView entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirChnlGroupViewGetList", description = "模糊查询PirChnlGroupView所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirChnlGroupViewGetListEntityRelated", description = "模糊查询PirChnlGroupView所需的参数，实体类相关。")
		public static class EntityRelated extends PirChnlGroupViewGenEnt{
        
		}
	}

}







