package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfHpFluct;
import com.zee.ent.generate.mf.MfHpFluctGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfHpFluctParameter，方法参数，自动生成。批发价格周期性波动规律分析
 */

public class MfHpFluctParameter extends BaseParameter {

	@ApiModel(value = "MfHpFluctAddList", description = "批量添加MfHpFluct所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfHpFluct> entityList = new ArrayList<MfHpFluct>();

		public ArrayList<MfHpFluct> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfHpFluct> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfHpFluctDeleteByIdList", description = "批量删除MfHpFluct所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfHpFluctUpdateList", description = "批量修改MfHpFluct所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfHpFluct entity = new MfHpFluct();

		public MfHpFluct getEntity() {
			return entity;
		}

		public void setEntity(MfHpFluct entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfHpFluctGetList", description = "模糊查询MfHpFluct所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfHpFluctGetListEntityRelated", description = "模糊查询MfHpFluct所需的参数，实体类相关。")
		public static class EntityRelated extends MfHpFluctGenEnt{
        
		}
	}

}







