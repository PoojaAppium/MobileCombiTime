Feature: Login Feature

  @Login @Smoke
  Scenario Outline: Login using valid credentials
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "<TerminalID>"
    And Fill password "<Password>"
    And Click on Login
    Then Validate Home Page UI
    And Quite Application

    Examples: 
      | TerminalID |  | Password |
      | 6239194-10 |  |    12345 |

  @Login
  Scenario Outline: Login Using Invalid Password
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "<TerminalID>"
    And Fill password "<Password>"
    And Click on Login
    Then Validate toast message <Wrong Credentials Entered>
    And Quite Application

    Examples: 
      | TerminalID |  | Password |
      | 6239194-10 |  |  1289345 |

  @Login
  Scenario Outline: Login Using Invalid Terminal ID
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "<TerminalID>"
    And Fill password "<Password>"
    And Click on Login
    Then Validate toast message <Wrong Credentials Entered>
    And Quite Application

    Examples: 
      | TerminalID |  | Password |
      | 6239194-12 |  |    12345 |

  @Login
  Scenario Outline: Login Using nonexisting Terminal ID
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "<TerminalID>"
    And Fill password "<Password>"
    And Click on Login
    Then Validate toast message <Terminal app not found>
    And Quite Application

    Examples: 
      | TerminalID  |  | Password |
      | 62397vh4-12 |  |   123745 |

  @Login
  Scenario Outline: Login empty Terminal ID and empty password
    Given Application Launch
    When Application Open
    Then Validate Combitime Logo
    And Fill Terminal ID "<TerminalID>"
    And Fill password "<Password>"
    And Click on Login
    Then Validate toast message <Please enter your Terminal Id and Password to login>
    And Quite Application

    Examples: 
      | TerminalID |  | Password |
      |            |  |          |
