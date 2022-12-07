package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class Dashboard {
 AndroidDriver<WebElement> Driver ;
	 
	 public Dashboard(AndroidDriver<WebElement> Driver) {
		 this.Driver=Driver;
	 }
	 
	By Title = By.xpath("(//android.view.ViewGroup[@index='4'])[1]");
	By ADD_ICON = By.xpath("(//android.view.ViewGroup[@bounds='[868,2048][998,2167]'])[4]");
	By CI_button = By.xpath("(//android.widget.ImageView[@index='0'])[4]");
	By Jobs = By.xpath("(//android.widget.ImageView[@index='0'])[4]");
	//By Jobs = By.xpath("(//android.widget.ImageView[@index='0'])[4]");
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}

	public void ValidateTitle() {
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		Assert.assertTrue(Ele.isDisplayed());
	}
	
public void ClickOnPlusIcon() {
	  WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
			Wait(Ele);
			WebDriverWait WW = new WebDriverWait(Driver,25);
			WW.until(ExpectedConditions.elementToBeClickable(Ele));
			Ele.click();
}

  public void ClickOnJobs() {
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Jobs']"));
		Wait(Ele);
		Ele.click();
  }
  
  public void ClickOnCI() {
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
		Wait(Ele);
		Ele.click();
  }
  
  public void ClickOnBreak() {
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Break']"));
		Wait(Ele);
		Ele.click();
  }
  
  public void ClickOnEnterMileage() {
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Enter mileage']"));
		Wait(Ele);
		Ele.click();
  }

}





