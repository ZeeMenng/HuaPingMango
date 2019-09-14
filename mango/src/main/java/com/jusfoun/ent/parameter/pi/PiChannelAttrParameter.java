package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiChannelAttr;
import com.jusfoun.ent.generate.pi.PiChannelAttrGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiChannelAttrParameter，方法参数，自动生成。CMS栏目扩展属性表
 */

public class PiChannelAttrParameter extends BaseParameter {

	@ApiModel(value = "PiChannelAttrAddList", description = "批量添加PiChannelAttr所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiChannelAttr> entityList = new ArrayList<PiChannelAttr>();

		public ArrayList<PiChannelAttr> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiChannelAttr> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiChannelAttrDeleteByIdList", description = "批量删除PiChannelAttr所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiChannelAttrUpdateList", description = "批量修改PiChannelAttr所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiChannelAttr entity = new PiChannelAttr();

		public PiChannelAttr getEntity() {
			return entity;
		}

		public void setEntity(PiChannelAttr entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiChannelAttrGetList", description = "模糊查询PiChannelAttr所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiChannelAttrGetListEntityRelated", description = "模糊查询PiChannelAttr所需的参数，实体类相关。")
		public static class EntityRelated extends PiChannelAttrGenEnt{
        
		}
	}

}







