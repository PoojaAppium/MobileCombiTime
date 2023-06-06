package POM;

import java.io.IOException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Excel;
import Utility.Generic;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class ItemConsumption {

	
	AndroidDriver<WebElement> Driver ;
	Generic G ;
	 Excel E;
	 
	 public ItemConsumption(AndroidDriver<WebElement> Driver) {
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
		Assert.assertEquals(Ele.getText(), "Draw items");
		
		}
		catch(StaleElementReferenceException E) {
			WebElement Ele = Driver.findElement(Title);
			Wait(Ele);
			Assert.assertTrue(Ele.isDisplayed());
			Assert.assertEquals(Ele.getText(), "Draw items");
		}
	}
//_________________________________________ Validate Job Info______________________________________________________________	
		
		public void ValidateProjectInfo() throws IOException {
			
			WebElement ProjectDescription = Driver.findElement(By.xpath("(//android.widget.TextView[@index='5'])[1]"));
			WebElement ProjectTaskID = Driver.findElement(By.xpath("(//android.widget.TextView[@index='8'])[1]"));
			WebElement ProjectName = Driver.findElement(By.xpath("(//android.widget.TextView[@index='2'])[1]"));
			Excel E = new Excel(Driver);
			
			System.out.println(ProjectDescription.getText() + ProjectTaskID.getText() + ProjectName.getText() );
		
			Assert.assertEquals(ProjectDescription.getText().contains(ProjectDescription.getText()),E.ReadFromExcel(2, 4).contains(ProjectDescription.getText()));
			Assert.assertEquals(ProjectTaskID.getText().contains(ProjectTaskID.getText()),E.ReadFromExcel(2, 5).contains(ProjectTaskID.getText()));
			Assert.assertEquals(ProjectName.getText().contains(ProjectName.getText()),E.ReadFromExcel(3, 3).contains(ProjectName.getText()));

			
			}	
//_________________________________________Validate Previous Value of Item History_________________________________________________________________________________
		
public void ValidatePreviousOrderHistoryOfSameItem() throws IOException {
			
	ClickOnAddIcon_TOaddItem();
	WebElement ItemNumberDropdown = Driver.findElement(By.xpath("//android.view.ViewGroup[@bounds = '[971,353][1025,408]']"));
	WebDriverWait WW = new WebDriverWait(Driver,25);
	WW.until(ExpectedConditions.elementToBeClickable(ItemNumberDropdown));
	ItemNumberDropdown.click();
	WebElement ItemNumberDropdownValue1 = Driver.findElement(By.xpath("//android.widget.ScrollView[@index='1']/android.view.ViewGroup[@index='0']"));
	Wait(ItemNumberDropdownValue1);
	ItemNumberDropdownValue1.click();
	WebElement FilledItemNumber = Driver.findElement(By.xpath("(//android.widget.EditText[@index = '2'])[1]"));
	Excel E = new Excel(Driver);
	E.WriteInExcel(2, 7, FilledItemNumber.getText());
//	WebElement BackToPreviousPage = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[135,144][198,208]'])[1]"));
//	Wait(BackToPreviousPage);
	Driver.navigate().back();
	String ItemName = E.ReadFromExcel(2, 7);
	
	
			WebElement DrawnHere = Driver.findElement(By.xpath("//android.widget.TextView[@text='ITEM "+ItemName+"']//following-sibling::android.widget.TextView[@index='6']"));
			WebElement DrawnTotal = Driver.findElement(By.xpath("//android.widget.TextView[@text='ITEM "+ItemName+"']//following-sibling::android.widget.TextView[@index='9']"));
			E.WriteInExcel(2, 8, DrawnHere.getText());
			E.WriteInExcel(2, 9, DrawnTotal.getText());

			}	
		
		
		
		
		
//____________________________Click on Add Button_______________________________________________________________
		
public void ClickOnAddIcon_TOaddItem() throws IOException {
			
			WebElement AddIcon = Driver.findElement(By.xpath("//android.view.ViewGroup[@index='5']"));
			Wait(AddIcon);
			AddIcon.click();

			}	

//_________________________________Fill Item consumtion Form_______________________________________________________________________


