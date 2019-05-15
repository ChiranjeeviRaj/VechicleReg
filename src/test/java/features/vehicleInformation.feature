Feature: Vehicle Information Verification

  Scenario: Searching of Vehicle through registration number
    Given a Gov Vehicle Verification page opened
    When Click Get Started and enter registration number and click on continue button
    Then verifies make and color of the vehicle if the registration found
    And close the browser