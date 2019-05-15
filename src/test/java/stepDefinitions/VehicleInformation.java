package stepDefinitions;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import com.exercise.service.com.exercise.service.model.VehicleModel;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import utilities.GenericActions;

public class VehicleInformation {
	
	private GenericActions actions = new GenericActions();

    @Given("^a Gov Vehicle Verification page opened$")
    public void a_gov_vehicle_verification_page_opened() {
    	actions.openBrowser();
    	
    }

    @When("^Click Get Started and enter registration number and click on continue button$")
    public void click_get_started_and_enter_registration_number_and_click_on_continue_button(){
    	actions.navigateToRegistrationPage();
    }

    @Then("^verifies make and color of the vehicle if the registration found$")
    public void verifies_make_and_color_of_the_vehicle_if_the_registration_found() throws IOException{
    	Map<String, VehicleModel> vehicleModel  = actions.fetchCSVData();
    	for (Entry<String, VehicleModel> entry : vehicleModel.entrySet()) {
    		actions.navigateToRegistrationServicePage();
    		actions.clickGetStartedAndEnterRegDetailsAndSubmit(entry.getKey());
        	Assert.assertEquals(entry.getValue().getMake(), actions.getVehicleMake());
        	Assert.assertEquals(entry.getValue().getColor(), actions.getVehicleColor());
        	actions.reporting("PASS", entry.getKey());
        	actions.writeIntoLogs("PASS for the Reg " + entry.getKey());
        	actions.takeScreenShot();
		}
    	
    }

    @And("^close the browser$")
    public void close_the_browser()  {
    	actions.closeBrowser();
    }

}
