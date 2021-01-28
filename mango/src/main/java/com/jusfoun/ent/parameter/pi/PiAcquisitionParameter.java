package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiAcquisition;
import com.zee.ent.generate.pi.PiAcquisitionGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiAcquisitionParameter，方法参数，自动生成。CMS采集任务表
 */

public class PiAcquisitionParameter extends BaseParameter {

	@ApiModel(value = "PiAcquisitionAddList", description = "批量添加PiAcquisition所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiAcquisition> entityList = new ArrayList<PiAcquisition>();

		public ArrayList<PiAcquisition> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiAcquisition> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiAcquisitionDeleteByIdList", description = "批量删除PiAcquisition所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiAcquisitionUpdateList", description = "批量修改PiAcquisition所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiAcquisition entity = new PiAcquisition();

		public PiAcquisition getEntity() {
			return entity;
		}

		public void setEntity(PiAcquisition entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiAcquisitionGetList", description = "模糊查询PiAcquisition所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiAcquisitionGetListEntityRelated", description = "模糊查询PiAcquisition所需的参数，实体类相关。")
		public static class EntityRelated extends PiAcquisitionGenEnt{
        
        @ApiModelProperty(value="停止时间查询起止时间。",required=false)
		private Date beginEndTime;

        @ApiModelProperty(value="停止时间查询结束时间。",required=false)
		private Date endEndTime;

        @ApiModelProperty(value="开始时间查询起止时间。",required=false)
		private Date beginStartTime;

        @ApiModelProperty(value="开始时间查询结束时间。",required=false)
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







