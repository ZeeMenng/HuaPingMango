package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiProductRecommend;
import com.zee.ent.generate.pi.PiProductRecommendGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-8-19 10:15:47
 * @description 实体类PiProductRecommendParameter，方法参数，自动生成。企业推介产品表
 */

public class PiProductRecommendParameter extends BaseParameter {

	@ApiModel(value = "PiProductRecommendAddList", description = "批量添加PiProductRecommend所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiProductRecommend> entityList = new ArrayList<PiProductRecommend>();

		public ArrayList<PiProductRecommend> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiProductRecommend> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiProductRecommendDeleteByIdList", description = "批量删除PiProductRecommend所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiProductRecommendUpdateList", description = "批量修改PiProductRecommend所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiProductRecommend entity = new PiProductRecommend();

		public PiProductRecommend getEntity() {
			return entity;
		}

		public void setEntity(PiProductRecommend entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiProductRecommendGetList", description = "模糊查询PiProductRecommend所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiProductRecommendGetListEntityRelated", description = "模糊查询PiProductRecommend所需的参数，实体类相关。")
		public static class EntityRelated extends PiProductRecommendGenEnt{
        
        @ApiModelProperty(value="数据入库时间查询起止时间。",required=false)
		private Date beginAddTime;

        @ApiModelProperty(value="数据入库时间查询结束时间。",required=false)
		private Date endAddTime;

        @ApiModelProperty(value="发布时间查询起止时间。",required=false)
		private Date beginReleaseTime;

        @ApiModelProperty(value="发布时间查询结束时间。",required=false)
		private Date endReleaseTime;

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
        
		public Date getBeginReleaseTime() {
			return this.beginReleaseTime;
		}
        
		public void setBeginReleaseTime(Date beginReleaseTime) {
			this.beginReleaseTime = beginReleaseTime;
		}
        
        public Date getEndReleaseTime() {
			return this.endReleaseTime;
		}
        
		public void setEndReleaseTime(Date endReleaseTime) {
			this.endReleaseTime = endReleaseTime;
		}
        
		}
	}

}







