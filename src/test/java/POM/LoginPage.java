package POM;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Generic;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {

	 AndroidDriver<WebElement> Driver ;
	 Generic G;
	 
	 public LoginPage(AndroidDriver<WebElement> Driver) {
		 this.Driver=Driver;
	 }
	 
	By Title = By.xpath("//android.widget.TextView[@text='by Zenegy']");
	By TerminalID = By.xpath("(//android.widget.EditText[@index='3'])[1]");
	By Password = By.xpath("(//android.widget.EditText[@index='4'])[1]");
	By LoginButton = By.xpath("(//android.view.ViewGroup[@index='5'])[1]");
	By WrongCredErrorMessage = By.xpath("//android.widget.TextView[@bounds='[309,1154][772,1207]']");
	By TerminalNotFoundError = By.xpath("//android.widget.TextView[@bounds='[338,1154][743,1207]']");
	By PleaseEnterTerminalAndPassword = By.xpath("//android.widget.TextView[@bounds='[176,1131][904,1230]']");
	By ForgotPasswordButton = By.xpath("//android.widget.TextView[@index='4']");
	
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}
	
	
	public void ValidateTitle() {
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		Assert.assertTrue(Ele.isDisplayed());
	}
	
	public void FillTerminalID(String Terminalid) {
		G = new Generic(Driver);
		G.SendKeys(TerminalID, Terminalid);
	}
	
	public void FillPassword(String PasswordValue) {
		G = new Generic(Driver);
		G.SendKeys(Password, PasswordValue);
		
	}
	
	public void Submit() {
		WebElement submit = Driver.findElement(LoginButton);
		Wait(submit);
		submit.click();
	}
	
	public void ValidateError_WrongCredential(String Text) throws InterruptedException {
		Thread.sleep(3000);
		WebElement ErrorMessage = Driver.findElement(WrongCredErrorMessage);
		Wait(ErrorMessage);
		String ErrorText = ErrorMessage.getText();
		System.out.println(ErrorText);
		Assert.assertEquals(ErrorText , Text);
	}
	
	public void ValidateError_TerminalNotFound(String Text) throws InterruptedException {
		Thread.sleep(3000);
		WebElement ErrorMessage = Driver.findElement(TerminalNotFoundError);
		Wait(ErrorMessage);
		String ErrorText = ErrorMessage.getText();
		System.out.println(ErrorText);
		Assert.assertEquals(ErrorText , Text);
	}
	
	
	public void ValidateError_EmptyTerminalAndPassword(String Text) throws InterruptedException {
		Thread.sleep(3000);
		WebElement ErrorMessage = Driver.findElement(PleaseEnterTerminalAndPassword);
		Wait(ErrorMessage);
		String ErrorText = ErrorMessage.getText();
		System.out.println(ErrorText);
		Assert.assertEquals(ErrorText , Text);
	}
	
	public void ForgotPasswordButton(String Text) throws InterruptedException {
		Thread.sleep(3000);
		WebElement button = Driver.findElement(ForgotPasswordButton);
		Wait(button);
		button.click();
		
	}
	
}
