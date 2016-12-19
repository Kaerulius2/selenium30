package ru.stqa.training.selenium.Pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class mainPage extends Page {

    public mainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//li[@class='product column shadow hover-light']")
    List<WebElement> listOfDucks;

    public mainPage open()
    {
        driver.get("http://localhost/litecart");
        return this;
    }

    public void duckClick (int nomer)
    {
        listOfDucks.get(nomer).click();
    }

}
