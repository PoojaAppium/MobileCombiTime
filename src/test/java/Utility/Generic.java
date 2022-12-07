package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;


public class Generic {

	AndroidDriver<WebElement> Driver ;
	
	public Generic(AndroidDriver<WebElement> Driver) {
		this.Driver =  Driver;
	}
	
	
	public void SendKeys(By uN, String text) {
		WebElement Web = Driver.findElement((By) uN);
		Web.clear();
		Web.sendKeys(text);
	}
		
public void Wait(WebElement Ele) {
	WebDriverWait WW = new WebDriverWait(Driver , 25);
	WW.until(ExpectedConditions.visibilityOf(Ele));
}

public void ValidateToastMessage(Locator Loc , String Text) {
	WebDriverWait WW = new WebDriverWait(Driver , 40);
	WW.until(ExpectedConditions.presenceOfElementLocated((By) Loc));
	 WebElement toastMessage = Driver.findElement((By) Loc);
		 Assert.assertEquals(toastMessage.getText(), Text);
		System.out.println( "TOAST MESSAGE ----" + toastMessage.getText());
		
}

//____________________________________Scroll In Mobile__________________________________________________________________________________

public void ScrollDown(String Text) {
//	MobileElement element = (MobileElement) Driver.findElement(MobileBy.AndroidUIAutomator(
//	        "new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()" +
//	         ".scrollIntoView(new UiSelector().text("+Text+"))"));

	MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).ScrollInToView("+"new UiSelector().description("+Text+")");

}

//_________________________________________________________________________________________________________________________________________

public void ValidatePageTitle(String Text) {
	WebDriverWait WW = new WebDriverWait(Driver , 40);
		WebElement Title = Driver.findElement(By.xpath("(//android.widget.TextView[@index='4'])[1]"));
		WW.until(ExpectedConditions.visibilityOf(Title));
		 Assert.assertEquals(Title.getText(), Text);
}

public void ValidateTimeRegistration() {
	WebDriverWait WW = new WebDriverWait(Driver , 40);
		WebElement Title = Driver.findElement(By.xpath("//android.widget.TextView[@text='Time Registrations']"));
		WW.until(ExpectedConditions.visibilityOf(Title));
		 Assert.assertEquals(Title.getText(), "Time Registrations");
}

//____________________________________UTILITY EXCEL________________________________________________________________________


public void Write(int Row , int Cell , String CellValue) throws IOException {
	Excel E = new Excel(Driver);
	E.WriteInExcel(Row, Cell, CellValue);
}

public String Read(int Row , int Cell) throws FileNotFoundException {
	
	FileInputStream ExcelFile = new FileInputStream("./Test Data/TestData.xlsx");
    // Access the required test data sheet
		XSSFWorkbook ExcelWBook = null;
		try {
			ExcelWBook = new XSSFWorkbook(ExcelFile);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		XSSFSheet   ExcelWSheet = ExcelWBook.getSheet("Sheet1");  
     try {
        String CellData =
           ExcelWSheet.getRow(Row).getCell(Cell).getStringCellValue();
        System.out.println("The value of CellData " + CellData);
        return CellData;
     } catch (Exception e) {
        return "Errors in Getting Cell Data";
     }

}

//_______________________________________________________________________________________________________________________________________________
public void ValidateProductionJobInPlanned(int i) {
	
	WebElement JobName = Driver.findElement(By.xpath("(//android.widget.ScrollView//android.view.ViewGroup[1]//android.widget.TextView[@index = '0'])["+i+"]"));
	Wait(JobName);
	String ClickedJobName = JobName.getText();
boolean keyword = ClickedJobName.contains("Production");
if(keyword==true) {
	System.out.println("Production Job is present");	
}
		else {
			System.out.println("ERROORR - JOB IS PRESENT");		
			
	}	
}

public void SelectProductionJobByIndex(int i) {
	try {
	WebElement JobActivationFlag = Driver.findElement(By.xpath("(//android.widget.ScrollView//android.view.ViewGroup[1]//android.widget.Switch)["+i+"]"));
	WebElement JobName = Driver.findElement(By.xpath("(//android.widget.ScrollView//android.view.ViewGroup[1]//android.widget.TextView[@index = '0'])["+i+"]"));
	Wait(JobActivationFlag);
	Wait(JobName);
	String ClickedJobName = JobName.getText();
	Write(3,3,ClickedJobName);
	System.out.println(ClickedJobName + "!!!@##$$%%%^&&** RUNNING JOB NAME ");
	System.out.println("Clicking on Job Name : " + ClickedJobName);
	JobActivationFlag.click();
	}
	catch(Exception E) {
		
		WebElement NoJobs = Driver.findElement(By.xpath("//android.widget.TextView[@text='No Jobs is Available']"));
		Wait(NoJobs);
		if(NoJobs.isDisplayed()) {
			System.out.println("No Job Available");	
			
		}
		
		else {
			System.out.println("ERROORR - JOB IS PRESENT But not interactable");
		
		}	
	}

	
}


public void ValidateJobInSelected(int i) {
	try {
	WebElement ActivatedJobName = Driver.findElement(By.xpath("(//android.widget.Switch[@text='ON']/ancestor::android.widget.ScrollView//android.widget.TextView[@index='0'])["+i+"]"));
	Wait(ActivatedJobName);
	String SelectedJobName = ActivatedJobName.getText();
	
	System.out.println(SelectedJobName + "!!!@##$$%%%^&&** RUNNING JOB NAME---ERRRRRORRRRRRR CLASS NOT FOUND ");
	
	String PlannedJobName = Read(3,3);
	Assert.assertEquals(SelectedJobName, PlannedJobName);
	
	}
	catch(Exception E) {
	WebElement NoJobSelected = Driver.findElement(By.xpath("//android.widget.TextView[@text='No Jobs is Selected']"));
	Wait(NoJobSelected);
	if(NoJobSelected.isDisplayed()) {
		System.out.println("No job is Selected");
	}
	}

}


public void ValidateJobInRunning(int i) {
	try {
	WebElement RunningJobName = Driver.findElement(By.xpath("(//android.widget.TextView[@text='Stop Time']/ancestor::android.widget.ScrollView//android.view.ViewGroup["+i+"]//android.widget.TextView[@index='0'])["+i+"]"));
	Wait(RunningJobName);
	String JobName = RunningJobName.getText();
	
    String SelectedJobName = Read(3, 3);
	
	Assert.assertEquals(JobName, SelectedJobName);
	
	}
	catch(Exception E) {
	WebElement NoJobSelected = Driver.findElement(By.xpath("//android.widget.TextView[@text='No Jobs is Running']"));
	Wait(NoJobSelected);
	if(NoJobSelected.isDisplayed()) {
		System.out.println("NO JOB IS RUNNING");
	}
	}
	
}


public void ValidateJobInTIMEREGISTRATION(int i) throws InterruptedException, FileNotFoundException {
	
	WebElement TimeRegistration = Driver.findElement(By.xpath("(//android.view.ViewGroup//android.widget.TextView[@index='0'])[1]"));
	Wait(TimeRegistration);
	List<WebElement> Registrations = Driver.findElements(By.xpath("//android.widget.TextView[@text='Time Registrations']/ancestor::android.widget.ScrollView//android.widget.ScrollView[@index='1']//android.widget.TextView[@index='1']"));
	for(WebElement Element : Registrations) {
		if(Element.getText().contains(Read(3, 3))) {
			System.out.println("VALIDATED");
		}
	}

}

public void HandleAlert_CI(String Text) {
	WebElement AlertTitle = Driver.findElement(By.id("android:id/alertTitle"));
	Wait(AlertTitle);
	String AlertnName = AlertTitle.getText();
	System.out.println(AlertnName);
	if(AlertnName.contentEquals(Text)) {
		WebElement AlertSubmit = Driver.findElement(By.id("android:id/button1"));
		Wait(AlertSubmit);
		AlertSubmit.click();
	}
	else {
		System.out.println("OtherIssue");
	}
	
}

//_______________________________________________Validate Clock In_________________________________________________________

public void ValidateClockInHomePage() throws IOException {
	
	List<WebElement> TimeRegistrationAllJobs = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@index='1']"));
for(WebElement RegistrationName : TimeRegistrationAllJobs){
	
	if(RegistrationName.getText().equalsIgnoreCase("Clock In")) {
		List<WebElement> ClockInTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
		int numberOfCI = ClockInTime.size();
		System.out.println(numberOfCI);
		
		Excel E = new Excel(Driver);
		String ExpectedValue = E.ReadFromExcel(5,5).substring(13, 17);
		
		String EV  = Driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains("+ExpectedValue+").instance(0))").getText();
		System.out.println(EV);
		
		WebElement element = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2'])[numberOfCI]"));
		String time = element.getText();
		String ActualValue = time.substring(0,4);
		System.out.println(ActualValue);
		Assert.assertEquals(EV,ExpectedValue);
		
		
		
	}
	
}	
	
}
	


