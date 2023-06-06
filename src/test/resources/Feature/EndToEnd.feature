Feature: Punch Attend Punch Task

  @Tag1
  Scenario: Login
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "PP-1"
    And Fill password "12345"
    And Click on Login
    Then Validate Home Page UI
    When Home Page Opens
    When Wait for 5 Seconds
    When Click on Plus ICON
    And Click on Jobs button
    Then Validate page Title of <Jobs>
    When Click on Filter icon
    And Deselect Internal Job
    And Select Project Type job
    And Click on cancel button
    Then Validate page Title of <Jobs>
    When Click On Planned Section
    Then Validate job in Planned section.
    When Flag on of 1st job.
    And Click On Select Section
    Then Validate job in Selected section
    When Click on Submit button
    Then Validate Toast Message "Added successfully"
    When Click on Plus ICON
    And Click on Jobs button
    When Click On Running section
    Then Validate job in Running section.
    When Navigate Back
    Then Validate Time registration
    And Validate Auto Clock IN

  @TTag2
  Scenario Outline: Login
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "PP-1"
    And Fill password "12345"
    And Click on Login
    Then Validate Home Page UI
    And Right swip the project job
    Then Validate more button
    And Click on More button
    Then Validate Item Consumption button
    And Click on Item cunsumption
    Then Validate Item Consumption Page
    And Validate Project info in Time consumtion
    And click on Plus icon
    Then Validate Pagle title of Add draw item Form
    And Fill "<Quantity>" all details and Click on Save
    And Validate ItemConsumption Added successfull toast Message.
    Then validate same item consumtion of Quantity "<Quantity>" registration in history page.

    Examples: 
      | Quantity |
      |       66 |

  @TTag3
  Scenario Outline: Supplement
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "PP-1"
    And Fill password "12345"
    And Click on Login
    Then Validate Home Page UI
    And Right swip the project job
    Then Validate more button
    And Click on More button
    Then Validate Supplement button
    And Click on Supplement
    Then Validate Supplement Page
    And Validate Project info in Supplement
    And click on Plus icon to Add Supplement
    Then Validate Pagle title of report Supplement Form
    And Fill "<Quantity>" all details of Supplement and Click on Save
    And Validate Supplement Added successfull toast Message.
    Then validate same Supplement of Quantity "<Quantity>" registration in history page.

    Examples: 
      | Quantity | 
      |       76 | 
      
      
      
      
       @TTag4
  Scenario Outline: Mileage
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "PP-1"
    And Fill password "12345"
    And Click on Login
    Then Validate Home Page UI
    And Right swip the project job
    Then Validate more button
    And Click on More button
    Then Validate Mileage button
    And Click on mileage
    Then Validate mileage Page
    And Validate Project info in mileage
    And click on Plus icon to Add mileage
    Then Validate Pagle title of report mileage Form
    And Fill "<Quantity>" all details of mileage and Click on Save
    And Validate mileage Added successfull toast Message.
    Then validate same mileage of Quantity "<Quantity>" registration in history page.

    Examples: 
      | Quantity | 
      |       76 | 
