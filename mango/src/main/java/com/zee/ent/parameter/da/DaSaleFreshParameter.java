package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaSaleFresh;
import com.zee.ent.generate.da.DaSaleFreshGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaSaleFreshParameter，方法参数，自动生成。鲜果销售数据表
 */

public class DaSaleFreshParameter extends BaseParameter {

	@ApiModel(value = "DaSaleFreshAddList", description = "批量添加DaSaleFresh所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSaleFresh> entityList = new ArrayList<DaSaleFresh>();

		public ArrayList<DaSaleFresh> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSaleFresh> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSaleFreshDeleteByIdList", description = "批量删除DaSaleFresh所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSaleFreshUpdateList", description = "批量修改DaSaleFresh所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSaleFresh entity = new DaSaleFresh();

		public DaSaleFresh getEntity() {
			return entity;
		}

		public void setEntity(DaSaleFresh entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSaleFreshGetList", description = "模糊查询DaSaleFresh所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSaleFreshGetListEntityRelated", description = "模糊查询DaSaleFresh所需的参数，实体类相关。")
		public static class EntityRelated extends DaSaleFreshGenEnt{
        
        @ApiModelProperty(value="交易日期查询起止时间。",required=false)
		private Date beginTradeTime;

        @ApiModelProperty(value="交易日期查询结束时间。",required=false)
		private Date endTradeTime;

		public Date getBeginTradeTime() {
			return this.beginTradeTime;
		}
        
		public void setBeginTradeTime(Date beginTradeTime) {
			this.beginTradeTime = beginTradeTime;
		}
        
        public Date getEndTradeTime() {
			return this.endTradeTime;
		}
        
		public void setEndTradeTime(Date endTradeTime) {
			this.endTradeTime = endTradeTime;
		}
        
		}
	}

}







