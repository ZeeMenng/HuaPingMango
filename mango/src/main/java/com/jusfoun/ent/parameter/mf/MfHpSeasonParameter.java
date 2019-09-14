package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfHpSeason;
import com.jusfoun.ent.generate.mf.MfHpSeasonGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfHpSeasonParameter，方法参数，自动生成。批发价格季节性波动规律分析
 */

public class MfHpSeasonParameter extends BaseParameter {

	@ApiModel(value = "MfHpSeasonAddList", description = "批量添加MfHpSeason所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfHpSeason> entityList = new ArrayList<MfHpSeason>();

		public ArrayList<MfHpSeason> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfHpSeason> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfHpSeasonDeleteByIdList", description = "批量删除MfHpSeason所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfHpSeasonUpdateList", description = "批量修改MfHpSeason所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfHpSeason entity = new MfHpSeason();

		public MfHpSeason getEntity() {
			return entity;
		}

		public void setEntity(MfHpSeason entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfHpSeasonGetList", description = "模糊查询MfHpSeason所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfHpSeasonGetListEntityRelated", description = "模糊查询MfHpSeason所需的参数，实体类相关。")
		public static class EntityRelated extends MfHpSeasonGenEnt{
        
		}
	}

}







