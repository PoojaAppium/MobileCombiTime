package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.xml.sax.Locator;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.offset.PointOption;


public class Generic {

	AndroidDriver<WebElement> Driver ;
	
	public Generic(AndroidDriver<WebElement> Driver) {
		this.Driver =  Driver;
	}
//___________________________________SEND KEYS ______________________________________________________________________________________	
	
	public void SendKeys(By uN, String text) {
		WebElement Web = Driver.findElement((By) uN);
		Web.clear();
		Web.sendKeys(text);
	}
//___________________________Click And Send Keys _______________________________________________________________________________________________________
	
	public void Click_SendKeys(WebElement Ele, String text) {
		
		WebDriverWait WW = new WebDriverWait(Driver,40);
		WW.until(ExpectedConditions.visibilityOf(Ele));
		WW.until(ExpectedConditions.elementToBeClickable(Ele));
		Ele.click();
		Ele.sendKeys(text);
	}
	
	
	
//____________________________________________________________WAIT________________________________________________________________
public void Wait(WebElement Ele) {
	try {
	WebDriverWait WW = new WebDriverWait(Driver , 25);
	WW.until(ExpectedConditions.visibilityOf(Ele));
	}
	catch(Exception E) {
		TouchAction action = new TouchAction(Driver);
        int x = Driver.manage().window().getSize().getWidth()/2;
        int y = Driver.manage().window().getSize().getHeight()/2;
        action.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y-(y))).release().perform();
	}
}

//_________________________________VALIDATE TOAST MESSAGE_____________________________________________________________________________________

public void ValidateToastMessage(Locator Loc , String Text) {
	WebDriverWait WW = new WebDriverWait(Driver , 40);
	WW.until(ExpectedConditions.presenceOfElementLocated((By) Loc));
	 WebElement toastMessage = Driver.findElement((By) Loc);
		 Assert.assertEquals(toastMessage.getText(), Text);
		System.out.println( "TOAST MESSAGE ----" + toastMessage.getText());
		
}

//____________________________________Scroll In Mobile__________________________________________________________________________________

public void ScrollDown(String Text) {
	MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector()).ScrollInToView("+"new UiSelector().description("+Text+")");

}

public void Scroll() {
	TouchAction action = new TouchAction(Driver);
    int x = Driver.manage().window().getSize().getWidth()/2;
    int y = Driver.manage().window().getSize().getHeight()/2;
    action.longPress(PointOption.point(x, y)).moveTo(PointOption.point(x, y-(y))).release().perform();

}

//______________________GET ELEMENT LOCATION___________________________________________________________________
public static int getXcord(WebElement element)
{
    Point point = element.getLocation();
    
    int xcord = point.getX();
    
    return xcord;
}

public static int getYcord(WebElement element)
{
    Point point = element.getLocation();
    
    int ycord = point.getY();
    
    return ycord;
}


//____________________________________________HorizontallyScroll__________________________________________________________________________________


public void HorizontallyScroll(WebElement ele) {
	TouchAction action = new TouchAction(Driver);
    int x = Driver.manage().window().getSize().getWidth()/2;
    int y = Driver.manage().window().getSize().getHeight()/2;
    action.longPress(PointOption.point(getXcord(ele), getYcord(ele))).moveTo(PointOption.point(getXcord(ele)-(getXcord(ele)),getYcord(ele) )).release().perform();
    
    


}


//_______________________________________________________________Validate Page Title__________________________________________________________________________

public void ValidatePageTitle(String Text) throws InterruptedException {
	Thread.sleep(3000);
	WebDriverWait WW = new WebDriverWait(Driver , 40);
		WebElement Title = Driver.findElement(By.xpath("(//android.widget.TextView[@index='4'])[1]"));
		WW.until(ExpectedConditions.visibilityOf(Title));
		 Assert.assertEquals(Title.getText(), Text);
}

//_______________________________________________________Validate Time Registration Title _________________________________________________________________________________

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

