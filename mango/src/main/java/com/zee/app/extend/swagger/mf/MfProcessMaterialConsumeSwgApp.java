package com.zee.app.extend.swagger.mf;

import io.swagger.annotations.ApiOperation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfProcessMaterialConsumeGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.TimesView;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  对外接口，扩展自MfProcessMaterialConsumeGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfProcessMaterialConsume")
public class  MfProcessMaterialConsumeSwgApp extends  MfProcessMaterialConsumeGenSwgApp {
	/**
	 * 
	 * @title: forecast
	 * @description: 原料消耗及预测
	 * @author: lxl
	 * @date:2018年5月30日 下午11:22:00
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "预测", notes = "预测")
	@RequestMapping(value = "/forecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel forecast() throws ParseException {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                         ");
		selectBuffer.append("		A.id id,                                                   ");
		selectBuffer.append("		IFNULL(A.actual_amount,0) actualAmount,                    ");
		selectBuffer.append("		IFNULL(A.forecast_amount,0) forecastAmount,                ");
		selectBuffer.append("		IFNULL(A.scale_actual,0) scaleActual,                      ");
		selectBuffer.append("		IFNULL(A.scale_forecast,0) scaleForecast,                  ");
		selectBuffer.append("		A.date_time dateTime                                       ");
		selectBuffer.append("	FROM                                                           ");
		selectBuffer.append("		mf_process_material_consume A                              ");
		
		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("actualAmount");
		keySet.add("forecastAmount");
		keySet.add("scaleActual");
		keySet.add("scaleForecast");
		keySet.add("dateTime");
		map.put("Sql", selectBuffer.toString());
		resultModel = mfProcessMaterialConsumeUntBll.getListBySQL(map);
		
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");
		
		resultModel = TimesView.getTimesData(pmap, modelList,keySet);
		List<Map<String, Object>> modelLists =CastObjectUtil.cast(resultModel.getData());
		List<String> dateTimeList =  new ArrayList<String>(); 
		List<String> dateTimeForecastList =  new ArrayList<String>(); 
		List<Object> actualAmountList =  new ArrayList<Object>(); 
		List<Object> forecastAmountList =  new ArrayList<Object>();
		List<Object> scaleActualList =  new ArrayList<Object>();
		List<Object> scaleForecastList =  new ArrayList<Object>();
		Map<String, Object> xmap = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        Date nowDate=sdf.parse(sdf.format(date));
		for(Map<String, Object> maps : modelLists){
			Date dateTime=sdf.parse(String.valueOf(maps.get("dateTime")));
			if(dateTime.before(nowDate)){
				dateTimeList.add((String) maps.get("dateTime"));
				actualAmountList.add(maps.get("actualAmount"));
				scaleActualList.add(maps.get("scaleActual"));
			}else{
				dateTimeForecastList.add((String) maps.get("dateTime"));
				forecastAmountList.add(maps.get("forecastAmount"));
				scaleForecastList.add(maps.get("scaleForecast"));
			}
		}
		xmap.put("XdateTime", dateTimeList);
		xmap.put("xdateTimeForecast", dateTimeForecastList);
		xmap.put("actualAmountList", actualAmountList);
		xmap.put("forecastAmountList", forecastAmountList);
		xmap.put("scaleActualList", scaleActualList);
		xmap.put("scaleForecastList", scaleForecastList);
		resultModel.setData(xmap);
		return resultModel;
	}
	
}



