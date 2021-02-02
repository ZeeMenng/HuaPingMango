package com.zee.app.extend.swagger.mf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfProcessYieldGenSwgApp;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.gp.GpDictionary;
import com.zee.ent.parameter.base.BaseParameter;
import com.zee.ent.parameter.base.BaseParameter.BaseParamGetList.Order;
import com.zee.ent.parameter.gp.GpDictionaryParameter;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.TimesView;

import io.swagger.annotations.ApiOperation;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  对外接口，扩展自MfProcessYieldGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfProcessYield")
public class  MfProcessYieldSwgApp extends  MfProcessYieldGenSwgApp {
	
	@Autowired
	@Qualifier("gpDictionaryUntBll")
	protected GpDictionaryUntBll gpDictionaryUntBll;
	/**
	 * 
	 * @title: forecast
	 * @description: 加工品产量预测
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
		ResultModel resultModel2 = new ResultModel();
		GpDictionaryParameter.GetList jsonData = new GpDictionaryParameter.GetList();

		GpDictionaryParameter.GetList.EntityRelated entityRelated = new GpDictionaryParameter.GetList.EntityRelated();
		BaseParameter.BaseParamGetList.Order order = new BaseParameter.BaseParamGetList.Order();

		entityRelated.setTypeId(CustomSymbolic.DI_PROCESS_BREED);
		order.setColumnName("priority");
		order.setIsASC(true);

		jsonData.setEntityRelated(entityRelated);
		jsonData.setOrderList(new ArrayList<Order>() {
			{
				add(order);
			}
		});

		ResultModel result = gpDictionaryUntBll.getList(jsonData);
		List<GpDictionary> results  = (List<GpDictionary>) result.getData();
		
		List<Object> yproductNameList = new ArrayList<Object>();
		List<String> yproductName = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    Date date = new Date();
	    Date nowDate=sdf.parse(sdf.format(date));
		int dsize = results.size();
		for(GpDictionary dictionaryList :results){
			Map<String,Object> productMap = new HashMap<String,Object>();
			Byte code =dictionaryList.getCode();
			if(code!=0){
				String text = dictionaryList.getText();
				productMap.put("names", text);
				yproductName.add(text);
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("	SELECT                                                                  ");
			selectBuffer.append("		A.id id,                                                            ");
			selectBuffer.append("		A.process_strains_code processStrainsCode,                          ");
			selectBuffer.append("		A.process_strains_text processStrainsText,                          ");
			selectBuffer.append("		IFNULL(A.output_value_forecast, 0) outputValueForecast,                          ");
			selectBuffer.append("		IFNULL(A.output_value_actual, 0) outputValueActual,                        ");
			selectBuffer.append("		IFNULL(A.yield_forecast, 0) yieldForecast,                ");
			selectBuffer.append("		IFNULL(A.yield_actual, 0) yieldActual,                         ");
			selectBuffer.append("		A.date_time dateTime                                                ");
			selectBuffer.append("	FROM                                                                    ");
			selectBuffer.append("		mf_process_yield A                                                  ");
			selectBuffer.append("		 where A.process_strains_code='"+code+"'                                                  ");
			selectBuffer.append("		GROUP BY A.date_time                                                 ");

			
			
			
			
			Set<String> keySet = new HashSet<String>();
			keySet.add("id");
			keySet.add("processStrainsCode");
			keySet.add("processStrainsText");
			keySet.add("outputValueForecast");
			keySet.add("outputValueActual");
			keySet.add("yieldForecast");
			keySet.add("yieldActual");
			keySet.add("dateTime");
			
			map.put("Sql", selectBuffer.toString());
			
			resultModel = mfProcessYieldUntBll.getListBySQL(map);
			
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, String> pmap = new HashMap<String, String>();
			pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
			pmap.put("hasCurrent", "true");
			pmap.put("pastNum", "5");
			pmap.put("afterNum", "0");

			resultModel = TimesView.getTimesData(pmap, modelList, keySet);
			List<Map<String, Object>> modelLists =CastObjectUtil.cast(resultModel.getData());
			List<Object> productNameList = new ArrayList<Object>();
			for(Map<String, Object> YmodelLists :modelLists ){
				
				 Date dateTime=sdf.parse(String.valueOf(YmodelLists.get("dateTime")));
				 if(dateTime.before(nowDate)){
					 productNameList.add(YmodelLists.get("yieldActual"));
				}else{
					productNameList.add(YmodelLists.get("yieldForecast"));
					
				}
			}
			productMap.put("values", productNameList);
			yproductNameList.add(productMap);
				
			}
		}
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("	SELECT                                                                  ");
		selectBuffer2.append("		SUM(IFNULL(A.output_value_forecast, 0)) outputValueForecast,                          ");
		selectBuffer2.append("		SUM(IFNULL(A.output_value_actual, 0)) outputValueActual,                        ");
		selectBuffer2.append("		SUM(IFNULL(A.yield_forecast, 0)) yieldForecast,                ");
		selectBuffer2.append("		SUM(IFNULL(A.yield_actual, 0)) yieldActual,                         ");
		selectBuffer2.append("		A.date_time dateTime                                                ");
		selectBuffer2.append("	FROM                                                                    ");
		selectBuffer2.append("		mf_process_yield A                                                  ");
		selectBuffer2.append("		GROUP BY A.date_time                                                 ");
		
		Set<String> keySet = new HashSet<String>();
		keySet.add("outputValueForecast");
		keySet.add("outputValueActual");
		keySet.add("yieldForecast");
		keySet.add("yieldActual");
		keySet.add("dateTime");
		map2.put("Sql", selectBuffer2.toString());
		resultModel2 = mfProcessYieldUntBll.getListBySQL(map2);
		List<Map<String, Object>> modelList2 = (List<Map<String, Object>>) resultModel2.getData();
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");
		resultModel2 = TimesView.getTimesData(pmap, modelList2, keySet);
		List<Map<String, Object>> sumMap = (List<Map<String, Object>>) resultModel2.getData();
	        List<Object> actualList= new ArrayList<Object>();//总产量
	        List<Object> xDate= new ArrayList<Object>();
		for(Map<String, Object> sumMapList : sumMap){
			xDate.add(sumMapList.get("dateTime"));
			 Date dateTime=sdf.parse(String.valueOf(sumMapList.get("dateTime")));
			 if(dateTime.before(nowDate)){
				 actualList.add( sumMapList.get("yieldActual"));
			}else{
				actualList.add(sumMapList.get("yieldForecast"));
				
			}
			
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
				map.put("sumActualListY", actualList);//产量
				map.put("xDate", xDate);//x轴时间
				map.put("yproductNameList", yproductNameList);
		resultModel.setData(map);
		return resultModel;
	}
	
	
	/**
	 * 
	 * @title: forecast
	 * @description: 加工品产值预测
	 * @author: lxl
	 * @date:2018年5月30日 下午11:22:00
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "预测", notes = "预测")
	@RequestMapping(value = "/outputValueForecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel outputValueForecast() throws ParseException {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		GpDictionaryParameter.GetList jsonData = new GpDictionaryParameter.GetList();

		GpDictionaryParameter.GetList.EntityRelated entityRelated = new GpDictionaryParameter.GetList.EntityRelated();
		BaseParameter.BaseParamGetList.Order order = new BaseParameter.BaseParamGetList.Order();

		entityRelated.setTypeId(CustomSymbolic.DI_PROCESS_BREED);
		order.setColumnName("priority");
		order.setIsASC(true);

		jsonData.setEntityRelated(entityRelated);
		jsonData.setOrderList(new ArrayList<Order>() {
			{
				add(order);
			}
		});

		ResultModel result = gpDictionaryUntBll.getList(jsonData);
		List<GpDictionary> results  = (List<GpDictionary>) result.getData();
		
		List<Object> yproductNameList = new ArrayList<Object>();
		List<String> yproductName = new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
	    Date date = new Date();
	    Date nowDate=sdf.parse(sdf.format(date));
		int dsize = results.size();
		for(GpDictionary dictionaryList :results){
			Map<String,Object> productMap = new HashMap<String,Object>();
			Byte code =dictionaryList.getCode();
			if(code!=0){
				String text = dictionaryList.getText();
				productMap.put("names", text);
				yproductName.add(text);
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("	SELECT                                                                  ");
			selectBuffer.append("		A.id id,                                                            ");
			selectBuffer.append("		A.process_strains_code processStrainsCode,                          ");
			selectBuffer.append("		A.process_strains_text processStrainsText,                          ");
			selectBuffer.append("		IFNULL(A.output_value_forecast, 0) outputValueForecast,                          ");
			selectBuffer.append("		IFNULL(A.output_value_actual, 0) outputValueActual,                        ");
			selectBuffer.append("		IFNULL(A.yield_forecast, 0) yieldForecast,                ");
			selectBuffer.append("		IFNULL(A.yield_actual, 0) yieldActual,                         ");
			selectBuffer.append("		A.date_time dateTime                                                ");
			selectBuffer.append("	FROM                                                                    ");
			selectBuffer.append("		mf_process_yield A                                                  ");
			selectBuffer.append("		 where A.process_strains_code='"+code+"'                                                  ");
			selectBuffer.append("		GROUP BY A.date_time                                                 ");

			
			
			
			
			Set<String> keySet = new HashSet<String>();
			keySet.add("id");
			keySet.add("processStrainsCode");
			keySet.add("processStrainsText");
			keySet.add("outputValueForecast");
			keySet.add("outputValueActual");
			keySet.add("yieldForecast");
			keySet.add("yieldActual");
			keySet.add("dateTime");
			
			map.put("Sql", selectBuffer.toString());
			
			resultModel = mfProcessYieldUntBll.getListBySQL(map);
			
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, String> pmap = new HashMap<String, String>();
			pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
			pmap.put("hasCurrent", "true");
			pmap.put("pastNum", "5");
			pmap.put("afterNum", "0");

			resultModel = TimesView.getTimesData(pmap, modelList, keySet);
			List<Map<String, Object>> modelLists =CastObjectUtil.cast(resultModel.getData());
			List<Object> productNameList = new ArrayList<Object>();
			for(Map<String, Object> YmodelLists :modelLists ){
				 Date dateTime=sdf.parse(String.valueOf(YmodelLists.get("dateTime")));
				 if(dateTime.before(nowDate)){
					 productNameList.add(YmodelLists.get("outputValueActual"));
				}else{
					productNameList.add(YmodelLists.get("outputValueForecast"));
					
				}
			}
			productMap.put("values", productNameList);
			yproductNameList.add(productMap);
				
			}
		}
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("	SELECT                                                                  ");
		selectBuffer2.append("		SUM(IFNULL(A.output_value_forecast, 0)) outputValueForecast,                          ");
		selectBuffer2.append("		SUM(IFNULL(A.output_value_actual, 0)) outputValueActual,                        ");
		selectBuffer2.append("		SUM(IFNULL(A.yield_forecast, 0)) yieldForecast,                ");
		selectBuffer2.append("		SUM(IFNULL(A.yield_actual, 0)) yieldActual,                         ");
		selectBuffer2.append("		A.date_time dateTime                                                ");
		selectBuffer2.append("	FROM                                                                    ");
		selectBuffer2.append("		mf_process_yield A                                                  ");
		selectBuffer2.append("		GROUP BY A.date_time                                                 ");
		
		Set<String> keySet = new HashSet<String>();
		keySet.add("outputValueForecast");
		keySet.add("outputValueActual");
		keySet.add("yieldForecast");
		keySet.add("yieldActual");
		keySet.add("dateTime");
		map2.put("Sql", selectBuffer2.toString());
		resultModel2 = mfProcessYieldUntBll.getListBySQL(map2);
		List<Map<String, Object>> modelList2 = (List<Map<String, Object>>) resultModel2.getData();
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");
		resultModel2 = TimesView.getTimesData(pmap, modelList2, keySet);
		List<Map<String, Object>> sumMap = (List<Map<String, Object>>) resultModel2.getData();
		
	        List<Object> actualList= new ArrayList<Object>();//总产量
	        List<Object> xDate= new ArrayList<Object>();
		for(Map<String, Object> sumMapList : sumMap){
			xDate.add(sumMapList.get("dateTime"));
			 Date dateTime=sdf.parse(String.valueOf(sumMapList.get("dateTime")));
			 if(dateTime.before(nowDate)){
				 actualList.add( sumMapList.get("outputValueActual"));
			}else{
				actualList.add(sumMapList.get("outputValueForecast"));
				
			}
			
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
				map.put("sumActualListY", actualList);//产量
				map.put("xDate", xDate);//x轴时间
				map.put("yproductNameList", yproductNameList);
		resultModel.setData(map);
		return resultModel;
	}
}



