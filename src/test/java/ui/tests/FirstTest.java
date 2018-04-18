package ui.tests;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.utils.DriverConfiguration;
import ui.utils.Parallelized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.core.Is.is;

@RunWith(Parameterized.class)
public class FirstTest {
    private WebDriver driver;
    DriverConfiguration configuration;
    private static String URL = "http://localhost/litecart/admin/";
    private static Cookie authCookie;
    private WebDriverWait wait10sec;
    @Parameterized.Parameter
    public String linkNumber;

    public static int init() {

        WebDriver authDriver = null;
        authDriver = new DriverConfiguration().getDriver();
        authDriver.get(URL);
        authDriver.findElement(By.name("username")).sendKeys("admin");
        authDriver.findElement(By.name("password")).sendKeys("admin");
        authDriver.findElement(By.name("remember_me")).click();
        authDriver.findElement(By.name("login")).click();
        authCookie = authDriver.manage().getCookieNamed("remember_me");
        int result = authDriver.findElements(By.xpath("//ul[@id='box-apps-menu']/li")).size();
        if (authDriver != null) {
            authDriver.quit();
        }
        return result;

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        List<Object[]> links = new ArrayList<>();
        int linksCount = init();
        for (int i = 1; i < linksCount + 1; i++) {
            links.add(new Object[]{String.valueOf(i)});
        }
        return links;
    }

    @Before
    public void setUp() throws Exception {
        driver = new DriverConfiguration().getDriver();
        wait10sec =  new WebDriverWait(driver,10);
        driver.navigate().to(URL);
        driver.manage().addCookie(authCookie);
        driver.get(URL);
    }

    @Test
    public void checkConfigurationTest() {
        wait10sec.until(ExpectedConditions.elementToBeClickable(By.id("app-")));
        clickOnMenuElement(driver.findElement(By.xpath("//ul[@id='box-apps-menu']/li[" + linkNumber + "]")));
        int innerLinks = driver.findElements(By.xpath("//*[starts-with(@id,'doc')]")).size();
        if (innerLinks > 0) {
            for (int i = 1; i < innerLinks + 1; i++) {
                clickOnMenuElement(driver.findElement(By.xpath(".//li[starts-with(@id,'doc')][" + i + "]")));
            }
        }
    }

    public void clickOnMenuElement(WebElement menuElement) {
        menuElement.click();
        wait10sec.until(ExpectedConditions.stalenessOf(menuElement));
        Assert.assertThat("H1 isn`t present", driver.findElements(By.cssSelector("h1")).size() > 0, is(true));
    }

    @After
    public void tearDown() throws Exception {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
