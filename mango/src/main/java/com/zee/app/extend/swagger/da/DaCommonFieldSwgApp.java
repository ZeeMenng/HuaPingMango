package com.zee.app.extend.swagger.da;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zee.app.generate.swagger.da.DaCommonFieldGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaCommonField;
import com.zee.set.enumer.DictionaryEnum;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-5-22 15:09:45
 * @description  对外接口，扩展自DaCommonFieldGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daCommonField")
public class  DaCommonFieldSwgApp extends  DaCommonFieldGenSwgApp {
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "DaCommonField") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody DaCommonField jsonData) {
		ResultModel result = daCommonFieldUntBll.add(jsonData);

		return result;
	}
	
	@ApiOperation(value = "数据采集系统-首页", notes = "数据采集系统-首页—总数据量查询")
	@RequestMapping(value = "/getTotalNumByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;
		Map<String, Object> map;
		StringBuffer selectBuffer;
		Map<String, List<Map<String, Object>>> maps;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		//直报数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("select round(count(*)/10000,2) as DirectTotal from da_common_field ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		maps = new HashMap<String, List<Map<String, Object>>>();
		maps.put("DirectTotal", (List<Map<String, Object>>) resultModel.getData());
		
		for (Map<String, Object> map1 : maps.get("DirectTotal")) {
			dataMap.put("DirectTotal", map1.get("DirectTotal"));
		}
		//舆情数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("select t1.ThemeNum,t3.SentimentTotal from (select count(*) as ThemeNum from da_sentiment_theme) t1, ");
		selectBuffer.append("(select round(count(*)/10000,2) as SentimentTotal from da_sentiment_article) t3 ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		maps = new HashMap<String, List<Map<String, Object>>>();
		maps.put("ThemeNum", (List<Map<String, Object>>) resultModel.getData());

		for (Map<String, Object> map1 : maps.get("ThemeNum")) {
			dataMap.put("ThemeNum", map1.get("ThemeNum"));
			dataMap.put("SentimentTotal", map1.get("SentimentTotal"));
		}
		
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("select key_name KeyName from da_sentiment_theme where key_name is not null");
		map.put("Sql", selectBuffer.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		maps = new HashMap<String, List<Map<String, Object>>>();
		maps.put("KeyName", (List<Map<String, Object>>) resultModel.getData());

		int i = 0;
		String[] keynameArr;
		for (Map<String, Object> map1 : maps.get("KeyName")) {
			keynameArr = map1.get("KeyName").toString().split("，");
			i += keynameArr.length;
		}
		dataMap.put("KeywordNum", i);

		dataList.add(dataMap);
		resultModel.setData(dataList);

		return resultModel;
	}

	@ApiOperation(value = "数据采集系统-首页", notes = "数据采集系统-首页—数据总量走势")
	@RequestMapping(value = "/getTrendByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getTrendByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map;
		StringBuffer selectBuffer;
		Map<String, List<Map<String, Object>>> maps;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		
		//时间轴查询
		Date monthTime = DateUtils.getMonthTime(-1);
		List<String> timesList = DateUtils.findDates(monthTime, DateUtils.getCurrentDate());
		String beginTime = DateUtils.date2String(monthTime, CustomSymbolic.DATE_FORMAT);
		String endTime = DateUtils.getCurrentDateStr();
		
		//舆情数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("select count(*) ThemeDay,substr(add_time,1,10) times from da_sentiment_article  ");
		selectBuffer.append("where add_time >= '"+beginTime+"' AND add_time <= '"+endTime+"' group by substr(add_time,1,10)");
		List<Map<String, Object>> themeList = jdbcTemplate.queryForList(selectBuffer.toString());
		Map<String, List<Map<String, Object>>> themeMaps = new HashMap<String, List<Map<String, Object>>>();
		themeMaps.put("Theme", themeList);
		
		//物联网数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("select count(*) MonitorDay,substr(`time`,1,10) times from da_iot_monitor_data ");
		selectBuffer.append("where `time` >= '"+beginTime+"' AND `time` <= '"+endTime+"' group by substr(`time`,1,10)");
		List<Map<String, Object>> monitorList = jdbcTemplate.queryForList(selectBuffer.toString());
		Map<String, List<Map<String, Object>>> monitorMaps = new HashMap<String, List<Map<String, Object>>>();
		monitorMaps.put("Monitor", monitorList);
		
		//直报数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("select count(*) DirectDay,substr(add_time,1,10) times from da_common_field   ");
		selectBuffer.append("where add_time >= '"+beginTime+"' AND add_time <= '"+endTime+"' group by substr(add_time,1,10)");
		List<Map<String, Object>> directList = jdbcTemplate.queryForList(selectBuffer.toString());
		Map<String, List<Map<String, Object>>> directMaps = new HashMap<String, List<Map<String, Object>>>();
		directMaps.put("Direct", directList);

		//数据资源
		for(int i = 0;i < timesList.size(); i++){
			String times = timesList.get(i);
			int val;
			int DirectDay = 0;
			int ThemeDay = 0;
			int MonitorDay = 0;
			for (Map<String, Object> map1 : directMaps.get("Direct")) {
				String DirectTimes = map1.get("times").toString();
				if(DirectTimes.equals(times)){
					DirectDay =  Integer.valueOf( map1.get("DirectDay").toString());
					break;
				}
			}
			for (Map<String, Object> map2 : themeMaps.get("Theme")) {
				String ThemeTimes = map2.get("times").toString();
				if(ThemeTimes.equals(times)){
					ThemeDay = Integer.valueOf( map2.get("ThemeDay").toString());
					break;
				}
			}
			for (Map<String, Object> map3 : monitorMaps.get("Monitor")) {
				String monitorTimes = map3.get("times").toString();
				if(monitorTimes.equals(times)){
					MonitorDay = Integer.valueOf( map3.get("MonitorDay").toString());
					break;
				}
			}
		  val = DirectDay + ThemeDay + MonitorDay;
		  dataList.add(val);
		}
		dataMap.put("times",timesList);
		dataMap.put("data", dataList);
		resultModel.setData(dataMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);
		return resultModel;
	}
	
	@ApiOperation(value = "数据采集系统-首页", notes = "数据采集系统-首页—日采集数据走势")
	@RequestMapping(value = "/getDayTrendByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getDayTrendByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map;
		StringBuffer selectBuffer;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		List<Object> timesList = new ArrayList<Object>();
		
		timesList.add("2点");
		timesList.add("4点");
		timesList.add("6点");
		timesList.add("8点");
		timesList.add("10点");
		timesList.add("12点");
		timesList.add("14点");
		timesList.add("16点");
		timesList.add("18点");
		timesList.add("20点");
		timesList.add("22点");
		timesList.add("24点");
		
		//时间查询
		String beginTime = DateUtils.getCurrentDateStr();
		String endTime = DateUtils.getDayTime(-1);
		
		
		//舆情数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT COALESCE(sum(HOUR(add_time)='1' or HOUR(add_time)='2' ),0) as '2点',COALESCE(sum(HOUR(add_time)='3' or HOUR(add_time)='4' ),0) as '4点',");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='5' or HOUR(add_time)='6' ),0) as '6点',COALESCE(sum(HOUR(add_time)='7' or HOUR(add_time)='8' ),0) as '8点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='9' or HOUR(add_time)='10' ),0) as '10点',COALESCE(sum(HOUR(add_time)='11' or HOUR(add_time)='12' ),0) as '12点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='13' or HOUR(add_time)='14' ),0) as '14点',COALESCE(sum(HOUR(add_time)='15' or HOUR(add_time)='16' ),0) as '16点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='17' or HOUR(add_time)='18' ),0) as '18点',COALESCE(sum(HOUR(add_time)='19' or HOUR(add_time)='20' ),0) as '20点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='21' or HOUR(add_time)='22' ),0) as '22点',COALESCE(sum(HOUR(add_time)='23' or HOUR(add_time)='0' ),0) as '24点' ");
		selectBuffer.append("FROM da_sentiment_article where add_time between '"+beginTime+"' and '"+endTime+"' ");
		Map<String, Object> themeMap = jdbcTemplate.queryForMap(selectBuffer.toString());
		
		//物联网数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT COALESCE(sum(HOUR(`time`)='1' or HOUR(`time`)='2' ),0) as '2点',COALESCE(sum(HOUR(`time`)='3' or HOUR(`time`)='4' ),0) as '4点',");
		selectBuffer.append("COALESCE(sum(HOUR(`time`)='5' or HOUR(`time`)='6' ),0) as '6点',COALESCE(sum(HOUR(`time`)='7' or HOUR(`time`)='8' ),0) as '8点', ");
		selectBuffer.append("COALESCE(sum(HOUR(`time`)='9' or HOUR(`time`)='10' ),0) as '10点',COALESCE(sum(HOUR(`time`)='11' or HOUR(`time`)='12' ),0) as '12点', ");
		selectBuffer.append("COALESCE(sum(HOUR(`time`)='13' or HOUR(`time`)='14' ),0) as '14点',COALESCE(sum(HOUR(`time`)='15' or HOUR(`time`)='16' ),0) as '16点', ");
		selectBuffer.append("COALESCE(sum(HOUR(`time`)='17' or HOUR(`time`)='18' ),0) as '18点',COALESCE(sum(HOUR(`time`)='19' or HOUR(`time`)='20' ),0) as '20点', ");
		selectBuffer.append("COALESCE(sum(HOUR(`time`)='21' or HOUR(`time`)='22' ),0) as '22点',COALESCE(sum(HOUR(`time`)='23' or HOUR(`time`)='0' ),0) as '24点' ");
		selectBuffer.append("FROM da_iot_monitor_data where `time` between '"+beginTime+"' and '"+endTime+"'  ");
		Map<String, Object> monitorMap = jdbcTemplate.queryForMap(selectBuffer.toString());
		
		//直报数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT COALESCE(sum(HOUR(add_time)='1' or HOUR(add_time)='2' ),0) as '2点',COALESCE(sum(HOUR(add_time)='3' or HOUR(add_time)='4' ),0) as '4点',");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='5' or HOUR(add_time)='6' ),0) as '6点',COALESCE(sum(HOUR(add_time)='7' or HOUR(add_time)='8' ),0) as '8点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='9' or HOUR(add_time)='10' ),0) as '10点',COALESCE(sum(HOUR(add_time)='11' or HOUR(add_time)='12' ),0) as '12点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='13' or HOUR(add_time)='14' ),0) as '14点',COALESCE(sum(HOUR(add_time)='15' or HOUR(add_time)='16' ),0) as '16点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='17' or HOUR(add_time)='18' ),0) as '18点',COALESCE(sum(HOUR(add_time)='19' or HOUR(add_time)='20' ),0) as '20点', ");
		selectBuffer.append("COALESCE(sum(HOUR(add_time)='21' or HOUR(add_time)='22' ),0) as '22点',COALESCE(sum(HOUR(add_time)='23' or HOUR(add_time)='0' ),0) as '24点' ");
		selectBuffer.append("FROM da_common_field where  add_time between '"+beginTime+"' and '"+endTime+"' ");
		Map<String, Object> directMap = jdbcTemplate.queryForMap(selectBuffer.toString());

		//数据资源
		for(int i = 0;i < timesList.size(); i++){
			String times = timesList.get(i).toString();
			int val;
			int DirectDay = Integer.valueOf( directMap.get(times).toString());
			int ThemeDay = Integer.valueOf( themeMap.get(times).toString());
			int MonitorDay = Integer.valueOf( monitorMap.get(times).toString());

		  val = DirectDay + ThemeDay + MonitorDay;
		  dataList.add(val);
		}
		
		dataMap.put("times",timesList);
		dataMap.put("data", dataList);
		
		resultModel = new ResultModel();
		resultModel.setData(dataMap);
		resultModel.setIsSuccessCode(CustomSymbolic.DCODE_BOOLEAN_T);

		return resultModel;
	}
	
	@ApiOperation(value = "数据采集系统-首页", notes = "数据采集系统-首页—数据来源渠道排名")
	@RequestMapping(value = "/getSource", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getSourceByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		List<Object> sourceNameList = new ArrayList<Object>();
		List<Object> sourceCodeList = new ArrayList<Object>();
		
		//来源渠道
		String sourceChannel = DictionaryEnum.SOURCE_CHANNEL_TYPE_TEXT.getText();
		StringBuffer selectBufferS = new StringBuffer();
		selectBufferS.append(" select code sourceCode,text sourceName from gp_dictionary where type_id = '"+sourceChannel+"' ");
		
		map.put("Sql", selectBufferS.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		maps.put("source", (List<Map<String, Object>>)resultModel.getData());

		for (Map<String, Object> maps0 : maps.get("source")) {
			sourceCodeList.add(maps0.get("sourceCode").toString());
			sourceNameList.add(maps0.get("sourceName").toString());
		}
		
		String sourceCode = sourceCodeList.toString().replace("[", "'").replace("]", "'").replace(",","','");
		//直报数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select count(*) num,source_channel_type_code sCode,source_channel_type_text sName from da_common_field ");
		selectBuffer.append(" where source_channel_type_code in ("+sourceCode+") group by source_channel_type_code ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		List<String> directList = new ArrayList<String>();
		
		Map<String, List<Map<String, Object>>> directMaps = new HashMap<String, List<Map<String, Object>>>();
		directMaps.put("Direct", (List<Map<String, Object>>) resultModel.getData());
		
		//指标上报
		//视图数据
		//舆情数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select count(*) num,datasource_code sCode,datasource_text sName from da_sentiment_article ");
		selectBuffer.append(" where datasource_code in ("+sourceCode+") group by datasource_code ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		List<String> themeList = new ArrayList<String>();
		
		Map<String, List<Map<String, Object>>> themeMaps = new HashMap<String, List<Map<String, Object>>>();
		themeMaps.put("Theme", (List<Map<String, Object>>) resultModel.getData());

		//数据资源
		for(int i = 0;i < sourceCodeList.size(); i++){
			String sourceCode0 = sourceCodeList.get(i).toString();
			int val;
			int DirectNum = 0;
			int ThemeNum = 0;
			if(!directMaps.get("Direct").isEmpty()){
				for (Map<String, Object> map1 : directMaps.get("Direct")) {
					String DirectCode = map1.get("sCode").toString();
					if(sourceCode0.equals(DirectCode)&&!DirectCode.equals("")){
						DirectNum =  Integer.valueOf( map1.get("num").toString());
					}
				}
			}
			if(!themeMaps.get("Theme").isEmpty()){
				for (Map<String, Object> map2 : themeMaps.get("Theme")) {
					String ThemeTimes = map2.get("sCode").toString();
					if(sourceCode0.equals(ThemeTimes)&&!ThemeTimes.equals("")){
						ThemeNum = Integer.valueOf( map2.get("num").toString());
					}
				}
			}
		  val = DirectNum + ThemeNum;
		  dataList.add(val);
		}
		dataMap.put("times",sourceNameList);
		dataMap.put("data", dataList);
//		dataList.add(dataMap);
		resultModel.setData(dataMap);

		return resultModel;
	}
	
	@ApiOperation(value = "数据采集系统-首页", notes = "数据采集系统-首页—数据主体数据统计")
	@RequestMapping(value = "/getSubject", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getSubjectByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer;
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<Object> dataList = new ArrayList<Object>();
		List<Object> sourceNameList = new ArrayList<Object>();
		List<Object> sourceCodeList = new ArrayList<Object>();
		
		//数据主体(区域维度字典)
		String subjectSources = DictionaryEnum.AREA_LATITUDE_TYPE_TEXT.getText();
		StringBuffer selectBufferS = new StringBuffer();
		selectBufferS.append(" select code sourceCode,text sourceName from gp_dictionary where type_id = '"+subjectSources+"' ");
		
		map.put("Sql", selectBufferS.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		maps.put("source", (List<Map<String, Object>>)resultModel.getData());

		for (Map<String, Object> maps0 : maps.get("source")) {
			sourceCodeList.add(maps0.get("sourceCode").toString());
			sourceNameList.add(maps0.get("sourceName").toString());
		}
		
		String sourceCode = sourceCodeList.toString().replace("[", "'").replace("]", "'").replace(",","','");
		//直报数据
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append(" select count(*) num,area_latitude_type_code sCode,area_latitude_type_text sName from da_common_field where trim(area_latitude_type_code)  <> '' ");
		selectBuffer.append(" and area_latitude_type_code is not null and area_latitude_type_code in ("+sourceCode+") group by area_latitude_type_code ");
		map.put("Sql", selectBuffer.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		List<String> directList = new ArrayList<String>();
		
		Map<String, List<Map<String, Object>>> directMaps = new HashMap<String, List<Map<String, Object>>>();
		directMaps.put("Direct", (List<Map<String, Object>>) resultModel.getData());
		
		//指标上报
		//视图数据
		//数据资源
		for(int i = 0;i < sourceCodeList.size(); i++){
			String sourceCode0 = sourceCodeList.get(i).toString();
			int val;
			int DirectNum = 0;
			int ThemeNum = 0;
			if(!directMaps.get("Direct").isEmpty()){
				for (Map<String, Object> map1 : directMaps.get("Direct")) {
					String DirectCode = map1.get("sCode").toString();
					if(sourceCode0.equals(DirectCode)&&!DirectCode.equals("")){
						DirectNum =  Integer.valueOf( map1.get("num").toString());
					}
				}
			}
//			if(!themeMaps.get("Theme").isEmpty()){
//				for (Map<String, Object> map2 : themeMaps.get("Theme")) {
//					String ThemeTimes = map2.get("sCode").toString();
//					if(sourceCode0.equals(ThemeTimes)&&!ThemeTimes.equals("")){
//						ThemeNum = Integer.valueOf( map2.get("num").toString());
//					}
//				}
//			}
//		  val = DirectNum + ThemeNum;
		  val = DirectNum;
		  dataList.add(val);
		}
		dataMap.put("times",sourceNameList);
		dataMap.put("data", dataList);
		resultModel.setData(dataMap);

		return resultModel;
	}
	
	@ApiOperation(value = "数据采集系统-首页", notes = "数据采集系统-首页—待审核数据（全部）")
	@RequestMapping(value = "/getMonthlyTobeAudited", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getMonthlyTobeAuditedByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, List<Map<String, Object>>> maps = new HashMap<String, List<Map<String, Object>>>();
		StringBuffer selectBuffer;
		Map<String, Object> dataMap;
		List<Object> dataList = new ArrayList<Object>();
		
		//待审核数据查询
		map = new HashMap<String, Object>();
		selectBuffer = new StringBuffer();
		selectBuffer.append(" (select '舆情数据' text,count(*) num from da_sentiment_article where update_status_code = '0') union ");
		selectBuffer.append(" (select '主体数据' text,A.num+B.num as num from ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_peasant_info B where A.id = B.common_field_id and A.audit_status_code = '0') A, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_enterprise_info B where A.id = B.common_field_id and A.audit_status_code = '0') B) union ");
		selectBuffer.append(" (select '产地数据' text,count(*) num from da_common_field A,da_base_info B where A.id = B.common_field_id and A.audit_status_code = '0') union ");
		selectBuffer.append(" (select '认证数据' text,count(*) num from da_common_field A,da_identification_info B where A.id = B.common_field_id and A.audit_status_code = '0') union ");
		selectBuffer.append(" (select '生产数据' text,A.num+B.num as num from ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_input B where A.id = B.common_field_id and A.audit_status_code = '0') A, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_grow_yield B where A.id = B.common_field_id and A.audit_status_code = '0') B) union ");
		selectBuffer.append(" (select '加工数据' text,count(*) num from da_common_field A,da_process B where A.id = B.common_field_id and A.audit_status_code = '0') union ");
		selectBuffer.append(" (select '销售数据' text,A.num+B.num+C.num+D.num+E.num as num from ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_sale_peasant B where A.id = B.common_field_id and A.audit_status_code = '0') A, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_sale_fresh B where A.id = B.common_field_id and A.audit_status_code = '0') B, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_sale_process B where A.id = B.common_field_id and A.audit_status_code = '0') C, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_sale_channel B where A.id = B.common_field_id and A.audit_status_code = '0') D, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_sale_ecommerce_order B where A.id = B.common_field_id and A.audit_status_code = '0') E) union ");
		selectBuffer.append(" (select '贸易数据' text,count(*) num from da_common_field A,da_import_export B where A.id = B.common_field_id and A.audit_status_code = '0') union ");
		selectBuffer.append(" (select '价格数据' text,count(*) num from da_common_field A,da_market_price B where A.id = B.common_field_id and A.audit_status_code = '0') union ");
		selectBuffer.append(" (select '气象数据' text,count(*) num from da_common_field A,da_corp_disaster B where A.id = B.common_field_id and A.audit_status_code = '0') union ");
		selectBuffer.append(" (select '成本数据' text,count(*) num from da_common_field A,da_grow_cost B where A.id = B.common_field_id and A.audit_status_code = '0') union ");
		selectBuffer.append(" (select '产业数据' text,A.num+B.num+C.num+D.num as num from ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_industry_basicinfo B where A.id = B.common_field_id and A.audit_status_code = '0') A, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_industry_fresh B where A.id = B.common_field_id and A.audit_status_code = '0' ) B, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_industry_process B where A.id = B.common_field_id and A.audit_status_code = '0') C, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_industry_statistics B where A.id = B.common_field_id and A.audit_status_code = '0' ) D) union ");
		selectBuffer.append(" (select '监测数据' text,A.num+B.num as num from ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_check_collection B where A.id = B.common_field_id and A.audit_status_code = '0' ) A, ");
		selectBuffer.append(" (select count(*) num from da_common_field A,da_check_testing B where A.id = B.common_field_id and A.audit_status_code = '0' ) B)");
		
		map.put("Sql", selectBuffer.toString());
		resultModel = daCommonFieldUntBll.getListBySQL(map);
		
		maps.put("tobeAuditData", (List<Map<String, Object>>) resultModel.getData());
		if(!maps.get("tobeAuditData").isEmpty()){
			for (Map<String, Object> map1 : maps.get("tobeAuditData")) {
				dataMap = new HashMap<String, Object>();
				dataMap.put("text", map1.get("text").toString());
				dataMap.put("num", map1.get("num").toString());
				dataList.add(dataMap);
			}
		}
		
		resultModel.setData(dataList);
		return resultModel;
	}
	
}



