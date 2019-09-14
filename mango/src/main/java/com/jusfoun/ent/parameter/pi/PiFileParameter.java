package com.jusfoun.ent.parameter.pi;

import java.util.*;

import com.jusfoun.ent.extend.pi.PiFile;
import com.jusfoun.ent.generate.pi.PiFileGenEnt;
import com.jusfoun.ent.parameter.base.BaseParameter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;



/**
 * @author Zee
 * @createDate 2017/05/18 14:54:22
 * @updateDate 2018/1/26 17:25:56
 * @description 实体类PiFileParameter，方法参数，自动生成。CMS文章相关文件表
 */

public class PiFileParameter extends BaseParameter {

	@ApiModel(value = "PiFileAddList", description = "批量添加PiFile所需参数")
	public static class AddList extends BaseParameter.BaseParamAddList {

		@ApiModelProperty(value = "要新增的记录列表 ", required = false)
		private ArrayList<PiFile> entityList = new ArrayList<PiFile>();

		public ArrayList<PiFile> getEntityList() {
			return entityList;
		}

		public void setEntityList(ArrayList<PiFile> entityList) {
			this.entityList = entityList;
		}

	}

	@ApiModel(value = "PiFileDeleteByIdList", description = "批量删除PiFile所需参数")
	public static class DeleteByIdList extends BaseParameter.BaseParamDeleteByIdList {

	}

	@ApiModel(value = "PiFileUpdateList", description = "批量修改PiFile所需参数")
	public static class UpdateList extends BaseParameter.BaseParamUpdateList {

		@ApiModelProperty(value = "要修改为的信息承载实体 ", required = false)
		private PiFile entity = new PiFile();

		public PiFile getEntity() {
			return entity;
		}

		public void setEntity(PiFile entity) {
			this.entity = entity;
		}

	}

	@ApiModel(value = "PiFileGetList", description = "模糊查询PiFile所需参数")
	public static class GetList extends BaseParameter.BaseParamGetList {

		@ApiModelProperty(value = "实体相关的查询条件 ", required = false)
		private EntityRelated entityRelated = new EntityRelated();

		public EntityRelated getEntityRelated() {
			return entityRelated;
		}

		public void setEntityRelated(EntityRelated entityRelated) {
			this.entityRelated = entityRelated;
		}

		@ApiModel(value = "PiFileGetListEntityRelated", description = "模糊查询PiFile所需的参数，实体类相关。")
		public static class EntityRelated extends PiFileGenEnt{
        
		}
	}

}