public void ValidateClockInHomePage_Duplicate() throws IOException {
	
	List<WebElement> TimeRegistrationAllJobs = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@index='1']"));
for(WebElement RegistrationName : TimeRegistrationAllJobs){
	
	if(RegistrationName.getText().equalsIgnoreCase("Clock In")) {
		List<WebElement> ClockInTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
		int numberOfCI = ClockInTime.size();
		System.out.println(numberOfCI);
		for(WebElement Time : ClockInTime) {	
			Excel E = new Excel(Driver);
			if(Time.getText().equalsIgnoreCase(E.ReadFromExcel(5, 5).substring(13,21))) {
				String Time1 = Time.getText();
				System.out.println(Time1);
				Excel E1 = new Excel(Driver);
				String ExpectedTime =E1.ReadFromExcel(5, 5);
				Assert.assertEquals(Time1, ExpectedTime.substring(13,21));
				System.out.println("PASS+trdtrytvhygygyffytd 65433556445@$$$$#$$$$##@#!#$$%$#%^$%7");
			}
			
			else {
				Excel E1 = new Excel(Driver);
				ScrollDown(E1.ReadFromExcel(5, 5).substring(13,21));
				String Time2 = Time.getText();
				System.out.println(Time2);
				Excel E2 = new Excel(Driver);
				String ExpectedTime =E2.ReadFromExcel(5, 5);
				Assert.assertEquals(Time2, ExpectedTime.substring(13,21));
				System.out.println("PASS+trdtrytvhygygyffytd 65433556445@$$$$#$$$$##@#!#$$%$#%^$%7");
			}
		}
		
	}
	
	
	
}
	
	
}

}



	
	

