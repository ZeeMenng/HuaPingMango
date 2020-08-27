package com.jusfoun.utl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.jusfoun.app.base.BaseApp;
import com.jusfoun.bll.extend.unity.da.DaSentimentArticleUntBll;
import com.jusfoun.bll.extend.unity.da.DaSentimentContentUntBll;
import com.jusfoun.bll.extend.unity.gp.GpLoginLogUntBll;
import com.jusfoun.bll.extend.unity.gp.GpTokenUntBll;
import com.jusfoun.bll.extend.unity.gp.GpUserUntBll;
import com.jusfoun.ent.custom.ResultModel;
import com.jusfoun.ent.extend.da.DaSentimentArticle;
import com.jusfoun.ent.extend.da.DaSentimentContent;
import com.jusfoun.ent.extend.gp.GpLoginLog;
import com.jusfoun.ent.extend.gp.GpToken;
import com.jusfoun.ent.extend.gp.GpUser;
import com.jusfoun.set.enumer.DictType;

/**
 * @author lxy
 * @createDate 2018年4月20日 下午3:46:52
 * @updateDate 2018年4月20日 下午3:46:52
 * @description 多线程处理类
 */
@Component
public class Executors extends BaseApp{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	@Qualifier("gpTokenUntBll")
	protected GpTokenUntBll gpTokenUntBll;
	
	@Autowired
	@Qualifier("gpLoginLogUntBll")
	protected GpLoginLogUntBll gpLoginLogUntBll;
	
	@Autowired
	@Qualifier("gpUserUntBll")
	protected GpUserUntBll gpUserUntBll;
	
	@Autowired
	@Qualifier("daSentimentArticleUntBll")
	protected DaSentimentArticleUntBll daSentimentArticleUntBll;
	
	@Autowired
	@Qualifier("daSentimentContentUntBll")
	protected DaSentimentContentUntBll daSentimentContentUntBll;
	
	/**
	 * 处理登陆信息
	 * @param pMap
	 */
	@Async("executor")
	public void dealLogin(Map<String, Object> pMap) {
		String token = pMap.get("token") == null ? "" : pMap.get("token").toString();
		String userId = pMap.get("userId") == null ? "" : pMap.get("userId").toString();
		String userName = pMap.get("userName") == null ? "" : pMap.get("userName").toString();
		String ip = pMap.get("ip") == null ? "" : pMap.get("ip").toString();
		String browser = pMap.get("browser") == null ? "" : pMap.get("browser").toString();
		String os = pMap.get("os") == null ? "" : pMap.get("os").toString();
		Date loginTime = DateUtils.getCurrentTime();
		
		//处理用户token表
		GpToken gpToken = new GpToken();
		gpToken.setUserId(userId);
		gpToken.setUserName(userName);
		gpToken.setAccessToken(token);
		gpToken.setAddTime(loginTime);
		gpTokenUntBll.add(gpToken);
		
		// 处理登陆日志
		GpLoginLog gpLoginLog = new GpLoginLog();
		gpLoginLog.setId(token);
		gpLoginLog.setUserName(userName);
		gpLoginLog.setIp(ip);
		gpLoginLog.setBrowser(browser);
		gpLoginLog.setOs(os);
		gpLoginLog.setLoginTime(loginTime);
		gpLoginLog.setIsSuccessCode((byte) 0);
		gpLoginLogUntBll.add(gpLoginLog);
		
		// 处理用户登陆次数
		GpUser gpUser = new GpUser();
		gpUser.setId(pMap.get("id") == null ? "" : pMap.get("id").toString());
		Integer loginCount = pMap.get("loginCount") == null ? 0 : Integer.parseInt(pMap.get("loginCount").toString());
		gpUser.setLoginCount(loginCount + 1);
		gpUserUntBll.update(gpUser);
	}
	
	/**
	 * 获取post参数
	 * @return
	 */
	private JSONObject getPostData(Map<String, Object> paramMap){
		JSONObject postData = new JSONObject();
		postData.put("secretkey", SymbolicConstant.SENTIMENT_SECRETKEY);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("viewName", "hour");//视图名，年year，月month，日date，小时hour（默认为年）
		map.put("hasCurrent", "false");
		map.put("pastNum", "1");
		List<String> timesList = TimesView.getTimesView(map);
		String startTime = DateUtils.date2String(DateUtils.string2Date(timesList.get(0), "yyyy-MM-dd HH"), "yyyyMMddHH") + "0000";
		String endTime = DateUtils.date2String(DateUtils.string2Date(timesList.get(0), "yyyy-MM-dd HH"), "yyyyMMddHH") + "5959";
//		postData.put("publishTime", "20180801000000-20180830235959");
//		System.out.println("publishTime===" + startTime + "-" + endTime);
		postData.put("publishTime", startTime + "-" + endTime);
		postData.put("queryKeys", paramMap.get("keyName").toString());
		postData.put("noQueryKeys", paramMap.get("excludeName").toString());
		postData.put("pageNum", "1");
		postData.put("pageSize", paramMap.get("frequency").toString());
//		postData.put("mediaTypes", paramMap.get("mediaTypeCodeSet").toString());//暂时注掉，类型少了爬不到数据
		postData.put("sort", "time");
		postData.put("desc", "true");
		postData.put("red", "false");
		postData.put("merge", "true");
		
		
		return postData;
	}
	
