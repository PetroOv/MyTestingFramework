package ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends BaseAdminPage {
    @FindBy(how = How.XPATH,using = "//span[contains(text(), 'Catalog')]")
    private WebElement catalog;
    @FindBy(how= How.XPATH,using = "//a[contains(text(), ' Add New Product')]")
    private WebElement addNewProductButton;
    public WebDriver driver;

    public AdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public AdminPage goToCatalog(){
        catalog.click();
        return this;
    }

    public CreateProductAdminPage clickOnAddProduct(){
        addNewProductButton.click();
        return new CreateProductAdminPage(driver);
    }
}
