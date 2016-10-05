package com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MainTest {

    private Process ps;
    private WebDriver driver;
    private Page page;

    @BeforeSuite
    public void runServer(){
        try {
            ps = Runtime.getRuntime().exec("nohup java -jar ./src/test/resources/test-slider-1.0.0-SNAPSHOT.jar &");
        } catch (IOException e) {
            e.printStackTrace();
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("http://localhost:4567/index.html");
        page = new Page(driver);
    }

//    @Test
//    public void test(){
//        driver = new FirefoxDriver();
//        driver.get("http://localhost:4567/index.html");
//        driver.findElement(By.id("amount")).clear();
//        driver.findElement(By.id("amount")).sendKeys("100");
//        driver.findElement(By.cssSelector("a.btn:nth-child(1)")).click();// //a[@data-bind='click: doPayment']
//        driver.findElement(By.cssSelector("a.btn:nth-child(2)")).click();//кнопка сброса
//        String res = driver.findElement(By.cssSelector("#balance-holder > span:nth-child(1)")).getText();
//        System.out.println(res);
//
////        .decrease > a:nth-child(1)
////                .increase > a:nth-child(1)
//
//    }
//
//    @Test(priority = 1)
//    public void test2(){
//        driver.get("http://localhost:4567/index.html");
//        driver.findElement(By.cssSelector(".increase > a:nth-child(1)")).click();
//        String res = driver.findElement(By.xpath("//div[@class='tariff unavailable']//div[@class='time']//strong")).getText();
//        System.out.println(res);
//        String res2 = driver.findElement(By.xpath("//div[@class='main-offer']//div[@class='time']//strong")).getText();
//        System.out.println(res2);
//        //div[@class='main-offer']//div[@class='speed']//strong
//        //div[@class='main-offer']//div[@class='cost']//strong
//        //div[@class='tariff unavailable']//div[@class='speed']//strong
//        //div[@class='tariff unavailable']//div[@class='cost no-arrow']//strong
//        //a[text()='Подключить']
//
//
//
//    }

    @Test
    public void test1(){
        page.enterAmount("100");
        page.doPayment();
        System.out.println(page.getCurrentBalance());
        System.out.println(page.isPurchaseButtonEnabled());
    }

    @Test(priority = 1)
    public void test2(){
        Assert.assertEquals(page.isPurchaseButtonEnabled(), false);
        page.increasePlan();
        Assert.assertEquals(page.isPurchaseButtonEnabled(), true);
        page.doPurchase();
        Assert.assertEquals(page.getMainOfferCost(), page.getTariffUnavailableCost());
        Assert.assertEquals(page.getMainOfferSpeed(), page.getTariffUnavailableSpeed());
        Assert.assertEquals(page.isPurchaseButtonEnabled(), false);
    }

    @Test(priority = 2)
    public void test3(){
        page.doReset();
        Assert.assertEquals(page.getCurrentBalance(), "0");
    }

    @Test(priority = 3)
    public void test4(){
        page.enterAmount("500.35");
        page.doPayment();
        page.increasePlan();
        page.doPurchase();
        Assert.assertEquals(page.getCurrentBalance(), "200.35");
    }

    @Test(priority = 4)
    public void test5(){
        String before = page.getCurrentBalance();
        driver.navigate().refresh();
        String after = page.getCurrentBalance();
        Assert.assertEquals(after, before);

    }



    @AfterSuite
    public void stopServer(){
        driver.close();
        driver.quit();
        ps.destroy();
    }

}


