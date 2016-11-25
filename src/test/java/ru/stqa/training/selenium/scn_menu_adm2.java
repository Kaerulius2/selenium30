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


public class scn_menu_adm2 {

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
    public void scn_menu_adm_f2() {

        //String xp1,xp2,tit;

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        int numberOfstrings = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']")).size(); // получим количество элементов меню верхнего уровня

        for (int i = 0; i <numberOfstrings; i++) {
            driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[@id='app-']")).get(i).click(); // кликаем по очередному элементу
            assertTrue(isElementPresent(By.tagName("h1"))); //проверим наличие заголовка страницы
            assertFalse(driver.getTitle().equals("")); //проверим наличие непустого тайтла

            if (driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul/li")).size()!=0) //если есть подменю
            {
                int numberOfSubstrings = driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul/li")).size(); //получим количество пунктов подменю
                for (int j = 0; j < numberOfSubstrings; j++) {
                    driver.findElements(By.xpath("//ul[@id='box-apps-menu']/li[@class='selected']/ul/li")).get(j).click(); //берем очередной пункт
                    assertTrue(isElementPresent(By.tagName("h1"))); //проверим наличие заголовка страницы
                    assertFalse(driver.getTitle().equals("")); //проверим наличие непустого тайтла
                }
            }

        }


    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
