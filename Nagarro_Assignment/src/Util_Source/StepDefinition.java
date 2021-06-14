package Util_Source;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.annotation.en.Given; 
import cucumber.annotation.en.Then; 
import cucumber.annotation.en.When;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
public class StepDefinition {

    @Given("^I set POST user service api endpoint$")
    public void i_set_post_user_service_api_endpoint() throws Throwable {
        throw new PendingException();
    }

    @Given("^I set GET user service api endpoint$")
    public void i_set_get_user_service_api_endpoint() throws Throwable {
        throw new PendingException();
    }

    @When("^I set request HEADER$")
    public void i_set_request_header() throws Throwable {
        throw new PendingException();
    }

    @Then("^I receive valid response code 201$")
    public void i_receive_valid_response_code_201() throws Throwable {
        throw new PendingException();
    }

    @Then("^I receive valid response code 200$")
    public void i_receive_valid_response_code_200() throws Throwable {
        throw new PendingException();
    }

    @And("^Send a POST HTTP request$")
    public void send_a_post_http_request() throws Throwable {
        throw new PendingException();
    }

    @And("^I verify JSON parameters$")
    public void i_verify_json_parameters() throws Throwable {
        throw new PendingException();
    }

    @And("^Send a GET HTTP request$")
    public void send_a_get_http_request() throws Throwable {
        throw new PendingException();
    }

    @And("^I verify first name$")
    public void i_verify_first_name() throws Throwable {
        throw new PendingException();
    }

}