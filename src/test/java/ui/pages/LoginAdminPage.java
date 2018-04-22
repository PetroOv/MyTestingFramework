package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginAdminPage extends BaseAdminPage {
    WebDriver driver;
    @FindBy(how = How.NAME, using = "username")
    private WebElement username;
    @FindBy(how = How.NAME, using = "password")
    private WebElement password;
    @FindBy(how = How.NAME, using = "remember_me")
    private WebElement rememberMe;
    @FindBy(how = How.NAME, using = "login")
    private WebElement logIn;

    public LoginAdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public LoginAdminPage enterUserName(String username){
        this.username.sendKeys(username);
        return this;
    }
    public LoginAdminPage enterPassword(String password){
        this.password.sendKeys(password);
        return this;
    }

    public AdminPage logInSystem(){
        logIn.click();
        return new AdminPage(driver);
    }
}
