package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaAltitudeInfo;
import com.jusfoun.ent.generate.da.DaAltitudeInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaAltitudeInfoParameter，方法参数，自动生成。海拔数据表
 */

public class DaAltitudeInfoParameter extends BaseParameter {

	@ApiModel(value = "DaAltitudeInfoAddList", description = "批量添加DaAltitudeInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaAltitudeInfo> entityList = new ArrayList<DaAltitudeInfo>();

		public ArrayList<DaAltitudeInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaAltitudeInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaAltitudeInfoDeleteByIdList", description = "批量删除DaAltitudeInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaAltitudeInfoUpdateList", description = "批量修改DaAltitudeInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaAltitudeInfo entity = new DaAltitudeInfo();

		public DaAltitudeInfo getEntity() {
			return entity;
		}

		public void setEntity(DaAltitudeInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaAltitudeInfoGetList", description = "模糊查询DaAltitudeInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaAltitudeInfoGetListEntityRelated", description = "模糊查询DaAltitudeInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaAltitudeInfoGenEnt{
        
		}
	}

}







