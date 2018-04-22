package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseUserPage {
    public WebDriver driver;
    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public ProductPage clickOnProductByName(String name){
        driver.findElement(By.cssSelector("#box-latest-products a[title='" + name + "']")).click();
    return new ProductPage(driver);
    }
    public ProductPage clickOnMostPopularProduct(){
        driver.findElement(By.cssSelector("#box-most-popular a.link")).click();
        return new ProductPage(driver);
    }
}
