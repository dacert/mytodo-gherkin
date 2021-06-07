Feature: Manage groups
  As a user of the application
  I want to be able to manage my groups
  So I can store my non-personal Todo's

  Background:
    Given I have authenticated in the app
    And I tap the menu button
    And I tap the Groups menu item

  @enabled
  Scenario: List the groups
    Then The list of groups must have a size greater than 0

  Scenario: Create a group
    When I tap the Add button
    And I enter "foo" into the name input
    And I tap the create button
    Then I should see 'foo' group in a groups list

  Scenario: Creating a group with a duplicate name
    When I tap the Add button
    And I enter "duplicate" into the name input
    And I tap the create button
    Then I should see a "Name is duplicated" message

  Scenario Outline: Create Group name validation
    When I tap the Add button
    And I enter "<Name>" into the name input
    Then I should see an error text "<Invalid>"

    Examples: Letters and Numbers
    Names need only letters and numbers to be valid

      | Name | Invalid |
      | Abcd 1 | invalid |
      | Abc_d1* | invalid |

  Scenario: View details of a group that I am member
    When I tap in a "details" group
    Then I should see the Details screen of "details" group

  Scenario Outline: Change the name of a group that I'm the owner
    When I tap in a "foo" group
    And I enter "<Name>" into the name input
    And I tap the Edit button
    Then I should see a "<Message>" message
    And I should see "<Name>" group in a groups list

    Examples:
      | Name | Message |
      | duplicate | Name is duplicated |
      | foo2 | Susses |

  Scenario Outline: Edit name validation
    When I tap in a "foo" group
    And I enter "<Name>" into the name input
    Then I should see an error text "<Invalid>"

    Examples: Letters and Numbers
    Names need only letters and numbers to be valid

      | Name | Invalid |
      | Abcd 1 | invalid |
      | Abc_d1* | invalid |

  Scenario: Edit group's member in a group that I am the owner
    When I tap in a "foo2" group
    And I enter "email@dominio.com; mail123@dominio.com" into the member input
    And I tap the Edit button
    Then I should see "foo2" group in a groups list

  Scenario: Delete a group different to the Personal group
    When I tap in a "foo2" group
    And I tap the Delete button
    And I tap the Ok button in the confirmation dialog
    Then I should not see "foo2" group in a groups list