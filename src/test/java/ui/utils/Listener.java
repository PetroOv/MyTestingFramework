package ui.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.File;
import java.io.IOException;
public class Listener extends AbstractWebDriverEventListener {

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println("Start search for: " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        System.out.println(by + " has been found");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        System.out.println("Click on: " + element);
    }
    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        System.out.println("Clicked on: " + element);
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        System.out.println("Shit Happened: "+throwable.getMessage());
        File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tempFile, new File(System.currentTimeMillis()+ "screen.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}