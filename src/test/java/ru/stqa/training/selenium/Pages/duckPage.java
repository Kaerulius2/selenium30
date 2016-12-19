package ru.stqa.training.selenium.Pages;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;




import java.util.List;

public class duckPage extends Page {

    public duckPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy (name = "add_cart_product")
    WebElement buttonAdd;

    @FindBy (name = "options[Size]")
    List<WebElement> selectSize;

    @FindBy (xpath = "//span[@class='quantity']")
    WebElement quantity;

    public void clickAddButton()
    {
        buttonAdd.click();
    }

    public int getQuantity()
    {
        String quan = quantity.getText();
        int i = Integer.parseInt(quan);
        return i;
    }

    public void waitQuantity(int i)
    {
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@class='quantity']"),Integer.toString(i+1)));
    }



    public boolean selectSizeIsPresent()
    {
        return selectSize.size()>0;
    }

    public void makeSelect()
    {
        Select size=new Select(driver.findElement(By.name("options[Size]")));
        size.selectByIndex(1);
    }

}
