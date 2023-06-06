package POM;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Excel;
import Utility.Generic;
import io.appium.java_client.android.AndroidDriver;

public class ProjectMileage {

	AndroidDriver<WebElement> Driver;
	Generic G ;
	 Excel E;
	 
	 public ProjectMileage(AndroidDriver<WebElement> Driver) {
		 this.Driver=Driver;
	 }
	 
	By Title = By.xpath("//android.widget.TextView[@index='4']");

	
//________________________ WAIT_______________________________________________________________________
	
	public void Wait(WebElement Web) {
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}
	
//_________________________________ VALIDATE TITLE ______________________________________________________________

	public void ValidateTitle() {
		try {
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		Assert.assertTrue(Ele.isDisplayed());
		Assert.assertEquals(Ele.getText(), "Mileage");
		
		}
		catch(StaleElementReferenceException E) {
			WebElement Ele = Driver.findElement(Title);
			Wait(Ele);
			Assert.assertTrue(Ele.isDisplayed());
			Assert.assertEquals(Ele.getText(), "Mileage");
		}
	}
//_________________________________________ Validate Job Info______________________________________________________________	
		
		public void ValidateProjectInfo() throws IOException {
			
			WebElement ProjectDescription = Driver.findElement(By.xpath("(//android.widget.TextView[@index='5'])[1]"));
			WebElement ProjectTaskID = Driver.findElement(By.xpath("(//android.widget.TextView[@index='11'])[1]"));
			WebElement ProjectName = Driver.findElement(By.xpath("(//android.widget.TextView[@index='2'])[1]"));
			Excel E = new Excel(Driver);
			
			System.out.println(ProjectDescription.getText() + ProjectTaskID.getText() + ProjectName.getText() );
		
			Assert.assertEquals(ProjectDescription.getText().contains(ProjectDescription.getText()),E.ReadFromExcel(2, 4).contains(ProjectDescription.getText()));
			Assert.assertEquals(ProjectTaskID.getText().contains(ProjectTaskID.getText()),E.ReadFromExcel(2, 5).contains(ProjectTaskID.getText()));
			Assert.assertEquals(ProjectName.getText().contains(ProjectName.getText()),E.ReadFromExcel(3, 3).contains(ProjectName.getText()));

			
			}	
	
	
	
//____________________________Click on Add Button_________________________________________________________________________________________________
		
		public void ClickOnAddIcon_TOaddMileage() {
					
					WebElement AddIcon = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='5'])[1]"));
					Wait(AddIcon);
					AddIcon.click();

					}	

		
//_____________________________ FILL MILEAGE FORM____________________________________________________________________________________________________________
		
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
