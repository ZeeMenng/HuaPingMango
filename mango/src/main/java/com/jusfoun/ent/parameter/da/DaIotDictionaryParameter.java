package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaIotDictionary;
import com.zee.ent.generate.da.DaIotDictionaryGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIotDictionaryParameter，方法参数，自动生成。物联网传感器设备字典
 */

public class DaIotDictionaryParameter extends BaseParameter {

	@ApiModel(value = "DaIotDictionaryAddList", description = "批量添加DaIotDictionary所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIotDictionary> entityList = new ArrayList<DaIotDictionary>();

		public ArrayList<DaIotDictionary> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIotDictionary> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIotDictionaryDeleteByIdList", description = "批量删除DaIotDictionary所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIotDictionaryUpdateList", description = "批量修改DaIotDictionary所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIotDictionary entity = new DaIotDictionary();

		public DaIotDictionary getEntity() {
			return entity;
		}

		public void setEntity(DaIotDictionary entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIotDictionaryGetList", description = "模糊查询DaIotDictionary所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIotDictionaryGetListEntityRelated", description = "模糊查询DaIotDictionary所需的参数，实体类相关。")
		public static class EntityRelated extends DaIotDictionaryGenEnt{
        
		}
	}

}







