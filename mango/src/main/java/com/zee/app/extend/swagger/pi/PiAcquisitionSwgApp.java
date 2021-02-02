package com.zee.app.extend.swagger.pi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiAcquisitionGenSwgApp;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.bll.extend.unity.pi.PiAcquisitionUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiAcquisition;
import com.zee.ent.extend.pi.PiContent;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DictionaryUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS采集任务表 对外接口，扩展自PiAcquisitionGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piAcquisition")
public class PiAcquisitionSwgApp extends PiAcquisitionGenSwgApp {
	
	@Autowired
	protected GpDictionaryUntBll gpDictionaryUntBll;
	
	@Autowired
	@Qualifier("piAcquisitionUntBll")
	protected PiAcquisitionUntBll piAcquisitionUntBll;
	
	@Autowired
	private DictionaryUtil dictionaryUtil;
	
	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@RequestMapping(value = "/addAcquisition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResultModel addAcquisition() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}
		
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			
			String acqName = null;
			String startDate = null;
			String endDate = null;
			String status = null;
			PiAcquisition piAcquisition = new PiAcquisition();
			
			if (jsonObject.containsKey("acqName")) {
				acqName = jsonObject.getString("acqName");
				piAcquisition.setAcqName(acqName);
			}
			if (jsonObject.containsKey("acquisitionStatus")) {
				status = jsonObject.getString("acquisitionStatus");
				piAcquisition.setStatus(Integer.parseInt(status));
			}
			if (jsonObject.containsKey("startDate")) {
				startDate = jsonObject.getString("startDate");
				java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date sDate = null;
				try {
					if(StringUtils.isNotBlank(startDate)) {
						sDate = formatter.parse(startDate);
						piAcquisition.setStartTime(sDate);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (jsonObject.containsKey("endDate")) {
				endDate = jsonObject.getString("endDate");
				java.text.SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
				Date eDate = null;
				try {
					if(StringUtils.isNotBlank(endDate)) {
						eDate = formatter.parse(endDate);
						piAcquisition.setEndTime(eDate);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			resultModel = piAcquisitionUntBll.add(piAcquisition);
		}
		
		return resultModel;
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
		
		selectBuffer.append("SELECT                                                    										");
		selectBuffer.append("	a.id id,                                                 									");
		selectBuffer.append("	a.acq_name acqName,                                                 						");
		selectBuffer.append("	a.status status,                                                 							");
		selectBuffer.append("	DATE_FORMAT(a.start_time,'%Y-%m-%d %H:%i:%S') AS startTime,                   				");
		selectBuffer.append("	DATE_FORMAT(a.end_time,'%Y-%m-%d %H:%i:%S') AS endTime,                              		");
		selectBuffer.append("	d.text statusText                                                 							");
		selectBuffer.append("FROM                                                      		        						");
		selectBuffer.append("	pi_acquisition a                                             	        					");
		selectBuffer.append("	LEFT JOIN gp_dictionary d ON d.code=a.status            									");
		selectBuffer.append("WHERE 1=1       																				");
		selectBuffer.append("AND																							");
		selectBuffer.append("d.type_id='");
		selectBuffer.append(CustomSymbolic.DI_ACQUISITION																	 );
		selectBuffer.append("'");
		
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
                
				if (entityRelatedObject.containsKey("acqName") && StringUtils.isNotBlank(entityRelatedObject.getString("acqName")))
					selectBuffer.append(" and a.acq_name like '%").append(entityRelatedObject.getString("acqName")).append("%'");
				/*if (entityRelatedObject.containsKey("startTime") && StringUtils.isNotBlank(entityRelatedObject.getString("startTime")))
					selectBuffer.append(" and A.start_time like '%").append(entityRelatedObject.getString("startTime")).append("%'");
				if (entityRelatedObject.containsKey("endTime") && StringUtils.isNotBlank(entityRelatedObject.getString("endTime")))
					selectBuffer.append(" and A.end_time like '%").append(entityRelatedObject.getString("endTime")).append("%'");*/
				
				if (entityRelatedObject.containsKey("acquisitionStatus") && StringUtils.isNotBlank(entityRelatedObject.getString("acquisitionStatus")))
					selectBuffer.append(" and a.status=")
					.append(entityRelatedObject.getString("acquisitionStatus"));
				
				if (entityRelatedObject.containsKey("startDate")
						&& StringUtils.isNotBlank(entityRelatedObject.getString("startDate")))
					selectBuffer.append(" and ")
					.append("(SELECT TIMESTAMPDIFF(SECOND,'")
					.append(entityRelatedObject.getString("startDate"))
					.append("',start_time)) > 0 ");
				
				if (entityRelatedObject.containsKey("endDate")
						&& StringUtils.isNotBlank(entityRelatedObject.getString("endDate")))
					selectBuffer.append(" and ")
					.append("(SELECT TIMESTAMPDIFF(SECOND,'")
					.append(entityRelatedObject.getString("endDate"))
					.append("',end_time)) < 0 ");
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
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = piAcquisitionUntBll.getListBySQL(map);

		return resultModel;
	}

	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getAcquisitionDetailsById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getAcquisitionDetailsById(@PathVariable("id") String id) {
		
		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT                                                    								");
		selectBuffer.append("	a.id AS id,                                                 						");
		selectBuffer.append("	a.acq_name AS acqName,                                                  	    				");
		selectBuffer.append("	a.status AS status,                                                 	    				");
		selectBuffer.append("	a.start_time AS startTime,                                                 	    				");
		selectBuffer.append("	a.end_time AS endTime                                                 	    				");
		
		selectBuffer.append("FROM                                                      		        				");
		selectBuffer.append("	pi_acquisition a                                            	       				    ");
		
		selectBuffer.append("WHERE a.id =      												        				");
		selectBuffer.append("'");
		selectBuffer.append(id);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		resultModel = piAcquisitionUntBll.getListBySQL(map);
		
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		if(object!=null) {
			objectList = (List<Map<String, Object>>)object;
			if(objectList!=null && objectList.size()>=1) {
				objMap = (Map<String, Object>)objectList.get(0);
				
				JSONObject jsonData = JSONObject.fromObject(objMap);
				if (jsonData.containsKey("status")) {
					String tempStatus = jsonData.getString("status");
					String status = transfSqlData(CustomSymbolic.DI_ACQUISITION,tempStatus);
					objMap.put("status", status);
				}
				resultModel.setData(objMap);
			}
		}
		return resultModel;
	}
	
	private String transfSqlData(String dictType,String isRecommend) {
		ResultModel resultModel = new ResultModel();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         "); 
		selectBuffer.append("		d.text AS text 												   "); 
		selectBuffer.append("	FROM                                                           "); 
		selectBuffer.append("		gp_dictionary d                                               ");
		selectBuffer.append("   WHERE d.type_id =        											   ");
		selectBuffer.append("'");
		selectBuffer.append(dictType);
		selectBuffer.append("'");

		map.put("Sql", selectBuffer.toString());
		
		resultModel = gpDictionaryUntBll.getListBySQL(map);
		Object object = resultModel.getData();
		List<Map<String, Object>> objectList = null;
		Map<String, Object> objMap = null;
		String text = "";
		if (object != null) {
			objectList = (List<Map<String, Object>>) object;
			if(objectList.size()>=1) {
				objMap = objectList.get(0);
				JSONObject jsonData = JSONObject.fromObject(objMap);
				if (jsonData.containsKey("text")) {
					text = jsonData.getString("text");
				}
			}
		}	
		return text;
	}
}



