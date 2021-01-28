package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaSaleEcommerceOrder;
import com.zee.ent.generate.da.DaSaleEcommerceOrderGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaSaleEcommerceOrderParameter，方法参数，自动生成。电商数据表，用于存放直报的电商数据
 */

public class DaSaleEcommerceOrderParameter extends BaseParameter {

	@ApiModel(value = "DaSaleEcommerceOrderAddList", description = "批量添加DaSaleEcommerceOrder所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSaleEcommerceOrder> entityList = new ArrayList<DaSaleEcommerceOrder>();

		public ArrayList<DaSaleEcommerceOrder> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSaleEcommerceOrder> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSaleEcommerceOrderDeleteByIdList", description = "批量删除DaSaleEcommerceOrder所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSaleEcommerceOrderUpdateList", description = "批量修改DaSaleEcommerceOrder所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSaleEcommerceOrder entity = new DaSaleEcommerceOrder();

		public DaSaleEcommerceOrder getEntity() {
			return entity;
		}

		public void setEntity(DaSaleEcommerceOrder entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSaleEcommerceOrderGetList", description = "模糊查询DaSaleEcommerceOrder所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSaleEcommerceOrderGetListEntityRelated", description = "模糊查询DaSaleEcommerceOrder所需的参数，实体类相关。")
		public static class EntityRelated extends DaSaleEcommerceOrderGenEnt{
        
        @ApiModelProperty(value="订单日期查询起止时间。",required=false)
		private Date beginOrderTime;

        @ApiModelProperty(value="订单日期查询结束时间。",required=false)
		private Date endOrderTime;

		public Date getBeginOrderTime() {
			return this.beginOrderTime;
		}
        
		public void setBeginOrderTime(Date beginOrderTime) {
			this.beginOrderTime = beginOrderTime;
		}
        
        public Date getEndOrderTime() {
			return this.endOrderTime;
		}
        
		public void setEndOrderTime(Date endOrderTime) {
			this.endOrderTime = endOrderTime;
		}
        
		}
	}

}







