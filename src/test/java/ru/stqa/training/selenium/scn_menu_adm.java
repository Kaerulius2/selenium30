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

import java.lang.reflect.Array;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.Test;
import static junit.framework.Assert.assertEquals;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;


public class scn_menu_adm {

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
    public void scn_menu_adm_f() {

        String xp1,xp2,tit;

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        //Collection<WebElement> items = driver.findElements(By.xpath(".//*[@class='name']")); // список всех пунктов меню //li[@id='app-']/ul/li[contains (@id, 'doc-')] в каждом пункте меню находим список и кликаем по нему.
        //int size=items.size();


        ArrayList<String> items = new ArrayList<String>(); //создаем массив структуры меню
        items.add("Appearence");
        items.add("Template");
        items.add("Logotype");
        items.add("Catalog");
        items.add("Product Groups");
        items.add("Option Groups");
        items.add("Manufacturers");
        items.add("Suppliers");
        items.add("Delivery Statuses");
        items.add("Sold Out Statuses");
        items.add("Quantity Units");
        items.add("CSV Import/Export");
        items.add("Countries");
        items.add("Currencies");
        items.add("Customers");
        items.add("CSV Import/Export");
        items.add("Newsletter");
        items.add("Geo Zones");
        items.add("Languages");
        items.add("Storage Encoding");
        items.add("Modules");
        items.add("Background Jobs");
        items.add("Customer");
        items.add("Shipping");
        items.add("Payment");
        items.add("Order Total");
        items.add("Order Success");
        items.add("Order Action");
        items.add("Orders");
        items.add("Orders");
        items.add("Order Statuses");
        items.add("Pages");
        items.add("Reports");
        items.add("Monthly Sales");
        items.add("Most Sold Products");
        items.add("Most Shopping Customers");
        items.add("Settings");
        items.add("Store Info");
        items.add("Defaults");
        items.add("General");
        items.add("Listings");
        items.add("Images");
        items.add("Checkout");
        items.add("Advanced");
        items.add("Security");
        items.add("Slides");
        items.add("Tax");
        items.add("Tax Classes");
        items.add("Tax Rates");
        items.add("Translations");
        items.add("Search Translations");
        items.add("Scan Files");
        items.add("CSV Import/Export");
        items.add("Users");
        items.add("vQmods");

        for (String item : items) { //проходим по всем элементам массива
            xp1="//a[contains(.,'" + item + "')]";
            if(item.equals("Customer"))                         //костыль для подпункта Customer. Выполняется один раз.
            {
                driver.findElement(By.xpath("//*[@id='doc-customer']")).click();
                assertFalse(driver.getTitle().equals(""));
                continue;
            }
            driver.findElement(By.xpath(xp1)).click(); // нажмем на элемент меню
            assertFalse(driver.getTitle().equals("")); //проверим наличие тайтла
        }


    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
