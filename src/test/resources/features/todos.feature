Feature: Manage to-dos
  As a user of the application
  I want to be able to manage my to-dos
  So I can add, edit, delete and list my groups to-dos

  Background:
    Given I've logged on the application
    And I click the menu button
    And I click the Groups menu item
    And I click in an edit icon of a group named "default"

  Scenario: List my groups to-dos
    Then The list of to-dos must have a size greater than 0

  Scenario: Create a to-do
    When I tap the Add to-do button
    And I wait to see the to-do create dialog
    And I enter "Note to do" into the text input
    And I tap the save to-do button
    Then I should see a "Note to do" to-do in a to-dos list

  Scenario: Edit a to-do that I have created
    When I tap in a "Note to do" to-do
    And I wait to see the to-do edit dialog
    And I enter "Note to do, edited" into the text input
    And I tap the save to-do button
    Then I should see a "Note to do, edited" to-do in a to-dos list

  Scenario Outline: Edit to-do validation
    When I tap in a "Note to do, edited" to-do
    And I wait to see the to-do edit dialog
    And I enter "<Text>" into the text input
    Then I should see the save to-do button in "<Enabled>" state

    Examples:

      | Text  | Enabled |
      |  | false |
      | Note to do  | true |
