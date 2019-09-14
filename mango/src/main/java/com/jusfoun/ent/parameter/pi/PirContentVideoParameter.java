package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PirContentVideo;
import com.jusfoun.ent.generate.pi.PirContentVideoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/7 15:00:36
 * @description 实体类PirContentVideoParameter，方法参数，自动生成。CMS文章内容所包含视频信息表
 */

public class PirContentVideoParameter extends BaseParameter {

	@ApiModel(value = "PirContentVideoAddList", description = "批量添加PirContentVideo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirContentVideo> entityList = new ArrayList<PirContentVideo>();

		public ArrayList<PirContentVideo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirContentVideo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirContentVideoDeleteByIdList", description = "批量删除PirContentVideo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirContentVideoUpdateList", description = "批量修改PirContentVideo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirContentVideo entity = new PirContentVideo();

		public PirContentVideo getEntity() {
			return entity;
		}

		public void setEntity(PirContentVideo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirContentVideoGetList", description = "模糊查询PirContentVideo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirContentVideoGetListEntityRelated", description = "模糊查询PirContentVideo所需的参数，实体类相关。")
		public static class EntityRelated extends PirContentVideoGenEnt{
        
		}
	}

}







