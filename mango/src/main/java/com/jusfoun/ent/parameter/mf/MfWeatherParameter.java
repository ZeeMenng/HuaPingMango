package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfWeather;
import com.jusfoun.ent.generate.mf.MfWeatherGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfWeatherParameter，方法参数，自动生成。气象建模,气象阈值表
 */

public class MfWeatherParameter extends BaseParameter {

	@ApiModel(value = "MfWeatherAddList", description = "批量添加MfWeather所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfWeather> entityList = new ArrayList<MfWeather>();

		public ArrayList<MfWeather> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfWeather> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfWeatherDeleteByIdList", description = "批量删除MfWeather所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfWeatherUpdateList", description = "批量修改MfWeather所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfWeather entity = new MfWeather();

		public MfWeather getEntity() {
			return entity;
		}

		public void setEntity(MfWeather entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfWeatherGetList", description = "模糊查询MfWeather所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfWeatherGetListEntityRelated", description = "模糊查询MfWeather所需的参数，实体类相关。")
		public static class EntityRelated extends MfWeatherGenEnt{
        
		}
	}

}







