package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static java.lang.Thread.*;
import static junit.framework.Assert.assertTrue;


public class scn_cart_add_delete {

    private WebDriver driver;
    private WebDriverWait wait;

    public int waitSec=5;

    public boolean isElementPresent(WebDriver driver, By locator)
    {
        try
        {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            return driver.findElements(locator).size()>0;
        }finally {
            driver.manage().timeouts().implicitlyWait(waitSec, TimeUnit.SECONDS);
        }

    }

    @Before
    public void start()
    {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(waitSec, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,waitSec);

    }

    @Test
    public void scn_cart_add_delete_f() {

        driver.get("http://localhost/litecart/");

        for(int i=0;i<3;i++) //добавим подряд три продукта
        {
            driver.findElements(By.xpath("//li[@class='product column shadow hover-light']")).get(i).click();

            if(isElementPresent(driver,By.name("options[Size]"))) //если есть опция размера, нужно её выбрать
            {
                Select size=new Select(driver.findElement(By.name("options[Size]")));
                size.selectByIndex(1);
            }
            driver.findElement(By.name("add_cart_product")).click();
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@class='quantity']"),Integer.toString(i+1))); //Ждем пока количество товаров в корзине не увеличится на единицу

            driver.navigate().back(); //возвращаемся на предыдущую страницу

        }//for добавлние товаров

        driver.findElement(By.xpath("//a[@class='link' and contains(@href,'checkout')]")).click(); // зайдем в корзину


        while(isElementPresent(driver,By.name("remove_cart_item"))) //пока есть кнопка "Удалить"
        {
            int num = driver.findElements(By.xpath("//td[@class='item']")).size(); //получим количество товара в таблице
            driver.findElement(By.name("remove_cart_item")).click(); //

            if(isElementPresent(driver, By.xpath("//td[@class='item']"))) { //если есть хоть одна строчка есть
                wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath("//td[@class='item']"), num - 1)); //ждем, когда таблица уменьшится на 1 товар
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
