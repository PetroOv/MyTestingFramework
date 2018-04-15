package ui.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ui.utils.DriverConfiguration;

public class FirstTest {
    WebDriver driver;

    @Before
    public void setUp() throws Exception {
        driver = DriverConfiguration.getDriver();
    }

    @Test
    public void checkConfigurationTest() {
        driver.get("http://google.com");
        System.out.println(driver.getTitle());
    }

    @After
    public void tearDown() throws Exception {
        if(driver!=null){
            driver.quit();
            driver = null;
        }
    }
}
