package POM;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;

import Utility.Excel;
import Utility.Generic;
import io.appium.java_client.android.AndroidDriver;

public class Jobs {

	
 AndroidDriver<WebElement> Driver ;
 Generic G;
 Excel E;
	 
	 public Jobs(AndroidDriver<WebElement> Driver) {
		 this.Driver=Driver;
	 }
	 
	By Title = By.xpath("(//android.widget.TextView[@index = '4'])[1]");
	By InternalFilter = By.xpath("//android.widget.TextView[@text='Internal']");
	By Filter = By.xpath("//android.view.ViewGroup[@bounds='[833,2067][970,2167]']");
	By ProductionFilter = By.xpath("//android.widget.TextView[@text='Production']");
	By ProjectFilter = By.xpath("//android.widget.TextView[@text='Project']");
	By CancelFilter = By.xpath("//android.widget.TextView[@text='Cancel']");
	By FirstJob = By.xpath("(//android.widget.ScrollView//android.view.ViewGroup[1]//android.widget.Switch)[1]");
	By Selected = By.xpath("(//android.view.ViewGroup[@bounds='[382,466][698,659]'])[2]");
	By RunningSection = By.xpath("//android.view.View[@content-desc=\"Running\"]/android.view.ViewGroup");
	By PlannedSection = By.xpath("//android.view.View[@content-desc=\"Planned\"]");
	By SubmitIcon = By.xpath("//android.view.ViewGroup[@bounds='[968,144][1031,208]']");
	By ClockIN_OK_button = By.id("android:id/button1");
	By ClockIN_Alert = By.id("android:id/alertTitle");
	By BackButton = By.xpath("(//android.view.ViewGroup[@bounds='[135,144][198,208]'])[1]");
	By TimeRegistration = By.xpath("(//android.view.ViewGroup//android.widget.TextView[@index='0'])[1]");
	By JobRegisteredSuccessfull_TOASTMessage = By.xpath("//android.widget.TextView[@text='Added successfully']");
	By JobRegisteredUpdatedSuccessfull_TOASTMessage = By.xpath("//android.widget.TextView[@text='Updated successfully']");
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}

	public void ValidateTitle() {
		try {
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		Assert.assertTrue(Ele.isDisplayed());
		
		}
		catch(StaleElementReferenceException E) {
			WebElement Ele = Driver.findElement(Title);
			Wait(Ele);
			Assert.assertTrue(Ele.isDisplayed());
		}
	}
	
	public void ClickOnFilter() {
		WebElement Ele = Driver.findElement(Filter);
		Wait(Ele);
		Ele.click();
	}
	
	public void DeselectInterJobFilter() {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WebElement Ele = Driver.findElement(InternalFilter);
		Wait(Ele);
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();	
	}
	
	public void ClickOnProductionFilter() {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WebElement Ele = Driver.findElement(ProductionFilter);
		Wait(Ele);
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();
	}
	
	public void ClickOnProjectFilter() {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WebElement Ele = Driver.findElement(ProjectFilter);
		Wait(Ele);
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();
	}
	
	
	
	
	public void ClickOnCancelFilter() {
		WebElement Ele = Driver.findElement(CancelFilter);
		Wait(Ele);
		Ele.click();
	}

 public void ActivateProductionJob(int i) {
	 G = new Generic(Driver);
	 G.SelectProductionJobByIndex(i);
	 
 }
 
 public void ClickOnSelectedSection() {
	 WebElement Ele = Driver.findElement(Selected);
		Wait(Ele);
		Ele.click();
 }

 public void ClickOnSelectedButton() {
	 WebElement Ele = Driver.findElement(Selected);
		Wait(Ele);
		Ele.click();
 }
 
 public void ValidateJobInPlanned(int i) {
	G= new Generic(Driver);
	G.ValidateProductionJobInPlanned(i);
 }
 
 
 public void ValidateJobInSelected(int i) throws FileNotFoundException {
	G= new Generic(Driver);
	try {
	 G.ValidateJobInSelected(i);
	}
	catch(StaleElementReferenceException E) {
		G.ValidateJobInSelected(i);
	}
 }
 
 public void ClickOnSubmitButton() {
	 WebElement Ele = Driver.findElement(SubmitIcon);
		Wait(Ele);
		Ele.click();
 }
 
 public void ValidateToastMessage_SuccessfullAdded(String text) {
	 WebDriverWait WW = new WebDriverWait(Driver , 40);
		WW.until(ExpectedConditions.presenceOfElementLocated((JobRegisteredSuccessfull_TOASTMessage)));
		 WebElement toastMessage = Driver.findElement((JobRegisteredSuccessfull_TOASTMessage));
			 Assert.assertEquals(toastMessage.getText(), text);
			System.out.println( "TOAST MESSAGE ----" + toastMessage.getText());
 }
 
 public void ValidateToastMessage_UpdatedSuccessfullUpdated(String text) {
	 WebDriverWait WW = new WebDriverWait(Driver , 40);
		WW.until(ExpectedConditions.presenceOfElementLocated((JobRegisteredUpdatedSuccessfull_TOASTMessage)));
		 WebElement toastMessage = Driver.findElement((JobRegisteredUpdatedSuccessfull_TOASTMessage));
			 Assert.assertEquals(toastMessage.getText(), text);
			System.out.println( "TOAST MESSAGE ----" + toastMessage.getText());
 }
 
 public void ValidateRunningJob(int i) {
		G= new Generic(Driver);
		G.ValidateJobInRunning(i);
}
 
 public void ValidateJobinTimeRegistration(int i) throws InterruptedException, IOException {
		G= new Generic(Driver);
		G.ValidateJobInTIMEREGISTRATION(i);
}
 
 public void NavigateToBack() {
	 WebElement Ele = Driver.findElement(BackButton);
		Wait(Ele);
		Ele.click();
}
 
 
 public void ClockInDuringJobRegistration() {
	 WebElement Ele = Driver.findElement(ClockIN_Alert);
		Wait(Ele);
		String Message = Ele.getText();
		Assert.assertEquals(Message, "No Clock-in found");
			WebElement button = Driver.findElement(ClockIN_OK_button);
			WebDriverWait WW = new WebDriverWait(Driver,25);
			WW.until(ExpectedConditions.elementToBeClickable(button));
			button.click();
	
		
}
 
 public void ClickOnPlannedSection() {
	 WebElement Ele = Driver.findElement(PlannedSection);
		Wait(Ele);
		Ele.click();
}
 
 public void ClickOnRunningSection() {
	 WebElement Ele = Driver.findElement(RunningSection);
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();
}
 
 
 
}
