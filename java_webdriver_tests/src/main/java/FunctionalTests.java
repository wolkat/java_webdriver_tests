package main.java;


import java.util.concurrent.TimeUnit;

import main.pages.AbstractPage;
import main.pages.HomePage;
import main.pages.LoginPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.PageFactory;


public class FunctionalTests {

    private WebDriver driver;
    private static final String BASE_URL = "http://www.allegro.pl";
    private static final String USER_LOGIN = "tgt.tests@gmail.com";
    private static final String USER_PASSWORD = "TGT_Allegro_2014";
    private static final String SEARCH_TEXT = "laptop";
    private static final By SEARCH_FIELD_ELEMENT = By.id("main-search-text");
    private static final By SEARCH_BUTTON_ELEMENT = By.xpath("//input[@class='sprite search-btn']");
    private static final By SEARCH_RESULTS_LIST = By.id("featured-offers");
    private static final String ITEM_FROM_SEARCH_RESULTS = "//*[@id='featured-offers']/article[%s]//h2//span";
    private static final By ITEM_ELEMENT = By.id("sma-content");
    private static final By ADD_TO_BUCKET_BUTTON = By.xpath("//*[@class='sma-res sma-sty-1 smAddToBasket']");
    private static final By BUCKET_MODULE = By.id("cartModule");
    private static final By ITEM_TITLE_FROM_BUCKET = By.xpath("//a[@class='title']");
    public static final int _1 = 1;
    private HomePage homePage;

    @BeforeMethod
    public void setUp() {
        this.driver = new FirefoxDriver();
        AbstractPage.webDriver = driver;
        driver.get(BASE_URL);
        this.homePage = PageFactory.initElements(driver, HomePage.class);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {
        isOnHomePage();
        LoginPage loginPage = homePage.clickLogin();
        homePage = loginPage.waitForPageToLoad(LoginPage.class)
        		.setUserLogin(USER_LOGIN)
        		.setUserPassword(USER_PASSWORD)
        		.clickLoginButton();
        isOnHomePage();
        isUserLoggedIn(USER_LOGIN);
        homePage.clickLogOut()
        		.waitForPageToLoad(HomePage.class);
        isOnHomePage();
        isLoggingLinkDisplayed();
    }

    @Test
    public void secondTest() throws InterruptedException {
        homePage.isOnHomePage();
        driver.findElement(SEARCH_FIELD_ELEMENT).sendKeys(SEARCH_TEXT);
        driver.findElement(SEARCH_BUTTON_ELEMENT).click();
        Assert.assertTrue(driver.findElement(SEARCH_RESULTS_LIST).isDisplayed());
        String itemTitle = driver.findElement(By.xpath(String.format(ITEM_FROM_SEARCH_RESULTS, _1))).getText();
        Assert.assertTrue(itemTitle.toLowerCase().contains(SEARCH_TEXT));
        driver.findElement(By.xpath(String.format(ITEM_FROM_SEARCH_RESULTS, _1))).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(ADD_TO_BUCKET_BUTTON).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Assert.assertTrue(driver.findElement(BUCKET_MODULE).isDisplayed());
        Assert.assertEquals(driver.findElement(BUCKET_MODULE).findElement(ITEM_TITLE_FROM_BUCKET).getText(), itemTitle);
    }
    
    private void isOnHomePage(){
    	Assert.assertTrue(homePage.isOnHomePage());
    }
    
    private void isUserLoggedIn(String userName){
    	Assert.assertTrue(homePage.isUserLoggedIn(userName));
    }

    private void isLoggingLinkDisplayed(){
    	Assert.assertTrue(homePage.isLoggingLinkDisplayed());
    }
}
