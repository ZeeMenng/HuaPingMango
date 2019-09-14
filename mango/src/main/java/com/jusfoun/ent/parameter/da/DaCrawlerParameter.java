package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaCrawler;
import com.jusfoun.ent.generate.da.DaCrawlerGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:23
 * @description 实体类DaCrawlerParameter，方法参数，自动生成。爬虫
 */

public class DaCrawlerParameter extends BaseParameter {

	@ApiModel(value = "DaCrawlerAddList", description = "批量添加DaCrawler所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaCrawler> entityList = new ArrayList<DaCrawler>();

		public ArrayList<DaCrawler> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaCrawler> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaCrawlerDeleteByIdList", description = "批量删除DaCrawler所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaCrawlerUpdateList", description = "批量修改DaCrawler所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaCrawler entity = new DaCrawler();

		public DaCrawler getEntity() {
			return entity;
		}

		public void setEntity(DaCrawler entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaCrawlerGetList", description = "模糊查询DaCrawler所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaCrawlerGetListEntityRelated", description = "模糊查询DaCrawler所需的参数，实体类相关。")
		public static class EntityRelated extends DaCrawlerGenEnt{
        
        @ApiModelProperty(value="创建时间查询起止时间。",required=false)
		private Date beginCreatedTime;

        @ApiModelProperty(value="创建时间查询结束时间。",required=false)
		private Date endCreatedTime;

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
        
		}
	}

}







