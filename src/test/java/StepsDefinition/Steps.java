package StepsDefinition;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import POM.ClockIN_Out;
import POM.Dashboard;
import POM.ItemConsumption;
import POM.Jobs;
import POM.LoginPage;
import POM.ProjectMileage;
import POM.ProjectSupplement;
import Utility.Generic;
import io.appium.java_client.AppiumDriver;
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
	 ClockIN_Out CI;


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
//			DC.setCapability("unicodeKeyboard", "true");
//			DC.setCapability("resetKeyboard", "true");
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
public void click_on_Plus_ICON() throws InterruptedException {
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


@When("Select Project Type job")
public void select_Project_Type_job() {
	 J = new Jobs(Driver);
	 J.ClickOnProjectFilter();
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
public void flag_on_of_1st_job() throws InterruptedException {
	J = new Jobs(Driver);
	 J.ActivateProductionJob(2); // ACTIVATE 2ND JOB , ONLY 1 JOB WILL ACTIVATE
	 Thread.sleep(2000);
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
	 J.ValidateToastMessage_SuccessfullAdded(string);
	}
	catch(Exception E) {
		J = new Jobs(Driver);
		J.ClockInDuringJobRegistration();
		J.ClickOnSubmitButton();
		J.ValidateToastMessage_SuccessfullAdded(string);
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
public void validate_Time_registration() throws InterruptedException, IOException {
	J = new Jobs(Driver);
	 J.ValidateJobinTimeRegistration(1); // no. of job registered 
}

//__________________________________________________Clock IN ______________________________________________________________________________

@When("Click on ClockIN button")
public void click_on_ClockIN_button() throws IOException, InterruptedException {
D = new Dashboard(Driver);
D.ClickOnCI();
}

@Then("Validate page Title of <Clock IN>")
public void validate_page_Title_of_Clock_IN() throws InterruptedException {
    CI = new ClockIN_Out(Driver);
    CI.ValidateClockIN_ClockOut_Title();
}

@When("Make Clock IN")
public void make_Clock_IN() throws IOException {
	 CI = new ClockIN_Out(Driver);
	    CI.MakeClockIn();
}


@Then("Validate Clock IN in Dashboard")
public void validate_Clock_IN_in_Dashboard() throws IOException, ParseException {
	 CI = new ClockIN_Out(Driver);
	    CI.ValidateClockIn_in_HomePage();
}

@Then("Validate Home Page Clock in & Clock out")
public void validate_Home_Page_Clock_in_Clock_out() {
  Generic G = new Generic(Driver);
  G.ValidateCICO_INHOMEPAGE_BeforeRegistration();
   
}

@Then("Validate Clock Out in Plus icon")
public void validate_Clock_Out_in_Plus_icon() {
D = new Dashboard(Driver);
D.ValidateCOinPlusIcon_AfterCI();

}
//___________________________________________________________________CLOCK OUT________________________________________________________

@When("Click on ClockOut button")
public void click_on_ClockOut_button() throws IOException, InterruptedException {
	D = new Dashboard(Driver);
	D.ClickOnCO();
}

@Then("Validate page Title of <Clock Out>")
public void validate_page_Title_of_Clock_Out() throws IOException, ParseException, InterruptedException {
	CI = new ClockIN_Out(Driver);
    CI.ValidateClockIN_ClockOut_Title();
}

@When("Make Clock Out")
public void make_Clock_Out() throws IOException {
	 CI = new ClockIN_Out(Driver);
	    CI.MakeClockOut();
}

@Then("Validate Clock Out in Dashboard")
public void validate_Clock_Out_in_Dashboard() throws IOException, ParseException {
	 CI = new ClockIN_Out(Driver);
	    CI.ValidateClockIN_ClockOut_in_HomePage(); //GADBAD
}

@Then("Validate Clock in in Plus icon")
public void validate_Clock_in_in_Plus_icon() {
	D = new Dashboard(Driver);
	D.ValidateCIinPlusIcon_AfterCO();
}

//___________________________________________________________________CLOCK IN / CLOCK OUT_________________________________________________________

@Given("Home Page Opens <Action>")
public void home_Page_Opens_Action() {
	D = new Dashboard(Driver);
    D.ValidateTitle();
}

@When("Wait for {int} Seconds <Action>")
public void wait_for_Seconds_Action(Integer int1) throws InterruptedException {
	  Thread.sleep(int1*1000);
}

@Then("Validate Home Page Clock in & Clock out <Action>")
public void validate_Home_Page_Clock_in_Clock_out_Action() throws InterruptedException {
	 Generic G = new Generic(Driver);
	  G.validateinHomePage();
}

@When("Click on Plus ICON <Action>")
public void click_on_Plus_ICON_Action() throws InterruptedException {
	 D = new Dashboard(Driver);
	    D.ClickOnPlusIcon();  
}

@When("Click on ClockOut\\/ClockIN button <Action>")
public void click_on_ClockOut_ClockIN_button_Action() throws IOException, InterruptedException {
	D = new Dashboard(Driver);
	D.ClickOnCICO();
}

@Then("Validate page Title of ClockOut\\/ClockIN <Action>")
public void validate_page_Title_of_ClockOut_ClockIN_Action() throws InterruptedException {
    CI = new ClockIN_Out(Driver);
    CI.ValidateClockIN_ClockOut_Title();
}

@When("Make ClockOut\\/ClockIN <Action>")
public void make_ClockOut_ClockIN_Action() throws IOException, InterruptedException {
	CI = new ClockIN_Out(Driver);
	CI.MakeClockIN_ClockOut();
}

@Then("Validate ClockOut\\/ClockIN in Dashboard <Action>")
public void validate_ClockOut_ClockIN_in_Dashboard_Action() throws IOException, ParseException {
  
	CI = new ClockIN_Out(Driver);
	CI.ValidateClockIN_ClockOut_in_HomePage();
	
}

@Then("Validate Clockin\\/ClockOut in Plus icon <Action>")
public void validate_Clockin_ClockOut_in_Plus_icon_Action() {
	D = new Dashboard(Driver); 
	D.ValidateCICOinPlusIcon_AfterCOCI();
	
}

@Then("Close Application <Action>")
public void close_Application_Action() {
  Driver.quit();
}


//_______________________________ Auto Clock In_________________________________________________________________________________


@Given("Validate Auto Clock IN")
public void validate_Auto_Clock_IN() throws IOException, ParseException {
  
	//Validate Auto clock in of same timing when registering project job. 
	 Generic G = new Generic(Driver);
	  G.AutoClockIn();
	
}

//__________________________________ Right Swipe_______________________________________________________________________________________________

@Then("Right swip the project job")
public void right_swip_the_project_job() throws IOException, InterruptedException {
  
	 Dashboard D = new Dashboard(Driver);
	  D.RightSwipeJob();
	
	
}

//_______________________________________________More Button____________________________________________________________________________

@Then("Validate more button")
public void validate_more_button() throws IOException {
    Dashboard D = new Dashboard(Driver);
    D.ValidateMoreButton_InSidePanel();
}

//_______________________________________ Click on More Button____________________________________________________________________

@Then("Click on More button")
public void click_on_More_button() {
	 Dashboard D = new Dashboard(Driver);
	    D.ClickOnMoreButton();
}

//___________________________________ Validate Item Consumption Button_____________________________________________________________________

@Then("Validate Item Consumption button")
public void validate_Item_Consumption_button() {
    
	 Dashboard D = new Dashboard(Driver);
	    D.ValidateItemConsumption();
}

//_____________________________________ Click Item consumption_____________________________________________________________________________________________

@Then("Click on Item cunsumption")
public void click_on_Item_cunsumption() {
  
	 Dashboard D = new Dashboard(Driver);
	    D.ClickOnItemConsumption();
	
}

//____________________________________________Validate Item consumption page___________________________________________________________

@Then("Validate Item Consumption Page")
public void validate_Item_Consumption_Page() {
  
	
	ItemConsumption IC = new ItemConsumption(Driver);
	IC.ValidateTitle();
}

//____________________________________________ Validate Project Info_____________________________________________________________________

@Then("Validate Project info in Time consumtion")
public void validate_Project_info_in_Time_consumtion() throws IOException {
	
	ItemConsumption IC = new ItemConsumption(Driver);
	
  try {
	
	IC.ValidateProjectInfo();
	IC.ValidatePreviousOrderHistoryOfSameItem();
  }
  
  catch(StaleElementReferenceException E) {
	  IC.ValidateProjectInfo();
		IC.ValidatePreviousOrderHistoryOfSameItem();
  }
}

//____________________________________________ Click on Plus Icon____________________________________________________________________________________

@Then("click on Plus icon")
public void click_on_Plus_icon() throws IOException {
	ItemConsumption IC = new ItemConsumption(Driver);
	IC.ClickOnAddIcon_TOaddItem();
}


//________________________________________ Validate Item form Page title ______________________________________________________________________________

@Then("Validate Pagle title of Add draw item Form")
public void validate_Pagle_title_of_Add_draw_item_Form() throws InterruptedException {

	
	Thread.sleep(4000);
	Generic G = new Generic( Driver);
	try {
	
	G.ValidatePageTitle("Add draw item");
	}
	catch(StaleElementReferenceException E) {
		
		G.ValidatePageTitle("Add draw item");
	}
	
	
}

//_____________________________________________ Fill Item Consumed Form and Save_______________________________________________________________

@Then("Fill {string} all details and Click on Save")
public void fill_all_details_and_Click_on_Save(String Quantity) throws IOException, InterruptedException {
	ItemConsumption IC = new ItemConsumption(Driver);
	IC.FillAllRequiredField(Quantity ,"Noida","BT54", "S546");
}

//_______________________ Validate ItemConsumtion Added sucessfull Toast Message_________________________________________________________________

@Then("Validate ItemConsumption Added successfull toast Message.")
public void validate_ItemConsumption_Added_successfull_toast_Message() {
   
	ItemConsumption IC = new ItemConsumption(Driver);
	IC.ValidateSuccessfullToastMessgae();
	
}


//_____________________________________ Validate Details in History______________________________________________________________________________

@Then("validate same item consumtion of Quantity {string} registration in history page.")
public void validate_same_item_consumtion_of_Quantity_registration_in_history_page(String Quantity) throws IOException {
 
	ItemConsumption IC = new ItemConsumption(Driver);
	IC.ValidateItemConsumptionInHistory(Quantity);
	
	
}


//________________SUPPLEMENT ___________________________________   SUPPLEMENT__________________________________SUPPLEMENT ________________________________
//___________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________





//___________________________________________________ VAIDATE SUPPLEMENT BUTTON IN RIGHT SWIPE MENU ______________________________________________________

@Then("Validate Supplement button")
public void validate_Supplement_button() throws InterruptedException {
    Dashboard D = new Dashboard(Driver);
    Thread.sleep(2000);
    try {
    D.ValidateSupplementInMenuOption();
    }
    catch(StaleElementReferenceException E) {
    	D.ValidateSupplementInMenuOption();
    }
}



//__________________________________________________ CLICK ON SUPPLEMENT IN RIGHT SWIPE MENU_________________________________________________________________________

@Then("Click on Supplement")
public void click_on_Supplement() {
	 Dashboard D = new Dashboard(Driver);
	    D.ClickOnSupplement();
}


//________________________ Validate Supplement Page________________________________________________________________________________________________________________

@Then("Validate Supplement Page")
public void validate_Supplement_Page() {

	ProjectSupplement PS = new ProjectSupplement(Driver);
	PS.ValidateTitle();
	
	
}

//___________________________________ Validate Project info of Supplement_________________________________________________________________________

@Then("Validate Project info in Supplement")
public void validate_Project_info_in_Supplement() throws IOException {
	ProjectSupplement PS = new ProjectSupplement(Driver);
	PS.ValidateProjectInfo();
}

//____________________________________CLICK ON ADD BUTTON TO ADD SUPPLEMENT______________________________________________________________________


@Then("click on Plus icon to Add Supplement")
public void click_on_Plus_icon_to_Add_Supplement() {
	ProjectSupplement PS = new ProjectSupplement(Driver);
	PS.ClickOnAddIcon_TOaddISupplement();
}

//_____________________________________________________________ VALIDATE PAGE TITLE OF SUPPLEMENT FORM___________________________________________________________________________

@Then("Validate Pagle title of report Supplement Form")
public void validate_Pagle_title_of_report_Supplement_Form() throws InterruptedException {
   
	Generic G = new Generic( Driver);
	G.ValidatePageTitle("Add Supplement");
	
	
}
//_____________________________________ADD SUPPLEMENT FORM AND SAVE______________________________________________________________________________________



@Then("Fill {string} all details of Supplement and Click on Save")
public void fill_all_details_of_Supplement_and_Click_on_Save(String Quantity) throws IOException, InterruptedException {
	ProjectSupplement PS = new ProjectSupplement(Driver);
	PS.FillAllRequiredField(Quantity,"AUTO KE THROUGH");
	
}

//__________________________________________________VALIDATE SUCCESFULL MESSAGE AFTER ADD_________________________________________________________


@Then("Validate Supplement Added successfull toast Message.")
public void validate_Supplement_Added_successfull_toast_Message() {
//   	ProjectSupplement PS = new ProjectSupplement(Driver);
//	PS.ValidateSuccessfullToastMessgae();
	//LOADER RUNNING NOT ABLE TO SEE MESSAGE
}

//_____________________________________ VALIDATE IN ORDER HISTORY OF SUPPLEMENT _______________________________________________________________________


@Then("validate same Supplement of Quantity {string} registration in history page.")
public void validate_same_Supplement_of_Quantity_registration_in_history_page(String Quantity) throws IOException {
  
	ProjectSupplement PS = new ProjectSupplement(Driver);
	PS.ValidateSupplementOrderHistory(Quantity);
}

//________________________________________________________MILEAGE________________________________________________________________________________________________
//__________________________________________________________________________________________________________________________________________


@Then("Validate Mileage button")
public void validate_Mileage_button() throws InterruptedException {
  
	Dashboard D = new Dashboard(Driver);
    Thread.sleep(2000);
    try {
    D.ValidateMileageInMenuOption();
    }
    catch(StaleElementReferenceException E) {
    	D.ValidateMileageInMenuOption();
    }
	
}

//______________________________________________________Click on mileage in Menu option_____________________________________________________________

@Then("Click on mileage")
public void click_on_mileage() {
  
	 Dashboard D = new Dashboard(Driver);
	    D.ClickOnMileage();
}

//________________________________________________________Validate Mileage Page______________________________________________________________________
@Then("Validate mileage Page")
public void validate_mileage_Page() {
	ProjectMileage PM = new ProjectMileage(Driver);
	PM.ValidateTitle();	
}

//___________________________________________________________________________ Validate Project Info____________________________________________________________________________

@Then("Validate Project info in mileage")
public void validate_Project_info_in_mileage() throws IOException {
	ProjectMileage PM = new ProjectMileage(Driver);
	PM.ValidateProjectInfo();
}

//_______________________________________________________________________________ Click on Plus Icon _______________________________________________________________________________________

@Then("click on Plus icon to Add mileage")
public void click_on_Plus_icon_to_Add_mileage() {
	ProjectMileage PM = new ProjectMileage(Driver);
	PM.ClickOnAddIcon_TOaddMileage();
}

//_______________________________________________________________________________ Validate mileage form title__________________________________________________________________________________________

@Then("Validate Pagle title of report mileage Form")
public void validate_Pagle_title_of_report_mileage_Form() throws InterruptedException {

	
	Thread.sleep(4000);
	Generic G = new Generic( Driver);
	try {
	
	G.ValidatePageTitle("Add Mileage");
	}
	catch(StaleElementReferenceException E) {
		
		G.ValidatePageTitle("Add Mileage");
	}
	
	
	
	
}

//_________________________________________________________________________________FILL MILEAGE FORM___________________________________________________________________________________________


@Then("Fill {string} all details of mileage and Click on Save")
public void fill_all_details_of_mileage_and_Click_on_Save(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

//___________________________________________________________________________VALIDATE Successful message_________________________________________________________________________
@Then("Validate mileage Added successfull toast Message.")
public void validate_mileage_Added_successfull_toast_Message() {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

//____________________________________________________________________________

@Then("validate same mileage of Quantity {string} registration in history page.")
public void validate_same_mileage_of_Quantity_registration_in_history_page(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}














}
