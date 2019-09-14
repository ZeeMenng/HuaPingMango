package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiContent;
import com.jusfoun.ent.generate.pi.PiContentGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentParameter，方法参数，自动生成。CMS内容表
 */

public class PiContentParameter extends BaseParameter {

	@ApiModel(value = "PiContentAddList", description = "批量添加PiContent所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContent> entityList = new ArrayList<PiContent>();

		public ArrayList<PiContent> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContent> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentDeleteByIdList", description = "批量删除PiContent所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentUpdateList", description = "批量修改PiContent所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContent entity = new PiContent();

		public PiContent getEntity() {
			return entity;
		}

		public void setEntity(PiContent entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentGetList", description = "模糊查询PiContent所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentGetListEntityRelated", description = "模糊查询PiContent所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentGenEnt{
        
        @ApiModelProperty(value="排序日期查询起止时间。",required=false)
		private Date beginSortDate;

        @ApiModelProperty(value="排序日期查询结束时间。",required=false)
		private Date endSortDate;

		public Date getBeginSortDate() {
			return this.beginSortDate;
		}
        
		public void setBeginSortDate(Date beginSortDate) {
			this.beginSortDate = beginSortDate;
		}
        
        public Date getEndSortDate() {
			return this.endSortDate;
		}
        
		public void setEndSortDate(Date endSortDate) {
			this.endSortDate = endSortDate;
		}
        
		}
	}

}







