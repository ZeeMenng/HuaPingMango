package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiContentPicture;
import com.zee.ent.generate.pi.PiContentPictureGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentPictureParameter，方法参数，自动生成。CMS内容图片表
 */

public class PiContentPictureParameter extends BaseParameter {

	@ApiModel(value = "PiContentPictureAddList", description = "批量添加PiContentPicture所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentPicture> entityList = new ArrayList<PiContentPicture>();

		public ArrayList<PiContentPicture> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentPicture> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentPictureDeleteByIdList", description = "批量删除PiContentPicture所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentPictureUpdateList", description = "批量修改PiContentPicture所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentPicture entity = new PiContentPicture();

		public PiContentPicture getEntity() {
			return entity;
		}

		public void setEntity(PiContentPicture entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentPictureGetList", description = "模糊查询PiContentPicture所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentPictureGetListEntityRelated", description = "模糊查询PiContentPicture所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentPictureGenEnt{
        
		}
	}

}







