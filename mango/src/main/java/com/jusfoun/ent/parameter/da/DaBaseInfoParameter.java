package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaBaseInfo;
import com.zee.ent.generate.da.DaBaseInfoGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-20 14:12:54
 * @description 实体类DaBaseInfoParameter，方法参数，自动生成。基地信息表
 */

public class DaBaseInfoParameter extends BaseParameter {

	@ApiModel(value = "DaBaseInfoAddList", description = "批量添加DaBaseInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaBaseInfo> entityList = new ArrayList<DaBaseInfo>();

		public ArrayList<DaBaseInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaBaseInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaBaseInfoDeleteByIdList", description = "批量删除DaBaseInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaBaseInfoUpdateList", description = "批量修改DaBaseInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaBaseInfo entity = new DaBaseInfo();

		public DaBaseInfo getEntity() {
			return entity;
		}

		public void setEntity(DaBaseInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaBaseInfoGetList", description = "模糊查询DaBaseInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaBaseInfoGetListEntityRelated", description = "模糊查询DaBaseInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaBaseInfoGenEnt{
        
		}
	}

}







