package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertTrue;


public class scn_register {

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

    public String gen_e_mail(int l) {

        String[] bukvi = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "g", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z" };
        String email;

        Random rand = new Random();

        StringBuilder login = new StringBuilder(l);

        for (int i = 0; i <l ; i++) {
            int index = rand.nextInt(bukvi.length);
            login.append(bukvi[index]);
        }

        email=login.toString()+"@xxx.xxx";
        return email;


    }


    @Before
    public void start()
    {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);

    }

    @Test
    public void scn_register_f() {

        String mail;
        driver.get("http://localhost/litecart/");


        driver.findElement(By.xpath("//td/a[contains(@href,'create_account')]")).click(); //заходим в создание аккаунта (через первую ссылку)

        driver.findElement(By.name("tax_id")).sendKeys("55-3456456");       //заполним по порядку
        driver.findElement(By.name("company")).sendKeys("Google inc.");
        driver.findElement(By.name("firstname")).sendKeys("Alex");
        driver.findElement(By.name("lastname")).sendKeys("Golubkoff");
        driver.findElement(By.name("address1")).sendKeys("Addison ave 354, Silicon valley, CA");
        driver.findElement(By.name("address1")).sendKeys("Addison ave 356, Silicon valley, CA");
        driver.findElement(By.xpath("//span[@role='combobox']")).click(); //кликаем на выбор страны - он не селект - но выводит список
        driver.findElements(By.xpath("//li[contains(.,'United States')]")).get(0).click(); //выбираем страну из списка
        driver.findElement(By.name("city")).sendKeys("New York");
        driver.findElement(By.name("postcode")).sendKeys("10012");
        Select st = new Select(driver.findElement(By.xpath("//select[@name='zone_code']"))); //а вот тут создадим настоящий селект для выбора штата
        st.selectByVisibleText("New York"); //возьмем штат

        mail=gen_e_mail(7);     //сгенерим "правильный" емейл с удобным для чистки несуществующим доменом
        driver.findElement(By.name("email")).sendKeys(mail);
        driver.findElement(By.name("phone")).sendKeys(Keys.HOME + "+12121234567"); //телефончик

        driver.findElement(By.name("newsletter")).click(); // отпишемся сразу от спама

        driver.findElement(By.name("password")).sendKeys("PaSsWoRd123"); // заполним пароли
        driver.findElement(By.name("confirmed_password")).sendKeys("PaSsWoRd123");

        driver.findElement(By.name("create_account")).click(); //готово

        driver.findElements(By.xpath("//a[contains(@href,'logout')]")).get(0).click(); //выйдем

        //и снова зайдём
        driver.findElement(By.name("email")).sendKeys(mail);
        driver.findElement(By.name("password")).sendKeys("PaSsWoRd123");
        driver.findElement(By.name("login")).click();

        assertTrue(driver.findElements(By.xpath("//a[contains(@href,'logout')]")).size()>0); // если у нас появились элементы Logout, значит логин успешный

        //и выйдем
        driver.findElements(By.xpath("//a[contains(@href,'logout')]")).get(0).click();

    }//test

    @After
    public void stop()
    {
        driver.quit();
        driver= null;
    }
}
