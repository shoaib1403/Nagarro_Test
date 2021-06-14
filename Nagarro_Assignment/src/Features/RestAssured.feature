Feature: Testing a REST API with GET and POST methods
  Users should be able to submit GET and POST requests to a REST API 

  Scenario: Post JSON parameters
  	Given I set POST user service api endpoint
    When I set request HEADER
    And Send a POST HTTP request
    Then I receive valid response code 201
    And I verify JSON parameters

  Scenario: Get first name
  	Given I set GET user service api endpoint
    When I set request HEADER
    And Send a GET HTTP request
    Then I receive valid response code 200
    And I verify first name