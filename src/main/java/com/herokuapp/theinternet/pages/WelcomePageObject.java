package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WelcomePageObject extends BasePageObject {

    String pageUrl = "http://the-internet.herokuapp.com/";
    private By formAuthenticationLocator = By.linkText("Form Authentication");
    private By checkboxLocator = By.xpath("//a[contains(text(),'Checkboxes')]");
    private By dropDownLocator = By.xpath("//a[contains(text(),'Dropdown')]");

    public WelcomePageObject(WebDriver driver, Logger log){
        super(driver, log);
    }



    public void openPage(){
        log.info("Opening page " + pageUrl);
        openUrl(pageUrl);
        log.info("Page opened!");
    }

    public LoginPage clickFormAuthenticationLink(){
        log.info("Clicking Form Authentication link on Welcome Page");
        click(formAuthenticationLocator);
        return new LoginPage(driver, log);
    }

    public CheckboxesPage clickCheckBoxesLink(){
        log.info("Clicking Checkboxes link on the Welcome Page");
        click(checkboxLocator);
        return new CheckboxesPage(driver, log);
    }

    public DropDownPage clickDropDownLink(){
        log.info("Clicking Dropdown link on the Welcome page");
        click(dropDownLocator);
        return new DropDownPage(driver, log);
    }

}
