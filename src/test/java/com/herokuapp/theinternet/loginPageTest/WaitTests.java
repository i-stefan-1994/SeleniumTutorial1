package com.herokuapp.theinternet.loginPageTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class WaitTests {

    WebElement webElement = null;
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    private void setUp() {
//        Create driver
        System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void notVisibleTest(){
        System.out.println("Starting test");
        String url = "http://the-internet.herokuapp.com/dynamic_loading/1";
        driver.get(url);
        driver.findElement(By.xpath("//button[contains(text(),'Start')]")).click();

        String expectedFinishText = "Hello World!";
        /**
         * explicit wait
         */
        WebElement finishElement = driver.findElement(By.id("finish"));

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(finishElement));

        String actualFinishText = finishElement.getText();
        System.out.println(actualFinishText);
        Assert.assertTrue(actualFinishText.contains(expectedFinishText));

    }

    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        driver.close();
    }
}
