package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaSaleChannel;
import com.zee.ent.generate.da.DaSaleChannelGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaSaleChannelParameter，方法参数，自动生成。销售渠道数据表
 */

public class DaSaleChannelParameter extends BaseParameter {

	@ApiModel(value = "DaSaleChannelAddList", description = "批量添加DaSaleChannel所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSaleChannel> entityList = new ArrayList<DaSaleChannel>();

		public ArrayList<DaSaleChannel> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSaleChannel> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSaleChannelDeleteByIdList", description = "批量删除DaSaleChannel所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSaleChannelUpdateList", description = "批量修改DaSaleChannel所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSaleChannel entity = new DaSaleChannel();

		public DaSaleChannel getEntity() {
			return entity;
		}

		public void setEntity(DaSaleChannel entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSaleChannelGetList", description = "模糊查询DaSaleChannel所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSaleChannelGetListEntityRelated", description = "模糊查询DaSaleChannel所需的参数，实体类相关。")
		public static class EntityRelated extends DaSaleChannelGenEnt{
        
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







