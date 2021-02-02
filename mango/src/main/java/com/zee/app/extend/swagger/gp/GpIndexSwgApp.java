package com.zee.app.extend.swagger.gp;

import java.util.ArrayList;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiChannelCountGenSwgApp;
import com.zee.bll.extend.unity.gp.GpLoginLogUntBll;
import com.zee.bll.extend.unity.gp.GprUserOrganizationUntBll;
import com.zee.bll.extend.unity.pi.PiChannelCountUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.CastObjectUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONObject;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description 系统用户。 对外接口，扩展自GpUserGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/gp/index")
public class GpIndexSwgApp extends PiChannelCountGenSwgApp {

	@Autowired
	@Qualifier("gpLoginLogUntBll")
	protected GpLoginLogUntBll gpLoginLogUntBll;

	@Autowired
	@Qualifier("piChannelCountUntBll")
	protected PiChannelCountUntBll piChannelCountUntBll;
	
	@Autowired
	@Qualifier("gprUserOrganizationUntBll")
	protected GprUserOrganizationUntBll gprUserOrganizationUntBll;


	/**
	 * 查询主页数据
	 * @param userId
	 */
	@ApiOperation(value = "查询主页数据", notes = "查询主页：网站访问数量、文章发布数量、组织机构数量、注册用户数量")
	@RequestMapping(value = "/getIndexData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getIndexData() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)){
			return resultModel;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append(" select t1.sitesNum,t2.articleNum,t3.orgNum,t4.userNum FROM                ");
		selectBuffer.append(" (select sum(A.views) sitesNum from pi_channel_count A,                    ");
		selectBuffer.append(" (select B.channel_id,B.channel_name from (select * from pi_channel 	    ");
		selectBuffer.append(" where parent_id = '3b8f6e987d853f93c899d82c32216c3e'                      ");
		selectBuffer.append(" or id = '3b8f6e987d853f93c899d82c32216c3e') A,pi_channel_ext B            ");
		selectBuffer.append(" where A.id = B.channel_id) B where B.channel_id = A.channel_id) t1,       ");
		selectBuffer.append(" (select A.num1+B.num2 articleNum from                                     ");
		selectBuffer.append(" (select count(*) num1 from pi_content_ext ) A,                       	    ");
		selectBuffer.append(" (select count(*) num2 from da_sentiment_article ) B) t2,                  ");
		selectBuffer.append(" (select count(*) orgNum from gp_organization where level_code = 3) t3,    ");
		selectBuffer.append(" (select count(*) userNum from gp_user ) t4                                ");

		map.put("Sql", selectBuffer.toString());

		resultModel = piChannelCountUntBll.getListBySQL(map);

		return resultModel;
	}

	/**
	 * 查询网站访问数量
	 * @param userId
	 */
	@ApiOperation(value = "查询网站访问数量", notes = "查询不同channel的访问数量")
	@RequestMapping(value = "/getSiteViews", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getSiteViews() {
		ResultModel resultModel = new ResultModel();

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		
		selectBuffer.append(" select A.views views,B.channel_name channelName               ");
		selectBuffer.append(" from pi_channel_count A,(select B.channel_id,B.channel_name from                     ");
		selectBuffer.append(" (select * from pi_channel where parent_id = '3b8f6e987d853f93c899d82c32216c3e'  	   ");
		selectBuffer.append(" or id = '3b8f6e987d853f93c899d82c32216c3e') A,pi_channel_ext B                       ");
		selectBuffer.append(" where A.id = B.channel_id) B where B.channel_id = A.channel_id                       ");                                        
		
		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelCountUntBll.getListBySQL(map);
		List<Map<String, Object>> dataArray = CastObjectUtil.cast(resultModel.getData());

		List< Object> resultList = new ArrayList();
		List< Object> list; 

		for (Map<String, Object> map1 : dataArray) {
			list = new ArrayList();
			list.add(map1.get("channelName").toString());
			
			if(map1.get("views")!=null && !map1.get("views").toString().equals("")){
				list.add(map1.get("views").toString());
			}else{
				list.add("0");
			}
			
			resultList.add(list);
		}

		resultModel.setData(resultList);
		return resultModel;
	}
	
	/**
	 * 查询文章发布数量
	 * @param type：查询类型  1-人工发布；2-自动采集。
	 */
	@ApiOperation(value = "查询文章发布数量", notes = "查询文章发布数量")
	@ApiImplicitParam(paramType = "path", name = "type", value = "查询类型", required = true, dataType = "String")
	@RequestMapping(value = "/getPublishArticles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getPublishArticles() {
		ResultModel resultModel = new ResultModel();
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData)) {
			return resultModel;
		}
		String type = "1";
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);
			if (json.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = json.getJSONObject("entityRelated");
				if (entityRelatedObject.containsKey("type") && StringUtils.isNotBlank(entityRelatedObject.getString("type"))) {
					type = entityRelatedObject.getString("type");
				}
			}
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		if(type.equals("1")){
			selectBuffer.append(" select A.month,IFNULL(B.num,0) num from cur_12_month_view A LEFT JOIN ( ");
			selectBuffer.append(" select substr(add_time, 1, 7) times,count(*) num from pi_content_ext ");
			selectBuffer.append(" group by substr(add_time, 1, 7)) B on A.month = B.times order by A.month asc ");
		}else{
			selectBuffer.append(" select A.month,IFNULL(B.num,0) num from cur_12_month_view A LEFT JOIN ( ");
			selectBuffer.append(" select substr(add_time, 1, 7) times,count(*) num from da_sentiment_article ");
			selectBuffer.append(" group by substr(add_time, 1, 7)) B on A.month = B.times order by A.month asc ");
		}
		

		map.put("Sql", selectBuffer.toString());
		resultModel = piChannelCountUntBll.getListBySQL(map);
		
		List<Map<String, Object>> dataArray =  CastObjectUtil.cast(resultModel.getData());

		List< Object> resultList = new ArrayList();
		List< Object> list; 

		for (Map<String, Object> map1 : dataArray) {
			list = new ArrayList();
			list.add(map1.get("month").toString());
			list.add(map1.get("num").toString());
			
			resultList.add(list);
		}

		resultModel.setData(resultList);
		
		return resultModel;
	}

}
