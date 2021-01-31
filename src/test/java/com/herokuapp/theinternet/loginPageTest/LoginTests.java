package com.herokuapp.theinternet.loginPageTest;

import com.herokuapp.theinternet.baseTest.testUtilities.TestUtilities;
import com.herokuapp.theinternet.pages.LoginPage;
import com.herokuapp.theinternet.pages.SecureAreaPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTests extends TestUtilities {


    WebElement webElement = null;
    WelcomePageObject welcomePageObject;

    @Test(priority = 0, groups = {"positiveTests", "smokeTests"})
    public void logInTest() {
        welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

        //execute login
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "SuperSecretPassword!");


        //Verifications
        //New page url is expected
        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        //log out button is visible
        Assert.assertTrue(secureAreaPage.isLogOutbuttonVisible(), "Log out button is not visible");

        //succesful log in message
        String expectedSuccessMessage = "You logged into a secure area!";
        String actualSuccessMessage = secureAreaPage.getSuccessMessageText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedSuccessMessage));

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(priority = 1, groups = {"negativeTests"})
    public void negativeLoginTest(String username, String password, String expectedErrorMessage) {
        welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

        //execute login
        SecureAreaPage secureAreaPage = loginPage.logIn("gigimuschi", "SuperSecretPassword!");


        //Verifications
        //New page url is expected
//        Assert.assertEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        //log out button is visible
//        Assert.assertTrue(secureAreaPage.isLogOutbuttonVisible(), "Log out button is not visible");

        //succesful log in message
        String expectedFailMessage = "Your username is invalid!";
        String actualSuccessMessage = secureAreaPage.getFailMessageText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedFailMessage));

    }

    @Parameters({"username", "password", "expectedErrorMessage"})
    @Test(priority = 0, groups = {"negativeTests"})
    public void incorrectPasswordTest(String username, String password, String expectedErrorMessage) {
        welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        LoginPage loginPage = welcomePageObject.clickFormAuthenticationLink();

        //execute login
        SecureAreaPage secureAreaPage = loginPage.logIn("tomsmith", "123!");


        //Verifications
        //New page url is expected
        Assert.assertNotEquals(secureAreaPage.getCurrentUrl(), secureAreaPage.getPageUrl());

        //log out button is visible
//        Assert.assertTrue(secureAreaPage.isLogOutbuttonVisible(), "Your password is invalid!");

        //succesful log in message
        String expectedFailMessage = "Your password is invalid!";
        String actualSuccessMessage = secureAreaPage.getFailMessageText();
        Assert.assertTrue(actualSuccessMessage.contains(expectedFailMessage), "Actual fail message is: " + actualSuccessMessage);

    }

}
