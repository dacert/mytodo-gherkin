Feature: Manage groups
  As a user of the application
  I want to be able to manage my groups
  So I can store my non-personal Todo's

  Background:
    Given I have authenticated in the app
    And I tap the menu button
    And I tap the Groups menu item

  Scenario: List the groups
    Then The list of groups must have a size greater than 0

  Scenario: Create a group
    When I tap the Add button
    And I wait to see the create dialog
    And I enter "foo" into the name input
    And I enter "email@dominio.com; mail123@dominio.com" into the member input
    And I tap the save button
    Then I should see "foo" group in a groups list

  Scenario: Creating a group with a duplicate name
    When I tap the Add button
    And I wait to see the create dialog
    And I enter "duplicate" into the name input
    And I tap the save button
    Then I should see a "Name is duplicated" message

  Scenario Outline: Create Group name validation
    When I tap the Add button
    And I wait to see the create dialog
    And I enter "<Name>" into the name input
    Then I should see the save button in "<Enabled>" state

    Examples: Letters and Numbers
    Names need only letters and numbers to be valid

      | Name | Enabled |
      | Abcd 1 | false |
      | Abc_d1* | false |

  Scenario: Delete a group
    When I tap in a "foo" group
    And I wait to see the details dialog
    And I tap the delete button
    And I tap the Ok button in the confirmation dialog
    Then I should not see "foo" group in a groups list
