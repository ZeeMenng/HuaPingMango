package com.zee.ent.parameter.da;

import java.util.*;

import com.zee.ent.extend.da.DaImportExport;
import com.zee.ent.generate.da.DaImportExportGenEnt;
import com.zee.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018-6-13 19:04:24
 * @description 实体类DaImportExportParameter，方法参数，自动生成。进出口数据表，用于存放直报数据的进出口数据
 */

public class DaImportExportParameter extends BaseParameter {

	@ApiModel(value = "DaImportExportAddList", description = "批量添加DaImportExport所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<DaImportExport> entityList = new ArrayList<DaImportExport>();

		public ArrayList<DaImportExport> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<DaImportExport> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "DaImportExportDeleteByIdList", description = "批量删除DaImportExport所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "DaImportExportUpdateList", description = "批量修改DaImportExport所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private DaImportExport entity = new DaImportExport();

		public DaImportExport getEntity() {
			return entity;
		}

		public void setEntity(DaImportExport entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "DaImportExportGetList", description = "模糊查询DaImportExport所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "DaImportExportGetListEntityRelated", description = "模糊查询DaImportExport所需的参数，实体类相关。")
		public static class EntityRelated extends DaImportExportGenEnt{
        
        @ApiModelProperty(value="出口日期查询起止时间。",required=false)
		private Date beginExportTime;

        @ApiModelProperty(value="出口日期查询结束时间。",required=false)
		private Date endExportTime;

        @ApiModelProperty(value="进口日期查询起止时间。",required=false)
		private Date beginImportTime;

        @ApiModelProperty(value="进口日期查询结束时间。",required=false)
		private Date endImportTime;

		public Date getBeginExportTime() {
			return this.beginExportTime;
		}
        
		public void setBeginExportTime(Date beginExportTime) {
			this.beginExportTime = beginExportTime;
		}
        
        public Date getEndExportTime() {
			return this.endExportTime;
		}
        
		public void setEndExportTime(Date endExportTime) {
			this.endExportTime = endExportTime;
		}
        
		public Date getBeginImportTime() {
			return this.beginImportTime;
		}
        
		public void setBeginImportTime(Date beginImportTime) {
			this.beginImportTime = beginImportTime;
		}
        
        public Date getEndImportTime() {
			return this.endImportTime;
		}
        
		public void setEndImportTime(Date endImportTime) {
			this.endImportTime = endImportTime;
		}
        
		}
	}

}







