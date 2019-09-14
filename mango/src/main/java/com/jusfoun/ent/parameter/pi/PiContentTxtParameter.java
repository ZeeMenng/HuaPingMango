package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiContentTxt;
import com.jusfoun.ent.generate.pi.PiContentTxtGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentTxtParameter，方法参数，自动生成。CMS内容文本表
 */

public class PiContentTxtParameter extends BaseParameter {

	@ApiModel(value = "PiContentTxtAddList", description = "批量添加PiContentTxt所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentTxt> entityList = new ArrayList<PiContentTxt>();

		public ArrayList<PiContentTxt> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentTxt> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentTxtDeleteByIdList", description = "批量删除PiContentTxt所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentTxtUpdateList", description = "批量修改PiContentTxt所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentTxt entity = new PiContentTxt();

		public PiContentTxt getEntity() {
			return entity;
		}

		public void setEntity(PiContentTxt entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentTxtGetList", description = "模糊查询PiContentTxt所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentTxtGetListEntityRelated", description = "模糊查询PiContentTxt所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentTxtGenEnt{
        
		}
	}

}







