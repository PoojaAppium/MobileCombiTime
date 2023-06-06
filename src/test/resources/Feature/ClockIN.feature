@CICO1
Feature: ClockIN Feature

  Background: Login
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "6239194-9"
    And Fill password "12345"
    And Click on Login
    Then Validate Home Page UI

  @ClockIn
  Scenario: Register Make Clock In
    Given Home Page Opens
    When Wait for 7 Seconds
    Then Validate Home Page Clock in & Clock out
    When Click on Plus ICON
    And Click on ClockIN button
    Then Validate page Title of <Clock IN>
    When Make Clock IN
    Then Validate Clock IN in Dashboard
    Then Validate Clock Out in Plus icon

  @ClockOut
  Scenario: Register Make Clock Out
    Given Home Page Opens
    When Wait for 7 Seconds
    Then Validate Home Page Clock in & Clock out
    When Click on Plus ICON
    And Click on ClockOut button
    Then Validate page Title of <Clock Out>
    When Make Clock Out
    Then Validate Clock Out in Dashboard
    Then Validate Clock in in Plus icon

  @ClockOut/ClockIN1
  Scenario Outline: Register Make Clock Out
    Given Home Page Opens <Action>
    When Wait for 7 Seconds <Action>
    Then Validate Home Page Clock in & Clock out <Action>
    When Click on Plus ICON <Action>
    And Click on ClockOut/ClockIN button <Action>
    Then Validate page Title of ClockOut/ClockIN <Action>
    When Make ClockOut/ClockIN <Action>
    Then Validate ClockOut/ClockIN in Dashboard <Action>
    Then Validate Clockin/ClockOut in Plus icon <Action>
    Then Close Application <Action>

    Examples: |Action|
      | a |
      | b |
