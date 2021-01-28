package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiContentCheck;
import com.zee.ent.generate.pi.PiContentCheckGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiContentCheckParameter，方法参数，自动生成。CMS内容审核信息表
 */

public class PiContentCheckParameter extends BaseParameter {

	@ApiModel(value = "PiContentCheckAddList", description = "批量添加PiContentCheck所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiContentCheck> entityList = new ArrayList<PiContentCheck>();

		public ArrayList<PiContentCheck> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiContentCheck> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiContentCheckDeleteByIdList", description = "批量删除PiContentCheck所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiContentCheckUpdateList", description = "批量修改PiContentCheck所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiContentCheck entity = new PiContentCheck();

		public PiContentCheck getEntity() {
			return entity;
		}

		public void setEntity(PiContentCheck entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiContentCheckGetList", description = "模糊查询PiContentCheck所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiContentCheckGetListEntityRelated", description = "模糊查询PiContentCheck所需的参数，实体类相关。")
		public static class EntityRelated extends PiContentCheckGenEnt{
        
        @ApiModelProperty(value="终审时间查询起止时间。",required=false)
		private Date beginCheckDate;

        @ApiModelProperty(value="终审时间查询结束时间。",required=false)
		private Date endCheckDate;

		public Date getBeginCheckDate() {
			return this.beginCheckDate;
		}
        
		public void setBeginCheckDate(Date beginCheckDate) {
			this.beginCheckDate = beginCheckDate;
		}
        
        public Date getEndCheckDate() {
			return this.endCheckDate;
		}
        
		public void setEndCheckDate(Date endCheckDate) {
			this.endCheckDate = endCheckDate;
		}
        
		}
	}

}







