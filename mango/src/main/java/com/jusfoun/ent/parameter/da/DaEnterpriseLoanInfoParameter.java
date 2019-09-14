package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaEnterpriseLoanInfo;
import com.jusfoun.ent.generate.da.DaEnterpriseLoanInfoGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaEnterpriseLoanInfoParameter，方法参数，自动生成。企业贷款记录表
 */

public class DaEnterpriseLoanInfoParameter extends BaseParameter {

	@ApiModel(value = "DaEnterpriseLoanInfoAddList", description = "批量添加DaEnterpriseLoanInfo所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaEnterpriseLoanInfo> entityList = new ArrayList<DaEnterpriseLoanInfo>();

		public ArrayList<DaEnterpriseLoanInfo> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaEnterpriseLoanInfo> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaEnterpriseLoanInfoDeleteByIdList", description = "批量删除DaEnterpriseLoanInfo所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaEnterpriseLoanInfoUpdateList", description = "批量修改DaEnterpriseLoanInfo所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaEnterpriseLoanInfo entity = new DaEnterpriseLoanInfo();

		public DaEnterpriseLoanInfo getEntity() {
			return entity;
		}

		public void setEntity(DaEnterpriseLoanInfo entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaEnterpriseLoanInfoGetList", description = "模糊查询DaEnterpriseLoanInfo所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaEnterpriseLoanInfoGetListEntityRelated", description = "模糊查询DaEnterpriseLoanInfo所需的参数，实体类相关。")
		public static class EntityRelated extends DaEnterpriseLoanInfoGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreatedTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreatedTime;

        @ApiModelProperty(value="贷款时间查询起止时间。",required=false)
		private Date beginLoanDate;

        @ApiModelProperty(value="贷款时间查询结束时间。",required=false)
		private Date endLoanDate;

		public Date getBeginCreatedTime() {
			return this.beginCreatedTime;
		}
        
		public void setBeginCreatedTime(Date beginCreatedTime) {
			this.beginCreatedTime = beginCreatedTime;
		}
        
        public Date getEndCreatedTime() {
			return this.endCreatedTime;
		}
        
		public void setEndCreatedTime(Date endCreatedTime) {
			this.endCreatedTime = endCreatedTime;
		}
        
		public Date getBeginLoanDate() {
			return this.beginLoanDate;
		}
        
		public void setBeginLoanDate(Date beginLoanDate) {
			this.beginLoanDate = beginLoanDate;
		}
        
        public Date getEndLoanDate() {
			return this.endLoanDate;
		}
        
		public void setEndLoanDate(Date endLoanDate) {
			this.endLoanDate = endLoanDate;
		}
        
		}
	}

}







