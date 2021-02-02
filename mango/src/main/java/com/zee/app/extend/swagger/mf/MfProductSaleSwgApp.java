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

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfProductSaleGenSwgApp;
import com.zee.bll.extend.unity.da.DaGrowYieldUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.TimesView;


/**
 * @author lxl
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  产销监测-产销分析
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfProductSale")
public class  MfProductSaleSwgApp extends  MfProductSaleGenSwgApp {
	@Autowired
	private  GpDictionaryUntBll  gpDictionaryUntBll;
	
	@Autowired
	private  DaGrowYieldUntBll daGrowYieldUntBll;
	
	/**
	 * 
	 * @title: forecast
	 * @description: 产销预测
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
		selectBuffer.append("	SELECT                                                                    ");
		selectBuffer.append("		A.id id,                                                              ");
		selectBuffer.append("		IFNULL(A.yield_actual,0) yieldActual,                                 ");
		selectBuffer.append("		IFNULL(A.yield_forecast,0) yieldForecast,                             ");
		selectBuffer.append("		IFNULL(A.sale_actual,0) saleActual,                                   ");
		selectBuffer.append("		IFNULL(A.sale_forecast,0) saleForecast,                               ");
		selectBuffer.append("		A.date_time dateTime                                                  ");
		selectBuffer.append("	FROM                                                                      ");
		selectBuffer.append("		mf_product_sale A                                                     ");

		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("yieldActual");
		keySet.add("yieldForecast");
		keySet.add("saleActual");
		keySet.add("saleForecast");
		keySet.add("dateTime");
		map.put("Sql", selectBuffer.toString());
		resultModel = mfProductSaleUntBll.getListBySQL(map);
		
		List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
		List<HashMap<String, Object>> xmap = new ArrayList<HashMap<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "2");
		
		resultModel = TimesView.getTimesData(pmap, modelList,keySet);
		List<Map<String, Object>> modelLists =CastObjectUtil.cast(resultModel.getData());
		List<String> dateList = new ArrayList<String>();
		List<Object> yieldList = new ArrayList<Object>();
		List<Object> saleList = new ArrayList<Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        Date nowDate=sdf.parse(sdf.format(date));
		for(Map<String, Object> modelMap:modelLists){
			 Date dateTime=sdf.parse(String.valueOf(modelMap.get("dateTime")));
			 dateList.add((String) modelMap.get("dateTime"));
			if(dateTime.before(nowDate)){
				yieldList.add(modelMap.get("yieldActual"));
				saleList.add(modelMap.get("saleActual"));
			}else{
				yieldList.add(modelMap.get("yieldForecast"));
				saleList.add(modelMap.get("saleForecast"));
			}
		}
		maps.put("dateTime", dateList);//时间轴
		maps.put("yieldY", yieldList);//产量
		maps.put("saleY", saleList);//销量
		xmap.add((HashMap<String, Object>) maps);
		resultModel.setData(xmap);
		return resultModel;
	}
	
	/**
	 * 
	 * @title: getTopData
	 * @description: 华坪芒果销售地区TOP10
	 * @author: lxl
	 * @date:2018年5月23日 下午11:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getTopData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTopData() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		String year = "";// 年份
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
			if (jsonObject.containsKey("entitySale")) {
				JSONObject entitySaleObject = jsonObject.getJSONObject("entitySale");
				if (entitySaleObject.containsKey("year")
						&& !StringUtils.isBlank(entitySaleObject.getString("year"))) {
					year = entitySaleObject.getString("year");

				}
			}
		}
		//鲜果销售量top10地区
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select dsf.sale_amount_unit AS saleAmount,dsf.sale_region_text as saleRegionText,MAX(scf.add_time) add_time")
					.append(" FROM")
					.append(" da_sale_fresh dsf,da_common_field scf")
					.append(" WHERE")
					.append(" dsf.common_field_id = scf.id")
					.append(" and  year(scf.start_time) ='"+year+"'")
					.append(" GROUP BY dsf.sale_region_text")
					.append(" ORDER BY saleAmount desc");
        map.put("Sql", selectBuffer.toString());
        
        //加工品销量TOP10地区
        Map<String, Object> map2 = new HashMap<String, Object>();
		StringBuffer selectBuffer2 = new StringBuffer();
		selectBuffer2.append("select dsp.sale_amount_unit AS saleAmount,dsp.sale_region_text as saleRegionText,MAX(scf.add_time) add_time")
					.append(" FROM")
					.append(" da_sale_process dsp,da_common_field scf")
					.append(" WHERE")
					.append(" dsp.common_field_id = scf.id")
					.append(" and  year(scf.start_time) ='"+year+"'")
					.append(" GROUP BY dsp.sale_region_text")
					.append(" ORDER BY saleAmount desc");
        map2.put("Sql", selectBuffer2.toString());
		resultModel = mfProductSaleUntBll.getListBySQL(map);
		resultModel2 = mfProductSaleUntBll.getListBySQL(map2);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("saleFresh", resultModel.getData());//鲜果销售量top10地区
		maps.put("saleProcess", resultModel2.getData());// 加工品销量TOP10地区
		list.add(maps);
		resultModel.setData(list);
		return resultModel;
	}
	
	/**
	 * 
	 * @title: getFreshProSaleRate
	 * @description: 鲜芒果不同品种产销率监测
	 * @author: lxl
	 * @date:2018年5月23日 下午11:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getFreshProSaleRate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getFreshProSaleRate() {
		ResultModel resultModel = new ResultModel();

//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//		String year = "";// 年份
//	//	String strainsCode="";//产品品种ID
//		if (!StringUtils.isBlank(jsonData)) {
//			JSONObject jsonObject = JSONObject.fromObject(jsonData);
//			if (jsonObject.containsKey("entitySale")) {
//				JSONObject entitySaleObject = jsonObject.getJSONObject("entitySale");
//				if (entitySaleObject.containsKey("year")
//						&& !StringUtils.isBlank(entitySaleObject.getString("year"))) {
//					year = entitySaleObject.getString("year");
//
//				}
//				if (entitySaleObject.containsKey("strainsCode")
//						&& !StringUtils.isBlank(entitySaleObject.getString("strainsCode"))) {
//					strainsCode = entitySaleObject.getString("strainsCode");
//
//				}
//			}
//		}
		
		//ResultModel result =  getDictionary("48690fc04089cb696dfad2c1780153a7");
		
		//获取种植面积最大品种
		ResultModel result =  getGrowAreaMaxCategory();
		
		List<Map<String, Object>> diList  =(List<Map<String, Object>>) result.getData();
		List<Object> objList = new ArrayList<Object>();
		for(int i=0;i<diList.size();i++){
			int code = (int) diList.get(i).get("code");
			String name = (String) diList.get(i).get("name");
			ResultModel result2 = getGrowYield(code);
			List<Map<String, Object>> list =(List<Map<String, Object>>) result2.getData();
		if(list.size()>0){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT sale.id ,sale.`months` as dateTime, sale.`months` as month ,sale.saleAmount,pr.productTotal,")
					.append(" IFNULL(round((sale.saleAmount / pr.productTotal)*100,2),0) AS proSaleRate ")
					.append(" FROM")
				    .append(" ( SELECT mf.id,DATE_FORMAT(dcf.start_time, '%Y') AS months,mf.sale_amount_unit AS saleAmount, MAX(dcf.add_time) add_time")
				    .append(" FROM da_sale_fresh mf,da_common_field dcf")
				    .append(" WHERE mf.common_field_id = dcf.id")
				    .append(" and dcf.data_time_type_code=1 ")
				    .append(" and (dcf.region_id='530723' or dcf.region_name  like '%华坪%') ")
				    .append(" AND mf.strains_code = '"+code+"'")
				    .append(" GROUP BY months")
				    .append(" ) sale")
				    .append(" LEFT JOIN (")
				    .append(" SELECT DATE_FORMAT(dcf.start_time, '%Y') AS months,mf.product_total_unit AS productTotal, MAX(dcf.add_time) add_time, mf.strains_text name  ")
				    .append(" FROM da_grow_yield mf,da_common_field dcf")
				    .append(" WHERE mf.common_field_id = dcf.id ")
				    .append(" and dcf.data_time_type_code=1 ")
				    .append(" and (dcf.region_id='530723' or dcf.region_name  like '%华坪%') ")
				    .append(" AND mf.strains_code = '"+code+"'")
				    .append(" GROUP BY months")
				    .append(") pr ON pr.`months` = sale.`months`");
        map.put("Sql", selectBuffer.toString());
        resultModel = mfProductSaleUntBll.getListBySQL(map);
        
        Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("month");
		keySet.add("saleAmount");
		keySet.add("productTotal");
		keySet.add("proSaleRate");
		keySet.add("dateTime");
		List<Map<String, Object>> modelLists  = CastObjectUtil.cast(resultModel.getData());
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");

		resultModel = TimesView.getTimesData(pmap, modelLists, keySet);
		List<Map<String, Object>> list2 =CastObjectUtil.cast(resultModel.getData());
		
		
		Map<String, Object> map2 = new HashMap<String,Object>();
		List<Object> mList = new ArrayList<Object>();
		List<Integer> proSaleRateList = new ArrayList<Integer>();
				for(int j=0;j<list2.size();j++){
					mList.add((String) list2.get(j).get("dateTime"));
					proSaleRateList.add((int) Double.parseDouble(String.valueOf(list2.get(j).get("proSaleRate"))));
				}
				map2.put("date", mList);
				map2.put("name", name);
				map2.put("proSaleRateList", proSaleRateList);
				objList.add(map2);
			}
		}
		resultModel.setData(objList.size()<8?objList:objList.subList(0, 8));
		return resultModel;
	}
	
	/**
	 * 获取种植面积最大4个芒果品种
	 * @return
	 */
	public ResultModel getGrowAreaMaxCategory(){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select t.strains_code as code,t.strains_text as name from (select A.strains_code,A.strains_text,sum(A.grow_area_unit) from da_grow_yield A ");
		selectBuffer.append(" inner join da_common_field B on A.common_field_id = B.id where A.crop_type_code = '1' and B.region_id = '530723' ");
		selectBuffer.append(" and A.strains_code <> '0' and A.strains_text <> '其他' group by A.strains_code order by sum(A.grow_area_unit) desc limit 0,4) t ");
		
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = daGrowYieldUntBll.getListBySQL(map);
		return resultModel;
		
	}
	
	
	public ResultModel getDictionary(String type){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("  SELECT text as name ,code  from gp_dictionary  ");
		selectBuffer.append("  where type_id ='"+type+"'  ");
		selectBuffer.append(" GROUP BY name ");
		
		map.put("Sql", selectBuffer.toString());
		ResultModel resultModel = gpDictionaryUntBll.getListBySQL(map);
		return resultModel;
		
	}
	public ResultModel getGrowYield(int code){
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				    .append(" SELECT DATE_FORMAT(dcf.start_time, '%m') AS months,mf.sale_amount_unit AS productTotal, MAX(dcf.add_time) add_time, mf.strains_text name  ")
				    .append(" FROM da_sale_fresh mf,da_common_field dcf")
				    .append(" WHERE mf.common_field_id = dcf.id AND mf.strains_code = '"+code+"' and mf.strains_code <> 0 ")
				    .append(" GROUP BY months");
        map.put("Sql", selectBuffer.toString());
		resultModel = mfProductSaleUntBll.getListBySQL(map);
		return resultModel;
	}
	/**
	 * 
	 * @title: getProcessProSaleRate
	 * @description: 芒果加工品产销率监测
	 * @author: lxl
	 * @date:2018年5月23日 下午11:22:00
	 * @param
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getProcessProSaleRate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getProcessProSaleRate() {
		ResultModel resultModel = new ResultModel();

//		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
//		if (StringUtils.isBlank(jsonData))
//			return resultModel;
//		String year = "";// 年份
////		String processStrainsCode="";//产品品种ID
//		if (!StringUtils.isBlank(jsonData)) {
//			JSONObject jsonObject = JSONObject.fromObject(jsonData);
//			if (jsonObject.containsKey("entitySale")) {
//				JSONObject entitySaleObject = jsonObject.getJSONObject("entitySale");
//				if (entitySaleObject.containsKey("year")
//						&& !StringUtils.isBlank(entitySaleObject.getString("year"))) {
//					year = entitySaleObject.getString("year");
//
//				}
////				if (entitySaleObject.containsKey("processStrainsCode")
////						&& !StringUtils.isBlank(entitySaleObject.getString("processStrainsCode"))) {
////					processStrainsCode = entitySaleObject.getString("processStrainsCode");
////
////				}
//			}
//		}
		
		ResultModel result =  getDictionary("817d9f61ddf66623ffd2cf55e0f107ea");;
		List<Map<String, Object>> diList  =(List<Map<String, Object>>) result.getData();
		List<Object> objList = new ArrayList<Object>();
		for(int i=0;i<diList.size();i++){
			int code = (int) diList.get(i).get("code");
			String name = (String) diList.get(i).get("name");
			ResultModel result2 = getProcess(code);
			List<Map<String, Object>> list =(List<Map<String, Object>>) result2.getData();
		if(list.size()>0){
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT pr.id,pr.`months` as dateTime, pr.months as month , sale.saleAmount,pr.productTotal,")
					.append(" IFNULL(round((sale.saleAmount / pr.productTotal)*100,2),0) AS proSaleRate")
					.append(" FROM")
				    .append("  (")
				    .append(" SELECT mf.id,DATE_FORMAT(dcf.start_time, '%Y') AS months,mf.product_total_unit AS productTotal")
				    .append(" FROM da_process mf,da_common_field dcf")
				    .append(" WHERE mf.common_field_id = dcf.id ")
				    .append(" and dcf.data_time_type_code=1 ")
				    .append(" and (dcf.region_id='530723' or dcf.region_name  like '%华坪%') ")
				    .append(" AND mf.process_strains_code = '"+code+"'")
				    .append(" GROUP BY months")
				    .append(" ) pr")
				    .append(" LEFT JOIN (")
				    .append(" SELECT mf.id,DATE_FORMAT(dcf.start_time, '%Y') AS months,mf.sale_amount_unit AS saleAmount")
				    .append(" FROM da_sale_process mf,da_common_field dcf")
				    .append(" WHERE mf.common_field_id = dcf.id")
				    .append(" and dcf.data_time_type_code=1 ")
				    .append(" and (dcf.region_id='530723' or dcf.region_name  like '%华坪%') ")
				    .append(" AND mf.process_strains_code = '"+code+"'")
				    .append(" GROUP BY months")
				    .append(" ) sale")
				    .append(" ON pr.`months` = sale.`months`");
        map.put("Sql", selectBuffer.toString());
		resultModel = mfProductSaleUntBll.getListBySQL(map);
		Set<String> keySet = new HashSet<String>();
		keySet.add("id");
		keySet.add("month");
		keySet.add("saleAmount");
		keySet.add("productTotal");
		keySet.add("proSaleRate");
		keySet.add("dateTime");
		List<Map<String, Object>> modelLists  = CastObjectUtil.cast(resultModel.getData());
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
		pmap.put("hasCurrent", "true");
		pmap.put("pastNum", "5");
		pmap.put("afterNum", "0");

		resultModel = TimesView.getTimesData(pmap, modelLists, keySet);
		List<Map<String, Object>> list2 =CastObjectUtil.cast(resultModel.getData());
		
		Map<String, Object> map2 = new HashMap<String,Object>();
		List<Object> mList = new ArrayList<Object>();
		List<Integer> proSaleRateList = new ArrayList<Integer>();
				for(int j=0;j<list2.size();j++){
					mList.add((String) list2.get(j).get("dateTime"));
					proSaleRateList.add((int) Double.parseDouble(String.valueOf(list2.get(j).get("proSaleRate"))));
				}
				map2.put("date", mList);
				map2.put("name", name);
				map2.put("proSaleRateList", proSaleRateList);
				objList.add(map2);
			}
		}
		resultModel.setData(objList.size()<8?objList:objList.subList(0, 8));
		
		return resultModel;
	}
	public ResultModel getProcess(int code){
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer
				    .append(" SELECT *  ")
				    .append(" FROM da_process mf,da_common_field dcf")
				    .append(" WHERE mf.common_field_id = dcf.id AND mf.process_strains_code = '"+code+"' and mf.process_strains_code <> 0")
				    ;
        map.put("Sql", selectBuffer.toString());
		 resultModel = mfProductSaleUntBll.getListBySQL(map);
		 return resultModel;
	}
/**
 * 
 * @title: getProSaleForecast
 * @description: 产销预测
 * @author: lxl
 * @date:2018年5月23日 下午11:22:00
 * @param
 * @return
 */
@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
@RequestMapping(value = "/getProSaleForecast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResultModel getProSaleForecast() {
	ResultModel resultModel = new ResultModel();

	String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
	if (StringUtils.isBlank(jsonData))
		return resultModel;
	Map<String, Object> map = new HashMap<String, Object>();
	StringBuffer selectBuffer = new StringBuffer();
	selectBuffer.append("SELECT p.year, IFNULL(yield,0) AS yield, IFNULL(sale,0) AS sale")
				.append(" FROM after_3_year_view p")
			    .append(" LEFT JOIN (")
			    .append(" SELECT DATE_FORMAT(dcf.start_time, '%Y') AS months,mf.yield AS yield,mf.sale")
			    .append(" FROM mf_product_sale mf,da_common_field dcf")
			    .append(" WHERE mf.common_field_id = dcf.id")
			    .append(" GROUP BY months")
			    .append(" ) m ON m.months = p.`year`")
			    .append(" ORDER BY p.`year`");
    map.put("Sql", selectBuffer.toString());
	resultModel = mfProductSaleUntBll.getListBySQL(map);
	return resultModel;
}


/**
 * 
 * @title: getProSaleStatus
 * @description: 产销现状
 * @author: lxl
 * @date:2018年5月23日 下午11:22:00
 * @param
 * @return
 */
@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
@RequestMapping(value = "/getProSaleStatus", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResultModel getProSaleStatus() {
	ResultModel resultModel = new ResultModel();

	String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
	if (StringUtils.isBlank(jsonData))
		return resultModel;
	Map<String, Object> map = new HashMap<String, Object>();
	StringBuffer selectBuffer = new StringBuffer();
	selectBuffer.append("SELECT t1.`year`,IFNULL(t1.productTotal,0) as productTotal,IFNULL(t2.saleAmount,0) as saleAmount,")
				.append(" IFNULL(round(((t1.productTotal-(SELECT m.productTotal FROM lately_5_year_view l")
				.append(" LEFT JOIN ( SELECT dsf.product_total_unit AS productTotal,DATE_FORMAT(scf.start_time, '%Y') AS startTime")
				.append(" FROM da_grow_yield dsf,da_common_field scf")
				.append(" WHERE dsf.common_field_id = scf.id")
				.append(" GROUP BY DATE_FORMAT(scf.start_time, '%Y')")
				.append(" ) m ON m.startTime = l. YEAR   where l.YEAR=t1.`year`-1 ))/")
				.append(" (SELECT m.productTotal FROM lately_5_year_view l LEFT JOIN (")
				.append(" SELECT dsf.product_total_unit AS productTotal,DATE_FORMAT(scf.start_time, '%Y') AS startTime")
				.append(" FROM da_grow_yield dsf,da_common_field scf WHERE dsf.common_field_id = scf.id")
				.append(" GROUP BY DATE_FORMAT(scf.start_time, '%Y')")
				.append(" ) m ON m.startTime = l. YEAR   where l.YEAR=t1.`year`-1 ))*100,2),0) as productGrowthRate,")
				.append(" IFNULL(round(((t2.saleAmount-(SELECT m.saleAmount FROM lately_5_year_view l")
				.append(" LEFT JOIN ( SELECT dsf.sale_amount_unit AS saleAmount, DATE_FORMAT(scf.start_time, '%Y') AS startTime")
				.append(" FROM da_sale_fresh dsf,da_common_field scf WHERE dsf.common_field_id = scf.id")
				.append(" GROUP BY DATE_FORMAT(scf.start_time, '%Y')")
				.append(" ) m ON m.startTime = l. YEAR where l.year=t2.`YEAR`-1))/(SELECT m.saleAmount")
				.append(" FROM lately_5_year_view l LEFT JOIN (")
				.append(" SELECT dsf.sale_amount_unit AS saleAmount,DATE_FORMAT(scf.start_time, '%Y') AS startTime")
				.append(" FROM da_sale_fresh dsf,da_common_field scf")
				.append(" WHERE dsf.common_field_id = scf.id")
				.append(" GROUP BY DATE_FORMAT(scf.start_time, '%Y')")
				.append(" ) m ON m.startTime = l. YEAR where l.year=t2.`YEAR`-1))*100,2),0) as saleGrowthRate")
				.append(" FROM")
				.append(" (SELECT")
				.append(" l.year as year,m.productTotal")
				.append(" FROM lately_5_year_view l")
				.append(" LEFT JOIN (SELECT")
				.append(" dsf.product_total_unit AS productTotal,")
				.append(" DATE_FORMAT(scf.start_time, '%Y') as  startTime")
				.append(" FROM da_grow_yield dsf,da_common_field scf")
				.append(" WHERE dsf.common_field_id = scf.id")
				.append(" GROUP BY DATE_FORMAT(scf.start_time, '%Y') ")
				.append(" ) m on m.startTime=l.year")
				.append(" ) t1")
				.append(" LEFT JOIN")
				.append(" (SELECT")
				.append(" l.year as year,m.saleAmount")
				.append(" FROM lately_5_year_view l")
				.append(" LEFT JOIN")
				.append(" (SELECT")
				.append(" dsf.sale_amount_unit AS saleAmount,")
				.append(" DATE_FORMAT(scf.start_time, '%Y') as  startTime")
				.append(" FROM da_sale_fresh dsf,da_common_field scf")
				.append(" WHERE dsf.common_field_id = scf.id")
				.append(" GROUP BY DATE_FORMAT(scf.start_time, '%Y') ")
				.append(" ) m on m.startTime=l.year")
				.append(" ) t2 on t1.year=t2.year");
    map.put("Sql", selectBuffer.toString());
	resultModel = mfProductSaleUntBll.getListBySQL(map);
	return resultModel;
}

/**
 * 
 * @title: getWeatherDate
 * @description: 气象角度
 * @author: lxl
 * @date:2018年5月23日 下午11:22:00
 * @param
 * @return
 */
@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
@RequestMapping(value = "/getWeatherDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResultModel getWeatherDate() {
	ResultModel resultModel = new ResultModel();

	String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
	if (StringUtils.isBlank(jsonData))
		return resultModel;
	Map<String, Object> map = new HashMap<String, Object>();
	StringBuffer selectBuffer = new StringBuffer();
	selectBuffer.append("SELECT p.`month`,IFNULL(num,0) as num")
				.append(" FROM cur_12_month_view p")
				.append(" LEFT JOIN (")
				.append(" SELECT DATE_FORMAT(s.add_time, '%Y-%m') AS months,sum(mit.warning) AS num,mit.weather_threshold")
				.append(" FROM da_common_field s,mf_weather mit")
				.append(" WHERE s.id = mit.common_field_id")
				.append(" GROUP BY months")
				.append(" ) m ON m.months = p.`month`")
				.append(" ORDER BY p.`month`");
    map.put("Sql", selectBuffer.toString());
	resultModel = mfProductSaleUntBll.getListBySQL(map);
	return resultModel;

}


/**
 * 
 * @title: getPriceDate
 * @description: 价格角度
 * @author: lxl
 * @date:2018年5月23日 下午11:22:00
 * @param
 * @return
 */
@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
@RequestMapping(value = "/getPriceDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResultModel getPriceDate() {
	ResultModel resultModel = new ResultModel();

	String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
	if (StringUtils.isBlank(jsonData))
		return resultModel;
	Map<String, Object> map = new HashMap<String, Object>();
	StringBuffer selectBuffer = new StringBuffer();
	selectBuffer.append("SELECT t2.id,t2.`months` as month,t2.`months` as dateTime, t2.per_price  as costPrice,t1.per_price as fieldPrice")
				.append(" FROM")
				.append(" (")
				.append(" SELECT DATE_FORMAT(s.start_time, '%Y') AS months,mit.per_price_unit AS per_price,")
				.append(" mit.price_type_code,mit.price_type_text, mit.id")
				.append(" FROM da_common_field s,da_market_price mit")
				.append(" WHERE s.id = mit.common_field_id and mit.price_type_code='8'")
				.append(" and (s.region_id='031000000' or s.region_name  like '%中国%') ")
				.append(" and s.data_time_type_code=1 ")
				.append(" and mit.strains_code=0 ")
				.append(" GROUP BY months")
				.append(") t2")
				.append(" LEFT JOIN (")
				.append(" SELECT")
				.append(" DATE_FORMAT(s.start_time, '%Y') AS months,")
				.append(" avg(mit.per_price_unit) AS per_price,")
				.append(" mit.price_type_code,")
				.append(" mit.id,")
				.append(" mit.price_type_text")
				.append(" FROM da_common_field s,da_market_price mit")
				.append(" WHERE s.id = mit.common_field_id and mit.price_type_code='2'")
				.append(" and s.data_time_type_code=1 ")
				.append(" GROUP BY months")
				.append(" ) t1")
				.append(" on t1.`months`=t2.months");
    map.put("Sql", selectBuffer.toString());
	resultModel = mfProductSaleUntBll.getListBySQL(map);
	Set<String> keySet = new HashSet<String>();
	keySet.add("id");
	keySet.add("month");
	keySet.add("costPrice");
	keySet.add("fieldPrice");
	keySet.add("dateTime");
	List<Map<String, Object>> modelLists  = CastObjectUtil.cast(resultModel.getData());
	Map<String, String> pmap = new HashMap<String, String>();
	pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
	pmap.put("hasCurrent", "true");
	pmap.put("pastNum", "5");
	pmap.put("afterNum", "0");

	resultModel = TimesView.getTimesData(pmap, modelLists, keySet);
	return resultModel;
	
}

/**
 * 
 * @title: getInternationalDate
 * @description: 国际角度
 * @author: lxl
 * @date:2018年5月23日 下午11:22:00
 * @param
 * @return
 */
@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
@RequestMapping(value = "/getInternationalDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResultModel getInternationalDate() {
	ResultModel resultModel = new ResultModel();

	String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
	if (StringUtils.isBlank(jsonData))
		return resultModel;
	Map<String, Object> map = new HashMap<String, Object>();
	StringBuffer selectBuffer = new StringBuffer();
	selectBuffer.append("SELECT t1.`year` month,t1.importAmount,t2.saleAmount")
	.append(" FROM(")
	.append(" SELECT p.`year`,IFNULL(import_amount, 0) AS importAmount")
	.append(" FROM lately_5_year_view p")
	.append(" LEFT JOIN (")
	.append(" SELECT DATE_FORMAT(s.start_time, '%Y') AS months,sum(mit.import_amount_unit) as import_amount")
	.append(" FROM da_common_field s,da_import_export mit")
	.append(" WHERE s.id = mit.common_field_id")
	.append(" GROUP BY months")
	.append(" ) m ON m.months = p.`year`")
	.append(" ORDER BY p.`year`")
	.append(" ) t1")
	.append(" LEFT JOIN (")
	.append(" SELECT p.`year`,IFNULL(sale_amount, 0) AS saleAmount")
	.append(" FROM lately_5_year_view p")
	.append(" LEFT JOIN (")
	.append(" SELECT DATE_FORMAT(s.start_time, '%Y') AS months,sum(mit.sale_amount_unit) as sale_amount")
	.append(" FROM da_common_field s,da_sale_fresh mit")
	.append(" WHERE s.id = mit.common_field_id")
	.append(" GROUP BY months")
	.append(" ) m ON m.months = p.`year`")
	.append(" ORDER BY p.`year`")
	.append(" ) t2 ON t1.`year` = t2.`year`");
//	selectBuffer.append("SELECT t1.`month`,t1.importAmount,t2.saleAmount")
//				.append(" FROM(")
//				.append(" SELECT p.`month`,IFNULL(import_amount, 0) AS importAmount")
//				.append(" FROM cur_12_month_view p")
//				.append(" LEFT JOIN (")
//				.append(" SELECT DATE_FORMAT(s.start_time, '%Y-%m') AS months,mit.import_amount_unit as import_amount")
//				.append(" FROM da_common_field s,da_import_export mit")
//				.append(" WHERE s.id = mit.common_field_id")
//				.append(" GROUP BY months")
//				.append(" ) m ON m.months = p.`month`")
//				.append(" ORDER BY p.`month`")
//				.append(" ) t1")
//				.append(" LEFT JOIN (")
//				.append(" SELECT p.`month`,IFNULL(sale_amount, 0) AS saleAmount")
//				.append(" FROM cur_12_month_view p")
//				.append(" LEFT JOIN (")
//				.append(" SELECT DATE_FORMAT(s.start_time, '%Y-%m') AS months,mit.sale_amount_unit as sale_amount")
//				.append(" FROM da_common_field s,da_sale_fresh mit")
//				.append(" WHERE s.id = mit.common_field_id")
//				.append(" GROUP BY months")
//				.append(" ) m ON m.months = p.`month`")
//				.append(" ORDER BY p.`month`")
//				.append(" ) t2 ON t1.`month` = t2.`month`");
    map.put("Sql", selectBuffer.toString());
	resultModel = mfProductSaleUntBll.getListBySQL(map);
	return resultModel;

}

/**
 * 
 * @title: getSupplyDemandDate
 * @description: 供求角度
 * @author: lxl
 * @date:2018年5月23日 下午11:22:00
 * @param
 * @return 
 */
@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
@RequestMapping(value = "/getSupplyDemandDate", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
public ResultModel getSupplyDemandDate() {
	ResultModel resultModel = new ResultModel();

	String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
	if (StringUtils.isBlank(jsonData))
		return resultModel;
//	String year = "";// 年份
//	if (!StringUtils.isBlank(jsonData)) {
//		JSONObject jsonObject = JSONObject.fromObject(jsonData);
//		if (jsonObject.containsKey("entitySale")) {
//			JSONObject entitySaleObject = jsonObject.getJSONObject("entitySale");
//			if (entitySaleObject.containsKey("year")
//					&& !StringUtils.isBlank(entitySaleObject.getString("year"))) {
//				year = entitySaleObject.getString("year");
//
//			}
//		}
//	}
	
	Map<String, Object> map = new HashMap<String, Object>();
	StringBuffer selectBuffer = new StringBuffer();
	selectBuffer.append("SELECT t1.id ,t1.`months` as month,t1.`months` as dateTime,t1.product_total as productTotal,t2.sale_amount as saleAmount")
				.append(" FROM")
				.append(" (")
				.append(" SELECT mit.id,DATE_FORMAT(s.start_time, '%Y') AS months, mit.product_total_unit as product_total")
				.append(" FROM da_common_field s,da_grow_yield mit")
				.append(" WHERE s.id = mit.common_field_id ")
				.append(" and (s.region_id='031000000' or s.region_name  like '%中国%') ")
				.append(" and s.data_time_type_code=1 ")
				.append(" and mit.strains_code=0 ")
				.append(" GROUP BY months")
				.append(" ) t1")
				.append(" LEFT JOIN (")
				.append(" SELECT DATE_FORMAT(s.start_time, '%Y') AS months,mit.sale_amount_unit as sale_amount")
				.append(" FROM da_common_field s,da_sale_fresh mit")
				.append(" WHERE s.id = mit.common_field_id and s.data_time_type_code='1'  and s.area_latitude_type_code='1' ")
				.append(" and (s.region_id='031000000' or s.region_name  like '%中国%') ")
				.append(" and s.data_time_type_code=1 ")
				.append(" and mit.strains_code=0 ")
				.append(" GROUP BY months")
				.append(" ) t2")
				.append(" on t1.`months`=t2.`months`");
    map.put("Sql", selectBuffer.toString());
	resultModel = mfProductSaleUntBll.getListBySQL(map);
	Set<String> keySet = new HashSet<String>();
	keySet.add("id");
	keySet.add("month");
	keySet.add("saleAmount");
	keySet.add("productTotal");
	keySet.add("dateTime");
	List<Map<String, Object>> modelLists  = CastObjectUtil.cast(resultModel.getData());
	Map<String, String> pmap = new HashMap<String, String>();
	pmap.put("viewName", "year");//视图名，年year，月month，日date（默认为年）
	pmap.put("hasCurrent", "true");
	pmap.put("pastNum", "5");
	pmap.put("afterNum", "0");

	resultModel = TimesView.getTimesData(pmap, modelLists, keySet);
	return resultModel;
	
}
}
