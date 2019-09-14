package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PirContentChannel;
import com.jusfoun.ent.generate.pi.PirContentChannelGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PirContentChannelParameter，方法参数，自动生成。CMS内容栏目关联表
 */

public class PirContentChannelParameter extends BaseParameter {

	@ApiModel(value = "PirContentChannelAddList", description = "批量添加PirContentChannel所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirContentChannel> entityList = new ArrayList<PirContentChannel>();

		public ArrayList<PirContentChannel> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirContentChannel> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirContentChannelDeleteByIdList", description = "批量删除PirContentChannel所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirContentChannelUpdateList", description = "批量修改PirContentChannel所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirContentChannel entity = new PirContentChannel();

		public PirContentChannel getEntity() {
			return entity;
		}

		public void setEntity(PirContentChannel entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirContentChannelGetList", description = "模糊查询PirContentChannel所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirContentChannelGetListEntityRelated", description = "模糊查询PirContentChannel所需的参数，实体类相关。")
		public static class EntityRelated extends PirContentChannelGenEnt{
        
		}
	}

}







