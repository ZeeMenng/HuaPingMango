package com.jusfoun.app.extend.swagger.da;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.da.DaEnterpriseCertificateInfoGenSwgApp;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.da.DaEnterpriseCertificateInfo;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaEnterpriseCertificateInfoGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daEnterpriseCertificateInfo")
public class  DaEnterpriseCertificateInfoSwgApp extends  DaEnterpriseCertificateInfoGenSwgApp {

	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaEnterpriseCertificateInfo") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaEnterpriseCertificateInfo jsonData) {
		jsonData.setCreatedTime(DateUtils.getCurrentTime());
		ResultModel result = daEnterpriseCertificateInfoUntBll.add(jsonData);

		return result;
	}
	
	@ApiOperation(value = "删除记录", notes = "根据主键删除相应记录")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/delete", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel delete(@RequestParam String id) {
		ResultModel result = daEnterpriseCertificateInfoUntBll.delete(id);

		return result;
	}
	
	@ApiOperation(value = "修改记录", notes = "修改指定记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，实体属性", required = true, dataType = "DaEnterpriseCertificateInfo") })
	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel update(@RequestBody DaEnterpriseCertificateInfo jsonData) {
		ResultModel result = daEnterpriseCertificateInfoUntBll.update(jsonData);

		return result;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息")
	@ApiImplicitParam(paramType = "query", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModel(@RequestParam String id) {
		ResultModel result = daEnterpriseCertificateInfoUntBll.getModel(id);
		DaEnterpriseCertificateInfo bean = (DaEnterpriseCertificateInfo) result.getData();
		bean.setFileResourcePath(linkPath + bean.getFileResourcePath());
		result.setData(bean);
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
		selectBuffer.append("	SELECT                                                                     ");
		selectBuffer.append("		A.id id,                                                               ");
		selectBuffer.append("		A.cer_name cerName,                                                    ");
		selectBuffer.append("		A.enterprise_id enterpriseId,                                          ");
		selectBuffer.append("		A.enterprise_name enterpriseName,                                      ");
		selectBuffer.append("		A.order_by orderBy,                                                    ");
		selectBuffer.append("		A.start_date startDate,                                                ");
		selectBuffer.append("		A.end_date endDate,                                                    ");
		selectBuffer.append("		A.organ organ,                                                         ");
		selectBuffer.append("		A.content content,                                                     ");
		selectBuffer.append("		A.certificate_status_code certificateStatusCode,                       ");
		selectBuffer.append("		A.certificate_status_text certificateStatusText,                       ");
		selectBuffer.append("		A.certificate_type_code certificateTypeCode,                           ");
		selectBuffer.append("		A.certificate_type_text certificateTypeText,                           ");
		selectBuffer.append("		A.publicity_time publicityTime,                                        ");
		selectBuffer.append("		A.detail detail,                                                       ");
		selectBuffer.append("		A.datasource_code datasourceCode,                                      ");
		selectBuffer.append("		A.remark remark,                                                       ");
		selectBuffer.append("		A.created_time createdTime,                                            ");
		selectBuffer.append("		A.file_no fileNo,                                                      ");
		selectBuffer.append("		A.file_resource_id fileResourceId,                                     ");
		selectBuffer.append("		CONCAT('"+linkPath+"',A.file_resource_path) fileResourcePath           ");
		selectBuffer.append("	FROM                                                                       ");
		selectBuffer.append("		da_enterprise_certificate_info A                                       ");
		selectBuffer.append("	WHERE                                                                      ");
		selectBuffer.append("		1 = 1                                                                  ");
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
				
				if (entityRelatedObject.containsKey("enterpriseId") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseId")))
					selectBuffer.append(" and A.enterprise_id = '").append(entityRelatedObject.getString("enterpriseId")).append("'");
				if (entityRelatedObject.containsKey("certificateTypeCode") && StringUtils.isNotBlank(entityRelatedObject.getString("certificateTypeCode")))
					selectBuffer.append(" and A.certificate_type_code = '").append(entityRelatedObject.getString("certificateTypeCode")).append("'");
				if (entityRelatedObject.containsKey("cerName") && StringUtils.isNotBlank(entityRelatedObject.getString("cerName")))
					selectBuffer.append(" and A.cer_name like '%").append(entityRelatedObject.getString("cerName")).append("%'");
				if (entityRelatedObject.containsKey("enterpriseName") && StringUtils.isNotBlank(entityRelatedObject.getString("enterpriseName")))
					selectBuffer.append(" and A.enterprise_name like '%").append(entityRelatedObject.getString("enterpriseName")).append("%'");
				if (entityRelatedObject.containsKey("orderBy") && StringUtils.isNotBlank(entityRelatedObject.getString("orderBy")))
					selectBuffer.append(" and A.order_by like '%").append(entityRelatedObject.getString("orderBy")).append("%'");
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
					selectBuffer.append(orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}else{
				selectBuffer.append(" order by createdTime DESC ");
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = daEnterpriseCertificateInfoUntBll.getListBySQL(map);

		return resultModel;
	}
}



