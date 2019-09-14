package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaProcess;
import com.jusfoun.ent.generate.da.DaProcessGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaProcessParameter，方法参数，自动生成。加工品数据表
 */

public class DaProcessParameter extends BaseParameter {

	@ApiModel(value = "DaProcessAddList", description = "批量添加DaProcess所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaProcess> entityList = new ArrayList<DaProcess>();

		public ArrayList<DaProcess> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaProcess> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaProcessDeleteByIdList", description = "批量删除DaProcess所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaProcessUpdateList", description = "批量修改DaProcess所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaProcess entity = new DaProcess();

		public DaProcess getEntity() {
			return entity;
		}

		public void setEntity(DaProcess entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaProcessGetList", description = "模糊查询DaProcess所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaProcessGetListEntityRelated", description = "模糊查询DaProcess所需的参数，实体类相关。")
		public static class EntityRelated extends DaProcessGenEnt{
        
        @ApiModelProperty(value="加工时间查询起止时间。",required=false)
		private Date beginProcessTime;

        @ApiModelProperty(value="加工时间查询结束时间。",required=false)
		private Date endProcessTime;

		public Date getBeginProcessTime() {
			return this.beginProcessTime;
		}
        
		public void setBeginProcessTime(Date beginProcessTime) {
			this.beginProcessTime = beginProcessTime;
		}
        
        public Date getEndProcessTime() {
			return this.endProcessTime;
		}
        
		public void setEndProcessTime(Date endProcessTime) {
			this.endProcessTime = endProcessTime;
		}
        
		}
	}

}







