package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaSentimentContent;
import com.jusfoun.ent.generate.da.DaSentimentContentGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaSentimentContentParameter，方法参数，自动生成。舆情文章内容表
 */

public class DaSentimentContentParameter extends BaseParameter {

	@ApiModel(value = "DaSentimentContentAddList", description = "批量添加DaSentimentContent所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSentimentContent> entityList = new ArrayList<DaSentimentContent>();

		public ArrayList<DaSentimentContent> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSentimentContent> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSentimentContentDeleteByIdList", description = "批量删除DaSentimentContent所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSentimentContentUpdateList", description = "批量修改DaSentimentContent所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSentimentContent entity = new DaSentimentContent();

		public DaSentimentContent getEntity() {
			return entity;
		}

		public void setEntity(DaSentimentContent entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSentimentContentGetList", description = "模糊查询DaSentimentContent所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSentimentContentGetListEntityRelated", description = "模糊查询DaSentimentContent所需的参数，实体类相关。")
		public static class EntityRelated extends DaSentimentContentGenEnt{
        
		}
	}

}







