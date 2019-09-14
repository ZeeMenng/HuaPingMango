package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiInteraction;
import com.jusfoun.ent.generate.pi.PiInteractionGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-5 11:35:44
 * @description 实体类PiInteractionParameter，方法参数，自动生成。pi_interaction
 */

public class PiInteractionParameter extends BaseParameter {

	@ApiModel(value = "PiInteractionAddList", description = "批量添加PiInteraction所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiInteraction> entityList = new ArrayList<PiInteraction>();

		public ArrayList<PiInteraction> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiInteraction> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiInteractionDeleteByIdList", description = "批量删除PiInteraction所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiInteractionUpdateList", description = "批量修改PiInteraction所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiInteraction entity = new PiInteraction();

		public PiInteraction getEntity() {
			return entity;
		}

		public void setEntity(PiInteraction entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiInteractionGetList", description = "模糊查询PiInteraction所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiInteractionGetListEntityRelated", description = "模糊查询PiInteraction所需的参数，实体类相关。")
		public static class EntityRelated extends PiInteractionGenEnt{
        
        @ApiModelProperty(value="记录创建时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="记录创建时间查询结束时间。",required=false)
		private Date endAddTime;

        @ApiModelProperty(value="审核时间查询起止时间。",required=false)
		private Date beginAuditerTime;

        @ApiModelProperty(value="审核时间查询结束时间。",required=false)
		private Date endAuditerTime;

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







