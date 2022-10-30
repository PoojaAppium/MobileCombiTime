package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.android.AndroidDriver;

public class ForgotPassword {

	AndroidDriver<WebElement> Driver ;
	 
	 public ForgotPassword(AndroidDriver<WebElement> Driver) {
		 this.Driver=Driver;
	 }
	 
	By Title = By.xpath("(//android.widget.TextView[@index='0'])[1]");
	By TerminalID = By.xpath("//android.widget.EditText[@index='2']");
	By SubmitTerminalIDButton = By.xpath("//android.view.ViewGroup[@index='3']");
	By RememberPasswordTryLogin = By.xpath("//android.widget.TextView[@index='4']");
	
	
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}
	
	public void ValidateTitle() {
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		String ExpectedResult = Ele.getText();
		Assert.assertEquals(ExpectedResult, "Forgot password ?");
	}
	
	public void FillTerminalID(String Terminalid) {
		WebElement ID = Driver.findElement(TerminalID);
		Wait(ID);
		ID.sendKeys(Terminalid);		
	}
	
	public void ForgotYourPassword_SubmitButton(String PasswordValue) {
		WebElement Button = Driver.findElement(SubmitTerminalIDButton);
		Wait(Button);
		Button.click();
	}
	
	
}
