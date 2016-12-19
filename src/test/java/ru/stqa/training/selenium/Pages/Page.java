package ru.stqa.training.selenium.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Page {

    protected WebDriver driver;
    public WebDriverWait wait;

    public Page(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

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

}