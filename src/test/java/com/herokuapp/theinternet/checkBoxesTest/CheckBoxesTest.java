package com.herokuapp.theinternet.checkBoxesTest;

import com.herokuapp.theinternet.baseTest.testUtilities.TestUtilities;
import com.herokuapp.theinternet.pages.CheckboxesPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckBoxesTest extends TestUtilities {

    @Test
    public void selectingTwoCheckBoxesTest(){
        log.info("Starting selecting two checkboxes test");

        //open main age
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        //TODO Click on Checkboxes link
        CheckboxesPage checkboxesPage = welcomePageObject.clickCheckBoxesLink();

        //TODO check all checkboxes
        checkboxesPage.selectAllCheckboxes();

        //TODO verify if all checkboxes are selected
        boolean areAllCheckBoxesSelected = checkboxesPage.areAllCheckBoxesSelected();
        Assert.assertTrue(areAllCheckBoxesSelected);
    }

}
