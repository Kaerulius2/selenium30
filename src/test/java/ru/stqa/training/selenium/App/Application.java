package ru.stqa.training.selenium.App;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.training.selenium.Pages.mainPage;
import ru.stqa.training.selenium.Pages.cartPage;
import ru.stqa.training.selenium.Pages.duckPage;

import java.util.Set;

public class Application {


    private WebDriver driver;

    private mainPage mainPAGE;
    private cartPage cartPAGE;
    private duckPage duckPAGE;

    public Application() {
        driver = new ChromeDriver();

        mainPAGE = new mainPage(driver);
        cartPAGE = new cartPage(driver);
        duckPAGE = new duckPage(driver);

    }

    public void selectDuck(int i)
    {
        mainPAGE.open();
        mainPAGE.duckClick(i);
    }

    public void addDuckToCart()
    {
        if(duckPAGE.selectSizeIsPresent())
        {
            duckPAGE.makeSelect();
        }

        int q = duckPAGE.getQuantity();
        duckPAGE.clickAddButton();
        duckPAGE.waitQuantity(q);
        mainPAGE.open();
    }

    public void removeAllFromCart()
    {
        cartPAGE.open();

        while(cartPAGE.getCountDucks()>0)
        {
            cartPAGE.removeDuck();
        }
    }

    public void quit() {
        driver.quit();
    }


    }



