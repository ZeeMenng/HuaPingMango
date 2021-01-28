package com.zee.ent.parameter.mf;

import java.util.*;

import com.zee.ent.extend.mf.MfInputSubject;
import com.zee.ent.generate.mf.MfInputSubjectGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfInputSubjectParameter，方法参数，自动生成。投入品主体监管表
 */

public class MfInputSubjectParameter extends BaseParameter {

	@ApiModel(value = "MfInputSubjectAddList", description = "批量添加MfInputSubject所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfInputSubject> entityList = new ArrayList<MfInputSubject>();

		public ArrayList<MfInputSubject> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfInputSubject> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfInputSubjectDeleteByIdList", description = "批量删除MfInputSubject所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfInputSubjectUpdateList", description = "批量修改MfInputSubject所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfInputSubject entity = new MfInputSubject();

		public MfInputSubject getEntity() {
			return entity;
		}

		public void setEntity(MfInputSubject entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfInputSubjectGetList", description = "模糊查询MfInputSubject所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfInputSubjectGetListEntityRelated", description = "模糊查询MfInputSubject所需的参数，实体类相关。")
		public static class EntityRelated extends MfInputSubjectGenEnt{
        
		}
	}

}







