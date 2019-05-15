$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("vehicleInformation.feature");
formatter.feature({
  "line": 1,
  "name": "Vehicle Information Verification",
  "description": "",
  "id": "vehicle-information-verification",
  "keyword": "Feature"
});
formatter.scenario({
  "line": 3,
  "name": "Searching of Vehicle through registration number",
  "description": "",
  "id": "vehicle-information-verification;searching-of-vehicle-through-registration-number",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 4,
  "name": "a Gov Vehicle Verification page opened",
  "keyword": "Given "
});
formatter.step({
  "line": 5,
  "name": "Click Get Started and enter registration number and click on continue button",
  "keyword": "When "
});
formatter.step({
  "line": 6,
  "name": "verifies make and color of the vehicle if the registration found",
  "keyword": "Then "
});
formatter.step({
  "line": 7,
  "name": "close the browser",
  "keyword": "And "
});
formatter.match({
  "location": "VehicleInformation.a_gov_vehicle_verification_page_opened()"
});
formatter.result({
  "duration": 9316549900,
  "status": "passed"
});
formatter.match({
  "location": "VehicleInformation.click_get_started_and_enter_registration_number_and_click_on_continue_button()"
});
formatter.result({
  "duration": 2222932600,
  "status": "passed"
});
formatter.match({
  "location": "VehicleInformation.verifies_make_and_color_of_the_vehicle_if_the_registration_found()"
});
formatter.result({
  "duration": 15061931800,
  "status": "passed"
});
formatter.match({
  "location": "VehicleInformation.close_the_browser()"
});
formatter.result({
  "duration": 1086180900,
  "status": "passed"
});
});