package com.jusfoun.utl.crawler;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import com.jusfoun.bll.extend.unity.da.DaCrawlerUntBll;
import com.jusfoun.ent.extend.da.DaCrawler;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;
import us.codecraft.webmagic.selector.Html;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
public class TaoBaoProcessor implements PageProcessor {
	private static final Logger log = LoggerFactory.getLogger(TaoBaoProcessor.class);

    private static final String TAO_BAO_DETAIL_URL_START="https://item.taobao.com/item.htm";
    private static final String TIAN_MAO_DETAIL_URL_START="https://detail.tmall.com/item.htm";
//    private static final String LOGIN_USER_NAME="lcc@jusfoun.com";
//    private static final String LOGIN_PASSWORD="lcc_jusfoun";
    
	@Autowired
	protected DaCrawlerUntBll daCrawlerUntBll;
	/**
	 * 参数设置
	 */
    private Site site = Site
            .me()
            .setCharset("UTF-8")
            .setCycleRetryTimes(3)
            .setRetryTimes(3)
            .setSleepTime(3 * 1000)
            .addHeader("Connection", "keep-alive")
            .addHeader("Cache-Control", "max-age=0")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:50.0) Gecko/20100101 Firefox/50.0");
    	
    @Override
    public Site getSite() {
        return site;
    }

    /**
     * 处理
     */
    @Override
    public void process(Page page) {
    	WebDriver driver;
		try {
			driver = PhantomJsDriver.getPhantomJSDriver();
		} catch (Exception e1) {
			log.debug("TaoBaoProcessor-login:"+e1);
			return;
		}
    	boolean bl = true;
        try {
        	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            String url=page.getRequest().getUrl();
            driver.get(url);
            Thread.sleep(3000);
            WebElement webElement = driver.findElement(By.id("page"));
             if(url.startsWith(TAO_BAO_DETAIL_URL_START)){
        		 driver.findElement(By.id("J_PromoPriceNum"));
             }else if(url.startsWith(TIAN_MAO_DETAIL_URL_START)){
            	 driver.findElement(By.id("J_PromoPrice"));
             }
	        String str = webElement.getAttribute("outerHTML");
//	        File f = new File("E:\\abc.html");
//	        BufferedWriter output;
//			try {
//				output = new BufferedWriter(new FileWriter(f));
//				output.write(str);  
//				output.flush();  
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			Html html = new Html(str);
	    	if(isFirstPage(html)){
	            analysisPagination(page,html);
	        }else if(isListPage(html)){
	            analysisListPage(page,html);
	        }else {
	            analysisDetailPage(page,html,driver);
	        }
		} catch (Exception e) {
			log.error("TaoBaoProcessor-process:"+e);
			if(null != e.getMessage() && e.getMessage().contains("died")){
	        	PhantomJsDriver.setPhantomJSDriver(driver);
				PhantomJsDriver.destroyDriver();
				try {
					PhantomJsDriver.creatDriver();
				} catch (Exception e1) {
					log.debug("TaoBaoProcessor-login:"+e1);
					return;
				}
				bl = false;
			}
//			File srcFile = ((TakesScreenshot)driver).
//					getScreenshotAs(OutputType.FILE);
//			try {
//				FileUtils.copyFile
//				(srcFile,new File("/mango/backstage/error"+DateUtils.getCurrentTimeStr()+".png"));
//			} catch (IOException e1) {
//				e1.printStackTrace();
//			}
		}
        if(bl)
        	PhantomJsDriver.setPhantomJSDriver(driver);
    }

    /**
     * 获取列表
     * @param page
     * @param html
     * @param driver
     */
    private void analysisListPage(Page page, Html html) {
    	log.info("ListUrl:"+page.getUrl());
        List<String> detailPageList= html.xpath("//*[@id=\"mainsrp-itemlist\"]").$("a[id^='J_Itemlist_TLink_']").xpath("//a/@href").regex("(//(detail.tmall|item.taobao).com/item.htm.+)").all();
        page.addTargetRequests(detailPageList);
    }

    /**
     * 分析分页规则
     * @param page
     * @param html
     */
    private void analysisPagination(Page page,Html html){
    	analysisListPage(page,html);
    	Integer max = Integer.valueOf(html.xpath("//*[@id=\"mainsrp-pager\"]/div/div/div/div[2]/input/@max").get());
        List<String> pageParameterList=new ArrayList<>();
        max = max>5?5:max;
        for(int i = 1; i < max; i++){
        	pageParameterList.add(page.getUrl() + "&s=" + i*44);
        }
        page.addTargetRequests(pageParameterList);
    }

    /**
     * 分析详情页
     * @param page
     * @param html
     * @param driver
     */
    private void analysisDetailPage(Page page,Html html,WebDriver driver){
       String url=page.getUrl().toString();
       DaCrawler d = new DaCrawler();
        if(url.startsWith(TAO_BAO_DETAIL_URL_START)){
            analysisTaoBaoDetailPage(page,html,driver,d);
        }else if(url.startsWith(TIAN_MAO_DETAIL_URL_START)){
            analysisTianMaoDetailPage(page,html,driver,d);
        }
        //爬取数据入库
        if(StringUtils.isNotBlank(d.getGoodsId())){
        	Map<String, Object> map = new HashMap<String, Object>();
    		map.put("Sql", "SELECT id, `promo_price` FROM da_crawler WHERE goods_id = '"+d.getGoodsId()+"' and source = " + d.getSource());
    		List<Map<String, Object>> listmd = (List<Map<String, Object>>)daCrawlerUntBll.getListBySQL(map).getData();
    		if(ListUtils.isEmpty(listmd)){
    			daCrawlerUntBll.add(d,false);
    		}else{ 
    			BigDecimal bigDecimal = new BigDecimal(listmd.get(0).get("promo_price").toString());
    			if(bigDecimal.compareTo(d.getPromoPrice()) != 0){
    				d.setId(listmd.get(0).get("id").toString());
    				daCrawlerUntBll.update(d,false);
    			}
    		}
        }
    }

    /**
     * 分析淘宝详情页
     * @param page
     * @param html
     * @param driver
     * @param d 
     */
    private void analysisTaoBaoDetailPage(Page page,Html html,WebDriver driver, DaCrawler d){
    	log.info("TaoBaoUrl:"+page.getUrl());
        String parameter = html.xpath("//*[@id=\"attributes\"]").get();//产品参数
    	if(StringUtils.isNotBlank(parameter) && parameter.contains("水果种类")){
    		String promoPrice = html.xpath("//*[@id=\"J_PromoPriceNum\"]/html()").get();//淘宝价
    		String price = html.xpath("//*[@id=\"J_StrPrice\"]/em[2]/html()").get();//价格
    		String name = html.xpath("//*[@id=\"J_Title\"]/h3/html()").get();//商品名称
    		String from = html.xpath("//*[@id=\"J-From\"]/html()").get();//配送地
    		String sellCounter = html.xpath("//*[@id=\"J_SellCounter\"]/html()").get();//交易成功
    		String rateCounter = html.xpath("//*[@id=\"J_RateCounter\"]/html()").get();//累计评价
    		String inventory = html.xpath("//*[@id=\"J_SpanStock\"]/html()").get();//库存
    		String id = page.getUrl().regex("(?:^|\\?|&)id=(\\d*)(?:&|$)").get();//商品主键
    		d.setCreatedTime(DateUtils.getCurrentTime());
    		d.setFrom(from);
    		d.setGoodsId(id);
    		d.setSource((byte) 2);
    		d.setInventory(inventory);
    		d.setName(name);
    		d.setParameter(parameter);
    		d.setPrice(new BigDecimal(price));
    		if(StringUtils.isBlank(promoPrice))
    			d.setPromoPrice(new BigDecimal(price));
    		else
    			d.setPromoPrice(new BigDecimal(promoPrice));
    		d.setRateCounter(rateCounter);
    		d.setSellCounter(sellCounter);
    	}
    }

    /**
     * 分析天猫详情页
     * @param page
     * @param html
     * @param driver
     * @param d 
     */
    private void analysisTianMaoDetailPage(Page page,Html html,WebDriver driver, DaCrawler d){
    	log.info("TianMaoUrl:"+page.getUrl());
    	String parameter = html.xpath("//*[@id=\"J_AttrUL\"]").get();
    	if(StringUtils.isNotBlank(parameter) && parameter.contains("水果种类")){
    		String promoPrice = html.xpath("//*[@id=\"J_PromoPrice\"]/dd/div/span/html()").get();//狂欢价
    		String price = html.xpath("//*[@id=\"J_StrPriceModBox\"]/dd/span/html()").get();//专柜价//*
    		String name = html.xpath("//*[@id=\"J_DetailMeta\"]/div[1]/div[1]/div/div[1]/h1/html()").get();//商品名称
    		String from = html.xpath("//*[@id=\"J_deliveryAdd\"]/html()").get();//配送地
    		String sellCounter = html.xpath("//*[@id=\"J_DetailMeta\"]/div[1]/div[1]/div/ul/li[1]/div/span[2]/html()").get();//月销量
    		String rateCounter = html.xpath("//*[@id=\"J_ItemRates\"]/div/span[2]/html()").get();//累计评价
    		String inventory = html.xpath("//*[@id=\"J_EmStock\"]/html()").get();//库存
    		String id = page.getUrl().regex("(?:^|\\?|&)id=(\\d*)(?:&|$)").get();//商品主键
    		d.setCreatedTime(DateUtils.getCurrentTime());
    		d.setFrom(from);
    		d.setGoodsId(id);
    		d.setSource((byte) 1);
    		d.setInventory(inventory);
    		d.setName(name);
    		d.setParameter(parameter);
    		d.setPrice(new BigDecimal(price));
    		if(StringUtils.isBlank(promoPrice))
    			d.setPromoPrice(new BigDecimal(price));
    		else
    			d.setPromoPrice(new BigDecimal(promoPrice));
    		d.setRateCounter(rateCounter);
    		d.setSellCounter(sellCounter);
    	}
    }

    /**
     * 是否为列表页
     * @param html
     * @return
     */
    private boolean isListPage(Html html) {
        String tmp = html.$("#mainsrp-pager").get();
        if (StringUtils.isNotBlank(tmp)) {
            return true;
        }
        return false;
    }

    /**
     * 列表页获取当前页码
     * @param html
     * @return
     */
    private String getCurrentPageNo(Html html){
        return html.xpath("//*[@id=\"mainsrp-pager\"]/div/div/div/ul/li[contains(@class,'active')]/span/text()").toString();
    }

    /**
     * 判断是否列表页的第一页
     * @param html
     * @return
     */
    private Boolean isFirstPage(Html html){
        return isListPage(html)&&null!=getCurrentPageNo(html)&&getCurrentPageNo(html).equals("1");
    }

//    public static void main(String[] args) throws IOException, InterruptedException {
//    	PhantomJsDriver.creatDriver();
//        Spider spider=Spider.create(new TaoBaoProcessor());
//        HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
//        httpClientDownloader.setProxyProvider(SimpleProxyProvider.from(GenerateProxy.getRandomProxyIP()));
//        spider.setDownloader(httpClientDownloader);
//        spider.addUrl("https://detail.tmall.com/item.htm?id=567474408014&ns=1&abbucket=4").thread(1).run();
//        PhantomJsDriver.destroyDriver();
//    }
}