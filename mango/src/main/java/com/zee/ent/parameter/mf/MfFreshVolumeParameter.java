package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfFreshVolume;
import com.zee.ent.generate.mf.MfFreshVolumeGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfFreshVolumeParameter，方法参数，自动生成。mf_fresh_volume
 */

public class MfFreshVolumeParameter extends BaseParameter {

	@ApiModel(value = "MfFreshVolumeAddList", description = "批量添加MfFreshVolume所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfFreshVolume> entityList = new ArrayList<MfFreshVolume>();

		public ArrayList<MfFreshVolume> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfFreshVolume> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfFreshVolumeDeleteByIdList", description = "批量删除MfFreshVolume所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfFreshVolumeUpdateList", description = "批量修改MfFreshVolume所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfFreshVolume entity = new MfFreshVolume();

		public MfFreshVolume getEntity() {
			return entity;
		}

		public void setEntity(MfFreshVolume entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfFreshVolumeGetList", description = "模糊查询MfFreshVolume所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfFreshVolumeGetListEntityRelated", description = "模糊查询MfFreshVolume所需的参数，实体类相关。")
		public static class EntityRelated extends MfFreshVolumeGenEnt{
        
		}
	}

}







