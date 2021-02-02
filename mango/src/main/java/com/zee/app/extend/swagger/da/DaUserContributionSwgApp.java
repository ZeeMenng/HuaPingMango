package com.zee.app.extend.swagger.da;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaUserContributionGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.service.DaUserContributionUtil;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-19 16:50:20
 * @description 用户贡献 对外接口，扩展自DaUserContributionGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daUserContribution")
public class  DaUserContributionSwgApp extends  DaUserContributionGenSwgApp {

	@Autowired
	protected DaUserContributionUtil daUserContributionUtil;
	
	@ApiOperation(value = "更新用户贡献度", notes = "更新用户贡献度")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaUserContribution") })
	@RequestMapping(value = "/updateUserContribution/{type}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateUserContribution(@PathVariable("type")String type) {
		
		ResultModel resultModel = new ResultModel();
		daUserContributionUtil.updateUserContribution(type);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "查询我的贡献", notes = "查询我的贡献")
	@RequestMapping(value = "/getMyContribution", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMyContribution() {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                       ");
		selectBuffer.append("		A.id id,                                                 ");
		selectBuffer.append("		A.price_times priceTimes,                                ");
		selectBuffer.append("		A.price_num priceNum,                                    ");
		selectBuffer.append("		A.yield_times yieldTimes,                                ");
		selectBuffer.append("		A.yield_num yieldNum,                                    ");
		selectBuffer.append("		A.farm_oper_times farmOperTimes,                         ");
		selectBuffer.append("		A.farm_oper_num farmOperNum,                             ");
		selectBuffer.append("		A.user_id userId,                                        ");
		selectBuffer.append("		A.add_time addTime,                                      ");
		selectBuffer.append("		A.update_time updateTime                                 ");
		selectBuffer.append("	FROM                                                         ");
		selectBuffer.append("		da_user_contribution A                                   ");
		selectBuffer.append("	WHERE                                                        ");
		selectBuffer.append("		user_id = '" + this.getCurrentUser().getId() + "'        ");
		map.put("Sql", selectBuffer.toString());

		resultModel = daUserContributionUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		if(modelList != null && modelList.size() > 0){
			resultModel.setData(modelList.get(0));
		}else{
			resultModel.setResultMessage("您还没有任何贡献度");
		}
		return resultModel;
	}
	
}



