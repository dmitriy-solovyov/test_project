package com.suite;

import com.utils.Plan;
import com.pages.Page;
import com.providers.PlansDataProvider;
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

    @Test
    public void checkElementsOnPage(){
        Assert.assertTrue(page.isAmountElementPresent(), "Amount element not found");
        Assert.assertTrue(page.isCurrentBalancePresent(), "Current Balance element not found");
        Assert.assertTrue(page.isDoPaymentButtonPresent(), "DoPayment Button not found");
        Assert.assertTrue(page.isDoResetButtonPresent(), "DoReset Button not found");

        Assert.assertTrue(page.isIncreaseButtonPresent(), "Increase button not found");
        Assert.assertTrue(page.isDecreaseButtonPresent(), "Decrease button not found");
        Assert.assertTrue(page.isDoPurchaseButtonPresent(), "DoPurchase button not found");

        Assert.assertTrue(page.isMainOfferCostPresent(), "MainOfferCost element not found");
        Assert.assertTrue(page.isMainOfferSpeedPresent(), "MainOfferSpeed element not found");
        Assert.assertTrue(page.isMainOfferTimePresent(), "MainOfferTime element not found");

        Assert.assertTrue(page.isTariffUnavailableCostPresent(), "TariffUnavailableCost element not found");
        Assert.assertTrue(page.isTariffUnavailableSpeedPresent(), "TariffUnavailableSpeed element not found");
        Assert.assertTrue(page.isTariffUnavailableTimePresent(), "TariffUnavailableTime element not found");

        page.doReset();
    }

    @Test(priority = 1, dataProviderClass = PlansDataProvider.class, dataProvider = "plan")
    public void checkPlans(Plan plan){
        page.increasePlan();
        Plan currentPlan = new Plan(page.getMainOfferTime(), page.getMainOfferSpeed(), page.getMainOfferCost());
        Assert.assertEquals(currentPlan,plan);
    }

    @Test(priority = 2)
    public void addMoneyToBalance(){
        page.doReset();
        page.enterAmount("100");
        page.doPayment();
        Assert.assertEquals(page.getCurrentBalance(), "100", "Current balance has incorrect value");
    }

    @Test(priority = 3)
    public void buyPlan(){
        page.doReset();
        page.enterAmount("350");
        page.doPayment();
        page.increasePlan();
        page.doPurchase();
        Plan current = new Plan(page.getTariffUnavailableTime() + "дней останется", page.getTariffUnavailableSpeed(), page.getTariffUnavailableCost());
        Plan selected = new Plan(page.getMainOfferTime(), page.getMainOfferSpeed(), page.getMainOfferCost());

        Assert.assertEquals(current, selected);
    }

    @Test(priority = 4)
    public void checkPurchaseButtonConditions(){
        page.doReset();
        page.enterAmount("350");
        page.doPayment();
        Assert.assertEquals(page.isPurchaseButtonEnabled(), false, "Purchase button should be disabled for current plan");
        page.increasePlan();
        Assert.assertEquals(page.isPurchaseButtonEnabled(), true, "Purchase button should be enabled");
        page.doPurchase();
        Assert.assertEquals(page.isPurchaseButtonEnabled(), false, "Purchase button should be disabled for current plan");
    }

    @Test(priority = 5)
    public void checkParametersAfterRefreshPage(){
        page.doReset();
        page.enterAmount("350");
        page.doPayment();
        String before = page.getCurrentBalance();
        driver.navigate().refresh();
        String after = page.getCurrentBalance();
        Assert.assertEquals(after, before, "Balance before refresh doesn't equal balance after refresh");
    }

    @Test(priority = 6)
    public void checkNegativeBalance(){
        page.doReset();
        page.enterAmount("-350");
        page.doPayment();

        Assert.assertEquals(page.getCurrentBalance(), "0", "Balance cannot be negative");

    }

    @Test(priority = 7)
    public void checkPaymentAndBalanceWithFractionalValue(){
        page.doReset();
        page.enterAmount("500.55");
        page.doPayment();
        page.increasePlan();
        page.doPurchase();

        Assert.assertEquals(page.getCurrentBalance(), "200.55");
    }

    @AfterSuite
    public void stopServer(){
        driver.close();
        driver.quit();
        ps.destroy();
    }

}