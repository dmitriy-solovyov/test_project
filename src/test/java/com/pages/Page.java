package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {

    private WebDriver driver;

    private By currentBalance = By.xpath("//dd[@id='balance-holder']//span");
    private By amountElement = By.id("amount");
    private By doPaymentButton = By.xpath("//a[@data-bind='click: doPayment']");
    private By doResetButton = By.xpath("//a[@data-bind='click: doReset']");

    private By increaseButton = By.xpath("//a[@data-bind='click: moveRight']");
    private By decreaseButton = By.xpath("//a[@data-bind='click: moveLeft']");
    private By doPurchaseButton = By.xpath(" //a[text()='Подключить']");

    private By mainOfferTime = By.xpath("//div[@class='main-offer']//div[@class='time']//strong");
    private By mainOfferSpeed = By.xpath("//div[@class='main-offer']//div[@class='speed']//strong");
    private By mainOfferCost = By.xpath("//div[@class='main-offer']//div[@class='cost']//strong");

    private By mainOfferTimeDimension = By.xpath("//div[@class='main-offer']//div[@class='time']//span");
    private By mainOfferSpeedDimension = By.xpath("//div[@class='main-offer']//div[@class='speed']//span");
    private By mainOfferCostDimension = By.xpath("//div[@class='main-offer']//div[@class='cost']//span");

    private By tariffUnavailableTime = By.xpath("//div[@class='tariff unavailable']//div[@class='time']//strong");
    private By tariffUnavailableSpeed = By.xpath("//div[@class='tariff unavailable']//div[@class='speed']//strong");
    private By tariffUnavailableCost = By.xpath("//div[@class='tariff unavailable']//div[@class='cost no-arrow']//strong");

    private By tariffUnavailableTimeDimension = By.xpath("//div[@class='tariff unavailable']//div[@class='time']//span");
    private By tariffUnavailableSpeedDimension = By.xpath("//div[@class='tariff unavailable']//div[@class='speed']//span");
    private By tariffUnavailableCostDimension = By.xpath("//div[@class='tariff unavailable']//div[@class='cost no-arrow']//span");


    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void enterAmount(String amount){
        driver.findElement(amountElement).clear();
        driver.findElement(amountElement).sendKeys(amount);
    }

    public void doPayment(){
        driver.findElement(doPaymentButton).click();
    }

    public void doReset(){
        driver.findElement(doResetButton).click();
    }

    public String getCurrentBalance(){
        return driver.findElement(currentBalance).getText();
    }

    public void increasePlan(){
        driver.findElement(increaseButton).click();
    }

    public void decreasePlan(){
        driver.findElement(decreaseButton).click();
    }

    public boolean isPurchaseButtonEnabled(){
        String className = driver.findElement(doPurchaseButton).getAttribute("class");
        boolean isEnabled = className.equals("btn") ? true : false;
        return isEnabled;
    }

    public void doPurchase(){
        driver.findElement(doPurchaseButton).click();
    }

    public String getMainOfferTime() {
        return driver.findElement(mainOfferTime).getText() + driver.findElement(mainOfferTimeDimension).getText();
    }

    public String getTariffUnavailableCost() {
        return driver.findElement(tariffUnavailableCost).getText() + driver.findElement(tariffUnavailableCostDimension).getText();
    }

    public String getMainOfferSpeed() {
        return driver.findElement(mainOfferSpeed).getText() + driver.findElement(mainOfferSpeedDimension).getText();
    }

    public String getMainOfferCost() {
        return driver.findElement(mainOfferCost).getText() + driver.findElement(mainOfferCostDimension).getText();
    }

    public String getTariffUnavailableTime() {
        return driver.findElement(tariffUnavailableTime).getText();
    }

    public String getTariffUnavailableSpeed() {
        return driver.findElement(tariffUnavailableSpeed).getText() + driver.findElement(tariffUnavailableSpeedDimension).getText();
    }

    public boolean isCurrentBalancePresent(){
        return driver.findElement(currentBalance).isDisplayed();
    }

    public boolean isAmountElementPresent(){
        return driver.findElement(amountElement).isDisplayed();
    }

    public boolean isDoPaymentButtonPresent(){
        return driver.findElement(doPaymentButton).isDisplayed();
    }

    public boolean isDoResetButtonPresent(){
        return driver.findElement(doResetButton).isDisplayed();
    }

    public boolean isIncreaseButtonPresent(){
        return driver.findElement(increaseButton).isDisplayed();
    }

    public boolean isDecreaseButtonPresent(){
        return driver.findElement(decreaseButton).isDisplayed();
    }

    public boolean isDoPurchaseButtonPresent(){
        return driver.findElement(doPurchaseButton).isDisplayed();
    }

    public boolean isMainOfferTimePresent(){
        return driver.findElement(mainOfferTime).isDisplayed();
    }

    public boolean isMainOfferSpeedPresent(){
        return driver.findElement(mainOfferSpeed).isDisplayed();
    }

    public boolean isMainOfferCostPresent(){
        return driver.findElement(mainOfferCost).isDisplayed();
    }

    public boolean isTariffUnavailableTimePresent(){
        return driver.findElement(tariffUnavailableTime).isDisplayed();
    }

    public boolean isTariffUnavailableSpeedPresent(){
        return driver.findElement(tariffUnavailableSpeed).isDisplayed();
    }

    public boolean isTariffUnavailableCostPresent(){
        return driver.findElement(tariffUnavailableCost).isDisplayed();
    }
}
