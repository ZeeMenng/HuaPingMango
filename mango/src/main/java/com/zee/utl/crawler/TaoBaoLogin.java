package com.zee.utl.crawler;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import us.codecraft.webmagic.selector.Html;
import java.io.IOException;
import java.util.Random;

public class TaoBaoLogin {

    private static final String LOGIN_USER_NAME="lcc@jusfoun.com";
    private static final String LOGIN_PASSWORD="lcc_jusfoun";

    public static void taoBaoLogin(WebDriver driver) throws IOException, InterruptedException {
        driver.get("https://login.taobao.com/member/login.jhtml?spm=a1z5c.7883446.754894437.1.730e65e7dRmGq8&f=top&redirectURL=https%3A%2F%2Fsurvey.taobao.com%2Fsurvey%2FQ095thIx2%3Fspm%3Da2107.1.1000340.2.564c11d9FHZwVV");
        Actions action = new Actions(driver);
        WebElement webElement = driver.findElement(By.id("page"));
        String str = webElement.getAttribute("outerHTML");
        Html html = new Html(str);
		String tmp = html.$("#TPL_username_1").get();
        if (StringUtils.isNotBlank(tmp)) {
        	Random rand = new Random();
        	Thread.sleep(rand.nextInt(5000));
        	 action.click(driver.findElement(By.id("J_Quick2Static")));
        	 action.perform();
        	 Thread.sleep(rand.nextInt(5000));
        	 action.click(driver.findElement(By.xpath("//*[@id=\"J_OtherLogin\"]/a[1]")));
        	 action.perform();
        	 Thread.sleep(rand.nextInt(5000));
        	 action.click(driver.findElement(By.xpath("//*[@id=\"pl_login_logged\"]/div/div[2]/div/input")));
             action.sendKeys(LOGIN_USER_NAME);
             action.perform();
             Thread.sleep(rand.nextInt(5000));
        	 WebElement passwordElement = driver.findElement(By.xpath("//*[@id=\"pl_login_logged\"]/div/div[3]/div/input"));
        	 action.click(passwordElement);
             action.sendKeys(LOGIN_PASSWORD);
             action.perform();
             Thread.sleep(rand.nextInt(5000));
//             WebElement source = driver.findElement(By.id("nc_1_n1z"));
//             action.clickAndHold(source).moveToElement(source, 314, 0);
//             action.perform();
//             action.release();
//             action.perform();
//             Thread.sleep(rand.nextInt(5000));
             action.click(driver.findElement(By.xpath("//*[@id=\"pl_login_logged\"]/div/div[7]/div[1]/a/span")));
             action.perform();
             Thread.sleep(rand.nextInt(5000));
        }
    }
}