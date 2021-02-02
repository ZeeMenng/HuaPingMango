package com.zee.app.extend.swagger.da;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.zee.app.generate.swagger.da.DaCrawlerGenSwgApp;
import com.zee.bll.extend.unity.da.DaCrawlerUntBll;
import com.zee.bll.extend.unity.da.DaMarketPriceUntBll;
import com.zee.bll.extend.unity.da.DaOnlineRetailersUntBll;
import com.zee.bll.extend.unity.gp.GpDictionaryUntBll;
import com.zee.bll.extend.unity.gp.GpRegionUntBll;
import com.zee.ent.custom.ResultModel;
import com.zee.ent.extend.da.DaMarketPrice;
import com.zee.ent.extend.da.DaOnlineRetailers;
import com.zee.set.symbolic.CustomSymbolic;
import com.zee.utl.DateUtils;
import com.zee.utl.crawler.PhantomJsDriver;
import com.zee.utl.crawler.GenerateProxy;
import com.zee.utl.crawler.MofProcessor;
import com.zee.utl.crawler.TaoBaoProcessor;

import io.swagger.annotations.ApiOperation;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.selector.Html;

/**
 * @author Zee
 * @createDate 2017/05/22 15:10:18
 * @updateDate 2018-6-13 17:43:47
 * @description 爬虫 对外接口，扩展自DaCrawlerGenSwgApp ，可手动更改。
 */
@RestController
@RequestMapping(value = "/extend/swagger/da/daCrawler")
public class  DaCrawlerSwgApp extends  DaCrawlerGenSwgApp {
	private static final Logger log = LoggerFactory.getLogger(DaCrawlerSwgApp.class);
	@Autowired
	@Qualifier("daCrawlerUntBll")
	protected DaCrawlerUntBll daCrawlerUntBll;
	
	@Autowired
	private TaoBaoProcessor taoBaoProcessor;
	@Autowired
	private MofProcessor mofProcessor;
	@Autowired
	protected DaMarketPriceUntBll daMarketPriceUntBll;
	@Autowired
	@Qualifier("gpDictionaryUntBll")
	protected GpDictionaryUntBll gpDictionaryUntBll;
	@Autowired
	protected GpRegionUntBll gpRegionUntBll;
	@Autowired
	protected DaOnlineRetailersUntBll daOnlineRetailersUntBll;
	
	@ApiOperation(value = "测试", notes = "爬虫")
	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void test() {
		log.info("定时器crawler执行开始：" + DateUtils.getCurrentTimeStr());
//		try {
//			PhantomJsDriver.creatDriver();
//			Spider spider=Spider.create(taoBaoProcessor);
//			HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
//	        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(GenerateProxy.getRandomProxyIP()));
//			spider.setDownloader(httpClientDownloader);
//			spider.addUrl("https://s.taobao.com/search?q=%E8%8A%92%E6%9E%9C").thread(1).run();
//		} catch (Exception e) {
//			log.error("定时器crawler1执行错误：" + e);
//		}finally {
//			PhantomJsDriver.destroyDriver();
//		}
		
//		try {
//			daCrawlerUntBll.dataCleaning();
//		} catch (Exception e) {
//			log.error("定时器crawler2执行错误：" + e);
//		}
//		try{
//			PhantomJsDriver.creatDriver();
//			String format = DateUtils.date2String(DateUtils.getCurrentDate(),"yyyy-MM-dd");
////	    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
////			Calendar calendar = Calendar.getInstance();
////			calendar.setTime(new Date());
//			String[] urls = new String[5];
//			int length = 0;
////			for(int i=1;i<403;i++){
////				calendar.add(Calendar.DATE, -1);
////				String format = sdf.format(calendar.getTime());
//				urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=15052367&startTime="+format+"&endTime="+format;
//				urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=8754876&startTime="+format+"&endTime="+format;
//				urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=13148&startTime="+format+"&endTime="+format;
//				urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=15052357&startTime="+format+"&endTime="+format;
//				urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=13228&startTime="+format+"&endTime="+format;
////			}
//			Map<String, Object> map = new HashMap<String, Object>();
//			map.put("Sql", "SELECT `code`, `text` FROM gp_dictionary WHERE type_id = '"+SymbolicConstant.DI_CROP_STRAINS+"'");
//			List<Map<String, Object>> listmd = (List<Map<String, Object>>)gpDictionaryUntBll.getListBySQL(map).getData();
//			mofProcessor.setListmd(listmd);
//			Spider spider=Spider.create(mofProcessor);
//			HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
//	        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(GenerateProxy.getRandomProxyIP()));
//			spider.setDownloader(httpClientDownloader);
//	        spider.addUrl(urls).thread(1).run();
//		} catch (Exception e) {
//			log.error("定时器crawler3执行错误：" + e);
//		}finally {
//			PhantomJsDriver.destroyDriver();
//		}
//		try {
//			daMarketPriceUntBll.updateAvgTopBottomPrice();
//		} catch (Exception e) {
//			log.error("定时器crawler4执行错误：" + e);
//		}
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Sql", "SELECT parameter FROM da_crawler where source = 1 and parameter LIKE '%城市%' and parameter LIKE '%厂名%'");
			List<Map<String, Object>> list = (List<Map<String, Object>>) daCrawlerUntBll.getListBySQL(map).getData();
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				Map<String, Object> map2 = (Map<String, Object>) iterator.next();
				String parameter = map2.get("parameter").toString();
				parameter = parameter.replaceAll("&nbsp;", "");
				Html html = new Html(parameter);
				Document document = html.getDocument();
				Elements elementsByTag = document.getElementsByTag("li");
				DaOnlineRetailers or = new DaOnlineRetailers();
				for (Element li : elementsByTag) {
					String text = li.text();
					String attr = li.attr("title");
					if(text.contains("城市")){
						map.put("Sql", "select `code`, longitude, latitude, `name` from gp_region where `name` = '"+attr+"'");
						List<Map<String, Object>>  listm = (List<Map<String, Object>>)gpRegionUntBll.getListBySQL(map).getData();
						if(!org.thymeleaf.util.ListUtils.isEmpty(listm)){
							String code = (String) listm.get(0).get("code");
							String name = (String) listm.get(0).get("name");
							String longitude = (String) listm.get(0).get("longitude");
							String latitude = (String) listm.get(0).get("latitude");
							or.setLatitude(latitude);
							or.setLongitude(longitude);
							or.setRegionCode(code);
							or.setRegionText(name);
						}
					}else if(text.contains("厂名")) {
						or.setName(attr);
					}else if(text.contains("厂址")) {
						or.setAddress(attr);
					}else if(text.contains("厂家联系方式")) {
						or.setTel(attr);
					}
				}
				daOnlineRetailersUntBll.add(or);
			}
		} catch (Exception e) {
			log.error("定时器crawler5执行错误：" + e);
		}
		log.info("定时器crawler执行结束：" + DateUtils.getCurrentTimeStr());
	}
}