Feature: Personal group
  As a user of the application
  I want to have my own personal group by default
  So I can store my personal Todo's

  Scenario: Create the user's default group in the registration process
    When I register
    And I view my groups
    Then I should see a Personal group