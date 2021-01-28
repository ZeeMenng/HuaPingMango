package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaBaseDetail;
import com.zee.ent.generate.da.DaBaseDetailGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-21 11:38:36
 * @description 实体类DaBaseDetailParameter，方法参数，自动生成。基地细分表-地块表
 */

public class DaBaseDetailParameter extends BaseParameter {

	@ApiModel(value = "DaBaseDetailAddList", description = "批量添加DaBaseDetail所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaBaseDetail> entityList = new ArrayList<DaBaseDetail>();

		public ArrayList<DaBaseDetail> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaBaseDetail> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaBaseDetailDeleteByIdList", description = "批量删除DaBaseDetail所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaBaseDetailUpdateList", description = "批量修改DaBaseDetail所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaBaseDetail entity = new DaBaseDetail();

		public DaBaseDetail getEntity() {
			return entity;
		}

		public void setEntity(DaBaseDetail entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaBaseDetailGetList", description = "模糊查询DaBaseDetail所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaBaseDetailGetListEntityRelated", description = "模糊查询DaBaseDetail所需的参数，实体类相关。")
		public static class EntityRelated extends DaBaseDetailGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreateDate;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreateDate;

        @ApiModelProperty(value="更新时间查询起止时间。",required=false)
		private Date beginUpdateDate;

        @ApiModelProperty(value="更新时间查询结束时间。",required=false)
		private Date endUpdateDate;

		public Date getBeginCreateDate() {
			return this.beginCreateDate;
		}
        
		public void setBeginCreateDate(Date beginCreateDate) {
			this.beginCreateDate = beginCreateDate;
		}
        
        public Date getEndCreateDate() {
			return this.endCreateDate;
		}
        
		public void setEndCreateDate(Date endCreateDate) {
			this.endCreateDate = endCreateDate;
		}
        
		public Date getBeginUpdateDate() {
			return this.beginUpdateDate;
		}
        
		public void setBeginUpdateDate(Date beginUpdateDate) {
			this.beginUpdateDate = beginUpdateDate;
		}
        
        public Date getEndUpdateDate() {
			return this.endUpdateDate;
		}
        
		public void setEndUpdateDate(Date endUpdateDate) {
			this.endUpdateDate = endUpdateDate;
		}
        
		}
	}

}







