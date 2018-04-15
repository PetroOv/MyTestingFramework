package ui.utils;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverConfiguration {
    public static WebDriver getDriver() {
        WebDriver driver;
        String browserName = System.getProperty("browser").toLowerCase();
        switch (browserName) {
            case "chrome":
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
            case "firefox":
                FirefoxDriverManager.getInstance().setup();
                driver = new FirefoxDriver();
                break;
            case "edge":
                EdgeDriverManager.getInstance().setup();
                driver = new EdgeDriver();
                break;
            default:
                ChromeDriverManager.getInstance().setup();
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }
}
