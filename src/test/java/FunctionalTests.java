package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FunctionalTests {

    private WebDriver driver;
    private static final String BASE_URL = "http://www.allegro.pl";
    private static final String VISIBLE_FORM = "//*[@class='main-wrapper responsive-content slide-out-navigation']";
    private static final By MAIN_BOX = By.className("main-box");
    private static final By LOGIN_LINK = By.xpath(VISIBLE_FORM + "//*[@class='login']//span");
    private static final By LOGOUT_LINK = By.xpath(VISIBLE_FORM + "//*[@class='logout']//span");
    private static final By AUTH_CONTAINER_ELEMENT = By.xpath("//*[@class='container authentication-container']");
    private static final By INPUT_LOGIN = By.id("userForm_login");
    private static final By INPUT_PASSWORD = By.id("userForm_password");
    private static final By LOGIN_BUTTON = By.xpath("//button[@class='btn btn-primary']");
    private static final By USER_LOGIN_ELEMENT = By.xpath(VISIBLE_FORM + "//*[@id='user-login']//span");
    private static final String USER_LOGIN = "tgt.tests@gmail.com";
    private static final String USER_PASSWORD = "TGT_Allegro_2014";
    private static final String SEARCH_TEXT = "laptop";
    private static final By SEARCH_FIELD_ELEMENT = By.id("main-search-text");
    private static final By SEARCH_BUTTON_ELEMENT = By.xpath("//input[@class='sprite search-btn']");
    private static final By SEARCH_RESULTS_LIST = By.id("featured-offers");
    private static final String ITEM_FROM_SEARCH_RESULTS = "//*[@id='featured-offers']/article[%s]//h2//span";
    private static final By ITEM_ELEMENT = By.id("pagecontent1");
    private static final By ADD_TO_BUCKET_BUTTON = By.id("add-to-cart-btn");
    private static final By BUCKET_MODULE = By.id("cartModule");
    private static final By ITEM_TITLE_FROM_BUCKET = By.xpath("//a[@class='title']");
    public static final int _1 = 1;

    @BeforeMethod
    public void setUp() {
        this.driver = new FirefoxDriver();
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        isOnHomePage();
        driver.findElement(LOGIN_LINK).click();
        Assert.assertTrue(driver.findElement(AUTH_CONTAINER_ELEMENT).isDisplayed());
        driver.findElement(INPUT_LOGIN).sendKeys(USER_LOGIN);
        driver.findElement(INPUT_PASSWORD).sendKeys(USER_PASSWORD);
        driver.findElement(LOGIN_BUTTON).click();
        isOnHomePage();
        Assert.assertEquals(driver.findElement(USER_LOGIN_ELEMENT).getText(), USER_LOGIN);
        driver.findElement(LOGOUT_LINK).click();
        isOnHomePage();
        Assert.assertTrue(driver.findElement(LOGIN_LINK).isDisplayed());
    }

    @Test
    public void secondTest() {
        isOnHomePage();
        driver.findElement(SEARCH_FIELD_ELEMENT).sendKeys(SEARCH_TEXT);
        driver.findElement(SEARCH_BUTTON_ELEMENT).click();
        Assert.assertTrue(driver.findElement(SEARCH_RESULTS_LIST).isDisplayed());
        String itemTitle = driver.findElement(By.xpath(String.format(ITEM_FROM_SEARCH_RESULTS, _1))).getText();
        Assert.assertTrue(itemTitle.toLowerCase().contains(SEARCH_TEXT));
        driver.findElement(By.xpath(String.format(ITEM_FROM_SEARCH_RESULTS, _1))).click();
        Assert.assertTrue(driver.findElement(ITEM_ELEMENT).isDisplayed());
        driver.findElement(ADD_TO_BUCKET_BUTTON).click();
        Assert.assertTrue(driver.findElement(BUCKET_MODULE).isDisplayed());
        Assert.assertEquals(driver.findElement(BUCKET_MODULE).findElement(ITEM_TITLE_FROM_BUCKET).getText(), itemTitle);
    }

    private void isOnHomePage() {
        Assert.assertTrue(driver.findElement(MAIN_BOX).isDisplayed());
    }
}