//________________________________________________________ValidateProductionJobInPlanned_______________________________________________________________________________________
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


public void ValidateJobInSelected(int i) throws FileNotFoundException {
	//try {
	WebElement ActivatedJobName = Driver.findElement(By.xpath("(//android.widget.Switch[@text='ON']/ancestor::android.widget.ScrollView//android.widget.TextView[@index='0'])["+i+"]"));
	Wait(ActivatedJobName);
	String SelectedJobName = ActivatedJobName.getText();
	
	System.out.println(SelectedJobName + "!!!@##$$%%%^&&** RUNNING JOB NAME---ERRRRRORRRRRRR CLASS NOT FOUND ");
	
	String PlannedJobName = Read(3,3);
	Assert.assertEquals(SelectedJobName, PlannedJobName);
	
//	}
//	catch(Exception E) {
//	WebElement NoJobSelected = Driver.findElement(By.xpath("//android.widget.TextView[@text='No Jobs is Selected']"));
//	Wait(NoJobSelected);
//	if(NoJobSelected.isDisplayed()) {
//		System.out.println("No job is Selected");
//	}
	//}

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

//____________________________________ VALIDATE JOB IN TIME REGISTRATION_____________________________________________________________________

public void ValidateJobInTIMEREGISTRATION(int i) throws InterruptedException, IOException {
	
	WebElement TimeRegistration = Driver.findElement(By.xpath("(//android.view.ViewGroup//android.widget.TextView[@index='0'])[1]"));
	Wait(TimeRegistration);
	List<WebElement> Registrations = Driver.findElements(By.xpath("//android.widget.TextView[@text='Time Registrations']/ancestor::android.widget.ScrollView//android.widget.ScrollView[@index='1']//android.widget.TextView[@index='1']"));
	for(WebElement Element : Registrations) {
		if(Element.getText().contains(Read(3, 3))) {
			System.out.println("VALIDATED");
		}
		
		WebElement ProjectName = Driver.findElement(By.xpath("//android.widget.TextView[@text='Project PRJ']/following-sibling::android.widget.TextView[@index='4']"));
		WebElement ProjectTaskID = Driver.findElement(By.xpath("//android.widget.TextView[@text='Project PRJ']/following-sibling::android.widget.TextView[@index='5']"));
		WebElement ProjectDescription = Driver.findElement(By.xpath("//android.widget.TextView[@text='Project PRJ']/following-sibling::android.widget.TextView[@index='6']"));
	
        Write(2,4,ProjectName.getText());
        Write(2,5,ProjectTaskID.getText());
        Write(2,6,ProjectDescription.getText());
   
		
		
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



public void HandleAlert_CO(String Text) {	
		//for CO
		WebElement AlertTitle = Driver.findElement(By.id("android:id/alertTitle"));
		Wait(AlertTitle);
		String AlertnName = AlertTitle.getText();
		System.out.println(AlertnName);
		if(AlertnName.contentEquals(Text)) {
			WebElement AlertSubmit_YES = Driver.findElement(By.id("android:id/button2"));
			WebDriverWait WW = new WebDriverWait(Driver,40);
			WW.until(ExpectedConditions.elementToBeClickable(AlertSubmit_YES));
			AlertSubmit_YES.click();
		}
		else {
			System.out.println("NO ABSENCE CAUSE ASKED BEFORE CLOCK OUT******");
		}

}






//_______________________________________________Validate Clock In_________________________________________________________

public void ValidateClockInHomePage() throws IOException, ParseException {
	
	List<WebElement> TimeRegistrationAllJobs = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@index='1']"));
//for(WebElement RegistrationName : TimeRegistrationAllJobs){
	
	//if(RegistrationName.getText().equalsIgnoreCase("Clock In")) {
		
		List<WebElement> ClockInTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
		int numberOfCI = ClockInTime.size();
		System.out.println("22222"+numberOfCI);
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
		LocalDate ld = LocalDate.now();
		String Date = dtf.format(ld);
		System.out.println("11111"+ Date);
		
		WebElement dateInApp = Driver.findElement(By.xpath("(//android.widget.TextView[@index='1'])[1]"));
		
		if(Date == dateInApp.getText().substring(0, 12)) {
			
		Date date = new Date();        
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:00")
				.withZone(ZoneId.systemDefault());
		String dateToStr = format.format(date.toInstant()); 
		System.out.println("33333"+"Date is "+ dateToStr);
		
		String EV  = Driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+dateToStr+"\").instance(0))").getText();
		System.out.println("44444"+EV);

		WebElement element = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2'])["+numberOfCI+"]"));
		String time = element.getText();
		String ActualValue = time.substring(0,4);
		System.out.println(ActualValue);
		Assert.assertEquals(EV,dateToStr);
		System.out.println("66666"+"VALIDATED CLOCK IN REGISTERED***********");		
	}
	
	
else if(Date!= dateInApp.getText().substring(0, 12)) {
	
	DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("E");
	LocalDate ld1 = LocalDate.now();
	String Day = dtf1.format(ld1);
	System.out.println(Day);
	
	WebElement dayInApp = Driver.findElement(By.xpath("//android.widget.TextView[@text='"+Day+"']"));
	dayInApp.click();
		
	Date date = new Date();        
	DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:00")
			.withZone(ZoneId.systemDefault());
	String dateToStr = format.format(date.toInstant()); 
	System.out.println("77777"+"Date is "+ dateToStr);
	
	String EV  = Driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+dateToStr+"\").instance(0))").getText();
	System.out.println(EV);

	WebElement element = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2'])["+numberOfCI+"]"));
	String time = element.getText();
	String ActualValue = time.substring(0,4);
	System.out.println("99999"+ActualValue);
	Assert.assertEquals(EV,dateToStr);
	System.out.println("88888"+"VALIDATED CLOCK IN REGISTERED***********");	
	}
	
}	
//}	
//}
	


