package com.zee.app.extend.swagger.da;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaEnterpriseDistributionGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.utl.SymbolicConstant;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-25 10:47:17
 * @description 芒果相关企业分布 对外接口，扩展自DaEnterpriseDistributionGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daEnterpriseDistribution")
public class  DaEnterpriseDistributionSwgApp extends  DaEnterpriseDistributionGenSwgApp {
	/**
	 * 芒果地图
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();
		ResultModel resultModel2 = new ResultModel();
		ResultModel resultModel3 = new ResultModel();
		ResultModel resultModel4 = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> map2 = new HashMap<String, Object>();
		Map<String, Object> map3 = new HashMap<String, Object>();
		Map<String, Object> map4 = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		//芒果相关企业分布
//		selectBuffer.append("select ")
//		.append("A.longitude longitude, ")
//		.append("A.region_text regionText, ")
//		.append("A.latitude latitude, ")
//		.append("COUNT(1) entNumber ")
//		.append("FROM ")
//		.append("da_enterprise_distribution A ")
//		.append("WHERE 1=1 ")
//		.append("GROUP BY A.region_code ");
		selectBuffer.append("select ")
		.append("A.measure_longitude longitude, ")
		.append("A.region_code regionCode, ")
		.append("A.region_text regionText, ")
		.append("A.measure_latitude latitude, ")
		.append("COUNT(1) entNumber ")
		.append("FROM ")
		.append("da_enterprise_info A ")
		.append("WHERE 1=1 ")
		.append("GROUP BY A.region_code ");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daEnterpriseDistributionUntBll.getListBySQL(map);
		
		StringBuffer selectBuffer2 = new StringBuffer();
		//芒果电商分布
		selectBuffer2.append("select ")
		.append("A.longitude longitude, ")
		.append("A.region_code regionCode, ")
		.append("A.region_text regionText, ")
		.append("A.latitude latitude, ")
		.append("COUNT(1) entNumber ")
		.append("FROM ")
		.append("da_online_retailers A ")
		.append("WHERE 1=1 ")
		.append("GROUP BY A.region_code ");
		map2.put("Sql", selectBuffer2.toString());
		
		resultModel2 = daEnterpriseDistributionUntBll.getListBySQL(map2);
		StringBuffer selectBuffer3 = new StringBuffer();
		//芒果主产区分布
		selectBuffer3.append("select ")
		.append("A.longitude longitude, ")
		.append("A.region_text regionText, ")
		.append("A.content content, ")
		.append("A.latitude latitude ")
		.append("FROM ")
		.append("da_main_production_area A ");
		map3.put("Sql", selectBuffer3.toString());
	
		resultModel3 = daEnterpriseDistributionUntBll.getListBySQL(map3);
		StringBuffer selectBuffer4 = new StringBuffer();
		//芒果批发市场分布
		selectBuffer4.append("select ")
		.append("A.region_text regionText, ")
		.append("A.name name, ")
		.append("A.content content, ")
		.append("case when A.cover_area is null then '' else A.cover_area end coverArea, ")
		.append("case when A.merchants_num is null then '' else A.merchants_num end merchantsNum, ")
		.append("case when A.stalls_num is null then '' else A.stalls_num end stallsNum, ")
		.append("case when A.days_volume is null then '' else A.days_volume end daysVolume, ")
		.append("case when A.years_amount is null then '' else A.years_amount end yearsAmount, ")
		.append("case when A.tel is null then '' else A.tel end tel, ")
		.append("case when A.address is null then '' else A.address end Address, ")
		.append("A.longitude longitude, ")
		.append("A.latitude latitude ")
		.append("FROM ")
		.append("da_wholesale_market A ");
		map4.put("Sql", selectBuffer4.toString());
		
		
		
		resultModel4 = daEnterpriseDistributionUntBll.getListBySQL(map4);
		
		Map<String, Object> maps = new HashMap<String,Object>();
		maps.put("enterpriseDistribution", resultModel.getData());//芒果相关企业分布
		maps.put("onlineRetailers", resultModel2.getData());//芒果电商分布
		maps.put("mainProductionArea", resultModel3.getData());//芒果主产区分布
		maps.put("wholesaleMarket", resultModel4.getData());//芒果批发市场分布
		resultModel.setData(maps);
		return resultModel;
	}

}



