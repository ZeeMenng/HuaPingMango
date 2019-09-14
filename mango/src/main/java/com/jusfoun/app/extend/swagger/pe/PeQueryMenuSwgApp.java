package com.jusfoun.app.extend.swagger.pe;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jusfoun.app.generate.swagger.pe.PeQueryMenuGenSwgApp;
import com.jusfoun.bll.extend.unity.pe.PeQueryMenuUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.utl.SymbolicConstant;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-7-2 16:58:02
 * @description 数据版门户-数据资源-查询菜单 对外接口，扩展自PeQueryMenuGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pe/peQueryMenu")
public class  PeQueryMenuSwgApp extends  PeQueryMenuGenSwgApp {

	@Autowired
	protected PeQueryMenuUntBll peQueryMenuUntBll;
	
	@ApiOperation(value = "数据服务版门户-数据资源-获取查询菜单", notes = "数据服务版门户-数据资源-获取查询菜单")
	@RequestMapping(value = "/getQueryMenuList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getQueryMenuList() {
		ResultModel resultModel = new ResultModel();

		String menuRelationId = null;
		
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("menuRelationId")
						&& StringUtils.isNotBlank(entityRelatedObject.getString("menuRelationId"))) {
					menuRelationId = entityRelatedObject.getString("menuRelationId");
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		/*SELECT 
		A.`id` AS id,
		A.farther_id AS pId,
		A.menu_name AS menuName,
		A.`region_id` AS regionId,
		A.`data_time_type_code` AS dimension,
		A.`strains_code` AS breed,
		A.`price_type_code` AS priceType,
		A.`price_unit_code` AS priceUnit,
		A.query_type AS queryType
		FROM pe_query_menu A 
		WHERE relation_id LIKE '03%' ORDER BY relation_id;*/
		
		selectBuffer.append(" SELECT                                                    															");
		selectBuffer.append(" A.`id` AS id,																											");
		selectBuffer.append(" A.farther_id AS pId,																									");
		selectBuffer.append(" A.menu_name AS name,																									");
		selectBuffer.append(" A.`region_id` AS regionId,																							");
		selectBuffer.append(" A.`data_time_type_code` AS dimension,																					");
		selectBuffer.append(" A.`strains_code` AS breed,																							");
		selectBuffer.append(" A.`price_type_code` AS priceType,																						");
		selectBuffer.append(" A.`price_unit_code` AS priceUnit,																						");
		selectBuffer.append(" A.`price_unit_text` AS priceUnitText,																					");
		selectBuffer.append(" A.query_type AS queryType																								");
		selectBuffer.append(" FROM pe_query_menu A 																									");
		selectBuffer.append(" WHERE relation_id LIKE 																								");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(menuRelationId)) {
			selectBuffer.append(menuRelationId);
		}
		selectBuffer.append("%'");
		selectBuffer.append(" AND relation_id != 																									");
		selectBuffer.append("'");
		if(StringUtils.isNotBlank(menuRelationId)) {
			selectBuffer.append(menuRelationId);
		}
		selectBuffer.append("'");
		selectBuffer.append("  ORDER BY relation_id																									");

		map.put("Sql", selectBuffer.toString());
		resultModel = peQueryMenuUntBll.getListBySQL(map);
		
		return resultModel;
	}
}



