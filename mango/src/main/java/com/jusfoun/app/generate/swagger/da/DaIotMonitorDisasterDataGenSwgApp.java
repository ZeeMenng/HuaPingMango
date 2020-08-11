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
import com.jusfoun.bll.extend.split.da.DaIotMonitorDisasterDataSplBll;
import com.jusfoun.bll.extend.unity.da.DaIotMonitorDisasterDataUntBll;
import com.jusfoun.ent.extend.da.DaIotMonitorDisasterData;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.parameter.da.DaIotMonitorDisasterDataParameter;
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
 * @updateDate 2020/8/11 11:42:43
 * @description da_iot_monitor_data表中满足预警条件的数据 对外接口，扩展自BaseSwgApp，自动生成。
 */

@Api(value = "DaIotMonitorDisasterData",tags="da_iot_monitor_data表中满足预警条件的数据")
@RequestMapping(value = "/generate/swagger/da/daIotMonitorDisasterData")
public class DaIotMonitorDisasterDataGenSwgApp extends BaseSwgApp {

	@Autowired
	@Qualifier("daIotMonitorDisasterDataUntBll")
	protected DaIotMonitorDisasterDataUntBll daIotMonitorDisasterDataUntBll;

	@Autowired
	@Qualifier("daIotMonitorDisasterDataSplBll")
	protected DaIotMonitorDisasterDataSplBll daIotMonitorDisasterDataSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaIotMonitorDisasterData") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaIotMonitorDisasterData jsonData) {
		ResultModel result = daIotMonitorDisasterDataUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量新增", notes = "新增多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaIotMonitorDisasterDataAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody DaIotMonitorDisasterDataParameter.AddList jsonData) {
		ResultModel result = daIotMonitorDisasterDataUntBll.add(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = daIotMonitorDisasterDataUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = daIotMonitorDisasterDataUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaIotMonitorDisasterDataDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaIotMonitorDisasterDataParameter.DeleteByIdList jsonData) {
		ResultModel result = daIotMonitorDisasterDataUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaIotMonitorDisasterData") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaIotMonitorDisasterData jsonData) {
		ResultModel result = daIotMonitorDisasterDataUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaIotMonitorDisasterDataUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody DaIotMonitorDisasterDataParameter.UpdateList jsonData) {
		ResultModel result = daIotMonitorDisasterDataUntBll.updateList(jsonData);

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaIotMonitorDisasterDataAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDff(@RequestBody DaIotMonitorDisasterDataParameter.AddList jsonData) {
		ResultModel result = daIotMonitorDisasterDataUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值,如果没有此条记录则执行新增")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaIotMonitorDisasterDataAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDffOrAdd(@RequestBody DaIotMonitorDisasterDataParameter.AddList jsonData) {
		ResultModel result = daIotMonitorDisasterDataUntBll.updateListWithDffOrAdd(jsonData.getEntityList());

		return result;
	}
     

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daIotMonitorDisasterDataUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daIotMonitorDisasterDataUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "DaIotMonitorDisasterDataGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody DaIotMonitorDisasterDataParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = daIotMonitorDisasterDataUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.hid hid,A.device_name deviceName,A.basc_code bascCode,A.basc_name bascName,A.longitude longitude,A.latitude latitude,A.mango_type mangoType,A.disaster_type_code disasterTypeCode,A.disaster_type_text disasterTypeText,A.create_time createTime,A.remark remark,A.sensor_type sensorType  from da_iot_monitor_disaster_data A inner join da_iot_monitor_disaster_data B on A.id=B.id where 1=1 ");
        
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
                
				if (entityRelatedObject.containsKey("deviceName") && StringUtils.isNotBlank(entityRelatedObject.getString("deviceName")))
					selectBuffer.append(" and A.device_name like '%").append(entityRelatedObject.getString("deviceName")).append("%'");
				if (entityRelatedObject.containsKey("bascCode") && StringUtils.isNotBlank(entityRelatedObject.getString("bascCode")))
					selectBuffer.append(" and A.basc_code like '%").append(entityRelatedObject.getString("bascCode")).append("%'");
				if (entityRelatedObject.containsKey("bascName") && StringUtils.isNotBlank(entityRelatedObject.getString("bascName")))
					selectBuffer.append(" and A.basc_name like '%").append(entityRelatedObject.getString("bascName")).append("%'");
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

		resultModel = daIotMonitorDisasterDataUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "da_iot_monitor_data表中满足预警条件的数据列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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




