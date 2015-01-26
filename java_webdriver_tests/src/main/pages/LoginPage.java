package main.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage{
	
	@FindBy(how = How.XPATH, using = "//*[@class='container authentication-container']")
	private WebElement AUTH_CONTAINER_ELEMENT;
	
	@FindBy(how = How.ID, using = "userForm_login")
	private WebElement INPUT_LOGIN;
	
	@FindBy(how = How.ID, using = "userForm_password")
	private WebElement INPUT_PASSWORD;
	
	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-primary']")
	private WebElement LOGIN_BUTTON;
	
	public LoginPage setUserLogin(String login){
		INPUT_LOGIN.sendKeys(login);
		return this;
	}
	
	public LoginPage setUserPassword(String password){
		INPUT_PASSWORD.sendKeys(password);
		return this;
	}
	
	public HomePage clickLoginButton(){
		LOGIN_BUTTON.click();
		return PageFactory.initElements(webDriver, HomePage.class);
	}
}
