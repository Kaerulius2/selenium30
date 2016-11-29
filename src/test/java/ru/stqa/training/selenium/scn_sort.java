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
import java.util.*;
import java.lang.String;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;




public class scn_sort {

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
    public void scn_sort_f() {

        int i=0;
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        driver.findElement(By.xpath("//a[contains(@href,\"?app=countries&doc=countries\")]")).click(); //заходим в "Страны"

        //проверим сортировку стран

        List<WebElement> countries = driver.findElements(By.xpath("//a[contains(@href,'?app=countries&doc=edit_country&country_code') and not(@title='Edit')]")); //находим все страны

        String[] unSort = new String[countries.size()]; //объявим два массива, по количеству стран
        String[] Sort = new String[countries.size()];

        for (WebElement country : countries) {
            unSort[i]=country.getText(); //заполним массивы странами.
            Sort[i]=country.getText();
            i++;
            }

        Arrays.sort(Sort); //отсортируем один из массивов

        assertTrue(Arrays.equals(Sort,unSort)); // проверим, что и сортированный и не сортированный массивы идентичны

    //теперь проверим таймзоны
        List<WebElement> countZones = driver.findElements(By.xpath("//a[contains(@href,'?app=countries&doc=edit_country&country_code') and not(@title='Edit')]/../following-sibling::td[1]")); //находим все количества зон
        int count_z=countZones.size();
        i=0;

        for (i=0;i<count_z;i++) { //пробежимся по элементам
            if(!driver.findElements(By.xpath("//a[contains(@href,'?app=countries&doc=edit_country&country_code') and not(@title='Edit')]/../following-sibling::td[1]")).get(i).getText().equals("0"))  //если количество зон не 0
                {
                    driver.findElements(By.xpath("//a[contains(@href,'?app=countries&doc=edit_country&country_code') and not(@title='Edit')]")).get(i).click(); // кликаем на страну
                    List<WebElement> zones = driver.findElements(By.xpath("//input[contains(@name,'zones') and contains(@name,'name')]")); //находим зоны

                    String[] unSort_Z = new String[zones.size()];  //аналогично делаем два массива, один сортируем, проверяем
                    String[] Sort_Z = new String[zones.size()];
                    int j=0;
                    for (WebElement zone : zones) {
                        unSort_Z[j]=zone.getAttribute("value");
                        Sort_Z[j]=zone.getAttribute("value");
                        j++;
                    }

                    Arrays.sort(Sort_Z);
                    assertTrue(Arrays.equals(Sort_Z,unSort_Z));
                    driver.navigate().back();
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
