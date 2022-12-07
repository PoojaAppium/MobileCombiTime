package StepsDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import POM.ClockIn;
import POM.Dashboard;
import POM.Jobs;
import POM.LoginPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class Steps {

	public AndroidDriver<WebElement> Driver;
	 LoginPage LP;
	 Dashboard D;
	 Jobs J;
	 ClockIn CI;


	@Given("Application Launch")
	public void application_Launch() throws InterruptedException {
	
     LP = new LoginPage(Driver);
     Thread.sleep(2000);
		try{		
			DesiredCapabilities DC = new DesiredCapabilities();
			DC.setCapability("PlatformVersion", "12");
			DC.setCapability("deviceName", "vivo X70 Pro");
			DC.setCapability("UDID", "1361177652000IA");
			DC.setCapability("platformName", "Android");
			DC.setCapability("appActivity", "com.combitime.MainActivity");
			DC.setCapability("appPackage", "com.combitime.test");
			DC.setCapability("automationName", "UiAutomator2");
			DC.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 60);
			DC.setCapability("noSign", true);
			DC.setCapability("autoGrantPermissions",true);
			URL url = new URL("http://127.0.0.1:4723/wd/hub");
			Driver = new AndroidDriver<WebElement>(url,DC);
			Driver.manage().timeouts().implicitlyWait(30 , TimeUnit.SECONDS); 
			System.out.println("System Started");
			Thread.sleep(5000);
			}
			catch(Exception exe) {
				exe.printStackTrace();
			}	
		
		Thread.sleep(2000);
		}
	@When("Application Open")
	public void application_Open() {
	 System.out.println("Application Opens");
	}


	@And("Fill Terminal ID {string}")
	public void fill_Terminal_ID(String string) {
		LP = new LoginPage(Driver);
		LP.FillTerminalID(string);
	}

	@And("Fill password {string}")
	public void fill_password(String string) {
		LP = new LoginPage(Driver);
		try {
		LP.FillPassword(string);
		}
		catch(StaleElementReferenceException E) {
			LP.FillPassword(string);
		}
	}

	@And("Click on Login")
	public void click_on_Login() {
		LP = new LoginPage(Driver);
		LP.Submit();
	}


@Then("Validate toast message <Wrong Credentials Entered>")
public void validate_toast_message_Wrong_Credentials_Entered() throws InterruptedException  {
  LP = new LoginPage(Driver);
  LP.ValidateError_WrongCredential("Wrong Credentials Entered");
}

@Then("Validate toast message <Terminal app not found>")
public void validate_toast_message_Terminal_app_not_found() throws InterruptedException {
	LP = new LoginPage(Driver);
	  LP.ValidateError_TerminalNotFound("Terminal app not found");
}

@Then("Validate toast message <Please enter your Terminal Id and Password to login>")
public void validate_toast_message_Please_enter_your_Terminal_Id_and_Password_to_login() throws InterruptedException {
	LP = new LoginPage(Driver);
	  LP.ValidateError_EmptyTerminalAndPassword("Please enter your Terminal Id and Password to login");
}

@Then("Validate Combitime Logo")
public void validate_Combitime_Logo() {
    LP = new LoginPage(Driver);
    LP.ValidateTitle();
}

@Then("Validate Home Page UI")
public void validate_Home_Page_UI() {
   D = new Dashboard(Driver);
   D.ValidateTitle();
}

//@When("Logout Application")
//public void logout_Application() {
//    // Write code here that turns the phrase above into concrete actions
//    throw new io.cucumber.java.PendingException();
//}

@When("Navigate to back")
public void navigate_to_back() {
    Driver.navigate().back();
}
	
@Then("Quite Application")
public void quite_Application() {
 Driver.quit();
}


//_________________________________________________________________________________________________________________________
//__________________________________J O B _________________________________________________________________________________
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
@Given("Home Page Opens")
public void home_Page_Opens() {
    D = new Dashboard(Driver);
    D.ValidateTitle();
}

@When("Wait for {int} Seconds")
public void wait_for_Seconds(Integer int1) throws InterruptedException {
    Thread.sleep(int1*1000);
}

