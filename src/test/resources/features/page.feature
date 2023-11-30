#@BeforeAll
@testAll
Feature: User opens About us page


  Scenario Outline: User on the expected page
    Given they are on "<page>"
    Then the "<page>" will be correct
    Examples:
      | page               |
      | home-page          |
      | contact-sales-page |
