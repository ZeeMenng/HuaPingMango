package com.jusfoun.app.extend.swagger.mf;

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

import com.jusfoun.app.generate.swagger.mf.MfFormatrixGenSwgApp;
import com.jusfoun.bll.extend.unity.mf.MfFormatrixUntBll;
import com.jusfoun.bll.extend.unity.mf.MfMarketPriceCrawUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.ApiOperation;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-25 17:07:14
 * @description 波士顿矩阵 对外接口，扩展自MfFormatrixGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/mf/mfFormatrix")
public class  MfFormatrixSwgApp extends  MfFormatrixGenSwgApp {
	
	@Autowired
	@Qualifier("mfFormatrixUntBll")
	protected MfFormatrixUntBll mfFormatrixUntBll;
	
	@ApiOperation(value = "查询", notes = "市场价格-价格预测-波士顿矩阵图")
	@RequestMapping(value = "/getPriceMatrix", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPriceMatrix() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		
	 	//波士顿矩阵图
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select t.price_change increase,t.return_change speed,t2.code districtId,t2.name districtName from mf_formatrix t, ");
		selectBuffer.append(" (SELECT t.code,t.name FROM gp_region t WHERE (t.farther_code = '031000000')) t2 ");
		selectBuffer.append(" where t.region_name = t2.name and t2.name like '%'||t.region_name||'%' ");
		map.put("Sql", selectBuffer.toString());
		resultModel = mfFormatrixUntBll.getListBySQL(map);
			
		return resultModel;
	}
	
}



