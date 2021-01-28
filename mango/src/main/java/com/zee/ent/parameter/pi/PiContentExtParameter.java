package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiContentExt;
import com.zee.ent.generate.pi.PiContentExtGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentExtParameter，方法参数，自动生成。CMS内容扩展表
 */

public class PiContentExtParameter extends BaseParameter {

	@ApiModel(value = "PiContentExtAddList", description = "批量添加PiContentExt所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentExt> entityList = new ArrayList<PiContentExt>();

		public ArrayList<PiContentExt> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentExt> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentExtDeleteByIdList", description = "批量删除PiContentExt所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentExtUpdateList", description = "批量修改PiContentExt所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentExt entity = new PiContentExt();

		public PiContentExt getEntity() {
			return entity;
		}

		public void setEntity(PiContentExt entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentExtGetList", description = "模糊查询PiContentExt所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentExtGetListEntityRelated", description = "模糊查询PiContentExt所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentExtGenEnt{
        
        @ApiModelProperty(value="发布日期查询起止时间。",required=false)
		private Date beginReleaseDate;

        @ApiModelProperty(value="发布日期查询结束时间。",required=false)
		private Date endReleaseDate;

		public Date getBeginReleaseDate() {
			return this.beginReleaseDate;
		}
        
		public void setBeginReleaseDate(Date beginReleaseDate) {
			this.beginReleaseDate = beginReleaseDate;
		}
        
        public Date getEndReleaseDate() {
			return this.endReleaseDate;
		}
        
		public void setEndReleaseDate(Date endReleaseDate) {
			this.endReleaseDate = endReleaseDate;
		}
        
		}
	}

}







