package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEnterpriseInfo;
import com.jusfoun.ent.generate.da.DaEnterpriseInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-9-19 11:30:20
 * @description 实体类DaEnterpriseInfoParameter，方法参数，自动生成。企业基本信息表
 */

public class DaEnterpriseInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseInfoAddList", description = "批量添加DaEnterpriseInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseInfo> entityList = new ArrayList<DaEnterpriseInfo>();

		public ArrayList<DaEnterpriseInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseInfoDeleteByIdList", description = "批量删除DaEnterpriseInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseInfoUpdateList", description = "批量修改DaEnterpriseInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseInfo entity = new DaEnterpriseInfo();

		public DaEnterpriseInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseInfoGetList", description = "模糊查询DaEnterpriseInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseInfoGetListEntityRelated", description = "模糊查询DaEnterpriseInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseInfoGenEnt{
        
        @ApiModelProperty(value="核准日期查询起止时间。",required=false)
		private Date beginApprovalDate;

        @ApiModelProperty(value="核准日期查询结束时间。",required=false)
		private Date endApprovalDate;

        @ApiModelProperty(value="营业期限查询起止时间。",required=false)
		private Date beginBusinessDuetime;

        @ApiModelProperty(value="营业期限查询结束时间。",required=false)
		private Date endBusinessDuetime;

        @ApiModelProperty(value="成立日期查询起止时间。",required=false)
		private Date beginEstablishDate;

        @ApiModelProperty(value="成立日期查询结束时间。",required=false)
		private Date endEstablishDate;

        @ApiModelProperty(value="成立时间查询起止时间。",required=false)
		private Date beginFoundDate;

        @ApiModelProperty(value="成立时间查询结束时间。",required=false)
		private Date endFoundDate;

		public Date getBeginApprovalDate() {
			return this.beginApprovalDate;
		}
        
		public void setBeginApprovalDate(Date beginApprovalDate) {
			this.beginApprovalDate = beginApprovalDate;
		}
        
        public Date getEndApprovalDate() {
			return this.endApprovalDate;
		}
        
		public void setEndApprovalDate(Date endApprovalDate) {
			this.endApprovalDate = endApprovalDate;
		}
        
		public Date getBeginBusinessDuetime() {
			return this.beginBusinessDuetime;
		}
        
		public void setBeginBusinessDuetime(Date beginBusinessDuetime) {
			this.beginBusinessDuetime = beginBusinessDuetime;
		}
        
        public Date getEndBusinessDuetime() {
			return this.endBusinessDuetime;
		}
        
		public void setEndBusinessDuetime(Date endBusinessDuetime) {
			this.endBusinessDuetime = endBusinessDuetime;
		}
        
		public Date getBeginEstablishDate() {
			return this.beginEstablishDate;
		}
        
		public void setBeginEstablishDate(Date beginEstablishDate) {
			this.beginEstablishDate = beginEstablishDate;
		}
        
        public Date getEndEstablishDate() {
			return this.endEstablishDate;
		}
        
		public void setEndEstablishDate(Date endEstablishDate) {
			this.endEstablishDate = endEstablishDate;
		}
        
		public Date getBeginFoundDate() {
			return this.beginFoundDate;
		}
        
		public void setBeginFoundDate(Date beginFoundDate) {
			this.beginFoundDate = beginFoundDate;
		}
        
        public Date getEndFoundDate() {
			return this.endFoundDate;
		}
        
		public void setEndFoundDate(Date endFoundDate) {
			this.endFoundDate = endFoundDate;
		}
        
		}
	}

}







