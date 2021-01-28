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
import com.zee.bll.extend.split.da.DaImportExportSplBll;
import com.zee.bll.extend.unity.da.DaImportExportUntBll;
import com.zee.ent.extend.da.DaImportExport;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.parameter.da.DaImportExportParameter;
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
 * @description 进出口数据表，用于存放直报数据的进出口数据 对外接口，扩展自BaseSwgApp，自动生成。
 */

@Api(value = "DaImportExport",tags="进出口数据表，用于存放直报数据的进出口数据")
@RequestMapping(value = "/generate/swagger/da/daImportExport")
public class DaImportExportGenSwgApp extends BaseSwgApp {

	@Autowired
	@Qualifier("daImportExportUntBll")
	protected DaImportExportUntBll daImportExportUntBll;

	@Autowired
	@Qualifier("daImportExportSplBll")
	protected DaImportExportSplBll daImportExportSplBll;

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaImportExport") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaImportExport jsonData) {
		ResultModel result = daImportExportUntBll.add(jsonData);

		return result;
	}

	@ApiOperation(value = "批量新增", notes = "新增多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaImportExportAddList") })
	@RequestMapping(value = "/addList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel addList(@RequestBody DaImportExportParameter.AddList jsonData) {
		ResultModel result = daImportExportUntBll.add(jsonData.getEntityList());

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = daImportExportUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录，路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteByPath(@PathVariable("id") String id) {
		ResultModel result = daImportExportUntBll.delete(id);

		return result;
	}

	@ApiOperation(value = "批量删除", notes = "根据主键列表批量删除相应记录")
	@ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表", required = true, dataType = "DaImportExportDeleteByIdList")
	@RequestMapping(value = "/deleteList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel deleteList(@RequestBody DaImportExportParameter.DeleteByIdList jsonData) {
		ResultModel result = daImportExportUntBll.deleteByIdList(jsonData.getIdList());

		return result;
	}

	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaImportExport") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaImportExport jsonData) {
		ResultModel result = daImportExportUntBll.update(jsonData);

		return result;
	}

	@ApiOperation(value = "批量修改", notes = "同时修改多条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，主键列表和要修改为的信息承载实体", required = true, dataType = "DaImportExportUpdateList") })
	@RequestMapping(value = "/updateList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateList(@RequestBody DaImportExportParameter.UpdateList jsonData) {
		ResultModel result = daImportExportUntBll.updateList(jsonData);

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaImportExportAddList") })
	@RequestMapping(value = "/updateListWithDff", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDff(@RequestBody DaImportExportParameter.AddList jsonData) {
		ResultModel result = daImportExportUntBll.updateListWithDff(jsonData.getEntityList());

		return result;
	}
    
    
    @ApiOperation(value = "批量修改", notes = "同时修改多条记录、多个属性为不同值,如果没有此条记录则执行新增")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，对象列表", required = true, dataType = "DaImportExportAddList") })
	@RequestMapping(value = "/updateListWithDffOrAdd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResultModel updateListWithDffOrAdd(@RequestBody DaImportExportParameter.AddList jsonData) {
		ResultModel result = daImportExportUntBll.updateListWithDffOrAdd(jsonData.getEntityList());

		return result;
	}
     

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daImportExportUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = daImportExportUntBll.getModel(id);

		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "DaImportExportGetList") })
	@RequestMapping(value = "/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getList(@RequestBody DaImportExportParameter.GetList jsonData) {
		// 处理业务与返回数据
		ResultModel result = daImportExportUntBll.getList(jsonData);

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
		selectBuffer.append("select A.id id,A.common_field_id commonFieldId,A.name name,A.crop_type_code cropTypeCode,A.crop_type_text cropTypeText,A.strains_code strainsCode,A.strains_text strainsText,A.product_name productName,A.import_amount importAmount,A.import_amount_unit_code importAmountUnitCode,A.import_amount_unit_text importAmountUnitText,A.import_amount_unit importAmountUnit,A.import_volume importVolume,A.import_volume_unit_code importVolumeUnitCode,A.import_volume_unit_text importVolumeUnitText,A.import_volume_unit importVolumeUnit,A.import_price importPrice,A.import_price_unit_code importPriceUnitCode,A.import_price_unit_text importPriceUnitText,A.import_price_unit importPriceUnit,A.import_time importTime,A.import_country_code importCountryCode,A.import_country_text importCountryText,A.import_enterprise importEnterprise,A.export_amount exportAmount,A.export_amount_unit_code exportAmountUnitCode,A.export_amount_unit_text exportAmountUnitText,A.export_amount_unit exportAmountUnit,A.export_volume exportVolume,A.export_volume_unit_code exportVolumeUnitCode,A.export_volume_unit_text exportVolumeUnitText,A.export_volume_unit exportVolumeUnit,A.export_price exportPrice,A.export_price_unit_code exportPriceUnitCode,A.export_price_unit_text exportPriceUnitText,A.export_price_unit exportPriceUnit,A.export_time exportTime,A.export_country_code exportCountryCode,A.export_country_text exportCountryText,A.export_enterprise exportEnterprise  from da_import_export A inner join da_import_export B on A.id=B.id where 1=1 ");
        
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
                
				if (entityRelatedObject.containsKey("name") && StringUtils.isNotBlank(entityRelatedObject.getString("name")))
					selectBuffer.append(" and A.name like '%").append(entityRelatedObject.getString("name")).append("%'");
				if (entityRelatedObject.containsKey("cropTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeCode")))
					selectBuffer.append(" and A.crop_type_code like '%").append(entityRelatedObject.getString("cropTypeCode")).append("%'");
				if (entityRelatedObject.containsKey("cropTypeText") && StringUtils.isNotBlank(entityRelatedObject.getString("cropTypeText")))
					selectBuffer.append(" and A.crop_type_text like '%").append(entityRelatedObject.getString("cropTypeText")).append("%'");
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

		resultModel = daImportExportUntBll.getListBySQL(map);

		return resultModel;
	}

	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "进出口数据表，用于存放直报数据的进出口数据列表数据" + DateUtils.getCurrentDateStr() + ".xls";
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




