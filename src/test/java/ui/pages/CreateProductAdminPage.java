package ui.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.io.File;

public class CreateProductAdminPage extends BaseAdminPage {
    public WebDriver driver;
    @FindBy(how = How.CSS,using = "input[name=\"status\"]:not(checked)")
    private WebElement enableRB;
    @FindBy(how = How.CSS,using = "input[name='name[en]']")
    private WebElement productName;
    @FindBy(how = How.CSS,using = "input[type='file']")
    private WebElement productImage;
    @FindBy(how = How.CSS,using = "input[name='date_valid_from']")
    private WebElement dateFrom;
    @FindBy(how = How.CSS,using = "button[name='save']")
    private WebElement saveProductButton;

    public CreateProductAdminPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }
    public CreateProductAdminPage enableProduct(){
        enableRB.click();
        return this;
    }
    public CreateProductAdminPage enterProductName(String name){
        productName.sendKeys(name);
        return this;
    }
    public CreateProductAdminPage setProductImage(String imageName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(imageName).getFile());
        productImage.sendKeys(file.getAbsolutePath());
        return this;
    }
    public CreateProductAdminPage setDateFrom(String day, String month, String year){
        dateFrom.sendKeys(day + month + Keys.TAB + year);
        return this;
    }

    public AdminPage saveProduct(){
        saveProductButton.click();
        return new AdminPage(driver);
    }
}
