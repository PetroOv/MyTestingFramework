package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class BaseUserPage extends BasePage {
public WebDriver driver;
    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Checkout')]")
    private WebElement checkoutLink;

    @FindBy(how = How.CSS,using = "a.content span.quantity")
            private WebElement checkOutQuantity;

    public BaseUserPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public CheckOutPage goToCheckOut() {
        checkoutLink.click();
        return new CheckOutPage(driver);
    }

    public int getCurrentCheckOutedProducts(){
        return Integer.parseInt(checkOutQuantity.getText());
    }
}
