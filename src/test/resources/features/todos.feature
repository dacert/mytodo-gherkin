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