package com.zee.utl.crawler;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * PhantomJs是一个基于webkit内核的无头浏览器，即没有UI界面，即它就是一个浏览器，只是其内的点击、翻页等人为相关操作需要程序设计实现;
 */
public class PhantomJsDriver2 {
	public static ArrayBlockingQueue<WebDriver> queue2 = new ArrayBlockingQueue<WebDriver>(10);
	
	public static void creatDriver(){
		DesiredCapabilities dcaps = getDcaps();
		for(int i = 0; i < 3; i++){
			WebDriver driver = new PhantomJSDriver(dcaps);
	        queue2.add(driver);
		}
    }

	public static DesiredCapabilities getDcaps() {
		DesiredCapabilities dcaps = new DesiredCapabilities();
		dcaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		dcaps.setCapability(CapabilityType.TAKES_SCREENSHOT, false);
		dcaps.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
		dcaps.setJavascriptEnabled(true);
		dcaps.setCapability( 
				PhantomJSDriverService.PHANTOMJS_PAGE_CUSTOMHEADERS_PREFIX 
				+ "User-Agent", 
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/50.0.2661.102 Safari/537.36"); 
		ArrayList<String> cliArgsCap2 = new ArrayList<String>();
        cliArgsCap2.add("--load-images=false");
        cliArgsCap2.add("--disk-cache=yes");
        cliArgsCap2.add("--ignore-ssl-errors=true");
		dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_CLI_ARGS, cliArgsCap2);
		dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_PAGE_SETTINGS_PREFIX+"loadImages", false);
		if(System.getProperties().getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1)
			dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PhantomJsDriver.WIN_PATH);
		else
			dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PhantomJsDriver.LINUX_PATH);
		return dcaps;
	}
	
	public static void destroyDriver(){
		while(null != queue2.peek()){
			WebDriver remove = queue2.remove();
			remove.quit();
		}
	}

    public static WebDriver getPhantomJSDriver(){
    	WebDriver poll = queue2.poll();
        if(null == poll){
        	DesiredCapabilities dcaps = getDcaps();
			WebDriver driver = new PhantomJSDriver(dcaps);
	        poll = driver;
        }
        return poll;
    }
    
    public static void setPhantomJSDriver(WebDriver driver){
        if(!queue2.offer(driver)){
        	driver.quit();
        }
    }
}