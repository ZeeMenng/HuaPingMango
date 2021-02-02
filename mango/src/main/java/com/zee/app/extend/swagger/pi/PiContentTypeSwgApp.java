package com.zee.app.extend.swagger.pi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiContentTypeGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS内容类型表 对外接口，扩展自PiContentTypeGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piContentType")
public class PiContentTypeSwgApp extends PiContentTypeGenSwgApp {

	@ApiOperation(value = "查询", notes = "查询pi_content_type表数据")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "PiContentTypeGetList") })
	@RequestMapping(value = "/getContentTypeList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getContentTypeList() {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select t.id id,t.name name from pi_content_type t where 1=1 ");
        
		map.put("Sql", selectBuffer.toString());
		
		resultModel = piContentTypeUntBll.getListBySQL(map);
		
		List<Map<String, Object>> cList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> cTypeList = (List<Map<String, Object>>) resultModel.getData();
		for (Map<String, Object> cTypeMap : cTypeList) {
			Map<String, Object> cMap = new HashMap<String, Object>();
			if(!CustomSymbolic.CONTENT_TYPE_IMAGETEXT.equals(cTypeMap.get("id"))
					&& !CustomSymbolic.CONTENT_TYPE_FOCUS.equals(cTypeMap.get("id"))
					&& !CustomSymbolic.CONTENT_TYPE_COMMON.equals(cTypeMap.get("id"))) {
				cMap.put("id", cTypeMap.get("id"));
				cMap.put("name", cTypeMap.get("name"));
				cList.add(cMap);
			}
		}
		resultModel.setData(cList);
		return resultModel;
	}
}



