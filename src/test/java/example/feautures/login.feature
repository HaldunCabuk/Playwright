Feature: Login on swag Labs

  Scenario: login

    Given user navigate to "https://www.saucedemo.com/"
    When user logins with the following credentials
      | username | standard_user |
      | password | secret_sauce  |
    Then login should be successful
    And sort the products Z to A
    And products should be in descending order