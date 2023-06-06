package POM;

import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utility.Excel;
import Utility.Generic;
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
		WebDriverWait WW = new WebDriverWait(Driver,50);
		WW.until(ExpectedConditions.visibilityOf(Web));
	}

	public void ValidateTitle() {
		WebElement Ele = Driver.findElement(Title);
		Wait(Ele);
		Assert.assertTrue(Ele.isDisplayed());
		 System.out.println("Validate Page Title***********");
	}
	
public void ClickOnPlusIcon() throws InterruptedException {

	  WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
			Wait(Ele);
			WebDriverWait WW = new WebDriverWait(Driver,25);
			WW.until(ExpectedConditions.elementToBeClickable(Ele));
			Ele.click();
			 System.out.println("Click On Plus ICON TO OPEN*********** ");
	
}

  public void ClickOnJobs() {
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Jobs']"));
		Wait(Ele);
		Ele.click();
		 System.out.println("Click On Job Button***********");
  }
  
  public void ClickOnCI() throws IOException, InterruptedException {
	  Thread.sleep(2000);
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
		Wait(Ele);
	  Ele.click();
	  System.out.println("Click On Clock In Button***********");
	  
  }
  
  public void ClickOnCO() throws IOException, InterruptedException {
	  Thread.sleep(2000);
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-out']"));
		Wait(Ele);
		Ele.click();
		System.out.println("Click On Clock Out Button***********");

  }
  
  
  public void ClickOnCICO() throws IOException, InterruptedException {
	  Thread.sleep(2000);
	  try {
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
		Wait(Ele);
	  Ele.click();
	  System.out.println("Click On Clock In Button***********");
	  }
	  
	  catch(Exception E) {
		  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-out']"));
			Wait(Ele);
			Ele.click();
			System.out.println("Click On Clock Out Button***********");
	  }
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
  
  public void ValidateCOinPlusIcon_AfterCI() {
	  WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
		Wait(Ele);
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();
		  WebElement CO = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-out']"));
			Wait(Ele);
		Assert.assertEquals("Clock-out", CO.getText());	
		 System.out.println("Validate CO After CI***********");
		
  }
  
  public void ValidateCIinPlusIcon_AfterCO() {
	  WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
		Wait(Ele);
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();
		  WebElement CI = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
			Wait(Ele);
		Assert.assertEquals("Clock-in", CI.getText());
		  System.out.println("Validate CI After CO***********");
		
  }
  
  public void ClickOnCI_CO() throws IOException, InterruptedException {
	  try{
	  Thread.sleep(2000);
	  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
		Wait(Ele);
	  Ele.click();
	  System.out.println("Click on Clock In Button***********");
	  }
	  catch(Exception E) {
		  Thread.sleep(2000);
		  WebElement Ele = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-out']"));
			Wait(Ele);
		  Ele.click();  
		  System.out.println("Click on Clock out Button***********");
	  }

  }
  
  
  public void ValidateCICOinPlusIcon_AfterCOCI() {
	  WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
		Wait(Ele);
		WebDriverWait WW = new WebDriverWait(Driver,25);
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();
		try {
		  WebElement CI = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
			Wait(Ele);
		Assert.assertEquals("Clock-in", CI.getText());
		  System.out.println("Validate CI After CO***********");
		}
		catch(Exception E) {
			  WebElement CO = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-out']"));
				Wait(Ele);
			Assert.assertEquals("Clock-out", CO.getText());	
			 System.out.println("Validate CO After CI***********");
		}
		
  } 
  
  //_________________________________ Horrizontaly Scroll of Project job ________________________________________________________________
  
  
  public void RightSwipeJob() throws IOException, InterruptedException {
	  
	  Excel E = new Excel(Driver);
	 String ProjectJobName = E.ReadFromExcel(3, 3);
	  
	  
	  WebElement projectJobName = Driver.findElement(By.xpath("//android.widget.TextView[@text = '"+ProjectJobName+"']"));
	  Generic G = new Generic(Driver);
	  G.HorizontallyScroll(projectJobName);
	  Thread.sleep(2000);
	  
	  
	  
  }
  
 //_______________________________ Validate More button ______________________________________________________________________________________
  
  public void ValidateMoreButton_InSidePanel() throws IOException {

	  WebElement MoreButton = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'More']"));
	  Wait(MoreButton);
	 Assert.assertEquals(MoreButton.getText(), "More");
	  
	  
	  
  }

  
  //______________________________________ Click on More Button_____________________________________________________________________________
  
  
public void ClickOnMoreButton() {
	

	  WebElement MoreButton = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'More']"));
	  Wait(MoreButton);
	  MoreButton.click();
	  
	
}

//___________________________________ Validate More option side panel tab_______________________________________________________________________________

public void ValidateMoreOptionHeader() {
	

	  WebElement MoreOption = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'More Options']"));
	  Wait(MoreOption);
	  Assert.assertEquals(MoreOption.getText(), "More Options");
	  
	
}

//___________________________________ Validate Item Consumption side panel tab_______________________________________________________________________________

public void ValidateItemConsumption() {
	

	  WebElement IC = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Item consumed']"));
	  Wait(IC);
	  Assert.assertEquals(IC.getText(), "Item consumed");
	  
	
} 


//___________________________________ Validate Item Consumption side panel tab_______________________________________________________________________________

public void ValidateSupplementInMenuOption() {
	

	  WebElement S = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Supplements']"));
	  Wait(S);
	  Assert.assertEquals(S.getText(), "Supplements");
	  
	
} 

//___________________________________ Validate Item Consumption side panel tab_______________________________________________________________________________

public void ValidateMileageInMenuOption() {
	

	  WebElement M = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Mileage']"));
	  Wait(M);
	  Assert.assertEquals(M.getText(), "Mileage");
	  
	
} 

//______________________________________ Click on Item consumption____________________________________________________________________________

public void ClickOnItemConsumption() {
	

	  WebElement ItemConsumtion = Driver.findElement(By.xpath("//android.widget.TextView[@text='More Options']/ancestor::android.view.ViewGroup[@index= '1']//android.widget.TextView[@text='Item consumed']"));
	  Wait(ItemConsumtion);
	  ItemConsumtion.click();
	  
	
}

//______________________________________ Click on Supplement IN SIDE MENU TAB____________________________________________________________________________

public void ClickOnSupplement() {
	

	  WebElement Supplement = Driver.findElement(By.xpath("//android.widget.TextView[@text='More Options']/ancestor::android.view.ViewGroup[@index= '1']//android.widget.TextView[@text='Supplements']"));
	  Wait(Supplement);
	  Supplement.click();
	  
	
}

//______________________________________ Click on Mileage IN SIDE MENU TAB____________________________________________________________________________

public void ClickOnMileage() {
	

	  WebElement Mileage = Driver.findElement(By.xpath("//android.widget.TextView[@text='More Options']/ancestor::android.view.ViewGroup[@index= '1']//android.widget.TextView[@text='Mileage']"));
	  Wait(Mileage);
	  Mileage.click();
	  
	
}


































}





