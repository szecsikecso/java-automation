@Home
Feature: HomePage
  Scenario Outline: To verify that the homepage is loading in
    Given User is on homepage
    Then User can see <Text>
    Examples:
      | Text           |
      | "Hello World!" |