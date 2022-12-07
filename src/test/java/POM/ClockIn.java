package POM;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Excel;
import Utility.Generic;
import io.appium.java_client.android.AndroidDriver;

public class ClockIn {
	AndroidDriver<WebElement> Driver ;
	Generic G ;
	 Excel E;
	 
	 public ClockIn(AndroidDriver<WebElement> Driver) {
		 this.Driver=Driver;
	 }
	 
	By Title = By.xpath("//android.widget.TextView[@index='4']");
	By Submit = By.xpath("//android.view.ViewGroup[@index='5']");
	By Time = By.xpath("//android.widget.TextView[@index='1']");
	By AbsenceCauseSection = By.xpath("//android.view.ViewGroup[@index='4']");
	By TimeSelectionCodes = By.xpath("//android.view.ViewGroup[@index='7']");
	By NoAbsenceCauseSelected_Alert = By.xpath("(//android.widget.FrameLayout[@index='0'])[2]");
	By NoAbsenceCauseSelected_AlertTitle = By.id("android:id/alertTitle");
	By NoAbsenceCauseSelected_AlertSubmit = By.id("android:id/button1");
	By AbseceCause_SickNess = By.xpath("//android.widget.Switch[@index='1']");
	By Time_CI = By.xpath("//android.widget.TextView[@index='1']");
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}

	public void Validate_CI_Title() throws InterruptedException {
		Thread.sleep(3000);
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		String title = Ele.getText();
		System.out.println(title);
		Assert.assertEquals(title ,"Clock-in");
	}
	
	public void AlertHandle() {
		//No Absence Cause Selected
		G = new Generic(Driver);
		G.HandleAlert_CI("No Absence Cause Selected");
		
	}
	
	public void MakeClockIn() throws IOException {
			try {
			WebElement Time2 = Driver.findElement(Time_CI);
			Excel E = new Excel(Driver);
			System.out.println(Time2.getText());
			E.WriteInExcel(5, 5, Time2.getText());
			WebElement Ele_Submit = Driver.findElement(Submit);
			Wait(Ele_Submit);
			Ele_Submit.click();
			WebElement Ele_Alert = Driver.findElement(NoAbsenceCauseSelected_Alert);	
			Wait(Ele_Alert);
	
				G = new Generic(Driver);
				G.HandleAlert_CI("No Absence Cause Selected");
				WebElement Ele = Driver.findElement(AbsenceCauseSection);
				Wait(Ele);
				Ele.click();
				WebElement flag1 = Driver.findElement(AbseceCause_SickNess);
				Wait(flag1);
				flag1.click();
				WebElement Submitbutton = Driver.findElement(Submit);
				Wait(Submitbutton);
				Submitbutton.click();
			}
			catch(Exception E) {
				WebElement Time = Driver.findElement(Time_CI);
				Excel E1 = new Excel(Driver);
				E1.WriteInExcel(5, 5, Time.getText());
				WebElement Ele_Submit = Driver.findElement(Submit);
				Wait(Ele_Submit);
				Ele_Submit.click();
			}
}
	
	
	
	public void ValidateClockIn_in_HomePage() throws IOException {
		
		G = new Generic(Driver);
		G.ValidateClockInHomePage();
		
		
		
	}
	
	
}
