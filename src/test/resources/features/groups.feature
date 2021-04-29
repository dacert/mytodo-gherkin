Feature: Create group
  As a user of the application
  I want to be able to manage my groups
  So I can store my non-personal Todo's

  Background:
    Given I have authenticated in the app

  Scenario: List the groups
    When I see the groups list
    Then I should see the groups that I'm member

  Scenario: View details of a group that I am member
    When I see a group 'foo'
    Then I should see the details of the group

  Scenario: Create a group
    When I create a group called 'foo'
    And I view my groups
    Then I should see a group 'foo'

  Scenario: Creating a group with a duplicate name
    When I try to create a group called 'foo'
    Then I should see that the group name is invalid

  Scenario: Change the name of a group that I'm the owner
    When I rename a group 'foo' to 'foo2'
    And I view my groups
    Then I should see a group 'foo2'

  Scenario: Change the name of a group that I'm the owner with an existing name
    When I try to rename a group 'foo' to 'foo'
    Then I should see that the group name is invalid

  Scenario: Add member to a group that I am the owner
    When I add a member 'email' to a group 'foo'
    And I see a group 'foo'
    Then I should see a member 'email'

  Scenario: Remove member from a group that I'm the owner
    When I remove a member 'email' from a group 'foo'
    And I see a group 'foo'
    Then I should not see a member 'email'

  Scenario Outline: Email validation
    Given I try to add a new member with email "<Email>"
    Then I should see that the email is <Valid or Invalid>

    Examples:
      | Email | Valid or Invalid |
      | email@dominio.com | valid |
      | email123@dominio.com | valid |
      | emaildominio.com | invalid |
      | email | invalid |

  Scenario Outline: Name validation
    Given I try to create a group with name "<Name>"
    Then I should see that the name is <Valid or Invalid>

    Examples: Letters and Numbers
    Names need only letters and numbers to be valid

      | Name | Valid or Invalid |
      | abc1 | valid |
      | ABC | valid |
      | 123 | valid |
      | Abcd1 | valid |
      | Abcd 1 | invalid |
      | Abc_d1* | invalid |

  Scenario: Delete a group different to the Personal group
    When I delete a group 'foo'
    And I view my groups
    Then I should not see a group 'foo'