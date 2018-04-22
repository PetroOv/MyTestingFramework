package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CheckOutPage extends BasePage {
    public WebDriver driver;
    @FindBy(how = How.CSS, using = "em")
    WebElement message;
    @FindBy(how = How.CSS, using = "td.item")
    private List<WebElement> checkOutedProducts;
    @FindBy(how = How.CSS, using = "button[name='remove_cart_item']")
    private WebElement removeButton;
    private By tableBy = By.cssSelector("#box-checkout-summary");

    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public String getMessage() {
        return message.getText();
    }

    public CheckOutPage removeProduct() {
        removeButton.click();
        return this;
    }

    public CheckOutPage clearCheckOutList() {
        int size = checkOutedProducts.size();
        for (int i = 0; i < size; i++) {
            WebElement table = driver.findElement(tableBy);
            removeProduct();
            new WebDriverWait(driver, 10).until(stalenessOf(table));
        }
        return this;
    }
}
