package POM;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Excel;
import Utility.Generic;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.android.nativekey.AndroidKey;


public class ProjectSupplement {

	AndroidDriver<WebElement> Driver;
	Generic G ;
	 Excel E;
	 
	 public ProjectSupplement(AndroidDriver<WebElement> Driver) {
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
		Assert.assertEquals(Ele.getText(), "Report supplements");
		
		}
		catch(StaleElementReferenceException E) {
			WebElement Ele = Driver.findElement(Title);
			Wait(Ele);
			Assert.assertTrue(Ele.isDisplayed());
			Assert.assertEquals(Ele.getText(), "Report supplements");
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
	
	
		
	
//____________________________Click on Add Button_______________________________________________________________
		
		public void ClickOnAddIcon_TOaddISupplement() {
					
					WebElement AddIcon = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='5'])[1]"));
					Wait(AddIcon);
					AddIcon.click();

					}	

//_________________________________Fill Supplement Form_______________________________________________________________________


		public void FillAllRequiredField(String Quantity, String Notes) throws IOException, InterruptedException {
			
			WebElement DateRefrence = Driver.findElement(By.xpath("//android.widget.TextView[@index = '1']"));
			WebElement CostTypeDropdown = Driver.findElement(By.xpath("//android.widget.EditText[@bounds = '[41,641][791,707]']"));
			WebElement NotesEnter = Driver.findElement(By.xpath("//android.widget.EditText[@bounds='[41,1067][1039,1133]']"));
			WebElement EnterResourceDropdown = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='1']//android.widget.EditText[@index='2'])[3]"));
			
			Generic G = new Generic(Driver);
			Excel E = new Excel(Driver);
			Actions A = new Actions(Driver);
			E.WriteInExcel(3, 7, DateRefrence.getText());
			
			WebDriverWait WW = new WebDriverWait(Driver,25);
			WW.until(ExpectedConditions.elementToBeClickable(CostTypeDropdown));
			CostTypeDropdown.click();
			System.out.println("TOAUCH CLICJK DONE");
	
			
			WebElement CostTypeValue1 = Driver.findElement(By.xpath("(//android.widget.ScrollView[@index='1']/android.view.ViewGroup[@index='0'])[2]"));
			Wait(CostTypeValue1);
			CostTypeValue1.click();
			WebElement CostTypeValue2_AfterSelectionvalue = Driver.findElement(By.xpath("(//android.widget.EditText[@index='2'])[1]"));
		E.WriteInExcel(4, 4, CostTypeValue2_AfterSelectionvalue.getText());
			

			FillQuantity(Quantity);
			G.Click_SendKeys(NotesEnter, Notes);
			
			
			WebElement NotesValue = Driver.findElement(By.xpath("(//android.widget.EditText[@index='2'])[2]"));
			E.WriteInExcel(3, 6, NotesValue.getText());
			
			WW.until(ExpectedConditions.elementToBeClickable(EnterResourceDropdown));
			EnterResourceDropdown.click();
			WebElement ResourceValue = Driver.findElement(By.xpath("(//android.widget.ScrollView[@index='1'])[2]//android.widget.TextView[@index='0']"));
			WW.until(ExpectedConditions.elementToBeClickable(ResourceValue));
			E.WriteInExcel(4, 5, ResourceValue.getText().trim());
			ResourceValue.click();
			
			
			WebElement SaveButton = Driver.findElement(By.xpath("//android.view.ViewGroup[@index = '11']"));
			Thread.sleep(2000);
			Wait(SaveButton);
			SaveButton.click();
			
		
		
			}	
		//____________________________ Validate Successfull registration Toast Message_________________________________________________________________________________


		public void ValidateSuccessfullToastMessgae() {
			
			WebElement SuccfullToastMessage = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Raw material added successfully']"));
			Wait(SuccfullToastMessage);
			Assert.assertEquals(SuccfullToastMessage.getText(), "Supplement added successfully");
			
			}	
		//_____________________________________________________ValidateSupplement_______________________________________________________



		public void ValidateSupplementOrderHistory(String Quantity) throws IOException {
			
			Excel E = new Excel(Driver);
			String CostType = E.ReadFromExcel(4, 4);
			String ResourceName = E.ReadFromExcel(4, 5);
			Generic G = new Generic(Driver);
			G.Scroll();
			
			List<WebElement> AllSupplementsOfSameCostType = Driver.findElements(By.xpath("//android.widget.TextView[@text = '"+CostType+"']"));
			int i = AllSupplementsOfSameCostType.size();
			
			WebElement FilledQuantity = Driver.findElement(By.xpath("(//android.widget.TextView[@text='"+CostType+"']//following-sibling::android.widget.TextView[@index='11'])["+i+"]"));
			WebElement Status = Driver.findElement(By.xpath("(//android.widget.TextView[@text='"+CostType+"']//following-sibling::android.widget.TextView[@index='5'])["+i+"]"));
			WebElement Date = Driver.findElement(By.xpath("(//android.widget.TextView[@text='"+CostType+"']//following-sibling::android.widget.TextView[@index='8'])["+i+"]"));
			WebElement Resource = Driver.findElement(By.xpath("(//android.widget.TextView[@text='"+CostType+"']//following-sibling::android.widget.TextView[@index='14'])["+i+"]"));
			WebElement Notes = Driver.findElement(By.xpath("(//android.widget.TextView[@text='"+CostType+"']//following-sibling::android.widget.TextView[@index='17'])["+i+"]"));
		    
			Assert.assertEquals(FilledQuantity.getText(), Quantity+".00");
			Assert.assertEquals(Status.getText(), "Pending");
			Assert.assertEquals(Date.getText(),E.ReadFromExcel(3, 7));
			Assert.assertEquals(Status.getText(), "Pending");
			Assert.assertEquals(Resource.getText().trim(),ResourceName);
			Assert.assertEquals(Notes.getText(), E.ReadFromExcel(3, 6));

			
			
			}	
		
	//_________________________FILL QUANTITY ONLY____________________________________________________________________________________________________
		
		public void FillQuantity(String Quantity) throws InterruptedException {

		
		WebElement EnterQuantityTextField = Driver.findElement(By.xpath("//android.widget.EditText[@index='0']"));
		EnterQuantityTextField.click();	
		for(char c : Quantity.toCharArray()) {
			  Driver.pressKey(new KeyEvent(AndroidKey.valueOf("DIGIT_" + c)));
			}
//		Driver.pressKey(new KeyEvent(AndroidKey.DIGIT_1));
//		Driver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
//		Driver.pressKey(new KeyEvent(AndroidKey.DIGIT_5));
//		Driver.hideKeyboard();
	
		}

		


	}

	
	
	
	
	
	
	
	
	
	
	
	

