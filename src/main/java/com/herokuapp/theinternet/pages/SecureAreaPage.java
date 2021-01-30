package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePageObject {

    private String pageUrl = "http://the-internet.herokuapp.com/secure";
    private By logOutButton = By.xpath("//a[@class='button secondary radius']");
    private By successMessage = By.id("flash-messages");
    private By failMessage = By.xpath("//div[@id='flash']");

    public SecureAreaPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Get URL variable from PageObject
     * @return
     */
    public String getPageUrl(){
        return pageUrl;
    }

    /**
     * Verification if logOutButton is visible on the page
     * @return
     */
    public boolean isLogOutbuttonVisible(){
        return find(logOutButton).isEnabled();
    }

    /**
     * Return text from success message
     * @return
     */
    public String getSuccessMessageText(){
        return find(successMessage).getText();
    }

    /**
     * Return text from a fail message
     * @return
     */
    public String getFailMessageText(){
        return find(failMessage).getText();
    }
}
