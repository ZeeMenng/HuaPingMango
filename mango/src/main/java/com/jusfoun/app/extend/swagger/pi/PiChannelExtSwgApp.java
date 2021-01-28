package com.zee.app.extend.swagger.pi;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiChannelExtGenSwgApp;
import com.zee.ent.custom.ResultModel;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS栏目内容表 对外接口，扩展自PiChannelExtGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piChannelExt")
public class PiChannelExtSwgApp extends PiChannelExtGenSwgApp {

	@ApiOperation(value = "查询", notes = "查询pi_channel_ext表信息")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串，查询参数", required = true, dataType = "PiContentTypeGetList") })
	@RequestMapping(value = "/getChannelExtList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getChannelExtList() {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select e.channel_id id,e.channel_name name from pi_channel_ext e where 1=1 ");
        
		map.put("Sql", selectBuffer.toString());

		resultModel = piChannelExtUntBll.getListBySQL(map);

		return resultModel;
	}
}



