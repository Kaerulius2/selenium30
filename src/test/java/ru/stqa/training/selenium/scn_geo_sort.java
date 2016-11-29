package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;


public class scn_geo_sort {

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
    public void scn_geo_sort_f() {


        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        driver.findElement(By.xpath("//a[contains(@href,'?app=geo_zones&doc=geo_zones')]")).click(); //заходим в "Геозоны"

        //проверим сортировку стран

        List<WebElement> countries = driver.findElements(By.xpath("//a[contains(@href,'geo_zone_id') and not(@title='Edit')]")); //определяем геозоны

        int size=countries.size();

        for (int i=0; i<size; i++) {
            driver.findElements(By.xpath("//a[contains(@href,'geo_zone_id') and not(@title='Edit')]")).get(i).click(); //кликаем на зону

            List<WebElement> zones = driver.findElements(By.xpath("//select[contains(@name,'zone_code')]/option[contains(@selected,'selected')]"));

            String[] unSort_Z = new String[zones.size()];  //делаем два массива
            String[] Sort_Z = new String[zones.size()];
            int j=0;

                for(WebElement zone : zones)
                {
                    unSort_Z[j]=zone.getAttribute("innerText"); //заполняем оба иннер-текстом
                    Sort_Z[j]=zone.getAttribute("innerText");
                    j++;
                }

            Arrays.sort(Sort_Z); //один сортируем
            assertTrue(Arrays.equals(Sort_Z,unSort_Z)); // проверяем, что одинаковые
            driver.navigate().back(); //назад для проверки новой зоны

        }//for

    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
