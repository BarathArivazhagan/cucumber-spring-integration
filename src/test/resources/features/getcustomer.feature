Feature: To retrieve the customer with customer details

  Scenario: retrieve the customer with customer id
    Given the customer saved with customer name "barath" and customer id 100
    When the client calls GET "/customer/{customerId}" with customer id as 100
    Then the client receives status code of 200
    And the response contains customer name "barath"

  Scenario: retrieve the customer with customer name
    When the client calls GET "/customer/byName" with customer name as "barath" in query param
    Then the client receives status code of 200
    And the response contains customer name "barath"
