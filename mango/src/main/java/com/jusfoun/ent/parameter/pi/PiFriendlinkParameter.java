package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiFriendlink;
import com.jusfoun.ent.generate.pi.PiFriendlinkGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/12 14:43:27
 * @description 实体类PiFriendlinkParameter，方法参数，自动生成。CMS友情链接
 */

public class PiFriendlinkParameter extends BaseParameter {

	@ApiModel(value = "PiFriendlinkAddList", description = "批量添加PiFriendlink所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiFriendlink> entityList = new ArrayList<PiFriendlink>();

		public ArrayList<PiFriendlink> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiFriendlink> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiFriendlinkDeleteByIdList", description = "批量删除PiFriendlink所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiFriendlinkUpdateList", description = "批量修改PiFriendlink所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiFriendlink entity = new PiFriendlink();

		public PiFriendlink getEntity() {
			return entity;
		}

		public void setEntity(PiFriendlink entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiFriendlinkGetList", description = "模糊查询PiFriendlink所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiFriendlinkGetListEntityRelated", description = "模糊查询PiFriendlink所需的参数，实体类相关。")
		public static class EntityRelated extends PiFriendlinkGenEnt{
        
		}
	}

}







