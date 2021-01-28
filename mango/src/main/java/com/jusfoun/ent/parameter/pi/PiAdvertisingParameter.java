package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiAdvertising;
import com.zee.ent.generate.pi.PiAdvertisingGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/7/12 14:46:55
 * @description 实体类PiAdvertisingParameter，方法参数，自动生成。CMS广告表
 */

public class PiAdvertisingParameter extends BaseParameter {

	@ApiModel(value = "PiAdvertisingAddList", description = "批量添加PiAdvertising所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiAdvertising> entityList = new ArrayList<PiAdvertising>();

		public ArrayList<PiAdvertising> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiAdvertising> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiAdvertisingDeleteByIdList", description = "批量删除PiAdvertising所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiAdvertisingUpdateList", description = "批量修改PiAdvertising所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiAdvertising entity = new PiAdvertising();

		public PiAdvertising getEntity() {
			return entity;
		}

		public void setEntity(PiAdvertising entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiAdvertisingGetList", description = "模糊查询PiAdvertising所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiAdvertisingGetListEntityRelated", description = "模糊查询PiAdvertising所需的参数，实体类相关。")
		public static class EntityRelated extends PiAdvertisingGenEnt{
        
        @ApiModelProperty(value="结束时间。查询起止时间。",required=false)
		private Date beginEndTime;

        @ApiModelProperty(value="结束时间。查询结束时间。",required=false)
		private Date endEndTime;

        @ApiModelProperty(value="开始时间。查询起止时间。",required=false)
		private Date beginStartTime;

        @ApiModelProperty(value="开始时间。查询结束时间。",required=false)
		private Date endStartTime;

		public Date getBeginEndTime() {
			return this.beginEndTime;
		}
        
		public void setBeginEndTime(Date beginEndTime) {
			this.beginEndTime = beginEndTime;
		}
        
        public Date getEndEndTime() {
			return this.endEndTime;
		}
        
		public void setEndEndTime(Date endEndTime) {
			this.endEndTime = endEndTime;
		}
        
		public Date getBeginStartTime() {
			return this.beginStartTime;
		}
        
		public void setBeginStartTime(Date beginStartTime) {
			this.beginStartTime = beginStartTime;
		}
        
        public Date getEndStartTime() {
			return this.endStartTime;
		}
        
		public void setEndStartTime(Date endStartTime) {
			this.endStartTime = endStartTime;
		}
        
		}
	}

}







