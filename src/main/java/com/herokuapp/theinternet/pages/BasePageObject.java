package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePageObject {
    protected WebDriver driver;
    protected Logger log;

    public BasePageObject(WebDriver driver, Logger log) {
        this.driver = driver;
        this.log = log;
    }

    /**
        open page with given url
     */
    protected void openUrl(String url){
        driver.get(url);
    }

    /**
     * find element by given locator
     * @param locator
     * @return
     */
    protected WebElement find(By locator){
        return driver.findElement(locator);
    }


    /**
     * Click element when locator is visible
     * @param locator
     */
    protected void click(By locator){
        waitForVisibilityOf(locator, 5);
        find(locator).click();
    }

    protected void type(String text, By locator){
        waitForVisibilityOf(locator);
        find(locator).sendKeys(text);
    }

    /**
     * Wait for specific ExpectedCondition for the given ammount of time in seconds
     * @param condition
     * @param timeOutSeconds
     */
    private void waitFor(ExpectedCondition<WebElement> condition, Integer timeOutSeconds){
        timeOutSeconds = timeOutSeconds != null ? timeOutSeconds : 30;
        WebDriverWait wait = new WebDriverWait(driver, timeOutSeconds);
        wait.until(condition);
    }

    protected void waitForVisibilityOf(By locator, Integer ... timeoutInSeconds){
        int attempts = 0;
        while(attempts < 2){
            try {
                waitFor(ExpectedConditions.visibilityOfElementLocated(locator),
                        (timeoutInSeconds.length) > 0 ? timeoutInSeconds[0] : null);
                break;
            }catch (StaleElementReferenceException e){
                e.printStackTrace();
            }
            attempts++;
        }
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
}
