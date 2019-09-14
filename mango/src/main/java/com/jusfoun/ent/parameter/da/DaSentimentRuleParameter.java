package com.jusfoun.ent.parameter.da;

import java.util.*;

import com.jusfoun.ent.extend.da.DaSentimentRule;
import com.jusfoun.ent.generate.da.DaSentimentRuleGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-7-22 16:33:32
 * @description 实体类DaSentimentRuleParameter，方法参数，自动生成。舆情采集规则表
 */

public class DaSentimentRuleParameter extends BaseParameter {

	@ApiModel(value = "DaSentimentRuleAddList", description = "批量添加DaSentimentRule所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaSentimentRule> entityList = new ArrayList<DaSentimentRule>();

		public ArrayList<DaSentimentRule> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaSentimentRule> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaSentimentRuleDeleteByIdList", description = "批量删除DaSentimentRule所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaSentimentRuleUpdateList", description = "批量修改DaSentimentRule所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaSentimentRule entity = new DaSentimentRule();

		public DaSentimentRule getEntity() {
			return entity;
		}

		public void setEntity(DaSentimentRule entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaSentimentRuleGetList", description = "模糊查询DaSentimentRule所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaSentimentRuleGetListEntityRelated", description = "模糊查询DaSentimentRule所需的参数，实体类相关。")
		public static class EntityRelated extends DaSentimentRuleGenEnt{
        
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







