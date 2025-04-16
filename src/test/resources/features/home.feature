@testAll
Feature: User opens pages on OrangeHRM website

  Scenario Outline: User is on the expected page
    Given they are on "<page>"
    Then the "<page>" will be correct

    Examples:
      | page               |
      | home-page          |
      | contact-sales-page |
