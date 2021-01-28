package com.zee.ent.parameter.pi;

import java.util.*;

import com.zee.ent.extend.pi.PiUserFavorite;
import com.zee.ent.generate.pi.PiUserFavoriteGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-15 13:54:46
 * @description 实体类PiUserFavoriteParameter，方法参数，自动生成。pi_user_favorite
 */

public class PiUserFavoriteParameter extends BaseParameter {

	@ApiModel(value = "PiUserFavoriteAddList", description = "批量添加PiUserFavorite所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiUserFavorite> entityList = new ArrayList<PiUserFavorite>();

		public ArrayList<PiUserFavorite> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiUserFavorite> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiUserFavoriteDeleteByIdList", description = "批量删除PiUserFavorite所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiUserFavoriteUpdateList", description = "批量修改PiUserFavorite所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiUserFavorite entity = new PiUserFavorite();

		public PiUserFavorite getEntity() {
			return entity;
		}

		public void setEntity(PiUserFavorite entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiUserFavoriteGetList", description = "模糊查询PiUserFavorite所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiUserFavoriteGetListEntityRelated", description = "模糊查询PiUserFavorite所需的参数，实体类相关。")
		public static class EntityRelated extends PiUserFavoriteGenEnt{
        
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







