package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiChannelTxt;
import com.zee.ent.generate.pi.PiChannelTxtGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiChannelTxtParameter，方法参数，自动生成。CMS栏目文本表
 */

public class PiChannelTxtParameter extends BaseParameter {

	@ApiModel(value = "PiChannelTxtAddList", description = "批量添加PiChannelTxt所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiChannelTxt> entityList = new ArrayList<PiChannelTxt>();

		public ArrayList<PiChannelTxt> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiChannelTxt> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiChannelTxtDeleteByIdList", description = "批量删除PiChannelTxt所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiChannelTxtUpdateList", description = "批量修改PiChannelTxt所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiChannelTxt entity = new PiChannelTxt();

		public PiChannelTxt getEntity() {
			return entity;
		}

		public void setEntity(PiChannelTxt entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiChannelTxtGetList", description = "模糊查询PiChannelTxt所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiChannelTxtGetListEntityRelated", description = "模糊查询PiChannelTxt所需的参数，实体类相关。")
		public static class EntityRelated extends PiChannelTxtGenEnt{
        
		}
	}

}







