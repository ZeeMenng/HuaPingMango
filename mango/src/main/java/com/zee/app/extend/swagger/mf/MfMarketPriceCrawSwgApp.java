package com.zee.app.extend.swagger.mf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.mf.MfMarketPriceCrawGenSwgApp;
import com.zee.bll.extend.unity.da.DaMarketPriceUntBll;
import com.zee.bll.extend.unity.mf.MfMarketPriceCrawUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.utl.SymbolicConstant;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 14:13:29
 * @description  对外接口，扩展自MfMarketPriceCrawGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfMarketPriceCraw")
public class  MfMarketPriceCrawSwgApp extends  MfMarketPriceCrawGenSwgApp {

	@Autowired
	@Qualifier("mfMarketPriceCrawUntBll")
	protected MfMarketPriceCrawUntBll mfMarketPriceCrawUntBll;
	
	@Autowired
	@Qualifier("daMarketPriceUntBll")
	protected DaMarketPriceUntBll daMarketPriceUntBll;
	
	
	@ApiOperation(value = "查询", notes = "市场价格-波动预测-近一月平均价地图")
	@RequestMapping(value = "/getPriceAvgMap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceAvgMap() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		
	 	//查询近一月平均价地图
		StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append(" select T.region_id,T.region_name,round(avg(T.average_price_unit),2) average_price_unit from (select B.region_id,B.region_name,");
			selectBuffer.append(" A.average_price_unit from da_market_price A INNER JOIN da_common_field B ON A.common_field_id = B.id WHERE ");
			selectBuffer.append(" B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 and A.price_type_code = '2' ");
			selectBuffer.append(" and substr(B.start_time,1,7) = (select max(substr(B.start_time,1,7)) from da_market_price A INNER JOIN da_common_field B ");
			selectBuffer.append(" ON A.common_field_id = B.id WHERE B.data_time_type_code = 5 and A.crop_type_code = '1' and B.area_latitude_type_code = 3 ");
			selectBuffer.append(" and A.price_type_code = '2' and A.average_price_unit is not null) order by B.add_time desc) T group by T.region_id ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daMarketPriceUntBll.getListBySQL(map);
			
			
		return resultModel;
	}
}



