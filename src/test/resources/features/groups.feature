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
    And I enter "email@dominio.com; mail123@dominio.com" into the members input
    And I tap the save button
    Then I should see "foo" group in a groups list

  Scenario: Creating a group with a duplicate name
    When I tap the Add button
    And I wait to see the create dialog
    And I enter "duplicate" into the name input
    And I tap the save button
    Then I should see a "Name is duplicated" message

  Scenario Outline: Create Group validation
    When I tap the Add button
    And I wait to see the create dialog
    And I enter "<Name>" into the name input
    And I enter "<Members>" into the members input
    Then I should see the save button in "<Enabled>" state

    Examples: Letters and Numbers and semicolon separated emails
    Names need only letters and numbers to be valid.
    The members are a valid email list separated by semicolon.

      | Name | Members | Enabled |
      | Abcd 1 | email@dominio.com | false |
      | Abc_d1* | email@dominio.com; | false |
      | Abcd1 | email@domin | false |
      | Abc | email@dominio.com mail123@dominio.com | false |
      | Abcd1 | email@dominio.com; mail123@dominio.com | true |

  Scenario: View details of a group that I am member
    When I tap in an edit icon of a group named "details"
    And I wait to see the details dialog
    Then I should see the details dialog with a group named "details"

  Scenario Outline: Change the name of a group that I'm the owner
    When I tap in an edit icon of a group named "foo"
    And I wait to see the details dialog
    And I enter "<Name>" into the name input
    And I tap the save button
    Then I should see a "<Message>" message
    And I should "<See>" a clickable group with "<Name>" name in a groups list

    Examples:
      | Name | See | Message |
      | duplicate | false | Name is duplicated |
      | foo2 | true | Success |

  Scenario Outline: Edit name validation
    When I tap in an edit icon of a group named "foo2"
    And I wait to see the details dialog
    And I enter "<Name>" into the name input
    And I enter "<Members>" into the members input
    Then I should see the save button in "<Enabled>" state

    Examples: Letters and Numbers and semicolon separated emails
    Names need only letters and numbers to be valid.
    The members are a valid email list separated by semicolon.

      | Name | Members | Enabled |
      | Abcd 1 | email@dominio.com | false |
      | Abc_d1* | email@dominio.com; | false |
      | Abcd1 | email@domin | false |
      | Abc | email@dominio.com mail123@dominio.com | false |
      | Abcd1 | email@dominio.com; mail123@dominio.com | true |

  Scenario: Edit group's member in a group that I am the owner
    When I tap in an edit icon of a group named "foo2"
    And I wait to see the details dialog
    And I enter "email@dominio.com;mail123@dominio.com" into the members input
    And I tap the save button
    Then I tap in an edit icon of a group named "foo2"
    And I should see "email@dominio.com;mail123@dominio.com" members in the group

  Scenario: Delete a group
    When I tap in an edit icon of a group named "foo2"
    And I wait to see the details dialog
    And I tap the delete button
    And I tap the Ok button in the confirmation dialog
    Then I should not see "foo2" group in a groups list
