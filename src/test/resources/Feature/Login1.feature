Feature: Login Feature

  @LoginType2
  Scenario Outline: Login
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    When Fill Terminal ID "<Invalid TerminalID>"
    And Fill password "<Invalid Password>"
    And Click on Login
    Then Validate toast message <Wrong Credentials Entered>
    When Navigate to back
    And Fill Terminal ID "<Non Existing TerminalID>"
    And Fill password "<Invalid Password>"
    And Click on Login
    Then Validate toast message <Terminal app not found>
    When Navigate to back
    And Fill Terminal ID "<Empty TerminalID>"
    And Fill password "<Empty Password>"
    And Click on Login
    Then Validate toast message <Please enter your Terminal Id and Password to login>
    When Navigate to back
    And Fill Terminal ID "<TerminalID>"
    And Fill password "<Password>"
    And Click on Login
    Then Validate Home Page UI
    And Quite Application

    Examples: 
      | TerminalID |  | Password |  | Invalid TerminalID |  | Invalid Password |  | Non Existing TerminalID |  | Empty TerminalID |  | Empty Password |
      | 6239194-10 |  |    12345 |  | 6239194-12         |  |            65778 |  | 7675652-67              |  |                  |  |                |
