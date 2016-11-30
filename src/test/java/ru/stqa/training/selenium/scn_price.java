package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;


public class scn_price {

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
    public void scn_price_f() {

        driver.get("http://localhost/litecart/"); //откроем главную страницу
        //запомним наименование на главной странице товара
        String name_main = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li/a/div[@class='name']")).get(0).getText();

        //сохраним обычную цену
        String rPrice = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//s[@class='regular-price']")).getText();

        //сохраним свойства цены
        String col_rPrice= driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//s[@class='regular-price']")).getCssValue("color");
        String dec_rPrice = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//s[@class='regular-price']")).getCssValue("text-decoration");
        String w_rPrice = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//s[@class='regular-price']")).getCssValue("font-weight");
        //проверим свойства
        assertEquals(col_rPrice,"rgba(119, 119, 119, 1)"); //что серая
        assertEquals(dec_rPrice,"line-through"); //что зачеркнута
        assertEquals(w_rPrice,"normal"); //что нежирная

        //сохраним акционную цену
        String cPrice = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//strong[@class='campaign-price']")).getText();
        //сохраним свойства этой цены
        String col_cPrice= driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color");
        String dec_cPrice = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("text-decoration");
        String w_cPrice = driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("font-weight");

        // проверим свойства акционной цены
        assertEquals(col_cPrice,"rgba(204, 0, 0, 1)"); //что красная
        assertEquals(dec_cPrice,"none");    //что не зачеркнута
        assertEquals(w_cPrice,"bold");  //что полужирная

        driver.findElements(By.xpath("//div[@id='box-campaigns']/div/ul/li")).get(0).click(); //переход на страницу товара

        //проверим, что названия совпадают на обеих страницах
        assertEquals(name_main, driver.findElement(By.xpath("//h1")).getText());

        //проверим, что совпадают обе цены
        assertEquals(rPrice,driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//s[@class='regular-price']")).getText());
        assertEquals(cPrice,driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//strong[@class='campaign-price']")).getText());

        //проверим, что свойства цен также соответствуют
        //сначала простую цену
        assertEquals("rgba(102, 102, 102, 1)", driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//s[@class='regular-price']")).getCssValue("color")); //что серая
        assertEquals("line-through", driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//s[@class='regular-price']")).getCssValue("text-decoration")); //что зачеркнута
        assertEquals("normal", driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//s[@class='regular-price']")).getCssValue("font-weight")); //что нежирная
        //потом акционную
        assertEquals ("rgba(204, 0, 0, 1)", driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("color")); //что красная
        assertEquals("none", driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("text-decoration")); //что не зачеркнута
        assertEquals("bold", driver.findElement(By.xpath("//div[@class='information']")).findElement(By.xpath("//strong[@class='campaign-price']")).getCssValue("font-weight")); //что полужирная

    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
