package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;


public class scn_add_new_duck {

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
    public void scn_add_new_duck_f() {

        String nameDuck = "New_Uber_Duck";

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        driver.findElement(By.xpath("//a[contains(@href,'?app=catalog&doc=catalog')]")).click(); //заходим в "Каталог"
        driver.findElement(By.xpath("//a[contains(@href,'app=catalog&doc=edit_product')]")).click(); //заходим в добавление продукта

        driver.findElement(By.xpath("//label[contains(.,'Enabled')]/input")).click(); //Enabled!
        driver.findElement(By.name("name[en]")).sendKeys(nameDuck);
        driver.findElement(By.name("code")).sendKeys("NUB001");
        driver.findElements(By.xpath("//input[@name='categories[]']")).get(1).click(); //выберем категорию
        Select category = new Select(driver.findElement(By.xpath("//select[@name='default_category_id']"))); //создадим селект и проверим, что он отображает категорию
        assertTrue(category.getFirstSelectedOption().getText().equals("Rubber Ducks"));

        List<WebElement> sex = driver.findElements(By.xpath("//input[@name='product_groups[]']")); //установим все категории
        for(WebElement sexx : sex) {
            sexx.click();
        }

        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys(Keys.END + "2.00");


        driver.findElement(By.name("new_images[]")).sendKeys("D:\\duck.png"); //картинку приложим

        driver.findElement(By.name("date_valid_from")).sendKeys("29.11.2016");
        driver.findElement(By.name("date_valid_to")).sendKeys("29.12.2017");

        driver.findElement(By.xpath("//a[contains(@href,'#tab-information')]")).click(); //идем в информацию

        Select manufact = new Select(driver.findElement(By.name("manufacturer_id"))); //выберем производителя для разнообразия по тексту
        manufact.selectByVisibleText("ACME Corp.");

        driver.findElement(By.name("keywords")).sendKeys("Uber Duck");

        driver.findElement(By.name("short_description[en]")).sendKeys("World's best duck!");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("World's best duck! Ever! World's best duck! Ever!World's best duck! Ever!World's best duck! Ever!World's best duck! Ever!World's best duck! Ever!World's best duck! Ever!World's best duck! Ever!World's best duck! Ever!");
        driver.findElement(By.name("head_title[en]")).sendKeys("NEW Uber Duck");
        driver.findElement(By.name("meta_description[en]")).sendKeys("NEW Duck");

        driver.findElement(By.xpath("//a[contains(@href,'#tab-prices')]")).click(); //идем в цены


        driver.findElement(By.name("purchase_price")).clear(); //ставим цену
        driver.findElement(By.name("purchase_price")).sendKeys(Keys.END + "23.55");

        Select currency=new Select(driver.findElement(By.name("purchase_price_currency_code")));
        currency.selectByIndex(1);

        double usd = 51.45;  //установим значение в долларах, которое будем вводить
        double eur = 0.726*usd; // посчитаем и округлим значение евро
        eur=Math.round(eur*100.0)/100.0;

        driver.findElement(By.name("prices[USD]")).sendKeys(Keys.END + Double.toString(usd)); //введем значение в долларах
        double res = new Double(driver.findElement(By.name("prices[EUR]")).getAttribute("placeholder")); //получим рассчитанное значение евро
        assertTrue(eur==res); //сравним с исходным

        double taxUSD = new Double(driver.findElement(By.name("gross_prices[USD]")).getAttribute("value")); //проверим, что поле Price Incl. Tax содержит то же, что и поле Price для обеих валют
        double taxEUR = new Double(driver.findElement(By.name("gross_prices[EUR]")).getAttribute("placeholder"));
        assertTrue(eur==taxEUR);
        assertTrue(usd==taxUSD);

        driver.findElement(By.xpath("//button[@name='save']")).click(); //сохраним

        assertTrue(driver.findElement(By.xpath("//a[contains(@href,'?app=catalog&doc=edit_product')]")).getText().contains(nameDuck)); //проверим, что есть запись с таким именем

    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
