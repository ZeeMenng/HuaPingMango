package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaIotMonitorQueryDate;
import com.zee.ent.generate.da.DaIotMonitorQueryDateGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIotMonitorQueryDateParameter，方法参数，自动生成。物联网数据最新查询时间表
 */

public class DaIotMonitorQueryDateParameter extends BaseParameter {

	@ApiModel(value = "DaIotMonitorQueryDateAddList", description = "批量添加DaIotMonitorQueryDate所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIotMonitorQueryDate> entityList = new ArrayList<DaIotMonitorQueryDate>();

		public ArrayList<DaIotMonitorQueryDate> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIotMonitorQueryDate> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIotMonitorQueryDateDeleteByIdList", description = "批量删除DaIotMonitorQueryDate所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIotMonitorQueryDateUpdateList", description = "批量修改DaIotMonitorQueryDate所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIotMonitorQueryDate entity = new DaIotMonitorQueryDate();

		public DaIotMonitorQueryDate getEntity() {
			return entity;
		}

		public void setEntity(DaIotMonitorQueryDate entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIotMonitorQueryDateGetList", description = "模糊查询DaIotMonitorQueryDate所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIotMonitorQueryDateGetListEntityRelated", description = "模糊查询DaIotMonitorQueryDate所需的参数，实体类相关。")
		public static class EntityRelated extends DaIotMonitorQueryDateGenEnt{
        
        @ApiModelProperty(value="查询起止时间。",required=false)
		private Date beginLastQueryDate;

        @ApiModelProperty(value="查询结束时间。",required=false)
		private Date endLastQueryDate;

		public Date getBeginLastQueryDate() {
			return this.beginLastQueryDate;
		}
        
		public void setBeginLastQueryDate(Date beginLastQueryDate) {
			this.beginLastQueryDate = beginLastQueryDate;
		}
        
        public Date getEndLastQueryDate() {
			return this.endLastQueryDate;
		}
        
		public void setEndLastQueryDate(Date endLastQueryDate) {
			this.endLastQueryDate = endLastQueryDate;
		}
        
		}
	}

}







