package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiChannelModel;
import com.jusfoun.ent.generate.pi.PiChannelModelGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiChannelModelParameter，方法参数，自动生成。CMS栏目可选内容模型表
 */

public class PiChannelModelParameter extends BaseParameter {

	@ApiModel(value = "PiChannelModelAddList", description = "批量添加PiChannelModel所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiChannelModel> entityList = new ArrayList<PiChannelModel>();

		public ArrayList<PiChannelModel> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiChannelModel> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiChannelModelDeleteByIdList", description = "批量删除PiChannelModel所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiChannelModelUpdateList", description = "批量修改PiChannelModel所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiChannelModel entity = new PiChannelModel();

		public PiChannelModel getEntity() {
			return entity;
		}

		public void setEntity(PiChannelModel entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiChannelModelGetList", description = "模糊查询PiChannelModel所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiChannelModelGetListEntityRelated", description = "模糊查询PiChannelModel所需的参数，实体类相关。")
		public static class EntityRelated extends PiChannelModelGenEnt{
        
		}
	}

}







