package com.herokuapp.theinternet;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests {

    WebElement webElement = null;
    private WebDriver driver;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    private void setUp(String browser) {
//        Create driver

        switch(browser){
            case "crome":
                break;
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
                driver = new FirefoxDriver();
                break;
        }

        driver.manage().window().maximize();
    }

    @Test(priority = 0, groups = {"positiveTests", "smokeTests"})
    public void logInTest() {
        System.out.println("Starting login test");
        String url = "http://the-internet.herokuapp.com/";
        driver.get(url);
        System.out.println("Main page is opened");

        driver.findElement(By.linkText("Form Authentication")).click();

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("tomsmith");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("SuperSecretPassword!");

        WebDriverWait wait = new WebDriverWait(driver, 10);

        webElement = driver.findElement(By.className("radius"));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
        boolean isClickable = webElement.isEnabled();
        webElement.click();

        Assert.assertTrue(isClickable, "Button is not clickable");

        tearDown();

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(priority = 1, groups = {"negativeTests", "smokeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        System.out.println("Starting login test");
        String url = "http://the-internet.herokuapp.com/";
        driver.get(url);
        System.out.println("Main page is opened");

        driver.findElement(By.linkText("Form Authentication")).click();

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

        webElement = driver.findElement(By.className("radius"));
//        wait.until(ExpectedConditions.elementToBeClickable(webElement));
//        boolean isClickable = webElement.isEnabled();
        webElement.click();

        webElement = driver.findElement(By.id("flash"));
//        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = webElement.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual error message does not contain expected. \nActual: " +
                        actualErrorMessage + "\nExpected: " + expectedErrorMessage);

        tearDown();

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(priority = 0, groups = {"negativeTests"})
    public void incorrectPasswordTest(String username, String password, String expectedErrorMessage) {
        String url = "http://the-internet.herokuapp.com/";
        driver.get(url);
        System.out.println("Main page is opened");

        driver.findElement(By.linkText("Form Authentication")).click();

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);

        webElement = driver.findElement(By.className("radius"));
//        wait.until(ExpectedConditions.elementToBeClickable(webElement));
//        boolean isClickable = webElement.isEnabled();
        webElement.click();

        webElement = driver.findElement(By.id("flash"));
//        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = webElement.getText();

        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),
                "Actual error message does not contain expected. \nActual: " +
                        actualErrorMessage + "\nExpected: " + expectedErrorMessage);

        tearDown();
    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        driver.quit();
    }
}