public void ValidateClockIN_ClockOutHomePage_AfterRegistration() throws IOException {
	

	List<WebElement> TimeRegistrationAllJobs = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@index='1']"));
for(WebElement RegistrationName : TimeRegistrationAllJobs){
	
	if(RegistrationName.getText().equalsIgnoreCase("Clock Out")) {
		
		List<WebElement> ClockOutTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock Out']/following-sibling::android.widget.TextView[@index='2']"));
		int numberOfCO = ClockOutTime.size();
		System.out.println(numberOfCO);
		
//		Excel E = new Excel(Driver);
//		String ExpectedValue = E.ReadFromExcel(5,5).substring(13, 17);
		
//		DateFormat formatter = new SimpleDateFormat("hh:mm:00");
//		Date date = (Date)formatter. parse(ExpectedValue);
//		System.out.println(date);
		
		Date date = new Date();        
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:00")
				.withZone(ZoneId.systemDefault());
		String dateToStr = format.format(date.toInstant()); 
		System.out.println("Date is "+ dateToStr);
		
		String EV  = Driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+dateToStr+"\").instance(0))").getText();
		System.out.println(EV);

		WebElement element = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock Out']/following-sibling::android.widget.TextView[@index='2'])["+numberOfCO+"]"));
		String time = element.getText();
		String ActualValue = time.substring(0,4);
		System.out.println(ActualValue);
		Assert.assertEquals(EV,dateToStr);
		System.out.println("VALIDATED CLOCK OUT REGISTERED***********");
			}
	
	else if(RegistrationName.getText().equalsIgnoreCase("Clock In")){
		
		List<WebElement> ClockInTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
		int numberOfCI = ClockInTime.size();
		System.out.println(numberOfCI);
		
		Date date = new Date();        
		DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:00")
				.withZone(ZoneId.systemDefault());
		String dateToStr = format.format(date.toInstant()); 
		System.out.println("Date is "+ dateToStr);
		
		String EV  = Driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+dateToStr+"\").instance(0))").getText();
		System.out.println(EV);

		WebElement element = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2'])["+numberOfCI+"]"));
		String time = element.getText();
		String ActualValue = time.substring(0,4);
		System.out.println(ActualValue);
		Assert.assertEquals(EV,dateToStr);
		System.out.println("VALIDATED CLOCK IN REGISTERED***********");		
	}
	
	
		}
		

	
	
}

