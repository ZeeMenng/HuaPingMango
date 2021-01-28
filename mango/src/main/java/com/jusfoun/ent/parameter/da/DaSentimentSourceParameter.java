package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaSentimentSource;
import com.zee.ent.generate.da.DaSentimentSourceGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-19 11:37:09
 * @description 实体类DaSentimentSourceParameter，方法参数，自动生成。舆情数据源表
 */

public class DaSentimentSourceParameter extends BaseParameter {

	@ApiModel(value = "DaSentimentSourceAddList", description = "批量添加DaSentimentSource所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSentimentSource> entityList = new ArrayList<DaSentimentSource>();

		public ArrayList<DaSentimentSource> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSentimentSource> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSentimentSourceDeleteByIdList", description = "批量删除DaSentimentSource所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSentimentSourceUpdateList", description = "批量修改DaSentimentSource所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSentimentSource entity = new DaSentimentSource();

		public DaSentimentSource getEntity() {
			return entity;
		}

		public void setEntity(DaSentimentSource entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSentimentSourceGetList", description = "模糊查询DaSentimentSource所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSentimentSourceGetListEntityRelated", description = "模糊查询DaSentimentSource所需的参数，实体类相关。")
		public static class EntityRelated extends DaSentimentSourceGenEnt{
        
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







