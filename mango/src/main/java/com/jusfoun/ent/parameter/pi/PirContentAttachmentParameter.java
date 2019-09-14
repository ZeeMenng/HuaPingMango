package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PirContentAttachment;
import com.jusfoun.ent.generate.pi.PirContentAttachmentGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/7 15:00:36
 * @description 实体类PirContentAttachmentParameter，方法参数，自动生成。CMS文章内容包含附件信息表。
 */

public class PirContentAttachmentParameter extends BaseParameter {

	@ApiModel(value = "PirContentAttachmentAddList", description = "批量添加PirContentAttachment所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirContentAttachment> entityList = new ArrayList<PirContentAttachment>();

		public ArrayList<PirContentAttachment> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirContentAttachment> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirContentAttachmentDeleteByIdList", description = "批量删除PirContentAttachment所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirContentAttachmentUpdateList", description = "批量修改PirContentAttachment所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirContentAttachment entity = new PirContentAttachment();

		public PirContentAttachment getEntity() {
			return entity;
		}

		public void setEntity(PirContentAttachment entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirContentAttachmentGetList", description = "模糊查询PirContentAttachment所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirContentAttachmentGetListEntityRelated", description = "模糊查询PirContentAttachment所需的参数，实体类相关。")
		public static class EntityRelated extends PirContentAttachmentGenEnt{
        
		}
	}

}







