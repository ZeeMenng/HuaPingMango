package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfProcessVolume;
import com.jusfoun.ent.generate.mf.MfProcessVolumeGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfProcessVolumeParameter，方法参数，自动生成。mf_process_volume
 */

public class MfProcessVolumeParameter extends BaseParameter {

	@ApiModel(value = "MfProcessVolumeAddList", description = "批量添加MfProcessVolume所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfProcessVolume> entityList = new ArrayList<MfProcessVolume>();

		public ArrayList<MfProcessVolume> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfProcessVolume> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfProcessVolumeDeleteByIdList", description = "批量删除MfProcessVolume所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfProcessVolumeUpdateList", description = "批量修改MfProcessVolume所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfProcessVolume entity = new MfProcessVolume();

		public MfProcessVolume getEntity() {
			return entity;
		}

		public void setEntity(MfProcessVolume entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfProcessVolumeGetList", description = "模糊查询MfProcessVolume所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfProcessVolumeGetListEntityRelated", description = "模糊查询MfProcessVolume所需的参数，实体类相关。")
		public static class EntityRelated extends MfProcessVolumeGenEnt{
        
		}
	}

}







