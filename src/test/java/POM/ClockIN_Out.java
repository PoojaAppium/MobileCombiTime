package POM;

import java.io.IOException;
import java.text.ParseException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Excel;
import Utility.Generic;
import io.appium.java_client.android.AndroidDriver;

public class ClockIN_Out {
	AndroidDriver<WebElement> Driver ;
	Generic G ;
	 Excel E;
	 
	 public ClockIN_Out(AndroidDriver<WebElement> Driver) {
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
	By Time_CICO = By.xpath("//android.widget.TextView[@index='1']");
//	By Time_CO = By.xpath("//android.widget.TextView[@index='1']");
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}

	
	public void AlertHandle() {
		//No Absence Cause Selected
		G = new Generic(Driver);
		G.HandleAlert_CI("No Absence Cause Selected");
		System.out.println("Handled Alert to fill Absence***********");
		
	}
	
	public void MakeClockIn() throws IOException {
		try {
			WebElement Time2 = Driver.findElement(Time_CICO);
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
				System.out.println("Registered Clock IN ***********");
			}
			catch(Exception E) {
				WebElement Time = Driver.findElement(Time_CICO);
				Excel E1 = new Excel(Driver);
				E1.WriteInExcel(5, 5, Time.getText());
				WebElement Ele_Submit = Driver.findElement(Submit);
				Wait(Ele_Submit);
				Ele_Submit.click();
				System.out.println("Registered Clock In***********");
			}	
			
}
	
	
	public void MakeClockIN_ClockOut() throws IOException, InterruptedException {
		Thread.sleep(3000);
		WebElement Ele1 = Driver.findElement(Title);
		Wait(Ele1);
		String title = Ele1.getText();
		System.out.println(title);
		
		if(Ele1.getText().equalsIgnoreCase("Clock-in")) {
			Assert.assertEquals(title ,"Clock-in");	
			System.out.println("Validated Clock IN Title***********");
	//------------------------------------------------------------------------------
			try {
				WebElement Time2 = Driver.findElement(Time_CICO);
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
					System.out.println("Registered Clock IN ***********");
				}
				catch(Exception E) {
					WebElement Time = Driver.findElement(Time_CICO);
					Excel E1 = new Excel(Driver);
					E1.WriteInExcel(5, 5, Time.getText());
					WebElement Ele_Submit = Driver.findElement(Submit);
					Wait(Ele_Submit);
					Ele_Submit.click();
					System.out.println("Registered Clock In***********");
				}	
			
//----------------------------------------------------------------------------------			
		}
		
		
		else if(Ele1.getText().equalsIgnoreCase("Clock-out")) {
			
		Assert.assertEquals(title ,"Clock-out");
		System.out.println("Validated Clock out Title***********");
//--------------------------------------------------------------------------------------
		try {
			WebElement Time2 = Driver.findElement(Time_CICO);
			Excel E = new Excel(Driver);
			System.out.println(Time2.getText());
			E.WriteInExcel(5, 5, Time2.getText());
			WebElement Ele_Submit = Driver.findElement(Submit);
			Wait(Ele_Submit);
			Ele_Submit.click();
			WebElement Ele_Alert = Driver.findElement(NoAbsenceCauseSelected_Alert);	
			Wait(Ele_Alert);
				G = new Generic(Driver);
				G.HandleAlert_CO("No Absence Cause Selected");
				Thread.sleep(5000);
				System.out.println("Registered Clock out***********");
			}
			catch(Exception E) {
				WebElement Time = Driver.findElement(Time_CICO);
				Excel E1 = new Excel(Driver);
				E1.WriteInExcel(5, 5, Time.getText());
				WebElement Ele_Submit = Driver.findElement(Submit);
				Wait(Ele_Submit);
				Ele_Submit.click();
				System.out.println("Registered Clock out***********");
			}
//-----------------------------------------------------------------------------------------------	
		
		System.out.println("***********************");
		
		
		}
		
}
	
	
	public void MakeClockOut() throws IOException {
		try {
			WebElement Time2 = Driver.findElement(Time_CICO);
			Excel E = new Excel(Driver);
			System.out.println(Time2.getText());
			E.WriteInExcel(5, 5, Time2.getText());
			WebElement Ele_Submit = Driver.findElement(Submit);
			Wait(Ele_Submit);
			Ele_Submit.click();
			WebElement Ele_Alert = Driver.findElement(NoAbsenceCauseSelected_Alert);	
			Wait(Ele_Alert);
				G = new Generic(Driver);
				G.HandleAlert_CO("No Absence Cause Selected");
				Thread.sleep(5000);
				System.out.println("Registered Clock out***********");
			}
			catch(Exception E) {
				WebElement Time = Driver.findElement(Time_CICO);
				Excel E1 = new Excel(Driver);
				E1.WriteInExcel(5, 5, Time.getText());
				WebElement Ele_Submit = Driver.findElement(Submit);
				Wait(Ele_Submit);
				Ele_Submit.click();
				System.out.println("Registered Clock out***********");
			}
	}
	
	public void ValidateClockIn_in_HomePage() throws IOException, ParseException {
		
		G = new Generic(Driver);
		G.ValidateClockInHomePage();	
		System.out.println("Validate Clock in In dashboard ***********");
		
	}
	
public void ValidateClockIN_ClockOut_in_HomePage() throws IOException, ParseException {
		
		G = new Generic(Driver);
		G.ValidateClockIN_ClockOutHomePage_AfterRegistration();	
		System.out.println("Validate  ClockIN Clock OUT In dashboard ***********");
	}
	





public void ValidateClockIN_ClockOut_Title() throws InterruptedException {
	Thread.sleep(3000);
	WebElement Ele = Driver.findElement(Title);
	Wait(Ele);
	String title = Ele.getText();
	System.out.println(title);
	if(Ele.getText()=="Clock-in") {
		Assert.assertEquals(title ,"Clock-in");	
		System.out.println("Validated Clock IN Title***********");
	}
	else if(Ele.getText()=="Clock-out") {
	Assert.assertEquals(title ,"Clock-out");
	System.out.println("Validated Clock out Title***********");
	}
	
	
}
	
}
