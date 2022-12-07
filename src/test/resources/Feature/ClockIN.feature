Feature: ClockIN Feature

  Background: Login
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "6239194-9"
    And Fill password "12345"
    And Click on Login
    Then Validate Home Page UI

  @CI
  Scenario: Register ProductionType Job
    Given Home Page Opens
    When Wait for 7 Seconds
    When Click on Plus ICON
    And Click on ClockIN button
    Then Validate page Title of <Clock IN>
    When Make Clock IN
    Then Validate Clock IN in Dashboard
