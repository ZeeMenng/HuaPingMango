package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirContentImage;
import com.zee.ent.generate.pi.PirContentImageGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/5/7 15:00:36
 * @description 实体类PirContentImageParameter，方法参数，自动生成。CMS文章内容所包含图片信息表
 */

public class PirContentImageParameter extends BaseParameter {

	@ApiModel(value = "PirContentImageAddList", description = "批量添加PirContentImage所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirContentImage> entityList = new ArrayList<PirContentImage>();

		public ArrayList<PirContentImage> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirContentImage> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirContentImageDeleteByIdList", description = "批量删除PirContentImage所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirContentImageUpdateList", description = "批量修改PirContentImage所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirContentImage entity = new PirContentImage();

		public PirContentImage getEntity() {
			return entity;
		}

		public void setEntity(PirContentImage entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirContentImageGetList", description = "模糊查询PirContentImage所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirContentImageGetListEntityRelated", description = "模糊查询PirContentImage所需的参数，实体类相关。")
		public static class EntityRelated extends PirContentImageGenEnt{
        
		}
	}

}







