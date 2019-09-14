package com.jusfoun.utl.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jusfoun.bll.extend.unity.da.DaMarketPriceUntBll;
import com.jusfoun.bll.extend.unity.gp.GpDictionaryUntBll;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;
import com.jusfoun.utl.crawler.GenerateProxy;
import com.jusfoun.utl.crawler.MofProcessor;
import com.jusfoun.utl.crawler.PhantomJsDriver2;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Component
public class WholesaleTask {
	private static final Logger log = LoggerFactory.getLogger(WholesaleTask.class);
	
	@Autowired
	private MofProcessor mofProcessor;
	
	@Autowired
	protected DaMarketPriceUntBll daMarketPriceUntBll;
	
	@Autowired
	protected GpDictionaryUntBll gpDictionaryUntBll;
	
	@Value("${mangoTaskSwitch.wholesaleTask}")
	private Boolean wholesaleTask;//定时任务开关

	@Scheduled(cron = "0 0 23 * * ?")
	public void executeTask(){
		if(wholesaleTask){
			crawlerWholesale();
		}
	}

	/**
	 * 爬去芒果信息(数据来源农业)
	 */
	private void crawlerWholesale(){
		log.info("定时器crawlerWholesale执行开始：" + DateUtils.getCurrentTimeStr());
		try {
			PhantomJsDriver2.creatDriver();
			String format = DateUtils.date2String(DateUtils.getCurrentDate(),"yyyy-MM-dd");
			String[] urls = new String[5];
			int length = 0;
			urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=15052367&startTime="+format+"&endTime="+format;
			urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=8754876&startTime="+format+"&endTime="+format;
			urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=13148&startTime="+format+"&endTime="+format;
			urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=15052357&startTime="+format+"&endTime="+format;
			urls[length++]="http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?par_craft_index=13076&craft_index=13228&startTime="+format+"&endTime="+format;
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("Sql", "SELECT `code`, `text` FROM gp_dictionary WHERE type_id = '"+SymbolicConstant.DI_CROP_STRAINS+"'");
			List<Map<String, Object>> listmd = (List<Map<String, Object>>)gpDictionaryUntBll.getListBySQL(map).getData();
			mofProcessor.setListmd(listmd);
			Spider spider=Spider.create(mofProcessor);
			HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
	        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(GenerateProxy.getRandomProxyIP()));
			spider.setDownloader(httpClientDownloader);
	        spider.addUrl(urls).thread(1).run();
		} catch (Exception e) {
			log.error("定时器crawlerWholesale1执行错误：" + e);
		}finally {
			PhantomJsDriver2.destroyDriver();
		}
		try {
			daMarketPriceUntBll.updateAvgTopBottomPrice();
		} catch (Exception e) {
			log.error("定时器crawlerWholesale2执行错误：" + e);
		}
		log.info("定时器crawlerWholesale执行结束：" + DateUtils.getCurrentTimeStr());
	}
}