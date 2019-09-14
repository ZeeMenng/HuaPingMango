package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiChannel;
import com.jusfoun.ent.generate.pi.PiChannelGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiChannelParameter，方法参数，自动生成。CMS栏目表
 */

public class PiChannelParameter extends BaseParameter {

	@ApiModel(value = "PiChannelAddList", description = "批量添加PiChannel所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiChannel> entityList = new ArrayList<PiChannel>();

		public ArrayList<PiChannel> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiChannel> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiChannelDeleteByIdList", description = "批量删除PiChannel所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiChannelUpdateList", description = "批量修改PiChannel所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiChannel entity = new PiChannel();

		public PiChannel getEntity() {
			return entity;
		}

		public void setEntity(PiChannel entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiChannelGetList", description = "模糊查询PiChannel所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiChannelGetListEntityRelated", description = "模糊查询PiChannel所需的参数，实体类相关。")
		public static class EntityRelated extends PiChannelGenEnt{
        
		}
	}

}







