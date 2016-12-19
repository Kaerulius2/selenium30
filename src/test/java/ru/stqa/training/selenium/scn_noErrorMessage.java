package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.logging.LogEntry;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;


public class scn_noErrorMessage {

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
    public void scn_noErrorMessage_f() {

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        driver.findElement(By.xpath("//a[contains(@href,'?app=catalog&doc=catalog')]")).click(); //кликнем каталог
        driver.findElement(By.xpath("//a[contains(@href,'?app=catalog&doc=catalog&category_id=1')]")).click(); //кликнем уток

        int countDucks = driver.findElements(By.xpath("//tr[@class='row']//a[contains(@href,'edit_product')]//i[@class='fa fa-pencil']")).size(); //количество товаров

        for (int i=0; i<countDucks;i++) //для каждого товара
        {
            driver.findElements(By.xpath("//tr[@class='row']//a[contains(@href,'edit_product')]//i[@class='fa fa-pencil']")).get(i).click(); //клик по товару

            for (LogEntry le : driver.manage().logs().get("browser").getAll()) {  //получим логи браузера и для каждого
                String mes=le.getMessage();                                       //возьмем сообщение
                if(!mes.equals(""))                                               //если оно не пустое
                {
                    Assert.fail("Ошибка: "+mes);                                  //зафейлим тест
                }

            }

            driver.navigate().back(); //возврат к списку уток
        }





    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
