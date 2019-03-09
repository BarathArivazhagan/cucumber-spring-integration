Feature: To save the customer with customer details

  Scenario: client makes call to POST /customers/new to save the customer
    Given the customer with customer name "barath" and customer id 7777
    When the client calls "/customer" with the given details
    Then the client receives status code of 200
    And the response contains customer name "barath"
