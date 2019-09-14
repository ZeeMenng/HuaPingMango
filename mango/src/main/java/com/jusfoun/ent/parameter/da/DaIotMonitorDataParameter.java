package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaIotMonitorData;
import com.jusfoun.ent.generate.da.DaIotMonitorDataGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIotMonitorDataParameter，方法参数，自动生成。物联网设备监控数据
 */

public class DaIotMonitorDataParameter extends BaseParameter {

	@ApiModel(value = "DaIotMonitorDataAddList", description = "批量添加DaIotMonitorData所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIotMonitorData> entityList = new ArrayList<DaIotMonitorData>();

		public ArrayList<DaIotMonitorData> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIotMonitorData> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIotMonitorDataDeleteByIdList", description = "批量删除DaIotMonitorData所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIotMonitorDataUpdateList", description = "批量修改DaIotMonitorData所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIotMonitorData entity = new DaIotMonitorData();

		public DaIotMonitorData getEntity() {
			return entity;
		}

		public void setEntity(DaIotMonitorData entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIotMonitorDataGetList", description = "模糊查询DaIotMonitorData所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIotMonitorDataGetListEntityRelated", description = "模糊查询DaIotMonitorData所需的参数，实体类相关。")
		public static class EntityRelated extends DaIotMonitorDataGenEnt{
        
        @ApiModelProperty(value="时间查询起止时间。",required=false)
		private Date beginTime;

        @ApiModelProperty(value="时间查询结束时间。",required=false)
		private Date endTime;

		public Date getBeginTime() {
			return this.beginTime;
		}
        
		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}
        
        public Date getEndTime() {
			return this.endTime;
		}
        
		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
        
		}
	}

}







