package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertTrue;


public class scn_windows {

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

    public ExpectedCondition<String> thereIsWindowOtherThan(final Set<String> oldW) //на вход получим множество окон до клика
    {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver input) {
                Set<String> newW = driver.getWindowHandles();   //получим множество окон после клика
                newW.removeAll(oldW);                           //вычтем из нового множества старое
                if(newW.size()>0) {                             //если множества различны (т.е. после клика появились новые окна), вернем первое, иначе - NULL
                    return newW.iterator().next();
                }else
                {
                    return null;
                }
            }
        };

    }


    @Before
    public void start()
    {
        //driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);

    }

    @Test
    public void scn_windows_f() {


        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        driver.findElement(By.xpath("//a[contains(@href,'?app=countries&doc=countries')]")).click(); //заходим в "Страны"

        //i[@class='fa fa-external-link']  //это внешние ссылки

        driver.findElements(By.xpath("//a[contains(@href,'?app=countries&doc=edit_country&country_code') and not(@title='Edit')]")).get(0).click(); //кликнем на, допустим, первую страну

        List<WebElement> links = driver.findElements(By.xpath("//i[@class='fa fa-external-link']")); //найдем список ссылок

        for(WebElement link : links) //для каждой ссылки
        {
            String parentWin = driver.getWindowHandle();
            Set<String> oldWins = driver.getWindowHandles();
            link.click();                                           //кликаем на очередной элемент
            String newWin = wait.until(thereIsWindowOtherThan(oldWins)); // ожидаем, когда появится хендл нового окна (или окон)
            driver.switchTo().window(newWin);                              //переключимся в него
            driver.close();                                                 //закроем страницу
            driver.switchTo().window(parentWin);                         //переключимcя на родительскую страницу
        }





    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
