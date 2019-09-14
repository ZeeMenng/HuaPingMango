package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfPrewarningValue;
import com.jusfoun.ent.generate.mf.MfPrewarningValueGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-9-26 15:07:44
 * @description 实体类MfPrewarningValueParameter，方法参数，自动生成。质量安全预警值设置
 */

public class MfPrewarningValueParameter extends BaseParameter {

	@ApiModel(value = "MfPrewarningValueAddList", description = "批量添加MfPrewarningValue所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfPrewarningValue> entityList = new ArrayList<MfPrewarningValue>();

		public ArrayList<MfPrewarningValue> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfPrewarningValue> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfPrewarningValueDeleteByIdList", description = "批量删除MfPrewarningValue所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfPrewarningValueUpdateList", description = "批量修改MfPrewarningValue所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfPrewarningValue entity = new MfPrewarningValue();

		public MfPrewarningValue getEntity() {
			return entity;
		}

		public void setEntity(MfPrewarningValue entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfPrewarningValueGetList", description = "模糊查询MfPrewarningValue所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfPrewarningValueGetListEntityRelated", description = "模糊查询MfPrewarningValue所需的参数，实体类相关。")
		public static class EntityRelated extends MfPrewarningValueGenEnt{
        
        @ApiModelProperty(value="创建日期查询起止时间。",required=false)
		private Date beginCreateDate;

        @ApiModelProperty(value="创建日期查询结束时间。",required=false)
		private Date endCreateDate;

		public Date getBeginCreateDate() {
			return this.beginCreateDate;
		}
        
		public void setBeginCreateDate(Date beginCreateDate) {
			this.beginCreateDate = beginCreateDate;
		}
        
        public Date getEndCreateDate() {
			return this.endCreateDate;
		}
        
		public void setEndCreateDate(Date endCreateDate) {
			this.endCreateDate = endCreateDate;
		}
        
		}
	}

}







