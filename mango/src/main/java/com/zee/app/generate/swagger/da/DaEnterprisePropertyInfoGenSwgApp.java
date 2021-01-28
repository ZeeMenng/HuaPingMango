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
import com.zee.bll.extend.split.da.DaEnterprisePropertyInfoSplBll;
import com.zee.bll.extend.unity.da.DaEnterprisePropertyInfoUntBll;
import com.zee.ent.extend.da.DaEnterprisePropertyInfo;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.da.DaEnterprisePropertyInfoParameter;
import com.zee.utl.DateUtils;
import com.zee.utl.SymbolicConstant;

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
 * @description 企业财产信息表 对外接口，扩展自BaseSwgApp，自动生成。
 */

@Api(value = "DaEnterprisePropertyInfo",tags="企业财产信息表")
@RequestMapping(value = "/generate/swagger/da/daEnterprisePropertyInfo")
public class DaEnterprisePropertyInfoGenSwgApp extends BaseSwgApp {

	@Autowired
	@Qualifier("daEnterprisePropertyInfoUntBll")
	protected DaEnterprisePropertyInfoUntBll daEnterprisePropertyInfoUntBll;

	@Autowired
	@Qualifier("daEnterprisePropertyInfoSplBll")
	protected DaEnterprisePropertyInfoSplBll daEnterprisePropertyInfoSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaEnterprisePropertyInfo") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaEnterprisePropertyInfo jsonData) {
		ResultModel result = daEnterprisePropertyInfoUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量新增", notes = "新增多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterprisePropertyInfoAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody DaEnterprisePropertyInfoParameter.AddList jsonData) {
		ResultModel result = daEnterprisePropertyInfoUntBll.add(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = daEnterprisePropertyInfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = daEnterprisePropertyInfoUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaEnterprisePropertyInfoDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaEnterprisePropertyInfoParameter.DeleteByIdList jsonData) {
		ResultModel result = daEnterprisePropertyInfoUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaEnterprisePropertyInfo") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaEnterprisePropertyInfo jsonData) {
		ResultModel result = daEnterprisePropertyInfoUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaEnterprisePropertyInfoUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody DaEnterprisePropertyInfoParameter.UpdateList jsonData) {
		ResultModel result = daEnterprisePropertyInfoUntBll.updateList(jsonData);

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterprisePropertyInfoAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDff(@RequestBody DaEnterprisePropertyInfoParameter.AddList jsonData) {
		ResultModel result = daEnterprisePropertyInfoUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值,如果没有此条记录则执行新增")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaEnterprisePropertyInfoAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDffOrAdd(@RequestBody DaEnterprisePropertyInfoParameter.AddList jsonData) {
		ResultModel result = daEnterprisePropertyInfoUntBll.updateListWithDffOrAdd(jsonData.getEntityList());

		return result;
	}
     

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daEnterprisePropertyInfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daEnterprisePropertyInfoUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "DaEnterprisePropertyInfoGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody DaEnterprisePropertyInfoParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = daEnterprisePropertyInfoUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.enterprise_id enterpriseId,A.enterprise_name enterpriseName,A.year year,A.total_assets totalAssets,A.total_current_assets totalCurrentAssets,A.total_liabilities totalLiabilities,A.total_owners_equity totalOwnersEquity,A.operating_income operatingIncome,A.operating_costs operatingCosts,A.operating_profit operatingProfit,A.total_profit totalProfit,A.net_profit netProfit,A.assets_interest_rate assetsInterestRate,A.equity_interest_rate equityInterestRate,A.gross_profit_margin grossProfitMargin,A.before_year_total_assets beforeYearTotalAssets,A.before_year_operating_income beforeYearOperatingIncome,A.before_year_total_liabilities beforeYearTotalLiabilities,A.total_current_quick totalCurrentQuick,A.fixed_assets fixedAssets,A.invisible_assets_net_alue invisibleAssetsNetAlue,A.update_time updateTime,A.update_user_id updateUserId,A.flag_code flagCode,A.flag_text flagText,A.source_create_time sourceCreateTime,A.currency currency,A.datasource_code datasourceCode,A.created_time createdTime,A.remark remark  from da_enterprise_property_info A inner join da_enterprise_property_info B on A.id=B.id where 1=1 ");
        
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
				if (entityRelatedObject.containsKey("year") && StringUtils.isNotBlank(entityRelatedObject.getString("year")))
					selectBuffer.append(" and A.year like '%").append(entityRelatedObject.getString("year")).append("%'");
				if (entityRelatedObject.containsKey("totalAssets") && StringUtils.isNotBlank(entityRelatedObject.getString("totalAssets")))
					selectBuffer.append(" and A.total_assets like '%").append(entityRelatedObject.getString("totalAssets")).append("%'");
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

		resultModel = daEnterprisePropertyInfoUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "企业财产信息表列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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




