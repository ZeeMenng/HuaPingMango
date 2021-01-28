package com.zee.app.extend.swagger.pe;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pe.PeAerialViewGenSwgApp;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pe.PeAerialView;
import com.zee.utl.BeanUtil;
import com.zee.utl.SymbolicConstant;
import com.zee.utl.Tools;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-26 15:20:30
 * @description 鸟瞰图 对外接口，扩展自PeAerialViewGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pe/peAerialView")
public class  PeAerialViewSwgApp extends  PeAerialViewGenSwgApp {
	
	@ApiOperation(value = "新增记录", notes = "新增单条记录")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "body", name = "jsonData", value = "json字符串", required = true, dataType = "PeAerialView") })
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel add(@RequestBody PeAerialView jsonData) {
		
		if(jsonData.getResourcePath().contains(this.linkPath)){
			jsonData.setResourcePath(jsonData.getResourcePath().substring(this.linkPath.length(), jsonData.getResourcePath().length()));
		}
		ResultModel result = peAerialViewUntBll.add(jsonData);

		return result;
	}
	
	
	/**
	 * 门户数据服务版--鸟瞰华坪
	 * lxl
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getAerialList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getAerialList() {
		ResultModel resultModel = new ResultModel();
    
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,"
				+ "A.resource_id resourceId,"
				+ "A.title title,"
				+ "A.contet contet,"
				+ "A.page_view pageView,"
				+ "CONCAT('"+this.linkPath+"',B.path) path,"
				+ " B.new_name newName,"
				+ "A.activity activity  "
				+ "from pe_aerial_view A "
				+ "INNER JOIN gp_resource B "
				+ "ON A.resource_id = B.id where 1=1   "
				+ "and is_slideshow_code = '1' "
				+ "ORDER BY A.is_recommend_code ASC, B.add_time DESC ")
		;
		 
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and A.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("title") && StringUtils.isNotBlank(entityRelatedObject.getString("title")))
					selectBuffer.append(" and A.title like '%").append(entityRelatedObject.getString("title")).append("%'");
				if (entityRelatedObject.containsKey("contet") && StringUtils.isNotBlank(entityRelatedObject.getString("contet")))
					selectBuffer.append(" and A.contet like '%").append(entityRelatedObject.getString("contet")).append("%'");
				if (entityRelatedObject.containsKey("pageView") && StringUtils.isNotBlank(entityRelatedObject.getString("pageView")))
					selectBuffer.append(" and A.page_view like '%").append(entityRelatedObject.getString("pageView")).append("%'");
			}

			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
			}

			if (jsonObject.containsKey("orderList")) {
				JSONArray orderListArray = jsonObject.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append(orderColumnObject.getString("columnName"));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = peAerialViewUntBll.getListBySQL(map);

		return resultModel;
	}
	/**
	 * lxl  鸟瞰华平首页--推荐
	 * @return
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getHomeAerialList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getHomeAerialList() {
		ResultModel resultModel = new ResultModel();
    
		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,"
				+ "A.resource_id resourceId,"
				+ "A.title title,"
				+ "A.contet contet,"
				+ "A.page_view pageView,"
				+ "CONCAT('"+this.linkPath+"',B.path) path,"
				+ " B.new_name newName,"
				+ "A.activity activity  "
				+ "from pe_aerial_view A "
				+ "INNER JOIN gp_resource B "
				+ "ON A.resource_id = B.id where 1=1   "
				+ "and A.is_recommend_code='0' "
				+ " order by B.add_time DESC ");

		map.put("Sql", selectBuffer.toString());

		resultModel = peAerialViewUntBll.getListBySQL(map);

		return resultModel;
	}
	/**
	 * 门户数据服务版--鸟瞰华坪轮播图
	 * lxl
	 */
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getBroadcastByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getBroadcastByJsonData() {
		ResultModel resultModel = new ResultModel();
		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("SELECT CONCAT('"+this.linkPath+"',b.path) path,a.title from ")
		.append("pe_aerial_view  a INNER JOIN gp_resource b on a.resource_id=b.id ")
		.append("where a.is_slideshow_code='0' ")
		.append("order by b.add_time ");
		map.put("Sql", selectBuffer.toString());
		
		resultModel = peAerialViewUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel resultModel = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return resultModel;
		}

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,"
				+ "A.resource_id resourceId,"
				+ "A.title title,"
				+ "A.contet contet,"
				+ "A.page_view pageView,"
				+ "CONCAT('"+this.linkPath+"',A.resource_path)  imgPath ,"
				+ " B.new_name newName,"
				+ "A.is_recommend_code isRecommendCode,"
				+ "A.is_slideshow_code isSlideshowCode  "
				+ "from pe_aerial_view A "
				+ "INNER JOIN gp_resource B "
				+ "ON A.resource_id = B.id where A.id='"+id+"'   ")
		;
		map.put("Sql", selectBuffer.toString());
		resultModel = peAerialViewUntBll.getListBySQL(map);
		List<Map<String, Object>> list=(List<Map<String, Object>> )resultModel.getData();
		resultModel.setData(list.get(0));
		
		return resultModel;
	}
	
	@RequestMapping(value = "/updateAerialView", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel updateAerialView() throws Exception {
		String strJson = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		ResultModel result = new ResultModel();
		String jsonData = new String(strJson);
		if (!StringUtils.isBlank(jsonData)){
			JSONObject jsonObject = JSONObject.fromObject(jsonData);
		PeAerialView peAerialView = (PeAerialView) BeanUtil.map2Bean(jsonObject, PeAerialView.class);
		if(peAerialView.getResourcePath().contains(this.linkPath)){
			peAerialView.setResourcePath(peAerialView.getResourcePath().substring(this.linkPath.length(), peAerialView.getResourcePath().length()));
		} 
		result = peAerialViewUntBll.update(peAerialView);
		} 
		return result;
	
	
	}
	
	
	
	
	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(SymbolicConstant.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("select A.id id,A.title title,A.resource_id resourceId,CONCAT('"+this.linkPath+"',A.resource_path) resourcePath,A.contet contet,A.page_view pageView,A.activity activity,A.is_slideshow_code isSlideshowCode,A.is_recommend_code isRecommendCode,A.add_time addTime,A.update_time updateTime  from pe_aerial_view A inner join pe_aerial_view B on A.id=B.id where 1=1 ");
        
        if (!StringUtils.isBlank(jsonData)) {
			JSONObject jsonObject = JSONObject.fromObject(jsonData);

			if (jsonObject.containsKey("selectRows")) {
				JSONArray selectRowsArray = jsonObject.getJSONArray("selectRows");
				if (selectRowsArray.size() > 0) {
					selectBuffer.append(" and A.id in('");
					for (int i = 0; i < selectRowsArray.size(); i++) {
						selectBuffer.append(i == selectRowsArray.size() - 1 ? selectRowsArray.getString(i) + "'" : selectRowsArray.getString(i) + "','");
					}
					selectBuffer.append(")");
				}
			}

			if (jsonObject.containsKey("entityRelated")) {
				JSONObject entityRelatedObject = jsonObject.getJSONObject("entityRelated");
                
				if (entityRelatedObject.containsKey("title") && StringUtils.isNotBlank(entityRelatedObject.getString("title")))
					selectBuffer.append(" and A.title like '%").append(entityRelatedObject.getString("title")).append("%'");
				if (entityRelatedObject.containsKey("resourcePath") && StringUtils.isNotBlank(entityRelatedObject.getString("resourcePath")))
					selectBuffer.append(" and A.resource_path like '%").append(entityRelatedObject.getString("resourcePath")).append("%'");
				if (entityRelatedObject.containsKey("contet") && StringUtils.isNotBlank(entityRelatedObject.getString("contet")))
					selectBuffer.append(" and A.contet like '%").append(entityRelatedObject.getString("contet")).append("%'");
			}

			if (jsonObject.containsKey("page")) {
				JSONObject pageObject = jsonObject.getJSONObject("page");
				map.put("Page", pageObject);
			}

			if (jsonObject.containsKey("orderList")) {
				JSONArray orderListArray = jsonObject.getJSONArray("orderList");
				if (orderListArray.size() != 0)
					selectBuffer.append(" order by ");
				for (int i = 0; i < orderListArray.size(); i++) {
					JSONObject orderColumnObject = orderListArray.getJSONObject(i);
					selectBuffer.append(Tools.getCamelUnderline(orderColumnObject.getString("columnName")));
					selectBuffer.append(orderColumnObject.getBoolean("isASC") ? " ASC" : " DESC");
					selectBuffer.append((i + 1) == orderListArray.size() ? " " : " ,");
				}
			}
		}

		map.put("Sql", selectBuffer.toString());

		resultModel = peAerialViewUntBll.getListBySQL(map);

		return resultModel;
	}
	
	
}



