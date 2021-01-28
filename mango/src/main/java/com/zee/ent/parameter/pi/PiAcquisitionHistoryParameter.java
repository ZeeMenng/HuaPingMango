package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiAcquisitionHistory;
import com.zee.ent.generate.pi.PiAcquisitionHistoryGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiAcquisitionHistoryParameter，方法参数，自动生成。CMS采集的文章历史记录表
 */

public class PiAcquisitionHistoryParameter extends BaseParameter {

	@ApiModel(value = "PiAcquisitionHistoryAddList", description = "批量添加PiAcquisitionHistory所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiAcquisitionHistory> entityList = new ArrayList<PiAcquisitionHistory>();

		public ArrayList<PiAcquisitionHistory> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiAcquisitionHistory> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiAcquisitionHistoryDeleteByIdList", description = "批量删除PiAcquisitionHistory所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiAcquisitionHistoryUpdateList", description = "批量修改PiAcquisitionHistory所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiAcquisitionHistory entity = new PiAcquisitionHistory();

		public PiAcquisitionHistory getEntity() {
			return entity;
		}

		public void setEntity(PiAcquisitionHistory entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiAcquisitionHistoryGetList", description = "模糊查询PiAcquisitionHistory所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiAcquisitionHistoryGetListEntityRelated", description = "模糊查询PiAcquisitionHistory所需的参数，实体类相关。")
		public static class EntityRelated extends PiAcquisitionHistoryGenEnt{
        
		}
	}

}







