package com.herokuapp.theinternet.dropDownTest;

import com.herokuapp.theinternet.baseTest.testUtilities.TestUtilities;
import com.herokuapp.theinternet.pages.DropDownPage;
import com.herokuapp.theinternet.pages.WelcomePageObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DropDownTest extends TestUtilities {

    @Test
    public void optionTwoTest(){
        WelcomePageObject welcomePageObject = new WelcomePageObject(driver, log);
        welcomePageObject.openPage();

        //TODO click dropdown link
        DropDownPage dropDownPage = welcomePageObject.clickDropDownLink();

        //TODO select option 2
        dropDownPage.selectOption(2);

        //TODO verify option 2 is selected
        String selectedOption = dropDownPage.getSelectedOption();
        Assert.assertTrue(selectedOption.equals("Option 2"),
                "Option 2 is not selected. Actual selected: " + selectedOption);
    }
}
