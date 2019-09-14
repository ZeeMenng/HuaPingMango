package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaTemplateData;
import com.jusfoun.ent.generate.da.DaTemplateDataGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaTemplateDataParameter，方法参数，自动生成。模板数据表
 */

public class DaTemplateDataParameter extends BaseParameter {

	@ApiModel(value = "DaTemplateDataAddList", description = "批量添加DaTemplateData所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaTemplateData> entityList = new ArrayList<DaTemplateData>();

		public ArrayList<DaTemplateData> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaTemplateData> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaTemplateDataDeleteByIdList", description = "批量删除DaTemplateData所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaTemplateDataUpdateList", description = "批量修改DaTemplateData所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaTemplateData entity = new DaTemplateData();

		public DaTemplateData getEntity() {
			return entity;
		}

		public void setEntity(DaTemplateData entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaTemplateDataGetList", description = "模糊查询DaTemplateData所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaTemplateDataGetListEntityRelated", description = "模糊查询DaTemplateData所需的参数，实体类相关。")
		public static class EntityRelated extends DaTemplateDataGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endAddTime;

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
        
		}
	}

}







