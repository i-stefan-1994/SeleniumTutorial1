package com.herokuapp.theinternet.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckboxesPage extends BasePageObject{

    private By checkbox = By.xpath("//input[@type='checkbox']");

    public CheckboxesPage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void selectAllCheckboxes(){
        log.info("Selecting all checkboxes");
        List<WebElement> checkboxes = findAll(checkbox);
        for(WebElement checkbox : checkboxes){
            if(!checkbox.isSelected()){
                checkbox.click();
            }
        }
    }

    public boolean areAllCheckBoxesSelected(){
        log.info("Are all checkboxes selected?");
        List<WebElement> checkboxes = findAll(checkbox);
        for (WebElement checkbox: checkboxes){
            if(!checkbox.isSelected()){
                return false;
            }
        }

        return true;
    }

}
