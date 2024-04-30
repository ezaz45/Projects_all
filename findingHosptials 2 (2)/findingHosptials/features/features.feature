Feature: finding hosptials

  Scenario: Get doctor detials and surgiers
    Given practo website is open and working
    When set location as chennai
    And set specialist as dentist
    And sort the paitent stories
    And sort the experience
    And sort the fees and availability
    And sort one of the relevance
    Then print first five doctor detials
    And click on surgiers
    Then print list of surgiers

  Scenario: validate demo form with invalid detials
    Given practo website is open and working
    When taking demo form is open
    And set detials for all fields
    Then check whether the submit button disabled or not

  Scenario: validate demo form with valid detials
    Given practo website is open and working
    When taking demo form is opened
    And set valid detials for all fields
    Then check whether the submit button enabled or not
    And check thank you message displayed