//______________________________________________VALIDATE CI & CO IN HOME PAGE_______________________________________________

public void ValidateCICO_INHOMEPAGE_BeforeRegistration(){
	
	List<WebElement> TimeRegistrationAllJobs = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@index='1']"));
    int all_ele = TimeRegistrationAllJobs.size();
    System.out.println(all_ele);
	
	
	for(WebElement RegistrationName : TimeRegistrationAllJobs){
		
		System.out.println("1 number" + RegistrationName.getText());
		
		if(RegistrationName.getText().equalsIgnoreCase("Clock In")) {
			List<WebElement> ClockInTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
			int numberOfCI = ClockInTime.size();
			System.out.println("2 number" +numberOfCI); 
			int Count = numberOfCI;
			while(all_ele>0) {
				Scroll();
				List<WebElement> ClockInTime1 = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
				int numberOfCI1 = ClockInTime1.size();
				System.out.println("**********" +numberOfCI1); 
				 Count = Count + numberOfCI1;	
				System.out.println(Count);
				try {
					WebElement Mileage = Driver.findElement(By.xpath("(//android.widget.TextView[@index = '0'])[2]"));
					if(Mileage.isDisplayed()) {
						break;
					}
				}
				catch(Exception E) {
					System.out.println("Scroll Again");
				}
			}
			
			int TotalCount = Count;
			System.out.println(TotalCount);
			
			
			if( TotalCount==1) {
				try {
					//Agar koi Clock Out Present nhi Hua To
				 WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
					Wait(Ele);
					WebDriverWait WW = new WebDriverWait(Driver,25);
					WW.until(ExpectedConditions.elementToBeClickable(Ele));
					Ele.click();
					  WebElement CO = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-out']"));
						Wait(Ele);
						String Clockout = CO.getText();
						System.out.println("3 number" +Clockout + "NEWWWWW METTHHOOODDDD TRIIAAALLLL");
						Assert.assertEquals("Clock-out", Clockout);	
			}
			
				catch(Exception E) {
					//Agar 1 Clock Out Present Hua To
					List<WebElement> ClockOutTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock Out']/following-sibling::android.widget.TextView[@index='2']"));
					int numberOfCO = ClockOutTime.size();
					System.out.println("9 number" + numberOfCO); 
					int Count_OUT = numberOfCO;
					while(all_ele>0) {
						Scroll();
						Count_OUT = Count_OUT + numberOfCO;	
						System.out.println(Count_OUT);
						try {
							WebElement Mileage = Driver.findElement(By.xpath("(//android.widget.TextView[@index = '0'])[2]"));
							if(Mileage.isDisplayed()) {
								break;
							}
						}
						catch(Exception E1) {
							System.out.println("Scroll Again");
						}
					}
					
					int TotalCount_Out = Count_OUT;
					System.out.println(TotalCount_Out);
					
					
					
					
					try {
					if(TotalCount_Out==1) {			
					  WebElement CI = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
						Wait(CI);
						String ClockIN = CI.getText();
						System.out.println("3 number" + ClockIN + "NEWWWWW METTHHOOODDDD TRIIAAALLLL");
						Assert.assertEquals("Clock-in", ClockIN);
						
					}
					}
					catch(StaleElementReferenceException E1) {					
						  WebElement CI = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
							Wait(CI);
							String ClockIN = CI.getText();
							System.out.println("3 number" + ClockIN + "NEWWWWW METTHHOOODDDD TRIIAAALLLL");
							Assert.assertEquals("Clock-in", ClockIN);
							
					}
				}
				WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
				Wait(Ele);
				WebDriverWait WW = new WebDriverWait(Driver,25);
				WW.until(ExpectedConditions.elementToBeClickable(Ele));
				Ele.click();
			}
			
			
			else if (TotalCount>1) {
				
				
				List<WebElement> ClockInTime1 = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
				int numberOfCI1 = ClockInTime1.size();
				System.out.println("5 number" + numberOfCI1); 
				
				int Count_IN = numberOfCI1;
				while(all_ele>0) {
					Scroll();
					Count_IN = Count_IN + numberOfCI1;	
					System.out.println(Count);
					try {
						WebElement Mileage = Driver.findElement(By.xpath("(//android.widget.TextView[@index = '0'])[2]"));
						if(Mileage.isDisplayed()) {
							break;
						}
					}
					catch(Exception E) {
						System.out.println("Scroll Again");
					}
				}
				
				int TotalCount_IN = Count_IN;
				System.out.println(TotalCount_IN);

				List<WebElement> ClockOutTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock Out']/following-sibling::android.widget.TextView[@index='2']"));
				int numberOfCO = ClockOutTime.size();
				System.out.println("6 number" +numberOfCO); 
				
				int Count_OUT = numberOfCO;
				while(all_ele>0) {
					Scroll();
					Count_OUT = Count_OUT + numberOfCO;	
					System.out.println(Count_OUT);
					try {
						WebElement Mileage = Driver.findElement(By.xpath("(//android.widget.TextView[@index = '0'])[2]"));
						if(Mileage.isDisplayed()) {
							break;
						}
					}
					catch(Exception E) {
						System.out.println("Scroll Again");
					}
				}
				
				int TotalCount_Out = Count_OUT;
				System.out.println(TotalCount_Out);
				
				if((TotalCount_IN+TotalCount_Out)%2==0) {
					 WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
						Wait(Ele);
						WebDriverWait WW = new WebDriverWait(Driver,25);
						WW.until(ExpectedConditions.elementToBeClickable(Ele));
						Ele.click();
						  WebElement CI = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-in']"));
							Wait(Ele);
							String ClockIN = CI.getText();
							System.out.println("7 number" +ClockIN + "NEWWWWW METTHHOOODDDD TRIIAAALLLL");
							Assert.assertEquals("Clock-in", ClockIN);
							//Close same eelement
							Ele.click();
				
			}
			
			else if((TotalCount_IN+TotalCount_Out)%2!=0) {
				
				WebElement Ele = Driver.findElement(By.xpath("(//android.view.ViewGroup[@bounds = '[868,2048][998,2167]'])[4]"));
				Wait(Ele);
				WebDriverWait WW = new WebDriverWait(Driver,25);
				WW.until(ExpectedConditions.elementToBeClickable(Ele));
				Ele.click();
				  WebElement CO = Driver.findElement(By.xpath("//android.widget.TextView[@text = 'Clock-out']"));
					Wait(Ele);
					String Clockout = CO.getText();
					
					Assert.assertEquals("Clock-out", Clockout);
					//Close same eelement
					Ele.click();
					System.out.println("8 number" + Clockout + "NEWWWWW METTHHOOODDDD TRIIAAALLLL");
				
				
			}			}
						
			
	
}
	
}

