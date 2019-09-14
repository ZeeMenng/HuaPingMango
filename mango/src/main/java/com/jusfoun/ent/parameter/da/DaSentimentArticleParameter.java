package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaSentimentArticle;
import com.jusfoun.ent.generate.da.DaSentimentArticleGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-25 10:44:14
 * @description 实体类DaSentimentArticleParameter，方法参数，自动生成。舆情文章表
 */

public class DaSentimentArticleParameter extends BaseParameter {

	@ApiModel(value = "DaSentimentArticleAddList", description = "批量添加DaSentimentArticle所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSentimentArticle> entityList = new ArrayList<DaSentimentArticle>();

		public ArrayList<DaSentimentArticle> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSentimentArticle> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSentimentArticleDeleteByIdList", description = "批量删除DaSentimentArticle所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSentimentArticleUpdateList", description = "批量修改DaSentimentArticle所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSentimentArticle entity = new DaSentimentArticle();

		public DaSentimentArticle getEntity() {
			return entity;
		}

		public void setEntity(DaSentimentArticle entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSentimentArticleGetList", description = "模糊查询DaSentimentArticle所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSentimentArticleGetListEntityRelated", description = "模糊查询DaSentimentArticle所需的参数，实体类相关。")
		public static class EntityRelated extends DaSentimentArticleGenEnt{
        
        @ApiModelProperty(value="增加时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="增加时间查询结束时间。",required=false)
		private Date endAddTime;

        @ApiModelProperty(value="文章发布时间查询起止时间。",required=false)
		private Date beginPublishTime;

        @ApiModelProperty(value="文章发布时间查询结束时间。",required=false)
		private Date endPublishTime;

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
        
		public Date getBeginPublishTime() {
			return this.beginPublishTime;
		}
        
		public void setBeginPublishTime(Date beginPublishTime) {
			this.beginPublishTime = beginPublishTime;
		}
        
        public Date getEndPublishTime() {
			return this.endPublishTime;
		}
        
		public void setEndPublishTime(Date endPublishTime) {
			this.endPublishTime = endPublishTime;
		}
        
		}
	}

}







