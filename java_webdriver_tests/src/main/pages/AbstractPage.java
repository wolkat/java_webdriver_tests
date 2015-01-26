package main.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class AbstractPage {
	
	public static WebDriver webDriver;
		
	public <T> T waitForPageToLoad(Class<T> clazz){
		(new WebDriverWait(webDriver, 30)).until(new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver d) {
				return (((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
			}
		});
		return PageFactory.initElements(webDriver, clazz);
	}
}
