package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaCheckTesting;
import com.zee.ent.generate.da.DaCheckTestingGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaCheckTestingParameter，方法参数，自动生成。检测数据表
 */

public class DaCheckTestingParameter extends BaseParameter {

	@ApiModel(value = "DaCheckTestingAddList", description = "批量添加DaCheckTesting所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaCheckTesting> entityList = new ArrayList<DaCheckTesting>();

		public ArrayList<DaCheckTesting> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaCheckTesting> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaCheckTestingDeleteByIdList", description = "批量删除DaCheckTesting所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaCheckTestingUpdateList", description = "批量修改DaCheckTesting所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaCheckTesting entity = new DaCheckTesting();

		public DaCheckTesting getEntity() {
			return entity;
		}

		public void setEntity(DaCheckTesting entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaCheckTestingGetList", description = "模糊查询DaCheckTesting所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaCheckTestingGetListEntityRelated", description = "模糊查询DaCheckTesting所需的参数，实体类相关。")
		public static class EntityRelated extends DaCheckTestingGenEnt{
        
        @ApiModelProperty(value="检测时间查询起止时间。",required=false)
		private Date beginCheckDate;

        @ApiModelProperty(value="检测时间查询结束时间。",required=false)
		private Date endCheckDate;

		public Date getBeginCheckDate() {
			return this.beginCheckDate;
		}
        
		public void setBeginCheckDate(Date beginCheckDate) {
			this.beginCheckDate = beginCheckDate;
		}
        
        public Date getEndCheckDate() {
			return this.endCheckDate;
		}
        
		public void setEndCheckDate(Date endCheckDate) {
			this.endCheckDate = endCheckDate;
		}
        
		}
	}

}







