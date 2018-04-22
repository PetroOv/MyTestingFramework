package ui.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ui.pages.*;
import ui.utils.DriverConfiguration;

import static org.hamcrest.CoreMatchers.is;

public class ThirdHomework {
    private WebDriver driver;
    private static String URL = "http://localhost/litecart/";
    private static String ADMIN_URL = "http://localhost/litecart/admin/";
    LoginAdminPage page;
    private static final String PRODUCT_NAME = "Test product";
    private static final String PASSWORD = "admin";
    private static final String USERNAME = "admin";
    private static final String DAY = "18";
    private static final String MONTH = "4";
    private static final String YEAR = "2018";
    private static final String IMAGE = "image.jpg";

    @Before
    public void setUp() throws Exception {
        driver = new DriverConfiguration().getDriver();
    }

    @Test
    public void shoppingTest() throws InterruptedException {
        driver.get(ADMIN_URL);
        page = new LoginAdminPage(driver);
        AdminPage adminPage = page.enterUserName(USERNAME).enterPassword(PASSWORD).logInSystem();
        CreateProductAdminPage createProductPage = adminPage.goToCatalog().clickOnAddProduct();
        createProductPage.enterProductName(PRODUCT_NAME).enableProduct().setDateFrom(DAY,MONTH,YEAR).setProductImage(IMAGE).saveProduct();
        driver.get(URL);
        HomePage homePage = new HomePage(driver);
        ProductPage productPage = homePage.clickOnProductByName(PRODUCT_NAME);
        homePage = productPage.addProductToCart().goToHomePage();
        productPage = homePage.clickOnMostPopularProduct();
        CheckOutPage checkOutPage = productPage.addProductToCart().goToCheckOut();
        checkOutPage.clearCheckOutList();
        Assert.assertThat("Empty list message isn`t displayed",checkOutPage.getMessage(), is("There are no items in your cart."));
    }

    @After
    public void tearDown() throws Exception {
        if(driver!=null) driver.quit();
    }

}
