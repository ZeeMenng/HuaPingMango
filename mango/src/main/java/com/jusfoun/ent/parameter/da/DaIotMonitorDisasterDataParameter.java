package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaIotMonitorDisasterData;
import com.jusfoun.ent.generate.da.DaIotMonitorDisasterDataGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIotMonitorDisasterDataParameter，方法参数，自动生成。da_iot_monitor_data表中满足预警条件的数据
 */

public class DaIotMonitorDisasterDataParameter extends BaseParameter {

	@ApiModel(value = "DaIotMonitorDisasterDataAddList", description = "批量添加DaIotMonitorDisasterData所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIotMonitorDisasterData> entityList = new ArrayList<DaIotMonitorDisasterData>();

		public ArrayList<DaIotMonitorDisasterData> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIotMonitorDisasterData> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIotMonitorDisasterDataDeleteByIdList", description = "批量删除DaIotMonitorDisasterData所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIotMonitorDisasterDataUpdateList", description = "批量修改DaIotMonitorDisasterData所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIotMonitorDisasterData entity = new DaIotMonitorDisasterData();

		public DaIotMonitorDisasterData getEntity() {
			return entity;
		}

		public void setEntity(DaIotMonitorDisasterData entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIotMonitorDisasterDataGetList", description = "模糊查询DaIotMonitorDisasterData所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIotMonitorDisasterDataGetListEntityRelated", description = "模糊查询DaIotMonitorDisasterData所需的参数，实体类相关。")
		public static class EntityRelated extends DaIotMonitorDisasterDataGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreateTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreateTime;

		public Date getBeginCreateTime() {
			return this.beginCreateTime;
		}
        
		public void setBeginCreateTime(Date beginCreateTime) {
			this.beginCreateTime = beginCreateTime;
		}
        
        public Date getEndCreateTime() {
			return this.endCreateTime;
		}
        
		public void setEndCreateTime(Date endCreateTime) {
			this.endCreateTime = endCreateTime;
		}
        
		}
	}

}







