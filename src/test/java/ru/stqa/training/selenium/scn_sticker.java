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
import static junit.framework.Assert.assertTrue;


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


        driver.get("http://localhost/litecart/");

        int countOfDucks = driver.findElements(By.xpath("//li[@class='product column shadow hover-light']")).size(); // узнаем количество продуктов на странице, категория не важна.

        for(int i=0; i<countOfDucks; i++)
        {
            int res = driver.findElements(By.xpath("//li[@class='product column shadow hover-light']")).get(i).findElements(By.xpath("./a/div/div[contains(@class, 'sticker')]")).size(); // для каждого продукта узнаем количество стикеров
            assertTrue(res==1); //проверим, что стикер один
        }

    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
