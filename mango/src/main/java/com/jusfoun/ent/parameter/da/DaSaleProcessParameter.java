package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaSaleProcess;
import com.zee.ent.generate.da.DaSaleProcessGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaSaleProcessParameter，方法参数，自动生成。加工销售数据表
 */

public class DaSaleProcessParameter extends BaseParameter {

	@ApiModel(value = "DaSaleProcessAddList", description = "批量添加DaSaleProcess所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSaleProcess> entityList = new ArrayList<DaSaleProcess>();

		public ArrayList<DaSaleProcess> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSaleProcess> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSaleProcessDeleteByIdList", description = "批量删除DaSaleProcess所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSaleProcessUpdateList", description = "批量修改DaSaleProcess所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSaleProcess entity = new DaSaleProcess();

		public DaSaleProcess getEntity() {
			return entity;
		}

		public void setEntity(DaSaleProcess entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSaleProcessGetList", description = "模糊查询DaSaleProcess所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSaleProcessGetListEntityRelated", description = "模糊查询DaSaleProcess所需的参数，实体类相关。")
		public static class EntityRelated extends DaSaleProcessGenEnt{
        
        @ApiModelProperty(value="销售日期查询起止时间。",required=false)
		private Date beginSaleTime;

        @ApiModelProperty(value="销售日期查询结束时间。",required=false)
		private Date endSaleTime;

		public Date getBeginSaleTime() {
			return this.beginSaleTime;
		}
        
		public void setBeginSaleTime(Date beginSaleTime) {
			this.beginSaleTime = beginSaleTime;
		}
        
        public Date getEndSaleTime() {
			return this.endSaleTime;
		}
        
		public void setEndSaleTime(Date endSaleTime) {
			this.endSaleTime = endSaleTime;
		}
        
		}
	}

}







