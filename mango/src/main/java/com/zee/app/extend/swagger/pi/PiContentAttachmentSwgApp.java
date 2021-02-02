package com.zee.app.extend.swagger.pi;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.HashMap;import com.zee.utl.CastObjectUtil;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zee.app.generate.swagger.pi.PiContentAttachmentGenSwgApp;
import com.zee.bll.extend.unity.pi.PiContentUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.pi.PiContentAttachment;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;


/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2017/12/15 23:41:48
 * @description CMS内容附件表 对外接口，扩展自PiContentAttachmentGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/pi/piContentAttachment")
public class PiContentAttachmentSwgApp extends PiContentAttachmentGenSwgApp {
	
	@Autowired
	@Qualifier("piContentUntBll")
	protected PiContentUntBll piContentUntBll;
	
	@ApiOperation(value = "单条查询", notes = "根据主键查询记录详细信息,路径拼接模式")
	@ApiImplicitParam(paramType = "path", name = "id", value = "用户ID", required = true, dataType = "String")
	@RequestMapping(value = "/getModel/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getModelByPath(@PathVariable("id") String id) {
		ResultModel result = piContentAttachmentUntBll.getModel(id);
		if(result.getData() != null){
			PiContentAttachment piContentAttachment = (PiContentAttachment)result.getData();
			Map<String, Object> map = new HashMap<String, Object>();
			StringBuffer selectBuffer = new StringBuffer();
			selectBuffer.append("	SELECT                                                                ");
			selectBuffer.append("		A.id,                                                             ");
			selectBuffer.append("		A.content_id,                                                     ");
			selectBuffer.append("		B.title                                                           ");
			selectBuffer.append("	FROM                                                                  ");
			selectBuffer.append("		pi_content_attachment A                                           ");
			selectBuffer.append("	INNER JOIN pi_content_ext B ON A.content_id = B.content_id            ");
			selectBuffer.append("	WHERE                                                                 ");
			selectBuffer.append("		A.id = '"+id+"'                                                   ");
			map.put("Sql", selectBuffer.toString());
			ResultModel resultModel = piContentUntBll.getListBySQL(map);
			List<Map<String, Object>> modelList = CastObjectUtil.cast(resultModel.getData());
			Map<String, Object> moduleMap = modelList.get(0);
			piContentAttachment.setTitle(moduleMap.get("title").toString());
			result.setData(piContentAttachment);
		}
		return result;
	}

	@ApiOperation(value = "模糊查询", notes = "根据查询条件模糊查询")
	@RequestMapping(value = "/getListByJsonData", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultModel getListByJsonData() {
		ResultModel resultModel = new ResultModel();

		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		if (StringUtils.isBlank(jsonData))
			return resultModel;

		Map<String, Object> map = new HashMap<String, Object>();
		StringBuffer selectBuffer = new StringBuffer();
		selectBuffer.append("	SELECT                                                       "); 
		selectBuffer.append("		A.id id,                                                 "); 
		selectBuffer.append("		A.content_id contentId,                                  "); 
		selectBuffer.append("		A.priority priority,                                     "); 
		selectBuffer.append("		A.attachment_path attachmentPath,                        "); 
		selectBuffer.append("		A.attachment_name attachmentName,                        "); 
		selectBuffer.append("		A.filename filename,                                     "); 
		selectBuffer.append("		A.download_count downloadCount,                          "); 
		selectBuffer.append("		C.title                                                  "); 
		selectBuffer.append("	FROM                                                         "); 
		selectBuffer.append("		pi_content_attachment A                                  "); 
		selectBuffer.append("	INNER JOIN pi_content B ON A.content_id = B.id               "); 
		selectBuffer.append("	INNER JOIN pi_content_ext C ON B.id = C.content_id           "); 
		selectBuffer.append("	WHERE                                                        "); 
		selectBuffer.append("		1 = 1                                                    "); 
		
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
                
				if (entityRelatedObject.containsKey("attachmentName") && StringUtils.isNotBlank(entityRelatedObject.getString("attachmentName")))
					selectBuffer.append(" and A.attachment_name like '%").append(entityRelatedObject.getString("attachmentName")).append("%'");
				if (entityRelatedObject.containsKey("fileName") && StringUtils.isNotBlank(entityRelatedObject.getString("fileName")))
					selectBuffer.append(" and A.filename like '%").append(entityRelatedObject.getString("fileName")).append("%'");
				if (entityRelatedObject.containsKey("attachmentPath") && StringUtils.isNotBlank(entityRelatedObject.getString("attachmentPath")))
					selectBuffer.append(" and A.attachment_path like '%").append(entityRelatedObject.getString("attachmentPath")).append("%'");
				if (entityRelatedObject.containsKey("title") && StringUtils.isNotBlank(entityRelatedObject.getString("title")))
					selectBuffer.append(" and C.title like '%").append(entityRelatedObject.getString("title")).append("%'");
				
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

		resultModel = piContentAttachmentUntBll.getListBySQL(map);

		return resultModel;
	}
	
	@RequestMapping(value = "/exportExcel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void exportExcel() {
		ResultModel resultModel = getListByJsonData();
		String fileName = "CMS内容附件表列表数据" + DateUtils.getCurrentDateStr() + ".xls";
		String jsonData = request.getParameter(CustomSymbolic.CONTROLLER_PARAM_JSON);
		JSONArray columnInfoList = new JSONArray();
		if (!StringUtils.isBlank(jsonData)) {
			JSONObject json = JSONObject.fromObject(jsonData);

			if (json.containsKey("columnInfo")) {
				columnInfoList = json.getJSONArray("columnInfo");
			}
		}

		if (resultModel != null) {
			try {
				exportExcel(fileName, columnInfoList, resultModel);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
}



