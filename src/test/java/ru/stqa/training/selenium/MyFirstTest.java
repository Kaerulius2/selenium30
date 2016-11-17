package ru.stqa.training.selenium;

import org.apache.commons.lang3.ObjectUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start()
    {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver,10);

    }

    @Test
    public void myFirstTest() {
        driver.get("http://www.yandex.ru");
        driver.findElement(By.name("text")).sendKeys("Selenium 3.0");
        driver.findElement(By.className("search2__button")).submit();

    }

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
