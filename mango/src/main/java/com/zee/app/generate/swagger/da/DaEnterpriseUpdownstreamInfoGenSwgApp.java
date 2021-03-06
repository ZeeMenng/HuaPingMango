package com.zee.app.generate.swagger.da;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zee.app.generate.swagger.base.BaseSwgApp;
import com.zee.bll.extend.split.da.DaEnterpriseUpdownstreamInfoSplBll;
import com.zee.bll.extend.unity.da.DaEnterpriseUpdownstreamInfoUntBll;
import com.zee.ent.extend.da.DaEnterpriseUpdownstreamInfo;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.da.DaEnterpriseUpdownstreamInfoParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:00:55
 * @updateDate 2021/1/28 16:06:31
 * @description 企业关联企业表，企业上下游关系记录。 对外接口，扩展自BaseSwgApp，自动生成。
 */

@Api(value = "DaEnterpriseUpdownstreamInfo",tags="企业关联企业表，企业上下游关系记录。")
@RequestMapping(value = "/generate/swagger/da/daEnterpriseUpdownstreamInfo")
public class DaEnterpriseUpdownstreamInfoGenSwgApp extends BaseSwgApp {

	@Autowired
	@Qualifier("daEnterpriseUpdownstreamInfoUntBll")
	protected DaEnterpriseUpdownstreamInfoUntBll daEnterpriseUpdownstreamInfoUntBll;

	@Autowired
	@Qualifier("daEnterpriseUpdownstreamInfoSplBll")
	protected DaEnterpriseUpdownstreamInfoSplBll daEnterpriseUpdownstreamInfoSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaEnterpriseUpdownstreamInfo") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaEnterpriseUpdownstreamInfo jsonData) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量新增", notes = "新增多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterpriseUpdownstreamInfoAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody DaEnterpriseUpdownstreamInfoParameter.AddList jsonData) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.add(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaEnterpriseUpdownstreamInfoDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaEnterpriseUpdownstreamInfoParameter.DeleteByIdList jsonData) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaEnterpriseUpdownstreamInfo") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaEnterpriseUpdownstreamInfo jsonData) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaEnterpriseUpdownstreamInfoUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody DaEnterpriseUpdownstreamInfoParameter.UpdateList jsonData) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.updateList(jsonData);

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterpriseUpdownstreamInfoAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDff(@RequestBody DaEnterpriseUpdownstreamInfoParameter.AddList jsonData) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值,如果没有此条记录则执行新增")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterpriseUpdownstreamInfoAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDffOrAdd(@RequestBody DaEnterpriseUpdownstreamInfoParameter.AddList jsonData) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.updateListWithDffOrAdd(jsonData.getEntityList());

		return result;
	}
     

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "DaEnterpriseUpdownstreamInfoGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody DaEnterpriseUpdownstreamInfoParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = daEnterpriseUpdownstreamInfoUntBll.getList(jsonData);

		return result;
	}
    
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.enterprise_id enterpriseId,A.enterprise_name enterpriseName,A.cognate_enterprise_id cognateEnterpriseId,A.cognate_enterprise_name cognateEnterpriseName,A.up_down_type_code upDownTypeCode,A.up_down_type_name upDownTypeName,A.datasource_code datasourceCode,A.created_time createdTime,A.remark remark  from da_enterprise_updownstream_info A inner join da_enterprise_updownstream_info B on A.id=B.id where 1=1 ");
        
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and A.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("enterpriseName") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseName")))
					selectBuffer.append(" and A.enterprise_name like '%").append(entityRelatedObject.getString("enterpriseName")).append("%'");
				if (entityRelatedObject.containsKey("cognateEnterpriseName") && StringUtils.isNotBlank(entityRelatedObject.getString("cognateEnterpriseName")))
					selectBuffer.append(" and A.cognate_enterprise_name like '%").append(entityRelatedObject.getString("cognateEnterpriseName")).append("%'");
				if (entityRelatedObject.containsKey("upDownTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("upDownTypeCode")))
					selectBuffer.append(" and A.up_down_type_code like '%").append(entityRelatedObject.getString("upDownTypeCode")).append("%'");
			}

			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
			}

			if (jsonObject.containsKey("orderList")) {
				JSONArray orderListArray = jsonObject.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append("A." + orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = daEnterpriseUpdownstreamInfoUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "企业关联企业表，企业上下游关系记录列表数据" + DateUtils.getCurrentDateStr() + ".xls";
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		JSONArray columnInfoList = new JSONArray();
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);

			if (json.containsKey("columnInfo")) {
				columnInfoList = json.getJSONArray("columnInfo");
			}
		}

		if (resultModel != null) {
			try {
				exportExcel(fileName, columnInfoList, resultModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}




