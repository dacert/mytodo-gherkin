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