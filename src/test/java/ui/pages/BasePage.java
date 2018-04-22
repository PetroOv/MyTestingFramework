package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
    public WebDriver driver;
    @FindBy(how = How.CSS,using = "img[title='My Store']")
    private WebElement logoImg;
    BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public HomePage goToHomePage(){
        logoImg.click();

        return new HomePage(driver);
    }
}
