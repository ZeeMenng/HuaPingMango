package com.jusfoun.app.extend.swagger.mf;

import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.mf.MfPerUnitYieldGenSwgApp;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.TimesView;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-28 20:02:00
 * @description 单产预测建模表 对外接口，扩展自MfPerUnitYieldGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfPerUnitYield")
public class  MfPerUnitYieldSwgApp extends  MfPerUnitYieldGenSwgApp {
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "预测", notes = "预测")
	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel forecast() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                 "); 
		selectBuffer.append("		A.id id,                                           "); 
		selectBuffer.append("		IFNULL(A.actual_volume, 0) actualVolume,           "); 
		selectBuffer.append("		IFNULL(A.forecast_volume, 0) forecastVolume,       "); 
		selectBuffer.append("		A.date_time dateTime                               "); 
		selectBuffer.append("	FROM                                                   "); 
		selectBuffer.append("		mf_per_unit_yield A                                "); 
		
		//获取查询表变量名
		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("actualVolume");
		keySet.add("forecastVolume");
		keySet.add("dateTime");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = mfPerUnitYieldUntBll.getListBySQL(map);
		
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");
		
		return TimesView.getTimesData(pmap, modelList, keySet);
	}
	
}



