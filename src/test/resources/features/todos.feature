Feature: Manage to-dos
  As a user of the application
  I want to be able to manage my to-dos
  So I can add, edit, delete and list my groups to-dos

  Background:
    Given I have authenticated in the app to see my to-dos

  Scenario: List my groups to-dos
    Then The list of to-dos must have a size greater than 0