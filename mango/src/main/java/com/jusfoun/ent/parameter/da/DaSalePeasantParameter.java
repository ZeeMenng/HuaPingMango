package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaSalePeasant;
import com.jusfoun.ent.generate.da.DaSalePeasantGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaSalePeasantParameter，方法参数，自动生成。农户销售数据表
 */

public class DaSalePeasantParameter extends BaseParameter {

	@ApiModel(value = "DaSalePeasantAddList", description = "批量添加DaSalePeasant所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSalePeasant> entityList = new ArrayList<DaSalePeasant>();

		public ArrayList<DaSalePeasant> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSalePeasant> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSalePeasantDeleteByIdList", description = "批量删除DaSalePeasant所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSalePeasantUpdateList", description = "批量修改DaSalePeasant所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSalePeasant entity = new DaSalePeasant();

		public DaSalePeasant getEntity() {
			return entity;
		}

		public void setEntity(DaSalePeasant entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSalePeasantGetList", description = "模糊查询DaSalePeasant所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSalePeasantGetListEntityRelated", description = "模糊查询DaSalePeasant所需的参数，实体类相关。")
		public static class EntityRelated extends DaSalePeasantGenEnt{
        
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







