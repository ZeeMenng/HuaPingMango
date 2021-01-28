package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaCommonField;
import com.zee.ent.generate.da.DaCommonFieldGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaCommonFieldParameter，方法参数，自动生成。通用字段表
 */

public class DaCommonFieldParameter extends BaseParameter {

	@ApiModel(value = "DaCommonFieldAddList", description = "批量添加DaCommonField所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaCommonField> entityList = new ArrayList<DaCommonField>();

		public ArrayList<DaCommonField> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaCommonField> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaCommonFieldDeleteByIdList", description = "批量删除DaCommonField所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaCommonFieldUpdateList", description = "批量修改DaCommonField所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaCommonField entity = new DaCommonField();

		public DaCommonField getEntity() {
			return entity;
		}

		public void setEntity(DaCommonField entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaCommonFieldGetList", description = "模糊查询DaCommonField所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaCommonFieldGetListEntityRelated", description = "模糊查询DaCommonField所需的参数，实体类相关。")
		public static class EntityRelated extends DaCommonFieldGenEnt{
        
        @ApiModelProperty(value="记录创建时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="记录创建时间查询结束时间。",required=false)
		private Date endAddTime;

        @ApiModelProperty(value="审核时间查询起止时间。",required=false)
		private Date beginAuditerTime;

        @ApiModelProperty(value="审核时间查询结束时间。",required=false)
		private Date endAuditerTime;

        @ApiModelProperty(value="结束时间查询起止时间。",required=false)
		private Date beginEndTime;

        @ApiModelProperty(value="结束时间查询结束时间。",required=false)
		private Date endEndTime;

        @ApiModelProperty(value="开始时间查询起止时间。",required=false)
		private Date beginStartTime;

        @ApiModelProperty(value="开始时间查询结束时间。",required=false)
		private Date endStartTime;

        @ApiModelProperty(value="记录最后一次修改时间查询起止时间。",required=false)
		private Date beginUpdateTime;

        @ApiModelProperty(value="记录最后一次修改时间查询结束时间。",required=false)
		private Date endUpdateTime;

		public Date getBeginAddTime() {
			return this.beginAddTime;
		}
        
		public void setBeginAddTime(Date beginAddTime) {
			this.beginAddTime = beginAddTime;
		}
        
        public Date getEndAddTime() {
			return this.endAddTime;
		}
        
		public void setEndAddTime(Date endAddTime) {
			this.endAddTime = endAddTime;
		}
        
		public Date getBeginAuditerTime() {
			return this.beginAuditerTime;
		}
        
		public void setBeginAuditerTime(Date beginAuditerTime) {
			this.beginAuditerTime = beginAuditerTime;
		}
        
        public Date getEndAuditerTime() {
			return this.endAuditerTime;
		}
        
		public void setEndAuditerTime(Date endAuditerTime) {
			this.endAuditerTime = endAuditerTime;
		}
        
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







