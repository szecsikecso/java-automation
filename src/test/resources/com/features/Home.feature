@Home
Feature: HomePage
  Scenario Outline: To verify that the homepage is loading in
    Given User is on homepage
    Given blabla
    Then User can see <Text>
    Examples:
      | Text                                                                                    |
      | "Hello world!"                                                                          |
      | "Welcome to WordPress. This is your first post. Edit or delete it, then start writing!" |