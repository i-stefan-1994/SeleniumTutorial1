package com.herokuapp.theinternet.baseTest;

import com.herokuapp.theinternet.base.BrowserDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    @Parameters({"browser"})
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("firefox") String browser, ITestContext ctx) {
//        Creating drivers

        String testName = ctx.getCurrentXmlTest().getName();
//        log = LogManager.getLogManager().getLogger(testName);
        log = LogManager.getLogger(testName);

        BrowserDriverFactory factory = new BrowserDriverFactory(browser, log);
        driver = factory.createDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    protected void tearDown() {
        log.info("Close driver");
        driver.quit();
    }
}
