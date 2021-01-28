package com.zee.utl.crawler;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;
import com.zee.bll.extend.unity.da.DaCrawlerUntBll;
import com.zee.ent.extend.da.DaCrawler;
import com.zee.utl.DateUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JDProcessor implements PageProcessor {
	private static final Logger log = LoggerFactory.getLogger(JDProcessor.class);
    
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
    	WebDriver driver = PhantomJsDriver2.getPhantomJSDriver();
    	boolean bl = true;
        try {
        	driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            driver.get(page.getRequest().getUrl());
            WebElement webElement = null;
			if(page.getRequest().getUrl().contains("search")){
				webElement = driver.findElement(By.id("J_main"));
            }else{
            	driver.findElement(By.cssSelector("span.p-price>span.price"));
            	webElement = driver.findElement(By.tagName("body"));
            }
	        String str = webElement.getAttribute("outerHTML");
			Html html = new Html(str);
	    	if(isFirstPage(html)){
	            analysisPagination(page,html);
	        }else if(isListPage(html)){
	            analysisListPage(page,html);
	        }else {
	            analysisDetailPage(page,html,driver);
	        }
		} catch (Exception e) {
			log.error("JDProcessor-process:"+e);
			if(null != e.getMessage() && e.getMessage().contains("died")){
				PhantomJsDriver2.destroyDriver();
				PhantomJsDriver2.creatDriver();
				bl = false;
			}
		}
        if(bl)
        	PhantomJsDriver2.setPhantomJSDriver(driver);
    }

    /**
     * 获取列表
     * @param page
     * @param html
     * @param driver
     */
    private void analysisListPage(Page page, Html html) {
    	log.info("ListUrl:"+page.getUrl());
        List<String> detailPageList= html.xpath("//*[@id=\"J_goodsList\"]").$("li").xpath("//a[1]/@href").regex("//item.jd.com.+\\.html").all();
        page.addTargetRequests(detailPageList);
    }

    /**
     * 分析分页规则
     * @param page
     * @param html
     */
    private void analysisPagination(Page page,Html html){
    	analysisListPage(page,html);
    	Integer max = Integer.valueOf(html.xpath("//*[@id=\"J_bottomPage\"]/span[2]/em[1]/b/text()").get());
        List<String> pageParameterList=new ArrayList<>();
        max = max>30?30:max;
        for(int i = 1; i < max; i++){
        	pageParameterList.add(page.getUrl() + "&qrst=1&rt=1&stop=1&vt=2&stock=1&cid2=12221&page="+ (2*i-1) +"&s="+ i*56+3 +"&click=0");
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
        DaCrawler d = new DaCrawler();
        analysisTaoBaoDetailPage(page,html,driver,d);
        //爬取数据入库
        if(StringUtils.isNotBlank(d.getGoodsId())){
        	Map<String, Object> map = new HashMap<String, Object>();
    		map.put("Sql", "SELECT id, `promo_price` FROM da_crawler WHERE goods_id = '"+d.getGoodsId()+"' and source = " + d.getSource());
    		List<Map<String, Object>> listmd = (List<Map<String, Object>>)daCrawlerUntBll.getListBySQL(map).getData();
    		if(ListUtils.isEmpty(listmd)){
    			daCrawlerUntBll.add(d);
    		}else{ 
    			BigDecimal bigDecimal = new BigDecimal(listmd.get(0).get("promo_price").toString());
    			if(bigDecimal.compareTo(d.getPromoPrice()) != 0){
    				d.setId(listmd.get(0).get("id").toString());
    				daCrawlerUntBll.update(d);
    			}
    		}
        }
    }

    /**
     * 分析京东详情页
     * @param page
     * @param html
     * @param driver
     * @param d 
     */
    private void analysisTaoBaoDetailPage(Page page,Html html,WebDriver driver, DaCrawler d){
    	log.info("JDUrl:"+driver.getCurrentUrl());
        String parameter = html.css("#detail ul.parameter2.p-parameter-list").get();//产品参数
		String price = html.xpath("//*[@class=\"p-price\"]/[@class=\"price\"]/html()").get();//价格
		String name = html.xpath("//*[@class=\"sku-name\"]/html()").replace("<.*>", "").get();//商品名称
		String from = html.xpath("//*[@id=\"summary-service\"]/html()").regex("从.+市").get();//配送地
		String rateCounter = html.xpath("//*[@id=\"comment-count\"]").regex("\\d+").get();//累计评价
		String id = "";//商品主键
		Pattern p = Pattern.compile("\\d*");
		Matcher m = p.matcher(driver.getCurrentUrl());
		while (m.find()) {
			id = m.group();
			if(!id.isEmpty())
				break;
		}
		d.setCreatedTime(DateUtils.getCurrentTime());
		d.setFrom(from);
		d.setGoodsId(id);
		d.setSource((byte) 3);
		d.setName(name);
		d.setParameter(parameter);
		d.setPrice(new BigDecimal(price));
		d.setPromoPrice(new BigDecimal(price));
		d.setRateCounter(rateCounter);
    }

    /**
     * 是否为列表页
     * @param html
     * @return
     */
    private boolean isListPage(Html html) {
        String tmp = html.$("#J_bottomPage").get();
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
        return html.xpath("//*[@id=\"J_bottomPage\"]/span[2]/input/@value").toString();
    }

    /**
     * 判断是否列表页的第一页
     * @param html
     * @return
     */
    private Boolean isFirstPage(Html html){
        return isListPage(html)&&null!=getCurrentPageNo(html)&&getCurrentPageNo(html).equals("1");
    }

//    private static ChromeDriverService service;
//    public static WebDriver getChromeDriver(){
//        System.setProperty("webdriver.chrome.driver","F:\\chromedriver.exe");
//        service = new ChromeDriverService.Builder().usingDriverExecutable(new File("F:\\chromedriver.exe")) .usingAnyFreePort().build();
//        try {
//			service.start();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        // 创建一个 Chrome 的浏览器实例
//        return new RemoteWebDriver(service.getUrl(), DesiredCapabilities.chrome());
//    }
}