	/**
	 * 处理文章，//先实现简单的，后期改
	 * @param pMap
	 */
	@SuppressWarnings("unchecked")
	@Async("executor")
	public void dealArticle(Map<String, Object> paramMap) {
		
		String themeId = paramMap.get("id").toString();
		
		JSONObject postData = getPostData(paramMap);
		JSONObject jsonObj = restTemplate.postForEntity(SymbolicConstant.SENTIMENT_SEARCH_URL, postData, JSONObject.class).getBody();
		Map<String, Object> dataMap = (Map<String, Object>)jsonObj.get("data");
		List<Map<String, Object>> list = (List<Map<String, Object>>) dataMap.get("resultList");
		if(list == null){
			return;
		}
		for (Map<String, Object> map : list) {
			String articleName = map.get("title")==null?"":map.get("title").toString();
			articleName = Tools.delHTMLTag(articleName);
			Date publishTime = null;
			if(map.get("publishTime") != null){
				publishTime = DateUtils.string2Date(map.get("publishTime").toString(), "yyyy-MM-dd HH:mm:ss");
			}
			String threadStarter = map.get("threadStarter")==null?"":map.get("threadStarter").toString();
			String url = map.get("url")==null?"":map.get("url").toString();
			Byte mediaTypeCode = 0;
			if(map.get("mediaType") != null){
				mediaTypeCode = Byte.valueOf(map.get("mediaType").toString());
			}
			String mediaTypeText = "其他";
			if(DictType.MEDIATYPE_1.getCode() == mediaTypeCode){
				mediaTypeText = DictType.MEDIATYPE_1.getText();
			}
			if(DictType.MEDIATYPE_3.getCode() == mediaTypeCode){
				mediaTypeText = DictType.MEDIATYPE_3.getText();
			}
			if(DictType.MEDIATYPE_4.getCode() == mediaTypeCode){
				mediaTypeText = DictType.MEDIATYPE_4.getText();
			}
			if(DictType.MEDIATYPE_5.getCode() == mediaTypeCode){
				mediaTypeText = DictType.MEDIATYPE_5.getText();
			}
			if(DictType.MEDIATYPE_6.getCode() == mediaTypeCode){
				mediaTypeText = DictType.MEDIATYPE_6.getText();
			}
			if(DictType.MEDIATYPE_10.getCode() == mediaTypeCode){
				mediaTypeText = DictType.MEDIATYPE_10.getText();
			}
			if(DictType.MEDIATYPE_11.getCode() == mediaTypeCode){
				mediaTypeText = DictType.MEDIATYPE_11.getText();
			}
			
			Byte sentimentTypeCode = 0;
			if(map.get("sentimentType") != null){
				sentimentTypeCode = Byte.valueOf(map.get("sentimentType").toString());
			}
			String sentimentTypeText = "其他";
			if(DictType.SENTIMENTTYPE_1.getCode() == sentimentTypeCode){
				sentimentTypeText = DictType.SENTIMENTTYPE_1.getText();
			}
			if(DictType.SENTIMENTTYPE_2.getCode() == sentimentTypeCode){
				sentimentTypeText = DictType.SENTIMENTTYPE_2.getText();
			}
			if(DictType.SENTIMENTTYPE_3.getCode() == sentimentTypeCode){
				sentimentTypeText = DictType.SENTIMENTTYPE_3.getText();
			}
			Integer viewCount = 0;
			if(map.get("viewCount") != null){
				viewCount = Integer.valueOf(map.get("sentimentType").toString());
			}
			String content = "";
			if(map.get("content") != null){
				content = map.get("content").toString();
			}
			String serialNumber = "";
			if(map.get("uuid") != null){
				serialNumber = map.get("uuid").toString();
			}
			
			try {
				DaSentimentArticle articleBean = new DaSentimentArticle();
				
				articleBean.setThemeId(themeId);
				articleBean.setArticleName(articleName);
				articleBean.setPublishTime(publishTime);
				articleBean.setThreadStarter(threadStarter);
				articleBean.setUrl(url);
				articleBean.setMediaTypeCode(Byte.valueOf(mediaTypeCode));
				articleBean.setMediaTypeText(mediaTypeText);
				articleBean.setSentimentTypeCode(sentimentTypeCode);
				articleBean.setSentimentTypeText(sentimentTypeText);
				articleBean.setViewCount(viewCount);
				articleBean.setDatasourceCode(DictType.DATASOURCE_1.getCode());
				articleBean.setDatasourceText(DictType.DATASOURCE_1.getText());
				articleBean.setSerialNumber(serialNumber);
				ResultModel addArticleBean = daSentimentArticleUntBll.add(articleBean,false);
				
				DaSentimentContent contentBean = new DaSentimentContent();
				contentBean.setArticleId(addArticleBean.getObjectId());
				contentBean.setTxt(content);
				daSentimentContentUntBll.add(contentBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}