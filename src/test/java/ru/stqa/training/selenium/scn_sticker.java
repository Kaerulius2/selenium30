package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;


public class scn_sticker {

    private WebDriver driver;
    private WebDriverWait wait;

    public boolean isElementPresent(final By locator)
    {
        try
        {
            driver.findElement(locator);
            return true;
        }catch (NoSuchElementException ex){
            return false;
        }
    }

    @Before
    public void start()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);

    }

    @Test
    public void scn_sticker_f() {

        String xp1,xp2,tit;

        driver.get("http://localhost/litecart/");

        //Collection<WebElement> items = driver.findElements(By.xpath(".//*[@class='name']")); // список всех пунктов меню //li[@id='app-']/ul/li[contains (@id, 'doc-')] в каждом пункте меню находим список и кликаем по нему.
        //int size=items.size();
        ////*[contains(@class, 'sticker')] все стикеры
        //li[@class='product column shadow hover-light'] все продукты
        //li[@class='product column shadow hover-light']//*[contains(@class, 'sticker')] типа все стикеры



    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
