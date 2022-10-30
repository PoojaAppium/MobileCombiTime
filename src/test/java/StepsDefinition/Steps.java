package StepsDefinition;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import POM.Dashboard;
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
public void validate_toast_message_Wrong_Credentials_Entered() throws InterruptedException {
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
	
@Then("Quite Application")
public void quite_Application() {
 Driver.quit();
}
	
}
