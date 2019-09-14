package com.jusfoun.ent.parameter.mf;

import java.util.*;

import com.jusfoun.ent.extend.mf.MfQualityInspectionRate;
import com.jusfoun.ent.generate.mf.MfQualityInspectionRateGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:07:53
 * @description 实体类MfQualityInspectionRateParameter，方法参数，自动生成。质量检测表
 */

public class MfQualityInspectionRateParameter extends BaseParameter {

	@ApiModel(value = "MfQualityInspectionRateAddList", description = "批量添加MfQualityInspectionRate所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<MfQualityInspectionRate> entityList = new ArrayList<MfQualityInspectionRate>();

		public ArrayList<MfQualityInspectionRate> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<MfQualityInspectionRate> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "MfQualityInspectionRateDeleteByIdList", description = "批量删除MfQualityInspectionRate所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "MfQualityInspectionRateUpdateList", description = "批量修改MfQualityInspectionRate所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private MfQualityInspectionRate entity = new MfQualityInspectionRate();

		public MfQualityInspectionRate getEntity() {
			return entity;
		}

		public void setEntity(MfQualityInspectionRate entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "MfQualityInspectionRateGetList", description = "模糊查询MfQualityInspectionRate所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "MfQualityInspectionRateGetListEntityRelated", description = "模糊查询MfQualityInspectionRate所需的参数，实体类相关。")
		public static class EntityRelated extends MfQualityInspectionRateGenEnt{
        
        @ApiModelProperty(value="质量检测时间查询起止时间。",required=false)
		private Date beginCreatTime;

        @ApiModelProperty(value="质量检测时间查询结束时间。",required=false)
		private Date endCreatTime;

		public Date getBeginCreatTime() {
			return this.beginCreatTime;
		}
        
		public void setBeginCreatTime(Date beginCreatTime) {
			this.beginCreatTime = beginCreatTime;
		}
        
        public Date getEndCreatTime() {
			return this.endCreatTime;
		}
        
		public void setEndCreatTime(Date endCreatTime) {
			this.endCreatTime = endCreatTime;
		}
        
		}
	}

}