//_____________________________________________________________validateinHomePage__________________________________________________________________________________________________________	
	
}
	
	public void validateinHomePage() throws InterruptedException {

			List<WebElement> TimeRegistrationAllJobs = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@index='1']"));
		    int all_ele = TimeRegistrationAllJobs.size();
		    System.out.println(all_ele);  // ALL CI CO
	
					List<WebElement> ClockInTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
					int numberOfCI = ClockInTime.size();
					System.out.println("2 number" +numberOfCI);  //ALL CI
					
					List<WebElement> ClockOutTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock Out']/following-sibling::android.widget.TextView[@index='2']"));
					int numberOfCO = ClockOutTime.size();
					System.out.println("9 number" + numberOfCO);  // ALL CO
					
				//	WebElement BottomLine = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index = '5'])[2]"));
				//	WebElement Mileage = Driver.findElement(By.xpath("(//android.widget.TextView[@index = '0'])[2]"));
					int Count = numberOfCI;
		
					List<WebElement> BottomLine = Driver.findElements(By.xpath("(//android.view.ViewGroup[@index = '5' or '4'])[2]"));	
				while(BottomLine.size()==1) {
						Scroll();
						
						Thread.sleep(4000);
						List<WebElement> ClockInTime1 = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
					int numberOfCI1 = ClockInTime1.size();
						System.out.println("**********" +numberOfCI1); 
						 Count = Count + numberOfCI1;	
						System.out.println("gcfhgv"+ Count);
						List<WebElement> Mileage = Driver.findElements(By.xpath("(//android.widget.TextView[@index = '0'])[2]"));
							if(Mileage.get(0).isDisplayed()) {
								break;
							}
					
					}
					
					int TotalCount = Count;
					System.out.println(TotalCount);
					
					
				}
				
				
