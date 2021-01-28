package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaPeasantInfo;
import com.zee.ent.generate.da.DaPeasantInfoGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-21 10:22:07
 * @description 实体类DaPeasantInfoParameter，方法参数，自动生成。农户信息表
 */

public class DaPeasantInfoParameter extends BaseParameter {

	@ApiModel(value = "DaPeasantInfoAddList", description = "批量添加DaPeasantInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaPeasantInfo> entityList = new ArrayList<DaPeasantInfo>();

		public ArrayList<DaPeasantInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaPeasantInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaPeasantInfoDeleteByIdList", description = "批量删除DaPeasantInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaPeasantInfoUpdateList", description = "批量修改DaPeasantInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaPeasantInfo entity = new DaPeasantInfo();

		public DaPeasantInfo getEntity() {
			return entity;
		}

		public void setEntity(DaPeasantInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaPeasantInfoGetList", description = "模糊查询DaPeasantInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaPeasantInfoGetListEntityRelated", description = "模糊查询DaPeasantInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaPeasantInfoGenEnt{
        
		}
	}

}







