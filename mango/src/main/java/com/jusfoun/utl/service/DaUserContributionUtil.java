package com.zee.utl.service;

import io.swagger.annotations.ApiOperation;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.zee.app.generate.swagger.base.BaseSwgApp;
import com.zee.bll.extend.unity.da.DaUserContributionUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaUserContribution;
import com.zee.utl.BeanUtil;
import com.zee.utl.SymbolicConstant;

@Component
public class DaUserContributionUtil extends BaseSwgApp {
	
	@Autowired
	@Qualifier("daUserContributionUntBll")
	protected DaUserContributionUntBll daUserContributionUntBll;
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "更新用户贡献度", notes = "更新用户贡献度")
	public void updateUserContribution(String type) {
		
		String userId = this.getCurrentUser().getId();
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                                  ");
		selectBuffer.append("		A.id id,                                                            ");
		selectBuffer.append("		A.price_times priceTimes,                                           ");
		selectBuffer.append("		A.price_num priceNum,                                               ");
		selectBuffer.append("		A.yield_times yieldTimes,                                           ");
		selectBuffer.append("		A.yield_num yieldNum,                                               ");
		selectBuffer.append("		A.farm_oper_times farmOperTimes,                                    ");
		selectBuffer.append("		A.farm_oper_num farmOperNum,                                        ");
		selectBuffer.append("		A.user_id userId,                                                   ");
		selectBuffer.append("		A.add_time addTime,                                                 ");
		selectBuffer.append("		A.update_time updateTime                                            ");
		selectBuffer.append("	FROM                                                                    ");
		selectBuffer.append("		da_user_contribution A							                    ");
		selectBuffer.append("	WHERE                                                                   ");
		selectBuffer.append("		A.user_id = '"+userId+"'                                            ");
		map.put("Sql", selectBuffer.toString());

		ResultModel resultModel = daUserContributionUntBll.getListBySQL(map);
		List<Map<String, Object>> modelList = (List<Map<String, Object>>) resultModel.getData();
		
		if(SymbolicConstant.CONTRIBUTION_PRICE.equals(type)){
			update("priceTimes", "priceNum", modelList);
		}else if(SymbolicConstant.CONTRIBUTION_YIELD.equals(type)){
			update("yieldTimes", "yieldNum", modelList);
		}else if(SymbolicConstant.CONTRIBUTION_FARM_OPER.equals(type)){
			update("farmOperTimes", "farmOperNum", modelList);
		}
	}
	
	private void update(String times, String num, List<Map<String, Object>> modelList) {
		try {
			int timesInt = 1;
			int numInt = 1;
			DaUserContribution bean = new DaUserContribution();
			bean.setUserId(this.getCurrentUser().getId());
			if(modelList != null && modelList.size() > 0){
				Map<String, Object> map2 = modelList.get(0);
				timesInt = (int) map2.get(times);
				timesInt ++;
				numInt = (int) map2.get(num);
				numInt ++;
				bean = (DaUserContribution) BeanUtil.map2Bean(map2, DaUserContribution.class);
			}
			Field timesField = bean.getClass().getSuperclass().getDeclaredField(times);
			timesField.setAccessible(true);
			timesField.set(bean, timesInt);
			Field numField = bean.getClass().getSuperclass().getDeclaredField(num);
			numField.setAccessible(true);
			numField.set(bean, numInt);
			
			if(StringUtils.isNotBlank(bean.getId())){
				daUserContributionUntBll.update(bean);
			}else{
				daUserContributionUntBll.add(bean);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
