package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PirChannelUser;
import com.zee.ent.generate.pi.PirChannelUserGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PirChannelUserParameter，方法参数，自动生成。CMS栏目用户关联表
 */

public class PirChannelUserParameter extends BaseParameter {

	@ApiModel(value = "PirChannelUserAddList", description = "批量添加PirChannelUser所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PirChannelUser> entityList = new ArrayList<PirChannelUser>();

		public ArrayList<PirChannelUser> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PirChannelUser> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PirChannelUserDeleteByIdList", description = "批量删除PirChannelUser所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PirChannelUserUpdateList", description = "批量修改PirChannelUser所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PirChannelUser entity = new PirChannelUser();

		public PirChannelUser getEntity() {
			return entity;
		}

		public void setEntity(PirChannelUser entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PirChannelUserGetList", description = "模糊查询PirChannelUser所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PirChannelUserGetListEntityRelated", description = "模糊查询PirChannelUser所需的参数，实体类相关。")
		public static class EntityRelated extends PirChannelUserGenEnt{
        
		}
	}

}







