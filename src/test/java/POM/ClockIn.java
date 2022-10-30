package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class ClockIn {
	AndroidDriver<WebElement> Driver ;
	 
	 public ClockIn(AndroidDriver<WebElement> Driver) {
		 this.Driver=Driver;
	 }
	 
	By Title = By.xpath("//android.widget.TextView[@index='4']");
	By ADD_ICON = By.xpath("(//android.view.ViewGroup[@bounds='[868,2048][998,2167]'])[4]");
	By CI_button = By.xpath("(//android.widget.ImageView[@index='0'])[4]");
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}

	public void ValidateTitle() {
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		Assert.assertTrue(Ele.isDisplayed());
	}
}
