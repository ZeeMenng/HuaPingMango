package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiChannelExt;
import com.jusfoun.ent.generate.pi.PiChannelExtGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiChannelExtParameter，方法参数，自动生成。CMS栏目内容表
 */

public class PiChannelExtParameter extends BaseParameter {

	@ApiModel(value = "PiChannelExtAddList", description = "批量添加PiChannelExt所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiChannelExt> entityList = new ArrayList<PiChannelExt>();

		public ArrayList<PiChannelExt> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiChannelExt> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiChannelExtDeleteByIdList", description = "批量删除PiChannelExt所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiChannelExtUpdateList", description = "批量修改PiChannelExt所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiChannelExt entity = new PiChannelExt();

		public PiChannelExt getEntity() {
			return entity;
		}

		public void setEntity(PiChannelExt entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiChannelExtGetList", description = "模糊查询PiChannelExt所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiChannelExtGetListEntityRelated", description = "模糊查询PiChannelExt所需的参数，实体类相关。")
		public static class EntityRelated extends PiChannelExtGenEnt{
        
		}
	}

}







