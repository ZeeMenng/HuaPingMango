package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaSentimentTheme;
import com.zee.ent.generate.da.DaSentimentThemeGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaSentimentThemeParameter，方法参数，自动生成。舆情主题名称
 */

public class DaSentimentThemeParameter extends BaseParameter {

	@ApiModel(value = "DaSentimentThemeAddList", description = "批量添加DaSentimentTheme所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSentimentTheme> entityList = new ArrayList<DaSentimentTheme>();

		public ArrayList<DaSentimentTheme> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSentimentTheme> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSentimentThemeDeleteByIdList", description = "批量删除DaSentimentTheme所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSentimentThemeUpdateList", description = "批量修改DaSentimentTheme所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSentimentTheme entity = new DaSentimentTheme();

		public DaSentimentTheme getEntity() {
			return entity;
		}

		public void setEntity(DaSentimentTheme entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSentimentThemeGetList", description = "模糊查询DaSentimentTheme所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSentimentThemeGetListEntityRelated", description = "模糊查询DaSentimentTheme所需的参数，实体类相关。")
		public static class EntityRelated extends DaSentimentThemeGenEnt{
        
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