public void FillAllRequiredField(String Quantity, String location, String BatchNumber, String SerialNumber) throws IOException, InterruptedException {
	
	WebElement ItemNumberDropdown = Driver.findElement(By.xpath("//android.view.ViewGroup[@bounds = '[971,353][1025,408]']"));
	WebElement EnterQuantityTextField = Driver.findElement(By.xpath("//android.widget.EditText[@bounds = '[55,579][1025,645]']"));
	WebElement locationField = Driver.findElement(By.xpath("//android.widget.EditText[@bounds = '[55,784][915,850]']"));
	WebElement BatchNumberTextFiled = Driver.findElement(By.xpath("//android.widget.EditText[@bounds = '[55,989][915,1055]']"));
	WebElement SerialNumberTextFeild = Driver.findElement(By.xpath("//android.widget.EditText[@bounds = '[55,1194][915,1260]']"));
	WebElement SaveButton = Driver.findElement(By.xpath("//android.view.ViewGroup[@index='5']"));

	Generic G = new Generic(Driver);
	
	WebDriverWait WW = new WebDriverWait(Driver,50);
	WW.until(ExpectedConditions.elementToBeClickable(ItemNumberDropdown));
	ItemNumberDropdown.click();
	System.out.println("TOAUCH CLICJK DONE");
	
	
	WebElement ItemNumberDropdownValue1 = Driver.findElement(By.xpath("//android.widget.ScrollView[@index='1']/android.view.ViewGroup[@index='0']"));
	Wait(ItemNumberDropdownValue1);
	ItemNumberDropdownValue1.click();
	Thread.sleep(2000);
	G.Click_SendKeys(EnterQuantityTextField, Quantity);
	G.Click_SendKeys(locationField, location);
	
	try {
		G.Click_SendKeys(BatchNumberTextFiled, BatchNumber);
	}
	catch(Exception E1) {
		System.out.println("No Batch Number Feild");
	}
	
	try {
		G.Click_SendKeys(SerialNumberTextFeild, SerialNumber);
		}
		catch(Exception E1) {
			System.out.println("No Serial Number Feild");
		}
	
	Wait(SaveButton);
	SaveButton.click();
	
	}	
//____________________________ Validate Successfull registration Toast Message_________________________________________________________________________________


public void ValidateSuccessfullToastMessgae() {
	
	WebElement SuccfullToastMessage = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Raw material added successfully']"));
	Wait(SuccfullToastMessage);
	Assert.assertEquals(SuccfullToastMessage.getText(), "Raw material added successfully");
	
	}	






//_____________________________________________________ValidateItemConsumptionInHistory_______________________________________________________



public void ValidateItemConsumptionInHistory(String Quantity) throws IOException {
	
	Excel E = new Excel(Driver);
    String DrawnHere = 	E.ReadFromExcel(2, 8);
	String DrawnTotal = E.ReadFromExcel(2, 9);
	
	String ItemName = E.ReadFromExcel(2, 7);
	WebElement DrawnHereWB = Driver.findElement(By.xpath("//android.widget.TextView[@text='ITEM "+ItemName+"']//following-sibling::android.widget.TextView[@index='6']"));
	WebElement DrawnTotalWB = Driver.findElement(By.xpath("//android.widget.TextView[@text='ITEM "+ItemName+"']//following-sibling::android.widget.TextView[@index='9']"));
    
	 float TotalDrawnHere =  Float.parseFloat(DrawnHere)+Float.parseFloat(Quantity);
	String Text = DrawnHereWB.getText();
     float DrawnHerrFinalVlue = (float) Float.parseFloat(Text);
    
    float TotalDrawnTotal = Float.parseFloat(DrawnTotal)+Float.parseFloat(Quantity);
	String Text1 = DrawnTotalWB.getText();
    float DrawnTotalFinalVlue = Float.parseFloat(Text1);
    
	Assert.assertEquals(TotalDrawnHere , DrawnHerrFinalVlue , 0.00 );
	Assert.assertEquals(TotalDrawnTotal , DrawnTotalFinalVlue, 0.00 );
	
	
	
	
	
	
	
	}	

















		
		
	}
	
	
	
	
	
	
	

