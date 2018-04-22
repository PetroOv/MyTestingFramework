package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BaseUserPage {
    public WebDriver driver;
    @FindBy(how = How.CSS,using = "button[name='add_cart_product']")
    private WebElement addProductToCartButton;
    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public ProductPage addProductToCart(){
        int expectedProductsOnCart = this.getCurrentCheckOutedProducts() + 1;
        addProductToCartButton.click();
        new WebDriverWait(driver,10).until(expectCheckoutPosition(expectedProductsOnCart));
        return this;
    }

    public ExpectedCondition<Boolean> expectCheckoutPosition(int expect) {
        return input -> {
            int current = this.getCurrentCheckOutedProducts();
            return expect == current;
        };
    }
}
