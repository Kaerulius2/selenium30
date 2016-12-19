package ru.stqa.training.selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class cartPage extends Page {

    public cartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public cartPage open()
    {
        driver.get("http://localhost/litecart/en/checkout");
        return this;
    }

    @FindBy (name="remove_cart_item")
    WebElement button_Remove;

    @FindBy(xpath = "//td[@class='item']")
    List<WebElement> itemsInTable;

    public void removeDuck()
    {
        int num=getCountDucks();
        button_Remove.click();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"), num - 1));
    }



    public int getCountDucks()
    {
        return itemsInTable.size();
    }

}
