package ru.stqa.training.selenium;

import org.apache.commons.io.ByteOrderMark;
import org.apache.commons.lang3.ObjectUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class scn_login {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);

    }

    @Test
    public void myFirstTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin"); //поиск по имени поля ввода логина, ввод логина
        driver.findElement(By.name("password")).sendKeys("admin"); //поиск по имени поля ввода пароля, ввод пароля
        driver.findElement(By.name("login")).click(); //ищем кнопку "Логин", жмём её

        driver.findElement(By.xpath(".//*[@id='app-']/a/span[2]")).click(); // откроем пункт меню Appearence
        driver.findElement(By.xpath(".//*[@id='doc-logotype']/a/span")).click(); // откроем подменю Logotype
        driver.findElement(By.name("cancel")).click(); //закроем лого
        driver.findElement(By.name("cancel")).click(); //вернемся на главную страницу админки
        driver.findElement(By.xpath(".//*[@id='widget-stats']/div/table/tbody/tr[1]/th")).getText().equals("Statistics"); //убедимся, что присутствует блок стастистики.
    }

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
