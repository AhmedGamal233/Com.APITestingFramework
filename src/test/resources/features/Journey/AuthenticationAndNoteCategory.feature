@Regression
Feature: Verify user login properties and context endpoints
  Background: Set base configuration to use
    Given i generate user tokens
      |username        |msa@mimonote.com|
      |password        |123456@a|
      |deviceServiceId |string|


  Scenario: verify signOut of user
    Given user try to sign out
    Then Status code 200 is returned


  Scenario: verify Note Categories items
    Given user open note categories
      | name                    |a|
      | noteTypeId              |0|
      | defNoteTypeId           |0|
      | parentNoteCategoryId    |0|
      | defParentNoteCategoryId |0|
      | pageIndex               |10|
      | pageSize                |20|
    When note categories has items
    Then Verify following fields
      | items | isNotNull |  |
      | items | arrayWithSize |20|