@When("Click on Plus ICON")
public void click_on_Plus_ICON() {
	 D = new Dashboard(Driver);
	    D.ClickOnPlusIcon();  
}

@When("Click on Jobs button")
public void click_on_Jobs_button() {
	 D = new Dashboard(Driver);
	    D.ClickOnJobs();
}

@Then("Validate page Title of <Jobs>")
public void validate_page_Title_of_Job() {
 J = new Jobs(Driver);
 J.ValidateTitle();
}


@When("Click on Filter icon")
public void click_on_Filter_icon() {
	 J = new Jobs(Driver);
	 J. ClickOnFilter();
}

@When("Deselect Internal Job")
public void deselect_Internal_Job() {
	 J = new Jobs(Driver);
	 J.DeselectInterJobFilter();
}

@When("Select Production Type job")
public void select_Production_Type_job() {
	 J = new Jobs(Driver);
	 J.ClickOnProductionFilter();
}

@When("Click on cancel button")
public void click_on_cancel_button() {
	 J = new Jobs(Driver);
	 J.ClickOnCancelFilter();
}

@When("Click On Planned Section")
public void click_On_Planned_Section() {
	J = new Jobs(Driver);
	 J.ClickOnPlannedSection();
}

@Then("Validate job in Planned section.")
public void validate_job_in_Planned_section() {
	J = new Jobs(Driver);
	 J.ValidateJobInPlanned(2);
}

@When("Flag on of 1st job.")
public void flag_on_of_1st_job() {
	J = new Jobs(Driver);
	 J.ActivateProductionJob(2); // ACTIVATE 2ND JOB , ONLY 1 JOB WILL ACTIVATE
}

@When("Click On Select Section")
public void click_On_Select_Section() {
	J = new Jobs(Driver);
	 J.ClickOnSelectedButton();
}

@Then("Validate job in Selected section")
public void validate_job_in_Selected_section() throws IOException {
	J = new Jobs(Driver);
	 J.ValidateJobInSelected(1); //DEPENDS ON NO. OF JOB 
}

@When("Click on Submit button")
public void click_on_Submit_button() {
		J = new Jobs(Driver);
	 J.ClickOnSubmitButton();

}

@Then("Validate Toast Message {string}")
public void validate_Toast_Message(String string) {
	try {
	J = new Jobs(Driver);
	 J.ValidateToastMessage_SuccessfullUpdated(string);
	}
	catch(Exception E) {
		J = new Jobs(Driver);
		J.ClockInDuringJobRegistration();
		J.ClickOnSubmitButton();
		J.ValidateToastMessage_SuccessfullUpdated(string);
	}
}


@When("Click On Running section")
public void click_On_Running_section() {
	J = new Jobs(Driver);
	 J.ClickOnRunningSection();
}

@Then("Validate job in Running section.")
public void validate_job_in_Running_section() {
	J = new Jobs(Driver);
	 J.ValidateRunningJob(1);
}

@When("Navigate Back")
public void navigate_Back() {
	J = new Jobs(Driver);
	 J.NavigateToBack();

}

@Then("Validate Time registration")
public void validate_Time_registration() throws InterruptedException, FileNotFoundException {
	J = new Jobs(Driver);
	 J.ValidateJobinTimeRegistration(1); // no. of job registered 
}

//__________________________________________________Clock IN ______________________________________________________________________________

@When("Click on ClockIN button")
public void click_on_ClockIN_button() {
D = new Dashboard(Driver);
D.ClickOnCI();
}

@Then("Validate page Title of <Clock IN>")
public void validate_page_Title_of_Clock_IN() throws InterruptedException {
    CI = new ClockIn(Driver);
    CI.Validate_CI_Title();
}

@When("Make Clock IN")
public void make_Clock_IN() throws IOException {
	 CI = new ClockIn(Driver);
	    CI.MakeClockIn();
}


@Then("Validate Clock IN in Dashboard")
public void validate_Clock_IN_in_Dashboard() throws IOException {

	 CI = new ClockIn(Driver);
	    CI.ValidateClockIn_in_HomePage();
}































}
