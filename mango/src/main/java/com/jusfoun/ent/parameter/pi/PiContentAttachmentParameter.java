package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiContentAttachment;
import com.zee.ent.generate.pi.PiContentAttachmentGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentAttachmentParameter，方法参数，自动生成。CMS内容附件表
 */

public class PiContentAttachmentParameter extends BaseParameter {

	@ApiModel(value = "PiContentAttachmentAddList", description = "批量添加PiContentAttachment所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentAttachment> entityList = new ArrayList<PiContentAttachment>();

		public ArrayList<PiContentAttachment> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentAttachment> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentAttachmentDeleteByIdList", description = "批量删除PiContentAttachment所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentAttachmentUpdateList", description = "批量修改PiContentAttachment所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentAttachment entity = new PiContentAttachment();

		public PiContentAttachment getEntity() {
			return entity;
		}

		public void setEntity(PiContentAttachment entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentAttachmentGetList", description = "模糊查询PiContentAttachment所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentAttachmentGetListEntityRelated", description = "模糊查询PiContentAttachment所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentAttachmentGenEnt{
        
		}
	}

}