//------------------------------------------------ VALIDATE AUTO CLOCK IN ------------------------------------------------------------------------------------------
	
	

		public void AutoClockIn() throws IOException, ParseException {
			
			List<WebElement> TimeRegistrationAllJobs = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@index='1']"));
		//for(WebElement RegistrationName : TimeRegistrationAllJobs){
			
			//if(RegistrationName.getText().equalsIgnoreCase("Clock In")) {
				
				List<WebElement> ClockInTime = Driver.findElements(By.xpath("//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
				int numberOfCI = ClockInTime.size();
				System.out.println("22222"+numberOfCI);
				
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
				LocalDate ld = LocalDate.now();
				String Date = dtf.format(ld);
				System.out.println("11111"+ Date);
				
				WebElement dateInApp = Driver.findElement(By.xpath("(//android.widget.TextView[@index='1'])[1]"));
				
				if(Date == dateInApp.getText().substring(0, 12)) {
					
				Date date = new Date();        
				DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:00")
						.withZone(ZoneId.systemDefault());
				String dateToStr = format.format(date.toInstant()); 
				System.out.println("33333"+"Date is "+ dateToStr);
				
				String EV  = Driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\""+dateToStr+"\").instance(0))").getText();
				System.out.println("44444"+EV);

				WebElement element = Driver.findElement(By.xpath("(//android.view.ViewGroup[@index='4']//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2'])["+numberOfCI+"]"));
				String time = element.getText();
				String ActualValue = time.substring(0,4);
				System.out.println(ActualValue);
				Assert.assertEquals(EV,dateToStr);
				System.out.println("66666"+"VALIDATED Auti Clock IN REGISTERED***********");	
				
			// Assert Job timing same as CI timing. 
				
				WebElement TimeRegistration = Driver.findElement(By.xpath("(//android.view.ViewGroup//android.widget.TextView[@index='0'])[1]"));
				Wait(TimeRegistration);
				List<WebElement> Registrations = Driver.findElements(By.xpath("//android.widget.TextView[@text='Time Registrations']/ancestor::android.widget.ScrollView//android.widget.ScrollView[@index='1']//android.widget.TextView[@index='1']"));
				for(WebElement Element : Registrations) {
					if(Element.getText().contains(Read(3, 3))) {
						
						System.out.println("VALIDATED");
					}
				}
			
				// Assert 	
				
				WebElement ProjectTime = Driver.findElement(By.xpath("//android.widget.TextView[@text='Project PRJ']/following-sibling::android.widget.TextView[@index='2']"));
				WebElement ClockIn_Time = Driver.findElement(By.xpath("//android.widget.TextView[@text='Clock In']/following-sibling::android.widget.TextView[@index='2']"));
				
				Assert.assertEquals(ProjectTime.getText(), ClockIn_Time.getText(), "ASSERT CLOCK IN");
				
			
				
			}
	}
	
	
	
		//android.widget.TextView[@text='Project PRJ']/following-sibling::android.widget.TextView[@index='4']
	
	
	
	}

