﻿package com.jusfoun.app.generate.swagger.da;

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

import com.jusfoun.app.generate.swagger.base.BaseSwgApp;
import com.jusfoun.bll.extend.split.da.DaIndustryBasicinfoSplBll;
import com.jusfoun.bll.extend.unity.da.DaIndustryBasicinfoUntBll;
import com.jusfoun.ent.extend.da.DaIndustryBasicinfo;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.parameter.da.DaIndustryBasicinfoParameter;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:00:55
 * @updateDate 2020/8/11 11:42:42
 * @description 产业基本情况表 对外接口，扩展自BaseSwgApp，自动生成。
 */

@Api(value = "DaIndustryBasicinfo",tags="产业基本情况表")
@RequestMapping(value = "/generate/swagger/da/daIndustryBasicinfo")
public class DaIndustryBasicinfoGenSwgApp extends BaseSwgApp {

	@Autowired
	@Qualifier("daIndustryBasicinfoUntBll")
	protected DaIndustryBasicinfoUntBll daIndustryBasicinfoUntBll;

	@Autowired
	@Qualifier("daIndustryBasicinfoSplBll")
	protected DaIndustryBasicinfoSplBll daIndustryBasicinfoSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaIndustryBasicinfo") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaIndustryBasicinfo jsonData) {
		ResultModel result = daIndustryBasicinfoUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量新增", notes = "新增多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaIndustryBasicinfoAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody DaIndustryBasicinfoParameter.AddList jsonData) {
		ResultModel result = daIndustryBasicinfoUntBll.add(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = daIndustryBasicinfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = daIndustryBasicinfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaIndustryBasicinfoDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaIndustryBasicinfoParameter.DeleteByIdList jsonData) {
		ResultModel result = daIndustryBasicinfoUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaIndustryBasicinfo") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaIndustryBasicinfo jsonData) {
		ResultModel result = daIndustryBasicinfoUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaIndustryBasicinfoUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody DaIndustryBasicinfoParameter.UpdateList jsonData) {
		ResultModel result = daIndustryBasicinfoUntBll.updateList(jsonData);

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaIndustryBasicinfoAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDff(@RequestBody DaIndustryBasicinfoParameter.AddList jsonData) {
		ResultModel result = daIndustryBasicinfoUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值,如果没有此条记录则执行新增")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaIndustryBasicinfoAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDffOrAdd(@RequestBody DaIndustryBasicinfoParameter.AddList jsonData) {
		ResultModel result = daIndustryBasicinfoUntBll.updateListWithDffOrAdd(jsonData.getEntityList());

		return result;
	}
     

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daIndustryBasicinfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daIndustryBasicinfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "DaIndustryBasicinfoGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody DaIndustryBasicinfoParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = daIndustryBasicinfoUntBll.getList(jsonData);

		return result;
	}
    
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.common_field_id commonFieldId,A.crop_type_code cropTypeCode,A.crop_type_text cropTypeText,A.strains_code strainsCode,A.strains_text strainsText,A.name name,A.crop_total_area cropTotalArea,A.crop_total_area_code cropTotalAreaCode,A.crop_total_area_text cropTotalAreaText,A.crop_total_area_unit cropTotalAreaUnit,A.crop_fruit_area cropFruitArea,A.crop_fruit_area_code cropFruitAreaCode,A.crop_fruit_area_text cropFruitAreaText,A.crop_fruit_area_unit cropFruitAreaUnit,A.crop_graft_area cropGraftArea,A.crop_graft_area_code cropGraftAreaCode,A.crop_graft_area_text cropGraftAreaText,A.crop_graft_area_unit cropGraftAreaUnit,A.ngrafted_tree_area ngraftedTreeArea,A.ngrafted_tree_area_code ngraftedTreeAreaCode,A.ngrafted_tree_area_text ngraftedTreeAreaText,A.ngrafted_tree_area_unit ngraftedTreeAreaUnit,A.ngrafted_youngtree_area ngraftedYoungtreeArea,A.ngrafted_youngtree_area_code ngraftedYoungtreeAreaCode,A.ngrafted_youngtree_area_text ngraftedYoungtreeAreaText,A.ngrafted_youngtree_area_unit ngraftedYoungtreeAreaUnit,A.land_area landArea,A.land_area_code landAreaCode,A.land_area_text landAreaText,A.land_area_unit landAreaUnit,A.grow_area growArea,A.grow_area_code growAreaCode,A.grow_area_text growAreaText,A.grow_area_unit growAreaUnit,A.develop_variety developVariety  from da_industry_basicinfo A inner join da_industry_basicinfo B on A.id=B.id where 1=1 ");
        
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
                
				if (entityRelatedObject.containsKey("cropTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeCode")))
					selectBuffer.append(" and A.crop_type_code like '%").append(entityRelatedObject.getString("cropTypeCode")).append("%'");
				if (entityRelatedObject.containsKey("cropTypeText") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeText")))
					selectBuffer.append(" and A.crop_type_text like '%").append(entityRelatedObject.getString("cropTypeText")).append("%'");
				if (entityRelatedObject.containsKey("strainsCode") && StringUtils.isNotBlank(entityRelatedObject.getString("strainsCode")))
					selectBuffer.append(" and A.strains_code like '%").append(entityRelatedObject.getString("strainsCode")).append("%'");
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

		resultModel = daIndustryBasicinfoUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "产业基本情况表列表数据" + DateUtils.getCurrentDateStr() + ".xls";
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
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




