package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaIotMonitorBasic;
import com.jusfoun.ent.generate.da.DaIotMonitorBasicGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaIotMonitorBasicParameter，方法参数，自动生成。物联网设备基本信息
 */

public class DaIotMonitorBasicParameter extends BaseParameter {

	@ApiModel(value = "DaIotMonitorBasicAddList", description = "批量添加DaIotMonitorBasic所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaIotMonitorBasic> entityList = new ArrayList<DaIotMonitorBasic>();

		public ArrayList<DaIotMonitorBasic> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaIotMonitorBasic> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaIotMonitorBasicDeleteByIdList", description = "批量删除DaIotMonitorBasic所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaIotMonitorBasicUpdateList", description = "批量修改DaIotMonitorBasic所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaIotMonitorBasic entity = new DaIotMonitorBasic();

		public DaIotMonitorBasic getEntity() {
			return entity;
		}

		public void setEntity(DaIotMonitorBasic entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaIotMonitorBasicGetList", description = "模糊查询DaIotMonitorBasic所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaIotMonitorBasicGetListEntityRelated", description = "模糊查询DaIotMonitorBasic所需的参数，实体类相关。")
		public static class EntityRelated extends DaIotMonitorBasicGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreateTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreateTime;

        @ApiModelProperty(value="更新时间查询起止时间。",required=false)
		private Date beginUpdateTime;

        @ApiModelProperty(value="更新时间查询结束时间。",required=false)
		private Date endUpdateTime;

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
        
		public Date getBeginUpdateTime() {
			return this.beginUpdateTime;
		}
        
		public void setBeginUpdateTime(Date beginUpdateTime) {
			this.beginUpdateTime = beginUpdateTime;
		}
        
        public Date getEndUpdateTime() {
			return this.endUpdateTime;
		}
        
		public void setEndUpdateTime(Date endUpdateTime) {
			this.endUpdateTime = endUpdateTime;
		}
        
		}
	}

}







