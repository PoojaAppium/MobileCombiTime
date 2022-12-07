Feature: Job Feature

  Background: Login
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "6239194-9"
    And Fill password "12345"
    And Click on Login
    Then Validate Home Page UI

  @Job
  Scenario: Register ProductionType Job
    Given Home Page Opens
    When Wait for 5 Seconds
    When Click on Plus ICON
    And Click on Jobs button
    Then Validate page Title of <Jobs>
    When Click on Filter icon
    And Deselect Internal Job
    And Select Production Type job
    And Click on cancel button
    Then Validate page Title of <Jobs>
    When Click On Planned Section
    Then Validate job in Planned section.
    When Flag on of 1st job.
    And Click On Select Section
    Then Validate job in Selected section
    When Click on Submit button
    Then Validate Toast Message "Jobs has been updated"
    When Click On Running section
    Then Validate job in Running section.
    When Navigate Back
    Then Validate Time registration
