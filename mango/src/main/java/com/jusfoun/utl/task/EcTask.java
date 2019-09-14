package com.jusfoun.utl.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jusfoun.bll.extend.unity.da.DaCrawlerUntBll;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.crawler.GenerateProxy;
import com.jusfoun.utl.crawler.JDProcessor;
import com.jusfoun.utl.crawler.PhantomJsDriver;
import com.jusfoun.utl.crawler.PhantomJsDriver2;
import com.jusfoun.utl.crawler.TaoBaoProcessor;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

@Component
public class EcTask {
	private static final Logger log = LoggerFactory.getLogger(EcTask.class);
	
	@Autowired
	private TaoBaoProcessor taoBaoProcessor;
	
	@Autowired
	private JDProcessor jDProcessor;
	
	@Autowired
	protected DaCrawlerUntBll daCrawlerUntBll;
	
	@Value("${mangoTaskSwitch.ecTask}")
	private Boolean ecTask;//定时任务开关
	
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void executeTask(){
		if(ecTask){
			crawlerJD();
			crawlerTB();
			cleanData();
		}
	}
	
	private void crawlerTB(){
		log.info("定时器crawlerTB执行开始：" + DateUtils.getCurrentTimeStr());
		try {
			PhantomJsDriver.creatDriver();
			Spider spider=Spider.create(taoBaoProcessor);
			HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
			httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(GenerateProxy.getRandomProxyIP()));
			spider.setDownloader(httpClientDownloader);
			spider.addUrl("https://s.taobao.com/search?q=%E8%8A%92%E6%9E%9C&imgfile=&js=1&stats_click=search_radio_all%3A1&initiative_id=staobaoz_20181128&ie=utf8").thread(1).run();
		} catch (Exception e) {
			log.error("定时器crawlerTB执行错误：" + e);
		}finally {
			PhantomJsDriver.destroyDriver();
		}
		
		log.info("定时器crawlerTB执行结束：" + DateUtils.getCurrentTimeStr());
	}
	
	private void crawlerJD(){
		log.info("定时器crawlerJD执行开始：" + DateUtils.getCurrentTimeStr());
		try {
			PhantomJsDriver2.creatDriver();
			Spider spider=Spider.create(jDProcessor);
			HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
			httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(GenerateProxy.getRandomProxyIP()));
			spider.setDownloader(httpClientDownloader);
			spider.addUrl("https://search.jd.com/Search?keyword=%E8%8A%92%E6%9E%9C&enc=utf-8&wq=%E8%8A%92%E6%9E%9C&pvid=e37928e300504e0b93705c78c4e93f74&cid2=12221").thread(1).run();
		} catch (Exception e) {
			log.error("定时器crawlerJD执行错误：" + e);
		}finally {
			PhantomJsDriver2.destroyDriver();
		}
		log.info("定时器crawlerJD执行结束：" + DateUtils.getCurrentTimeStr());
	}
	
	private void cleanData(){
		log.info("cleanData执行开始：" + DateUtils.getCurrentTimeStr());
		try {
			daCrawlerUntBll.dataCleaning();
		} catch (Exception e) {
			log.error("定时器cleanData执行错误：" + e);
		}
		log.info("cleanData执行结束：" + DateUtils.getCurrentTimeStr());
	}
}