package com.zee.app.extend.swagger.da;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.da.DaIotMonitorBasicGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.set.enumer.StatusEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.TimesView;

import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaIotMonitorBasicGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daIotMonitorBasic")
public class  DaIotMonitorBasicSwgApp extends  DaIotMonitorBasicGenSwgApp {

	@ApiOperation(value = "查询物联网设备", notes = "根据查询物联网设备")
	@RequestMapping(value = "/getBascs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select basc_code,basc_name from da_iot_monitor_basic group by basc_code ");
        
		map.put("Sql", selectBuffer.toString());

		resultModel = daIotMonitorBasicUntBll.getListBySQL(map);

		return resultModel;
	}
	
}



