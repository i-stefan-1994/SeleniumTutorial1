package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownPage extends BasePageObject{


    private By dropdown = By.xpath("//select[@id='dropdown']");

    public DropDownPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    /**
     * Method selects given option from the dropdown menu
     * @param optionNumber
     */
    public void selectOption(int optionNumber){
        log.info("Selecting option " + optionNumber + " from dropdown");
        WebElement dropDownElement = find(dropdown);
        Select dropdown = new Select(dropDownElement);

        /*
        3 different methods to select

        #1
        Select by index
         */

        //Select by value
        dropdown.selectByValue("" + optionNumber);
        /*
        #3 Select by visible text
         */
    }

    /**
     * Method returns selected option in dropdown as a String
     * @return
     */
    public String getSelectedOption(){
        WebElement dropdownElement = find(dropdown);
        Select dropdown = new Select(dropdownElement);
        String selectedOption = dropdown.getFirstSelectedOption().getText();
        log.info(selectedOption + " is selected in the dropdown");
        return selectedOption;
    }
}
