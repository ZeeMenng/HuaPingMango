package com.jusfoun.utl.crawler;

import org.apache.commons.lang.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import com.jusfoun.bll.extend.unity.da.DaCommonFieldUntBll;
import com.jusfoun.bll.extend.unity.da.DaMarketPriceUntBll;
import com.jusfoun.bll.extend.unity.da.DaWholesaleMarketUntBll;
import com.jusfoun.ent.extend.da.DaCommonField;
import com.jusfoun.ent.extend.da.DaMarketPrice;
import com.jusfoun.utl.DateUtils;
import com.jusfoun.utl.SymbolicConstant;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class MofProcessor implements PageProcessor {
	private static final Logger log = LoggerFactory.getLogger(MofProcessor.class);

	@Autowired
	@Qualifier("daMarketPriceUntBll")
	protected DaMarketPriceUntBll daMarketPriceUntBll;
	@Autowired
	@Qualifier("daCommonFieldUntBll")
	protected DaCommonFieldUntBll daCommonFieldUntBll;
	@Autowired
	@Qualifier("daWholesaleMarketUntBll")
	protected DaWholesaleMarketUntBll daWholesaleMarketUntBll;
	
	private List<Map<String, Object>> listmd;
	public void setListmd(List<Map<String, Object>> listmd) {
		this.listmd = listmd;
	}

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
            WebElement webElement = driver.findElement(By.xpath("/html/body/section/div/div[1]/table"));
	        String str = webElement.getAttribute("outerHTML");
	        Html html = new Html(str);
	        analysisPage(page,html,driver);
		} catch (Exception e) {
			log.error("MofProcessor-process:"+e);
			if(null != e.getMessage() && e.getMessage().contains("died")){
	        	PhantomJsDriver2.setPhantomJSDriver(driver);
				PhantomJsDriver2.destroyDriver();
				PhantomJsDriver2.creatDriver();
				bl = false;
			}
		}
        if(bl)
        	PhantomJsDriver2.setPhantomJSDriver(driver);
    }

    /**
     * 分析详情页
     */
    private void analysisPage(Page page,Html html,WebDriver driver){
    	Document document = html.getDocument();
		Elements trList = document.getElementsByTag("tr");
		for (Element tr : trList) {
			Elements tdList = tr.getElementsByTag("td");
			if(!tdList.isEmpty()){
				String text0 = tdList.get(0).text();
				Date currentTime = DateUtils.string2Date(text0,"yyyy-MM-dd");
				DaMarketPrice dmp = new DaMarketPrice();
				String text1 = tdList.get(1).getElementsByTag("span").text();
				this.setStrains(listmd, dmp, text1.equals("象牙芒")?"红象牙芒":text1);
				String text2 = tdList.get(2).getElementsByTag("span").text();
				this.setPrice(dmp, text2);
				String text3 = tdList.get(3).getElementsByTag("a").text();
				
				DaCommonField dcf = new DaCommonField();
				dmp.setName(text3);
				this.setCity(currentTime, dcf, text3);
				this.addValue(currentTime, dcf, dmp);
			}
		}
    }
    
	/**
	 * 产地
	 */
	private void setCity(Date currentTime, DaCommonField dcf, String attr) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("Sql", "select region_code, region_text from da_wholesale_market WHERE `name` = '"+attr+"'");
		List<Map<String, Object>> listm = (List<Map<String, Object>>)daWholesaleMarketUntBll.getListBySQL(map).getData();
		if(!org.thymeleaf.util.ListUtils.isEmpty(listm)){
			String code = (String) listm.get(0).get("region_code");
			String name = (String) listm.get(0).get("region_text");
			dcf.setRegionId(code);
			dcf.setRegionName(name);
			dcf.setAreaLatitudeTypeCode((byte) 3);
			dcf.setAreaLatitudeTypeText("市");
//			map.put("Sql", "SELECT id, region_id, region_name FROM da_common_field WHERE data_time_type_code = 5 AND source_channel_type_code = 4 AND audit_status_code = 1 AND start_time = str_to_date('"+DateUtils.date2String(currentTime,SymbolicConstant.TIME_FORMAT)+"','%Y-%m-%d %H:%i:%s') AND region_id = '"+code+"'");
//			listm = (List<Map<String, Object>>)daCommonFieldUntBll.getListBySQL(map).getData();
//			if(!org.thymeleaf.util.ListUtils.isEmpty(listm))
//				dcf.setId(listm.get(0).get("id").toString());
		}else{
			log.info("--------------------------region:"+attr);
		}
	}
    
	/**
	 * 单价、价格区间
	 */
	private void setPrice(DaMarketPrice dmp, String attr) {
		dmp.setPerPriceUnitCode((byte) 1);
		dmp.setPerPriceUnitText("元/公斤");
		BigDecimal price = new BigDecimal(attr);
		dmp.setPerPrice(price);
		dmp.setPerPriceUnit(price);
		
		if(price.compareTo(new BigDecimal(20)) == 1){
			dmp.setPriceRangeCode((byte) 5);
			dmp.setPriceRangeText("20元以上/公斤");
		}else if(price.compareTo(new BigDecimal(15)) >= 0){
			dmp.setPriceRangeCode((byte) 4);
			dmp.setPriceRangeText("15-20元/公斤");
		}else if(price.compareTo(new BigDecimal(10)) >= 0){
			dmp.setPriceRangeCode((byte) 3);
			dmp.setPriceRangeText("10-15元/公斤");
		}else if(price.compareTo(new BigDecimal(5)) >= 0){
			dmp.setPriceRangeCode((byte) 2);
			dmp.setPriceRangeText("5-10元/公斤");
		}else{
			dmp.setPriceRangeCode((byte) 1);
			dmp.setPriceRangeText("5元以下/公斤");
		}
	}
    
	/**
	 * 持久化
	 * @param currentTime
	 * @param daCrawler
	 * @param dcf
	 * @param dmp
	 * @param msec
	 */
	private void addValue(Date currentTime, DaCommonField dcf, DaMarketPrice dmp) {
//		if(StringUtils.isBlank(dcf.getId())){
			dcf.setDataTimeTypeCode((byte) 5);
			dcf.setDataTimeTypeText("日");
			dcf.setSourceChannelTypeCode((byte) 4);
			dcf.setSourceChannelTypeText("网络采集");
			dcf.setAuditStatusCode((byte) 1);
			dcf.setAuditStatusText("审核通过");
			dcf.setStartTime(currentTime);
			daCommonFieldUntBll.add(dcf,false);
//		}
		dmp.setCommonFieldId(dcf.getId());
		dmp.setCropTypeCode((byte) 1);
		dmp.setCropTypeText("芒果");
		dmp.setPriceTypeCode((byte) 2);
		dmp.setPriceTypeText("批发价");
		daMarketPriceUntBll.add(dmp,false);
	}

	/**
	 * 种类
	 * @param listmd
	 * @param dmp
	 * @param msec
	 * @param attr
	 */
	private void setStrains(List<Map<String, Object>> listmd, DaMarketPrice dmp, String attr) {
		for (Iterator iterator = listmd.iterator(); iterator.hasNext();) {
			Map<String, Object> map2 = (Map<String, Object>) iterator.next();
			if(attr.contains(map2.get("text").toString())){
				dmp.setStrainsCode(Byte.valueOf(map2.get("code").toString()));
				dmp.setStrainsText(map2.get("text").toString());
				break;
			}
		}
	}
}