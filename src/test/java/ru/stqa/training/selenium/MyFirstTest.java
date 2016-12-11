package ru.stqa.training.selenium;

import org.apache.commons.lang3.ObjectUtils;
import java.net.MalformedURLException;
import java.net.URL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    private WebDriver driver;
    private WebDriver driver2;
    private WebDriverWait wait;

    @Before
    public void start() throws MalformedURLException {
        //driver = new ChromeDriver();
        //driver = new RemoteWebDriver(new URL("http://192.168.1.146:4444/wd/hub"), DesiredCapabilities.chrome());
        driver2= new RemoteWebDriver(new URL("http://192.168.1.111:4444/wd/hub"), DesiredCapabilities.firefox());
        driver = new RemoteWebDriver(new URL("http://192.168.1.111:4444/wd/hub"), DesiredCapabilities.chrome());

        wait = new WebDriverWait(driver,10);

    }

    @Test
    public void myFirstTest() {
        driver.get("http://www.yandex.ru");
        driver2.get("http://www.yandex.ru");
        driver.findElement(By.name("text")).sendKeys("Selenium 3.0");
        driver.findElement(By.className("search2__button")).submit();
        driver2.findElement(By.name("text")).sendKeys("Selenium 3.0");
        driver2.findElement(By.className("search2__button")).submit();

    }

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
