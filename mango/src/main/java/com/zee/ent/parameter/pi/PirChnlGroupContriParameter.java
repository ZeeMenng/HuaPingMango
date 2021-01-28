package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirChnlGroupContri;
import com.zee.ent.generate.pi.PirChnlGroupContriGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PirChnlGroupContriParameter，方法参数，自动生成。CMS栏目投稿会员组关联表
 */

public class PirChnlGroupContriParameter extends BaseParameter {

	@ApiModel(value = "PirChnlGroupContriAddList", description = "批量添加PirChnlGroupContri所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirChnlGroupContri> entityList = new ArrayList<PirChnlGroupContri>();

		public ArrayList<PirChnlGroupContri> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirChnlGroupContri> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirChnlGroupContriDeleteByIdList", description = "批量删除PirChnlGroupContri所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirChnlGroupContriUpdateList", description = "批量修改PirChnlGroupContri所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirChnlGroupContri entity = new PirChnlGroupContri();

		public PirChnlGroupContri getEntity() {
			return entity;
		}

		public void setEntity(PirChnlGroupContri entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirChnlGroupContriGetList", description = "模糊查询PirChnlGroupContri所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirChnlGroupContriGetListEntityRelated", description = "模糊查询PirChnlGroupContri所需的参数，实体类相关。")
		public static class EntityRelated extends PirChnlGroupContriGenEnt{
        
		}
	}

}







