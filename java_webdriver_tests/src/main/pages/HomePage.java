package main.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AbstractPage{
	
	private static final String VISIBLE_FORM = "//*[@class='main-wrapper responsive-content slide-out-navigation']";
	
	@FindBy(how = How.CLASS_NAME, using = "main-box")
	private WebElement MAIN_BOX;
	
	@FindBy(how = How.XPATH, using = VISIBLE_FORM + "//*[@class='login']//span")
	private WebElement LOGIN_LINK;
	
	@FindBy(how = How.XPATH, using = VISIBLE_FORM + "//*[@class='logout']//span")
	private WebElement LOGOUT_LINK;
	
	@FindBy(how = How.XPATH, using = VISIBLE_FORM + "//*[@id='user-login']//span")
	private WebElement USER_LOGIN_ELEMENT;
	
	
	public LoginPage clickLogin(){
		LOGIN_LINK.click();
		return PageFactory.initElements(webDriver, LoginPage.class);
	}
	
	public HomePage clickLogOut(){
		LOGOUT_LINK.click();
		return this;
	}
	
	public boolean isUserLoggedIn(String userName){
		return USER_LOGIN_ELEMENT.getText().equals(userName);
	}
	
	public boolean isLoggingLinkDisplayed(){
		return LOGIN_LINK.isDisplayed();
	}
	
	public boolean isOnHomePage() {
        return MAIN_BOX.isDisplayed();
    }
}
